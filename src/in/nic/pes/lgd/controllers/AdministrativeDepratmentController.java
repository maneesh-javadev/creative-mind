package in.nic.pes.lgd.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.entities.LBTypeDetails;
import com.cmc.lgd.localbody.entities.LocalBodyEntityDetails;
import com.cmc.lgd.localbody.entities.LocalBodyTable;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.Country;
import in.nic.pes.lgd.bean.DeptAdminUnit;
import in.nic.pes.lgd.bean.DeptAdminUnitEntity;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.ExistDeptHierarchy;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.bean.OrganizationCoverage;
import in.nic.pes.lgd.bean.OrganizationCoverageDetail;
import in.nic.pes.lgd.bean.OrganizationUnit;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.common.FileUploadUtility;
import in.nic.pes.lgd.constant.DepartmentConstent;
import in.nic.pes.lgd.forms.AdminOrgDeptForm;
import in.nic.pes.lgd.forms.OrgLocatedAtLevelsForm;
import in.nic.pes.lgd.forms.OrganizationTypeForm;
import in.nic.pes.lgd.forms.RestructureDeptLevelForm;
import in.nic.pes.lgd.service.AdministrativeDepratmentService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.OrganizationService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.validator.AdminDepartmentValidator;
import pes.attachment.util.AttachedFilesForm;

@Controller
public class AdministrativeDepratmentController { // NO_UCD (unused code)

	private static final Logger LOG = Logger.getLogger(AdministrativeDepratmentController.class);

	@Autowired
	private AdministrativeDepratmentService adminDeprtmentService;

	@Autowired
	private AdminDepartmentValidator adminValidator;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private StateService stateService;

	@Autowired
	private OrganizationService organisationService;
	
	@Autowired
	FileUploadUtility fileUploadUtility;

	private Integer stateCode;
	
	private boolean isCenterUser;
	
