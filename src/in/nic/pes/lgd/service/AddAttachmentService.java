package in.nic.pes.lgd.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.dao.IAddAttachmentDAO;
import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

@Service
public class AddAttachmentService implements IAddAttachmentService {
	@Autowired
    IAddAttachmentDAO addAttachmentDAO;

	@Override
	public String saveMetaData(List<AttachedFilesForm> attachedFileList,String formName,Integer rowid) throws Exception {
		
		return addAttachmentDAO.saveMetaData(attachedFileList,formName,rowid);
	}

	@Override
	public List<Attachment> getMetaData(String formId) throws Exception {
		
		return addAttachmentDAO.getMetaData(formId);
	}
    
	/*@Override
	public List<Attachment> getMetaData(String formId,Integer rowIdentifier) throws Exception {
		
		return addAttachmentDAO.getMetaData(formId,rowIdentifier);
	}*/

	/*@Override
	public List<Attachment> getAllMetaDataInfo(String formId) throws Exception {
		
		return addAttachmentDAO.getAllMetaDataInfo(formId);
	}*/
	
	/*@Override
	public List<Attachment> getAllMetaDataInfo(String formId,Integer rowIdentifier) throws Exception {
		
		return addAttachmentDAO.getAllMetaDataInfo(formId, rowIdentifier);
	}*/
	@Override
	public String deleteMetaData(String attachmentId[]) throws Exception {
		
		return addAttachmentDAO.deleteMetaData(attachmentId);
	}
    
	/*@Override
	public String deleteMetaData(String formId,Integer rowIdentifier) throws Exception {
		
		return addAttachmentDAO.deleteMetaData(formId,rowIdentifier);
	}
*/
	
	@Override
	public List<String> getMimeTypeList() throws Exception {
		
		return addAttachmentDAO.getMimeTypeList();
	}

