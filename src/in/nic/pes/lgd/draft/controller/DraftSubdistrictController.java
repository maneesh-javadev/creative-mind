package in.nic.pes.lgd.draft.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.servlet.ModelAndView;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.draft.constant.DraftConstant;
import in.nic.pes.lgd.draft.constant.DraftUtils;
import in.nic.pes.lgd.draft.entities.LandRegionAttribute;
import in.nic.pes.lgd.draft.form.DraftGovermentOrderForm;
import in.nic.pes.lgd.draft.form.DraftManageSubdistrictForm;
import in.nic.pes.lgd.draft.form.DraftSubdistrictForm;
import in.nic.pes.lgd.draft.service.DraftDistrictService;
import in.nic.pes.lgd.draft.service.DraftSubdistrictService;
import in.nic.pes.lgd.draft.service.DraftUtilService;
import in.nic.pes.lgd.draft.validator.DraftSubdistrictValidator;
import in.nic.pes.lgd.validator.CommonValidatorImpl;

@Controller 
public class DraftSubdistrictController {
	
	@Autowired 
	private DraftDistrictService draftDistrictService;
	
	@Autowired 
	private DraftSubdistrictService draftSubdistrictService;
	
	@Autowired
	private DraftUtilService draftUtilService;
	
	@Autowired
	private DraftUtils draftUtils;
	
	@Autowired
	private DraftSubdistrictValidator draftSubdistrictValidator;
	
	private Integer stateCode;
	
	private Integer userId;
	
	private Integer districtCode;
	

