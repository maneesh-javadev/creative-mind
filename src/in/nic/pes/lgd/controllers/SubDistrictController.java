package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.common.EsapiEncoder;
import in.nic.pes.lgd.common.FileUploadUtility;
import in.nic.pes.lgd.constant.StateConstant;
import in.nic.pes.lgd.dao.DistrictDAO;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.SubDistrictInfo;
import in.nic.pes.lgd.forms.SubdistrictDataForm;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.ComboFillingService;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.ConfigGovtOrderService;
import in.nic.pes.lgd.service.ConvertLocalbodyService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.ShiftService;
import in.nic.pes.lgd.service.SubDistrictService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.validator.CommonValidatorImpl;
import in.nic.pes.lgd.validator.ReorganizaVillageValidator;
import in.nic.pes.lgd.validator.SubDistrictValidator;
import in.nic.pes.lgd.validator.VillageValidator;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
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

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

import com.cmc.lgd.localbody.controllers.ManageRestructuredLBController.EffectiveDateList;
import com.cmc.lgd.localbody.entities.ManageLBDetails;
import com.org.ep.exception.BaseAppException;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;
import in.nic.pes.lgd.draft.form.DraftManageSubdistrictForm;

@Controller
public class SubDistrictController { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(SubDistrictController.class);

	int Itr = 0;

	int villageCount = 1;
	int subDistCount = 1;

	@Autowired
	private CommonValidatorImpl commonValidator;

	@Autowired
	private SubDistrictService subdistrictService;

	@Autowired
	ShiftService shiftService;

	@Autowired
	VillageService villageService;

	@Autowired
	SubDistrictValidator subDistrictValidator;

	@Autowired
	DistrictDAO districtDAO;

	@Autowired
	VillageValidator villageValidator;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DistrictService districtService;

	@Autowired
	ConvertLocalbodyService convertLocalbodyService;

	@Autowired
	GovernmentOrderService govtOrderService;

	@Autowired
	ConfigGovtOrderService configGovtOrderService;
	@Autowired
	ComboFillingService comboFillingService;

	@Autowired
	FileUploadUtility fileUploadUtility;

	@Autowired
	CommonService commonService;

	@Autowired
	EmailService emailService;

	private Integer stateCode;

	private Integer districtCode;

	private Integer userId;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId().intValue();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	private void setGlobalParams(HttpSession session)
	  {
	    SessionObject sessionObject = (SessionObject)session.getAttribute("sessionObject");
	    this.stateCode = sessionObject.getStateId();
	    this.districtCode = sessionObject.getDistrictCode();
	    this.userId = Integer.valueOf(sessionObject.getUserId().intValue());
	  }
	
	
	
