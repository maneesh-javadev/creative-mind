package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.FreezeAttachment;
import in.nic.pes.lgd.bean.FreezeDistrictBean;
import in.nic.pes.lgd.bean.FreezeStatusConstituency;
import in.nic.pes.lgd.bean.LocalbodyPRIDistrictFreeze;
import in.nic.pes.lgd.bean.MappedLbWardDisttrictWise;
import in.nic.pes.lgd.bean.NodalOfficer;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.dao.DistrictFreezeDao;
import in.nic.pes.lgd.utils.ApplicationConstant;
import pes.attachment.util.AttachedFilesForm;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @Author Vinay Yadav
 * @since 12 Feb 2015
 * 
 * 
 */
public class DistrictFreezeDAOImpl implements DistrictFreezeDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger log = Logger.getLogger(DistrictFreezeDAOImpl.class);
	
	/**
	 * 
	 * @param session
	 */
	private void closeConnection (Session session) {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	@Override
	public FreezeDistrictBean getDestrictDetails(Integer districtCode) throws Exception {
		log.info("DistrictFreezeDAOImpl.getDestrictDetails execution started.");
		Session session = null;
		FreezeDistrictBean districtDetails = new FreezeDistrictBean();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("DISTRICT_FREEZE_DETAILS");
			query.setParameter("dlc", districtCode, Hibernate.INTEGER);
			query.setParameter("lblrType", ApplicationConstant.LAND_REGION_LEVEL_CODE);
			
			districtDetails = (FreezeDistrictBean) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Error in DistrictFreezeDAOImpl.getDestrictDetails : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return districtDetails;
	}
	
	@Override
	public Boolean freezeUnFreezeDistrictEntity(FreezeDistrictBean freezeDistrictBean) throws Exception {
		log.info("DistrictFreezeDAOImpl.freezeUnFreezeDistrictEntity execution started.");
		Session session = null;
		Boolean status = Boolean.FALSE;
		try {
					
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select * from freeze_unfreeze_entity_fn(:userType, :entityType, :entityCode, :userId, :ipAddress, :processType, :reason, :lblrType)");
			query.setParameter("userType"	, ApplicationConstant.DISTRICT_LEVEL_CODE		 , Hibernate.STRING);
			query.setParameter("entityType"	, ApplicationConstant.DISTRICT_LEVEL_CODE		 , Hibernate.STRING);
			query.setParameter("entityCode"	, freezeDistrictBean.getDistrictCode()	 		 , Hibernate.INTEGER);
			query.setParameter("userId"		, freezeDistrictBean.getUserId()		 		 , Hibernate.INTEGER);
			query.setParameter("ipAddress"	, freezeDistrictBean.getIpAddress()		 		 , Hibernate.STRING);
			query.setParameter("processType", freezeDistrictBean.getProcessAction().charAt(0), Hibernate.CHARACTER);
			query.setParameter("reason"		, freezeDistrictBean.getReason()				 , Hibernate.STRING);
			query.setParameter("lblrType"	, freezeDistrictBean.getLbLRType()				 , Hibernate.STRING);
			status = (Boolean) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Error in FreezeDistrict.freezeUnFreezeDistrictEntity : ", e);
			System.out.println(e.getCause().getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Village> draftedVillagesFromDistrict(Integer districtCode) throws Exception {
		log.info("DistrictFreezeDAOImpl.draftedVillagesFromDistrict execution started.");
		Session session = null;
		List<Village> draftedVillages = new ArrayList<Village>();
		try {
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery("select village_code as villageCode,village_name_english as villageNameEnglish from does_entity_child_in_draft_exist_fn(:parentType, :parentCode, :childType)");
			query.setParameter("parentType", ApplicationConstant.DISTRICT_LEVEL_CODE, Hibernate.STRING);
			query.setParameter("parentCode", districtCode, Hibernate.INTEGER);
			query.setParameter("childType" , ApplicationConstant.VILLAGE_LEVEL_CODE, Hibernate.STRING);
			query.addScalar("villageCode");
			query.addScalar("villageNameEnglish");
			draftedVillages = query.list();
		} catch (HibernateException e) {
			log.error("Error in DistrictFreezeDAOImpl.draftedVillagesFromDistrict : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return draftedVillages;
	}

	@Override
	public FreezeDistrictBean getDestrictDetailsForPRI(Integer districtCode) throws Exception {
		log.info("DistrictFreezeDAOImpl.getDestrictDetailsForPRI execution started.");
		Session session = null;
		FreezeDistrictBean districtDetails = new FreezeDistrictBean();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("DISTRICT_FREEZE_DETAILS");
			query.setParameter("dlc", districtCode, Hibernate.INTEGER);
			query.setParameter("lblrType", ApplicationConstant.LOCAL_BODY_PRI_LEVEL_CODE);
			
			districtDetails = (FreezeDistrictBean) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Error in FreezeDistrict.getDestrictDetailsForPRI : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return districtDetails;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyPRIDistrictFreeze> freezeUnFreezeDistrictEntityCheckByDistrictId(Integer districtCode, String lB) throws Exception {
		log.info("DistrictFreezeDAOImpl.freezeUnFreezeDistrictEntityCheckByDistrictId execution started.");
		Session session = null;
		SQLQuery query = null;
		List<LocalbodyPRIDistrictFreeze> freezeExists = new ArrayList<LocalbodyPRIDistrictFreeze>();
		try {
			session = sessionFactory.openSession();
			if(lB.equalsIgnoreCase("P")) {
				query = session.createSQLQuery("select lblc as lblc,lb_name as lbName,status as lbStatus  from does_lb_pri_in_freeze_exist_fn(:districtCode)");
			} else 
			if(lB.equalsIgnoreCase("T")) {
				query = session.createSQLQuery("select lblc as lblc,lb_name as lbName,status as lbStatus  from does_lb_trd_in_freeze_exist_fn(:districtCode)");
			} else
			if(lB.equalsIgnoreCase("U")) {
				query = session.createSQLQuery("select lblc as lblc,lb_name as lbName,status as lbStatus  from does_lb_urb_in_freeze_exist_fn(:districtCode)");
			}
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			query.addScalar("lblc");
			query.addScalar("lbName");
			query.addScalar("lbStatus");
			query.setResultTransformer(Transformers.aliasToBean(LocalbodyPRIDistrictFreeze.class));
			
			freezeExists = query.list();
		} catch (HibernateException e) {
			log.error("Error in DistrictFreezeDAOImpl.freezeUnFreezeDistrictEntityCheckByDistrictId : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return freezeExists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyPRIDistrictFreeze> draftedVillagesPRIFromDistrict(Integer districtCode, String lB) throws Exception {
		log.info("DistrictFreezeDAOImpl.draftedVillagesFromDistrict execution started.");
		Session session = null;
		SQLQuery query = null;
		List<LocalbodyPRIDistrictFreeze> draftExists = new ArrayList<LocalbodyPRIDistrictFreeze>();
		try {
			session = sessionFactory.openSession();
			if(lB.equalsIgnoreCase("P")) {
				query = session.createSQLQuery("select lblc as lblc,lb_name as lbName from does_lb_pri_child_in_draft_exist_fn(:districtCode)");
			} else 
			if(lB.equalsIgnoreCase("T")) {
				query = session.createSQLQuery("select lblc as lblc,lb_name as lbName from does_lb_trd_child_in_draft_exist_fn(:districtCode)");
			} else
			if(lB.equalsIgnoreCase("U")) {
				query = session.createSQLQuery("select lblc as lblc,lb_name as lbName from does_lb_urb_child_in_draft_exist_fn(:districtCode)");
			}
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			query.addScalar("lblc");
			query.addScalar("lbName");
			query.setResultTransformer(Transformers.aliasToBean(LocalbodyPRIDistrictFreeze.class));
			
			draftExists = query.list();
		} catch (HibernateException e) {
			log.error("Error in DistrictFreezeDAOImpl.draftedVillagesPRIFromDistrict : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return draftExists;
	}
	
	@Override
	public Boolean freezeUnFreezeDistrictPRIEntity(FreezeDistrictBean freezeDistrictBean) throws Exception {
		log.info("DistrictFreezeDAOImpl.freezeUnFreezeDistrictPRIEntity execution started.");
		System.out.println("call freeze_unfreeze_entity_PRI_fn method");
		Session session = null;
		Boolean status = Boolean.FALSE;
		try {
					
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select * from freeze_unfreeze_entity_PRI_fn(:userType, :entityType, :entityCode, :userId, :ipAddress, :processType, :reason, :lblrType)");
			query.setParameter("userType"	, ApplicationConstant.DISTRICT_LEVEL_CODE		 , Hibernate.STRING);
			query.setParameter("entityType"	, ApplicationConstant.DISTRICT_LEVEL_CODE		 , Hibernate.STRING);
			query.setParameter("entityCode"	, freezeDistrictBean.getDistrictCode()	 		 , Hibernate.INTEGER);
			query.setParameter("userId"		, freezeDistrictBean.getUserId()		 		 , Hibernate.INTEGER);
			query.setParameter("ipAddress"	, freezeDistrictBean.getIpAddress()		 		 , Hibernate.STRING);
			query.setParameter("processType", freezeDistrictBean.getProcessAction().charAt(0), Hibernate.CHARACTER);
			query.setParameter("reason"		, freezeDistrictBean.getReason()				 , Hibernate.STRING);
			query.setParameter("lblrType"	, freezeDistrictBean.getLbLRType()				 , Hibernate.STRING);
			status = (Boolean) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Error in DistrictFreezeDAOImpl.freezeUnFreezeDistrictPRIEntity : ", e);
			System.out.println(e.getCause().getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}

	@Override
	public FreezeDistrictBean getDestrictDetailsForTRD(Integer districtCode) {
		log.info("DistrictFreezeDAOImpl.getDestrictDetailsForTRD execution started.");
		Session session = null;
		FreezeDistrictBean districtDetails = new FreezeDistrictBean();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("DISTRICT_FREEZE_DETAILS");
			query.setParameter("dlc", districtCode, Hibernate.INTEGER);
			query.setParameter("lblrType", ApplicationConstant.LOCAL_BODY_TRD_LEVEL_CODE);
			
			districtDetails = (FreezeDistrictBean) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Error in DistrictFreezeDAOImpl.getDestrictDetailsForTRD : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return districtDetails;
	}

	@Override
	public FreezeDistrictBean getDestrictDetailsForURB(Integer districtCode) throws Exception {
		log.info("DistrictFreezeDAOImpl.getDestrictDetailsForURB execution started.");
		Session session = null;
		FreezeDistrictBean districtDetails = new FreezeDistrictBean();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("DISTRICT_FREEZE_DETAILS");
			query.setParameter("dlc", districtCode, Hibernate.INTEGER);
			query.setParameter("lblrType", ApplicationConstant.LOCAL_BODY_URB_LEVEL_CODE);
			
			districtDetails = (FreezeDistrictBean) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Error in DistrictFreezeDAOImpl.getDestrictDetailsForURB : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return districtDetails;
	}
	@Override
	public NodalOfficer freezeUnFreezeDistrictNodaloffice(Integer districtCode) throws Exception {
		log.info("DistrictFreezeDAOImpl.getDestrictDetailsForURB execution started.");
		Session session = null;
		NodalOfficer districtDetails =null;
		try {
			session = sessionFactory.openSession();
		//	Query query = session.getNamedQuery("DISTRICT_FREEZE_DETAILS");
		//	Query query = session.getNamedQuery("DISTRICT_FREEZE_DETAILS");
			Query query =session.createQuery("from NodalOfficer where isactive=true and nodalUserDistrict="+districtCode);
		//	Criteria criteria=session.createCriteria(NodalOfficer.class).add(Restrictions.eq("nodalUserDistrict", districtCode));
		  //  query.setParameter("nodalUserDistrict", districtCode, Hibernate.INTEGER);
		//	query.setParameter("lblrType", ApplicationConstant.LOCAL_BODY_URB_LEVEL_CODE);
			//districtDetails =(NodalOfficer) criteria.list().get(0);
			districtDetails = (NodalOfficer) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Error in DistrictFreezeDAOImpl.freezeUnFreezeDistrictNodaloffice : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return districtDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MappedLbWardDisttrictWise> getMappedLbWardDisttrictWise(Integer districtCode) {
		// TODO Auto-generated method stub
		Session session = null;
		List<MappedLbWardDisttrictWise> mappedLbWardDisttrictWise=null;
		
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("MAPPED_LBWARD_TOPAC_DISTRICTWISE");
			 query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			mappedLbWardDisttrictWise = query.list();
		} catch (HibernateException e) {
			log.error("Error in DistrictFreezeDAOImpl.getMappedLbWardDisttrictWise : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		
		return mappedLbWardDisttrictWise;
	}
	public Boolean saveFreezePcAcMappingOfDistrict(NodalOfficer nodaluser,AttachedFilesForm filesForm){
		Session session = null;
		Transaction tx=null;
		try {
			session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			FreezeAttachment attachment = new FreezeAttachment();
			attachment.setFileTitle(filesForm.getFileTitle());
			attachment.setFileName(filesForm.getFileName());
			attachment.setFileSize(filesForm.getFileSize());
			attachment.setFileContentType(filesForm.getFileType());
			attachment.setFileLocation(filesForm.getFileLocation());
			attachment.setFileTimestamp(filesForm.getFileTimestampName());
			FreezeAttachment savedAttachment =(FreezeAttachment) session.merge(attachment);
			
			FreezeStatusConstituency freezeStatusConstituency=new FreezeStatusConstituency();
			freezeStatusConstituency.setDistrictCode(nodaluser.getNodalUserDistrict());
			freezeStatusConstituency.setCreatedBy(nodaluser.getNodalOfficerPK().getNodalUserId());
			freezeStatusConstituency.setDocumentId(savedAttachment.getAttachmentId());
			freezeStatusConstituency.setDistrict_status("F");
			
			 Timestamp timestamp= new Timestamp(System.currentTimeMillis());
			freezeStatusConstituency.setCreatedOn(timestamp);
			freezeStatusConstituency.setNodalUserId(nodaluser.getNodalOfficerPK().getNodalUserId());
			freezeStatusConstituency.setLastUpdated(timestamp);
			freezeStatusConstituency.setLastUpdatedBy(nodaluser.getNodalOfficerPK().getNodalUserId());
			session.merge(freezeStatusConstituency);
			
			tx.commit();
			/* query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			mappedLbWardDisttrictWise = (List<MappedLbWardDisttrictWise>) query.list();*/
		} catch (HibernateException e) {
			tx.rollback();
			log.error("Error in saveFreezePcAcMappingOfDistrict : ", e);
			throw e;
		} finally {
			
			closeConnection(session);
		}
		return true;
	}

	@Override
	public Boolean getMappedLbWardDisttrictWise(NodalOfficer nodal, AttachedFilesForm attachedFilesForm) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String isDistrictByNodalofficer(int distCode){
		String path=null;
		log.info("DistrictFreezeDAOImpl.isDistrictByNodalofficer execution started.");
		Session session = null;
		FreezeStatusConstituency districtDetails =null;
		try {
			session = sessionFactory.openSession();
			Query query =session.createQuery("from FreezeStatusConstituency where districtStatus='F' and districtCode=:districtCode");
			 query.setParameter("districtCode", distCode, Hibernate.INTEGER);
			districtDetails = (FreezeStatusConstituency) query.uniqueResult();
			if(districtDetails!=null){
				Query query1 =session.createQuery("from FreezeAttachment where  attachmentId="+districtDetails.getDocumentId());
				FreezeAttachment freezeAttachment=(FreezeAttachment)query1.uniqueResult();
				if(freezeAttachment!=null)
				path=freezeAttachment.getFileLocation();
			}
				
		} catch (HibernateException e) {
			log.error("Error in DistrictFreezeDAOImpl.isDistrictByNodalofficer : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return path;
	}
	@Override
	public Boolean unfreezeDistrictPcAcMapping(NodalOfficer nodalOfficerbean){
		
	Boolean	isUpdate=false;
	Transaction tx=null;
	Session session = null;
	
	
	try {
		
	session = sessionFactory.openSession();
	tx=session.beginTransaction();
	FreezeStatusConstituency freezeStatusConstituency=(FreezeStatusConstituency) session.get(FreezeStatusConstituency.class, nodalOfficerbean.getNodalUserDistrict());
	
	freezeStatusConstituency.setDistrictCode(nodalOfficerbean.getNodalUserDistrict());
	freezeStatusConstituency.setDistrict_status("U");
	 Timestamp timestamp= new Timestamp(System.currentTimeMillis());
	freezeStatusConstituency.setLastUpdated(timestamp);
	freezeStatusConstituency.setLastUpdatedBy(nodalOfficerbean.getNodalOfficerPK().getNodalUserId());
	session.update(freezeStatusConstituency);
	 tx.commit();
	
} catch (HibernateException e) {
	tx.rollback();
	log.error("Error in saveFreezePcAcMappingOfDistrict : ", e);
	throw e;
} finally {
	
	closeConnection(session);
}
		return true;
	}
}
