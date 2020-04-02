package in.nic.pes.lgd.controllers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.common.beans.Category;
import in.nic.pes.common.functionpojo.LanguagePackageSwitchUnitPojo;
import in.nic.pes.common.menu.pojo.Form;
import in.nic.pes.common.menu.pojo.MenuProfile;
import in.nic.pes.common.menu.pojo.Privilege;
import in.nic.pes.common.menu.pojo.UserSelection;
import in.nic.pes.lgd.bean.ExternalUser;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.common.MenuDisableConstant;
import in.nic.pes.lgd.constant.CommanConstant;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.InitialService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.validator.ExternalUserValidator;

@Controller
public class DefaultController { // NO_UCD (unused code)
	@Autowired
	CommonService commonService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	InitialService initialService;
	
	@Autowired
	ExternalUserValidator externalUserValidator;
	
	@RequestMapping("/home")
	public ModelAndView home(HttpServletRequest request,HttpSession session)
	{
		try {	
			UserSelection selection=(UserSelection) session.getAttribute("USERS_SELECTION");
			String remoteUser =selection.getLoginId();
			ModelAndView mav=null;
			if(selection!=null)
			{
				SessionObject sessionObject = new SessionObject();
				sessionObject.setAssignedUnitHierarchy(selection.getAssignedUnitHierarchy());
				sessionObject.setAssignUnit(selection.getAssignUnit());
				sessionObject.setCategory(selection.getCategory());
				sessionObject.setDistrict(selection.getDistrict());
				/**
				    * IF District value null from switch unit then default value set to be 0 by Maneesh Kumar 31032016
				 */
				sessionObject.setDistrictCode(selection.getDistrictCode()!=null?selection.getDistrictCode():Integer.parseInt(CommanConstant.DISTRICT_CODE_DEFAULT_VALUE.toString()));
				sessionObject.setFormList(selection.getFormList());
				sessionObject.setListLanguagePackages(selection.getListLanguagePackages());
				sessionObject.setLoginId(selection.getLoginId());
				sessionObject.setMenuProfile(selection.getMenuProfile());
				sessionObject.setSelectedEntity(selection.getSelectedEntity());
				sessionObject.setStateId(selection.getStateId());
				sessionObject.setSubDist(selection.getSubDist());
				sessionObject.setSubDistCode(selection.getSubDistCode());
				sessionObject.setUserId(selection.getUserId());
				sessionObject.setIsCenterorstate(selection.getIsCenterorstate());
				sessionObject.setRemoteAddress(request.getRemoteAddr());
				sessionObject.setSlc(commonService.getSlc(selection.getStateId()));	
				
				Properties properties = new Properties();
				InputStream inputStreamPro = getClass().getClassLoader().getResourceAsStream("/dashboard_info.properties");
				properties.load(inputStreamPro);
				 final String  CENTER_REVENUE_ID =properties.getProperty("CENTER_REVENUE_ID");
				 final String  CENTER_PRI_ID =properties.getProperty("CENTER_PRI_ID");
				 final String  CENTER_URBAN_ID =properties.getProperty("CENTER_URBAN_ID");
				 final String  CENTER_TLB_ID =properties.getProperty("CENTER_TLB_ID");
				 final String  CENTER_BLOCK_ID =properties.getProperty("CENTER_BLOCK_ID");
				 final String  CENTER_CEC_ID =properties.getProperty("CENTER_CEC_ID");
				 final String  CENTER_LOCAL_ID =properties.getProperty("CENTER_LOCAL_ID");
				 final String  LOCAL_USER_TYPE =properties.getProperty("LOCAL_USER_TYPE");
				
				 if(remoteUser!=null && !remoteUser.trim().equals("") && (remoteUser.trim().equalsIgnoreCase(CENTER_REVENUE_ID)
					||remoteUser.trim().equalsIgnoreCase(CENTER_PRI_ID) || remoteUser.trim().equalsIgnoreCase(CENTER_URBAN_ID)
					||remoteUser.trim().equalsIgnoreCase(CENTER_URBAN_ID) || remoteUser.trim().equalsIgnoreCase(CENTER_TLB_ID)
					||remoteUser.trim().equalsIgnoreCase(CENTER_BLOCK_ID) || remoteUser.trim().equalsIgnoreCase(CENTER_CEC_ID)
					|| remoteUser.trim().equalsIgnoreCase(CENTER_LOCAL_ID))) {
					
					Character isUserType='X';
					if(remoteUser.trim().equalsIgnoreCase(CENTER_REVENUE_ID)) {
						isUserType='R';
					}else if(remoteUser.trim().equalsIgnoreCase(CENTER_PRI_ID)) {
						isUserType='P';
					}else if(remoteUser.trim().equalsIgnoreCase(CENTER_URBAN_ID)) {
						isUserType='U';
					}else if(remoteUser.trim().equalsIgnoreCase(CENTER_TLB_ID)) {
						isUserType='T';
					}else if(remoteUser.trim().equalsIgnoreCase(CENTER_BLOCK_ID)) {
						isUserType='B';
					}else if(remoteUser.trim().equalsIgnoreCase(CENTER_CEC_ID)) {
						isUserType='E';
					}else if(remoteUser.trim().equalsIgnoreCase(CENTER_LOCAL_ID)) {
						isUserType=LOCAL_USER_TYPE.charAt(0);
					}
					
					
					session.setAttribute("stateCode", sessionObject.getStateId()!=null?sessionObject.getStateId().toString():null);
					session.setAttribute("districtId", sessionObject.getDistrictCode()!=null?sessionObject.getDistrictCode().toString():0);
					session.setAttribute("userid", sessionObject.getUserId()!=null?sessionObject.getUserId().toString():null);
					session.setAttribute("sessionObject", sessionObject);
					session.setAttribute("isUserType",isUserType);
					session.setAttribute("isDashBoradCenterorState",sessionObject.getIsCenterorstate());
					mav = new ModelAndView("dashboardHomeCenter");
					return mav;
				}
				else {
					boolean assignNodelOfficer=true;
					List<MenuProfile> menuList = selection.getMenuProfile();
					
					Object loginKeyId= session.getAttribute("login_key_id");
					if(loginKeyId==null) {
						session.setAttribute("login_key_id",sessionObject.getLoginId());
					}
					
					System.out.println("state Code-->"+sessionObject.getStateId());
					System.out.println("district Code-->"+sessionObject.getDistrictCode());
					System.out.println("user id-->"+sessionObject.getUserId());
					Character isUserType=null;
					/*
					 *   Nodal officer Section
					 */
					
					if(sessionObject.getUserId()!=null){
						assignNodelOfficer=commonService.isAssignNodelOfficer(sessionObject.getUserId());
						
					}
					Character userType=null;
					if(!assignNodelOfficer){
					boolean isEntityDefiner=false;	
					boolean isDeptDefiner=false;
					
						for( MenuProfile profile : menuList ){
							if(isUserType==null && profile.getFormName()!=null){
								if( profile.getFormName().equalsIgnoreCase("configMapPRI.htm")||  profile.getFormName().equalsIgnoreCase("viewLocalBodyforPRI.htm")){
									isUserType='P';
								}else if( profile.getFormName().equalsIgnoreCase("configMapTrd.htm")||  profile.getFormName().equalsIgnoreCase("viewLocalBodyforTraditional.htm")){
									isUserType='T';
								}else if( profile.getFormName().equalsIgnoreCase("configMapLGDM.htm")||  profile.getFormName().equalsIgnoreCase("viewLocalBodyforUrban.htm")){
									isUserType='U';
								}else if( profile.getFormName().equalsIgnoreCase("config_landregion.htm") ||  profile.getFormName().equalsIgnoreCase("viewdistrict.htm")){
									isUserType='R';
								}else if( profile.getFormName().equalsIgnoreCase("config_block.htm") ||  profile.getFormName().equalsIgnoreCase("assignVillagestoBlock.htm")){
									isUserType='B';
									 }else if(profile.getFormName().equalsIgnoreCase("map_constitutionBody.htm")){
										 isUserType='E';
									 }
								
								
								if(isDeptDefiner==false && profile.getFormName().equalsIgnoreCase("createDepartmentState.htm")){
									isDeptDefiner=true;
								}
								
								if(isEntityDefiner==false && profile.getFormName().equalsIgnoreCase("newDeptAdminEntity.htm")){
									isEntityDefiner=true;
								}
								
								
							
							}
						}
						
						if(isUserType==null){
							assignNodelOfficer=true;
						}else{
							session.setAttribute("isUserType",isUserType);
							
						}
						boolean isUserEntityDefiner=false;
						if(isDeptDefiner==false && isEntityDefiner==true && ("S").equals(sessionObject.getIsCenterorstate())){
							isUserEntityDefiner=true;
						}
						session.setAttribute("isUserEntityDefiner", isUserEntityDefiner);
						
					}else{
						
						isUserType=stateService.getUserTypeofNodalOfficer(sessionObject.getUserId());
						if(isUserType!=null){
							session.setAttribute("isUserType",isUserType);
							System.out.println("isUserType-->"+isUserType);
						}
					}
					/*
					 *  End Nodal officer details
					 */
					
					if(MenuDisableConstant.ENTITY_STATE_LEVEL.toString().equalsIgnoreCase(selection.getIsCenterorstate())){
						//Boolean isDistrictUser = selection.getDistrictCode() != null ? true : false;
						/*String entityType = MenuDisableConstant.ENTITY_STATE_LEVEL.toString();
						Integer entityCode = selection.getStateId();
						if( isDistrictUser ){
							entityType = MenuDisableConstant.ENTITY_DISTRICT_LEVEL.toString();
							entityCode = selection.getDistrictCode();
						}*/
						//FreezeUnfreezeStatus statusFreezeUnfreeze = commonService.getFreezeUnfreezeStatus(entityCode, entityType);
						EnumSet<MenuDisableConstant> completeList = EnumSet.noneOf(MenuDisableConstant.class); ; 
						Character level=sessionObject.getDistrictCode()>0?'D':'S';
						
						if( isUserType!=null && stateService.getFreezeStatusbyUserId(sessionObject.getUserId(), isUserType, level,sessionObject.getStateId())){
							switch(isUserType){
							case 'B':
									completeList.addAll(MenuDisableConstant.blocklinks);
									break;
							case 'R':
								if(level=='D'){
									completeList.addAll(MenuDisableConstant.districtLinks);
								}else if(level=='S'){
									completeList.addAll(MenuDisableConstant.stateLinks);
								}
								break;
								
							case 'P':
								completeList.addAll(MenuDisableConstant.priLinks);
								break;
								
							case 'U':
								completeList.addAll(MenuDisableConstant.urbanLinks);
								break;	
								
								
							case 'T':
								completeList.addAll(MenuDisableConstant.traditionalLinks);
								break;	
								
							case 'E':
								completeList.addAll(MenuDisableConstant.constituencylinks);
								break;		
								
							}
							
						}	
				
						
						if(!completeList.isEmpty()){
							for( MenuProfile profile : menuList ){
								for (MenuDisableConstant links : completeList) {
						    		if (links.toString().equalsIgnoreCase(profile.getResourceId().trim())) {
						    			profile.setDisabled(Boolean.TRUE);
						    		}
						    	}
							}
						}
					}
					
					System.out.println("user type-->"+userType);
					
					selection.setMenuProfile(menuList);
					
					/**
					 * Set stateCode,districtId and userid in session attribute for temp period which is missing from new switch unit jar 3.3 by Maneesh Kumar 29Mar2016
					 */
					session.setAttribute("stateCode", sessionObject.getStateId()!=null?sessionObject.getStateId().toString():null);
					session.setAttribute("districtId", sessionObject.getDistrictCode()!=null?sessionObject.getDistrictCode().toString():0);
					session.setAttribute("userid", sessionObject.getUserId()!=null?sessionObject.getUserId().toString():null);
					session.setAttribute("sessionObject", sessionObject);
					session.setAttribute("menuList", selection);
					session.setAttribute("nodal", selection);
					session.setAttribute("isassignNodelOfficer", assignNodelOfficer);
					
					/*int userId=Integer.parseInt(sessionObject.getUserId()!=null?sessionObject.getUserId().toString():"0");
					if(userId==155324 ||userId==155325||userId==155327||userId==155326 )
					{
						assignNodelOfficer=true;
					}*/
						

				
					if(assignNodelOfficer==false){
						mav = new ModelAndView("redirect:getAssignNodelOfficer.htm");
					}else{
						
						mav = new ModelAndView("dashboardHome");
					
					}
				}
				
			}else{
				mav = new ModelAndView("redirect:logout.htm");
			}
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	
	/**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 *//*
	@RequestMapping(value = "/getExternalLogin", method = RequestMethod.GET)
	public ModelAndView getExternalLogin(@ModelAttribute("lform") LoginForm loginForm) {
		ModelAndView mav = new ModelAndView("EXTERNAL_USER");
		System.out.println("inside the external user 1");
		return mav;
	}
	
	
	*//**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 *//*
	@RequestMapping(value = "/getExternalLogin", method = RequestMethod.POST)
	public ModelAndView getExternalLoginPost(@ModelAttribute("lform") LoginForm loginForm, BindingResult bindingResult,HttpSession session,HttpServletRequest request) {
		try {
		ModelAndView mav = null;
		System.out.println("inside the external user 2");
		CaptchaValidator captchaValidator = new CaptchaValidator();
		boolean messageFlag = captchaValidator.validateCaptcha(session, loginForm.getCaptchaAnswer());
		if ( !messageFlag ) {
			mav.addObject("captchaFaliedMessage", messageFlag);                                        
			loginForm.setCaptchaAnswer(null);
			return mav;
		} 
		
		 externalUserValidator.validate(loginForm, bindingResult);
		if (bindingResult.hasErrors()) {
			loginForm.setPassword(null);
			loginForm.setEnpassword(null);
			loginForm.setCaptchaAnswer(null);
			return mav = new ModelAndView("EXTERNAL_USER");
		}
		Integer id= initialService.findUserAfterAuth(loginForm.getUsername(), loginForm.getEnpassword());
		if(id!=null){
			ExternalUser externalUser=initialService.getExternalUserEntity(id);
			
			System.out.println("mk getExternalLogin method2");
			UserSelection selection=new UserSelection();
		
			List<MenuProfile> menuList=initialService.findMenuListforExternalUser(externalUser.getUserId());
			 HashSet<Form> formList = new HashSet<Form>(); 
			 Form form=null;
			 List<String> formsArr=new ArrayList<String>();
			 List<Privilege> privilege=null;
			 
			 Privilege privilegeObj=null;
			for(MenuProfile obj:menuList) {
				if(obj.getFormName()!=null && obj.getFormName().trim()!="" && obj.getFormName().length()>0) {
					form=new Form();
					
					privilege=new ArrayList<Privilege>();
					privilegeObj=new Privilege();
					privilegeObj.setAction(obj.getFormName());
					
					privilegeObj.setType(obj.getFormName().substring(0,4));
					privilege.add(privilegeObj);
					
					formsArr.add("'"+obj.getFormName()+"'");
					
					form.setAction(obj.getFormName());
					form.setAppid(1);
					form.setFormName(obj.getResourceId());
					form.setPrivileges(privilege);
					formList.add(form);
				}
			}
			String forms=formsArr.toString();
			forms=forms.substring(1, forms.length()-1);
			Map<Integer,String> formMap=new HashMap<Integer,String>();
			formMap.put(1, forms);
			
			Category category=new Category();
			category.setCategoryCode("O");
			category.setEntityLevelCode(1);
			category.setEntityTypeCode(2);
			
			LanguagePackageSwitchUnitPojo LanguagePackageSwitchUnitPojo=new LanguagePackageSwitchUnitPojo();
			LanguagePackageSwitchUnitPojo.setLanguageId(1);
			LanguagePackageSwitchUnitPojo.setLanguageName("Language.English");
			LanguagePackageSwitchUnitPojo.setPackageIdentifier("en");
			
			List<LanguagePackageSwitchUnitPojo> listLanguagePackages=new ArrayList<LanguagePackageSwitchUnitPojo>();
			listLanguagePackages.add(LanguagePackageSwitchUnitPojo);
			
			
			selection.setAppid(1);
			selection.setAssignedUnitHierarchy("LADAKH /Rular Development Department");
			selection.setAssignUnit(400);
			selection.setCategory(category);
			selection.setFormList(formList);
			selection.setForms(formMap);
			selection.setIsAgencyUser(false);
			selection.setIsAreaProfilerUser(false);
			selection.setIsCenterorstate("S");
			selection.setIsselfRegistered(false);
			selection.setListLanguagePackages(listLanguagePackages);
			selection.setDefaultLanguagePackage(listLanguagePackages);
			selection.setLoginId(externalUser.getUserLoginId());
			selection.setMenuProfile(menuList);
			selection.setSelectedFinancialYear("2018-2019");
			selection.setStateId(externalUser.getStateCode());
			selection.setDistrictCode(externalUser.getDistrctCode());
			selection.setUserId(externalUser.getUserId());
			
			
			
		
			
			
			session.setAttribute("USERS_SELECTION", selection);
			session.setAttribute("isExternalUserTemp", Boolean.TRUE);
			return mav = new ModelAndView("redirect:home.do");
		}else {
			
			return mav = new ModelAndView("redirect:logout.htm");
			
		}
		
		
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
	}*/

}
