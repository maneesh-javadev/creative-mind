package in.nic.pes.lgd.validator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import in.nic.pes.lgd.common.SubAttachmentHandler;
import in.nic.pes.lgd.service.GovernmentOrderService;
import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;


public class CommonValidatorImpl {
	private static final Logger log = Logger.getLogger(CommonValidatorImpl.class);
	@Autowired
	private GovernmentOrderService govtOrderService;
	
	public void isValidMime(Errors errors, HttpServletRequest request, List<CommonsMultipartFile> file) {
		try {
			AddAttachmentBean addAttachmentBean2 = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			List<CommonsMultipartFile> attachedFile = file;
			// Tanuj changed
			if (attachedFile != null) {
				if (attachedFile.get(0).getSize() > 0) {
					addAttachmentBean2 = govtOrderService.getAttachmentBeanforMap(attachedFile, null, request);

					if (addAttachmentBean2 != null) {
					String validateAttachment1 = attachmentHandler.validateAttachment(addAttachmentBean2);
					attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
						if (!validateAttachment1.equalsIgnoreCase("validationSuccessFullyDone")) {
						request.setAttribute("validationErrorOne1", validateAttachment1);
							errors.rejectValue("attachFile1", validateAttachment1);
					}
					} else {
					attachmentHandler.deleteMetaDataINFileSystem(addAttachmentBean2);
				}	
			 }
			}
			if(!errors.hasErrors()){
			isValidAttachmentFiel1(errors,request,file);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			errors.rejectValue("attachFile1", "errorMessage.addAttachment.allowedFileMimeType");
		}
	}
	//To Check the Validation of Attachment File While Creating Village. 
	public void isValidMimeForVilCreate(Errors errors, HttpServletRequest request, List<CommonsMultipartFile> file) { // NO_UCD (use default)
		try {
			AddAttachmentBean addAttachmentBean2 = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			List<CommonsMultipartFile> attachedFile = file;
			// Tanuj changed
			if (attachedFile != null) {
				if (attachedFile.get(0).getSize() > 0) {
					addAttachmentBean2 = govtOrderService.getAttachmentBeanforMap(attachedFile, null, request);

					if (addAttachmentBean2 != null) {
					String validateAttachment1 = attachmentHandler.validateAttachment(addAttachmentBean2);
					attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
						if (!validateAttachment1.equalsIgnoreCase("validationSuccessFullyDone")) {
						request.setAttribute("validationErrorOne1", validateAttachment1);
							errors.rejectValue("attachFile2", validateAttachment1);
					}
					} else {
					attachmentHandler.deleteMetaDataINFileSystem(addAttachmentBean2);
				}	
			 }
			}
			if(!errors.hasErrors()){
			isValidAttachmentFiel2(errors,request,file);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			errors.rejectValue("attachFile1", "errorMessage.addAttachment.allowedFileMimeType");
		}
	}
	
	public void isValidMimeforMapLandRegion(Errors errors, HttpServletRequest request, List<CommonsMultipartFile> file)
	{
		
		
	}
	
	public void isValidMimeforGovOrderLandRegion(Errors errors, HttpServletRequest request, List<CommonsMultipartFile> file)
	{
		try 
		{
			AddAttachmentBean addAttachmentBean2 = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			List<CommonsMultipartFile> attachedFile = file;
			//Tanuj changed
			
			if(attachedFile!=null)
			{
			 if(attachedFile.get(0).getSize() > 0)
			 {
				addAttachmentBean2 = govtOrderService.getAttachmentBeanforGovLandregion(attachedFile, null,request);

				if (addAttachmentBean2 != null) 
				{
					String validateAttachment1 = attachmentHandler.validateAttachment(addAttachmentBean2);
					attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
					if (!validateAttachment1.equalsIgnoreCase("validationSuccessFullyDone")) 
					{
						request.setAttribute("validationErrorOne1", validateAttachment1);
						errors.rejectValue("attachFile1",validateAttachment1 );
					}
				}
				else
				{	
					attachmentHandler.deleteMetaDataINFileSystem(addAttachmentBean2);
				}	
			 }
			}
			if(!errors.hasErrors()){
			isValidAttachmentFiel1(errors,request,file);
			}
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
			errors.rejectValue("attachFile1", "errorMessage.addAttachment.allowedFileMimeType");
		}
	}
	
	public void isValidMimeforState(Errors errors, HttpServletRequest request, List<CommonsMultipartFile> file,HttpSession httpSession)
	{
		try 
		{
			AddAttachmentBean addAttachmentBean2 = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			List<CommonsMultipartFile> attachedFile = file;
			//Tanuj changed
			if(attachedFile!=null)
			{
			 if(attachedFile.get(0).getSize() > 0)
			 {
				addAttachmentBean2 = govtOrderService.getAttachmentBeanforMap(attachedFile, null,request);

				if (addAttachmentBean2 != null) 
				{
					String validateAttachment1 = attachmentHandler.validateAttachment(addAttachmentBean2);
					attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
					if (!validateAttachment1.equalsIgnoreCase("validationSuccessFullyDone")) 
					{
						request.setAttribute("validationErrorOne1", validateAttachment1);
						httpSession.setAttribute("validationErrorOne1", validateAttachment1);
						errors.rejectValue("attachFile1", "errorMessage.addAttachment.allowedFileMimeType");
					}
				}
				else
				{	
					attachmentHandler.deleteMetaDataINFileSystem(addAttachmentBean2);
				}	
			 }
			}
			if(!errors.hasErrors()){
			isValidAttachmentFiel1(errors,request,file);	
			}
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
			errors.rejectValue("attachFile1", "errorMessage.addAttachment.allowedFileMimeType");
		}
	}

	public void isValidMimeState(Errors errors, HttpServletRequest request, List<CommonsMultipartFile> file)
	{
		try 
		{
			AddAttachmentBean addAttachmentBean2 = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			List<CommonsMultipartFile> attachedFile = file;
			 if(attachedFile.get(0).getSize() > 0)
			 {
				addAttachmentBean2 = govtOrderService.getAttachmentBeanforMapState(attachedFile, null,request);

				if (addAttachmentBean2 != null) 
				{
					String validateAttachment1 = attachmentHandler.validateAttachment(addAttachmentBean2);
					attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
					if (!validateAttachment1.equalsIgnoreCase("validationSuccessFullyDone")) 
					{
						request.setAttribute("validationErrorOne1", validateAttachment1);
						errors.rejectValue("attachFile1", "errorMessage.addAttachment.allowedFileMimeType");
					}
				}
				else
				{	
					attachmentHandler.deleteMetaDataINFileSystem(addAttachmentBean2);
				}	
			 }
			 if(!errors.hasErrors()){
				isValidAttachmentFiel1(errors,request,file);
			 }
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
			errors.rejectValue("attachFile1", "errorMessage.addAttachment.allowedFileMimeType");
		}
	}
	
	public void isValidMimeLB(Errors errors, HttpServletRequest request, List<CommonsMultipartFile> file,HttpSession session)
	{
		try 
		{
			AddAttachmentBean addAttachmentBean2 = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			List<CommonsMultipartFile> attachedFile = file;
			 if(attachedFile.get(0).getSize() > 0)
			 {
				addAttachmentBean2 = govtOrderService.getAttachmentBeanforMapState(attachedFile, null,request);

				if (addAttachmentBean2 != null) 
				{
					String validateAttachment1 = attachmentHandler.validateAttachment(addAttachmentBean2);
					attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
					if (!validateAttachment1.equalsIgnoreCase("validationSuccessFullyDone")) 
					{
						request.setAttribute("validationErrorOne1", validateAttachment1);
						session.setAttribute("validationErrorOne1", validateAttachment1);
						errors.rejectValue("attachFile2", "errorMessage.addAttachment.allowedFileMimeTypeLB");
					}
				}
				else
				{	
					attachmentHandler.deleteMetaDataINFileSystem(addAttachmentBean2);
				}	
			 }
			 if(!errors.hasErrors()){
				isValidAttachmentFiel2(errors,request,file);
			 }
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
			errors.rejectValue("attachFile2", "errorMessage.addAttachment.allowedFileMimeType");
		}
	}

// TODO Remove unused code found by UCDetector
// 	public void isValidMimeLBforView(Errors errors, HttpServletRequest request, List<CommonsMultipartFile> file,HttpSession session)
// 	{
// 		try 
// 		{
// 			AddAttachmentBean addAttachmentBean2 = null;
// 			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
// 			List<CommonsMultipartFile> attachedFile = file;
// 			 if(attachedFile.get(0).getSize() > 0)
// 			 {
// 				addAttachmentBean2 = govtOrderService.getAttachmentBeanforMapState(attachedFile, null,request);
// 
// 				if (addAttachmentBean2 != null) 
// 				{
// 					String validateAttachment1 = attachmentHandler.validateAttachment(addAttachmentBean2);
// 					attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
// 					if (!validateAttachment1.equalsIgnoreCase("validationSuccessFullyDone")) 
// 					{
// 						request.setAttribute("validationErrorOne1", validateAttachment1);
// 						session.setAttribute("validationErrorOne1", validateAttachment1);
// 						errors.rejectValue("attachFile1", "errorMessage.addAttachment.allowedFileMimeTypeLB");
// 					}
// 				}
// 				else
// 				{	
// 					attachmentHandler.deleteMetaDataINFileSystem(addAttachmentBean2);
// 				}	
// 			 }
// 			
// 		}
// 		catch (Exception e) 
// 		{
// 			log.debug("Exception" + e);
// 			errors.rejectValue("attachFile2", "errorMessage.addAttachment.allowedFileMimeType");
// 		}
// 	}
	
	
	public void isValidMimeLBforViewMLB(Errors errors, HttpServletRequest request, List<CommonsMultipartFile> file,HttpSession session)
	{
		try 
		{
			AddAttachmentBean addAttachmentBean2 = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			List<CommonsMultipartFile> attachedFile = file;
			 if(attachedFile.get(0).getSize() > 0)
			 {
				addAttachmentBean2 = govtOrderService.getAttachmentBeanforMapState(attachedFile, null,request);

				if (addAttachmentBean2 != null) 
				{
					String validateAttachment1 = attachmentHandler.validateAttachment(addAttachmentBean2);
					attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
					if (!validateAttachment1.equalsIgnoreCase("validationSuccessFullyDone")) 
					{
						request.setAttribute("validationErrorOne1", validateAttachment1);
						session.setAttribute("validationErrorOne1", validateAttachment1);
						errors.rejectValue("attachFile1", "errorMessage.addAttachment.allowedFileMimeTypeLB");
					}
				}
				else
				{	
					attachmentHandler.deleteMetaDataINFileSystem(addAttachmentBean2);
				}	
			 }
			 if(!errors.hasErrors()){
				isValidAttachmentFiel1(errors,request,file);
			 }
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
			errors.rejectValue("attachFile1", "errorMessage.addAttachment.allowedFileMimeType");
		}
	}
	
	
	public void isValidMimeForDraftsubdistrict(Errors errors, HttpServletRequest request, List<CommonsMultipartFile> file,HttpSession session)
	{
		try 
		{
			
			 isValidGazettePublicationUpload(errors,request,file);
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
			errors.rejectValue("attachFile1", "errorMessage.addAttachment.allowedFileMimeType");
		}
	}
	
	public void isValidAttachmentFiel1(Errors errors, HttpServletRequest request, List<CommonsMultipartFile> file) throws Exception{
		if (file != null && !file.isEmpty()) {
			//AttachmentMaster master = localBodyService.getUploadFileConfigurationDetails(fileMasterId);
			AddAttachmentBean attachmentBean = new AddAttachmentBean();
			//attachmentBean.setUploadLocation(master.getFileLocation());
			attachmentBean.setCurrentlyUploadFileList(file);
			SubAttachmentHandler attachmentHandler = new SubAttachmentHandler();
			String validateAttachment = attachmentHandler.validateAttachment(attachmentBean);
			List<AttachedFilesForm> metaInfoList = attachmentHandler.getMetaDataListOfFiles(attachmentBean);
			if (!(metaInfoList != null && "validationSuccessFullyDone".equals(validateAttachment))) {
				errors.rejectValue("attachFile1","errorMessage.addAttachment.allowedFileMimeType");
			}
		}
	}

	public void isValidAttachmentFiel2(Errors errors, HttpServletRequest request, List<CommonsMultipartFile> file) throws Exception{
		if (file != null && !file.isEmpty()) {
			//AttachmentMaster master = localBodyService.getUploadFileConfigurationDetails(fileMasterId);
			AddAttachmentBean attachmentBean = new AddAttachmentBean();
			//attachmentBean.setUploadLocation(master.getFileLocation());
			attachmentBean.setCurrentlyUploadFileList(file);
			SubAttachmentHandler attachmentHandler = new SubAttachmentHandler();
			String validateAttachment = attachmentHandler.validateAttachment(attachmentBean);
			List<AttachedFilesForm> metaInfoList = attachmentHandler.getMetaDataListOfFiles(attachmentBean);
			if (!(metaInfoList != null && "validationSuccessFullyDone".equals(validateAttachment))) {
				errors.rejectValue("attachFile2","errorMessage.addAttachment.allowedFileMimeType");
			}
		}
	}
	
	public void isValidGazettePublicationUpload(Errors errors, HttpServletRequest request, List<CommonsMultipartFile> file) throws Exception{
		if (file != null && !file.isEmpty()) {
			//AttachmentMaster master = localBodyService.getUploadFileConfigurationDetails(fileMasterId);
			AddAttachmentBean attachmentBean = new AddAttachmentBean();
			//attachmentBean.setUploadLocation(master.getFileLocation());
			attachmentBean.setCurrentlyUploadFileList(file);
			SubAttachmentHandler attachmentHandler = new SubAttachmentHandler();
			String validateAttachment = attachmentHandler.validateAttachment(attachmentBean);
			List<AttachedFilesForm> metaInfoList = attachmentHandler.getMetaDataListOfFiles(attachmentBean);
			if (!(metaInfoList != null && "validationSuccessFullyDone".equals(validateAttachment))) {
				errors.rejectValue("gazettePublicationUpload","errorMessage.addAttachment.allowedFileMimeType");
			}
		}
	}
	
	

}
