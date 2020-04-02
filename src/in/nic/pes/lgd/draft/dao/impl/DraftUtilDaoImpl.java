package in.nic.pes.lgd.draft.dao.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderTemplate;
import in.nic.pes.lgd.bean.LandRegionDetail;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.draft.constant.DraftConstant;
import in.nic.pes.lgd.draft.constant.DraftUtils;
import in.nic.pes.lgd.draft.dao.DraftSubdistrictDao;
import in.nic.pes.lgd.draft.dao.DraftUtilDao;
import in.nic.pes.lgd.draft.dao.DraftVillageDao;
import in.nic.pes.lgd.draft.entities.GovermentDetailDraft;
import in.nic.pes.lgd.draft.entities.LandRegionAttribute;
import in.nic.pes.lgd.draft.entities.SubdistrictDraft;
import in.nic.pes.lgd.draft.form.DraftGovermentOrderForm;
import in.nic.pes.lgd.draft.form.DraftSubdistrictForm;

@Repository
public class DraftUtilDaoImpl implements DraftUtilDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DraftUtils draftUtils;
	
	@Autowired 
	DraftSubdistrictDao draftSubdistrictDao;
	
	@Autowired
	DraftVillageDao draftVillageDao;


	@Override
	public LandRegionAttribute onLoadLandregionEntity(Integer stateCode, String entityType, Integer operationCode) throws Exception {
		LandRegionAttribute onLoadAttribute = new LandRegionAttribute();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = null;

			String governmentOrderQuery = "select distinct isgovtorderupload from configuration_government_order where operation_code = :oprationCode and slc = :stateCode and isactive";
			query = session.createSQLQuery(governmentOrderQuery).setParameter("stateCode", stateCode, Hibernate.INTEGER).setParameter("oprationCode", operationCode, Hibernate.INTEGER);
			Boolean isGovenmentOrderUpload = (Boolean) query.uniqueResult();
			onLoadAttribute.setIsGovernmentOrderUpload(isGovenmentOrderUpload);
			session.flush();

			if (isGovenmentOrderUpload != null) {
				if (!isGovenmentOrderUpload) {
					query = session.createQuery("from GovernmentOrderTemplate got where got.operations.operationCode=:oprationCode and got.isactive is true");
					query.setParameter("oprationCode", operationCode, Hibernate.INTEGER);
					onLoadAttribute.setGovernmentOrderTemplates(query.list());
					session.flush();
				}

				String mapUploadQuery = "select ismapupload from get_statewise_map_configuration(:stateCode) where landregion_type=:entityType";
				query = session.createSQLQuery(mapUploadQuery).setParameter("stateCode", stateCode, Hibernate.INTEGER).setParameter("entityType", entityType, Hibernate.STRING);
				Boolean isMapUpload = null;
				if (!query.list().isEmpty()) {
					isMapUpload = Boolean.TRUE;
					onLoadAttribute.setIsMapUpload(isMapUpload);
				}
				session.flush();
				if (isMapUpload != null) {
					// setOnloadAttributes(session, onloadAttributes,
					// PANCHAYAT_TYPE, Boolean.FALSE, stateCode);
				} else {
					onLoadAttribute.setSystemConfigMessage("Map configuration is not defined in the state.");
				}
			} else {
				onLoadAttribute.setSystemConfigMessage("Configuration government order is not defined in the state please defined the setup first.");
			}
			
		onLoadAttribute.setAttachmentMasterGO(this.getUploadFileConfigurationDetails(Long.parseLong( DraftConstant.ATTACHMENT_MASTER_GO.toString())));

		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return onLoadAttribute;
	}

	@Override
	public AttachmentMaster getUploadFileConfigurationDetails(Long fileMasterId) throws HibernateException {
		Session session = null;
		AttachmentMaster attachmentMaster = new AttachmentMaster();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(AttachmentMaster.class).add(Restrictions.eq("fileMasterId", fileMasterId));
			attachmentMaster = (AttachmentMaster) criteria.uniqueResult();
		} catch (HibernateException e) {

			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return attachmentMaster;
	}

	public void saveGovermentOrderinAttachement(DraftGovermentOrderForm draftGovermentOrderForm, Session session) throws Exception {
		Attachment attachment = new Attachment();
		attachment.setFileName(draftGovermentOrderForm.getFileName());
		attachment.setFileLocation(draftGovermentOrderForm.getFileLocation());
		attachment.setFileTitle(draftGovermentOrderForm.getFileName());
		attachment.setFileTimestamp(draftGovermentOrderForm.getFileTimestamp());
		attachment.setFileContentType(draftGovermentOrderForm.getFileContentType());
		attachment.setFileSize(draftGovermentOrderForm.getFileSize());
		GovernmentOrder governmentOrder = (GovernmentOrder) session.get(GovernmentOrder.class, draftGovermentOrderForm.getOrderCode());
		// governmentOrder.setOrderCode(draftGovermentOrderForm.getOrderCode());
		attachment.setGovernmentOrder(governmentOrder);
		session.saveOrUpdate(attachment);

	}

	public DraftGovermentOrderForm getDraftGovermentOrderDetails(DraftGovermentOrderForm draftGovermentOrderForm) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(GovermentDetailDraft.class).add(Restrictions.eq("groupId", draftGovermentOrderForm.getParamCode()));
			GovermentDetailDraft govermentDetailDraft = (GovermentDetailDraft) criteria.uniqueResult();
			draftUtils.copyBeanAttributes(draftGovermentOrderForm, govermentDetailDraft);

		} catch (HibernateException e) {

			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return draftGovermentOrderForm;
	}

	@Override
	public Integer updateDraftGovermentOrderDetails(DraftGovermentOrderForm draftGovermentOrderForm, HttpServletRequest request,HttpSession httpSession) throws Exception {
		Session session = null;
		Integer updatedGroupId=null;
		try {
			session = sessionFactory.openSession();
			if (draftGovermentOrderForm.getGazettePublicationUpload() != null && !draftGovermentOrderForm.getGazettePublicationUpload().get(0).isEmpty()) {
			draftGovermentOrderForm = draftUtils.uploadFileToServer(draftGovermentOrderForm, Long.parseLong(DraftConstant.ATTACHMENT_MASTER_GO.toString()), request,httpSession);
			
			Criteria criteria = session.createCriteria(GovermentDetailDraft.class).add(Restrictions.eq("groupId", draftGovermentOrderForm.getParamCode()));
			GovermentDetailDraft govermentDetailDraft = (GovermentDetailDraft) criteria.uniqueResult();
			draftUtils.copyBeanAttributes(govermentDetailDraft, draftGovermentOrderForm);
			session.update(govermentDetailDraft);
			session.flush();
			updatedGroupId=govermentDetailDraft.getGroupId();
			}else{
				if(draftGovermentOrderForm.isEditMode()){
					GovermentDetailDraft govermentDetailDraft=(GovermentDetailDraft)session.get(GovermentDetailDraft.class, draftGovermentOrderForm.getParamCode());
					govermentDetailDraft.setOrderNo(draftGovermentOrderForm.getOrderNo());
					govermentDetailDraft.setOrderDate(draftGovermentOrderForm.getOrderDate());
					govermentDetailDraft.setEffectiveDate(draftGovermentOrderForm.getEffectiveDate());
					govermentDetailDraft.setGazPubDate(draftGovermentOrderForm.getGazPubDate());
					govermentDetailDraft.setGroupId(draftGovermentOrderForm.getParamCode());
					
					session.update(govermentDetailDraft);
					session.flush();
					updatedGroupId=govermentDetailDraft.getGroupId();
					
				}
			}
			
			
		} catch (HibernateException e) {

			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return updatedGroupId;
	}

public String fillTemplateDetailsByEntity(DraftGovermentOrderForm draftGovermentOrderForm,HttpSession httpSession)throws Exception{
	String templateBodySrc=null;
	Session session=null;
	try {
		session = sessionFactory.openSession();
		Query query = session.createQuery("from GovernmentOrderTemplate got where templateCode=:templCode and got.isactive=true");
		query.setParameter("templCode", draftGovermentOrderForm.getTemplateId(), Hibernate.INTEGER);
		List<GovernmentOrderTemplate> governmentOrderTemplateList  = query.list();
		if (governmentOrderTemplateList!=null && governmentOrderTemplateList.get(0).getTemplateDescription() != null) {
			templateBodySrc = governmentOrderTemplateList.get(0).getTemplateDescription();
		}else if (governmentOrderTemplateList!=null && governmentOrderTemplateList.get(0).getTemplateRegional() != null) {
			templateBodySrc = governmentOrderTemplateList.get(0).getTemplateRegional();
		}
		
		
		
		
		
		
		if( DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString().charAt(0)==draftGovermentOrderForm.getEntityType() && (DraftConstant.OPERATION_TYPE_CREATE.toString()).equals(draftGovermentOrderForm.getOperationType()) ){
			String nameofnewSubDistrict="";
			String aliasoftheSubDistrictname="";
			StringBuilder coverage=new StringBuilder("");
			String contributingSubdistrictCoverage="";
			String contributingVillageCoverage="";
			LinkedList<DraftSubdistrictForm> storedSubdistrictForms=null;
			Integer landRegionCode=null;
			String landRegionType=DraftConstant.ENTITY_TYPE_DISTRICT.toString();
			if(draftGovermentOrderForm.isEditMode()){
				query=session.createQuery("from SubdistrictDraft where groupId=:groupId");
				query.setParameter("groupId", draftGovermentOrderForm.getParamCode());
				List<SubdistrictDraft> subdistrictDraftList=query.list();
				storedSubdistrictForms=new LinkedList<DraftSubdistrictForm>();
				DraftSubdistrictForm tempObject=null;
				for(SubdistrictDraft subdistrictDraft:subdistrictDraftList){
					tempObject=new DraftSubdistrictForm();
					draftUtils.copyBeanAttributes(tempObject, subdistrictDraft);
					tempObject.setUserId((int)draftGovermentOrderForm.getUserId());
					storedSubdistrictForms.add(tempObject);
					if(landRegionCode==null){
						landRegionCode=tempObject.getDistrictCode();
					}
				}
				
			}else{
				 storedSubdistrictForms = (LinkedList<DraftSubdistrictForm>) httpSession.getAttribute("storedSubdistrictForms");
				 landRegionCode=draftGovermentOrderForm.getLandRegionCode();
			}
			
			
			for(DraftSubdistrictForm draftSubdistrictForm:storedSubdistrictForms){
				nameofnewSubDistrict=nameofnewSubDistrict+draftSubdistrictForm.getSubdistrictNameEnglish()+",";
				aliasoftheSubDistrictname=aliasoftheSubDistrictname+draftSubdistrictForm.getAliasEnglish()+",";
				
				
				List<Subdistrict> coverageSubdistrictList=draftSubdistrictDao.getCoverageSubdistrictDetails(draftSubdistrictForm);
				for(Subdistrict coverageSubdistrict:coverageSubdistrictList){
					coverage.append(coverageSubdistrict.getSubdistrictNameEnglish()+"("+coverageSubdistrict.getSubdistrictCode()+")("+coverageSubdistrict.getCoverageType()+"),");
				}
				
				if(coverage.length()>0 && coverage.indexOf(",")!=coverage.lastIndexOf(",")){
					contributingSubdistrictCoverage=coverage.toString();
					contributingSubdistrictCoverage=contributingSubdistrictCoverage.substring(0, (contributingSubdistrictCoverage.length()-1));
				}
				
				
				coverage=new StringBuilder("");
				List<Village> coverageVillageList=draftVillageDao.getCoverageVillageDetails(draftSubdistrictForm);
				for(Village coverageVillage:coverageVillageList){
					coverage.append(coverageVillage.getVillageNameEnglish()+"("+coverageVillage.getVillageCode()+")("+coverageVillage.getPartSubdistrict()+"),");
				}
				
				if(coverage.length()>0 && coverage.indexOf(",")!=coverage.lastIndexOf(",")){
					contributingVillageCoverage=coverage.toString();
					contributingVillageCoverage=contributingVillageCoverage.substring(0, (contributingVillageCoverage.length()-1));
				}
			}
			
			query = session.getNamedQuery("getLandRegionDetail");
			query.setParameter("entity_type", landRegionType);
			query.setParameter("entity_code", landRegionCode);
			List<LandRegionDetail> landRegionDetailList=query.list();
			LandRegionDetail LandRegionDetail=null;
			if(landRegionDetailList!=null && !landRegionDetailList.isEmpty()){
				LandRegionDetail=landRegionDetailList.get(0);
			}
			
			Format sdf = new SimpleDateFormat("dd/MM/yyyy");
			templateBodySrc = templateBodySrc.replace("{NameofnewSubDistrict}", nameofnewSubDistrict);
			templateBodySrc = templateBodySrc.replace("{AliasoftheSubDistrictname}", aliasoftheSubDistrictname);
			templateBodySrc = templateBodySrc.replace("{StateName}", LandRegionDetail.getStateNameEnglish());
			templateBodySrc = templateBodySrc.replace("{DistrictName}", LandRegionDetail.getDistrictNameEnglish());
			templateBodySrc = templateBodySrc.replace("{contributingSubdistrictCoverage}", contributingSubdistrictCoverage);
			templateBodySrc = templateBodySrc.replace("{contributingVillageCoverage}", contributingVillageCoverage);
			templateBodySrc = templateBodySrc.replace("{OrderNo}", draftGovermentOrderForm.getOrderNo());
			templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(draftGovermentOrderForm.getOrderDate()));
			templateBodySrc = templateBodySrc.replace("{EffectiveDate}",sdf.format(draftGovermentOrderForm.getEffectiveDate()));
			
			
			
		
		}
		
	} catch (HibernateException e) {
		e.printStackTrace();
		throw e;
	} finally {
		if (session != null && session.isOpen()){
			session.close();
		}
	}
	return templateBodySrc;
}

}
