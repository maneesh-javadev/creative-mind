package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.NomenclatureSubdistrict;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.common.EsapiEncoder;
import in.nic.pes.lgd.forms.NomenclatureSubDistForm;
import in.nic.pes.lgd.service.NomenclatureSubDistService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;
@Controller
public class NomenclatureSubDistController { // NO_UCD (unused code)
	
	@Autowired
	private NomenclatureSubDistService nomenclatureSubDistService;
	
	private Integer stateCode;

	private Integer districtCode;

	private Long userId;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId();
	}
	
	@RequestMapping(value ="/nomenclature_sub_district",  method = RequestMethod.GET)
	public ModelAndView nomenclatureSubDistrict(@ModelAttribute("nomenclatureSubDistForm") NomenclatureSubDistForm  nomenclatureSubDistForm, Model model,HttpSession httpSession,HttpServletRequest request)
	{
		ModelAndView mv=null;
		try {
			if (stateCode==null) 
			{
					return mv = new ModelAndView("redirect:login.htm");
			}
			
			List<NomenclatureSubdistrict> nomenclatureSubdistrictList=null;
			nomenclatureSubdistrictList= new ArrayList<NomenclatureSubdistrict>();
			
			nomenclatureSubdistrictList=nomenclatureSubDistService.checkNomenclature(stateCode, userId);
			
			if (nomenclatureSubdistrictList.size()>0) {
				mv=new ModelAndView("nomenclatureSubDistrictView");
				mv.addObject("nomenclatureSubdistrictList", nomenclatureSubdistrictList);
			}
			else if(nomenclatureSubdistrictList.size()==0)
			{
				mv=new ModelAndView("nomenclatureSubDistrict");
			}
			else
			{
				mv=new ModelAndView("error");
				mv.addObject("message", "Error occured while fetching details");
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
		
	}
	
	@RequestMapping(value ="/modifyNomenclatureSd",  method = RequestMethod.POST)
	public ModelAndView modifyNomenclatureSub(@ModelAttribute("nomenclatureSubDistForm")@Valid NomenclatureSubDistForm  nomenclatureSubDistForm,BindingResult bindingResult, Model model,HttpSession httpSession,HttpServletRequest request)
	{
		ModelAndView mv= null;
		try {
			
			List<NomenclatureSubdistrict> nomenclatureSubdistrictList=null;
			nomenclatureSubdistrictList= new ArrayList<NomenclatureSubdistrict>();
			 
			nomenclatureSubdistrictList=nomenclatureSubDistService.checkNomenclature(stateCode, userId);
			if(bindingResult.hasErrors())
			{
				mv=new ModelAndView("modifyNomenclatureSubDistrictPage");
				mv.addObject("nomenclatureSubdistrictList", nomenclatureSubdistrictList);
				mv.addObject("size", nomenclatureSubdistrictList.size());
//			mv=new ModelAndView("nomenclatureSubDistrict");
			}
			else
			{
				if (nomenclatureSubDistService.modifyNomenclatureSudDist(nomenclatureSubDistForm, stateCode, httpSession)) {
					mv= new ModelAndView("redirect:nomenclature_sub_district.htm");
				} else {
					mv= new ModelAndView();
					mv.setViewName("error");
					mv.addObject("message", "Internal Server Error.");
				}
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}
	@RequestMapping(value ="/saveNomenclature_sub_district",  method = RequestMethod.POST)
	public ModelAndView saveNomenclatureSubDistrict(@ModelAttribute("nomenclatureSubDistForm")@Valid NomenclatureSubDistForm  nomenclatureSubDistForm,BindingResult bindingResult, Model model,HttpSession httpSession,HttpServletRequest request)
	{
		ModelAndView mv= null;
		try {
			if(bindingResult.hasErrors())
			{
				mv=new ModelAndView("nomenclatureSubDistrict");
			}
			else
			{
				
				if (nomenclatureSubDistService.addNomenclatureSudDist(nomenclatureSubDistForm, stateCode, httpSession)) {
					mv= new ModelAndView("redirect:nomenclature_sub_district.htm");
				} else {
					mv= new ModelAndView();
					mv.setViewName("error");
					mv.addObject("message", "Internal Server Error.");
				}
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}
	@RequestMapping(value ="/modifyNomenclatureSubDistrict",  method = RequestMethod.GET)
	public ModelAndView modifyNomenclatureSubDistrict(@ModelAttribute("nomenclatureSubDistForm")NomenclatureSubDistForm  nomenclatureSubDistForm,BindingResult bindingResult, Model model,HttpSession httpSession,HttpServletRequest request)
	{
		ModelAndView mv=null;
		
		try {
			
			List<NomenclatureSubdistrict> nomenclatureSubdistrictList=null;
			nomenclatureSubdistrictList= new ArrayList<NomenclatureSubdistrict>();
			EsapiEncoder.encode(nomenclatureSubDistForm);
			nomenclatureSubdistrictList=nomenclatureSubDistService.checkNomenclature(stateCode, userId);
			EsapiEncoder.encode(nomenclatureSubdistrictList);
			for (int i = 0; i < nomenclatureSubdistrictList.size(); i++) {
//			if (nomenclatureSubdistrictList.get(i).isIssubdistrictblocksame()) {
					nomenclatureSubDistForm.setBlockExist(nomenclatureSubdistrictList.get(i).isBlockExists());
					nomenclatureSubDistForm.setNameofTheBlockisSameforTheState(nomenclatureSubdistrictList.get(i).isIssubdistrictblocksame());
//			}
			}
			if (nomenclatureSubdistrictList.size()>0) {
				mv=new ModelAndView("modifyNomenclatureSubDistrictPage");
				mv.addObject("nomenclatureSubdistrictList", nomenclatureSubdistrictList);
				mv.addObject("size", nomenclatureSubdistrictList.size());
			}
			else if(nomenclatureSubdistrictList.size()==0)
			{
				mv=new ModelAndView("nomenclatureSubDistrict");
			}
			else
			{
				mv=new ModelAndView("error");
				mv.addObject("message", "Error occured while fetching details");
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
		
	}
/*	public NomenclatureSubDistForm NomenclatureSubDistForm()
	{
		return new NomenclatureSubDistForm();
	}*/
	@ModelAttribute
	public NomenclatureSubDistForm get()
	{
		return new NomenclatureSubDistForm();
	}
}
