package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.LocalGovtSetupService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class LocalGovtSetUpNew { // NO_UCD (unused code)
	
	@Autowired
	private LocalGovtSetupService localGovtSetupService;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/viewSetUpGovt", method=RequestMethod.GET)
	public ModelAndView contributeViewmethod2(@ModelAttribute("addVillageNew") VillageForm addVillageNew,HttpServletRequest request, Model model)  { 
		
		ModelAndView mav =null;
		try {
			List subTier = localGovtSetupService.getSubTier();
			List subType = localGovtSetupService.getSubType();
			List Designation = localGovtSetupService.getDesignation();
			
			/* System.out.println("Sub Tier---------Size-------/--------"+subTier.size());
			 System.out.println("Designation---------Size---45------------"+Designation.size());
			 System.out.println("Sub Type---------Size------75---------"+subType.size());*/
			 
			 mav=new ModelAndView("viewSetUpGovt");
			
			 model.addAttribute("size", subTier.size());
			 model.addAttribute("commonTier", subTier);
			 addVillageNew.setCommonTier(subTier);
			 
			 model.addAttribute("size", Designation.size());
			 model.addAttribute("designationTier", Designation);
			 addVillageNew.setDesignationTier(Designation);
			
			 model.addAttribute("size", subType.size());
			 model.addAttribute("subType", subType);
			 addVillageNew.setSubType(subType);
			
			 mav.addObject("addVillageNew",addVillageNew);
		}
		catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/viewTierSetup", method=RequestMethod.GET)
	public ModelAndView contributeViewmethod3(@ModelAttribute("addVillageNew") VillageForm addVillageNew, Model model, HttpServletRequest request)  { 
		
		ModelAndView mav =null;
		try {
		List subTier = localGovtSetupService.getSubTier();
		List subType = localGovtSetupService.getSubType();
		
		/* System.out.println("Sub Tier---------Size-------/--------"+subTier.size());
		 System.out.println("Sub Type---------Size------75---------"+subType.size());*/
		 
		 mav=new ModelAndView("viewTierSetup");
		
		 model.addAttribute("size", subTier.size());
		 model.addAttribute("commonTier", subTier);
		 addVillageNew.setCommonTier(subTier);
		
		 model.addAttribute("size", subType.size());
		 model.addAttribute("subType", subType);
		 addVillageNew.setSubType(subType);
		
		 mav.addObject("addVillageNew",addVillageNew);
	}
	catch (Exception e) {
		IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
		mav = new ModelAndView(redirectPath);
		return mav;
	}

	return mav;
}
	
	//, @RequestParam("") Integer id
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/viewDesignationElected", method=RequestMethod.GET)
	public ModelAndView contributeViewmethod4(@ModelAttribute("addVillageNew") VillageForm addVillageNew, Model model, HttpServletRequest request)  {
		
		ModelAndView mav =null;
		try {
		List DesignationElected = localGovtSetupService.getDesignationElected();
		
		 //System.out.println("Designation---------Size---45------------"+DesignationElected.size());
		 
	/*	 if(DesignationElected.size()==0){
//			 return "redirect:designation_hierarchy_elected.htm";
			 
		 }*/
		 
		 mav=new ModelAndView("viewDesignationElected");
		
		 model.addAttribute("size", DesignationElected.size());
		 
		 
		 model.addAttribute("designationElected", DesignationElected);
		 addVillageNew.setDesignationElected(DesignationElected);
		
		 mav.addObject("addVillageNew",addVillageNew);
		}
		catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/viewDesignationOfficial", method=RequestMethod.GET)
	public ModelAndView contributeViewmethod5(@ModelAttribute("addVillageNew") VillageForm addVillageNew, Model model, HttpServletRequest request)  {
		
		ModelAndView mav =null;
		try {
		List DesignationOfficail= localGovtSetupService.getDesignationOfficail();
		
		// System.out.println("Designation---------Size---45------------"+DesignationOfficail.size());
		 
		 mav=new ModelAndView("viewDesignationOfficial");
		
		 model.addAttribute("size", DesignationOfficail.size());
		 model.addAttribute("designationOfficial", DesignationOfficail);
		 addVillageNew.setDesignationOfficial(DesignationOfficail);
		
		 mav.addObject("addVillageNew",addVillageNew);
		}
		catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	
		return mav;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/viewDesignationReporting", method=RequestMethod.GET)
	public ModelAndView contributeViewmethod6(@ModelAttribute("addVillageNew") VillageForm addVillageNew, Model model, HttpServletRequest request)  { 
		ModelAndView mav =null;
		try {
		List Designation = localGovtSetupService.getDesignation();		
		 //System.out.println("Designation---------Size---45------------"+Designation.size());
		 mav=new ModelAndView("viewDesignationReporting");
		
		 model.addAttribute("size", Designation.size());
		 model.addAttribute("designationTier", Designation);
		 addVillageNew.setDesignationTier(Designation);
		
		 mav.addObject("addVillageNew",addVillageNew);
	}
	catch (Exception e) {
		IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
		mav = new ModelAndView(redirectPath);
		return mav;
	}

	return mav;
}
	
	
}
