package in.nic.pes.lgd.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.controllers.ManageRestructuredLBController.EffectiveDateList;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.common.FileUploadUtility;
import in.nic.pes.lgd.constant.CommanConstant;
import in.nic.pes.lgd.forms.DistrictDataForm;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.DistrictInfo;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.ShiftVillageForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.SubdistrictDataForm;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.ConfigGovtOrderService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.SubDistrictService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.validator.CommonValidatorImpl;
import in.nic.pes.lgd.validator.DistrictValidator;
import in.nic.pes.lgd.validator.VillageValidator;
import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

@Controller
public class DistrictController { // NO_UCD (unused code)
	private static final Logger LOG = Logger.getLogger(DistrictController.class);

	@SuppressWarnings("unused")
	private int Itr = 0;

	@SuppressWarnings("unused")
	private long roleCode = 1000;

	@Autowired
	private CommonValidatorImpl commonValidator;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private StateService stateService;

	@Autowired
	private DistrictValidator districtValidator;

	@Autowired
	private SubDistrictService subdistrictService;

	@Autowired
	private VillageService villageService;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private VillageValidator villageValidator;

	@Autowired
	private GovernmentOrderService govtOrderService;

	@Autowired
	private ConfigGovtOrderService configGovtOrderService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private FileUploadUtility fileUploadUtility;

	@Autowired
	private EmailService emailService;
	
	
	
	@Autowired
	private SubDistrictService subDistrictService;

	/**
	 * List of Attachments for Government Order and Map.
	 */
	List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
	List<Attachment> attachmentList = new ArrayList<Attachment>();
	
	private Integer stateCode;

	private Integer districtCode;

	private Long userId;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		setGlobalParams(session);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	private void setGlobalParams(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId();
	}

