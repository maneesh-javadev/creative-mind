package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderDetails;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.MimetypeMaster;
import in.nic.pes.lgd.bean.ViewConfigMapLocalBody;
import in.nic.pes.lgd.bean.VillageDraft;
import in.nic.pes.lgd.forms.GovernmentOrderForm;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import pes.attachment.util.AttachedFilesForm;

public interface GovernmentOrderDAO {

	public String getGovOrderPathDAO(Integer orderCode,String SQL,Session hsession)throws Exception;
	public void saveGovernmentOrder(GovernmentOrder govtOrder)throws Exception;
	public void updateGovernmentOrder(GovernmentOrder govtOrder)throws Exception;
	public void updateGovernmentOrder(GovernmentOrder govtOrder,Session session)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public int saveGovernmentOrder1(GovernmentOrder govtOrder)throws Exception;
	public List<GovernmentOrder> getOrderDetailsbyOrderCode(int orderCode)throws Exception;	
	
	public List<Attachment> getAttachmentDetails(Session session)throws Exception;
	public boolean updateAttachmentDetail(List<AttachedFilesForm> attachedList,
			GovernmentOrderForm govtForm, Session session)throws Exception;
	public List<MimetypeMaster> getMimeTypeList(Session session)throws Exception;
	public List<AttachmentMaster> getGenerateFileLocation(long filemasterid) throws Exception; 
	public boolean saveOrderDetailsEntityWise(GovernmentOrderEntityWise governmentOrderEntityWise, Session session)throws Exception;
	public List<AttachmentMaster> getUploadFileConfigurationDetails(
			long fileMasterId)throws Exception;
	public List<ViewConfigMapLocalBody> getMapConfigByState(int stateCode, char type)throws Exception;
	public String deleteAttachment(String[] attachmentId, char type)throws Exception;
	public List<MapAttachment> getMapAttachmentListbyEntity(Integer entityCode,
			char entityType)throws Exception;
	public boolean  deleteAttachmentForLandRegion(String deleteAttachmentId[]
			,Session session1 ) 
			throws Exception;
	//To get Attachment Details of Draft Village
	public List<VillageDraft> getAttachmentsbyOrderCodeForDraftVil(int villageCode) throws Exception;
	public List<GovernmentOrderDetails> fetchExistingGovernmentOrder(String orderNo, String rangeFrom, String rangeTo) throws HibernateException;
	
}
