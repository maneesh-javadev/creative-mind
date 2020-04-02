package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.ConfigurationGovernmentOrder;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.VillageDraft;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AttachedFilesForm;

public interface GovernmentOrderService {

	
// TODO Remove unused code found by UCDetector
// 	public String getGovOrderPath(Integer orderCode)throws Exception;
	public void saveGovernmentOrder(String orderNo, Date orderDate,
			Date effectiveDate, Date gazPubDate, String issuedBy,
			String orderPath, MultipartFile filename, HttpServletRequest request)throws Exception;

	public void updateGovernmentOrder(String orderNo, int orderCode,
			Date orderDate, Date effectiveDate, Date gazPubDate,
			String issuedBy, String orderPath, MultipartFile filename,
			HttpServletRequest request)throws Exception;

	public GovernmentOrder updateGovernmentOrder(String orderNo, int orderCode,
			Date orderDate, Date effectiveDate, Date gazPubDate,
			String issuedBy, HttpServletRequest request, Session session)throws Exception;

	public boolean writeMap(MultipartFile filePath, HttpServletRequest request)throws Exception;

	public List<GovernmentOrder> getOrderDetailsbyOrderCode(int orderCode)throws Exception;

	public List<Attachment> getMetaData()throws Exception;

	public boolean saveMetaData(List<AttachedFilesForm> attachedList,
			GovernmentOrderForm govtOrder)throws Exception;

	void saveGovtOrder(String orderNo, Date orderDate, Date effectiveDate,
			Date gazPubDate, String issuedBy, String orderPath,
			String filePath, HttpServletRequest request)throws Exception;

	public List<String> getMimeTypeList()throws Exception;
	public AttachmentMaster getGenerateFileLocation(long filemasterid) throws Exception;

// TODO Remove unused code found by UCDetector
// 	void saveDataInAttachment(GovernmentOrder govtOrder,
// 			GovernmentOrderForm govtForm, List<AttachedFilesForm> attachedList,
// 			Session session)throws Exception;

// TODO Remove unused code found by UCDetector
// 	boolean saveDataInGovtOrderEntityWise(GovernmentOrder govtOrder,
// 			String codes, char entityType, Session session)throws Exception;

	public AttachmentMaster getUploadFileConfigurationDetails(long fileMasterId)throws Exception;

	public List<Attachment> getAttachmentsbyOrderCode(int orderCode)throws Exception;

	public List<GetGovernmentOrderDetail> getGovtDetailsbyEntityCodeandVersion(
			Integer localBodyCode, Integer localBodyVersion, char type)throws Exception;

	public String previewTemplate(int templCode, HttpSession session)throws Exception;

	public Map<String, String> getMapAndGovtOrderConfiguration(int stateCode,
			int operationCode, char type)throws Exception;
	
	public Map<String, String> getGovtOrderConfigurationConvert(int stateCode,int operationCode, char type) throws Exception; 

	public Map<String, String> getGovtOrderConfiguration(int stateCode,
			int operationCode, char type)throws Exception;
	public List<ConfigurationGovernmentOrder> getGovtOrderConfiguration(
			int stateCode, int operationCode)throws Exception;
	


	public String deleteMetaData(String attachmentId[], char type)throws Exception;

	public AddAttachmentBean getAttachmentBeanforMap(List<CommonsMultipartFile> attachedFile,String[] titles, HttpServletRequest request)throws Exception;
	
	public AddAttachmentBean getAttachmentBeanforMapState(List<CommonsMultipartFile> attachedFile, String[] titles,HttpServletRequest request) throws Exception;

	public List<MapAttachment> getMapAttachmentListbyEntity(
			Integer localBodyCode, char entityType)throws Exception;

	public void saveDatainMapAttachment(
			List<AttachedFilesForm> attachedMapList, Integer localBodyCode,
			char type, Session session)throws Exception;
	public Map<String, String> getMapConfiguration(int stateCode,int operationCode, char type)throws Exception;

	public String deleteAttachedFiles(String[] deletedFileID1, HttpServletRequest request, char type);

	public List<GovernmentOrder> getGovtOrederDetails();
	public void saveDataInAttachmentWithOrderCode(Integer orderCode, List<AttachedFilesForm> attachedList,
			Session session) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public GovernmentOrder saveDataInGovtOrderForInvalidSubD(GovernmentOrderForm govtForm,Session session, int userId)throws Exception;
	public void saveDataInAttachmentForInvalidSubD(GovernmentOrderForm govtForm, List<AttachedFilesForm> attachedList,int maxCode,
			Session session)throws Exception;
	
	public boolean saveDataInAttachment(GovernmentOrderForm governmentOrderForm,LocalGovtBodyForm LBForm , HttpSession session,int getordercode,Session hsession) throws Exception;
	
	public void saveDataInAttachmentWithOrderCodeInGenrate(Integer orderCode, GenerateDetails filesForm,
			Session session) throws Exception; 
	
	public Integer saveDatainMapAttachmentforLandregion(
			List<AttachedFilesForm> attachedMapList, Integer entityCode,
			char entityType, Session session) throws Exception;
	
	public boolean deleteMapAttachementforLandRegion(Integer entityCode,char entityType,Session session) throws Exception;
	public AddAttachmentBean getAttachmentBeanforMapLandregion(
			List<CommonsMultipartFile> attachedFile, String[] titles,
			HttpServletRequest request) throws Exception;
	public AddAttachmentBean getAttachmentBeanforGovLandregion(
			List<CommonsMultipartFile> attachedFile, String[] titles,
			HttpServletRequest request) throws Exception;
	//Get the attachment details for Draft Village Form
	public List<VillageDraft> getAttachmentsbyOrderCodeForDraftVil(int villageCode)throws Exception;
// TODO Remove unused code found by UCDetector
// 	/**
// 	 *Fetch GovermentDetails
// 	 * @param orderNo
// 	 * @param rangeFrom
// 	 * @param rangeTo
// 	 * @return
// 	 * @throws HibernateException
// 	 */
// 	public List<GovernmentOrderDetails> fetchExistingGovernmentOrder(String orderNo, String rangeFrom, String rangeTo) throws HibernateException;
}

