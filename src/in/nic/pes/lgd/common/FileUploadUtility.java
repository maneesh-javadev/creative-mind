package in.nic.pes.lgd.common;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.forms.AssemblyForm;
import in.nic.pes.lgd.forms.BlockForm;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LBFreezeForm;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.forms.OrgLocatedAtLevelsForm;
import in.nic.pes.lgd.forms.ParliamentForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.validator.CommonValidatorImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

public class FileUploadUtility {
	private static final Logger log = Logger.getLogger(FileUploadUtility.class);
	@Autowired
	private GovernmentOrderService govtOrderService;

	@Autowired
	private CommonValidatorImpl commonValidator;

	public List<AttachedFilesForm> ValidateAndUploadFileGovtOrder(HttpServletRequest request, GovernmentOrderForm object, BindingResult result) {
		boolean validFlag = true;
		GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentBean(govtOrderForm, request, result);
		SubAttachmentHandler attachmentHandlerOne = new SubAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		String validateAttachmentOne = "";
		try {
			validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
			commonValidator.isValidMime(result, request, govtOrderForm.getAttachFile1());
			if (result.hasErrors()) {
				validFlag = false;
			}
			if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFiles(addAttachmentBean);
			if (!validFlag) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	/**
	 * To validate Government Order Attachment Details in Village Form.
	 */
	public List<AttachedFilesForm> ValidateAndUploadFileGovtOrderVillage(HttpServletRequest request, VillageForm object, BindingResult result) {
		boolean validFlag = true;
		VillageForm govtOrderForm = (VillageForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentBeanForVillage(govtOrderForm, request, result);
		SubAttachmentHandler attachmentHandlerOne = new SubAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		String validateAttachmentOne = "";
		try {
			validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
			commonValidator.isValidMime(result, request, govtOrderForm.getAttachFile2());
			if (result.hasErrors()) {
				validFlag = false;
			}
			if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFiles(addAttachmentBean);
			if (!validFlag) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	// To validate Govt Order Attachment in Modify Village Form.
	public List<AttachedFilesForm> ValidateAndUploadFileGovtOrderVillageModify(HttpServletRequest request, VillageForm object, BindingResult result) {
		boolean validFlag = true;
		VillageForm govtOrderForm = (VillageForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentBean(govtOrderForm, request, result);
		SubAttachmentHandler attachmentHandlerOne = new SubAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		String validateAttachmentOne = "";
		try {
			commonValidator.isValidMime(result, request, govtOrderForm.getAttachFile1());
			if (result.hasErrors()) {
				validFlag = false;
			}
			validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
			if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFiles(addAttachmentBean);
			if (!validFlag) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	// To validate Map Upload Attachment in Create Village Form.
	public List<AttachedFilesForm> ValidateAndUploadFileMap(HttpServletRequest request, Object object, BindingResult result, ModelAndView mav) {
		boolean validFlag = true;
		VillageForm govtOrderForm = (VillageForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentBeanMap(govtOrderForm, request, result);
		AddAttachmentHandler attachmentHandlerOne = new AddAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		String validateAttachmentOne = "";
		try {
			validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
			commonValidator.isValidMime(result, request, govtOrderForm.getAttachFile1());
			if (result.hasErrors()) {
				validFlag = false;
			}
			if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFiles(addAttachmentBean);
			if (!validFlag) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);

			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	/**
	 * To validate Map Upload Attachment in Modify Village Form.
	 */
	public List<AttachedFilesForm> ValidateAndUploadFileMapVilModify(HttpServletRequest request, Object object, BindingResult result, ModelAndView mav) {
		boolean validFlag = true;
		VillageForm govtOrderForm = (VillageForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentBeanMap2(govtOrderForm, request, result);
		AddAttachmentHandler attachmentHandlerOne = new AddAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		String validateAttachmentOne = "";
		try {
			validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
			commonValidator.isValidMime(result, request, govtOrderForm.getAttachFile2());
			if (result.hasErrors()) {
				validFlag = false;
			}
			if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFiles(addAttachmentBean);
			if (!validFlag) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	public List<AttachedFilesForm> ValidateAndUploadFileMapForSubDistrict(HttpServletRequest request, Object object, BindingResult result, ModelAndView mav) {
		boolean validFlag = true;
		SubDistrictForm govtOrderForm = (SubDistrictForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentBeanMapForSubDistrict(govtOrderForm, request, result);
		AddAttachmentHandler attachmentHandlerOne = new AddAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		String validateAttachmentOne = "";
		try {
			validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
			commonValidator.isValidMime(result, request, govtOrderForm.getAttachFile1());
			if (result.hasErrors()) {
				validFlag = false;
			}
			if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFiles(addAttachmentBean);
			if (!validFlag) {
				return null;
			}
		if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	private AddAttachmentBean getAttachmentBean(GovernmentOrderForm govtOrderForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 0;// explicitly defined 0
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(govtOrderForm.getAttachFile1());// 1.List Of File To Be Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location For File Upload In File System.
			addAttachmentBeanTwo.setFileTitle(govtOrderForm.getFileTitle1());// 3.File Title Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed Total Number Of Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed Total File Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed Individual File Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number Of Already Attached File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already attached file total size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime Type List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File Id array to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File Name Array To Be Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted File Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number of Mandatory file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}
		return addAttachmentBeanTwo;
	}

	private AddAttachmentBean getAttachmentBeanMap(VillageForm govtOrderForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(2);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(govtOrderForm.getAttachFile1());// 1.List Of File To Be Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location For File Upload In File System.
			addAttachmentBeanTwo.setFileTitle(govtOrderForm.getFileTitle1());// 3.File Title Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed Total Number Of Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed Total File Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed Individual File Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number Of Already Attached File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already attached file total size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime Type List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File Id array to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File Name Array To Be Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted File Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number of Mandatory file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}
		return addAttachmentBeanTwo;
	}

	private AddAttachmentBean getAttachmentBeanMapForSubDistrict(SubDistrictForm govtOrderForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(2);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(govtOrderForm.getAttachFile1());// 1.List Of File To Be Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location For File Upload In File System.
			addAttachmentBeanTwo.setFileTitle(govtOrderForm.getFileTitle1());// 3.File Title Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed Total Number Of Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed Total File Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed Individual File Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number Of Already Attached File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already attached file total size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime Type List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File Id array to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File Name Array To Be Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted File Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number of Mandatory file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}
		return addAttachmentBeanTwo;
	}

	public AddAttachmentBean getAttachmentBeanMap2(VillageForm govtOrderForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(2);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 0;// Explicitly defined 0
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(govtOrderForm.getAttachFile2());
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);
			addAttachmentBeanTwo.setFileTitle(govtOrderForm.getFileTitle1());
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID2"));
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName2"));
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList2"));
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}

		return addAttachmentBeanTwo;
	}

	public List<AttachedFilesForm> ValidateAndUploadFileGovtOrder(HttpServletRequest request, AddAttachmentBean govAttachmentBean, List<CommonsMultipartFile> govAttachFile, BindingResult result, ModelAndView mav) {
		boolean validFlag = true;
		AddAttachmentHandler attachmentHandlerOne = new AddAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		try {
			attachmentHandlerOne.validateAttachment(govAttachmentBean);
			if (result.hasErrors()) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(govAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFiles(govAttachmentBean);
			if (!validFlag) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, govAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	public AddAttachmentBean getAttachmentBean(VillageForm govtOrderForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 0;// Explicitly defined 0
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(govtOrderForm.getAttachFile1());// 1.List Of File To Be Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location For File Upload In File System.
			addAttachmentBeanTwo.setFileTitle(govtOrderForm.getFileTitle1());// 3.File Title Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed Total Number Of Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed Total File Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed Individual File Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number Of Already Attached File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already attached file total size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime Type List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File Id array to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File Name Array To Be Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted File Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number of Mandatory file.
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}
		return addAttachmentBeanTwo;
	}

	public List<AttachedFilesForm> ValidateAndUploadFileMap2(HttpServletRequest request, AddAttachmentBean mapAttachmentBean, List<CommonsMultipartFile> mapAttachFile, BindingResult result, ModelAndView mav) {
		boolean validFlag = true;
		AddAttachmentHandler mapAttachmentHandlerOne = new AddAttachmentHandler();
		List<AttachedFilesForm> mapAttachedFileList = null;
		try {
			mapAttachmentHandlerOne.validateAttachment(mapAttachmentBean);
			mapAttachmentHandlerOne.deleteMetaDataINFileSystem(mapAttachmentBean);
			//String deleteFileID[] = mapAttachmentBean.getDeletedFileID();
			mapAttachedFileList = mapAttachmentHandlerOne.getMetaDataListOfFiles(mapAttachmentBean);
			if (validFlag == false) {
				return null;
			}
			if (mapAttachedFileList != null) {
				mapAttachmentHandlerOne.saveMetaDataINFileSystem(mapAttachedFileList, mapAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mapAttachedFileList;
	}
	
	@SuppressWarnings("unchecked")
	public List<AttachedFilesForm> ValidateAndUploadFileMapLocalBody(HttpServletRequest request, Object object, BindingResult result, ModelAndView mav, HttpSession session) {
		boolean validFlag = true;
		LocalGovtBodyForm lbForm = (LocalGovtBodyForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentBeanMapLocalbody(lbForm, request, result);
		SubAttachmentHandler attachmentHandlerOne = new SubAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		String validateAttachmentOne = "";
		try {
			validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
			commonValidator.isValidMimeLB(result, request, lbForm.getAttachFile2(), session);
			if (result.hasErrors()) {
				validFlag = false;
			}
			if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFilesLocalBody(addAttachmentBean);
			if (!validFlag) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystemLocalBody(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	private AddAttachmentBean getAttachmentBeanMapLocalbody(LocalGovtBodyForm lbForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(2);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(lbForm.getAttachFile1());// 1.List Of File To Be Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location For File Upload In File System.
			addAttachmentBeanTwo.setFileTitle(lbForm.getFileTitle1());// 3.File Title Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed Total Number Of Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed Total File Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed Individual File Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number Of Already Attached File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already attached file total size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime Type List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File Id array to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File Name Array To Be Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted File Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number of Mandatory file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}
		return addAttachmentBeanTwo;
	}

	public List<AttachedFilesForm> ValidateAndUploadFileMapForBlock(HttpServletRequest request, Object object, BindingResult result, ModelAndView mav) {
		boolean validFlag = true;
		BlockForm govtOrderForm = (BlockForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentBeanMapForBlock(govtOrderForm, request, result);
		AddAttachmentHandler attachmentHandlerOne = new AddAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		try {
			attachmentHandlerOne.validateAttachment(addAttachmentBean);
			if (result.hasErrors()) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFiles(addAttachmentBean);
			if (!validFlag) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	private AddAttachmentBean getAttachmentBeanMapForBlock(BlockForm govtOrderForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(2);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(govtOrderForm.getAttachFile1());// 1.List Of File To Be Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location For File Upload In File System.
			addAttachmentBeanTwo.setFileTitle(govtOrderForm.getFileTitle1());// 3.File Title Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed Total Number Of Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed Total File Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed Individual File Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number Of Already Attached File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already attached file total size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime Type List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File Id array to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File Name Array To Be Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted File Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number of Mandatory file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}
		return addAttachmentBeanTwo;
	}

	@SuppressWarnings("unchecked")
	public List<AttachedFilesForm> ValidateAndUploadFileMapParliament(HttpServletRequest request, Object object, BindingResult result, ModelAndView mav) {
		boolean validFlag = true;
		ParliamentForm pcForm = (ParliamentForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentBeanMapParliament(pcForm, request, result);
		SubAttachmentHandler attachmentHandlerOne = new SubAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		String validateAttachmentOne = "";
		try {
			validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
			commonValidator.isValidMime(result, request, pcForm.getAttachFile1());
			if (result.hasErrors()) {
				validFlag = false;
			}
			if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFilesLocalBody(addAttachmentBean);
			if (!validFlag) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystemLocalBody(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	private AddAttachmentBean getAttachmentBeanMapParliament(ParliamentForm pcForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(2);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(pcForm.getAttachFile1());// 1.List Of File To Be Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location For File Upload In File System.
			addAttachmentBeanTwo.setFileTitle(pcForm.getFileTitle1());// 3.File Title Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed Total Number Of Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed Total File Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed Individual File Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number Of Already Attached File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already attached file total size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime Type List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File Id array to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File Name Array To Be Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted File Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number of Mandatory file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}
		return addAttachmentBeanTwo;
	}

	// Tanuj
	@SuppressWarnings("unchecked")
	public List<AttachedFilesForm> ValidateAndUploadFileMapAssembly(HttpServletRequest request, Object object, BindingResult result, ModelAndView mav) {
		boolean validFlag = true;
		AssemblyForm acForm = (AssemblyForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentBeanMapAssembly(acForm, request, result);
		SubAttachmentHandler attachmentHandlerOne = new SubAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		String validateAttachmentOne = "";
		try {
			validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
			commonValidator.isValidMime(result, request, acForm.getAttachFile1());
			if (result.hasErrors()) {
				validFlag = false;
			}
			if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFilesLocalBody(addAttachmentBean);
			if (validFlag == false) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystemLocalBody(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	private AddAttachmentBean getAttachmentBeanMapAssembly(AssemblyForm acForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(2);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(acForm.getAttachFile1());// 1.List Of File To Be Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location For File Upload In File System.
			addAttachmentBeanTwo.setFileTitle(acForm.getFileTitle1());// 3.File Title Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed Total Number Of Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed Total File Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed Individual File Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number Of Already Attached File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already attached file total size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime Type List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File Id array to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File Name Array To Be Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted File Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number of Mandatory file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}
		return addAttachmentBeanTwo;
	}

	public List<AttachedFilesForm> ValidateAndUploadFileMapForDistrict(HttpServletRequest request, Object object, BindingResult result, ModelAndView mav) {
		boolean validFlag = true;
		DistrictForm govtOrderForm = (DistrictForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentBeanMapForDistrict(govtOrderForm, request, result);
		AddAttachmentHandler attachmentHandlerOne = new AddAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		String validateAttachmentOne = "";
		try {
			validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
			commonValidator.isValidMime(result, request, govtOrderForm.getAttachFile1());
			if (result.hasErrors()) {
				validFlag = false;
				result.reject("attachFile1", validateAttachmentOne);
			}
			if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
				validFlag = false;
				result.reject("attachFile1", validateAttachmentOne);
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFiles(addAttachmentBean);
			if (!validFlag) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	private AddAttachmentBean getAttachmentBeanMapForDistrict(DistrictForm govtOrderForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(2);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(govtOrderForm.getAttachFile1());// 1.List Of File To Be Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location For File Upload In File System.
			addAttachmentBeanTwo.setFileTitle(govtOrderForm.getFileTitle1());// 3.File Title Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed Total Number Of Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed Total File Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed Individual File Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number Of Already Attached File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already attached file total size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime Type List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File Id array to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File Name Array To Be Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted File Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number of Mandatory file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}
		return addAttachmentBeanTwo;
	}

	public List<AttachedFilesForm> ValidateAndUploadFileGovtOrderforChangeCoverage(HttpServletRequest request, LocalGovtBodyForm object, BindingResult result, ModelAndView mav, HttpSession session) {
		boolean validFlag = true;
		LocalGovtBodyForm localGovtBodyForm = (LocalGovtBodyForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentBeanforCoverageChange(localGovtBodyForm, request, result);
		SubAttachmentHandler attachmentHandlerOne = new SubAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		String validateAttachmentOne = "";
		try {
			validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
			commonValidator.isValidMimeLBforViewMLB(result, request, localGovtBodyForm.getAttachFile1(), session);
			if (result.hasErrors()) {
				validFlag = false;
			}
			if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFiles(addAttachmentBean);
			if (validFlag == false) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	private AddAttachmentBean getAttachmentBeanforCoverageChange(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(localGovtBodyForm.getAttachFile1());// 1.List Of File To Be Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location For File Upload In File System.
			addAttachmentBeanTwo.setFileTitle(localGovtBodyForm.getFileTitle1());// 3.File Title Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed Total Number Of Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed Total File Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed Individual File Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number Of Already Attached File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already attached file total size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime Type List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File Id array to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File Name Array To Be Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted File Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number of Mandatory file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}
		return addAttachmentBeanTwo;
	}

	public boolean validWarninngFlagMapUpload(List<MapAttachment> mapAttachmentList, List<AttachedFilesForm> validFileMap) throws Exception {
		String existingMapFile = "";
		String currentMapFile = "";
		if (mapAttachmentList != null && !mapAttachmentList.isEmpty()) {
			MapAttachment mapattDistt = mapAttachmentList.get(0);
			existingMapFile = mapattDistt.getFileName();
		}
		if (!validFileMap.isEmpty()) {
			currentMapFile = validFileMap.get(0).getFileName();
		}
		if (!"".equals(currentMapFile) && !currentMapFile.equalsIgnoreCase(existingMapFile)) {
			return true;
		}
		return false;
	}

	// Check for Attachment
	private AddAttachmentBean getAttachmentBeanForVillage(VillageForm govtOrderForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(govtOrderForm.getAttachFile2());// 1.List Of File To Be Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location For File Upload In File System.
			addAttachmentBeanTwo.setFileTitle(govtOrderForm.getFileTitle1());// 3.File Title Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed Total Number Of Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed Total File Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed Individual File Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number Of Already Attached File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already attached file total size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime Type List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File Id array to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File Name Array To Be Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted File Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number of Mandatory file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}

		return addAttachmentBeanTwo;
	}

	/*---------------------------------------------To upload govt order files for de-notified VPs-----------------*/
	/**
	 * To validate Government Order Attachment Details in Village Form.
	 */
	public List<AttachedFilesForm> ValidateAndUploadFileGOVVP(HttpServletRequest request, LBFreezeForm object, BindingResult result) {
		boolean validFlag = true;
		LBFreezeForm govtOrderForm = (LBFreezeForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentForVillagePanchaytDenotify(govtOrderForm, request, result);
		SubAttachmentHandler attachmentHandlerOne = new SubAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		String validateAttachmentOne = "";
		try {
			validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
			commonValidator.isValidMime(result, request, govtOrderForm.getAttachFile2());
			if (result.hasErrors()) {
				validFlag = false;
			}
			if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFiles(addAttachmentBean);
			if (!validFlag) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	// Check for Attachment
	private AddAttachmentBean getAttachmentForVillagePanchaytDenotify(LBFreezeForm govtOrderForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(govtOrderForm.getAttachFile2());// 1.List Of File To Be Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location For File Upload In File System.
			addAttachmentBeanTwo.setFileTitle(govtOrderForm.getFileTitle1());// 3.File Title Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed Total Number Of Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed Total File Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed Individual File Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number Of Already Attached File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already attached file total size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime Type List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File Id array to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File Name Array To Be Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted File Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number of Mandatory file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}
		return addAttachmentBeanTwo;
	}

	private AddAttachmentBean getAttachmentBeanMapToDenotify(LBFreezeForm govtOrderForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(2);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(govtOrderForm.getAttachFile1());// 1.List Of File To Be Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location For File Upload In File System.
			addAttachmentBeanTwo.setFileTitle(govtOrderForm.getFileTitle1());// 3.File Title Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed Total Number Of Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed Total File Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed Individual File Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number Of Already Attached File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already attached file total size.
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime Type List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File Id array to be deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File Name Array To Be Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted File Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number of Mandatory file.
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}
		return addAttachmentBeanTwo;
	}

	public List<AttachedFilesForm> ValidateAndUploadFileMapToDenotify(HttpServletRequest request, Object object, BindingResult result, ModelAndView mav) {
		boolean validFlag = true;
		LBFreezeForm govtOrderForm = (LBFreezeForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentBeanMapToDenotify(govtOrderForm, request, result);
		AddAttachmentHandler attachmentHandlerOne = new AddAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		String validateAttachmentOne = "";
		try {
			validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
			commonValidator.isValidMime(result, request, govtOrderForm.getAttachFile2());
			if (result.hasErrors()) {
				validFlag = false;
			}
			if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFiles(addAttachmentBean);
			if (!validFlag) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	/**
	 * Code for validate uploaded Govt. Order File in Update Organization Level
	 * 
	 * @param request
	 * @param object
	 * @param result
	 * @return
	 */
	public List<AttachedFilesForm> validateAndUploadFileGovtOrderUpdateOrgLvl(HttpServletRequest request, OrgLocatedAtLevelsForm object, BindingResult result) {
		boolean validFlag = true;
		OrgLocatedAtLevelsForm govtOrderForm = (OrgLocatedAtLevelsForm) object;
		AddAttachmentBean addAttachmentBean = getAttachmentBeanForUpdateOrgLvl(govtOrderForm, request, result);
		SubAttachmentHandler attachmentHandlerOne = new SubAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		String validateAttachmentOne = "";
		try {
			validateAttachmentOne = attachmentHandlerOne.validateAttachment(addAttachmentBean);
			commonValidator.isValidMime(result, request, govtOrderForm.getAttachFile());
			if (result.hasErrors()) {
				validFlag = false;
			}
			if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {
				validFlag = false;
			}
			attachmentHandlerOne.deleteMetaDataINFileSystem(addAttachmentBean);
			metaInfoOfToBeAttachedFileList = attachmentHandlerOne.getMetaDataListOfFiles(addAttachmentBean);
			if (!validFlag) {
				return null;
			}
			if (metaInfoOfToBeAttachedFileList != null) {
				attachmentHandlerOne.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	private AddAttachmentBean getAttachmentBeanForUpdateOrgLvl(OrgLocatedAtLevelsForm govtOrderForm, HttpServletRequest request, BindingResult result) {
		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0;
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 0;
			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(govtOrderForm.getAttachFile());
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);
			addAttachmentBeanTwo.setFileTitle(govtOrderForm.getFileTitle1());
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}
		return addAttachmentBeanTwo;
	}
}