	@Override
	public AttachmentMaster getAttachmentMaster() {
		
		return addAttachmentDAO.getAttachmentMaster();
	}
	/*@Override
	 public AddAttachmentBean populateBeanForUploadFile(List<CommonsMultipartFile> attachedFile,int noOFMandatoryFile,HttpServletRequest request)throws Exception{		 
	 return populateBeanForUploadFile(attachedFile,noOFMandatoryFile,null,request);
	}*/
	@Override
	 public AddAttachmentBean populateBeanForUploadFile(List<CommonsMultipartFile> attachedFile,int noOFMandatoryFile,AttachmentMaster attachmentMasterDetail,HttpServletRequest request)throws Exception{		 
		  // boolean flag=true; 
			/*Getting Allowed Mime Type List From Database*/
			List<String> allowedMimeTypeList = getMimeTypeList();
			if(null==attachmentMasterDetail){
				attachmentMasterDetail = getAttachmentMaster();//Getting Master Table Data.
			}
			String fileUploadLocation = attachmentMasterDetail.getFileLocation();//1.File Upload Location.
			long allowedIndividualFileSize = attachmentMasterDetail.getIndividualFileSize();//2.Allowed Individual File Size.
			long allowedTotalFileSize =attachmentMasterDetail.getTotalFileSize();
			int allowedTotalNoOfAttachment=attachmentMasterDetail.getFileLimit();//0 for unlimited//			
			/*================Getting the values from Application and Setting IN AddAttachmentBeanClass==================*/			
			AddAttachmentBean addAttachmentBean = new AddAttachmentBean();
			//addAttachmentBean.setCurrentlyUploadFileList(trgProposal.getAttachedFile());//1.List Of File To Be Upload.
			addAttachmentBean.setCurrentlyUploadFileList(attachedFile);
			addAttachmentBean.setUploadLocation(fileUploadLocation);//2.Location For File Upload In File System.
			addAttachmentBean.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);//3.Allowed Total Number Of Attachment.
			addAttachmentBean.setAllowedTotalFileSize(allowedTotalFileSize);//4.Allowed Total File Size.
			addAttachmentBean.setAllowedIndividualFileSize(allowedIndividualFileSize);//5.Allowed Individual File Size.
			addAttachmentBean.setFileTitle(request.getParameterValues("fileTitle"));//6.File Title Array.
			addAttachmentBean.setDeletedFileList(request.getParameterValues("fileSize"));//7.Deleted File Array.			
			addAttachmentBean.setDeletedFileID(request.getParameterValues("deletedFileID"));//10.File Id array to be deleted
			addAttachmentBean.setDeletedFileName(request.getParameterValues("deletedFileName"));//11.File Name Array To Be Deleted.
			addAttachmentBean.setDeletedFileList(request.getParameterValues("deletedFileSizeList"));//12.Deleted File Array.
			addAttachmentBean.setNoOFMandatoryFile(noOFMandatoryFile);//13.Number of Mandatory file. noOFMandatoryFile1			
			addAttachmentBean.setMimeTypeList(allowedMimeTypeList);//12.File Mime Type List.
			return addAttachmentBean;
	}
	/*@Override
	 public AddAttachmentBean populateBeanForUploadFile(List<CommonsMultipartFile> attachedFile,int noOFMandatoryFile,AttachmentMaster attachmentMasterDetail,HttpServletRequest request,String addStringForMoreFiles)throws Exception{
		AddAttachmentBean addAttachmentBean=populateBeanForUploadFile(attachedFile,noOFMandatoryFile,attachmentMasterDetail,request);
		addAttachmentBean.setFileTitle(request.getParameterValues("fileTitle"+addStringForMoreFiles));//6.File Title Array.
		addAttachmentBean.setDeletedFileList(request.getParameterValues("fileSize"+addStringForMoreFiles));//7.Deleted File Array.			
		addAttachmentBean.setDeletedFileID(request.getParameterValues("deletedFileID"+addStringForMoreFiles));//10.File Id array to be deleted
		addAttachmentBean.setDeletedFileName(request.getParameterValues("deletedFileName"+addStringForMoreFiles));//11.File Name Array To Be Deleted.
		addAttachmentBean.setDeletedFileList(request.getParameterValues("deletedFileSizeList"+addStringForMoreFiles));//12.Deleted File Array.
		return addAttachmentBean;
	}	*/
	
	@Override
	 public boolean validateUploadFile(AddAttachmentBean addAttachmentBean,int noOFMandatoryFile,HttpServletRequest request)throws Exception{
		boolean flag=true;
		/*================Check the Validation==================*/
		AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
		String validateAttachment = attachmentHandler.validateAttachment(addAttachmentBean);	
		if(!validateAttachment.equalsIgnoreCase("validationSuccessFullyDone")) {
			request.setAttribute("validationError", validateAttachment);
			request.setAttribute("noOFMandatoryFile", noOFMandatoryFile);				
			flag=false;
			
		}
		return flag;
	}
	@Override
	 public boolean uploadFile(AddAttachmentBean addAttachmentBean,Integer idNew,String formId,HttpServletRequest request,Map<String, Object> model)throws Exception{		 
		   boolean flag=true; 
		   AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
			if(metaInfoOfToBeAttachedFileList != null && metaInfoOfToBeAttachedFileList.size() != 0) {
				@SuppressWarnings("unused")
				String saveSuccess =saveMetaData(metaInfoOfToBeAttachedFileList,formId,idNew);
				//System.out.println("=========================saveMetaData IN Database==============:"+saveSuccess);
				@SuppressWarnings("unused")
				String saveAttachment = attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
				//System.out.println("=========================saveAttachment IN File System==============:"+saveAttachment);
			}			
			 /*======================================Attached File Deletion Part ==========================================*/				
			String deletedFileID[] = addAttachmentBean.getDeletedFileID();	//File Id array to be deleted
			if(deletedFileID!=null && deletedFileID.length > 0)
			{
				@SuppressWarnings("unused")
				String deleteSuccessOne = deleteMetaData(deletedFileID);
				//System.out.println("=======================deleteSuccessOne FROM DataBase==============:"+deleteSuccessOne);
			}
			
			//String deleteSuc = attachmentHandler.deleteMetaDataINFileSystem(addAttachmentBean);
			List<Attachment> newAttachmentsList= getMetaData(formId);
			model.put("Attached_File_Meta_Data_List", newAttachmentsList);	
			return flag;
	 }

	/*@Override
	public AttachmentMaster getAttachmentMaster(Long fileMasterId) {
		return addAttachmentDAO.getAttachmentMaster(fileMasterId);
	}*/

	/*@Override
	public String deleteSingleFile(Long attachmentId) {
		// TODO Auto-generated method stub
		return addAttachmentDAO.deleteSingleFile(attachmentId);
	}*/

}