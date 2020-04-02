package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.AdministrativeEntityCoverage;
import in.nic.pes.lgd.bean.DeptAdminUnit;
import in.nic.pes.lgd.bean.DeptAdminUnitEntity;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.LgdOrganizationAtLevels;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.OrganizationType;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.common.EsapiEncoder;
import in.nic.pes.lgd.common.MailService;
import in.nic.pes.lgd.constant.DepartmentConstent;
import in.nic.pes.lgd.draft.form.DepartmentOrgListDto;
import in.nic.pes.lgd.forms.DepartmentAdminForm;
import in.nic.pes.lgd.forms.DepartmentForm;
import in.nic.pes.lgd.forms.DesignationForm;
import in.nic.pes.lgd.forms.DesignationReportingForm;
import in.nic.pes.lgd.forms.MinistryForm;
import in.nic.pes.lgd.forms.OrganizationTypeForm;
import in.nic.pes.lgd.forms.OrganizationUnitForm;
import in.nic.pes.lgd.service.AdministrativeDepratmentService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.OrganizationService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.utils.CommonUtil;
import in.nic.pes.lgd.validator.DepartmentValidator;
import in.nic.pes.lgd.validator.MinistryValidator;
import in.nic.pes.lgd.validator.OrganizationTypeValidator;
import in.nic.pes.lgd.validator.OrganizationValidator;
import in.nic.pes.lgd.validator.VillageValidator;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.entities.LBTypeDetails;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class OrganizationController { // NO_UCD (unused code)

	private static final Logger log = Logger.getLogger(OrganizationController.class);

	@Autowired
	StateService stateService;

	@Autowired
	OrganizationService organisationService;

	@Autowired
	DistrictService districtService;

	@Autowired
	MinistryValidator ministryValidator;

	@Autowired
	OrganizationValidator organizationValidator;

	@Autowired
	DepartmentValidator departmentValidator;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private OrganizationTypeValidator organizationTypeValidator;

	@Autowired
	private LocalGovtBodyService localGovtBodyService;
	
	@Autowired
	private VillageValidator viewValidator;
	
	@Autowired
	private AdministrativeDepratmentService adminDeprtmentService;

	private Integer stateCode;

	private Integer districtCode;

	private Long userId;
	
	private Integer assignUnit;
	
	private boolean isCenterUser;
	


	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request,HttpSession session) {
		try {
			setGlobalParams(session);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			dateFormat.setLenient(false);
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "label.lgd", "userId", 1, e);
		}
	}

	private void setGlobalParams(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId();
		assignUnit= sessionObject.getAssignUnit();
		isCenterUser=("C").equals(sessionObject.getIsCenterorstate());
		
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/addOrganizationType", method = RequestMethod.GET)
	public ModelAndView showOrganizationType(HttpSession session, HttpServletRequest request) {
		ModelAndView mav;
		try {
			if (session.getAttribute("formbean") != null) {
				session.removeAttribute("formbean");
				session.removeValue("formbean");
			}
			mav = new ModelAndView("addorganizationtype");
			mav.addObject("createOrganizationType", new OrganizationTypeForm());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@ModelAttribute("stateSourceList")
	public List<State> populateSourceStateList(HttpServletRequest request) {
		try {
			return stateService.getStateSourceList();
		} catch (Exception e) {
			return null;
		}
	}

	@ModelAttribute("districtList")
	public List<District> getDistrictList(HttpSession session, HttpServletRequest request) {
		try {
			return districtService.getDistrictList(stateCode);
		} catch (Exception e) {
			return null;
		}
	}

	@ModelAttribute("ministryList")
	public List<Organization> populateMinistryList(HttpServletRequest request) {
		try {
			return organisationService.getMinistryList();
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/modifyOrganizationType", method = RequestMethod.GET)
	public ModelAndView modifyOrganizationType(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("modifyorganizationtype");
		try {
			mav.addObject("orgTypeForm", new OrganizationTypeForm());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/editOrganizationType", method = RequestMethod.GET)
	public ModelAndView editOrgType(@ModelAttribute("orgTypeForm") OrganizationTypeForm orgTypeForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		List<OrganizationType> orgList = null;
		try {
			Integer orgTypeId = Integer.parseInt(orgTypeForm.getOrgTypeId());
			orgList = organisationService.getOrganizationType(orgTypeId);
			if (orgList.size() > 0) {
				mav = new ModelAndView("editorganizationtype");
				orgTypeForm.setOrgType(orgList.get(0).getOrgType());
				orgTypeForm.setOrgTypeCode(orgList.get(0).getOrgTypeCode());
			} else {
				String message = "Some Probmlem to get data of Organization Type";
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			}
		} catch (Exception e) {
			;
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}

	@RequestMapping(value = "/editOrganizationType", method = RequestMethod.POST)
	public ModelAndView editOrgTypeSave(@ModelAttribute("orgTypeForm") OrganizationTypeForm orgTypeForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		String message;
		try {
			organizationTypeValidator.validateforModify(orgTypeForm, result);
			if (result.hasErrors()) {
				mav = new ModelAndView("editorganizationtype");
				return mav;
			}
			OrganizationType orgType = new OrganizationType();
			orgType.setOrgType(orgTypeForm.getOrgType());
			orgType.setOrgTypeCode(orgTypeForm.getOrgTypeCode());
			boolean flag = organisationService.updateOrganizationType(orgType);
			if (flag) {
				message = "Organization Type Update Successfully";
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			} else {
				message = "Organization Type Update not Successfully";
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/modifyOrganizationType", method = RequestMethod.POST)
	public ModelAndView modifyOrgType(@ModelAttribute("orgTypeForm") OrganizationTypeForm orgTypeForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		try {
			String orgTypeCode = orgTypeForm.getCode();
			String orgtype = orgTypeForm.getOrgType();
			mav = new ModelAndView("modifyorganizationtype");
			List<OrganizationType> organizationTypeDetails = null;

			if (orgTypeCode != null && orgTypeCode != "") {
				organizationTypeDetails = organisationService.getOrganizationTypeListbyOrgTypeCode(Integer.parseInt(orgTypeCode));
			} else if (orgtype != null) {
				organizationTypeDetails = organisationService.getOrganizationTypeListbyOrgType(orgtype);
			}
			orgTypeForm.setOrganizationTypeDetails(organizationTypeDetails);
			model.addAttribute("organizationTypeDetails", organizationTypeDetails);
		} catch (Exception e) {
			;
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/deleteOrganizationType", method = RequestMethod.POST)
	public ModelAndView deleteOrgType(@ModelAttribute("orgTypeForm") OrganizationTypeForm orgTypeForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		try {
			Integer orgTypeId = Integer.parseInt(orgTypeForm.getOrgTypeId());
			String orgTypeCode = orgTypeForm.getCode();
			String orgtype = orgTypeForm.getOrgType();
			boolean flag = organisationService.ChecktOrganizationTypeExist(orgTypeId);
			if (flag) {
				flag = organisationService.deleteOrganizationType(orgTypeId);
				if (flag) {
					mav = new ModelAndView("modifyorganizationtype");
					if (orgTypeCode != null && orgTypeCode != "") {
						organisationService.getOrganizationTypeListbyOrgTypeCode(Integer.parseInt(orgTypeCode));
					} else if (orgtype != null) {
						organisationService.getOrganizationTypeListbyOrgType(orgtype);
					}
					String message = "Organization Type delete  successfully";
					mav = new ModelAndView("success");
					mav.addObject("msgid", message);
				} else {
					String message = "Organization Type delete not successfully";
					mav = new ModelAndView("success");
					mav.addObject("msgid", message);
				}
			} else {
				String message = "Organization Type Already Exist in Organization";
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			}
		} catch (Exception e) {
			;
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@ModelAttribute("organizationTypeList")
	public List<OrganizationType> populateOrgTypeList(HttpServletRequest request) {
		try {
			return organisationService.getOrgTypeList();
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/saveMinistryDetails", method = RequestMethod.POST)
	public ModelAndView createMinistry(@ModelAttribute("createMinistry") MinistryForm ministryForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("createministry");
		try {
			ministryValidator.validate(ministryForm, result);
			if (result.hasErrors()) {
				mv.addObject("createMinistry", ministryForm);
				return mv;
			} else {
				organisationService.saveMinistryDetails(ministryForm);
				String aMessage = "The new ministry " + ministryForm.getMinistryName() + " is created successfully";
				ModelAndView mav = new ModelAndView("configview");
				mav.addObject("msgid", aMessage);
				return mav;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	@RequestMapping(value = "/saveOrganizationType", method = RequestMethod.POST)
	public ModelAndView createOrganizationType(@ModelAttribute("createOrganizationType") OrganizationTypeForm orgTypeForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("addorganizationtype");
		try {
			organizationValidator.validate(orgTypeForm, result);
			if (result.hasErrors()) {
				mv.addObject("createOrganizationType", orgTypeForm);
				return mv;
			} else {
				organisationService.saveOrganisationType(orgTypeForm);
				String aMessage = "The new Organization Type '" + orgTypeForm.getOrgTypeName() + "' is saved successfully!!";
				ModelAndView mav = new ModelAndView("configview");
				mav.addObject("msgid", aMessage);
				return mav;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	@RequestMapping(value = "/add_designation", method = RequestMethod.GET)
	public String designationHierarchyGet(@ModelAttribute("designationForm") DesignationForm designationForm, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("orgType", organisationService.getOrganizationTypeforAddDesig());
			return "addDesignation";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}

	@RequestMapping(value = "/add_designation", method = RequestMethod.POST)
	public ModelAndView designationHierarchy(@ModelAttribute("designationForm") DesignationForm designationForm, Model model, HttpServletRequest request) {
		boolean isCommitted = false;
		ModelAndView mav = null;
		Session session = null;
		try {
			isCommitted = organisationService.saveOrganizationDesignation(designationForm);
			if (isCommitted) {
				session = sessionFactory.openSession();
				model.addAttribute("orgTypeCode", designationForm.getOrgType());
				model.addAttribute("orgType", session.createQuery("select orgType from OrganizationType where orgTypeCode=" + designationForm.getOrgType()).list().get(0).toString());
				model.addAttribute("orgCode", designationForm.getOrgCode());
				model.addAttribute("orgName", session.createQuery("select orgName from Organization where orgCode=" + designationForm.getOrgCode()).list().get(0).toString());
				model.addAttribute("NewDesig", session.createQuery("from OrganizationDesignation o where o.isactive = true and orgCode=" + designationForm.getOrgCode()).list());
				session.close();
				mav = new ModelAndView("viewDesignationSuccess");
			} else {
				model.addAttribute("msgid", "New Designation Details Failed To Save. Kindly Re-enter the Details. !");
				mav = new ModelAndView("success");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return mav;
	}

	@RequestMapping(value = "/add_reporting_structure", method = RequestMethod.POST)
	public ModelAndView designationReporting(@ModelAttribute("designationReportingForm") DesignationReportingForm designationReportingForm, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			boolean isCommitted = organisationService.saveOrganizationReporting(designationReportingForm);
			if (isCommitted) {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "New Reporting structure of Organization has been created.");
			} else {
				model.addAttribute("msgid", "New Reporting Structure Failed To Save. Kindly Re-enter the Details. !");
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

	@RequestMapping(value = "/add_reporting_structure_state", method = RequestMethod.GET)
	public ModelAndView designationReportingStateGet(@ModelAttribute("designationReportingForm") DesignationReportingForm designationReportingForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("addReportingStructureState");
			model.addAttribute("orgType", organisationService.getOrganizationTypeforAddDesig());
			mav.addObject("slcCode", stateCode);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/add_reporting_structure", method = RequestMethod.GET)
	public ModelAndView designationReportingGet(@ModelAttribute("designationReportingForm") DesignationReportingForm designationReportingForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("addReportingStructure");
			model.addAttribute("orgType", organisationService.getOrganizationTypeforAddDesig());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/modifyMinistry")
	public ModelAndView modifyMinistryDetail(@ModelAttribute("modifyMinistryCmd") MinistryForm modifyMinistryCmd, Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int orgCode = modifyMinistryCmd.getMinistryId();
		try {
			List<Organization> listMinistry = organisationService.getMinistryDetail(orgCode);
			EsapiEncoder.encode(listMinistry);
			for (int i = 0; i < listMinistry.size(); i++) {
				modifyMinistryCmd.setMinistryNamecr(listMinistry.get(i).getOrgName());
				modifyMinistryCmd.setMinistryName(listMinistry.get(i).getOrgName());
				modifyMinistryCmd.setShortministryName(listMinistry.get(i).getShortName());
				modifyMinistryCmd.setOrgCode(listMinistry.get(i).getOrgCode());
				modifyMinistryCmd.setOrgVersion(listMinistry.get(i).getOrgVersion());
			}
			mv = new ModelAndView("modifyMinistry");
			mv.addObject("modifyMinistryCmd", modifyMinistryCmd);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/modifyMinistryAction", method = RequestMethod.POST)
	public ModelAndView modifyMinistry(@ModelAttribute("modifyMinistryCmd") MinistryForm modifyMinistryCmd, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("modifyMinistry");
		try {
			mav.addObject("modifyMinistryCmd", modifyMinistryCmd);
			ministryValidator.validateChange(modifyMinistryCmd, result);
			if (result.hasErrors()) {
				mav.addObject("modifyMinistryCmd", modifyMinistryCmd);
				return mav;
			} else {
				int orgCode = modifyMinistryCmd.getOrgCode();
				organisationService.ministryUpdate(modifyMinistryCmd, orgCode, session, request);
				mav = new ModelAndView("redirect:viewMinistryDetailmodify.htm?id=" + orgCode + "&type=S");
				return mav;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	@RequestMapping(value = "/viewMinistryDetailmodify", method = RequestMethod.GET)
	public ModelAndView viewMinistryDetail(@ModelAttribute("viewMinistry") MinistryForm viewMinistry, Model model, @RequestParam("id") Integer orgCode, @RequestParam("type") String type, HttpServletRequest request) {
		List<MinistryForm> listMinistry = null;
		listMinistry = new ArrayList<MinistryForm>();
		ModelAndView mv = new ModelAndView("view_ministrydetail");
		try {
			listMinistry = organisationService.getMinistryDetails(orgCode);
			model.addAttribute("listMinistry", listMinistry);
			viewMinistry.setListMinistry(listMinistry);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/modifyDepartment")
	public ModelAndView modifyDepartmentDetail(@ModelAttribute("modifyDepartmentCmd") DepartmentForm modifyDepartmentCmd, Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mv = new ModelAndView();
		Integer orgUnitCode = modifyDepartmentCmd.getDepartmentId(); // Select
																		// Department
		String category = null;
		boolean noRecord = false;// Code
		if (session.getAttribute("category") != null) {
			category = session.getAttribute("category").toString();
			modifyDepartmentCmd.setSpecificLevel(category);
		}
		try {
			if (category.equalsIgnoreCase("S")) {
				List<Organization> listMinistry = organisationService.getMinistryDetail(orgUnitCode);
				if (!listMinistry.isEmpty()) {
					Organization organization = listMinistry.get(0);
					modifyDepartmentCmd.setDeptName(organization.getOrgName());
					modifyDepartmentCmd.setDeptNamecr(organization.getOrgName());
					modifyDepartmentCmd.setDeptNameLocal(organization.getOrgNameLocal());
					modifyDepartmentCmd.setShortDeptName(organization.getShortName());
					modifyDepartmentCmd.setOrgCode(organization.getOlc());
					modifyDepartmentCmd.setOrgLevel(CommonUtil.getCategoryLevel(organization.getOrgLevel())); // added
					// by
					// sushil
				} else
					noRecord = true;
			} else if (category.equalsIgnoreCase("D") || category.equalsIgnoreCase("T") || category.equalsIgnoreCase("B") || category.equalsIgnoreCase("V")) {
				List<OrgLocatedAtLevels> departmentList = organisationService.getDepartMentLowLevelDetails(orgUnitCode);
				if (!departmentList.isEmpty()) {
					OrgLocatedAtLevels orgLocatedAtLevels = departmentList.get(0);
					modifyDepartmentCmd.setDeptName(orgLocatedAtLevels.getOrgLevelSpecificName());
					modifyDepartmentCmd.setDeptNamecr(orgLocatedAtLevels.getOrgLevelSpecificName());
					modifyDepartmentCmd.setDeptNameLocal(orgLocatedAtLevels.getOrgLevelSpecificNameLocal());
					modifyDepartmentCmd.setShortDeptName(orgLocatedAtLevels.getOrgLevelSpecificShortName());
					modifyDepartmentCmd.setOrgCode(orgLocatedAtLevels.getOlc());
					modifyDepartmentCmd.setOrgLevel(CommonUtil.getCategoryLevel(orgLocatedAtLevels.getLocatedAtLevel()));
				} else
					noRecord = true;
			}
			if (noRecord == false) {
				mv = new ModelAndView("modifyDepartment");
				model.addAttribute("orgUnitCode", orgUnitCode);
				mv.addObject("modifyDepartmentCmd", modifyDepartmentCmd);
				return mv;
			} else {
				mv = new ModelAndView("success");
				mv.addObject("msgid", "No Record(s) available for modify.");
				return mv;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	@RequestMapping(value = "/modifyDepartmentAction", method = RequestMethod.POST)
	public ModelAndView modifyDepartment(@ModelAttribute("modifyDepartmentCmd") DepartmentForm modifyDepartmentCmd, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("modifyDepartment");
		try {
			int orgCode = 0;
			orgCode = modifyDepartmentCmd.getOrgCode();
			String category = modifyDepartmentCmd.getSpecificLevel();
			
			if (category.length() > 0) {
				
				if (stateCode == 0) {
					departmentValidator.checkExistingDeptNameforView(stateCode, modifyDepartmentCmd.getOrgLevel(), modifyDepartmentCmd, result);
				} else {
					departmentValidator.checkExistingDeptNameforView(stateCode, category.charAt(0), modifyDepartmentCmd, result);
				}
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
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}

	@RequestMapping(value = "/viewDepartmentDetailmodify", method = RequestMethod.GET)
	public ModelAndView viewDepartmentDetail(@ModelAttribute("viewDepartment") MinistryForm viewDepartment, Model model, @RequestParam("id") Integer orgCode, @RequestParam("type") String type, HttpServletRequest request) {

		List<MinistryForm> listMinistry = null;
		ModelAndView mv;
		try {
			listMinistry = new ArrayList<MinistryForm>();
			listMinistry = organisationService.getMinistryDetails(orgCode);
			mv = new ModelAndView("view_departmentdetail");
			model.addAttribute("listMinistry", listMinistry);
			viewDepartment.setListMinistry(listMinistry);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	// started - worked by Chandra for admin unit , entity , set parent org
	// units
	@RequestMapping(value = "/newDeptAdmitUnit", method = RequestMethod.GET)
	public ModelAndView assignAdminUnit(HttpSession httpSession, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("view_deptAdminUnits");
		DeptAdminUnit deptAdminUnit = new DeptAdminUnit();
		Integer type = Integer.parseInt(DepartmentConstent.CREATE_ADMINISTRATIVE_UNIT.toString());
		try {
			setGlobalParams(httpSession);
			model.addAttribute("deptUnitExists", organisationService.getDeptAdminUnits(stateCode, type));
			if(!isCenterUser){
				List<LBTypeDetails> localBodyTypes = adminDeprtmentService.getLocalBodyTypesDetails(stateCode);
				model.addAttribute("localBodyTypes", localBodyTypes);
			}
			model.addAttribute("isCenterUser", isCenterUser);
			model.addAttribute("deptAdminUnit", deptAdminUnit);
			model.addAttribute("stateCode", stateCode);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/**
	 * Changes for admin unit level draft mode by ripunj on 21-04-2015
	 */
	@RequestMapping(value = "/saveDepartmenAdmitUnits", method = RequestMethod.POST)
	public ModelAndView saveDepartmenAdmitUnits(@ModelAttribute("deptAdminUnit") DeptAdminUnit deptAdminUnit, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		
		String Message = null;
		boolean opSuccess = false;
		
		String entityType = "levelNew";
		try {
		DepartmentAdminForm departmentAdminForm = new DepartmentAdminForm();
		List<DeptAdminUnit> deptUnitExists = null;
		if (deptAdminUnit.getAdminLevelNameEng() != null)
			departmentAdminForm.setAdminLevelNameEnglish(deptAdminUnit.getAdminLevelNameEng().trim());
		if (deptAdminUnit.getAdminLevelNameLocal() != null)
			departmentAdminForm.setAdminLevelNameLocal(deptAdminUnit.getAdminLevelNameLocal().trim());
		departmentAdminForm.setParentAdminCode(deptAdminUnit.getParentAdminCode());
		departmentAdminForm.setEntityType(entityType);
		departmentAdminForm.setSlc(stateCode);
		deptAdminUnit.setParentAdminCode(departmentAdminForm.getParentAdminCode());
		departmentValidator.validateAdminUnitLevelEntity(departmentAdminForm, bindingResult);
		/* modify by sunita on 10-07-2015 */
		Integer type = Integer.parseInt(DepartmentConstent.CREATE_ADMINISTRATIVE_UNIT.toString());
		
			if (bindingResult.hasErrors()) {
				deptUnitExists = new ArrayList<DeptAdminUnit>();
				mav = new ModelAndView("view_deptAdminUnits");
				deptUnitExists = organisationService.getDeptAdminUnits(stateCode, type);
				model.addAttribute("deptUnitExists", deptUnitExists);
				if(!isCenterUser){
					List<LBTypeDetails> localBodyTypes = adminDeprtmentService.getLocalBodyTypesDetails(stateCode);
					model.addAttribute("localBodyTypes", localBodyTypes);
				}
				model.addAttribute("isCenterUser", isCenterUser);
				model.addAttribute("deptAdminUnit", deptAdminUnit);
				model.addAttribute("stateCode", stateCode);
			} else {
				mav = new ModelAndView("view_deptAdminUnits");
				deptAdminUnit.setCreatedby(userId.intValue());
				deptAdminUnit.setSlc(stateCode);
				if (deptAdminUnit.getAdminLevelNameLocal().equals(""))
					deptAdminUnit.setAdminLevelNameLocal(null);
				opSuccess = organisationService.saveDeptAdmitUnit(deptAdminUnit);
				if (opSuccess) {
					/**
					 * Added by kirandeep for getting name of level
					 */
					if (deptAdminUnit.getButtonClicked() != null && deptAdminUnit.getButtonClicked() == 'D')
						Message = "Administrative Unit Level " + departmentAdminForm.getAdminLevelNameEnglish() + " is successfully Created For Draft";
					else if (deptAdminUnit.getButtonClicked() != null && deptAdminUnit.getButtonClicked() == 'P')
						Message = "Administrative Unit Level " + departmentAdminForm.getAdminLevelNameEnglish() + " is published successfully";
					else if (deptAdminUnit.getButtonClicked() != null && deptAdminUnit.getButtonClicked() == 'R')
						Message = "Revenue Level " + departmentAdminForm.getAdminLevelNameEnglish() + " is successfully Created For Draft";
					else if (deptAdminUnit.getButtonClicked() != null && deptAdminUnit.getButtonClicked() == 'L')
						Message = "Revenue Level " + departmentAdminForm.getAdminLevelNameEnglish() + " is published successfully";
				}
				
				else
				Message = "Records Insertion Failure";
				deptAdminUnit.setAdminLevelNameEng(null);
				deptAdminUnit.setAdminLevelNameLocal(null);
				mav.addObject("msgid", Message);
				
				mav.addObject("isdeptAdminUnitSaved", true);
				setGlobalParams(session);
				model.addAttribute("deptUnitExists", organisationService.getDeptAdminUnits(stateCode, type));
				if(!isCenterUser){
					List<LBTypeDetails> localBodyTypes = adminDeprtmentService.getLocalBodyTypesDetails(stateCode);
					model.addAttribute("localBodyTypes", localBodyTypes);
				}
				model.addAttribute("isCenterUser", isCenterUser);
				model.addAttribute("deptAdminUnit", deptAdminUnit);
				model.addAttribute("stateCode", stateCode);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}

	@RequestMapping(value = "/manage_Administrative_Unit", method = RequestMethod.GET)
	public ModelAndView getAdminLevelDetails(@ModelAttribute("adminUnitCode") DepartmentAdminForm deptAdminForm,Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("manage_AdminLevel");
		try {
			setManageAdminUnitLevel(deptAdminForm,mav,model,session);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	private void setManageAdminUnitLevel(DepartmentAdminForm deptAdminForm,ModelAndView mav,Model model,HttpSession session)throws Exception{
		boolean isDisplaySearch = false;
		setGlobalParams(session);
		/* modify by sunita on 13-07-2015 */
		Integer type = Integer.parseInt(DepartmentConstent.MANAGE_ADMINISTRATIVE_UNIT.toString());
		
		List<DeptAdminUnit> deptAdminUnitLevel = new ArrayList<DeptAdminUnit>();
		deptAdminUnitLevel = organisationService.getDeptAdminUnits(stateCode, type);
		isDisplaySearch = true;
		model.addAttribute("DEPT_ADMIN_LEVEL", deptAdminUnitLevel);
		model.addAttribute("viewPage", "abc");
		model.addAttribute("isDisplaySearch", isDisplaySearch);
		deptAdminForm.setOffset(1);
		deptAdminForm.setLimit(1000);
		model.addAttribute("offsets", 0);
		model.addAttribute("limits", 1000);
		model.addAttribute("stateCode", stateCode);
		model.addAttribute("stateName", stateService.getStateNameEnglish(stateCode));
		mav.addObject("adminLevelBean", deptAdminForm);
	}

	@RequestMapping(value = "/view_AdminLevelDetail")
	public ModelAndView viewDepartmentAdminDetailList(@ModelAttribute("adminUnitCode") DepartmentAdminForm adminUnitView, HttpSession session, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("view_AdminLevelDetail");
		DepartmentAdminForm deptAdminUnit = null;
		Integer adminUnitOrEntityCode = null;
		char adminType = 'U';
		List<AdministrativeEntityCoverage> administrativeEntityCoverages = new ArrayList<AdministrativeEntityCoverage>();
		
		try {
			if (adminUnitView.getAdminUnitCode() != null) {
				adminUnitOrEntityCode = adminUnitView.getAdminUnitCode();
				adminType = 'U';
				model.addAttribute("viewType", 0);
			} else if (adminUnitView.getAdminUnitEntityCode() != null) {
				adminUnitOrEntityCode = adminUnitView.getAdminUnitEntityCode();
				adminType = 'E';
				model.addAttribute("viewType", '1');
				administrativeEntityCoverages = organisationService.getEntityCoverageDetail(adminUnitOrEntityCode);
				model.addAttribute("entityCoverageData", administrativeEntityCoverages);
			}
			deptAdminUnit = organisationService.getDeptAdminUnitDetail(adminUnitOrEntityCode, adminType);
			model.addAttribute("adminUnitObject", deptAdminUnit);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/view_modifyDeptAdmitUnit")
	public ModelAndView modifyDeptAdminUnit(@ModelAttribute("adminUnitCode") DepartmentAdminForm adminUnitView, HttpSession httpSession, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("modify_AdminLevel");
		
		DeptAdminUnit deptAdminUnit = new DeptAdminUnit();
		List<DeptAdminUnit> deptUnitExists = new ArrayList<DeptAdminUnit>();
		int adminUnitCode = 0;
		char adminType = 'U';
		if (adminUnitView.getAdminUnitCode() != null)
			adminUnitCode = adminUnitView.getAdminUnitCode();
		else
			adminUnitCode = Integer.parseInt(request.getParameter("unitCode"));
		DepartmentAdminForm deptAdminUnitForm = null;
		int type = 1;
		boolean childExist = false;
		int parentCode = 0;
		try {

			deptUnitExists = organisationService.getDeptAdminUnits(stateCode, type);
			if(!isCenterUser){
				List<LBTypeDetails> localBodyTypes = adminDeprtmentService.getLocalBodyTypesDetails(stateCode);
				model.addAttribute("localBodyTypes", localBodyTypes);
			}
			model.addAttribute("isCenterUser", isCenterUser);
			deptAdminUnitForm = organisationService.getDeptAdminUnitDetail(adminUnitCode, adminType);
			childExist = organisationService.adminUnitOradminChildExist(stateCode, null, adminUnitCode, 'C', parentCode);
			deptAdminUnitForm.setChildExist(childExist);
			model.addAttribute("deptUnitExists", deptUnitExists);
			model.addAttribute("deptAdminUnitForm", deptAdminUnitForm);
			model.addAttribute("deptAdminUnit", deptAdminUnit);
			model.addAttribute("stateCode", stateCode);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/modifyDeptAdmitUnit", method = RequestMethod.POST)
	public ModelAndView modifyDeptAdmitUnit(@ModelAttribute("deptAdminUnit") DeptAdminUnit deptAdminUnit, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("modify_AdminLevel");
		
		DepartmentAdminForm departmentAdminForm = new DepartmentAdminForm();
		char parentModify = 'N';
		String entityType = "levelMod";
		List<DeptAdminUnit> deptUnitExists = null;
		DepartmentAdminForm deptAdminUnitForm = null;
		int type = 1;
		int adminUnitCode = deptAdminUnit.getAdminUnitCode();
		char adminType = 'U';
		departmentAdminForm.setSlc(stateCode);
		departmentAdminForm.setAdminLevelNameEnglish(deptAdminUnit.getAdminLevelNameEng());
		departmentAdminForm.setAdminLevelNameLocal(deptAdminUnit.getAdminLevelNameLocal());
		departmentAdminForm.setAdministrationUnit(deptAdminUnit.getAdminUnitCode());
		departmentAdminForm.setEntityType(entityType);
		departmentAdminForm.setOverlapUnit(deptAdminUnit.getOverlapUnit());
		departmentValidator.validateAdminUnitLevelEntity(departmentAdminForm, bindingResult);
		if (deptAdminUnit.getParentAdminCode() != null) {
			parentModify = 'Y';
			departmentAdminForm.setParentAdminCode(deptAdminUnit.getParentAdminCode());
			departmentAdminForm.setParentCategory(deptAdminUnit.getParentCategory());
		}
		String Message = null;
		boolean opSuccess = false;
		mav=new ModelAndView("manage_AdminLevel");
		try {
			if (bindingResult.hasErrors()) {
				
				deptUnitExists = new ArrayList<DeptAdminUnit>();
				deptUnitExists = organisationService.getDeptAdminUnits(stateCode, type);
				deptAdminUnitForm = organisationService.getDeptAdminUnitDetail(adminUnitCode, adminType);
				model.addAttribute("deptUnitExists", deptUnitExists);
				model.addAttribute("deptAdminUnitForm", deptAdminUnitForm);
				model.addAttribute("deptAdminUnit", deptAdminUnit);
				model.addAttribute("stateCode", stateCode);
			} else {
				opSuccess =organisationService.modifyDepartmentAdminDetail(departmentAdminForm, parentModify);
				if (opSuccess)
					Message = "Record Updated Successfully";
				else
					Message = "Records Insertion Failure";
				
				mav.addObject("msgid", Message);
				mav.addObject("isdeptAdminUnitupdated", true);
				
				departmentAdminForm.setAdminEntityNameEnglish(null);
				departmentAdminForm.setAdminLevelNameLocal(null);
				setManageAdminUnitLevel(departmentAdminForm,mav,model,session);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;

	}

	@RequestMapping(value = "/deleteDeptAdmitUnit")
	public ModelAndView deleteDeptAdmitUnit(@ModelAttribute("adminLevelBean") DepartmentAdminForm deptAdminForm, HttpSession httpSession, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		Integer adminUnitCode = Integer.parseInt(request.getParameter("unitCode"));
		Integer typeEntity = Integer.parseInt(request.getParameter("entityType"));
		boolean success = false;
		String Message = null;
		try {
			// Changes by ripunj on 22-04-2015 as update isactive flag instead
			// of deleting level and entities and their coverages.
			success = organisationService.deleteAdminUnitEntitysetFlagFalse(stateCode, adminUnitCode, typeEntity);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		if (success)
			Message = "Record Deleted Successfully";
		else
			Message = "Records Deletion Failure";
		mav = new ModelAndView("success");
		mav.addObject("msgid", Message);
		return mav;

	}

	@RequestMapping(value = "/newDeptAdminEntity", method = RequestMethod.GET)
	public ModelAndView deptAdmitEntity(HttpSession httpSession, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("view_deptAdminEntity");
		try{
			DepartmentAdminForm departmentAdminForm = new DepartmentAdminForm();
			model.addAttribute("departmentAdminForm", departmentAdminForm);
			setCommonPropertiesAdminEntity(httpSession,model);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	private void setCommonPropertiesAdminEntity(HttpSession httpSession,Model model)throws Exception{
		
		List<DeptAdminUnit> parentAdminEntity = new ArrayList<DeptAdminUnit>();
		setGlobalParams(httpSession);
		Integer districtId =districtCode;
		
		List<District> districtList = null;
		List<LocalbodyforStateWise> localBodyTypelist = null;
		List<State> stateList = null;
		int type = 2;
		if (stateCode == 0) {
			parentAdminEntity = organisationService.getDeptAdminUnits(stateCode, type);
			stateList = stateService.getStateListWithOperationState();
			model.addAttribute("stateList", stateList);
		} else {
			if (districtId == 0){
				if(httpSession.getAttribute("isUserEntityDefiner")!=null && ((boolean)httpSession.getAttribute("isUserEntityDefiner"))){
					Map<String,Object> orgUnitwiseDeptDetails=organisationService.getOrgUnitswiseDeptDetails(assignUnit, Boolean.FALSE);
					parentAdminEntity=(List<DeptAdminUnit>)orgUnitwiseDeptDetails.get("deptwiseAdminUnitLevelList");
					//parentAdminEntity=organisationService.getDeptwiseAdminUnitLevel(assignUnit);
				}else{
					parentAdminEntity = organisationService.getDeptAdminUnits(stateCode, type);
				}
				
			}else{
				parentAdminEntity = organisationService.getAdminLevelForDistrictUser(stateCode);
			}
				
			districtList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
			localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);
		}
		model.addAttribute("parentAdminEntity", parentAdminEntity);
		
		model.addAttribute("stateCode", stateCode);
		model.addAttribute("districtCode", districtId);
		model.addAttribute("distList", districtList);
		model.addAttribute("uLBTypeList", localBodyTypelist);
	}

	@RequestMapping(value = "/saveAdminEntityUnit", method = RequestMethod.POST)
	public ModelAndView saveorUpdateDepartmenAdmitUnits(@ModelAttribute("departmentAdminForm") DepartmentAdminForm departmentAdminForm, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session) throws Exception {
		Long userid = null;
		Integer userId = null;
		ModelAndView mav = null;
		
		String Message = null;
		/*
		 * Added by ripunj on 28-4-2015 for modifying admin unit entity for
		 * district User
		 */
		Integer districtId =districtCode;
		
		/* added by pooja on 05-08-2015 */
		if (districtId == 0) {
			districtId = organisationService.getdistrictByadminUnitCodeAndParentEntityCode(departmentAdminForm.getAdminUnitLevelCode(), departmentAdminForm.getParentAdminUnitEntityCode());
		}
		boolean successResult = false;
		String entityType = "entityNew";
		if (departmentAdminForm.getAdminEntityNameEnglish() != null)
			departmentAdminForm.setAdminLevelNameEnglish(departmentAdminForm.getAdminEntityNameEnglish().trim());
		departmentAdminForm.setEntityType(entityType);
		departmentAdminForm.setSlc(stateCode);
		departmentAdminForm.setDistrictCode(districtId);
		departmentValidator.validateAdminUnitLevelEntity(departmentAdminForm, bindingResult);
		List<DeptAdminUnit> parentAdminEntity = null;
		List<DeptAdminUnitEntity> deptAdminUnitEntities = null;
		int type = 2;
		try {
			if (bindingResult.hasErrors()) {
				mav = new ModelAndView("view_deptAdminEntity");
				parentAdminEntity = new ArrayList<DeptAdminUnit>();
				deptAdminUnitEntities = new ArrayList<DeptAdminUnitEntity>();
				parentAdminEntity = organisationService.getDeptAdminUnits(stateCode, type);
				deptAdminUnitEntities = organisationService.getDeptAdminEntity(stateCode);
				model.addAttribute("parentAdminEntity", parentAdminEntity);
				model.addAttribute("deptAdminUnitEntities", deptAdminUnitEntities);
				model.addAttribute("departmentAdminForm", departmentAdminForm);
				model.addAttribute("stateCode", stateCode);
			} else {
				mav = new ModelAndView("view_deptAdminEntity");
							
				departmentAdminForm.setCreatedby(userId);
				departmentAdminForm.setSlc(stateCode);
				successResult = organisationService.saveDeptAdmitEntity(departmentAdminForm);
				if (successResult) {
					/**
					 * Added by kirandeep for getting name of Entity
					 */
					if (departmentAdminForm.getPublishOrSave().equals('D')) {
						Message = "Administrative Unit entity " + departmentAdminForm.getAdminLevelNameEnglish() + " is successfully saved in draft mode";
					} else {
						Message = "Administrative Unit entity " + departmentAdminForm.getAdminLevelNameEnglish() + " is successfully saved ";
					}

				} else {
					Message = "Records Insertion Failure";
				}
				model.addAttribute("isDeptAdminEntitySaved",Boolean.TRUE);
				mav.addObject("msgid", Message);
				departmentAdminForm.setAdminEntityNameEnglish(null);
				departmentAdminForm.setAdminEntityNameLocal(null);
				setCommonPropertiesAdminEntity(session,model);
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;

	}

	@RequestMapping(value = "/manage_AdminEntiy_byParent", method = RequestMethod.POST)
	public ModelAndView getAdminEntityDetailsbyLevel(@ModelAttribute("adminLevelBean") DepartmentAdminForm AdminForm, Model model, HttpServletRequest request, HttpSession session) {
		DepartmentAdminForm deptAdminForm = new DepartmentAdminForm();
		ModelAndView mav = new ModelAndView("manage_AdminEntity");
		try {
			
			setCommanDataManageEntity(AdminForm,model,request,session);
			Integer parentCode = AdminForm.getParentAdminUnitEntityCode();
			if (parentCode == 0)
				model.addAttribute("message", "selected Administrative unit Level");
			else
				model.addAttribute("message", "selected parent level Administrative unit Entity");
			mav.addObject("adminLevelBean", deptAdminForm);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	private void setCommanDataManageEntity(DepartmentAdminForm AdminForm,Model model,HttpServletRequest request, HttpSession session)throws Exception{
		
		List<DeptAdminUnit> unitLevels = new ArrayList<DeptAdminUnit>();
		boolean isDisplaySearch = false;
		
		List<State> stateList = null;
		Integer stId = 0;
		
		/*
		 * Added by ripunj on 28-4-2015 for modifying admin unit entity for
		 * district User
		 */
		Integer districtId = districtCode;
		
		Integer unitLevelCode = AdminForm.getAdminUnitLevelCode();
		Integer parentCode = AdminForm.getParentAdminUnitEntityCode();
		if (parentCode == null)
			parentCode = Integer.valueOf(0);
		List<DeptAdminUnitEntity> deptAdminUnitEntity = new ArrayList<DeptAdminUnitEntity>();
		if (districtId == 0)
			deptAdminUnitEntity = organisationService.getUnityEntityByParentLevel(stateCode, unitLevelCode, parentCode);
		else
			deptAdminUnitEntity = organisationService.getUnityEntityByParentLevelForDistrictUser(districtId, unitLevelCode, parentCode);
		if (deptAdminUnitEntity.size() == 0) {
			if (stateCode == 0) {

				stateList = stateService.getStateListWithOperationState();
				if (request.getParameter("stateId") != null) {
					stId = Integer.parseInt(request.getParameter("stateId"));
				}
				model.addAttribute("stateList", stateList);
				model.addAttribute("stId", stId);
			}
			if (districtId == 0)
				unitLevels = organisationService.getDeptAdminUnits(stateCode, 2);
			else
				unitLevels = organisationService.getAdminLevelForDistrictUser(stateCode);
			model.addAttribute("parentAdminEntity", unitLevels);
			model.addAttribute("viewType", 0);
			// if(parentCode > 0)
			// {
			if (districtId == 0) {
				if (stId != 0)
					unitLevels = organisationService.getUnitLevelNamesForLocalBody(unitLevelCode, stId);
				else
					unitLevels = organisationService.getUnitLevelNamesForLocalBody(unitLevelCode, stateCode);
			} else
				unitLevels = organisationService.getUnitLevelNamesForLocalBodyDistrictUser(unitLevelCode, districtId);
			model.addAttribute("adminEntities", unitLevels);
			model.addAttribute("parentCode", parentCode);
			// }

		} else {
			model.addAttribute("parentCategory", deptAdminUnitEntity.get(0).getParentCategory());
			model.addAttribute("viewType", 1);
		}
			
		
		isDisplaySearch = true;
		/*added by deepti on 13-05-2016*/
		Character isExistOverlap= organisationService.getOverlappingExist(unitLevelCode);
		model.addAttribute("isExistOverlap", isExistOverlap);

		model.addAttribute("DEPT_ADMIN_LEVEL", deptAdminUnitEntity);
		model.addAttribute("viewPage", "abc");
		model.addAttribute("isDisplaySearch", isDisplaySearch);
		model.addAttribute("districtCode", districtId);
	}

	@RequestMapping(value = "/manage_Administrative_Entity", method = RequestMethod.GET)
	public ModelAndView getAdminEntityDetails(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("manage_AdminEntity");
		DepartmentAdminForm deptAdminForm = new DepartmentAdminForm();
		List<DeptAdminUnit> unitLevels = new ArrayList<DeptAdminUnit>();
		int type = Integer.parseInt(DepartmentConstent.MANAGE_ADMINISTRATIVE_UNIT.toString());
		setGlobalParams(session);
		/*
		 * Added by ripunj on 28-4-2015 for modifying admin unit entity for
		 * district User
		 */
		Integer districtId = districtCode;
		
		List<State> stateList = null;
		try {
			/* Modified by Pooja on 15-10-2015 */
			if (stateCode == 0) {
				unitLevels = organisationService.getDeptAdminUnits(stateCode, type);
				stateList = stateService.getStateListWithOperationState();
				model.addAttribute("stateList", stateList);
			} else {
				if (districtId == 0){

					if(session.getAttribute("isUserEntityDefiner")!=null && ((boolean)session.getAttribute("isUserEntityDefiner"))){
						Map<String,Object> orgUnitwiseDeptDetails=organisationService.getOrgUnitswiseDeptDetails(assignUnit, Boolean.FALSE);
						unitLevels=(List<DeptAdminUnit>)orgUnitwiseDeptDetails.get("deptwiseAdminUnitLevelList");
					}else{
						unitLevels = organisationService.getDeptAdminUnits(stateCode, type);
					}		
					
				}
				else{
					unitLevels = organisationService.getAdminLevelForDistrictUser(stateCode);
				}
			}
			model.addAttribute("parentAdminEntity", unitLevels);
			mav.addObject("adminLevelBean", deptAdminForm);
			model.addAttribute("stateCode", stateCode);
			model.addAttribute("viewType", 2);
			model.addAttribute("districtCode", districtId);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/view_modifyDeptAdmitEntity")
	public ModelAndView modifyDeptAdminEntity(@ModelAttribute("adminUnitCode") DepartmentAdminForm adminUnitView, HttpSession httpSession, HttpServletRequest request, Model model) throws Exception {
		ModelAndView mav = new ModelAndView("modify_AdminEntity");
		List<State> stateList = null;
		Integer slc = 0;
		
		/*
		 * Added by ripunj on 28-4-2015 for modifying admin unit entity for
		 * district User
		 */
		Integer districtId = districtCode;
		
		DeptAdminUnitEntity deptAdminUnitEntity = new DeptAdminUnitEntity();
		List<DeptAdminUnit> unitLevels = new ArrayList<DeptAdminUnit>();
		List<DeptAdminUnit> unitEntities = new ArrayList<DeptAdminUnit>();
		int adminUnitCode = 0;
		char adminType = 'E';
		int type = 2;
		if (adminUnitView.getAdminUnitCode() != null)
			adminUnitCode = adminUnitView.getAdminUnitEntityCode();
		else
			adminUnitCode = Integer.parseInt(request.getParameter("unitCode"));
		DepartmentAdminForm deptAdminUnitForm = null;
		try {

			deptAdminUnitForm = organisationService.getDeptAdminUnitDetail(adminUnitCode, adminType);
			if (stateCode == 0) {
				stateList = stateService.getStateListWithOperationState();
				model.addAttribute("stateList", stateList);
				slc = organisationService.getStateByParentUnitEntityCode(deptAdminUnitForm.getAdminUnitLevelCode(), deptAdminUnitForm.getParentAdminCode());
			}
			if (districtId == 0)
				unitLevels = organisationService.getDeptAdminUnits(stateCode, type);
			else
				unitLevels = organisationService.getAdminLevelForDistrictUser(stateCode);
			model.addAttribute("unitLevels", unitLevels);
			model.addAttribute("deptAdminUnitForm", deptAdminUnitForm);
			model.addAttribute("deptAdminUnit", deptAdminUnitEntity);
			model.addAttribute("stateCode", stateCode);
			if (deptAdminUnitForm != null) {
				if (districtId == 0) {
					if (stateCode == 0)
						unitEntities = organisationService.getUnitLevelNamesForLocalBody(deptAdminUnitForm.getAdminUnitLevelCode(), slc);
					else
						unitEntities = organisationService.getUnitLevelNamesForLocalBody(deptAdminUnitForm.getAdminUnitLevelCode(), stateCode);
				}

				else {
					unitEntities = organisationService.getUnitLevelNamesForLocalBodyDistrictUser(deptAdminUnitForm.getAdminUnitLevelCode(), districtId);
				}
			}
			List<Object> adminEntityOrg = organisationService.getAdminEntityOrgUnits(deptAdminUnitForm.getAdminUnitCode());
			mav.addObject("adminEntityOrg", adminEntityOrg);
			if(adminEntityOrg!=null && !adminEntityOrg.isEmpty()){
				mav.addObject("orgUnitsExist",true);
			}
			else{
				mav.addObject("orgUnitsExist",false);
			}
			

			model.addAttribute("unitEntities", unitEntities);
			// variable added for the hiding the view org units
			model.addAttribute("viewOrg", deptAdminUnitForm.isIsactive());
			model.addAttribute("districtCode", districtId);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/modifyDeptAdmitEntity", method = RequestMethod.POST)
	public ModelAndView modifyDeptAdmitEntity(@ModelAttribute("deptAdminUnit") DeptAdminUnitEntity deptAdminEntity, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = null;
		deptAdminEntity.setSlc(stateCode);
		// Changes for district user by ripunj on 28-04-2015
		Integer districtId = districtCode;
		
		if (deptAdminEntity.getAdminUnitLevelCode() == null) {
			deptAdminEntity.setAdminUnitLevelCode(Integer.parseInt((String) request.getParameter("administationUnit")));
		}
		if (deptAdminEntity.getParentAdminUnitEntityCode() == null) {
			deptAdminEntity.setParentAdminUnitEntityCode(Integer.parseInt((String) request.getParameter("parentAdminCode")));
		}
		if (districtId == 0) {
			districtId = organisationService.getdistrictByadminUnitCodeAndParentEntityCode(deptAdminEntity.getAdminUnitLevelCode(), deptAdminEntity.getParentAdminUnitEntityCode());
		}
		DepartmentAdminForm departmentAdminForm = new DepartmentAdminForm();
		char parentModify = 'N';
		char eType = 'E';
		int type = 2;
		String Message = null;
		String entityType = "entityMod";
		int adminUnitCode = 0;
		departmentAdminForm.setSlc(stateCode);
		departmentAdminForm.setAdminLevelNameEnglish(deptAdminEntity.getAdminEntityNameEnglish());
		departmentAdminForm.setAdminLevelNameLocal(deptAdminEntity.getAdminEntityNameLocal());
		departmentAdminForm.setAdministrationUnit(deptAdminEntity.getAdminUnitEntityCode());
		departmentAdminForm.setAdminUnitLevelCode(deptAdminEntity.getAdminUnitLevelCode());
		departmentAdminForm.setEntityType(entityType);
		deptAdminEntity.setDistrictCode(districtId);
		departmentAdminForm.setDistrictCode(districtId);
		departmentValidator.validateAdminUnitLevelEntity(departmentAdminForm, bindingResult);
		List<DeptAdminUnitEntity> deptAdminUnitEntities = null;
		List<DeptAdminUnit> unitLevels = null;
		List<Object> adminEntityOrg = null;
		String entityUpdateScript = null;
		String orgUpdateScript = "";
		String updateScript = null;
		String mailIds = "p_laxmi@nic.in";
		if (deptAdminEntity.getParentAdminUnitEntityCode() != null) {
			parentModify = 'Y';
			Integer parentAdminUnitEntityCode=deptAdminEntity.getParentAdminUnitEntityCode();
			departmentAdminForm.setParentAdminCode(parentAdminUnitEntityCode);
			departmentAdminForm.setParentAdminUnitEntityCode(parentAdminUnitEntityCode);
		}
		try {
			if (bindingResult.hasErrors()) {
				mav = new ModelAndView("modify_AdminEntity");
				deptAdminUnitEntities = new ArrayList<DeptAdminUnitEntity>();
				unitLevels = new ArrayList<DeptAdminUnit>();
				if (districtId == 0)
					deptAdminUnitEntities = organisationService.getDeptAdminEntity(stateCode);
				else
					deptAdminUnitEntities = organisationService.getDeptAdminEntityForDistrict(stateCode, districtId);
				departmentAdminForm = organisationService.getDeptAdminUnitDetail(adminUnitCode, eType);
				if (districtId == 0)
					unitLevels = organisationService.getDeptAdminUnits(stateCode, type);
				else
					unitLevels = organisationService.getAdminLevelForDistrictUser(stateCode);

				model.addAttribute("unitLevels", unitLevels);
				model.addAttribute("deptAdminUnitEntities", deptAdminUnitEntities);
				model.addAttribute("deptAdminUnitForm", departmentAdminForm);
				model.addAttribute("deptAdminUnit", deptAdminEntity);
				model.addAttribute("stateCode", stateCode);
				model.addAttribute("districtCode", districtId);
			} else {
				 entityUpdateScript = organisationService.modifyDepartmentAdminEntity(departmentAdminForm, parentModify);
				if (null != entityUpdateScript) {
					//orgUpdateScript = organisationService.updateOrgUnitNames(departmentAdminForm.getAdministrationUnit(), deptAdminEntity.getAdminEntityNameEnglish());
					adminEntityOrg = organisationService.getAdminEntityOrgUnits(departmentAdminForm.getAdministrationUnit());
					if (orgUpdateScript.length() > 0)
						updateScript = "Update DB Scripts\n\nAdmin Entity Scripts :- " + entityUpdateScript + "\n\nOrg Units Scripts :- " + orgUpdateScript;
					else
						updateScript = "Update DB Scripts\n\nAdmin Entity Scripts :- " + entityUpdateScript + "\n\nOrg Units Scripts :- No Records Updated for Org Units.";
					try{
					MailService mailService = new MailService();
					mailService.sendMail(mailIds, "Update Scripts for Admin Entity & Org Units", updateScript);
					}catch(Exception e){
						log.info("mail server not present in this server");
					}
					Message = "Record Updated Successfully for Admin Entity";
				} else {
					Message = "Records Insertion Failure";
				}
				
				setCommanDataManageEntity(departmentAdminForm,model,request,session);
				model.addAttribute("adminLevelBean",departmentAdminForm);
				model.addAttribute("isdeptAdminEntityUpdate",Boolean.TRUE);
				
				mav = new ModelAndView("manage_AdminEntity");
				mav.addObject("adminEntityOrg", adminEntityOrg);
				mav.addObject("msgid", Message);
				
				
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;

	}

	@RequestMapping(value = "/modifyAdminEntityCoverage")
	public ModelAndView modifyAdminEntityCoveragey(@ModelAttribute("adminUnitCode") DepartmentAdminForm adminUnitView, HttpSession httpSession, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("modify_coverage_AdminEntity");
		
		List<District> districtList = null;
		
		Integer adminEntityCode = null;
		DepartmentAdminForm departmentAdminForm = null;
		List<AdministrativeEntityCoverage> administrativeEntityCoverages = null;
		char adminType = 'E';
		String coverageEntityTypeList = "";
		List<AdministrativeEntityCoverage> entityCoverageDetailsForLB = new ArrayList<AdministrativeEntityCoverage>();
		List<AdministrativeEntityCoverage> entityCoverageDetailsForWard = new ArrayList<AdministrativeEntityCoverage>();
		List<AdministrativeEntityCoverage> entityCoverageDetailsForDist = new ArrayList<AdministrativeEntityCoverage>();
		List<AdministrativeEntityCoverage> entityCoverageDetailsForSubDist = new ArrayList<AdministrativeEntityCoverage>();
		List<AdministrativeEntityCoverage> entityCoverageDetailsForVillage = new ArrayList<AdministrativeEntityCoverage>();
		List<AdministrativeEntityCoverage> entityCoverageDetailsForGp = new ArrayList<AdministrativeEntityCoverage>();
		List<AdministrativeEntityCoverage> entityCoverageDetailsForGpVillage = new ArrayList<AdministrativeEntityCoverage>();
		List<AdministrativeEntityCoverage> entityCoverageDetailsForState = new ArrayList<AdministrativeEntityCoverage>();
		List<State> stateList = null;
		try {
			/*added by deepti on 13-05-2016(overlappValue)*/
			Character overlappValue=request.getParameter("overlappValue").charAt(0);
			adminEntityCode = adminUnitView.getAdminUnitEntityCode();
			if (stateCode == 0) {
				stateList = stateService.getStateListWithOperationState();
				model.addAttribute("stateList", stateList);
			}
			districtList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
			
			Object objectUrbanList[]=localGovtBodyService.getUrbanListbyAdminEntity(adminEntityCode,stateCode,"U");
			if(objectUrbanList!=null){
				model.addAttribute("uLBTypeList",(List<LocalbodyforStateWise>)objectUrbanList[0]);
				model.addAttribute("uLBTypeListCoverage",(List<LocalbodyforStateWise>)objectUrbanList[1]);
			}
			
			
			
			//localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);
			departmentAdminForm = organisationService.getDeptAdminUnitDetail(adminEntityCode, adminType);
			if (departmentAdminForm == null)
				departmentAdminForm = new DepartmentAdminForm();
			administrativeEntityCoverages = organisationService.getEntityCoverageDetail(adminEntityCode);
			/* modified by pooja on 07-08-2015 */

			if (administrativeEntityCoverages != null) {
				for (int i = 0; i < administrativeEntityCoverages.size(); i++) {
					/*if (administrativeEntityCoverages.get(i).getEntiyType() == 'G'){
						
					}*/
					if ((administrativeEntityCoverages.get(i).getEntiyType() == 'G') && (administrativeEntityCoverages.get(i).getLbCodeForWard() == null))
						entityCoverageDetailsForLB.add(administrativeEntityCoverages.get(i));
					if (administrativeEntityCoverages.get(i).getEntiyType() == 'W')
						entityCoverageDetailsForWard.add(administrativeEntityCoverages.get(i));
					if (administrativeEntityCoverages.get(i).getEntiyType() == 'D')
						entityCoverageDetailsForDist.add(administrativeEntityCoverages.get(i));
					if (administrativeEntityCoverages.get(i).getEntiyType() == 'T')
						entityCoverageDetailsForSubDist.add(administrativeEntityCoverages.get(i));
					if (administrativeEntityCoverages.get(i).getEntiyType() == 'V' && (administrativeEntityCoverages.get(i).getLbCodeForWard() == null))
						entityCoverageDetailsForVillage.add(administrativeEntityCoverages.get(i));
					if ((administrativeEntityCoverages.get(i).getEntiyType() == 'G') && (administrativeEntityCoverages.get(i).getLbCodeForWard() != null))
						entityCoverageDetailsForGp.add(administrativeEntityCoverages.get(i));
					if (administrativeEntityCoverages.get(i).getEntiyType() == 'V' && (administrativeEntityCoverages.get(i).getLbCodeForWard() != null))
						entityCoverageDetailsForGpVillage.add(administrativeEntityCoverages.get(i));
					if (administrativeEntityCoverages.get(i).getEntiyType() == 'S')
						entityCoverageDetailsForState.add(administrativeEntityCoverages.get(i));
					coverageEntityTypeList = coverageEntityTypeList + administrativeEntityCoverages.get(i).getEntiyType() + ":" + administrativeEntityCoverages.get(i).getLbCodeForWard() + ",";
				}
			}
			if (!"".equals(coverageEntityTypeList))
				coverageEntityTypeList = coverageEntityTypeList.substring(0, coverageEntityTypeList.length() - 1);
			model.addAttribute("coverageEntityTypeList", coverageEntityTypeList);
			model.addAttribute("departmentAdminForm", departmentAdminForm);
			model.addAttribute("stateCode", stateCode);
			model.addAttribute("distList", districtList);
			//model.addAttribute("uLBTypeList", localBodyTypelist);
			model.addAttribute("entityCoverageData", administrativeEntityCoverages);
			model.addAttribute("entityCoverageDetailsForLB", entityCoverageDetailsForLB);
			model.addAttribute("entityCoverageDetailsForWard", entityCoverageDetailsForWard);
			model.addAttribute("entityCoverageDetailsForDist", entityCoverageDetailsForDist);
			model.addAttribute("entityCoverageDetailsForSubDist", entityCoverageDetailsForSubDist);
			model.addAttribute("entityCoverageDetailsForVillage", entityCoverageDetailsForVillage);
			model.addAttribute("entityCoverageDetailsForGp", entityCoverageDetailsForGp);
			model.addAttribute("entityCoverageDetailsForGpVillage", entityCoverageDetailsForGpVillage);
			model.addAttribute("entityCoverageDetailsForState", entityCoverageDetailsForState);
			model.addAttribute("isExistOverlap",overlappValue);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/saveModifyCoverage", method = RequestMethod.POST)
	public ModelAndView saveModifyCoverage(@ModelAttribute("departmentAdminForm") DepartmentAdminForm departmentAdminForm, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		
		String Message = null;
		boolean successResult = false;
		departmentAdminForm.setSlc(stateCode);
		try {
			mav = new ModelAndView("manage_AdminEntity");
			departmentAdminForm.setSlc(stateCode);
			successResult = organisationService.modifyEntityCoverage(departmentAdminForm);
			if (successResult) {
				Message = "Record Updated Successfully";
			} else {
				Message = "Records Updation Failure";
			}
			mav.addObject("msgid", Message);
			setCommanDataManageEntity(departmentAdminForm,model,request,session);
			model.addAttribute("adminLevelBean",departmentAdminForm);
			model.addAttribute("isdeptAdminEntityUpdate",Boolean.TRUE);
			
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;

	}

	@RequestMapping(value = "/setParentOrgUnits", method = RequestMethod.GET)
	public ModelAndView getOrgUnit(HttpServletRequest request, HttpSession httpSession, Model model) {
		ModelAndView mav = null;
		setGlobalParams(httpSession);
		try{
			setCommanPropertiesParentOrgUnit(model, httpSession);
		/*if(httpSession.getAttribute("isUserEntityDefiner")!=null && ((boolean)httpSession.getAttribute("isUserEntityDefiner"))==true) {
			Map<String,Object> orgUnitwiseDeptDetails=organisationService.getOrgUnitswiseDeptDetails(assignUnit, Boolean.TRUE);
			model.addAttribute("UserEntityDefinerOrg",(List<Organization>)orgUnitwiseDeptDetails.get("UserEntityDefinerOrg"));
			model.addAttribute("deptwiseAdminUnitLevelList",(List<DeptAdminUnit>)orgUnitwiseDeptDetails.get("deptwiseAdminUnitLevelList"));
		}*/
		if (stateCode == 0) {
			mav = new ModelAndView("redirect:login.htm");
		} else {
			mav = new ModelAndView("setparentOrgUnit");
			model.addAttribute("orgUnitForm", new OrganizationTypeForm());
			model.addAttribute("stateCode", stateCode);
		}
		
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
  private void setCommanPropertiesParentOrgUnit(Model model,HttpSession httpSession)throws Exception{
	  if(httpSession.getAttribute("isUserEntityDefiner")!=null && ((boolean)httpSession.getAttribute("isUserEntityDefiner"))==true) {
			Map<String,Object> orgUnitwiseDeptDetails=organisationService.getOrgUnitswiseDeptDetails(assignUnit, Boolean.TRUE);
			model.addAttribute("UserEntityDefinerOrg",(List<Organization>)orgUnitwiseDeptDetails.get("UserEntityDefinerOrg"));
			model.addAttribute("deptwiseAdminUnitLevelList",(List<DeptAdminUnit>)orgUnitwiseDeptDetails.get("deptwiseAdminUnitLevelList"));
		}
  }

	
  
  @RequestMapping(value = "/setOrgParentChilds", method = RequestMethod.POST)
	public ModelAndView setOrgParentChilds(@ModelAttribute("orgUnitForm") OrganizationTypeForm organizationTypeForm, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		String message = null;
		boolean successResult = false;
		try {
			mav = new ModelAndView("success");
			successResult = organisationService.updateOrgParentChildUnits(organizationTypeForm.getParentOrgCode(), organizationTypeForm.getChildOrgCode());
			if (successResult) {
				message = "Record Updated Successfully";
			} else {
				message = "Records Updation Failure";
			}
			mav = new ModelAndView("setparentOrgUnit");
			setCommanPropertiesParentOrgUnit(model, session);
			mav.addObject("msgid", message);
			mav.addObject("isParentOrgUnitSaved", Boolean.TRUE);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;

	}

	// worked by Chandra closed
	/**
	 * Add by ripunj for admin unit level draft mode on 22-04-2015
	 */
	@RequestMapping(value = "/manage_draft_Administrative_Unit", method = RequestMethod.GET)
	public ModelAndView getDraftAdminLevelDetails(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("manage_AdminLevelDraft");
		try {
			DepartmentAdminForm deptAdminForm = new DepartmentAdminForm();
			boolean isDisplaySearch = false;
			
			int type = 2;
			setGlobalParams(session);
			Character isPublish = 'D';
			List<DeptAdminUnit> deptAdminUnitLevel = new ArrayList<DeptAdminUnit>();
			
			if(session.getAttribute("isUserEntityDefiner")!=null && ((boolean)session.getAttribute("isUserEntityDefiner"))){
				Map<String,Object> orgUnitwiseDeptDetails=organisationService.getOrgUnitswiseDeptDetails(assignUnit, Boolean.FALSE);
				deptAdminUnitLevel =(List<DeptAdminUnit>)orgUnitwiseDeptDetails.get("deptwiseAdminUnitLevelList");
			}else{
				deptAdminUnitLevel = organisationService.getDraftDeptAdminUnits(stateCode, type, isPublish);
			}	
			
			
			isDisplaySearch = true;
			model.addAttribute("DEPT_ADMIN_LEVEL", deptAdminUnitLevel);
			model.addAttribute("viewPage", "abc");
			model.addAttribute("isDisplaySearch", isDisplaySearch);
			deptAdminForm.setOffset(1);
			deptAdminForm.setLimit(1000);
			model.addAttribute("offsets", 0);
			model.addAttribute("limits", 1000);
			model.addAttribute("stateCode", stateCode);
			model.addAttribute("stateName", stateService.getStateNameEnglish(stateCode));
			mav.addObject("adminLevelBean", deptAdminForm);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/**
	 * Add by ripunj to view admin unit level draft mode on 22-04-2015
	 */
	@RequestMapping(value = "/view_modifyDeptAdmitUnitDraft")
	public ModelAndView modifyDeptAdminUnitDraft(@ModelAttribute("adminUnitCode") DepartmentAdminForm adminUnitView, HttpSession httpSession, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("modify_AdminLevelDraft");
	
		DeptAdminUnit deptAdminUnit = new DeptAdminUnit();
		List<DeptAdminUnit> deptUnitExists = new ArrayList<DeptAdminUnit>();
		int adminUnitCode = 0;
		char adminType = 'U';
		if (adminUnitView.getAdminUnitCode() != null)
			adminUnitCode = adminUnitView.getAdminUnitCode();
		else
			adminUnitCode = Integer.parseInt(request.getParameter("unitCode"));
		DepartmentAdminForm deptAdminUnitForm = null;
		int type = 1;
		try {

			deptUnitExists = organisationService.getDeptAdminUnits(stateCode, type);
			deptAdminUnitForm = organisationService.getDeptAdminUnitDetail(adminUnitCode, adminType);
			model.addAttribute("deptUnitExists", deptUnitExists);
			model.addAttribute("deptAdminUnitForm", deptAdminUnitForm);
			model.addAttribute("deptAdminUnit", deptAdminUnit);
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/**
	 * Add by ripunj to publish admin unit level draft mode on 22-04-2015
	 */
	@RequestMapping(value = "/publish_AdminLevelDetailDraft", method = RequestMethod.POST)
	public ModelAndView publishDraftDepartmenAdmitUnits(@ModelAttribute("deptAdminUnit") DeptAdminUnit deptAdminUnit, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		
		String Message = null;
		boolean opSuccess = false;
		Integer adminUnitCode = 0;
		List<DeptAdminUnit> deptUnitExists = null;
		char adminType = 'U';
		
		if (deptAdminUnit.getAdminUnitCode() != null)
			adminUnitCode = deptAdminUnit.getAdminUnitCode();
		else
			adminUnitCode = Integer.parseInt(request.getParameter("unitCode"));
		DepartmentAdminForm deptAdminUnitForm = null;
		int type = 1;

		try {
			if (bindingResult.hasErrors()) {
				deptUnitExists = new ArrayList<DeptAdminUnit>();
				mav = new ModelAndView("manage_draft_Administrative_Unit");
				deptUnitExists = organisationService.getDeptAdminUnits(stateCode, type);
				model.addAttribute("deptUnitExists", deptUnitExists);
				model.addAttribute("deptAdminUnit", deptAdminUnit);
				model.addAttribute("stateCode", stateCode);
			} else {
				mav = new ModelAndView("success");
				deptAdminUnitForm = organisationService.getDeptAdminUnitDetail(adminUnitCode, adminType);
				deptAdminUnit.setAdminLevelNameEng(deptAdminUnitForm.getAdminLevelNameEnglish());
				deptAdminUnit.setAdminLevelNameLocal(deptAdminUnitForm.getAdminLevelNameLocal());
				deptAdminUnit.setParentAdminCode(deptAdminUnitForm.getParentAdminCode());
				deptAdminUnit.setIsactive(true);
				deptAdminUnit.setIspublish('P');
				deptAdminUnit.setCreatedby(userId.intValue());
				deptAdminUnit.setSlc(stateCode);
				deptAdminUnit.setParentCategory(deptAdminUnitForm.getParentCategory());
				/*added by deepti on 13-05-2016*/
				deptAdminUnit.setOverlapUnit(deptAdminUnitForm.getOverlapUnit());
				opSuccess = organisationService.saveorUpdateDeptAdmitUnitDraft(deptAdminUnit);
				if (opSuccess) {
					Message = "Administrative Unit Level " + deptAdminUnitForm.getAdminLevelNameEnglish() + " is published successfully  . ";
				} else
					Message = "Draft Records Published Failure";
				mav.addObject("msgid", Message);
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
	 * Add by ripunj for modify admin unit level draft mode on 22-04-2015
	 */
	@RequestMapping(value = "/modifyDeptAdmitUnitDraft", method = RequestMethod.POST)
	public ModelAndView modifyDeptAdmitUnitDraft(@ModelAttribute("deptAdminUnit") DeptAdminUnit deptAdminUnit, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		int slc = stateCode;
		DepartmentAdminForm departmentAdminForm = new DepartmentAdminForm();
		String entityType = "levelMod";
		List<DeptAdminUnit> deptUnitExists = null;
		DepartmentAdminForm deptAdminUnitForm = null;
		int type = 1;
		int adminUnitCode = deptAdminUnit.getAdminUnitCode();
		char adminType = 'U';
		departmentAdminForm.setSlc(slc);
		departmentAdminForm.setAdminLevelNameEnglish(deptAdminUnit.getAdminLevelNameEng());
		departmentAdminForm.setAdminLevelNameLocal(deptAdminUnit.getAdminLevelNameLocal());
		departmentAdminForm.setAdministrationUnit(deptAdminUnit.getAdminUnitCode());
		departmentAdminForm.setEntityType(entityType);
		departmentValidator.validateAdminUnitLevelEntity(departmentAdminForm, bindingResult);
		if (deptAdminUnit.getParentAdminCode() != null) {
			departmentAdminForm.setParentAdminCode(deptAdminUnit.getParentAdminCode());
		}
		String Message = null;
		boolean opSuccess = false;
		try {
			deptAdminUnitForm = organisationService.getDeptAdminUnitDetail(adminUnitCode, adminType);
			if (deptAdminUnit.getButtonClicked() != null) {

			}
			if (bindingResult.hasErrors()) {
				mav = new ModelAndView("modify_AdminLevel");
				deptUnitExists = new ArrayList<DeptAdminUnit>();
				deptUnitExists = organisationService.getDeptAdminUnits(slc, type);
				model.addAttribute("deptUnitExists", deptUnitExists);
				model.addAttribute("deptAdminUnitForm", deptAdminUnitForm);
				model.addAttribute("deptAdminUnit", deptAdminUnit);
				model.addAttribute("stateCode", slc);
			} else {
				if (deptAdminUnit.getParentAdminCode() == null && deptAdminUnit.getParentAdminCode() < 0) {
					deptAdminUnit.setParentAdminCode(deptAdminUnitForm.getParentAdminCode());
				}
				if (deptAdminUnit.getButtonClicked() == 'P') {
					deptAdminUnit.setIspublish('P');
					deptAdminUnit.setIsactive(true);

				} else {
					deptAdminUnit.setIspublish('D');
					deptAdminUnit.setIsactive(false);
				}
				deptAdminUnit.setSlc(slc);
				deptAdminUnit.setAdminUnitCode(deptAdminUnitForm.getAdminUnitCode());
				opSuccess = organisationService.saveorUpdateDeptAdmitUnitDraft(deptAdminUnit);
				if (opSuccess)
					if (deptAdminUnit.getButtonClicked() != null && deptAdminUnit.getButtonClicked() == 'P')
						Message = "Administrative Unit Level " + deptAdminUnit.getAdminLevelNameEng() + " is published successfully ";
					else if (deptAdminUnit.getButtonClicked() != null && deptAdminUnit.getButtonClicked() == 'D')
						Message = "Administrative Unit Level " + deptAdminUnit.getAdminLevelNameEng() + " is successfully updated in draft mode .";
					else
						Message = "Draft Records Insertion Failure";
				mav = new ModelAndView("success");
				mav.addObject("msgid", Message);
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
	 * Add by ripunj for delete admin unit level draft mode on 22-04-2015
	 */
	@RequestMapping(value = "/deleteDeptAdmitUnitDraft")
	public ModelAndView deleteDeptAdmitUnitDraft(@ModelAttribute("adminLevelBean") DepartmentAdminForm deptAdminForm, HttpSession httpSession, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		
		Integer adminUnitCode = Integer.parseInt(request.getParameter("unitCode"));
		Integer typeEntity = Integer.parseInt(request.getParameter("entityType"));
		boolean success = false;
		String Message = null;
		try {
			success = organisationService.deleteAdminUnitLevel(stateCode, adminUnitCode, typeEntity);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		if (success)
			Message = "Draft Record Deleted Successfully";
		else
			Message = "Draft Records Deletion Failure";
		mav = new ModelAndView("success");
		mav.addObject("msgid", Message);
		return mav;

	}

	/**
	 * Code for the admin unit entity in draft starts here
	 */

	@RequestMapping(value = "/manage_Administrative_Entity_for_Draft", method = RequestMethod.GET)
	public ModelAndView getAdminEntityDetailsForDraft(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("manage_AdminEntityForDraft");
		List<State> stateList = null;
		setGlobalParams(session);
		
		/*
		 * Added by ripunj on 28-4-2015 for modifying admin unit entity for
		 * district User
		 */
		Integer districtId = districtCode;
		
		DepartmentAdminForm deptAdminForm = new DepartmentAdminForm();
		List<DeptAdminUnit> unitLevels = new ArrayList<DeptAdminUnit>();
		int type = 2;
		try {
			if (stateCode == 0) {
				unitLevels = organisationService.getDeptAdminUnits(stateCode, type);
				stateList = stateService.getStateListWithOperationState();
				model.addAttribute("stateList", stateList);
				model.addAttribute("stId", stateCode);
			} else {
				if (districtId == 0){
					if(session.getAttribute("isUserEntityDefiner")!=null && ((boolean)session.getAttribute("isUserEntityDefiner"))){
						Map<String,Object> orgUnitwiseDeptDetails=organisationService.getOrgUnitswiseDeptDetails(assignUnit, Boolean.FALSE);
						unitLevels=(List<DeptAdminUnit>)orgUnitwiseDeptDetails.get("deptwiseAdminUnitLevelList");
						//parentAdminEntity=organisationService.getDeptwiseAdminUnitLevel(assignUnit);
					}else{
						unitLevels = organisationService.getDeptAdminUnits(stateCode, type);
					}
					
				}else{
					unitLevels = organisationService.getAdminLevelForDistrictUser(stateCode);
				}
			}
			model.addAttribute("parentAdminEntity", unitLevels);
			mav.addObject("adminLevelBean", deptAdminForm);
			model.addAttribute("stateCode", stateCode);
			model.addAttribute("districtCode", districtId);
			model.addAttribute("viewType", 2);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/manage_AdminEntiy_byParentForDraft", method = RequestMethod.POST)
	public ModelAndView getAdminEntityDetailsbyLevelForDraft(@ModelAttribute("adminLevelBean") DepartmentAdminForm AdminForm, Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = new ModelAndView("manage_AdminEntityForDraft");
		try {
			/**
			 * Added by kirandeep for handling district level
			 */
			Integer districtId = districtCode;
			int type = 2;
			

			DepartmentAdminForm deptAdminForm = new DepartmentAdminForm();
			List<DeptAdminUnit> unitLevels = new ArrayList<DeptAdminUnit>();
			boolean isDisplaySearch = false;
			
			Integer stId = 0;
			List<State> stateList = null;
			
			Integer unitLevelCode = AdminForm.getAdminUnitLevelCode();
			Integer parentCode = AdminForm.getParentAdminUnitEntityCode();
			if (parentCode == null)
				parentCode = Integer.valueOf(0);
			List<DeptAdminUnitEntity> deptAdminUnitEntity = new ArrayList<DeptAdminUnitEntity>();
			deptAdminUnitEntity = organisationService.getUnityEntityByParentLevelForDraft(stateCode, unitLevelCode, parentCode);
			if (deptAdminUnitEntity.size() == 0) {
				/* Modified by pooja on 20-10-2015 */
				if (stateCode == 0) {

					stateList = stateService.getStateListWithOperationState();
					if (request.getParameter("stateId") != null) {
						stId = Integer.parseInt(request.getParameter("stateId"));
					}
					model.addAttribute("stateList", stateList);
					model.addAttribute("stId", stId);
				}
				if (districtId == 0)
					unitLevels = organisationService.getDeptAdminUnits(stateCode, type);
				else
					unitLevels = organisationService.getAdminLevelForDistrictUser(stateCode);
				model.addAttribute("parentAdminEntity", unitLevels);
				model.addAttribute("viewType", 0);
				// if(parentCode > 0)
				// {
				if (districtId == 0) {
					if (stId != 0)
						unitLevels = organisationService.getUnitLevelNamesForLocalBody(unitLevelCode, stId);
					else
						unitLevels = organisationService.getUnitLevelNamesForLocalBody(unitLevelCode, stateCode);
				} else {
					unitLevels = organisationService.getUnitLevelNamesForLocalBodyDistrictUser(unitLevelCode, districtId);
				}

				DeptAdminUnit deptAdminUnit = new DeptAdminUnit();
				for (DeptAdminUnit unitLevelsvar : unitLevels) {
					if (AdminForm.getParentAdminUnitEntityCode().equals(unitLevelsvar.getParentAdminCode())) {
						deptAdminUnit = unitLevelsvar;
					}
				}

				model.addAttribute("adminEntities", unitLevels);
				model.addAttribute("parentCode", parentCode);
				model.addAttribute("parentName", deptAdminUnit.getAdminLevelNameEng());
				// }
			} else
				model.addAttribute("viewType", 1);
			isDisplaySearch = true;
			/*added by deepti on 13-05-2016*/
			Character isExistOverlap= organisationService.getOverlappingExist(unitLevelCode);
			model.addAttribute("isExistOverlap", isExistOverlap);
			model.addAttribute("DEPT_ADMIN_LEVEL", deptAdminUnitEntity);
			model.addAttribute("viewPage", "abc");
			model.addAttribute("isDisplaySearch", isDisplaySearch);
			if (parentCode == 0)
				model.addAttribute("message", "selected Administrative unit Level");
			else
				model.addAttribute("message", "selected parent level Administrative unit Entity");
			mav.addObject("adminLevelBean", deptAdminForm);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/deleteDeptAdmitUnitForDraft")
	public ModelAndView deleteDeptAdmitUnitForDraft(@ModelAttribute("adminLevelBean") DepartmentAdminForm deptAdminForm, HttpSession httpSession, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		
		Integer adminUnitCode = Integer.parseInt(request.getParameter("unitCode"));
		Integer typeEntity = Integer.parseInt(request.getParameter("entityType"));
		boolean success = false;
		String Message = null;
		try {
			success = organisationService.deleteAdminUnitEntityForDraft(stateCode, adminUnitCode, typeEntity);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		if (success)
			Message = "Record Deleted Successfully";
		else
			Message = "Records Deletion Failure";
		mav = new ModelAndView("success");
		mav.addObject("msgid", Message);
		return mav;

	}

	@RequestMapping(value = "/modifyDeptAdmitEntityForDraft", method = RequestMethod.POST)
	public ModelAndView modifyDeptAdmitEntityForDraft(@ModelAttribute("deptAdminUnit") DeptAdminUnitEntity deptAdminEntity, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = null;
		int slc = stateCode;
		/*
		 * Added by ripunj on 28-4-2015 for modifying admin unit entity for
		 * district User
		 */
		Integer districtId = districtCode ;
		
		if (deptAdminEntity.getAdminUnitLevelCode() == null) {
			deptAdminEntity.setAdminUnitLevelCode(Integer.parseInt((String) request.getParameter("administationUnit")));
		}
		if (deptAdminEntity.getParentAdminUnitEntityCode() == null) {
			deptAdminEntity.setParentAdminUnitEntityCode(Integer.parseInt((String) request.getParameter("parentAdminCode")));
		}
		/* added by pooja on 05-08-2015 */
		if (districtId == 0) {
			districtId = organisationService.getdistrictByadminUnitCodeAndParentEntityCode(deptAdminEntity.getAdminUnitLevelCode(), deptAdminEntity.getParentAdminUnitEntityCode());
		}
		deptAdminEntity.setSlc(slc);
		deptAdminEntity.setDistrictCode(districtId);
		DepartmentAdminForm departmentAdminForm = new DepartmentAdminForm();
		char parentModify = 'N';
		char eType = 'E';
		int type = 2;
		String Message = null;
		String entityType = "entityMod";
		int adminUnitCode = 0;
		departmentAdminForm.setSlc(slc);
		departmentAdminForm.setAdminLevelNameEnglish(deptAdminEntity.getAdminEntityNameEnglish());
		departmentAdminForm.setAdminLevelNameLocal(deptAdminEntity.getAdminEntityNameLocal());
		departmentAdminForm.setAdministrationUnit(deptAdminEntity.getAdminUnitEntityCode());
		departmentAdminForm.setAdminUnitLevelCode(deptAdminEntity.getAdminUnitLevelCode());
		departmentAdminForm.setEntityType(entityType);
		departmentAdminForm.setDistrictCode(districtId);
		departmentValidator.validateAdminUnitLevelEntity(departmentAdminForm, bindingResult);
		List<DeptAdminUnitEntity> deptAdminUnitEntities = null;
		List<DeptAdminUnit> unitLevels = null;
		List<Object> adminEntityOrg = null;
		String entityUpdateScript = null;
		// String orgUpdateScript="";
		// String updateScript=null;
		// String mailIds="p_laxmi@nic.in";
		if (deptAdminEntity.getParentAdminUnitEntityCode() != null) {
			parentModify = 'Y';
			departmentAdminForm.setParentAdminCode(deptAdminEntity.getParentAdminUnitEntityCode());
		}
		try {
			if (bindingResult.hasErrors()) {
				mav = new ModelAndView("manage_AdminEntityForDraft");
				deptAdminUnitEntities = new ArrayList<DeptAdminUnitEntity>();
				unitLevels = new ArrayList<DeptAdminUnit>();
				if (districtId == 0)
					deptAdminUnitEntities = organisationService.getDeptAdminEntity(slc);
				else
					deptAdminUnitEntities = organisationService.getDeptAdminEntityForDistrict(slc, districtId);

				departmentAdminForm = organisationService.getDeptAdminUnitDetail(adminUnitCode, eType);
				if (districtId == 0)
					unitLevels = organisationService.getDeptAdminUnits(slc, type);
				else
					unitLevels = organisationService.getAdminLevelForDistrictUser(slc);
				model.addAttribute("unitLevels", unitLevels);
				model.addAttribute("deptAdminUnitEntities", deptAdminUnitEntities);
				model.addAttribute("deptAdminUnitForm", departmentAdminForm);
				model.addAttribute("deptAdminUnit", deptAdminEntity);
				model.addAttribute("stateCode", slc);
				model.addAttribute("districtCode", districtId);
			} else {
				entityUpdateScript = organisationService.modifyDepartmentAdminEntityForDraft(departmentAdminForm, parentModify);
				if (null != entityUpdateScript) {
					// orgUpdateScript=
					organisationService.updateOrgUnitNames(departmentAdminForm.getAdministrationUnit(), deptAdminEntity.getAdminEntityNameEnglish());
					adminEntityOrg = organisationService.getAdminEntityOrgUnits(departmentAdminForm.getAdministrationUnit());
					/*
					 * if(orgUpdateScript.length()>0) updateScript=
					 * "Update DB Scripts\n\nAdmin Entity Scripts :- "
					 * +entityUpdateScript+"\n\nOrg Units Scripts :- "
					 * +orgUpdateScript; else updateScript=
					 * "Update DB Scripts\n\nAdmin Entity Scripts :- "
					 * +entityUpdateScript+
					 * "\n\nOrg Units Scripts :- No Records Updated for Org Units."
					 * ;
					 */
					/**
					 * mail code commented by kirandeep creating error in
					 * staging and local Kinldy remove comment if required.
					 */
					// MailService mailService = new MailService();
					// mailService.sendMail(mailIds, "Update Scripts for Admin
					// Entity & Org Units",
					// updateScript);
					Message = "Administrative Unit Entity " + deptAdminEntity.getAdminEntityNameEnglish() + "  is successfully updated in draft mode . ";
				} else {
					Message = "Records Insertion Failure";
				}
				/**
				 * Added by kirandeep for displaying message
				 */
				mav = new ModelAndView("success");
				mav.addObject("adminEntityOrg", adminEntityOrg);
				mav.addObject("msgid", Message);

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;

	}

	@RequestMapping(value = "/view_modifyDeptAdmitEntityForDraft")
	public ModelAndView modifyDeptAdminEntityForDraft(@ModelAttribute("adminUnitCode") DepartmentAdminForm adminUnitView, HttpSession httpSession, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("modify_AdminEntityForDraft");
		List<State> stateList = null;
		Integer slc = 0;
		
		// Changes by ripunj on 28-04-2015 for district User in administrative
		// entity form
		Integer districtId = districtCode;
		
		DeptAdminUnitEntity deptAdminUnitEntity = new DeptAdminUnitEntity();
		List<DeptAdminUnit> unitLevels = new ArrayList<DeptAdminUnit>();
		List<DeptAdminUnit> unitEntities = new ArrayList<DeptAdminUnit>();
		int adminUnitCode = 0;
		char adminType = 'E';
		int type = 2;
		if (adminUnitView.getAdminUnitCode() != null)
			adminUnitCode = adminUnitView.getAdminUnitEntityCode();
		else
			adminUnitCode = Integer.parseInt(request.getParameter("unitCode"));
		DepartmentAdminForm deptAdminUnitForm = null;
		try {

			deptAdminUnitForm = organisationService.getDeptAdminUnitDetail(adminUnitCode, adminType);
			if (stateCode == 0) {
				stateList = stateService.getStateListWithOperationState();
				model.addAttribute("stateList", stateList);
				slc = organisationService.getStateByParentUnitEntityCode(deptAdminUnitForm.getAdminUnitLevelCode(), deptAdminUnitForm.getParentAdminCode());
			}
			if (districtId == 0){
				if(httpSession.getAttribute("isUserEntityDefiner")!=null && ((boolean)httpSession.getAttribute("isUserEntityDefiner"))){
					Map<String,Object> orgUnitwiseDeptDetails=organisationService.getOrgUnitswiseDeptDetails(assignUnit, Boolean.FALSE);
					unitLevels=(List<DeptAdminUnit>)orgUnitwiseDeptDetails.get("deptwiseAdminUnitLevelList");
				}else{
					unitLevels = organisationService.getDeptAdminUnits(stateCode, type);
				}		
				
			}
			else{
				unitLevels = organisationService.getAdminLevelForDistrictUser(stateCode);
			}
			model.addAttribute("unitLevels", unitLevels);
			model.addAttribute("deptAdminUnitForm", deptAdminUnitForm);
			model.addAttribute("deptAdminUnit", deptAdminUnitEntity);
			model.addAttribute("stateCode", stateCode);
			model.addAttribute("districtCode", districtId);
			if (deptAdminUnitForm != null) {
				if (districtId == 0) {
					if (stateCode == 0)
						unitEntities = organisationService.getUnitLevelNamesForLocalBody(deptAdminUnitForm.getAdminUnitLevelCode(), slc);
					else
						unitEntities = organisationService.getUnitLevelNamesForLocalBody(deptAdminUnitForm.getAdminUnitLevelCode(), stateCode);
				} else
					unitEntities = organisationService.getUnitLevelNamesForLocalBodyDistrictUser(deptAdminUnitForm.getAdminUnitLevelCode(), districtId);
			}
			/*
			 * List<Object>
			 * adminEntityOrg=organisationService.getAdminEntityOrgUnits(
			 * deptAdminUnitForm.getAdminUnitCode());
			 * mav.addObject("adminEntityOrg", adminEntityOrg);
			 */

			model.addAttribute("unitEntities", unitEntities);
			// model.addAttribute("viewOrg",deptAdminUnitForm.isIsactive());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/modifyAdminEntityCoverageForDraft")
	public ModelAndView modifyAdminEntityCoverageyForDraft(@ModelAttribute("adminUnitCode") DepartmentAdminForm adminUnitView, HttpSession httpSession, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("modify_coverage_AdminEntityForDraft");
		
		List<District> districtList = null;
		List<LocalbodyforStateWise> localBodyTypelist = null;
		Integer adminEntityCode = null;
		DepartmentAdminForm departmentAdminForm = null;
		List<AdministrativeEntityCoverage> administrativeEntityCoverages = null;
		char adminType = 'E';
		String coverageEntityTypeList = "";
		List<AdministrativeEntityCoverage> entityCoverageDetailsForLB = new ArrayList<AdministrativeEntityCoverage>();
		List<AdministrativeEntityCoverage> entityCoverageDetailsForWard = new ArrayList<AdministrativeEntityCoverage>();
		List<AdministrativeEntityCoverage> entityCoverageDetailsForDist = new ArrayList<AdministrativeEntityCoverage>();
		List<AdministrativeEntityCoverage> entityCoverageDetailsForSubDist = new ArrayList<AdministrativeEntityCoverage>();
		List<AdministrativeEntityCoverage> entityCoverageDetailsForVillage = new ArrayList<AdministrativeEntityCoverage>();
		List<AdministrativeEntityCoverage> entityCoverageDetailsForGp = new ArrayList<AdministrativeEntityCoverage>();
		List<AdministrativeEntityCoverage> entityCoverageDetailsForGpVillage = new ArrayList<AdministrativeEntityCoverage>();
		List<AdministrativeEntityCoverage> entityCoverageDetailsForState = new ArrayList<AdministrativeEntityCoverage>();
		List<State> stateList = null;

		try {
			/*added by deepti on 13-05-2016(overlappValue)*/
			Character overlappValue=request.getParameter("overlappValue").charAt(0);
			adminEntityCode = adminUnitView.getAdminUnitEntityCode();
			if (stateCode == 0) {
				stateList = stateService.getStateListWithOperationState();
				model.addAttribute("stateList", stateList);
			}
			districtList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
			localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);
			departmentAdminForm = organisationService.getDeptAdminUnitDetail(adminEntityCode, adminType);
			if (departmentAdminForm == null)
				departmentAdminForm = new DepartmentAdminForm();
			administrativeEntityCoverages = organisationService.getEntityCoverageDetailForDraft(adminEntityCode);
			/* Modified by Pooja on 14-08-2015 */
			if (administrativeEntityCoverages != null) {
				for (int i = 0; i < administrativeEntityCoverages.size(); i++) {
					if (administrativeEntityCoverages.get(i).getEntiyType() == 'G' && (administrativeEntityCoverages.get(i).getLbCodeForWard() == null))
						entityCoverageDetailsForLB.add(administrativeEntityCoverages.get(i));
					if (administrativeEntityCoverages.get(i).getEntiyType() == 'W')
						entityCoverageDetailsForWard.add(administrativeEntityCoverages.get(i));
					if (administrativeEntityCoverages.get(i).getEntiyType() == 'D')
						entityCoverageDetailsForDist.add(administrativeEntityCoverages.get(i));
					if (administrativeEntityCoverages.get(i).getEntiyType() == 'T')
						entityCoverageDetailsForSubDist.add(administrativeEntityCoverages.get(i));
					if (administrativeEntityCoverages.get(i).getEntiyType() == 'V' && (administrativeEntityCoverages.get(i).getLbCodeForWard() == null))
						entityCoverageDetailsForVillage.add(administrativeEntityCoverages.get(i));
					if ((administrativeEntityCoverages.get(i).getEntiyType() == 'G') && (administrativeEntityCoverages.get(i).getLbCodeForWard() != null))
						entityCoverageDetailsForGp.add(administrativeEntityCoverages.get(i));
					if (administrativeEntityCoverages.get(i).getEntiyType() == 'V' && (administrativeEntityCoverages.get(i).getLbCodeForWard() != null))
						entityCoverageDetailsForGpVillage.add(administrativeEntityCoverages.get(i));
					if (administrativeEntityCoverages.get(i).getEntiyType() == 'S')
						entityCoverageDetailsForState.add(administrativeEntityCoverages.get(i));
					coverageEntityTypeList = coverageEntityTypeList + administrativeEntityCoverages.get(i).getEntiyType() + ":" + administrativeEntityCoverages.get(i).getLbCodeForWard() + ",";
				}
			}
			if (!"".equals(coverageEntityTypeList))
				coverageEntityTypeList = coverageEntityTypeList.substring(0, coverageEntityTypeList.length() - 1);

			model.addAttribute("coverageEntityTypeList", coverageEntityTypeList);
			model.addAttribute("departmentAdminForm", departmentAdminForm);
			model.addAttribute("stateCode", stateCode);
			model.addAttribute("distList", districtList);
			model.addAttribute("uLBTypeList", localBodyTypelist);
			model.addAttribute("entityCoverageData", administrativeEntityCoverages);
			model.addAttribute("entityCoverageDetailsForLB", entityCoverageDetailsForLB);
			model.addAttribute("entityCoverageDetailsForWard", entityCoverageDetailsForWard);
			model.addAttribute("entityCoverageDetailsForDist", entityCoverageDetailsForDist);
			model.addAttribute("entityCoverageDetailsForSubDist", entityCoverageDetailsForSubDist);
			model.addAttribute("entityCoverageDetailsForVillage", entityCoverageDetailsForVillage);
			model.addAttribute("entityCoverageDetailsForGp", entityCoverageDetailsForGp);
			model.addAttribute("entityCoverageDetailsForGpVillage", entityCoverageDetailsForGpVillage);
			model.addAttribute("entityCoverageDetailsForState", entityCoverageDetailsForState);
			model.addAttribute("departmentAdminForm", departmentAdminForm);
			model.addAttribute("isExistOverlap",overlappValue);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/saveModifyCoverageForDraft", method = RequestMethod.POST)
	public ModelAndView saveModifyCoverageForDraft(@ModelAttribute("departmentAdminForm") DepartmentAdminForm departmentAdminForm, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		
		String Message = null;
		boolean successResult = false;
		try {
			mav = new ModelAndView("success");
			departmentAdminForm.setSlc(stateCode);
			successResult = organisationService.modifyEntityCoverageForDraft(departmentAdminForm);
			if (successResult) {
				Message = "Record Updated Successfully";
			} else {
				Message = "Records Updation Failure";
			}
			mav.addObject("msgid", Message);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;

	}
	/**
	 * Code for the admin unit entity in draft Ends here
	 */
	
	/**
	 * View affacted organization units
	 */
	
	@RequestMapping(value = "/viewAffactedOrganizationUnits")
	public ModelAndView viewAffactedOrganizationUnits(Model model, HttpServletRequest request,@RequestParam("AdminUnitCode") Integer AdminUnitCode ) {
		ModelAndView mav = new ModelAndView("view_affacted_rganization_unit");
		try {
			
			mav.addObject("adminEntityOrg", organisationService.getAdminEntityOrgUnits(AdminUnitCode));
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	/*added by Sunita on 24-11-2016*/
	@RequestMapping(value = "/newDeptAdmitUnitRevenue", method = RequestMethod.GET)
	public ModelAndView assignAdminUnitRevenue(HttpSession httpSession, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("view_deptAdminUnitsRevenue");
		DeptAdminUnit deptAdminUnit = new DeptAdminUnit();
		Integer type = Integer.parseInt(DepartmentConstent.CREATE_ADMINISTRATIVE_UNIT.toString());
		try {
			setGlobalParams(httpSession);
			model.addAttribute("deptUnitExists", organisationService.getDeptAdminUnitsRevenue(stateCode, type));
			model.addAttribute("deptAdminUnit", deptAdminUnit);
			model.addAttribute("stateCode", stateCode);
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
	 * @param Added by Anju
	 * @param on 8-1-2015
	 * @returncontroller Get for viewdepartmentforadmin
	 */
	
	@RequestMapping(value = "/viewdepartmentforadmin", method = RequestMethod.GET)
	public ModelAndView showDepartmentadminPage(HttpSession session,
			@ModelAttribute("viewDeptforadmin") OrganizationUnitForm viewDept,
			Model model, HttpServletRequest request) {
		boolean isCenterUser = false;
		ModelAndView mav = null;
		String statecode = (String) session.getAttribute("stateCode");
		if (statecode.equalsIgnoreCase("0")) {
			isCenterUser = true;
		}
		try {

			if (session.getAttribute("stateCode") == null) {
				mav = new ModelAndView("redirect:login.htm");
			}
			stateCode = Integer.parseInt((String) session
					.getAttribute("stateCode"));
			List<Organization> orgList = organisationService
					.getOrganizationDetailbystateCode(stateCode);
			model.addAttribute("isCenterUser", isCenterUser);
			model.addAttribute("localBodyType",
					localGovtBodyService.getLGTypeForGovtBody());
			model.addAttribute("stateCode", stateCode);
			model.addAttribute("orgList", orgList);
			mav = new ModelAndView("view_departmentadminList");
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
	 * 
	 * @param Added by Anju
	 * @param on 10-1-2015
	 * @returncontroller Post for viewOrganizationUnit
	 */
	@RequestMapping(value = "/viewOrganizationUnit", method = RequestMethod.POST)
	public ModelAndView getOrgByDeptCode1(
			@ModelAttribute("viewDeptforadmin") OrganizationUnitForm orggunit,
			BindingResult result, HttpServletRequest request,
			HttpSession session, Model model) {
		ModelAndView mav = null;
		try {
			boolean check = false;
			boolean isCenterUser = false;
			String statecode = (String) session.getAttribute("stateCode");
			if (statecode.equalsIgnoreCase("0")) {
				isCenterUser = true;
			}
			mav = new ModelAndView("view_departmentadminList");
			viewValidator.viewdepartmentorgunit(orggunit, result);
			List<Organization> orgList = organisationService
					.getOrganizationDetailbystateCode(stateCode);
			if (result.hasErrors()) {
				model.addAttribute("viewDeptforadmin", orggunit);
				model.addAttribute("orgList", orgList);
				model.addAttribute("isCenterUser", isCenterUser);
				return mav;
			}
			orggunit.setUserId(userId.intValue());
			check = organisationService.getUpdateMethod(orggunit);
			if (check) {
				model.addAttribute("msgid", "Updated Successfully!");
			} else {
				model.addAttribute("msgid", "Problem while updation!");
			}
			mav = new ModelAndView("success");
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
	
	

		@RequestMapping(value = "/manageDeptorOrg", method = RequestMethod.GET)
		public ModelAndView manageDeptorOrg(Model model, HttpSession session,@ModelAttribute("viewDeptforstate") MinistryForm viewDept,	HttpServletRequest request) {
			ModelAndView mav = null;
			try {
				setGlobalParams(session);
				mav = new ModelAndView("manageDeptorOrg");
				model.addAttribute("organizationTypeList", organisationService.getOrgTypeList());
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
		
	
	
	@RequestMapping(value = "/manageDeptorOrg", method = RequestMethod.POST)
	public ModelAndView manageDeptorOrgPost(HttpSession httpSession,@ModelAttribute("viewDeptforstate") MinistryForm viewDept,HttpServletRequest request, Model model) {

		ModelAndView mav = null;
		try {
			Integer orgTypeCode=viewDept.getOrgTypeCode();
			setGlobalParams(httpSession);
			if(orgTypeCode>0){
				List<Organization> orgList=organisationService.getOrganizationListbyOrgType(orgTypeCode, stateCode);
				if(orgList!=null && !orgList.isEmpty()){
					mav = new ModelAndView("manageDeptorOrg");
					model.addAttribute("orgList",orgList);
					
				}else
				{
					mav = new ModelAndView("success");
					String errorMsg = "Department/Oranization List is not available";
					model.addAttribute("msgid", errorMsg);
					
				}
			}else{
				mav = new ModelAndView("success");
				String errorMsg = "Please Select Orgnization Type";
				model.addAttribute("msgid", errorMsg);
				
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
	
	
	@RequestMapping(value = "/modifyOrganisationAtLevel")
	public ModelAndView modifyOrganisationAtLevel(@ModelAttribute("viewMinistry") MinistryForm viewDepartment, Model model, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("modifyOrganizationState");
		int orgCode = viewDepartment.getDepartmentId();
		try {
			List<Object> objectList = organisationService.getOrganizationAtLevelbyOrgCode(orgCode);
			mav.addObject("organisationAtLevels", objectList);
			OrganizationTypeForm organizationTypeForm = new OrganizationTypeForm();
			organizationTypeForm.setOrgCode(orgCode);
			mav.addObject("modifyOrganizationstate",organizationTypeForm);
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
	
	@RequestMapping(value = "/modifyOrganizationStatepost", method = RequestMethod.POST)
	public ModelAndView showmodStateifyOrgFormstatepost(@ModelAttribute("modifyOrganizationstate") OrganizationTypeForm orgTypeForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {

		// String viewDepartmentdetail = null;
		orgTypeForm.setUserId(userId.intValue());
		ModelAndView mav = new ModelAndView("modifyOrganizationState");
		String orgCodeNameAndLevel = orgTypeForm.getOrgNameAndLevel();
		
		String arrayOfCodeNameAndLevel[] = orgCodeNameAndLevel.split("_");
		orgTypeForm.setOrgLevel(arrayOfCodeNameAndLevel[3].charAt(0));
		orgTypeForm.setOrgNamech(arrayOfCodeNameAndLevel[1]);
		try {
			int orgLocatedCode = Integer.valueOf(arrayOfCodeNameAndLevel[0]);
//			String category = null;
			session.setAttribute("category", orgTypeForm.getOrgLevel());
			/*if (session.getAttribute("category") != null) {
				category = session.getAttribute("category").toString();
			}*/

			// organizationValidator.validate(orgTypeForm, result);
			
	
			departmentValidator.checkExistingOrgnizationNameforView(stateCode,CommonUtil.getCategoryLevel(orgTypeForm.getOrgLevel()) , orgTypeForm, result);
			if (result.hasErrors()) {
				mav.addObject("orgTypeForm", orgTypeForm);
				orgTypeForm.setOrgNameAndLevel(orgCodeNameAndLevel);
				List<LgdOrganizationAtLevels> organisationAtLevels = adminDeprtmentService.getOrganizationAtLevelsForDeptLBMapping(orgTypeForm.getOrgCode());
				mav.addObject("hasError",true);
				mav.addObject("organisationAtLevels", organisationAtLevels);
				return mav;
			}
			boolean flag=organisationService.orgUpdate(orgTypeForm, orgLocatedCode, session, request);
			String aMessage="Organization Modified successfully!!";
			if(!flag){
				 aMessage = "Organization updated name is duplicate!!";
			}
		
			mav = new ModelAndView("configview");
			mav.addObject("msgid", aMessage);
		} catch (Exception e) {
			e.printStackTrace();
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}
	
	
	
	
	@RequestMapping(value = "/modify_parent_Administrative_Entity_for_Draft" , method = RequestMethod.POST)
	public ModelAndView getModifyAdminEntityDetailsForDraft(@ModelAttribute("adminUnitView") DepartmentAdminForm adminUnitView, HttpSession httpSession, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("modify_parent_Administrative_Entity");
		List<State> stateList = null;
		Integer slc = 0;
		
		// Changes by ripunj on 28-04-2015 for district User in administrative
		// entity form
		Integer districtId = districtCode;
		
		DeptAdminUnitEntity deptAdminUnitEntity = new DeptAdminUnitEntity();
		List<DeptAdminUnit> unitLevels = new ArrayList<DeptAdminUnit>();
		List<DeptAdminUnit> unitEntities = new ArrayList<DeptAdminUnit>();
		List<DeptAdminUnit> unitEntities1 = new ArrayList<DeptAdminUnit>();
		int adminUnitCode = 0;
		char adminType = 'E';
		int type = 2;
		if (adminUnitView.getAdminUnitCode() != null)
			adminUnitCode = adminUnitView.getAdminUnitEntityCode();
		else
			adminUnitCode = Integer.parseInt(request.getParameter("unitCode"));
		DepartmentAdminForm deptAdminUnitForm = null;
		try {

			deptAdminUnitForm = organisationService.getDeptAdminUnitDetail(adminUnitCode, adminType);
			if (stateCode == 0) {
				stateList = stateService.getStateListWithOperationState();
				model.addAttribute("stateList", stateList);
				slc = organisationService.getStateByParentUnitEntityCode(deptAdminUnitForm.getAdminUnitLevelCode(), deptAdminUnitForm.getParentAdminCode());
			}
			if (districtId == 0){
				if(httpSession.getAttribute("isUserEntityDefiner")!=null && ((boolean)httpSession.getAttribute("isUserEntityDefiner"))){
					Map<String,Object> orgUnitwiseDeptDetails=organisationService.getOrgUnitswiseDeptDetails(assignUnit, Boolean.FALSE);
					unitLevels=(List<DeptAdminUnit>)orgUnitwiseDeptDetails.get("deptwiseAdminUnitLevelList");
				}else{
					unitLevels = organisationService.getDeptAdminUnits(stateCode, type);
				}		
				
			}
			else{
				unitLevels = organisationService.getAdminLevelForDistrictUser(stateCode);
			}
			model.addAttribute("unitLevels", unitLevels);
			model.addAttribute("deptAdminUnitForm", deptAdminUnitForm);
			model.addAttribute("ParentAdminLevelName", deptAdminUnitForm.getParentAdminLevelName());

			model.addAttribute("deptAdminUnit", deptAdminUnitEntity);
			model.addAttribute("stateCode", stateCode);
			model.addAttribute("districtCode", districtId);
			if (deptAdminUnitForm != null) {
				if (districtId == 0) {
					if (stateCode == 0)
						unitEntities = organisationService.getUnitLevelNamesForLocalBody(deptAdminUnitForm.getAdminUnitLevelCode(), slc);
					else
						unitEntities = organisationService.getUnitLevelNamesForLocalBody(deptAdminUnitForm.getAdminUnitLevelCode(), stateCode);
				} else
					unitEntities = organisationService.getUnitLevelNamesForLocalBodyDistrictUser(deptAdminUnitForm.getAdminUnitLevelCode(), districtId);
			}
			/*
			 * List<Object>
			 * adminEntityOrg=organisationService.getAdminEntityOrgUnits(
			 * deptAdminUnitForm.getAdminUnitCode());
			 * mav.addObject("adminEntityOrg", adminEntityOrg);
			 */
			
			Iterator<DeptAdminUnit> itr = unitEntities.iterator();
	        while (itr.hasNext()) {
	        	DeptAdminUnit unit = itr.next();
	            if (unit.getAdminLevelNameEng().equals(deptAdminUnitForm.getParentAdminLevelName())) {
	                itr.remove();
	            }
	        }
	        Integer adminLevel= deptAdminUnitForm.getAdminUnitLevelCode();
	        Integer parentEntity= deptAdminUnitForm.getAdminUnitCode();
	        List<DepartmentOrgListDto> deptOrgList = adminDeprtmentService.getDeptOrgList(adminLevel , parentEntity);
	       
	        if(deptOrgList != null  && !deptOrgList.isEmpty())
	        {
	        	model.addAttribute("ShowHide",true);
	        	model.addAttribute("deptOrgList",deptOrgList);
	        }
			

				model.addAttribute("unitEntities",unitEntities);
				
			// model.addAttribute("viewOrg",deptAdminUnitForm.isIsactive());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/modifyParentEntity" , method = RequestMethod.POST)
	public ModelAndView modifyParentEntity(@ModelAttribute("adminUnitView") DepartmentAdminForm adminUnitView, BindingResult result, HttpSession httpSession, HttpServletRequest request, Model model) 
	{
		  
		ModelAndView 	mavHome = new ModelAndView();
		 boolean successResult = false;
		try {
			if((adminUnitView.getAdminUnitEntityCode() != null) && (adminUnitView.getParentAdminUnitEntityCode() != null ) && (adminUnitView.getAdminUnitLevelCode() != null))
			{
				
				successResult = organisationService.updateParentAdminEntityDetailsCall(adminUnitView ,userId);
				if(successResult)
				{
					mavHome = new ModelAndView("success");
					mavHome.addObject("msgid", " Successfully Update Parent of Administrative Entity .  ");
			
				}
			}
			else {
				mavHome = new ModelAndView("success");
				mavHome.addObject("msgid", " Error while Updating Parent of Administrative Entity .  ");

			}
		}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			return mavHome;
		}

}