	@RequestMapping(value = "/invalidatedistrict", method = RequestMethod.GET)
	public ModelAndView preInvalidateDistrict(@ModelAttribute("invalidateDistrict") DistrictForm districtForm, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			if (stateCode == null) {
				return mav = new ModelAndView("redirect:login.htm");
			}

			// mv=new ModelAndView("invalidateDistrictDefault");
			List<District> districtList = null;
			districtList = new ArrayList<District>();
			districtList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
			// //for configuration start 24 jan
			// Copy to Check Configuration
			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 6, 'D');// 10
																								// is
																								// operation
																								// code
																								// for
																								// create
																								// new
																								// village
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			String message = hMap.get("message");
			if (govtOrderConfig != null && mapConfig != null) {
				mav = new ModelAndView("invalidateDistrictDefault"); // need to
																		// be
																		// modify
				mav.addObject("hideMap", mapConfig);
				mav.addObject("govtOrderConfig", govtOrderConfig);
				mav.addObject("districtList", districtList);

			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			}
			// /for configuration end
			// mv.addObject("districtList",districtList);
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	// for configuration start 24 jan
	@RequestMapping(value = "/enterInvalidateDistrictOrderDetails", method = RequestMethod.POST)
	public ModelAndView enterInvalidateDistrictOrderDetails(@ModelAttribute("invalidateDistrict") DistrictForm districtForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mv = null;
		try {

			// /////////// DROP DOWN VALIDATIONS /////////////////////

			

			// ////////code for government order
			// checking///////////////////////////////////
			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 10, 'V');// 10
																								// is
																								// operation
																								// code
																								// for
																								// create
																								// new
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false

			// ////////code for government order
			// checking///////////////////////////////////
			districtValidator.validateInvalidateDistrict(districtForm, result);
			String listformat = districtForm.getListformat();
			// String tlc=listformat.substring(listformat.indexOf('#')+1,
			// listformat.indexOf(':'));
			if (result.hasErrors() || listformat == null) {
				if (govtOrderConfig != null && mapConfig != null) {
					List<District> districtList = null;
					districtList = new ArrayList<District>();
					districtList = this.getDistrictList(stateCode, httpSession, request);
					mv = new ModelAndView("invalidateDistrictDefault");
					mv.addObject("govtOrderConfig", districtForm.getGovtOrderConfig());
					mv.addObject("districtList", districtList);
					mv.addObject(districtForm);
				}
			} else {
				if (!listformat.equals("@")) {
					listformat = listformat.substring(1, listformat.length() - 1);
					districtForm.setListformat(listformat);
				} else {
					String list = null;
					list = districtForm.getTargetDistrictYes().toString() + "#";
					String subdistlist[] = districtForm.getSubDistrictList().split(",");

					for (int i = 0; i < subdistlist.length; i++) {
						list += subdistlist[i] + ":";

					}
					list = list.substring(0, list.length() - 1);
					districtForm.setListformat(list);
				}
				govtOrderConfig = districtForm.getGovtOrderConfig();
				httpSession.setAttribute("formbean", districtForm);

				mv = new ModelAndView("redirect:govtOrderCommon.htm");
				mv.addObject("govtOrderConfig", govtOrderConfig);
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	// for configuration end
	@RequestMapping(value = "/invalidateDistrict", method = RequestMethod.POST)
	public ModelAndView InvalidateDistrict(@ModelAttribute("invalidateDistrict") DistrictForm districtForm, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mv = null;
		try {
			httpSession.setAttribute("formbean", districtForm);
			mv = new ModelAndView("redirect:govtOrderCommon.htm");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

		return mv;
	}

	@RequestMapping(value = "/invalidateDistrictFinal", method = RequestMethod.POST)
	public ModelAndView InvalidateDistrictFinal(@ModelAttribute("invalidateDistrict") DistrictForm districtForm, BindingResult result, HttpServletRequest request, SessionStatus status, HttpSession httpSession,
			@RequestParam(value = "fileName", required = false) String fileName) throws Exception {
		districtForm = (DistrictForm) httpSession.getAttribute("formbean");
		boolean success = false;
		ModelAndView mv = null;
		String orderCode = null;
		String tid = null;
		String successResult = null;
		Session session=null;
		try {

			List<District> dlist = new ArrayList<District>();
			dlist = districtService.getDistrictListByDistCode(Integer.parseInt(districtForm.getDistrictNameEnglish()));

			List<DistrictForm> listdetail = new ArrayList<DistrictForm>();
			listdetail = districtService.getMergeDistrictWithSubDistrictWithSubdistrict(districtForm.getListformat());
			districtForm.setUserId(userId.intValue());
			successResult = districtService.saveInvalidateDistrict(districtForm, httpSession);

			if (successResult == null) {
				mv = new ModelAndView("error");
				mv.addObject("message", "invalidate information is not save successfully");
				return mv;
			} else {
				String[] ldata = successResult.split(",");
				orderCode = ldata[0];
				tid = ldata[1];
			}
			int Transactionid = Integer.parseInt(tid);
			int ocode = Integer.parseInt(orderCode);

			//GovernmentOrderForm govtOrderForm = null;
			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
			// values==govtOrderUpload,govtOrderGenerate

			if (districtForm.getGovtOrderConfig().equals("govtOrderUpload")) {
				//govtOrderForm = (GovernmentOrderForm) httpSession.getAttribute("govtOrderForm");
				AddAttachmentBean addAttachmentBean = (AddAttachmentBean) httpSession.getAttribute("addAttachmentBean");
				AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
				attachmentHandler.validateAttachment(addAttachmentBean);
				metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
				attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}

			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			try {
				/*
				 * GovernmentOrder govtOrder = convertLocalbodyService
				 * .saveDataInGovtOrder(govtOrderForm, session);
				 */
				govtOrderService.saveDataInAttachmentWithOrderCode(ocode, metaInfoOfToBeAttachedFileList, session);
				/*
				 * govtOrderService.saveDataInGovtOrderEntityWise(govtOrder,
				 * districtForm.getDistrictNameEnglish(), 'D', session);
				 */
				tx.commit();
				success = true;
				session.close();

				EmailSmsThread est = new EmailSmsThread(Transactionid, 'D', emailService);
				est.start();
			} catch (Exception e) {
				tx.rollback();
				session.close();
				LOG.error("Exception-->" + e);
				success = false;
			}
			httpSession.removeAttribute("formbean");

			// success=true;
			if (success) {

				if (districtForm.getGovtOrderConfig().equals("govtOrderUpload")) {

					mv = new ModelAndView("invalidateDistSuccess");
					mv.addObject("districtBean", dlist);
					mv.addObject("districtBeanMerge", listdetail);
					// mv.addObject("dsdMapView", dsdMapView);
				} else if (districtForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					mv = new ModelAndView("viewGovtOrder");
					mv.addObject("filepath", "govtorder/" + fileName);

				}
				return mv;

			} else {
				String message = "Error";
				mv = new ModelAndView("error");
				mv.addObject("message", message);
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

	/*
	 * @RequestMapping(value="/add_district", method=RequestMethod.GET) public
	 * ModelAndView displayDistrict(@ModelAttribute("districtForm") DistrictForm
	 * districtForm, Model model) {
	 * 
	 * ModelAndView mv= new ModelAndView("adddistrict");
	 * mv.addObject("districtForm", districtForm); return mv; }
	 */

	@RequestMapping(value = "/abc", method = RequestMethod.POST)
	public ModelAndView saveDistrict(@ModelAttribute("districtForm") DistrictForm districtForm, Model model, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("home");
		try {
			districtService.saveDistrict(districtForm, request);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@ModelAttribute("stateSourceList")
	public List<State> populateSourceStateList(HttpServletRequest request) {

		try {
			return stateService.getStateSourceList();
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return null;
		}
	}

	/*
	 * @RequestMapping(value="/addNewDistrict", method=RequestMethod.POST)
	 * public ModelAndView addNewDistrict(@ModelAttribute
	 * ("district")DistrictForm district , BindingResult result, SessionStatus
	 * status,HttpServletRequest request) {
	 * LOG.debug("------------Add New District---"); try {
	 * districtService.saveDistrict(district,request); } catch (Exception e) {
	 * LOG.error("Exception-->"+e); ModelAndView mav = new
	 * ModelAndView("adddistrict"); mav.addObject("districtForm", new
	 * DistrictForm()); return mav; } //
	 * villageService.addVillage(addVillageNew); String
	 * aMessage="The new district is created and published successfully.";
	 * ModelAndView mv=new ModelAndView("newDistrictAdd"); mv.addObject("msgid",
	 * aMessage); mv.addObject("newDistrictAdd",district); return mv; }
	 */

	/**
	 * Modified by sushil
	 * */
	@RequestMapping(value = "/add_district", method = RequestMethod.GET)
	public ModelAndView showform(HttpSession httpSession, @ModelAttribute("district") DistrictForm districtForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			// Copy to Check Configuration
			Map<String, String> hMap = new HashMap<String, String>();
			districtForm.setStateCode(stateCode);
			
			// Please first check your operation code then set it in place of 10
			hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 3, 'D'); // 1
																							// is
																							// operation
																							// code
																							// for
																							// Create
																							// new
																							// State

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			String message = hMap.get("message");
			
			httpSession.removeAttribute("histroyDistrict");
			httpSession.removeAttribute("realCount");
			if (govtOrderConfig != null && mapConfig != null) {
				if (httpSession.getAttribute("realCount") == null) {
					/* added on 31/12/2014 for the localbody impact by kirandeep*/
					List<District> distList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
					model.addAttribute("visibilityanother", "display: inline;");
					model.addAttribute("display", "display: none;");
					model.addAttribute("disabled", false);
					model.addAttribute("visibilityNext", "display: none;");
					model.addAttribute("visibility", "display: inline;");
					model.addAttribute("distList", distList);
					model.addAttribute("totalVillage", "");
					model.addAttribute("districtNameList", "");
					mav = new ModelAndView("adddistrict");
					//mav.addObject("district", new DistrictForm());
				}
				mav.addObject("hideMap", mapConfig);
				mav.addObject("govtOrderConfig", govtOrderConfig);
			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			}
		} catch (NumberFormatException e) {
			LOG.error("Exception-->" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			LOG.error("Exception-->" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/* Modified by sushil */
	@RequestMapping(value = "/add_district_session", method = RequestMethod.GET)
	public ModelAndView showformSession(HttpSession httpSession, @ModelAttribute("district") DistrictForm districtForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			//List<SubDistrictForm> sdList = new ArrayList<SubDistrictForm>();
			List<Village> listVillage = new ArrayList<Village>();
			
			// Copy to Check Configuration
			Map<String, String> hMap = new HashMap<String, String>();
			districtForm.setStateCode(stateCode);
			
			// int stateCode=6;
			// Please first check your operation code then set it in place of 10
			hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 3, 'D');// 1
																							// is
																							// operation
																							// code
																							// for
																							// Create
																							// new
																							// State
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			String message = hMap.get("message");
			// httpSession.removeAttribute("stateCode");
			if (govtOrderConfig != null && mapConfig != null) {
				ArrayList<DistrictForm> listPreDistrictForms;
				ArrayList<DistrictForm> listDistrictForms;
				ArrayList<SubDistrictForm> listSubDistrictForms;
				ArrayList<SubDistrictForm> listpostSubDistrictForms;
				if (httpSession.getAttribute("realCount") == null) {
					List<District> distList = districtService.getDistrictList(stateCode);
					model.addAttribute("display", "display: none;");
					model.addAttribute("disabled", false);

					model.addAttribute("distList", distList);
					model.addAttribute("totalVillage", "");
					model.addAttribute("districtNameList", "");
					mav = new ModelAndView("adddistrict");
					mav.addObject("district", new DistrictForm());
				} else {
					String buttonClicked = request.getParameter("buttonClicked");
					if (buttonClicked != null && !buttonClicked.equals("") && !buttonClicked.equals("NextSession")) {
						listDistrictForms = new ArrayList<DistrictForm>();
						listSubDistrictForms = new ArrayList<SubDistrictForm>();
						listpostSubDistrictForms = new ArrayList<SubDistrictForm>();
						listPreDistrictForms = new ArrayList<DistrictForm>();
						List<District> distList = districtService.getDistrictList(stateCode);
						model.addAttribute("distList", distList);
						int realCount = 0;
						if (httpSession.getAttribute("realCount") != null) {
							realCount = (Integer) httpSession.getAttribute("realCount");
						}

						DistrictForm sessionDistrictForm = (DistrictForm) httpSession.getAttribute("lstDistForm" + realCount);
					
						String preDistrict = sessionDistrictForm.getPreDistrict();
						if(!("").equals(preDistrict)){
							String[] arrayPreDistrict = preDistrict.split(",");
							for (int i = 0; i < arrayPreDistrict.length; i++) {
								DistrictForm tempdistrictForm = new DistrictForm();
								String[] preDistrictArray = arrayPreDistrict[i].split(":");
								tempdistrictForm.setDistrictNameEnglish(preDistrictArray[1]);
								tempdistrictForm.setDistrictCode(Integer.parseInt(preDistrictArray[0]));
								tempdistrictForm.setOperation_state(preDistrictArray[2]!=null?preDistrictArray[2].charAt(0):null);
								listPreDistrictForms.add(tempdistrictForm);

							}
						}
						

						String postDistrict = sessionDistrictForm.getDistrictNameEnglishTemp();
						String[] arrayPostDistrict = postDistrict.split(",");
						for (int i = 0; i < arrayPostDistrict.length; i++) {
							DistrictForm tempdistrictForm = new DistrictForm();
							String[] postDistrictArray = arrayPostDistrict[i].split(":");
							if (postDistrictArray[0].contains("PART")) {
								tempdistrictForm.setDistrictNameEnglish(postDistrictArray[1]);
								tempdistrictForm.setAliasEnglish(postDistrictArray[0]);
								listDistrictForms.add(tempdistrictForm);
							}

						}

						String preSubDistrict = sessionDistrictForm.getPreSubDistrictsTemp();
						if (preSubDistrict != null && preSubDistrict.contains(",")) { // added
																						// by
																						// sushil
																						// on
																						// 25-04-2013
							String[] arraypreSubDistrict = preSubDistrict.split(",");
							if (arraypreSubDistrict != null && arraypreSubDistrict.length > 1) { // added
																									// by
																									// sushil
																									// on
																									// 25-04-2013
								for (int i = 0; i < arraypreSubDistrict.length; i++) {
									SubDistrictForm object = new SubDistrictForm();
									String[] tempArray = arraypreSubDistrict[i].split(":");
									object.setSubdistrictNameEnglish(tempArray[1]);
									object.setSubdistrictCode((Integer.parseInt(tempArray[0])));
									listSubDistrictForms.add(object);
								}
							}
						}else if(preSubDistrict != null && preSubDistrict.length()>0){
							SubDistrictForm object = new SubDistrictForm();
							String[] tempArray =preSubDistrict.split(":");
							object.setSubdistrictNameEnglish(tempArray[1]);
							object.setSubdistrictCode((Integer.parseInt(tempArray[0])));
							listSubDistrictForms.add(object);
						}
						String postSubDistrict = sessionDistrictForm.getContributedSubDistrictsTemp();
						String[] arraypostSubDistrict = postSubDistrict.split(",");
						for (int i = 0; i < arraypostSubDistrict.length; i++) {
							SubDistrictForm object = new SubDistrictForm();
							String[] tempArray = arraypostSubDistrict[i].split(":");
							if (tempArray[0].contains("PART")) {
								object.setSubdistrictNameEnglish(tempArray[1]);
								object.setAliasEnglish(tempArray[0]);
								listpostSubDistrictForms.add(object);
							}

						}

						String totalVillage = sessionDistrictForm.getTotalVillage();
						if (totalVillage == null) {
							totalVillage = "";
						}

						String districtNameList = sessionDistrictForm.getDistrictNameList();
						if (districtNameList == null) {
							districtNameList = "";
						}

						if (buttonClicked != null && buttonClicked.equals("Next")) {
							model.addAttribute("visibilityanother", "display: none;");
							model.addAttribute("style", "display: none;");
							model.addAttribute("visibilityNext", "display: inline;");
							model.addAttribute("visibility", "display: none;");
						}

						if (buttonClicked != null && buttonClicked.equals("Add Another")) {
							model.addAttribute("visibilityanother", "display: inline;");
							model.addAttribute("style", "display: block;");
							model.addAttribute("visibilityNext", "display: none;");
							model.addAttribute("visibility", "display: inline;");
						}
						model.addAttribute("totalVillage", totalVillage);
						model.addAttribute("districtNameList", districtNameList);
						model.addAttribute("distList", listPreDistrictForms);
						model.addAttribute("disabled", true);
						model.addAttribute("display", "display: block;");
						model.addAttribute("listVill", listVillage);
						model.addAttribute("listDistrict", listDistrictForms);
						model.addAttribute("preSubDistrictList", listSubDistrictForms);
						model.addAttribute("listpostSubDistrictForms", listpostSubDistrictForms);
						model.addAttribute("districtCount", realCount);

						//model.addAttribute("district", httpSession.getAttribute("lstDistForm"));
						mav = new ModelAndView("adddistrict");
						mav.addObject("district", new DistrictForm()); // sessionDistrictForm
					} else if (buttonClicked != null && !buttonClicked.equals("") && buttonClicked.equals("NextSession")) {
						mav = new ModelAndView("redirect:govtOrderCommon.htm");
					}
				}

				mav.addObject("hideMap", mapConfig);
				mav.addObject("govtOrderConfig", govtOrderConfig);

			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			}

		} catch (NumberFormatException e) {
			LOG.error("Exception-->" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			LOG.error("Exception-->" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/* Modified by sushil */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addNewDistrict", method = RequestMethod.POST)
	public ModelAndView addNewDistrict(HttpSession httpSession, @ModelAttribute("district") DistrictForm districtForm, BindingResult bindingResult, SessionStatus status, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			Pattern p = Pattern.compile("[a-zA-Z 0-9]");
			int tmp = 0;
			boolean error = false;
			

			if (districtForm.getStatusDist().toString().equals("")) {
				tmp = httpSession.getAttribute("distfrm") != null ? Integer.parseInt(httpSession.getAttribute("distfrm").toString()) : 0;
			} else {
				tmp = Integer.parseInt(districtForm.getStatusDist());
				httpSession.setAttribute("distfrm", districtForm.getStatusDist());
			}
			if (tmp == 1) {
				
				if (districtForm.getDistrictNameInEn() != null && !districtForm.getDistrictNameInEn().equals("")) {
					if (districtForm.getDistrictNameInEn().length() <= 50) {
						boolean hasSpecialChar1 = p.matcher(districtForm.getDistrictNameInEn().toString()).find();
						if (hasSpecialChar1) {
							if (!districtForm.getDistrictNameEnglish().matches("PART")) {
								if (!(districtForm.getDistrictNameInLcl() == null || districtForm.getDistrictNameInLcl().equals(""))) {
									if (!(districtForm.getDistrictNameInLcl().length() <= 50)){
										error = true;
									}	
									/* note -local name have any charcater 
									  boolean hasSpecialChar = p.matcher(districtForm.getDistrictNameInLcl().toString()).find();
									if (!hasSpecialChar){
										error = true;
									}	 */
								}

								if (!(districtForm.getDistrictAliasInEn() == null || districtForm.getDistrictAliasInEn().equals(""))) {
									if (!(districtForm.getDistrictAliasInEn().length() <= 50)){
										error = true;
									}	
									boolean hasSpecialChar = p.matcher(districtForm.getDistrictAliasInEn().toString()).find();
									if (!hasSpecialChar){
										error = true;
									}	
								}

								if (!(districtForm.getDistrictAliasInLcl() == null || districtForm.getDistrictAliasInLcl().equals(""))) {
									if (!(districtForm.getDistrictAliasInLcl().length() <= 50)){
										error = true;
									}	
									boolean hasSpecialChar = p.matcher(districtForm.getDistrictAliasInLcl().toString()).find();
									if (!hasSpecialChar){
										error = true;
									}	
								}

								if (districtForm.getCensus2011Code() != null && !districtForm.getCensus2011Code().equals("")) {
									if (districtForm.getCensus2011Code().length() <= 6) {
										try {
											@SuppressWarnings("unused")
											int tmpcheck = Integer.parseInt(districtForm.getCensus2011Code().toString());
										} catch (NumberFormatException e) {
											error = true;
										}
									} else
										error = true;
								}

								if (!(districtForm.getHeadquarterName() == null || districtForm.getHeadquarterName().equals(""))) {
									if (!(districtForm.getHeadquarterName().length() <= 50)){
										error = true;
									}	
									boolean hasSpecialChar = p.matcher(districtForm.getHeadquarterName().toString()).find();
									if (!hasSpecialChar){
										error = true;
									}	
								}
							} else
								error = true;
						} else
							error = true;
					} else
						error = true;
				} else
					error = true;

				if (!error) {
					String temp = districtForm.getDistrictNameEnglish().replace("PART", "").replace("FULL", "");
					// String[] temp1 =
					// districtForm.getDistrictNameEnglish().split(",");
					model.addAttribute("listDistrict", temp);
					if (districtForm.getAttachFile1() != null && districtForm.getAttachFile1().size() > 0 && !districtForm.getAttachFile1().get(0).isEmpty()) {
						List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMapForDistrict(request, districtForm, bindingResult, mav);
						httpSession.setAttribute("validFileMap", validFileMap);
						districtForm.setMapUploaded(true);
					} else {
						districtForm.setMapUploaded(false);
					}
					if (bindingResult.hasErrors()) {
						/* added by sushil on 10-06-2013 */
						Map<String, String> hMap = new HashMap<String, String>();

						
						// Please first check your operation code then set it in
						// place of 10
						hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 3, 'D'); // 1
																										// is
																										// operation
																										// code
																										// for
																										// Create
																										// new
																										// State

						String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
						String mapConfig = hMap.get("mapUpload");// values==true,false
						//String message = hMap.get("message");

						if (govtOrderConfig != null && mapConfig != null) {
							if (httpSession.getAttribute("realCount") == null) {
								List<District> distList = districtService.getDistrictList(stateCode);
								model.addAttribute("visibilityanother", "display: inline;");
								model.addAttribute("display", "display: none;");
								model.addAttribute("disabled", false);
								model.addAttribute("visibilityNext", "display: none;");
								model.addAttribute("visibility", "display: inline;");
								model.addAttribute("distList", distList);
								model.addAttribute("listDistrict", null);
								model.addAttribute("totalVillage", "");
								model.addAttribute("districtNameList", "");
								mav = new ModelAndView("adddistrict");
								mav.addObject("district", new DistrictForm());
							}
							mav.addObject("hideMap", mapConfig);
							mav.addObject("govtOrderConfig", govtOrderConfig);
						}
						return mav;
						/* end by sushil on 10-06-2013 */
					}
					model.addAttribute("district", districtForm);
					httpSession.setAttribute("formbean", districtForm);
					return new ModelAndView("redirect:govtOrderCommon.htm");
				} else {
					mav = new ModelAndView("error");
					mav.addObject("message", "Something went wrong, kindly try again");
					return mav;
				}
			} else {
				int countDist = 1;
				if (httpSession.getAttribute("countDist") == null) {
					countDist = 1;
				} else {
					countDist = (Integer) httpSession.getAttribute("countDist");
				}

				if (districtForm.getButtonClicked() != null && !districtForm.getButtonClicked().equals("") && !districtForm.getButtonClicked().equals("NextSession")) {
					ArrayList<DistrictInfo> districtInfos = new ArrayList<DistrictInfo>();
					DistrictInfo districtInfo = new DistrictInfo();
					ArrayList<String> distFullList = new ArrayList<String>();
					ArrayList<String> subdistFullList = new ArrayList<String>();
					ArrayList<String> newsubdistFullList = new ArrayList<String>();

					String historyDist = districtForm.getHistoryDistrictList();
					String[] historyArrayDist = historyDist.split(",");
					for (int j = 0; j < historyArrayDist.length; j++) {
						distFullList.add(historyArrayDist[j]);
					}
					String historySubDist = districtForm.getHistrorySubDistrictList();
					String[] historyArraySubDist = historySubDist.split(",");
					for (int j = 0; j < historyArraySubDist.length; j++) {
						subdistFullList.add(historyArraySubDist[j]);
					}

					String historyNewSubDist = districtForm.getHistroryNewSubDistrict();
					String[] historyArrayNewSubDist = historyNewSubDist.split(",");
					for (int j = 0; j < historyArrayNewSubDist.length; j++) {
						newsubdistFullList.add(historyArrayNewSubDist[j]);
					}
					districtInfo.setVillageMergeList(districtForm.getHistroryMergeSubDistrictList());
					districtInfo.setVillageNewList(districtForm.getHistroryNewSubDistrictList());
					districtInfo.setDistrictName(districtForm.getDistrictNameInEn());
					districtInfo.setContributeDistrictList(distFullList);
					districtInfo.setContributeSubDistrictList(subdistFullList);
					districtInfo.setContributeNewCreatedSubDistrictList(newsubdistFullList);
					if (countDist == 1) {
						districtInfos.add(districtInfo);
						httpSession.setAttribute("histroyDistrict", districtInfos);
					} else {
						if(httpSession.getAttribute("histroyDistrict") != null){
							districtInfos = (ArrayList<DistrictInfo>) httpSession.getAttribute("histroyDistrict");
						}
						districtInfos.add(districtInfo);
						httpSession.setAttribute("histroyDistrict", districtInfos);
					}
					model.addAttribute("display", "display: block;");

					if (districtForm.getAttachFile1() != null) {
						if (!districtForm.getAttachFile1().get(0).isEmpty()) {
							List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMapForDistrict(request, districtForm, bindingResult, mav);
							httpSession.setAttribute("validFileMap" + countDist, validFileMap);
							districtForm.setMapUploaded(true);
						} else {
							districtForm.setMapUploaded(false);
						}
					} else {
						districtForm.setMapUploaded(false);
					}

					httpSession.setAttribute("lstDistForm" + countDist, districtForm);
					httpSession.setAttribute("realCount", countDist);

					if (districtForm.getButtonClicked().equalsIgnoreCase("Add Another")) {
						model.addAttribute("buttonClicked", "Add Another");
						model.addAttribute("style", "display: inline;");
						httpSession.setAttribute("countDist", countDist + 1);
						return new ModelAndView("redirect:add_district_session.htm");
					}
					if (districtForm.getButtonClicked().equalsIgnoreCase("Next")) {
						districtForm.setOperation('C');
						httpSession.setAttribute("formbean", districtForm);
						model.addAttribute("buttonClicked", "Next");
						model.addAttribute("style", "display: none;");
						httpSession.setAttribute("countDist", countDist);
						return new ModelAndView("redirect:add_district_session.htm");
					}
				} else if (districtForm.getButtonClicked() != null && !districtForm.getButtonClicked().equals("") && districtForm.getButtonClicked().equals("NextSession")) {
					mav = new ModelAndView("redirect:govtOrderCommon.htm");
					mav = new ModelAndView("redirect:govtOrderCommon.htm");
				} else {
					mav = new ModelAndView("error");
					mav.addObject("message", "Something went wrong, kindly try again");
					return mav;
				}
			}
		} catch (Exception e) {
			LOG.error("Exception-->" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/* End method for next button */

	@RequestMapping(value = "/shiftvillage", method = RequestMethod.GET)
	public ModelAndView showShiftVillageSubDistrict(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("shiftvillageDistrict");
		try {
			mav.addObject("shiftvillage", new ShiftVillageForm());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/shiftvillage", method = RequestMethod.POST)
	public String showShiftVillageform(HttpSession httpSession, @ModelAttribute("shiftvillage") ShiftVillageForm shiftVillageForm, Model model, HttpServletRequest request) {
		List<ShiftVillageForm> listDist = new ArrayList<ShiftVillageForm>();
		try {
			if (httpSession.getAttribute("lstShiftvillageForm") != null) {
				List<ShiftVillageForm> distritForm = new ArrayList<ShiftVillageForm>();
				distritForm = (List<ShiftVillageForm>) httpSession.getAttribute("lstShiftvillageForm");
				for (int i = 0; i < distritForm.size(); i++) {
					listDist.add(distritForm.get(i));
				}
			}
			listDist.add(shiftVillageForm);
			httpSession.setAttribute("lstShiftvillageForm", listDist);
			return "redirect:add_district.htm";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganize_subdistrict_village", method = RequestMethod.GET)
	public String reorganizeSubDistrictNewVillGet(HttpSession httpSession, @ModelAttribute("addVillageNew") VillageForm addVillageNew, Model model, HttpServletRequest request) {
		Itr = 1;
		String temp = null;
		try {
			if (httpSession.getAttribute("lstVillForm") != null) {
				List<VillageForm> villForm = new ArrayList<VillageForm>();
				villForm = (List<VillageForm>) httpSession.getAttribute("lstVillForm");
				String contriVill[] = villForm.get(villForm.size() - 1).getContributedVillages().split(",");
				if (contriVill.length > 0) {
					for (int i = 0; i < contriVill.length; i++) {
						if (i == 0 && contriVill[i].contains("PART")){
							temp = contriVill[i].replace("PART", "") + ",";
						}	
						if (contriVill[i].contains("PART")){
							temp += contriVill[i].replace("PART", "") + ",";
						}	
					}
				}
				if (temp != null) {
					temp += villForm.get(villForm.size() - 1).getVillageList().replace("PART", "").replace("FULL", "");
				} else
					temp = villForm.get(villForm.size() - 1).getVillageList().replace("PART", "").replace("FULL", "");
			} else {
				DistrictForm districtForm = (DistrictForm) httpSession.getAttribute("lstDistForm");
				temp = districtForm.getContributedVillages().replace("PART", "").replace("FULL", "");
			}
			List<Village> listVill = new ArrayList<Village>();
			listVill = subdistrictService.getVillageDetailforReorganize(temp);
			model.addAttribute("listVill", listVill);
			return "reorganize_subdistrict_village";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganize_subdistrict_village", method = RequestMethod.POST)
	public String reorganizeSubDistrictNewVill(HttpSession httpSession, @ModelAttribute("addVillageNew") VillageForm addVillageNew, Model model, HttpServletRequest request) {
		Itr = 1;
		List<VillageForm> listVill = new ArrayList<VillageForm>();
		try {
			if (httpSession.getAttribute("lstVillForm") != null) {
				List<VillageForm> villForm = new ArrayList<VillageForm>();
				villForm = (List<VillageForm>) httpSession.getAttribute("lstVillForm");
				for (int i = 0; i < villForm.size(); i++) {
					listVill.add(villForm.get(i));
				}
			}
			listVill.add(addVillageNew);
			httpSession.setAttribute("lstVillForm", listVill);

			return "redirect:add_district.htm?createdistrict";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

	}

	@RequestMapping(value = "/reorganize_subdistrict_Modify", method = RequestMethod.GET)
	public ModelAndView modifyVillageGet(HttpSession httpSession, @ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, Model model, HttpServletRequest request) {
		Itr = 1;
		String villCode = null;
		ModelAndView mv = null;
		try {
			villCode = (String) httpSession.getAttribute("villCodeModify");
			int villageCode = Integer.parseInt(villCode.replace("PART", "").replace("FULL", ""));
			List<VillageDataForm> listVillageDetails = villageService.getVillageDetailsModify(villageCode);
			mv = new ModelAndView("reorganize_subdistrict");
			model.addAttribute("size", listVillageDetails.size());
			model.addAttribute("listVillageDetails", listVillageDetails);
			modifyVillageCmd.setListVillageDetails(listVillageDetails);
			mv.addObject("modifyVillageCmd", modifyVillageCmd);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganize_subdistrict_Modify", method = RequestMethod.POST)
	public ModelAndView modifyVillage(HttpSession httpSession, @ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, BindingResult result, SessionStatus status, Model model, HttpServletRequest request) {
		Itr = 1;
		ModelAndView mav = null;
		try {
			List<VillageForm> listVillModify = new ArrayList<VillageForm>();
			villageValidator.validate(modifyVillageCmd, result);
			/*
			 * if(result.hasErrors()) { //mav=new
			 * ModelAndView("reorganize_subdistrict_Modify_Village"); }
			 */
			// else{
			if (httpSession.getAttribute("modifyVillForm") != null) {
				List<VillageForm> villForm = new ArrayList<VillageForm>();
				villForm = (List<VillageForm>) httpSession.getAttribute("modifyVillForm");
				for (int i = 0; i < villForm.size(); i++) {
					listVillModify.add(villForm.get(i));
				}
			}
			listVillModify.add(modifyVillageCmd);
			httpSession.setAttribute("modifyVillForm", listVillModify);
			Itr = 1;
			status.setComplete();
			mav = new ModelAndView("redirect:add_district.htm?modify");
			// }
			return mav;

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

	}

	// modify district old start
	@RequestMapping(value = "/modifyDistrict", method = RequestMethod.GET)
	public ModelAndView modifyDistrict(@ModelAttribute("modifyDistrictCmd") DistrictForm modifyDistrictCmd, Model model, HttpServletRequest request, @RequestParam("id") Integer districtCode,
			@RequestParam(value = "disturb", required = false) String disturb) {
		ModelAndView mv = null;
		try {
			List<DistrictDataForm> listdistrictDetails = districtService.getDistrictDetailsModify(districtCode);
			mv = new ModelAndView("modify_district");
			model.addAttribute("size", listdistrictDetails.size());
			model.addAttribute("listDistrictDetails", listdistrictDetails);
			model.addAttribute("disturb", disturb);
			modifyDistrictCmd.setListDistrictDetails(listdistrictDetails);

			mv.addObject("modify_district", modifyDistrictCmd);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/modifyDistrictAction", method = RequestMethod.POST)
	public String modifyDistrict(@ModelAttribute("modifyDistrictCmd") DistrictForm modifyDistrictCmd, BindingResult result, SessionStatus status, Model model, HttpServletRequest request,
			@RequestParam(value = "disturb", required = false) String disturb, HttpSession httpSession) {
		String viewModifyDistrictdetail = null;
		ModelAndView mav = new ModelAndView("modify_district");
		try {
			districtValidator.validate(modifyDistrictCmd, result);
			if (result.hasErrors()) {
				mav.addObject("modifyDistrictCmd", modifyDistrictCmd);
				return "modify_district";
			}

			// districtService.modifyDistrictInfo(modifyDistrictCmd,request,HttpSession);

			districtService.modifyDistrictInfo(modifyDistrictCmd, request, httpSession);

			int districtCode = 0;
			int districtVersion = 0;
			List<DistrictDataForm> listDistrict = new ArrayList<DistrictDataForm>();
			listDistrict = modifyDistrictCmd.getListDistrictDetails();
			Iterator<DistrictDataForm> itr = listDistrict.iterator();
			while (itr.hasNext()) {
				DistrictDataForm element = (DistrictDataForm) itr.next();
				districtCode = element.getDistrictCode();
				districtVersion = element.getDistrictVersion();
			}
			if (disturb.equals("true")) {
				viewModifyDistrictdetail = "redirect:viewResolveEntitiesinDisturbedState.htm?resolved=" + districtCode + "," + districtVersion;
			} else {
				viewModifyDistrictdetail = "redirect:viewModifyDistrictDetailCopy.htm?id=" + districtCode + "&type=S";
			}
			return viewModifyDistrictdetail;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

	}

	@RequestMapping(value = "/viewDistrictDetailforModify", method = RequestMethod.GET)
	public ModelAndView viewDistrictDetail(@ModelAttribute("districtView") DistrictForm districtView, Model model, HttpServletRequest request, @RequestParam("id") Integer districtCode, @RequestParam("type") String type) {
		ModelAndView mv = null;
		try {
			List<DistrictDataForm> listDistrictDetails = districtService.getDistrictDetailsModify(districtCode);
			/*if(listDistrictDetails!=null && !listDistrictDetails.isEmpty()){
				String cordinate=listDistrictDetails.get(0).getCordinate();
		        if(cordinate!=null && cordinate.indexOf(",")==-1 && cordinate.indexOf(":")>-1){
		        	cordinate=cordinate+",";
		        	listDistrictDetails.get(0).setCordinate(cordinate);
		        }
			}*/
			mv = new ModelAndView("view_districtdetail");
			districtView.setListDistrictDetails(listDistrictDetails);

			model.addAttribute("successMsg", "The district was modified successfully");
			return mv;

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}




	

	// Modify Change Village Post Method
	@RequestMapping(value = "/modifyDistrictChangeAction", method = RequestMethod.POST)
	public String modifyDistrictChange(@ModelAttribute("modifyDistrictCmd") DistrictForm modifyDistrictCmd, BindingResult result, SessionStatus status, Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "disturb", required = false) String disturb) throws Exception {

		String viewDistrictHistory = null;
		int districtCode = 0;
		//int districtVersion = 0;
		//boolean saveSuccess = false;
		AddAttachmentBean addAttachmentBean = null;
		try {
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();

			DistrictForm districtForm = (DistrictForm) session.getAttribute("formbean");
			districtForm.setStateCode(stateCode);
			
			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");

			if (session.getAttribute("addAttachmentBean") != null) {
				addAttachmentBean = (AddAttachmentBean) session.getAttribute("addAttachmentBean");
				attachmentHandler.validateAttachment(addAttachmentBean);
			}
			/*
			 * ======================================Attached File Saving Part
			 * ==========================================
			 */

			districtForm.setStateCode(stateCode);
			districtForm.setUserId(userId.intValue());
			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
			if (metaInfoOfToBeAttachedFileList != null && metaInfoOfToBeAttachedFileList.size() != 0) {

				districtService.changeDistrict(districtForm, govtOrderForm, metaInfoOfToBeAttachedFileList, request);
				attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
				districtService.changeDistrictforTemplate(districtForm, govtOrderForm, request, session);

			}
			List<DistrictDataForm> listdistrict = new ArrayList<DistrictDataForm>();
			listdistrict = districtForm.getListDistrictDetails();
			Iterator<DistrictDataForm> itr = listdistrict.iterator();
			while (itr.hasNext()) {
				DistrictDataForm element = (DistrictDataForm) itr.next();
				districtCode = element.getDistrictCode();
				element.getDistrictVersion();
			}
			viewDistrictHistory = "redirect:viewModifyDistrictDetailCopy.htm?id=" + districtCode + "&type=S";

			return viewDistrictHistory;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

	}

	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganizeSubDistrict", method = RequestMethod.GET)
	public ModelAndView reorganizeDistrictGet(HttpSession httpSession, @ModelAttribute("newsubdistrictform") SubDistrictForm newsubdistrictform, Model model, HttpServletRequest request) {
		Itr = 1;
		String temp = null;
		ModelAndView mav = null;
		try {
			if (httpSession.getAttribute("lstSubdistritForm") != null) {
				List<SubDistrictForm> subdistritForm = new ArrayList<SubDistrictForm>();
				subdistritForm = (List<SubDistrictForm>) httpSession.getAttribute("lstSubdistritForm");
				String contriVill[] = subdistritForm.get(subdistritForm.size() - 1).getContributedSubDistricts().split(",");
				if (contriVill.length > 0) {
					for (int i = 0; i < contriVill.length; i++) {
						if (i == 0 && contriVill[i].contains("PART")){
							temp = contriVill[i].replace("PART", "") + ",";
						}	
						if (contriVill[i].contains("PART")){
							temp += contriVill[i].replace("PART", "") + ",";
						}	
					}
				}
				if (temp != null) {
					temp += subdistritForm.get(subdistritForm.size() - 1).getSubDistrictList().replace("PART", "").replace("FULL", "");
				} else
					temp = subdistritForm.get(subdistritForm.size() - 1).getSubDistrictList().replace("PART", "").replace("FULL", "");
			} else {
				DistrictForm districtForm = (DistrictForm) httpSession.getAttribute("lstDistForm");
				temp = districtForm.getContributedSubDistricts().replace("PART", "").replace("FULL", "");
			}
			List<Subdistrict> listSD = new ArrayList<Subdistrict>();
			listSD = subdistrictService.getSubDistrictViewList("from Subdistrict where subdistrictCode IN (" + temp + ") and isactive=true");
			model.addAttribute("listSD", listSD);
			mav = new ModelAndView("reorganize_Subdistrict");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganizeSubDistrict", method = RequestMethod.POST)
	public String reorganizeSubDistrictNewVill(HttpSession httpSession, @ModelAttribute("newsubdistrictform") SubDistrictForm newsubdistrictform, Model model, HttpServletRequest request) {
		List<SubDistrictForm> listVill = new ArrayList<SubDistrictForm>();
		try {
			if (httpSession.getAttribute("lstSubdistritForm") != null) {
				List<SubDistrictForm> subdistritForm = new ArrayList<SubDistrictForm>();
				subdistritForm = (List<SubDistrictForm>) httpSession.getAttribute("lstSubdistritForm");
				for (int i = 0; i < subdistritForm.size(); i++) {
					listVill.add(subdistritForm.get(i));
				}
			}

			//System.out.println("------------contributedSubDistricts----------------" + newsubdistrictform.getContributedSubDistricts());
			listVill.add(newsubdistrictform);

			httpSession.setAttribute("lstSubdistritForm", listVill);
			return "redirect:add_district.htm?modify";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/preview_districtnew", method = RequestMethod.GET)
	public String previewSubdistrictGet(HttpSession httpSession, @ModelAttribute("newDistrictPreview") DistrictForm sdForm, BindingResult result, HttpServletRequest request, Model model) {
		try

		{
			String sdFullList = null;
			List<Village> villListforFullSD = null;
			List<Subdistrict> SubListforFullSD = null;
			DistrictForm subDistrictForm = (DistrictForm) httpSession.getAttribute("lstDistForm");
			model.addAttribute("newDistrictPreview", subDistrictForm);
			String temp = subDistrictForm.getDistrictNameEnglish().replace("PART", "").replace("FULL", "");
			String[] temp1 = subDistrictForm.getDistrictNameEnglish().split(",");

			// String
			// temp2=subDistrictForm.getContributedSubDistricts().replace("PART",
			// "").replace("FULL", "");

			List<District> listDistrict = null;
			listDistrict = new ArrayList<District>();
			listDistrict = districtService.getDistrict("from District where districtCode IN (" + temp + ") and isactive=true");
			for (int i = 0; i < listDistrict.size(); i++) {
				if (temp1[i].contains("FULL")) {
					if (sdFullList != null) {
						sdFullList += listDistrict.get(i).getDistrictCode() + ",";
					} else {
						sdFullList = listDistrict.get(i).getDistrictCode() + ",";
					}
				}
				listDistrict.get(i).setDistrictNameEnglish(listDistrict.get(i).getDistrictNameEnglish() + " (" + (temp1[i].contains("FULL") ? "FULL)" : "PART)"));
				listDistrict.get(i).setAliasEnglish(listDistrict.get(i).getDistrictCode() + (temp1[i].contains("FULL") ? "FULL" : "PART")); // temporarily
																																			// holding
																																			// sub
																																			// district
																																			// code
																																			// in
																																			// alias
			}

			// code to get Subdistrict list----------
			temp = null;
			List<Subdistrict> listSubdistrit = new ArrayList<Subdistrict>();
			List<SubDistrictForm> sdList = new ArrayList<SubDistrictForm>();
			sdList = (List<SubDistrictForm>) httpSession.getAttribute("lstSubdistritForm");

			if (httpSession.getAttribute("lstSubdistritForm") != null) {
				if (sdList.get(sdList.size() - 1).getContributedSubDistricts() != null) {
					String contriSD[] = sdList.get(sdList.size() - 1).getContributedSubDistricts().split(",");
					for (int i = 0; i < contriSD.length; i++) {
						if (i == 0 && contriSD[i].contains("PART")){
							temp = contriSD[i].replace("PART", "") + ",";
						}	
						if (contriSD[i].contains("PART")){
							temp += contriSD[i].replace("PART", "") + ",";
						}	
					}
				}
				String subDistritNames = sdList.get(sdList.size() - 1).getContributedSubDistricts();
				if (subDistritNames != null) {
					if (temp != null) {
						temp = subDistritNames.replace("PART", "").replace("FULL", "");
					} else
						temp = subDistritNames.replace("PART", "").replace("FULL", "");
				}
			} else {
				DistrictForm districtF = new DistrictForm();
				districtF = (DistrictForm) httpSession.getAttribute("lstDistForm");
				if (districtF.getContributedSubDistricts() != null)

				{

					temp = districtF.getContributedSubDistricts().replace("PART", "").replace("FULL", "");
					// temp=subDistrictForm.getContributedSubDistricts().replace("PART",
					// "").replace("FULL", "");
				}
			}

			if (subDistrictForm.getContributedSubDistricts() != null) {
				String[] temp3 = subDistrictForm.getContributedSubDistricts().split(",");
				listSubdistrit = subdistrictService.getSubDistrictViewList("from Subdistrict where subdistrictCode IN (" + temp + ") and isactive=true");

				for (int i = 0; i < listSubdistrit.size(); i++) {
					try {
						listSubdistrit.get(i).setSubdistrictNameEnglish(listSubdistrit.get(i).getSubdistrictNameEnglish() + " (" + (temp3[i].contains("FULL") ? "FULL)" : "PART)"));
						listSubdistrit.get(i).setAliasEnglish(listSubdistrit.get(i).getTlc() + (temp3[i].contains("FULL") ? "FULL" : "PART")); // temporarily
																																				// holding
																																				// sub
																																				// district
																																				// code
																																				// in
																																				// alias
					} catch (Exception e) {
						LOG.error("Exception-->" + e);
					}
				}
			}
			if (httpSession.getAttribute("lstSubdistritForm") != null) {
				for (int k = 0; k < sdList.size(); k++) {
					Subdistrict subdistrict = null;
					subdistrict = new Subdistrict();
					subdistrict.setSubdistrictNameEnglish(sdList.get(k).getSubdistrictNameEnglish() + " (New)");
					listSubdistrit.add(subdistrict);
				}
			}

			// code to get Subdidtrict modify list----------
			if (httpSession.getAttribute("modifySubdistrictForm") != null) {
				List<SubDistrictForm> sd = new ArrayList<SubDistrictForm>();
				sd = null;
				sd = (List<SubDistrictForm>) httpSession.getAttribute("modifySubdistrictForm");
				//List<SubdistrictDataForm> listVillageData = new ArrayList<SubdistrictDataForm>();
				//SubDistrictForm villageForm = new SubDistrictForm();
				//List<SubdistrictDataForm> villDataForm = new ArrayList<SubdistrictDataForm>();
				//villDataForm = villageForm.getListSubdistrictDetails();
				for (int k = 0; k < sd.size(); k++) {
					Subdistrict vill = null;
					vill = new Subdistrict();
					vill.setSubdistrictNameEnglish(sd.get(k).getListSubdistrictDetails().get(0).getSubdistrictNameEnglish() + " (Modified)");
					listSubdistrit.add(vill);
				}
			}

			// code to get village list----------
			temp = null;
			List<Village> listVillage = new ArrayList<Village>();
			List<VillageForm> villForm = new ArrayList<VillageForm>();
			villForm = (List<VillageForm>) httpSession.getAttribute("lstVillForm");

			if (httpSession.getAttribute("lstVillForm") != null) {
				if (villForm.get(villForm.size() - 1).getContributedVillages() != null) {
					String contriVill[] = villForm.get(villForm.size() - 1).getContributedVillages().split(",");
					for (int i = 0; i < contriVill.length; i++) {
						if (i == 0 && contriVill[i].contains("PART")){
							temp = contriVill[i].replace("PART", "") + ",";
						}	
						if (contriVill[i].contains("PART")){
							temp += contriVill[i].replace("PART", "") + ",";
						}	
					}
				}
				if (temp != null) {
					temp += villForm.get(villForm.size() - 1).getContributedVillages().replace("PART", "").replace("FULL", "");
				} else
					temp = villForm.get(villForm.size() - 1).getContributedVillages().replace("PART", "").replace("FULL", "");

			} else {
				DistrictForm districtF = new DistrictForm();
				districtF = (DistrictForm) httpSession.getAttribute("lstDistForm");
				if (districtF.getContributedVillages() != null) {
					temp = districtF.getContributedVillages().replace("PART", "").replace("FULL", "");
				}
			}
			try {
				listVillage = villageService.getVillageViewList("from Village where villageCode IN (" + temp + ") and isactive=true");
			} catch (Exception e) {
				LOG.error("Exception-->" + e);
			}
			if (httpSession.getAttribute("lstVillForm") != null) {

				for (int k = 0; k < villForm.size(); k++) {
					Village vill = null;
					vill = new Village();
					vill.setVillageNameEnglish(villForm.get(k).getNewVillageNameEnglish() + " (New)");
					listVillage.add(vill);
				}
			}

			// code to get Shift village list----------
			temp = null;
			List<ShiftVillageForm> listVill = new ArrayList<ShiftVillageForm>();
			ShiftVillageForm shiftVillageForm = new ShiftVillageForm();

			listVill = (List<ShiftVillageForm>) httpSession.getAttribute("lstShiftvillageForm");
			if (httpSession.getAttribute("lstShiftvillageForm") != null) {
				if (listVill.get(listVill.size() - 1).getVillageNameEnglish() != null) {
					String contriVill[] = listVill.get(listVill.size() - 1).getVillageNameEnglish().split(",");
					for (int i = 0; i < contriVill.length; i++) {
						if (i == 0 && contriVill[i].contains("PART")){
							temp = contriVill[i].replace("PART", "") + ",";
						}	
						if (contriVill[i].contains("PART")){
							temp += contriVill[i].replace("PART", "") + ",";
						}	
					}
				}
				if (temp != null) {
					temp += listVill.get(listVill.size() - 1).getVillageNameEnglish().replace("PART", "").replace("FULL", "");
				} else
					temp = listVill.get(listVill.size() - 1).getVillageNameEnglish().replace("PART", "").replace("FULL", "");

			} else {
				if (shiftVillageForm.getVillageNameEnglish() != null) {
					temp = shiftVillageForm.getVillageNameEnglish().replace("PART", "").replace("FULL", "");

					listVillage = villageService.getVillageViewList("from Village where villageCode IN (" + temp + ") and isactive=true");
				}
			}
			if (httpSession.getAttribute("lstShiftvillageForm") != null) {
				for (int k = 0; k < listVill.size(); k++) {
					Village vill = null;
					vill = new Village();
					vill.setVillageNameEnglish(listVill.get(k).getVillageNameEnglish() + " (Shift)");
					listVillage.add(vill);
				}
			}

			// -- code to get modified village list----
			if (httpSession.getAttribute("modifyVillForm") != null) {
				villForm = null;
				villForm = (List<VillageForm>) httpSession.getAttribute("modifyVillForm");
				//List<VillageDataForm> listVillageData = new ArrayList<VillageDataForm>();
				//VillageForm villageForm = new VillageForm();
				//List<VillageDataForm> villDataForm = new ArrayList<VillageDataForm>();
				//villDataForm = villageForm.getListVillageDetails();
				for (int k = 0; k < villForm.size(); k++) {
					Village vill = null;
					vill = new Village();
					vill.setVillageNameEnglish(villForm.get(k).getListVillageDetails().get(0).getVillageNameEnglish() + " (Modified)");
					listVillage.add(vill);
				}
			}

			/*
			 * //code to get Shift village list---------- temp=null;
			 * villForm=(List<VillageForm>)
			 * httpSession.getAttribute("lstShiftvillageForm");
			 * if(httpSession.getAttribute("lstShiftvillageForm")!=null){
			 * if(villForm
			 * .get(villForm.size()-1).getContributedVillages()!=null){ String
			 * contriVill
			 * []=villForm.get(villForm.size()-1).getContributedVillages
			 * ().split(","); for (int i=0;i<contriVill.length;i++){ if(i==0 &&
			 * contriVill[i].contains("PART"))
			 * temp=contriVill[i].replace("PART", "")+",";
			 * if(contriVill[i].contains("PART"))
			 * temp+=contriVill[i].replace("PART", "")+","; } } if (temp!=null)
			 * { temp+=villForm.get(villForm.size()-1).getContributedVillages().
			 * replace("PART", "").replace("FULL", ""); } else
			 * temp=villForm.get(
			 * villForm.size()-1).getContributedVillages().replace("PART",
			 * "").replace("FULL", "");
			 * 
			 * } else { ShiftVillageForm districtF=new ShiftVillageForm();
			 * 
			 * if (districtF.getVillageNameEnglish()!=null){
			 * temp=districtF.getVillageNameEnglish().replace("PART",
			 * "").replace("FULL", ""); } }try {
			 * listVillage=villageService.getVillageViewList
			 * ("from Village where villageCode IN (" + temp +
			 * ") and isactive=true"); } catch (Exception e) { exception
			 * LOG.error("Exception-->"+e); }
			 * if(httpSession.getAttribute("lstShiftvillageForm")!=null){
			 * 
			 * for(int k=0; k<villForm.size();k++){ Village vill=null; vill=new
			 * Village();
			 * vill.setVillageNameEnglish(villForm.get(k).getNewVillageNameEnglish
			 * ()+"(Shift)"); listVillage.add(vill); } }
			 */
			/*
			 * List<District> distList=new ArrayList<District>(); String
			 * districtsourcecode= subDistrictForm.getDistrictsourcecode();
			 * LOG.debug("districtsourcecode----"+districtsourcecode);
			 * if(districtsourcecode !=null) {
			 * distList=districtDAO.getDistrictListbyDistCode
			 * ((subDistrictForm.getDistrictsourcecode()));
			 * if(distList.size()>0) {
			 * 
			 * } LOG.debug("distList is"+distList);
			 * model.addAttribute("distName",
			 * distList.get(0).getDistrictNameEnglish());
			 */model.addAttribute("listdist", listDistrict);
			model.addAttribute("listSD", listSubdistrit);
			model.addAttribute("listVill", listVillage);

			/*
			 * if(sdFullList!=null){ sdFullList=sdFullList.substring(0,
			 * sdFullList.length()-1); DisListforFullSD=new
			 * ArrayList<District>();
			 * DisListforFullSD=districtService.getDistrictViewList
			 * ("from District v where v.state.statePK.stateCode IN (" +
			 * sdFullList + ") and isactive=true"); for(int
			 * i=0;i<villListforFullSD.size();i++){
			 * listdistrit.add(DisListforFullSD.get(i)); } }
			 */
			if (sdFullList != null) {
				sdFullList = sdFullList.substring(0, sdFullList.length() - 1);
				SubListforFullSD = new ArrayList<Subdistrict>();
				SubListforFullSD = subdistrictService.getSubDistrictViewList("from Subdistrict v where v.district.districtPK.districtCode IN (" + sdFullList + ") and isactive=true");
				for (int i = 0; i < SubListforFullSD.size(); i++) {
					listSubdistrit.add(SubListforFullSD.get(i));
				}
			}

			if (sdFullList != null) {
				sdFullList = sdFullList.substring(0, sdFullList.length() - 1);
				villListforFullSD = new ArrayList<Village>();
				villListforFullSD = villageService.getVillageViewList("from Village v where v.subdistrict.subdistrictPK.subdistrictCode IN (" + sdFullList + ") and isactive=true");
				for (int i = 0; i < villListforFullSD.size(); i++) {
					listVillage.add(villListforFullSD.get(i));
				}
			}
			httpSession.setAttribute("contributedDs", listDistrict);
			httpSession.setAttribute("contributedSDs", listSubdistrit);
			httpSession.setAttribute("contributedVills", listVillage);

			/*
			 * model.addAttribute("newDistrictPreview", subDistrictForm);
			 */
			return "preview_districtnew";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

	}

	@RequestMapping(value = "/preview_districtnew", method = RequestMethod.POST)
	public ModelAndView previewSubdistrict(HttpSession httpSession, @ModelAttribute("district") DistrictForm sdForm, HttpServletRequest request, Model model) {
		ModelAndView mv = null;
		try {
			sdForm.setOperation('C');
			String govtOrderConfig = null;
			govtOrderConfig = sdForm.getGovtOrderConfig();
			httpSession.setAttribute("formbean", sdForm);
			mv = new ModelAndView("redirect:govtOrderCommon.htm");
			mv.addObject("govtOrderConfig", govtOrderConfig);
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	@RequestMapping(value = "/reorganizeSubDistrictModify", method = RequestMethod.GET)
	public ModelAndView viewDistrictDetail(HttpSession httpSession, @ModelAttribute("modifySubDistrictCmd") SubDistrictForm subDistrictForm, Model model, HttpServletRequest request) {
		Itr = 1;
		//int temp = 0;
		String SubdistrictCode = null;
		ModelAndView mv = null;
		try {
			SubdistrictCode = (String) httpSession.getAttribute("SubCodeModify");
			int subdistritCode = Integer.parseInt(SubdistrictCode.replace("PART", "").replace("FULL", ""));
			List<SubdistrictDataForm> listSubdistrictDetails = subdistrictService.getSubdistrictDetailsModify(subdistritCode);
			mv = new ModelAndView("reorganize_SubdistrictModify");
			model.addAttribute("size", listSubdistrictDetails.size());
			model.addAttribute("listVillageDetails", listSubdistrictDetails);
			subDistrictForm.setListSubdistrictDetails(listSubdistrictDetails);
			mv.addObject("modifySubDistrictCmd", subDistrictForm);
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganizeSubDistrictModify", method = RequestMethod.POST)
	public ModelAndView viewDistrictDetailList(HttpSession httpSession, @ModelAttribute("modifySubDistrictCmd") SubDistrictForm newsubdistrictform, BindingResult result, SessionStatus status, Model model, HttpServletRequest request) {

		Itr = 1;
		ModelAndView mav = null;
		try {
			List<SubDistrictForm> listSubdistrictModify = new ArrayList<SubDistrictForm>();
			// villageValidator.validate(modifySubDistrictCmd, result);
			/*
			 * if(result.hasErrors()) { //mav=new
			 * ModelAndView("reorganize_subdistrict_Modify_Village"); }
			 */
			// else{
			if (httpSession.getAttribute("modifySubdistrictForm") != null) {
				List<SubDistrictForm> SubdistrictForm = new ArrayList<SubDistrictForm>();
				SubdistrictForm = (List<SubDistrictForm>) httpSession.getAttribute("modifySubdistrictForm");
				for (int i = 0; i < SubdistrictForm.size(); i++) {
					listSubdistrictModify.add(SubdistrictForm.get(i));
				}
			}
			listSubdistrictModify.add(newsubdistrictform);
			httpSession.setAttribute("modifySubdistrictForm", listSubdistrictModify);
			Itr = 1;
			status.setComplete();
			mav = new ModelAndView("redirect:add_district.htm?modify");
			// }

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}


	public List<District> getDistrictList(int stateCode, HttpSession httpSession, HttpServletRequest request) {
		try {

			return districtService.getDistrictList(stateCode);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return null;
		}

	}

	@RequestMapping(value = "/cancelD", method = RequestMethod.GET)
	public String cancel(HttpSession httpSession, @ModelAttribute("newsubdistrictform") SubDistrictForm sdForm, Model model, HttpServletRequest request) {

		try {
			httpSession.removeAttribute("lstDistForm");
			httpSession.removeAttribute("lstSubdistritForm");
			httpSession.removeAttribute("lstVillForm");
			httpSession.removeAttribute("modifySubdistrictForm");
			httpSession.removeAttribute("modifyVillForm");
			httpSession.removeAttribute("lstShiftdistritForm");

			/*
			 * httpSession.removeAttribute("modifyVillForm");
			 */httpSession.removeAttribute("contributedSDs");

			Itr = 0;
			return "redirect:home.htm";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/savedistrict", method = RequestMethod.POST)
	public ModelAndView savedistrict(HttpSession httpSession, @ModelAttribute("district") DistrictForm distForm, BindingResult bindingResult, HttpServletRequest request, Model model, @RequestParam(value = "fileName", required = false) String fileName)
			throws Exception {
		ModelAndView mav = null;

		Integer annouId = null;
		Integer districtId = null;
		Transaction tx = null;
		Session session = null;
		Integer mapAttachmentId = 0;
		int Transactionid = 0;
		char t_type = 'D';
		DistrictForm districtFormtmp = (DistrictForm) httpSession.getAttribute("formbean");
		List<DistrictForm> invalidateDistrictList=new ArrayList<DistrictForm>();
		try {
			// / Manmohan
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			if (httpSession.getAttribute("countDist") == null){

				districtFormtmp.setOrderNo(distForm.getOrderNo());
				districtFormtmp.setOrderDate(distForm.getOrderDate());
				districtFormtmp.setGazPubDate(distForm.getGazPubDate());
				districtFormtmp.setEffectiveDate(distForm.getEffectiveDate());
				if (districtFormtmp.isMapUploaded()) {
					List<AttachedFilesForm> validFileMap = (List<AttachedFilesForm>) httpSession.getAttribute("validFileMap");
					mapAttachmentId = districtService.saveDataInMap(districtFormtmp, validFileMap, httpSession, session);
					districtFormtmp.setMapCode(mapAttachmentId);
				}
				String commonString = districtService.insertDistrict(request, httpSession, districtFormtmp, session);
				if (commonString != null) {
					String[] subStringsArray = commonString.split(",");
					annouId = Integer.parseInt(subStringsArray[1]);
					districtId = Integer.parseInt(subStringsArray[0]);
					Transactionid = Integer.parseInt(subStringsArray[2]);
					districtService.updateDistrictMap(session, districtId, mapAttachmentId); /*
																							 * Added
																							 * by
																							 * sushil
																							 * on
																							 * 11
																							 * -
																							 * 06
																							 * -
																							 * 2013
																							 */
				}
			} else {
				if (httpSession.getAttribute("countDist") != null) {
					
					
					Integer districtCount = (Integer) httpSession.getAttribute("countDist");
					for (int i = 1; i <= districtCount; i++) {
						if (httpSession.getAttribute("lstDistForm" + i) != null) {
							DistrictForm districtForms = (DistrictForm) httpSession.getAttribute("lstDistForm" + i);
							districtForms.setOrderNo(distForm.getOrderNo());
							districtForms.setOrderDate(distForm.getOrderDate());
							districtForms.setGazPubDate(distForm.getGazPubDate());
							districtForms.setEffectiveDate(distForm.getEffectiveDate());
							if (districtForms.isMapUploaded()) {
								List<AttachedFilesForm> validFileMap = (List<AttachedFilesForm>) httpSession.getAttribute("validFileMap" + i);
								mapAttachmentId = districtService.saveDataInMap(districtForms, validFileMap, httpSession, session);
								districtForms.setMapCode(mapAttachmentId);
							}

							// save data in sub district
							String commonString =districtService.insertDistrict(request, httpSession, districtForms, session);
							if (commonString != null) {
								
								String[] subStringsArray = commonString.split(",");
								annouId = Integer.parseInt(subStringsArray[1]);
								districtId = Integer.parseInt(subStringsArray[0]);
								Transactionid = Integer.parseInt(subStringsArray[2]);
								
								if(districtCount>1){
								DistrictForm districtTemp=new DistrictForm();
								districtTemp.setDistrictNameEnglishTemp(districtForms.getDistrictNameEnglishTemp());
								districtTemp.setEffectiveDate(districtForms.getEffectiveDate());
								invalidateDistrictList.add(districtTemp);
								}
								districtService.updateDistrictMap(session, districtId, mapAttachmentId); 
								/*
																										 * Added
																										 * by
																										 * sushil
																										 * on
																										 * 11
																										 * -
																										 * 06
																										 * -
																										 * 2013
																										 */
							}

						}
					}
					
					
					
					

				}
			}
			GovernmentOrderForm governmentOrderForm = new GovernmentOrderForm();
			governmentOrderForm.setOrderCode(annouId);
			governmentOrderForm.setOrderNo(distForm.getOrderNo());
			governmentOrderForm.setEffectiveDate(distForm.getEffectiveDate());
			governmentOrderForm.setGazPubDate(distForm.getGazPubDate());
			governmentOrderForm.setOrderDate(distForm.getOrderDate());
			governmentOrderForm.setAttachFile1(distForm.getAttachFile1());
			if (districtFormtmp.getGovtOrderConfig().equals("govtOrderUpload")) {
				List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, governmentOrderForm, bindingResult);
				villageService.saveDataInAttach(governmentOrderForm, validFileGovtUpload, request.getSession(), session);
			} else {
				govtOrderService.saveDataInAttachment(governmentOrderForm, null, httpSession, annouId, session);
			}
			tx.commit();
			Set<Integer> partDistrictCodeList = new HashSet<Integer>();
			Date effectiveDate=null;
			for(DistrictForm invalDistForm:invalidateDistrictList){
				partDistrictCodeList=this.getPartDstrict(invalDistForm.getDistrictNameEnglishTemp(), partDistrictCodeList);
				if(effectiveDate==null)
				{
					effectiveDate=invalDistForm.getEffectiveDate();
				}
				
			}
			for (Integer partDistCode : partDistrictCodeList){
				LOG.info("District Code:"+partDistCode+"!userId:"+userId+"effectiveDate"+effectiveDate);
				districtService.distInvalFnAfterCreateMulDist(partDistCode, userId.intValue(), effectiveDate);
			}
			
			
			model.addAttribute("msgid", "The new district is created successfully");
			mav = new ModelAndView("success");
		} catch (Exception e) {
			tx.rollback();
			LOG.error("Exception-->" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			Transactionid = 0;
			mav = new ModelAndView("error");

			mav.addObject("message", " District Creation Faced Problems, Please Check Logs.");
			return mav;
		} finally {
			try {
				
				if (session != null && session.isOpen()) {
					session.close();
				}
			} catch (Exception e) {
				tx.rollback();
				LOG.error("Exception-->" + e);
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView("success");
				return mav;
			}

		}
		if (Transactionid > 0) {
			EmailSmsThread est = new EmailSmsThread(Transactionid, t_type, emailService);
			est.start();
		}

		
		return mav;
	}
	
	private Set<Integer> getPartDstrict(String districtCodes,Set<Integer> partDistrictCodeList)throws Exception{
		if (districtCodes!=null && districtCodes.contains(",")) {
			Scanner scanner = new Scanner(districtCodes);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				String patrDistCode=scanner.next().split(":")[0];
				if (patrDistCode.contains("PART")) {
					partDistrictCodeList.add(Integer.parseInt(patrDistCode.replaceAll("PART", "")));
				}
			}
		}else  if(districtCodes!=null && districtCodes.length()>0)
		{
			String patrDistCode=districtCodes.split(":")[0];
			if (patrDistCode.contains("PART")) {
				partDistrictCodeList.add(Integer.parseInt(patrDistCode.replaceAll("PART", "")));
			}
		}
		return partDistrictCodeList;
	}

	// Attachment File
	public void setAttachmentDetails(DistrictForm districtform, HttpServletRequest request) {
		AttachmentMaster attachmentList;
		try {
			attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);

			districtform.setAllowedFileType(attachmentList.getFileType());
			districtform.setAllowedIndividualFileSize(attachmentList.getIndividualFileSize());
			districtform.setAllowedNoOfAttachment(attachmentList.getFileLimit());
			districtform.setAllowedTotalFileSize(attachmentList.getTotalFileSize());
			districtform.setUploadLocation(attachmentList.getFileLocation());
			districtform.setRequiredTitle(attachmentList.getRequireTitle());
			districtform.setUniqueTitle(attachmentList.getUniqueTitle());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);

		}
	}

	

	//--------------------  modify District Change ---------------------------------------------------------
	
		@RequestMapping(value = "/modifyDistrictchangebyId")
		public ModelAndView modifyDistrict(
				@ModelAttribute("modifyDistrictCmd") DistrictForm modifyDistrictCmd,
				Model model,
				HttpSession session,
				HttpServletRequest request)
		{
			int operationCode = 4;
			ModelAndView mv = null;
			Integer districtCode=modifyDistrictCmd.getDistrictId();
			try {
				char operation = 'M';
				
				request.setAttribute("stateCode", stateCode);
				List<DistrictDataForm> listdistrictDetails = districtService
						.getDistrictDetailsModify(districtCode);
				/* EsapiEncoder.encode(listdistrictDetails); */
				Map<String, String> hMap = new HashMap<String, String>();
				hMap = govtOrderService.getGovtOrderConfiguration(stateCode,
						operationCode, operation);
				String message = hMap.get("message");
				//System.out.println("hmap--" + hMap);
				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				model.addAttribute("size", listdistrictDetails.size());
				if (govtOrderConfig != null) {

					mv = new ModelAndView("modifyDistrictchange");
					mv.addObject("govtOrderConfig", govtOrderConfig);
					model.addAttribute("listdistrictDetails", listdistrictDetails);
					
					modifyDistrictCmd.setListDistrictDetails(listdistrictDetails);
					mv.addObject("modifyDistrictCmd", modifyDistrictCmd);
					mv.addObject("stateCode",stateCode);

				} else {
					mv = new ModelAndView("success");
					mv.addObject("msgid", message);
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
		}
		

	//--------------------End of  modify District Change ---------------------------------------------------------	
	//------------------ modify District Change Post method -------------------------------------------------------
		// Modify Village Change Government Order
		@RequestMapping(value = "/draftModifyDistrict", method = RequestMethod.POST)
		public ModelAndView draftModifyVillage(
				@ModelAttribute("modifyDistrictCmd") DistrictForm modifyDistrictCmd,
				BindingResult result, SessionStatus status,
				HttpServletRequest request, HttpSession session) {
			ModelAndView mv = new ModelAndView();
			try {
		
				//////////code for government order checking///////////////////////////////////
				
				Map<String, String> hMap1 = new HashMap<String, String>();
				// Please first check your operation code then set it in place of 10
				hMap1 = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 10, 'V');// 10 is operation code for create new
				String govtOrderConfig = hMap1.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap1.get("mapUpload");// values==true,false
				/////////code for government order checking///////////////////////////////////
				session.setAttribute("formbean", modifyDistrictCmd);
				modifyDistrictCmd=districtValidator.validateChange(modifyDistrictCmd, result);
				if (result.hasErrors()) {

	          if (govtOrderConfig != null && mapConfig != null) {
					result.getErrorCount();
					result.getAllErrors();
					mv.addObject("modifyDistrictCmd", modifyDistrictCmd);
					mv = new ModelAndView("modifyDistrictchange");
	          }
					return mv;
				} 
				else {
					mv = new ModelAndView("redirect:govtOrderCommon.htm");
					return mv;
				}
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory
						.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(
						request, "", "label.lgd", 1, e);
				mv = new ModelAndView(redirectPath);
				return mv;
			}
			
		}
	//---------------------	end of modify District Change Post method -------------------------------------------------
	
	// ---------------------- View
	// District------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/viewdistrict", method = RequestMethod.GET)
	public ModelAndView showDistrictPage(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("view_district");
		try {
			setGlobalParams(session);
			DistrictForm districtbean = new DistrictForm();
			boolean isDisplaySearch = false;
			
            String entityType=CommanConstant.ENTITY_TYPE_STATE.toString();
            Integer entityCode=stateCode;
			List<District> districtList = null;
			if (districtCode > 0) {
				entityType=CommanConstant.ENTITY_TYPE_DISTRICT.toString();
				entityCode=districtCode;
			} 
			districtList=districtService.getDistrictListbyCriteria(entityCode, entityType);
			
			isDisplaySearch = true;

			model.addAttribute("SEARCH_DISTRICT_RESULTS_KEY", districtList);

			model.addAttribute("viewPage", "abc");
			model.addAttribute("isDisplaySearch", isDisplaySearch);
			districtbean.setOffset(1);
			districtbean.setLimit(1000);
			model.addAttribute("offsets", 0);
			model.addAttribute("limits", 1000);
			mav.addObject("districtbean", districtbean);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// --------------------------------------------------------------------------------------------

	// ---------------------------- District Correction Get
	// Method----------------------------
	@RequestMapping(value = "/modifyDistrictCrbyId")
	public ModelAndView modifyDistrictCorrection(@ModelAttribute("modifyDistrictCmd") DistrictForm modifyDistrictCmd, HttpSession session, Model model, HttpServletRequest request) {
		ModelAndView mv = null;
		String strDistrictCode = request.getParameter("id");
		String reqWarningFlag = request.getParameter("warningEntiesFlag");
		session.setAttribute("reqWarningFlag", reqWarningFlag);
		session.setAttribute("id", strDistrictCode);
		Integer districtCode = modifyDistrictCmd.getDistrictId();
		if (districtCode == null){
			districtCode = Integer.parseInt(strDistrictCode);
		}	
		try {
			List<DistrictDataForm> listDistrictDetails = districtService.getDistrictDetailsModify(districtCode);

			int operationCode = 4; // Operation code for Modify Gov. order
			

			request.setAttribute("stateCode", stateCode);
			if (!listDistrictDetails.isEmpty()) {
				DistrictDataForm disttDataForm = listDistrictDetails.get(0);
				modifyDistrictCmd.setCoordinates(disttDataForm.getCordinate());
				Integer orderCode = disttDataForm.getOrderCodecr();
				mv = new ModelAndView("modifyDistrictCorrection");
				boolean mandatoryFlag = true;

				model.addAttribute("pageWarningEntiesFlag", disttDataForm.isWarningflag());
				model.addAttribute("reqWarningFlag", reqWarningFlag);

				Map<String, String> mapGovOrderConfig = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, 'M');
				Map<String, String> mapMapConfig = govtOrderService.getMapConfiguration(stateCode, operationCode, 'D');
				String message = mapGovOrderConfig.get("message");
				String message2 = mapMapConfig.get("message");
				String govtOrderConfig = mapGovOrderConfig.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = mapMapConfig.get("mapUpload");
				if (govtOrderConfig == null && mapConfig == null) {
					String aMessage = "Please Configure Map and Govt Order ";
					mv = new ModelAndView("success");
					mv.addObject("msgid", aMessage);
					return mv;
				} else if (govtOrderConfig == null) {
					mv = new ModelAndView("success");
					mv.addObject("msgid", message);
					return mv;
				} else if (mapConfig == null) {
					mv = new ModelAndView("success");
					mv.addObject("msgid", message2);
					return mv;
				}

				modifyDistrictCmd.setOrderCode(disttDataForm.getOrderCodecr());
				modifyDistrictCmd.setOrderDate(disttDataForm.getOrderDatecr());
				modifyDistrictCmd.setOrderNo(disttDataForm.getOrderNocr());
				modifyDistrictCmd.setEffectiveDate(disttDataForm.getOrdereffectiveDatecr());
				modifyDistrictCmd.setGazPubDate(disttDataForm.getGazPubDatecr());
				setAttachmentDetails(modifyDistrictCmd, request);

				if (orderCode != null)
					attachmentList = govtOrderService.getAttachmentsbyOrderCode(orderCode);
				else {
					//mandatoryFlag = false;
					attachmentList = new ArrayList<Attachment>();
				}

				mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(districtCode, 'D');

				modifyDistrictCmd.setListDistrictDetails(listDistrictDetails);

				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);
				model.addAttribute("listDistrictDetails", listDistrictDetails);
				mv.addObject("govtOrderConfig", govtOrderConfig);
				mv.addObject("mapConfig", mapConfig);
				mv.addObject("modifyDistrictCmd", modifyDistrictCmd);
				mv.addObject("govtfilecount", attachmentList.size());
				mv.addObject("mapfilecount", mapAttachmentList.size());
				session.setAttribute("mandatoryFlag", mandatoryFlag);
				mv.addObject("mandatoryFlag", mandatoryFlag);

				return mv;

			} else {
				String aMessage = "Sorry Data Not Found For Your Selection ";
				mv = new ModelAndView("success");
				mv.addObject("msgid", aMessage);
				return mv;

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

	}

	// ---------------------------------------------------------------------------------------------

	// -------------------- District Correction Post
	// Method--------------------------------------------------
	@RequestMapping(value = "/modifyDistrictCrAction", method = RequestMethod.POST)
	public ModelAndView modifyDistrictCrActionCorrection(@ModelAttribute("modifyDistrictCmd") DistrictForm modifyDistrictCmd, BindingResult result, SessionStatus status, Model model, HttpSession session, HttpServletRequest request) throws Exception {
		ModelAndView mav = null;

		try {
			List<DistrictDataForm> listDistrict = modifyDistrictCmd.getListDistrictDetails();
			if (!listDistrict.isEmpty()) {
				DistrictDataForm element = listDistrict.get(0);

				int operationCode = 4;
				char operation = 'M'; // operation modification district
				char entityType = 'D'; // Entity Type - District
				
				request.setAttribute("stateCode", stateCode);
				boolean delflag = false;

				Map<String, String> mapGovOrderConfig = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
				Map<String, String> mapMapConfig = govtOrderService.getMapConfiguration(stateCode, operationCode, entityType);
				String govtOrderConfig = mapGovOrderConfig.get("govtOrder"); // govtOrderUpload,govtOrderGenerate
				String mapConfig = mapMapConfig.get("mapUpload");// true,false

				modifyDistrictCmd.setOrderNocr(element.getOrderNocr().trim());
				modifyDistrictCmd.setOrderDatecr(element.getOrderDatecr());
				modifyDistrictCmd.setOrdereffectiveDatecr(element.getOrdereffectiveDatecr());
				modifyDistrictCmd.setGazPubDatecr(element.getGazPubDatecr());

				mav = new ModelAndView("modifyDistrictCorrection");
				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);
				districtValidator.validate(modifyDistrictCmd, result);
				commonValidator.isValidMimeforGovOrderLandRegion(result, request, modifyDistrictCmd.getAttachFile1());
				commonValidator.isValidMimeforMapLandRegion(result, request, modifyDistrictCmd.getAttachFile2());

				if (result.hasErrors()) {
					String Cordinate = null;
					if (element.getCordinate() != null) {
						if (!element.getCordinate().trim().equals("")) {
							Cordinate = element.getCordinate();
						}
					}
					element.setCordinate(Cordinate);
					listDistrict.set(0, element);
					modifyDistrictCmd.setListDistrictDetails(listDistrict);
					model.addAttribute("attachmentList", attachmentList);
					model.addAttribute("mapAttachmentList", mapAttachmentList);
					result.getErrorCount();
					result.getAllErrors();
					request.setAttribute("attachmentList", attachmentList);
					request.setAttribute("mapAttachmentList", mapAttachmentList);
					model.addAttribute("mapConfig", mapConfig);
					mav.addObject("mapConfig", mapConfig);
					mav.addObject("modifyDistrictCmd", modifyDistrictCmd);
					mav.addObject("govtOrderConfig", govtOrderConfig);
					mav.addObject("govtfilecount", attachmentList.size());
					mav.addObject("mapfilecount", mapAttachmentList.size());
					model.addAttribute("pageWarningEntiesFlag", modifyDistrictCmd.isWarningflag());
					if (session.getAttribute("mandatoryFlag") != null) {
						mav.addObject("mandatoryFlag", session.getAttribute("mandatoryFlag"));
					}
					return mav;
				}
				if (session.getAttribute("mandatoryFlag") != null) {
					session.removeAttribute("mandatoryFlag");
				}
				int districtCode = element.getDistrictCode();
				List<AttachedFilesForm> validFileGovtUpload = new ArrayList<AttachedFilesForm>();
				List<AttachedFilesForm> validFileMap = new ArrayList<AttachedFilesForm>();

				if (govtOrderConfig.equals("govtOrderUpload")) {

					AddAttachmentBean newGovAttchment = getAttachmentBean(modifyDistrictCmd, request);
					String deleteAttachmentId[] = null;
					if (newGovAttchment != null) {
						deleteAttachmentId = newGovAttchment.getDeletedFileID();
						validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, newGovAttchment, modifyDistrictCmd.getAttachFile1(), result, mav);
					}
					if (mapConfig.equals("true")) {
						AddAttachmentBean mapAttachmentBean = getAttachmentBeanforMap(modifyDistrictCmd, request);
						validFileMap = fileUploadUtility.ValidateAndUploadFileMap2(request, mapAttachmentBean, modifyDistrictCmd.getAttachFile2(), result, mav);

						String deleteID[] = mapAttachmentBean.getDeletedFileID();
						if (deleteID != null && deleteID.length > 0){
							delflag = true;
						}	
						if (validFileMap != null) {
							boolean warningFlag = fileUploadUtility.validWarninngFlagMapUpload(mapAttachmentList, validFileMap);
							if (warningFlag){
								modifyDistrictCmd.setWarningflag(!warningFlag);
							}	
						}

						districtService.modifyDistrictCrInfo(modifyDistrictCmd, request, validFileGovtUpload, validFileMap, delflag, deleteAttachmentId, session);

					} else {

						districtService.modifyDistrictCrInfo(modifyDistrictCmd, request, validFileGovtUpload, null, delflag, deleteAttachmentId, session);
					}

				} else if (govtOrderConfig.equals("govtOrderGenerate")) {

					if (mapConfig.equals("true")) {
						AddAttachmentBean mapAttachmentBean = getAttachmentBeanforMap(modifyDistrictCmd, request);
						validFileMap = fileUploadUtility.ValidateAndUploadFileMap2(request, mapAttachmentBean, modifyDistrictCmd.getAttachFile2(), result, mav);

						String deleteID[] = mapAttachmentBean.getDeletedFileID();
						if (deleteID != null && deleteID.length > 0){
							delflag = true;
						}	
						if (validFileMap != null) {
							boolean warningFlag = fileUploadUtility.validWarninngFlagMapUpload(mapAttachmentList, validFileMap);
							if (warningFlag){
								modifyDistrictCmd.setWarningflag(!warningFlag);
							}	
						}

						districtService.modifyDistrictCrInfo(modifyDistrictCmd, request, null, validFileMap, delflag, null, session);

					} else {
						districtService.modifyDistrictCrInfo(modifyDistrictCmd, request, null, null, delflag, null, session);
					}

				}

				Object warningEntiesFlag = session.getAttribute("reqWarningFlag");
				session.removeAttribute("warningEntiesFlag");
				if (warningEntiesFlag != null && "W".equalsIgnoreCase(warningEntiesFlag.toString())) {
					mav = new ModelAndView("redirect:viewResolveEntitiesinDisturbedStateLR.htm?warningEntiesFlag=" + warningEntiesFlag.toString());
				} else {
					mav = new ModelAndView("redirect:viewModifyDistrictDetailCopy.htm?id=" + districtCode + "&type=S");

				}

				return mav;

			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "No Record(s) available for corrction.");
				return mav;
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	// -----------------------------------------------------------------------------------------------

	// --------------- function for viladate warning Flag
	// ------------------------
	/*private boolean validWarninngFlagMapUpload(AddAttachmentBean addAttachmentBean2) {
		String existingMapFile = "";
		String currentMapFile = "";

		if (mapAttachmentList != null && !mapAttachmentList.isEmpty()) {
			MapAttachment mapattDistt = mapAttachmentList.get(0);
			existingMapFile = mapattDistt.getFileName();
		}
		List<CommonsMultipartFile> currUplodedMap = addAttachmentBean2.getCurrentlyUploadFileList();
		if (!currUplodedMap.isEmpty()) {
			currentMapFile = currUplodedMap.get(0).getOriginalFilename();
		}
		if (!"".equals(currentMapFile) && !currentMapFile.equalsIgnoreCase(existingMapFile)) {
			return true;
		}
		return false;
	}*/

	// -----------------------------------------------------------------------------------------------

	// ---------------- Function for Attachement Bean for Map
	// ---------------------------------------
	private AddAttachmentBean getAttachmentBeanforMap(DistrictForm districtForm, HttpServletRequest request) {
		try {
			List<MapAttachment> alreadyAttachList = null;
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(2);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0;
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;

			if (districtForm.getOrderCode() != null) {

				alreadyAttachList = govtOrderService.getMapAttachmentListbyEntity(districtForm.getSubdistrictCode(), 'T');

				noOFAlreadyAttachedFiles1 = alreadyAttachList.size(); // Already
																		// Attach
																		// Number
																		// Of
																		// File.
				// Already attached file total size.
				Iterator<MapAttachment> attachmentsIterator1 = alreadyAttachList.iterator();
				while (attachmentsIterator1.hasNext()) {
					MapAttachment att = (MapAttachment) attachmentsIterator1.next();
					long fileSize = att.getFileSize();
					totalSizeOFAlreadyAttachedFiles1 = totalSizeOFAlreadyAttachedFiles1 + fileSize;
				}
			}

			AddAttachmentBean addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(districtForm.getAttachFile2());
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);
			addAttachmentBeanTwo.setFileTitle(districtForm.getFileTitle2());
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File
																		// Mime
																		// Type
																		// List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID2"));// 10.File
																								// Id
																								// array
																								// to
																								// be
																								// deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName2"));// 11.File
																									// Name
																									// Array
																									// To
																									// Be
																									// Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList2"));// 12.Deleted
																										// File
																										// Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
																			// of
																			// Mandatory
																			// file.

			return addAttachmentBeanTwo;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
	}

	// -------------------------------------------------------------------------------------

	// ------------ Function for get Attachement Bean for Goverment
	// order------------
	private AddAttachmentBean getAttachmentBean(DistrictForm districtform, HttpServletRequest request) {

		List<Attachment> alreadyAttachList = null;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0;
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			Integer orderCode = null;
			List<DistrictDataForm> listDistrict = new ArrayList<DistrictDataForm>();
			listDistrict = districtform.getListDistrictDetails();
			Iterator<DistrictDataForm> itr = listDistrict.iterator();
			while (itr.hasNext()) {
				DistrictDataForm element = (DistrictDataForm) itr.next();
				orderCode = element.getOrderCodecr();
			}

			if (orderCode != null) {

				alreadyAttachList = govtOrderService.getAttachmentsbyOrderCode(orderCode);

				noOFAlreadyAttachedFiles1 = alreadyAttachList.size(); // Already
																		// Attach
																		// Number
																		// Of
																		// File.
				// Already attached file total size.
				Iterator<Attachment> attachmentsIterator1 = alreadyAttachList.iterator();
				while (attachmentsIterator1.hasNext()) {
					Attachment att = (Attachment) attachmentsIterator1.next();
					long fileSize = att.getFileSize();
					totalSizeOFAlreadyAttachedFiles1 = totalSizeOFAlreadyAttachedFiles1 + fileSize;
				}
			}

			AddAttachmentBean addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(districtform.getAttachFile1());
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);
			addAttachmentBeanTwo.setFileTitle(districtform.getFileTitle1());
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File
																		// Mime
																		// Type
																		// List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File
																								// Id
																								// array
																								// to
																								// be
																								// deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File
																									// Name
																									// Array
																									// To
																									// Be
																									// Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted
																										// File
																										// Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
																			// of
																			// Mandatory
																			// file.

			return addAttachmentBeanTwo;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return null;
		}
	}
	
	/**
	 * Add for Mark Pesa Land Region
	 */
	@RequestMapping(value = "/markPesaLandRegion", method = RequestMethod.GET)
	public ModelAndView markPesaLandRegion(HttpSession httpSession, @ModelAttribute("district") DistrictForm districtForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String displayMessage = "";
		String stateNameEng = "";
		try {
			// Copy to Check Configuration
			Map<String, String> hMap = new HashMap<String, String>();
			
			
			/*Code Added by Pooja on 19-05-2016 for display Mark Pesa Land Region Form only to Pesa States*/
			Boolean isPesaState = stateService.isPesaState(stateCode);
			if(isPesaState) {
				model.addAttribute("displayForm",true);
			} else {
				stateNameEng = stateService.getStateNameEnglish(stateCode);
				displayMessage = "Panchayats Extension to Scheduled Areas (PESA) Act is not implemented for "+stateNameEng+" State.";
				model.addAttribute("displayMessage",displayMessage);
				model.addAttribute("displayForm",false);
				mav = new ModelAndView("markPesaLandRegions");
				return mav;
			}
			// Please first check your operation code then set it in place of 10
			hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 3, 'D'); // 1
																							// is
																							// operation
																							// code
																							// for
																							// Create
																							// new
																							// State

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate 
			String mapConfig = hMap.get("mapUpload");// values==true,false
			String message = hMap.get("message");
		
			//List<District> distList = districtService.getDistrictList(slc);
			
			httpSession.removeAttribute("histroyDistrict");
			httpSession.removeAttribute("realCount");
			if (govtOrderConfig != null && mapConfig != null) {
				if (httpSession.getAttribute("realCount") == null) {
					model.addAttribute("visibilityanother", "display: inline;");
					model.addAttribute("display", "display: none;");
					model.addAttribute("disabled", false);
					model.addAttribute("visibilityNext", "display: none;");
					model.addAttribute("visibility", "display: inline;");
					model.addAttribute("totalVillage", "");
					model.addAttribute("districtNameList", "");
					model.addAttribute("stateCode",stateCode);
					mav = new ModelAndView("markPesaLandRegions");
					mav.addObject("district", new DistrictForm());
				}
				mav.addObject("hideMap", mapConfig);
				mav.addObject("govtOrderConfig", govtOrderConfig);
			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			}
		} catch (NumberFormatException e) {
			LOG.error("Exception-->" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			LOG.error("Exception-->" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	
	@RequestMapping(value = "/markPesaLandRegion", method = RequestMethod.POST)
	public ModelAndView markPesaLandRegionPost(HttpSession httpSession, @ModelAttribute("district") DistrictForm districtForm, BindingResult bindingResult, SessionStatus status, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			//Pattern p = Pattern.compile("[a-zA-Z 0-9]");
			//int tmp = 0;
			//boolean error = false;
			

			if ("".equals(districtForm.getStatusDist().toString())) {
				httpSession.setAttribute("distfrm", districtForm.getStatusDist());
			}			
			
			ArrayList<DistrictForm> listDistrictForms = new ArrayList<DistrictForm>();
			ArrayList<SubDistrictForm> listSubDistrictForms =new ArrayList<SubDistrictForm>();
			ArrayList<VillageForm> listVillageForms = new ArrayList<VillageForm>();

			String temp = districtForm.getDistrictNameEnglish().replace("PART", "").replace("FULL", "");
			model.addAttribute("listDistrict", temp);
			
			List<String> unMarkList = new ArrayList<String>(Arrays.asList(temp.split(",")));  //one 
			
			/*Added by ANju for LGD 0003817*/
			
			
			
			List<District> distList = districtService.getDistrictList(stateCode);
			List<District> distListnew = new ArrayList<District>();
			int flag=0;
			for (int i=0;i<distList.size();i++)
			{	flag=0;			
				if(unMarkList.contains(String.valueOf(distList.get(i).getDistrictCode())))
						{
							flag=1;
						}
				else
					flag=2;
				if(flag==2)
				{
					distListnew.add(distList.get(i));
				}
			}
			/*end by Anju Gupta for  LGD 0003817*/
			
			if (bindingResult.hasErrors()) {
				/* added by sushil on 10-06-2013 */
				Map<String, String> hMap = new HashMap<String, String>();
				// Please first check your operation code then set it in
				// place of 10
				hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 3, 'D'); // 1
				// is
				// operation
				// code
				// for
				// Create
				// new
				// State

				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap.get("mapUpload");// values==true,false
				//String message = hMap.get("message");

				if (govtOrderConfig != null && mapConfig != null) {
					if (httpSession.getAttribute("realCount") == null) {
						
						model.addAttribute("visibilityanother", "display: inline;");
						model.addAttribute("display", "display: none;");
						model.addAttribute("disabled", false);
						model.addAttribute("visibilityNext", "display: none;");
						model.addAttribute("visibility", "display: inline;");
						model.addAttribute("distList", distList);
						model.addAttribute("listDistrict", null);
						model.addAttribute("totalVillage", "");
						model.addAttribute("districtNameList", "");
						mav = new ModelAndView("markPesaLandRegion");
						mav.addObject("district", new DistrictForm());
					}
					mav.addObject("hideMap", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
				}
				return mav;
			}

			String postDistrict =districtForm.getDistrictNameEnglish();
			String postSubdist=districtForm.getContributedSubDistricts();
			String postSubVillage=districtForm.getContributedVillages();
			
			/*if(postDistrict!=null && postDistrict.length()>0){
				String[] arrayPostDistrict = postDistrict.split(",");
				for (int i = 0; i < arrayPostDistrict.length; i++) {
					DistrictForm tempdistrictForm = new DistrictForm();

					if (arrayPostDistrict[i].contains("PART")) {
						String[] postDistrictArray = arrayPostDistrict[i].split("PART");
						tempdistrictForm.setDistrictNameEnglish(postDistrictArray[0]);
						tempdistrictForm.setIsPesaStatus("P");
						listDistrictForms.add(tempdistrictForm);
					}else if (arrayPostDistrict[i].contains("FULL")) {
						String[] postDistrictArray = arrayPostDistrict[i].split("FULL");
						tempdistrictForm.setDistrictNameEnglish(postDistrictArray[0]);
						tempdistrictForm.setIsPesaStatus("F");
						listDistrictForms.add(tempdistrictForm);
					}
				}
			}
			if(postSubdist!=null && postSubdist.length()>0){
				String[] arrayPostSubDistrict = postSubdist.split(",");
				for (int i = 0; i < arrayPostSubDistrict.length; i++) {
					if (arrayPostSubDistrict[i].contains("PART")) {
						SubDistrictForm object = new SubDistrictForm();
						String[] tempArray = arrayPostSubDistrict[i].split("PART");
						object.setSubdistrictNameEnglish(tempArray[0]);
						object.setIsPesaStatus("P");
						listSubDistrictForms.add(object);
					}else if (arrayPostSubDistrict[i].contains("FULL")) {
						SubDistrictForm object = new SubDistrictForm();
						String[] tempArray = arrayPostSubDistrict[i].split("FULL");
						object.setSubdistrictNameEnglish(tempArray[0]);
						object.setIsPesaStatus("F");
						listSubDistrictForms.add(object);
					}
				}
			}
			if(postSubVillage!=null && postSubVillage.length()>0){
				String[] arraypostVillages = postSubVillage.split(",");
				for (int i = 0; i < arraypostVillages.length; i++) {
					VillageForm vilForm = new VillageForm();

					if (arraypostVillages[i].contains("PART")) {
						String[] tempArray = arraypostVillages[i].split("PART");
						vilForm.setVillageNameEnglishCh(tempArray[0]);
						vilForm.setIsPesaStatus("P");
						listVillageForms.add(vilForm);
					}
					if (arraypostVillages[i].contains("FULL")) {
						String[] tempArray = arraypostVillages[i].split("FULL");
						vilForm.setVillageNameEnglishCh(tempArray[0]);
						vilForm.setIsPesaStatus("F");
						listVillageForms.add(vilForm);
					}
				}
			}*/
			/*Added by Sunita on 07-04-2016*/
			String inputParam = "";
			if (postDistrict != null && postDistrict.length() > 0) {
				String[] arrayPostDistrict = postDistrict.split(",");
				for (int i = 0; i < arrayPostDistrict.length; i++) {
					if (arrayPostDistrict[i].contains("PART")) {
						String[] postDistrictArray = arrayPostDistrict[i].split("PART");
						inputParam = inputParam + "D_" + postDistrictArray[0] + "_P,";
					} else if (arrayPostDistrict[i].contains("FULL")) {
						String[] postDistrictArray = arrayPostDistrict[i].split("FULL");
						inputParam = inputParam + "D_" + postDistrictArray[0] + "_F,";
					}
				}
			}

			if (postSubdist != null && postSubdist.length() > 0) {
				String[] arrayPostSubDistrict = postSubdist.split(",");
				for (int i = 0; i < arrayPostSubDistrict.length; i++) {
					if (arrayPostSubDistrict[i].contains("PART")) {
						String[] tempArray = arrayPostSubDistrict[i].split("PART");
						inputParam = inputParam + "T_" + tempArray[0] + "_P,";
					} else if (arrayPostSubDistrict[i].contains("FULL")) {
						String[] tempArray = arrayPostSubDistrict[i].split("FULL");
						inputParam = inputParam + "T_" + tempArray[0] + "_F,";
					}
				}
			}

			if (postSubVillage != null && postSubVillage.length() > 0) {
				String[] arraypostVillages = postSubVillage.split(",");
				for (int i = 0; i < arraypostVillages.length; i++) {
					if (arraypostVillages[i].contains("PART")) {
						String[] tempArray = arraypostVillages[i].split("PART");
						inputParam = inputParam + "V_" + tempArray[0] + "_P,";
					}
					 else if (arraypostVillages[i].contains("FULL")) {
						String[] tempArray = arraypostVillages[i].split("FULL");
						inputParam = inputParam + "V_" + tempArray[0] + "_F,";
					}
				}
			}

			inputParam = inputParam.substring(0, inputParam.length() - 1);
			 /*End by Sunita*/
			 
			/*Added by Anju Guptafor to mark MarkPesa to N  for LGD-0003817*/
			if (distListnew != null && distListnew.size() > 0) {
				for (int i = 0; i < distListnew.size(); i++) {
					DistrictForm tempdistrictForm = new DistrictForm();
					tempdistrictForm.setDistrictNameEnglish(String
							.valueOf(distListnew.get(i).getDistrictCode()));
					tempdistrictForm.setIsPesaStatus("N");
					listDistrictForms.add(tempdistrictForm);
				}
			}
			
			/*End by Anju*/
			/*parameter state code added for landregion is pesa by kirandeep on 01/05/2015*/
			districtService.updatePesaStatus(inputParam,stateCode);
			
			
			mav = new ModelAndView("success");
			
			/* messege changed by kirandeep  */
			mav.addObject("msgid", "PESA Status of Land Regions updated successfully");

		} catch (Exception e) {
			LOG.error("Exception-->" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	
	
	/*@RequestMapping(value = "/viewDistrictDetailforModify", method = RequestMethod.GET)
	public ModelAndView viewDistrictDetail(@ModelAttribute("districtView") DistrictForm districtView, Model model, HttpServletRequest request, @RequestParam("id") Integer districtCode, @RequestParam("type") String type) {
		ModelAndView mv = null;
		try {
			List<DistrictDataForm> listDistrictDetails = districtService.getDistrictDetailsModify(districtCode);
			if(listDistrictDetails!=null && !listDistrictDetails.isEmpty()){
				String cordinate=listDistrictDetails.get(0).getCordinate();
		        if(cordinate!=null && cordinate.indexOf(",")==-1 && cordinate.indexOf(":")>-1){
		        	cordinate=cordinate+",";
		        	listDistrictDetails.get(0).setCordinate(cordinate);
		        }
			}
			mv = new ModelAndView("view_districtdetail");
			districtView.setListDistrictDetails(listDistrictDetails);

			model.addAttribute("successMsg", "The district was modified successfully");
			return mv;

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}*/

	
	
	@RequestMapping(value = "/viewModifyDistrictDetailCopy", method = RequestMethod.GET)
	public ModelAndView viewModifyDistrictdetail(@ModelAttribute("districtModifyView") DistrictForm districtModifyView, Model model, HttpServletRequest request, @RequestParam("id") Integer districtCode, @RequestParam("type") String type) {
		ModelAndView mv = null;
		try {
			List<DistrictDataForm> listDistrictDetails = districtService.getDistrictDetailsModify(districtCode);
			if(listDistrictDetails!=null && !listDistrictDetails.isEmpty()){
				String cordinate=listDistrictDetails.get(0).getCordinate();
		        if(cordinate!=null && cordinate.indexOf(",")==-1 && cordinate.indexOf(":")>-1){
		        	cordinate=cordinate+",";
		        	listDistrictDetails.get(0).setCordinate(cordinate);
		        }
			}
			mv = new ModelAndView("view_modify_districtdetail");
			districtModifyView.setListDistrictDetails(listDistrictDetails);

			model.addAttribute("successMsg", "The district was modified successfully");
			return mv;

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}
	
	//Added by Sushma Singh Feb 03,2020
	
	@RequestMapping(value = "/modifyDistrictChangeEffectiveDate", method = RequestMethod.POST)
	public ModelAndView modifyVillageChangeEffectiveDate(@ModelAttribute("districtbean")DistrictForm districtForm, Model model, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("CHANGE_EFFECTIVE_DATE_OF_DISTRICT");
			Integer DistrcitCode=null;
		try {
			model.addAttribute("DistrictCode",districtForm.getDistrictId());
			/*
			 * DistrcitCode=Integer.valueOf(request.getParameter("districtId"));
			 * model.addAttribute("DistrictCode",DistrcitCode);
			 */
			model.addAttribute("curDate",new Date());
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/saveEffectiveDateDistrict", headers="Accept=application/json", method = RequestMethod.POST)
	public @ResponseBody Response saveEffectiveDate(@RequestBody EffectiveDateList getEntityEffectiveDateList,HttpServletRequest request) throws Exception {
		HttpSession httpsession= request.getSession();
		setGlobalParams(httpsession);
		return districtService.saveEffectiveDateEntityDistrict(getEntityEffectiveDateList,userId.intValue());
	}
	
	
}
// ----------------------------------------------------------------------------------------------------	
