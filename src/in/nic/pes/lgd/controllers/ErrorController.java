package in.nic.pes.lgd.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class ErrorController { // NO_UCD (unused code)

	
	@RequestMapping(value="/errorRedirect",method=RequestMethod.GET)
	public ModelAndView showErrorPage(HttpServletRequest request)
	{
		try {
			System.out.println("error form acrossapplication mk");
			HttpSession oldSession = request.getSession();
			 oldSession.invalidate(); 
			return new ModelAndView("errorRedirect");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	@RequestMapping(value="/error",method=RequestMethod.GET)
	public ModelAndView showError(HttpServletRequest request )
	{
		try {
			System.out.println("error form acrossapplication mk");
			 HttpSession oldSession = request.getSession();
			 oldSession.invalidate(); 
			return new ModelAndView("errorRedirect");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	@RequestMapping(value="/lgdError",method=RequestMethod.GET)
	public ModelAndView lgdError(HttpServletRequest request )
	{
		try {
			System.out.println("error form acrossapplication mk");
			 HttpSession oldSession = request.getSession();
			 oldSession.invalidate(); 
			return new ModelAndView("lgdError");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	
	@RequestMapping(value="/permissionRedirect",method=RequestMethod.GET)
	@Produces({ MediaType.APPLICATION_XML })
	public String permissionDeniedMessage(HttpServletRequest request)
	{
		return "Permission Denied";
	}
		
}
