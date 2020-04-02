package in.nic.pes.lgd.common;

import java.util.List;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;
import pes.attachment.util.AttachedFilesUtil;

/**
 * @author Sushil Shakya
 * @since 20th December 2012
 */
public class SubAttachmentHandler extends AddAttachmentHandler {
	public List getMetaDataListOfFilesLocalBody(AddAttachmentBean addAttachmentBean) throws Exception { // NO_UCD (use default)
		List currentlyUploadFileList = addAttachmentBean.getCurrentlyUploadFileList();
		String uploadLocation = addAttachmentBean.getUploadLocation();
		String fileTitleList[] = addAttachmentBean.getFileTitle();
		AttachedFilesUtil filesUtil = new AttachedFilesUtil();
		List listOfFilesToBeUpload = filesUtil.getListOfFilesToBeUpload(currentlyUploadFileList);
		List metaInfoOfToBeAttachedFileList = null;
		AttachedFilesForm attachedFilesForm = new AttachedFilesForm();
		if (listOfFilesToBeUpload != null && listOfFilesToBeUpload.size() != 0) {
			attachedFilesForm.setAttachedFile(listOfFilesToBeUpload);
			attachedFilesForm.setTitleList(fileTitleList);
			attachedFilesForm.setUploadLocation(uploadLocation);
			metaInfoOfToBeAttachedFileList = filesUtil.returnMetaDataList(attachedFilesForm);
		}
		return metaInfoOfToBeAttachedFileList;
	}

	public String saveMetaDataINFileSystemLocalBody(List metaInfoOfToBeAttachedFileList, AddAttachmentBean addAttachmentBean) throws Exception { // NO_UCD (use default)
		List currentlyUploadFileList = addAttachmentBean.getCurrentlyUploadFileList();
		AttachedFilesUtil filesUtil = new AttachedFilesUtil();
		List listOfFilesToBeUpload = filesUtil.getListOfFilesToBeUpload(currentlyUploadFileList);
		AttachedFilesForm attachedFilesForm = new AttachedFilesForm();
		if (listOfFilesToBeUpload != null && listOfFilesToBeUpload.size() != 0){
			attachedFilesForm.setAttachedFile(listOfFilesToBeUpload);
		}	
		if (metaInfoOfToBeAttachedFileList != null && metaInfoOfToBeAttachedFileList.size() != 0) {
			String uploadLocation = addAttachmentBean.getUploadLocation();
			AttachedFilesUtil attachedFilesUtil = new AttachedFilesUtil();
			String saveAttachment = attachedFilesUtil.saveAttachment(attachedFilesForm, metaInfoOfToBeAttachedFileList, uploadLocation);
			return saveAttachment;
		} else {
			return "savingINFileSystemFails";
		}
	}
}