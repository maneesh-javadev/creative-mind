package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.DesignationLevelSortorder;
import in.nic.pes.lgd.bean.Designationpojo;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.LgdDesignation;
import in.nic.pes.lgd.bean.LgdDesignationPK;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.constant.DesignationConstant;
import in.nic.pes.lgd.forms.DesignationReportingForm;
import in.nic.pes.lgd.service.LgdDesignationService;
import in.nic.pes.lgd.service.LocalGovtSetupService;
import in.nic.pes.lgd.service.OrganizationService;
import in.nic.pes.lgd.validator.DepartmentDesignationValidator;
import in.nic.pes.lgd.validator.ManageDesignationDeptValidator;
import in.nic.pes.lgd.validator.OrgnisationMasterValidation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class DesignnationController { // NO_UCD (unused code)
	
	@Autowired
	private LocalGovtSetupService localGovtSetupService;
	
	@Autowired
	private LgdDesignationService designationService;

	@Autowired
	private OrganizationService organisationService;
	
	private Integer stateCode 	= null;
	
	private Integer userId;
	
	private final String ELECTIVE_REPRESENTATIVE = "E";
	private final String OFFICIAL = "O";
	private final String ORGANIZATION_DEPARTMENT = "D";
	private final String PANCHYAT_CODE = "P";
	private final String URBAN_CODE = "U";
	private final String TRADITIONAL_CODE = "T";
	
	private void init(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		userId = sessionObject.getUserId().intValue();
	}

	private String accessURL(HttpSession session, LgdDesignation lgdDesignation, Model model, String designationType, String lbType) throws Exception{
		init(session);
		lgdDesignation.setDesignationType(designationType);
		lgdDesignation.setFlowName("designationEROfficial");
		List<GetLocalGovtSetup> tiersetup = localGovtSetupService.isStateSetup(stateCode, lbType.charAt(0));
		if (tiersetup.size() > 0) {
			model.addAttribute("tiersetup", tiersetup);
			model.addAttribute("lbType", lbType);
			model.addAttribute("isViewOnly",true);
			return "lgdDesignation";
		}
		model.addAttribute("msgid", "You Didn't Configure The Local Government, Please Configure The System. !");
		return "success";
	}
	
	
	@RequestMapping(value = "/designation_hierarchy_elected_panchayat", method = RequestMethod.GET)
	public String designationHierarchyPanchayatGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
												   Model model, 
												   HttpSession httpSession,
												   HttpServletRequest request) {
		try {
			return accessURL(httpSession, lgdDesignation, model, ELECTIVE_REPRESENTATIVE, PANCHYAT_CODE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	@RequestMapping(value = "/designation_hierarchy_elected_traditional", method = RequestMethod.GET)
	public String designationHierarchyTraditionalGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
			   										 Model model, 
													 HttpSession httpSession,
													 HttpServletRequest request) {
		try {
			return accessURL(httpSession, lgdDesignation, model, ELECTIVE_REPRESENTATIVE, TRADITIONAL_CODE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}

	@RequestMapping(value = "/designation_hierarchy_elected_urban", method = RequestMethod.GET)
	public String designationHierarchyUrbanGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
											   Model model, 
											   HttpSession httpSession,
											   HttpServletRequest request) {
		try {
			return accessURL(httpSession, lgdDesignation, model, ELECTIVE_REPRESENTATIVE, URBAN_CODE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	@RequestMapping(value = "/designation_hierarchy_official_panchayat", method = RequestMethod.GET)
	public String designationHierarchyOfficialPanchayatGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
														   Model model, 
														   HttpSession httpSession,
														   HttpServletRequest request) {
		try {
			return accessURL(httpSession, lgdDesignation, model, OFFICIAL, PANCHYAT_CODE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	
	@RequestMapping(value = "/designation_hierarchy_official_traditional", method = RequestMethod.GET)
	public String designationHierarchyOfficialTraditionalGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
														     Model model, 
														     HttpSession httpSession,
														     HttpServletRequest request) {
		try {
			return accessURL(httpSession, lgdDesignation, model, OFFICIAL, TRADITIONAL_CODE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	@RequestMapping(value = "/designation_hierarchy_official_urban", method = RequestMethod.GET)
	public String designationHierarchyOfficialUrbanGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
												       Model model, 
												       HttpSession httpSession,
												       HttpServletRequest request) {
		try {
			return accessURL(httpSession, lgdDesignation, model, OFFICIAL, URBAN_CODE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	
	@RequestMapping(value = "/designation_entry", method = RequestMethod.POST)
	public String addDesignation(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
							     BindingResult br,
							     Model model, 
							     HttpSession httpSession,
							     HttpServletRequest request, 
							     @RequestParam("lbType") String lbType) {
		try {
			init(httpSession);
			OrgnisationMasterValidation orgValidator = new OrgnisationMasterValidation();
			orgValidator.validate(lgdDesignation, br);
			if (br.hasErrors()) {
				model.addAttribute("lgdDesignation", lgdDesignation);
				model.addAttribute("isError", true);
				List<GetLocalGovtSetup> tiersetup = localGovtSetupService.isStateSetup(stateCode, lbType.charAt(0));
				if (tiersetup.size() > 0) {
					model.addAttribute("tiersetup", tiersetup);
					model.addAttribute("lbType", lbType);
					return "lgdDesignation";
				}
				model.addAttribute("msgid", "You Didn't Configure The Local Government, Please Configure The System. !");
				return "success";
			}
			Integer tierSetupCode = lgdDesignation.getTierSetupCode();
			String designationType = lgdDesignation.getDesignationType();
			boolean isNextVesrionTop=false;
			boolean isChangedTop = true;
			int sortOrder = 0;
			//Saving Top Designation
			LgdDesignation topdesignation = new LgdDesignation();
			if(lgdDesignation.getLgdDesignationPK().getDesignationCode() != null){
				topdesignation = designationService.getDesignationById(lgdDesignation.getLgdDesignationPK().getDesignationCode());
				if(!topdesignation.getDesignationName().equals(lgdDesignation.getDesignationName())){
					isNextVesrionTop=true;
					
				}
				if(!topdesignation.getDesignationName().equals(lgdDesignation.getDesignationName())){
					isChangedTop = true;
				}else if(!topdesignation.getDesignationNameLocal().equals(lgdDesignation.getDesignationNameLocal())){
					isChangedTop = true;
				}else{
					isChangedTop = false;
				}
			}
			if(isChangedTop){
				topdesignation.setTierSetupCode(tierSetupCode);
				topdesignation.setDesignationName(lgdDesignation.getDesignationName());
				topdesignation.setDesignationNameLocal(lgdDesignation.getDesignationNameLocal());
				topdesignation.setDesignationType(lgdDesignation.getDesignationType());
				topdesignation.setIsActive(true);
				topdesignation.setIsMultiple(false);
				topdesignation.setIsTopDesignation(true);
				topdesignation.setIsContractPermanent(true);
				topdesignation.setSortOrder(sortOrder);
				topdesignation.setNextVersion(isNextVesrionTop);
				topdesignation.setEffectiveDate(new Date());
				if(lgdDesignation.getLgdDesignationPK().getDesignationCode() != null){
					if(isNextVesrionTop){
						
						topdesignation.setLastupdated(new Date());
						topdesignation.setLastupdatedby(userId);
						Integer currentVersion=topdesignation.getLgdDesignationPK().getDesignationVersion();
						topdesignation.getLgdDesignationPK().setDesignationVersion(currentVersion+1);	
						topdesignation.setStateCode(stateCode);
						topdesignation.setOpeationCode(Integer.parseInt(DesignationConstant.RENAME_DESIGNATION_NAME_OPERATION_CODE.toString()));
						topdesignation.setDescription("English Name of designation '{DESIGNATION_NAME}' having designation code '{DESIGNATION_CODE}' of '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type has been updated.");
						
					}
					
				}else{
					
					topdesignation.setCreatedby(userId);
					topdesignation.setCreatedon(new Date());
					topdesignation.setLastupdated(new Date());
					topdesignation.setLastupdatedby(userId);
					LgdDesignationPK lgdDesignationPK=new LgdDesignationPK();
					
					lgdDesignationPK.setDesignationVersion(1);
					lgdDesignationPK.setDesignationCode(designationService.getNextDesignationCodeFromLgdDesignation());
					topdesignation.setLgdDesignationPK(lgdDesignationPK);
					topdesignation.setStateCode(stateCode);
					topdesignation.setOpeationCode(Integer.parseInt(DesignationConstant.CREATE_NEW_DESIGNATION_OPERATION_CODE.toString()));
					topdesignation.setDescription("New Designation '{DESIGNATION_NAME}' having designation code '{DESIGNATION_CODE}' is created for '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type.");
					
				}
				
				designationService.saveOrUpdate(topdesignation); 
			}
			String otherDesignations = lgdDesignation.getOtherDesignations();
			List<LgdDesignation> removed = new ArrayList<LgdDesignation>();
			if(otherDesignations != null && !"".equals(otherDesignations)){
				List<LgdDesignation> existingDesignations = designationService.getExistingDesignations(tierSetupCode, designationType);
				
				Scanner scanner = new Scanner(otherDesignations);
				scanner.useDelimiter("@@");
				while(scanner.hasNext()){
					isNextVesrionTop=false;
					sortOrder++;
					String 	 rowvalues 			= scanner.next();
					String[] designationColumns	= rowvalues.split("\\##");
					Integer desCode = null;
					String inputDesCode = designationColumns[0];//Designation Code
					if(inputDesCode != null && !"".equals(inputDesCode)){
						desCode = Integer.parseInt(inputDesCode);
					}	
					LgdDesignation designationOthers = new LgdDesignation();
					if(desCode != null){
						
						designationOthers = designationService.getDesignationById(desCode);
						if((!designationOthers.getDesignationName().equals(designationColumns[1])) || (designationOthers.getSortOrder()!=sortOrder)){
							designationOthers.setLastupdated(new Date());
							designationOthers.setLastupdatedby(userId);
							Integer currentVersion=designationOthers.getLgdDesignationPK().getDesignationVersion();
							designationOthers.getLgdDesignationPK().setDesignationVersion(currentVersion+1);
							isNextVesrionTop=true;
							designationOthers.setStateCode(stateCode);
							if(!designationOthers.getDesignationName().equals(designationColumns[1])){
								designationOthers.setOpeationCode(Integer.parseInt(DesignationConstant.RENAME_DESIGNATION_NAME_OPERATION_CODE.toString()));
								designationOthers.setDescription("English Name of designation '{DESIGNATION_NAME}' having designation code '{DESIGNATION_CODE}' of '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type has been updated.");
							}else if(designationOthers.getSortOrder()!=sortOrder){
								designationOthers.setOpeationCode(Integer.parseInt(DesignationConstant.CHANGE_DESIGNATION_SORT_ORDER_OPERATION_CODE.toString()));
								designationOthers.setDescription("Sort order of designation {DESIGNATION_NAME} having designation code '{DESIGNATION_CODE}' of '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type has been updated.");
							}
							
						}
						
					}else{
						LgdDesignationPK lgdDesignationPK=new LgdDesignationPK();
						lgdDesignationPK.setDesignationVersion(1);
						lgdDesignationPK.setDesignationCode(designationService.getNextDesignationCodeFromLgdDesignation());
						designationOthers.setLgdDesignationPK(lgdDesignationPK);
						designationOthers.setStateCode(stateCode);
						designationOthers.setOpeationCode(Integer.parseInt(DesignationConstant.CREATE_NEW_DESIGNATION_OPERATION_CODE.toString()));
						designationOthers.setDescription("New Designation '{DESIGNATION_NAME}' having designation code '{DESIGNATION_CODE}' is created for '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type.");
					}
					designationOthers.setNextVersion(isNextVesrionTop);
					designationOthers.setTierSetupCode(tierSetupCode);
					designationOthers.setDesignationName(designationColumns[1]); //Designation Name
					designationOthers.setDesignationNameLocal(designationColumns[2]);//Designation Name in Local
					Boolean isMultiple = null;
					try{
						isMultiple = Boolean.parseBoolean(designationColumns[3]);//Multiple Designation
					}catch (Exception e) {
						isMultiple = null;
					}
					designationOthers.setIsMultiple(isMultiple);
					designationOthers.setDesignationType(designationType);
					designationOthers.setIsTopDesignation(false);
					designationOthers.setIsActive(true);
					designationOthers.setSortOrder(sortOrder);
					designationOthers.setCreatedby(userId);
					designationOthers.setCreatedon(new Date());
					designationOthers.setLastupdated(new Date());
					designationOthers.setLastupdatedby(userId);
					designationOthers.setEffectiveDate(new Date());
					
					Boolean isContractPerma = null;
					try{
						isContractPerma = Boolean.parseBoolean(designationColumns[4]);//Multiple Designation
					}catch (Exception e) {
						isContractPerma = null;
					}
					designationOthers.setIsContractPermanent(isContractPerma);
					
					designationService.saveOrUpdate(designationOthers);
					
					for(LgdDesignation des : existingDesignations){
						if(des.getIsTopDesignation()){
							removed.add(des);
						}else if(des.getLgdDesignationPK().getDesignationCode().equals(designationOthers.getLgdDesignationPK().getDesignationCode())){
							removed.add(des);
						}
					}
				}
				scanner.close();
				existingDesignations.removeAll(removed);
				
				for(LgdDesignation deletedObj : existingDesignations){
					if(!designationService.isDesignationBeingUsed(deletedObj.getLgdDesignationPK().getDesignationCode())){
						LgdDesignation loadedRemoved = designationService.getDesignationById(deletedObj.getLgdDesignationPK().getDesignationCode());
						designationService.remove(loadedRemoved);
					}
				}
			}
			model.addAttribute("msgid", "Designation Saved/Modified Successfully, If referenced can not be Manipulated !");
			return "success";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	
	
	@RequestMapping(value = "/designaton_master_state", method = RequestMethod.GET)
	public String designationMasterStateGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation, Model model, HttpSession session, HttpServletRequest request) {
		try {
			init(session);
			lgdDesignation.setDesignationType(ORGANIZATION_DEPARTMENT);
			lgdDesignation.setFlowName("designationState");
			Integer slc = organisationService.getSlc(stateCode).get(0).getSlc();
			model.addAttribute("orgType",organisationService.getOrganizationTypeforAddDesig());
			model.addAttribute("slcCode", slc);
			return "lgdDesignationDepartment";
		}
		catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	@RequestMapping(value = "/designaton_master_center", method = RequestMethod.GET)
	public String designationMasterCenterGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation, Model model, HttpServletRequest request) {
		try {
			
			lgdDesignation.setDesignationType(ORGANIZATION_DEPARTMENT);
			lgdDesignation.setFlowName("designationCenter");
			model.addAttribute("orgType", organisationService.getOrganizationTypeforAddDesig());
			return "lgdDesignationDepartmentCenter";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	/*Added by Pooja on 1 May 2015*/
	@RequestMapping(value = "/get_designation_department", method = RequestMethod.POST)
	public String getDesignationDepartment(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation, BindingResult br, Model model, HttpSession httpSession, HttpServletRequest request) {
		try {
			init(httpSession);
			DepartmentDesignationValidator deptDesignationValidator = new DepartmentDesignationValidator();
			deptDesignationValidator.validate(lgdDesignation, br);
			if (br.hasErrors()) {
				String flow = lgdDesignation.getFlowName();
				String returnUrl = null;
				if ("designationCenter".equals(flow)) {
					returnUrl = "lgdDesignationDepartmentCenter";
					} else if ("designationState".equals(flow)) {
					Integer slc = organisationService.getSlc(stateCode).get(0).getSlc();
					model.addAttribute("slcCode", slc);
					returnUrl = "lgdDesignationDepartment";
					}
				lgdDesignation.setDesignationType(ORGANIZATION_DEPARTMENT);
				model.addAttribute("orgType", organisationService.getOrganizationTypeforAddDesig());
				model.addAttribute("lgdDesignation", lgdDesignation);
				model.addAttribute("isError", true);
				return returnUrl;
			}

			Integer olc = lgdDesignation.getOlc();
			model.addAttribute("olc", olc);
			model.addAttribute("lgdDesignation", lgdDesignation);

			return "manageDesignationDepartment";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}


	@RequestMapping(value = "/save_designation_department", method = RequestMethod.POST)
	public String saveDesignationDepartment(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
									   BindingResult br,
									   Model model, 
									   HttpSession httpSession,
									   HttpServletRequest request) {
		
		try {
			init(httpSession);
			String designations = request.getParameter("designations");
			lgdDesignation.setOtherDesignations(designations);
			ManageDesignationDeptValidator manageDesignationDeptValidator = new ManageDesignationDeptValidator();
			manageDesignationDeptValidator.validate(lgdDesignation, br);
			if (br.hasErrors()) {
				Integer olc = lgdDesignation.getOlc();
				model.addAttribute("olc", olc);
				model.addAttribute("lgdDesignation", lgdDesignation);
				model.addAttribute("isError", true);
				return "manageDesignationDepartment";
			}
			boolean NameChange=false;
			boolean LevelChange=false;
			boolean AddNew=false;
			LgdDesignation deleteLgdDesignation=null;
			List<LgdDesignation> deleteNodeList = new ArrayList<LgdDesignation>();
			boolean isNextVesrion=false;
			//boolean addDesignationFlag=false;
			Integer olc = lgdDesignation.getOlc();
			String designationType = lgdDesignation.getDesignationType();
			Integer locatedAtLevel = 0;
			LgdDesignation lgdDesign = null;
			List<LgdDesignation> existDesignationsList =designationService.getExistingDesignationsForDepartment(olc);
			if(existDesignationsList!=null && !existDesignationsList.isEmpty()){
				/*if(designations.split("@@").length>existDesignationsList.size()){
					addDesignationFlag=true;
					
				}*/
				
				for(int i=0;i<existDesignationsList.size();i++){
					if(checkDeleteNode(existDesignationsList.get(i).getDesignationCode(),designations)){
						deleteLgdDesignation=existDesignationsList.get(i);
						deleteLgdDesignation.setIsActive(false);
						deleteLgdDesignation.setLastupdated(new Date());
						deleteLgdDesignation.setLastupdatedby(userId);
						deleteLgdDesignation.setNextVersion(true);
						deleteLgdDesignation.setOpeationCode(null);
						deleteNodeList.add(deleteLgdDesignation);
					}
				}
			}
			
			
			
			Scanner scanner = new Scanner(designations);
				scanner.useDelimiter("@@");
				
				while(scanner.hasNext()){
					isNextVesrion=false;
					String 	 rowvalues = scanner.next();
					String[] designationColumns	= rowvalues.split("\\##");
					Integer desCode = null;
					String inputDesCode = designationColumns[0];//Designation Code
					if(inputDesCode != null && !"".equals(inputDesCode)){
						desCode = Integer.parseInt(inputDesCode);
					}
					
					AddNew=false;
					List<DesignationLevelSortorder> existLevelList = null;
					
					AddNew=  desCode==null?true:false;
					if(desCode != null){
						lgdDesign = designationService.getDesignationById(desCode);
						existLevelList = designationService.getExistingDesignationLevel(lgdDesign);
						NameChange=!lgdDesign.getDesignationName().equals(designationColumns[1]);
						LevelChange= this.checkLevelChange(existLevelList,designationColumns[3]);
						 //       checkNewNode(desCode,existDesignationsList);
						if((NameChange) ||LevelChange || AddNew ){
							lgdDesign.setLastupdated(new Date());
							lgdDesign.setLastupdatedby(userId);
							Integer currentVersion=lgdDesign.getLgdDesignationPK().getDesignationVersion();
							lgdDesign.getLgdDesignationPK().setDesignationVersion(currentVersion+1);
							isNextVesrion=true;
							lgdDesign.setCreatedby(userId);
							lgdDesign.setCreatedon(new Date());
							lgdDesign.setLastupdated(new Date());
							lgdDesign.setLastupdatedby(userId);
							
							
						}
						
						}
					else
					{
						
						lgdDesign = new LgdDesignation();
						
						lgdDesign.setIsTopDesignation(false);
						LgdDesignationPK lgdDesignationPK=new LgdDesignationPK();
						lgdDesignationPK.setDesignationVersion(1);
						lgdDesignationPK.setDesignationCode(designationService.getNextDesignationCodeFromLgdDesignation());
						lgdDesign.setLgdDesignationPK(lgdDesignationPK);
						lgdDesign.setCreatedby(userId);
						lgdDesign.setCreatedon(new Date());
						lgdDesign.setLastupdated(new Date());
						lgdDesign.setLastupdatedby(userId);
					}
					if(NameChange && LevelChange ){
						lgdDesign.setOpeationCode(Integer.parseInt(DesignationConstant.MANAGE_DESIGNATION_OPERATION_CODE.toString()));
						lgdDesign.setDescription("English Name of designation and Level details of '{DESIGNATION_NAME}' having designation code '{DESIGNATION_CODE}' of '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type has been updated.");
					}else if(NameChange){
						lgdDesign.setOpeationCode(Integer.parseInt(DesignationConstant.RENAME_DESIGNATION_NAME_OPERATION_CODE.toString()));
						lgdDesign.setDescription("English Name of designation '{DESIGNATION_NAME}' having designation code '{DESIGNATION_CODE}' of '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type has been updated.");
					}else if(LevelChange){
						lgdDesign.setOpeationCode(Integer.parseInt(DesignationConstant.ADD_REMOVE_DESIGNATION_LEVEL_OPERATION_CODE.toString()));
						lgdDesign.setDescription("Designation '{DESIGNATION_NAME}' having designation code '{DESIGNATION_CODE}' of '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type is added for a new level/remove from a level.");
					}else if(AddNew){
						lgdDesign.setOpeationCode(Integer.parseInt(DesignationConstant.CREATE_NEW_DESIGNATION_OPERATION_CODE.toString()));
						lgdDesign.setDescription("New Designation '{DESIGNATION_NAME}' having designation code '{DESIGNATION_CODE}' is created for '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type.");
					}
					lgdDesign.setStateCode(stateCode);
					lgdDesign.setNextVersion(isNextVesrion);
					lgdDesign.setEffectiveDate(new Date());
					lgdDesign.setOlc(olc);
					lgdDesign.setDesignationName(designationColumns[1]); //Designation Name
					lgdDesign.setDesignationNameLocal(designationColumns[2]);//Designation Name in Local
					Boolean isMultiple = null;
					try{
						isMultiple = Boolean.parseBoolean(designationColumns[4]); //Is Multiple Designation
					}catch (Exception e) {
						isMultiple = null;
					}
					lgdDesign.setIsMultiple(isMultiple);
					lgdDesign.setDesignationType(designationType);
					lgdDesign.setIsActive(true);
					Boolean isContractPerma = null;
					try{
						isContractPerma = Boolean.parseBoolean(designationColumns[5]);//Is Contract Permanent
					}catch (Exception e) {
						isContractPerma = null;
					}
					lgdDesign.setIsContractPermanent(isContractPerma);
					List<DesignationLevelSortorder> addedLevelList = new ArrayList<DesignationLevelSortorder>();
					/*List<DesignationLevelSortorder> listForUpdate = new ArrayList<DesignationLevelSortorder>();
					List<DesignationLevelSortorder> listForInsert = new ArrayList<DesignationLevelSortorder>();
					List<DesignationLevelSortorder> listForIsActiveUpdate = new ArrayList<DesignationLevelSortorder>();
					List<Integer> selectLevels = new ArrayList<Integer>();
					List<Integer> updateLevels = new ArrayList<Integer>();
					List<Integer> existLevels = new ArrayList<Integer>();
					List<Integer> isActiveFalseLevels = new ArrayList<Integer>();*/
					
					String levels = designationColumns[3];
			/*		if (existLevelList != null) {
						if (levels != null && !"".equals(levels)) {

							String[] selectedLevels = levels.split(",");
							
							DesignationLevelSortorder designationLevelSortorder = null;
							if (selectedLevels != null && selectedLevels.length > 0) {
								for (String strLevelId : selectedLevels) {

									designationLevelSortorder = new DesignationLevelSortorder();
									designationLevelSortorder.setLgdDesignationCode(lgdDesign);
									designationLevelSortorder.setOrgLocatedLevelCode(Integer.parseInt(strLevelId));
									locatedAtLevel = designationService.getLocatedAtLevel(Integer.parseInt(strLevelId));
									designationLevelSortorder.setLocatedAtLevel(locatedAtLevel);
									designationLevelSortorder.setIsActive(true);
									listForInsert.add(designationLevelSortorder);
									selectLevels.add(Integer.parseInt(strLevelId));
									updateLevels.add(Integer.parseInt(strLevelId));
								}
							}
						}
					for( DesignationLevelSortorder els : existLevelList)
						{
							existLevels.add(els.getOrgLocatedLevelCode());
							isActiveFalseLevels.add(els.getOrgLocatedLevelCode());
						}
						
						updateLevels.retainAll(existLevels);
						selectLevels.removeAll(updateLevels);
						isActiveFalseLevels.removeAll(updateLevels);
						listForUpdate = existLevelList;
						if(updateLevels!=null && updateLevels.size()>0)
						{
							for(int i=0;i<listForUpdate.size();i++)
							{
								for(int j=0;j<updateLevels.size();j++)
								{
									if(listForUpdate.get(i).getOrgLocatedLevelCode().equals(updateLevels.get(j)))
									{
										if(!listForUpdate.get(i).getIsActive())
										{
											listForUpdate.get(i).setIsActive(true);
										}
										
										addedLevelList.add(listForUpdate.get(i));
									}
								}
							}
						}
						listForIsActiveUpdate = existLevelList;
						DesignationLevelSortorder desigLevelSortOrder = null;
						if (selectLevels != null && selectLevels.size() > 0) {
							for (int i = 0; i < listForInsert.size(); i++ ) {
								for(int j=0;j<selectLevels.size();j++)
								{
									if(listForInsert.get(i).getOrgLocatedLevelCode().equals(selectLevels.get(j))){
								desigLevelSortOrder = new DesignationLevelSortorder();
								desigLevelSortOrder.setLgdDesignationCode(lgdDesign);
								desigLevelSortOrder.setOrgLocatedLevelCode(listForInsert.get(i).getOrgLocatedLevelCode());
								locatedAtLevel = designationService.getLocatedAtLevel(listForInsert.get(i).getOrgLocatedLevelCode());
								desigLevelSortOrder.setLocatedAtLevel(locatedAtLevel);
								desigLevelSortOrder.setIsActive(true);
								addedLevelList.add(desigLevelSortOrder);
									}
								}
							}
						}
						if (listForIsActiveUpdate != null && listForIsActiveUpdate.size() > 0) {
							for (int i = 0; i < listForIsActiveUpdate.size(); i++) {
								for(int j=0;j<isActiveFalseLevels.size();j++)
								{
									if(listForIsActiveUpdate.get(i).getOrgLocatedLevelCode().equals(isActiveFalseLevels.get(j))){
										listForIsActiveUpdate.get(i).setIsActive(false);
										addedLevelList.add(listForIsActiveUpdate.get(i));
									}
								}
							}
						}
						
					} 
					else {*/
						if (levels != null && !"".equals(levels)) {

							String[] selectedLevels = levels.split(",");

							DesignationLevelSortorder designationLevelSortorder = null;
							Integer srNo=null;
							if (selectedLevels != null && selectedLevels.length > 0) {
								for (String strLevelId : selectedLevels) {
									if(isNextVesrion==false && existLevelList!=null && !existLevelList.isEmpty()){
										for(DesignationLevelSortorder obj:existLevelList){
											if(obj.getOrgLocatedLevelCode().equals(Integer.parseInt(strLevelId))){
												srNo=obj.getSrNo();
												break;
											}
									}
									
									}
									designationLevelSortorder = new DesignationLevelSortorder();
									designationLevelSortorder.setSrNo(srNo);
									designationLevelSortorder.setLgdDesignationCode(lgdDesign);
									designationLevelSortorder.setOrgLocatedLevelCode(Integer.parseInt(strLevelId));
									locatedAtLevel = designationService.getLocatedAtLevel(Integer.parseInt(strLevelId));
									designationLevelSortorder.setLocatedAtLevel(locatedAtLevel);
									designationLevelSortorder.setIsActive(true);
									addedLevelList.add(designationLevelSortorder);

								}
							}
						}
					//}
							
					lgdDesign.setDesignationLevelList(addedLevelList);
					designationService.saveOrUpdate(lgdDesign);	
				}
				for(LgdDesignation deletelgdDesignation :deleteNodeList){
					designationService.saveOrUpdate(deletelgdDesignation);	
				}
				scanner.close();
				model.addAttribute("msgid", "Designation Saved / Modified Successfully, If referenced can not be Manipulated !");
			return "success";
		} 
			
		catch (Exception e) {
			e.printStackTrace();
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	
	@RequestMapping(value = "/reorder_designaton_master_state", method = RequestMethod.GET)
	public String reorderDesignationMasterStateGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation, Model model, HttpSession session, HttpServletRequest request) {
		try {
			init(session);
			lgdDesignation.setDesignationType(ORGANIZATION_DEPARTMENT);
			lgdDesignation.setFlowName("designationState");
			Integer slc = organisationService.getSlc(stateCode).get(0).getSlc();
			model.addAttribute("orgType",organisationService.getOrganizationTypeforAddDesig());
			model.addAttribute("slcCode", slc);
			return "designationDepartmentReorder";
		}
		catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	@RequestMapping(value = "/reorder_designaton_master_center", method = RequestMethod.GET)
	public String reorderDesignationMasterCenterGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation, Model model, HttpServletRequest request) {
		try {
			lgdDesignation.setDesignationType(ORGANIZATION_DEPARTMENT);
			lgdDesignation.setFlowName("designationCenter");
			model.addAttribute("orgType", organisationService.getOrganizationTypeforAddDesig());
			return "designationDepartmentReorderCenter";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	@RequestMapping(value = "/get_designation_department_reorder", method = RequestMethod.POST)
	public String getDesignationDepartmentReorder(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation, BindingResult br, Model model, HttpSession httpSession, HttpServletRequest request) {
		try {
			init(httpSession);
			DepartmentDesignationValidator deptDesignationValidator = new DepartmentDesignationValidator();
			deptDesignationValidator.validate(lgdDesignation, br);
			if (br.hasErrors()) {
				String flow = lgdDesignation.getFlowName();
				String returnUrl = null;
				if ("designationCenter".equals(flow)) {
					returnUrl = "lgdDesignationDepartmentCenter";
					} else if ("designationState".equals(flow)) {
					Integer slc = organisationService.getSlc(stateCode).get(0).getSlc();
					model.addAttribute("slcCode", slc);
					returnUrl = "lgdDesignationDepartment";
					}
				lgdDesignation.setDesignationType(ORGANIZATION_DEPARTMENT);
				model.addAttribute("orgType", organisationService.getOrganizationTypeforAddDesig());
				model.addAttribute("lgdDesignation", lgdDesignation);
				model.addAttribute("isError", true);
				return returnUrl;
			}

			Integer olc = lgdDesignation.getOlc();
			model.addAttribute("olc", olc);
			Integer orgLocatedLevelCode = Integer.parseInt(request.getParameter("orgLocatedLevelCode"));
			model.addAttribute("orgLocatedLevelCode",orgLocatedLevelCode);
			String orgSpecificName = designationService.getOrgSpecificName(olc, orgLocatedLevelCode);
			model.addAttribute("orgSpecificName",orgSpecificName);
			model.addAttribute("lgdDesignation", lgdDesignation);

			return "reorderOrganizationDesignation";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}

	
	@RequestMapping(value = "/save_designation_department_reorder", method = RequestMethod.POST)
	public String saveDesignationDepartmentReorder(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
									   BindingResult br,
									   Model model, 
									   HttpSession httpSession,
									   HttpServletRequest request) {
		init(httpSession);
		List<DesignationLevelSortorder> desigLevelList = null;
		List<DesignationLevelSortorder> newDesigLevelList=null;
		LgdDesignation lgdDesign = null;
		DesignationLevelSortorder desigLevelSortOrder = null;
		try {
			String designations = request.getParameter("designations");
			Integer orgLocatedLevelCode = Integer.parseInt(request.getParameter("orgLocatedLevelCode"));
			int sortOrder = 0;
			Scanner scanner = new Scanner(designations);
				scanner.useDelimiter("##");
				while(scanner.hasNext()){
					sortOrder++;
					newDesigLevelList=new ArrayList<DesignationLevelSortorder>();
					Integer desCode = Integer.parseInt(scanner.next());
					lgdDesign = designationService.getDesignationById(desCode);
					Integer currentVersion=lgdDesign.getDesignationVersion();
					lgdDesign.getLgdDesignationPK().setDesignationVersion(currentVersion+1);
					lgdDesign.setNextVersion(Boolean.TRUE);
					lgdDesign.setOpeationCode(Integer.parseInt(DesignationConstant.CHANGE_DESIGNATION_SORT_ORDER_OPERATION_CODE.toString()));
					lgdDesign.setDescription("Sort order of designation {DESIGNATION_NAME} having designation code '{DESIGNATION_CODE}' of '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type has been updated.");
					desigLevelList = designationService.getdesignationLevelsbyDesigCode(desCode);
					
					for(int i=0;i<desigLevelList.size();i++)
					{
						if(desigLevelList.get(i).getOrgLocatedLevelCode().equals(orgLocatedLevelCode))
						{
							desigLevelList.get(i).setSortOrder(sortOrder);
							desigLevelSortOrder = desigLevelList.get(i);
							desigLevelSortOrder.setLgdDesignationCode(lgdDesign);
							desigLevelSortOrder.setSrNo(null);
							newDesigLevelList.add(desigLevelSortOrder);
						}else{
							desigLevelSortOrder = desigLevelList.get(i);
							desigLevelSortOrder.setLgdDesignationCode(lgdDesign);
							desigLevelSortOrder.setSrNo(null);
							newDesigLevelList.add(desigLevelSortOrder);
						}
					}
					if(sortOrder == 1)
					{
						lgdDesign.setIsTopDesignation(true);
					}
					lgdDesign.setDesignationLevelList(newDesigLevelList);
					designationService.saveOrUpdate(lgdDesign);	
					//designationService.saveOrUpdateReorderLevel(desigLevelSortOrder);
				}
				scanner.close();
				
			model.addAttribute("msgid", "Designations re-ordered Successfully.");
			return "success";
		} 
			
		catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	/*@RequestMapping(value = "/designation_entry_department", method = RequestMethod.POST)
	public String addDesignationDepartment(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
									   BindingResult br,
									   Model model, 
									   HttpSession httpSession,
									   HttpServletRequest request) {
		try {
			OrgnisationMasterValidation orgValidator = new OrgnisationMasterValidation();
			orgValidator.validate(lgdDesignation, br);
			if (br.hasErrors()) {
				String flow = lgdDesignation.getFlowName();
				String returnUrl = null;
				Integer type = null;
				if("designationCenter".equals(flow)){
					type = 0;
					returnUrl =  "lgdDesignationDepartmentCenter";
				}else if("designationState".equals(flow)){
					Integer slc = organisationService.getSlc(stateCode).get(0).getSlc();
					model.addAttribute("slcCode", slc);
					type = 1;
					returnUrl =  "lgdDesignationDepartment";
				} 
				model.addAttribute("orgType",organisationService.getOrganizationTypeforAddDesig());
				int ordType = lgdDesignation.getOrgType() != null ? lgdDesignation.getOrgType() : 0;
				model.addAttribute("organization",organisationService.getOrgbyTypeCodeAtlevel(ordType, type, 0));
				int olc = lgdDesignation.getOlc() != null ? lgdDesignation.getOlc() : 0;
				model.addAttribute("organizationLevel",organisationService.getOrgAtLevels(olc, type));
				model.addAttribute("lgdDesignation", lgdDesignation);
				model.addAttribute("isError", true);
				return returnUrl;
			}
			Integer olc = lgdDesignation.getOlc();
			Integer locatedLevel = lgdDesignation.getOrgLocatedLevelCode();
			String designationType = lgdDesignation.getDesignationType();
			boolean isChangedTop = true;
			int sortOrder = 0;
			//Saving Top Designation
			LgdDesignation topdesignation = new LgdDesignation();
			if(lgdDesignation.getLgdDesignationPK().getDesignationCode() != null){
				topdesignation = designationService.getDesignationById(lgdDesignation.getLgdDesignationPK().getDesignationCode());
				if(!topdesignation.getDesignationName().equals(lgdDesignation.getDesignationName())){
					isChangedTop = true;
				}else if(!topdesignation.getDesignationNameLocal().equals(lgdDesignation.getDesignationNameLocal())){
					isChangedTop = true;
				}else{
					isChangedTop = false;
				}
			}
			if(isChangedTop){
				topdesignation.setOlc(olc);
				topdesignation.setOrgLocatedLevelCode(locatedLevel);
				topdesignation.setDesignationName(lgdDesignation.getDesignationName());
				topdesignation.setDesignationNameLocal(lgdDesignation.getDesignationNameLocal());
				topdesignation.setDesignationType(lgdDesignation.getDesignationType());
				topdesignation.setIsActive(true);
				topdesignation.setIsMultiple(false);
				topdesignation.setIsTopDesignation(true);
				topdesignation.setIsContractPermanent(true);
				topdesignation.setSortOrder(sortOrder);
				designationService.saveOrUpdate(topdesignation); 
			}
			String otherDesignations = lgdDesignation.getOtherDesignations();
			List<LgdDesignation> removed = new ArrayList<LgdDesignation>();
			if(otherDesignations != null && !"".equals(otherDesignations)){
				List<LgdDesignation> existingDesignations = designationService.getExistingDesignationsForDepartment(olc, locatedLevel);
				Scanner scanner = new Scanner(otherDesignations);
				scanner.useDelimiter("@@");
				while(scanner.hasNext()){
					sortOrder++;
					String 	 rowvalues = scanner.next();
					String[] designationColumns	= rowvalues.split("\\##");
					Integer desCode = null;
					String inputDesCode = designationColumns[0];//Designation Code
					if(inputDesCode != null && !"".equals(inputDesCode)){
						desCode = Integer.parseInt(inputDesCode);
					}	
					LgdDesignation designationOthers = new LgdDesignation();
					if(desCode != null){
						designationOthers = designationService.getDesignationById(desCode);
					}
					designationOthers.setOlc(olc);
					designationOthers.setOrgLocatedLevelCode(locatedLevel);
					designationOthers.setDesignationName(designationColumns[1]); //Designation Name
					designationOthers.setDesignationNameLocal(designationColumns[2]);//Designation Name in Local
					Boolean isMultiple = null;
					try{
						isMultiple = Boolean.parseBoolean(designationColumns[3]);//Multiple Designation
					}catch (Exception e) {
						isMultiple = null;
					}
					designationOthers.setIsMultiple(isMultiple);
					designationOthers.setDesignationType(designationType);
					designationOthers.setIsTopDesignation(false);
					designationOthers.setIsActive(true);
					designationOthers.setSortOrder(sortOrder);
					Boolean isContractPerma = null;
					try{
						isContractPerma = Boolean.parseBoolean(designationColumns[4]);//Multiple Designation
					}catch (Exception e) {
						isContractPerma = null;
					}
					designationOthers.setIsContractPermanent(isContractPerma);
					designationService.saveOrUpdate(designationOthers);
					for(LgdDesignation des : existingDesignations){
						if(des.getIsTopDesignation()){
							removed.add(des);
						}else if(des.getDesignationCode().equals(designationOthers.getDesignationCode())){
							removed.add(des);
						}
					}
				}
				existingDesignations.removeAll(removed);
				for(LgdDesignation deletedObj : existingDesignations){
					if(!designationService.isDesignationBeingUsed(deletedObj.getDesignationCode())){
						LgdDesignation loadedRemoved = designationService.getDesignationById(deletedObj.getDesignationCode());
						designationService.remove(loadedRemoved);
					}
				}
			}
			model.addAttribute("msgid", "Designation Saved / Modified Successfully, If referenced can not be Manipulated !");
			return "success";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	*/
	@RequestMapping(value = "/create_designation_reporting_panchayat", method = RequestMethod.GET)
	public String designationReportingPanchyat(@ModelAttribute("designationReportingForm") DesignationReportingForm designationReportingForm,
											   Model model, HttpSession httpSession, HttpServletRequest request) {
		try {
			init(httpSession);
			List<GetLocalGovtSetup> tierSetupList = localGovtSetupService.isStateSetup(stateCode, PANCHYAT_CODE.charAt(0));
			if(tierSetupList.isEmpty()) {
				model.addAttribute("msgid","Either You Didn't Setup The Local Government for Panchyat OR Designation Hierarchy. !");
				return "success";
			} else {
				boolean flag = localGovtSetupService.checkTierPanchayatforDesignation(tierSetupList, stateCode);
				if(!flag) {
					model.addAttribute("msgid","Kindly setup the Designation Hierarchy for all the the Local Body Types. !");
					return "success";
				}else {
					List<LocalBodyType> lgt = localGovtSetupService.getLgDetails(stateCode, 'R');
					model.addAttribute("lgT", lgt);
					model.addAttribute("rtype",PANCHYAT_CODE);
					return "create_designation_reporting";
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	@RequestMapping(value = "/create_designation_reporting_traditional", method = RequestMethod.GET)
	public String designationReportingTraditional(@ModelAttribute("designationReportingForm") DesignationReportingForm designationReportingForm,
												  Model model, HttpSession httpSession, HttpServletRequest request) {
		try {
			init(httpSession);
			List<GetLocalGovtSetup>	tierSetupList = localGovtSetupService.isStateSetup(stateCode, 'T');
			if(tierSetupList.isEmpty()) {
				model.addAttribute("msgid","Either You Didn't Setup The Local Government for Traditional OR Designation Hierarchy. !");
				return "success";
			} else {
				boolean flag = localGovtSetupService.checkTierPanchayatforDesignation(tierSetupList, stateCode);
				if(!flag) {
					model.addAttribute("msgid","Kindly setup the Designation Hierarchy for all the the Local Body Types. !");
					return "success";
				} else {
					List<LocalBodyType> lgt = localGovtSetupService.getLgDetails(stateCode, 'T');
					model.addAttribute("lgT", lgt);
					model.addAttribute("rtype","T");
					return "create_designation_reporting";
				}
			}
		} catch (Exception e) { 
			IExceptionHandler expHandler = ExceptionHandlerFactory .getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException( request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}

	@RequestMapping(value = "/create_designation_reporting_urban", method = RequestMethod.GET)
	public String designationReportingUrbanGet(@ModelAttribute("designationReportingForm") DesignationReportingForm designationReportingForm,
											   Model model, HttpSession httpSession, HttpServletRequest request) {
		try {
			init(httpSession);
			List<GetLocalGovtSetup> tierSetupList = localGovtSetupService.isStateSetup(stateCode, 'U');
			if(tierSetupList.isEmpty()){
				model.addAttribute("msgid","Either You Didn't Setup The Local Government for Urban OR Designation Hierarchy. !");
				return "success";
			} else {
				boolean flag = localGovtSetupService.checkTierPanchayatforDesignation(tierSetupList, stateCode);
				if(!flag) {
					model.addAttribute("msgid","Kindly setup the Designation Hierarchy for all the the Local Body Types. !");
					return "success";
				} else {
					List<LocalBodyType> lgt = localGovtSetupService.getLgDetails(stateCode, 'U');
					model.addAttribute("lgT", lgt);
					model.addAttribute("rtype","U");
					return "create_designation_reporting";
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}

	@RequestMapping(value = "/create_designation_reporting", method = RequestMethod.POST)
	public String designationReporting(@ModelAttribute("designationReportingForm") DesignationReportingForm designationReportingForm, Model model, HttpServletRequest request) {
		try {
			
			boolean isCommited = localGovtSetupService.saveReportingLBSetup(designationReportingForm);
			if (isCommited ) {
				List<Designationpojo> desigList = designationService.recentAddedDesignation(Integer.parseInt(designationReportingForm.getLgTypeName().toString().split("%")[0]));
				model.addAttribute("designationTier", desigList);
				model.addAttribute("successMsg","The Reporting Structure was added successfully");
				return "viewDesignationReporting";
			}
			model.addAttribute("msgid", "Designation Reporting Failed To Save. Kindly Re-enter the Details. !");
			return "success";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	private String reorderAccessURL(HttpSession session, LgdDesignation lgdDesignation, Model model, String designationType, String lbType) throws Exception{
		init(session);
		lgdDesignation.setDesignationType(designationType);
		lgdDesignation.setFlowName("designationEROfficial");
		List<GetLocalGovtSetup> tiersetup = localGovtSetupService.isStateSetup(stateCode, lbType.charAt(0));
		if (tiersetup.size() > 0) {
			model.addAttribute("tiersetup", tiersetup);
			model.addAttribute("lbType", lbType);
			model.addAttribute("isViewOnly",true);
			return "reorderLgdDesignation";
		}
		
		model.addAttribute("msgid", "You Didn't Configure The Local Government, Please Configure The System. !");
		return "success";
	}
	
	
	@RequestMapping(value = "/reorder_designation_hierarchy_elected_panchayat", method = RequestMethod.GET)
	public String reorderDesignationHierarchyPanchayatGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
												   Model model, 
												   HttpSession httpSession,
												   HttpServletRequest request) {
		try {
			return reorderAccessURL(httpSession, lgdDesignation, model, ELECTIVE_REPRESENTATIVE, PANCHYAT_CODE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	@RequestMapping(value = "/reorder_designation_hierarchy_elected_traditional", method = RequestMethod.GET)
	public String  reorderDesignationHierarchyTraditionalGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
			   										 Model model, 
													 HttpSession httpSession,
													 HttpServletRequest request) {
		try {
			return reorderAccessURL(httpSession, lgdDesignation, model, ELECTIVE_REPRESENTATIVE, TRADITIONAL_CODE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}

	@RequestMapping(value = "/reorder_designation_hierarchy_elected_urban", method = RequestMethod.GET)
	public String  reorderDesignationHierarchyUrbanGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
											   Model model, 
											   HttpSession httpSession,
											   HttpServletRequest request) {
		try {
			return reorderAccessURL(httpSession, lgdDesignation, model, ELECTIVE_REPRESENTATIVE, URBAN_CODE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	@RequestMapping(value = "/reorder_designation_hierarchy_official_panchayat", method = RequestMethod.GET)
	public String  reorderDesignationHierarchyOfficialPanchayatGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
														   Model model, 
														   HttpSession httpSession,
														   HttpServletRequest request) {
		try {
			return reorderAccessURL(httpSession, lgdDesignation, model, OFFICIAL, PANCHYAT_CODE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	
	@RequestMapping(value = "/reorder_designation_hierarchy_official_traditional", method = RequestMethod.GET)
	public String  reorderDesignationHierarchyOfficialTraditionalGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
														     Model model, 
														     HttpSession httpSession,
														     HttpServletRequest request) {
		try {
			return reorderAccessURL(httpSession, lgdDesignation, model, OFFICIAL, TRADITIONAL_CODE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	@RequestMapping(value = "/reorder_designation_hierarchy_official_urban", method = RequestMethod.GET)
	public String  reorderDesignationHierarchyOfficialUrbanGet(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
												       Model model, 
												       HttpSession httpSession,
												       HttpServletRequest request) {
		try {
			return reorderAccessURL(httpSession, lgdDesignation, model, OFFICIAL, URBAN_CODE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
	
	@RequestMapping(value = "/reorder_designation_entry", method = RequestMethod.POST)
	public String reOrderDesignation(@ModelAttribute("lgdDesignation") LgdDesignation lgdDesignation,
							     BindingResult br,
							     Model model, 
							     HttpSession httpSession,
							     HttpServletRequest request, 
							     @RequestParam("lbType") String lbType) {
		try {
			init(httpSession);
			Integer sortOrder=0;
			String otherDesignations = lgdDesignation.getOtherDesignations();
			if(otherDesignations != null && !"".equals(otherDesignations)){
				Scanner scanner = new Scanner(otherDesignations);
				scanner.useDelimiter("@@");
				while(scanner.hasNext()){
					sortOrder++;
					String 	 inputDesCode 			= scanner.next();
					Integer desCode=null;
					if(inputDesCode != null && !"".equals(inputDesCode)){
						desCode = Integer.parseInt(inputDesCode);
					}	
					if(desCode!=null){
						designationService.saveReOrder(desCode,sortOrder);
					}else{
						model.addAttribute("msgid", "Designation Re-Order not Successful !");
						scanner.close();
						return "success";
					}
				}
				scanner.close();
			}
			model.addAttribute("msgid", "Designation Re-Order Successful !");
			return "success";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
	
public boolean checkLevelChange(List<DesignationLevelSortorder> existLevelList,String levels) throws Exception{
boolean isLevelChange=false;
boolean isFind=false;
if(existLevelList!=null && !existLevelList.isEmpty() && levels.length()>0){
	String levelsArr[]=levels.split(",");
	if(levelsArr.length==existLevelList.size()){
	
		for(DesignationLevelSortorder designationLevelSortorder: existLevelList){
			isFind=false;
			Scanner scanner = new Scanner(levels);
			scanner.useDelimiter(",");
			while(scanner.hasNext()){
				Integer 	 selOrgLocatedLevelCode = Integer.parseInt(scanner.next());
				if(selOrgLocatedLevelCode.equals(designationLevelSortorder.getOrgLocatedLevelCode())){
					isFind=true;
					break;
				}
			}
			if(!isFind){
				isLevelChange=true;
				break;
			}
		}
	}else{
		isLevelChange=true;
	}
	
}else{
	isLevelChange=false;
}


return isLevelChange;
}


public boolean checkDeleteNode(Integer existDesigCode,String currentDesig)throws Exception{
	 boolean  isDeleteNode=true;
	Integer curDesigCode=null;
	 Scanner scanner = new Scanner(currentDesig);
	 
		scanner.useDelimiter("@@");
		while(scanner.hasNext()){
			String 	 rowvalues = scanner.next();
			String[] designationColumns	= rowvalues.split("\\##");
			if(designationColumns!=null && designationColumns[0]!=null && designationColumns[0].trim().length()>0){
				curDesigCode	=Integer.parseInt(designationColumns[0]);
				if(curDesigCode.equals(existDesigCode)){
					isDeleteNode=false;
					break;
				}
			}
		  
		}
	return isDeleteNode;
}

public boolean checkNewNode(Integer currentDesigCode,List<LgdDesignation> existDesignationsList )throws Exception{
	 boolean  isNewNode=true;
	 for(LgdDesignation lgdDesignation:existDesignationsList){
		 if(currentDesigCode.equals(lgdDesignation.getDesignationCode())){
			 isNewNode=false;
			 break;
		 }
	 }
	 return isNewNode;
}

}
