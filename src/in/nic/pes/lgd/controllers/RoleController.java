/*package in.nic.pes.common.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.ietf.jgss.MessageProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import in.nic.pes.common.beans.Contact;
import in.nic.pes.common.beans.RoleForm;
import in.nic.pes.common.dao.CommonDao;
import in.nic.pes.common.dao.CommonHibernateDao;

import in.nic.pes.common.dao.RoleDAOImpl;
import in.nic.pes.common.model.FormsMaster;
import in.nic.pes.common.model.FormsMetadata;
import in.nic.pes.common.model.PesApplication;
import in.nic.pes.common.model.Roles;
import in.nic.pes.common.model.UserGroup;
import in.nic.pes.common.services.RoleService;
import in.nic.pes.common.validator.RoleFormValidator;

*//**
*
* @author Archana Negi
* created on 2 Sep 2011
*//*

@Controller
@SessionAttributes({"FETCH_PES_APPS_RESULTS_KEY","SEARCH_FORMS_RESULTS_KEY","FETCH_GROUPS_RESULTS_KEY"})
public class RoleController 
{
	
	private static final Logger logger = Logger.getLogger(RoleController.class);
	@Autowired
	RoleService roleService;
	
	//@Autowired
	//private CommonDao commondao;
	
	@Autowired
	private RoleFormValidator validator;
	
	@RequestMapping("/default")
	public String home(){
		return "home";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("/CreateUserLoad")
	public ModelAndView userLoad()
	{
		ModelAndView mav = new ModelAndView("newRole");
		RoleForm role = new RoleForm();
		mav.getModelMap().put("newRole", role);
		return mav;
	}
	

	@RequestMapping("/ViewAllRoles")
	public ModelAndView getAllRoles(@RequestParam(value="actionFlag",required=false,defaultValue="")String actionFlag)
	{
		ModelAndView mav = new ModelAndView("showRoles");
		RoleForm role = new RoleForm();
		
		List<PesApplication> apps = roleService.getAllPESApps();
		mav.addObject("FETCH_PES_APPS_RESULTS_KEY", apps);
		
		
		List<Roles> roles = roleService.getAllRoles();
		mav.addObject("SEARCH_ROLES_RESULTS_KEY", roles);
		mav.addObject("action",actionFlag);
		mav.getModelMap().put("showRoles", role);
		return mav;
	}

	
	@RequestMapping(value="/saveRole", method=RequestMethod.GET)
	public ModelAndView newuserForm()
	{
		ModelAndView mav = new ModelAndView("newRole");
		RoleForm role = new RoleForm();
		List<PesApplication> apps = roleService.getAllPESApps();	
		mav.addObject("FETCH_PES_APPS_RESULTS_KEY", apps);
		

		
		mav.getModelMap().put("newRole", role);
		
		return mav;
	}
	
	@RequestMapping("/populateForms")
	public ModelAndView populateForms(@RequestParam(value="pesApplicationName",required= false, defaultValue="") Integer pesApplicationName,@ModelAttribute("newRole")RoleForm role, BindingResult result, SessionStatus status)
	{
		ModelAndView mav = new ModelAndView("newRole",result.getModel());
		
		List<FormsMaster> fm = roleService.populateForms(role,pesApplicationName);
		mav.addObject("SEARCH_FORMS_RESULTS_KEY", fm);

		return mav;
	}
	
	@RequestMapping("/populateGroups")
	public ModelAndView populateGroups(@RequestParam(value="pesApplicationName",required= false, defaultValue="") Integer pesApplicationName,@ModelAttribute("newRole")RoleForm role, BindingResult result, SessionStatus status)
	{
		ModelAndView mav = new ModelAndView("newRole",result.getModel());
		
		List<UserGroup> userg=roleService.getAllGroups(role,pesApplicationName);
		mav.addObject("FETCH_GROUPS_RESULTS_KEY", userg);

		return mav;
	}
	
	@RequestMapping("/populateFields")
	public ModelAndView populateFields(@ModelAttribute("newRole")RoleForm role, BindingResult result, SessionStatus status)
	{
		ModelAndView mav = new ModelAndView("newRole",result.getModel());
		
		try{
			List<FormsMaster> fmt = roleService.populateSelectedForms(role);
			if(fmt != null && fmt.size() > 0){
			mav.addObject("SEARCH_SELECTED_FORMS_METADATA_RESULTS_KEY", fmt);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
		try{
		List<FormsMaster> fmv = roleService.populateUnselectedForms(role);
		if(fmv != null && fmv.size() > 0){
		mav.addObject("SEARCH_FORMS_RESULTS_KEY", fmv);
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		
		try{
			if(role.getPesApplicationName() == 11){
			
		List<FormsMetadata> fm = roleService.populateFields(role);
		if(fm != null && fm.size() > 0){
		mav.addObject("SEARCH_FORMS_METADATA_RESULTS_KEY", fm);
		
		}
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		

		
		return mav;
	}
	
	@RequestMapping(value="/saveRole", method=RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("newRole")RoleForm role, BindingResult result, SessionStatus status)
	{
		System.out.println(" testing 123");
		ModelAndView mv = new ModelAndView("newRole");
		validator.validate(role, result);
		if (result.hasErrors()) 
		{				
			return mv;
		}
		
		roleService.save(role);
		logger.debug("Hello World, Spring 3.0!");
		status.setComplete();
		//return "redirect:ViewAllRoles.do";
		String defautUG=(String)role.getDefaultUsergroup();
		int dug=0;
		try{
			if(defautUG.length() > 0)
			{
				dug=Integer.parseInt(defautUG);
			}
		}catch(Exception e){
			
		}
		
		ModelAndView mav = new ModelAndView("success");
//		String success="";
//		String success2="";
//		Properties properties = new Properties();
//		try {
//		    properties.load(new FileInputStream("Messages.properties"));
//		    success=(String)properties.get("success.role");
//		    success2=(String)properties.getProperty("success.role");
//		} catch (IOException e) {
//			
//		}
//		System.out.print("SUCCESS FROM PROPERTIES====="+success);
//		System.out.print("SUCCESS FROM PROPERTIES====2="+success2);
		
		if(dug == 1){
			mav.addObject("AUTO_USER_GROUP_CREATED","New User group with name SYS"+ role.getRoleName()+ "was created");
		}
		
		//mav.addObject("ROLE_SUCCESSFULLY_CREATED","New Role was created successfully");
		mav.addObject("ROLE_SUCCESSFULLY_CREATED","success");
		
		return mav;
	}
	
	

	@RequestMapping(value="/updateRole", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam("roleId")long roleId)
	{
		ModelAndView mav = new ModelAndView("editRole");
		Roles roles = roleService.getById(roleId);
		mav.addObject("editRole", roles);
		return mav;
	}
	
	@RequestMapping(value="/updateRole", method=RequestMethod.POST)
	public String update(@ModelAttribute("editRole") Roles roles, BindingResult result, SessionStatus status)
	{
		validator.validate(roles, result);
		if (result.hasErrors())
		{
			return "editRole";
		}
		roleService.update(roles);
		status.setComplete();
		return "redirect:ViewAllRoles.do";
	}
	

	@RequestMapping("deleteRole")
	public ModelAndView delete(@RequestParam(value="id",required=false,defaultValue="")Long id,@RequestParam(value="actionFlag",required=false,defaultValue="")String actionFlag)
	{
		
		
	roleService.delete(id);
	ModelAndView mav = new ModelAndView("showRoles");
	RoleForm role = new RoleForm();

	
	List<Roles> roles = roleService.getAllRoles();
	mav.addObject("SEARCH_ROLES_RESULTS_KEY", roles);
	
	mav.addObject("action",actionFlag);
	mav.getModelMap().put("showRoles", role);
	return mav;
	}
	

	
	@RequestMapping("/searchRoles")
	public ModelAndView searchRoles(@ModelAttribute("showRoles") RoleForm roleform, BindingResult result, SessionStatus status)
	{
		ModelAndView mav = new ModelAndView("showRoles");
		List<Roles> role = roleService.searchRoles(roleform.getRoleName().trim());
		mav.addObject("SEARCH_ROLES_RESULTS_KEY", role);
		return mav;
	}
	
}

	

*/