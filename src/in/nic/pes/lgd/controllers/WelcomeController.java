package in.nic.pes.lgd.controllers;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.StatewiseEntitiesCount;
import in.nic.pes.lgd.common.CompareStatewiseEntitiesCount;
import in.nic.pes.lgd.service.InitialService;
import in.nic.pes.lgd.service.ReportService;


@Controller
public class WelcomeController {

	@Autowired
	InitialService initialService;
	
	@Autowired
	ReportService reportService;

	
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(HttpServletRequest request,Model model) {
		try {
			if (request.getRemoteUser() != null) {
			return "redirect:switchunit.htm";
		    }
			
			StatewiseEntitiesCount statewiseEntitiesCount=null;
			
			InputStream inputStreamPro = getClass().getClassLoader().getResourceAsStream("/upload_info.properties");
			Properties properties = new Properties();
			properties.load(inputStreamPro);
			String masterPtah = properties.getProperty("homepage.json.data.file");
			
			 File f = new File(masterPtah+File.separator+"lgd_entity_count.json");
			 if(f.exists()){
			 		Gson gson = new Gson();
			 		statewiseEntitiesCount= gson.fromJson(new FileReader(f.getAbsolutePath()), StatewiseEntitiesCount.class);
			 		List<StatewiseEntitiesCount> statewiseEntitiesCountList =initialService.getDataFromJsonFile();
					Comparator<StatewiseEntitiesCount> comp = new CompareStatewiseEntitiesCount() ;
			        Collections.sort(statewiseEntitiesCountList, comp);
			        model.addAttribute("statewiseEntitiesCountList",statewiseEntitiesCountList);
			 } 
			 else{
				 List<StatewiseEntitiesCount> statewiseEntitiesCountList= initialService.lgdEntitiesCountFn(Boolean.TRUE);
				 if(statewiseEntitiesCountList!=null && !statewiseEntitiesCountList.isEmpty()){
					 statewiseEntitiesCount=statewiseEntitiesCountList.get(0);
				 }
			 }
			
			model.addAttribute("statewiseEntitiesCount",statewiseEntitiesCount);
			model.addAttribute("isHomepage",true);
			
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			return expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
		}
		return "baseHome";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login( HttpSession session,HttpServletRequest request)
	{
		try
		{
			InputStream inputStream = this.getClass().getResourceAsStream("/switchunit.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
	        String loginkey=properties.getProperty("switchunit.loginkey");
	        String remoteUser = request.getParameter("username") ; //DISTRICTUSERAMB//SUPERADMINSKIM centerlgd
	        //String remoteUser=request.getRemoteUser();
		    request.getSession().invalidate();
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
	
	
	
	@RequestMapping(value = "/loginIssue", method = RequestMethod.GET)
	public ModelAndView loginIssue( HttpSession session,HttpServletRequest request)
	{
		ModelAndView mav=null;
		try
		{
			mav=new ModelAndView("LoginIssue");
			StringBuilder sb=new StringBuilder("Kindly follow below steps for resolving login related issues:<br>");
			
			sb.append("1. Kindly clear your web browser’s history and cache.<br>");
			sb.append("2. Close your web browser.<br>");
			sb.append("3. Open your web browser and Enter URL of LGD website in the address bar and press enter.<br>");
			sb.append("4. Click on Login button available on the Home of LGD website.<br>");
			sb.append("5. A login popup window will appear.<br>");
			sb.append("6. Enter Valid User Credentials.<br>");
			sb.append("7. In case in the Login Popup window exception is coming or nothing is visible in it then follow below steps :<br>");
			sb.append("•  Enter Below URL in the address bar of your web browser and press enter :http://panchayatonline.gov.in<br>");
			sb.append("•  Click on the Login button available on the home page.<br>");
			sb.append("•  A login window will appear.<br>");
			sb.append("•  Add security exception from there using add exception button.<br>");
			sb.append("•  Now Close your web browser window.<br>");
			sb.append("•  Open your web browser window and now access LGD website.<br>");
			//sb.append("In case your issues remains still unresolved after following above steps, kindly provide your team<br> viewer details for immediate support.");
			mav.addObject("filedescription", sb.toString());
		}
		catch(Exception ex)
		{
			mav=new ModelAndView("error");
		}
		
		return mav;
	}
	
	
	private String getCommonJsonFilePath()throws Exception{
		AttachmentMaster attachmentMaster=reportService.getUploadFileConfigurationDetails(6L);
		 File dir = new File(attachmentMaster.getFileLocation());
		 if(!dir.exists()){
		    	dir.mkdirs();
		   }
		return dir.getAbsolutePath();
	}
	
	
	@RequestMapping(value = "/showDishaHelp")
	public ModelAndView showDishaHelp( HttpSession session,HttpServletRequest request)
	{
		return new ModelAndView("SHOW_DIHSA_OCVERAGE");
	}
}




