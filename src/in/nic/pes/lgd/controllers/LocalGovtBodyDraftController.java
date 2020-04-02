package in.nic.pes.lgd.controllers;
/**
 * @author Sushil Shakya
 * @Date 13-02-2013
 */
import in.nic.pes.common.menu.pojo.UserSelection;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.LocalBodyDetails;
import in.nic.pes.lgd.common.SubAttachmentHandler;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.forms.LocalGovtBodyStub;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.utils.ApplicationConstant;
import in.nic.pes.lgd.utils.WildCardFileFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class LocalGovtBodyDraftController { // NO_UCD (use default)
	private static final Logger log = Logger.getLogger(LocalGovtBodyDraftController.class);
	
	@Autowired
	private LocalGovtBodyService localGovtBodyService;
	
	@Autowired
	private EmailService emailService;
	
	public LocalGovtBodyDraftController() {
		super();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}
	
	@RequestMapping(value = "/saveAsDraft", method = RequestMethod.POST)
	public ModelAndView saveAsDraft(LocalGovtBodyForm localGovtBodyForm, BindingResult result, Model model, HttpServletRequest request, HttpSession httpsession) {
		try {
			String msg = null;
			UserSelection userSelection = (UserSelection) httpsession.getAttribute("USERS_SELECTION");
			if(userSelection != null) {
				LocalGovtBodyStub govtBodyStub = new LocalGovtBodyStub();
				govtBodyStub.setLocalBodyDetails(localGovtBodyForm.getLocalBodyDetails());
				List<CommonsMultipartFile> list = localGovtBodyForm.getAttachFile1();
				if(list != null && list.size() > 0) {
					AddAttachmentBean attachmentBean = new AddAttachmentBean();
					attachmentBean.setUploadLocation(ApplicationConstant.getDefaultFilePath());
					attachmentBean.setCurrentlyUploadFileList(localGovtBodyForm.getAttachFile1());
					SubAttachmentHandler attachmentHandler = new SubAttachmentHandler();
					String validateAttachment = attachmentHandler.validateAttachment(attachmentBean);
					List<AttachedFilesForm> metaInfoList  = attachmentHandler.getMetaDataListOfFiles(attachmentBean);
					
					if (metaInfoList != null && "validationSuccessFullyDone".equals(validateAttachment)) {
						govtBodyStub.setAttachedFilesList(metaInfoList);
						String saveAttachment = attachmentHandler.saveMetaDataINFileSystem(metaInfoList, attachmentBean);
						if("saveSuccessFully".equals(saveAttachment)) {
							AttachedFilesForm attachedFilesForm = metaInfoList.get(0);
							Attachment attachment = new Attachment();
							attachment.setFileLocation(attachmentBean.getUploadLocation().replaceAll("\\\\", "/")+attachedFilesForm.getFileTimestampName());
							attachment.setFileName(attachedFilesForm.getFileName());
							attachment.setFileTimestamp(attachedFilesForm.getFileTimestampName());
							attachment.setFileTitle(attachedFilesForm.getFileTitle());
							attachment.setFileSize(attachedFilesForm.getFileSize());
							attachment.setFileContentType(attachedFilesForm.getFileType());
							List<Attachment> attachlist = new ArrayList<Attachment>();
							attachlist.add(attachment);
							govtBodyStub.setListAttachments(attachlist);
							msg = "Government Order File uploaded successfully and ";
						}
					}	
				}
				
				govtBodyStub.setLgd_LBorderNo(localGovtBodyForm.getLgd_LBorderNo());
				govtBodyStub.setLgd_LBorderDate(localGovtBodyForm.getLgd_LBorderDate());
				govtBodyStub.setLgd_LBeffectiveDate(localGovtBodyForm.getLgd_LBeffectiveDate());
				govtBodyStub.setLgd_LBgazPubDate(localGovtBodyForm.getLgd_LBgazPubDate());
				govtBodyStub.setGovtOrderConfig(localGovtBodyForm.getGovtOrderConfig());
				govtBodyStub.setLocalGovtBodyForm(localGovtBodyForm);
				
				govtBodyStub.setUserId(userSelection.getUserId());
				govtBodyStub.setAssignUnit(userSelection.getAssignUnit());
				govtBodyStub.setCategoryCode(userSelection.getCategory());
				govtBodyStub.setIsActive(userSelection.getIsActive());
				govtBodyStub.setStateId(userSelection.getStateId());
				govtBodyStub.setLoginId(userSelection.getLoginId());
				
				FileOutputStream fileOut = new FileOutputStream(ApplicationConstant.getDefaultFilePath()+"\\"+localGovtBodyForm.getLocalBodyCode()+".ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(govtBodyStub);
				out.close();
				fileOut.close();
				msg += "Local Government Body "+localGovtBodyForm.getLocalBodyDetails().get(0).getLocalBodyCode()+" details are successfully saved in draft mode. This will be available up to 1 month for final update.";
			} else {
				msg = "Draft not saved successfully, because of Unauthorized access.";
			}
			ModelAndView mav = new ModelAndView("success");
			mav.addObject("msgid", msg);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	@RequestMapping(value = "/getDraftFileList", method = RequestMethod.GET)
	public ModelAndView getDraftedLGObjectList(Model model, HttpServletRequest request) { // NO_UCD (use default)
		FileInputStream inputStream = null;
		ObjectInputStream objIn = null;
		List<LocalBodyDetails> list = null;
		try {
			File[] files = WildCardFileFilter.getFiles(null);
			if (files != null && files.length > 0) {
				UserSelection userSelection = (UserSelection) request.getSession().getAttribute("USERS_SELECTION");
				list = new ArrayList<LocalBodyDetails>();
				for (int i = 0; i < files.length; i++) {
					inputStream = new FileInputStream(files[i]);
					objIn = new ObjectInputStream(inputStream);
					LocalGovtBodyStub govtBodyStub = (LocalGovtBodyStub) objIn.readObject();
					boolean flag = compareObjects(govtBodyStub, userSelection);
					if(flag) {
						List<LocalBodyDetails> bodyDetails = govtBodyStub.getLocalBodyDetails();
						if(bodyDetails != null && bodyDetails.size() > 0) {
							LocalBodyDetails details = bodyDetails.get(0);
							details.setGovtOrderConfig(govtBodyStub.getGovtOrderConfig());
							details.setLocalGovtBodyForm(govtBodyStub.getLocalGovtBodyForm());
							details.setListAttachments(govtBodyStub.getListAttachments());
							details.setAttachedFilesList(govtBodyStub.getAttachedFilesList());
							list.add(details);
							objIn.close();
							inputStream.close();
						}
					}
				}
				if(!list.isEmpty()) {
					model.addAttribute("fileList", list);
				}
			}
			return new ModelAndView("viewLocalBodyDraftList", model.asMap());
		} catch (Exception e) {
			log.error("Exception: ", e);
			if(!list.isEmpty()) {
				model.addAttribute("fileList", list);
				return new ModelAndView("viewLocalBodyDraftList", model.asMap());
			}
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	private boolean compareObjects(LocalGovtBodyStub govtBodyStub, UserSelection userSelection) {
		boolean flag = false;
		if(govtBodyStub != null && userSelection != null) {
			if((userSelection.getUserId().longValue() == govtBodyStub.getUserId().longValue()) && (userSelection.getAssignUnit().intValue() == govtBodyStub.getAssignUnit().intValue()) && (userSelection.getStateId().intValue() == govtBodyStub.getStateId().intValue())) {
				flag = true;
			}
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/viewDraft", method = RequestMethod.POST)
	public ModelAndView viewDraft(Model model, HttpServletRequest request) {
		try {
			int objId = Integer.parseInt(request.getParameter("hidObjId"));
			List<LocalBodyDetails> list =  (List<LocalBodyDetails>) request.getSession().getAttribute("fileList");
			LocalGovtBodyForm localGovtBodyForm = null;
			ModelAndView mav = new ModelAndView("modify_govt_local_bodyforname");
			if(list != null && list.size() > 0) {
				LocalBodyDetails tmpObj = list.get(objId-1);
				list.clear();
				list.add(tmpObj);
				localGovtBodyForm = tmpObj.getLocalGovtBodyForm();
				request.setAttribute("attachmentList", tmpObj.getListAttachments());
				mav.addObject("govtOrderConfig", tmpObj.getGovtOrderConfig());
				mav.addObject("isFromDraft", true);
				request.getSession().setAttribute("attachedList", tmpObj.getAttachedFilesList());
				request.getSession().removeAttribute("fileList");
			}
			localGovtBodyForm.setLocalBodyDetails(list);
			mav.addObject("hideMap", true);
			mav.addObject("localGovtBodyForm", localGovtBodyForm);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	@RequestMapping(value = "/deleteDraft", method = RequestMethod.POST)
	public ModelAndView deleteDraft(Model model, HttpServletRequest request) { // NO_UCD (use default)
		FileInputStream inputStream = null;
		ObjectInputStream objIn = null;
		try {
			int objId = Integer.parseInt(request.getParameter("hidObj"));
			File[] files = WildCardFileFilter.getFiles(objId+"");
			String msg = null;
			if (files != null && files.length > 0) {
				UserSelection userSelection = (UserSelection) request.getSession().getAttribute("USERS_SELECTION");
				for (int i = 0; i < files.length; i++) {
					inputStream = new FileInputStream(files[i]);
					objIn = new ObjectInputStream(inputStream);
					LocalGovtBodyStub govtBodyStub = (LocalGovtBodyStub) objIn.readObject();
					objIn.close();
					inputStream.close();
					boolean flag = compareObjects(govtBodyStub, userSelection);
					if(flag) {
						File file = files[i];
						boolean isDeleted = file.delete();
						msg = "Drafted file deleted successfully ";
						log.info("Drafted file deleted.");
						List<Attachment> attachList = govtBodyStub.getListAttachments();
						if(attachList != null && attachList.size() > 0) {
							for (Attachment attachment : attachList) {
								if(isDeleted) {
									File[] govOrderFile = WildCardFileFilter.getFiles(attachment.getFileTimestamp());
									if(govOrderFile != null && govOrderFile.length > 0) {
										boolean bool = govOrderFile[0].delete();
										if(bool) {
											msg += ", attached government order is also deleted successfully.";
											log.info("govtOrder file also deleted.");
										}
									}
								}
							}
						}
					}
				}
			}
			model.addAttribute("msg", msg);
			ModelAndView modelAndView = getDraftedLGObjectList(model, request);
			return modelAndView;
		} catch (Exception e) {
	
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	/**
	 * This method for save from draft mode. Not using  
	 */
	@RequestMapping(value = "/saveDraftDetail", method = RequestMethod.POST)
	public ModelAndView saveDraftDetail(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		UserSelection userSelection = (UserSelection) request.getSession().getAttribute("USERS_SELECTION");
		try {
			String check = localGovtBodyService.saveLocalBodyDraft(localGovtBodyForm,request, userSelection);
			String[] val=check.split(",");
			
			if (check != null) {
				if (localGovtBodyForm.getLbType() == 'P') {
					int t_code = Integer.parseInt(val[0]);
					char t_type = 'R';
					EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
					est.start();
				}

				if (localGovtBodyForm.getLbType() == 'T') {
					int t_code = Integer.parseInt(val[0]);
					char t_type = 'T';
					EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
					est.start();
				}
				
				if (localGovtBodyForm.getLbType() == 'U') {
					int t_code = Integer.parseInt(val[0]);
					char t_type = 'U';
					EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
					est.start();
				}
				String aMessage = "Modify Local Gov. Body Name is successfully updated. Local Body Code is " 	+ localGovtBodyForm.getLocalBodyDetails().get(0).getLocalBodyCode();
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
}