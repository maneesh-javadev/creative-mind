package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.DeptAdminUnitEntity;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictLineDepartment;
import in.nic.pes.lgd.bean.EntityTransactionsNew;
import in.nic.pes.lgd.bean.ExistingDeptMapping;
import in.nic.pes.lgd.bean.ExtendDepartment;
import in.nic.pes.lgd.bean.LgdOrganizationAtLevels;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.common.MailService;
import in.nic.pes.lgd.draft.form.DepartmentOrgListDto;
import in.nic.pes.lgd.forms.AdminOrgDeptForm;
import in.nic.pes.lgd.forms.DepartmentForm;
import in.nic.pes.lgd.forms.MinistryForm;
import in.nic.pes.lgd.forms.OrganizationTypeForm;
import in.nic.pes.lgd.resourcebundles.LGDResourceBundle;
import in.nic.pes.lgd.service.AdministrativeDepratmentService;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.OrganizationService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.validator.AdminDepartmentValidator;
import in.nic.pes.lgd.validator.DepartmentValidator;
import in.nic.pes.lgd.validator.MinistryValidator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.batik.svggen.font.table.ScriptList;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class DepartmentController { // NO_UCD (unused code)

	private static final Logger log = Logger.getLogger(DepartmentController.class);

	@Autowired
	private AdminDepartmentValidator adminValidator;

	@Autowired
	StateService stateService;

	@Autowired
	OrganizationService organisationService;

	@Autowired
	DistrictService districtService;

	@Autowired
	MinistryValidator ministryValidator;

	@Autowired
	LocalGovtBodyService localGovtBodyService;

	@Autowired
	DepartmentValidator departmentValidator;

	@Autowired
	CommonService commonService;
	
	@Autowired
	  private GovernmentOrderService govtOrderService;
	 

	@Autowired
	private AdministrativeDepratmentService adminDeprtmentService;

	int itr = 0;

	boolean isCommitted = false;
	String successMessage = null;
	boolean isAUM = false;
	String alternateFlow = null; // to open alternate flow pages.

	private Integer stateCode;

	private boolean isCenterUser;

	private Long userId;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		setGlobalParams(session);
	}
	
	private void setGlobalParams(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		isCenterUser = ("s").equals(sessionObject.getIsCenterorstate());
		userId = sessionObject.getUserId();
	}

	/**
	 * The {@code clearSessionAttributes} used to remove all session attributes
	 * which has been used in create department process.
	 * 
	 * @param session
	 */
	private void clearSessionAttributes(HttpSession session, boolean isRemoveUrlParam) {
		session.removeAttribute("process");
		session.removeAttribute("storedDeptForms");
		if (isRemoveUrlParam) {
			session.removeAttribute("isOrganizationFlow");
		}
		session.removeAttribute("unitCodesNames");
	}

	@RequestMapping(value = "/createDepartmentCentral", method = RequestMethod.GET)
	public ModelAndView showDepartmentFormCentralGet(HttpSession session, @ModelAttribute("createDepartment") DepartmentForm departmentForm, Model model, HttpServletRequest request) {
		ModelAndView mav;
		try {
			mav = null;
			isAUM = false;
			alternateFlow = "";
			if (stateCode == null) {
				mav = new ModelAndView("redirect:login.htm");
			} else {
				session.removeAttribute("centralLvlDept");
				session.removeAttribute("stateLvlDept");
				session.removeAttribute("districtLvlDept");
				session.removeAttribute("subDistrictLvlDept");
				session.removeAttribute("blockLvlDept");

				model.addAttribute("ministryList", organisationService.getMinistryList());
				mav = new ModelAndView("createdepartment");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/createDepartmentState", method = RequestMethod.GET)
	public ModelAndView showDepartmentFormStateGet(HttpSession session, @ModelAttribute("createDepartment") DepartmentForm departmentForm, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			if (stateCode == null) {
				mav = new ModelAndView("redirect:login.htm");
			} else {

				isAUM = true;
				alternateFlow = "AF";

				session.removeAttribute("centralLvlDept");
				session.removeAttribute("stateLvlDept");
				session.removeAttribute("districtLvlDept");
				session.removeAttribute("subDistrictLvlDept");
				session.removeAttribute("blockLvlDept");

				model.addAttribute("ministryList", organisationService.getMinistryList());
				model.addAttribute("localBodyType", localGovtBodyService.getLGTypeForGovtBody());
				model.addAttribute("stateCode", stateCode);
				mav = new ModelAndView("createdepartmentAlternate");
			}
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

	@RequestMapping(value = "/createDepartment", method = RequestMethod.POST)
	public ModelAndView showDepartmentForm(HttpSession session, @ModelAttribute("createDepartment") @Valid DepartmentForm departmentForm, BindingResult bindingResult, Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			if (isAUM) {
				departmentValidator.checkExistingOrgNameAUM(departmentForm.getDeptName(), 2, bindingResult, stateCode);
				departmentValidator.validateforState(departmentForm, bindingResult);
			} else {
				departmentValidator.validate(departmentForm, bindingResult);
			}

			if (bindingResult.hasErrors()) {
				model.addAttribute("ministryList", organisationService.getMinistryList());
				if (isAUM) {
					model.addAttribute("localBodyType", localGovtBodyService.getLGTypeForGovtBody());
					model.addAttribute("stateCode", stateCode);
					mav = new ModelAndView("createdepartmentAlternate");
				} else
					mav = new ModelAndView("createdepartment");
			} else {
				if (isAUM) {
					departmentForm.setAUM(true);
				}
				if (departmentForm.getSpecificLevel() != null) {
					String level[] = departmentForm.getSpecificLevel().split(",");
					departmentForm.setOrganization(false);
					session.setAttribute("centralLvlDept", departmentForm);

					if (level[0].equals("S")) {
						mav = new ModelAndView("redirect:createDepartmentStateLevel.htm?level=" + departmentForm.getSpecificLevel());
					} else if (level[0].equals("D")) {
						mav = new ModelAndView("redirect:createDepartmentDistrictLevel.htm?level=" + departmentForm.getSpecificLevel());
					} else if (level[0].equals("T")) {
						mav = new ModelAndView("redirect:createDepartmentSubdistrictLevel.htm?level=" + departmentForm.getSpecificLevel());
					} else if (level[0].equals("B")) {
						mav = new ModelAndView("redirect:createDepartmentBlockLevel.htm?level=" + departmentForm.getSpecificLevel());
					} else if (level[0].equals("V")) {
						mav = new ModelAndView("redirect:createDepartmentVillageLevel.htm?level=" + departmentForm.getSpecificLevel());
					} else {
						mav = new ModelAndView("home");
					}
				} else {
					isCommitted = organisationService.saveDepartment(departmentForm, null, null, null, null, null, stateCode);
					model.addAttribute("msgid", this.successMsg(isCommitted, departmentForm.isOrganization(), departmentForm.getDeptName(), request));
					mav = new ModelAndView("success");
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

	@RequestMapping(value = "/createDepartmentStateLevel", method = RequestMethod.GET)
	public ModelAndView showDepartmentFormStateLevelGet(HttpSession session, @ModelAttribute("createDepartment") DepartmentForm departmentForm, Model model, @RequestParam(value = "level", required = false) String level, HttpServletRequest request) {
		try {
			model.addAttribute("level", level);
			if (level != null) {
				departmentForm.setOrgLevel(level.charAt(0));
			}
			ModelAndView mav = new ModelAndView("createdepartmentStateLevel");
			mav.addObject("createDepartment", departmentForm);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createDepartmentStateLevel", method = RequestMethod.POST)
	public ModelAndView showDepartmentFormStateLevel(HttpSession session, @ModelAttribute("createDepartment") @Valid DepartmentForm departmentForm, BindingResult bindingResult, Model model,
			@RequestParam(value = "level", required = false) String level, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			departmentValidator.validate(departmentForm, bindingResult);
			if (bindingResult.hasErrors()) {
				model.addAttribute("level", level);
				mav = new ModelAndView("createdepartmentStateLevel");
			} else {
				List<DepartmentForm> departmentFormList = new ArrayList<DepartmentForm>();

				if (session.getAttribute("stateLvlDept") != null) {
					List<DepartmentForm> dptFormStateLevelList = new ArrayList<DepartmentForm>();
					dptFormStateLevelList = (List<DepartmentForm>) session.getAttribute("stateLvlDept");
					for (int i = 0; i < dptFormStateLevelList.size(); i++) {
						departmentFormList.add(dptFormStateLevelList.get(i));
					}
				}
				departmentFormList.add(departmentForm);
				session.setAttribute("stateLvlDept", departmentFormList);

				if (departmentForm.getAddAnother().equals("true")) {
					itr = 1;
					mav = new ModelAndView("redirect:createDepartmentStateLevel.htm?level=" + level);
				}

				else {
					if (level.length() > 2) {
						level = level.substring(2, level.length());
					}
					String tempLevel[] = level.split(",");
					itr = 0;
					if (level.length() == 1) {
						DepartmentForm dptFormCentralLevel = new DepartmentForm();
						List<DepartmentForm> dptFormStateLevelList = new ArrayList<DepartmentForm>();
						dptFormCentralLevel = (DepartmentForm) session.getAttribute("centralLvlDept");
						dptFormStateLevelList = (List<DepartmentForm>) session.getAttribute("stateLvlDept");
						isCommitted = organisationService.saveDepartment(dptFormCentralLevel, dptFormStateLevelList, null, null, null, null, stateCode);
						model.addAttribute("msgid", this.successMsg(isCommitted, dptFormCentralLevel.isOrganization(), dptFormCentralLevel.getDeptName(), request));
						mav = new ModelAndView("success");
					}

					if (tempLevel[0].equals("D")) {
						mav = new ModelAndView("redirect:createDepartmentDistrictLevel.htm?level=" + level);
					}
					if (tempLevel[0].equals("T")) {
						mav = new ModelAndView("redirect:createDepartmentSubdistrictLevel.htm?level=" + level);
					} else if (tempLevel[0].equals("B")) {
						mav = new ModelAndView("redirect:createDepartmentBlockLevel.htm?level=" + level);
					}
					if (tempLevel[0].equals("V")) {
						mav = new ModelAndView("redirect:createDepartmentVillageLevel.htm?level=" + level);
					}
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createDepartmentDistrictLevel", method = RequestMethod.GET)
	public ModelAndView showDepartmentFormDistrictLevelGet(HttpSession session, @ModelAttribute("createDepartment") DepartmentForm departmentForm, BindingResult bindingResult, Model model,
			@RequestParam(value = "level", required = false) String level, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav;
		try {

			List<DepartmentForm> dptFormStateLevelList = new ArrayList<DepartmentForm>();
			DepartmentForm dptFormCentralLevel = new DepartmentForm();
			dptFormCentralLevel = (DepartmentForm) session.getAttribute("centralLvlDept");
			DepartmentForm deptForm = new DepartmentForm();
			if (dptFormCentralLevel.isAUM()) {
				deptForm = (DepartmentForm) session.getAttribute("centralLvlDept");
				deptForm.setStateName(stateCode.toString());
				departmentValidator.validateforState(deptForm, bindingResult);
				if (bindingResult.hasErrors()) {
					session.removeAttribute("centralLvlDept");
					session.removeAttribute("stateLvlDept");
					session.removeAttribute("districtLvlDept");
					session.removeAttribute("subDistrictLvlDept");
					session.removeAttribute("blockLvlDept");
					departmentForm.setDeptName(deptForm.getDeptName());
					departmentForm.setDeptNameLocal(deptForm.getDeptNameLocal());
					departmentForm.setShortDeptName(deptForm.getShortDeptName());
					model.addAttribute("ministryList", organisationService.getMinistryList());
					model.addAttribute("localBodyType", localGovtBodyService.getLGTypeForGovtBody());
					model.addAttribute("stateCode", stateCode);
					mav = new ModelAndView("createdepartmentAlternate");
					return mav;
				}
				dptFormStateLevelList.add(deptForm);
			} else {
				dptFormStateLevelList = (List<DepartmentForm>) session.getAttribute("stateLvlDept");

			}
			model.addAttribute("distList", districtService.getDistrictListbyStateCodeForListBox(stateCode));
			model.addAttribute("parent", dptFormStateLevelList);
			model.addAttribute("level", level);
			mav = new ModelAndView("createdepartmentDistrictLevel" + alternateFlow);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createDepartmentDistrictLevel", method = RequestMethod.POST)
	public ModelAndView showDepartmentFormDistrictLevel(HttpSession session, @ModelAttribute("createDepartment") @Valid DepartmentForm departmentForm, BindingResult bindingResult, Model model,
			@RequestParam(value = "level", required = false) String level, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			departmentValidator.validateforLowLevel(departmentForm, bindingResult);
			departmentValidator.checkExistingDeptName(stateCode, 'D', departmentForm.getDeptName(), bindingResult);
			if (bindingResult.hasErrors()) {
				List<DepartmentForm> dptFormStateLevelList = new ArrayList<DepartmentForm>();
				DepartmentForm dptFormCentralLevel = new DepartmentForm();
				dptFormCentralLevel = (DepartmentForm) session.getAttribute("centralLvlDept");
				DepartmentForm deptForm = new DepartmentForm();
				if (dptFormCentralLevel.isAUM()) {
					deptForm = (DepartmentForm) session.getAttribute("centralLvlDept");
					dptFormStateLevelList.add(deptForm);
				} else {
					dptFormStateLevelList = (List<DepartmentForm>) session.getAttribute("stateLvlDept");
				}
				model.addAttribute("distList", districtService.getDistrictListbyStateCodeForListBox(stateCode));
				model.addAttribute("parent", dptFormStateLevelList);
				model.addAttribute("level", level);
				mav = new ModelAndView("createdepartmentDistrictLevel" + alternateFlow);
			} else {
				List<DepartmentForm> departmentFormList = new ArrayList<DepartmentForm>();
				if (session.getAttribute("districtLvlDept") != null) {
					List<DepartmentForm> dptFormDistrictLevelList = new ArrayList<DepartmentForm>();
					dptFormDistrictLevelList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
					for (int i = 0; i < dptFormDistrictLevelList.size(); i++) {
						departmentFormList.add(dptFormDistrictLevelList.get(i));
					}
				}
				departmentFormList.add(departmentForm);
				session.setAttribute("districtLvlDept", departmentFormList);

				if (departmentForm.getAddAnother().equals("true")) {
					itr = 1;
					mav = new ModelAndView("redirect:createDepartmentDistrictLevel.htm?level=" + level);
				} else {
					if (level.length() == 1) {
						DepartmentForm dptFormCentralLevel = new DepartmentForm();
						List<DepartmentForm> dptFormStateLevelList = new ArrayList<DepartmentForm>();
						List<DepartmentForm> dptFormDistrictLevelList = new ArrayList<DepartmentForm>();
						dptFormCentralLevel = (DepartmentForm) session.getAttribute("centralLvlDept");
						dptFormStateLevelList = (List<DepartmentForm>) session.getAttribute("stateLvlDept");
						dptFormDistrictLevelList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
						isCommitted = organisationService.saveDepartment(dptFormCentralLevel, dptFormStateLevelList, dptFormDistrictLevelList, null, null, null, stateCode);
						model.addAttribute("msgid", this.successMsg(isCommitted, dptFormCentralLevel.isOrganization(), dptFormCentralLevel.getDeptName(), request));
						mav = new ModelAndView("success");
					} else {
						String tempLevel[] = null;
						if (level.length() > 2) {
							level = level.substring(2, level.length());
							tempLevel = level.split(",");
						}
						if (tempLevel[0].equals("T")) {
							mav = new ModelAndView("redirect:createDepartmentSubdistrictLevel.htm?level=" + level);
						} else if (tempLevel[0].equals("B")) {
							mav = new ModelAndView("redirect:createDepartmentBlockLevel.htm?level=" + level);
						}
						if (tempLevel[0].equals("V")) {
							mav = new ModelAndView("redirect:createDepartmentVillageLevel.htm?level=" + level);
						}

					}
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createDepartmentSubdistrictLevel", method = RequestMethod.GET)
	public ModelAndView showDepartmentFormSubdistrictLevelGet(HttpSession session, @ModelAttribute("createDepartment") DepartmentForm departmentForm, BindingResult bindingResult, Model model,
			@RequestParam(value = "level", required = false) String level, HttpServletRequest request) {
		String temp = null;
		List<DepartmentForm> dptFormParentList = null;
		ModelAndView mav;
		try {
			dptFormParentList = new ArrayList<DepartmentForm>();
			if (level.length() > 2) {
				temp = level.substring(2, level.length());
			}
			DepartmentForm dptFrm = new DepartmentForm();
			dptFrm = (DepartmentForm) session.getAttribute("centralLvlDept");
			departmentValidator.validateforState(dptFrm, bindingResult);
			if (bindingResult.hasErrors()) {
				session.removeAttribute("centralLvlDept");
				session.removeAttribute("stateLvlDept");
				session.removeAttribute("districtLvlDept");
				session.removeAttribute("subDistrictLvlDept");
				session.removeAttribute("blockLvlDept");
				departmentForm.setDeptName(dptFrm.getDeptName());
				departmentForm.setDeptNameLocal(dptFrm.getDeptNameLocal());
				departmentForm.setShortDeptName(dptFrm.getShortDeptName());
				model.addAttribute("ministryList", organisationService.getMinistryList());
				model.addAttribute("localBodyType", localGovtBodyService.getLGTypeForGovtBody());
				model.addAttribute("stateCode", stateCode);
				mav = new ModelAndView("createdepartmentAlternate");
				return mav;
			}
			String tempLevel = dptFrm.getSpecificLevel();
			if (tempLevel.equals("D,T") || tempLevel.equals("D,T,B") || tempLevel.equals("D,T,V") || tempLevel.equals("D,T,B,V") || tempLevel.equals("S,D,T,B,V") || tempLevel.equals("S,D,T,V") || tempLevel.equals("S,D,T")) {
				dptFormParentList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
			} else if (tempLevel.equals("T") || tempLevel.equals("T,B") || tempLevel.equals("T,V") || tempLevel.equals("T,B,V")) {
				dptFormParentList.add(dptFrm);
			}

			model.addAttribute("stateList", stateService.getStateSourceList(stateCode));
			model.addAttribute("parent", dptFormParentList);
			model.addAttribute("level", temp);
			model.addAttribute("plevel", level);
			mav = new ModelAndView("createdepartmentSubdistrictLevel" + alternateFlow);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createDepartmentSubdistrictLevel", method = RequestMethod.POST)
	public ModelAndView showDepartmentFormSubdistrictLevel(HttpSession session, @ModelAttribute("createDepartment") @Valid DepartmentForm departmentForm, BindingResult bindingResult, Model model,
			@RequestParam(value = "level", required = false) String level, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			departmentValidator.validateforLowLevel(departmentForm, bindingResult);
			departmentValidator.checkExistingDeptName(stateCode, 'T', departmentForm.getDeptName(), bindingResult);

			if (bindingResult.hasErrors()) {
				String temp = null;
				List<DepartmentForm> dptFormParentList = null;
				dptFormParentList = new ArrayList<DepartmentForm>();
				if (level.length() > 2) {
					temp = level.substring(2, level.length());
				}
				DepartmentForm dptFrm = new DepartmentForm();
				dptFrm = (DepartmentForm) session.getAttribute("centralLvlDept");
				String tempLevel = dptFrm.getSpecificLevel();
				if (tempLevel.equals("D,T") || tempLevel.equals("D,T,B") || tempLevel.equals("D,T,V") || tempLevel.equals("D,T,B,V") || tempLevel.equals("S,D,T,B,V") || tempLevel.equals("S,D,T,V") || tempLevel.equals("S,D,T")) {
					dptFormParentList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
				} else if (tempLevel.equals("T") || tempLevel.equals("T,B") || tempLevel.equals("T,V") || tempLevel.equals("T,B,V")) {
					dptFormParentList.add(dptFrm);
				}
				model.addAttribute("stateList", stateService.getStateSourceList(stateCode));
				model.addAttribute("parent", dptFormParentList);
				model.addAttribute("level", temp);
				mav = new ModelAndView("createdepartmentSubdistrictLevel" + alternateFlow);
			} else {
				String tempLevel[] = level.split(",");

				if (level.length() == 0) {
					DepartmentForm dptFormCentralLevel = new DepartmentForm();
					List<DepartmentForm> dptFormStateLevelList = new ArrayList<DepartmentForm>();
					List<DepartmentForm> dptFormDistrictLevelList = new ArrayList<DepartmentForm>();
					dptFormCentralLevel = (DepartmentForm) session.getAttribute("centralLvlDept");
					dptFormStateLevelList = (List<DepartmentForm>) session.getAttribute("stateLvlDept");
					dptFormDistrictLevelList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
					isCommitted = organisationService.saveDepartment(dptFormCentralLevel, dptFormStateLevelList, dptFormDistrictLevelList, departmentForm, null, null, stateCode);
					model.addAttribute("msgid", this.successMsg(isCommitted, dptFormCentralLevel.isOrganization(), dptFormCentralLevel.getDeptName(), request));
					mav = new ModelAndView("success");
				}

				session.setAttribute("subDistrictLvlDept", departmentForm);
				if (tempLevel[0].equals("B")) {
					mav = new ModelAndView("redirect:createDepartmentBlockLevel.htm?level=" + level);
				}
				if (tempLevel[0].equals("V")) {
					mav = new ModelAndView("redirect:createDepartmentVillageLevel.htm?level=" + level);
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createDepartmentBlockLevel", method = RequestMethod.GET)
	public ModelAndView showDepartmentFormBlockLevelGet(HttpSession session, @ModelAttribute("createDepartment") DepartmentForm departmentForm, BindingResult bindingResult, Model model, @RequestParam(value = "level", required = false) String level,
			HttpServletRequest request) {
		String temp = null;
		List<DepartmentForm> dptFormParentList = null;
		ModelAndView mav;
		try {
			dptFormParentList = new ArrayList<DepartmentForm>();
			if (level.length() > 2) {
				temp = level.substring(2, level.length());
			}
			DepartmentForm dptFrm = new DepartmentForm();

			dptFrm = (DepartmentForm) session.getAttribute("centralLvlDept");
			departmentValidator.validateforState(dptFrm, bindingResult);
			if (bindingResult.hasErrors()) {
				session.removeAttribute("centralLvlDept");
				session.removeAttribute("stateLvlDept");
				session.removeAttribute("districtLvlDept");
				session.removeAttribute("subDistrictLvlDept");
				session.removeAttribute("blockLvlDept");
				departmentForm.setDeptName(dptFrm.getDeptName());
				departmentForm.setDeptNameLocal(dptFrm.getDeptNameLocal());
				departmentForm.setShortDeptName(dptFrm.getShortDeptName());
				model.addAttribute("ministryList", organisationService.getMinistryList());
				model.addAttribute("localBodyType", localGovtBodyService.getLGTypeForGovtBody());
				model.addAttribute("stateCode", stateCode);
				mav = new ModelAndView("createdepartmentAlternate");
				return mav;
			}

			String tempLevel = dptFrm.getSpecificLevel();
			if (tempLevel.equals("D,T") || tempLevel.equals("D,B") || tempLevel.equals("D,B,V") || tempLevel.equals("D,T,B,V") || tempLevel.equals("D,T,B") || tempLevel.equals("S,D,T,B,V") || tempLevel.equals("S,D,T,V")) {
				dptFormParentList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
			} else if (tempLevel.equals("B") || tempLevel.equals("T,B") || tempLevel.equals("B,V") || tempLevel.equals("T,B,V")) {
				dptFormParentList.add(dptFrm);
			}
			model.addAttribute("stateList", stateService.getStateSourceList(stateCode));
			model.addAttribute("parent", dptFormParentList);
			model.addAttribute("level", temp);
			model.addAttribute("plevel", level);
			mav = new ModelAndView("createdepartmentBlockLevel");

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createDepartmentBlockLevel", method = RequestMethod.POST)
	public ModelAndView showDepartmentFormBlockLevel(HttpSession session, @ModelAttribute("createDepartment") @Valid DepartmentForm departmentForm, BindingResult bindingResult, Model model,
			@RequestParam(value = "level", required = false) String level, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			departmentValidator.validateforLowLevel(departmentForm, bindingResult);
			departmentValidator.checkExistingDeptName(stateCode, 'B', departmentForm.getDeptName(), bindingResult);
			if (bindingResult.hasErrors()) {
				String temp = null;
				List<DepartmentForm> dptFormParentList = null;
				dptFormParentList = new ArrayList<DepartmentForm>();
				if (level.length() > 2) {
					temp = level.substring(2, level.length());
				}
				DepartmentForm dptFrm = new DepartmentForm();
				dptFrm = (DepartmentForm) session.getAttribute("centralLvlDept");
				String tempLevel = dptFrm.getSpecificLevel();
				if (tempLevel.equals("D,T") || tempLevel.equals("D,B") || tempLevel.equals("D,B,V") || tempLevel.equals("D,T,B,V") || tempLevel.equals("D,T,B") || tempLevel.equals("S,D,T,B,V") || tempLevel.equals("S,D,T,V")) {
					dptFormParentList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
				} else if (tempLevel.equals("B") || tempLevel.equals("T,B") || tempLevel.equals("B,V") || tempLevel.equals("T,B,V")) {
					dptFormParentList.add(dptFrm);
				}
				model.addAttribute("stateList", stateService.getStateSourceList(stateCode));
				model.addAttribute("parent", dptFormParentList);
				model.addAttribute("level", temp);
				mav = new ModelAndView("createdepartmentBlockLevel");
			} else {
				String tempLevel[] = level.split(",");
				if (level.length() == 0) {
					DepartmentForm dptFormCentralLevel = new DepartmentForm();
					List<DepartmentForm> dptFormStateLevelList = new ArrayList<DepartmentForm>();
					List<DepartmentForm> dptFormDistrictLevelList = new ArrayList<DepartmentForm>();
					DepartmentForm dptFormSubDistrictLevel = new DepartmentForm();
					dptFormCentralLevel = (DepartmentForm) session.getAttribute("centralLvlDept");
					dptFormStateLevelList = (List<DepartmentForm>) session.getAttribute("stateLvlDept");
					dptFormDistrictLevelList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
					dptFormSubDistrictLevel = (DepartmentForm) session.getAttribute("subDistrictLvlDept");
					isCommitted = organisationService.saveDepartment(dptFormCentralLevel, dptFormStateLevelList, dptFormDistrictLevelList, dptFormSubDistrictLevel, departmentForm, null, stateCode);
					model.addAttribute("msgid", this.successMsg(isCommitted, dptFormCentralLevel.isOrganization(), dptFormCentralLevel.getDeptName(), request));
					mav = new ModelAndView("success");
				}

				session.setAttribute("blockLvlDept", departmentForm);

				if (tempLevel[0].equals("V")) {
					mav = new ModelAndView("redirect:createDepartmentVillageLevel.htm?level=" + level);
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createDepartmentVillageLevel", method = RequestMethod.GET)
	public ModelAndView showDepartmentFormVillageLevelGet(HttpSession session, @ModelAttribute("createDepartment") DepartmentForm departmentForm, BindingResult bindingResult, Model model, @RequestParam(value = "level", required = false) String level,
			HttpServletRequest request) {
		List<DepartmentForm> dptFormParentList = null;
		DepartmentForm dptFrm = null;
		ModelAndView mav;
		try {
			dptFormParentList = new ArrayList<DepartmentForm>();
			dptFrm = new DepartmentForm();
			dptFrm = (DepartmentForm) session.getAttribute("centralLvlDept");
			departmentValidator.validateforState(dptFrm, bindingResult);
			if (bindingResult.hasErrors()) {
				session.removeAttribute("centralLvlDept");
				session.removeAttribute("stateLvlDept");
				session.removeAttribute("districtLvlDept");
				session.removeAttribute("subDistrictLvlDept");
				session.removeAttribute("blockLvlDept");
				departmentForm.setDeptName(dptFrm.getDeptName());
				departmentForm.setDeptNameLocal(dptFrm.getDeptNameLocal());
				departmentForm.setShortDeptName(dptFrm.getShortDeptName());
				model.addAttribute("ministryList", organisationService.getMinistryList());
				model.addAttribute("localBodyType", localGovtBodyService.getLGTypeForGovtBody());
				model.addAttribute("stateCode", stateCode);
				mav = new ModelAndView("createdepartmentAlternate");
				return mav;
			}
			String tempLevel = dptFrm.getSpecificLevel();
			if (tempLevel.equals("D,V")) {
				dptFormParentList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
			} else if (tempLevel.equals("T,V") || tempLevel.equals("T,B,V") || tempLevel.equals("D,T,V") || tempLevel.equals("D,T,B,V") || tempLevel.equals("S,D,T,B,V") || tempLevel.equals("S,D,T,V")) {
				dptFrm = null;
				dptFrm = new DepartmentForm();
				dptFrm = (DepartmentForm) session.getAttribute("subDistrictLvlDept");
				dptFormParentList.add(dptFrm);
			} else if (tempLevel.equals("B,V") || tempLevel.equals("D,B,V")) {
				dptFrm = null;
				dptFrm = new DepartmentForm();
				dptFrm = (DepartmentForm) session.getAttribute("blockLvlDept");
				dptFormParentList.add(dptFrm);
			} else if (tempLevel.equals("V")) {
				dptFormParentList.add(dptFrm);
			}
			model.addAttribute("level", level);
			model.addAttribute("stateList", stateService.getStateSourceList(stateCode));
			model.addAttribute("parent", dptFormParentList);

			mav = new ModelAndView("createdepartmentVillageLevel" + alternateFlow);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createDepartmentVillageLevel", method = RequestMethod.POST)
	public ModelAndView showDepartmentFormVillageLevel(HttpSession session, @ModelAttribute("createDepartment") @Valid DepartmentForm departmentForm, BindingResult bindingResult, Model model,
			@RequestParam(value = "level", required = false) String level, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			departmentValidator.validateforLowLevel(departmentForm, bindingResult);
			departmentValidator.checkExistingDeptName(stateCode, 'V', departmentForm.getDeptName(), bindingResult);
			if (bindingResult.hasErrors()) {
				List<DepartmentForm> dptFormParentList = null;
				DepartmentForm dptFrm = null;
				dptFormParentList = new ArrayList<DepartmentForm>();
				dptFrm = new DepartmentForm();
				dptFrm = (DepartmentForm) session.getAttribute("centralLvlDept");
				String tempLevel = dptFrm.getSpecificLevel();
				if (tempLevel.equals("D,V")) {
					dptFormParentList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
				} else if (tempLevel.equals("T,V") || tempLevel.equals("T,B,V") || tempLevel.equals("D,T,V") || tempLevel.equals("D,T,B,V") || tempLevel.equals("S,D,T,B,V") || tempLevel.equals("S,D,T,V")) {
					dptFrm = null;
					dptFrm = new DepartmentForm();
					dptFrm = (DepartmentForm) session.getAttribute("subDistrictLvlDept");
					dptFormParentList.add(dptFrm);
				} else if (tempLevel.equals("B,V")) {
					dptFrm = null;
					dptFrm = new DepartmentForm();
					dptFrm = (DepartmentForm) session.getAttribute("blockLvlDept");
					dptFormParentList.add(dptFrm);
				} else if (tempLevel.equals("V")) {
					dptFormParentList.add(dptFrm);
				}
				model.addAttribute("stateList", stateService.getStateSourceList(stateCode));
				model.addAttribute("parent", dptFormParentList);
				mav = new ModelAndView("createdepartmentVillageLevel" + alternateFlow);
			} else {
				DepartmentForm dptFormCentralLevel = new DepartmentForm();
				List<DepartmentForm> dptFormStateLevelList = new ArrayList<DepartmentForm>();
				List<DepartmentForm> dptFormDistrictLevelList = new ArrayList<DepartmentForm>();
				DepartmentForm dptFormSubDistrictLevel = new DepartmentForm();
				DepartmentForm dptFormBlockLevel = new DepartmentForm();
				dptFormCentralLevel = (DepartmentForm) session.getAttribute("centralLvlDept");
				dptFormStateLevelList = (List<DepartmentForm>) session.getAttribute("stateLvlDept");
				dptFormDistrictLevelList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
				dptFormSubDistrictLevel = (DepartmentForm) session.getAttribute("subDistrictLvlDept");
				dptFormBlockLevel = (DepartmentForm) session.getAttribute("blockLvlDept");
				isCommitted = organisationService.saveDepartment(dptFormCentralLevel, dptFormStateLevelList, dptFormDistrictLevelList, dptFormSubDistrictLevel, dptFormBlockLevel, departmentForm, stateCode);
				if (isCommitted) {
					model.addAttribute("msgid", this.successMsg(isCommitted, dptFormCentralLevel.isOrganization(), dptFormCentralLevel.getDeptName(), request));
					mav = new ModelAndView("success");
				} else {
					model.addAttribute("msgid", "Department is not create Successfully");
					mav = new ModelAndView("success");
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

	@RequestMapping(value = "/createMinistry", method = RequestMethod.GET)
	public ModelAndView showMinistryForm(HttpServletRequest request) {
		try {
			ModelAndView mav = new ModelAndView("createministry");
			mav.addObject("createMinistry", new MinistryForm());
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	@ModelAttribute("stateSourceList")
	public List<State> populateSourceStateList(HttpServletRequest request) {

		try {
			return stateService.getStateSourceList();
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}

	}

	@ModelAttribute("districtList")
	public List<District> getDistrictList(HttpServletRequest request) {
		try {
			return districtService.getDistrictList(29);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
	}

	public String successMsg(boolean isCommitted, boolean isOrganization, String CUnitName, HttpServletRequest request) {
		String msg = null;
		try {
			if (isCommitted == true) {
				if (isOrganization) {
					msg = "The new organization " + CUnitName + " is created successfully !";
				} else {
					msg = "The new department " + CUnitName + " is created successfully !";
				}
			} else {
				if (isOrganization) {
					msg = "New Organization Details Has Failed To Save. Kindly Re-enter the Details. !";
				} else {
					msg = "New Department Details Has Failed To Save. Kindly Re-enter the Details. !";
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return redirectPath;
		}
		return msg;
	}

	public String successMsgforExtend(boolean isCommitted, boolean isOrganization, String CUnitName, HttpServletRequest request) {
		String msg = null;
		try {
			if (isCommitted == true) {
				if (isOrganization) {
					msg = "The organization " + CUnitName + " is extend successfully !";
				} else {
					msg = "The department " + CUnitName + " is extend successfully !";
				}
			} else {
				if (isOrganization) {
					msg = "Organization Details Has Failed To extend. Kindly Re-enter the Details. !";
				} else {
					msg = "Department Details Has Failed To extend. Kindly Re-enter the Details. !";
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return redirectPath;
		}
		return msg;
	}

	@RequestMapping(value = "/createOrganizationCentral", method = RequestMethod.GET)
	public ModelAndView showOrgFormCentralGet(HttpSession session, @ModelAttribute("createOrganization") DepartmentForm departmentForm, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		isAUM = false;
		alternateFlow = "";
		try {

			if (stateCode == null) {
				mav = new ModelAndView("redirect:login.htm");
			} else {
				
				int slc = 0;
				session.removeAttribute("centralLvlDept");
				session.removeAttribute("stateLvlDept");
				session.removeAttribute("districtLvlDept");
				session.removeAttribute("subDistrictLvlDept");
				model.addAttribute("ministryList", organisationService.getMinistryList());
				model.addAttribute("orgType", organisationService.getOrganizationType());
				model.addAttribute("stateDeptList", organisationService.getDepartmentList("from Organization o where o.organizationType.orgTypeCode=2 and o.isactive=true and o.slc=" + slc));
				mav = new ModelAndView("createorganization");

			}
		} /*
			 * catch (NumberFormatException e) { IExceptionHandler expHandler =
			 * ExceptionHandlerFactory.getInstance().create(); String
			 * redirectPath =
			 * expHandler.handleSaveRedirectException(request,"","label.lgd", 1,
			 * e); mav = new ModelAndView(redirectPath); return mav;
			 */
		catch (Exception e) {
			int userID = 1;
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", userID, e);

			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/createOrganizationState", method = RequestMethod.GET)
	public ModelAndView showOrgFormStateGet(HttpSession session, @ModelAttribute("createOrganization") DepartmentForm departmentForm, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			if (stateCode == null) {
				mav = new ModelAndView("redirect:login.htm");
			} else {

				int slc = commonService.getSlc(stateCode);
				isAUM = true;
				alternateFlow = "AF";
				session.removeAttribute("centralLvlDept");
				session.removeAttribute("stateLvlDept");
				session.removeAttribute("districtLvlDept");
				session.removeAttribute("subDistrictLvlDept");
				model.addAttribute("ministryList", organisationService.getMinistryList());
				model.addAttribute("orgType", organisationService.getOrganizationType());
				model.addAttribute("stateDeptList", organisationService.getDepartmentList("from Organization o where o.organizationType.orgTypeCode=2 and o.isactive=true and o.slc=" + slc));
				mav = new ModelAndView("createorganizationAlternate");
			}
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

	/**
	 * @ Modified by sushil on 11-05-2013 for server side validation
	 * 
	 * @return mav
	 */
	@RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
	public ModelAndView showOrgForm(HttpSession session, @ModelAttribute("createOrganization") @Valid DepartmentForm departmentForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		boolean isCommitted = false;
		String msg = null;
		try {
			if (isAUM) {
				departmentValidator.checkExistingOrgNameAUM(departmentForm.getDeptName(), Integer.parseInt(departmentForm.getOrgType()), bindingResult, stateCode);
			} else {
				departmentValidator.validate(departmentForm, bindingResult);
			}

			if (bindingResult.hasErrors()) {
				model.addAttribute("ministryList", organisationService.getMinistryList());
				model.addAttribute("orgType", organisationService.getOrganizationType());
				model.addAttribute("stateDeptList", organisationService.getDepartmentList("from Organization o where o.organizationType.orgTypeCode=2 and o.isactive=true and o.slc=" + stateCode));
				if (isAUM) {
					mav = new ModelAndView("createorganizationAlternate");
				} else {
					mav = new ModelAndView("createorganization");
				}
			} else {
				if (isAUM) {
					departmentForm.setAUM(true);
				}
				departmentForm.setOrganization(true);
				if (departmentForm.getSpecificLevel() != null) {
					String level[] = departmentForm.getSpecificLevel().split(",");
					session.setAttribute("centralLvlDept", departmentForm);

					if (level[0].equals("S")) {
						mav = new ModelAndView("redirect:createDepartmentStateLevel.htm?level=" + departmentForm.getSpecificLevel());
					} else if (level[0].equals("D")) {
						mav = new ModelAndView("redirect:createDepartmentDistrictLevel.htm?level=" + departmentForm.getSpecificLevel());
					} else if (level[0].equals("T")) {
						mav = new ModelAndView("redirect:createDepartmentSubdistrictLevel.htm?level=" + departmentForm.getSpecificLevel());
					} else if (level[0].equals("B")) {
						mav = new ModelAndView("redirect:createDepartmentBlockLevel.htm?level=" + departmentForm.getSpecificLevel());
					} else if (level[0].equals("V")) {
						mav = new ModelAndView("redirect:createDepartmentVillageLevel.htm?level=" + departmentForm.getSpecificLevel());
					} else {
						mav = new ModelAndView("home");
					}
				} else {
					isCommitted = organisationService.saveDepartment(departmentForm, null, null, null, null, null, stateCode);
					if (isCommitted == true) {
						msg = "The new organization " + departmentForm.getDeptName() + " is created successfully !";
					} else {
						msg = "New Organization Details Has Failed To Save. Kindly Re-enter the Details. !";
					}
					model.addAttribute("msgid", msg);
					mav = new ModelAndView("success");
				}
			}
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

	/**
	 * @Modified by sushil on 16-05-2013 for modify center level orgnization
	 * @return mav
	 */
	@RequestMapping(value = "/modifyOrganizationCentral")
	public ModelAndView showmodifyOrgFormCentralGet(HttpSession session, @ModelAttribute("orgTypeForm") OrganizationTypeForm orgTypeForm, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		int orgCode = orgTypeForm.getOrganizationId();
		isAUM = false;
		alternateFlow = "";
		mav = new ModelAndView("modifyOrganizationCentral");
		try {
			List<Organization> listMinistry = organisationService.getMinistryDetail(orgCode);
			for (int i = 0; i < listMinistry.size(); i++) {
				orgTypeForm.setOrgName(listMinistry.get(i).getOrgName());
				orgTypeForm.setOrgNameLocal(listMinistry.get(i).getOrgNameLocal());
				orgTypeForm.setShortName(listMinistry.get(i).getShortName());
				orgTypeForm.setOrgCode(listMinistry.get(i).getOrgCode());
				orgTypeForm.setOrgTypeCode(listMinistry.get(i).getOrgTypeCode()); // added
																					// by
																					// sushil
																					// on
																					// 20-05-2013
			}

			if (stateCode == null) {
				mav = new ModelAndView("redirect:login.htm");
			} else {
				
				int slc = 0;
				mav.addObject("orgTypeForm", orgTypeForm);
				// model.addAttribute("orgTypeForm", orgTypeForm);
				session.removeAttribute("centralLvlDept");
				session.removeAttribute("stateLvlDept");
				session.removeAttribute("districtLvlDept");
				session.removeAttribute("subDistrictLvlDept");
				model.addAttribute("ministryList", organisationService.getMinistryList());
				model.addAttribute("orgType", organisationService.getOrganizationType());
				model.addAttribute("stateDeptList", organisationService.getDepartmentList("from Organization o where o.organizationType.orgTypeCode=2 and o.isactive=true and o.slc=" + slc));
			}
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

	/**
	 * Modified by sushil on 20-05-2013
	 */
	@RequestMapping(value = "/modifyOrganizationCentralforpost", method = RequestMethod.POST)
	public ModelAndView showmodifyOrgFormCentralpost(@ModelAttribute("orgTypeForm") OrganizationTypeForm orgTypeForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("modifyOrganizationCentral");
		try {
			int orgCode = orgTypeForm.getOrgCode();

			departmentValidator.validateCenterOrganization(orgTypeForm, result);
			if (result.hasErrors()) {
				mav.addObject("orgTypeForm", orgTypeForm);
				return mav;
			}

			boolean bool = organisationService.orgUpdate(orgTypeForm, orgCode, session, request);
			if (bool) {
				String aMessage = "Organization Modified successfully!!";
				mav = new ModelAndView("configview");
				mav.addObject("msgid", aMessage);
			} else {
				String aMessage = "Organization Modification failed !";
				mav = new ModelAndView("configview");
				mav.addObject("msgid", aMessage);
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

	@RequestMapping(value = "/modifyOrganizationState")
	public ModelAndView showmodifyOrgFormStateGet(@ModelAttribute("modifyOrganizationstate") OrganizationTypeForm orgTypeForm, Model model, HttpServletRequest request, HttpSession session) {
		isAUM = false;
		alternateFlow = "";
		ModelAndView mav = new ModelAndView("modifyOrganizationState");
		int orgCode = orgTypeForm.getOrganizationId();
		try {

			String category = null;
			if (session.getAttribute("category") != null) {
				category = session.getAttribute("category").toString();
			}
			if (category.equalsIgnoreCase("S")) {
				List<Organization> listMinistry = organisationService.getMinistryDetail(orgCode);
				for (int i = 0; i < listMinistry.size(); i++) {
					orgTypeForm.setOrgName(listMinistry.get(i).getOrgName());
					orgTypeForm.setOrgNamech(listMinistry.get(i).getOrgName());
					orgTypeForm.setOrgNameLocal(listMinistry.get(i).getOrgNameLocal());
					orgTypeForm.setShortName(listMinistry.get(i).getShortName());
					orgTypeForm.setOrgCode(listMinistry.get(i).getOrgCode());
				}
			} else {
				List<DistrictLineDepartment> listMinistry = organisationService.getLandRegionLineDepartmentDetailforOrganization(orgCode);
				if (!listMinistry.isEmpty()) {
					DistrictLineDepartment element = listMinistry.get(0);
					orgTypeForm.setOrgName(element.getOrgName());
					orgTypeForm.setOrgNamech(element.getOrgName());
					orgTypeForm.setOrgNameLocal(element.getOrgNameInLocal());
					orgTypeForm.setShortName(element.getShortDeptName());
					orgTypeForm.setOrgCode(element.getOrgCode());
				}

			}
			if (stateCode == null) {
				mav = new ModelAndView("redirect:login.htm");
			} else {

				/*
				 * List<DepartmentDataForm> listorganizationState =
				 * organisationService .getOrgDetailsModify();
				 */

				session.removeAttribute("centralLvlDept");
				session.removeAttribute("stateLvlDept");
				session.removeAttribute("districtLvlDept");
				session.removeAttribute("subDistrictLvlDept");
				List<Organization> ministryList = organisationService.getMinistryList();
				model.addAttribute("ministryList", ministryList);
				model.addAttribute("orgType", organisationService.getOrganizationType());
				model.addAttribute("stateDeptList", organisationService.getDepartmentList("from Organization o where o.organizationType.orgTypeCode=2 and o.isactive=true and o.slc=" + stateCode));
				mav.addObject("modifyOrganizationstate", orgTypeForm);
			}
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

	

	@RequestMapping(value = "/DeleteOrganizationState")
	public ModelAndView DeleteOrgStateDetail(@ModelAttribute("viewDept") MinistryForm viewDept, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		int orgCode = viewDept.getOrganizationId();
		try {
			mav = new ModelAndView("DeleteOrganizationState");
			List<Organization> listOrganizationDetails = null;
			listOrganizationDetails = new ArrayList<Organization>();
			listOrganizationDetails = organisationService.getOrganizationDetails(orgCode);
			mav.addObject("viewOrgState", listOrganizationDetails);
			model.addAttribute("orgCode", orgCode);
			session.setAttribute("id", orgCode);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// View Organization Details for Center
	@RequestMapping(value = "/DeleteOrganizationCenterDetail")
	public ModelAndView DeleteOrgCenterDetail(@ModelAttribute("viewDept") MinistryForm viewDept, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		int orgCode = viewDept.getOrganizationId();
		try {
			mav = new ModelAndView("DeleteOrganizationCenterDetail");
			List<Organization> listOrganizationDetails = null;
			listOrganizationDetails = new ArrayList<Organization>();
			listOrganizationDetails = organisationService.getOrganizationDetails(orgCode);
			// model.addAttribute("listMinistry", listOrganizationDetails);
			session.setAttribute("centerOrgId", orgCode);
			mav.addObject("listMinistry", listOrganizationDetails);
			mav.addObject("viewDept", viewDept);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// Delete Department Detail for state
	@RequestMapping(value = "/Deletedepartmentforstate")
	public ModelAndView DeleteDepartmentDetail(@ModelAttribute("viewDept") MinistryForm viewDepartment, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		int orgCode = viewDepartment.getDepartmentId();

		try {
			List<MinistryForm> listMinistry = null;
			listMinistry = new ArrayList<MinistryForm>();
			listMinistry = organisationService.getMinistryDetails(orgCode);
			mav = new ModelAndView("Deletedepartmentforstate");
			model.addAttribute("listMinistry", listMinistry);

			viewDepartment.setListMinistry(listMinistry);
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/abolishDepartmentforState")
	public ModelAndView abolishDepartment(@ModelAttribute("viewDept") MinistryForm viewDept, BindingResult result, Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;
		// String id=(String) session.getAttribute("id");
		/*
		 * int id = (Integer) session.getAttribute("id");
		 * viewDept.setDeptCode(id);
		 */
		int id = viewDept.getDepartmentId();
		try {

			isCommitted = organisationService.abolishDepartmentforState(id);
			organisationService.saveDataInGovtOrderfordeletedepartment(viewDept);

			if (isCommitted) {
				String aMessage = "The selected entity has been sucessfully deleted.!";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
				return mav;
			} else {
				model.addAttribute("msgid", "The Selected Entity Has Failed To Delete. !");
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

	// Delete Department Detail for center
	// modified by Sushil on 14-06-2013
	@RequestMapping(value = "/Deletedepartmentforcenter")
	public ModelAndView viewDepartmentDetail(@ModelAttribute("viewDept") MinistryForm viewDepartment, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		int orgCode = viewDepartment.getDepartmentId();
		try {
			List<MinistryForm> listMinistry = null;
			listMinistry = new ArrayList<MinistryForm>();
			listMinistry = organisationService.getMinistryDetails(orgCode);
			mav = new ModelAndView("Deletedepartmentforcenter");
			model.addAttribute("listMinistry", listMinistry);
			viewDepartment.setListMinistry(listMinistry);
			session.setAttribute("centerOrgId", orgCode);
			mav.addObject("viewDept", viewDepartment);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/*
	 * @RequestMapping(value="/extendDepartmentState",method=RequestMethod.GET)
	 */
	public ModelAndView showExtendsDepartmentFormStateGet1(HttpSession session, @ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		List<Organization> orgList = null;
		try {

			if (stateCode == null) {
				mav = new ModelAndView("redirect:login.htm");
			}
			clearSessionAttributes(session, true);
			
			orgList = new ArrayList<Organization>();
			if (!isCenterUser)
				orgList = organisationService.getOrganizationDetailbystateCode(stateCode);
			if (isCenterUser)
				model.addAttribute("ministryList", organisationService.getMinistryList());
			session.removeAttribute("centralLvlDept");
			session.removeAttribute("stateLvlDept");
			session.removeAttribute("districtLvlDept");
			session.removeAttribute("subDistrictLvlDept");
			session.removeAttribute("blockLvlDept");
			model.addAttribute("isCenterUser", isCenterUser);
			model.addAttribute("localBodyType", localGovtBodyService.getLGTypeForGovtBody());
			model.addAttribute("stateCode", stateCode);
			model.addAttribute("orgList", orgList);
			mav = new ModelAndView("extenddepartmentAlternate");

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

	/*
	 * @RequestMapping(value="/extendDepartmentState",method=RequestMethod.POST)
	 */
	public ModelAndView showExtendsDepartmentFormStatePOST(HttpSession session, @ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		// List<Organization> orgList=null;
		// Organization organization=new Organization();
		List<OrgLocatedAtLevels> orgLocatedAtLevelsList = null;
		// List<OrganizationCoverage> orgCoverageList=null;
		// List<ExtendDepartment> deptAdminLevelsList=null;
		// List<ExtendDepartment> extendDepartmentList=null;
		// boolean flagd=true,flagb=true,flagt=true,flagv=true;
		Integer unitLevelCode = adminOrgDeptForm.getUnitLevelCode();
		String pageAccess = adminOrgDeptForm.getFormAccessLevel();
		String asParentDept = adminOrgDeptForm.getDepartmentNameEnglish();
		try {
			if (stateCode == null) {
				mav = new ModelAndView("redirect:login.htm");
			} else {

				if (adminOrgDeptForm.getOrgName() != null) {
					Integer orgCode = Integer.parseInt(adminOrgDeptForm.getOrgName().trim());

					String[] arrValue = adminOrgDeptForm.getAdminUnitLevelName().split("\\-");
					// String orgLocaltedLevelCode=arrValue[1];

					Map<Integer, String> hSequenceMap = new LinkedHashMap<Integer, String>();
					for (String string : arrValue) {
						String[] mapPair = string.split("\\|");
						hSequenceMap.put(Integer.parseInt(mapPair[0]), mapPair[1]);
						break;
					}
					boolean isOrganization = false;
					Object objOrgFlow = session.getAttribute("isOrganizationFlow");
					if (objOrgFlow != null) {
						isOrganization = (Boolean) objOrgFlow;
					}
					OrgLocatedAtLevels orgLocatedAtLevels = new OrgLocatedAtLevels();
					if (!hSequenceMap.isEmpty()) {
						Set<Entry<Integer, String>> sequenceObj = hSequenceMap.entrySet();
						@SuppressWarnings("unchecked")
						Entry<Integer, String> e = (Entry<Integer, String>) sequenceObj.toArray()[0];
						unitLevelCode = e.getKey();
						pageAccess = e.getValue();
						int mapSize = hSequenceMap.size();
						if (mapSize == 1) {
							model.addAttribute("enableSaveBtn", true);
						}
						hSequenceMap.remove(unitLevelCode);
						session.setAttribute("process", hSequenceMap);

						orgLocatedAtLevelsList = organisationService.getAdministrativeUnitLevelByOrgLevel(orgCode, unitLevelCode);
						String isCoverageFull = organisationService.getOrgCoverageForFullCoveage(orgLocatedAtLevelsList.get(0).getOrgLocatedLevelCode());
						model.addAttribute("isCoverageFull", isCoverageFull);
						if (orgLocatedAtLevelsList != null && orgLocatedAtLevelsList.size() > 0) {
							orgLocatedAtLevels = orgLocatedAtLevelsList.get(0);
							Integer orgLocatedLevelCode = orgLocatedAtLevels.getOrgLocatedLevelCode();
							session.setAttribute("orgLocatedLevelCode", orgLocatedLevelCode);
							model.addAttribute("orgLocatedLevelCode", orgLocatedLevelCode);
						}
						@SuppressWarnings("unchecked")
						LinkedList<AdminOrgDeptForm> departmentForms = (LinkedList<AdminOrgDeptForm>) session.getAttribute("storedDeptForms");
						if (departmentForms == null) {
							departmentForms = new LinkedList<AdminOrgDeptForm>();
						}
						adminOrgDeptForm.setOrganization(isOrganization);
						departmentForms.add(adminOrgDeptForm);
						session.setAttribute("storedDeptForms", departmentForms);
						adminOrgDeptForm.setDepartmentNameEnglish(orgLocatedAtLevels.getOrgLevelSpecificName());
						adminOrgDeptForm.setDepartmentNameLocal(orgLocatedAtLevels.getOrgLevelSpecificNameLocal());
						adminOrgDeptForm.setDepartmentShortName(orgLocatedAtLevels.getOrgLevelSpecificShortName());
					}
					setProperties(session, pageAccess, model, unitLevelCode, asParentDept, orgCode, false);
					model.addAttribute("adminOrgDeptForm", adminOrgDeptForm);
					// orgList=new ArrayList<Organization>();
					// orgList
					// =organisationService.getOrganizationDetails(orgCode);
					mav = new ModelAndView("modifyExtendDepartmentFlow");
				} else {
					mav = new ModelAndView("redirect:login.htm");
				}
			}
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

	@RequestMapping(value = "/extendDepartmentPost", method = RequestMethod.POST)
	public ModelAndView extendDeparmentAction(HttpSession session, @ModelAttribute("extendDepartment") @Valid DepartmentForm departmentForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
	
		ModelAndView mav = null;
		try {
			/*
			 * if(isAUM) {
			 * departmentValidator.checkExistingOrgNameAUM(departmentForm.
			 * getDeptName(),2, bindingResult, stateCode); } else{
			 * departmentValidator.checkExistingOrgName(departmentForm.
			 * getDeptName(),2, bindingResult); }
			 */

			/*
			 * if(bindingResult.hasErrors()) {
			 * model.addAttribute("ministryList",
			 * organisationService.getMinistryList()); if(isAUM) {
			 * model.addAttribute("localBodyType",
			 * localGovtBodyService.getLGTypeForGovtBody());
			 * model.addAttribute("stateCode", stateCode); mav=new
			 * ModelAndView("createdepartmentAlternate"); } else mav=new
			 * ModelAndView("createdepartment"); } else{
			 */

			if (departmentForm.getOrgCode() != null) {
				session.setAttribute("orgCode", departmentForm.getOrgCode());

				if (isAUM)
					departmentForm.setAUM(true);
				{
					if (departmentForm.getSpecificLevel() != null) {
						String level[] = departmentForm.getSpecificLevel().split(",");
						departmentForm.setOrganization(false);
						session.setAttribute("centralLvlDept", departmentForm);

						if (level[0].equals("S")) {
							mav = new ModelAndView("redirect:extendDepartmentStateLevel.htm?level=" + departmentForm.getSpecificLevel());
						} else if (level[0].equals("D")) {
							mav = new ModelAndView("redirect:extendDepartmentDistrictLevel.htm?level=" + departmentForm.getSpecificLevel());
						} else if (level[0].equals("T")) {
							mav = new ModelAndView("redirect:extendDepartmentSubdistrictLevel.htm?level=" + departmentForm.getSpecificLevel());
						} else if (level[0].equals("B")) {
							mav = new ModelAndView("redirect:extendDepartmentBlockLevel.htm?level=" + departmentForm.getSpecificLevel());
						} else if (level[0].equals("V")) {
							mav = new ModelAndView("redirect:extendDepartmentVillageLevel.htm?level=" + departmentForm.getSpecificLevel());
						} else {
							mav = new ModelAndView("home");
						}
					}
					/*
					 * else{ isCommitted=organisationService.saveDepartment(
					 * departmentForm, null, null, null, null, null, stateCode);
					 * model.addAttribute("msgid",this.successMsg(isCommitted,
					 * departmentForm.isOrganization(),
					 * departmentForm.getDeptName(),request)); mav=new
					 * ModelAndView("success"); }
					 */
				}
			}
			/* } */
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/extendDepartmentDistrictLevel", method = RequestMethod.GET)
	public ModelAndView extendDepartmentFormDistrictLevelGet(HttpSession session, @ModelAttribute("extendDepartment") DepartmentForm departmentForm, Model model, @RequestParam(value = "level", required = false) String level,
			HttpServletRequest request) {
		ModelAndView mav = null;
		ExtendDepartment extendDepartment = null;
		Integer orgCode = null;
		String temp = "";
		try {
			List<DepartmentForm> dptFormStateLevelList = new ArrayList<DepartmentForm>();
			DepartmentForm dptFormCentralLevel = new DepartmentForm();
			dptFormCentralLevel = (DepartmentForm) session.getAttribute("centralLvlDept");
			DepartmentForm deptForm = new DepartmentForm();
			if (dptFormCentralLevel.isAUM()) {
				deptForm = (DepartmentForm) session.getAttribute("centralLvlDept");
				dptFormStateLevelList.add(deptForm);
			} else {
				dptFormStateLevelList = (List<DepartmentForm>) session.getAttribute("stateLvlDept");

			}
			if (session.getAttribute("orgCode") != null) {
				orgCode = Integer.parseInt(session.getAttribute("orgCode").toString().trim());
			}
			if (orgCode != null) {
				extendDepartment = organisationService.getExtendBasicDetails(orgCode, 'D');

				if (extendDepartment != null) {
					/*
					 * if(extendDepartment.getOrgCoverageEntityType()=='C' &&
					 * extendDepartment.getCoverageDetailCode()==0)
					 * model.addAttribute("Full",true);
					 */

					departmentForm.setDeptName(extendDepartment.getOrgLevelSpecificName());
					departmentForm.setDeptNameLocal(extendDepartment.getOrgLevelSpecificNameLocal());
					departmentForm.setShortDeptName(extendDepartment.getOrgLevelSpecificShortName());
					departmentForm.setOrgCode(orgCode);
					List<Integer> entityCode = extendDepartment.getEntityCode();
					for (Integer element : entityCode) {
						temp += element + ",";
					}
					if (temp.length() > 0) {
						temp = temp.substring(0, temp.lastIndexOf(","));
					}
					model.addAttribute("distList", organisationService.getDistrictListforExtendDep(stateCode, temp));
					departmentForm.setLevelNew(false);
					model.addAttribute("setNew", false);
					model.addAttribute("orgDetail", extendDepartment);

				} else {
					model.addAttribute("distList", districtService.getDistrictListbyStateCodeForListBox(stateCode));
					departmentForm.setLevelNew(true);
					model.addAttribute("setNew", true);
					model.addAttribute("stateCode", stateCode);

				}

				model.addAttribute("parent", dptFormStateLevelList);
				model.addAttribute("level", level);

				mav = new ModelAndView("extenddepartmentDistrictLevel" + alternateFlow);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/extendDepartmentDistrictLevel", method = RequestMethod.POST)
	public ModelAndView extendDepartmentFormDistrictLevel(HttpSession session, @ModelAttribute("extendDepartment") @Valid DepartmentForm departmentForm, BindingResult bindingResult, Model model,
			@RequestParam(value = "level", required = false) String level, HttpServletRequest request) {
		
		ModelAndView mav = null;
		try {

			/* if(departmentForm.isLevelNew()==true){ */
			departmentValidator.checkExistingDeptName(stateCode, 'D', departmentForm.getDeptName(), bindingResult);
			if (bindingResult.hasErrors()) {
				List<DepartmentForm> dptFormStateLevelList = new ArrayList<DepartmentForm>();
				DepartmentForm dptFormCentralLevel = new DepartmentForm();
				dptFormCentralLevel = (DepartmentForm) session.getAttribute("centralLvlDept");
				DepartmentForm deptForm = new DepartmentForm();
				if (dptFormCentralLevel.isAUM()) {
					deptForm = (DepartmentForm) session.getAttribute("centralLvlDept");
					dptFormStateLevelList.add(deptForm);
				} else {
					dptFormStateLevelList = (List<DepartmentForm>) session.getAttribute("stateLvlDept");
				}
				model.addAttribute("distList", districtService.getDistrictListbyStateCodeForListBox(stateCode));
				model.addAttribute("parent", dptFormStateLevelList);
				model.addAttribute("level", level);
				departmentForm.setLevelNew(true);
				model.addAttribute("setNew", true);
				model.addAttribute("stateCode", stateCode);
				mav = new ModelAndView("extenddepartmentDistrictLevel" + alternateFlow);
			} else {
				List<DepartmentForm> departmentFormList = new ArrayList<DepartmentForm>();
				if (session.getAttribute("districtLvlDept") != null) {
					List<DepartmentForm> dptFormDistrictLevelList = new ArrayList<DepartmentForm>();
					dptFormDistrictLevelList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
					for (int i = 0; i < dptFormDistrictLevelList.size(); i++) {
						departmentFormList.add(dptFormDistrictLevelList.get(i));
					}
				}
				departmentFormList.add(departmentForm);
				session.setAttribute("districtLvlDept", departmentFormList);

				if (departmentForm.getAddAnother().equals("true")) {
					itr = 1;
					mav = new ModelAndView("redirect:extendDepartmentDistrictLevel.htm?level=" + level);
				} else {
					if (level.length() == 1) {
						DepartmentForm dptFormCentralLevel = new DepartmentForm();
						List<DepartmentForm> dptFormStateLevelList = new ArrayList<DepartmentForm>();
						List<DepartmentForm> dptFormDistrictLevelList = new ArrayList<DepartmentForm>();
						dptFormCentralLevel = (DepartmentForm) session.getAttribute("centralLvlDept");
						dptFormStateLevelList = (List<DepartmentForm>) session.getAttribute("stateLvlDept");
						dptFormDistrictLevelList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
						Integer orgCode = Integer.parseInt(session.getAttribute("orgCode").toString().trim());
						isCommitted = organisationService.extendDepartment(dptFormCentralLevel, dptFormStateLevelList, dptFormDistrictLevelList, null, null, null, stateCode, orgCode);
						model.addAttribute("msgid", this.successMsgforExtend(isCommitted, dptFormCentralLevel.isOrganization(), dptFormCentralLevel.getDeptName(), request));
						mav = new ModelAndView("success");
					}
					String tempLevel[] = null;
					if (level.length() > 2) {
						level = level.substring(2, level.length());
						tempLevel = level.split(",");

						if ("T".equals(tempLevel[0])) {
							mav = new ModelAndView("redirect:extendDepartmentSubdistrictLevel.htm?level=" + level);
						} else if ("B".equals(tempLevel[0])) {
							mav = new ModelAndView("redirect:extendDepartmentBlockLevel.htm?level=" + level);
						} else if ("V".equals(tempLevel[0])) {
							mav = new ModelAndView("redirect:extendDepartmentVillageLevel.htm?level=" + level);
						}
					}
				}
			}
			/* } */
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/extendDepartmentSubdistrictLevel", method = RequestMethod.GET)
	public ModelAndView extendDepartmentFormSubdistrictLevelGet(HttpSession session, @ModelAttribute("extendDepartment") DepartmentForm departmentForm, Model model, @RequestParam(value = "level", required = false) String level,
			HttpServletRequest request) {
		String temp = null;
		List<DepartmentForm> dptFormParentList = null;
		ExtendDepartment extendDepartment = null;
		Integer orgCode = null;
		ModelAndView mav;
		try {
			dptFormParentList = new ArrayList<DepartmentForm>();
			if (level.length() > 2) {
				temp = level.substring(2, level.length());
			}
			DepartmentForm dptFrm = new DepartmentForm();
			dptFrm = (DepartmentForm) session.getAttribute("centralLvlDept");
			String tempLevel = dptFrm.getSpecificLevel();
			if (tempLevel.equals("D,T") || tempLevel.equals("D,T,B") || tempLevel.equals("D,T,V") || tempLevel.equals("D,T,B,V") || tempLevel.equals("S,D,T,B,V") || tempLevel.equals("S,D,T,V") || tempLevel.equals("S,D,T")) {
				dptFormParentList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
			} else if (tempLevel.equals("T") || tempLevel.equals("T,B") || tempLevel.equals("T,V") || tempLevel.equals("T,B,V")) {
				dptFormParentList.add(dptFrm);
			}

			if (session.getAttribute("orgCode") != null) {
				orgCode = Integer.parseInt(session.getAttribute("orgCode").toString().trim());
			}
			if (orgCode != null) {
				extendDepartment = organisationService.getExtendBasicDetails(orgCode, 'T');

				if (extendDepartment != null) {
					/*
					 * if(extendDepartment.getOrgCoverageEntityType()=='C' &&
					 * extendDepartment.getCoverageDetailCode()==0)
					 * model.addAttribute("Full",true);
					 */

					departmentForm.setDeptName(extendDepartment.getOrgLevelSpecificName());
					departmentForm.setDeptNameLocal(extendDepartment.getOrgLevelSpecificNameLocal());
					departmentForm.setShortDeptName(extendDepartment.getOrgLevelSpecificShortName());
					departmentForm.setOrgCode(orgCode);

					departmentForm.setLevelNew(false);
					model.addAttribute("setNew", false);
					model.addAttribute("orgDetail", extendDepartment);

				} else {

					departmentForm.setLevelNew(true);
					model.addAttribute("setNew", true);
					model.addAttribute("stateCode", stateCode);

				}
			}

			model.addAttribute("olc", orgCode);
			model.addAttribute("stateList", stateService.getStateSourceList(stateCode));
			model.addAttribute("parent", dptFormParentList);
			model.addAttribute("level", temp);
			mav = new ModelAndView("extenddepartmentSubdistrictLevel" + alternateFlow);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/extendDepartmentSubdistrictLevel", method = RequestMethod.POST)
	public ModelAndView extendDepartmentFormSubdistrictLevel(HttpSession session, @ModelAttribute("createDepartment") @Valid DepartmentForm departmentForm, BindingResult bindingResult, Model model,
			@RequestParam(value = "level", required = false) String level, HttpServletRequest request) {
		
		ModelAndView mav = null;
		try {
			/* if(departmentForm.isLevelNew()==true){ */
			departmentValidator.checkExistingDeptName(stateCode, 'T', departmentForm.getDeptName(), bindingResult);
			if (bindingResult.hasErrors()) {
				String temp = null;
				List<DepartmentForm> dptFormParentList = null;
				dptFormParentList = new ArrayList<DepartmentForm>();
				if (level.length() > 2) {
					temp = level.substring(2, level.length());
				}
				DepartmentForm dptFrm = new DepartmentForm();
				dptFrm = (DepartmentForm) session.getAttribute("centralLvlDept");
				String tempLevel = dptFrm.getSpecificLevel();
				if (tempLevel.equals("D,T") || tempLevel.equals("D,T,B") || tempLevel.equals("D,T,V") || tempLevel.equals("D,T,B,V") || tempLevel.equals("S,D,T,B,V") || tempLevel.equals("S,D,T,V") || tempLevel.equals("S,D,T")) {
					dptFormParentList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
				} else if (tempLevel.equals("T") || tempLevel.equals("T,B") || tempLevel.equals("T,V") || tempLevel.equals("T,B,V")) {
					dptFormParentList.add(dptFrm);
				}
				departmentForm.setLevelNew(true);
				model.addAttribute("setNew", true);
				model.addAttribute("stateCode", stateCode);
				model.addAttribute("parent", dptFormParentList);
				model.addAttribute("level", temp);
				mav = new ModelAndView("createdepartmentSubdistrictLevel" + alternateFlow);
			} else {
				String tempLevel[] = level.split(",");

				if (level.length() == 0) {
					DepartmentForm dptFormCentralLevel = new DepartmentForm();
					List<DepartmentForm> dptFormStateLevelList = new ArrayList<DepartmentForm>();
					List<DepartmentForm> dptFormDistrictLevelList = new ArrayList<DepartmentForm>();
					dptFormCentralLevel = (DepartmentForm) session.getAttribute("centralLvlDept");
					dptFormStateLevelList = (List<DepartmentForm>) session.getAttribute("stateLvlDept");
					dptFormDistrictLevelList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
					Integer orgCode = Integer.parseInt(session.getAttribute("orgCode").toString().trim());
					isCommitted = organisationService.extendDepartment(dptFormCentralLevel, dptFormStateLevelList, dptFormDistrictLevelList, departmentForm, null, null, stateCode, orgCode);
					model.addAttribute("msgid", this.successMsgforExtend(isCommitted, dptFormCentralLevel.isOrganization(), dptFormCentralLevel.getDeptName(), request));
					mav = new ModelAndView("success");
				}

				session.setAttribute("subDistrictLvlDept", departmentForm);
				if ("B".equals(tempLevel[0])) {
					mav = new ModelAndView("redirect:extendDepartmentBlockLevel.htm?level=" + level);
				}
				if ("V".equals(tempLevel[0])) {
					mav = new ModelAndView("redirect:extendDepartmentVillageLevel.htm?level=" + level);
				}
			}
			/* } */
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/extendDepartmentBlockLevel", method = RequestMethod.GET)
	public ModelAndView extendDepartmentFormBlockLevelGet(HttpSession session, @ModelAttribute("extendDepartment") DepartmentForm departmentForm, Model model, @RequestParam(value = "level", required = false) String level,
			HttpServletRequest request) {
		String temp = null;
		List<DepartmentForm> dptFormParentList = null;
		ModelAndView mav;
		Integer orgCode = null;
		ExtendDepartment extendDepartment = null;
		try {
			dptFormParentList = new ArrayList<DepartmentForm>();
			if (level.length() > 2) {
				temp = level.substring(2, level.length());
			}
			DepartmentForm dptFrm = new DepartmentForm();
			dptFrm = (DepartmentForm) session.getAttribute("centralLvlDept");
			String tempLevel = dptFrm.getSpecificLevel();
			if (tempLevel.equals("D,T") || tempLevel.equals("D,B") || tempLevel.equals("D,B,V") || tempLevel.equals("D,T,B,V") || tempLevel.equals("D,T,B") || tempLevel.equals("S,D,T,B,V") || tempLevel.equals("S,D,T,V")) {
				dptFormParentList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
			} else if (tempLevel.equals("B") || tempLevel.equals("T,B") || tempLevel.equals("B,V") || tempLevel.equals("T,B,V")) {
				dptFormParentList.add(dptFrm);
			}
			if (session.getAttribute("orgCode") != null) {
				orgCode = Integer.parseInt(session.getAttribute("orgCode").toString().trim());
			}
			if (orgCode != null) {
				extendDepartment = organisationService.getExtendBasicDetails(orgCode, 'B');

				if (extendDepartment != null) {
					/*
					 * if(extendDepartment.getOrgCoverageEntityType()=='C' &&
					 * extendDepartment.getCoverageDetailCode()==0)
					 * model.addAttribute("Full",true);
					 */

					departmentForm.setDeptName(extendDepartment.getOrgLevelSpecificName());
					departmentForm.setDeptNameLocal(extendDepartment.getOrgLevelSpecificNameLocal());
					departmentForm.setShortDeptName(extendDepartment.getOrgLevelSpecificShortName());
					departmentForm.setOrgCode(orgCode);

					departmentForm.setLevelNew(false);
					model.addAttribute("setNew", false);
					model.addAttribute("orgDetail", extendDepartment);

				} else {

					departmentForm.setLevelNew(true);
					model.addAttribute("setNew", true);
					model.addAttribute("stateCode", stateCode);

				}
			}

			model.addAttribute("olc", orgCode);
			model.addAttribute("stateList", stateService.getStateSourceList(stateCode));
			model.addAttribute("parent", dptFormParentList);
			model.addAttribute("level", temp);

			mav = new ModelAndView("extenddepartmentBlockLevel");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/extendDepartmentBlockLevel", method = RequestMethod.POST)
	public ModelAndView extendDepartmentFormBlockLevel(HttpSession session, @ModelAttribute("createDepartment") @Valid DepartmentForm departmentForm, BindingResult bindingResult, Model model,
			@RequestParam(value = "level", required = false) String level, HttpServletRequest request) {
		
		ModelAndView mav = null;
		try {
			if (departmentForm.isLevelNew() == true) {
				departmentValidator.checkExistingDeptName(stateCode, 'B', departmentForm.getDeptName(), bindingResult);
				if (bindingResult.hasErrors()) {
					String temp = null;
					List<DepartmentForm> dptFormParentList = null;
					dptFormParentList = new ArrayList<DepartmentForm>();
					if (level.length() > 2) {
						temp = level.substring(2, level.length());
					}
					DepartmentForm dptFrm = new DepartmentForm();
					dptFrm = (DepartmentForm) session.getAttribute("centralLvlDept");
					String tempLevel = dptFrm.getSpecificLevel();
					if (tempLevel.equals("D,T") || tempLevel.equals("D,B") || tempLevel.equals("D,B,V") || tempLevel.equals("D,T,B,V") || tempLevel.equals("D,T,B") || tempLevel.equals("S,D,T,B,V") || tempLevel.equals("S,D,T,V")) {
						dptFormParentList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
					} else if (tempLevel.equals("B") || tempLevel.equals("T,B") || tempLevel.equals("B,V") || tempLevel.equals("T,B,V")) {
						dptFormParentList.add(dptFrm);
					}
					departmentForm.setLevelNew(true);
					model.addAttribute("setNew", true);
					model.addAttribute("stateCode", stateCode);
					model.addAttribute("stateList", stateService.getStateSourceList(stateCode));
					model.addAttribute("parent", dptFormParentList);
					model.addAttribute("level", temp);
					mav = new ModelAndView("createdepartmentBlockLevel");
				} else {
					String tempLevel[] = level.split(",");
					if (level.length() == 0) {
						DepartmentForm dptFormCentralLevel = new DepartmentForm();
						List<DepartmentForm> dptFormStateLevelList = new ArrayList<DepartmentForm>();
						List<DepartmentForm> dptFormDistrictLevelList = new ArrayList<DepartmentForm>();
						DepartmentForm dptFormSubDistrictLevel = new DepartmentForm();
						dptFormCentralLevel = (DepartmentForm) session.getAttribute("centralLvlDept");
						dptFormStateLevelList = (List<DepartmentForm>) session.getAttribute("stateLvlDept");
						dptFormDistrictLevelList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
						dptFormSubDistrictLevel = (DepartmentForm) session.getAttribute("subDistrictLvlDept");
						Integer orgCode = Integer.parseInt(session.getAttribute("orgCode").toString().trim());
						isCommitted = organisationService.extendDepartment(dptFormCentralLevel, dptFormStateLevelList, dptFormDistrictLevelList, dptFormSubDistrictLevel, departmentForm, null, stateCode, orgCode);
						model.addAttribute("msgid", this.successMsgforExtend(isCommitted, dptFormCentralLevel.isOrganization(), dptFormCentralLevel.getDeptName(), request));
						mav = new ModelAndView("success");
					}

					session.setAttribute("blockLvlDept", departmentForm);

					if ("V".equals(tempLevel[0])) {
						mav = new ModelAndView("redirect:extendDepartmentVillageLevel.htm?level=" + level);
					}
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/extendDepartmentVillageLevel", method = RequestMethod.GET)
	public ModelAndView extendDepartmentFormVillageLevelGet(HttpSession session, @ModelAttribute("extendDepartment") DepartmentForm departmentForm, Model model, @RequestParam(value = "level", required = false) String level,
			HttpServletRequest request) {
		List<DepartmentForm> dptFormParentList = null;
		DepartmentForm dptFrm = null;
		Integer orgCode = null;
		ExtendDepartment extendDepartment = null;
		ModelAndView mav;
		try {
			dptFormParentList = new ArrayList<DepartmentForm>();
			dptFrm = new DepartmentForm();
			dptFrm = (DepartmentForm) session.getAttribute("centralLvlDept");
			String tempLevel = dptFrm.getSpecificLevel();
			if (tempLevel.equals("D,V")) {
				dptFormParentList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
			} else if (tempLevel.equals("T,V") || tempLevel.equals("T,B,V") || tempLevel.equals("D,T,V") || tempLevel.equals("D,T,B,V") || tempLevel.equals("S,D,T,B,V") || tempLevel.equals("S,D,T,V")) {
				dptFrm = null;
				dptFrm = new DepartmentForm();
				dptFrm = (DepartmentForm) session.getAttribute("subDistrictLvlDept");
				dptFormParentList.add(dptFrm);
			} else if (tempLevel.equals("B,V") || tempLevel.equals("D,B,V")) {
				dptFrm = null;
				dptFrm = new DepartmentForm();
				dptFrm = (DepartmentForm) session.getAttribute("blockLvlDept");
				dptFormParentList.add(dptFrm);
			} else if (tempLevel.equals("V")) {
				dptFormParentList.add(dptFrm);
			}

			if (session.getAttribute("orgCode") != null) {
				orgCode = Integer.parseInt(session.getAttribute("orgCode").toString().trim());
			}
			if (orgCode != null) {
				extendDepartment = organisationService.getExtendBasicDetails(orgCode, 'V');

				if (extendDepartment != null) {
					/*
					 * if(extendDepartment.getOrgCoverageEntityType()=='C' &&
					 * extendDepartment.getCoverageDetailCode()==0)
					 * model.addAttribute("Full",true);
					 */

					departmentForm.setDeptName(extendDepartment.getOrgLevelSpecificName());
					departmentForm.setDeptNameLocal(extendDepartment.getOrgLevelSpecificNameLocal());
					departmentForm.setShortDeptName(extendDepartment.getOrgLevelSpecificShortName());
					departmentForm.setOrgCode(orgCode);

					departmentForm.setLevelNew(false);
					model.addAttribute("setNew", false);
					model.addAttribute("orgDetail", extendDepartment);

				} else {

					departmentForm.setLevelNew(true);
					model.addAttribute("setNew", true);
					model.addAttribute("stateCode", stateCode);

				}
			}

			model.addAttribute("olc", orgCode);
			model.addAttribute("stateList", stateService.getStateSourceList(stateCode));
			model.addAttribute("parent", dptFormParentList);

			mav = new ModelAndView("extendDepartmentVillageLevel" + alternateFlow);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/extendDepartmentVillageLevel", method = RequestMethod.POST)
	public ModelAndView extendDepartmentFormVillageLevel(HttpSession session, @ModelAttribute("createDepartment") @Valid DepartmentForm departmentForm, BindingResult bindingResult, Model model,
			@RequestParam(value = "level", required = false) String level, HttpServletRequest request) {
		
		ModelAndView mav = null;
		Integer orgCode = null;
		try {

			if (departmentForm.isLevelNew() == true) {
				departmentValidator.checkExistingDeptName(stateCode, 'V', departmentForm.getDeptName(), bindingResult);
				if (bindingResult.hasErrors()) {
					List<DepartmentForm> dptFormParentList = null;
					DepartmentForm dptFrm = null;
					dptFormParentList = new ArrayList<DepartmentForm>();
					dptFrm = new DepartmentForm();
					dptFrm = (DepartmentForm) session.getAttribute("centralLvlDept");
					String tempLevel = dptFrm.getSpecificLevel();
					if (tempLevel.equals("D,V")) {
						dptFormParentList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
					} else if (tempLevel.equals("T,V") || tempLevel.equals("T,B,V") || tempLevel.equals("D,T,V") || tempLevel.equals("D,T,B,V") || tempLevel.equals("S,D,T,B,V") || tempLevel.equals("S,D,T,V")) {
						dptFrm = null;
						dptFrm = new DepartmentForm();
						dptFrm = (DepartmentForm) session.getAttribute("subDistrictLvlDept");
						dptFormParentList.add(dptFrm);
					} else if (tempLevel.equals("B,V")) {
						dptFrm = null;
						dptFrm = new DepartmentForm();
						dptFrm = (DepartmentForm) session.getAttribute("blockLvlDept");
						dptFormParentList.add(dptFrm);
					} else if (tempLevel.equals("V")) {
						dptFormParentList.add(dptFrm);
					}

					model.addAttribute("stateList", stateService.getStateSourceList(stateCode));
					model.addAttribute("parent", dptFormParentList);
					mav = new ModelAndView("extendDepartmentVillageLevel" + alternateFlow);
				} else {
					DepartmentForm dptFormCentralLevel = new DepartmentForm();
					List<DepartmentForm> dptFormStateLevelList = new ArrayList<DepartmentForm>();
					List<DepartmentForm> dptFormDistrictLevelList = new ArrayList<DepartmentForm>();
					DepartmentForm dptFormSubDistrictLevel = new DepartmentForm();
					DepartmentForm dptFormBlockLevel = new DepartmentForm();
					dptFormCentralLevel = (DepartmentForm) session.getAttribute("centralLvlDept");
					dptFormStateLevelList = (List<DepartmentForm>) session.getAttribute("stateLvlDept");
					dptFormDistrictLevelList = (List<DepartmentForm>) session.getAttribute("districtLvlDept");
					dptFormSubDistrictLevel = (DepartmentForm) session.getAttribute("subDistrictLvlDept");
					dptFormBlockLevel = (DepartmentForm) session.getAttribute("blockLvlDept");
					if (session.getAttribute("orgCode") != null) {
						orgCode = Integer.parseInt(session.getAttribute("orgCode").toString().trim());
					}
					isCommitted = organisationService.extendDepartment(dptFormCentralLevel, dptFormStateLevelList, dptFormDistrictLevelList, dptFormSubDistrictLevel, dptFormBlockLevel, departmentForm, stateCode, orgCode);
					if (isCommitted) {
						model.addAttribute("msgid", this.successMsgforExtend(isCommitted, dptFormCentralLevel.isOrganization(), dptFormCentralLevel.getDeptName(), request));
						mav = new ModelAndView("success");
					} else {
						model.addAttribute("msgid", "Department is not extend Successfully");
						mav = new ModelAndView("success");
					}

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

	@RequestMapping(value = "/modifyDepartmentState")
	public ModelAndView modifyDepartmentDetailState(@ModelAttribute("modifyDepartmentCmd") DepartmentForm modifyDepartmentCmd, Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mv = new ModelAndView();
		Integer orgUnitCode = modifyDepartmentCmd.getDepartmentId(); // Select
																		// Department
		String category = null;
		Integer entityCode = null;
		if (session.getAttribute("category") != null) {
			category = session.getAttribute("category").toString();
			modifyDepartmentCmd.setSpecificLevel(category);
		}
		if (session.getAttribute("entityCode") != null) {
			entityCode = Integer.parseInt(session.getAttribute("entityCode").toString());

			// session.removeAttribute("category");

		}
		try {
			List<DistrictLineDepartment> listDistDept = organisationService.getLandRegionLineDepartmentList(entityCode, orgUnitCode, category.charAt(0));
			if (!listDistDept.isEmpty()) {
				DistrictLineDepartment element = listDistDept.get(0);
				modifyDepartmentCmd.setDeptName(element.getOrgName());
				modifyDepartmentCmd.setDeptNamecr(element.getOrgName());
				modifyDepartmentCmd.setDeptNameLocal(element.getOrgNameInLocal());
				modifyDepartmentCmd.setShortDeptName(element.getShortDeptName());
				modifyDepartmentCmd.setOrgCode(element.getOrgCode());
				modifyDepartmentCmd.setOrgLevel(category.charAt(0));
				mv = new ModelAndView("modifyDepartment");
				model.addAttribute("orgUnitCode", orgUnitCode);
				mv.addObject("modifyDepartmentCmd", modifyDepartmentCmd);
				return mv;
			} else {
				mv = new ModelAndView("success");
				mv.addObject("msgid", "No Record(s) available for modify.");
				return mv;
			}
			/*
			 * if (category.equalsIgnoreCase("S")) { List<Organization>
			 * listMinistry =
			 * organisationService.getMinistryDetail(orgUnitCode); if
			 * (!listMinistry.isEmpty()) { Organization organization =
			 * listMinistry.get(0);
			 * modifyDepartmentCmd.setDeptName(organization.getOrgName());
			 * modifyDepartmentCmd.setDeptNamecr(organization.getOrgName());
			 * modifyDepartmentCmd.setDeptNameLocal(organization.getOrgNameLocal
			 * ());
			 * modifyDepartmentCmd.setShortDeptName(organization.getShortName())
			 * ; modifyDepartmentCmd.setOrgCode(organization.getOlc());
			 * modifyDepartmentCmd.setOrgLevel(organization.getOrgLevel()); //
			 * added by sushil } else noRecord = true;
			 * 
			 * } else if (category.equalsIgnoreCase("D") ||
			 * category.equalsIgnoreCase("T") || category.equalsIgnoreCase("B")
			 * || category.equalsIgnoreCase("V")) { List<OrgLocatedAtLevels>
			 * departmentList =
			 * organisationService.getDepartMentLowLevelDetails(orgUnitCode);
			 * 
			 * if (!departmentList.isEmpty()) { OrgLocatedAtLevels
			 * orgLocatedAtLevels = departmentList.get(0);
			 * modifyDepartmentCmd.setDeptName(orgLocatedAtLevels.
			 * getOrgLevelSpecificName());
			 * modifyDepartmentCmd.setDeptNamecr(orgLocatedAtLevels.
			 * getOrgLevelSpecificName());
			 * modifyDepartmentCmd.setDeptNameLocal(orgLocatedAtLevels.
			 * getOrgLevelSpecificNameLocal());
			 * modifyDepartmentCmd.setShortDeptName(orgLocatedAtLevels.
			 * getOrgLevelSpecificShortName());
			 * modifyDepartmentCmd.setOrgCode(orgLocatedAtLevels.getOlc());
			 * modifyDepartmentCmd.setOrgLevel(orgLocatedAtLevels.
			 * getLocatedAtLevel());
			 * 
			 * } else noRecord = true; } if (noRecord == false) { mv = new
			 * ModelAndView("modifyDepartment");
			 * model.addAttribute("orgUnitCode", orgUnitCode);
			 * mv.addObject("modifyDepartmentCmd", modifyDepartmentCmd); return
			 * mv; } else { mv = new ModelAndView("success");
			 * mv.addObject("msgid", "No Record(s) available for modify.");
			 * return mv; }
			 */

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

	}

	@RequestMapping(value = "/modifyDepartmentStateAction", method = RequestMethod.POST)
	public ModelAndView modifyDepartmentState(@ModelAttribute("modifyDepartmentCmd") DepartmentForm modifyDepartmentCmd, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		// String viewDepartmentdetail = null;
		ModelAndView mav = new ModelAndView("modifyDepartment");
		try {
			int orgCode = 0;
			orgCode = modifyDepartmentCmd.getOrgCode();
			String category = modifyDepartmentCmd.getSpecificLevel();
			
			if (stateCode != null && category.length() > 0) {
				departmentValidator.checkExistingDeptNameforView(stateCode, category.charAt(0), modifyDepartmentCmd, result);
			}
			departmentValidator.validateViewDepartment(modifyDepartmentCmd, result);
			if (result.hasErrors()) {

				mav.addObject("modifyDepartmentCmd", modifyDepartmentCmd);
				return mav;
			}
			organisationService.departmentUpdate(modifyDepartmentCmd, orgCode, session, request);
			String aMessage = "Department Modified successfully!!";
			mav = new ModelAndView("configview");
			mav.addObject("msgid", aMessage);

			/*
			 * String aMessage="Ministry Modified successfully!!"; ModelAndView
			 * mv=new ModelAndView("configview"); mv.addObject("msgid",
			 * aMessage); return mv;
			 */
			/*
			 * mav.addObject("modifyDepartmentCmd", modifyDepartmentCmd);
			 * viewDepartmentdetail =
			 * "redirect:viewDepartmentDetailmodify.htm?id=" + orgCode +
			 * "&type=S";
			 */
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}

	/**
	 * The {@code setProperties} used for set modal attributes to be displayed
	 * on view (jsp).
	 * 
	 * @param session
	 * @param pageAccess
	 * @param model
	 * @param unitLevelCode
	 * @param asParentDept
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void setProperties(HttpSession session, String pageAccess, Model model, Integer unitLevelCode, String asParentDept, Integer orgCode, boolean fromError) throws Exception {

		if (fromError && (pageAccess == null || "".equals(pageAccess))) {
			LinkedHashMap<Integer, String> map = (LinkedHashMap<Integer, String>) session.getAttribute("process");
			if (map != null && !map.isEmpty()) {
				Set<Integer> keySet = map.keySet();
				List<Object[]> unitCodesNames = adminDeprtmentService.getUnitLevelNames(keySet);
				model.addAttribute("keySetUnitCodes", keySet);
				model.addAttribute("unitCodesNames", unitCodesNames);
			}

			boolean isOrganizationFlow = false;
			Object objOrgFlow = session.getAttribute("isOrganizationFlow");
			if (objOrgFlow != null) {
				isOrganizationFlow = (Boolean) objOrgFlow;
			}
			if (isCenterUser) {
				model.addAttribute("ministryList", organisationService.getMinistryList());
			}
			if (isOrganizationFlow) {
				if (!isCenterUser) {
					model.addAttribute("listDepartments", adminDeprtmentService.getDepartmentList(stateCode, 2));
				}
				model.addAttribute("listOrgTypes", organisationService.getOrganizationType());
			}
		}

		if (isCenterUser && !"A".equalsIgnoreCase(pageAccess)) {
			List<State> statelist = stateService.getStateSourceListExtended(orgCode, unitLevelCode);
			model.addAttribute("statelist", statelist);
		}
		if ("A".equalsIgnoreCase(pageAccess)) {
			List<DeptAdminUnitEntity> adminEntities = adminDeprtmentService.getAdminEntitiesExtended(orgCode, unitLevelCode);
			model.addAttribute("adminEntities", adminEntities);
		}
		if ("D".equalsIgnoreCase(pageAccess)) {
			List<District> districtList = districtService.getDistrictListbyStateCodeForListBoxExtended(stateCode, orgCode, unitLevelCode);
			model.addAttribute("distList", districtList);
		}
		model.addAttribute("parent", asParentDept);
		model.addAttribute("stateCode", stateCode);
		model.addAttribute("orgCode", orgCode);
		model.addAttribute("isCenterUser", isCenterUser);
		model.addAttribute("unitLevelCode", unitLevelCode);
		model.addAttribute("pageAccessLevel", pageAccess);
	}

	/**
	 * The {@code saveAdminDepartment} used to save department(s) after
	 * completion of sequential processing or even for single (top level i.e
	 * Center or State). Forward control to success / error page.
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param adminOrgDeptForm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveExtendedAdminDepartmentProcess", method = RequestMethod.POST)
	public ModelAndView saveAdminDepartment(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm, final BindingResult binding) {
		ModelAndView mav = null;

		try {

			boolean isOrganization = false;
			Object objOrgFlow = session.getAttribute("isOrganizationFlow");
			if (objOrgFlow != null) {
				isOrganization = (Boolean) objOrgFlow;
			}
			Integer orgLocatedLevelCode = (Integer) session.getAttribute("orgLocatedLevelCode");
			adminOrgDeptForm.setOrgLocatedLevelCode(orgLocatedLevelCode);
			adminValidator.validateExtendDept(adminOrgDeptForm, binding, isCenterUser, isOrganization, session);
			Integer orgCode = Integer.parseInt(adminOrgDeptForm.getOrgName().trim());
			if (binding.hasErrors()) {
				mav = new ModelAndView("modifyExtendDepartmentFlow");
				setProperties(session, adminOrgDeptForm.getFormAccessLevel(), model, adminOrgDeptForm.getUnitLevelCode(), adminOrgDeptForm.getParentDepartmentName(), orgCode, true);
				model.addAttribute("adminOrgDeptForm", adminOrgDeptForm);
				return mav;
			}
			adminOrgDeptForm.setOrganization(isOrganization);
			Boolean isHierarchyAvailable = (Boolean) session.getAttribute("isHierarchyAvailable");
			LinkedList<AdminOrgDeptForm> departmentForms = new LinkedList<AdminOrgDeptForm>();
			if (isHierarchyAvailable != null && isHierarchyAvailable) {
				// Implementation of save department without hierarchy
				departmentForms = (LinkedList<AdminOrgDeptForm>) session.getAttribute("storedDeptForms");
				departmentForms.add(adminOrgDeptForm);
			} else {
				departmentForms.add(adminOrgDeptForm);
			}

			boolean isSuccess = adminDeprtmentService.saveExtendedAdminDepartmentProcess(departmentForms, stateCode, orgLocatedLevelCode, isCenterUser);
			mav = new ModelAndView("success");
			String deptOrg = "Department ";
			if (isOrganization) {
				deptOrg = "Organization ";
			}
			if (isSuccess) {
				mav.addObject("msgid", deptOrg.concat("Extended Successfully."));

			} else {
				mav.addObject("msgid", deptOrg.concat("Extended Process Failed."));
			}
			clearSessionAttributes(session, true);
		} catch (Exception e) {
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}

	/**
	 * The {@code errorHandler} returns error path and saved required stack
	 * trace.
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	private String errorHandler(HttpServletRequest request, Exception e) {
		IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		return expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
	}

	/**
	 * For Department localbody mapping
	 * 
	 * @author Ripunj
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "addDeptMappingForm", method = RequestMethod.GET)
	public ModelAndView getDeptMappingForm(ModelMap model, HttpServletRequest request, HttpSession session) {
		String message = "";
		ModelAndView mav = null;
		
		try {
			
			setGlobalParams(session);
			model.addAttribute("stateCode", stateCode);
			model.addAttribute("userId", userId);
			
			mav = new ModelAndView("departmentMappingAdd");
			adminDeprtmentService.setMappingBasedOnRole(model, request, stateCode);
			
			
			if (model.get("message") != null) {
				message = model.get("message").toString();
			}
			
			model.put("message", message);
			return mav;
		} catch (Exception Ex) {
			System.out.println("Exception in addDeptMappingForm.do GET request is -->" + Ex);
			mav = new ModelAndView(errorHandler(request, Ex));
			return mav;

		}

	}

	@RequestMapping(value = "addDeptMappingForm", method = RequestMethod.POST)
	public ModelAndView saveDeptMappingForm(ModelMap model, HttpServletRequest request, HttpSession session) {
		String message = "";
		
		ModelAndView mav = null;
		try {
			model.addAttribute("stateCode", stateCode);
			model.addAttribute("userId", userId);
           
			boolean saveFlag = adminDeprtmentService.saveDepartmentMapping(model, request);
			
			
	        if (saveFlag) {
				message = "Mapping Saved Sucessfully";
			} else {
				message = "Mapping Creation Failure";
			}
			model.put("message", message);
			mav = new ModelAndView("success");
			// return "StateAdmin/DepartmentMappingAdd";
			return getDeptMappingForm(model, request, session);
		} catch (Exception Ex) {
			System.out.println("Exception in addDeptMappingForm.do GET request is -->" + Ex);
			mav = new ModelAndView(errorHandler(request, Ex));
			return mav;

		}

	}	//By Rajeev kumar pandey dated 29-8-19
	@RequestMapping(value = "/accessDepartmentScript", method = RequestMethod.GET)
	public ModelAndView departmentScriptAccessControl(HttpSession session, @ModelAttribute("createDepartment") DepartmentForm departmentForm, Model model, HttpServletRequest request) {
		ModelAndView mavOBJ;
		List<EntityTransactionsNew> entityTransactionsNews =null;
		try {
			mavOBJ = new ModelAndView("department_script");
			entityTransactionsNews =adminDeprtmentService.getTranctionDtlsChangeEntity(stateCode);
			request.setAttribute("organizationList",adminDeprtmentService.getAllOrganizationDetailsBySC(stateCode));
			if(entityTransactionsNews != null && !entityTransactionsNews.isEmpty()) {
				request.setAttribute("entityTransactionsNews", entityTransactionsNews);
			}
			
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mavOBJ = new ModelAndView(redirectPath);
			return mavOBJ;
		}
		return mavOBJ;
	}
	//By Abhishek Singh dated 17-8-17
	@RequestMapping(value = "/manageParentChildHierarchy", method = RequestMethod.GET)
	public ModelAndView parentChildHierarchyControl(HttpSession session, @ModelAttribute("createDepartment") DepartmentForm departmentForm, Model model, HttpServletRequest request) {
		ModelAndView mavOBJ;
		try {
			mavOBJ = new ModelAndView("region_hierarchy");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mavOBJ = new ModelAndView(redirectPath);
			return mavOBJ;
		}
		return mavOBJ;
	}
	//By Rajeev kumar pandey dated 29-8-19
	@RequestMapping(value = "/accessDepartmentScript", method = RequestMethod.POST)
	public ModelAndView getGeneratedScript(HttpSession session, @ModelAttribute("createDepartment") DepartmentForm departmentForm, Model model,  HttpServletRequest request) {
		List<Object> scriptQuery = null;
		Integer transactionId =null;
		String query =null;
		String Subject =null;
		String textScript =new String();
		String desSubj =new String();

		String disc = "";
		File file =null;
		Boolean status =false;
		 ModelAndView mavHome = new ModelAndView();
		try {
		
			transactionId = Integer.parseInt(request.getParameter("tId").toString());
			
			disc =request.getParameter("optionval");
			scriptQuery = adminDeprtmentService.getScriptThroughTxId(transactionId);
			
			Iterator iterator = scriptQuery.iterator();
		      while(iterator.hasNext()) {
		    	  query= (String)iterator.next();
		    	  textScript =textScript +query;
		         System.out.println(textScript);
		      }
				String serverPathLocation = LGDResourceBundle.getBundle("email_configure", Locale.ENGLISH).getObject("lgd.serverPathLocation").toString();
				
		      Subject = serverPathLocation + "          "  + "TX-" + String.valueOf(transactionId) + "          " + "||"+"        " + disc;
			String mailIds = LGDResourceBundle.getBundle("email_configure", Locale.ENGLISH).getObject("lgd.mailids").toString();
			/*String title="Exception while executing LGD "+type+" scheduler on "+strDate;*/
			MailService mailService = new MailService();
			StringBuilder mailDescription=new StringBuilder();
			mailDescription.append("<h1>"+"</h1><br>");
/*			mailDescription.append(errorObj.toString());
*/			
			try {
			    AttachmentMaster attachmentMaster = this.govtOrderService.getUploadFileConfigurationDetails(11L);
				String path = attachmentMaster.getFileLocation();				 Date date = new Date();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		      	file= new File(path+"scriptFile.txt");
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(textScript);
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			desSubj = "Generated Script file";
			status= mailService.sendMail(mailIds, Subject,desSubj , file);
			if(status)
			{
			   mavHome = new ModelAndView("success");
				mavHome.addObject("msgid", " Script Generated Successfully.  ");
			}else {
				  mavHome = new ModelAndView("success");
					mavHome.addObject("msgid", " Error while Generating Script.  ");
				
			}
				
		} catch (Exception e) {
			 IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		      String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", Integer.valueOf(1), e);
		      return new ModelAndView(redirectPath);
		   
		}

		return mavHome;
	}
	
}