	@RequestMapping(value = "/invalidatesubdistrict", method = RequestMethod.GET)
	public ModelAndView preInvalidateSubdistrict(@ModelAttribute("invalidatesubdistrict") SubDistrictForm subDistrictForm, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mv = null;
		
		try {
			if (stateCode == null) {
				return mv = new ModelAndView("redirect:login.htm");
			}
			

			// Copy to Check Configuration
			Map<String, String> hMap = new HashMap<String, String>();

			// Please first check your operation code then set it in place of 10
			hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 12, 'T');// 10
																								// is
																								// operation
																								// code
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			String message = hMap.get("message");
			if (govtOrderConfig != null && mapConfig != null) {
				mv = new ModelAndView("invalidateSubDistrictDefault");
				List<District> districtList = null;
				districtList = new ArrayList<District>();
				if (districtCode > 0) {
					districtList = districtService.getDistrictListbyDistrictCodeForLocalBody(districtCode);
				} else {
					districtList = this.getDistrictListByStateCodeForLocalBody(stateCode, httpSession, request);
				}

				mv.addObject("districtList", districtList);
				mv.addObject("districtCode", districtCode);
				subdistrictService.clearAll();
				mv.addObject("hideMap", mapConfig);
				mv.addObject("govtOrderConfig", govtOrderConfig);
			} else {
				mv = new ModelAndView("success");
				mv.addObject("msgid", message);
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

	@RequestMapping(value = "/invalidateSubD", method = RequestMethod.POST)
	public ModelAndView InvalidateSubdistrict(@ModelAttribute("invalidatesubdistrict") SubDistrictForm subDistrictForm, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mv = null;
		try {

			///////////// DROP DOWN VALIDATIONS /////////////////////
			// 3 lines temporarily commented

			comboFillingService.getComboValuesforApp('D', "S", stateCode, Integer.parseInt(subDistrictForm.getDistrictNameEnglish()));
			comboFillingService.getComboValuesforApp('T', "D", Integer.parseInt(subDistrictForm.getDistrictNameEnglish()), Integer.parseInt(subDistrictForm.getSubdistrictNameEnglish()));

			/* Modified by kirandeep for Invalidate Subdistrict on 16/03/2015 */
			Integer flagSubDistrictInvalid = subDistrictForm.getFlagSubDistrictInvalid();
			if (flagSubDistrictInvalid != -1) {

				comboFillingService.getComboValuesforApp('T', "D", Integer.parseInt(subDistrictForm.getDistrictNameEnglish()), Integer.parseInt(subDistrictForm.getTargetSubdistrictYes()));
			}
			//////////// CODE FOR GOVERNMENT ORDER//////////////////
			Map<String, String> hMap1 = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			hMap1 = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 10, 'V');// 10
																									// is
																									// operation
																									// code
																									// for
																									// create
																									// new
			String govtOrderConfig = hMap1.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap1.get("mapUpload");// values==true,false

			//////////// CODE FOR GOVERNMENT ORDER//////////////////
			subDistrictValidator.validateInvalidateSubDistrict(subDistrictForm, result);
			if (result.hasErrors()) {
				// mv.addObject("hideMap", subDistrictForm.);
				if (govtOrderConfig != null && mapConfig != null) {
					List<District> districtList = null;
					districtList = new ArrayList<District>();
					districtList = this.getDistrictList(stateCode, httpSession, request);
					mv = new ModelAndView("invalidateSubDistrictDefault");
					mv.addObject("govtOrderConfig", subDistrictForm.getGovtOrderConfig());
					mv.addObject("districtList", districtList);
					mv.addObject(subDistrictForm);
				}
			} else {
				httpSession.setAttribute("formbean", subDistrictForm);
				mv = new ModelAndView("redirect:govtOrderCommon.htm");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/invalidateSubDFinal", method = RequestMethod.POST)
	public ModelAndView InvalidateSubdistrictFinal(@ModelAttribute("invalidatesubdistrict") SubDistrictForm subDistrictForm, BindingResult result, SessionStatus status, HttpSession httpsession, Model model,
			@RequestParam(value = "fileName", required = false) String fileName, HttpServletRequest request) {
		ModelAndView mv = null;
		String message = null;
		String subname = null;
		String merge = "";
		String orderCode = null;
		String tid = null;
		String successResult = null;
		Session session = null;
		try {
			subDistrictForm = (SubDistrictForm) httpsession.getAttribute("formbean");
			String subdistrictCodes = null;
			String villageCodes = "";
			int subdistrictCode = 0;
			int sdcode = 0;
			boolean success = false;
			Subdistrict subdistrictBean = null;
			subdistrictBean = new Subdistrict();
			Subdistrict subdistrictBeanMerge = null;
			subdistrictBeanMerge = new Subdistrict();
			String key = "";
			String keyAppend = "";
			String finalValue = "";
			int maxCode = 0;
			Map<String, String> sdVillMap = new HashMap<String, String>();
			Map<Subdistrict, List<Village>> sdVillMapView = new HashMap<Subdistrict, List<Village>>();
			Date effectiveDate = subDistrictForm.getEffectiveDate();
			String orderNo = subDistrictForm.getOrderNo();
			Date orderDate = subDistrictForm.getOrderDate();
			Date gazPubDate = subDistrictForm.getGazPubDate();
			
			String sourceSubdistCode = subDistrictForm.getSubdistrictNameEnglish();
			if (subDistrictForm.isRdoSubdistrictDelete()) {

				Subdistrict subdistrictkeyBean = null;
				subdistrictkeyBean = new Subdistrict();
				sdVillMap = (HashMap<String, String>) httpsession.getAttribute("sdVillMap");

				if (sdVillMap != null) {
					Set keys = sdVillMap.keySet();
					List<Village> listVillage = null;
					listVillage = new ArrayList<Village>();

					for (Iterator i = keys.iterator(); i.hasNext();) {
						key = (String) i.next();
						subdistrictkeyBean = subdistrictService.getSingleSubdistrictDetailsMaxVersion(Integer.parseInt(key));
						httpsession.removeAttribute(key);
						String value = (String) sdVillMap.get(key);
						// System.out.println("value of villagecodes"+value);
						value = value.replaceAll(",", ":");
						villageCodes = value + ":";
						key += "#" + villageCodes;
						key = key.substring(0, key.length() - 1);
						keyAppend += key + ",";

						sdVillMapView.put(subdistrictkeyBean, listVillage);

					}
					subdistrictCodes = key.substring(0, key.length() - 1);

				} else {
					subdistrictCodes = subDistrictForm.getTargetSubdistrictNo();
					villageCodes = subDistrictForm.getContributedVillages();
				}
			}

			if (!subDistrictForm.isRdoSubdistrictDelete()) {
				if (subDistrictForm.getTargetSubdistrictYes() != null) {
					String vv = "";
					List<Village> listVillage = null;
					listVillage = new ArrayList<Village>();
					int sourceSubDistrictCode = Integer.parseInt(subDistrictForm.getSubdistrictNameEnglish());
					listVillage = villageService.getVillageList(sourceSubDistrictCode);
					// sdVillMapView1.put(sourceSubDistrictCode, listVillage);
					for (int i = 0; i < listVillage.size(); i++) {
						int value1 = (listVillage.get(i).getVillageCode());
						vv = Integer.toString(value1);
						finalValue += vv + ":";

					}
					// subdistrictCodes = vv.substring(0, vv.length() - 1);
					keyAppend = subDistrictForm.getTargetSubdistrictYes() + "#" + finalValue;
					keyAppend = keyAppend.substring(0, keyAppend.length() - 1);
					subdistrictCodes = subDistrictForm.getTargetSubdistrictYes();
					sdcode = Integer.parseInt(subdistrictCodes);

				}
			}
			/* Modified by kirandeep for Invalidate Subdistrict on 16/03/2015 */
			if (subDistrictForm.getFlagSubDistrictInvalid() == -1) {
				subdistrictCode = Integer.parseInt(subDistrictForm.getSubdistrictNameEnglish());
				try {
					successResult = subdistrictService.invalidateSubDistrictCall(sourceSubdistCode, userId, null, effectiveDate, orderNo, orderDate, gazPubDate);
				} catch (BaseAppException e) {
					mv = new ModelAndView("error");
					mv.addObject("message", e.getMessage());
					return mv;
				}

			}
			if (keyAppend != null && !keyAppend.equals("") && sdcode == 0 && subDistrictForm.getFlagSubDistrictInvalid() != -1) {
				keyAppend = keyAppend.substring(0, keyAppend.length() - 1);
				subdistrictCode = Integer.parseInt(subDistrictForm.getSubdistrictNameEnglish());
				successResult = subdistrictService.invalidateSubDistrictCall(sourceSubdistCode, userId, keyAppend, effectiveDate, orderNo, orderDate, gazPubDate);
			}
			if (keyAppend != null && !keyAppend.equals("") && sdcode != 0 && subDistrictForm.getFlagSubDistrictInvalid() != -1) {
				subdistrictCode = Integer.parseInt(subDistrictForm.getSubdistrictNameEnglish());
				successResult = subdistrictService.invalidateSubDistrictCall(sourceSubdistCode, userId, keyAppend, effectiveDate, orderNo, orderDate, gazPubDate);
			}

			if (successResult != null) {
				String[] ldata = successResult.split(",");
				orderCode = ldata[0];
				tid = ldata[1];
			}

			int Transactionid = Integer.parseInt(tid);
			maxCode = Integer.parseInt(orderCode);

			// To use the Max order code returned from the function invalidate.
			if (sdcode > 0) {
				subdistrictBeanMerge = subdistrictService.getSingleSubdistrictDetailsMaxVersion(sdcode);
			}
			subdistrictBean = subdistrictService.getSingleSubdistrictDetailsMaxVersion(subdistrictCode);
			if (sdVillMap != null) {
				httpsession.removeAttribute("sdVillMap");
				sdVillMap.clear();
			}
			// Govt order code------------------
			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) httpsession.getAttribute("govtOrderForm");
			AddAttachmentBean addAttachmentBean = (AddAttachmentBean) httpsession.getAttribute("addAttachmentBean");
			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList;

			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			if (subDistrictForm.getGovtOrderConfig().equals("govtOrderUpload")) {
				AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
				attachmentHandler.validateAttachment(addAttachmentBean);
				metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
				attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			} else {
				metaInfoOfToBeAttachedFileList = null;
				govtOrderService.saveDataInAttachment(govtOrderForm, null, httpsession, maxCode, session);
			}

			try {
				govtOrderService.saveDataInAttachmentForInvalidSubD(govtOrderForm, metaInfoOfToBeAttachedFileList, maxCode, session);
				tx.commit();

				success = true;
			} catch (Exception e) {
				tx.rollback();
				session.close();
				log.debug("Exception" + e);
			} finally {

				if (session != null && session.isOpen()) {
					session.close();
				}
				httpsession.removeAttribute("formbean");
				httpsession.removeAttribute("govtOrderForm");
				httpsession.removeAttribute("addAttachmentBean");
			}

			if (success) {
				subname = subdistrictBean.getSubdistrictNameEnglish();
				merge = subdistrictBeanMerge.getSubdistrictNameEnglish();
				if (sdcode > 0 && !merge.equals(""))
					message = "The Sub-District " + subname + " is merged with the Sub-District " + merge + " successfully";
				else
					message = "The Sub-District " + subname + " is invalidated  successfully";
				model.addAttribute("msgid", message);

				mv = new ModelAndView("success");

				EmailSmsThread est = new EmailSmsThread(Transactionid, 'T', emailService);
				est.start();
				/*
				 * mv = new ModelAndView();
				 * 
				 * mv = new ModelAndView("invalidateSdSuccess");
				 * mv.addObject("sdVillMapView", sdVillMapView);
				 * mv.addObject("subdistrictBean", subdistrictBean);
				 * mv.addObject("subdistrictBeanMerge", subdistrictBeanMerge);
				 */
				return mv;
			} else {
				message = " Subdistrict Creation Failure !! ";
				mv = new ModelAndView("error");
				mv.addObject("message", message);
				return mv;
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView("error");
			mv.addObject("message", e.getCause().getMessage());
			// mv = new ModelAndView();
			return mv;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	/*@RequestMapping(value = "/modifySubDistrict", method = RequestMethod.GET)
	public ModelAndView modifyVillage(@ModelAttribute("modifySubDistrictCmd") SubDistrictForm modifySubDistrictCmd, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, @RequestParam("id") Integer subdistrictCode,
			@RequestParam(value = "disturb", required = false) String disturb) {
		ModelAndView mv = new ModelAndView("modify_subdistrict");
		try {
			List<SubdistrictDataForm> listSubdistrictDetails = subdistrictService.getSubdistrictDetailsModify(subdistrictCode);

			model.addAttribute("size", listSubdistrictDetails.size());
			model.addAttribute("listSubdistrictDetails", listSubdistrictDetails);
			model.addAttribute("disturb", disturb);
			modifySubDistrictCmd.setListSubdistrictDetails(listSubdistrictDetails);
			mv.addObject("modify_subdistrict", modifySubDistrictCmd);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
		}
		return mv;
	}*/

	/*@RequestMapping(value = "/modifySubDistrictAction", method = RequestMethod.POST)
	public String modifySubdistrict(@ModelAttribute("modifySubDistrictCmd") SubDistrictForm modifySubDistrictCmd, BindingResult result, SessionStatus status, Model model, HttpServletRequest request,
			@RequestParam(value = "disturb", required = false) String disturb, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("modify_subdistrict");
		try {
			////////// code for government order
			////////// checking///////////////////////////////////
			Map<String, String> hMap1 = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			hMap1 = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 10, 'V');// 10
																									// is
																									// operation
																									// code
																									// for
																									// create
																									// new
			String govtOrderConfig = hMap1.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap1.get("mapUpload");// values==true,false

			String viewSubdistrictdetail = null;

			subDistrictValidator.validate(modifySubDistrictCmd, result);

			if (result.hasErrors()) {

				if (govtOrderConfig != null && mapConfig != null) {
					mav.addObject("modifySubDistrictCmd", modifySubDistrictCmd);
					return "modify_subdistrict";
				}
				// Maneesh
				subdistrictService.modifySubDistrictInfo(modifySubDistrictCmd, request, httpSession);
				// Maneesh
				int subdistrictCode = 0;
				int subdistrictVersion = 0;

				List<SubdistrictDataForm> listSubdistrict = new ArrayList<SubdistrictDataForm>();
				listSubdistrict = modifySubDistrictCmd.getListSubdistrictDetails();
				Iterator<SubdistrictDataForm> itr = listSubdistrict.iterator();
				while (itr.hasNext()) {
					SubdistrictDataForm element = (SubdistrictDataForm) itr.next();
					subdistrictCode = element.getSubdistrictCode();
					subdistrictVersion = element.getSubdistrictVersion();
				}

				if (disturb.equals("true")) {
					viewSubdistrictdetail = "redirect:viewResolveEntitiesinDisturbedState.htm?resolved=" + subdistrictCode + "," + subdistrictVersion;
				} else {
					viewSubdistrictdetail = "redirect:viewSubDistrictDetailformodify.htm?id=" + subdistrictCode + "&type=S";
				}
			}
			return viewSubdistrictdetail;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return redirectPath;
		}
	}
*/
	@RequestMapping(value = "/viewSubDistrictDetailformodify", method = RequestMethod.GET)
	public ModelAndView viewSubDistrictDetail(@ModelAttribute("modifySubDistrictCmd") SubDistrictForm modifySubDistrictCmd, Model model, @RequestParam("id") Integer subdistrictCode, HttpServletRequest request, @RequestParam("type") String type) {
		ModelAndView mv = new ModelAndView("view_subdistrictdetail_change");
		try {
			String warningEntiesFlag = request.getParameter("warningEntiesFlag");
			if (warningEntiesFlag == null) {
				warningEntiesFlag = "true";
			}
			String stype = request.getParameter("stype");
			List<SubdistrictDataForm> listSubdistrictDetails = subdistrictService.getSubdistrictDetailsModify(subdistrictCode);

			modifySubDistrictCmd.setListSubdistrictDetails(listSubdistrictDetails);
			model.addAttribute("successMsg", "The sub-district was modified successfully ");
			mv.addObject("warningEntiesFlag", warningEntiesFlag);
			mv.addObject("type", type);
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganize_subdistrict_new_village", method = RequestMethod.GET)
	public String reorganizeSubDistrictNewVillGet(HttpSession httpSession, @ModelAttribute("addVillageNew") VillageForm addVillageNew, Model model, HttpServletRequest request) {
		try {
			Itr = 1;
			String temp = null;
			if (httpSession.getAttribute("lstVillForm") != null) {
				List<VillageForm> villForm = new ArrayList<VillageForm>();
				villForm = (List<VillageForm>) httpSession.getAttribute("lstVillForm");
				String contriVill[] = villForm.get(villForm.size() - 1).getContributedVillages().split(",");
				if (contriVill.length > 0) {
					for (int i = 0; i < contriVill.length; i++) {
						if (i == 0 && contriVill[i].contains("PART")) {
							temp = contriVill[i].replace("PART", "") + ",";
						}
						if (contriVill[i].contains("PART")) {
							temp += contriVill[i].replace("PART", "") + ",";
						}
					}
				}
				if (temp != null) {
					temp += villForm.get(villForm.size() - 1).getVillageList().replace("PART", "").replace("FULL", "");
				} else
					temp = villForm.get(villForm.size() - 1).getVillageList().replace("PART", "").replace("FULL", "");
			} else {
				SubDistrictForm subDistrictForm = (SubDistrictForm) httpSession.getAttribute("lstSDForm");
				temp = subDistrictForm.getContributedVillages().replace("PART", "").replace("FULL", "");
			}
			List<Village> listVill = new ArrayList<Village>();
			listVill = subdistrictService.getVillageDetailforReorganize(temp);
			model.addAttribute("listVill", listVill);
			return "reorganize_subdistrict_New_Village";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return redirectPath;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganize_subdistrict_new_village", method = RequestMethod.POST)
	public String reorganizeSubDistrictNewVill(HttpSession httpSession, @ModelAttribute("addVillageNew") VillageForm addVillageNew, BindingResult bindingResult, HttpServletRequest request, Model model) {
		Errors errors = null;
		try {
			ReorganizaVillageValidator cValidator = new ReorganizaVillageValidator();
			cValidator.validate(addVillageNew, bindingResult);

			if (bindingResult.hasErrors()) {
				Itr = 1;
				String temp = null;
				if (httpSession.getAttribute("lstVillForm") != null) {
					List<VillageForm> villForm = new ArrayList<VillageForm>();
					villForm = (List<VillageForm>) httpSession.getAttribute("lstVillForm");
					String contriVill[] = villForm.get(villForm.size() - 1).getContributedVillages().split(",");
					if (contriVill.length > 0) {
						for (int i = 0; i < contriVill.length; i++) {
							if (i == 0 && contriVill[i].contains("PART")) {
								temp = contriVill[i].replace("PART", "") + ",";
							}
							if (contriVill[i].contains("PART")) {
								temp += contriVill[i].replace("PART", "") + ",";
							}
						}
					}
					if (temp != null) {
						temp += villForm.get(villForm.size() - 1).getVillageList().replace("PART", "").replace("FULL", "");
					} else
						temp = villForm.get(villForm.size() - 1).getVillageList().replace("PART", "").replace("FULL", "");
				} else {
					SubDistrictForm subDistrictForm = (SubDistrictForm) httpSession.getAttribute("lstSDForm");
					temp = subDistrictForm.getContributedVillages().replace("PART", "").replace("FULL", "");
				}
				List<Village> listVill = new ArrayList<Village>();
				listVill = subdistrictService.getVillageDetailforReorganize(temp);
				model.addAttribute("listVill", listVill);
				return "reorganize_subdistrict_New_Village";
			} else {

				Itr = 1;
				List<VillageForm> listVill = new ArrayList<VillageForm>();

				if (httpSession.getAttribute("lstVillForm") != null) {
					List<VillageForm> villForm = new ArrayList<VillageForm>();
					villForm = (List<VillageForm>) httpSession.getAttribute("lstVillForm");
					for (int i = 0; i < villForm.size(); i++) {
						listVill.add(villForm.get(i));
					}
				}
				listVill.add(addVillageNew);
				httpSession.setAttribute("lstVillForm", listVill);

				if (addVillageNew.getCoveredLandRegionByULB().equals("Final")) {
					return "redirect:new_subdistrict.htm?modify";
				} else
					return "redirect:reorganize_subdistrict_new_village.htm";
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

	}

	@RequestMapping(value = "/reorganize_subdistrict_modify_village", method = RequestMethod.GET)
	public ModelAndView modifyVillageGet(HttpSession httpSession, @ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, Model model, HttpServletRequest request) {
		ModelAndView mv = null;
		try {
			Itr = 1;
			String villCode = null;
			mv = new ModelAndView("reorganize_subdistrict_Modify_Village");

			villCode = (String) httpSession.getAttribute("villCodeModify");
			int villageCode = Integer.parseInt(villCode.replace("PART", "").replace("FULL", ""));
			List<VillageDataForm> listVillageDetails = villageService.getVillageDetailsModify(villageCode);

			model.addAttribute("size", listVillageDetails.size());
			model.addAttribute("listVillageDetails", listVillageDetails);
			modifyVillageCmd.setListVillageDetails(listVillageDetails);
			mv.addObject("modifyVillageCmd", modifyVillageCmd);
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganize_subdistrict_modify_village", method = RequestMethod.POST)
	public ModelAndView modifyVillage(HttpSession httpSession, @ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, BindingResult result, SessionStatus status, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			Itr = 1;
			List<VillageForm> listVillModify = new ArrayList<VillageForm>();
			villageValidator.validate(modifyVillageCmd, result);
			if (result.hasErrors()) {
				mav = new ModelAndView("reorganize_subdistrict_Modify_Village");
			} else {
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
				mav = new ModelAndView("redirect:new_subdistrict.htm?modify");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/add_AnotherSD", method = RequestMethod.GET)
	public String addAnotherSD(HttpSession httpSession, @ModelAttribute("newsubdistrictform") SubDistrictForm sdForm, Model model, HttpServletRequest request) {
		try {
			Itr = 0;
			
			List<District> distList = subdistrictService.getDistrictListbyStateCode(stateCode);
			model.addAttribute("distList", distList);
			// code to get sub district list----------
			SubDistrictForm subDistrictForm = (SubDistrictForm) httpSession.getAttribute("lstSDForm");
			String temp = subDistrictForm.getContributedSubDistricts().replace("PART", "").replace("FULL", "");
			String[] temp1 = subDistrictForm.getContributedSubDistricts().split(",");
			List<Subdistrict> listSubDistrict = null;
			listSubDistrict = new ArrayList<Subdistrict>();
			listSubDistrict = subdistrictService.getSubDistrictViewList("from Subdistrict where subdistrictCode IN (" + temp + ") and isactive=true");
			for (int i = 0; i < listSubDistrict.size(); i++) {
				listSubDistrict.get(i).setSubdistrictNameEnglish(listSubDistrict.get(i).getSubdistrictNameEnglish() + " (" + (temp1[i].contains("FULL") ? "FULL)" : "PART)"));
				listSubDistrict.get(i).setAliasEnglish(listSubDistrict.get(i).getTlc() + (temp1[i].contains("FULL") ? "FULL" : "PART")); // temporarily
																																			// holding
																																			// sub
																																			// district
																																			// code
																																			// in
																																			// alias
			}
			model.addAttribute("listSD", listSubDistrict);
			model.addAttribute("newsubdistrictform", subDistrictForm);
			return "newsubdistrict";
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return redirectPath;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return redirectPath;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/preview_subdistrict", method = RequestMethod.GET)
	public String previewSubdistrictGet(HttpSession httpSession, @ModelAttribute("newsubdistrictform") SubDistrictForm sdForm, HttpServletRequest request, Model model) {
		try {
			Itr = 1;
			String sdFullList = null;
			List<Village> villListforFullSD = null;
			SubDistrictForm subDistrictForm = (SubDistrictForm) httpSession.getAttribute("lstSDForm");
			/*
			 * EsapiEncoder.encode(subDistrictForm);
			 */String temp = subDistrictForm.getContributedSubDistricts().replace("PART", "").replace("FULL", "");
			String[] temp1 = subDistrictForm.getContributedSubDistricts().split(",");
			List<Subdistrict> listSubDistrict = null;
			listSubDistrict = new ArrayList<Subdistrict>();
			listSubDistrict = subdistrictService.getSubDistrictViewList("from Subdistrict where subdistrictCode IN (" + temp + ") and isactive=true");
			for (int i = 0; i < listSubDistrict.size(); i++) {
				if (temp1[i].contains("FULL")) {
					if (sdFullList != null) {
						sdFullList += listSubDistrict.get(i).getTlc() + ",";
					} else {
						sdFullList = listSubDistrict.get(i).getTlc() + ",";
					}
				}
				listSubDistrict.get(i).setSubdistrictNameEnglish(listSubDistrict.get(i).getSubdistrictNameEnglish() + " (" + (temp1[i].contains("FULL") ? "FULL)" : "PART)"));
				listSubDistrict.get(i).setAliasEnglish(listSubDistrict.get(i).getTlc() + (temp1[i].contains("FULL") ? "FULL" : "PART")); // temporarily
																																			// holding
																																			// sub
																																			// district
																																			// code
																																			// in
																																			// alias
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
						if (i == 0 && contriVill[i].contains("PART")) {
							temp = contriVill[i].replace("PART", "") + ",";
						}
						if (contriVill[i].contains("PART")) {
							temp += contriVill[i].replace("PART", "") + ",";
						}
					}
				}
				if (temp != null) {
					temp += villForm.get(villForm.size() - 1).getVillageList().replace("PART", "").replace("FULL", "");
				} else
					temp = villForm.get(villForm.size() - 1).getVillageList().replace("PART", "").replace("FULL", "");

			} else {
				SubDistrictForm subDistrictF = new SubDistrictForm();
				subDistrictF = (SubDistrictForm) httpSession.getAttribute("lstSDForm");
				if (subDistrictF.getContributedVillages() != null) {
					temp = subDistrictF.getContributedVillages().replace("PART", "").replace("FULL", "");
				}
			}
			listVillage = villageService.getVillageViewList("from Village where villageCode IN (" + temp + ") and isactive=true");
			if (httpSession.getAttribute("lstVillForm") != null) {
				for (int k = 0; k < villForm.size(); k++) {
					Village vill = null;
					vill = new Village();
					vill.setVillageNameEnglish(villForm.get(k).getNewVillageNameEnglish() + " (New)");
					listVillage.add(vill);
				}
			}
			// -- code to get modified village list----
			if (httpSession.getAttribute("modifyVillForm") != null) {
				villForm = null;
				villForm = (List<VillageForm>) httpSession.getAttribute("modifyVillForm");
				List<VillageDataForm> listVillageData = new ArrayList<VillageDataForm>();
				VillageForm villageForm = new VillageForm();
				List<VillageDataForm> villDataForm = new ArrayList<VillageDataForm>();
				villDataForm = villageForm.getListVillageDetails();
				for (int k = 0; k < villForm.size(); k++) {
					Village vill = null;
					vill = new Village();
					vill.setVillageNameEnglish(villForm.get(k).getListVillageDetails().get(0).getVillageNameEnglish() + " (Modified)");
					listVillage.add(vill);
				}
			}
			// -----code to get District Name-------------------
			List<District> distList = new ArrayList<District>();
			distList = districtDAO.getDistrictListbyDistCode(Integer.parseInt(subDistrictForm.getDistrictCode()));

			model.addAttribute("distName", distList.get(0).getDistrictNameEnglish());
			model.addAttribute("listVill", listVillage);
			model.addAttribute("listSD", listSubDistrict);
			model.addAttribute("newsubdistrictform", subDistrictForm);

			// -----code for view -------------------
			if (sdFullList != null) {
				sdFullList = sdFullList.substring(0, sdFullList.length() - 1);
				villListforFullSD = new ArrayList<Village>();
				villListforFullSD = villageService.getVillageViewList("from Village v where v.subdistrict.subdistrictPK.subdistrictCode IN (" + sdFullList + ") and isactive=true");
				for (int i = 0; i < villListforFullSD.size(); i++) {
					listVillage.add(villListforFullSD.get(i));
				}
			}
			httpSession.setAttribute("contributedSDs", listSubDistrict);
			httpSession.setAttribute("contributedVills", listVillage);
			return "preview_subdistrict";
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return redirectPath;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return redirectPath;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/preview_subdistrict", method = RequestMethod.POST)
	public ModelAndView previewSubdistrict(HttpSession httpSession, @ModelAttribute("newsubdistrictform") SubDistrictForm sdForm, BindingResult bindingResult, HttpServletRequest request, Model model) {

		ModelAndView mav = null;
		try {
			sdForm.setOperation('C');
			httpSession.setAttribute("formbean", sdForm);
			mav = new ModelAndView("redirect:govtOrderCommon.htm");

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/rename_ContriSD", method = RequestMethod.GET)
	public String renameContriSD(HttpSession httpSession, @ModelAttribute("newsubdistrictform") SubDistrictForm sdForm, Model model, HttpServletRequest request) {
		try {
			SubDistrictForm subDistrictForm = (SubDistrictForm) httpSession.getAttribute("lstSDForm");
			String temp = null;
			String contriSD[] = subDistrictForm.getContributedSubDistricts().split(",");
			for (int i = 0; i < contriSD.length; i++) {
				if (contriSD[i].contains("PART")) {
					if (temp == null) {
						temp = contriSD[i].replace("PART", "") + ",";
						temp += contriSD[i].replace("PART", "") + ",";
					}

				}
			}
			temp = temp.substring(0, temp.length() - 1);
			List<Subdistrict> listSubDistrict = null;
			listSubDistrict = new ArrayList<Subdistrict>();
			listSubDistrict = subdistrictService.getSubDistrictViewList("from Subdistrict where subdistrictCode IN (" + temp + ") and isactive=true");
			model.addAttribute("listSD", listSubDistrict);
			return "rename_ContriSD";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return redirectPath;
		}
	}

	@RequestMapping(value = "/rename_ContriSD", method = RequestMethod.POST)
	public String renameContriSDPost(HttpSession httpSession, HttpServletRequest request, @ModelAttribute("newsubdistrictform") SubDistrictForm sdForm, Model model) {
		Itr = 1;
		try {
			List<SubDistrictForm> listSD = new ArrayList<SubDistrictForm>();
			SubDistrictForm sdFormBean = new SubDistrictForm();
			String[] tempSDCode = sdForm.getAliasEnglish().split(",");// temporarily
																		// getting
																		// code
																		// in
																		// alias
																		// code
			String[] tempEName = sdForm.getSubdistrictNameEnglish().split(",");
			String[] tempLName = sdForm.getSubdistrictNameLocal().split(",");
			for (int i = 0; i < tempEName.length; i++) {
				if (tempEName[i].length() > 0) {
					sdFormBean = null;
					sdFormBean = new SubDistrictForm();
					sdFormBean.setSubdistrictCode(Integer.parseInt(tempSDCode[i]));
					sdFormBean.setSubdistrictNameEnglish(tempEName[i]);
					if (tempLName.length > 0) {
						sdFormBean.setSubdistrictNameLocal(tempLName[i]);
					}
					listSD.add(sdFormBean);
				}
			}
			httpSession.setAttribute("contriSD", listSD);

			sdFormBean = null;
			sdFormBean = (SubDistrictForm) httpSession.getAttribute("lstSDForm");
			model.addAttribute("newsubdistrictform", sdFormBean);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return redirectPath;
		}
		return "redirect:new_subdistrict.htm";
	}

	@RequestMapping(value = "/cancelSD", method = RequestMethod.GET)
	public String cancel(HttpSession httpSession, HttpServletRequest request, @ModelAttribute("newsubdistrictform") SubDistrictForm sdForm, Model model) {
		try {
			httpSession.removeAttribute("lstSDForm");
			httpSession.removeAttribute("lstVillForm");
			httpSession.removeAttribute("modifyVillForm");
			httpSession.removeAttribute("contributedSDs");
			httpSession.removeAttribute("contributedVills");
			Itr = 0;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return redirectPath;
		}
		return "redirect:home.htm";
	}

	@ModelAttribute
	public SubDistrictForm get() {
		return new SubDistrictForm();
	}

	// @ModelAttribute("districtList")
	public List<District> getDistrictList(int stateCode, HttpSession httpSession, HttpServletRequest request) {
		try {

			return districtService.getDistrictList(stateCode);
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
		}
		return null;
	}

	// @Author - Sarvapriya Malhotra
	@RequestMapping(value = "/saveAsDraftInvalidateSubdistrict", method = RequestMethod.POST)
	public ModelAndView saveAsDraftInvalidateSubdistrict(@ModelAttribute("invalidatesubdistrict") SubDistrictForm invalidateSubdistrict, HttpSession httpSession, HttpServletRequest request) {

		ModelAndView mv = null;

		try {
			mv = new ModelAndView("invalidateSubDistrictDefault");
			subdistrictService.saveAsDraftInvalidateSubdistrict(invalidateSubdistrict, httpSession);

			// ModelAndView mav=new ModelAndView("createnew_village");

			mv.addObject("invalidatesubdistrict", invalidateSubdistrict);

			httpSession.removeAttribute("sdVillMap");
			httpSession.invalidate();

			List<District> districtList = null;
			districtList = new ArrayList<District>();
			districtList = this.getDistrictList(stateCode, httpSession, request);
			mv.addObject("districtList", districtList);
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	// @Author - Sarvapriya Malhotra
	@RequestMapping(value = "/publishSaveAsDraftInvalidateSubdistrict", method = RequestMethod.POST)
	public ModelAndView publishSaveAsDraftInvalidateSubdistrict(@ModelAttribute("invalidatesubdistrict") SubDistrictForm invalidateSubdistrict, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mv = null;
		try {
			mv = new ModelAndView("invalidateSubDistrictDefault");
			// ModelAndView mav=new ModelAndView("createnew_village");
			invalidateSubdistrict.setDistrictNameEnglish(subdistrictService.getInvalidateDraftSubdistrict("../invalidateSubdistrict.xml").getSourceSubdistrict().getDistrictDetails().getNameEnglish());
			mv.addObject("invalidatesubdistrict", invalidateSubdistrict);

			List<District> districtList = null;
			districtList = new ArrayList<District>();
			districtList = this.getDistrictList(stateCode, httpSession, request);
			mv.addObject("districtList", districtList);
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

	}

	List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
	List<Attachment> attachmentList = new ArrayList<Attachment>();
	List<SubdistrictDataForm> listsubdistrictDetails = new ArrayList<SubdistrictDataForm>();
	List<Headquarters> headquartersDetails = new ArrayList<Headquarters>();

	@RequestMapping(value = "/modifySubdistrictCrbyId")
	public ModelAndView modifySubDistrictCorrection(@ModelAttribute("modifySubDistrictCmd") SubDistrictForm modifySubDistrictCmd, HttpSession session, Model model, HttpServletRequest request) {

		ModelAndView mv = null;
		String strSubDistrictCode = request.getParameter("id");
		String reqWarningFlag = request.getParameter("warningEntiesFlag");
		session.setAttribute("reqWarningFlag", reqWarningFlag);
		session.setAttribute("id", strSubDistrictCode);
		Integer subdistrictCode = modifySubDistrictCmd.getSubdistrictId();
		if (subdistrictCode == null) {
			subdistrictCode = Integer.parseInt(strSubDistrictCode);
		}

		try {
			listsubdistrictDetails = subdistrictService.getSubdistrictDetailsModify(subdistrictCode);

			if (!listsubdistrictDetails.isEmpty()) {
				SubdistrictDataForm subdistrictDataForm = listsubdistrictDetails.get(0);
				modifySubDistrictCmd.setCordinate(subdistrictDataForm.getCordinate());
				Integer orderCode = subdistrictDataForm.getOrderCodecr();
				boolean mandatoryFlag = true;

				mv = new ModelAndView("modifySubdistrictCorrection");
				int operationCode = 7; // operation code modify sub district
				char operation = 'M';
				
				request.setAttribute("stateCode", stateCode);

				// new
				Map<String, String> hMap = new HashMap<String, String>();
				Map<String, String> hMap2 = new HashMap<String, String>();
				hMap = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
				hMap2 = govtOrderService.getMapConfiguration(stateCode, 7, 'T');
				String message = hMap.get("message");
				String message2 = hMap2.get("message");
				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap2.get("mapUpload");// value=true,false

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

				modifySubDistrictCmd.setOrderCode(subdistrictDataForm.getOrderCode());
				modifySubDistrictCmd.setOrderDate(subdistrictDataForm.getOrderDate());
				modifySubDistrictCmd.setOrderNo(subdistrictDataForm.getOrderNo());
				modifySubDistrictCmd.setEffectiveDate(subdistrictDataForm.getEffectiveDate());
				modifySubDistrictCmd.setGazPubDate(subdistrictDataForm.getGazPubDate());
				// modifySubDistrictCmd.setWarningFlag(subdistrictDataForm.getWarningFlag());
				setAttachmentDetails(modifySubDistrictCmd, request);

				if (orderCode != null) {
					attachmentList = govtOrderService.getAttachmentsbyOrderCode(orderCode);
					if (attachmentList.size() <= 0) {
						//mandatoryFlag = false;
					}

				} else {
					//mandatoryFlag = false;
					attachmentList = new ArrayList<Attachment>();
				}

				mapAttachmentList = getMapAttachmentListbySubDistrict(modifySubDistrictCmd, subdistrictCode, listsubdistrictDetails);

				modifySubDistrictCmd.setListSubdistrictDetails(listsubdistrictDetails);
				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);
				mv.addObject("mapConfig", mapConfig);
				mv.addObject("govtOrderConfig", govtOrderConfig);
				mv.addObject("modifySubDistrictCmd", modifySubDistrictCmd);
				mv.addObject("govtfilecount", attachmentList.size());
				mv.addObject("mapfilecount", mapAttachmentList.size());
				mv.addObject("mandatoryFlag", mandatoryFlag);
				session.setAttribute("mandatoryFlag", mandatoryFlag);
				mv.addObject("pageWarningEntiesFlag", subdistrictDataForm.getWarningFlag());

			} else {

				String aMessage = "Sorry Data Not Found For Your Selection ";
				mv = new ModelAndView("success");
				mv.addObject("msgid", aMessage);
				return mv;
			}
			return mv;

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

	}

	@RequestMapping(value = "/modifySubDistrictCrAction", method = RequestMethod.POST)
	public ModelAndView modifyVillagecorrection(@ModelAttribute("modifySubDistrictCmd") SubDistrictForm modifySubDistrictCmd, BindingResult result, SessionStatus status, Model model, HttpSession session, HttpServletRequest request) throws Exception

	{
		ModelAndView mav = null;
		try {
			List<SubdistrictDataForm> listSubdistrict = modifySubDistrictCmd.getListSubdistrictDetails();

			if (!listSubdistrict.isEmpty()) {
				SubdistrictDataForm element = listSubdistrict.get(0);

				int operationCode = 7;
				char operation = 'M';
				char entityType = 'T';
				Map<String, String> mapGovOrderConfig = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
				Map<String, String> mapMapConfig = govtOrderService.getMapConfiguration(stateCode, operationCode, entityType);
				String govtOrderConfig = mapGovOrderConfig.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = mapMapConfig.get("mapUpload");// values==true,false

				modifySubDistrictCmd.setOrderNocr(element.getOrderNocr());
				modifySubDistrictCmd.setOrderDatecr(element.getOrderDatecr());
				modifySubDistrictCmd.setOrdereffectiveDatecr(element.getOrdereffectiveDatecr());
				modifySubDistrictCmd.setGazPubDatecr(element.getGazPubDatecr());
				modifySubDistrictCmd.setStateCode(stateCode);
				
				mav = new ModelAndView("modifySubdistrictCorrection");
				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);
				subDistrictValidator.validate(modifySubDistrictCmd, result);
				boolean oldFileFlag= modifySubDistrictCmd.getAttachFile1().get(0).getSize() <= 0 &&  modifySubDistrictCmd.getGovtfilecount()!=null && Integer.parseInt(modifySubDistrictCmd.getGovtfilecount())>0?true:false;
				
				if(oldFileFlag) {
				commonValidator.isValidMimeforGovOrderLandRegion(result, request, modifySubDistrictCmd.getAttachFile1());
				commonValidator.isValidMimeforMapLandRegion(result, request, modifySubDistrictCmd.getAttachFile2());
				}
				if (result.hasErrors()) {

					String Cordinate = null;
					if (element.getCordinate() != null) {
						if (!element.getCordinate().trim().equals("")) {
							Cordinate = element.getCordinate();
						}
					}
					element.setCordinate(Cordinate);
					listSubdistrict.set(0, element);
					model.addAttribute("attachmentList", attachmentList);
					model.addAttribute("mapAttachmentList", mapAttachmentList);
					mav.addObject("modifySubDistrictCmd", modifySubDistrictCmd);
					modifySubDistrictCmd.setListSubdistrictDetails(listSubdistrict);
					request.setAttribute("attachmentList", attachmentList);
					request.setAttribute("mapAttachmentList", mapAttachmentList);
					mav.addObject("mapConfig", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
					mav.addObject("govtfilecount", attachmentList.size());
					mav.addObject("mapfilecount", mapAttachmentList.size());
					model.addAttribute("pageWarningEntiesFlag", modifySubDistrictCmd.getWarningFlag());
					if (session.getAttribute("mandatoryFlag") != null) {
						mav.addObject("mandatoryFlag", session.getAttribute("mandatoryFlag"));

					}

					return mav;
				}

				if (session.getAttribute("mandatoryFlag") != null) {
					session.removeAttribute("mandatoryFlag");
				}
				List<AttachedFilesForm> validFileGovtUpload = new ArrayList<AttachedFilesForm>();
				List<AttachedFilesForm> validFileMap = new ArrayList<AttachedFilesForm>();
				boolean delflag = false;
				if (govtOrderConfig.equals("govtOrderUpload")) {

					AddAttachmentBean govAttachmentBean = getAttachmentBean(modifySubDistrictCmd, request);
					validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govAttachmentBean, modifySubDistrictCmd.getAttachFile1(), result, mav);
					String deleteAttachmentId[] = govAttachmentBean.getDeletedFileID();
					if (mapConfig.equals("true")) {

						AddAttachmentBean mapAttachmentBean = getAttachmentBeanforMap(modifySubDistrictCmd, request);
						validFileMap = fileUploadUtility.ValidateAndUploadFileMap2(request, mapAttachmentBean, modifySubDistrictCmd.getAttachFile2(), result, mav);

						String deleteID[] = mapAttachmentBean.getDeletedFileID();
						if (deleteID != null && deleteID.length > 0) {
							delflag = true;
						}
						if (validFileMap != null) {
							boolean warningFlag = fileUploadUtility.validWarninngFlagMapUpload(mapAttachmentList, validFileMap);
							if (warningFlag) {
								modifySubDistrictCmd.setWarningFlag(!warningFlag);
							}
						}

						subdistrictService.modifySubDistrictCrInfo(modifySubDistrictCmd, request, validFileGovtUpload, validFileMap, delflag, deleteAttachmentId, session);

					} else {

						subdistrictService.modifySubDistrictCrInfo(modifySubDistrictCmd, request, validFileGovtUpload, null, delflag, deleteAttachmentId, session);

					}

				}

				else if (govtOrderConfig.equals("govtOrderGenerate")) {

					if (mapConfig.equals("true")) {

						AddAttachmentBean mapAttachmentBean = getAttachmentBeanforMap(modifySubDistrictCmd, request);
						validFileMap = fileUploadUtility.ValidateAndUploadFileMap2(request, mapAttachmentBean, modifySubDistrictCmd.getAttachFile2(), result, mav);

						String deleteID[] = mapAttachmentBean.getDeletedFileID();
						if (deleteID != null && deleteID.length > 0) {
							delflag = true;
						}
						if (validFileMap != null) {
							boolean warningFlag = fileUploadUtility.validWarninngFlagMapUpload(mapAttachmentList, validFileMap);
							if (warningFlag) {
								modifySubDistrictCmd.setWarningFlag(!warningFlag);
							}
						}

						subdistrictService.modifySubDistrictCrInfo(modifySubDistrictCmd, request, null, validFileMap, delflag, null, session);

					} else {
						subdistrictService.modifySubDistrictCrInfo(modifySubDistrictCmd, request, null, null, delflag, null, session);
					}
				}

				Object warningEntiesFlag = session.getAttribute("reqWarningFlag");
				session.removeAttribute("warningEntiesFlag");
				if (warningEntiesFlag != null && "W".equalsIgnoreCase(warningEntiesFlag.toString())) {
					mav = new ModelAndView("redirect:viewResolveEntitiesinDisturbedStateLR.htm?warningEntiesFlag=" + warningEntiesFlag.toString());
				} else {
					mav = new ModelAndView("redirect:viewSubDistrictDetailformodify.htm?id=" + element.getSubdistrictCode() + "&type=S");
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


	// Modify Village Change Government Order
	@RequestMapping(value = "/draftModifySubDistrict", method = RequestMethod.POST)
	public ModelAndView draftModifySubDistrict(@ModelAttribute("modifySubDistrictCmd") SubDistrictForm modifySubDistrictCmd, BindingResult result, SessionStatus status, HttpServletRequest request, HttpSession session) {
		ModelAndView mv = null;
		try {
			
			////////// code for government order
			////////// checking///////////////////////////////////
			Map<String, String> hMap1 = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			hMap1 = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 10, 'V');// 10
																									// is
																									// operation
																									// code
																									// for
																									// create
																									// new
			String govtOrderConfig = hMap1.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap1.get("mapUpload");// values==true,false
			////////// code for government order
			////////// checking///////////////////////////////////
			// mv = new ModelAndView();
			session.setAttribute("formbean", modifySubDistrictCmd);

			modifySubDistrictCmd = subDistrictValidator.validateChange(modifySubDistrictCmd, result);
			if (result.hasErrors()) {
				if (govtOrderConfig != null && mapConfig != null) {
					mv = new ModelAndView("modifySubdistrictchange");
					mv.addObject("modifySubDistrictCmd", modifySubDistrictCmd);

					return mv;
				}
			} else {
				mv = new ModelAndView("redirect:govtOrderCommon.htm");
			}
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	

	@RequestMapping(value = "/modifySubdistrictchangebyId")
	public ModelAndView modifySubDistrict(@ModelAttribute("modifyDistrictCmd") SubDistrictForm modifySubDistrictCmd, Model model, HttpSession session, HttpServletRequest request) {

		ModelAndView mv = null;
		int subdistrictCode = modifySubDistrictCmd.getSubdistrictId();
		try {
			int operationCode = 7;

			char operation = 'M';
			
			request.setAttribute("stateCode", stateCode);
			List<SubdistrictDataForm> listsubdistrictDetails = subdistrictService.getSubdistrictDetailsModify(subdistrictCode);
			EsapiEncoder.encode(listsubdistrictDetails);
			Map<String, String> hMap = new HashMap<String, String>();
			hMap = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
			String message = hMap.get("message");

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			model.addAttribute("size", listsubdistrictDetails.size());
			if (govtOrderConfig != null) {

				mv = new ModelAndView("modifySubdistrictchange");
				mv.addObject("govtOrderConfig", govtOrderConfig);
				model.addAttribute("listsubdistrictDetails", listsubdistrictDetails);
				session.setAttribute("listsubdistrictDetails", listsubdistrictDetails);

				modifySubDistrictCmd.setListSubdistrictDetails(listsubdistrictDetails);
				mv.addObject("modifySubDistrictCmd", modifySubDistrictCmd);
				mv.addObject("districtCode", listsubdistrictDetails.get(0).getDistrict_code());

			} else {
				mv = new ModelAndView("success");
				mv.addObject("msgid", message);
			}
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	// Modify ChangeSubdistrict Post Method
	@RequestMapping(value = "/modifySubdistrictChangeAction", method = RequestMethod.POST)
	public ModelAndView modifyDistrictChange(@ModelAttribute("modifyDistrictCmd") SubDistrictForm modifyDistrictCmd, BindingResult result, SessionStatus status, Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "disturb", required = false) String disturb) throws Exception {
		ModelAndView mav = null;
		try {
			String viewSubDistrictHistory = null;
			int subdistrictCode = 0;
			int subdistrictVersion = 0;
			boolean saveSuccess = false;
			AddAttachmentBean addAttachmentBean = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();

			SubDistrictForm subdistrictForm = (SubDistrictForm) session.getAttribute("formbean");
			// subdistrictForm.getOrderNo();
			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");

			mav = new ModelAndView("modifySubdistrictchange");
			subDistrictValidator.validateChange(subdistrictForm, result);
			if (result.hasErrors()) {
				mav.addObject("modifyDistrictCmd", modifyDistrictCmd);
				return mav;
			}

			if (session.getAttribute("addAttachmentBean") != null) {
				addAttachmentBean = (AddAttachmentBean) session.getAttribute("addAttachmentBean");
				String validateAttachment = attachmentHandler.validateAttachment(addAttachmentBean);
			}
			/*
			 * ======================================Attached File Saving Part
			 * ==========================================
			 */

			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
			if (metaInfoOfToBeAttachedFileList != null && metaInfoOfToBeAttachedFileList.size() != 0) {
				subdistrictForm.setStateCode(stateCode);
				subdistrictForm.setUserId(userId.intValue());
				saveSuccess = subdistrictService.changeSubDistrict(subdistrictForm, govtOrderForm, metaInfoOfToBeAttachedFileList, request);

				if (!saveSuccess) {
					mav = new ModelAndView("error");
					mav.addObject("message", "Faild to save the Information, Kindly try again");

				}
				String saveAttachment = attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
				saveSuccess = subdistrictService.changeSubDistrictforTemplate(subdistrictForm, govtOrderForm, request, session);

			}
			List<SubdistrictDataForm> listsubdistrict = new ArrayList<SubdistrictDataForm>();
			listsubdistrict = subdistrictForm.getListSubdistrictDetails();
			Iterator<SubdistrictDataForm> itr = listsubdistrict.iterator();
			while (itr.hasNext()) {
				SubdistrictDataForm element = (SubdistrictDataForm) itr.next();
				subdistrictCode = element.getSubdistrictCode();
				subdistrictVersion = element.getSubdistrictVersion();
			}
			mav = new ModelAndView("redirect:viewSubDistrictDetailformodify.htm?id=" + subdistrictCode + "&type=S");

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// Attachment File
	public void setAttachmentDetails(SubDistrictForm subdistrictform, HttpServletRequest request) {
		try {
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);

			subdistrictform.setAllowedFileType(attachmentList.getFileType());
			subdistrictform.setAllowedIndividualFileSize(attachmentList.getIndividualFileSize());
			subdistrictform.setAllowedNoOfAttachment(attachmentList.getFileLimit());
			subdistrictform.setAllowedTotalFileSize(attachmentList.getTotalFileSize());
			subdistrictform.setUploadLocation(attachmentList.getFileLocation());
			subdistrictform.setRequiredTitle(attachmentList.getRequireTitle());
			subdistrictform.setUniqueTitle(attachmentList.getUniqueTitle());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			// return mv;
		}

	}

	private AddAttachmentBean getAttachmentBean(SubDistrictForm subdistrictform, HttpServletRequest request) {

		try {
			List<Attachment> alreadyAttachList = null;
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0;
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			Integer orderCode = 0;
			List<SubdistrictDataForm> listsubdistrict = subdistrictform.getListSubdistrictDetails();

			if (!listsubdistrict.isEmpty()) {
				SubdistrictDataForm element = listsubdistrict.get(0);
				orderCode = element.getOrderCodecr();
			}
			/*
			 * String orderCode1 = Integer.toString(orderCode); if (orderCode1
			 * != null) { if (request.getParameterValues("deletedFileID1") !=
			 * null) { String[] deletedFileID1 = (String[]) request
			 * .getParameterValues("deletedFileID1");
			 * govtOrderService.deleteAttachedFiles(deletedFileID1, request,
			 * 'O'); }
			 */

			if (orderCode != null) {
				alreadyAttachList = govtOrderService.getAttachmentsbyOrderCode(orderCode);
				noOFAlreadyAttachedFiles1 = alreadyAttachList.size();
				// Already
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
			addAttachmentBeanTwo.setCurrentlyUploadFileList(subdistrictform.getAttachFile1());
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);
			addAttachmentBeanTwo.setFileTitle(subdistrictform.getFileTitle1());
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
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
	}

	// getting values for map from MapLocalBody
	private List<MapAttachment> getMapAttachmentListbySubDistrict(SubDistrictForm subDistrictForm, Integer subdistrictCode, List<SubdistrictDataForm> listsubdistrictDetails) {
		List<MapAttachment> mapAttachmentList = null;
		try {
			char entityType = 'T';
			mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(subdistrictCode, entityType);
		} catch (Exception ex) {
			log.debug("Exception" + ex);
		}
		return mapAttachmentList;
	}

	private AddAttachmentBean getAttachmentBeanforMap(SubDistrictForm subDistrictForm, HttpServletRequest request) {
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

			/*
			 * if (subDistrictForm.getOrderCode() != null) { if
			 * (request.getParameterValues("deletedFileID2") != null) { String[]
			 * deletedFileID1 = (String[]) request
			 * .getParameterValues("deletedFileID2");
			 * govtOrderService.deleteAttachedFiles(deletedFileID1, request,
			 * 'M'); }
			 */
			alreadyAttachList = govtOrderService.getMapAttachmentListbyEntity(subDistrictForm.getSubdistrictCode(), 'T');

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

			AddAttachmentBean addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(subDistrictForm.getAttachFile2());
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);
			addAttachmentBeanTwo.setFileTitle(subDistrictForm.getFileTitle2());
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
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/new_subdistrict", method = RequestMethod.POST)
	public ModelAndView publishSubDistrict(HttpSession httpSession, @ModelAttribute("newsubdistrictform") @Valid SubDistrictForm sdForm, BindingResult bindingResult, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		String villageadded = null;
		String partAddedSubdistrict = null;
		try {
			if (!sdForm.getButtonClicked().equals("NextNoValidation")) {
				Map<String, String> hMap1 = new HashMap<String, String>();
				hMap1 = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 10, 'V');
				String govtOrderConfig = hMap1.get("govtOrder");
				String mapConfig = hMap1.get("mapUpload");
				String Vilid = null;
				//subDistrictValidator.validateCensusCode(sdForm, bindingResult);
				commonValidator.isValidMime(bindingResult, request, sdForm.getAttachFile1());
				String discode = request.getParameter("flag2");
				if (sdForm.getDistrictCode() == null) {
					sdForm.setDistrictCode(discode);
				}
				comboFillingService.getComboValuesforApp('D', "S", stateCode, Integer.parseInt(sdForm.getDistrictCode()));
				if (bindingResult.hasErrors()) {

					if (govtOrderConfig != null && mapConfig != null) {

						// code to get sub district list----------
						String temp = sdForm.getContributedSubDistricts().replace("PART", "").replace("FULL", "");
						String[] temp1 = sdForm.getContributedSubDistricts().split(",");
						List<Subdistrict> listSubDistrict = null;
						listSubDistrict = new ArrayList<Subdistrict>();
						listSubDistrict = subdistrictService.getSubDistrictViewList("from Subdistrict where subdistrictCode IN (" + temp + ") and isactive=true");
						for (int i = 0; i < listSubDistrict.size(); i++) {
							listSubDistrict.get(i).setSubdistrictNameEnglish(listSubDistrict.get(i).getSubdistrictNameEnglish() + " (" + (temp1[i].contains("FULL") ? "FULL)" : "PART)"));
							listSubDistrict.get(i).setAliasEnglish(listSubDistrict.get(i).getTlc() + (temp1[i].contains("FULL") ? "FULL" : "PART")); // temporarily
						}
						// code to get village list----------
						temp = null;
						List<Village> listVillage = new ArrayList<Village>();
						if (httpSession.getAttribute("lstVillForm") != null) {
							List<VillageForm> villForm = new ArrayList<VillageForm>();
							villForm = (List<VillageForm>) httpSession.getAttribute("lstVillForm");
							if (villForm.get(villForm.size() - 1).getContributedVillages() != null) {
								String contriVill[] = villForm.get(villForm.size() - 1).getContributedVillages().split(",");
								for (int i = 0; i < contriVill.length; i++) {
									if (i == 0 && contriVill[i].contains("PART")) {
										temp = contriVill[i].replace("PART", "") + ",";
									}
									if (contriVill[i].contains("PART")) {
										temp += contriVill[i].replace("PART", "") + ",";
									}
								}
							}
							if (temp != null) {
								temp += villForm.get(villForm.size() - 1).getVillageList().replace("PART", "").replace("FULL", "");
							} else
								temp = villForm.get(villForm.size() - 1).getVillageList().replace("PART", "").replace("FULL", "");
						} else {
							if (sdForm.getContributedVillages() != null) {
								temp = sdForm.getContributedVillages().replace("PART", "").replace("FULL", "");
							}
						}
						listVillage = villageService.getVillageViewList("from Village where villageCode IN (" + temp + ") and isactive=true");
						model.addAttribute("listVill", listVillage);
						model.addAttribute("listSD", listSubDistrict);
						// ---------------------------------------------
						model.addAttribute("newsubdistrictform", sdForm);
						mav = new ModelAndView("newsubdistrict");
						if (districtCode == 0) {
							mav.addObject("flag1", 1);
							List<District> distList = subdistrictService.getDistrictListbyStateCode(stateCode);
							model.addAttribute("distList", distList);
						} else {

							List<District> distList = districtService.getDistrictListByDistCode(districtCode);
							model.addAttribute("distList", distList);
							mav.addObject("flag1", 0);
							mav.addObject("flag2", districtCode);

						}
						return mav;

					}
				} else {
					if (sdForm.getAttachFile1() != null && !sdForm.getAttachFile1().get(0).isEmpty()) {
						List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMapForSubDistrict(request, sdForm, bindingResult, mav);
						httpSession.setAttribute("validFileMap" + villageCount, validFileMap);
						sdForm.setMapUploaded(true);
					} else {
						sdForm.setMapUploaded(false);
					}
					if (Itr == 0) {
						httpSession.setAttribute("lstSDForm", sdForm);
					}

					if ((sdForm.isReorganized()).equals("true") || sdForm.getAddAnotherSD().equals("true") || sdForm.isContriSD() == true || sdForm.getButtonClicked().equals("Next")) {
						if ((sdForm.isReorganized()).equals("true") && sdForm.isNewVillage()) {
							return new ModelAndView("redirect:reorganize_subdistrict_new_village.htm");
						} else if ((sdForm.isReorganized()).equals("true") && sdForm.isNewVillage() == false) {
							httpSession.removeAttribute("villCodeModify");
							httpSession.setAttribute("villCodeModify", sdForm.getModifyVillage());
							return new ModelAndView("redirect:reorganize_subdistrict_modify_village.htm");
						} else if (sdForm.getButtonClicked().equals("Next")) {
							sdForm.setOperation('C');
							httpSession.setAttribute("formbean", sdForm);
							httpSession.setAttribute("villageCount", subDistCount);
							httpSession.setAttribute("subDistrictObject" + subDistCount, sdForm);
							mav = new ModelAndView("redirect:govtOrderCommon.htm");
						}
						if (sdForm.getAddAnotherSD().equals("true")) {
							sdForm.setAddAnotherSD("false");
							SubDistrictInfo subDistrictInfo = new SubDistrictInfo();
							List<String> fullSubDistrictList = new ArrayList<String>();
							List<String> villageList = new ArrayList<String>();
							List<SubDistrictInfo> subDistrictInfoList = new ArrayList<SubDistrictInfo>();
							String combinevillageNameList = sdForm.getVillageNameList();
							if (combinevillageNameList != null && !combinevillageNameList.equals("")) {
								String[] arrayvillageNameList = combinevillageNameList.split(",");
								for (int i = 0; i < arrayvillageNameList.length; i++) {
									String villageName = arrayvillageNameList[i];
									villageName = villageName.substring(0, villageName.length() - 6);
									villageList.add(villageName);
								}
							}
							// comment
							List<Subdistrict> listSubDistrict = new ArrayList<Subdistrict>();
							String sub = sdForm.getSubDistrictListForSession();
							if (sub != null && !sub.equals("")) {
								String[] subDistrictList = sub.split(",");
								for (int i = 0; i < subDistrictList.length; i++) {
									Subdistrict subdistrict = new Subdistrict();
									String string = subDistrictList[i];
									String[] array = string.split(":");
									subdistrict.setSubdistrictNameEnglish(array[0]);
									subdistrict.setAliasEnglish(array[1]);
									listSubDistrict.add(subdistrict);
								}
							}

							httpSession.setAttribute("listSubDistrict", listSubDistrict);
							villageadded = sdForm.getContributedVillages();
							if (villageadded != null) {

								String[] arrayLati = villageadded.split(",");
								villageadded = "";
								for (int i = 0; i < arrayLati.length; i++) {
									Vilid = arrayLati[i];
									Vilid = Vilid.substring(0, Vilid.length() - 4);
									villageadded = Vilid + "," + villageadded;

								}
							}
							if (villageadded != null && !("").equals(villageadded)) {
								villageadded = villageadded.substring(0, villageadded.length() - 1);
								if (!sdForm.getPreviousAddedVillageCodes().equals("")) {
									villageadded = sdForm.getPreviousAddedVillageCodes() + "," + villageadded;
								}
							}

							// mav.addObject("previousAddedVillageCodes",villageadded);
							// model.addAttribute("previousAddedVillageCodes",villageadded);
							/*---------------------------------------------------------*/

							List<Subdistrict> listSubDistrict1 = new ArrayList<Subdistrict>();
							String sub1 = sdForm.getContsubDistrictListForSession();
							if (sub1 != null && !sub1.equals("")) {
								String[] subDistrictList1 = sub1.split(",");
								for (int i = 0; i < subDistrictList1.length; i++) {
									boolean flag = true;
									Subdistrict subdistrict1 = new Subdistrict();
									String string = subDistrictList1[i];
									String[] array = string.split(":");
									for (int j = 0; j < array.length; j++) {
										String fullSD = array[0];
										if (fullSD.contains("FULL")) {
											fullSubDistrictList.add(fullSD);
											flag = false;
											break;
										}

									}
									if (flag) {
										subdistrict1.setSubdistrictNameEnglish(array[0]);
										subdistrict1.setAliasEnglish(array[1]);
										listSubDistrict1.add(subdistrict1);
									}
								}
							}

							httpSession.setAttribute("listSubDistrict1", listSubDistrict1);

							/*---------------------------------------------------------*/

							List<Village> listVillageList = new ArrayList<Village>();
							String sub2 = sdForm.getVillagesListForSession();
							httpSession.setAttribute("sub2", sub2);
							if (sub2 != null && !sub2.equals("")) {
								String[] villageListMain = sub2.split(",");
								for (int i = 0; i < villageListMain.length; i++) {
									Village village = new Village();
									String string = villageListMain[i];
									String[] array = string.split("@");
									village.setVillageNameEnglish(array[0]);
									village.setAliasEnglish(array[1]);
									listVillageList.add(village);
								}
							}

							httpSession.setAttribute("listVillageList", listVillageList);

							/*---------------------------------------------------------*/

							List<Village> listcontVillageList = new ArrayList<Village>();
							String sub3 = sdForm.getContvillagesListForSession();
							if (sub3 != null && !sub3.equals("")) {
								String[] villagecontListMain = sub3.split(",");
								for (int i = 0; i < villagecontListMain.length; i++) {
									Village village1 = new Village();
									String string = villagecontListMain[i];
									String[] array = string.split(":");
									village1.setVillageNameEnglish(array[0]);
									village1.setAliasEnglish(array[1]);
									listcontVillageList.add(village1);
								}
							}
							// httpSession.setAttribute("listcontVillageList",listcontVillageList);

							/*---------------------------------------------------------*/

							httpSession.setAttribute("districtCode", sdForm.getDistrictCode());

							// comment

							if (httpSession.getAttribute("subDistrictTitle") != null && !("").equals(httpSession.getAttribute("subDistrictTitle"))) {
								villageCount = (Integer) httpSession.getAttribute("subDistrictTitle");
							} else {
								villageCount = 1;
							}
							if (subDistCount == 1) {
								String subDistrictNames = sdForm.getSubdistrictNameEnglish().trim();
								httpSession.setAttribute("subDistrictNames", subDistrictNames);
							}
							if (subDistCount > 1) {

								List<SubDistrictInfo> tempList = (List<SubDistrictInfo>) httpSession.getAttribute("subDistrictInfoList");
								for (SubDistrictInfo tempsubDistrictInfo : tempList) {
									subDistrictInfoList.add(tempsubDistrictInfo);
								}
								String subDistrictNames = (String) httpSession.getAttribute("subDistrictNames");
								subDistrictNames = subDistrictNames + "," + sdForm.getSubdistrictNameEnglish();
								httpSession.setAttribute("subDistrictNames", subDistrictNames);
							}
							subDistrictInfo.setContributeVillages(villageList);
							subDistrictInfo.setSubdistrictNameEnglish(sdForm.getSubdistrictNameEnglish());
							subDistrictInfo.setSubdistrictNameLocal(sdForm.getSubdistrictNameLocal());
							subDistrictInfo.setAliasEnglish(sdForm.getAliasEnglish());
							subDistrictInfo.setAliasLocal(sdForm.getAliasLocal());
							subDistrictInfo.setCensus2011Code(sdForm.getCensus2011Code());
							subDistrictInfo.setSscode(sdForm.getSscode());
							subDistrictInfo.setVillageNumber(villageCount);
							subDistrictInfo.setContributesubDistrict(fullSubDistrictList);
							subDistrictInfoList.add(subDistrictInfo);

							partAddedSubdistrict = sdForm.getContvillagesListForSession();
							if (partAddedSubdistrict != null && !partAddedSubdistrict.isEmpty()) {
								String[] partsub = partAddedSubdistrict.split(",");
								String[] singleSubDistrict = null;
								String[] subNames = new String[partsub.length];
								partAddedSubdistrict = "";
								int time = 0;
								for (String str : partsub) {
									singleSubDistrict = str.split("\\(");
									subNames[time] = singleSubDistrict[0];
									time++;
								}
								subNames = new HashSet<String>(Arrays.asList(subNames)).toArray(new String[0]);
								for (String st : subNames)
									partAddedSubdistrict = st + "," + partAddedSubdistrict;
								partAddedSubdistrict = partAddedSubdistrict.substring(0, partAddedSubdistrict.length() - 1);
							}

							httpSession.setAttribute("villageCount", villageCount);
							httpSession.setAttribute("subDistrictObject" + villageCount, sdForm);
							httpSession.setAttribute("subDistrictInfoList", subDistrictInfoList);
							if (sub2 != null && !sub2.equals("")) {
								villageCount++;
								httpSession.setAttribute("subDistrictTitle", villageCount);

							} else {
								httpSession.setAttribute("subDistrictTitle", "");
								Integer subFullCount = 0;
								if (sdForm.getContributedSubDistricts() != null && !("").equals(sdForm.getContributedSubDistricts()) && sdForm.getContributedVillages() == null) {
									String subArr[] = sdForm.getContributedSubDistricts().split(",");
									for (int i = 0; i < subArr.length; i++) {
										if (subArr[i].indexOf("FULL") > -1) {
											subFullCount++;
										}
									}
								}
								if (subFullCount < 2) {
									model.addAttribute("visibility", "display: none;");
									model.addAttribute("visibilityNext", "display: none;");
								}
							}

							mav = new ModelAndView("redirect:new_subdistrict_without_session.htm");

						}

						if (sdForm.isContriSD() == true) {
							mav = new ModelAndView("redirect:rename_ContriSD.htm");
						}
					}
					mav.addObject("govtOrderConfig", sdForm.getGovtOrderConfig());
				}
			} else {
				httpSession.setAttribute("subDistrictTitle", "");
				sdForm.setOperation('C');
				httpSession.setAttribute("formbean", sdForm);
				mav = new ModelAndView("redirect:govtOrderCommon.htm");
				mav.addObject("govtOrderConfig", sdForm.getGovtOrderConfig());
			}
		} catch (NumberFormatException e) {

			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		mav.addObject("previousAddedVillageCodes", villageadded);
		mav.addObject("contributedSubDistricts", partAddedSubdistrict);
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/new_subdistrict", method = RequestMethod.GET)
	public ModelAndView publishSubDistrictGet(HttpSession httpSession, @ModelAttribute("newsubdistrictform") SubDistrictForm sdForm, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		
		// CLEANUP OF SESSION OBJECT
		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				Enumeration attributeNames = session.getAttributeNames();
				while (attributeNames.hasMoreElements()) {
					String sAttribute = attributeNames.nextElement().toString();
					if (sAttribute.equals("lstSDForm") || sAttribute.equals("villCodeModify") || sAttribute.equals("formbean") || sAttribute.equals("listSubDistrict") || sAttribute.equals("listSubDistrict1") || sAttribute.equals("listVillageList")
							|| sAttribute.equals("districtCode") || sAttribute.equals("subDistrictInfoList") || sAttribute.equals("subDistrictTitle") || sAttribute.equals("sub2") || sAttribute.equals("subDistrictNames")) {
						// System.out.println(request.getRequestURL()+" Removing
						// Session Attribute : "+sAttribute);
						session.removeAttribute(sAttribute);
					}
				}

				if (session.getAttribute("villageCount") != null) {
					Integer villageCount = (Integer) httpSession.getAttribute("villageCount");
					for (int i = 1; i <= villageCount; i++) {
						session.removeAttribute("subDistrictObject" + i);
					}
					session.removeAttribute("villageCount");
				}

			}
		} catch (Exception e) {

			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		try {
			if (httpSession.getAttribute("villageCount") != null) {
				model.addAttribute("display", "display: block;");
				model.addAttribute("disabled", true);
				model.addAttribute("visibilityNext", "display: none;");
				if (request.getParameter("visibility") != null && !request.getParameter("visibility").equals("")) {
					model.addAttribute("visibility", "display: none;");
					model.addAttribute("visibilityNext", "display: inline;");
					model.addAttribute("topdiv", "display: none;");
				}
			} else {
				model.addAttribute("visibilityNext", "display: none;");
				model.addAttribute("display", "display: none;");
				model.addAttribute("disabled", false);
				model.addAttribute("topdiv", "display: inline;");
			}

			if (stateCode== null) {
				mav = new ModelAndView("redirect:login.htm");
			} else {
				if (Itr == 0) {
					
					/*
					 * added on 31/12/2014 for the localbody impact by kirandeep
					 */
					List<District> distList = subdistrictService.getDistrictListbyStateCodeForLocalBody(stateCode);

					model.addAttribute("distList", distList);
				}
				Map<String, String> hMap = new HashMap<String, String>();
				hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 16, 'T');// 10
																									// is
																									// operation
																									// code
				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap.get("mapUpload");// values==true,false
				String message = hMap.get("message");
				if (govtOrderConfig != null && mapConfig != null) {

					mav = new ModelAndView("newsubdistrict");
					if (districtCode == 0) {

						mav.addObject("flag1", 1);

					} else {
						List<District> distList = districtService.getDistrictListByDistCode(districtCode);
						model.addAttribute("distList", distList);
						mav.addObject("flag1", 0);
						mav.addObject("flag2", districtCode);
					}
					mav.addObject("hideMap", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", message);
				}

			}
		} catch (NumberFormatException e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/new_subdistrict_without_session", method = RequestMethod.GET)
	public ModelAndView publishSubDistrictGetNew(HttpSession httpSession, @ModelAttribute("newsubdistrictform") SubDistrictForm sdForm, Model model, HttpServletRequest request) {
		ModelAndView mav = null;

		try {
			subDistCount = subDistCount + 1;
			if (httpSession.getAttribute("villageCount") != null) {
				model.addAttribute("display", "display: block;");
				model.addAttribute("disabled", true);
				model.addAttribute("visibilityNext", "display: none;");
				if (request.getParameter("visibility") != null && !request.getParameter("visibility").equals("")) {
					model.addAttribute("visibility", "display: none;");
					model.addAttribute("visibilityNext", "display: inline;");
					model.addAttribute("topdiv", "display: none;");
				}
			} else {
				model.addAttribute("visibilityNext", "display: none;");
				model.addAttribute("display", "display: none;");
				model.addAttribute("disabled", false);
				model.addAttribute("topdiv", "display: inline;");
			}

			if (stateCode == null) {
				mav = new ModelAndView("redirect:login.htm");
			} else {
				if (Itr == 0) {
					

					if (districtCode != null) {
						sdForm.setDistrictCode(districtCode.toString());
					}

				} else {

					// code to get sub district list----------
					SubDistrictForm subDistrictForm = (SubDistrictForm) httpSession.getAttribute("lstSDForm");
					String temp = subDistrictForm.getContributedSubDistricts().replace("PART", "").replace("FULL", "");
					String[] temp1 = subDistrictForm.getContributedSubDistricts().split(",");
					List<Subdistrict> listSubDistrict = null;
					listSubDistrict = new ArrayList<Subdistrict>();
					listSubDistrict = subdistrictService.getSubDistrictViewList("from Subdistrict where subdistrictCode IN (" + temp + ") and isactive=true");
					for (int i = 0; i < listSubDistrict.size(); i++) {
						listSubDistrict.get(i).setSubdistrictNameEnglish(listSubDistrict.get(i).getSubdistrictNameEnglish() + " (" + (temp1[i].contains("FULL") ? "FULL)" : "PART)"));
						listSubDistrict.get(i).setAliasEnglish(listSubDistrict.get(i).getTlc() + (temp1[i].contains("FULL") ? "FULL" : "PART")); // temporarily
																																					// holding
																																					// sub
																																					// district
																																					// code
																																					// in
																																					// alias
					}
					// code to get village list----------
					temp = null;
					List<Village> listVillage = new ArrayList<Village>();
					if (httpSession.getAttribute("lstVillForm") != null) {
						List<VillageForm> villForm = new ArrayList<VillageForm>();
						villForm = (List<VillageForm>) httpSession.getAttribute("lstVillForm");
						if (villForm.get(villForm.size() - 1).getContributedVillages() != null) {
							String contriVill[] = villForm.get(villForm.size() - 1).getContributedVillages().split(",");
							for (int i = 0; i < contriVill.length; i++) {
								if (i == 0 && contriVill[i].contains("PART")) {
									temp = contriVill[i].replace("PART", "") + ",";
								}
								if (contriVill[i].contains("PART")) {
									temp += contriVill[i].replace("PART", "") + ",";
								}
							}
						}
						if (temp != null) {
							temp += villForm.get(villForm.size() - 1).getVillageList().replace("PART", "").replace("FULL", "");
						} else
							temp = villForm.get(villForm.size() - 1).getVillageList().replace("PART", "").replace("FULL", "");
					} else {
						SubDistrictForm subDistrictF = new SubDistrictForm();
						try {
							subDistrictF = (SubDistrictForm) httpSession.getAttribute("lstSDForm");
							temp = subDistrictF.getContributedVillages().replace("PART", "").replace("FULL", "");
						} catch (Exception e) {
							httpSession.removeAttribute("lstSDForm");
							httpSession.removeAttribute("lstVillForm");
							httpSession.removeAttribute("modifyVillForm");
							httpSession.removeAttribute("contributedSDs");
							httpSession.removeAttribute("contributedVills");
							Itr = 0;
							mav = new ModelAndView("redirect:new_subdistrict.htm");
							return mav;
						}
					}
					listVillage = villageService.getVillageViewList("from Village where villageCode IN (" + temp + ") and isactive=true");
					model.addAttribute("listVill", listVillage);
					model.addAttribute("listSD", listSubDistrict);
					// ---------------------------------------------

					model.addAttribute("newsubdistrictform", subDistrictForm);
				}
				// Copy to Check Configuration
				Map<String, String> hMap = new HashMap<String, String>();

				// Please first check your operation code then set it in place
				// of 10
				hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 16, 'T');// 10
																									// is
																									// operation
																									// code
				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap.get("mapUpload");// values==true,false
				String message = hMap.get("message");
				if (govtOrderConfig != null && mapConfig != null) {
					mav = new ModelAndView("newsubdistrict");
					mav.addObject("hideMap", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", message);
				}
				int distcode = Integer.parseInt(sdForm.getDistrictCode());
				List<District> distList = districtService.getDistrictListByDistCode(distcode);
				model.addAttribute("distList", distList);
				mav.addObject("flag1", 0);

			}
		} catch (NumberFormatException e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		mav.addObject("previousAddedVillageCodes", sdForm.getPreviousAddedVillageCodes());
		mav.addObject("partAddedSubdistrict", sdForm.getContributedSubDistricts());
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveSubdistrict", method = RequestMethod.POST)
	public synchronized ModelAndView saveSubdistrict(HttpSession httpSession, @ModelAttribute("newsubdistrictform") SubDistrictForm sdForm, BindingResult bindingResult, HttpServletRequest request, Model model,
			@RequestParam(value = "fileName", required = false) String fileName) throws Exception {
		Integer annouId = null;
		Transaction tx = null;
		ModelAndView mav = null;
		Session session = null;
		Integer mapAttachmentId = 0;
		SubDistrictForm subDistrictForms = null;
		String subName = "";

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			if (httpSession.getAttribute("villageCount") != null) {
				Integer villageCount = (Integer) httpSession.getAttribute("villageCount");
				for (int i = 1; i <= villageCount; i++) {
					if (httpSession.getAttribute("subDistrictObject" + i) != null) {
						subDistrictForms = (SubDistrictForm) httpSession.getAttribute("subDistrictObject" + i);
						subDistrictForms.setOrderNo(sdForm.getOrderNo());
						subDistrictForms.setOrderDate(sdForm.getOrderDate());
						subDistrictForms.setGazPubDate(sdForm.getGazPubDate());
						subDistrictForms.setEffectiveDate(sdForm.getEffectiveDate());
						subDistrictForms.setUserId(userId);
						subName = subDistrictForms.getSubdistrictNameEnglish() + ", " + subName;
						if (subDistrictForms.isMapUploaded()) {
							List<AttachedFilesForm> validFileMap = (List<AttachedFilesForm>) httpSession.getAttribute("validFileMap" + i);
							mapAttachmentId = subdistrictService.saveDataInMap(subDistrictForms, validFileMap, httpSession, session);
							subDistrictForms.setMapCode(mapAttachmentId);
						}
						String commonString = subdistrictService.insertSubDistrict(request, httpSession, subDistrictForms, session);
						String[] subStringsArray = commonString.split(",");
						annouId = Integer.parseInt(subStringsArray[1]);
					}
				}

			}
			subName = subName.substring(0, subName.length() - 2);
			GovernmentOrderForm governmentOrderForm = new GovernmentOrderForm();
			governmentOrderForm.setOrderCode(annouId);
			governmentOrderForm.setOrderNo(sdForm.getOrderNo());
			governmentOrderForm.setEffectiveDate(sdForm.getEffectiveDate());
			governmentOrderForm.setGazPubDate(sdForm.getGazPubDate());
			governmentOrderForm.setOrderDate(sdForm.getOrderDate());
			governmentOrderForm.setAttachFile1(sdForm.getAttachFile1());
			if (subDistrictForms.getGovtOrderConfig().equals("govtOrderUpload")) {
				List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, governmentOrderForm, bindingResult);
				villageService.saveDataInAttach(governmentOrderForm, validFileGovtUpload, request.getSession(), session);
			} else {
				govtOrderService.saveDataInAttachment(governmentOrderForm, null, httpSession, annouId, session);
			}
			if (subName == null) {
				subName = "";
			}
			model.addAttribute("msgid", " The New Sub-district " + subName + " is created Successfully");

			mav = new ModelAndView("success");
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			model.addAttribute("msgid", " Sub-district creation failure please check Logs");
			mav = new ModelAndView("success");
			return mav;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
			httpSession.removeAttribute("lstSDForm");
			httpSession.removeAttribute("formbean");
			httpSession.removeAttribute("villageCount");
			httpSession.removeAttribute("subDistrictInfoList");
			httpSession.removeAttribute("lstVillForm");
			if (httpSession.getAttribute("subDistrictObject1") != null) {
				httpSession.removeAttribute("subDistrictObject1");
			}

			if (httpSession.getAttribute("listVillageList") != null) {
				httpSession.removeAttribute("listVillageList");
			}

			if (httpSession.getAttribute("listSubDistrict") != null) {
				httpSession.removeAttribute("listSubDistrict");
			}
		}
		return mav;
	}

	@RequestMapping(value = "/updatesubdistrictdtls", method = RequestMethod.POST)
	public ModelAndView updatesubdistrictdtls(HttpSession httpSession, @ModelAttribute("newsubdistrictform") @Valid SubDistrictForm sdForm, BindingResult bindingResult, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		try {
			Integer villageNumber = null;
			SubDistrictForm subDistrictForm = null;
			if (request.getParameter("villageNumber") != null) {
				villageNumber = Integer.parseInt(request.getParameter("villageNumber"));
			}
			if (httpSession.getAttribute("subDistrictObject" + villageNumber) != null) {
				subDistrictForm = (SubDistrictForm) httpSession.getAttribute("subDistrictObject" + villageNumber);
			}
			subDistrictForm.setSubdistrictNameEnglish(request.getParameter("subdistengtemp"));
			subDistrictForm.setSubdistrictNameLocal(request.getParameter("subdistloctemp"));
			subDistrictForm.setAliasEnglish(request.getParameter("alengtemp"));
			subDistrictForm.setAliasLocal(request.getParameter("alloctemp"));
			subDistrictForm.setCensus2011Code(request.getParameter("cencd2011temp"));
			subDistrictForm.setSscode(request.getParameter("sscdtemp"));

			// --------------------------

			List<SubDistrictInfo> subDistrictInfoList = new ArrayList<SubDistrictInfo>();
			if (httpSession.getAttribute("subDistrictTitle") != null && !httpSession.getAttribute("subDistrictTitle").equals("")) {
				villageCount = (Integer) httpSession.getAttribute("subDistrictTitle");
			} else {
				villageCount = 1;
			}
			/*
			 * if(villageCount==1){ String subDistrictNames =
			 * subDistrictForm.getSubdistrictNameEnglish().trim();
			 * httpSession.setAttribute("subDistrictNames", subDistrictNames); }
			 */
			if (villageCount >= 1) {
				String subDistrictNamestemp = "";
				List<SubDistrictInfo> tempList = (List<SubDistrictInfo>) httpSession.getAttribute("subDistrictInfoList");
				for (SubDistrictInfo tempsubDistrictInfo : tempList) {
					if (tempsubDistrictInfo.getVillageNumber() == villageNumber.intValue()) {
						String subDistrictNames = (String) httpSession.getAttribute("subDistrictNames");
						String[] array = subDistrictNames.split(",");// AAA,BBB
																		// AND
																		// BBB
						for (int i = 0; i < array.length; i++) {
							String string = array[i];
							if (string.equalsIgnoreCase(tempsubDistrictInfo.getSubdistrictNameEnglish())) {
								if (subDistrictNamestemp.equalsIgnoreCase("")) {
									subDistrictNamestemp = subDistrictForm.getSubdistrictNameEnglish().trim();
								} else {
									subDistrictNamestemp = subDistrictNamestemp + "," + subDistrictForm.getSubdistrictNameEnglish().trim();
								}
							} else {
								if (subDistrictNamestemp.equalsIgnoreCase("")) {
									subDistrictNamestemp = string.trim();
								} else {
									subDistrictNamestemp = subDistrictNamestemp + "," + string.trim();
								}

							}

						}
						httpSession.setAttribute("subDistrictNames", subDistrictNamestemp);
						/*
						 * subDistrictNames = subDistrictNames + "," +
						 * subDistrictForm.getSubdistrictNameEnglish();
						 * httpSession.setAttribute("subDistrictNames",
						 * subDistrictNames);
						 */
						tempsubDistrictInfo.setSubdistrictNameEnglish(subDistrictForm.getSubdistrictNameEnglish());
						tempsubDistrictInfo.setSubdistrictNameEnglish(subDistrictForm.getSubdistrictNameEnglish());
						tempsubDistrictInfo.setSubdistrictNameLocal(subDistrictForm.getSubdistrictNameLocal());
						tempsubDistrictInfo.setAliasEnglish(subDistrictForm.getAliasEnglish());
						tempsubDistrictInfo.setAliasLocal(subDistrictForm.getAliasLocal());
						tempsubDistrictInfo.setCensus2011Code(subDistrictForm.getCensus2011Code());
						tempsubDistrictInfo.setSscode(subDistrictForm.getSscode());
						// tempsubDistrictInfo.setVillageNumber(villageCount);

					}
					subDistrictInfoList.add(tempsubDistrictInfo);
				}

			}
			httpSession.setAttribute("subDistrictObject" + villageNumber, subDistrictForm);
			httpSession.setAttribute("villageCount", villageNumber);
			httpSession.setAttribute("subDistrictInfoList", subDistrictInfoList);
			String sub2 = "";
			if (httpSession.getAttribute("sub2") != null) {
				sub2 = (String) httpSession.getAttribute("sub2");
			}

			if (sub2 == null || sub2.equals("")) {
				httpSession.setAttribute("subDistrictTitle", "");
				model.addAttribute("visibility", "display: none;");
				model.addAttribute("visibilityNext", "display: none;");
			}

			// ----------------

			mav = new ModelAndView("redirect:new_subdistrict_without_session.htm");
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// View SubDistrict Part
	@RequestMapping(value = "/viewsubdistrict", method = RequestMethod.GET)
	public ModelAndView showSubDistrictPage(@ModelAttribute("subdistrictbean") SubDistrictForm subdistrictbean, Model model, HttpSession httpSession, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			mav = new ModelAndView("view_subdistrict");
				if (districtCode > 0) {
				List<Subdistrict> subDistrictList = subdistrictService.getSubDistrictListForLocalbody(districtCode);
				subdistrictbean.setLimit(1000);
				httpSession.setAttribute("limit", subdistrictbean.getLimit());
				subdistrictbean.setOffset(1);
				httpSession.setAttribute("offset", subdistrictbean.getOffset());
				httpSession.setAttribute("districtCode", districtCode);
				model.addAttribute("SEARCH_SUBDISTRICT_RESULTS_KEY", subDistrictList);
				model.addAttribute("viewPage", false);
				if (subDistrictList.isEmpty()) {
					model.addAttribute("noRecord", true);
				}
				return mav;
			}

			mav.addObject("subdistrictbean", new SubDistrictForm());
			model.addAttribute("districtList", districtDAO.getDistrictListbyStateCodeForLocalBody(stateCode));
			model.addAttribute("viewPage", true);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/viewsubdistrict", method = RequestMethod.POST)
	public ModelAndView getSubDistrictPage(@ModelAttribute("subdistrictbean") SubDistrictForm subdistrictbean, BindingResult result, HttpServletRequest request, Model model, HttpSession httpSession) {

		ModelAndView mav = null;
		try {

			String strDistrictCode = subdistrictbean.getDistrictNameEnglish();
			if (!strDistrictCode.equals("")) {
				mav = new ModelAndView("view_subdistrict");
				subDistrictValidator.viewValidateSubDistrict(subdistrictbean, result);
				if (result.hasErrors()) {
					result.getAllErrors();
					return mav;
				}

				Integer districtCode = Integer.parseInt(strDistrictCode);
				List<Subdistrict> subDistrictList = subdistrictService.getSubDistrictListForLocalbody(districtCode);

				model.addAttribute("SEARCH_SUBDISTRICT_RESULTS_KEY", subDistrictList);
				subdistrictbean.setOffset(1);
				subdistrictbean.setLimit(1000);
				model.addAttribute("districtList", districtDAO.getDistrictListbyStateCodeForLocalBody(stateCode));
				model.addAttribute("viewPage", "abc");
				model.addAttribute("offsets", 0);
				model.addAttribute("limits", 1000);
				mav.addObject("subdistrictbean", subdistrictbean);
				if (subDistrictList.isEmpty()) {
					model.addAttribute("noRecord", true);
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

	

	/**
	 * Add for localBody Draft mode to get District List for selected state
	 * 
	 * @author Ripunj on 07-01-2015
	 */
	// @ModelAttribute("districtList")
	public List<District> getDistrictListByStateCodeForLocalBody(int stateCode, HttpSession httpSession, HttpServletRequest request) {
		try {

			return districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
		}
		return null;
	}
	
	@RequestMapping(value = "/modifySubdistrictChangeEffectiveDate", method = RequestMethod.POST)
	public ModelAndView modifyVillageChangeEffectiveDate(@ModelAttribute("draftSubdistrictForm") DraftManageSubdistrictForm subdistrictbean, Model model, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("CHANGE_EFFECTIVE_DATE_OF_SUBDISTRICT");
			Integer SubdistrcitCode=null;
		try {
			//model.addAttribute("SdCode",subdistrictbean.getSubdistrictCode());
			SubdistrcitCode=Integer.valueOf(request.getParameter("id"));
			model.addAttribute("SdCode",SubdistrcitCode);
			model.addAttribute("curDate",new Date());
			
			
				} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/saveEffectiveDateSubdistrict", headers="Accept=application/json", method = RequestMethod.POST)
	public @ResponseBody Response saveEffectiveDate(@RequestBody EffectiveDateList getEntityEffectiveDateList,HttpServletRequest request) throws Exception {
		HttpSession httpsession= request.getSession();
		setGlobalParams(httpsession);
		return subdistrictService.saveEffectiveDateEntitySubdistrict(getEntityEffectiveDateList,userId.intValue());
	}
	
	
	
	

}
