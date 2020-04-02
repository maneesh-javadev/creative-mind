package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;

import java.util.List;

import pes.attachment.util.AttachedFilesForm;

public interface IAddAttachmentDAO {
	public String saveMetaData(List<AttachedFilesForm> attachedFileList,String formName,Integer rowid) throws Exception;
	public String deleteMetaData(String attachmentId[]) throws Exception;
	public String deleteMetaData(String formId,Integer rowIdentifier) throws Exception;
	public List<Attachment> getMetaData(String formId) throws Exception;
	public List<Attachment> getMetaData(String formId, Integer rowIdentifier)throws Exception;
	public List<Attachment> getAllMetaDataInfo(String formId) throws Exception;
	public List<Attachment> getAllMetaDataInfo(String formId, Integer rowIdentifier) throws Exception;
	public List<String> getMimeTypeList() throws Exception;
	public AttachmentMaster getAttachmentMaster();
	public AttachmentMaster getAttachmentMaster(Long fileMasterId);
	public String deleteSingleFile(Long attachmentId);
}