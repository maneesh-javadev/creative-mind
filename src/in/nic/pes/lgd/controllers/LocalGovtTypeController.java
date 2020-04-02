package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.CheckLocalBodyType;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LocalGovtTypeDataForm;
import in.nic.pes.lgd.forms.LocalGovtTypeForm;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.LocalGovtTypeService;
import in.nic.pes.lgd.validator.CommonValidatorImpl;
import in.nic.pes.lgd.validator.LocalGovtTypeValidator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class LocalGovtTypeController { // NO_UCD (unused code)
	
	@Autowired
	CommonValidatorImpl commonValidator;
		
	@Autowired
	LocalGovtTypeService localGovtTypeService;
	
	@Autowired
	LocalGovtTypeValidator localgovtValidator;
	
	@Autowired
	private GovernmentOrderService govtOrderService;
	
	@Autowired
	private LocalGovtBodyService  localGovtBodyService ;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			dateFormat.setLenient(false);
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
			binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			
		}
	}	

	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/localgovtType",method=RequestMethod.GET)
	public ModelAndView showLocalGovtTypeForm(HttpSession session, HttpServletRequest request)
	{		
		ModelAndView mav =null;
		try {
		if(session.getAttribute("formbean") !=null)
		{
			session.removeAttribute("formbean");
			session.removeValue("formbean");
		}
		
		mav=new ModelAndView("localgovtType");		
		mav.addObject("govtOrderConfig", "govtOrderUpload");
		mav.addObject("createlocalgovtType",new LocalGovtTypeForm ());
		}
		catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	@RequestMapping(value = "/draftLocalgovtType", method = RequestMethod.POST)
	public ModelAndView draftLocalgovtType(@ModelAttribute("createlocalgovtType") LocalGovtTypeForm localgovtType,
			BindingResult result, SessionStatus status,
			HttpServletRequest request, HttpSession session) {
		
		ModelAndView mav =null;
		try {
		localgovtValidator.validate(localgovtType, result);
		boolean checkConfig=false;
		checkConfig=localGovtTypeService.checkgovtTypeDependency(localgovtType.getRuralCategory().charAt(0),localgovtType.getLevel().charAt(0));
		localgovtType.setOperation('C');
		session.setAttribute("formbean", localgovtType);
		
		if(result.hasErrors()) {			
				 mav = new ModelAndView("localgovtType");
				 mav.addObject("createlocalgovtType",localgovtType);
				 
		  }
		  else
		  {		
			   if (!checkConfig && localgovtType.getRuralCategory().equalsIgnoreCase("P")) {
				   
				   List<LocalBodyType> listGovtTypeDetails = null;
				   listGovtTypeDetails = new ArrayList<LocalBodyType>();
				listGovtTypeDetails = localGovtBodyService.getGovtTypeViewList("from LocalBodyType d where d.category ='R' and  ispartixgovt=true and d.isactive=true order by localBodyTypeName");
				
				if(listGovtTypeDetails.size()<=3)
				{
					mav = new ModelAndView("redirect:govtOrderCommon.htm");
					mav.addObject("govtOrderConfig", localgovtType.getGovtOrderConfig());
				}
			   }
			   else if(localgovtType.getCategoryRadio().equalsIgnoreCase("U") || localgovtType.getRuralCategory().equalsIgnoreCase("T"))
				{
				   mav = new ModelAndView("redirect:govtOrderCommon.htm");
				   mav.addObject("govtOrderConfig", localgovtType.getGovtOrderConfig());
				}
			   else
			   {
				  
				   String message="*In Panchayat Category,all local body types are already Defined";				 
				   mav = new ModelAndView("localgovtType");
				   mav.addObject("createlocalgovtType",localgovtType);
				   mav.addObject("msgid", message);
			   }
				
		  }
		}
		catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	

	@SuppressWarnings("deprecation")
	@RequestMapping(value="/savelocalgovtType", method=RequestMethod.POST)
	public ModelAndView createlocalgovtType(HttpServletRequest request,HttpSession session,Model model) throws Exception
	{	
		ModelAndView mav =null;
		try {
		int code=0;
		
		LocalGovtTypeForm localgovtType = (LocalGovtTypeForm) session.getAttribute("formbean");
		GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");
		
		AddAttachmentBean addAttachmentBean=(AddAttachmentBean)session.getAttribute("addAttachmentBean");
		
		
		AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
		attachmentHandler.validateAttachment(addAttachmentBean);
		
		
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
		if(metaInfoOfToBeAttachedFileList != null && metaInfoOfToBeAttachedFileList.size() != 0) {			
			
			code = localGovtTypeService.saveLocalGovtType(localgovtType,
					govtOrderForm, metaInfoOfToBeAttachedFileList, request);
			attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
		}
		
		LocalGovtTypeDataForm viewBodyType=new LocalGovtTypeDataForm();
		viewBodyType.setLocalgovtId(code);
		
		if(code > 0)
		{				
			mav=new ModelAndView("redirect:viewLocalBodyTypeDetail.htm?id="+code +"&type=S");
		}
		session.removeAttribute("formbean");
		session.removeValue("formbean");
		session.removeAttribute("govtOrderForm");
		session.removeValue("govtOrderForm");
		session.removeAttribute("addAttachmentBean");
		session.removeValue("addAttachmentBean");
		
	}
	catch (Exception e) {
		IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
		mav = new ModelAndView(redirectPath);
		return mav;
	}

	return mav;
}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/modifyLoacalGovTypebyId")
	public ModelAndView modifyLocalGovtType(@ModelAttribute("modifyLocalGovtTypeCmd")LocalGovtTypeForm modifyLocalGovtTypeCmd, Model model,
			@RequestParam(value="disturb",required=false) String disturb, HttpServletRequest request,HttpSession session)
	{	
		ModelAndView mav =null;
		String stateList="";
		int localBodyTypeCode= modifyLocalGovtTypeCmd.getLocalgovtId();
		try {
			
			
		
			
			
			List<CheckLocalBodyType> checkLocalBodyType=null;
			checkLocalBodyType=new ArrayList<CheckLocalBodyType>();
			
			checkLocalBodyType=localGovtTypeService.checkLocalBodyType(localBodyTypeCode);
			
			if(checkLocalBodyType.size()==0)
			{
				if(session.getAttribute("formbean") !=null)
				{
					session.removeAttribute("formbean");
					session.removeValue("formbean");
				}
			 
			 //List<LocalBodyType> listGovtTypeDetails = localGovtTypeService.getLocalBodyTypeDetails(localBodyTypeCode);
		 
			 List<LocalGovtTypeDataForm> listdetail = localGovtTypeService.getLocalBodyTypeDetailsall(localBodyTypeCode);
			/* if(listGovtTypeDetails.size()>0){
				 modifyLocalGovtTypeCmd.setCorrection(true);
				 modifyLocalGovtTypeCmd.setListdetail(listdetail);
				 //modifyLocalGovtTypeCmd.setListGovtTypeDetail(listGovtTypeDetails);
				 }
			 else{*/
				 //modifyLocalGovtTypeCmd.setCorrection(false);		
				 modifyLocalGovtTypeCmd.setCorrection(true);
				 modifyLocalGovtTypeCmd.setListdetail(listdetail);
				
			/* }*/
			 mav=new ModelAndView("modify_localGovtType");
			 mav.addObject("govtOrderConfig", "govtOrderUpload");
			 mav.addObject("modifyLocalGovtTypeCmd", modifyLocalGovtTypeCmd);
			}
			else
			{
				Iterator<CheckLocalBodyType> itr = checkLocalBodyType.iterator();
				while (itr.hasNext()) {
					CheckLocalBodyType element = (CheckLocalBodyType) itr.next();
					stateList+=element.getStateNameEnglish().trim()+",";
				}
				mav=new ModelAndView("modify_localGovtType");
				stateList=stateList.substring(0,stateList.length()-1);
				modifyLocalGovtTypeCmd.setCorrection(false);		
				mav.addObject("stateList", stateList);
				
			
			}
		}
		catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/draftModifyLocalgovtType", method = RequestMethod.POST)
	public ModelAndView draftModifyLocalgovtType(
			@ModelAttribute("modifyLocalGovtTypeCmd") LocalGovtTypeForm modifyLocalGovtTypeCmd,
			BindingResult result, SessionStatus status, Model model,
			HttpServletRequest request,HttpSession session) {
		
		ModelAndView mav =null;
		//int localBodyTypeCode = 0;
		List<LocalGovtTypeDataForm> listdetail = new ArrayList<LocalGovtTypeDataForm>();	 
		try {
			mav = new ModelAndView("modify_localGovtType");
			listdetail = modifyLocalGovtTypeCmd.getListGovtTypeDetails();
			
			
				modifyLocalGovtTypeCmd.setOperation('M');
				session.setAttribute("formbean", modifyLocalGovtTypeCmd);
	           
			
				 localgovtValidator.validateChange(modifyLocalGovtTypeCmd,
							result);
					if (result.hasErrors()) {
						 modifyLocalGovtTypeCmd.setCorrection(false);					 
						 modifyLocalGovtTypeCmd.setListdetail(listdetail);
					    	mav.addObject("modifyLocalGovtTypeCmd",
								modifyLocalGovtTypeCmd);
						
					
						return mav;
		    		}
					else
					{
					
						   mav = new ModelAndView("redirect:govtOrderCommon.htm");
						   mav.addObject("govtOrderConfig", modifyLocalGovtTypeCmd.getGovtOrderConfig());
					
			 
					}
		}
		catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/modifyLogalGovtTypeAction", method = RequestMethod.POST)
	public ModelAndView modifyConfigMap(HttpServletRequest request,HttpSession session) {
		ModelAndView mav = null;
		String viewStatedetail = null;
		int localBodyTypeCode = 0;
		List<LocalGovtTypeDataForm> listdetail = new ArrayList<LocalGovtTypeDataForm>();
		 
		try {

			mav = new ModelAndView("modify_localGovtType");
			LocalGovtTypeForm modifyLocalGovtTypeCmd = (LocalGovtTypeForm) session.getAttribute("formbean");
			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");
			
			listdetail = modifyLocalGovtTypeCmd.getListdetail();
			AddAttachmentBean addAttachmentBean=(AddAttachmentBean)session.getAttribute("addAttachmentBean");
			
			
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			attachmentHandler.validateAttachment(addAttachmentBean);
			
			
			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
			if(metaInfoOfToBeAttachedFileList != null && metaInfoOfToBeAttachedFileList.size() != 0) {		
				localGovtTypeService.modifyLocalGovtTypeInfo(
						modifyLocalGovtTypeCmd,govtOrderForm,metaInfoOfToBeAttachedFileList,request);
					attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
					
			}
					Iterator<LocalGovtTypeDataForm> itr1 = listdetail
							.iterator();
					while (itr1.hasNext()) {
						LocalGovtTypeDataForm element = (LocalGovtTypeDataForm) itr1
								.next();
						localBodyTypeCode = element.getLocalBodyTypeCode();
					}
					viewStatedetail = "redirect:viewLocalBodyTypeDetail.htm?id="
							+ localBodyTypeCode + "&type=S";
					
					session.removeAttribute("formbean");
					session.removeValue("formbean");
					session.removeAttribute("govtOrderForm");
					session.removeValue("govtOrderForm");
					session.removeAttribute("addAttachmentBean");
					session.removeValue("addAttachmentBean");
					mav= new ModelAndView(viewStatedetail);
					
					
					 return mav;
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		 
	}

	@RequestMapping(value="/viewLocalBodyTypeDetails", method=RequestMethod.GET)
	public ModelAndView viewLocalBodyTypeDetail(@ModelAttribute("viewBodyType")LocalGovtTypeDataForm viewBodyType , Model model,@RequestParam("id")Integer localBodyTypeCode,@RequestParam("type")String type, HttpServletRequest request)
	{
		 ModelAndView mav =null;
    	 try {
			List<LocalBodyType> listGovtTypeDetails = localGovtTypeService.getLocalBodyTypeDetails(localBodyTypeCode);
			 mav=new ModelAndView("view_bodytypedetail");
			 viewBodyType.setListGovtTypeDetail(listGovtTypeDetails);
			 return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} 
	}	
	
	@RequestMapping(value="/viewLocalBodyTypeDetailschange", method=RequestMethod.GET)
	public ModelAndView viewLocalBodyTypeDetailChange(@ModelAttribute("viewBodyType")LocalGovtTypeDataForm viewBodyType , Model model,@RequestParam("id")Integer localBodyTypeCode,@RequestParam("type")String type, HttpServletRequest request)
	{
		ModelAndView mav =null;
   	 try {
    	 List<LocalGovtTypeDataForm> listGovtTypeDetails = localGovtTypeService.getLocalBodyTypeDetailsall(localBodyTypeCode);
		 mav=new ModelAndView("view_bodytypedetailchange");
		 viewBodyType.setListGovtTypeDetails(listGovtTypeDetails);
	} catch (Exception e) {
		IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
		mav = new ModelAndView(redirectPath);
		return mav;
	} 
   	return mav;
}		
	
	
	
}
