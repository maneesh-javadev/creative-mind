package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.CitizenFeedback;
import in.nic.pes.lgd.common.CaptchaValidator;
import in.nic.pes.lgd.common.DownloadFiles;
import in.nic.pes.lgd.resourcebundles.LGDResourceBundle;
import in.nic.pes.lgd.service.CitizenSectionService;
import in.nic.pes.lgd.validator.FeedbackValidator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.rules.LocalBodyConstant;
import com.cmc.lgd.localbody.rules.LocalBodyUtil;
import com.cmc.lgd.localbody.services.LocalBodyService;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class CitizenSectionController { // NO_UCD (unused code)

	//private static final Logger log = Logger.getLogger(CitizenSectionController.class);
	
	@Autowired
	FeedbackValidator feedbackValidator;
	
	@Autowired
	CitizenSectionService citizenSectionService;
	
	@Autowired
	private LocalBodyUtil localBodyUtil;
	
	
	
	@RequestMapping(value="/citizenFeedback",method=RequestMethod.GET)
	public ModelAndView citizenFeedback(@ModelAttribute("citizenFeedback") CitizenFeedback citizenFeedback,
										 Model model,
										HttpServletRequest request){
		ModelAndView mav=new ModelAndView("citizenFeedback");
		try{
					mav.addObject("citizenFeedback",citizenFeedback);
					return mav;
		}catch(Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	@RequestMapping(value="/citizenFeedback",method=RequestMethod.POST)
	public ModelAndView citizenFeedbackPost(@ModelAttribute("citizenFeedback") CitizenFeedback citizenFeedback,
											BindingResult result,							 
											Model model,
											HttpServletRequest request,
											HttpSession session){
		ModelAndView mav=new ModelAndView("citizenFeedback");
		try{
			String captchaAnswer = citizenFeedback.getCaptchaAnswer();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session,captchaAnswer);
			if (messageFlag== true) {
				feedbackValidator.validate(citizenFeedback, result);
				if(result.hasErrors()){
					mav.addObject("citizenFeedback",citizenFeedback);
				}
				citizenFeedback.setCreatedon(new Date());
				citizenFeedback.setStatus("P");
				Boolean flag=citizenSectionService.saveFeedback(citizenFeedback);
				if(flag){
					citizenFeedback.setCitizenName(null);
					citizenFeedback.setCitizenEmail(null);
					citizenFeedback.setFeedback(null);
					mav.addObject("message","feedback save successfully!");
				}else{
					mav.addObject("message","Your Feedback is not saved, Try again later!");
				}
			}else{
				model.addAttribute("captchaSuccess2", messageFlag);
			}
			citizenFeedback.setCaptchaAnswer(null);
			return mav;
		}catch(Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	
	@RequestMapping(value="siteMap",method=RequestMethod.GET)
	public ModelAndView siteMap(Model model,HttpServletRequest request)
	{
		ModelAndView mav=null;
		try
		{
			mav=new ModelAndView("siteMap");
		}catch(Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
		
	}
	
	@RequestMapping(value="contactUs",method=RequestMethod.GET)
	public ModelAndView aboutUs(Model model,HttpServletRequest request)
	{
		ModelAndView mav=null;
		try
		{
			mav=new ModelAndView("contactus");
		}catch(Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
		
	}
	
	@RequestMapping(value="aboutPes",method=RequestMethod.GET)
	public ModelAndView aboutPes(Model model,HttpServletRequest request)
	{
		ModelAndView mav=null;
		try
		{
			mav=new ModelAndView("aboutpes");
		}catch(Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
		
	}
	
	@RequestMapping(value="aboutLGD",method=RequestMethod.GET)
	public ModelAndView aboutLGD(Model model,HttpServletRequest request)
	{
		ModelAndView mav=null;
		try
		{
			mav=new ModelAndView("aboutlgd");
		}catch(Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
		
	}
	
	@RequestMapping(value="nodalOfficerList",method=RequestMethod.GET)
	public ModelAndView nodalOfficerList(Model model,HttpServletRequest request)
	{
		ModelAndView mav=null;
		try
		{
			FileOutputStream outstream = null;
			String directoryLocation = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("userManual.directory.location").toString();
			String filePath = directoryLocation+File.separator+"ListofNodalOfficers.pdf";
			File pdfFile = new File(request.getServletContext().getRealPath("/") + File.separator +"ListofNodalOfficers.pdf");
			pdfFile.createNewFile();
			outstream=new FileOutputStream(pdfFile);
			DownloadFiles downloadFiles = new DownloadFiles();
			byte[] content = downloadFiles.getFileContent(filePath);
			if(content.length != 0) {
				FileCopyUtils.copy(content, outstream);
			}
			outstream.close();
			mav=new ModelAndView("nodalOfficersList");
		}catch(Exception e) {
			ModelAndView modelAndView = new ModelAndView("welcome");
			modelAndView.addObject("msg", "File Not found !!");
		}
		return mav;
		
	}
	
	@RequestMapping(value="userManualFiles",method=RequestMethod.GET)
	public ModelAndView userManualDistrict(Model model,HttpServletRequest request)
	{
		ModelAndView mav=null;
		try
		{
			String fileName=request.getParameter("fileName");
			FileOutputStream outstream = null;
			String directoryLocation = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("userManualDistrict.directory.location").toString();
			String filePath = directoryLocation+File.separator+fileName+".pdf";
			File pdfFile = new File(request.getServletContext().getRealPath("/") + File.separator +fileName+".pdf");
			pdfFile.createNewFile();
			outstream=new FileOutputStream(pdfFile);
			DownloadFiles downloadFiles = new DownloadFiles();
			byte[] content = downloadFiles.getFileContent(filePath);
			if(content.length != 0) {
				FileCopyUtils.copy(content, outstream);
			}
			outstream.close();
			mav=new ModelAndView("userManualFiles");
			mav.addObject("fileName", fileName);
		}catch(Exception e) {
			ModelAndView modelAndView = new ModelAndView("welcome");
			modelAndView.addObject("msg", "File Not found !!");
		}
		return mav;
		
	}


}
