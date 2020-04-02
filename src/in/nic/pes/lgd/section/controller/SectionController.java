package in.nic.pes.lgd.section.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
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

import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.section.form.SectionForm;
import in.nic.pes.lgd.section.rule.Section;
import in.nic.pes.lgd.section.rule.SectionConstant;
import in.nic.pes.lgd.section.rule.SectionValidator;
import in.nic.pes.lgd.section.service.SectionService;

/*
 * @author Maneesh Kumar @since 10Apr2016
 */
@Controller
public class SectionController {
	
	@Autowired
	private SectionService sectionService;
	
	@Autowired
	private SectionValidator sectionValidator;
	
	private Integer stateCode;
	
	private Integer userId;
	
	private Integer districtCode;
	
	private String isCenterorstate;
	
	/**
	 * initBinder set the some basic details in sectionForm
	 * @param binder
	 * @param session
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		setGlobalParams(session);
		SimpleDateFormat dateFormat = new SimpleDateFormat(SectionConstant.CURRENT_DATE_PATTERN.toString());
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.getBindingResult();
        binder.validate();
     	String obj = binder.getObjectName();
		if("sectionForm".equalsIgnoreCase(obj)) {
			SectionForm sectionForm = (SectionForm) binder.getTarget();
			sectionForm.setSlc(stateCode);
			sectionForm.setUserId(userId);
			sectionForm.setEffectiveDate(new Date());
			sectionForm.setIsCenterorstate(isCenterorstate);
			if((SectionConstant.IS_CENTER.toString().equals(isCenterorstate))){
				sectionForm.setParentType(SectionConstant.PARENT_TYPE_ORGANIZATION.toString());
			}
		}
	}
	
	
	/**
	 * 
	 * @param session
	 */
	private void setGlobalParams(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId().intValue();
		isCenterorstate=sessionObject.getIsCenterorstate();
	}
	
