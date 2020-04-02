package in.nic.pes.lgd.draft.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

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

import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.draft.constant.DraftConstant;
import in.nic.pes.lgd.draft.entities.LandRegionAttribute;
import in.nic.pes.lgd.draft.form.DraftGovermentOrderForm;
import in.nic.pes.lgd.draft.form.DraftSubdistrictForm;
import in.nic.pes.lgd.draft.service.DraftSubdistrictService;
import in.nic.pes.lgd.draft.service.DraftUtilService;

@Controller 
public class DraftGovermentController {
	
private Integer stateCode;
	
	@Autowired
	DraftUtilService draftUtilService;
	
	@Autowired 
	DraftSubdistrictService draftSubdistrictService;
	
	private Integer userId;
	
	private Integer districtCode;
	
	/**
	 * initBinder set the some basic details in sectionForm
	 * @param binder
	 * @param session
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		setGlobalParams(session);
		SimpleDateFormat dateFormat = new SimpleDateFormat(DraftConstant.CURRENT_DATE_PATTERN.toString());
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.getBindingResult();
        binder.validate();
     	//String obj = binder.getObjectName();
		/*if("DraftGovermentOrderForm".equalsIgnoreCase(obj)) {
			DraftGovermentOrderForm draftGovermentOrderForm = (DraftGovermentOrderForm) binder.getTarget();
			Integer entityCode=stateCode;
			String entityType=DraftConstant.ENTITY_TYPE_STATE.toString();
			if(districtCode>0){
				entityCode=districtCode;
				entityType=DraftConstant.ENTITY_TYPE_DISTRICT.toString();
			}
			
			draftSubdistrictForm.setEntityCode(entityCode);
			draftSubdistrictForm.setEntityType(entityType);
			draftSubdistrictForm.setUserId(userId);
			
		}*/
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
	
	
	@RequestMapping(value="/editDraftGovermentOrder",method=RequestMethod.POST)
	public ModelAndView editDraftGovermentOrder(HttpSession httpSession,@ModelAttribute("draftGovermentOrderForm") DraftGovermentOrderForm draftGovermentOrderForm,Model model, HttpServletRequest request){
		ModelAndView mav= null;	
		try{
			draftGovermentOrderForm=draftUtilService.getDraftGovermentOrderDetails(draftGovermentOrderForm);
			draftGovermentOrderForm.setEditMode(Boolean.TRUE);
			return setAttributes(request, new ModelAndView("edit_draft_goverment_order"), Integer.parseInt(DraftConstant.CREATE_SUBDISTRICT_OPERATION_CODE.toString()));
			
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		
		return mav;
	}
	
	@RequestMapping(value="/saveDraftGovermentOrder",method=RequestMethod.POST)
	public ModelAndView saveDraftGovermentOrder(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftGovermentOrderForm") DraftGovermentOrderForm draftGovermentOrderForm, BindingResult binding){
		ModelAndView mav= new ModelAndView("view_save_draft_subdistrict");	
		try{
			draftGovermentOrderForm.setEntityType(DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString().charAt(0));
			draftGovermentOrderForm.setOperationType(DraftConstant.OPERATION_TYPE_CREATE.toString());
			draftGovermentOrderForm.setEditMode(Boolean.TRUE);
			Integer updatedGroupId =draftUtilService.updateDraftGovermentOrderDetails(draftGovermentOrderForm,request,httpSession);
			Object[] objArr=draftSubdistrictService.viewDraftSubdistricDetails(updatedGroupId);
			model.addAttribute("draftGovermentOrderForm",(DraftGovermentOrderForm)objArr[1]);
			model.addAttribute("storeSubdistrictCoverageDetails",(Map<String,Object>)objArr[2]);
			model.addAttribute("storedSubdistrictForms",(LinkedList<DraftSubdistrictForm>)objArr[0]);
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		
		return mav;
	}
	
	
	private ModelAndView setAttributes(HttpServletRequest request, ModelAndView mav, Integer opeartionCode){
		try {
			setGlobalParams(request.getSession());
			
			LandRegionAttribute attributes = draftUtilService.onLoadLandregionEntity(stateCode, DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString(),opeartionCode);
			if(attributes.getIsGovernmentOrderUpload() == null || attributes.getIsMapUpload() == null){
				return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
			}
			
			if(!attributes.getIsGovernmentOrderUpload()){
				mav.addObject("templateList", attributes.getGovernmentOrderTemplates());
			}
		
			request.getSession().setAttribute("isGovernmentOrderUpload", attributes.getIsGovernmentOrderUpload());
			request.getSession().setAttribute("isMapUpload", attributes.getIsMapUpload());
			request.getSession().setAttribute("attachmentMasterGO", attributes.getAttachmentMasterGO());
			
			
			
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}

}
