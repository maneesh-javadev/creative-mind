package in.nic.pes.lgd.draft.constant;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cmc.lgd.localbody.entities.LocalBodyForm;
import com.cmc.lgd.localbody.rules.LocalBodyConstant;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.GovernmentOrderTemplate;
import in.nic.pes.lgd.bean.WsUserRegistrationForm;
import in.nic.pes.lgd.common.SubAttachmentHandler;
import in.nic.pes.lgd.draft.form.DraftGovermentOrderForm;
import in.nic.pes.lgd.draft.service.DraftUtilService;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.service.GovtOrderTemplateService;
import in.nic.pes.lgd.utils.DocumentConverter;
import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AttachedFilesForm;




public class DraftUtils {
	
	@Autowired
	DraftUtilService draftUtilService;
	
	@Autowired
	GovtOrderTemplateService govtOrderTemplateService;
	
	@Autowired
	private DocumentConverter docconverter;
	
	
	public DraftGovermentOrderForm uploadFileToServer(DraftGovermentOrderForm draftGovermentOrderForm, Long fileMasterId,HttpServletRequest request,HttpSession httpSession) throws Exception {
		List<CommonsMultipartFile> gazettePublicationUpload=draftGovermentOrderForm.getGazettePublicationUpload();
		if (gazettePublicationUpload != null && !gazettePublicationUpload.isEmpty()) {
			AttachmentMaster master = draftUtilService.getUploadFileConfigurationDetails(fileMasterId);
			AddAttachmentBean attachmentBean = new AddAttachmentBean();
			attachmentBean.setUploadLocation(master.getFileLocation());
			attachmentBean.setCurrentlyUploadFileList(gazettePublicationUpload);
			SubAttachmentHandler attachmentHandler = new SubAttachmentHandler();
			String validateAttachment = attachmentHandler.validateAttachment(attachmentBean);
			List<AttachedFilesForm> metaInfoList = attachmentHandler.getMetaDataListOfFiles(attachmentBean);
			if (metaInfoList != null && "validationSuccessFullyDone".equals(validateAttachment)) {
				String saveAttachment = attachmentHandler.saveMetaDataINFileSystem(metaInfoList, attachmentBean);
				if ("saveSuccessFully".equals(saveAttachment)) {
					AttachedFilesForm attachedFilesForm=metaInfoList.get(0);
					draftGovermentOrderForm.setFileLocation(attachmentBean.getUploadLocation().replaceAll("\\\\", "/")+"/" + attachedFilesForm.getFileTimestampName());
					draftGovermentOrderForm.setFileName(attachedFilesForm.getFileName());
					draftGovermentOrderForm.setFileTimestamp(attachedFilesForm.getFileTimestampName());
					draftGovermentOrderForm.setFileSize(attachedFilesForm.getFileSize());
					draftGovermentOrderForm.setFileContentType(attachedFilesForm.getFileType());
					
					
				}
			}
		} else if(draftGovermentOrderForm.getTemplateId()!=null){
			String templateBodySrc = draftUtilService.fillTemplateDetailsByEntity(draftGovermentOrderForm, httpSession);
			draftGovermentOrderForm.setEditorTemplateDetails(templateBodySrc);
			AttachmentMaster master = draftUtilService.getUploadFileConfigurationDetails(fileMasterId);
			draftGovermentOrderForm=this.convertTemplatetoPDF(draftGovermentOrderForm, master.getFileLocation(), request);
		}
		return draftGovermentOrderForm;
	}
	
