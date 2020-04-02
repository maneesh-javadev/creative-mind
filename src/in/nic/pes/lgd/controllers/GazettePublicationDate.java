package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.GazettePublication;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.validator.GazettePublicationValidator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;
@Controller
public class GazettePublicationDate { // NO_UCD (unused code)
		
	
		@Autowired
		DistrictService districtService;
		int stateCode=0;
		
		@InitBinder
		public void initBinder(WebDataBinder binder, HttpServletRequest request) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				dateFormat.setLenient(false);
				binder.registerCustomEditor(Date.class, new CustomDateEditor(
						dateFormat, true));
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				expHandler.handleSaveRedirectException(request,"label.lgd", "userId",1, e);
			}
		}
		
		// View District History
		@RequestMapping(value = "/AddGazettePublicationDate", method = RequestMethod.GET)
		public ModelAndView AddGazetteDetail(
				@ModelAttribute("governmentOrder") GovernmentOrderForm governmentOrder,BindingResult bindingResult,Model model,HttpServletRequest request) {
			ModelAndView mv = null;
			try
			{
			 mv = new ModelAndView("Add_GazettePublicationDate");
			List<GazettePublication> gazetteDateDetail = districtService.getGazettePublicationDate();
			model.addAttribute("gazettePublication", gazetteDateDetail);
			governmentOrder.setGazetteDateDetail(gazetteDateDetail);
			mv.addObject("governmentOrder", governmentOrder);
		}
			catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
				mv = new ModelAndView(redirectPath);
				return mv;
			}

			return mv;
		}
		
		@RequestMapping(value="/AddGazettePublicationDateshow", method=RequestMethod.POST)	
		public ModelAndView showGazetteDetail(@ModelAttribute("governmentOrder") GovernmentOrderForm governmentOrder,BindingResult bindingResult,Model model,HttpServletRequest request,HttpSession httpSession)throws Exception {
			
			ModelAndView mv = null;
			//String govtOrderConfig = null;
			try{
			GazettePublicationValidator cValidator = new GazettePublicationValidator();
			cValidator.validate(governmentOrder, bindingResult);
			if (bindingResult.hasErrors()) {
				 mv = new ModelAndView("Add_GazettePublicationDate");
				List<GazettePublication> gazetteDateDetail = districtService.getGazettePublicationDate();
				model.addAttribute("gazettePublication", gazetteDateDetail);
				governmentOrder.setGazetteDateDetail(gazetteDateDetail);
				mv.addObject("governmentOrder", governmentOrder);
			} else {
				
			                
				String orderCode=governmentOrder.getGazettePublicationCheckBox();
				Date Gazettedate = governmentOrder.getGazPubDate();// error shown so Caps p change to small p by kamlesh
				//List<GazettePublicationDateSave> gazetteDateDetail = 
				districtService.getGazettePublicationDateSave(orderCode,Gazettedate);
			    model.addAttribute("msgid", "New Gazette Publication Date Have Been Saved. !");
			    mv=new ModelAndView("success");
			}
			}
			    catch (Exception e) {
					IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
					String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
					mv = new ModelAndView(redirectPath);
					return mv;
				}

				return mv;
		}
}	
			
			
			
			 