	private Long userId;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		setGlobalParams(session);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.getBindingResult();
        binder.validate();
	}
	
	
	private void setGlobalParams(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		isCenterUser=("C").equals(sessionObject.getIsCenterorstate());
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
		session.removeAttribute("isHierarchyAvailable");
		session.removeAttribute("storedDeptForms");
		if (isRemoveUrlParam) {
			session.removeAttribute("isOrganizationFlow");
		}
		session.removeAttribute("unitCodesNames");
	}

	/**
	 * The {@code processToBuildHierarchy} use to build a Hierarchy from
	 * Administrative Unit Levels.
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param model
	 * @return Not Working
	 */
	/*@RequestMapping(value = "/setupAdminDepartment", method = RequestMethod.GET)
	public ModelAndView processToBuildHierarchy(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = new ModelAndView();
		try {
			setGlobalParams(session);
			clearSessionAttributes(session, false);
			List<DeptAdminUnit> landRegionUnitLevels = adminDeprtmentService.getLandRegionUnitLevel(isCenterUser);
			Integer parentAdminCode = 1;
			List<DeptAdminUnit> adminUnitLevels = adminDeprtmentService.getAdministrativeUnitLevel(parentAdminCode, stateCode);
		
			List<LBTypeDetails> localBodyTypes = adminDeprtmentService.getLocalBodyTypesDetails(stateCode);
			model.addAttribute("landRegionUnitLevels", landRegionUnitLevels);
			model.addAttribute("localBodyTypes", localBodyTypes);
			model.addAttribute("adminUnitLevels", adminUnitLevels);
			model.addAttribute("stateCode", stateCode);
			model.addAttribute("adminOrgDeptForm", new AdminOrgDeptForm());
			mav = new ModelAndView("setup_admin_deprtment");
		} catch (Exception e) {
			// TODO: handle exception
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}*/
   
	/**
	 * The {@code processToBuildHierarchy} use to build a Hierarchy from
	 * Administrative Unit Levels.
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 */
	/// frist page for Department creation
	@RequestMapping(value = "/startDepartmentCreatetion", method = RequestMethod.GET)
	public ModelAndView startDepartmentCreation(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model,  @ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm) {
			ModelAndView mav = null;
			try {
				Boolean isHierarchyAvailable = false;
				setGlobalParams(session);
				clearSessionAttributes(session, false);
				/*if (hierarchySequence != null && !hierarchySequence.trim().isEmpty()) {
					String[] arrValue = hierarchySequence.split("\\,");
					Map<Integer, String> hSequenceMap = new LinkedHashMap<Integer, String>();
					for (String string : arrValue) {
						String[] mapPair = string.split("\\|");
						hSequenceMap.put(Integer.parseInt(mapPair[0]), mapPair[1]);
					}
					Set<Integer> keySet = hSequenceMap.keySet();
					List<Object[]> unitCodesNames = adminDeprtmentService.getUnitLevelNames(keySet);
					isHierarchyAvailable = true;

					model.addAttribute("keySetUnitCodes", keySet);
					// model.addAttribute("unitCodesNames", unitCodesNames);
					*//**
					 * Display Name at operational level.
					 * 
					 * @author Vinay
					 *//*
					session.setAttribute("unitCodesNames", unitCodesNames);
					session.setAttribute("process", hSequenceMap);
					session.setAttribute("isHierarchyAvailable", isHierarchyAvailable);
				}*/
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

				
				model.addAttribute("stateCode", stateCode);
				model.addAttribute("isCenterUser", isCenterUser);
				model.addAttribute("isOrganizationFlow", isOrganizationFlow);
				model.addAttribute("addStateDpetForm",true);
				//model.addAttribute("adminOrgDeptForm", adminOrgDeptForm);
				mav = new ModelAndView("create_admin_deprtment");
			} catch (Exception e) {
				// TODO: handle exception
				mav = new ModelAndView(errorHandler(request, e));
			}
			return mav;
		}
	     /// for department hirarchy Creation
		@RequestMapping(value = "/setupAdminDepartment", method = RequestMethod.POST)
		public ModelAndView processToBuildHierarchy(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model,@ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm) {
			ModelAndView mav = new ModelAndView();
			try {
				String asParentDept = adminOrgDeptForm.getDepartmentNameEnglish();
				String asDeptLocalName=adminOrgDeptForm.getDepartmentNameLocal();
				String asDeptSortName=adminOrgDeptForm.getDepartmentShortName();
				
				   
				session.setAttribute("asParentDept", asParentDept);
				session.setAttribute("asDeptLocalName",asDeptLocalName);
				session.setAttribute("asDeptSortName",asDeptSortName);
				if(isCenterUser){
				Boolean isMinistry=adminOrgDeptForm.getIsMinistry();
				   session.setAttribute("isMinistry",isMinistry);
				   if(!isMinistry){
				 	Integer ministryId=adminOrgDeptForm.getMinistryId();
				 	  session.setAttribute("ministryId",ministryId);
				   }
				}
				setGlobalParams(session);
				clearSessionAttributes(session, false);
				List<DeptAdminUnit> landRegionUnitLevels = adminDeprtmentService.getLandRegionUnitLevel(isCenterUser);
				Integer parentAdminCode = 0;
				
				if(!isCenterUser){
				parentAdminCode=1;
				List<LBTypeDetails> localBodyTypes = adminDeprtmentService.getLocalBodyTypesDetails(stateCode);
				model.addAttribute("localBodyTypes", localBodyTypes);
				/**
				 * Add this line to show admin level under state for state user 31/10/2017 by Maneesh
				 */
				List<DeptAdminUnit> adminUnitLevels = adminDeprtmentService.getAdministrativeUnitLevel(parentAdminCode, stateCode);
				model.addAttribute("adminUnitLevels", adminUnitLevels);
				/**
				 * Add this line to show admin level under state for state user 31/10/2017 by Maneesh
				 */
				}else {
					List<DeptAdminUnit> adminUnitLevels = adminDeprtmentService.getAdministrativeUnitLevel(0, 0);
					model.addAttribute("adminUnitLevels", adminUnitLevels);
				}
			/*	List<DeptAdminUnit> adminUnitLevels = adminDeprtmentService.getAdministrativeUnitLevel(parentAdminCode, stateCode);*/
				model.addAttribute("landRegionUnitLevels", landRegionUnitLevels);
			/*	model.addAttribute("adminUnitLevels", adminUnitLevels);*/
				model.addAttribute("isCenterUser", isCenterUser);
				model.addAttribute("stateCode", stateCode);
				if(session.getAttribute("storedDeptForms")==null){
					LinkedList<AdminOrgDeptForm> departmentForms  = new LinkedList<AdminOrgDeptForm>();
					departmentForms.add(adminOrgDeptForm);
					session.setAttribute("storedDeptForms", departmentForms);
				}
				AdminOrgDeptForm adminOrgDeptFormNew=new AdminOrgDeptForm();
				adminOrgDeptFormNew.setIsAbroad(adminOrgDeptForm.getIsAbroad());
				model.addAttribute("adminOrgDeptForm",adminOrgDeptFormNew);
				
				mav = new ModelAndView("setup_admin_deprtment");
			} catch (Exception e) {
				// TODO: handle exception
				mav = new ModelAndView(errorHandler(request, e));
			}
			return mav;
		}
	
	/**
	 * After completion of building hierarchy, The
	 * {@code startDepartmentCreation} used to process sequence and setup
	 * ofInitial Data Required.
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param model
	 * @param hierarchySequence
	 * @return
	 */
	//after hirarchay Create
	@RequestMapping(value = "/startDepartmentCreatetionHirarchay", method = RequestMethod.POST)
	public ModelAndView startDepartmentCreation(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("hierarchySequence") String hierarchySequence,
			@ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm) {
		ModelAndView mav = null;
		try {
		/*	Integer unitLevelCode = adminOrgDeptForm.getUnitLevelCode();
			String pageAccess = adminOrgDeptForm.getFormAccessLevel();
			String asParentDept = adminOrgDeptForm.getDepartmentNameEnglish();
			*/
			Boolean isHierarchyAvailable = false;
			if (hierarchySequence != null && !hierarchySequence.trim().isEmpty()) {
				String[] arrValue = hierarchySequence.split("\\,");
				Map<Integer, String> hSequenceMap = new LinkedHashMap<Integer, String>();
				for (String string : arrValue) {
					String[] mapPair = string.split("\\|");
					hSequenceMap.put(Integer.parseInt(mapPair[0]), mapPair[1]);
				}
				Set<Integer> keySet = hSequenceMap.keySet();
				List<Object[]> unitCodesNames = adminDeprtmentService.getUnitLevelNames(keySet);
				isHierarchyAvailable = true;

				model.addAttribute("keySetUnitCodes", keySet);
				// model.addAttribute("unitCodesNames", unitCodesNames);
				/**
				 * Display Name at operational level.
				 * 
				 * @author Vinay
				 */
				session.setAttribute("unitCodesNames", unitCodesNames);
				session.setAttribute("process", hSequenceMap);
				session.setAttribute("isHierarchyAvailable", isHierarchyAvailable);
			}
			boolean isOrganizationFlow = false;
			Object objOrgFlow = session.getAttribute("isOrganizationFlow");
			if (objOrgFlow != null) {
				isOrganizationFlow = (Boolean) objOrgFlow;
			}
			if (isCenterUser) {
				model.addAttribute("ministryList", organisationService.getMinistryList());
				boolean isMinistry = false;
				Object crateMinistry = session.getAttribute("isMinistry");
				if (crateMinistry != null) {
					isMinistry = (Boolean) crateMinistry;
				}
				model.addAttribute("isMinistry", isMinistry);
			}
			if (isOrganizationFlow) {
				if (!isCenterUser) {
					model.addAttribute("listDepartments", adminDeprtmentService.getDepartmentList(stateCode, 2));
				}
				model.addAttribute("listOrgTypes", organisationService.getOrganizationType());
			}

			model.addAttribute("stateCode", stateCode);
			model.addAttribute("isCenterUser", isCenterUser);
			model.addAttribute("isOrganizationFlow", isOrganizationFlow);
			model.addAttribute("adminOrgDeptForm", adminOrgDeptForm);
			mav = new ModelAndView("create_admin_deprtment");
		} catch (Exception e) {
			// TODO: handle exception
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}
	
	
	/**
	 * The {@code continueCreateAdminDepartmentProcess} used for creation of
	 * hierarchy in sequential manner, which hierarchy was build earlier.
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param model
	 * @param adminOrgDeptForm
	 * @param binding
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/continueCreateAdminDepartmentProcess", method = RequestMethod.POST)
	public ModelAndView continueCreateAdminDepartmentProcess(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm,
			final BindingResult binding, @RequestParam("hierarchySequence") String hierarchySequence) {
		
		/*Boolean isHierarchyAvailable = false;*/
		if (hierarchySequence != null && !hierarchySequence.trim().isEmpty()) {
			String[] arrValue = hierarchySequence.split("\\,");
			Map<Integer, String> hSequenceMap = new LinkedHashMap<Integer, String>();
			for (String string : arrValue) {
				String[] mapPair = string.split("\\|");
				hSequenceMap.put(Integer.parseInt(mapPair[0]), mapPair[1]);
			}
			session.setAttribute("process", hSequenceMap);
			
			LinkedList<AdminOrgDeptForm> preDepartmentForms =session.getAttribute("storedDeptForms")!=null?(LinkedList<AdminOrgDeptForm>)session.getAttribute("storedDeptForms"):null;
			if(preDepartmentForms!=null && !preDepartmentForms.isEmpty()){
				adminOrgDeptForm.setDeptOrgCode(preDepartmentForms.get(0).getDeptOrgCode());
				adminOrgDeptForm.setOrgType(preDepartmentForms.get(0).getOrgType());
			}
		/*	Set<Integer> keySet = hSequenceMap.keySet();*/
 			/*List<Object[]> unitCodesNames = adminDeprtmentService.getUnitLevelNames(keySet);*/
			
			/*isHierarchyAvailable = true;*/
			//for parent Dept(State)
			adminOrgDeptForm.setDepartmentNameEnglish(session.getAttribute("asParentDept").toString());
			adminOrgDeptForm.setDepartmentNameLocal(session.getAttribute("asDeptLocalName").toString());
			adminOrgDeptForm.setDepartmentShortName(session.getAttribute("asDeptSortName").toString());
			if(isCenterUser){
				Boolean isMinistry=(Boolean)session.getAttribute("isMinistry");	
				   adminOrgDeptForm.setIsMinistry(isMinistry);
				   if(!isMinistry){
				 	Integer ministryId=(Integer)session.getAttribute("ministryId");
				 	adminOrgDeptForm.setMinistryId(ministryId);
				   }
				}
			
			
			

			
		}
		ModelAndView mav = new ModelAndView("create_admin_deprtment");
		try {
			
			Integer unitLevelCode = adminOrgDeptForm.getUnitLevelCode();
			String pageAccess = adminOrgDeptForm.getFormAccessLevel();
			String asParentDept = adminOrgDeptForm.getDepartmentNameEnglish();
			

			Object objOrgFlow = session.getAttribute("isOrganizationFlow");
			boolean isOrganization = (Boolean) objOrgFlow;

			adminValidator.validate(adminOrgDeptForm, binding, isCenterUser, isOrganization, session);
			if (binding.hasErrors()) {
				setProperties(session, pageAccess, model, unitLevelCode, adminOrgDeptForm.getParentDepartmentName(), true);
				String[] LevlDetArr=adminDeprtmentService.getLevelWiseTreeDetails(stateCode,String.valueOf(unitLevelCode));
				String parentHierarchy=LevlDetArr[0];
				
				adminOrgDeptForm.setParentHierarchy(parentHierarchy);
				adminOrgDeptForm.setDpName(LevlDetArr[1]);
				adminOrgDeptForm.setIpName(LevlDetArr[2]);
				adminOrgDeptForm.setVpName(LevlDetArr[3]);
				adminOrgDeptForm.setuName(LevlDetArr[4]);
				model.addAttribute("adminOrgDeptForm", adminOrgDeptForm);
				return mav;
			}
			AdminOrgDeptForm adminOrgDeptFormNew=null;
			LinkedHashMap<Integer, String> hSequenceMap = (LinkedHashMap<Integer, String>) session.getAttribute("process");
			if (hSequenceMap!=null && !hSequenceMap.isEmpty()) {
				Set<Entry<Integer, String>> sequenceObj = hSequenceMap.entrySet();
				Entry<Integer, String> e = (Entry<Integer, String>) sequenceObj.toArray()[0];
				unitLevelCode = e.getKey();
				
				pageAccess = e.getValue();
				int mapSize = hSequenceMap.size();
				if (mapSize == 1) {
					model.addAttribute("enableSaveBtn", true);
				}
				hSequenceMap.remove(unitLevelCode);
				session.setAttribute("process", hSequenceMap);

				LinkedList<AdminOrgDeptForm> departmentForms = (LinkedList<AdminOrgDeptForm>) session.getAttribute("storedDeptForms");
				if (departmentForms == null) {
					departmentForms = new LinkedList<AdminOrgDeptForm>();
				}
				if(!adminOrgDeptForm.isTopNode()){
					departmentForms.add(adminOrgDeptForm);
					session.setAttribute("storedDeptForms", departmentForms);
				}
				
				
				
				String[] LevlDetArr=adminDeprtmentService.getLevelWiseTreeDetails(stateCode,String.valueOf(unitLevelCode));
				String parentHierarchy=LevlDetArr[0];
				adminOrgDeptFormNew= new AdminOrgDeptForm();
				adminOrgDeptFormNew.setParentHierarchy(parentHierarchy);
				adminOrgDeptFormNew.setDpName(LevlDetArr[1]);
				adminOrgDeptFormNew.setIpName(LevlDetArr[2]);
				adminOrgDeptFormNew.setVpName(LevlDetArr[3]);
				adminOrgDeptFormNew.setuName(LevlDetArr[4]);
				
				adminOrgDeptFormNew.setOrganization(isOrganization);
				
			}
			setProperties(session, pageAccess, model, unitLevelCode, asParentDept, false);
			model.addAttribute("adminOrgDeptForm",adminOrgDeptFormNew );

		} catch (Exception e) {
			// TODO: handle exception
			mav = new ModelAndView(errorHandler(request, e));
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
	private void setProperties(HttpSession session, String pageAccess, Model model, Integer unitLevelCode, String asParentDept, boolean fromError) throws Exception {

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
				boolean isMinistry = false;
				Object crateMinistry = session.getAttribute("isMinistry");
				if (crateMinistry != null) {
					isMinistry = (Boolean) crateMinistry;
				}
				model.addAttribute("isMinistry", isMinistry);
			}
			if (isOrganizationFlow) {
				if (!isCenterUser) {
					model.addAttribute("listDepartments", adminDeprtmentService.getDepartmentList(stateCode, 2));
				}
				model.addAttribute("listOrgTypes", organisationService.getOrganizationType());
			}
		}

		if (isCenterUser && !"A".equalsIgnoreCase(pageAccess)) {
			List<State> statelist = stateService.getStateSourceList();
			model.addAttribute("statelist", statelist);
		}
		if ("A".equalsIgnoreCase(pageAccess)) {
			List<DeptAdminUnitEntity> adminEntities = adminDeprtmentService.getAdminEntities(unitLevelCode);
			model.addAttribute("adminEntities", adminEntities);
		}
		if("X".equalsIgnoreCase(pageAccess) || "Y".equalsIgnoreCase(pageAccess) || "Z".equalsIgnoreCase(pageAccess)){
			model.addAttribute("districtPanchayatList", adminDeprtmentService.getDistrictPanchayatList(1,stateCode, null));
		}
		if("U".equalsIgnoreCase(pageAccess)){
			model.addAttribute("urbanList", adminDeprtmentService.getDistrictPanchayatList((-unitLevelCode),stateCode, null));
		}
		if ("D".equalsIgnoreCase(pageAccess)) {
			List<District> districtList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
			model.addAttribute("distList", districtList);
		}
		if (isCenterUser &&  "C".equalsIgnoreCase(pageAccess)) {
			List<Country> countryList = adminDeprtmentService.getCountryList();
			model.addAttribute("countryList", countryList);
		}
		model.addAttribute("parent", asParentDept);
		model.addAttribute("stateCode", stateCode);
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
	@RequestMapping(value = "/saveCreatedAdminDepartmentProcess", method = RequestMethod.POST)
	public ModelAndView saveAdminDepartment(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm, final BindingResult binding) {
		ModelAndView mav = null;
		try {
			Object objOrgFlow = session.getAttribute("isOrganizationFlow");
			boolean isOrganization = (Boolean) objOrgFlow;
			adminValidator.validate(adminOrgDeptForm, binding, isCenterUser, isOrganization, session);

			if (binding.hasErrors()) {
				mav = new ModelAndView("create_admin_deprtment");
				setProperties(session, adminOrgDeptForm.getFormAccessLevel(), model, adminOrgDeptForm.getUnitLevelCode(), adminOrgDeptForm.getParentDepartmentName(), true);
				model.addAttribute("adminOrgDeptForm", adminOrgDeptForm);
				return mav;
			}
			adminOrgDeptForm.setOrganization(isOrganization);
			LinkedList<AdminOrgDeptForm> departmentForms = new LinkedList<AdminOrgDeptForm>();
			if ( session.getAttribute("storedDeptForms")!=null) {
				// Implementation of save department without hierarchy
				departmentForms = (LinkedList<AdminOrgDeptForm>) session.getAttribute("storedDeptForms");
				departmentForms.add(adminOrgDeptForm);
			} else {
				departmentForms.add(adminOrgDeptForm);
			}
			
			boolean isSuccess = adminDeprtmentService.saveAdminDepartmentProcess(departmentForms, stateCode, isCenterUser ,userId,isOrganization);
			mav = new ModelAndView("success");
			String deptOrg = "Department ";
			if (isOrganization) {
				deptOrg = "Organization ";
			}else if(isCenterUser) {
				deptOrg="Ministry/Department";
			}
			if (isSuccess) {
				mav.addObject("msgid", deptOrg.concat("Created Successfully."));

			} else {
				mav.addObject("msgid", deptOrg.concat("Creation Process Failed."));
			}
			clearSessionAttributes(session, true);
		} catch (Exception e) {
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}

	/**
	 * The{@code closeDepartmentProcess} used to cancel current process and
	 * forward request to home page.
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/closeDepartmentProcess", method = RequestMethod.GET)
	public ModelAndView closeDepartmentProcess(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			clearSessionAttributes(session, true);
			mav = new ModelAndView("redirect:home.htm");
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
	 * The {@code processToBuildHierarchy} use to rebuild a Hierarchy from
	 * Administrative Unit Levels.
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	//@RequestMapping(value = "/rebuildSetupAdminDepartment", method = RequestMethod.GET)
	public ModelAndView processToRebuildHierarchyDept(HttpSession session,@ModelAttribute("restructureDeptLevelForm") RestructureDeptLevelForm restructureDeptLevelForm, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = new ModelAndView();
		try {
			
			mav=setCommanProperties(mav, session, Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_DEPT.toString()), model,restructureDeptLevelForm);
		} catch (Exception e) {
			LOG.error("restructureDepartmentLevel(processToRebuildHierarchy)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	
	/*@RequestMapping(value = "/rebuildSetupAdminOrganization", method = RequestMethod.GET)
	public ModelAndView processToRebuildHierarchyOrg(HttpSession session,@ModelAttribute("restructureDeptLevelForm") RestructureDeptLevelForm restructureDeptLevelForm, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = new ModelAndView();
		try {
			
			mav=setCommanProperties(mav, session, Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_ORG.toString()), model,restructureDeptLevelForm);
		} catch (Exception e) {
			LOG.error("restructureDepartmentLevel(processToRebuildHierarchy)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}*/
	
	public ModelAndView setCommanProperties(ModelAndView mav,HttpSession session,Integer organizationTypeCode,Model model,RestructureDeptLevelForm restructureDeptLevelForm)throws Exception{
		setGlobalParams(session);
		clearSessionAttributesRestructure(session);
		mav = new ModelAndView("restructureDepartmentLevel");
		restructureDeptLevelForm.setOrganizationFlow(organizationTypeCode!=Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_DEPT.toString())?true:false);
		model.addAttribute("deptList", adminDeprtmentService.getOrganizationbyCriteria(stateCode,organizationTypeCode));
		model.addAttribute("isOrganizationFlow",restructureDeptLevelForm.isOrganizationFlow());
		return mav;
	}
	
	
	

	/**
	 * The {@code processToBuildHierarchy} use to build a Hierarchy from
	 * Administrative Unit Levels.
	 * 
	 * @author Maneesh Kumar
	 * @Since 27-05-2014
	 * @param session
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/rebuildSetupAdminDepartment", method = RequestMethod.POST)
	public ModelAndView processToRebuildHierarchyPOST(HttpSession session, @ModelAttribute("restructureDeptLevelForm") RestructureDeptLevelForm restructureDeptLevelForm, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = new ModelAndView();
		try {
			setGlobalParams(session);
			clearSessionAttributesRestructure(session);
			mav = new ModelAndView("restructureDepartmentLevel");
			restructureDeptLevelForm.setSlc(stateCode);
			mav.addObject("parentLandRegionUnitLevels", adminDeprtmentService.getExistLandRegionUnitLevelbyOrganization(isCenterUser, restructureDeptLevelForm.getOlc(), Integer.parseInt(DepartmentConstent.LAND_REGION_SLC.toString()),DepartmentConstent.ENTITY_TYPE_LANDREGION.toString()));
			mav.addObject("parentAdminUnitLevels", adminDeprtmentService.getExistLandRegionUnitLevelbyOrganization(isCenterUser, restructureDeptLevelForm.getOlc(), stateCode,DepartmentConstent.ENTITY_TYPE_ADMINUNIT.toString()));
			mav.addObject("existingDeptSetup", adminDeprtmentService.getExistingDepartmentSetUp(isCenterUser, restructureDeptLevelForm.getOlc(), stateCode,DepartmentConstent.ENTITY_TYPE_BOTH.toString()));
			mav.addObject("landRegionUnitLevels", adminDeprtmentService.getLandRegionUnitLevelbyOrganization(isCenterUser, restructureDeptLevelForm.getOlc(), Integer.parseInt(DepartmentConstent.LAND_REGION_SLC.toString()), null));
			mav.addObject("stateWiseAdminUnit", adminDeprtmentService.getStateWiseAdminUnit(isCenterUser, stateCode));
			
			//model.addAttribute("distinctExistingDept",adminDeprtmentService.getDistinctExistLandRegionUnitLevel(restructureDeptLevelForm.getOlc()));
			mav.addObject("isOrganizationFlow",restructureDeptLevelForm.isOrganizationFlow());
			mav.addObject("localBodyCodes", adminDeprtmentService.getExistLandRegionUnitLevelbyOrganization(isCenterUser, restructureDeptLevelForm.getOlc(), stateCode,DepartmentConstent.LOCAL_BODY_CODE.toString()));
		} catch (Exception e) {
			LOG.error("restructureDepartmentLevel(processToRebuildHierarchyPOST)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
        //submit restart De
	@RequestMapping(value = "/startRestructureDepartmentCreatetion", method = RequestMethod.POST)
	public ModelAndView startRestructureDepartmentCreatetion(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("olc") Integer olc, @RequestParam("updateHierarchy") String updateHierarchy,
			@RequestParam("hierarchySequence") String hierarchySequence,@RequestParam("organizationFlow") boolean organizationFlow, @ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm) {
		ModelAndView mav = new ModelAndView("recreate_admin_deprtment");
		try {
int i=0;
			List<DeptAdminUnit> deptAdminUnitList = adminDeprtmentService.createtHierarchybyadminUnitCode(olc, updateHierarchy,stateCode);
			Map<Integer, Integer> parentSeq = new HashMap<Integer, Integer>();
			if (hierarchySequence.length() > 0) {
				String tempArr[] = new String[2];
				Scanner scanner = new Scanner(hierarchySequence);
				scanner.useDelimiter("@");
				while (scanner.hasNext()) {
					tempArr = scanner.next().split(":");
				
					parentSeq.put(Integer.parseInt(tempArr[0]), Integer.parseInt(tempArr[1]));
				}
				scanner.close();
			}
			if (deptAdminUnitList != null && !deptAdminUnitList.isEmpty()) {

				Map<Integer, String> hSequenceMap = new LinkedHashMap<Integer, String>();
				Integer Key = null;
				boolean isTop = false;
				Integer isTopParent = null;

				for (DeptAdminUnit deptAdminUnit : deptAdminUnitList) {

					if (deptAdminUnit.getOrgLocatedLevelCode() != null) {
						Key = deptAdminUnit.getOrgLocatedLevelCode();
					} else {
						Key = deptAdminUnit.getAdminUnitCode();
						
					}
					if (isTopParent != null && isTopParent.equals(deptAdminUnit.getParentOrgLocatedLevelCode()) || parentSeq.get(deptAdminUnit.getAdminUnitCode()).equals(Integer.parseInt(DepartmentConstent.STATE_CODE.toString()))) {
						isTop = true;
					}
					hSequenceMap.put(
							Key,
							deptAdminUnit.getAdminLevelNameEng() + "@@" + deptAdminUnit.getAdminLevelNameLocal() + "@@" + deptAdminUnit.isRevenueEntity() + "@@" + parentSeq.get(deptAdminUnit.getAdminUnitCode()) + "@@"
									+ deptAdminUnit.getAdminUnitCode() + "@@" + (deptAdminUnit.getParentOrgLocatedLevelCode() != null ? deptAdminUnit.getParentOrgLocatedLevelCode() : Integer.parseInt(DepartmentConstent.ZERO_AGAIN_NULL.toString()))
									+ "@@" + isTop + "@@" + deptAdminUnit.getSortOrder());
					if (isTop) {
						isTopParent = deptAdminUnit.getParentOrgLocatedLevelCode();
					}
					isTop = false;
				}

				boolean isHierarchyAvailable = true;

				session.setAttribute("process", hSequenceMap);
				session.setAttribute("isHierarchyAvailable", isHierarchyAvailable);
				model.addAttribute("stateCode", stateCode);
				model.addAttribute("isCenterUser", isCenterUser);
				List<Object> objList = getPropertiesbyKey(hSequenceMap, model, true);
				Integer adminUnitCode = Integer.parseInt(objList.get(5).toString());
				Integer parent = Integer.parseInt(objList.get(4).toString());
				Integer orgLocatedLevelCode = Integer.parseInt(objList.get(0).toString());
				String pageAccess = objList.get(2).toString();
				isTop = Boolean.parseBoolean(objList.get(7).toString());
				Integer sortOrder = Integer.parseInt(objList.get(8).toString());
				if (!isTop) {
					model.addAttribute("existDeptList", adminDeprtmentService.getParentDeptListbyOrganization(adminOrgDeptForm.getOlc(), parent));
				}
				
				adminOrgDeptForm.setTopNode(isTop);
				adminOrgDeptForm.setParentUnitLevelCode(parent);
				adminOrgDeptForm.setNewLevel(true);
				adminOrgDeptForm.setOlc(olc);
				adminOrgDeptForm.setSearchOrgLocatedLevelCode(orgLocatedLevelCode);
				adminOrgDeptForm.setFormAccessLevel(pageAccess);
				adminOrgDeptForm.setUnitLevelCode(adminUnitCode);
				adminOrgDeptForm.setSortOrder(sortOrder);
				adminOrgDeptForm.setOrganizationFlow(organizationFlow);
				resetProperties(session, model, adminOrgDeptForm, hSequenceMap, null, objList.get(1).toString());
			}

		} catch (Exception e) {
			LOG.error("restructureDepartmentLevel(startRestructureDepartmentCreatetion)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;

	}

	@RequestMapping(value = "/countinueRestructureDepartmentCreatetion", method = RequestMethod.POST)
	public ModelAndView countinueRestructureDepartmentCreatetion(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("unitCodesName") String unitCodesName,
			@ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm, final BindingResult binding) {
		ModelAndView mav = new ModelAndView("recreate_admin_deprtment");
		try {
			boolean isOrganization = false;

			@SuppressWarnings("unchecked")
			LinkedList<AdminOrgDeptForm> departmentForms = (LinkedList<AdminOrgDeptForm>) session.getAttribute("storedDeptForms");
			if (departmentForms == null) {
				departmentForms = new LinkedList<AdminOrgDeptForm>();
			}
			@SuppressWarnings("unchecked")
			LinkedHashMap<Integer, String> hSequenceMap = (LinkedHashMap<Integer, String>) session.getAttribute("process");

			if (adminOrgDeptForm.isNewLevel()) {
				adminValidator.validate(adminOrgDeptForm, binding, isCenterUser, isOrganization, session);
				if (binding.hasErrors()) {
					resetProperties(session, model, adminOrgDeptForm, hSequenceMap, departmentForms, unitCodesName);
					return mav;
				}
			}
			adminOrgDeptForm.setOrganization(isOrganization);
			departmentForms.add(adminOrgDeptForm);

			if (!hSequenceMap.isEmpty()) {
				if (adminOrgDeptForm.isSameLevel()) {
					adminOrgDeptForm.setSearchOrgLocatedLevelCode(adminOrgDeptForm.getOrgLocatedLevelCode());
				}
				List<Object> objList = getPropertiesbyKey(hSequenceMap, model, adminOrgDeptForm.isSameLevel());
				Integer adminUnitCode = Integer.parseInt(objList.get(5).toString());
				Integer parentOrgLocatedLevelCode = Integer.parseInt(objList.get(6).toString());
				parentOrgLocatedLevelCode = !parentOrgLocatedLevelCode.equals(Integer.parseInt(DepartmentConstent.ZERO_AGAIN_NULL.toString())) ? parentOrgLocatedLevelCode : null;
				Integer parent = Integer.parseInt(objList.get(4).toString());
				String pageAccess = objList.get(2).toString();
				boolean isTop = Boolean.parseBoolean(objList.get(7).toString());
				Integer sortOrder = Integer.parseInt(objList.get(8).toString());
				session.setAttribute("storedDeptForms", departmentForms);
				AdminOrgDeptForm newAdminOrgDeptForm = new AdminOrgDeptForm();
				if (adminOrgDeptForm.isSameLevel()) {
					newAdminOrgDeptForm.setSearchOrgLocatedLevelCode(adminOrgDeptForm.getOrgLocatedLevelCode());
				}
				newAdminOrgDeptForm.setParentUnitLevelCode(parent);
				newAdminOrgDeptForm.setNewLevel(true);
				newAdminOrgDeptForm.setOlc(adminOrgDeptForm.getOlc());
				newAdminOrgDeptForm.setTopNode(isTop);
				newAdminOrgDeptForm.setFormAccessLevel(pageAccess);
				newAdminOrgDeptForm.setUnitLevelCode(adminUnitCode);
				newAdminOrgDeptForm.setSortOrder(sortOrder);
				newAdminOrgDeptForm.setOrganizationFlow(adminOrgDeptForm.isOrganizationFlow());
				resetProperties(session, model, newAdminOrgDeptForm, hSequenceMap, departmentForms, objList.get(1).toString());
				model.addAttribute("adminOrgDeptForm", newAdminOrgDeptForm);

			}

		} catch (Exception e) {
			LOG.error("restructureDepartmentLevel(countinueRestructureDepartmentCreatetion)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getPropertiesbyKey(Map<Integer, String> hSequenceMap, Model model, boolean isSameLevel) throws Exception {
		List<Object> obj = null;
		if (!hSequenceMap.isEmpty()) {
			Entry<Integer, String> e = null;
			obj = new ArrayList<Object>();
			Set<Entry<Integer, String>> sequenceObj = hSequenceMap.entrySet();
			e = (Entry<Integer, String>) sequenceObj.toArray()[0];

			if (!isSameLevel) {
				hSequenceMap.remove(e.getKey());
				e = (Entry<Integer, String>) sequenceObj.toArray()[0];
			}
			int mapSize = hSequenceMap.size();

			obj.add(e.getKey());
			String rowValue = e.getValue();
			Scanner scanner = new Scanner(rowValue);
			scanner.useDelimiter("@@");
			while (scanner.hasNext()) {
				obj.add(scanner.next());
			}
			scanner.close();
			if (mapSize == 1) {
				model.addAttribute("enableSaveBtn", true);
			}
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveRestructureDepartment", method = RequestMethod.POST)
	public ModelAndView saveRestructureDepartment(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("unitCodesName") String unitCodesName,
			@ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm, final BindingResult binding) {
		ModelAndView mav = new ModelAndView("recreate_admin_deprtment");
		try {
			boolean isOrganization = false;
			LinkedList<AdminOrgDeptForm> departmentForms = (LinkedList<AdminOrgDeptForm>) session.getAttribute("storedDeptForms");
			if (departmentForms == null) {
				departmentForms = new LinkedList<AdminOrgDeptForm>();
			}
			LinkedHashMap<Integer, String> hSequenceMap = (LinkedHashMap<Integer, String>) session.getAttribute("process");

			if (adminOrgDeptForm.isNewLevel()) {
				adminValidator.validate(adminOrgDeptForm, binding, isCenterUser, isOrganization, session);
				if (binding.hasErrors()) {
					resetProperties(session, model, adminOrgDeptForm, hSequenceMap, departmentForms, unitCodesName);
					return mav;
				}
			}

			adminOrgDeptForm.setOrganization(isOrganization);
			departmentForms.add(adminOrgDeptForm);
			
			clearSessionAttributesRestructure(session);
			boolean isSuccess = adminDeprtmentService.saveRestructureDepartmentLevel(departmentForms, stateCode, isOrganization, adminOrgDeptForm.getOlc(),userId);
			mav = new ModelAndView("success");
			String deptOrg = "Department ";
			if (adminOrgDeptForm.isOrganizationFlow()) {
				deptOrg = "Organization ";
			}
			if (isSuccess) {
				mav.addObject("msgid", deptOrg.concat(" is Extended Successfully."));

			} else {
				mav.addObject("msgid", deptOrg.concat(" Extended Process Failed."));
			}
			clearSessionAttributes(session, true);

		} catch (Exception e) {
			LOG.error("restructureDepartmentLevel(saveRestructureDepartment)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);

		}
		return mav;
	}

	private void resetProperties(HttpSession session, Model model, AdminOrgDeptForm adminOrgDeptForm, Map<Integer, String> hSequenceMap, LinkedList<AdminOrgDeptForm> departmentForms, String unitCodesName) throws Exception {
		model.addAttribute("unitLevelCode", adminOrgDeptForm.getUnitLevelCode());
		model.addAttribute("pageAccessLevel", adminOrgDeptForm.getFormAccessLevel());
		if (adminOrgDeptForm.isTopNode()) {
			model.addAttribute("firstDeptList", adminDeprtmentService.getParentDeptListbyOrganization(adminOrgDeptForm.getOlc(), adminOrgDeptForm.getParentUnitLevelCode()));
		} else {
			List<AdminOrgDeptForm> newparentList = new ArrayList<AdminOrgDeptForm>();

			if (departmentForms != null) {
				for (AdminOrgDeptForm searchForm : departmentForms) {
					if (searchForm.getUnitLevelCode().equals(adminOrgDeptForm.getParentUnitLevelCode())) {
						newparentList.add(searchForm);
					}

				}
			}
			model.addAttribute("newparentList", newparentList);
			model.addAttribute("existDeptList", adminDeprtmentService.getParentDeptListbyOrganization(adminOrgDeptForm.getOlc(), adminOrgDeptForm.getParentUnitLevelCode()));

		}

		List<AdminOrgDeptForm> newOfficeList = new ArrayList<AdminOrgDeptForm>();
		if (departmentForms != null) {
			for (AdminOrgDeptForm searchForm : departmentForms) {

				if (searchForm.getUnitLevelCode().equals(adminOrgDeptForm.getUnitLevelCode())) {
					newOfficeList.add(searchForm);
				}
			}
		}
		model.addAttribute("newOfficeList", newOfficeList);
		if ("A".equalsIgnoreCase(adminOrgDeptForm.getFormAccessLevel())) {
			List<DeptAdminUnitEntity> adminEntities = adminDeprtmentService.getAdminEntities(adminOrgDeptForm.getUnitLevelCode());
			model.addAttribute("adminEntities", adminEntities);
		}
		if("X".equalsIgnoreCase(adminOrgDeptForm.getFormAccessLevel()) || "Y".equalsIgnoreCase(adminOrgDeptForm.getFormAccessLevel()) || "Z".equalsIgnoreCase(adminOrgDeptForm.getFormAccessLevel())){
			List<LocalBodyTable> localBodyEntities=adminDeprtmentService.getLocalBodyEntities(-adminOrgDeptForm.getUnitLevelCode(),stateCode);
			model.addAttribute("localBodyEntities", localBodyEntities);
		}
		if ("D".equalsIgnoreCase(adminOrgDeptForm.getFormAccessLevel())) {
			List<District> districtList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
			model.addAttribute("distList", districtList);
		}
		if (hSequenceMap.size() == 1) {
			model.addAttribute("enableSaveBtn", true);
		}
		model.addAttribute("unitCodesName", unitCodesName);
		model.addAttribute("existOfficeList", adminDeprtmentService.getOfficeNamebyLevel(adminOrgDeptForm.getOlc(), adminOrgDeptForm.getUnitLevelCode()));
		model.addAttribute("isOrganizationFlow",adminOrgDeptForm.isOrganizationFlow());
	}

	/* Organization Unit Start */
	/**
	 * The {@code organizationUnit} use to get Organisation Unit from Center and
	 * State Levels.
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/organizationUnit", method = RequestMethod.GET)
	public ModelAndView organizationUnit(HttpSession session, HttpServletRequest request, Model model, AdminOrgDeptForm adminOrgDeptForm) {
		ModelAndView mav = new ModelAndView();
		try {
			setGlobalParams(session);
			mav = new ModelAndView("organisationUnit");
			mav.addObject("isCenterUser", isCenterUser);
			if (stateCode != 0) {
				model.addAttribute("orgList", organisationService.getOrganizationDetailbystateCode(stateCode));
				model.addAttribute("stateName", adminDeprtmentService.getStateName(stateCode));
			} else {
				model.addAttribute("ministryList", organisationService.getMinistryList());
			}
		} catch (Exception e) {
			System.out.print(e.getCause().getMessage());
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}

	/**
	 * The {@code saveOrganizationUnit} use to Save Organization Unit from
	 * Center and State Levels. *
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/organizationUnit", method = RequestMethod.POST)
	public ModelAndView saveOrganizationUnit(HttpSession session, HttpServletRequest request, Model model, @Valid AdminOrgDeptForm adminOrgDeptForm, BindingResult binding) {
		ModelAndView mav = new ModelAndView("");
		try {
			setGlobalParams(session);
			Collection<OrganizationUnit> organisationUnitList = new ArrayList<OrganizationUnit>();
			String offycName = adminOrgDeptForm.getDepartmentNameEnglish();

			if (StringUtils.isNotBlank(offycName)) {
				final Object[] orgUnitEntityData = adminDeprtmentService.getOrgUnitEntityData(adminOrgDeptForm.getDeptOrgCode(), stateCode);
				Transformer transformer = new Transformer() {
					public OrganizationUnit transform(Object input) {
						OrganizationUnit orgUnit = new OrganizationUnit();
						orgUnit.setOrgUnitName(input.toString());
						orgUnit.setHeadOffice('S');
						orgUnit.setActive(true);
						orgUnit.setOrgLocatedLevelCode((Integer) orgUnitEntityData[0]);
						orgUnit.setEntityLc((Integer) orgUnitEntityData[1]);
						orgUnit.setEntityType((Integer) orgUnitEntityData[2]);
						orgUnit.setParentOrgUnitCode((Integer) orgUnitEntityData[3]);
						orgUnit.setAddress1("");
						return orgUnit;
					}
				};
				organisationUnitList = CollectionUtils.collect(Arrays.asList(offycName.split(",")), transformer);
			}
			String updatedSubOffyc = adminOrgDeptForm.getUpdatedSubOfficeList();
			if (StringUtils.isNotBlank(updatedSubOffyc)) {
				organisationUnitList.addAll(modifiedSubOfficeList(updatedSubOffyc));
			}

			Object[] errAndAdminFormArray = adminValidator.orgUnitCommonFields(adminOrgDeptForm, binding, stateCode, (List<OrganizationUnit>) organisationUnitList);
			int flag = 0;
			if (null != errAndAdminFormArray) {
				if (null != errAndAdminFormArray[0]) {
					Map<Object, String> errMsgMap = (Map<Object, String>) errAndAdminFormArray[0];
					if (errMsgMap.size() != 0) {
						model.addAttribute("errMsgMap", errMsgMap);
						flag = 1;
					}
				}
				if (null != errAndAdminFormArray[1]) {
					model.addAttribute("admionFormList", (List<AdminOrgDeptForm>) errAndAdminFormArray[1]);
				}
			}
			if (binding.hasErrors() || flag == 1) {
				model.addAttribute("errFlag", flag);
				return organizationUnit(session, request, model, adminOrgDeptForm);
			}

			Boolean status = adminDeprtmentService.saveOrganisationUnit((List<OrganizationUnit>) organisationUnitList);
			String selectedOrgName = request.getParameter("selectdOrgName");

			mav.setViewName("success");
			if (status) {
				mav.addObject("msgid", "Sub Offices of " + selectedOrgName + " Department are created successfully.");
			} else {
				mav.addObject("msgid", "Sub Offices of " + selectedOrgName + " Department are not created successfully.");
			}
		} catch (Exception e) {
			System.out.print(e.getCause().getMessage());
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	private Collection<OrganizationUnit> modifiedSubOfficeList(String input) {
		Collection<OrganizationUnit> modifiedSubOffycList = new ArrayList<OrganizationUnit>();
		if (input != null && !"".equals(input)) {
			Transformer transformer = new Transformer() {
				@Override
				public OrganizationUnit transform(Object input) {
					String[] arr = ((String) input).split("\\_");
					OrganizationUnit temp = new OrganizationUnit();
					temp.setOrgUnitCode((arr[0] == null ? 0 : Integer.valueOf(arr[0])));
					temp.setOrgUnitName(arr[1]);
					return temp;
				}
			};
			modifiedSubOffycList = CollectionUtils.collect(Arrays.asList(input.split(",")), transformer);
		}
		return modifiedSubOffycList;
	}

	/* Organisation Unit Ends here */

	private void clearSessionAttributesRestructure(HttpSession session) {
		if (session.getAttribute("process") != null) {
			session.removeAttribute("process");
		}
		if (session.getAttribute("isHierarchyAvailable") != null) {
			session.removeAttribute("isHierarchyAvailable");
		}
		if (session.getAttribute("storedDeptForms") != null) {
			session.removeAttribute("storedDeptForms");
		}
	}

	/**
	 * This method is used for filter Office Name you Want to Change Coverage
	 * 
	 * @author Maneesh Kumar 21-July-2015
	 * @param model
	 * @param request
	 * @param httpSessione
	 * @return
	 */

	@RequestMapping(value = "/extendDepartmentState", method = RequestMethod.GET)
	public ModelAndView extendOrganizationUnits(Model model, HttpServletRequest request, HttpSession httpSessione) {
		ModelAndView mav = new ModelAndView("extendOrganizationUnits");
		try {
			setGlobalParams(httpSessione);
			model.addAttribute("stateCode",stateCode);
			//model.addAttribute("deptList", adminDeprtmentService.getOrganizationbyCriteria(stateCode, Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_DEPT.toString())));
			return mav;
		} catch (Exception e) {
			LOG.error("extendOrganisationUnits(extendOrganizationUnits)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

	}

	/**
	 * This method is used for get details of selected offce
	 * 
	 * @author Maneesh Kumar 21-July-2015
	 * @param OrgLocatedLevelCode
	 * @param model
	 * @param request
	 * @param httpSessione
	 * @return
	 */
	@RequestMapping(value = "/extendOrganizationUnits", method = RequestMethod.POST)
	public ModelAndView extendOrganizationUnitsPOST( @ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm, Model model, HttpServletRequest request, HttpSession httpSessione) {
		ModelAndView mav = new ModelAndView("extendOrganizationUnitSetup");
		try {
			
			
			model.addAttribute("isOrganizationFlow",adminOrgDeptForm.getOrgTypeCode()!=Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_DEPT.toString())?true:false);
			adminOrgDeptForm.setOrgLocatedLevelCode(adminOrgDeptForm.getOrgLocatedLevelCode());
			setExtendOrganizationUnitsProperties(adminOrgDeptForm, model);
			return mav;
		} catch (Exception e) {
			LOG.error("extendOrganisationUnits(extendOrganizationUnitsPOST)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

	}

	/**
	 * This method is used for save change office Coverage
	 * 
	 * @author Maneesh Kumar 21-July-2015
	 * @param session
	 * @param request
	 * @param response
	 * @param model
	 * @param adminOrgDeptForm
	 * @param binding
	 * @return
	 */
	@RequestMapping(value = "/saveOrganizationUnits", method = RequestMethod.POST)
	public ModelAndView saveOrganizationUnits(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm, final BindingResult binding) {
		ModelAndView mav = new ModelAndView("extendOrganizationUnitSetup");
		try {
			adminOrgDeptForm.setStateCode(stateCode);
			adminValidator.validateExtnedOrganizationUnits(adminOrgDeptForm, binding);
			if (binding.hasErrors()) {
				setExtendOrganizationUnitsProperties(adminOrgDeptForm, model);

			} else {
				mav = new ModelAndView("success");
				boolean isSuccess = adminDeprtmentService.saveOrganizationUnits(adminOrgDeptForm);
				if (isSuccess) {
					mav.addObject("msgid", "New Organisation Units have been created successfully.");

				} else {
					mav.addObject("msgid", " Extended Organization Units Failed.");
				}
			}

		} catch (Exception e) {
			LOG.error("extendOrganisationUnits(saveOrganizationUnits)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);

		}
		return mav;
	}

	/**
	 * This method is used set basic details of Office
	 * 
	 * @author Maneesh Kumar 21-July-2015
	 * @param adminOrgDeptForm
	 * @param model
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void setExtendOrganizationUnitsProperties(AdminOrgDeptForm adminOrgDeptForm, Model model) throws Exception {
		Object[] objectArray = adminDeprtmentService.getExtendOrganizationBasicDetail(adminOrgDeptForm.getOrgLocatedLevelCode(), stateCode);
		List<OrgLocatedAtLevels> orgLocatedAtLevelsList = (List<OrgLocatedAtLevels>) objectArray[0];
		List<OrganizationCoverage> organizationCoverageList = (List<OrganizationCoverage>) objectArray[1];
		List<OrganizationCoverageDetail> organizationCoverageDetailList = (List<OrganizationCoverageDetail>) objectArray[2];
		OrgLocatedAtLevels extendOrgUnitBasic = null;
		if (orgLocatedAtLevelsList != null && !orgLocatedAtLevelsList.isEmpty()) {
			extendOrgUnitBasic = orgLocatedAtLevelsList.get(0);
			
			String[] LevlDetArr=adminDeprtmentService.getLevelWiseTreeDetails(stateCode, extendOrgUnitBasic.getLocatedAtLevel()!=0?String.valueOf(extendOrgUnitBasic.getLocatedAtLevel()):"");
			String parentHierarchy=LevlDetArr[0];
			adminOrgDeptForm.setParentHierarchy(parentHierarchy);
			adminOrgDeptForm.setDpName(LevlDetArr[1]);
			adminOrgDeptForm.setIpName(LevlDetArr[2]);
			adminOrgDeptForm.setVpName(LevlDetArr[3]);
			adminOrgDeptForm.setuName(LevlDetArr[4]);
			
			adminOrgDeptForm.setUnitLevelCode(extendOrgUnitBasic.getLocatedAtLevel());
			if ("A".equalsIgnoreCase(extendOrgUnitBasic.getPageAccessLevel())) {
				if (objectArray[3] != null) {
					model.addAttribute("adminEntities", (List<DeptAdminUnitEntity>) objectArray[3]);
				}
				if (objectArray[4] != null) {
					model.addAttribute("selAdminEntities", (List<DeptAdminUnitEntity>) objectArray[4]);
				}
			} 
			else if("X".equalsIgnoreCase(extendOrgUnitBasic.getPageAccessLevel())){
				if(objectArray[3]!= null){
					model.addAttribute("districtPanchayatList", (List<LocalBodyEntityDetails>)objectArray[3]);
					
				}
				if(objectArray[4]!= null){
					model.addAttribute("seldistrictPanchayatList", (List<LocalBodyEntityDetails>)objectArray[4]);
				}
			}else if("Y".equalsIgnoreCase(extendOrgUnitBasic.getPageAccessLevel())){
				if(objectArray[3]!= null){
					model.addAttribute("districtPanchayatList", (List<LocalBodyEntityDetails>)objectArray[3]);
					
				}
				if(objectArray[4]!= null){
					model.addAttribute("seldistrictPanchayatList", (List<LocalBodyEntityDetails>)objectArray[4]);
				}
				if(objectArray[5]!= null){
					model.addAttribute("selintermediatePanchayatList", (List<LocalBodyEntityDetails>)objectArray[5]);
				}
			}else if("Z".equalsIgnoreCase(extendOrgUnitBasic.getPageAccessLevel())){
				if(objectArray[3]!= null){
					model.addAttribute("districtPanchayatList", (List<LocalBodyEntityDetails>)objectArray[3]);
					
				}
				if(objectArray[4]!= null){
					model.addAttribute("seldistrictPanchayatList", (List<LocalBodyEntityDetails>)objectArray[4]);
				}
				if(objectArray[5]!= null){
					model.addAttribute("selintermediatePanchayatList", (List<LocalBodyEntityDetails>)objectArray[5]);
				}
				if(objectArray[6]!= null){
					model.addAttribute("selvillagePanchayatList", (List<LocalBodyEntityDetails>)objectArray[6]);
				}
			}else if("U".equalsIgnoreCase(extendOrgUnitBasic.getPageAccessLevel())){
				if(objectArray[3]!= null){
					model.addAttribute("urbantList", (List<LocalBodyEntityDetails>)objectArray[3]);
					
				}
				if(objectArray[4]!= null){
					model.addAttribute("selurbantList", (List<LocalBodyEntityDetails>)objectArray[4]);
				}
			}
			else {
				model.addAttribute("distList", (List<District>) objectArray[3]);
				model.addAttribute("selDistList", (List<District>) objectArray[4]);

				if (objectArray[5] != null) {
					if ("B".equalsIgnoreCase(extendOrgUnitBasic.getPageAccessLevel())){
						model.addAttribute("selBlockList", (List<Block>) objectArray[5]);
					}else{
					model.addAttribute("selSubDistList", (List<Subdistrict>) objectArray[5]);
					}
					model.addAttribute("distListPartSubdistrict", (List<District>) objectArray[3]);
				}

				if (objectArray[6] != null) {
					model.addAttribute("selVillageList", (List<Village>) objectArray[6]);

				}
			}

			adminOrgDeptForm.setDepartmentNameEnglish(extendOrgUnitBasic.getOrgLevelSpecificName());
			adminOrgDeptForm.setDepartmentNameLocal(extendOrgUnitBasic.getOrgLevelSpecificNameLocal());
			adminOrgDeptForm.setDepartmentShortName(extendOrgUnitBasic.getOrgLevelSpecificShortName());
			adminOrgDeptForm.setParentDepartmentName(extendOrgUnitBasic.getParentOrgLevelSpecificName());
			adminOrgDeptForm.setStateCode(stateCode);

			model.addAttribute("adminOrgDeptForm", adminOrgDeptForm);
			model.addAttribute("pageAccessLevel", extendOrgUnitBasic.getPageAccessLevel());
			model.addAttribute("organizationCoverageList", organizationCoverageList);
			model.addAttribute("organizationCoverageDetailList", organizationCoverageDetailList);
		}
	}
	
	/**
	 * Code use to Rename Level Specific Name of Organization
	 * @author Pooja
	 * @since 15-09-2015
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/manageOrgLevels", method = RequestMethod.GET)
	public ModelAndView manageOrgLevels(HttpSession session, HttpServletRequest request, Model model, OrgLocatedAtLevelsForm orgLocatedAtLevelsForm) {
		ModelAndView mav = new ModelAndView();
		try {
			setGlobalParams(session);
			mav = new ModelAndView("manageOrgLevels");
			if (stateCode != 0) {
				model.addAttribute("orgTypeList", organisationService.getOrganizationTypeforAddDesig());
			}
		} catch (Exception e) {
			System.out.print(e.getCause().getMessage());
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}

	@RequestMapping(value = "/manageOrgLevels", method = RequestMethod.POST)
	public ModelAndView manageOrgLevelsPost(HttpSession session, HttpServletRequest request, Model model, @ModelAttribute("orgLocatedAtLevelsForm") OrgLocatedAtLevelsForm orgLocatedAtLevelsForm, BindingResult binding) {
		ModelAndView mav = new ModelAndView("");
		try {
			adminValidator.validateChangeLvlSpecificNameOrg(orgLocatedAtLevelsForm, binding);
			if (binding.hasErrors()) {
				return manageOrgLevels(session, request, model, orgLocatedAtLevelsForm);
			} else {
				String orgName = request.getParameter("orgName");
				String orgLocatedLevelData = organisationService.changeLvlSpecificNameOrg(orgLocatedAtLevelsForm, session);
				mav.setViewName("success");
				if (orgLocatedLevelData != null) {
					if (orgLocatedAtLevelsForm.getAttachFile().get(0).getFileItem().getSize() > 0) {
						List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.validateAndUploadFileGovtOrderUpdateOrgLvl(request, orgLocatedAtLevelsForm, binding);
						boolean insertGovtTableFlag = adminDeprtmentService.saveDataInAttachment(validFileGovtUpload, orgLocatedAtLevelsForm.getOrgLocatedLevelCode());
						if (!insertGovtTableFlag)
							mav.addObject("msgid", "Level Specific Name of Organization has been updated successfully but Govt. order was not Uploaded successfully.");
					}
					mav.addObject("msgid", "Level Specific Name of Organization has been updated successfully");

				} else {
					mav.addObject("msgid", "Due to Some Error, System is unable to update Level Specific Name of " + orgName + " Organization.");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getCause().getMessage());
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}
	
	/*Added By Pranav Tiwari
	setting Parent Org unit Center
	According to Ministry and Department
	*/
	@RequestMapping(value = "/setParentOrgUnitCenter", method = RequestMethod.GET)
	public ModelAndView setParentOrgUnitCenter(HttpServletRequest request,HttpSession session,Model model){
		ModelAndView mav = null;
		setGlobalParams(session);
		
		
		try {
			 mav = new ModelAndView("setParentOrgUnitCenter");
			 model.addAttribute("orgUnitCenterForm",new OrganizationTypeForm());
			
		} catch (Exception e) {
			throw e;
		}
		return mav;
	}
	
	@RequestMapping(value = "/rebuildSetupAdminDepartment", method = RequestMethod.GET)
	public ModelAndView extendExistDept(HttpSession session,@ModelAttribute("extendDpet") RestructureDeptLevelForm restructureDeptLevelForm, HttpServletRequest request, HttpServletResponse response, Model model){
		ModelAndView mav = new ModelAndView("BUILD_TREE");
		try {
			setGlobalParams(session);
			clearExtendSessionAttributes(session);
			session.setAttribute("isOrganizationFlow", Boolean.FALSE);
			model.addAttribute("deptList", adminDeprtmentService.getOrganizationbyCriteria(stateCode,Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_DEPT.toString())));
		} catch (Exception e) {
			LOG.error("restructureDepartmentLevel(processToRebuildHierarchy)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/rebuildSetupAdminOrganization", method = RequestMethod.GET)
	public ModelAndView processToRebuildHierarchyOrg(HttpSession session,@ModelAttribute("extendDpet") RestructureDeptLevelForm restructureDeptLevelForm, HttpServletRequest request, HttpServletResponse response, Model model){
		ModelAndView mav = new ModelAndView("BUILD_TREE");
		try {
			setGlobalParams(session);
			clearExtendSessionAttributes(session);
			session.setAttribute("isOrganizationFlow", Boolean.TRUE);
			model.addAttribute("deptList", adminDeprtmentService.getOrganizationbyCriteria(stateCode,Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_ORG.toString())));
		} catch (Exception e) {
			LOG.error("restructureDepartmentLevel(processToRebuildHierarchy)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/buildTree", method = RequestMethod.POST)
	public ModelAndView buildTree(HttpSession session,@ModelAttribute("buildTree") RestructureDeptLevelForm restructureDeptLevelForm, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = new ModelAndView("BUILD_TREE");
		try {
			setGlobalParams(session);
			restructureDeptLevelForm.setSlc(stateCode);
			model.addAttribute("existDeptHierarchy",adminDeprtmentService.getExistDeptHierarchy(restructureDeptLevelForm.getOlc(),stateCode,Boolean.TRUE));
		} catch (Exception e) {
			LOG.error("restructureDepartmentLevel(processToRebuildHierarchy)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	
	
	
	@RequestMapping(value="/startRebuildTree",method= RequestMethod.POST)
	public ModelAndView startRebuildTree(HttpSession session,@ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav =null;
		try {
			
			String hierarchyIds=adminOrgDeptForm.getHierarchyIds();
			if(hierarchyIds!=null && !("").equals(hierarchyIds.trim())){
			session.setAttribute("hierarchyIds", hierarchyIds);
			mav=new ModelAndView("PROCESS_LEVEL_WISE_FORM");	
			String hierArr[]=hierarchyIds.split("@");
			Integer curIndex=0;
			String elementDetails=hierArr[curIndex];
			String Level=elementDetails.substring((elementDetails.lastIndexOf(",")+1), elementDetails.length());	
			String parentId=elementDetails.substring(0,elementDetails.lastIndexOf(","));
			session.setAttribute("olc", adminOrgDeptForm.getOlc());
			model.addAttribute("enableSaveBtn",hierArr.length-1==curIndex);
			setLevelFormCommanProperties(adminOrgDeptForm,model,Integer.parseInt(Level),elementDetails,parentId,session,curIndex,null);
			
			}else{
				RestructureDeptLevelForm restructureDeptLevelForm=new RestructureDeptLevelForm();
				model.addAttribute("buildTree",restructureDeptLevelForm);
				mav= new ModelAndView("BUILD_TREE");
				setGlobalParams(session);
				restructureDeptLevelForm.setSlc(stateCode);
				model.addAttribute("existDeptHierarchy",adminDeprtmentService.getExistDeptHierarchy(restructureDeptLevelForm.getOlc(),stateCode,Boolean.TRUE));
			}
			
		} catch (Exception e) {
			LOG.error("restructureDepartmentLevel(processToRebuildHierarchy)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	@RequestMapping(value="/countinueRebuildTree",method= RequestMethod.POST)
	public ModelAndView countinueRebuildTree(HttpSession session,@ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm,final BindingResult binding,	HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav =null;
		try {
			
			boolean sameLevel=adminOrgDeptForm.isSameLevel();
			if(session.getAttribute("olc")==null){
				
			}
			
			Integer olc=Integer.parseInt(session.getAttribute("olc").toString());
			
			
			Object objOrgFlow = session.getAttribute("isOrganizationFlow");
			boolean isOrganization = (Boolean) objOrgFlow;
			
			

			adminValidator.validate(adminOrgDeptForm, binding, isCenterUser, isOrganization, session);
			LinkedList<AdminOrgDeptForm> departmentForms = (LinkedList<AdminOrgDeptForm>) session.getAttribute("storedDeptForms");
			if (binding.hasErrors()) {
				mav=new ModelAndView("PROCESS_LEVEL_WISE_FORM");
				adminOrgDeptForm.setOlc(olc);
				adminOrgDeptForm.setSameLevel(sameLevel);
				String hierarchyIds=session.getAttribute("hierarchyIds").toString();
				Integer curIndex=Integer.parseInt( session.getAttribute("curIndex").toString());
				String hierArr[]=hierarchyIds.split("@");
				String elementDetails=hierArr[curIndex];
				String Level=elementDetails.substring((elementDetails.lastIndexOf(",")+1), elementDetails.length());	
				String parentId=elementDetails.substring(0,elementDetails.lastIndexOf(","));
				model.addAttribute("enableSaveBtn",hierArr.length-1==curIndex);
				
				setLevelFormCommanProperties(adminOrgDeptForm,model,Integer.parseInt(Level),elementDetails,parentId,session,curIndex,departmentForms);
				return mav;
			}
			
			
			
			if (departmentForms == null) {
				departmentForms = new LinkedList<AdminOrgDeptForm>();
			}
			departmentForms.add(adminOrgDeptForm);
			
			session.setAttribute("storedDeptForms",departmentForms);
			if(session.getAttribute("hierarchyIds")!=null && session.getAttribute("curIndex")!=null){
				mav=new ModelAndView("PROCESS_LEVEL_WISE_FORM");
				adminOrgDeptForm=new AdminOrgDeptForm();
				adminOrgDeptForm.setOlc(olc);
				adminOrgDeptForm.setSameLevel(sameLevel);
				String hierarchyIds=session.getAttribute("hierarchyIds").toString();
				Integer curIndex=Integer.parseInt( session.getAttribute("curIndex").toString())+(sameLevel?0:1);
				String hierArr[]=hierarchyIds.split("@");
				String elementDetails=hierArr[curIndex];
				String Level=elementDetails.substring((elementDetails.lastIndexOf(",")+1), elementDetails.length());	
				String parentId=elementDetails.substring(0,elementDetails.lastIndexOf(","));
				model.addAttribute("enableSaveBtn",hierArr.length-1==curIndex);
				
				setLevelFormCommanProperties(adminOrgDeptForm,model,Integer.parseInt(Level),elementDetails,parentId,session,curIndex,departmentForms);
			}
		} catch (Exception e) {
			LOG.error("restructureDepartmentLevel(processToRebuildHierarchy)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	
	@RequestMapping(value="/saveBuildTree",method= RequestMethod.POST)
	public ModelAndView saveBuildTree(HttpSession session,@ModelAttribute("adminOrgDeptForm") AdminOrgDeptForm adminOrgDeptForm,final BindingResult binding,HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav =null;
		try {
			boolean isOrganization = false;
			
			if(session.getAttribute("olc")==null){
				
			}
			Integer olc=Integer.parseInt(session.getAttribute("olc").toString());
			boolean extendOneChild=false;
			if(session.getAttribute("hierarchyIds")!=null ){
				String hierarchyIds[]=session.getAttribute("hierarchyIds").toString().split("@");
				extendOneChild=hierarchyIds.length==1;
			}
			LinkedList<AdminOrgDeptForm> departmentForms=null;
			if(extendOneChild && !adminOrgDeptForm.isSameLevel()){
				departmentForms = new LinkedList<AdminOrgDeptForm>();
				}else{
				 departmentForms = (LinkedList<AdminOrgDeptForm>) session.getAttribute("storedDeptForms");
			}
			adminValidator.validate(adminOrgDeptForm, binding, isCenterUser, isOrganization, session);
			
			if (binding.hasErrors()) {
				mav=new ModelAndView("PROCESS_LEVEL_WISE_FORM");
				adminOrgDeptForm.setOlc(olc);
				String hierarchyIds=session.getAttribute("hierarchyIds").toString();
				Integer curIndex=Integer.parseInt( session.getAttribute("curIndex").toString());
				String hierArr[]=hierarchyIds.split("@");
				String elementDetails=hierArr[curIndex];
				String Level=elementDetails.substring((elementDetails.lastIndexOf(",")+1), elementDetails.length());	
				String parentId=elementDetails.substring(0,elementDetails.lastIndexOf(","));
				model.addAttribute("enableSaveBtn",hierArr.length-1==curIndex);
				
				setLevelFormCommanProperties(adminOrgDeptForm,model,Integer.parseInt(Level),elementDetails,parentId,session,curIndex,departmentForms);
				return mav;
			}
			
			departmentForms.add(adminOrgDeptForm);
			
			clearSessionAttributesRestructure(session);
			boolean isSuccess = adminDeprtmentService.saveRestructureDepartmentLevel(departmentForms, stateCode, isCenterUser, olc,userId);
			mav = new ModelAndView("success");
			String deptOrg = "Department ";
			if (adminOrgDeptForm.isOrganizationFlow()) {
				deptOrg = "Organization ";
			}
			if (isSuccess) {
				mav.addObject("msgid", deptOrg.concat(" is Extended Successfully."));

			} else {
				mav.addObject("msgid", deptOrg.concat(" Extended Process Failed."));
			}
			clearExtendSessionAttributes(session);
		} catch (Exception e) {
			LOG.error("restructureDepartmentLevel(processToRebuildHierarchy)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	
	private void setLevelFormCommanProperties(AdminOrgDeptForm adminOrgDeptForm,Model model,int level,String elementDetails,String parentId,HttpSession session,Integer curIndex,LinkedList<AdminOrgDeptForm> departmentForms)throws Exception{
		
		List<ExistDeptHierarchy> existDeptList=adminDeprtmentService.getExistDeptHierarchy(adminOrgDeptForm.getOlc(),null,Boolean.FALSE);
		model.addAttribute("exitParentList",getExistParentList(existDeptList,parentId));
		StringBuilder officeListArr=new StringBuilder();
		officeListArr=getExistOfficeList(existDeptList,elementDetails,officeListArr);
		if(departmentForms!=null){
		model.addAttribute("newParentList",getNewCreatedParentList(departmentForms,parentId));
		officeListArr=getNewCreatedOfficeList(departmentForms,elementDetails,officeListArr);
		}
		String officeList=officeListArr.toString();
		model.addAttribute("officeListDetails",officeList!=null && !("").equals(officeList)?officeList.substring(0,(officeList.lastIndexOf("#"))):"");
		model.addAttribute("isOrganizationFlow",Boolean.FALSE);
		
		String[] LevlDetArr=adminDeprtmentService.getLevelWiseTreeDetails(stateCode, elementDetails);
		String parentHierarchy=LevlDetArr[0];
		adminOrgDeptForm.setParentHierarchy(parentHierarchy);
		adminOrgDeptForm.setDpName(LevlDetArr[1]);
		adminOrgDeptForm.setIpName(LevlDetArr[2]);
		adminOrgDeptForm.setVpName(LevlDetArr[3]);
		adminOrgDeptForm.setuName(LevlDetArr[4]);
		adminOrgDeptForm.setHierarchyIds(elementDetails);
		adminOrgDeptForm.setUnitLevelCode(level);
		adminOrgDeptForm.setAdminUnitLevelName(getAdminUnitLevelName(parentHierarchy,level));
		
		String pageAccessLevel=getPageAccessLevel(level);
		model.addAttribute("pageAccessLevel", pageAccessLevel);
		model.addAttribute("isCenterUser", isCenterUser);
		
		if (isCenterUser && !"A".equalsIgnoreCase(pageAccessLevel)) {
			model.addAttribute("statelist", stateService.getStateSourceList());
		}
		if ("A".equalsIgnoreCase(pageAccessLevel)) {
			model.addAttribute("adminEntities", adminDeprtmentService.getAdminEntities(level));
		}
		if("X".equalsIgnoreCase(pageAccessLevel) || "Y".equalsIgnoreCase(pageAccessLevel) || "Z".equalsIgnoreCase(pageAccessLevel)){
			model.addAttribute("districtPanchayatList", adminDeprtmentService.getDistrictPanchayatList(1,stateCode, null));
		}
		if("U".equalsIgnoreCase(pageAccessLevel)){
			model.addAttribute("urbanList", adminDeprtmentService.getDistrictPanchayatList((-level),stateCode, null));
		}
		
		if ("D".equalsIgnoreCase(pageAccessLevel) || "T".equalsIgnoreCase(pageAccessLevel) || "B".equalsIgnoreCase(pageAccessLevel) || "V".equalsIgnoreCase(pageAccessLevel)) {
			model.addAttribute("distList", districtService.getDistrictListbyStateCodeForLocalBody(stateCode));
		}
		model.addAttribute("adminOrgDeptForm",adminOrgDeptForm);
		session.setAttribute("curIndex", curIndex);
	}
	
	private String getAdminUnitLevelName(String patteren,Integer matchCode)throws Exception{
		String adminUnitLevelName=null;
		Scanner scanner = new Scanner(patteren);
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			//check for sub District and block
			String temp=scanner.next();
			if(temp!=null && !("").equals(temp)){
				String adminLvlArr[]=	temp.split("#");
				if(Integer.parseInt(adminLvlArr[1])==matchCode){
					adminUnitLevelName=adminLvlArr[0];
					break;
				}
			}
			
			
		}
		scanner.close();
		return adminUnitLevelName;
	}
	
	private  StringBuilder  getNewCreatedOfficeList(LinkedList<AdminOrgDeptForm> departmentForms,String elementDetails,StringBuilder officeListArr)throws Exception{
			for(AdminOrgDeptForm adminOrgDeptForm:departmentForms){
				if(adminOrgDeptForm.getHierarchyIds()!=null && !("").equals(adminOrgDeptForm.getHierarchyIds().trim()) && (elementDetails).equals(adminOrgDeptForm.getHierarchyIds().trim())){
					officeListArr.append(adminOrgDeptForm.getDepartmentNameEnglish()+"#");
				}
			
			}
			return officeListArr;
		}
	
	private StringBuilder getExistOfficeList(List<ExistDeptHierarchy> existDeptList,String elementDetails,StringBuilder officeListArr)throws Exception{
		for(ExistDeptHierarchy existDeptHierarchy:existDeptList){
			if(elementDetails.trim().equals((existDeptHierarchy.getParentId()+","+existDeptHierarchy.getChildId()))){
					officeListArr.append(existDeptHierarchy.getChildName()+"#");
			}
			
		}
		return officeListArr;
	}
	
	
	private List<ExistDeptHierarchy> getExistParentList(List<ExistDeptHierarchy> existDeptList,String parentId)throws Exception{
		List<ExistDeptHierarchy> exitParentList=new ArrayList<ExistDeptHierarchy>();
		for(ExistDeptHierarchy existDeptHierarchy:existDeptList){
		
			if(existDeptHierarchy.getChildId()!=null &&  parentId!=null && ("1").equals(parentId.trim()) && ("1").equals(existDeptHierarchy.getChildId().toString())){
				exitParentList.add(existDeptHierarchy);
			}else if(existDeptHierarchy.getChildId()!=null &&  parentId!=null && ("0").equals(parentId.trim()) && ("0").equals(existDeptHierarchy.getChildId().toString())){
				exitParentList.add(existDeptHierarchy);
			}
			else{
				if(parentId.trim().equals(existDeptHierarchy.getParentId()+","+existDeptHierarchy.getChildId())){
					exitParentList.add(existDeptHierarchy);
				}
			}
			
		}
		return exitParentList;
	}
	
	private  List<AdminOrgDeptForm>  getNewCreatedParentList(LinkedList<AdminOrgDeptForm> departmentForms,String parentId)throws Exception{
	List<AdminOrgDeptForm> newParentList=new ArrayList<AdminOrgDeptForm>(); 
		for(AdminOrgDeptForm adminOrgDeptForm:departmentForms){
			if(adminOrgDeptForm.getHierarchyIds()!=null && !("").equals(adminOrgDeptForm.getHierarchyIds().trim()) && (parentId).equals(adminOrgDeptForm.getHierarchyIds().trim())){
				newParentList.add(adminOrgDeptForm);
			}
		
		}
		return newParentList;
	}
	private String getPageAccessLevel(Integer level)throws Exception{
		String pageAccessLevel="A";
		if(level<0){
			pageAccessLevel="U";
		}
		switch(level){
		case 1:
			pageAccessLevel="S";
			break;
			
		case 2:
			pageAccessLevel="D";
			break;
			
		case 3:
			pageAccessLevel="T";
			break;
			
		case 4:
			pageAccessLevel="V";
			break;
			
		case 5:
			pageAccessLevel="B";
			break;
			
		case -1:
			pageAccessLevel="X";
			break;
			
		case -2:
			pageAccessLevel="Y";
			break;
			
		case -3:
			pageAccessLevel="Z";
			break;
			
		}
		
		return pageAccessLevel;
	}
	

	/**
	 * The{@code closeDepartmentProcess} used to cancel current process and
	 * forward request to home page.
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/closeExtendDepartmentProcess", method = RequestMethod.POST)
	public ModelAndView closeExtendDepartmentProcess(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			clearExtendSessionAttributes(session);
			mav = new ModelAndView("redirect:home.htm");
		} catch (Exception e) {
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}
	
	private void  clearExtendSessionAttributes(HttpSession session)throws Exception{
		session.removeAttribute("hierarchyIds");
		session.removeAttribute("olc");
		session.removeAttribute("storedDeptForms");
		session.removeAttribute("curIndex");
	}
	
	
	
	
	@RequestMapping(value = "/rebuildSetupAdminDepartmentCenter", method = RequestMethod.GET)
	public ModelAndView extendExistDeptCenter(HttpSession session,@ModelAttribute("extendDpet") RestructureDeptLevelForm restructureDeptLevelForm, HttpServletRequest request, HttpServletResponse response, Model model){
		ModelAndView mav = new ModelAndView("BUILD_TREE_CENTER");
		try {
			setGlobalParams(session);
			clearExtendSessionAttributes(session);
			session.setAttribute("isOrganizationFlow", Boolean.FALSE);
			model.addAttribute("deptList", adminDeprtmentService.getOrganizationbyCriteria(stateCode,Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_DEPT.toString())));
		} catch (Exception e) {
			LOG.error("restructureDepartmentLevel(processToRebuildHierarchy)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	@RequestMapping(value = "/buildTreeCenter", method = RequestMethod.POST)
	public ModelAndView buildTreeCenter(HttpSession session,@ModelAttribute("buildTree") RestructureDeptLevelForm restructureDeptLevelForm, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = new ModelAndView("BUILD_TREE_CENTER");
		try {
			setGlobalParams(session);
			restructureDeptLevelForm.setSlc(stateCode);
			model.addAttribute("existDeptHierarchy",adminDeprtmentService.getExistDeptHierarchy(restructureDeptLevelForm.getOlc(),stateCode,Boolean.TRUE));
		} catch (Exception e) {
			LOG.error("restructureDepartmentLevel(processToRebuildHierarchy)--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
}