	public WsUserRegistrationForm uploadFileToServerForWsUserDetails(WsUserRegistrationForm wsUserRegistrationForm, Long fileMasterId,HttpServletRequest request,HttpSession httpSession) throws Exception {
		List<CommonsMultipartFile> wsUserRegFileUpload=wsUserRegistrationForm.getFileUpload();
		if (wsUserRegFileUpload != null && !wsUserRegFileUpload.isEmpty()) {
			AttachmentMaster master = draftUtilService.getUploadFileConfigurationDetails(fileMasterId);
			AddAttachmentBean attachmentBean = new AddAttachmentBean();
			attachmentBean.setUploadLocation(master.getFileLocation());
			attachmentBean.setCurrentlyUploadFileList(wsUserRegFileUpload);
			SubAttachmentHandler attachmentHandler = new SubAttachmentHandler();
			String validateAttachment = attachmentHandler.validateAttachment(attachmentBean);
			List<AttachedFilesForm> metaInfoList = attachmentHandler.getMetaDataListOfFiles(attachmentBean);
			if (metaInfoList != null && "validationSuccessFullyDone".equals(validateAttachment)) {
				String saveAttachment = attachmentHandler.saveMetaDataINFileSystem(metaInfoList, attachmentBean);
				if ("saveSuccessFully".equals(saveAttachment)) {
					AttachedFilesForm attachedFilesForm=metaInfoList.get(0);
					wsUserRegistrationForm.setFileLocation(attachmentBean.getUploadLocation().replaceAll("\\\\", "/")+"/" + attachedFilesForm.getFileTimestampName());
					wsUserRegistrationForm.setFileName(attachedFilesForm.getFileName());
					wsUserRegistrationForm.setFileTimestamp(attachedFilesForm.getFileTimestampName());
					wsUserRegistrationForm.setUploadFileName(attachedFilesForm.getFileTimestampName());
					wsUserRegistrationForm.setFileSize(attachedFilesForm.getFileSize());
					wsUserRegistrationForm.setFileContentType(attachedFilesForm.getFileType());
					
					
				}
			}
		}/* else if(wsUserRegistrationForm.getTemplateId()!=null){
			String templateBodySrc = draftUtilService.fillTemplateDetailsByEntity(wsUserRegistrationForm, httpSession);
			wsUserRegistrationForm.setEditorTemplateDetails(templateBodySrc);
			AttachmentMaster master = draftUtilService.getUploadFileConfigurationDetails(fileMasterId);
			draftGovermentOrderForm=this.convertTemplatetoPDF(draftGovermentOrderForm, master.getFileLocation(), request);
		}*/
		return wsUserRegistrationForm;
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
	
	
	public List<Integer> createListFromString(String entityCodes)throws Exception{
		List<Integer> entityCodeList= new ArrayList<Integer>();
		entityCodeList.add(-1);
		if(entityCodes!=null && !("").equals(entityCodes)){
			if (entityCodes.contains(",")) {
				
				Scanner scanner = new Scanner(entityCodes);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					entityCodeList.add(Integer.parseInt(scanner.next()));
				}
				scanner.close();
			}else if (entityCodes.contains("@")) {
				
				Scanner scanner = new Scanner(entityCodes);
				scanner.useDelimiter("@");
				while (scanner.hasNext()) {
					entityCodeList.add(Integer.parseInt(scanner.next()));
				}
				scanner.close();
			}else
			{
				entityCodeList.add(Integer.parseInt(entityCodes));
			}
		}
		
		return entityCodeList;
	}
	
	/**
	 * 
	 * @param localBodyForm
	 * @param request
	 * @throws Exception
	 */
	public DraftGovermentOrderForm convertTemplatetoPDF(DraftGovermentOrderForm draftGovermentOrderForm,String uploadGoFileLoc, HttpServletRequest request) throws Exception {
		String templateSource = draftGovermentOrderForm.getEditorTemplateDetails();
		if(draftGovermentOrderForm.getTemplateId() != null && !StringUtils.isBlank(templateSource)){
			GenerateDetails generatedetails = new GenerateDetails();
			generatedetails = docconverter.ganaratePDFDocument(generatedetails, request, templateSource);
			
			draftGovermentOrderForm.setFileLocation(uploadGoFileLoc.replaceAll("\\\\", "/")+"/" + generatedetails.getFileTimestamp());
			draftGovermentOrderForm.setFileName(generatedetails.getFileName());
			draftGovermentOrderForm.setFileTimestamp(generatedetails.getFileTimestamp());
			draftGovermentOrderForm.setFileSize(generatedetails.getFileSize());
			draftGovermentOrderForm.setFileContentType(generatedetails.getFileContentType());
			
			
		}
		return draftGovermentOrderForm;
	}

}
