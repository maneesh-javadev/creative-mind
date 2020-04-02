package in.nic.pes.lgd.controllers;

import in.nic.pes.common.menu.pojo.Form;
import in.nic.pes.common.menu.pojo.MenuProfile;
import in.nic.pes.common.menu.pojo.Privilege;
import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.bean.GeneratedFileDetails;
import in.nic.pes.lgd.bean.PesApplicationMaster;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.menu.LgdMenuAdapter;
import in.nic.pes.lgd.menu.LoginForm;
import in.nic.pes.lgd.menu.PropUtil;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.validator.LoginValidator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class LGDDefaultController { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(LGDDefaultController.class);

	@Autowired
	LgdMenuAdapter lgdMenuAdapterImpl;

	@Autowired
	StateService stateService;

	@Autowired
	CommonService commonService;
	
	@Autowired
	VillageService villageService;
	
	//@Autowired
	
	//
	// @RequestMapping("/home")
	// public String home(Model model, HttpSession session)
	// {
	// if(session.getAttribute("loggedUser")!=null)
	// {
	// if(session.getAttribute("role").toString().equals("lgadmin"))
	// {
	// return "redirect:viewprofile.htm";
	// }
	// else if(session.getAttribute("role").toString().equals("familyAdmin"))
	// {
	// return "redirect:requestfamily.htm";
	// }
	// else
	// {
	// return "home";
	// }
	//
	// }
	// else
	// {
	// return "redirect:login.htm";
	// }
	//
	// }
	//

	private List<CheckAuthorization> populateSourceStateList() throws Exception {

		return commonService.checkAuthorization('S', null, null, null);
	}

	

	
	
	/*
	 * @RequestMapping("/logout") public String logout(Model
	 * model,HttpServletRequest request, HttpSession session) {
	 * 
	 * try { session.removeAttribute("loggedUser");
	 * session.removeAttribute("stateId");
	 * session.removeAttribute("districtId");
	 * session.removeAttribute("localBodyId");
	 * session.removeAttribute("loggedUserId"); session.removeAttribute("role");
	 * session.removeAttribute("menuProfile"); session.invalidate(); return
	 * "redirect:login.htm"; } catch (Exception e) { IExceptionHandler
	 * expHandler = ExceptionHandlerFactory.getInstance().create(); String
	 * redirectPath =
	 * expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
	 * return redirectPath; } }
	 * 
	 * 
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public
	 * String login(@ModelAttribute("loginForm") LoginForm loginForm, Model
	 * model, HttpSession session,HttpServletRequest request) {
	 * 
	 * try { model.addAttribute("stateSourceList",populateSourceStateList());
	 * return "login"; } catch (Exception e) { IExceptionHandler expHandler =
	 * ExceptionHandlerFactory.getInstance().create(); String redirectPath =
	 * expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
	 * return redirectPath; } }
	 */
	/* Code given by vinay on 24-09-2014*/
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,HttpServletResponse response, HttpSession session)
	{
		String redirectPath = null;
		try 
		{
			
			//session.removeAttribute("loggedUser");
			session.removeAttribute("login_key_id");
			session.removeAttribute("stateId");
			session.removeAttribute("districtId");
			session.removeAttribute("localBodyId");
			session.removeAttribute("loggedUserId");
			session.removeAttribute("role");
			session.removeAttribute("menuProfile");
			session.removeAttribute("userPrivilegesForm");
			/**
			 * This line of code is User for External User 
			 * @param loginForm
			 * @author Maneesh Kumar
			 * @since 01-10-2019
			 * @return
			 */
			if(session.getAttribute("isExternalUserTemp")!=null) {
				session.removeAttribute("isExternalUserTemp");
			}
			session.invalidate();
			Cookie cookie=new Cookie("JSESSIONID",null);
			//for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
			cookie.setValue(null);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
			//}
            redirectPath = request.getServletContext().getInitParameter("CasURL");
		} catch (Exception e) {
		 IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		return expHandler.handleSaveRedirectException(request, "", "label.lgd", Integer.valueOf(1), e);
		}
		return "redirect:" + redirectPath;
	}

	/*@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login(@ModelAttribute("loginForm") LoginForm loginForm,
			Model model, HttpSession session, HttpServletRequest request) {
		try
		{
			String remoteUser = null;
			if (request.getSession() != null) {
				request.getSession().invalidate();
				request.getSession(true);
			}
			remoteUser = request.getRemoteUser();
			if (remoteUser == null) {
				remoteUser = "statedpt";
			}
			model.addAttribute("stateSourceList", populateSourceStateList());
			return "login";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}
*/
    //Code to be uncommented in case to run for Static Menu in Local environment---Start
	/* Code given by vinay on 24-09-2014*/
	//@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(@ModelAttribute("loginForm") LoginForm loginForm, Model model, HttpSession session,HttpServletRequest request) {
		try {
			log.info("remoteuserExist:" + request.getRemoteUser());
			if (request.getRemoteUser() != null) {
				 log.info("remoteuserName:" + request.getRemoteUser());
				return "redirect:switchunit.htm";
		    }
			
			//model.addAttribute("stateSourceList", populateSourceStateList());
			/*java.util.Map<String, Object> map = commonService.getFAQandRespose(1);
			for (String key : map.keySet()) {
                model.addAttribute(key, map.get(key));
            }*/
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			return expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
		}
		return "welcome";
	}
	//Code to be uncommented in case to run for Static Menu in Local environment---End

	
	//Code to be uncommented in case to run for Dynamic Menu---Start
	
	/*@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String welcome(@ModelAttribute("loginForm") LoginForm loginForm, Model model, HttpSession session,HttpServletRequest request)
	{
		return "welcome";
	}*/
	
	//Code to be uncommented in case to run for Dynamic Menu---Start
	
