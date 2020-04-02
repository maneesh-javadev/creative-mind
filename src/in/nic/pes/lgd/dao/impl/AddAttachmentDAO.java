package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.MimetypeMaster;
import in.nic.pes.lgd.dao.IAddAttachmentDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pes.attachment.util.AttachedFilesForm;
/**
 * Date 23-01-2013
 * @author Sushil
 *
 */
@Repository
@Transactional
public class AddAttachmentDAO  implements IAddAttachmentDAO { // NO_UCD (unused code)
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public String saveMetaData(List<AttachedFilesForm> attachedFileList,String formId,Integer rowid) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction txn = null;
		try{
			txn = session.beginTransaction();
			Iterator<AttachedFilesForm> it = attachedFileList.iterator();
			while(it.hasNext()) {
				AttachedFilesForm filesForm = (AttachedFilesForm)it.next();
				Attachment attachment = new Attachment();
				attachment.setAttachmentId(123L);
				attachment.setFileTitle(filesForm.getFileTitle());
				attachment.setFileName(filesForm.getFileName());
				attachment.setFileSize(filesForm.getFileSize());
				attachment.setFileContentType(filesForm.getFileType());
				attachment.setFileLocation(filesForm.getFileLocation());
				attachment.setFileTimestamp(filesForm.getFileTimestampName());
				session.save(attachment);
			}
			txn.commit();
		} finally{
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		return "saveSuccessfully";
	}

	@Override
	public String deleteMetaData(String[] attachmentId) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction txn = null;
		try{
			txn = session.beginTransaction();
			txn.begin();
			String attachmentIdOne[] = attachmentId;
			for(int i = 0; i<attachmentIdOne.length; i++) {
				String ID = attachmentIdOne[i];				
				Query qry = session.createQuery("delete from Attachment S where S.attachmentId=:attachId");        		
				qry.setParameter("attachId", new Long(ID));
				qry.executeUpdate();
			}
			txn.commit();
		}
		finally {
			closeEntityManager(session);
		}
		return "deleteSuccessFully";
	}
	
	private void closeEntityManager(Session session) {
		if(session != null && session.isOpen() ) {
			session.close();
		}
	}

	@Override
	public String deleteMetaData(String formId,Integer rowIdentifier) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction txn = null;
		try { 
			txn = session.beginTransaction();
			txn.begin();
			Query qry = session.createQuery("delete from Attachment S where S.formId=:formId and S.rowIdentifier=:rowIdentifier");
			qry.setParameter("formId", formId);
			qry.setParameter("rowIdentifier", new Integer(rowIdentifier));
			qry.executeUpdate();			
		    txn.commit();
		}
		finally {
			closeEntityManager(session);
		}
		return "deleteSuccessFully";
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> getMetaData(String formId) throws Exception {
		List<Attachment> list=null;
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("select new Attachment(S.fileName,S.fileTimestamp,S.fileTitle)  from Attachment S where S.formId=:formId");
			query.setParameter("formId", formId);
			list = query.list();
		} finally {
			closeEntityManager(session);
		}
		return list;		
	}
	@SuppressWarnings("unchecked")
	@Override 
	public List<Attachment> getMetaData(String formId, Integer rowIdentifier)throws Exception{
		List<Attachment> list=null;
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("select new Attachment(S.fileName,S.fileTimestamp) from Attachment S where S.formId=:formId and S.rowIdentifier=:rowIdentifier");
			query.setParameter("formId", formId);
			query.setParameter("rowIdentifier", new Integer(rowIdentifier));
			list = query.list();
		} finally {
			closeEntityManager(session);
		}
		return list;	
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> getAllMetaDataInfo(String formId) throws Exception {
		List<Attachment> list=null;
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("select new Attachment(S.attachmentId,S.fileTitle,S.fileName,S.fileSize,S.fileContentType,S.fileTimestamp,S.rowIdentifier) from Attachment S where S.formId=:formId");
			query.setParameter("formId", formId);
			list = query.list();
		} finally {
			closeEntityManager(session);
		}
		return list;		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> getAllMetaDataInfo(String formId, Integer rowIdentifier) throws Exception {
		List<Attachment> list=null;
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("select new Attachment(S.attachmentId,S.fileTitle,S.fileName,S.fileSize,S.fileContentType,S.fileTimestamp,S.rowIdentifier)  from Attachment S where S.formId=:formId and S.rowIdentifier=:rowIdentifier");
			query.setParameter("formId", formId);
			query.setParameter("rowIdentifier", new Integer(rowIdentifier));
			list = query.list();
		} finally {
			closeEntityManager(session);
		}
		return list;		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getMimeTypeList() throws Exception {
		List<String> allowedMimeTypeList = new ArrayList<String>();
		List<MimetypeMaster> mimeTypeList = null;
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("select S from MimetypeMaster S");
			mimeTypeList = query.list();
			Iterator<MimetypeMaster> iterator = mimeTypeList.iterator();
			while(iterator.hasNext()) {
				MimetypeMaster mtm = (MimetypeMaster)iterator.next();
				String mimeType = mtm.getMimetypeName();
				allowedMimeTypeList.add(mimeType);
			}
		} finally {
			closeEntityManager(session);
		}		
		return allowedMimeTypeList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public AttachmentMaster getAttachmentMaster() {
		AttachmentMaster attachmentMaster=new AttachmentMaster();
		List<AttachmentMaster> 	attachmentMasterList=new ArrayList<AttachmentMaster>();
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("select S from AttachmentMaster S");
			attachmentMasterList = query.list();
			attachmentMaster=attachmentMasterList.get(0);
		} finally {
			closeEntityManager(session);
		}	
		return attachmentMaster;
	}
	@SuppressWarnings("unchecked")
	@Override
	public AttachmentMaster getAttachmentMaster(Long fileMasterId) {
		AttachmentMaster attachmentMaster=new AttachmentMaster();
		List<AttachmentMaster> 	attachmentMasterList=new ArrayList<AttachmentMaster>();
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("select S from AttachmentMaster S where S.fileMasterId=:fileMasterId");
			query.setParameter("fileMasterId", new Long(fileMasterId));
			attachmentMasterList = query.list();
			attachmentMaster=attachmentMasterList.get(0);
		} finally {
			closeEntityManager(session);
		}	
		return attachmentMaster;
	}

	@Override
	public String deleteSingleFile(Long attachmentId) {
		Session session = sessionFactory.openSession();
		Transaction txn = null;
		try{    
			txn = session.beginTransaction();	
			txn.begin();
			Query qry = session.createQuery("delete from Attachment S where S.attachmentId=:attachmentId");
			qry.setParameter("attachmentId", attachmentId);
			qry.executeUpdate();			
		    txn.commit();
		}
		finally {
			closeEntityManager(session);
		}
		return "deleteSuccessFully";
	}
}
