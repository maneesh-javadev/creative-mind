package com.cmc.lgd.localbody.controllers;

import com.cmc.lgd.localbody.entities.CriteriaDraftedEntities;
import com.cmc.lgd.localbody.entities.DraftChangeGovtOrderLBTemp;
import com.cmc.lgd.localbody.entities.DraftChangeParentLocalbodyTemp;
import com.cmc.lgd.localbody.entities.DraftChangeTypeLocalbodyTemp;
import com.cmc.lgd.localbody.entities.DraftRenameLocalbodyTemp;
import com.cmc.lgd.localbody.entities.LBAttributes;
import com.cmc.lgd.localbody.entities.LocalBodyForm;
import com.cmc.lgd.localbody.entities.ManageLBDetails;
import com.cmc.lgd.localbody.rules.Errors;
import com.cmc.lgd.localbody.rules.LocalBodyConstant;
import com.cmc.lgd.localbody.rules.LocalBodyUtil;
import com.cmc.lgd.localbody.services.LocalBodyService;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.controllers.VillageController.EffectiveDateList;
import in.nic.pes.lgd.draft.form.LocalBodyDetailDto;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.ApplicationConstant;
import in.nic.pes.lgd.validator.CommonValidatorImpl;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManageRestructuredLBController
{
  private Integer stateCode;
  private Integer userId;
  private Integer districtCode;
  @Autowired
  private LocalBodyService localBodyService;
  @Autowired
  private LocalBodyUtil localBodyUtil;
  @Autowired
  private Errors lbFormValidator;
  @Autowired
  private VillageService villageService;
  @Autowired
  private GovernmentOrderService govtOrderService;
  @Autowired
  private CommonValidatorImpl commonValidator;
  List<MapAttachment> mapAttachmentList = new ArrayList();
  List<Attachment> attachmentList = new ArrayList();
  public static class EffectiveDateList extends ArrayList<GetEntityEffectiveDate> {}
 
  @InitBinder
  public void initBinder(WebDataBinder binder, HttpSession session)
  {
    setGlobalParams(session);
    SimpleDateFormat dateFormat = new SimpleDateFormat(LocalBodyConstant.CURRENT_DATE_PATTERN.toString());
    dateFormat.setLenient(false);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    binder.getBindingResult();
    binder.validate();
    String obj = binder.getObjectName();
    if ("localBodyForm".equalsIgnoreCase(obj))
    {
      LocalBodyForm localBodyForm = (LocalBodyForm)binder.getTarget();
      localBodyForm.setStateCode(this.stateCode);
      localBodyForm.setCreatedBy(this.userId);
      localBodyForm.setDistrictCode(this.districtCode);
    }
  }
 
  private void setGlobalParams(HttpSession session)
  {
    SessionObject sessionObject = (SessionObject)session.getAttribute("sessionObject");
    this.stateCode = sessionObject.getStateId();
    this.districtCode = sessionObject.getDistrictCode();
    this.userId = Integer.valueOf(sessionObject.getUserId().intValue());
  }
 
  private void setSessionAttributes(HttpSession session, LBAttributes attributes)
  {
    session.setAttribute("attachmentMasterGO", attributes.getAttachmentMasterGO());
    session.setAttribute("attachmentMasterMap", attributes.getAttachmentMasterMap());
    session.setAttribute("isGovernmentOrderUpload", attributes.getIsGovernmentOrderUpload());
    session.setAttribute("isMapUpload", attributes.getIsMapUpload());
  }
 
  private String errorHandler(HttpServletRequest request, Exception e)
  {
    IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
    return expHandler.handleSaveRedirectException(request, "", "label.lgd", Integer.valueOf(1), e);
  }
 
  private ModelAndView setAttributesForManageLB(HttpServletRequest request, ManageLBDetails manageLBDetails, ModelAndView mav)
  {
    mav.setViewName("restructued_grid_manage_localbody");
    try
    {
      setGlobalParams(request.getSession());
      String localBodyCreationType = manageLBDetails.getLocalBodyCreationType();
      LBAttributes attributes = this.localBodyService.onLoadLocalBody(this.stateCode, localBodyCreationType, LocalBodyConstant.PROCESS_MODIFY_NAME.toString());
      if ((attributes.getIsGovernmentOrderUpload() == null) || (attributes.getIsMapUpload() == null)) {
        return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
      }
      if (localBodyCreationType.equals(LocalBodyConstant.LB_URBAN.toString())) {
        mav.addObject("localBodyTypeList", attributes.getLocalBodyTypes());
      } else {
        mav.addObject("lbTypeHierarchy", attributes.getLbTypeHierarchy());
      }
      mav.addObject("LOCAL_BODY_CREATION_TYPE", localBodyCreationType);
      mav.addObject("criteriaLocalBodies", manageLBDetails);
      setSessionAttributes(request.getSession(), attributes);
    }
    catch (Exception ex)
    {
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/viewLocalBodyforPRI"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView gridManageRestructuredPriLB(HttpServletRequest request, Model model)
  {
    ManageLBDetails manageLBDetails = new ManageLBDetails();
    manageLBDetails.setLocalBodyCreationType(LocalBodyConstant.LB_PANCHAYAT.toString());
    return setAttributesForManageLB(request, manageLBDetails, new ModelAndView());
  }
 
  @RequestMapping(value={"/viewLocalBodyforTraditional"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView gridManageRestructuredTraditionalLB(HttpServletRequest request, Model model)
  {
    ManageLBDetails manageLBDetails = new ManageLBDetails();
    manageLBDetails.setLocalBodyCreationType(LocalBodyConstant.LB_TRADITIONAL.toString());
    return setAttributesForManageLB(request, manageLBDetails, new ModelAndView());
  }
 
  @RequestMapping(value={"/viewLocalBodyforUrban"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView gridManageRestructuredUrbanLB(HttpServletRequest request, Model model)
  {
    ManageLBDetails manageLBDetails = new ManageLBDetails();
    manageLBDetails.setLocalBodyCreationType(LocalBodyConstant.LB_URBAN.toString());
    return setAttributesForManageLB(request, manageLBDetails, new ModelAndView());
  }
 
  @RequestMapping({"/getLocalBodyDetailsForManage"})
  public ModelAndView getDraftedLocalBodyDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model, ManageLBDetails criteria)
  {
    ModelAndView mav = new ModelAndView();
    try
    {
      String creationType = criteria.getLocalBodyCreationType();
      String lbLevelCodes = criteria.getLocalBodyLevelCodes();
     
      Integer localBodyTypeCode = null;
      if ("Y".equals(criteria.getIsGISCoverage()))
      {
        creationType = criteria.getLocalBodyCreationType();
        lbLevelCodes = criteria.getLocalBodyLevelCodes();
        localBodyTypeCode = Integer.valueOf(Integer.parseInt(criteria.getLocalBodyTypeCode()));
        criteria.setLocalBodyCreationType(creationType);
        model.addAttribute("updateApprovedGP", Boolean.valueOf(criteria.isUpdateApprovedGP()));
      }
      else
      {
        if (!LocalBodyConstant.isValidCreationType(creationType)) {
          throw new Exception("Request has been intrupted, Requested action is not available.");
        }
        String lbTypePanchayat = criteria.getParamLocalBodyTypeCode();
        if (StringUtils.isNotBlank(lbTypePanchayat)) {
          if (LocalBodyConstant.LB_URBAN.toString().equals(creationType)) {
            localBodyTypeCode = Integer.valueOf(Integer.parseInt(lbTypePanchayat));
          } else {
            localBodyTypeCode = Integer.valueOf(Integer.parseInt(lbTypePanchayat.split("\\_")[0]));
          }
        }
        session.setAttribute("lbLevelCodes", lbLevelCodes);
      }
      List<ManageLBDetails> publishedLocalBodies = this.localBodyService.getLocalBodiesForManage(creationType, localBodyTypeCode, this.stateCode, lbLevelCodes, this.districtCode);
      mav.addObject("publishedLocalBodies", publishedLocalBodies);
      mav.addObject("searchResult", Boolean.TRUE);
      if (lbLevelCodes != null) {
        mav.addObject("modifyParentRequired", Boolean.TRUE);
      }
      return setAttributesForManageLB(request, criteria, mav);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return mav = new ModelAndView(errorHandler(request, ex));
    }
  }
 
  @RequestMapping(value={"/callViewPublishedLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView callViewPublishedLocalBody(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("criteriaLocalBodies") ManageLBDetails criteria)
  {
    ModelAndView mav = new ModelAndView("restructued_view_manage_localbody");
    try
    {
      Map<String, Object> draftedLBDetails = this.localBodyService.viewLocalBodyDetails(criteria.getLocalBodyCode());
      this.localBodyUtil.setMapAttributes(draftedLBDetails, mav);
      mav.addObject("criteriaLocalBodies", criteria);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/callModifyNamePublishedLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView callModifyNamePublishedLocalBody(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("criteriaLocalBodies") ManageLBDetails criteria)
  {
    ModelAndView mav = new ModelAndView("restructued_rename_localbody");
    try
    {
      LocalBodyForm lbForm = this.localBodyService.getLocalBodyFormObject(criteria.getLocalBodyCode());
      lbForm.setLocalBodyCreationType(criteria.getLocalBodyCreationType());
      mav.addObject("localBodyForm", lbForm);
      mav.addObject("previousNames", this.localBodyService.fetchPreviousNames(criteria.getLocalBodyCode()));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/publishRenameLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView publishRenameLocalBody(HttpServletRequest request, Model model, @ModelAttribute("localBodyForm") LocalBodyForm localBodyForm, BindingResult binding)
    throws Exception
  {
    setGlobalParams(request.getSession());
    ModelAndView mav = new ModelAndView();
    localBodyForm.setValidationAction(LocalBodyConstant.PROCESS_MODIFY_NAME.toString());
    this.lbFormValidator.validate(localBodyForm, binding, (Boolean)request.getSession().getAttribute("isGovernmentOrderUpload"));
    if (binding.hasErrors())
    {
      String annotation = null;
      if (localBodyForm.getId() != null)
      {
        annotation = "update_drafted_rename_lb";
        mav.addObject("modifyProcess", Boolean.TRUE);
      }
      else
      {
        annotation = "restructued_rename_localbody";
      }
      mav.setViewName(annotation);
      mav.addObject("localBodyForm", localBodyForm);
      mav.addObject("checkedServerValidation", Boolean.TRUE);
      return mav;
    }
    if ("DRAFT".equals(localBodyForm.getProcessAction())) {
      return createRenameLocalBodyAsDraft(request, model, localBodyForm);
    }
    if ("PUBLISH".equals(localBodyForm.getProcessAction())) {
      return publishRenameLocalBody(request, model, localBodyForm);
    }
    return null;
  }
 
  public ModelAndView createRenameLocalBodyAsDraft(HttpServletRequest request, Model model, LocalBodyForm localBodyForm)
  {
    ModelAndView mav = new ModelAndView();
    try
    {
      Boolean isSaved = this.localBodyService.saveRenameLocalBodyAsDraft(localBodyForm);
      if (isSaved.booleanValue()) {
        model.addAttribute("publishMessage", "Local Body is successfully saved in Draft Mode.");
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(localBodyForm.getLocalBodyCreationType()), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  public ModelAndView publishRenameLocalBody(HttpServletRequest request, Model model, LocalBodyForm localBodyForm)
  {
    ModelAndView mav = new ModelAndView();
    try
    {
      Boolean isSaved = this.localBodyService.publishRenameLocalBody(localBodyForm);
      if (isSaved.booleanValue())
      {
        mav.setViewName("success");
        StringBuilder builder = new StringBuilder("Local Government Body ( ");
        builder.append(localBodyForm.getLocalBodyNameEnglish());
        builder.append(" ) is renamed successfully.");
        mav.addObject("msgid", builder);
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/callViewDraftedRenameLB"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView callViewDraftedRenameLB(HttpServletRequest request, HttpServletResponse response, Model model, CriteriaDraftedEntities criteria)
  {
    setGlobalParams(request.getSession());
    ModelAndView mav = new ModelAndView("view_drafted_rename_lb");
    try
    {
      LBAttributes attributes = this.localBodyService.onLoadLocalBody(this.stateCode, criteria.getLocalBodyCreationType(), LocalBodyConstant.PROCESS_MODIFY_NAME.toString());
      if ((attributes.getIsGovernmentOrderUpload() == null) || (attributes.getIsMapUpload() == null)) {
        return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
      }
      Map<String, Object> draftedLBDetails = this.localBodyService.fetchDraftedRenamedLB(criteria.getEntityTempId());
      this.localBodyUtil.setMapAttributes(draftedLBDetails, mav);
      mav.addObject("draftBCreationType", criteria.getLocalBodyCreationType());
     
      setSessionAttributes(request.getSession(), attributes);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/modifiedDraftedRenameLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView modifiedDraftedRenameLocalBody(HttpServletRequest request, HttpServletResponse response, Model model, CriteriaDraftedEntities criteria)
  {
    setGlobalParams(request.getSession());
    ModelAndView mav = new ModelAndView("update_drafted_rename_lb");
    try
    {
      LBAttributes attributes = this.localBodyService.onLoadLocalBody(this.stateCode, criteria.getLocalBodyCreationType(), LocalBodyConstant.PROCESS_MODIFY_NAME.toString());
      if ((attributes.getIsGovernmentOrderUpload() == null) || (attributes.getIsMapUpload() == null)) {
        return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
      }
      DraftRenameLocalbodyTemp draftRenameLocalbodyTemp = this.localBodyService.getDraftRenameLocalbodyTemp(criteria.getEntityTempId());
      LocalBodyForm lbForm = this.localBodyService.getLocalBodyFormObject(draftRenameLocalbodyTemp.getLocalBodyCode());
     
      LocalBodyForm copyLBForm = new LocalBodyForm();
      this.localBodyUtil.copyBeanAttributes(copyLBForm, lbForm);
      model.addAttribute("previousLBDetails", copyLBForm);
     
      this.localBodyUtil.copyBeanAttributes(lbForm, draftRenameLocalbodyTemp);
      lbForm.setLocalBodyCreationType(criteria.getLocalBodyCreationType());
      model.addAttribute("localBodyForm", lbForm);
      model.addAttribute("modifyProcess", Boolean.TRUE);
      model.addAttribute("previousNames", this.localBodyService.fetchPreviousNames(draftRenameLocalbodyTemp.getLocalBodyCode()));
      setSessionAttributes(request.getSession(), attributes);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/publishSingleDraftedRenameLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView publishSingleDraftedRenameLocalBody(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("tempLocalBodyCode") Integer tempLocalBodyCode, @RequestParam("tempLocalBodyCreationType") String tempLocalBodyCreationType)
  {
    try
    {
      Boolean isPublishedStatus = this.localBodyService.publishOrDeleteDraftRenameLBToTransaction(tempLocalBodyCode, Boolean.FALSE.booleanValue(), request);
      if (isPublishedStatus.booleanValue()) {
        model.addAttribute("publishMessage", "Record Published Successfully.");
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(tempLocalBodyCreationType), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return new ModelAndView(errorHandler(request, ex));
    }
  }
 
  @RequestMapping(value={"/deleteSingleDraftedRenameLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView deleteSingleDraftedRenameLocalBody(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("tempLocalBodyCode") Integer tempLocalBodyCode, @RequestParam("tempLocalBodyCreationType") String tempLocalBodyCreationType)
  {
    try
    {
      Boolean isPublishedStatus = this.localBodyService.publishOrDeleteDraftRenameLBToTransaction(tempLocalBodyCode, Boolean.TRUE.booleanValue(), request);
      if (isPublishedStatus.booleanValue()) {
        model.addAttribute("publishMessage", "Drafted Local Body Deleted Successfully.");
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(tempLocalBodyCreationType), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return new ModelAndView(errorHandler(request, ex));
    }
  }
 
  @RequestMapping(value={"/callModifyParentPublishedLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView callModifyParentPublishedLocalBody(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("criteriaLocalBodies") ManageLBDetails criteria)
  {
    ModelAndView mav = new ModelAndView("restructued_modify_parent_localbody");
    try
    {
      LocalBodyForm lbForm = this.localBodyService.getLocalBodyFormObject(criteria.getLocalBodyCode());
      lbForm.setLocalBodyCreationType(criteria.getLocalBodyCreationType());
     
      LBAttributes modifyParentAttributes = this.localBodyService.getLBDetailsForModifyParent(lbForm.getLocalBodyTypeCode(), lbForm.getParentLocalBodyCode(), this.stateCode);
     
      mav.addObject("modifyParentAttributes", modifyParentAttributes);
      mav.addObject("localBodyForm", lbForm);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/publishModifyParentLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView publishModifyParentLocalBody(HttpServletRequest request, Model model, @ModelAttribute("localBodyForm") LocalBodyForm localBodyForm, BindingResult binding)
    throws Exception
  {
    setGlobalParams(request.getSession());
    ModelAndView mav = new ModelAndView();
    localBodyForm.setValidationAction(LocalBodyConstant.PROCESS_MODIFY_PARENT.toString());
    this.lbFormValidator.validate(localBodyForm, binding, (Boolean)request.getSession().getAttribute("isGovernmentOrderUpload"));
    if (binding.hasErrors())
    {
      String annotation = null;
      if (localBodyForm.getId() != null)
      {
        annotation = "update_drafted_change_parent_lb";
        mav.addObject("modifyProcess", Boolean.TRUE);
      }
      else
      {
        annotation = "restructued_modify_parent_localbody";
      }
      mav.setViewName(annotation);
      mav.addObject("localBodyForm", localBodyForm);
      mav.addObject("checkedServerValidation", Boolean.TRUE);
      LBAttributes modifyParentAttributes = this.localBodyService.getLBDetailsForModifyParent(localBodyForm.getLocalBodyTypeCode(), localBodyForm.getParentLocalBodyCode(), this.stateCode);
      mav.addObject("modifyParentAttributes", modifyParentAttributes);
      return mav;
    }
    if ("DRAFT".equals(localBodyForm.getProcessAction())) {
      return changeParentLocalBodyAsDraft(request, model, localBodyForm);
    }
    if ("PUBLISH".equals(localBodyForm.getProcessAction())) {
      return changeParentLocalBody(request, model, localBodyForm);
    }
    return null;
  }
 
  public ModelAndView changeParentLocalBodyAsDraft(HttpServletRequest request, Model model, LocalBodyForm localBodyForm)
  {
    ModelAndView mav = new ModelAndView();
    try
    {
      Boolean isSaved = this.localBodyService.saveChangeParentLocalBodyAsDraft(localBodyForm);
      if (isSaved.booleanValue()) {
        model.addAttribute("publishMessage", "Local Body is successfully saved in Draft Mode.");
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(localBodyForm.getLocalBodyCreationType()), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  public ModelAndView changeParentLocalBody(HttpServletRequest request, Model model, LocalBodyForm localBodyForm)
  {
    ModelAndView mav = new ModelAndView("success");
    try
    {
      String isSaved = this.localBodyService.publishChangeParentLocalBody(localBodyForm);
      StringBuilder builder = new StringBuilder("The local government body ");
      if (LocalBodyConstant.SUCCESS_MESSAGE.toString().equalsIgnoreCase(isSaved))
      {
        builder.append("Parent of local government body ");
       
        builder.append("updated successfully.");
      }
      else
      {
        builder.append(isSaved);
      }
      mav.addObject("msgid", builder);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/callViewDraftedChangeParentLB"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView callViewDraftedChangeParentLB(HttpServletRequest request, HttpServletResponse response, Model model, CriteriaDraftedEntities criteria)
  {
    setGlobalParams(request.getSession());
    ModelAndView mav = new ModelAndView("view_drafted_change_parent_lb");
    try
    {
      LBAttributes attributes = this.localBodyService.onLoadLocalBody(this.stateCode, criteria.getLocalBodyCreationType(), LocalBodyConstant.PROCESS_MODIFY_PARENT.toString());
      if ((attributes.getIsGovernmentOrderUpload() == null) || (attributes.getIsMapUpload() == null)) {
        return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
      }
      Map<String, Object> draftedLBDetails = this.localBodyService.fetchDraftedChangeParentLB(criteria.getEntityTempId());
      this.localBodyUtil.setMapAttributes(draftedLBDetails, mav);
      mav.addObject("draftBCreationType", criteria.getLocalBodyCreationType());
      setSessionAttributes(request.getSession(), attributes);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/modifiedDraftedChangeParentLB"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView modifiedDraftedChangeParentLB(HttpServletRequest request, HttpServletResponse response, Model model, CriteriaDraftedEntities criteria)
  {
    setGlobalParams(request.getSession());
    ModelAndView mav = new ModelAndView("update_drafted_change_parent_lb");
    try
    {
      LBAttributes attributes = this.localBodyService.onLoadLocalBody(this.stateCode, criteria.getLocalBodyCreationType(), LocalBodyConstant.PROCESS_MODIFY_PARENT.toString());
      if ((attributes.getIsGovernmentOrderUpload() == null) || (attributes.getIsMapUpload() == null)) {
        return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
      }
      DraftChangeParentLocalbodyTemp draftChangeParentLocalbodyTemp = this.localBodyService.getDraftChangeParentLocalbodyTemp(criteria.getEntityTempId());
      LocalBodyForm lbEntity = this.localBodyService.getLocalBodyFormObject(draftChangeParentLocalbodyTemp.getLocalBodyCode());
      LBAttributes modifyParentAttributes = this.localBodyService.getLBDetailsForModifyParent(lbEntity.getLocalBodyTypeCode(), lbEntity.getParentLocalBodyCode(), this.stateCode);
     
      LocalBodyForm lbFormObject = new LocalBodyForm();
      this.localBodyUtil.copyBeanAttributes(lbFormObject, draftChangeParentLocalbodyTemp);
      lbFormObject.setId(draftChangeParentLocalbodyTemp.getTempPkId());
      lbFormObject.setLocalBodyCreationType(criteria.getLocalBodyCreationType());
     
      model.addAttribute("modifyParentAttributes", modifyParentAttributes);
      model.addAttribute("previousLBDetails", lbEntity);
      model.addAttribute("localBodyForm", lbFormObject);
      model.addAttribute("modifyProcess", Boolean.TRUE);
      setSessionAttributes(request.getSession(), attributes);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/publishSingleDraftedChangeParentLB"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView publishSingleDraftedChangeParentLB(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("tempLocalBodyCode") Integer tempLocalBodyCode, @RequestParam("tempLocalBodyCreationType") String tempLocalBodyCreationType)
  {
    try
    {
      String isPublishedStatus = this.localBodyService.publishOrDeleteDraftChangeParentLBToTransaction(tempLocalBodyCode, Boolean.FALSE.booleanValue(), request);
      if (LocalBodyConstant.SUCCESS_MESSAGE.toString().equalsIgnoreCase(isPublishedStatus)) {
        model.addAttribute("publishMessage", "Record Published Successfully.");
      } else {
        model.addAttribute("publishMessage", isPublishedStatus);
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(tempLocalBodyCreationType), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return new ModelAndView(errorHandler(request, ex));
    }
  }
 
  @RequestMapping(value={"/deleteSingleDraftedChangeParentLB"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView deleteSingleDraftedChangeParentLB(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("tempLocalBodyCode") Integer tempLocalBodyCode, @RequestParam("tempLocalBodyCreationType") String tempLocalBodyCreationType)
  {
    try
    {
      String isPublishedStatus = this.localBodyService.publishOrDeleteDraftChangeParentLBToTransaction(tempLocalBodyCode, Boolean.TRUE.booleanValue(), request);
      if (LocalBodyConstant.SUCCESS_MESSAGE.toString().equalsIgnoreCase(isPublishedStatus)) {
        model.addAttribute("publishMessage", "Drafted Local Body Deleted Successfully.");
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(tempLocalBodyCreationType), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return new ModelAndView(errorHandler(request, ex));
    }
  }
 
  @RequestMapping(value={"/callModifyTypePublishedLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView callModifyTypePublishedLocalBody(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("criteriaLocalBodies") ManageLBDetails criteria)
  {
    ModelAndView mav = new ModelAndView("restructued_modify_type_localbody");
    try
    {
      LocalBodyForm lbForm = this.localBodyService.getLocalBodyFormObject(criteria.getLocalBodyCode());
      lbForm.setLocalBodyCreationType(criteria.getLocalBodyCreationType());
     
      Map<String, Object> modifyTypeAttributes = this.localBodyService.getLBDetailsForModifyType(lbForm.getLocalBodyTypeCode(), this.stateCode);
     
      mav.addObject("localBodyForm", lbForm);
      this.localBodyUtil.setMapAttributes(modifyTypeAttributes, mav);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/publishModifyTypeLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView publishModifyTypeLocalBody(HttpServletRequest request, Model model, @ModelAttribute("localBodyForm") LocalBodyForm localBodyForm, BindingResult binding)
    throws Exception
  {
    setGlobalParams(request.getSession());
    ModelAndView mav = new ModelAndView();
    localBodyForm.setValidationAction(LocalBodyConstant.PROCESS_MODIFY_TYPE.toString());
    this.lbFormValidator.validate(localBodyForm, binding, (Boolean)request.getSession().getAttribute("isGovernmentOrderUpload"));
    if (binding.hasErrors())
    {
      String annotation = null;
      if (localBodyForm.getId() != null)
      {
        annotation = "update_drafted_change_type_lb";
        mav.addObject("modifyProcess", Boolean.TRUE);
      }
      else
      {
        annotation = "restructued_modify_type_localbody";
      }
      mav.setViewName(annotation);
      mav.addObject("localBodyForm", localBodyForm);
      mav.addObject("checkedServerValidation", Boolean.TRUE);
      Map<String, Object> modifyTypeAttributes = this.localBodyService.getLBDetailsForModifyType(localBodyForm.getLocalBodyTypeCode(), this.stateCode);
      this.localBodyUtil.setMapAttributes(modifyTypeAttributes, mav);
      return mav;
    }
    if ("DRAFT".equals(localBodyForm.getProcessAction())) {
      return changeTypeLocalBodyAsDraft(request, model, localBodyForm);
    }
    if ("PUBLISH".equals(localBodyForm.getProcessAction())) {
      return changeTypeLocalBodyPublish(request, model, localBodyForm);
    }
    return null;
  }
 
  public ModelAndView changeTypeLocalBodyAsDraft(HttpServletRequest request, Model model, LocalBodyForm localBodyForm)
  {
    ModelAndView mav = new ModelAndView();
    try
    {
      Boolean isSaved = this.localBodyService.saveChangeTypeLocalBodyAsDraft(localBodyForm);
      if (isSaved.booleanValue()) {
        model.addAttribute("publishMessage", "Local Body is successfully saved in Draft Mode.");
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(localBodyForm.getLocalBodyCreationType()), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  public ModelAndView changeTypeLocalBodyPublish(HttpServletRequest request, Model model, LocalBodyForm localBodyForm)
  {
    ModelAndView mav = new ModelAndView();
    try
    {
      Boolean isSaved = this.localBodyService.publishChangeTypeLocalBody(localBodyForm);
      if (isSaved.booleanValue())
      {
        mav.setViewName("success");
        StringBuilder builder = new StringBuilder("The local government body ");
        builder.append(localBodyForm.getLocalBodyNameEnglish());
        builder.append(" was updated successfully.");
        mav.addObject("msgid", builder);
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/callViewDraftedChangeTypeLB"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView callViewDraftedChangeTypeLB(HttpServletRequest request, HttpServletResponse response, Model model, CriteriaDraftedEntities criteria)
  {
    setGlobalParams(request.getSession());
    ModelAndView mav = new ModelAndView("view_drafted_change_type_lb");
    try
    {
      LBAttributes attributes = this.localBodyService.onLoadLocalBody(this.stateCode, criteria.getLocalBodyCreationType(), LocalBodyConstant.PROCESS_MODIFY_TYPE.toString());
      if ((attributes.getIsGovernmentOrderUpload() == null) || (attributes.getIsMapUpload() == null)) {
        return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
      }
      Map<String, Object> draftedLBDetails = this.localBodyService.fetchDraftedChangeTypeLB(criteria.getEntityTempId());
      this.localBodyUtil.setMapAttributes(draftedLBDetails, mav);
      mav.addObject("draftBCreationType", criteria.getLocalBodyCreationType());
      setSessionAttributes(request.getSession(), attributes);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/modifiedDraftedChangeTypeLB"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView modifiedDraftedChangeTypeLB(HttpServletRequest request, HttpServletResponse response, Model model, CriteriaDraftedEntities criteria)
  {
    setGlobalParams(request.getSession());
    ModelAndView mav = new ModelAndView("update_drafted_change_type_lb");
    try
    {
      LBAttributes attributes = this.localBodyService.onLoadLocalBody(this.stateCode, criteria.getLocalBodyCreationType(), LocalBodyConstant.PROCESS_MODIFY_TYPE.toString());
      if ((attributes.getIsGovernmentOrderUpload() == null) || (attributes.getIsMapUpload() == null)) {
        return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
      }
      DraftChangeTypeLocalbodyTemp draftChangeTypeLocalbodyTemp = this.localBodyService.getDraftChangeTypeLocalbodyTemp(criteria.getEntityTempId());
      LocalBodyForm previousLBDetails = this.localBodyService.getLocalBodyFormObject(draftChangeTypeLocalbodyTemp.getLocalBodyCode());
      Map<String, Object> modifyTypeAttributes = this.localBodyService.getLBDetailsForModifyType(previousLBDetails.getLocalBodyTypeCode(), this.stateCode);
     
      LocalBodyForm lbFormObject = new LocalBodyForm();
      this.localBodyUtil.copyBeanAttributes(lbFormObject, draftChangeTypeLocalbodyTemp);
      lbFormObject.setId(draftChangeTypeLocalbodyTemp.getTempPkId());
      lbFormObject.setLocalBodyCreationType(criteria.getLocalBodyCreationType());
      model.addAttribute("localBodyForm", lbFormObject);
      model.addAttribute("previousLBTableObject", previousLBDetails);
      model.addAttribute("modifyProcess", Boolean.TRUE);
      this.localBodyUtil.setMapAttributes(modifyTypeAttributes, mav);
      setSessionAttributes(request.getSession(), attributes);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/publishSingleDraftedChangeTypeLB"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView publishSingleDraftedChangeTypeLB(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("tempLocalBodyCode") Integer tempLocalBodyCode, @RequestParam("tempLocalBodyCreationType") String tempLocalBodyCreationType)
  {
    try
    {
      Boolean isPublishedStatus = this.localBodyService.publishOrDeleteDraftChangeTypeLBToTransaction(tempLocalBodyCode, Boolean.FALSE.booleanValue(), request);
      if (isPublishedStatus.booleanValue()) {
        model.addAttribute("publishMessage", "Record Published Successfully.");
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(tempLocalBodyCreationType), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return new ModelAndView(errorHandler(request, ex));
    }
  }
 
  @RequestMapping(value={"/deleteSingleDraftedChangeTypeLB"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView deleteSingleDraftedChangeTypeLB(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("tempLocalBodyCode") Integer tempLocalBodyCode, @RequestParam("tempLocalBodyCreationType") String tempLocalBodyCreationType)
  {
    try
    {
      Boolean isPublishedStatus = this.localBodyService.publishOrDeleteDraftChangeTypeLBToTransaction(tempLocalBodyCode, Boolean.TRUE.booleanValue(), request);
      if (isPublishedStatus.booleanValue()) {
        model.addAttribute("publishMessage", "Drafted Local Body Deleted Successfully.");
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(tempLocalBodyCreationType), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return new ModelAndView(errorHandler(request, ex));
    }
  }
 
  @RequestMapping(value={"/callChangeGOPublishedLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView callChangeGOPublishedLocalBody(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("criteriaLocalBodies") ManageLBDetails criteria)
  {
    ModelAndView mav = new ModelAndView("restructued_modify_go_localbody");
    try
    {
      Map<String, Object> govOrderAttributes = this.localBodyService.getLBDetailsForModifyGovOrder(criteria.getLocalBodyCode(), this.stateCode, criteria.getLocalBodyCreationType());
      this.localBodyUtil.setMapAttributes(govOrderAttributes, mav);
      mav.addObject("modifyProcess", Boolean.TRUE);
      mav.addObject("GOV_ORDER_ACTION", Boolean.TRUE);
      request.getSession().setAttribute("isMapUploadModifyGO", govOrderAttributes.get("isMapUploadModifyGO"));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/publishModifyGovOrderLB"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView publishModifyGovOrderLB(HttpServletRequest request, Model model, @ModelAttribute("localBodyForm") LocalBodyForm localBodyForm, BindingResult binding)
    throws Exception
  {
    setGlobalParams(request.getSession());
    ModelAndView mav = new ModelAndView();
    localBodyForm.setValidationAction(LocalBodyConstant.PROCESS_MODIFY_GOV_ORDER.toString());
    this.lbFormValidator.validate(localBodyForm, binding, (Boolean)request.getSession().getAttribute("isGovernmentOrderUpload"));
    if (binding.hasErrors())
    {
      String annotation = null;
      if (localBodyForm.getId() != null)
      {
        annotation = "update_drafted_change_go";
        mav.addObject("modifyProcess", Boolean.TRUE);
      }
      else
      {
        annotation = "restructued_modify_go_localbody";
      }
      mav.setViewName(annotation);
      Map<String, Object> govOrderAttributes = this.localBodyService.getLBDetailsForModifyGovOrder(localBodyForm.getLocalBodyCode(), this.stateCode, localBodyForm.getLocalBodyCreationType());
      this.localBodyUtil.setMapAttributes(govOrderAttributes, mav);
      mav.addObject("localBodyForm", localBodyForm);
      mav.addObject("checkedServerValidation", Boolean.TRUE);
      mav.addObject("GOV_ORDER_ACTION", Boolean.TRUE);
      return mav;
    }
    if ("DRAFT".equals(localBodyForm.getProcessAction())) {
      return changeGovOrderLocalBodyAsDraft(request, model, localBodyForm);
    }
    if ("PUBLISH".equals(localBodyForm.getProcessAction())) {
      return changeGovOrderLocalBodyPublish(request, model, localBodyForm);
    }
    return null;
  }
 
  public ModelAndView changeGovOrderLocalBodyAsDraft(HttpServletRequest request, Model model, LocalBodyForm localBodyForm)
  {
    ModelAndView mav = new ModelAndView();
    try
    {
      Boolean isSaved = this.localBodyService.saveChangeGOLocalBodyAsDraft(localBodyForm);
      if (isSaved.booleanValue()) {
        model.addAttribute("publishMessage", "Local Body is successfully saved in Draft Mode.");
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(localBodyForm.getLocalBodyCreationType()), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  public ModelAndView changeGovOrderLocalBodyPublish(HttpServletRequest request, Model model, LocalBodyForm localBodyForm)
  {
    ModelAndView mav = new ModelAndView();
    try
    {
      Boolean isSaved = this.localBodyService.publishChangeGOLocalBody(localBodyForm);
      if (isSaved.booleanValue())
      {
        mav.setViewName("success");
        StringBuilder builder = new StringBuilder("The local government body ");
        builder.append(localBodyForm.getLocalBodyNameEnglish());
        builder.append(" was updated successfully.");
        mav.addObject("msgid", builder);
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/callViewDraftedChangeGO"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView callViewDraftedChangeGO(HttpServletRequest request, HttpServletResponse response, Model model, CriteriaDraftedEntities criteria)
  {
    setGlobalParams(request.getSession());
    ModelAndView mav = new ModelAndView("view_drafted_change_go");
    try
    {
      LBAttributes attributes = this.localBodyService.onLoadLocalBody(this.stateCode, criteria.getLocalBodyCreationType(), LocalBodyConstant.PROCESS_MODIFY_TYPE.toString());
      if ((attributes.getIsGovernmentOrderUpload() == null) || (attributes.getIsMapUpload() == null)) {
        return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
      }
      DraftChangeGovtOrderLBTemp draftChangeGovtOrderLBTemp = this.localBodyService.getDraftChangeGovtOrderLBTemp(criteria.getEntityTempId());
      LocalBodyForm lbForm = this.localBodyService.getLocalBodyFormObject(draftChangeGovtOrderLBTemp.getLocalBodyCode());
      mav.addObject("draftBCreationType", criteria.getLocalBodyCreationType());
      mav.addObject("localBodyTableObj", lbForm);
      mav.addObject("draftChangeGovtOrderLBTemp", draftChangeGovtOrderLBTemp);
      setSessionAttributes(request.getSession(), attributes);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/modifiedDraftedChangeGO"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView modifiedDraftedChangeGO(HttpServletRequest request, HttpServletResponse response, Model model, CriteriaDraftedEntities criteria)
  {
    setGlobalParams(request.getSession());
    ModelAndView mav = new ModelAndView("update_drafted_change_go");
    try
    {
      LBAttributes attributes = this.localBodyService.onLoadLocalBody(this.stateCode, criteria.getLocalBodyCreationType(), LocalBodyConstant.PROCESS_MODIFY_TYPE.toString());
      if ((attributes.getIsGovernmentOrderUpload() == null) || (attributes.getIsMapUpload() == null)) {
        return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
      }
      DraftChangeGovtOrderLBTemp draftChangeGovtOrderLBTemp = this.localBodyService.getDraftChangeGovtOrderLBTemp(criteria.getEntityTempId());
      LocalBodyForm lbFormObject = this.localBodyService.getLocalBodyFormObject(draftChangeGovtOrderLBTemp.getLocalBodyCode());
      this.localBodyUtil.copyBeanAttributes(lbFormObject, draftChangeGovtOrderLBTemp);
      lbFormObject.setId(draftChangeGovtOrderLBTemp.getTempPkId());
      lbFormObject.setLocalBodyCreationType(criteria.getLocalBodyCreationType());
     
      this.localBodyService.setGOandMapFileAttributes(lbFormObject);
     
      model.addAttribute("localBodyForm", lbFormObject);
      model.addAttribute("modifyProcess", Boolean.TRUE);
      mav.addObject("GOV_ORDER_ACTION", Boolean.TRUE);
      setSessionAttributes(request.getSession(), attributes);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/publishSingleDraftedChangeGO"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView publishSingleDraftedChangeGO(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("tempLocalBodyCode") Integer tempLocalBodyCode, @RequestParam("tempLocalBodyCreationType") String tempLocalBodyCreationType)
  {
    try
    {
      Boolean isPublishedStatus = this.localBodyService.publishOrDeleteDraftChangeGOToTransaction(tempLocalBodyCode, Boolean.FALSE.booleanValue(), request);
      if (isPublishedStatus.booleanValue()) {
        model.addAttribute("publishMessage", "Record Published Successfully.");
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(tempLocalBodyCreationType), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return new ModelAndView(errorHandler(request, ex));
    }
  }
 
  @RequestMapping(value={"/deleteSingleDraftedChangeGO"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView deleteSingleDraftedChangeGO(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("tempLocalBodyCode") Integer tempLocalBodyCode, @RequestParam("tempLocalBodyCreationType") String tempLocalBodyCreationType)
  {
    try
    {
      Boolean isPublishedStatus = this.localBodyService.publishOrDeleteDraftChangeGOToTransaction(tempLocalBodyCode, Boolean.TRUE.booleanValue(), request);
      if (isPublishedStatus.booleanValue()) {
        model.addAttribute("publishMessage", "Drafted Local Body Deleted Successfully.");
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(tempLocalBodyCreationType), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return new ModelAndView(errorHandler(request, ex));
    }
  }
 
  @RequestMapping(value={"/callChangeCoveredAreaPublishedLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView callChangeCoveredAreaPublishedLocalBody(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("criteriaLocalBodies") ManageLBDetails criteria)
  {
    ModelAndView mav = new ModelAndView("restructued_change_covered_localbody");
    try
    {
      String creationType = criteria.getLocalBodyCreationType();
      if (!LocalBodyConstant.isValidCreationType(creationType)) {
        throw new Exception("Request has been intrupted, Requested action is not available.");
      }
      LocalBodyForm lbForm = this.localBodyService.getLocalBodyFormObject(criteria.getLocalBodyCode());
      lbForm.setLocalBodyCreationType(creationType);
      lbForm.setIsGISCoverage(criteria.getIsGISCoverage());
      Integer clientParentCode = Integer.valueOf(0);
      if (LocalBodyConstant.LB_URBAN.toString().equalsIgnoreCase(creationType)) {
        mav.setViewName("restructued_change_covered_lb_urban");
      } else if ((lbForm.getParentLocalBodyCode() != null) && (lbForm.getParentLocalBodyCode().intValue() != 0)) {
        clientParentCode = this.localBodyService.getLBCodeByParentCode(lbForm.getParentLocalBodyCode());
      }
      this.localBodyUtil.setMapAttributes(this.localBodyService.changeCoverageDefaultDetails(lbForm), mav);
      mav.addObject("clientParentCode", clientParentCode);
      mav.addObject("localBodyForm", lbForm);
      mav.addObject("isDistrictLevel", Boolean.valueOf(ApplicationConstant.checkStateLBOnlyDisttWise(this.stateCode)));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/changeCoverageLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView buildLocalBody(HttpServletRequest request, HttpSession session, Model model, @ModelAttribute("localBodyForm") LocalBodyForm localBodyForm, BindingResult binding)
    throws Exception
  {
    ModelAndView mav = new ModelAndView();
    localBodyForm.setValidationAction(LocalBodyConstant.PROCESS_CHANGE_COVERAGE.toString());
    this.lbFormValidator.validate(localBodyForm, binding, (Boolean)request.getSession().getAttribute("isGovernmentOrderUpload"));
    if (binding.hasErrors())
    {
      String annotation = null;
      if (localBodyForm.getId() != null)
      {
        if (LocalBodyConstant.LB_URBAN.toString().equals(localBodyForm.getLocalBodyCreationType())) {
          annotation = "modify_temp_urban_change_coverage";
        } else {
          annotation = "modify_temp_change_coverage";
        }
        this.localBodyUtil.setMapAttributes(this.localBodyService.changeCoverageDefaultDetails(localBodyForm), mav);
        mav.addObject("modifyProcess", Boolean.TRUE);
      }
      else
      {
        if (LocalBodyConstant.LB_URBAN.toString().equals(localBodyForm.getLocalBodyCreationType())) {
          annotation = "restructued_change_covered_lb_urban";
        } else {
          annotation = "restructued_change_covered_localbody";
        }
        this.localBodyUtil.setMapAttributes(this.localBodyService.changeCoverageDefaultDetails(localBodyForm), mav);
        mav.addObject("modifyProcess", Boolean.TRUE);
      }
      mav.setViewName(annotation);
      mav.addObject("checkedServerValidation", Boolean.TRUE);
      mav.addObject("isDistrictLevel", Boolean.valueOf(ApplicationConstant.checkStateLBOnlyDisttWise(this.stateCode)));
     
      return mav;
    }
    if ("DRAFT".equals(localBodyForm.getProcessAction())) {
      return changeCoverageLocalBodyAsDraft(request, model, localBodyForm);
    }
    if ("PUBLISH".equals(localBodyForm.getProcessAction())) {
      return publishChangeCoverageLocalBody(request, session, model, localBodyForm);
    }
    return null;
  }
 
  public ModelAndView changeCoverageLocalBodyAsDraft(HttpServletRequest request, Model model, LocalBodyForm localBodyForm)
  {
    ModelAndView mav = new ModelAndView();
    try
    {
      Boolean isSaved = this.localBodyService.saveChangeCoveredAreaAsDraft(localBodyForm);
      if (isSaved.booleanValue()) {
        model.addAttribute("publishMessage", "Change Coverage Details has been Drafted Successfully.");
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(localBodyForm.getLocalBodyCreationType()), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  public ModelAndView publishChangeCoverageLocalBody(HttpServletRequest request, HttpSession session, Model model, @ModelAttribute("localBodyForm") LocalBodyForm localBodyForm)
  {
    ModelAndView mav = new ModelAndView();
    try
    {
      String changeCovStatus = this.localBodyService.publishChangeCoveredArea(localBodyForm, request);
      if (LocalBodyConstant.LB_PUBLISH_FAILD.toString().equals(changeCovStatus)) {
        throw new Exception();
      }
      if ("Y".equals(localBodyForm.getIsGISCoverage()))
      {
        String localBodyLevelCodes = session.getAttribute("lbLevelCodes").toString();
        StringBuilder annotation = new StringBuilder("redirect:getLocalBodyDetailsForManage.htm?");
        annotation.append("isGISCoverage=" + localBodyForm.getIsGISCoverage());
        annotation.append("&updateApprovedGP=" + Boolean.TRUE);
        annotation.append("&localBodyCode=" + localBodyForm.getLocalBodyCode());
        annotation.append("&localBodyCreationType=" + localBodyForm.getLocalBodyCreationType());
        annotation.append("&localBodyTypeCode=" + localBodyForm.getLocalBodyTypeCode());
        annotation.append("&localBodyLevelCodes=" + localBodyLevelCodes);
        annotation.append("&localBodyNameEnglish=" + localBodyForm.getLocalBodyNameEnglish());
        return new ModelAndView(annotation.toString());
      }
      session.removeAttribute("lbLevelCodes");
      mav.setViewName("success");
      StringBuilder builder = new StringBuilder("Covered area of Local Body is updated successfully.");
      mav.addObject("msgid", builder);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/viewDraftedChangeCoverageArea"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView viewDraftedChangeCoverageArea(HttpServletRequest request, HttpServletResponse response, Model model, CriteriaDraftedEntities criteria)
  {
    setGlobalParams(request.getSession());
    ModelAndView mav = new ModelAndView("view_drafted_change_coverage_area_lb");
    try
    {
      LBAttributes attributes = this.localBodyService.onLoadLocalBody(this.stateCode, criteria.getLocalBodyCreationType(), LocalBodyConstant.PROCESS_CHANGE_COVERAGE.toString());
      if ((attributes.getIsGovernmentOrderUpload() == null) || (attributes.getIsMapUpload() == null)) {
        return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
      }
      Map<String, Object> draftedLBDetails = this.localBodyService.getDraftedChangeCoverageDetails(criteria.getEntityTempId());
      this.localBodyUtil.setMapAttributes(draftedLBDetails, mav);
      mav.addObject("draftBCreationType", criteria.getLocalBodyCreationType());
     
      setSessionAttributes(request.getSession(), attributes);
      mav.addObject("isDistrictLevel", Boolean.valueOf(ApplicationConstant.checkStateLBOnlyDisttWise(this.stateCode)));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/modifiedDraftedChangeCoveredArea"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView modiifiedDraftedLB(HttpServletRequest request, HttpServletResponse response, Model model, CriteriaDraftedEntities criteria)
  {
    setGlobalParams(request.getSession());
    ModelAndView mav = new ModelAndView();
    try
    {
      LBAttributes attributes = this.localBodyService.onLoadLocalBody(this.stateCode, criteria.getLocalBodyCreationType(), LocalBodyConstant.PROCESS_CHANGE_COVERAGE.toString());
      if ((attributes.getIsGovernmentOrderUpload() == null) || (attributes.getIsMapUpload() == null)) {
        return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
      }
      Map<String, Object> draftedLBDetails = this.localBodyService.getDraftedChangeCoverageModification(criteria.getEntityTempId());
      LocalBodyForm lbForm = (LocalBodyForm)draftedLBDetails.get("lbForm");
      lbForm.setLocalBodyCreationType(criteria.getLocalBodyCreationType());
      lbForm.setHidContributingLBCodes(lbForm.getContributingLBCodes());
      lbForm.setHidContributingLandRegionCodes(lbForm.getContributingLandRegionCodes());
      lbForm.setIsResetedCoverage(Boolean.FALSE);
     
      mav.addObject("checkedServerValidation", Boolean.TRUE);
      mav.addObject("modifyProcess", Boolean.TRUE);
      mav.addObject("localBodyForm", lbForm);
      draftedLBDetails.remove("lbForm");
      Integer clientParentCode = Integer.valueOf(0);
      if (LocalBodyConstant.LB_URBAN.toString().equals(criteria.getLocalBodyCreationType()))
      {
        mav.setViewName("modify_temp_urban_change_coverage");
        mav.addObject("isDistrictLevel", Boolean.valueOf(ApplicationConstant.checkStateLBOnlyDisttWise(this.stateCode)));
      }
      else
      {
        mav.setViewName("modify_temp_change_coverage");
        if ((lbForm.getParentLocalBodyCode() != null) && (lbForm.getParentLocalBodyCode().intValue() != 0)) {
          clientParentCode = this.localBodyService.getLBCodeByParentCode(lbForm.getParentLocalBodyCode());
        }
      }
      mav.addObject("clientParentCode", clientParentCode);
      this.localBodyUtil.setMapAttributes(draftedLBDetails, mav);
      setSessionAttributes(request.getSession(), attributes);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/deleteSingleDraftedChangeCoverageLB"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView deleteSingleDraftedChangeCoverageLB(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("tempChangeCoverageCode") Integer tempLocalBodyCode, @RequestParam("tempLocalBodyCreationType") String tempLocalBodyCreationType)
  {
    try
    {
      Boolean isdeletedStatus = this.localBodyService.publishOrDeleteDraftToTransactionChangeCoverage(tempLocalBodyCode, Boolean.TRUE.booleanValue(), request);
      if (isdeletedStatus.booleanValue()) {
        model.addAttribute("publishMessage", "Drafted Local Body Deleted Successfully.");
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(tempLocalBodyCreationType), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return new ModelAndView(errorHandler(request, ex));
    }
  }
 
  @RequestMapping(value={"/publishSinleDraftedChangeCoverageLB"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView publishSinleDraftedChangeCoverageLB(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("tempChangeCoverageCode") Integer tempLocalBodyCode, @RequestParam("tempLocalBodyCreationType") String tempLocalBodyCreationType)
  {
    try
    {
      Boolean isPublishedStatus = this.localBodyService.publishOrDeleteDraftToTransactionChangeCoverage(tempLocalBodyCode, Boolean.FALSE.booleanValue(), request);
      if (isPublishedStatus.booleanValue()) {
        model.addAttribute("publishMessage", "Record Published Successfully.");
      }
      return this.localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(tempLocalBodyCreationType), this.stateCode, this.districtCode);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return new ModelAndView(errorHandler(request, ex));
    }
  }
 
  @RequestMapping(value={"/callMapCoveredAreaPublishedLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView callMapCoveredAreaPublishedLocalBody(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("criteriaLocalBodies") ManageLBDetails criteria)
  {
    ModelAndView mav = new ModelAndView("restructued_map_covered_localbody_lb_urban");
    try
    {
      String localBodyCreationType = criteria.getLocalBodyCreationType();
      LocalBodyForm lbForm = this.localBodyService.getLocalBodyFormObject(criteria.getLocalBodyCode());
      lbForm.setLocalBodyCreationType(localBodyCreationType);
      Map<String, Object> modifyTypeAttributes = this.localBodyService.getLBDetailsForModifyType(lbForm.getLocalBodyTypeCode(), this.stateCode);
      mav.addObject("localBodyForm", lbForm);
      this.localBodyUtil.setMapAttributes(modifyTypeAttributes, mav);
      this.localBodyUtil.setMapAttributes(this.localBodyService.mapCoveredAreaDefaultDetails(lbForm), mav);
      if (lbForm.getLocalBodyTypeCode().intValue() == 3)
      {
        mav.addObject("mappedHabitationList", this.villageService.getMappedHabitationList(criteria.getLocalBodyCode()));
        mav.addObject("habitationConfigration", this.villageService.getHabitationConfigrationLocalbody(this.stateCode));
      }
      mav.addObject("isDistrictLevel", Boolean.valueOf(true));
      mav.addObject("LOCAL_BODY_CREATION_TYPE", localBodyCreationType);
      if (!LocalBodyConstant.LB_URBAN.toString().equalsIgnoreCase(localBodyCreationType)) {
        mav.setViewName("restructued_map_covered_localbody");
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/mapCoverageLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView changeMapCoverageLocalBody(HttpServletRequest request, Model model, @ModelAttribute("localBodyForm") LocalBodyForm localBodyForm, BindingResult binding)
    throws Exception
  {
    ModelAndView mav = new ModelAndView();
    try
    {
      if ((localBodyForm.getContributingUnmappedDistrictCodes() != null) && (localBodyForm.getContributingUnmappedDistrictCodes().contains("_true_"))) {
        localBodyForm.setContributingUnmappedDistrictCodes(localBodyForm.getContributingUnmappedDistrictCodes().replace("_true_", "_"));
      }
      if ((localBodyForm.getContributingUnmappedDistrictCodes() != null) && (localBodyForm.getContributingUnmappedDistrictCodes().contains("false"))) {
        localBodyForm.setContributingUnmappedDistrictCodes(localBodyForm.getContributingUnmappedDistrictCodes().replace("_false_", "_"));
      }
      if ((localBodyForm.getContributingUnmappedSubDistrictCodes() != null) && (localBodyForm.getContributingUnmappedSubDistrictCodes().contains("_true_"))) {
        localBodyForm.setContributingUnmappedSubDistrictCodes(localBodyForm.getContributingUnmappedSubDistrictCodes().replace("_true_", "_"));
      }
      if ((localBodyForm.getContributingUnmappedSubDistrictCodes() != null) && (localBodyForm.getContributingUnmappedSubDistrictCodes().contains("_false_"))) {
        localBodyForm.setContributingUnmappedSubDistrictCodes(localBodyForm.getContributingUnmappedSubDistrictCodes().replace("_false_", "_"));
      }
      if ((localBodyForm.getContributingUnmappedVillageCodes() != null) && (localBodyForm.getContributingUnmappedVillageCodes().contains("_true_"))) {
        localBodyForm.setContributingUnmappedVillageCodes(localBodyForm.getContributingUnmappedVillageCodes().replace("_true_", "_"));
      }
      if ((localBodyForm.getContributingUnmappedVillageCodes() != null) && (localBodyForm.getContributingUnmappedVillageCodes().contains("_false_"))) {
        localBodyForm.setContributingUnmappedVillageCodes(localBodyForm.getContributingUnmappedVillageCodes().replace("_false_", "_"));
      }
      if ((localBodyForm.getHeadQuarterCode() != null) && (localBodyForm.getHeadQuarterCode().contains("_true"))) {
        localBodyForm.setHeadQuarterCode(localBodyForm.getHeadQuarterCode().replace("_true", ""));
      }
      if ((localBodyForm.getHeadQuarterCode() != null) && (localBodyForm.getHeadQuarterCode().contains("_false"))) {
        localBodyForm.setHeadQuarterCode(localBodyForm.getHeadQuarterCode().replace("_false", ""));
      }
      localBodyForm.setValidationAction(LocalBodyConstant.PROCESS_MAP_COVERAGE.toString());
      this.lbFormValidator.validate(localBodyForm, binding, (Boolean)request.getSession().getAttribute("isGovernmentOrderUpload"));
      if (binding.hasErrors())
      {
        String annotation = null;
        if (LocalBodyConstant.LB_URBAN.toString().equals(localBodyForm.getLocalBodyCreationType())) {
          annotation = "restructued_map_covered_localbody_lb_urban";
        } else {
          annotation = "restructued_map_covered_localbody";
        }
        mav.addObject("localBodyForm", localBodyForm);
        this.localBodyUtil.setMapAttributes(this.localBodyService.mapCoveredAreaDefaultDetails(localBodyForm), mav);
        mav.addObject("isDistrictLevel", Boolean.valueOf(ApplicationConstant.checkStateLBOnlyDisttWise(this.stateCode)));
        mav.setViewName(annotation);
        mav.addObject("checkedServerValidation", Boolean.TRUE);
        return mav;
      }
      String updateStatusMsg = this.localBodyService.updateMappedCoveredArea(localBodyForm, request);
      if ((localBodyForm.getLocalBodyTypeCode().intValue() == 3) && (((localBodyForm.getContributingHabiationCodes() != null) && (!"".equals(localBodyForm.getContributingHabiationCodes()))) || ((localBodyForm.getAvilableHabitation() != null) && (!"".equals(localBodyForm.getAvilableHabitation()))))) {
        this.villageService.saveHabitationLocalbody(localBodyForm);
      }
      mav.setViewName("success");
      mav.addObject("msgid", updateStatusMsg);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/updateGISBoundaries"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView updateGPGISBoundaries(HttpServletRequest request, Model model, @ModelAttribute("criteriaLocalBodies") ManageLBDetails manageLBDetails, BindingResult binding)
    throws Exception
  {
    ModelAndView mav = new ModelAndView("updateGISBoundaries");
    try
    {
      model.addAttribute("localBodyTypeList", this.localBodyService.buildLBTypeList(this.stateCode));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/updateGISBoundaries"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView updateGPGISBoundariesPost(HttpServletRequest request, Model model, @ModelAttribute("criteriaLocalBodies") ManageLBDetails criteria, BindingResult binding)
    throws Exception
  {
    ModelAndView mav = new ModelAndView("updateGISBoundaries");
    try
    {
      String lbLevelCodes = criteria.getLocalBodyLevelCodes();
      List<ManageLBDetails> publishedLocalBodies = this.localBodyService.getLocalBodiesForManage("P", Integer.valueOf(3), this.stateCode, lbLevelCodes, this.districtCode);
      mav.addObject("publishedLocalBodies", publishedLocalBodies);
      mav.addObject("searchResult", Boolean.TRUE);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/callLocalBodyCorrection"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView callLocalBodyCorrection(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("criteriaLocalBodies") ManageLBDetails criteria)
  {
    ModelAndView mav = new ModelAndView("call_Local_Body_Correction");
    try
    {
      Integer sizeValue = null;
      LocalBodyForm lbForm = this.localBodyService.getLocalBodyFormObject(criteria.getLocalBodyCode());
      List<Object[]> getGovtOrder = this.localBodyService.getOrderCodeThroughLblc(criteria.getLocalBodyCode());
      List<LocalBodyDetailDto> getPreviousAttachedFiles = this.localBodyService.getPreviousAttachedFilesbyLblc(criteria.getLocalBodyCode());
      if ((getGovtOrder != null) && (getGovtOrder.size() > 0))
      {
        Object[] OBJ1 = (Object[])getGovtOrder.get(0);
        Date getDate = (Date)OBJ1[0];
        BigInteger attachmentId = (BigInteger)OBJ1[2];
        Integer orderCode = (Integer)OBJ1[1];
        Integer govtWiseId = (Integer)OBJ1[3];
       
        mav.addObject("orderCode", orderCode);
        mav.addObject("getEffective", getDate);
        mav.addObject("attachmentId", attachmentId);
        mav.addObject("govtWiseId", govtWiseId);
      }
      if ((getPreviousAttachedFiles != null) && (((LocalBodyDetailDto)getPreviousAttachedFiles.get(0)).getFileSize().intValue() > 0))
      {
        sizeValue = ((LocalBodyDetailDto)getPreviousAttachedFiles.get(0)).getFileSize();
       
        lbForm.setOrderNo(((LocalBodyDetailDto)getPreviousAttachedFiles.get(0)).getOrderNo().toString());
        lbForm.setOrderDate(((LocalBodyDetailDto)getPreviousAttachedFiles.get(0)).getOrderDate());
        lbForm.setGazPubDate(((LocalBodyDetailDto)getPreviousAttachedFiles.get(0)).getGazPubDate());
        mav.addObject("getPreviousAttachedFiles", getPreviousAttachedFiles);
        mav.addObject("sizeOfFile", sizeValue);
      }
      lbForm.setLocalBodyCreationType(criteria.getLocalBodyCreationType());
      mav.addObject("localBodyForm", lbForm);
      
     Date dt =lbForm.getiParamEffectiveDate();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      String dob=df.format(dt);
     
      mav.addObject("date", dob);
      mav.addObject("localBodyVersion", lbForm.getLocalBodyVersion());
      mav.addObject("minorVersion", lbForm.getMinorVersion());
      mav.addObject("previousNames", this.localBodyService.fetchPreviousNames(criteria.getLocalBodyCode()));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      mav = new ModelAndView(errorHandler(request, ex));
    }
    return mav;
  }
 
  @RequestMapping(value={"/modifyLocalBody"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView modifyLocalBodyCorrection(HttpServletRequest request, HttpSession session, Model model, @ModelAttribute("localBodyForm") LocalBodyForm localBodyForm, BindingResult result)
    throws IOException
  {
    ModelAndView mav = new ModelAndView("call_Local_Body_Correction");
    ModelAndView mavHome = new ModelAndView();
    try
    {
      LocalBodyForm listLocalBodyForm = localBodyForm;
      if (listLocalBodyForm != null)
      {
        LocalBodyForm element = listLocalBodyForm;
        Integer localBoadyCode = null;
        int operationCode = 4;
        char operation = 'M';
        char entityType = 'L';
       
        request.setAttribute("stateCode", this.stateCode);
        boolean delflag = false;
       
        Map<String, String> mapGovOrderConfig = this.govtOrderService.getGovtOrderConfiguration(this.stateCode.intValue(), operationCode, operation);
        Map<String, String> mapMapConfig = this.govtOrderService.getMapConfiguration(this.stateCode.intValue(), operationCode, entityType);
        String govtOrderConfig = (String)mapGovOrderConfig.get("govtOrder");
        String mapConfig = (String)mapMapConfig.get("mapUpload");
       
        localBodyForm.setOrderNo(element.getOrderNo().trim());
        localBodyForm.setOrderDate(element.getOrderDate());
       
        localBodyForm.setGazPubDate(element.getGazPubDate());
        if (session.getAttribute("mandatoryFlag") != null) {
          session.removeAttribute("mandatoryFlag");
        }
        if (!localBodyForm.getGazettePublicationUpload().isEmpty())
        {
          List<CommonsMultipartFile> file = localBodyForm.getGazettePublicationUpload();
         
          String filename = ((CommonsMultipartFile)file.get(0)).getOriginalFilename();
          String filenameWithoutExtnsn = FilenameUtils.removeExtension(filename);
          String extnsn = FilenameUtils.getExtension(filename);
          AttachmentMaster attachmentMaster = this.govtOrderService.getUploadFileConfigurationDetails(1L);
          String path = attachmentMaster.getFileLocation();
         
          AttachmentMaster attachmentMasterFileMove = this.govtOrderService.getUploadFileConfigurationDetails(8L);
          String pathFileMove = attachmentMasterFileMove.getFileLocation();
          if (((CommonsMultipartFile)file.get(0)).getSize() > 5242880L)
          {
            mav.addObject("errorMessage", "kindly upload file less than 5 Mb");
            mav = new ModelAndView("redirect:callLocalBodyCorrection.htm?");
          }
          localBoadyCode = localBodyForm.getLocalBodyCode();
         
          List<LocalBodyDetailDto> getPreviousAttachedFiles = this.localBodyService.getPreviousAttachedFilesbyLblc(localBoadyCode);
          if ((getPreviousAttachedFiles != null) && (getPreviousAttachedFiles.size() > 0))
          {
            String fileLocation = ((LocalBodyDetailDto)getPreviousAttachedFiles.get(0)).getFileLocation();
            fileLocation = fileLocation.replace("\\\\", "/");
            try
            {
              InputStream inStream = null;
              OutputStream outStream = null;
             
              File existingStoredFile = new File(fileLocation, ((LocalBodyDetailDto)getPreviousAttachedFiles.get(0)).getFileTimestamp());
              String fileToMove = pathFileMove;
             
              File fileMoveToTrunk = new File(fileToMove, ((LocalBodyDetailDto)getPreviousAttachedFiles.get(0)).getFileTimestamp());
              inStream = new FileInputStream(existingStoredFile);
              outStream = new FileOutputStream(fileMoveToTrunk);
              System.out.println("fileToMove +++++++++++++++++++++++++" + fileToMove);
              byte[] buffer = new byte['?'];
              int length;
              while ((length = inStream.read(buffer)) > 0)
              {
                 
                outStream.write(buffer, 0, length);
              }
              inStream.close();
              outStream.close();
              existingStoredFile.delete();
              System.out.println(" existingStoredFile.delete(); +++++++++++++++++++++++++" + existingStoredFile);
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
          }
          if ((localBodyForm.getGazettePublicationUpload() != null) && (localBodyForm.getGazettePublicationUpload().size() > 0))
          {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFilename = filenameWithoutExtnsn + "_" + dateFormat.format(date) + "." + extnsn;
           
            byte[] bytes = ((CommonsMultipartFile)file.get(0)).getBytes();
            File dir = new File(path);
            String freshFileLocation = path;
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(dir + "/" + newFilename));
            stream.write(bytes);
            stream.close();
           
            localBodyForm.setOrderNo(element.getOrderNo().trim());
            localBodyForm.setOrderDate(element.getOrderDate());
           
            localBodyForm.setGazPubDate(element.getGazPubDate());
           
            localBodyForm.setOrderFileContentType(((CommonsMultipartFile)file.get(0)).getContentType());
            localBodyForm.setFileLocation(freshFileLocation);
           
            Long fileSize = Long.valueOf(((CommonsMultipartFile)localBodyForm.getGazettePublicationUpload().get(0)).getSize() / 1024L);
            localBodyForm.setOrderFileSize(fileSize);
           
            localBodyForm.setNewFilename(newFilename);
          }
          Boolean dateSaveSuccessFully = this.localBodyService.saveLocalBodyForm(localBodyForm);
          System.out.println(" dateSaveSuccessFully +++++++++++++++++++++++++" + dateSaveSuccessFully);
          System.out.println(" LocalBodyCode +++++++++++++++++++++++++" + localBodyForm.getLocalBodyCode());
          System.out.println(" orderCode +++++++++++++++++++++++++" + localBodyForm.getOrderCode());
          if (dateSaveSuccessFully.booleanValue())
          {
            mavHome.setViewName("success");
            StringBuilder builder = new StringBuilder("Correction of  government order of LocalBody ( ");
            builder.append(localBodyForm.getLocalBodyNameEnglish());
            builder.append(" )  is  successfully.");
            mavHome.addObject("msgid", builder);
          }
        }
      }
    }
    catch (Exception e)
    {
      IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
      String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", Integer.valueOf(1), e);
      return new ModelAndView(redirectPath);
    }
    return mavHome;
  }
  
  /* 
	 * Change Map Covered Area of Published Local Body Flow -
	 * Map Covered Area Local Body based on Local Government Body Code but no draft facility. 	
	 */
	
	@RequestMapping(value = "/callChangeCoverageTypePublishedLocalBody", method = RequestMethod.POST)
	public ModelAndView callChangeCoverageTypePublishedLocalBody(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("criteriaLocalBodies") ManageLBDetails criteria) {
		ModelAndView mav = new ModelAndView("restructued_change_coverage_type");
		try {
			String localBodyCreationType = criteria.getLocalBodyCreationType();
			LocalBodyForm lbForm = localBodyService.getLocalBodyFormObject(criteria.getLocalBodyCode());
			lbForm.setLocalBodyCreationType(localBodyCreationType);
			java.util.Map<String, Object> modifyTypeAttributes = localBodyService.getLBDetailsForModifyType(lbForm.getLocalBodyTypeCode(), stateCode);
			mav.addObject("localBodyForm", lbForm);
			localBodyUtil.setMapAttributes(modifyTypeAttributes, mav);
			localBodyUtil.setMapAttributes(localBodyService.mapCoveredAreaDefaultDetails(lbForm), mav);
			if(lbForm.getLocalBodyTypeCode()==3){
				mav.addObject("mappedHabitationList", villageService.getMappedHabitationList(criteria.getLocalBodyCode()));
				mav.addObject("habitationConfigration",villageService.getHabitationConfigrationLocalbody(stateCode));
			}
			
			mav.addObject("isDistrictLevel", true);
			mav.addObject("LOCAL_BODY_CREATION_TYPE", localBodyCreationType);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	/* 
	 * Change Map Covered Area of Published Local Body Flow -
	 * Map Covered Area Local Body based on Local Government Body Code but no draft facility. 	
	 */
	
	@RequestMapping(value = "/saveChangeCoverageTypePublishedLocalBody", method = RequestMethod.POST)
	public ModelAndView saveChangeCoverageTypePublishedLocalBody(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("localBodyForm") LocalBodyForm localBodyForm) {
		ModelAndView mav = new ModelAndView("success");
		try {
			localBodyService.saveLocalbodyCoverageType(localBodyForm.getLocalBodyCode(), localBodyForm.getChangeCoverageTypeLRList(),userId);
			mav.addObject("msgid", "Coverage Type updated successfully ");
		} catch (Exception ex) {
			ex.printStackTrace();
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/modifyLbChangeEffectiveDate", method = RequestMethod.POST)
	public ModelAndView modifyVillageChangeEffectiveDate(@ModelAttribute("criteriaLocalBodies") ManageLBDetails criteria, Model model, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("CHANGE_EFFECTIVE_DATE_OF_LOCALBODY");
		try {
			model.addAttribute("lbCode",criteria.getLocalBodyCode());
			model.addAttribute("curDate",new Date());
			
			//model.addAttribute("villageDetail",villageService.getVillageDetailsModify(modifyVillageCmd.getVillageId()));
				} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	
	@RequestMapping(value = "/saveEffectiveDateLB", headers="Accept=application/json", method = RequestMethod.POST)
	public @ResponseBody Response saveEffectiveDate(@RequestBody EffectiveDateList getEntityEffectiveDateList,HttpServletRequest request) {
		HttpSession httpsession= request.getSession();
		setGlobalParams(httpsession);
		return localBodyService.saveEffectiveDateEntityLB(getEntityEffectiveDateList,userId.intValue());
	}
	
	
}