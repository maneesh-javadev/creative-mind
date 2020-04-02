package com.cmc.lgd.localbody.rules;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.common.SubAttachmentHandler;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.utils.CommonUtil;
import in.nic.pes.lgd.utils.DocumentConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AttachedFilesForm;

import com.cmc.lgd.localbody.entities.CriteriaDraftedEntities;
import com.cmc.lgd.localbody.entities.LBAttributes;
import com.cmc.lgd.localbody.entities.LocalBodyForm;
import com.cmc.lgd.localbody.services.LocalBodyService;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

public class LocalBodyUtil {
	
	@Autowired
	private LocalBodyService localBodyService;
	
	@Autowired
	private DocumentConverter docconverter;


	public List<Attachment> uploadFileToServer(List<CommonsMultipartFile> attachmentList, Long fileMasterId) throws Exception {
		List<Attachment> attchmentsDetails = new ArrayList<Attachment>();
		if (attachmentList != null && !attachmentList.isEmpty()) {
			AttachmentMaster master = localBodyService.getUploadFileConfigurationDetails(fileMasterId);
			AddAttachmentBean attachmentBean = new AddAttachmentBean();
			attachmentBean.setUploadLocation(master.getFileLocation());
			attachmentBean.setCurrentlyUploadFileList(attachmentList);
			SubAttachmentHandler attachmentHandler = new SubAttachmentHandler();
			String validateAttachment = attachmentHandler.validateAttachment(attachmentBean);
			List<AttachedFilesForm> metaInfoList = attachmentHandler.getMetaDataListOfFiles(attachmentBean);
			if (metaInfoList != null && "validationSuccessFullyDone".equals(validateAttachment)) {
				String saveAttachment = attachmentHandler.saveMetaDataINFileSystem(metaInfoList, attachmentBean);
				if ("saveSuccessFully".equals(saveAttachment)) {
					for(AttachedFilesForm attachedFilesForm : metaInfoList){
						Attachment attachment = new Attachment();
						attachment.setFileLocation(attachmentBean.getUploadLocation().replaceAll("\\\\", "/") +"/" +attachedFilesForm.getFileTimestampName());
						attachment.setFileName(attachedFilesForm.getFileName());
						attachment.setFileTimestamp(attachedFilesForm.getFileTimestampName());
						attachment.setFileTitle(attachedFilesForm.getFileTitle());
						attachment.setFileSize(attachedFilesForm.getFileSize());
						attachment.setFileContentType(attachedFilesForm.getFileType());
						attchmentsDetails.add(attachment);
					}
				}
			}
		}
		return attchmentsDetails;
	}
	
	
	public String downloadFileFromServer(String fileName, Long fileMasterId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AttachmentMaster master = localBodyService.getUploadFileConfigurationDetails(fileMasterId);
		String uploadedLocation = master.getFileLocation();
		if((uploadedLocation.lastIndexOf("\\\\") | uploadedLocation.lastIndexOf("\\/"))  > -1){
			fileName =  uploadedLocation.concat(fileName);
		} else{
			fileName =  uploadedLocation.concat("/").concat(fileName);
		}
		String isFileDownloadSuccess = CommonUtil.fileDownload(fileName, response, null);
		if(!"SUCCESS".equals(isFileDownloadSuccess)){
			String realPath = request.getServletContext().getRealPath("/").replaceAll("\\\\", "/");
			realPath += "/images/file_not_found.jpg";
			isFileDownloadSuccess = CommonUtil.fileDownload(realPath, response, null);
		}
		return isFileDownloadSuccess;
	}
	
	/**
	 * 
	 * @param map
	 * @param mav
	 */
	public void setMapAttributes(java.util.Map<String, Object> map, ModelAndView mav){
		for(String key : map.keySet()){
			mav.addObject(key, map.get(key));
		}
	}
	
	/**
	 * 
	 * @param localBodyForm
	 * @param request
	 * @throws Exception
	 */
	public void convertTemplatetoPDF(LocalBodyForm localBodyForm, HttpServletRequest request) throws Exception {
		String templateSource = localBodyForm.getEditorTemplateDetails();
		if(localBodyForm.getTemplateId() != null && !StringUtils.isBlank(templateSource)){
			GenerateDetails generatedetails = new GenerateDetails();
			generatedetails = docconverter.ganaratePDFDocument(generatedetails, request, templateSource);
			localBodyForm.setOrderPath(generatedetails.getFileName());
			localBodyForm.setOrderFileSize(generatedetails.getFileSize());
			localBodyForm.setOrderFileContentType(generatedetails.getFileContentType());
		}
	}
	
	/**
	 * 
	 */
	private void setConverterAttributes() {
		DateLocaleConverter converter = new DateLocaleConverter(Locale.getDefault(), LocalBodyConstant.CURRENT_DATE_PATTERN.toString());
	    converter.setLenient(true);
	    ConvertUtils.register(converter, java.util.Date.class);
	    ConvertUtils.register(new IntegerConverter(null), Integer.class);
	}
	
	/**
	 * 
	 * @param toBean
	 * @param fromBean
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void copyBeanAttributes(Object toBean, Object fromBean) throws IllegalAccessException, InvocationTargetException{
		BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
		setConverterAttributes();
		
		beanUtilsBean.copyProperties(toBean, fromBean);
		return;
	}
	
	/**
	 * 
	 * @param request
	 * @param localBodyCreationType
	 * @return
	 */
	public ModelAndView setAttributesForDraftedEntities(HttpServletRequest request, CriteriaDraftedEntities criteriaDraftedEntities, Integer stateCode, Integer districtCode){
		ModelAndView mav = new ModelAndView("restructued_grid_drafted_entities");
		try {
			LBAttributes attributes = localBodyService.onLoadDraftedSearchLocalBody(stateCode, criteriaDraftedEntities.getLocalBodyCreationType());
			List<CriteriaDraftedEntities> searchedDraftedEntities = localBodyService.fetchDraftedEntities(criteriaDraftedEntities.getLocalBodyCreationType(),
																										  criteriaDraftedEntities.getParamLocalBodyTypeCode(),
																										  stateCode,
																										  criteriaDraftedEntities.getParamLocalBodyName(),
																										  criteriaDraftedEntities.getParamActionProcessCode(),
																										  districtCode);
			mav.addObject("draftedOperations", attributes.getDraftOperations());
			mav.addObject("localBodyTypeList", attributes.getLocalBodyTypes());
			mav.addObject("draftedEntitiesList", searchedDraftedEntities);
			mav.addObject("criteriaDraftedEntities", criteriaDraftedEntities);
		}catch(Exception ex){
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			mav = new ModelAndView(expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, ex));
			ex.printStackTrace();
		}
		return mav;
	}
}