	/**
	 * The {@code errorHandler} returns error path and saved required stack trace. 
	 * @param request
	 * @param e
	 * @return
	 */
	private String errorHandler(HttpServletRequest request, Exception e){
		IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		return expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
	}
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @param sectionForm
	 * @return
	 */
	@RequestMapping(value="/createSection",method=RequestMethod.GET)
	public ModelAndView createSection(HttpServletRequest request,Model model,@ModelAttribute("sectionForm") SectionForm sectionForm){
		ModelAndView mav=new ModelAndView("create_section");
		try{
			
		}catch(Exception ex){
			ex.printStackTrace();
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	@RequestMapping(value="/buildSection",method=RequestMethod.POST)
	public ModelAndView buildSection(HttpServletRequest request,Model model,@ModelAttribute("sectionForm") SectionForm sectionForm, BindingResult binding){
		ModelAndView mav=null;
		try{
			sectionValidator.validate(sectionForm, binding);
			if(binding.hasErrors()){
				mav=new ModelAndView("create_section");
				model.addAttribute("serverError",true);
				return mav;
			}
			mav=new ModelAndView("result_section");
			String returnValue=sectionService.saveSectionDetails(sectionForm);
			StringBuilder sb=new StringBuilder("Section "+sectionForm.getSectionNameEnglish());
			String typeName=SectionConstant.PARENT_TYPE_LOCALBODY.toString().equals(sectionForm.getParentType())?"localbodies":"organiation units";
			boolean listShow=false;
			if(returnValue==null || (returnValue!=null && returnValue.isEmpty())){
				sb.append(" creation failed.");
			}else if(("SUCCESS").equals(returnValue)){
				sb.append(" is created successfully for Selected  "+typeName+".");
			}else{
				sb.append(" is created successfully for Selected "+typeName+" except below "+typeName+" as It is already exists for these "+typeName+".");
				listShow=true;
				setFailedEntityList(returnValue,model);
				
			}
			mav.addObject("listShow",listShow);
			mav.addObject("msgid", sb.toString());
			mav.addObject("opeartion",SectionConstant.SECTION_OPERATION_CREATE.toString());
			
		}catch(Exception ex){
			ex.printStackTrace();
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	public void setFailedEntityList(String returnValue,Model model)throws Exception{
		Map<String, String> list = new HashMap<String, String>();
		String dataArray[]=returnValue.split(",");
		for(int i=0;i<dataArray.length;i++){
			String rowArray[]=dataArray[i].split(":");
			list.put(rowArray[0],rowArray[1]);
		}
		
		model.addAttribute("failureResult", list);
	}
	
	
	
	@RequestMapping(value="/manageSection",method=RequestMethod.GET)
	public ModelAndView viewSection(HttpServletRequest request,Model model,@ModelAttribute("sectionForm") SectionForm sectionForm){
		ModelAndView mav=new ModelAndView("manage_section");
		try{
			String requestURL = request.getRequestURL().toString();
			String contextPath = request.getContextPath();
			System.out.println("requestURL:"+requestURL);
			System.out.println("contextPath:"+contextPath);
		}catch(Exception ex){
			ex.printStackTrace();
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/manageSection",method=RequestMethod.POST)
	public ModelAndView viewSectionPost(HttpServletRequest request,Model model,@ModelAttribute("sectionForm") SectionForm sectionForm,BindingResult binding){
		ModelAndView mav=new ModelAndView("manage_section");
		try{
			sectionValidator.validateView(sectionForm, binding);
			if(binding.hasErrors()){
				mav=new ModelAndView("manage_section");
				model.addAttribute("serverError",true);
				return mav;
			}
			Integer parentCode=null;
			if((SectionConstant.PARENT_TYPE_LOCALBODY.toString()).equals(sectionForm.getParentType())){
			String[] lbCodeArray = sectionForm.getLocalBodyLevelCodes().split(",");
			parentCode = Integer.parseInt(lbCodeArray[lbCodeArray.length - 1]);
			}else{
				String[] orgCodeArray = sectionForm.getOrgLevelCodes().split(",");
				parentCode = Integer.parseInt(orgCodeArray[orgCodeArray.length - 1]);
			}
			Object[] manageSectionObject=sectionService.getManageSectionObject(parentCode, sectionForm.getParentType());
			
			if(manageSectionObject!=null){
				sectionForm.setSectionList((List<Section>) manageSectionObject[0]);
				sectionForm.setSectionDeleteList((List<Section>) manageSectionObject[1]);
				model.addAttribute("searchFlag",true);
			}else{
			model.addAttribute("searchFlag",false);
			}
			model.addAttribute("serverLoad",true);
		}catch(Exception ex){
			ex.printStackTrace();
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	@RequestMapping(value="/updateSection",method=RequestMethod.POST)
	public ModelAndView manageSection(HttpServletRequest request,Model model,@ModelAttribute("sectionForm") SectionForm sectionForm,BindingResult binding){
		ModelAndView mav=new ModelAndView("update_section");
		try{
			/*sectionValidator.validateView(sectionForm, binding);
			if(binding.hasErrors()){
				mav=new ModelAndView("view_section");
				model.addAttribute("serverError",true);
				return mav;
			}*/
			List<Section> sectionList=sectionService.getSectionListbyId(sectionForm.getSectionCode());
			Section section=sectionList.get(0);
			BeanUtils.copyProperties(sectionForm, section);
			sectionForm.setSectionCode(section.getSectionPK().getSectionCode());
			sectionForm.setSectionVersion(section.getSectionPK().getSectionVersion());
			
		}catch(Exception ex){
			ex.printStackTrace();
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	@RequestMapping(value="/buildUpdateSection",method=RequestMethod.POST)
	public ModelAndView v(HttpServletRequest request,Model model,@ModelAttribute("sectionForm") SectionForm sectionForm,BindingResult binding){
		ModelAndView mav=new ModelAndView("update_section");
		try{
			sectionValidator.validateUpdate(sectionForm, binding);
			if(binding.hasErrors()){
				mav=new ModelAndView("update_section");
				model.addAttribute("serverError",true);
				return mav;
			}
			mav=new ModelAndView("result_section");
			if(sectionService.updateSectionNameEnglish(sectionForm)){
				StringBuilder sb=new StringBuilder("'"+sectionForm.getSectionNameEnglish()+"'");
				sb.append(" Section is renamed to '"+sectionForm.getSectionNameEnglishChange()+"' Section successfully.");
				mav.addObject("msgid",sb.toString() );
			}else{
				mav.addObject("msgid","Section rename failure.");
				
			}
			mav.addObject("opeartion",SectionConstant.SECTION_OPERATION_UPDATE.toString());
			
		}catch(Exception ex){
			ex.printStackTrace();
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	@RequestMapping(value="/deleteSection",method=RequestMethod.POST)
	public ModelAndView deleteSection(HttpServletRequest request,Model model,@ModelAttribute("sectionForm") SectionForm sectionForm,BindingResult binding){
		ModelAndView mav=new ModelAndView("result_section");
		try{
			
			if(sectionService.deleteSection(sectionForm.getSectionCode())){
				mav.addObject("msgid","Section "+sectionForm.getSectionNameEnglish()+" delete successfully.");
			}else{
				mav.addObject("msgid","Section delete failure.");
			}
			mav.addObject("opeartion",SectionConstant.SECTION_OPERATION_DELETE.toString());
			
		}catch(Exception ex){
			ex.printStackTrace();
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	
	@RequestMapping(value="/reActiveSection",method=RequestMethod.POST)
	public ModelAndView reActiveSection(HttpServletRequest request,Model model,@ModelAttribute("sectionForm") SectionForm sectionForm){
		ModelAndView mav=new ModelAndView("result_section");
		try{
			
			if(sectionService.reActiveSection(sectionForm)){
				mav.addObject("msgid","Section "+sectionForm.getSectionNameEnglish()+" restore successfully.");
			}else{
				mav.addObject("msgid","Section restore failure.");
			}
			mav.addObject("opeartion",SectionConstant.SECTION_OPERATION_RESTORE.toString());
		}catch(Exception ex){
			ex.printStackTrace();
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	
}
