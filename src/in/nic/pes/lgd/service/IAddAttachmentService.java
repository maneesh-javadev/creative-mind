package in.nic.pes.lgd.service;
/*
 * @auther Deepak Tandon
 * created on 29th Dec,2011
 */

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AttachedFilesForm;
public interface IAddAttachmentService {
	public abstract String saveMetaData(List<AttachedFilesForm> attachedFileList,String formName,Integer rowid) throws Exception;
	public abstract String deleteMetaData(String attachmentId[]) throws Exception;
// TODO Remove unused code found by UCDetector
// 	public String deleteMetaData(String formId,Integer rowIdentifier) throws Exception;
	public abstract List<Attachment> getMetaData(String formId) throws Exception;
// TODO Remove unused code found by UCDetector
// 	public List<Attachment> getMetaData(String formId, Integer rowIdentifier)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public List<Attachment> getAllMetaDataInfo(String formId) throws Exception;
// TODO Remove unused code found by UCDetector
// 	public List<Attachment> getAllMetaDataInfo(String formId, Integer rowIdentifier) throws Exception;
	public abstract List<String> getMimeTypeList() throws Exception;
	public AttachmentMaster getAttachmentMaster();
// TODO Remove unused code found by UCDetector
// 	public AttachmentMaster getAttachmentMaster(Long fileMasterId);
// TODO Remove unused code found by UCDetector
// 	public AddAttachmentBean populateBeanForUploadFile(List<CommonsMultipartFile> attachedFile, int noOFMandatoryFile,HttpServletRequest request) throws Exception;
	public boolean validateUploadFile(AddAttachmentBean addAttachmentBean,int noOFMandatoryFile, HttpServletRequest request) throws Exception; // NO_UCD (unused code)
	public boolean uploadFile(AddAttachmentBean addAttachmentBean, Integer idNew,String formId, HttpServletRequest request, Map<String, Object> model)throws Exception; // NO_UCD (unused code)
	public AddAttachmentBean populateBeanForUploadFile(List<CommonsMultipartFile> attachedFile, int noOFMandatoryFile,	AttachmentMaster attachmentMasterDetail, HttpServletRequest request)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public AddAttachmentBean populateBeanForUploadFile(List<CommonsMultipartFile> attachedFile, int noOFMandatoryFile,	AttachmentMaster attachmentMasterDetail,HttpServletRequest request, String addStringForMoreFiles)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public String deleteSingleFile(Long attachmentId);
}
