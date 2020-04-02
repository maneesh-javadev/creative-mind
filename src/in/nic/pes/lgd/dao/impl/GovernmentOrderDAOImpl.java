package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderDetails;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.MimetypeMaster;
import in.nic.pes.lgd.bean.ViewConfigMapLocalBody;
import in.nic.pes.lgd.bean.VillageDraft;
import in.nic.pes.lgd.dao.GovernmentOrderDAO;
import in.nic.pes.lgd.forms.GovernmentOrderForm;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pes.attachment.util.AttachedFilesForm;

@Repository
@Transactional
public class GovernmentOrderDAOImpl implements GovernmentOrderDAO {
	private static final Logger log = Logger.getLogger(GovernmentOrderDAOImpl.class);
	@Autowired
	SessionFactory sessionFactory;

	public void saveGovernmentOrder(GovernmentOrder govtOrder)throws Exception {

		Session session = null;
		Transaction txn = null;

		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			session.save(govtOrder);
			txn.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(txn != null){
				txn.rollback();
			}	
		}

		finally {
			if(session != null && session.isOpen())
			{	
				session.close();
			}	
		}
	}
	
	
	public String getGovOrderPathDAO(Integer orderCode,String SQL,Session hsession)throws Exception {
		
		String path=null;
		try
		{
		path= hsession.createSQLQuery(SQL).toString();
		}catch(Exception e)
		{
			log.debug("Exception" + e);
		}
		
		return path;
	}

	/*@Override
	public int saveGovernmentOrder1(GovernmentOrder govtOrder)throws Exception {

		Session session = null;
		Transaction txn = null;
		int orderCode = 0;

		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			orderCode = (Integer) session.save(govtOrder);
			txn.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(txn != null){
				txn.rollback();
			}	
		}

		finally {
			if(session != null && session.isOpen())
			{	
				session.close();
			}	
		}
		return orderCode;
	}
*/
	@Override
	public void updateGovernmentOrder(GovernmentOrder govtOrder)throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction txn = null;

		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			session.update(govtOrder);
			txn.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(txn != null){
				txn.rollback();
			}	
		}

		finally {
			if(session != null && session.isOpen())
			{	
				session.close();
			}	
		}

	}

	@Override
	public void updateGovernmentOrder(GovernmentOrder govtOrder, Session session)throws Exception {
		// TODO Auto-generated method stub
		try {
			session.update(govtOrder);
			session.flush();
			if(session.contains(govtOrder)){
				session.evict(govtOrder);
			}	
		} catch (Exception e) {
			log.debug("Exception" + e);

		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GovernmentOrder> getOrderDetailsbyOrderCode(int orderCode)throws Exception {

		Session session = null;
		Query query = null;
		List<GovernmentOrder> govtorderList = null;
		try {
			session = sessionFactory.openSession();
			query = session
					.createQuery("from GovernmentOrder where orderCode=:orderCode");
			query.setParameter("orderCode", orderCode, Hibernate.INTEGER);
			govtorderList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return govtorderList;
	}

	@Override
	public boolean updateAttachmentDetail(List<AttachedFilesForm> attachedList,
			GovernmentOrderForm govtForm, Session session)throws Exception {
		long createdby = 4000;
		GovernmentOrder govtOrder =null;
		try {

			govtOrder = saveGovernmentOrder(govtForm, session,
					createdby);

		 
		for( AttachedFilesForm filesForm : attachedList){
			 
				Attachment attachment = new Attachment();
				attachment.setGovernmentOrder(govtOrder);
				attachment.setFileName(filesForm.getFileName());
				attachment.setFileSize(filesForm.getFileSize());
				attachment.setFileContentType(filesForm.getFileType());
				attachment.setFileLocation(filesForm.getFileLocation());
				attachment.setFileTimestamp(filesForm.getFileTimestampName());
				session.save(attachment);
				session.flush();
				if (session.contains(attachment)){
					session.evict(attachment);
				}	
			}

			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}

	}

	/**
	 * @param govtForm
	 * @param session
	 * @param createdby
	 * @throws HibernateException
	 */
	private GovernmentOrder saveGovernmentOrder(GovernmentOrderForm govtForm,
			Session session, long createdby) throws Exception {

		GovernmentOrder govtOrder = null;
		try {
			govtOrder = new GovernmentOrder();
			govtOrder.setOrderNo(govtForm.getOrderNo());
			govtOrder.setOrderDate(govtForm.getOrderDate());
			govtOrder.setEffectiveDate(govtForm.getEffectiveDate());
			govtOrder.setGazPubDate(govtForm.getGazPubDate());
			govtOrder.setCreatedby(createdby);
			govtOrder.setCreatedon(new Date());
			govtOrder.setIssuedBy("GOVERNOR");
			govtOrder.setUserId(createdby);

			session.save(govtOrder);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return govtOrder;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> getAttachmentDetails(Session session)throws Exception {
		Criteria criteria = null;
		List<Attachment> list=null;
		try {
			criteria = session.createCriteria(Attachment.class);
			list = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MimetypeMaster> getMimeTypeList(Session session)throws Exception {
		Criteria criteria = null;
		List<MimetypeMaster> mimeTypeList=null;
		try {
			criteria = session.createCriteria(MimetypeMaster.class);
			mimeTypeList = criteria.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		}
		return mimeTypeList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AttachmentMaster> getGenerateFileLocation(long filemasterid)throws Exception 
	{
		List<AttachmentMaster> fileDetailsList = null;
		Session session = null;
		Query query = null;
		try
		{
			session = sessionFactory.openSession();
			query = session.createQuery("from AttachmentMaster where fileMasterId=:fileMasterId");
			query.setParameter("fileMasterId", filemasterid, Hibernate.LONG);
			fileDetailsList = query.list();
		}
		catch (Exception ex) 
		{
			log.debug("Exception" + ex);
		}
		finally 
		{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return fileDetailsList;
	}
	
	@Override
	public boolean saveOrderDetailsEntityWise(
			GovernmentOrderEntityWise governmentOrderEntityWise, Session session)throws Exception {
		try {

			session.save(governmentOrderEntityWise);

			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	public List<AttachmentMaster> getUploadFileConfigurationDetails(
			long fileMasterId) throws Exception{
		Session session = null;
		Query query = null;
		
		List<AttachmentMaster> fileDetailsList = null;
		try {
			session = sessionFactory.openSession();
			query = session
					.createQuery("from AttachmentMaster where fileMasterId=:fileMasterId");
			query.setParameter("fileMasterId", fileMasterId, Hibernate.LONG);
			fileDetailsList = query.list();
		} catch (Exception ex) {
			log.debug("Exception" + ex);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return fileDetailsList;
	}

	@SuppressWarnings("unchecked")
	public List<ViewConfigMapLocalBody> getMapConfigByState(int stateCode,
			char type)throws Exception {
		Session session = null;
		Query query = null;
		List<ViewConfigMapLocalBody> getMapConfig = null;
		try {
			session = sessionFactory.openSession();
			if (type == 'P'){
				query = session.getNamedQuery("getConfigMapdetailforPLB")
						.setParameter("stateCode", stateCode);
			}	
			else if (type == 'T'){
				query = session.getNamedQuery("getConfigMapdetailforTLB")
						.setParameter("stateCode", stateCode);
			}	
			else if (type == 'U'){
				query = session.getNamedQuery("getConfigMapdetailforULB")
						.setParameter("stateCode", stateCode);
			}	
			getMapConfig = query.list();
			if (!getMapConfig.isEmpty() && getMapConfig.get(0) != null){
				return getMapConfig;
			}	

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String deleteAttachment(String[] attachmentId,char type)throws Exception {
		Session session = null;
		Criteria criteria = null;
 		try {
			session = sessionFactory.openSession();
			
			String attachmentIdOne[] = attachmentId;
			for (int i = 0; i < attachmentIdOne.length; i++) {
				String ID = attachmentIdOne[i];
				Long deleteAttachmentId = Long.parseLong(ID);
				if (type == 'O') {
					criteria = session.createCriteria(Attachment.class)
							.add(Restrictions.eq("attachmentId",
									deleteAttachmentId));

					Iterator<Attachment> it = criteria.list().iterator();
					while (it.hasNext()) {
						Attachment attachment = (Attachment) it.next();
						session.delete(attachment);
						session.flush();
						if(session.contains(attachment)){
							session.evict(attachment);
						}	
					
					}
				} else if (type == 'M') {
					criteria = session.createCriteria(MapAttachment.class)
							.add(Restrictions.eq("attachmentId",
									deleteAttachmentId));

					Iterator<MapAttachment> it = criteria.list().iterator();
					while (it.hasNext()) {
						MapAttachment attachment = (MapAttachment) it.next();
						session.delete(attachment);
						session.flush();
						if(session.contains(attachment)){
							session.evict(attachment);
						}	
					}
				}
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return "deleteSuccessFully";

	}

	@SuppressWarnings("unchecked")
	public List<MapAttachment> getMapAttachmentListbyEntity(Integer entityCode,
			char entityType)throws Exception {
		Session session = null;
		Query query = null;
		List<MapAttachment> mapAttachmentList = null;
		try {
			session = sessionFactory.openSession();
			query = session
					.createQuery(
							"from MapAttachment where map_lc =:entityCode and mapEntityType =:entityType")
					.setParameter("entityCode", entityCode)
					.setParameter("entityType", entityType);
			mapAttachmentList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return mapAttachmentList;
	}
	
	public boolean deleteAttachmentForLandRegion(String deleteAttachmentId[]
												,Session session1 ) 
												throws Exception 
	{
		try
		{
		
			for (int i = 0; i < deleteAttachmentId.length; i++) {
				
				String ID = deleteAttachmentId[i];
				Long deleteAttachmentIdLong = Long.parseLong(ID);
				Criteria criteria = null;
				criteria = session1.createCriteria(Attachment.class)
													.add(Restrictions.eq("attachmentId",
																		deleteAttachmentIdLong));

				@SuppressWarnings("unchecked")
				Iterator<Attachment> it = criteria.list().iterator();
				while (it.hasNext()) {
				Attachment attachment = (Attachment) it.next();
				session1.delete(attachment);
				session1.flush();
				if(session1.contains(attachment)){
					session1.evict(attachment);
				}	
				}
				
			}
			return true;
		}catch(Exception e)
		{
			log.debug("Exception" + e);
			return false;
		}
		
	}	
	//To get Attachment Details of Draft Village
	@SuppressWarnings("unchecked")
	@Override
	public List<VillageDraft> getAttachmentsbyOrderCodeForDraftVil(int villageCode) throws Exception {
		Session session = null;
		Query query = null;
		List<VillageDraft> vilDraftAttachmentList = new ArrayList<VillageDraft>();
		try {
			session = sessionFactory.openSession();
			query = session.createQuery(
					"from VillageDraft where villageCode=:village_code")
					.setParameter("village_code", villageCode, Hibernate.INTEGER);
			vilDraftAttachmentList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return vilDraftAttachmentList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GovernmentOrderDetails> fetchExistingGovernmentOrder(String orderNo, String rangeFrom, String rangeTo) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.fetchExistingGovernmentOrder execution started.");
		Session session = null;
		List<GovernmentOrderDetails> governmentOrderList = new ArrayList<GovernmentOrderDetails>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Fetch_Government_Order_Details");
			query.setParameter("orderNo", orderNo);
			query.setParameter("rangeFrom", rangeFrom, Hibernate.STRING);
			query.setParameter("rangeTo", rangeTo, Hibernate.STRING);
			governmentOrderList = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.fetchExistingGovernmentOrder : ", e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return governmentOrderList;
	}

	

}