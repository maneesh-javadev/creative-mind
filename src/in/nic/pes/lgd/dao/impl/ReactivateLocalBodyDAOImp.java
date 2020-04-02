package in.nic.pes.lgd.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cmc.lgd.localbody.rules.LocalBodyConstant;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.common.SubAttachmentHandler;
import in.nic.pes.lgd.dao.ReactivateLocalBodyDAO;
import in.nic.pes.lgd.draft.service.DraftUtilService;
import in.nic.pes.lgd.forms.InvalidateLocalBodyForm;
import in.nic.pes.lgd.response.InvalidatedVillageResponse;
import in.nic.pes.lgd.response.Response;
import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AttachedFilesForm;

public class ReactivateLocalBodyDAOImp implements ReactivateLocalBodyDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	DraftUtilService draftUtilService;
	
	@Override
	public List<Operations> getInvalidateType() {
		List<Operations> operations=null;
		Session session=null;
		List<Integer>  operationCodes=new ArrayList<Integer>();
		try{
			operationCodes.add(57);
			operationCodes.add(58);
			operationCodes.add(59);
			session=sessionFactory.openSession();
			Query query=session.createQuery("from Operations where operationCode in(:operationCodes)");
			query.setParameterList("operationCodes", operationCodes);
			operations=query.list();
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		finally {
			session.close();
		}
		return operations;
	}

	@Override
	public List<Object> getInvalidatedLocalBody(int operationCodes,int slc) {
		Session session=null;
		List<Object> objects=null;
		try{
			session =sessionFactory.openSession();
			
			Query query=session.createSQLQuery("select b.local_body_code,b.local_body_type_code,b.flag_code, et.tid,et.effective_date,et.description,b.local_body_name_english,b.local_body_version from   " + 
					"(select max(local_body_version) as local_body_version,local_body_code from localbody where slc=:slc group by local_body_code)a" + 
					",(select max(minor_version) as minor_version,local_body_version,local_body_code from localbody where slc=:slc group by local_body_code,local_body_version)c," + 
					"(select isactive,flag_code,local_body_name_english,local_body_type_code,local_body_version,minor_version ,local_body_code ,lblc,transaction_id from   " + 
					" localbody where slc=:slc and isactive=false) b," + 
					" entity_transactions et,local_body_type lt " + 
					" where a.local_body_version = b.local_body_version and a.local_body_code=b.local_body_code   " + 
					" and et.operation_code=:operationCode and b.transaction_id=et.tid and et.slc=:slc and lt.local_body_type_code=b.local_body_type_code and lt.isactive and (lt.category='U' or lt.level='V') " + 
					" and a.local_body_code=c.local_body_code and a.local_body_version=c.local_body_version and c.minor_version=b.minor_version" + 
					" order by 7 ");
			query.setParameter("operationCode", operationCodes);
			query.setParameter("slc", slc);
			objects=query.list();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			session.close();
		}
		return objects;
	}

	@Override
	public List<Object> validateReactivation(final InvalidateLocalBodyForm invalidateLocalBody) throws Exception {
		Session session=null;
		Query query=null;
		try {
			System.out.println("inside ReactivateLocalBodyDAOImp.validateReactivation()");
			System.out.println("validateReactivation/Submition started from sysout at : " + new Date());
			session =sessionFactory.openSession();
			query = session.createSQLQuery(" select * from reactivate_localbody(?,?,?,?,?,?,?,?,?) ");
			query.setParameter(0, invalidateLocalBody.getLocalBodyCode(),Hibernate.INTEGER);
			query.setParameter(1, invalidateLocalBody.getUserId().intValue(),Hibernate.INTEGER);
			query.setParameter(2, invalidateLocalBody.getEffectiveDate(),Hibernate.DATE);
			query.setParameter(3, invalidateLocalBody.getOrderNo() == null ? null: invalidateLocalBody.getOrderNo() ,Hibernate.STRING);
			query.setParameter(4, invalidateLocalBody.getOrderDate(),Hibernate.DATE);
			query.setParameter(5, invalidateLocalBody.getLocalBodyTypeCode(),Hibernate.INTEGER);
			query.setParameter(6, invalidateLocalBody.getFlagCode(),Hibernate.INTEGER);
			query.setParameter(7, invalidateLocalBody.getGazPubDate(),Hibernate.DATE);
			query.setParameter(8, invalidateLocalBody.getOrderPath(),Hibernate.STRING);
			@SuppressWarnings("unchecked")
			List<Object> validatedData = query.list();
			System.out.println("validateReactivation/Submition ended from sysout at : " + new Date());
			System.out.println("outside ReactivateLocalBodyDAOImp.validateReactivation()");
			return validatedData;
		}catch(final Exception ex) {
			ex.printStackTrace();
			return null;
		}finally{
			session.flush();
			session.close();
		}
	}
	
	public void submitGovernmentOrder(final List<Object> updatedGp, List<CommonsMultipartFile> attachementList) {
		Session session = null;
		try {
			int orderCode = 0;
			session = sessionFactory.openSession();
			Long fileMasterId = Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_GO.toString());
			for (Object object : updatedGp) {
				Object[] dataObject = (Object[]) object;
				orderCode = Integer.parseInt(dataObject[7].toString());
			}
			GovernmentOrder govtOrder = (GovernmentOrder) session.get(GovernmentOrder.class, orderCode);
			System.out.println("ReactivateLocalBodyDAOImp.submitGovernmentOrder()");
			System.out.println("Submittion started at :  " + new Date());
			saveDataInAttachment(govtOrder, attachementList, fileMasterId, session);
			System.out.println("saveDataInAttachment started");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush();
			session.close();
		}
	}
	
	public void saveDataInAttachment(GovernmentOrder govtOrder , List<CommonsMultipartFile> attachementList,Long fileMasterId, Session session) throws Exception {
		System.out.println("ReactivateLocalBodyDAOImp.saveDataInAttachment() started");
		Attachment attachment = null;
		try {
			if (attachementList != null && !attachementList.isEmpty()) {
				AttachmentMaster master = draftUtilService.getUploadFileConfigurationDetails(fileMasterId);
				AddAttachmentBean attachmentBean = new AddAttachmentBean();
				attachmentBean.setUploadLocation(master.getFileLocation());
				attachmentBean.setCurrentlyUploadFileList(attachementList);
				SubAttachmentHandler attachmentHandler = new SubAttachmentHandler();
				String validateAttachment = attachmentHandler.validateAttachment(attachmentBean);
				List<AttachedFilesForm> metaInfoList = attachmentHandler.getMetaDataListOfFiles(attachmentBean);
				if (metaInfoList != null && "validationSuccessFullyDone".equals(validateAttachment)) {
					String saveAttachment = attachmentHandler.saveMetaDataINFileSystem(metaInfoList, attachmentBean);
					if ("saveSuccessFully".equals(saveAttachment)) {
						AttachedFilesForm attachedFilesForm=metaInfoList.get(0);
						attachment = new Attachment();
						attachment.setGovernmentOrder(govtOrder);
						attachment.setFileName(attachedFilesForm.getFileName());
						attachment.setFileSize(attachedFilesForm.getFileSize());
						attachment.setFileContentType(attachedFilesForm.getFileType());
						attachment.setFileLocation(attachmentBean.getUploadLocation().replaceAll("\\\\", "/")+"/" + attachedFilesForm.getFileTimestampName());
						attachment.setFileTimestamp(attachedFilesForm.getFileTimestampName());
						session.save(attachment);
					}
				}
				
				
				
				
			}
			System.out.println("ReactivateLocalBodyDAOImp.saveDataInAttachment() started");
		} catch (Exception e) {
		e.printStackTrace();
		}
	}

	@Override
	public List<Object> getInvalidatedVillages(int operationCodes, int slc, int dlc) {
		Session session=null;
		List<Object> objects=null;
		try{
			session =sessionFactory.openSession();
			Query query=session.createSQLQuery("select b.village_code,b.village_name_english, et.description, et.effective_date, b.flag_code, et.tid,b.village_version from entity_transactions et, " + 
					" ( select max(village_version) as village_version,village_code from village  where slc=:slc and dlc=:dlc group by village_code)a," + 
					" ( select village_version,max(minor_version) as minor_version,village_code from village  where slc=:slc and dlc=:dlc group by village_code,village_version)x ," + 
					" (select v.isactive,v.flag_code,v.village_name_english,v.village_version as village_version,v.village_code ,v.transaction_id, v.dlc,v.minor_version from " + 
					" village v where v.slc=:slc and v.dlc=:dlc ) b  where  a.village_version = b.village_version and a.village_code=b.village_code and et.operation_code=:operationCode and et.slc=:slc and   b.transaction_id=et.tid and x.village_code=b.village_code and x.village_version=b.village_version and x.minor_version= b.minor_version and b.isactive=false order by 2 asc");
			query.setParameter("operationCode", operationCodes);
			query.setParameter("slc", slc);
			query.setParameter("dlc", dlc);
			objects=query.list();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			session.close();
		}
		return objects;
	}

	@Override
	public Response performReactivationOfVillage(InvalidatedVillageResponse invalidatedVillageResponse) throws Exception{
		Session session=null;
		Query query=null;
		Response response = new Response();
		try {
			System.out.println("performReactivationOfVillage started from sysout at : " + new Date());
			session =sessionFactory.openSession();
			query = session.createSQLQuery(" select * from reactivate_village(?,?) ");
			query.setParameter(0, invalidatedVillageResponse.getVillageCode(),Hibernate.INTEGER);
			query.setParameter(1, invalidatedVillageResponse.getUserId(),Hibernate.LONG);
			String retValue  = (String)query.uniqueResult();
			if(retValue!=null && retValue.length()>0){
				String retValArr[]=retValue.split("#");
				if(Integer.parseInt(retValArr[1])==200){
					response.setResponseCode(200);
				}else{
					response.setResponseCode(500);
				}
				response.setResponseMessage(retValArr[2]);	
			}
			else{
				response.setResponseCode(500);
				response.setResponseMessage("Exception to perform reactivate village");	
			}
			return response;
		}catch(Exception e) {
			e.printStackTrace();
			response.setResponseCode(500);
			response.setResponseMessage("Exception to perform reactivate village");	
			return response;
		}
		finally{
			session.flush();
			session.close();
		}
	}
}