	@Autowired
	private CommonValidatorImpl commonValidator;
	
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
     	String obj = binder.getObjectName();
		if("draftSubdistrictForm".equalsIgnoreCase(obj)) {
			DraftSubdistrictForm draftSubdistrictForm = (DraftSubdistrictForm) binder.getTarget();
			Integer entityCode=stateCode;
			String entityType=DraftConstant.ENTITY_TYPE_STATE.toString();
			if(districtCode>0){
				entityCode=districtCode;
				entityType=DraftConstant.ENTITY_TYPE_DISTRICT.toString();
			}
			
			draftSubdistrictForm.setEntityCode(entityCode);
			draftSubdistrictForm.setEntityType(entityType);
			draftSubdistrictForm.setUserId(userId);
			
		}else if("draftManageSubdistrictForm".equalsIgnoreCase(obj)) {
			DraftManageSubdistrictForm draftManageSubdistrictForm = (DraftManageSubdistrictForm) binder.getTarget();
			Integer entityCode=stateCode;
			String entityType=DraftConstant.ENTITY_TYPE_STATE.toString();
			if(districtCode>0){
				entityCode=districtCode;
				entityType=DraftConstant.ENTITY_TYPE_DISTRICT.toString();
			}
			
			draftManageSubdistrictForm.setEntityCode(entityCode);
			draftManageSubdistrictForm.setEntityType(entityType);
			draftManageSubdistrictForm.setUserId(userId);
			
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
	 * @param draftSubdistrictForm
	 * @return
	 */
	@RequestMapping(value="/createDraftSubdistrict",method=RequestMethod.GET)
	public ModelAndView createDraftSubdistrict(HttpSession httpSession,HttpServletRequest request,Model model,@ModelAttribute("draftSubdistrictForm") DraftSubdistrictForm draftSubdistrictForm){
		draftSubdistrictForm.setOperationCode(Integer.parseInt(DraftConstant.CREATE_SUBDISTRICT_OPERATION_CODE.toString()));
		model.addAttribute("serverAdd",false);
		removeDraftSessionAttibute(httpSession);
		return setAttributes(request, new ModelAndView("create_draft_subdistrict"), draftSubdistrictForm.getOperationCode(),draftSubdistrictForm.getEntityType(),draftSubdistrictForm.getEntityCode());
	}
	
	@RequestMapping(value="/startDraftSubdistrictCreation",method=RequestMethod.POST)
	public ModelAndView buildDraftSubdistrict(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftSubdistrictForm") DraftSubdistrictForm draftSubdistrictForm, BindingResult binding){
		ModelAndView mav= new ModelAndView("create_draft_subdistrict");
		try{
			setGlobalParams(request.getSession());
			draftSubdistrictForm=setPartFullSubdistrictList(draftSubdistrictForm);
			if(httpSession.getAttribute("subdistrictNameEnglish")!=null && !httpSession.getAttribute("subdistrictNameEnglish").toString().equals(draftSubdistrictForm.getSubdistrictNameEnglish())){
				draftSubdistrictForm.setListofSubdistrictNameEng(httpSession.getAttribute("listofSubdistrictNameEng")!=null?httpSession.getAttribute("listofSubdistrictNameEng").toString():"");
			}
			draftSubdistrictValidator.validate(draftSubdistrictForm, binding);
			if(binding.hasErrors()){
				model.addAttribute("serverError",true);
				mav.addObject("districtList",draftDistrictService.getDraftDistrictList(draftSubdistrictForm.getEntityCode(),draftSubdistrictForm.getEntityType()));
				return mav;
				}
			
			if((httpSession.getAttribute("subdistrictNameEnglish")==null) || (httpSession.getAttribute("subdistrictNameEnglish")!=null && !httpSession.getAttribute("subdistrictNameEnglish").toString().equals(draftSubdistrictForm.getSubdistrictNameEnglish())))
			{
				
				String subdistrictPartList=null;
				String subdistrictFullList=null;
				String villageFullList=null;
				LinkedList<DraftSubdistrictForm> storedSubdistrictForms = (LinkedList<DraftSubdistrictForm>) httpSession.getAttribute("storedSubdistrictForms");
				if (storedSubdistrictForms == null) {
					storedSubdistrictForms = new LinkedList<DraftSubdistrictForm>();
					draftSubdistrictForm.setSelectDistrictCode(draftSubdistrictForm.getDistrictCode());
					draftSubdistrictForm.setListofSubdistrictNameEng(draftSubdistrictForm.getSubdistrictNameEnglish());
					subdistrictFullList=draftSubdistrictForm.getListofSubdistrictFull();
					subdistrictPartList=draftSubdistrictForm.getListofSubdistrictPart();
					villageFullList=draftSubdistrictForm.getListofVillageFull();
					
				}else{
					
					
					draftSubdistrictForm.setDistrictCode(draftSubdistrictForm.getSelectDistrictCode());
					if(httpSession.getAttribute("listofAllSubdistrictPart")!=null){
				     subdistrictPartList=httpSession.getAttribute("listofAllSubdistrictPart").toString()+"@"+draftSubdistrictForm.getListofSubdistrictPart();
				   }
					if(httpSession.getAttribute("listofAllSubdistrictFull")!=null){
						subdistrictFullList=httpSession.getAttribute("listofAllSubdistrictFull").toString()+"@"+draftSubdistrictForm.getListofSubdistrictFull();
						}
					if(httpSession.getAttribute("listofAllVillageFull")!=null){
						
						villageFullList=httpSession.getAttribute("listofAllVillageFull").toString()+","+draftSubdistrictForm.getListofVillageFull();
						}
					
					if(httpSession.getAttribute("listofSubdistrictNameEng")!=null){
						draftSubdistrictForm.setListofSubdistrictNameEng(httpSession.getAttribute("listofSubdistrictNameEng").toString()+","+draftSubdistrictForm.getSubdistrictNameEnglish());
						
						}
					
					
					
				}
				draftSubdistrictForm.setListofAllSubdistrictPart(subdistrictPartList);
				draftSubdistrictForm.setListofAllSubdistrictFull(subdistrictFullList);
				draftSubdistrictForm.setListofAllVillageFull(villageFullList);
				draftSubdistrictForm.setStateCode(stateCode);
				draftSubdistrictForm.setOpeartionFlag(DraftConstant.OPERATION_TYPE_CREATE.toString());
				storedSubdistrictForms.add(draftSubdistrictForm);
				httpSession.setAttribute("storedSubdistrictForms", storedSubdistrictForms);
				httpSession.setAttribute("listofAllSubdistrictFull", subdistrictFullList);
				httpSession.setAttribute("listofAllSubdistrictPart", subdistrictPartList);
				httpSession.setAttribute("listofAllVillageFull",villageFullList);
				httpSession.setAttribute("subdistrictNameEnglish",draftSubdistrictForm.getSubdistrictNameEnglish());
				httpSession.setAttribute("listofSubdistrictNameEng",draftSubdistrictForm.getListofSubdistrictNameEng());
				
				
			}else{
				if(httpSession.getAttribute("listofAllSubdistrictPart")!=null){
					draftSubdistrictForm.setListofAllSubdistrictPart(httpSession.getAttribute("listofAllSubdistrictPart").toString());
					}
					if(httpSession.getAttribute("listofAllSubdistrictFull")!=null){
						draftSubdistrictForm.setListofAllSubdistrictFull(httpSession.getAttribute("listofAllSubdistrictFull").toString());
						}
					
					if(httpSession.getAttribute("listofAllVillageFull")!=null){
						draftSubdistrictForm.setListofAllVillageFull(httpSession.getAttribute("listofAllVillageFull").toString());
						}
					if(draftSubdistrictForm.getSelectDistrictCode()==null && draftSubdistrictForm.getDistrictCode()!=null){
						draftSubdistrictForm.setSelectDistrictCode(draftSubdistrictForm.getDistrictCode());
					}
					if(httpSession.getAttribute("listofAllSubdistrictNameEng")!=null){
						draftSubdistrictForm.setListofSubdistrictNameEng(httpSession.getAttribute("listofSubdistrictNameEng").toString());
						}
					
					draftSubdistrictForm.setUserId(userId);
			}
			
			if(("AddAnother").equals(draftSubdistrictForm.getFormAction())){
				DraftSubdistrictForm draftSubdistrictFormNew=new DraftSubdistrictForm();
				draftSubdistrictFormNew.setSelectDistrictCode(draftSubdistrictForm.getSelectDistrictCode());
				draftSubdistrictFormNew.setListofAllSubdistrictFull(draftSubdistrictForm.getListofAllSubdistrictFull());
				draftSubdistrictFormNew.setListofAllSubdistrictPart(draftSubdistrictForm.getListofAllSubdistrictPart());
				draftSubdistrictFormNew.setListofAllVillageFull(draftSubdistrictForm.getListofAllVillageFull());
				draftSubdistrictFormNew.setListofSubdistrictNameEng(draftSubdistrictForm.getListofSubdistrictNameEng());
				model.addAttribute("serverAdd",true);
				model.addAttribute("draftSubdistrictForm", draftSubdistrictFormNew);
				
				mav.addObject("districtList",draftDistrictService.getDraftDistrictList(draftSubdistrictForm.getEntityCode(),draftSubdistrictForm.getEntityType()));
			}else{
				//mav=new ModelAndView("comman_draft_goverment_order");
				DraftGovermentOrderForm draftGovermentOrderForm=new DraftGovermentOrderForm();
				draftGovermentOrderForm.setLandRegionCode(draftSubdistrictForm.getSelectDistrictCode());
				draftGovermentOrderForm.setLandRegionType(DraftConstant.ENTITY_TYPE_DISTRICT.toString());
				draftGovermentOrderForm.setOperationType(DraftConstant.OPERATION_TYPE_CREATE.toString());
				model.addAttribute("draftGovermentOrderForm", draftGovermentOrderForm);
				draftSubdistrictForm.setOperationCode(Integer.parseInt(DraftConstant.CREATE_SUBDISTRICT_OPERATION_CODE.toString()));
				
				if(httpSession.getAttribute("storedSubdistrictForms")!=null && ((LinkedList<DraftSubdistrictForm>) httpSession.getAttribute("storedSubdistrictForms")).size()>1){
					List<DraftSubdistrictForm> invaliSubdistList=draftSubdistrictService.checkSubdistrictIsEmpty(draftSubdistrictForm.getListofAllSubdistrictPart(), draftSubdistrictForm.getListofAllVillageFull());
					if(invaliSubdistList!=null && !invaliSubdistList.isEmpty()){
						model.addAttribute("invaliSubdistList",invaliSubdistList);
						model.addAttribute("isIvalidateSubdistrict",Boolean.TRUE);
					}else{
						model.addAttribute("isIvalidateSubdistrict",Boolean.FALSE);
					}
				}else{
					model.addAttribute("isIvalidateSubdistrict",Boolean.FALSE);
				}
				
				return setAttributes(request, new ModelAndView("comman_draft_goverment_order"), draftSubdistrictForm.getOperationCode(),draftSubdistrictForm.getEntityType(),draftSubdistrictForm.getEntityCode());
			}
			
			
			
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	
	@RequestMapping(value="/buildDraftSubdistrict",method=RequestMethod.POST)
	private ModelAndView buildDraftSubdistrict(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftGovermentOrderForm") DraftGovermentOrderForm draftGovermentOrderForm, BindingResult binding)
	{
		ModelAndView mav= new ModelAndView("view_save_draft_subdistrict");
		try{
			
			commonValidator.isValidMimeForDraftsubdistrict(binding, request,draftGovermentOrderForm.getGazettePublicationUpload(),httpSession);
			if(binding.hasErrors()){
				LinkedList<DraftSubdistrictForm> storedSubdistrictForms = (LinkedList<DraftSubdistrictForm>) httpSession.getAttribute("storedSubdistrictForms");
				DraftSubdistrictForm draftSubdistrictForm=storedSubdistrictForms.get(0);
				draftSubdistrictForm.setEntityCode(stateCode);
				draftSubdistrictForm.setEntityType("S");
				draftGovermentOrderForm.setLandRegionCode(draftSubdistrictForm.getSelectDistrictCode());
				draftGovermentOrderForm.setLandRegionType(DraftConstant.ENTITY_TYPE_DISTRICT.toString());
				draftGovermentOrderForm.setOperationType(DraftConstant.OPERATION_TYPE_CREATE.toString());
				
				draftSubdistrictForm.setOperationCode(Integer.parseInt(DraftConstant.CREATE_SUBDISTRICT_OPERATION_CODE.toString()));
				
				if(httpSession.getAttribute("storedSubdistrictForms")!=null && ((LinkedList<DraftSubdistrictForm>) httpSession.getAttribute("storedSubdistrictForms")).size()>1){
					List<DraftSubdistrictForm> invaliSubdistList=draftSubdistrictService.checkSubdistrictIsEmpty(draftSubdistrictForm.getListofAllSubdistrictPart(), draftSubdistrictForm.getListofAllVillageFull());
					if(invaliSubdistList!=null && !invaliSubdistList.isEmpty()){
						model.addAttribute("invaliSubdistList",invaliSubdistList);
						model.addAttribute("isIvalidateSubdistrict",Boolean.TRUE);
					}else{
						model.addAttribute("isIvalidateSubdistrict",Boolean.FALSE);
					}
				}else{
					model.addAttribute("isIvalidateSubdistrict",Boolean.FALSE);
				}
				
				return setAttributes(request, new ModelAndView("comman_draft_goverment_order"), draftSubdistrictForm.getOperationCode(),draftSubdistrictForm.getEntityType(),draftSubdistrictForm.getEntityCode());
				
			}
			LinkedList<DraftSubdistrictForm> storedSubdistrictForms = (LinkedList<DraftSubdistrictForm>) httpSession.getAttribute("storedSubdistrictForms");
			if(storedSubdistrictForms!=null && !storedSubdistrictForms.isEmpty()){
				draftGovermentOrderForm.setUserId(userId);
				draftGovermentOrderForm.setEntityType(DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString().charAt(0));
				draftGovermentOrderForm=draftUtils.uploadFileToServer(draftGovermentOrderForm,Long.parseLong( DraftConstant.ATTACHMENT_MASTER_GO.toString()),request,httpSession);
				if((DraftConstant.FORM_ACTION_DRAFT.toString()).equals(draftGovermentOrderForm.getFormAction())){
					model.addAttribute("groupId",draftSubdistrictService.saveSubdistrictinDraft(storedSubdistrictForms, draftGovermentOrderForm,request,httpSession));
					model.addAttribute("storeSubdistrictCoverageDetails",draftSubdistrictService.viewSaveSubdistrictDetails(storedSubdistrictForms, draftGovermentOrderForm));
				}
				else{
					model.addAttribute("storeSubdistrictCoverageDetails",draftSubdistrictService.viewSaveSubdistrictDetails(storedSubdistrictForms, draftGovermentOrderForm));
					storedSubdistrictForms=draftSubdistrictService.saveSubdistrict(storedSubdistrictForms, draftGovermentOrderForm);
					String invalidateSubdistrictcodes =draftGovermentOrderForm.getInvalidateSubdistrictcodes();
					if(invalidateSubdistrictcodes!=null && !("").equals(invalidateSubdistrictcodes.trim())){
						
						if (invalidateSubdistrictcodes.contains(",")) {
							
							Scanner scanner = new Scanner(invalidateSubdistrictcodes);
							scanner.useDelimiter(",");
							while (scanner.hasNext()) {
								draftSubdistrictService.subdistInvalFnAfterCreateMulDist(Integer.parseInt(scanner.next()), userId, draftGovermentOrderForm.getEffectiveDate()) ;
							}
						}else{
							draftSubdistrictService.subdistInvalFnAfterCreateMulDist(Integer.parseInt(invalidateSubdistrictcodes), userId, draftGovermentOrderForm.getEffectiveDate()) ;
						}
					}
					
					removeDraftSessionAttibute(httpSession);
				}
				
				
				model.addAttribute("storedSubdistrictForms",storedSubdistrictForms);
				
				
			}else{
				return new ModelAndView("redirect:home.htm?");
			}
			
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		
		return mav;
	}
	
	@RequestMapping(value="/manageDraftSubdistrict")
	public ModelAndView manageDraftSubdistrict(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftSubdistrictForm") DraftSubdistrictForm draftSubdistrictForm){
		ModelAndView mav= new ModelAndView("manage_draft_subdistrict");	
		try{
			model.addAttribute("subdistrictDraftList",draftSubdistrictService.getSubdistrictDraftList(draftSubdistrictForm.getEntityType(), draftSubdistrictForm.getEntityCode()));
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		
		return mav;
	}
	
	@RequestMapping(value="/editDraftSubdistrict",method=RequestMethod.POST)
	public ModelAndView editDraftSubdistrict(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftSubdistrictForm") DraftSubdistrictForm draftSubdistrictForm){
		ModelAndView mav= new ModelAndView("edit_draft_subdistrict");	
		try{
			draftSubdistrictForm=draftSubdistrictService.loadSubdistrictDraftbyId(draftSubdistrictForm);
			draftSubdistrictForm.setSelectDistrictCode(draftSubdistrictForm.getDistrictCode());
			mav.addObject("districtList",draftDistrictService.getDraftDistrictList(draftSubdistrictForm.getEntityCode(),draftSubdistrictForm.getEntityType()));
			model.addAttribute("serverAdd",true);
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		
		return mav;
	}
	
	@RequestMapping(value="/saveDraftSubdistrict",method=RequestMethod.POST)
	public ModelAndView saveDraftSubdistrict(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftSubdistrictForm") DraftSubdistrictForm draftSubdistrictForm, BindingResult binding){
		ModelAndView mav= new ModelAndView("view_save_draft_subdistrict");
		try{
			draftSubdistrictForm=setPartFullSubdistrictList(draftSubdistrictForm);
			draftSubdistrictService.updateSubdistrictinDraft(draftSubdistrictForm);
			return new ModelAndView("redirect:viewDraftSubdistrict.htm?paramCode="+draftSubdistrictForm.getGroupId());
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		
		return mav;
	}
	/**
	 * 
	 * @param request
	 * @param mav
	 * @param localBodyForm
	 * @return
	 */
	private ModelAndView setAttributes(HttpServletRequest request, ModelAndView mav,Integer operationCode,String entityType,Integer entityCode){
		try {
			setGlobalParams(request.getSession());
			
			LandRegionAttribute attributes = draftUtilService.onLoadLandregionEntity(stateCode, DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString(), operationCode);
			if(attributes.getIsGovernmentOrderUpload() == null || attributes.getIsMapUpload() == null){
				return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
			}
			
			if(!attributes.getIsGovernmentOrderUpload()){
				mav.addObject("templateList", attributes.getGovernmentOrderTemplates());
			}
		
			request.getSession().setAttribute("isGovernmentOrderUpload", attributes.getIsGovernmentOrderUpload());
			request.getSession().setAttribute("isMapUpload", attributes.getIsMapUpload());
			if(operationCode==Integer.parseInt(DraftConstant.CREATE_SUBDISTRICT_OPERATION_CODE.toString())){
				mav.addObject("districtList",draftDistrictService.getDraftDistrictList(entityCode,entityType));
			}
			
			request.getSession().setAttribute("attachmentMasterGO", attributes.getAttachmentMasterGO());
			
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	public DraftSubdistrictForm setPartFullSubdistrictList(DraftSubdistrictForm draftSubdistrictForm)throws Exception{
		String ContibutingSubdistrict=draftSubdistrictForm.getContibutingSubdistrict();
		StringBuilder subdistrictPartList=new StringBuilder();
		StringBuilder subdistrictFullList=new StringBuilder();
		if (ContibutingSubdistrict!=null) {
			Scanner scanner = new Scanner(ContibutingSubdistrict);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				String subdistrictRow=scanner.next();
				String subdistrctArray[]= subdistrictRow.split("@");
				if(subdistrictRow.contains("@PART")){
					subdistrictPartList.append(subdistrctArray[0]+"@");
				}else if(subdistrictRow.contains("@FULL")){
					subdistrictFullList.append(subdistrctArray[0]+"@");
				}
				
			}
			String tempPart=null;
			if(StringUtils.isNotBlank(subdistrictPartList.toString())){
				tempPart=		subdistrictPartList.toString();
				tempPart=tempPart.substring(0, tempPart.length()-1);
			}
			String tempFull=null;
			if(StringUtils.isNotBlank(subdistrictFullList.toString())){
				tempFull=subdistrictFullList.toString();
				tempFull=tempFull.substring(0, tempFull.length()-1);
			}

			draftSubdistrictForm.setListofSubdistrictPart(tempPart);
			draftSubdistrictForm.setListofSubdistrictFull(tempFull);
			draftSubdistrictForm.setListofVillageFull(draftSubdistrictForm.getContibutingVillage());
			
			scanner.close();
		}
		
		draftSubdistrictForm.setCoordinates(concatedCoordinates(draftSubdistrictForm.getLongitude(), draftSubdistrictForm.getLatitude()));
		
		return draftSubdistrictForm;
	}
	
	public void removeDraftSessionAttibute(HttpSession httpSession ){
		httpSession.removeAttribute("storedSubdistrictForms");
		httpSession.removeAttribute("listofSubdistrictFull");
		httpSession.removeAttribute("listofSubdistrictPart");
		httpSession.removeAttribute("listofVillageFull");
		httpSession.removeAttribute("subdistrictNameEnglish");
		httpSession.removeAttribute("listofSubdistrictNameEng");
		
	}
	
	@RequestMapping(value="/publishDraftSubdistrict",method=RequestMethod.POST)
	private ModelAndView publishDraftSubdistrict(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftSubdistrictForm") DraftSubdistrictForm draftSubdistrictForm)
	{
		ModelAndView mav= new ModelAndView("view_save_draft_subdistrict");
		try{
				
				Object[] objArr=draftSubdistrictService.subdistrictDrafttoPublish(draftSubdistrictForm);
				model.addAttribute("draftGovermentOrderForm",(DraftGovermentOrderForm)objArr[1]);
				model.addAttribute("storeSubdistrictCoverageDetails",(Map<String,Object>)objArr[2]);
				model.addAttribute("storedSubdistrictForms",(LinkedList<DraftSubdistrictForm>)objArr[0]);
		
			
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		
		return mav;
	}
	
	@RequestMapping(value="/deleteDraftSubdistrict",method=RequestMethod.POST)
	private ModelAndView deleteDraftSubdistrict(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftSubdistrictForm") DraftSubdistrictForm draftSubdistrictForm)
	{
		ModelAndView mav= new ModelAndView("manage_draft_subdistrict");
		try{
			return new ModelAndView("redirect:manageDraftSubdistrict.htm?deleteFlag="+draftSubdistrictService.deleteDraftSubdistrict(draftSubdistrictForm.getParamCode(), Integer.parseInt(DraftConstant.DRAFT_OPERATION_CREATE_SUBDISTRICT.toString())));
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	@RequestMapping(value="/viewDraftSubdistrict")
	public ModelAndView viewDraftSubdistrict(HttpSession httpSession, HttpServletRequest request,Model model,@RequestParam("paramCode")Integer paramCode){
		ModelAndView mav= new ModelAndView("view_save_draft_subdistrict");	
		try{
			
			Object[] objArr=draftSubdistrictService.viewDraftSubdistricDetails(paramCode);
			model.addAttribute("draftGovermentOrderForm",(DraftGovermentOrderForm)objArr[1]);
			model.addAttribute("storeSubdistrictCoverageDetails",(Map<String,Object>)objArr[2]);
			model.addAttribute("storedSubdistrictForms",(LinkedList<DraftSubdistrictForm>)objArr[0]);
			model.addAttribute("isView",true);
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		
		return mav;
	}
	
	/**
	 * 
	 * @param longitude
	 * @param latitude
	 * 
	 * @return
	 */
	private String concatedCoordinates(String longitude, String latitude){
		String coordinates = null; 
		longitude = longitude != null ? longitude : "";
		latitude = latitude != null ? latitude : "";
		if(!"".equals(longitude) && !"".equals(latitude)){
			String[] longitudeArray = longitude.trim().split(",");
			String[] latitudeArray = latitude.trim().split(",");
			if(longitudeArray.length == latitudeArray.length){
				List<String> arrCoordinates = new ArrayList<String>();
				for(int i = 0; i < longitudeArray.length; i++){
					arrCoordinates.add(longitudeArray[i].concat(":").concat(latitudeArray[i]));
				}
				coordinates = StringUtils.join(arrCoordinates, ",");
			}
		}
		return coordinates;
	}
	
	
	@RequestMapping(value="/manageSubdistrict",method=RequestMethod.GET)
	public ModelAndView manageSubdistrict(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftSubdistrictForm") DraftSubdistrictForm draftSubdistrictForm){
		ModelAndView mav= new ModelAndView("manage_subdistrict");	
		try{
			mav.addObject("districtList",draftDistrictService.getDraftDistrictList(draftSubdistrictForm.getEntityCode(),draftSubdistrictForm.getEntityType()));
			
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	@RequestMapping(value="/manageSubdistrict",method=RequestMethod.POST)
	public ModelAndView manageSubdistrictPOST(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftSubdistrictForm") DraftSubdistrictForm draftSubdistrictForm){
		ModelAndView mav= new ModelAndView("manage_subdistrict");	
		try{
			List<Subdistrict> draftSubdistrictList=draftSubdistrictService.getDraftSubdistrictList(draftSubdistrictForm.getDistrictCode(), null, null);
			mav.addObject("districtList",draftDistrictService.getDraftDistrictList(draftSubdistrictForm.getEntityCode(),draftSubdistrictForm.getEntityType()));
			if(draftSubdistrictList!=null && !draftSubdistrictList.isEmpty()){
				mav.addObject("draftSubdistrictList",draftSubdistrictList);
				mav.addObject("searchResult", Boolean.TRUE);
			}else{
				mav.addObject("searchResult", Boolean.FALSE);
			}
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	@RequestMapping(value="/changeSubdistrictDraft",method=RequestMethod.POST)
	public ModelAndView changeSubdistrictDraft(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftManageSubdistrictForm") DraftManageSubdistrictForm draftManageSubdistrictForm){
		ModelAndView mav= new ModelAndView("change_subdistrict");	
		try{
			draftManageSubdistrictForm=draftSubdistrictService.loadSubdistrictDetails(draftManageSubdistrictForm);
			draftManageSubdistrictForm.setOperationCode(Integer.parseInt(DraftConstant.CHANGE_SUBDISTRICT_OPERATION_CODE.toString()));
			draftManageSubdistrictForm.setChangeSubdistrictNameEnglish(draftManageSubdistrictForm.getSubdistrictNameEnglish());
			draftManageSubdistrictForm.setSubdistrictCode(draftManageSubdistrictForm.getParamCode());
			model.addAttribute("draftManageSubdistrictForm",draftManageSubdistrictForm);
			return setAttributes(request, new ModelAndView("change_subdistrict"), draftManageSubdistrictForm.getOperationCode(),draftManageSubdistrictForm.getEntityType(),draftManageSubdistrictForm.getEntityCode());	
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	@RequestMapping(value="/buildSubdistrictDraftChange",method=RequestMethod.POST)
	public ModelAndView buildSubdistrictDraftChange(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftManageSubdistrictForm") DraftManageSubdistrictForm draftManageSubdistrictForm, BindingResult result){
		ModelAndView mav= new ModelAndView("change_subdistrict");
		try{
			setGlobalParams(request.getSession());
			draftManageSubdistrictForm.setStateCode(stateCode);
			draftManageSubdistrictForm.setOpeartionFlag(DraftConstant.OPERATION_TYPE_MANAGE.toString());
			commonValidator.isValidMimeForDraftsubdistrict(result, request,draftManageSubdistrictForm.getGazettePublicationUpload(),httpSession);
			if(result.hasErrors()){
				//draftManageSubdistrictForm=draftSubdistrictService.loadSubdistrictDetails(draftManageSubdistrictForm);
				draftManageSubdistrictForm.setOperationCode(Integer.parseInt(DraftConstant.CHANGE_SUBDISTRICT_OPERATION_CODE.toString()));
				model.addAttribute("draftManageSubdistrictForm",draftManageSubdistrictForm);
				return setAttributes(request, new ModelAndView("change_subdistrict"), draftManageSubdistrictForm.getOperationCode(),draftManageSubdistrictForm.getEntityType(),draftManageSubdistrictForm.getEntityCode());	
				
			}
			if((DraftConstant.FORM_ACTION_DRAFT.toString()).equals(draftManageSubdistrictForm.getFormAction())){
				Integer groupId=draftSubdistrictService.saveChangeSubdistrictinDraft(draftManageSubdistrictForm, request, httpSession);
				model.addAttribute("groupId",groupId);
				draftManageSubdistrictForm.setDirectMode(Boolean.FALSE);
				draftManageSubdistrictForm.setParamCode(groupId);
				
			}else{
				draftManageSubdistrictForm=draftSubdistrictService.saveChangeSubdistrict(draftManageSubdistrictForm, request, httpSession);
				draftManageSubdistrictForm.setDirectMode(Boolean.TRUE);
			}
			Object[] objArr=draftSubdistrictService.viewChangeDraftSubdistricDetails(draftManageSubdistrictForm);
			model.addAttribute("draftManageSubdistrictForm",(DraftManageSubdistrictForm)objArr[0]);
			model.addAttribute("StoreSubdistrictDetails",(Map<String,Object>)objArr[1]);
			model.addAttribute("isView",true);
			mav=new ModelAndView("view_chnage_draft_subdistrict");	
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	@RequestMapping(value="/viewChangeDraftSubdistrict")
	public ModelAndView viewChangeDraftSubdistrict(HttpSession httpSession, HttpServletRequest request,Model model,@RequestParam("paramCode")Integer paramCode){
		ModelAndView mav= new ModelAndView("view_chnage_draft_subdistrict");	
		try{
			DraftManageSubdistrictForm draftManageSubdistrictForm=new DraftManageSubdistrictForm();
			draftManageSubdistrictForm.setParamCode(paramCode);
			draftManageSubdistrictForm.setFormAction(DraftConstant.FORM_ACTION_DRAFT.toString());
			Object[] objArr=draftSubdistrictService.viewChangeDraftSubdistricDetails(draftManageSubdistrictForm);
			model.addAttribute("draftManageSubdistrictForm",(DraftManageSubdistrictForm)objArr[0]);
			model.addAttribute("StoreSubdistrictDetails",(Map<String,Object>)objArr[1]);
			model.addAttribute("isView",true);
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		
		return mav;
	}
	
	@RequestMapping(value="/publishChangeDraftSubdistrict",method=RequestMethod.POST)
	private ModelAndView publishChangeDraftSubdistrict(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftManageSubdistrictForm") DraftManageSubdistrictForm draftManageSubdistrictForm)
	{
		ModelAndView mav= new ModelAndView("view_chnage_draft_subdistrict");
		try{
				draftManageSubdistrictForm.setFormAction(DraftConstant.FORM_ACTION_PUBLISH.toString());
				Object[] objArr=draftSubdistrictService.changeSubdistrictDrafttoPublish(draftManageSubdistrictForm,request,httpSession);
				model.addAttribute("draftManageSubdistrictForm",(DraftManageSubdistrictForm)objArr[0]);
				model.addAttribute("StoreSubdistrictDetails",(Map<String,Object>)objArr[1]);
		
			
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		
		return mav;
	}
	
	@RequestMapping(value="/deleteChangeDraftSubdistrict",method=RequestMethod.POST)
	private ModelAndView deleteChangeDraftSubdistrict(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftManageSubdistrictForm") DraftManageSubdistrictForm draftManageSubdistrictForm)
	{
		ModelAndView mav= new ModelAndView("manage_draft_subdistrict");
		try{
			return new ModelAndView("redirect:manageDraftSubdistrict.htm?deleteFlag="+draftSubdistrictService.deleteDraftSubdistrict(draftManageSubdistrictForm.getParamCode(), Integer.parseInt(DraftConstant.DRAFT_OPERATION_CHANGE_SUBDISTRICT.toString())));
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	@RequestMapping(value="/editChangeDraftSubdistrict",method=RequestMethod.POST)
	private ModelAndView editChangeDraftSubdistrict(HttpSession httpSession, HttpServletRequest request,Model model,@ModelAttribute("draftManageSubdistrictForm") DraftManageSubdistrictForm draftManageSubdistrictForm)
	{
		ModelAndView mav= new ModelAndView("change_subdistrict");
		try{
			draftManageSubdistrictForm=draftSubdistrictService.getChangeDraftSubdistrict(draftManageSubdistrictForm.getParamCode());
			draftManageSubdistrictForm.setOperationCode(Integer.parseInt(DraftConstant.CHANGE_SUBDISTRICT_OPERATION_CODE.toString()));
			draftManageSubdistrictForm.setEditMode(Boolean.TRUE);
			model.addAttribute("draftManageSubdistrictForm",draftManageSubdistrictForm);
			return setAttributes(request, mav, draftManageSubdistrictForm.getOperationCode(),draftManageSubdistrictForm.getEntityType(),draftManageSubdistrictForm.getEntityCode());	
			
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		
		return mav;
	}
	
	
}