/*	CBT*/
	
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String help(HttpServletRequest request, Map<String, Object> model) {
		//ModelAndView mv = null;
		String Foldermapping = request.getParameter("Foldermapping");
		String Filename = request.getParameter("Filename");
		String Url=request.getParameter("Url");
		model.put("Foldermapping", Foldermapping);
		model.put("Filename", Filename);
		model.put("Url", Url);
		return "help";
	}
	
	/*Help*/
	

	//@RequestMapping(value="/viewManualCBT")
		@RequestMapping(value="/viewManualHelps", method=RequestMethod.GET)
		@ResponseBody	
	    public String viewManualCBT( Model model,HttpServletResponse response,HttpServletRequest request,@RequestParam("formName")String formName) throws ServletException,IOException {
			
			List<GeneratedFileDetails> generatorfiledetails=new ArrayList<GeneratedFileDetails>();
			String str="";
			try{
			   //String fileName = formName+"html";
				
				//System.out.println("file name::"+formName);
				
				String fileName=formName+".html";
		
			
	          //String fName=fileName.replace("form.", "");
	          //System.out.println("::::::::"+fileName);
	          
	          /*String documentType="Computer Based Tutorial";
	          
	          Integer documentId=2;*/
				
				String documentType="User Manual";
		        Integer documentId=1;
	          
	         
			
			
	         generatorfiledetails= stateService.getCBTHtml(fileName,documentType,documentId);
	         
	         
	         
	         if(generatorfiledetails.size() > 0){
	        	 str = generatorfiledetails.get(0).getFormContenthtml();
	         }else{
	        	 str= "Yet manual not uploaded.";
	         }
			
			 //System.out.println("m in data");
			
			}catch (Exception e) {
				log.debug("Exception" + e);
			}
			
			return str;
	    }


	
	//@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model, HttpSession session,HttpServletRequest request)
	{
		try
		{
			InputStream inputStream = this.getClass().getResourceAsStream("/switchunit.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
	        String loginkey=properties.getProperty("switchunit.loginkey");
	        
		    String remoteUser = request.getRemoteUser() == null ? loginForm.getUsername() : request.getRemoteUser(); //DISTRICTUSERAMB//SUPERADMINSKIM centerlgd
	        //String remoteUser=request.getRemoteUser();
		    //request.getSession().invalidate();
		    session=request.getSession(true);
		    session.setAttribute(loginkey, remoteUser);
		    
			//UserSelection selection=(UserSelection) session.getAttribute("USERS_SELECTION");
		    return "redirect:switchunit.htm"; 
		}
		catch(Exception ex)
		{
			return"login";
		}
	}
	
	
	// CODE BY ARNAB FOR CAS IMPLEMENTATION----END	
	
	
	//@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userlogin(@ModelAttribute("loginForm") LoginForm loginForm,
			BindingResult result, Model model, HttpSession session,
			HttpServletRequest request) throws IOException
	{
		try 
		{
			LoginValidator cValidator = new LoginValidator();
			cValidator.validate(loginForm, result);
			if (result.hasErrors()) 
			{
				model.addAttribute("stateSourceList", populateSourceStateList());
				return "login";
			}

			else 
			{
				if (loginForm.getStateNameEnglish() == null)
				{
					model.addAttribute("stateSourceList", populateSourceStateList());
					return "login";
				}
				commonService.checkAuthorization('S', null, null,
						Integer.parseInt(loginForm.getStateNameEnglish()));

				String userName = loginForm.getUsername();
				String passWord = loginForm.getPassword();
				//ServletContext sc = session.getServletContext();

				Properties prop = new Properties();
				InputStream is = PropUtil.class
						.getResourceAsStream("password.properties");
				prop.load(is);

				session.setAttribute("stateCode",
						loginForm.getStateNameEnglish());
				session.setAttribute("isCenterState","S");
				//session.setAttribute("districtId",442);
				
				//session.setAttribute("districtId",50); //118
				
				//session.setAttribute("districtId",119);
				
				//session.setAttribute("districtId",118);
				//session.setAttribute("districtId",58);
				session.setAttribute("userid", new Long(1111));
				
				/* Central Administrator Manager- (CM) */
				String cuser = prop.getProperty("cusername");
				String cpassword = prop.getProperty("cpaddword");

				/* State Revenue Department Manager(SM) */
				String suser = prop.getProperty("susername");
				String spass = prop.getProperty("spaddword");

				/* State PR Department Manager (LGDM-PRI ) */
				String luser = prop.getProperty("luser");
				String lpass = prop.getProperty("lpassword");

				/* State Election Commission Manager (CM) */
				String fuser = prop.getProperty("fuser");
				String fpass = prop.getProperty("fpassword");

				/* State Administrative Unit Manager(AUM) */
				String buser = prop.getProperty("buser");
				String bpass = prop.getProperty("bpassword");

				/* State Urban Department Manager(LGDM-urban) */
				String ctuser = prop.getProperty("ctuser");
				String ctpass = prop.getProperty("ctpassword");

				/* State Traditional Department Manager (LGDM-TRADITIONAL) */
				String tuser = prop.getProperty("tuser");
				String tpass = prop.getProperty("tpassword");

				/* State Common Manager (Common user) */
				String vuser = prop.getProperty("vuser");
				String vpass = prop.getProperty("vpassword");

				/* check admin id database */

				// Central Administrator Manager(form-ff1 to ff26-f30)

				if (cuser.equals(userName) && cpassword.equals(passWord)) {
					session.setAttribute("login_key_id",
							"Central Administrator Manager");

					session.setAttribute("stateId", new Long(30));

					//session.setAttribute("districtId", new Long(20));

					session.setAttribute("localBodyId", new Long(10));

					session.setAttribute("loggedUserId", new Long(100));

					session.setAttribute("role", "centerAdmin");

					Set<in.nic.pes.common.menu.pojo.Form> forms = new HashSet<in.nic.pes.common.menu.pojo.Form>();

					List<Privilege> privileges = new ArrayList<Privilege>();

					Privilege pp2 = new Privilege();
					pp2.setAction("");

					pp2.setType("view");
					privileges.add(pp2);

					// -------------------------------------
					// STATE
					/*
					 * Form ff1 = new Form(); ff1.setFormName("add_state.htm");
					 * ff1.setPrivileges(privileges); forms.add(ff1);
					 */

					Form ff2 = new Form();
					ff2.setFormName("viewstate.htm");
					ff2.setPrivileges(privileges);
					forms.add(ff2);

					// SHIFT
					Form ff3 = new Form();
					ff3.setFormName("shiftdistrict.htm");
					ff3.setPrivileges(privileges);
					forms.add(ff3);

					Form ff4 = new Form();
					ff4.setFormName("shiftSubDistrict.htm");
					ff4.setPrivileges(privileges);
					forms.add(ff4);

					/*
					 * Form ff5 = new Form(); ff5.setFormName("shiftblock.htm");
					 * ff5.setPrivileges(privileges); forms.add(ff5);
					 */

					Form ff6 = new Form();
					ff6.setFormName("shiftvillageblock.htm");
					ff6.setPrivileges(privileges);
					forms.add(ff6);

					Form ff7 = new Form();
					ff7.setFormName("shiftvillageSubDistrict.htm");
					ff7.setPrivileges(privileges);
					forms.add(ff7);

					// LOCAL BODY TYPE
					Form ff8 = new Form();
					ff8.setFormName("localgovtType.htm");
					ff8.setPrivileges(privileges);
					forms.add(ff8);

					Form ff9 = new Form();
					ff9.setFormName("viewlocalgovttype.htm");
					ff9.setPrivileges(privileges);
					forms.add(ff9);

					// DEPARTMENT
					Form ff10 = new Form();
					ff10.setFormName("createDepartmentCentral.htm");
					ff10.setPrivileges(privileges);
					forms.add(ff10);

					Form ff11 = new Form();
					ff11.setFormName("viewdepartment.htm");
					ff11.setPrivileges(privileges);
					forms.add(ff11);

					// ORGANIZATION TYPE
					Form ff12 = new Form();
					ff12.setFormName("addOrganizationType.htm");
					ff12.setPrivileges(privileges);
					forms.add(ff12);

					Form ff13 = new Form();
					ff13.setFormName("modifyOrganizationType.htm");
					ff13.setPrivileges(privileges);
					forms.add(ff13);

					Form ff14 = new Form();
					ff14.setFormName("home.htm");
					ff14.setPrivileges(privileges);
					forms.add(ff14);

					// ORGANIZATION
					Form ff15 = new Form();
					ff15.setFormName("createOrganizationCentral.htm");
					ff15.setPrivileges(privileges);
					forms.add(ff15);

					Form ff16 = new Form();
					ff16.setFormName("vieworganization.htm");
					ff16.setPrivileges(privileges);
					forms.add(ff16);

					// MINISTRY
					Form ff17 = new Form();
					ff17.setFormName("createMinistry.htm");
					ff17.setPrivileges(privileges);
					forms.add(ff17);

					Form ff18 = new Form();
					ff18.setFormName("viewministry.htm");
					ff18.setPrivileges(privileges);
					forms.add(ff18);

					// CONFIGURE GOVT ORDER GENERATRION
					Form ff19 = new Form();
					ff19.setFormName("centralAdmin.htm");
					ff19.setPrivileges(privileges);
					forms.add(ff19);

					// GOVT TEMPLATE
					Form ff20 = new Form();
					ff20.setFormName("createGovtTemplate.htm");
					ff20.setPrivileges(privileges);
					forms.add(ff20);

					Form ff21 = new Form();
					ff21.setFormName("viewListOfTemplates.htm");
					ff21.setPrivileges(privileges);
					forms.add(ff21);

					Form ff22 = new Form();
					ff22.setFormName("generateGovtTemplate.htm");
					ff22.setPrivileges(privileges);
					forms.add(ff22);

					Form ff23 = new Form();
					ff23.setFormName("uploadGovtOrder.htm");
					ff23.setPrivileges(privileges);
					forms.add(ff23);

					/*
					 * // UNRESOLVED ENTITY Form ff24 = new Form();
					 * ff24.setFormName
					 * ("viewResolveEntitiesinDisturbedState.htm");
					 * ff24.setPrivileges(privileges); forms.add(ff24);
					 * 
					 * Form ff25 = new Form();
					 * ff25.setFormName("assignBlockVillageDisturbedState.htm");
					 * ff25.setPrivileges(privileges); forms.add(ff25);
					 * 
					 * // UNPUBLISHED ITEM Form ff26 = new Form();
					 * ff26.setFormName("viewUnpublishedItems.htm");
					 * ff26.setPrivileges(privileges); forms.add(ff26);
					 */

					/*
					 * // Organization Type Form ff27 = new Form();
					 * ff27.setFormName("addOrganizationType.htm");
					 * ff27.setPrivileges(privileges); forms.add(ff27);
					 * 
					 * Form ff28 = new Form();
					 * ff28.setFormName("modifyOrganizationType.htm");
					 * ff28.setPrivileges(privileges); forms.add(ff28);
					 * 
					 * Form ff29 = new Form(); ff29.setFormName("home.htm");
					 * //need to be update ff29.setPrivileges(privileges);
					 * forms.add(ff29);
					 */

					// REPORTS
					/*
					 * Form ff71 = new Form(); ff71.setFormName("");
					 * ff71.setPrivileges(privileges); forms.add(ff71);
					 * 
					 * Form ff72 = new Form(); ff72.setFormName("");
					 * ff72.setPrivileges(privileges); forms.add(ff72);
					 */
					// -------------------------------------
					List<MenuProfile> menuProfile = null;
					menuProfile = lgdMenuAdapterImpl
							.getParentInformation(forms);
					session.setAttribute("menuProfile", menuProfile);
					return "redirect:home.htm";
				}

				// State Revenue Department Manager-LRM (form-ff31-ff50-ff55)
				else if (suser.equals(userName) && spass.equals(passWord)) {
					session.setAttribute("login_key_id",
							"State Revenue Dept Manager");

					session.setAttribute("stateId", new Long(6));

					//session.setAttribute("districtId", new Long(20));

					session.setAttribute("localBodyId", new Long(10));

					session.setAttribute("loggedUserId", new Long(100));

					session.setAttribute("role", "stateRevenueDept");

					Set<in.nic.pes.common.menu.pojo.Form> forms = new HashSet<in.nic.pes.common.menu.pojo.Form>();

					List<Privilege> privileges = new ArrayList<Privilege>();

					Privilege pp = new Privilege();
					pp.setAction("");

					pp.setType("view");
					privileges.add(pp);

					// STATE
					/*
					 * Form ff1 = new Form(); ff1.setFormName("add_state.htm");
					 * ff1.setPrivileges(privileges); forms.add(ff1);
					 */

					Form ff31 = new Form();
					ff31.setFormName("viewstate.htm");
					ff31.setPrivileges(privileges);
					forms.add(ff31);

					// DISTRICT
					Form ff32 = new Form();
					ff32.setFormName("add_district.htm");
					ff32.setPrivileges(privileges);
					forms.add(ff32);

					Form ff33 = new Form();
					ff33.setFormName("viewdistrict.htm");
					ff33.setPrivileges(privileges);
					forms.add(ff33);

					// SUBDISTRICT
					Form ff34 = new Form();
					ff34.setFormName("new_subdistrict.htm");
					ff34.setPrivileges(privileges);
					forms.add(ff34);

					Form ff35 = new Form();
					ff35.setFormName("viewsubdistrict.htm");
					ff35.setPrivileges(privileges);
					forms.add(ff35);

					// VILLAGE
					Form ff36 = new Form();
					ff36.setFormName("createVillage.htm");
					ff36.setPrivileges(privileges);
					forms.add(ff36);

					Form ff37 = new Form();
					ff37.setFormName("viewvillage.htm");
					ff37.setPrivileges(privileges);
					forms.add(ff37);

					// SHIFT
					/*
					 * Form ff9 = new Form();
					 * ff9.setFormName("shiftdistrict.htm");
					 * ff9.setPrivileges(privileges); forms.add(ff9);
					 */

					Form ff38 = new Form();
					ff38.setFormName("shiftSubDistrict.htm");
					ff38.setPrivileges(privileges);
					forms.add(ff38);
					/*
					 * Form ff39 = new Form();
					 * ff39.setFormName("shiftblock.htm");
					 * ff39.setPrivileges(privileges); forms.add(ff39);
					 */

					Form ff40 = new Form();
					ff40.setFormName("shiftvillageblock.htm");
					ff40.setPrivileges(privileges);
					forms.add(ff40);

					Form ff41 = new Form();
					ff41.setFormName("shiftvillageSubDistrict.htm");
					ff41.setPrivileges(privileges);
					forms.add(ff41);

					// INVALIDATE
					Form ff42 = new Form();
					ff42.setFormName("invalidatevillage.htm");
					ff42.setPrivileges(privileges);
					forms.add(ff42);

					Form ff43 = new Form();
					ff43.setFormName("invalidatesubdistrict.htm");
					ff43.setPrivileges(privileges);
					forms.add(ff43);

					Form ff44 = new Form();
					ff44.setFormName("invalidatedistrict.htm");
					ff44.setPrivileges(privileges);
					forms.add(ff44);

					// CONFIGURE GOVT ORDER GENERATRION
					Form ff45 = new Form();
					ff45.setFormName("lrmForm.htm");
					ff45.setPrivileges(privileges);
					forms.add(ff45);

					// CONFIGURE MAP
					Form ff46 = new Form();
					ff46.setFormName("config_landregion.htm");
					ff46.setPrivileges(privileges);
					forms.add(ff46);

					Form ff51 = new Form();
					ff51.setFormName("nomenclature_sub_district.htm");
					ff51.setPrivileges(privileges);
					forms.add(ff51);

					// GOVT TEMPLATE
					Form ff47 = new Form();
					ff47.setFormName("createGovtTemplate.htm");
					ff47.setPrivileges(privileges);
					forms.add(ff47);

					Form ff48 = new Form();
					ff48.setFormName("viewListOfTemplates.htm");
					ff48.setPrivileges(privileges);
					forms.add(ff48);

					Form ff49 = new Form();
					ff49.setFormName("generateGovtTemplate.htm");
					ff49.setPrivileges(privileges);
					forms.add(ff49);

					Form ff50 = new Form();
					ff50.setFormName("uploadGovtOrder.htm");
					ff50.setPrivileges(privileges);
					forms.add(ff50);

					Form ff52 = new Form();
					ff52.setFormName("viewResolveEntitiesinDisturbedStateLR.htm");
					ff52.setPrivileges(privileges);
					forms.add(ff52);

					Form ff53 = new Form();
					ff53.setFormName("assignBlockVillageDisturbedState.htm");
					ff53.setPrivileges(privileges);
					forms.add(ff53);

					// REPORTS
					/*
					 * Form ff71 = new Form(); ff71.setFormName("");
					 * ff71.setPrivileges(privileges); forms.add(ff71);
					 * 
					 * Form ff72 = new Form(); ff72.setFormName("");
					 * ff72.setPrivileges(privileges); forms.add(ff72);
					 */

					List<MenuProfile> menuProfile = null;
					menuProfile = lgdMenuAdapterImpl
							.getParentInformation(forms);
					session.setAttribute("menuProfile", menuProfile);
					return "redirect:home.htm";
				}

				// State Election Commission Manager
				else if (fuser.equals(userName) && fpass.equals(passWord)) {
					session.setAttribute("login_key_id",
							"State Election Commission Manager");

					session.setAttribute("stateId", new Long(6));

					//session.setAttribute("districtId", new Long(58));

					session.setAttribute("subDistrictId", new Long(1034));

					session.setAttribute("lbCode", new Long(36160));

					session.setAttribute("role", "familyAdmin");

					List<Privilege> privileges = new ArrayList<Privilege>();

					Privilege pp = new Privilege();
					pp.setAction("viewtouristplace.htm");

					pp.setType("view");
					privileges.add(pp);

					Privilege pp1 = new Privilege();
					pp1.setAction("modifytouristaplace.htm");
					pp1.setType("modify");

					privileges.add(pp1);

					Set<Form> forms = new HashSet<Form>();

					// CONSTITUENCY //error
					Form ff51 = new Form();
					ff51.setFormName("parliament_Constituency.htm");
					ff51.setPrivileges(privileges);
					forms.add(ff51);

					Form ff52 = new Form();
					ff52.setFormName("assembly_Constituency.htm");
					ff52.setPrivileges(privileges);
					forms.add(ff52);

					Form ff53 = new Form();
					ff53.setFormName("MapofParliament.htm");
					ff53.setPrivileges(privileges);
					forms.add(ff53);

					Form ff60 = new Form();
					ff60.setFormName("home.htm");
					ff60.setPrivileges(privileges);
					forms.add(ff60);

					Form ff61 = new Form();
					ff61.setFormName("home.htm");
					ff61.setPrivileges(privileges);
					forms.add(ff61);

					Form ff62 = new Form();
					ff62.setFormName("home.htm");
					ff62.setPrivileges(privileges);
					forms.add(ff62);

					// WARD

					Form ff54 = new Form();
					ff54.setFormName("createWardforPRI.htm");
					ff54.setPrivileges(privileges);
					forms.add(ff54);

					Form ff55 = new Form();
					ff55.setFormName("createWardforTraditional.htm");
					ff55.setPrivileges(privileges);
					forms.add(ff55);

					Form ff56 = new Form();
					ff56.setFormName("createWardforUrban.htm");
					ff56.setPrivileges(privileges);
					forms.add(ff56);

					Form ff57 = new Form();
					ff57.setFormName("viewwardforPRI.htm");
					ff57.setPrivileges(privileges);
					forms.add(ff57);

					Form ff58 = new Form();
					ff58.setFormName("viewwardforTraditional.htm");
					ff58.setPrivileges(privileges);
					forms.add(ff58);

					Form ff59 = new Form();
					ff59.setFormName("viewwardforUrban.htm");
					ff59.setPrivileges(privileges);
					forms.add(ff59);

					// GOVT TEMPLATE
					Form ff65 = new Form();
					ff65.setFormName("createGovtTemplate.htm");
					ff65.setPrivileges(privileges);
					forms.add(ff65);

					Form ff66 = new Form();
					ff66.setFormName("viewListOfTemplates.htm");
					ff66.setPrivileges(privileges);
					forms.add(ff66);

					Form ff67 = new Form();
					ff67.setFormName("generateGovtTemplate.htm");
					ff67.setPrivileges(privileges);
					forms.add(ff67);

					Form ff68 = new Form();
					ff68.setFormName("uploadGovtOrder.htm");
					ff68.setPrivileges(privileges);
					forms.add(ff68);

					// Configure Map
					Form ff69 = new Form();
					ff69.setFormName("constituencyMgr.htm");
					ff69.setPrivileges(privileges);
					forms.add(ff69);

					Form ff70 = new Form();
					ff70.setFormName("config_constituency.htm");
					ff70.setPrivileges(privileges);
					forms.add(ff70);

					/*
					 * Form ff20 = new Form();
					 * ff20.setFormName("managewarddetails.htm");
					 * ff20.setPrivileges(privileges); forms.add(ff20);
					 */

					List<MenuProfile> menuProfile = null;
					menuProfile = lgdMenuAdapterImpl
							.getParentInformation(forms);

					// Iterator it = formList.iterator();
					// model.addAttribute("menuProfile", menuProfile);

					session.setAttribute("menuProfile", menuProfile);

					return "redirect:home.htm";

				}

				// State Administrative Unit Manager
				else if (buser.equals(userName) && bpass.equals(passWord)) {

					session.setAttribute("login_key_id",
							"State Administrative Unit Manager");

					session.setAttribute("stateId", new Long(6));

					//session.setAttribute("districtId", new Long(58));

					session.setAttribute("subDistrictId", new Long(1034));

					session.setAttribute("lbCode", new Long(36160));

					session.setAttribute("role", "bslldAdmin");

					List<Privilege> privileges = new ArrayList<Privilege>();

					Privilege pp = new Privilege();
					pp.setAction("viewtouristplace.htm");

					pp.setType("view");
					privileges.add(pp);

					Set<Form> forms = new HashSet<Form>();

					// BLOCK
					Form ff38 = new Form();
					ff38.setFormName("new_block.htm");
					ff38.setPrivileges(privileges);
					forms.add(ff38);

					Form ff39 = new Form();
					ff39.setFormName("viewblock.htm");
					ff39.setPrivileges(privileges);
					forms.add(ff39);

					Form ff68 = new Form();
					ff68.setFormName("assignBlockVillageDisturbedState.htm");
					ff68.setPrivileges(privileges);
					forms.add(ff68);

					// DEPARTMENT
					Form ff42 = new Form();
					ff42.setFormName("createDepartmentState.htm");
					ff42.setPrivileges(privileges);
					forms.add(ff42);

					Form ff43 = new Form();
					ff43.setFormName("viewdepartment.htm");
					ff43.setPrivileges(privileges);
					forms.add(ff43);

					// ORGANIZATION
					Form ff47 = new Form();
					ff47.setFormName("createOrganizationState.htm");
					ff47.setPrivileges(privileges);
					forms.add(ff47);

					Form ff48 = new Form();
					ff48.setFormName("vieworganization.htm");
					ff48.setPrivileges(privileges);
					forms.add(ff48);

					// CONFIGURE GOVT ORDER GENERATRION
					Form ff57 = new Form();
					ff57.setFormName("administrativeUnitMgr.htm");
					ff57.setPrivileges(privileges);
					forms.add(ff57);

					// CONFIGURE MAP
					Form ff62 = new Form();
					ff62.setFormName("config_block.htm");
					ff62.setPrivileges(privileges);
					forms.add(ff62);

					// Reporting Hierarchy //error
					Form ff49 = new Form();
					ff49.setFormName("home.htm");
					ff49.setPrivileges(privileges);
					forms.add(ff49);

					Form ff50 = new Form();
					ff50.setFormName("home.htm");
					ff50.setPrivileges(privileges);
					forms.add(ff50);

					// GOVT TEMPLATE
					Form ff65 = new Form();
					ff65.setFormName("createGovtTemplate.htm");
					ff65.setPrivileges(privileges);
					forms.add(ff65);

					Form ff66 = new Form();
					ff66.setFormName("viewListOfTemplates.htm");
					ff66.setPrivileges(privileges);
					forms.add(ff66);

					Form ff67 = new Form();
					ff67.setFormName("generateGovtTemplate.htm");
					ff67.setPrivileges(privileges);
					forms.add(ff67);

					Form ff167 = new Form();
					ff167.setFormName("uploadGovtOrder.htm");
					ff167.setPrivileges(privileges);
					forms.add(ff167);

					List<MenuProfile> menuProfile = null;
					menuProfile = lgdMenuAdapterImpl
							.getParentInformation(forms);

					session.setAttribute("menuProfile", menuProfile);

					return "redirect:home.htm";
				}
				// State Urban Department Manager
				else if (ctuser.equals(userName) && ctpass.equals(passWord)) {

					session.setAttribute("login_key_id",
							"State Urban Department Manager");

					session.setAttribute("stateId", new Long(30));

					//session.setAttribute("districtId", new Long(20));

					session.setAttribute("localBodyId", new Long(10));

					session.setAttribute("loggedUserId", new Long(100));

					session.setAttribute("role", "UrbanManager");

					List<Privilege> privileges = new ArrayList<Privilege>();

					Privilege pp = new Privilege();
					pp.setAction("viewtouristplace.htm");

					pp.setType("view");
					privileges.add(pp);

					Set<Form> forms = new HashSet<Form>();

					// SYSTEM CONFIGURATION
					// Define LG Setup for Urban

					Form ff95 = new Form();
					ff95.setFormName("configMapLGDM.htm");
					ff95.setPrivileges(privileges);
					forms.add(ff95);

					Form ff54 = new Form();
					ff54.setFormName("local_gov_setupUrban.htm");
					ff54.setPrivileges(privileges);
					forms.add(ff54);

					Form ff73 = new Form();
					ff73.setFormName("lgdmForm.htm");
					ff73.setPrivileges(privileges);
					forms.add(ff73);

					
					Form ff74 = new Form();
					ff74.setFormName("lgdmFormPri.htm");
					ff74.setPrivileges(privileges);
					forms.add(ff74);

					Form ff75 = new Form();
					ff75.setFormName("lgdmForm.htm");
					ff75.setPrivileges(privileges);
					forms.add(ff75);

					// Create Local Body for Urban
					Form ff23 = new Form();
					ff23.setFormName("createLocalBodyforUrban.htm");
					ff23.setPrivileges(privileges);
					forms.add(ff23);

					Form ff87 = new Form();
					ff87.setFormName("designation_hierarchy_elected_urban.htm");
					ff87.setPrivileges(privileges);
					forms.add(ff87);

					Form ff92 = new Form();
					ff92.setFormName("designation_hierarchy_official_urban.htm");
					ff92.setPrivileges(privileges);
					forms.add(ff92);

					Form ff98 = new Form();
					ff98.setFormName("create_designation_reporting_urban.htm");
					ff98.setPrivileges(privileges);
					forms.add(ff98);

					Form ff26 = new Form();
					ff26.setFormName("viewLocalBodyforUrban.htm");
					ff26.setPrivileges(privileges);
					forms.add(ff26);

					// CONVERT LOCAL BODY
					Form ff27 = new Form();
					ff27.setFormName("convertRLBtoULB.htm");
					ff27.setPrivileges(privileges);
					forms.add(ff27);

					/*
					 * Form ff28 = new Form();
					 * ff28.setFormName("convertTLBtoULB.htm");
					 * ff28.setPrivileges(privileges); forms.add(ff28);
					 */

					// Reporting Hierarchy
					Form ff49 = new Form();
					ff49.setFormName("home.htm");
					ff49.setPrivileges(privileges);
					forms.add(ff49);

					Form ff50 = new Form();
					ff50.setFormName("home.htm");
					ff50.setPrivileges(privileges);
					forms.add(ff50);

					// GOVT TEMPLATE
					Form ff65 = new Form();
					ff65.setFormName("createGovtTemplate.htm");
					ff65.setPrivileges(privileges);
					forms.add(ff65);

					Form ff66 = new Form();
					ff66.setFormName("viewListOfTemplates.htm");
					ff66.setPrivileges(privileges);
					forms.add(ff66);

					Form ff67 = new Form();
					ff67.setFormName("generateGovtTemplate.htm");
					ff67.setPrivileges(privileges);
					forms.add(ff67);

					Form ff167 = new Form();
					ff167.setFormName("uploadGovtOrder.htm");
					ff167.setPrivileges(privileges);
					forms.add(ff167);

					List<MenuProfile> menuProfile = null;
					menuProfile = lgdMenuAdapterImpl
							.getParentInformation(forms);

					session.setAttribute("menuProfile", menuProfile);

					return "redirect:home.htm";

				}
				// State PR Department Manager

				else if (luser.equals(userName) && lpass.equals(passWord)) {

					// --Serialization Test with the Database ends

					session.setAttribute("login_key_id",
							"State PR Department Manager");

					session.setAttribute("stateId", new Long(6));

					//session.setAttribute("districtId", new Long(58));

					session.setAttribute("subDistrictId", new Long(1034));

					session.setAttribute("localBodyId", new Long(36027));

					session.setAttribute("loggedUserId", new Long(100));

					session.setAttribute("role", "lgadmin");

					List<Privilege> privileges = new ArrayList<Privilege>();

					Privilege pp = new Privilege();
					pp.setAction("viewtouristplace.htm");

					pp.setType("view");
					privileges.add(pp);

					Privilege pp1 = new Privilege();
					pp1.setAction("modifytouristaplace.htm");
					pp1.setType("modify");

					privileges.add(pp1);
					Set<Form> forms = new HashSet<Form>();

					// SYSTEM CONFIGURATION

					// Define LG Setup for PRI

					Form ff96 = new Form();
					ff96.setFormName("configMapPRI.htm");
					ff96.setPrivileges(privileges);
					forms.add(ff96);

					Form ff54 = new Form();
					ff54.setFormName("local_gov_setupPanchayat.htm");
					ff54.setPrivileges(privileges);
					forms.add(ff54);

					Form ff73 = new Form();
					ff73.setFormName("lgdmForm.htm");
					ff73.setPrivileges(privileges);
					forms.add(ff73);

					/*
					 * ////Define LG Setup for Traditional Form ff73 = new
					 * Form();
					 * ff73.setFormName("local_gov_setupTraditional.htm");
					 * ff73.setPrivileges(privileges); forms.add(ff73);
					 */

					// Create Local Body for PRI & Traditional
					Form ff23 = new Form();
					ff23.setFormName("createLocalBodyforPRI.htm");
					ff23.setPrivileges(privileges);
					forms.add(ff23);

					Form ff82 = new Form();
					ff82.setFormName("designation_hierarchy_elected_panchayat.htm");
					ff82.setPrivileges(privileges);
					forms.add(ff82);

					Form ff90 = new Form();
					ff90.setFormName("designation_hierarchy_official_panchayat.htm");
					ff90.setPrivileges(privileges);
					forms.add(ff90);

					Form ff93 = new Form();
					ff93.setFormName("create_designation_reporting_panchayat.htm");
					ff93.setPrivileges(privileges);
					forms.add(ff93);

					/*
					 * //traditional Form ff74 = new Form();
					 * ff74.setFormName("createLocalBodyforTraditional.htm");
					 * ff74.setPrivileges(privileges); forms.add(ff74);
					 */

					Form ff75 = new Form();
					ff75.setFormName("viewLocalBodyforPRI.htm");
					ff75.setPrivileges(privileges);
					forms.add(ff75);
					/*
					 * //traditional Form ff76 = new Form();
					 * ff76.setFormName("viewLocalBodyforTraditional.htm");
					 * ff76.setPrivileges(privileges); forms.add(ff76);
					 */

					// CONVERT LOCAL BODY
					
					 Form ff77 = new Form();
					 ff77.setFormName("convertRLBtoTLB.htm");
					 ff77.setPrivileges(privileges); 
					 forms.add(ff77);
		
					// TLBtoRLB
					Form ff78 = new Form();
					ff78.setFormName("home.htm");
					ff78.setPrivileges(privileges);
					forms.add(ff78);

					// Reporting Hierarchy
					Form ff49 = new Form();
					ff49.setFormName("home.htm");
					ff49.setPrivileges(privileges);
					forms.add(ff49);

					Form ff50 = new Form();
					ff50.setFormName("home.htm");
					ff50.setPrivileges(privileges);
					forms.add(ff50);

					// GOVT TEMPLATE
					Form ff65 = new Form();
					ff65.setFormName("createGovtTemplate.htm");
					ff65.setPrivileges(privileges);
					forms.add(ff65);

					Form ff66 = new Form();
					ff66.setFormName("viewListOfTemplates.htm");
					ff66.setPrivileges(privileges);
					forms.add(ff66);

					Form ff67 = new Form();
					ff67.setFormName("generateGovtTemplate.htm");
					ff67.setPrivileges(privileges);
					forms.add(ff67);

					Form ff167 = new Form();
					ff167.setFormName("uploadGovtOrder.htm");
					ff167.setPrivileges(privileges);
					forms.add(ff167);

					List<MenuProfile> menuProfile = null;
					menuProfile = lgdMenuAdapterImpl
							.getParentInformation(forms);

					// Iterator it = formList.iterator();
					// model.addAttribute("menuProfile", menuProfile);

					session.setAttribute("menuProfile", menuProfile);

					// return "redirect:switchunit.htm";
					return "redirect:home.htm";
				}
				// user traditional

				else if (tuser.equals(userName) && tpass.equals(passWord)) {

					// --Serialization Test with the Database ends

					session.setAttribute("login_key_id",
							"State Traditional Department Manager");

					session.setAttribute("stateId", new Long(6));

					//session.setAttribute("districtId", new Long(58));

					session.setAttribute("subDistrictId", new Long(1034));

					session.setAttribute("localBodyId", new Long(36027));

					session.setAttribute("loggedUserId", new Long(100));

					session.setAttribute("role", "lgadmin");

					List<Privilege> privileges = new ArrayList<Privilege>();

					Privilege pp = new Privilege();
					pp.setAction("viewtouristplace.htm");

					pp.setType("view");
					privileges.add(pp);

					Privilege pp1 = new Privilege();
					pp1.setAction("modifytouristaplace.htm");
					pp1.setType("modify");

					privileges.add(pp1);
					Set<Form> forms = new HashSet<Form>();

					// SYSTEM CONFIGURATION

					Form ff97 = new Form();
					ff97.setFormName("configMapTrd.htm");
					ff97.setPrivileges(privileges);
					forms.add(ff97);

					Form ff73 = new Form();
					ff73.setFormName("lgdmForm.htm");
					ff73.setPrivileges(privileges);
					forms.add(ff73);

					// //Define LG Setup for Traditional
					Form ff54 = new Form();
					ff54.setFormName("local_gov_setupTraditional.htm");
					ff54.setPrivileges(privileges);
					forms.add(ff54);

					// traditional
					Form ff74 = new Form();
					ff74.setFormName("createLocalBodyforTraditional.htm");
					ff74.setPrivileges(privileges);
					forms.add(ff74);

					Form ff83 = new Form();
					ff83.setFormName("designation_hierarchy_elected_traditional.htm");
					ff83.setPrivileges(privileges);
					forms.add(ff83);

					Form ff91 = new Form();
					ff91.setFormName("designation_hierarchy_official_traditional.htm");
					ff91.setPrivileges(privileges);
					forms.add(ff91);

					Form ff94 = new Form();
					ff94.setFormName("create_designation_reporting_traditional.htm");
					ff94.setPrivileges(privileges);
					forms.add(ff94);

					// traditional
					Form ff76 = new Form();
					ff76.setFormName("viewLocalBodyforTraditional.htm");
					ff76.setPrivileges(privileges);
					forms.add(ff76);

					// CONVERT LOCAL BODY
					
					 Form ff77 = new Form();
					 ff77.setFormName("convertRLBtoTLB.htm");
					 ff77.setPrivileges(privileges); 
					 forms.add(ff77);
					 
					// TLBtoRLB
					/*
					 * Form ff78 = new Form(); ff78.setFormName("home.htm");
					 * ff78.setPrivileges(privileges); forms.add(ff78);
					 */

					// Reporting Hierarchy
					Form ff49 = new Form();
					ff49.setFormName("home.htm");
					ff49.setPrivileges(privileges);
					forms.add(ff49);

					Form ff50 = new Form();
					ff50.setFormName("home.htm");
					ff50.setPrivileges(privileges);
					forms.add(ff50);

					// GOVT TEMPLATE
					Form ff65 = new Form();
					ff65.setFormName("createGovtTemplate.htm");
					ff65.setPrivileges(privileges);
					forms.add(ff65);

					Form ff66 = new Form();
					ff66.setFormName("viewListOfTemplates.htm");
					ff66.setPrivileges(privileges);
					forms.add(ff66);

					Form ff67 = new Form();
					ff67.setFormName("generateGovtTemplate.htm");
					ff67.setPrivileges(privileges);
					forms.add(ff67);

					Form ff167 = new Form();
					ff167.setFormName("uploadGovtOrder.htm");
					ff167.setPrivileges(privileges);
					forms.add(ff167);

					List<MenuProfile> menuProfile = null;
					menuProfile = lgdMenuAdapterImpl
							.getParentInformation(forms);

					// Iterator it = formList.iterator();
					// model.addAttribute("menuProfile", menuProfile);

					session.setAttribute("menuProfile", menuProfile);

					// return "redirect:switchunit.htm";
					return "redirect:home.htm";
				}

				// common user

				else if (vuser.equals(userName) && vpass.equals(passWord)) {
					/*System.out
							.println("suser ====================================================================");*/
					session.setAttribute("login_key_id", "LGD User");

					//session.setAttribute("stateId", new Long(6));

					//session.setAttribute("districtId", new Long(20));

					session.setAttribute("localBodyId", new Long(10));

					session.setAttribute("loggedUserId", new Long(100));

					session.setAttribute("role", "stateRevenueDept");

					Set<in.nic.pes.common.menu.pojo.Form> forms = new HashSet<in.nic.pes.common.menu.pojo.Form>();

					List<Privilege> privileges = new ArrayList<Privilege>();

					Privilege pp = new Privilege();
					pp.setAction("");

					pp.setType("view");
					privileges.add(pp);

					/*
					 * // STATE Form ff1 = new Form();
					 * ff1.setFormName("add_state.htm");
					 * ff1.setPrivileges(privileges); forms.add(ff1);
					 */

					Form ff2 = new Form();
					ff2.setFormName("viewstate.htm");
					ff2.setPrivileges(privileges);
					forms.add(ff2);

					// DISTRICT
					Form ff3 = new Form();
					ff3.setFormName("add_district.htm");
					ff3.setPrivileges(privileges);
					forms.add(ff3);

					Form ff4 = new Form();
					ff4.setFormName("viewdistrict.htm");
					ff4.setPrivileges(privileges);
					forms.add(ff4);

					// SUBDISTRICT
					Form ff5 = new Form();
					ff5.setFormName("new_subdistrict.htm");
					ff5.setPrivileges(privileges);
					forms.add(ff5);

					Form ff6 = new Form();
					ff6.setFormName("viewsubdistrict.htm");
					ff6.setPrivileges(privileges);
					forms.add(ff6);

					// VILLAGE
					Form ff7 = new Form();
					ff7.setFormName("createVillage.htm");
					ff7.setPrivileges(privileges);
					forms.add(ff7);

					Form ff8 = new Form();
					ff8.setFormName("viewvillage.htm");
					ff8.setPrivileges(privileges);
					forms.add(ff8);

					// SHIFT
					Form ff9 = new Form();
					ff9.setFormName("shiftdistrict.htm");
					ff9.setPrivileges(privileges);
					forms.add(ff9);

					Form ff10 = new Form();
					ff10.setFormName("shiftSubDistrict.htm");
					ff10.setPrivileges(privileges);
					forms.add(ff10);

					Form ff11 = new Form();
					ff11.setFormName("shiftblock.htm");
					ff11.setPrivileges(privileges);
					forms.add(ff11);

					Form ff12 = new Form();
					ff12.setFormName("shiftvillageblock.htm");
					ff12.setPrivileges(privileges);
					forms.add(ff12);

					Form ff13 = new Form();
					ff13.setFormName("shiftvillageSubDistrict.htm");
					ff13.setPrivileges(privileges);
					forms.add(ff13);

					// INVALIDATE
					Form ff14 = new Form();
					ff14.setFormName("invalidatevillage.htm");
					ff14.setPrivileges(privileges);
					forms.add(ff14);
					
					Form ff120 = new Form();
					ff120.setFormName("invalidateprilocalbody.htm");
					ff120.setPrivileges(privileges);
					forms.add(ff120);

					Form ff15 = new Form();
					ff15.setFormName("invalidatesubdistrict.htm");
					ff15.setPrivileges(privileges);
					forms.add(ff15);

					Form ff16 = new Form();
					ff16.setFormName("invalidatedistrict.htm");
					ff16.setPrivileges(privileges);
					forms.add(ff16);

					Form ff101 = new Form();
					ff101.setFormName("invalidateLocalBodyUrban.htm");
					ff101.setPrivileges(privileges);
					forms.add(ff101);

					Form ff102 = new Form();
					ff102.setFormName("invalidateLocalBodyPanchayat.htm");
					ff102.setPrivileges(privileges);
					forms.add(ff102);

					Form ff103 = new Form();
					ff103.setFormName("invalidateLocalBodyTraditional.htm");
					ff103.setPrivileges(privileges);
					forms.add(ff103);
					Form ff104 = new Form();
					ff104.setFormName("invalidateblock.htm");
					ff104.setPrivileges(privileges);
					forms.add(ff104);

					// CONFIGURE GOVT ORDER GENERATRION
					Form ff17 = new Form();
					ff17.setFormName("lrmForm.htm");
					ff17.setPrivileges(privileges);
					forms.add(ff17);

					// CONFIGURE MAP
					Form ff18 = new Form();
					ff18.setFormName("config_landregion.htm");
					ff18.setPrivileges(privileges);
					forms.add(ff18);

					Form ff19 = new Form();
					ff19.setFormName("nomenclature_sub_district.htm");
					ff19.setPrivileges(privileges);
					forms.add(ff19);

					Form ff95 = new Form();
					ff95.setFormName("configMapLGDM.htm");
					ff95.setPrivileges(privileges);
					forms.add(ff95);

					Form ff96 = new Form();
					ff96.setFormName("configMapPRI.htm");
					ff96.setPrivileges(privileges);
					forms.add(ff96);

					Form ff97 = new Form();
					ff97.setFormName("configMapTrd.htm");
					ff97.setPrivileges(privileges);
					forms.add(ff97);

					// GOVT TEMPLATE
					Form ff20 = new Form();
					ff20.setFormName("createGovtTemplatePri.htm");
					ff20.setPrivileges(privileges);
					forms.add(ff20);
					
					Form ff21 = new Form();
					ff21.setFormName("createGovtTemplateUrban.htm");
					ff21.setPrivileges(privileges);
					forms.add(ff21);
					
					Form ff22 = new Form();
					ff22.setFormName("createGovtTemplateTra.htm");
					ff22.setPrivileges(privileges);
					forms.add(ff22);
					
					Form ff23 = new Form();
					ff23.setFormName("createGovtTemplateBlk.htm");
					ff23.setPrivileges(privileges);
					forms.add(ff23);
					
					Form ff24 = new Form();
					ff24.setFormName("createGovtTemplateCenter.htm");
					ff24.setPrivileges(privileges);
					forms.add(ff24);
					
					Form ff25 = new Form();
					ff25.setFormName("createGovtTemplateLR.htm");
					ff25.setPrivileges(privileges);
					forms.add(ff25);

					Form ff26 = new Form();
					ff26.setFormName("viewListOfTemplates.htm");
					ff26.setPrivileges(privileges);
					forms.add(ff26);

					Form ff27 = new Form();
					ff27.setFormName("generateGovtTemplate.htm");
					ff27.setPrivileges(privileges);
					forms.add(ff27);

					Form ff28 = new Form();
					ff28.setFormName("uploadGovtOrder.htm");
					ff28.setPrivileges(privileges);
					forms.add(ff28);

					// LOCAL BODY TYPE
					Form ff29 = new Form();
					ff29.setFormName("localgovtType.htm");
					ff29.setPrivileges(privileges);
					forms.add(ff29);

					Form ff30 = new Form();
					ff30.setFormName("viewlocalgovttype.htm");
					ff30.setPrivileges(privileges);
					forms.add(ff30);

					// DEPARTMENT
					Form ff31 = new Form();
					ff31.setFormName("createDepartmentCentral.htm");
					ff31.setPrivileges(privileges);
					forms.add(ff31);

					Form ff32 = new Form();
					ff32.setFormName("createDepartmentState.htm");
					ff32.setPrivileges(privileges);
					forms.add(ff32);

					Form ff33 = new Form();
					ff33.setFormName("viewdepartment.htm");
					ff33.setPrivileges(privileges);
					forms.add(ff33);

					Form ff110 = new Form();
					ff110.setFormName("viewdepartmentforstate.htm");
					ff110.setPrivileges(privileges);
					forms.add(ff110);
					
					// ORGANIZATION TYPE
					Form ff34 = new Form();
					ff34.setFormName("addOrganizationType.htm");
					ff34.setPrivileges(privileges);
					forms.add(ff34);

					Form ff35 = new Form();
					ff35.setFormName("modifyOrganizationType.htm");
					ff35.setPrivileges(privileges);
					forms.add(ff35);

					Form ff36 = new Form();
					ff36.setFormName("home.htm");
					ff36.setPrivileges(privileges);
					forms.add(ff36);

					// ORGANIZATION
					Form ff37 = new Form();
					ff37.setFormName("createOrganizationCentral.htm");
					ff37.setPrivileges(privileges);
					forms.add(ff37);

					Form ff106 = new Form();
					ff106.setFormName("createOrganizationState.htm");
					ff106.setPrivileges(privileges);
					forms.add(ff106);

					Form ff38 = new Form();
					ff38.setFormName("vieworganization.htm");
					ff38.setPrivileges(privileges);
					forms.add(ff38);
					Form ff111 = new Form();
					ff111.setFormName("vieworganizationforstate.htm");
					ff111.setPrivileges(privileges);
					forms.add(ff111);

					// MINISTRY
					Form ff39 = new Form();
					ff39.setFormName("createMinistry.htm");
					ff39.setPrivileges(privileges);
					forms.add(ff39);

					Form ff40 = new Form();
					ff40.setFormName("viewministry.htm");
					ff40.setPrivileges(privileges);
					forms.add(ff40);

					// CONFIGURE GOVT ORDER GENERATRION
					Form ff41 = new Form();
					ff41.setFormName("centralAdmin.htm");
					ff41.setPrivileges(privileges);
					forms.add(ff41);

					// UNRESOLVED ENTITY
					Form ff42 = new Form();
					ff42.setFormName("viewResolveEntitiesinDisturbedStateLR.htm");
					ff42.setPrivileges(privileges);
					forms.add(ff42);

					Form ff43 = new Form();
					ff43.setFormName("assignBlockVillageDisturbedState.htm");
					ff43.setPrivileges(privileges);
					forms.add(ff43);

					// UNPUBLISHED ITEM
					Form ff44 = new Form();
					ff44.setFormName("viewUnpublishedItems.htm");
					ff44.setPrivileges(privileges);
					forms.add(ff44);

					// CONSTITUENCY //error
					Form ff45 = new Form();
					ff45.setFormName("parliament_Constituency.htm");
					ff45.setPrivileges(privileges);
					forms.add(ff45);

					Form ff46 = new Form();
					ff46.setFormName("assembly_Constituency.htm");
					ff46.setPrivileges(privileges);
					forms.add(ff46);

					Form ff53 = new Form();
					ff53.setFormName("map_constitutionBody.htm");
					ff53.setPrivileges(privileges);
					forms.add(ff53);

					Form ff54 = new Form();
					ff54.setFormName("home.htm");
					ff54.setPrivileges(privileges);
					forms.add(ff54);

					Form ff55 = new Form();
					ff55.setFormName("home.htm");
					ff55.setPrivileges(privileges);
					forms.add(ff55);

					Form ff56 = new Form();
					ff56.setFormName("home.htm");
					ff56.setPrivileges(privileges);
					forms.add(ff56);

					// WARD

					Form ff57 = new Form();
					ff57.setFormName("createWardforPRI.htm");
					ff57.setPrivileges(privileges);
					forms.add(ff57);

					Form ff58 = new Form();
					ff58.setFormName("createWardforTraditional.htm");
					ff58.setPrivileges(privileges);
					forms.add(ff58);

					Form ff59 = new Form();
					ff59.setFormName("createWardforUrban.htm");
					ff59.setPrivileges(privileges);
					forms.add(ff59);

					Form ff60 = new Form();
					ff60.setFormName("viewwardforPRI.htm");
					ff60.setPrivileges(privileges);
					forms.add(ff60);

					Form ff61 = new Form();
					ff61.setFormName("viewwardforTraditional.htm");
					ff61.setPrivileges(privileges);
					forms.add(ff61);

					Form ff62 = new Form();
					ff62.setFormName("viewwardforUrban.htm");
					ff62.setPrivileges(privileges);
					forms.add(ff62);

					// BLOCK
					Form ff63 = new Form();
					ff63.setFormName("new_block.htm");
					ff63.setPrivileges(privileges);
					forms.add(ff63);

					Form ff64 = new Form();
					ff64.setFormName("viewblock.htm");
					ff64.setPrivileges(privileges);
					forms.add(ff64);

					// CONFIGURE GOVT ORDER GENERATRION
					Form ff65 = new Form();
					ff65.setFormName("administrativeUnitMgr.htm");
					ff65.setPrivileges(privileges);
					forms.add(ff65);

					// CONFIGURE MAP
					Form ff66 = new Form();
					ff66.setFormName("config_block.htm");
					ff66.setPrivileges(privileges);
					forms.add(ff66);

					Form ff84 = new Form();
					ff84.setFormName("config_constituency.htm");
					ff84.setPrivileges(privileges);
					forms.add(ff84);

					// Reporting Hierarchy //error
					Form ff67 = new Form();
					ff67.setFormName("add_designation.htm");
					ff67.setPrivileges(privileges);
					forms.add(ff67);

					Form ff68 = new Form();
					ff68.setFormName("add_reporting_structure.htm");
					ff68.setPrivileges(privileges);
					forms.add(ff68);
					
					

					// SYSTEM CONFIGURATION
					// Define LG Setup for Urban
					Form ff69 = new Form();
					ff69.setFormName("local_gov_setupUrban.htm");
					ff69.setPrivileges(privileges);
					forms.add(ff69);

					Form ff70 = new Form();
					ff70.setFormName("lgdmForm.htm");
					ff70.setPrivileges(privileges);
					forms.add(ff70);

					// Create Local Body for Urban
					Form ff71 = new Form();
					ff71.setFormName("createLocalBodyforUrban.htm");
					ff71.setPrivileges(privileges);
					forms.add(ff71);

					Form ff72 = new Form();
					ff72.setFormName("viewLocalBodyforUrban.htm");
					ff72.setPrivileges(privileges);
					forms.add(ff72);

					// CONVERT LOCAL BODY
					Form ff73 = new Form();
					ff73.setFormName("convertRLBtoULB.htm");
					ff73.setPrivileges(privileges);
					forms.add(ff73);

					/*
					 * Form ff74 = new Form();
					 * ff74.setFormName("convertTLBtoULB.htm");
					 * ff74.setPrivileges(privileges); forms.add(ff74);
					 */

					// SYSTEM CONFIGURATION

					// Define LG Setup for PRI
					Form ff75 = new Form();
					ff75.setFormName("local_gov_setupPanchayat.htm");
					ff75.setPrivileges(privileges);
					forms.add(ff75);

					Form ff76 = new Form();
					ff76.setFormName("lgdmForm.htm");
					ff76.setPrivileges(privileges);
					forms.add(ff76);

					// //Define LG Setup for Traditional
					Form ff77 = new Form();
					ff77.setFormName("local_gov_setupTraditional.htm");
					ff77.setPrivileges(privileges);
					forms.add(ff77);

					// Create Local Body for PRI & Traditional
					Form ff78 = new Form();
					ff78.setFormName("createLocalBodyforPRI.htm");
					ff78.setPrivileges(privileges);
					forms.add(ff78);

					// traditional
					Form ff79 = new Form();
					ff79.setFormName("createLocalBodyforTraditional.htm");
					ff79.setPrivileges(privileges);
					forms.add(ff79);

					Form ff80 = new Form();
					ff80.setFormName("viewLocalBodyforPRI.htm");
					ff80.setPrivileges(privileges);
					forms.add(ff80);
					// traditional
					Form ff81 = new Form();
					ff81.setFormName("viewLocalBodyforTraditional.htm");
					ff81.setPrivileges(privileges);
					forms.add(ff81);

					Form ff82 = new Form();
					ff82.setFormName("designation_hierarchy_elected_panchayat.htm");
					ff82.setPrivileges(privileges);
					forms.add(ff82);

					Form ff83 = new Form();
					ff83.setFormName("designation_hierarchy_elected_traditional.htm");
					ff83.setPrivileges(privileges);
					forms.add(ff83);

					Form ff87 = new Form();
					ff87.setFormName("designation_hierarchy_elected_urban.htm");
					ff87.setPrivileges(privileges);
					forms.add(ff87);

					Form ff90 = new Form();
					ff90.setFormName("designation_hierarchy_official_panchayat.htm");
					ff90.setPrivileges(privileges);
					forms.add(ff90);

					Form ff91 = new Form();
					ff91.setFormName("designation_hierarchy_official_traditional.htm");
					ff91.setPrivileges(privileges);
					forms.add(ff91);

					Form ff92 = new Form();
					ff92.setFormName("designation_hierarchy_official_urban.htm");
					ff92.setPrivileges(privileges);
					forms.add(ff92);

					Form ff93 = new Form();
					ff93.setFormName("create_designation_reporting_panchayat.htm");
					ff93.setPrivileges(privileges);
					forms.add(ff93);

					Form ff94 = new Form();
					ff94.setFormName("create_designation_reporting_traditional.htm");
					ff94.setPrivileges(privileges);
					forms.add(ff94);

					Form ff98 = new Form();
					ff98.setFormName("create_designation_reporting_urban.htm");
					ff98.setPrivileges(privileges);
					forms.add(ff98);

					// CONVERT LOCAL BODY
					
					 Form ff85 = new Form();
					 ff85.setFormName("convertRLBtoTLB.htm");
					 ff85.setPrivileges(privileges); forms.add(ff85);
					
					 Form ff150 = new Form();
					 ff150.setFormName("convertTLBtoRLB.htm");
					 ff150.setPrivileges(privileges); 
					 forms.add(ff150);

					// TLBtoRLB
					Form ff86 = new Form();
					ff86.setFormName("home.htm");
					ff86.setPrivileges(privileges);
					forms.add(ff86);

					// un resolve entities

					Form ff99 = new Form();
					ff99.setFormName("viewResolveEntitiesinDisturbedStateLR.htm");
					ff99.setPrivileges(privileges);
					forms.add(ff99);

					Form ff100 = new Form();
					ff100.setFormName("assignBlockVillageDisturbedState.htm");
					ff100.setPrivileges(privileges);
					forms.add(ff100);

					Form ff105 = new Form();
					ff105.setFormName("AddGazettePublicationDate.htm");
					ff105.setPrivileges(privileges);
					forms.add(ff105);

					Form ff108 = new Form();
					ff108.setFormName("Modifyparliament_Constituency.htm");
					ff108.setPrivileges(privileges);
					forms.add(ff108);

					Form ff107 = new Form();
					ff107.setFormName("ModifyAssembly_Constituency.htm");
					ff107.setPrivileges(privileges);
					forms.add(ff107);

					Form ff109 = new Form();
					ff109.setFormName("standard_Code.htm");
					ff109.setPrivileges(privileges);
					forms.add(ff109);
					
					//Designation Master Menu For Center
					Form ff112 = new Form();
					ff112.setFormName("designaton_master_center.htm");
					ff112.setPrivileges(privileges);
					forms.add(ff112);
					//Designation Master Menu For State
					Form ff113 = new Form();
					ff113.setFormName("designaton_master_state.htm");
					ff113.setPrivileges(privileges);
					forms.add(ff113);
					
					Form ff114 = new Form();
					ff114.setFormName("add_reporting_structure_state.htm");
					ff114.setPrivileges(privileges);
					forms.add(ff114);

					Form ff115 = new Form();
					ff115.setFormName("lgdmFormPRI.htm");
					ff115.setPrivileges(privileges);
					forms.add(ff115);
					Form ff116 = new Form();
					ff116.setFormName("lgdmFormTrade.htm");
					ff116.setPrivileges(privileges);
					forms.add(ff116);
					
					Form ff117 = new Form();
					ff117.setFormName("viewResolveEntitiesinDisturbedStatePRI.htm");
					ff117.setPrivileges(privileges);
					forms.add(ff117);
					
					Form ff118 = new Form();
					ff118.setFormName("viewResolveEntitiesinDisturbedStateTra.htm");
					ff118.setPrivileges(privileges);
					forms.add(ff118);
					
					Form ff119 = new Form();
					ff119.setFormName("viewResolveEntitiesinDisturbedStateULB.htm");
					ff119.setPrivileges(privileges);
					forms.add(ff119);
					
					
					
					Form ff220 = new Form();
					ff220.setFormName("viewListOfTemplatesPri.htm");
					ff220.setPrivileges(privileges);
					forms.add(ff220);
					
					Form ff221 = new Form();
					ff221.setFormName("viewListOfTemplatesUrban.htm");
					ff221.setPrivileges(privileges);
					forms.add(ff221);
					
					Form ff222 = new Form();
					ff222.setFormName("viewListOfTemplatesTra.htm");
					ff222.setPrivileges(privileges);
					forms.add(ff222);
					
					Form ff223 = new Form();
					ff223.setFormName("viewListOfTemplatesBlk.htm");
					ff223.setPrivileges(privileges);
					forms.add(ff223);
					
					Form ff224 = new Form();
					ff224.setFormName("viewListOfTemplatesCenter.htm");
					ff224.setPrivileges(privileges);
					forms.add(ff224);
					
					Form ff225 = new Form();
					ff225.setFormName("viewListOfTemplatesLR.htm");
					ff225.setPrivileges(privileges);
					forms.add(ff225);
					
					
					//sessionObject.setIsCenterorstate(selection.getIsCenterorstate());
					//sessionObject.setRemoteAddress(req.getRemoteAddr());
					SessionObject sessionObject = new SessionObject();
					sessionObject.setSlc(commonService.getSlc(6));				
					sessionObject.setActive(true);
					session.setAttribute("sessionObject", sessionObject);

					// REPORTS
					/*
					 * Form ff71 = new Form(); ff71.setFormName("");
					 * ff71.setPrivileges(privileges); forms.add(ff71);
					 * 
					 * Form ff72 = new Form(); ff72.setFormName("");
					 * ff72.setPrivileges(privileges); forms.add(ff72);
					 */

					List<MenuProfile> menuProfile = null;
					menuProfile = lgdMenuAdapterImpl
							.getParentInformation(forms);
					session.setAttribute("menuProfile", menuProfile);
					return "redirect:home.htm";
				}

				else {
					model.addAttribute("login",
							"Invalid username or password.!");
					model.addAttribute("stateSourceList",
							populateSourceStateList());
					return "login";
				}
			}
		}
		catch (SQLException sqle)
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1,
					sqle);
			
			return "redirect:infringement.htm";
		}
		catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1,
					e);

			return "redirect:errorRedirect.htm";
		}
		 
	}
	
	
	@RequestMapping(value = "/globalAddQuery", method = RequestMethod.GET)
	public String globalAddQuery(@ModelAttribute("loginForm") LoginForm loginForm, Model model, HttpSession session,HttpServletRequest request) throws Exception
		{
		
		PesApplicationMaster pesApplicationMaster=new PesApplicationMaster();
		pesApplicationMaster=villageService.getApplicationURL(3);
		String Url=this.getAplicationUrl(pesApplicationMaster.getPesApplicationUrl())+"globalAddQuery.htm";
		return "redirect:"+Url;
		}
	
	public String getAplicationUrl(String url)
	{
		String _url = url.substring(0,url.lastIndexOf("/")+1);
//printMessage("INFO: Actual Application URL = "+_url);
		return _url;
	}
	
	
	@RequestMapping(value = "/globalviewAllFaq", method = RequestMethod.GET)
	public String viewAllFaq(@ModelAttribute("loginForm") LoginForm loginForm, Model model, HttpSession session,HttpServletRequest request) throws Exception
		{
		
		PesApplicationMaster pesApplicationMaster=new PesApplicationMaster();
		pesApplicationMaster=villageService.getApplicationURL(3);
		String Url=this.getAplicationUrl(pesApplicationMaster.getPesApplicationUrl())+"viewAllFaq.htm";
		return "redirect:"+Url;
		}
	
	@RequestMapping(value = "/badRequest", method = RequestMethod.GET)
	public String handleBadRequest(Model model, HttpSession session, HttpServletRequest request) {
		session.invalidate();
		return "/common/badRequest";
	}
	
	@RequestMapping(value = "/nonscript", method = RequestMethod.GET)
	public String nonscript() {
		return "nonscript";
	}
	
	/**
	 *  Mobile App for Download by Maneesh on 11-Feb-2015 
	 * @param request
	 * @param response
	 * @throws IOException
	 */

	@RequestMapping(value="/downloadFile",method = RequestMethod.GET)
    public void doDownload(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
 
		InputStream inputStreamPro = getClass().getClassLoader().getResourceAsStream("/mobileApp.properties");
        Properties properties = new Properties();
        properties.load(inputStreamPro);
        String filePath=properties.getProperty("mobileapp.location");
		 filePath = filePath.replace("\\\\", "/");
		 final int BUFFER_SIZE = 4096;
		// get absolute path of the application
       ServletContext context = request.getServletContext();
       /* String appPath = context.getRealPath("");
        System.out.println("appPath = " + appPath);
 
        // construct the complete absolute path of the file
        String fullPath = appPath + filePath;      */
        File downloadFile = new File(filePath);
        if(downloadFile.exists()){
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        // get MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
        }else{
        	
        }
 
    }
	
	
	
}