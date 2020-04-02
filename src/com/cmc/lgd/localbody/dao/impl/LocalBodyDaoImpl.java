package com.cmc.lgd.localbody.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmc.lgd.localbody.dao.LocalBodyDao;
import com.cmc.lgd.localbody.entities.CompletedCoverageDetails;
import com.cmc.lgd.localbody.entities.CoverageDetailsLocalBody;
import com.cmc.lgd.localbody.entities.CriteriaDraftedEntities;
import com.cmc.lgd.localbody.entities.DraftChangeCoverageTemp;
import com.cmc.lgd.localbody.entities.DraftChangeGovtOrderLBTemp;
import com.cmc.lgd.localbody.entities.DraftChangeParentLocalbodyTemp;
import com.cmc.lgd.localbody.entities.DraftChangeTypeLocalbodyTemp;
import com.cmc.lgd.localbody.entities.DraftLocalbodyTemp;
import com.cmc.lgd.localbody.entities.DraftOperations;
import com.cmc.lgd.localbody.entities.DraftRenameLocalbodyTemp;
import com.cmc.lgd.localbody.entities.DraftUsedChangeCoverageLbLrTemp;
import com.cmc.lgd.localbody.entities.DraftUsedLbLrTemp;
import com.cmc.lgd.localbody.entities.DraftedLBCoverageDetails;
import com.cmc.lgd.localbody.entities.GISMappedEntities;
import com.cmc.lgd.localbody.entities.GovernmentOrderDetails;
import com.cmc.lgd.localbody.entities.InsertionLocalBodyFn;
import com.cmc.lgd.localbody.entities.LBAttributes;
import com.cmc.lgd.localbody.entities.LBTypeDetails;
import com.cmc.lgd.localbody.entities.LBTypeDetailsWithCategory;
import com.cmc.lgd.localbody.entities.LBTypeHierarchy;
import com.cmc.lgd.localbody.entities.LocalBodyEntityDetails;
import com.cmc.lgd.localbody.entities.LocalBodyForm;
import com.cmc.lgd.localbody.entities.LocalBodyTable;
import com.cmc.lgd.localbody.entities.ManageLBDetails;
import com.cmc.lgd.localbody.entities.UnmappedLandregions;
import com.cmc.lgd.localbody.rules.LocalBodyConstant;
import com.cmc.lgd.localbody.rules.LocalBodyUtil;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GovernmentOrderTemplate;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.common.EncryptionUtil;
import in.nic.pes.lgd.dao.impl.VillageDAOImpl;
import in.nic.pes.lgd.draft.form.LocalBodyDetailDto;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.loggers.LGDLogger;
import in.nic.pes.lgd.restructure.reporting.dao.impl.ViewEntityDetailsDaoImpl;
import in.nic.pes.lgd.service.VillageService;

@SuppressWarnings("unchecked")
@Repository
public class LocalBodyDaoImpl implements LocalBodyDao {

	private static final Logger log = Logger.getLogger(LocalBodyDaoImpl.class);
	
	private String goFileLocation, mapFileLocation;

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private LocalBodyUtil localBodyUtil;

	  @Autowired
	  private VillageService villageService;
	/**
	 * 
	 * @param PARAM_ATTRIBUTE
	 * @return
	 */
	@SuppressWarnings("unused")
	private Integer getDWRStateCodeOrDistrictCode(String PARAM_ATTRIBUTE){
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		String stateDistrictCodeObj = (String) session.getAttribute(PARAM_ATTRIBUTE);
		if(stateDistrictCodeObj != null){
			return Integer.parseInt(stateDistrictCodeObj);
		}
		return null;
	}
	
	/**
	 * 
	 * @param session
	 */
	private void closeConnection (Session session){
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param onloadAttributes
	 * @param PANCHAYAT_TYPE
	 * @param isDraftedSearch
	 * @param stateCode
	 */
	private void setOnloadAttributes(Session session, LBAttributes onloadAttributes, String PANCHAYAT_TYPE, Boolean isDraftedSearch, Integer stateCode){
		Query query = null;
		if( LocalBodyConstant.LB_URBAN.toString().equals(PANCHAYAT_TYPE) || isDraftedSearch ){
			query = session.getNamedQuery("Urban_Local_body_Type_Details");
			query.setParameter("stateCode", stateCode).setParameter("panchayatType", PANCHAYAT_TYPE, Hibernate.STRING);
			List<LBTypeDetails> lbTypes = query.list();
			onloadAttributes.setLocalBodyTypes(lbTypes);
			session.flush();
			
			Criteria criteriaDraftOperations = session.createCriteria(DraftOperations.class);
			criteriaDraftOperations.add(Restrictions.eq("processEntity", LocalBodyConstant.LOCAL_BODY_CONSTANT.toString()));
			onloadAttributes.setDraftOperations(criteriaDraftOperations.list());
			session.flush();
		} else {
			query = session.getNamedQuery("Dynamic_Local_body_Hierarchy");
			query.setParameter("stateCode", stateCode).setParameter("localBodyType", PANCHAYAT_TYPE, Hibernate.STRING);
			List<LBTypeHierarchy> lbHierarchies = query.list();
			onloadAttributes.setLbTypeHierarchy(lbHierarchies);
			session.flush();
		}
		Criteria criteriaGO = session.createCriteria(AttachmentMaster.class).add(Restrictions.eq("fileMasterId", Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_GO.toString())));
		onloadAttributes.setAttachmentMasterGO((AttachmentMaster) criteriaGO.uniqueResult());
		Criteria criteriaMap = session.createCriteria(AttachmentMaster.class).add(Restrictions.eq("fileMasterId", Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_MAP.toString())));
		onloadAttributes.setAttachmentMasterMap((AttachmentMaster) criteriaMap.uniqueResult());
	}
		
	@Override
	public LBAttributes onLoadLocalBody(Integer stateCode, String PANCHAYAT_TYPE, String processName) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.onLoadLocalBody execution started.");
		Session session = null;
		LBAttributes onloadAttributes = new LBAttributes();
		try {
			session = sessionFactory.openSession();
			Query query = null;
			
			String governmentOrderQuery = "select distinct isgovtorderupload from configuration_government_order where operation_code = :oprationCode and slc = :stateCode and isactive";
			query = session.createSQLQuery(governmentOrderQuery).setParameter("stateCode", stateCode, Hibernate.INTEGER).setParameter("oprationCode", LocalBodyConstant.OPERATION_CODE.get(PANCHAYAT_TYPE, processName));
			//query.setCacheable(true);
			//query.setCacheMode(CacheMode.GET);
			Boolean isGovenmentOrderUpload = (Boolean) query.uniqueResult();
			onloadAttributes.setIsGovernmentOrderUpload(isGovenmentOrderUpload);
			session.flush();
			
			if( isGovenmentOrderUpload != null) {
				if( !isGovenmentOrderUpload ){
					query = session.createQuery("from GovernmentOrderTemplate got where got.operations.operationCode=:oprationCode and got.isactive is true");
					query.setParameter("oprationCode", LocalBodyConstant.OPERATION_CODE.get(PANCHAYAT_TYPE, processName), Hibernate.INTEGER);
					onloadAttributes.setGovernmentOrderTemplates(query.list());
					session.flush();
				}
								
				String mapUploadQuery = "select ismapupload from get_statewise_lb_map_config( :stateCode ) where category = :panchayatType";
				query = session.createSQLQuery(mapUploadQuery).setParameter("stateCode", stateCode, Hibernate.INTEGER).setParameter("panchayatType", PANCHAYAT_TYPE, Hibernate.STRING);
				Boolean isMapUpload = null;
				if( !query.list().isEmpty() ){
					isMapUpload = Boolean.TRUE;
					onloadAttributes.setIsMapUpload(isMapUpload);
				}
				session.flush();
				if( isMapUpload != null ){
					setOnloadAttributes(session, onloadAttributes, PANCHAYAT_TYPE, Boolean.FALSE, stateCode);
				}else {
					onloadAttributes.setSystemConfigMessage("Map configuration is not defined in the state.");
				}
			} else {
				onloadAttributes.setSystemConfigMessage("Configuration government order is not defined in the state please defined the setup first.");
			}
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.onLoadLocalBody : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return onloadAttributes;
	}
	
	@Override
	public LBAttributes onLoadDraftedSearchLocalBody(Integer stateCode, String PANCHAYAT_TYPE) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.onLoadLocalBody execution started.");
		Session session = null;
		LBAttributes onloadAttributes = new LBAttributes();
		try {
			session = sessionFactory.openSession();
			setOnloadAttributes(session, onloadAttributes, PANCHAYAT_TYPE, Boolean.TRUE, stateCode);
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.onLoadLocalBody : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return onloadAttributes;
	}
	
	
	@Override
	public List<LBTypeDetails> buildLocalBodyHierarchy(Integer setupCode, Integer setupVersion) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.buildLocalBodyHierarchy execution started.");
		Session session = null;
		List<LBTypeDetails> lbTypesDetails = new ArrayList<LBTypeDetails>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Dynamic_Local_body_Type_Details");
			query.setParameter("setupCode", setupCode, Hibernate.INTEGER).setParameter("setupVersion", setupVersion, Hibernate.INTEGER);
			lbTypesDetails = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.buildLocalBodyHierarchy : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return lbTypesDetails;
	}
	
	@Override
	public List<LocalBodyEntityDetails> getDistrictPanchayatList(Integer lbTypeCode, Integer stateCode, Integer draftTempCode, Integer processId) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getDistrictPanchayatList execution started.");
		Session session = null;
		List<LocalBodyEntityDetails> localBodyDetails = new ArrayList<LocalBodyEntityDetails>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Local_Body_List_By_Type_State");
			query.setParameter("lbTypeCode", lbTypeCode, Hibernate.INTEGER);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("draftTempCode", draftTempCode, Hibernate.INTEGER);
			query.setParameter("processId", processId, Hibernate.INTEGER);
			localBodyDetails = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getDistrictPanchayatList : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return localBodyDetails;
	}
	
	@Override
	public List<LocalBodyEntityDetails> getDistrictPanchayatListForDistrictUser(Integer lbTypeCode, Integer districtCode, Integer draftTempCode, Integer processId) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getDistrictPanchayatListForDistrictUser execution started.");
		Session session = null;
		List<LocalBodyEntityDetails> localBodyDetails = new ArrayList<LocalBodyEntityDetails>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Local_Body_List_By_Type_District");
			query.setParameter("lbTypeCode", lbTypeCode, Hibernate.INTEGER);
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			query.setParameter("draftTempCode", draftTempCode, Hibernate.INTEGER);
			query.setParameter("processId", processId, Hibernate.INTEGER);
			localBodyDetails = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getDistrictPanchayatListForDistrictUser : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return localBodyDetails;
	}

	@Override
	public List<LocalBodyEntityDetails> getParentwiseLocalBody(Integer localBodyCode, Integer draftTempCode, Integer processId) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getParentwiseLocalBody execution started.");
		Session session = null;
		List<LocalBodyEntityDetails> parentwiseLocalBodyDetails = new ArrayList<LocalBodyEntityDetails>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Local_Body_List_By_Parent");
			query.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			query.setParameter("draftTempCode", draftTempCode, Hibernate.INTEGER);
			query.setParameter("processId", processId, Hibernate.INTEGER);
			parentwiseLocalBodyDetails = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getParentwiseLocalBody : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return parentwiseLocalBodyDetails;
	}
	
	@Override
	public List<UnmappedLandregions> getUnmappedLandRegions(String lbTypeLevel, Integer stateCode, Integer districtCode, Integer draftTempCode, Integer processId) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getUnmappedLandRegions execution started.");
		Session session = null;
		List<UnmappedLandregions> unmappedLanregionDetails = new ArrayList<UnmappedLandregions>();
		try {
			session = sessionFactory.openSession();
			String namedQuery = "Unmapped_Land_Regions";
			if(districtCode != null && districtCode > 0){
				namedQuery = "Unmapped_Land_Regions_For_District_User";
			}
			//Query query = session.getNamedQuery("Unmapped_Land_Regions");
			Query query = session.getNamedQuery( namedQuery );
			query.setParameter("lbTypeLevel", lbTypeLevel, Hibernate.STRING);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("draftTempCode", draftTempCode, Hibernate.INTEGER);
			if(districtCode != null && districtCode > 0){
				query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			}
			query.setParameter("processId", processId, Hibernate.INTEGER);
			unmappedLanregionDetails = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getUnmappedLandRegions : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return unmappedLanregionDetails;		
	}
	
	@Override
	public List<CoverageDetailsLocalBody> fetchCoverageDetailsLocalBody(String localBodyCodes, String lbTypeLevel) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.fetchCoverageDetailsLocalBody execution started.");
		Session session = null;
		List<CoverageDetailsLocalBody> coverageDetailsForLB = new ArrayList<CoverageDetailsLocalBody>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Fetch_Coverage_Details_LB");
			query.setParameter("localBodyCodes", localBodyCodes, Hibernate.STRING);
			query.setParameter("lbTypeLevel", lbTypeLevel, Hibernate.STRING);
			coverageDetailsForLB = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.fetchCoverageDetailsLocalBody : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return coverageDetailsForLB;
	}
	
	@Override
	public List<CoverageDetailsLocalBody> fetchCoverageLBLandRegion(String localBodyCodes, String lbTypeLevel, List<Integer> landRegionCodes) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.fetchCoverageLBLandRegion execution started.");
		Session session = null;
		List<CoverageDetailsLocalBody> coverageDetailsLandRegion = new ArrayList<CoverageDetailsLocalBody>();
		try {
			session = sessionFactory.openSession();
			String namedQuery = null;
			if(LocalBodyConstant.SUB_DISTRICT_CONSTANT.toString().equals(lbTypeLevel)){
				namedQuery = "Fetch_Coverage_Subdistrict_LB";
			}else if (LocalBodyConstant.VILLAGE_CONSTANT.toString().equals(lbTypeLevel)){
				namedQuery = "Fetch_Coverage_Village_LB";
			}
			Query query = session.getNamedQuery(namedQuery);
			query.setParameter("localBodyCodes", localBodyCodes, Hibernate.STRING);
			query.setParameter("lbTypeLevel", lbTypeLevel, Hibernate.STRING);
			query.setParameterList("landRegionCodes", landRegionCodes);
			coverageDetailsLandRegion = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.fetchCoverageLBLandRegion : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return coverageDetailsLandRegion;
	}
	
	@Override
	public List<UnmappedLandregions> fetchCoverageDetailsLandRegion(String unmappedCoverageLevel, String localBodyCodes, String localBodyType) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.fetchCoverageDetailsLandRegion execution started.");
		Session session = null;
		List<UnmappedLandregions> unmappedCovergaesLandRegion = new ArrayList<UnmappedLandregions>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Partially_Unmapped_List_At_LR_Levels");
			query.setParameter("unmappedCoverageLevel", unmappedCoverageLevel, Hibernate.STRING);
			query.setParameter("localBodyCodes", localBodyCodes, Hibernate.STRING);
			query.setParameter("localBodyType", localBodyType, Hibernate.STRING);
			unmappedCovergaesLandRegion = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.fetchCoverageDetailsLandRegion : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return unmappedCovergaesLandRegion;
	}
	
	@Override
	public List<GovernmentOrderDetails> fetchExistingGovernmentOrder(String orderNo, String rangeFrom, String rangeTo, Integer stateCode) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.fetchExistingGovernmentOrder execution started.");
		Session session = null;
		List<GovernmentOrderDetails> governmentOrderList = new ArrayList<GovernmentOrderDetails>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("LB_Fetch_Government_Order_Details");
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("orderNo", orderNo, Hibernate.STRING);
			query.setParameter("rangeFrom", rangeFrom, Hibernate.STRING);
			query.setParameter("rangeTo", rangeTo, Hibernate.STRING);
			governmentOrderList = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.fetchExistingGovernmentOrder : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return governmentOrderList;
	}

	@Override
	public String publishLocalBody(LocalBodyForm localBodyForm) throws Exception {
		// TODO Auto-generated method stub
		String status = LocalBodyConstant.LB_PUBLISH_FAILD.toString();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			InsertionLocalBodyFn returnedValue = (InsertionLocalBodyFn) executeInsertLBFunction(session, localBodyForm);
			System.out.println(returnedValue.getCreate_localbody_fn());
			status = returnedValue.getCreate_localbody_fn();
			if(localBodyForm.getId() != null){
				try {
					publishOrDeleteDraftToTransaction(localBodyForm.getId(), Boolean.TRUE, null);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					status = LocalBodyConstant.LB_PUBLISH_FAILD.toString();
				}
			}
		} catch (HibernateException e) {
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}
	
	@Override
	public Boolean saveLocalBodyAsDraft(DraftLocalbodyTemp draftLocalbodyTemp) throws HibernateException {
		// TODO Auto-generated method stub
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			if(draftLocalbodyTemp.getId() != null){
				Criteria c=  session.createCriteria(DraftUsedLbLrTemp.class).add(Restrictions.eq("draftLocalbodyTemp", draftLocalbodyTemp));
				List<DraftUsedLbLrTemp> lst = c.list();
				for(DraftUsedLbLrTemp temp : lst){
					session.delete(temp);
				}
				session.flush();
			}
			
			session.saveOrUpdate(draftLocalbodyTemp);
			tx.commit();
			status = Boolean.TRUE;
		} catch (HibernateException e) {
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}
	
	@Override
	public LBAttributes getLocalBodyDetailsForView(Integer localBodyCode) {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getLocalBodyDetailsForView execution started.");
		Session session = null;
		LBAttributes attributes = new LBAttributes();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("View_Local_Body_Details");
			query.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			LocalBodyEntityDetails lbDetails = (LocalBodyEntityDetails) query.uniqueResult();
			attributes.setLocalBodyDetails(lbDetails);
			session.flush();
			
			query = session.getNamedQuery("View_Land_Region_Details");
			query.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			List<CoverageDetailsLocalBody> landRegionCovergeDetails = query.list();
			attributes.setLandRegionCovergeDetails(landRegionCovergeDetails);
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getLocalBodyDetailsForView : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return attributes;	
	}

	@Override
	public LBAttributes onLoadSearchCriteria(Integer stateCode,	String PANCHAYAT_TYPE) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.onLoadSearchCriteria execution started.");
		Session session = null;
		LBAttributes onloadAttributes = new LBAttributes();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Urban_Local_body_Type_Details");
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER).setParameter("panchayatType", PANCHAYAT_TYPE, Hibernate.STRING);
			List<LBTypeDetails> lbTypes = query.list();
			onloadAttributes.setLocalBodyTypes(lbTypes);
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.onLoadSearchCriteria : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return onloadAttributes;	
	}

	@Override
	public Boolean publishOrDeleteDraftToTransaction(Integer tempLocalBodyCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.publishMultipleDraftToTransaction execution started.");
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftLocalbodyTemp.class).add(Restrictions.eq("id", tempLocalBodyCode));
			DraftLocalbodyTemp draftLocalbodyTemp = (DraftLocalbodyTemp) criteria.uniqueResult();
			if( !isDeleteStatus ) {
				LocalBodyForm localBodyForm =  convertDraftLocalbodyTempToLBForm(draftLocalbodyTemp, Boolean.TRUE, request);
				executeInsertLBFunction(session, localBodyForm);
				session.flush();
			}
			
			//Deleting Temporary Table Records
			Transaction tx = session.beginTransaction();
			session.delete(draftLocalbodyTemp);
			tx.commit();
			status = Boolean.TRUE;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.publishMultipleDraftToTransaction : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}

	@Override
	public Map<String, Object> getDraftedTempLBDetails(Integer tempLBCode) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getDraftedTempLBDetails execution started.");
		Session session = null;
		Map<String, Object> draftedEntityMap = new HashedMap();
		
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftLocalbodyTemp.class).add(Restrictions.eq("id", tempLBCode));
			DraftLocalbodyTemp draftLocalbodyTemp = (DraftLocalbodyTemp) criteria.uniqueResult();
			draftedEntityMap.put("draftLocalbodyTemp", draftLocalbodyTemp);
			session.flush();
			
			Query query = session.createSQLQuery("select * from get_lb_type_name_fn(:lbTypeCode)");
			query.setParameter("lbTypeCode", draftLocalbodyTemp.getLocalBodyTypeCode(), Hibernate.INTEGER);
			draftedEntityMap.put("localbodyTypeName", (String) query.uniqueResult());
			session.flush();
			
			Integer parentCode = draftLocalbodyTemp.getParentLocalBodyCode();
			if(parentCode != null && parentCode != 0){
				query = session.getNamedQuery("Local_Body_Parent_Levels");
				query.setParameter("lbParentCode", draftLocalbodyTemp.getParentLocalBodyCode(), Hibernate.INTEGER);
				draftedEntityMap.put("parentLBLevels", query.list());
				session.flush();
			}
			
			query = session.getNamedQuery("Drafted_LB_Coverage_Details");
			query.setParameter("draftedLBCode", draftLocalbodyTemp.getId(), Hibernate.INTEGER);
			query.setParameter("paramTableName", LocalBodyConstant.LOCAL_BODY_DRAFT_TABLE.toString(), Hibernate.STRING);
			draftedEntityMap.put("draftedLBCoverages", query.list());
			
			if(null != draftLocalbodyTemp.getOrderCode()){
				query = session.getNamedQuery("Fetch_GO_Details_By_Order_Code");
				query.setParameter("paramOderCode", draftLocalbodyTemp.getOrderCode(), Hibernate.INTEGER);
				draftedEntityMap.put("govtOrderDetails", query.uniqueResult());
			}
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getDraftedTempLBDetails : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return draftedEntityMap;	
	}
	
	@Override
	public AttachmentMaster getUploadFileConfigurationDetails(Long fileMasterId) throws HibernateException{
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getUploadFileConfigurationDetails execution started.");
		Session session = null;
		AttachmentMaster attachmentMaster = new AttachmentMaster();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(AttachmentMaster.class).add(Restrictions.eq("fileMasterId", fileMasterId));
			attachmentMaster = (AttachmentMaster) criteria.uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getUploadFileConfigurationDetails : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return attachmentMaster;	
	}

	@Override
	public String checkLocalBodyNameExist(String localBodyName, Integer localBodyType, Integer parentCode, Integer stateCode, Integer draftTempId) {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.checkLocalBodyNameExist execution started.");
		String status = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select * from draft_lbname_exist_fn(:localBodyName, :localBodyType, :parentCode, :stateCode, :draftTempId)");
			query.setParameter("localBodyName", localBodyName, Hibernate.STRING);
			query.setParameter("localBodyType", localBodyType, Hibernate.INTEGER);
			query.setParameter("parentCode", parentCode, Hibernate.INTEGER);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("draftTempId", draftTempId, Hibernate.INTEGER);
			status = (String) query.uniqueResult() ;
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.checkLocalBodyNameExist : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
			
		return status;
	}
	
	@Override
	public Boolean checkMapUpload(Integer tierSetupCode) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.checkMapUpload execution started.");
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select ismapupload from configuration_map_localbody where isactive and tier_setup_code = :tierSetupCode");
			query.setParameter("tierSetupCode", tierSetupCode, Hibernate.INTEGER);
			
			status = (Boolean) query.uniqueResult() ;
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.checkMapUpload : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}
	
	@Override
	public LBAttributes getGovernmentOrderTemplate(Integer templateCode) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getGovernmentOrderTemplate execution started.");
		Session session = null;
		LBAttributes lbAttributes = new LBAttributes();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(GovernmentOrderTemplate.class).add(Restrictions.eq("templateCode", templateCode));
			lbAttributes.setTemplateSource((GovernmentOrderTemplate) criteria.uniqueResult());
			
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.onLoadLocalBody : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return lbAttributes;
	}

	@Override
	public Map<String, Object> getLBFormforModification(Integer lbTempCode) throws Exception {
		// TODO Auto-generated method stub
		try{
			Map<String, Object> draftedLBDetails = getDraftedTempLBDetails(lbTempCode);
			DraftLocalbodyTemp draftLocalbodyTemp = (DraftLocalbodyTemp) draftedLBDetails.get("draftLocalbodyTemp");
			draftedLBDetails.remove("draftLocalbodyTemp");
			draftedLBDetails.put("lbForm", convertDraftLocalbodyTempToLBForm(draftLocalbodyTemp, Boolean.FALSE, null));
			return draftedLBDetails;
		}catch(Exception ex){
			throw ex;
		}
	}

	/**
	 * 
	 * @param session
	 * @param localBodyForm
	 * @return
	 */
	private Object executeInsertLBFunction (Session session, LocalBodyForm localBodyForm){
		Query query = session.getNamedQuery("Insert_Local_Body_Details")
					 .setParameter("local_body_name_english", localBodyForm.getLocalBodyNameEnglish(), Hibernate.STRING)
					 .setParameter("local_body_name_local"	, localBodyForm.getLocalBodyNameLocal(), Hibernate.STRING)
					 .setParameter("local_body_type_code"	, localBodyForm.getLocalBodyTypeCode(), Hibernate.INTEGER)
					 .setParameter("state_code"				, localBodyForm.getStateCode(), Hibernate.INTEGER)
					 .setParameter("alias_english"			, localBodyForm.getLocalBodyAliasEnglish(), Hibernate.STRING)
					 .setParameter("alias_local"			, localBodyForm.getLocalBodyAliasLocal(), Hibernate.STRING)
					 .setParameter("is_pesa"				, LocalBodyConstant.LB_ISPESA_DEFAULT_VALUE.toString().charAt(0), Hibernate.CHARACTER) //localBodyForm.getPesaActImpl() hidden from UI also
					 .setParameter("order_no"				, localBodyForm.getOrderNo(), Hibernate.STRING)
					 .setParameter("order_date"				, localBodyForm.getOrderDate(), Hibernate.DATE)
					 .setParameter("effective_date"			, localBodyForm.getEffectiveDate(), Hibernate.DATE)
					 .setParameter("gaz_pub_date"			, localBodyForm.getGazPubDate(), Hibernate.DATE)
					 .setParameter("order_path"				, localBodyForm.getOrderPath(), Hibernate.STRING)
					 .setParameter("operation_code"			, localBodyForm.getOperationCode(), Hibernate.INTEGER)
					 .setParameter("replaces"				, "".equals(localBodyForm.getContributingLBCodes()) ? null : localBodyForm.getContributingLBCodes(), Hibernate.STRING)
					 .setParameter("land_region_list_mapped", "".equals(localBodyForm.getContributingLandRegionCodes()) ? null : localBodyForm.getContributingLandRegionCodes(), Hibernate.STRING)
					 .setParameter("local_body_subtype_code", localBodyForm.getLocalBodySubtypeCode(), Hibernate.INTEGER)
					 .setParameter("parent_local_body_code"	, localBodyForm.getParentLocalBodyCode(), Hibernate.INTEGER)
					 .setParameter("sscode"					, localBodyForm.getStateSpecificCode(), Hibernate.STRING)
					 .setParameter("coordinates"			, localBodyForm.getCoordinates(), Hibernate.STRING)
					 .setParameter("flag_code"				, localBodyForm.getFlagCode(), Hibernate.INTEGER)
					 .setParameter("createdby"				, localBodyForm.getCreatedBy(), Hibernate.INTEGER)
					 .setParameter("description"			, localBodyForm.getDescription(), Hibernate.STRING)
					 .setParameter("goordercode"			, localBodyForm.getOrderCode(), Hibernate.INTEGER)
					 .setParameter("gofilename"				, localBodyForm.getOrderPath() , Hibernate.STRING)
					 .setParameter("gofilesize"				, localBodyForm.getOrderFileSize() , Hibernate.LONG)
					 .setParameter("gofiletype"				, localBodyForm.getOrderFileContentType(), Hibernate.STRING)
					 .setParameter("mapfilename"			, localBodyForm.getMapUploadPath(), Hibernate.STRING)
					 .setParameter("mapfilesize"			, localBodyForm.getMapFileSize(), Hibernate.LONG)
					 .setParameter("mapfiletype"			, localBodyForm.getMapFileContentType(), Hibernate.STRING);
		
		Object returnedValue =  query.uniqueResult();
		return returnedValue;
	}
	
	
	/**
	 * 
	 * @param draftLocalbodyTemp
	 * @return
	 * @throws Exception 
	 */
	private LocalBodyForm convertDraftLocalbodyTempToLBForm (Object draftLocalbodyTemp, Boolean isPublishEvent, HttpServletRequest request) throws Exception {
		LocalBodyForm toBean = new LocalBodyForm();
		try {
			log.info("Copying properties from DraftLocalbodyTemp to LocalBodyForm");
			localBodyUtil.copyBeanAttributes(toBean, draftLocalbodyTemp);
		    
		    if(isPublishEvent){
		    	if(toBean.getTemplateId() == null && toBean.getEditorTemplateDetails() == null){
		    		if(goFileLocation == null){
		    			AttachmentMaster master = getUploadFileConfigurationDetails(Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_GO.toString()));
		    			goFileLocation = master.getFileLocation();
		    		}
	    			setGovernmentOrderFileAttributes(toBean, goFileLocation);
		    	}else{
		    		localBodyUtil.convertTemplatetoPDF(toBean, request);
		    	}
		    	if(toBean.getMapUploadPath() != null){
		    		if(mapFileLocation == null){
		    			AttachmentMaster master = getUploadFileConfigurationDetails(Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_MAP.toString()));
		    			mapFileLocation = master.getFileLocation();
		    		}
		    		setMapFileAttributes(toBean, mapFileLocation);
		    	}
		    }
		} catch (IllegalAccessException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		}
		return toBean;
	}
	
	private LocalBodyForm convertDraftTempToLBForm (Object draftTempObject, Boolean isPublishEvent, HttpServletRequest request) throws Exception {
		LocalBodyForm toBean = new LocalBodyForm();
		try {
			log.info("Copying properties from convertDraftTempToLBForm to LocalBodyForm");
			localBodyUtil.copyBeanAttributes(toBean, draftTempObject);
		    if(isPublishEvent){
		    	if(toBean.getTemplateId() == null && toBean.getEditorTemplateDetails() == null){
		    		if(goFileLocation == null){
		    			AttachmentMaster master = getUploadFileConfigurationDetails(Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_GO.toString()));
		    			goFileLocation = master.getFileLocation();
		    		}
	    			setGovernmentOrderFileAttributes(toBean, goFileLocation);
		    	}else{
		    		localBodyUtil.convertTemplatetoPDF(toBean, request);
		    	}
		    }
		} catch (IllegalAccessException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		}
		return toBean;
	}
	
	/**
	 * 
	 * @param toBean
	 * @param fileLocation
	 */
	private void setGovernmentOrderFileAttributes(LocalBodyForm toBean, String fileLocation){
		java.io.File goFile = new java.io.File(fileLocation + "/" +toBean.getOrderPath());
		toBean.setOrderFileSize(goFile.length() / 1024L);
		javax.activation.MimetypesFileTypeMap mimeTypesMap = new javax.activation.MimetypesFileTypeMap();
		String mimeType = mimeTypesMap.getContentType(goFile);
		toBean.setOrderFileContentType(mimeType);
	}
	
	/**
	 * 
	 * @param toBean
	 * @param fileLocation
	 */
	private void setMapFileAttributes(LocalBodyForm toBean, String fileLocation){
		java.io.File mapFile = new java.io.File(fileLocation + "/" +toBean.getMapUploadPath());
		toBean.setMapFileSize(mapFile.length() / 1024L);
		javax.activation.MimetypesFileTypeMap mimeTypesMap = new javax.activation.MimetypesFileTypeMap();
		String mimeType = mimeTypesMap.getContentType(mapFile);
		toBean.setMapFileContentType(mimeType);
	}

	/* Manage Local Body Function(s) started Here */
	
	@Override
	public List<ManageLBDetails> getLocalBodiesForManage(String lbCreationType, Integer lbTypeCode, Integer stateCode, Integer parentLBCode, Integer districtCode) {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getLocalBodiesForManage execution started.");
		Session session = null;
		List<ManageLBDetails> publishedLocalBodies = new ArrayList<ManageLBDetails>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Fetch_Published_Local_Bodies");
			query.setParameter("lbCreationType", lbCreationType, Hibernate.STRING);
			query.setParameter("localBodyType", lbTypeCode, Hibernate.INTEGER);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("parentLBCode", parentLBCode, Hibernate.INTEGER);
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			publishedLocalBodies = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getLocalBodiesForManage : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return publishedLocalBodies;	
	}

	@Override
	public Map<String, Object> viewLocalBodyDetails(Integer localBodyCode) {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.viewLocalBodyDetails execution started.");
		Session session = null;
		Map<String, Object> localBodyEntityMap = new HashedMap();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LocalBodyTable.class);
			criteria.add(Restrictions.eq("localBodyCode", localBodyCode));
			criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
			LocalBodyTable lbEntiity = (LocalBodyTable) criteria.uniqueResult();
			localBodyEntityMap.put("localBodyEntity", lbEntiity);
			//session.flush();
			
			Query query = session.createSQLQuery("select * from get_lb_type_name_fn(:lbTypeCode)");
			query.setParameter("lbTypeCode", lbEntiity.getLocalBodyTypeCode(), Hibernate.INTEGER);
			localBodyEntityMap.put("localbodyTypeName", (String) query.uniqueResult());
			//session.flush();
			
			Integer parentCode = lbEntiity.getParentLocalBodyCode();
			if(parentCode != null && parentCode != 0){
				query = session.getNamedQuery("Local_Body_Parent_Levels");
				query.setParameter("lbParentCode", parentCode, Hibernate.INTEGER);
				localBodyEntityMap.put("parentLBLevels", query.list());
				//session.flush();
			}
			
			query = session.getNamedQuery("View_Land_Region_Details");
			query.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			List<CoverageDetailsLocalBody> landRegionCovergeDetails = query.list();
			localBodyEntityMap.put("publishedLBCoverages", landRegionCovergeDetails);
			//session.flush();
			
			query = session.createQuery("from MapAttachment where map_lc =:localBodyCode and mapEntityType =:entityType");
			query.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			query.setParameter("entityType", LocalBodyConstant.LOCAL_BODY_CONSTANT.toString(), Hibernate.STRING);
			MapAttachment mapAttachment = (MapAttachment) query.uniqueResult();
			localBodyEntityMap.put("mapAttachment", mapAttachment);
			//session.flush();
			
			query = session.getNamedQuery("Entity_Wise_GO_Details");
			query.setParameter("entityType", LocalBodyConstant.LOCAL_BODY_CONSTANT.toString(), Hibernate.STRING);
			query.setParameter("entityCode", lbEntiity.getLocalBodyCode(), Hibernate.INTEGER);
			query.setParameter("entityVersion", lbEntiity.getLocalBodyVersion(), Hibernate.INTEGER);
			query.setParameter("minorVersion", lbEntiity.getMinorVersion(), Hibernate.INTEGER);
			localBodyEntityMap.put("governmentOrderDetails", query.uniqueResult());
			
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getDraftedTempLBDetails : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return localBodyEntityMap;
	}

	@Override
	public LocalBodyTable getLocalBodyFormObject(Integer localBodyCode) throws HibernateException{
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getLBObjectForRename execution started.");
		Session session = null;
		LocalBodyTable lbEntiity = new LocalBodyTable();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LocalBodyTable.class);
			criteria.add(Restrictions.eq("localBodyCode", localBodyCode));
			criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
			lbEntiity = (LocalBodyTable) criteria.uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getLBObjectForRename : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return lbEntiity;	
	}

	@Override
	public Integer getLBCodeByParentCode(Integer parentCode) throws HibernateException{
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getLBCodeByParentCode execution started.");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LocalBodyTable.class);
			criteria.add(Restrictions.eq("lblc", parentCode));
			criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
			criteria.setProjection(Projections.property("localBodyCode"));
			return (Integer) criteria.uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getLBCodeByParentCode : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
	}
	
	@Override
	public DraftRenameLocalbodyTemp getDraftRenameLocalbodyTemp(Integer entityTempId) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getDraftRenameLocalbodyTemp execution started.");
		Session session = null;
		DraftRenameLocalbodyTemp draftRenameLocalbodyTemp = new DraftRenameLocalbodyTemp();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftRenameLocalbodyTemp.class);
			criteria.add(Restrictions.eq("id", entityTempId));
			draftRenameLocalbodyTemp = (DraftRenameLocalbodyTemp) criteria.uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getDraftRenameLocalbodyTemp : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return draftRenameLocalbodyTemp;
	}

	@Override
	public Boolean saveRenameLocalBodyAsDraft(LocalBodyForm localBodyForm) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			DraftRenameLocalbodyTemp draftRenameLocalbodyTemp = new DraftRenameLocalbodyTemp();
			localBodyUtil.copyBeanAttributes(draftRenameLocalbodyTemp, localBodyForm);
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			session.saveOrUpdate(draftRenameLocalbodyTemp);
			tx.commit();
			status = Boolean.TRUE;
		} catch (HibernateException e) {
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}

	@Override
	public Boolean publishRenameLocalBody(LocalBodyForm localBodyForm) throws HibernateException {
		// TODO Auto-generated method stub
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			status = executeInsertRenameLocalBodyFunction(session, localBodyForm);
			if(localBodyForm.getId() != null){
				try {
					publishOrDeleteDraftRenameLBToTransaction(localBodyForm.getId(), Boolean.TRUE, null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					status = Boolean.FALSE;//LocalBodyConstant.LB_PUBLISH_FAILD.toString();
				}
			}
		} catch (HibernateException e) {
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}
	
	private Boolean executeInsertRenameLocalBodyFunction(Session session, LocalBodyForm localBodyForm) throws HibernateException {
		Boolean status = Boolean.FALSE;
		Query query = session.getNamedQuery("Change_Local_Body_Name_Fn");
				
		query.setParameter("local_body_code"				, localBodyForm.getLocalBodyCode()		  , Hibernate.INTEGER); //getId() // Local Body Code
		query.setParameter("local_body_name_name_english"	, localBodyForm.getLocalBodyNameEnglish() , Hibernate.STRING);
		query.setParameter("user_id"						, localBodyForm.getCreatedBy()			  , Hibernate.INTEGER);
		query.setParameter("order_no"						, localBodyForm.getOrderNo()			  , Hibernate.STRING);
		query.setParameter("order_date"						, localBodyForm.getOrderDate()            , Hibernate.DATE);
		query.setParameter("effective_date"					, localBodyForm.getEffectiveDate()        , Hibernate.DATE);
		query.setParameter("order_path"						, localBodyForm.getOrderPath()            , Hibernate.STRING);
		query.setParameter("gaz_pub_date"					, localBodyForm.getGazPubDate()			  , Hibernate.DATE);
		query.setParameter("local_body_name_name_local"		, localBodyForm.getLocalBodyNameLocal()	  , Hibernate.STRING);
		query.setParameter("alias_english"					, localBodyForm.getLocalBodyAliasEnglish(), Hibernate.STRING);
		query.setParameter("alias_local"					, localBodyForm.getLocalBodyAliasLocal()  ,	Hibernate.STRING);
		query.setParameter("goordercode"					, localBodyForm.getOrderCode()			  , Hibernate.INTEGER);
		query.setParameter("gofilename"						, localBodyForm.getOrderPath() 			  , Hibernate.STRING);
		query.setParameter("gofilesize"						, localBodyForm.getOrderFileSize() 		  , Hibernate.LONG);
		query.setParameter("gofiletype"						, localBodyForm.getOrderFileContentType() , Hibernate.STRING);

		@SuppressWarnings({ "unused", "rawtypes" })
		List comboList = query.list();
		status = Boolean.TRUE;
		return status;
	}
	

	@Override
	public List<CriteriaDraftedEntities> fetchDraftedEntities(String PANCHAYAT_TYPE, Integer localBodyTypeCode, Integer stateCode, String localBodyName, Integer processCode, Integer districtCode) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.fetchDraftedEntities execution started.");
		Session session = null;
		List<CriteriaDraftedEntities> draftedEntities = new ArrayList<CriteriaDraftedEntities>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Fetch_Drafted_Entities");
			query.setParameter("PANCHAYAT_TYPE", PANCHAYAT_TYPE, Hibernate.STRING);
			query.setParameter("localBodyType", localBodyTypeCode, Hibernate.INTEGER);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("localBodyName", localBodyName, Hibernate.STRING);
			query.setParameter("draftOperationCode", processCode, Hibernate.INTEGER);
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			draftedEntities = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.fetchDraftedEntities : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return draftedEntities;	
	}

	@Override
	public Map<String, Object> fetchDraftedRenamedLB(Integer tempEntityCode) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.fetchDraftedRenamedLB execution started.");
		Session session = null;
		Map<String, Object> draftedEntityMap = new HashedMap();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftRenameLocalbodyTemp.class).add(Restrictions.eq("id", tempEntityCode));
			DraftRenameLocalbodyTemp draftRenameLocalbodyTemp = (DraftRenameLocalbodyTemp) criteria.uniqueResult();
			draftedEntityMap.put("draftRenameLocalbodyTemp", draftRenameLocalbodyTemp);
			session.flush();
			
			criteria = session.createCriteria(LocalBodyTable.class);
			criteria.add(Restrictions.eq("localBodyCode", draftRenameLocalbodyTemp.getLocalBodyCode()));
			criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
			LocalBodyTable localBodyTable = (LocalBodyTable) criteria.uniqueResult();
			draftedEntityMap.put("localBodyTableObj", localBodyTable);
			session.flush();
			
			if(null != draftRenameLocalbodyTemp.getOrderCode()){
				Query query = session.getNamedQuery("Fetch_GO_Details_By_Order_Code");
				query.setParameter("paramOderCode", draftRenameLocalbodyTemp.getOrderCode(), Hibernate.INTEGER);
				draftedEntityMap.put("govtOrderDetails", query.uniqueResult());
			}
			draftedEntityMap.put("previousNames", fetchPreviousNames(localBodyTable.getLocalBodyCode()));
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.fetchDraftedRenamedLB : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return draftedEntityMap;	
	}
	
	@Override
	public Boolean publishOrDeleteDraftRenameLBToTransaction(Integer tempEntityCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.publishOrDeleteDraftRenameLBToTransaction execution started.");
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftRenameLocalbodyTemp.class).add(Restrictions.eq("id", tempEntityCode));
			DraftRenameLocalbodyTemp draftRenameLocalbodyTemp = (DraftRenameLocalbodyTemp) criteria.uniqueResult();
			
			if( !isDeleteStatus ) {
				LocalBodyForm localBodyForm =  convertDraftTempToLBForm(draftRenameLocalbodyTemp, Boolean.TRUE, request);//convertDraftLocalbodyTempToLBForm(draftLocalbodyTemp, Boolean.TRUE, request);
				executeInsertRenameLocalBodyFunction(session, localBodyForm);
				session.flush();
			}
			
			//Deleting Temporary Table Records
			Transaction tx = session.beginTransaction();
			session.delete(draftRenameLocalbodyTemp);
			tx.commit();
			status = Boolean.TRUE;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.publishOrDeleteDraftRenameLBToTransaction : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}
	
	@Override
	public LBAttributes getLBDetailsForModifyParent(Integer lbTypeCode, Integer parentLBCode, Integer stateCode) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getLBDetailsForModifyParent execution started.");
		Session session = null;
		LBAttributes lbAttributes = new LBAttributes();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LocalBodyTable.class);
			criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
			criteria.add(Restrictions.eq("lblc", parentLBCode));
			LocalBodyTable lbObject = (LocalBodyTable) criteria.uniqueResult();
			lbAttributes.setParentLocalBodyName(lbObject.getLocalBodyNameEnglish());
			//session.flush();
			
			Query query = session.createSQLQuery("select * from get_lb_type_name_fn(:lbTypeCode)");
			query.setParameter("lbTypeCode", lbTypeCode, Hibernate.INTEGER);
			lbAttributes.setLocalbodyTypeName((String) query.uniqueResult());
			//session.flush();
			
			query = session.getNamedQuery("List_Parent_Local_Bodies");
			query.setParameter("parentLBType", lbObject.getLocalBodyTypeCode(), Hibernate.INTEGER);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			//query.setParameter("parentLbCode", parentLBCode, Hibernate.INTEGER);
			query.setParameter("parentLbCode", lbObject.getLocalBodyCode(), Hibernate.INTEGER);
			List<LocalBodyEntityDetails> parentLBList = query.list();
			lbAttributes.setListLocalBodyEntities(parentLBList);
			
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getLBDetailsForModifyParent : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return lbAttributes;
	}

	@Override
	public Boolean saveChangeParentLocalBodyAsDraft(LocalBodyForm localBodyForm) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			DraftChangeParentLocalbodyTemp draftChangeParentLocalbodyTemp = new DraftChangeParentLocalbodyTemp();
			localBodyUtil.copyBeanAttributes(draftChangeParentLocalbodyTemp, localBodyForm);
			draftChangeParentLocalbodyTemp.setTempPkId(localBodyForm.getId());
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			session.saveOrUpdate(draftChangeParentLocalbodyTemp);
			tx.commit();
			status = Boolean.TRUE;
		} catch (HibernateException e) {
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}

	@Override
	public String publishChangeParentLocalBody(LocalBodyForm localBodyForm) throws Exception {
		// TODO Auto-generated method stub
		String status = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			status = executeInsertChangeParentLocalBodyFunction(session, localBodyForm);
			if(LocalBodyConstant.SUCCESS_MESSAGE.toString().equalsIgnoreCase(status)){
				if(localBodyForm.getId() != null){
					try {
						publishOrDeleteDraftChangeParentLBToTransaction(localBodyForm.getId(), Boolean.TRUE, null);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						throw e;
					}
				}
			}
		} catch (HibernateException e) {
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}
	
	private String executeInsertChangeParentLocalBodyFunction(Session session, LocalBodyForm localBodyForm) throws HibernateException {
		
		Query query = session.getNamedQuery("Change_Local_Body_Parent_Fn");
				
		query.setParameter("local_body_code"				, localBodyForm.getLocalBodyCode()		  , Hibernate.INTEGER); // Local Body Code
		query.setParameter("parent_lblc"					, localBodyForm.getParentLocalBodyCode()  , Hibernate.INTEGER);
		query.setParameter("user_id"						, localBodyForm.getCreatedBy()			  , Hibernate.INTEGER);
		query.setParameter("local_body_version"				, null  								  , Hibernate.INTEGER);
		query.setParameter("order_no"						, localBodyForm.getOrderNo()			  , Hibernate.STRING);
		query.setParameter("order_date"						, localBodyForm.getOrderDate()            , Hibernate.DATE);
		query.setParameter("effective_date"					, localBodyForm.getEffectiveDate()        , Hibernate.DATE);
		query.setParameter("gaz_pub_date"					, localBodyForm.getGazPubDate()			  , Hibernate.DATE);
		query.setParameter("order_path"						, localBodyForm.getOrderPath()            , Hibernate.STRING);
		query.setParameter("goordercode"					, localBodyForm.getOrderCode()			  , Hibernate.INTEGER);
		query.setParameter("gofilename"						, localBodyForm.getOrderPath() 			  , Hibernate.STRING);
		query.setParameter("gofilesize"						, localBodyForm.getOrderFileSize() 		  , Hibernate.LONG);
		query.setParameter("gofiletype"						, localBodyForm.getOrderFileContentType() , Hibernate.STRING);

		
		InsertionLocalBodyFn returnedValue = (InsertionLocalBodyFn) query.uniqueResult();
		return returnedValue.getCreate_localbody_fn();
	}

	@Override
	public DraftChangeParentLocalbodyTemp getDraftChangeParentLocalbodyTemp(Integer entityTempId) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getDraftChangeParentLocalbodyTemp execution started.");
		Session session = null;
		DraftChangeParentLocalbodyTemp draftChangeParentLocalbodyTemp = new DraftChangeParentLocalbodyTemp();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftChangeParentLocalbodyTemp.class);
			criteria.add(Restrictions.eq("tempPkId", entityTempId));
			draftChangeParentLocalbodyTemp = (DraftChangeParentLocalbodyTemp) criteria.uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getDraftChangeParentLocalbodyTemp : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return draftChangeParentLocalbodyTemp;
	}

	@Override
	public Map<String, Object> fetchDraftedChangeParentLB(Integer tempEntityCode) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.fetchDraftedChangeParentLB execution started.");
		Session session = null;
		Map<String, Object> draftedEntityMap = new HashedMap();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftChangeParentLocalbodyTemp.class).add(Restrictions.eq("tempPkId", tempEntityCode));
			DraftChangeParentLocalbodyTemp draftChangeParentLocalbodyTemp = (DraftChangeParentLocalbodyTemp) criteria.uniqueResult();
			draftedEntityMap.put("draftChangeParentLocalbodyTemp", draftChangeParentLocalbodyTemp);
			session.flush();
			
			criteria = session.createCriteria(LocalBodyTable.class);
			criteria.add(Restrictions.eq("localBodyCode", draftChangeParentLocalbodyTemp.getLocalBodyCode()));
			criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
			LocalBodyTable localBodyTableObj = (LocalBodyTable) criteria.uniqueResult();
			draftedEntityMap.put("localBodyTableObj", localBodyTableObj);
			session.flush();
			
			ProjectionList proList = Projections.projectionList();
	        proList.add(Projections.property("localBodyNameEnglish"));
			
			criteria = session.createCriteria(LocalBodyTable.class);
			criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
			criteria.add(Restrictions.eq("lblc", localBodyTableObj.getParentLocalBodyCode()));
			criteria.setProjection(proList);
			String lbParentName = (String) criteria.uniqueResult();
			draftedEntityMap.put("parentLBName", lbParentName);
			session.flush();
			
			criteria = session.createCriteria(LocalBodyTable.class);
			criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
			criteria.add(Restrictions.eq("lblc", draftChangeParentLocalbodyTemp.getParentLocalBodyCode()));
			criteria.setProjection(proList);
			String lbNewParentName = (String) criteria.uniqueResult();
			draftedEntityMap.put("newParentLBName", lbNewParentName);
			session.flush();
			
			Query query = session.createSQLQuery("select * from get_lb_type_name_fn(:lbTypeCode)");
			query.setParameter("lbTypeCode", localBodyTableObj.getLocalBodyTypeCode(), Hibernate.INTEGER);
			draftedEntityMap.put("lbTypeName", (String) query.uniqueResult());
			session.flush();
			
			if(null != draftChangeParentLocalbodyTemp.getOrderCode()){
				query = session.getNamedQuery("Fetch_GO_Details_By_Order_Code");
				query.setParameter("paramOderCode", draftChangeParentLocalbodyTemp.getOrderCode(), Hibernate.INTEGER);
				draftedEntityMap.put("govtOrderDetails", query.uniqueResult());
			}
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.fetchDraftedChangeParentLB : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return draftedEntityMap;	
	}

	@Override
	public String publishOrDeleteDraftChangeParentLBToTransaction(Integer tempEntityCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.publishOrDeleteDraftChangeParentLBToTransaction execution started.");
		String status = LocalBodyConstant.SUCCESS_MESSAGE.toString();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftChangeParentLocalbodyTemp.class).add(Restrictions.eq("tempPkId", tempEntityCode));
			DraftChangeParentLocalbodyTemp draftChangeParentLocalbodyTemp = (DraftChangeParentLocalbodyTemp) criteria.uniqueResult();
			
			if( !isDeleteStatus ) {
				LocalBodyForm localBodyForm =  convertDraftTempToLBForm(draftChangeParentLocalbodyTemp, Boolean.TRUE, request);//convertDraftLocalbodyTempToLBForm(draftLocalbodyTemp, Boolean.TRUE, request);
				status = executeInsertChangeParentLocalBodyFunction(session, localBodyForm);
				session.flush();
			}
			if(LocalBodyConstant.SUCCESS_MESSAGE.toString().equalsIgnoreCase(status)){
				//Deleting Temporary Table Records
				Transaction tx = session.beginTransaction();
				session.delete(draftChangeParentLocalbodyTemp);
				tx.commit();
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.publishOrDeleteDraftChangeParentLBToTransaction : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}

	@Override
	public Map<String, Object> getLBDetailsForModifyType(Integer lbTypeCode, Integer stateCode) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getLBDetailsForModifyType execution started.");
		Session session = null;
		Map<String, Object> localBodyDetailsMap = new HashedMap();
		try {
			session = sessionFactory.openSession();
			
			Query query = session.createSQLQuery("select * from get_lb_type_name_fn(:lbTypeCode,:stateCode)");
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("lbTypeCode", lbTypeCode, Hibernate.INTEGER);
			localBodyDetailsMap.put("localBodyTypeName", (String) query.uniqueResult());
			session.flush();
			
			query = session.getNamedQuery("Excluded_Selection_Urban_LB_Type");
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("panchayatType", LocalBodyConstant.LB_URBAN.toString(), Hibernate.STRING);
			query.setParameter("selectedLBType", lbTypeCode, Hibernate.INTEGER);
			List<LBTypeDetails> lbTypes = query.list();
			localBodyDetailsMap.put("urbanLocalBodyTypes", lbTypes);
			session.flush();
			
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getLBDetailsForModifyType : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return localBodyDetailsMap;
	}

	@Override
	public Boolean saveChangeTypeLocalBodyAsDraft(LocalBodyForm localBodyForm) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.saveChangeTypeLocalBodyAsDraft execution started.");
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			DraftChangeTypeLocalbodyTemp draftChangeTypeLocalbodyTemp = new DraftChangeTypeLocalbodyTemp();
			localBodyUtil.copyBeanAttributes(draftChangeTypeLocalbodyTemp, localBodyForm);
			draftChangeTypeLocalbodyTemp.setTempPkId(localBodyForm.getId());
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			session.saveOrUpdate(draftChangeTypeLocalbodyTemp);
			tx.commit();
			status = Boolean.TRUE;
		} catch (HibernateException e) {
			log.error("Error in LocalBodyDaoImpl.publishOrDeleteDraftChangeParentLBToTransaction : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}

	@Override
	public Boolean publishChangeTypeLocalBody(LocalBodyForm localBodyForm) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.publishChangeTypeLocalBody execution started.");
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			status = executeInsertChangeTypeLocalBodyFunction(session, localBodyForm);
			if(localBodyForm.getId() != null){
				try {
					publishOrDeleteDraftChangeTypeLBToTransaction(localBodyForm.getId(), Boolean.TRUE, null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					status = Boolean.FALSE;//LocalBodyConstant.LB_PUBLISH_FAILD.toString();
				}
			}
		} catch (HibernateException e) {
			log.error("Error in LocalBodyDaoImpl.publishChangeTypeLocalBody : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}
	
	private Boolean executeInsertChangeTypeLocalBodyFunction(Session session, LocalBodyForm localBodyForm) throws HibernateException {
		Boolean status = Boolean.FALSE;
		Query query = session.getNamedQuery("Change_Local_Body_Type_Fn");
		
		query.setParameter("local_body_code"		, localBodyForm.getLocalBodyCode()		  , Hibernate.INTEGER); // Local Body Code
		query.setParameter("local_body_name_english", localBodyForm.getLocalBodyNameEnglish() , Hibernate.STRING);
		query.setParameter("description"			, null                 					  , Hibernate.STRING);
		query.setParameter("local_body_type_code"	, localBodyForm.getLocalBodyTypeCode()    , Hibernate.INTEGER);
		query.setParameter("local_body_subtype_code", null  								  , Hibernate.INTEGER);
		query.setParameter("parent_local_body_code"	, 0										  , Hibernate.INTEGER);
		query.setParameter("state_code"				, localBodyForm.getStateCode()			  , Hibernate.INTEGER);
		query.setParameter("effective_date"			, localBodyForm.getEffectiveDate()        , Hibernate.DATE);
		query.setParameter("createdby"				, localBodyForm.getCreatedBy()            , Hibernate.INTEGER);
		query.setParameter("order_no"				, localBodyForm.getOrderNo()			  , Hibernate.STRING);
		query.setParameter("order_date"				, localBodyForm.getOrderDate()            , Hibernate.DATE);
		query.setParameter("gaz_pub_date"			, localBodyForm.getGazPubDate()			  , Hibernate.DATE);
		query.setParameter("order_path"				, localBodyForm.getOrderPath()            , Hibernate.STRING);
		query.setParameter("goordercode"			, localBodyForm.getOrderCode()			  , Hibernate.INTEGER);
		query.setParameter("gofilename"				, localBodyForm.getOrderPath() 			  , Hibernate.STRING);
		query.setParameter("gofilesize"				, localBodyForm.getOrderFileSize() 		  , Hibernate.LONG);
		query.setParameter("gofiletype"				, localBodyForm.getOrderFileContentType() , Hibernate.STRING);
		
		@SuppressWarnings({ "unused" })
		Object queryOutput = query.uniqueResult();
		status = Boolean.TRUE;
		return status;
	}
	
	@Override
	public Map<String, Object> fetchDraftedChangeTypeLB(Integer tempEntityCode) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.fetchDraftedChangeParentLB execution started.");
		Session session = null;
		Map<String, Object> draftedEntityMap = new HashedMap();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftChangeTypeLocalbodyTemp.class).add(Restrictions.eq("tempPkId", tempEntityCode));
			DraftChangeTypeLocalbodyTemp draftChangeTypeLocalbodyTemp = (DraftChangeTypeLocalbodyTemp) criteria.uniqueResult();
			draftedEntityMap.put("draftChangeTypeLocalbodyTemp", draftChangeTypeLocalbodyTemp);
			session.flush();
			
			criteria = session.createCriteria(LocalBodyTable.class);
			criteria.add(Restrictions.eq("localBodyCode", draftChangeTypeLocalbodyTemp.getLocalBodyCode()));
			criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
			LocalBodyTable localBodyTable = (LocalBodyTable) criteria.uniqueResult();
			draftedEntityMap.put("localBodyTableObj", localBodyTable);
			session.flush();
			
			Query query = session.createSQLQuery("select * from get_lb_type_name_fn(:lbTypeCode)");
			query.setParameter("lbTypeCode", localBodyTable.getLocalBodyTypeCode(), Hibernate.INTEGER);
			draftedEntityMap.put("previousLocalBodyTypeName", (String) query.uniqueResult());
			session.flush();
			
			query = session.createSQLQuery("select * from get_lb_type_name_fn(:lbTypeCode)");
			query.setParameter("lbTypeCode", draftChangeTypeLocalbodyTemp.getLocalBodyTypeCode(), Hibernate.INTEGER);
			draftedEntityMap.put("currentLocalBodyTypeName", (String) query.uniqueResult());
			session.flush();
			
			if(null != draftChangeTypeLocalbodyTemp.getOrderCode()){
				query = session.getNamedQuery("Fetch_GO_Details_By_Order_Code");
				query.setParameter("paramOderCode", draftChangeTypeLocalbodyTemp.getOrderCode(), Hibernate.INTEGER);
				draftedEntityMap.put("govtOrderDetails", query.uniqueResult());
			}
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.fetchDraftedChangeParentLB : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return draftedEntityMap;	
	}
	
	@Override
	public DraftChangeTypeLocalbodyTemp getDraftChangeTypeLocalbodyTemp(Integer entityTempId) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getDraftChangeTypeLocalbodyTemp execution started.");
		Session session = null;
		DraftChangeTypeLocalbodyTemp draftChangeTypeLocalbodyTemp = new DraftChangeTypeLocalbodyTemp();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftChangeTypeLocalbodyTemp.class);
			criteria.add(Restrictions.eq("tempPkId", entityTempId));
			draftChangeTypeLocalbodyTemp = (DraftChangeTypeLocalbodyTemp) criteria.uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getDraftChangeTypeLocalbodyTemp : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return draftChangeTypeLocalbodyTemp;
	}

	@Override
	public Boolean publishOrDeleteDraftChangeTypeLBToTransaction(Integer tempEntityCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.publishOrDeleteDraftChangeTypeLBToTransaction execution started.");
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftChangeTypeLocalbodyTemp.class).add(Restrictions.eq("tempPkId", tempEntityCode));
			DraftChangeTypeLocalbodyTemp draftChangeTypeLocalbodyTemp = (DraftChangeTypeLocalbodyTemp) criteria.uniqueResult();
			
			if( !isDeleteStatus ) {
				LocalBodyForm localBodyForm =  convertDraftTempToLBForm(draftChangeTypeLocalbodyTemp, Boolean.TRUE, request);//convertDraftLocalbodyTempToLBForm(draftLocalbodyTemp, Boolean.TRUE, request);
				executeInsertChangeTypeLocalBodyFunction(session, localBodyForm);
				session.flush();
			}
			
			//Deleting Temporary Table Records
			Transaction tx = session.beginTransaction();
			session.delete(draftChangeTypeLocalbodyTemp);
			tx.commit();
			status = Boolean.TRUE;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.publishOrDeleteDraftChangeTypeLBToTransaction : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}
	
	@Override
	public Map<String, Object> getLBDetailsForModifyGovOrder(Integer localBodyCode, Integer stateCode, String PANCHAYAT_TYPE_CONSTANT) throws Exception {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getLBDetailsForModifyType execution started.");
		Session session = null;
		Map<String, Object> mapAttributes = new HashedMap();
		try {
			
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LocalBodyTable.class);
			criteria.add(Restrictions.eq("localBodyCode", localBodyCode));
			criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
			LocalBodyTable lbEntiity = (LocalBodyTable) criteria.uniqueResult();
			session.flush();
			
			Query query = session.getNamedQuery("Entity_Wise_GO_Details");
			query.setParameter("entityType", LocalBodyConstant.LOCAL_BODY_CONSTANT.toString(), Hibernate.STRING);
			query.setParameter("entityCode", lbEntiity.getLocalBodyCode(), Hibernate.INTEGER);
			query.setParameter("entityVersion", lbEntiity.getLocalBodyVersion(), Hibernate.INTEGER);
			GovernmentOrderDetails governmentOrderDetails = (GovernmentOrderDetails) query.uniqueResult();
			session.flush();
			
			LocalBodyForm localBodyForm = new LocalBodyForm();
			localBodyUtil.copyBeanAttributes(localBodyForm, lbEntiity);
			if(governmentOrderDetails != null) {
				localBodyForm.setOrderCode(governmentOrderDetails.getOrderCode());
				localBodyForm.setOrderNo(governmentOrderDetails.getOrderNo());
				localBodyForm.setOrderDate(governmentOrderDetails.getOrderDate());
				localBodyForm.setOrderDate(governmentOrderDetails.getOrderDate());
				localBodyForm.setEffectiveDate(governmentOrderDetails.getEffectiveDate());
				localBodyForm.setGazPubDate(governmentOrderDetails.getGazPubDate());
				localBodyForm.setOrderPath(governmentOrderDetails.getOrderPath());
				localBodyForm.setOrderFileSize(governmentOrderDetails.getFileSize());
				localBodyForm.setOrderFileContentType(governmentOrderDetails.getFileContentType());
			}
			localBodyForm.setLocalBodyCreationType(PANCHAYAT_TYPE_CONSTANT);
			
			query = session.createSQLQuery("SELECT tier_setup_code FROM get_local_gov_setup_fn(:stateCode, :panchayatType) where local_body_type_code = :paramLBTypeCode");
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("panchayatType", PANCHAYAT_TYPE_CONSTANT, Hibernate.STRING);
			query.setParameter("paramLBTypeCode", lbEntiity.getLocalBodyTypeCode(), Hibernate.INTEGER);
			Integer tierSetupCode = (Integer) query.uniqueResult();
			Boolean isMapUpload = checkMapUpload(tierSetupCode);
			session.flush();
			mapAttributes.put("isMapUploadModifyGO", isMapUpload);
			
			if(isMapUpload){
				query = session.createQuery("from MapAttachment where map_lc =:localBodyCode and mapEntityType =:entityType");
				query.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
				query.setParameter("entityType", LocalBodyConstant.LOCAL_BODY_CONSTANT.toString(), Hibernate.STRING);
				MapAttachment mapAttachment = (MapAttachment) query.uniqueResult();
				session.flush();
				mapAttributes.put("mapAttachment", mapAttachment);
				if(mapAttachment != null){
					localBodyForm.setMapUploadPath(mapAttachment.getFileName());
				}
				
			}
			mapAttributes.put("localBodyForm", localBodyForm);
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getLBDetailsForModifyType : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return mapAttributes;
	}

	@Override
	public Boolean saveChangeGOLocalBodyAsDraft(LocalBodyForm localBodyForm) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.saveChangeTypeLocasaveChangeGOLocalBodyAsDraftlBodyAsDraft execution started.");
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			DraftChangeGovtOrderLBTemp draftChangeGovtOrderLBTemp = new DraftChangeGovtOrderLBTemp();
			localBodyUtil.copyBeanAttributes(draftChangeGovtOrderLBTemp, localBodyForm);
			draftChangeGovtOrderLBTemp.setTempPkId(localBodyForm.getId());
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			session.saveOrUpdate(draftChangeGovtOrderLBTemp);
			tx.commit();
			status = Boolean.TRUE;
		} catch (HibernateException e) {
			log.error("Error in LocalBodyDaoImpl.saveChangeGOLocalBodyAsDraft : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}

	@Override
	public Boolean publishChangeGOLocalBody(LocalBodyForm localBodyForm) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.publishChangeGOLocalBody execution started.");
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			status = executeInsertChangeGovOrderFunction(session, localBodyForm);
			if(localBodyForm.getId() != null){
				try {
					publishOrDeleteDraftChangeGOToTransaction(localBodyForm.getId(), Boolean.TRUE, null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					status = Boolean.FALSE;
				}
			}
		} catch (HibernateException e) {
			log.error("Error in LocalBodyDaoImpl.publishChangeGOLocalBody : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}

	@Override
	public DraftChangeGovtOrderLBTemp getDraftChangeGovtOrderLBTemp(Integer tempEntityCode) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getDraftChangeGovtOrderLBTemp execution started.");
		Session session = null;
		DraftChangeGovtOrderLBTemp draftChangeGovtOrderLBTemp = new DraftChangeGovtOrderLBTemp();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftChangeGovtOrderLBTemp.class);
			criteria.add(Restrictions.eq("tempPkId", tempEntityCode));
			draftChangeGovtOrderLBTemp = (DraftChangeGovtOrderLBTemp) criteria.uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getDraftChangeGovtOrderLBTemp : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return draftChangeGovtOrderLBTemp;
	}

	@Override
	public Boolean publishOrDeleteDraftChangeGOToTransaction( Integer tempEntityCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.publishOrDeleteDraftChangeGOToTransaction execution started.");
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftChangeGovtOrderLBTemp.class).add(Restrictions.eq("tempPkId", tempEntityCode));
			DraftChangeGovtOrderLBTemp draftChangeGovtOrderLBTemp = (DraftChangeGovtOrderLBTemp) criteria.uniqueResult();
			
			if( !isDeleteStatus ) {
				LocalBodyForm localBodyForm =  convertDraftLocalbodyTempToLBForm(draftChangeGovtOrderLBTemp, Boolean.TRUE, request);//convertDraftLocalbodyTempToLBForm(draftLocalbodyTemp, Boolean.TRUE, request);
				executeInsertChangeGovOrderFunction(session, localBodyForm);
				session.flush();
			}
			
			//Deleting Temporary Table Records
			Transaction tx = session.beginTransaction();
			session.delete(draftChangeGovtOrderLBTemp);
			tx.commit();
			status = Boolean.TRUE;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.publishOrDeleteDraftChangeGOToTransaction : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}


	private Boolean executeInsertChangeGovOrderFunction(Session session, LocalBodyForm localBodyForm) throws HibernateException {
		Boolean status = Boolean.FALSE;
		/*Query query = session.getNamedQuery("Change_Local_Body_Type_Fn");
				
		query.setParameter("local_body_code"		, localBodyForm.getLocalBodyCode()		  , Hibernate.INTEGER); // Local Body Code
		query.setParameter("local_body_name_english", localBodyForm.getLocalBodyNameEnglish() , Hibernate.STRING);
		query.setParameter("local_body_type_code"	, localBodyForm.getLocalBodyTypeCode()    , Hibernate.INTEGER);
		query.setParameter("description"			, null                 					  , Hibernate.STRING);
		query.setParameter("local_body_subtype_code", null  								  , Hibernate.INTEGER);
		query.setParameter("parent_local_body_code"	, 0										  , Hibernate.INTEGER);
		query.setParameter("state_code"				, localBodyForm.getStateCode()			  , Hibernate.INTEGER);
		query.setParameter("order_no"				, localBodyForm.getOrderNo()			  , Hibernate.STRING);
		query.setParameter("order_date"				, localBodyForm.getOrderDate()            , Hibernate.DATE);
		query.setParameter("effective_date"			, localBodyForm.getEffectiveDate()        , Hibernate.DATE);
		query.setParameter("gaz_pub_date"			, localBodyForm.getGazPubDate()			  , Hibernate.DATE);
		query.setParameter("order_path"				, localBodyForm.getOrderPath()            , Hibernate.STRING);
		query.setParameter("createdby"				, localBodyForm.getCreatedBy()            , Hibernate.INTEGER);
		
		@SuppressWarnings({ "unused" })
		Object queryOutput = query.uniqueResult();*/
		status = Boolean.TRUE;
		return status;
	}

	@Override
	public LocalBodyForm setGOandMapFileAttributes(LocalBodyForm localBodyForm) throws Exception {
		// TODO Auto-generated method stub
		try{
			AttachmentMaster master = getUploadFileConfigurationDetails(Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_GO.toString()));
			setGovernmentOrderFileAttributes(localBodyForm, master.getFileLocation());
			
			AttachmentMaster masterMap = getUploadFileConfigurationDetails(Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_MAP.toString()));
			setMapFileAttributes(localBodyForm, masterMap.getFileLocation());
		}catch(Exception e){
			throw e;
		}
		return localBodyForm;
	}
	
	@Override
	public Map<String, Object> changeCoverageDefaultDetails(LocalBodyForm localBodyForm) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getLBDetailsForModifyType execution started.");
		Session session = null;
		Map<String, Object> mapAttributes = new HashedMap();
		try {
			session = sessionFactory.openSession();
			String namedQuery="Fetch_Completed_Coverage_Details";
			if(("Y").equals(localBodyForm.getIsGISCoverage())){
				namedQuery="Fetch_GIS_Completed_Coverage_Details";
			}
			Query query = session.getNamedQuery(namedQuery);
			query.setParameter("localBodyCode", localBodyForm.getLocalBodyCode(), Hibernate.INTEGER);
			List<CompletedCoverageDetails> completeCoverages = query.list();
			mapAttributes.put("completedCoverageDetails", completeCoverages);
			session.flush();
			
			if( ! LocalBodyConstant.LB_URBAN.toString().equalsIgnoreCase(localBodyForm.getLocalBodyCreationType())){
				query = session.createSQLQuery("select level from local_body_type where local_body_type_code = :lbTypeCode and isactive");
				query.setParameter("lbTypeCode", localBodyForm.getLocalBodyTypeCode());
				mapAttributes.put("localBodyTypeLevel", query.uniqueResult());
				session.flush();
				
				query = session.createSQLQuery("select lrlc from lb_covered_landregion where lb_covered_region_code = :lbCoveredRegionCode and isactive and ismainregion");
				query.setParameter("lbCoveredRegionCode", localBodyForm.getLbCoveredRegionCode(), Hibernate.INTEGER);
				//Integer hqCode = (Integer) query.uniqueResult();
				List<Integer> hqCodeList =  query.list();
				//if(hqCode != null){
				if(hqCodeList != null && !hqCodeList.isEmpty()) {
					//localBodyForm.setHeadQuarterCode(hqCode.toString());
					localBodyForm.setHeadQuarterCode(hqCodeList.get(0).toString());
				}
				session.flush();
			}
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getLBDetailsForModifyType : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return mapAttributes;
	}
	
	@Override
	public String publishChangeCoveredArea(LocalBodyForm localBodyForm) throws Exception {
		// TODO Auto-generated method stub
		String status = LocalBodyConstant.LB_PUBLISH_FAILD.toString();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			InsertionLocalBodyFn returnedValue = (InsertionLocalBodyFn) executeUpdateChangedCoverageLBFunction(session, localBodyForm);
			status = returnedValue.getCreate_localbody_fn();
			if(localBodyForm.getId() != null){
				try {
					publishOrDeleteDraftToTransactionChangeCoverage(localBodyForm.getId(), Boolean.TRUE, null);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					status = LocalBodyConstant.LB_PUBLISH_FAILD.toString();
				}
			}
		} catch (HibernateException e) {
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}

	@Override
	public Boolean saveChangeCoveredAreaAsDraft(DraftChangeCoverageTemp draftChangeCoverageTemp) throws Exception {
		// TODO Auto-generated method stub
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			if(draftChangeCoverageTemp.getId() != null) {
				Criteria c=  session.createCriteria(DraftUsedChangeCoverageLbLrTemp.class).add(Restrictions.eq("draftChangeCoverageTemp", draftChangeCoverageTemp));
				List<DraftUsedChangeCoverageLbLrTemp> lst = c.list();
				for(DraftUsedChangeCoverageLbLrTemp temp : lst){
					session.delete(temp);
				}
				session.flush();
			}
			session.saveOrUpdate(draftChangeCoverageTemp);
			tx.commit();
			status = Boolean.TRUE;
		} catch (HibernateException e) {
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}

	@Override
	public Map<String, Object> getDraftedChangeCoverageDetails(Integer tempChangeCoveredAreaCode) throws Exception {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getDraftedTempChangeCoverageDetails execution started.");
		Session session = null;
		Map<String, Object> draftedEntityMap = new HashedMap();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftChangeCoverageTemp.class).add(Restrictions.eq("id", tempChangeCoveredAreaCode));
			DraftChangeCoverageTemp draftChangeCoverageTemp = (DraftChangeCoverageTemp) criteria.uniqueResult();	
			draftedEntityMap.put("draftLocalbodyTemp", draftChangeCoverageTemp);
			
			LocalBodyForm toBean = new LocalBodyForm();
			localBodyUtil.copyBeanAttributes(toBean, getLocalBodyFormObject(draftChangeCoverageTemp.getLocalBodyCode()));
			draftedEntityMap.putAll(changeCoverageDefaultDetails(toBean));
			
			final String removedLRstr = draftChangeCoverageTemp.getRemovedLangRegionCodes();
			if(StringUtils.isNotBlank(removedLRstr)) {
				List<CompletedCoverageDetails> completeCoverages =( List<CompletedCoverageDetails> ) draftedEntityMap.get("completedCoverageDetails");				
				Collection<CompletedCoverageDetails> selectedObjects = CollectionUtils.select(completeCoverages, new Predicate() {
							public boolean evaluate(Object input) {
								CompletedCoverageDetails cov = (CompletedCoverageDetails) input;
								String c = String.valueOf(cov.getLandRegionCode());
								return !(Arrays.asList(removedLRstr.split(",")).contains(c));
							}
				});
				completeCoverages.clear();
				completeCoverages.addAll(selectedObjects);
			}
			
			Query query = session.getNamedQuery("Drafted_LB_Coverage_Details");
			query.setParameter("draftedLBCode", draftChangeCoverageTemp.getId(), Hibernate.INTEGER);
			query.setParameter("paramTableName", LocalBodyConstant.CHANGE_COVERAGE_DRAFT_TABLE.toString(), Hibernate.STRING);
			List<DraftedLBCoverageDetails> draftedCoverages = query.list();
			draftedEntityMap.put("draftedLBCoverages", draftedCoverages);
			session.clear();
			

			Integer hqCode = draftChangeCoverageTemp.getHeadQuarterCode() != null ? Integer.parseInt(draftChangeCoverageTemp.getHeadQuarterCode()) : null;
			query = session.getNamedQuery("Fetch_Headquarter_Details");
			query.setParameter("localBodyCode", draftChangeCoverageTemp.getLocalBodyCode(), Hibernate.INTEGER);
			query.setParameter("headquarterCode", hqCode, Hibernate.INTEGER);
			List<DraftedLBCoverageDetails> hqCoverage = query.list();
			draftedEntityMap.put("headquarterDetails", hqCoverage);
			session.flush();
			
			if(null != draftChangeCoverageTemp.getOrderCode()){
				query = session.getNamedQuery("Fetch_GO_Details_By_Order_Code");
				query.setParameter("paramOderCode", draftChangeCoverageTemp.getOrderCode(), Hibernate.INTEGER);
				draftedEntityMap.put("govtOrderDetails", query.uniqueResult());
			}
			draftedEntityMap.put("localBodyForm", toBean);
			
		} catch (HibernateException e) {
			log.error("Error in LocalBodyDaoImpl.getDraftedTempLBDetails : ", e);
			throw e;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			closeConnection(session);
		}
		return draftedEntityMap;	
	}
	
	@Override
	public Map<String, Object> getDraftedChangeCoverageModification(Integer tempChangeCoverageCode) throws Exception {
		// TODO Auto-generated method stub
		try{
			Map<String, Object> mapChangeCoverageDetails = getDraftedChangeCoverageDetails(tempChangeCoverageCode);
			LocalBodyForm lbForm = (LocalBodyForm) mapChangeCoverageDetails.get("localBodyForm");
			localBodyUtil.copyBeanAttributes(lbForm, mapChangeCoverageDetails.get("draftLocalbodyTemp"));
			mapChangeCoverageDetails.remove("draftLocalbodyTemp");
			mapChangeCoverageDetails.remove("localBodyForm");
			mapChangeCoverageDetails.put("lbForm", convertDraftLocalbodyTempToLBForm(lbForm, Boolean.FALSE, null));
			return mapChangeCoverageDetails;
		}catch(Exception ex){
			throw ex;
		}
	}
	
	
	@Override
	public Boolean publishOrDeleteDraftToTransactionChangeCoverage(Integer tempLocalBodyCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.publishOrDeleteDraftToTransactionChangeCoverage execution started.");
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DraftChangeCoverageTemp.class).add(Restrictions.eq("id", tempLocalBodyCode));
			DraftChangeCoverageTemp draftChangeCoverageTemp = (DraftChangeCoverageTemp) criteria.uniqueResult();
			if( !isDeleteStatus ) {
				LocalBodyForm localBodyForm = convertDraftTempToLBForm(draftChangeCoverageTemp, Boolean.TRUE, request);
				executeUpdateChangedCoverageLBFunction(session, localBodyForm);
				session.flush();
			}
			
			//Deleting Temporary Table Records
			Transaction tx = session.beginTransaction();			
			session.delete(draftChangeCoverageTemp);
			tx.commit();
			status = Boolean.TRUE;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.publishOrDeleteDraftToTransactionChangeCoverage : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return status;
	}

	/**
	 * 
	 * @param session
	 * @param localBodyForm
	 * @return
	 */
	private Object executeUpdateChangedCoverageLBFunction (Session session, LocalBodyForm localBodyForm){
		StringBuilder consolidateLBCoverages = new StringBuilder();
		Collection<Integer> removedCodes = new ArrayList<Integer>();
		boolean isAppendCondition = Boolean.FALSE;
		if(StringUtils.isNotBlank(localBodyForm.getRemovedLangRegionCodes())){
			removedCodes = CollectionUtils.collect(Arrays.asList(localBodyForm.getRemovedLangRegionCodes().split(",")), new Transformer() {
				@Override
				public Integer transform(Object input) {
					return Integer.parseInt(input.toString());
				}
			});
			isAppendCondition = Boolean.TRUE;
		}
		
		StringBuilder queryBuilder = new StringBuilder(" select land_region_code").append(" || '_' || ");
		queryBuilder.append(" case when coverage_type = 'P' then 'PART' else 'FULL' end ").append("|| '_' ||");
		queryBuilder.append(" land_region_type").append("|| '_' || ");
		queryBuilder.append(" case when land_region_code = :headquarterCode then 'true' else 'false' end ");
		if(localBodyForm.getIsGISCoverage()!=null && ("Y").equals(localBodyForm.getIsGISCoverage())){
			queryBuilder.append(" from get_gis_draft_localbody_coverage(:localBodyCode) ");
		}else{
			queryBuilder.append(" from get_renew_lb_coverage_complete_list_fn(:localBodyCode) ");
		}
		
		if( isAppendCondition ){
			queryBuilder.append(" where land_region_code not in (:removedCodes)");
		}
		
		Query query = session.createSQLQuery(queryBuilder.toString());
		query.setParameter("localBodyCode"	, localBodyForm.getLocalBodyCode(), Hibernate.INTEGER);
		query.setParameter("headquarterCode", StringUtils.isNotBlank(localBodyForm.getHeadQuarterCode()) ? 
											  Integer.parseInt(localBodyForm.getHeadQuarterCode()) : null, Hibernate.INTEGER);
		if( isAppendCondition ){
			query.setParameterList("removedCodes", removedCodes);
		}
		List<String> dbCoverages = query.list();
		
		if(!dbCoverages.isEmpty()){
			consolidateLBCoverages.append( StringUtils.join(dbCoverages, ','));
			if(StringUtils.isNotBlank(localBodyForm.getContributingLandRegionCodes())){
				consolidateLBCoverages.append(",");
				consolidateLBCoverages.append(localBodyForm.getContributingLandRegionCodes());
			}
		}else{
			consolidateLBCoverages.append(localBodyForm.getContributingLandRegionCodes());
		}
		session.flush();
		
		query = session.getNamedQuery("Change_Local_Body_Covered_Area_Fn")
					   .setParameter("local_body_code"			, localBodyForm.getLocalBodyCode()		 , Hibernate.INTEGER)
					   .setParameter("land_region_list"       	, consolidateLBCoverages.toString()		 , Hibernate.STRING)  
					   .setParameter("user_id"					, localBodyForm.getCreatedBy()			 , Hibernate.INTEGER)
					   .setParameter("order_no"					, localBodyForm.getOrderNo()			 , Hibernate.STRING)
					   .setParameter("order_date"				, localBodyForm.getOrderDate()			 , Hibernate.DATE)
					   .setParameter("effective_date"			, localBodyForm.getEffectiveDate()		 , Hibernate.DATE)
					   .setParameter("gaz_pub_date"				, localBodyForm.getGazPubDate()			 , Hibernate.DATE)
					   .setParameter("order_path"				, localBodyForm.getOrderPath()			 , Hibernate.STRING)
					   .setParameter("contributing_lb_list"	 	, StringUtils.isNotBlank(localBodyForm.getContributingLBCodes()) ? localBodyForm.getContributingLBCodes() : null, Hibernate.STRING)
					   .setParameter("contributing_f_lb_list" 	, null									 , Hibernate.STRING)
					   .setParameter("goordercode"				, localBodyForm.getOrderCode()			 , Hibernate.INTEGER)
					   .setParameter("gofilename"				, localBodyForm.getOrderPath() 			 , Hibernate.STRING)
					   .setParameter("gofilesize"				, localBodyForm.getOrderFileSize() 		 , Hibernate.LONG)
					   .setParameter("gofiletype"				, localBodyForm.getOrderFileContentType(), Hibernate.STRING);
		
		Object returnedValue =  query.uniqueResult();
		return returnedValue;
	}
	
	@Override
	public List<Object[]> getLBListOfSelectedLR(Integer landRegionCode, Integer lbtypecode) throws Exception {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getLBListOfSelectedLR execution started.");
		Session session = null;
		List<Object[]> localbodyList=new ArrayList<Object[]>();
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select local_body_code, local_body_name_english from localbody where lb_covered_region_code in(select lb_covered_region_code from lb_covered_landregion where lrlc=:ldCode and isactive) and isactive and local_body_type_code=:lbtypecode");
			query.setParameter("ldCode", landRegionCode, Hibernate.INTEGER);
			query.setParameter("lbtypecode", lbtypecode, Hibernate.INTEGER);			
			localbodyList = query.list();
		} catch (Exception e) {
			// TODO Auto-generated method stub
			log.error("Exception" , e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyList;
	}

	
	@Override
	public Map<String, Object> mapCoveredAreaDefaultDetails(LocalBodyForm localBodyForm) throws HibernateException {		
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.mapCoveredAreaDefaultDetails execution started.");
		Session session = null;
		Map<String, Object> mapAttributes = new HashedMap();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Fetch_Completed_Coverage_Details");
			query.setParameter("localBodyCode", localBodyForm.getLocalBodyCode(), Hibernate.INTEGER);
			List<CompletedCoverageDetails> completeCoverages = query.list();
			mapAttributes.put("completedCoverageDetails", completeCoverages);
			session.flush();		
		
			query = session.createSQLQuery("select level from local_body_type where local_body_type_code = :lbTypeCode and isactive");
			query.setParameter("lbTypeCode", localBodyForm.getLocalBodyTypeCode());
			mapAttributes.put("localBodyTypeLevel", query.uniqueResult());
			session.flush();
			
			if( ! LocalBodyConstant.LB_URBAN.toString().equalsIgnoreCase(localBodyForm.getLocalBodyCreationType())){
				query = session.createSQLQuery("select lrlc from lb_covered_landregion where lb_covered_region_code = :lbCoveredRegionCode and isactive=true and ismainregion=true");
				query.setParameter("lbCoveredRegionCode", localBodyForm.getLbCoveredRegionCode(), Hibernate.INTEGER);
				List<Integer> hqCodeList =  query.list();
				if(hqCodeList != null && !hqCodeList.isEmpty()) {
					mapAttributes.put("mainRegionCode", hqCodeList.get(0));
				}
				//mapAttributes.put("mainRegionCode", query.uniqueResult());
				session.flush();
			}
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.mapCoveredAreaDefaultDetails : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return mapAttributes;
	}
	
	@Override
	public String updateMappedCoveredArea(LocalBodyForm localBodyForm) throws Exception {		
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.updateMappedCoveredArea execution started.");
		Session session = null;
		String statusMsg = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select * from map_lb_coverage_fn(:localBodyCode, :newCoverageDtls, :changeCoverageType, :headquarterCode)");
			query.setParameter("localBodyCode", localBodyForm.getLocalBodyCode(), Hibernate.INTEGER);
			query.setParameter("newCoverageDtls", localBodyForm.getContributingLandRegionCodes(), Hibernate.STRING);
			query.setParameter("changeCoverageType", StringUtils.isNotBlank(localBodyForm.getChangeCoverageTypeLRList()) ? localBodyForm.getChangeCoverageTypeLRList() : null, Hibernate.STRING);
			query.setParameter("headquarterCode", StringUtils.isNotBlank(localBodyForm.getHeadQuarterCode()) ?  Integer.valueOf(localBodyForm.getHeadQuarterCode()) : null, Hibernate.INTEGER);
			statusMsg = (String) query.uniqueResult();
		} catch (HibernateException e) {
			// TODO Auto-generated method stub
			log.error("LocalBodyDaoImpl.updateMappedCoveredArea : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return statusMsg;
	}

	@Override
	public List<String> fetchMimeType() {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.fetchMimeType execution started.");
		Session session = null;
		List<String> mimeTypes = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select mimetype_name from mimetype_master");
			mimeTypes = query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated method stub
			log.error("LocalBodyDaoImpl.fetchMimeType : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return mimeTypes;
	}

	
	@Override
	public String getMappedLBsForGIS(Integer localBodyCode, String panchayatName, boolean isShowOnlyBoundary, boolean updateApprovedGP ) throws IOException {

		log.info("LocalBodyDaoImpl.getMappedLBsForGIS execution started.");
		Session session = null;
		InputStream inputStreamPro=null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select * from get_lb_cov_with_covtype_fn(:localBodyCode)").setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			String mappedLocalBodies = (String) query.uniqueResult();

			if (!LocalBodyConstant.FAILED_MESSAGE.toString().equals(mappedLocalBodies)) {
				inputStreamPro = getClass().getClassLoader().getResourceAsStream("/upload_info.properties");

				Properties properties = new Properties();
				properties.load(inputStreamPro);
				String gisServerLoc = properties.getProperty("gisMap.server.location");
				String enlocalBodyCode=EncryptionUtil.encode(localBodyCode.toString());
				String enmappedLocalBodies=EncryptionUtil.encode(mappedLocalBodies);
				String enpanchayatName=EncryptionUtil.encode(panchayatName);
				String enisShowOnlyBoundary=EncryptionUtil.encode("false");
				if(isShowOnlyBoundary){
					enisShowOnlyBoundary=EncryptionUtil.encode("true");
				}
				String enupdateApprovedGP=EncryptionUtil.encode("false");
				if(updateApprovedGP){
					enupdateApprovedGP=EncryptionUtil.encode("true");
				}
				mappedLocalBodies = gisServerLoc + "?gpcode=" + enlocalBodyCode + "&vils=" + enmappedLocalBodies + "&panchayatName=" + enpanchayatName + "&isShowOnlyBoundary=" + enisShowOnlyBoundary + "&updateApprovedGP=" + enupdateApprovedGP;
			}
			return mappedLocalBodies;
		} catch (HibernateException e) {

			String exc = e.toString().substring(0, 100);
			log.debug("Exception " +exc);

			return LocalBodyConstant.FAILED_MESSAGE.toString();
		} finally {
			if(inputStreamPro!=null){
			inputStreamPro.close();}
			closeConnection(session);
		}
	}

	@Override
	public String saveGISStatus(String statusFromGIS) {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.saveGISStatus execution started.");
		Session session = null;
		try {
			String[] gisResponseDetails = StringUtils.split(statusFromGIS, ":");
			GISMappedEntities gisEntity = new GISMappedEntities();
			gisEntity.setEntityCode(Integer.valueOf(gisResponseDetails[0]));
			gisEntity.setStatus(gisResponseDetails[1]);
			
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(gisEntity);
			tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated method stub
			log.error("LocalBodyDaoImpl.saveGISStatus : ", e);
			return LocalBodyConstant.FAILED_MESSAGE.toString();
		} finally {
			closeConnection(session);
		}
		return LocalBodyConstant.SUCCESS_MESSAGE.toString();
	}

	@Override
	public List<String> fetchPreviousNames(Integer localbodyCode) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.fetchPreviousNames execution started.");
		Session session = null;
		List<String> previousNames = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select distinct trim( local_body_name_english ) from localbody  where local_body_code = :localBodyCode");
			query.setParameter("localBodyCode", localbodyCode);
			query.setCacheable(Boolean.TRUE);
			query.setCacheMode(CacheMode.GET);
			previousNames = query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated method stub
			log.error("LocalBodyDaoImpl.fetchPreviousNames : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return previousNames;
	}
	
	@Override
	public List<LBTypeHierarchy> getLBTypeHierarchyStateWiseDetials(Integer stateCode) throws HibernateException {
		List<LBTypeHierarchy> lbTypeHierarchyList=null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Dynamic_Local_body_Hierarchy_ALL_TYPE")
						   .setParameter("stateCode", stateCode, Hibernate.INTEGER);
			lbTypeHierarchyList = query.list();
		} catch (HibernateException e) {
			LGDLogger.getLogger(ViewEntityDetailsDaoImpl.class).error(e.toString());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbTypeHierarchyList;
	}
	
	@Override
	public List<LBTypeDetailsWithCategory> buildLocalBodyHierarchyWithCategory(Integer setupCode, Integer setupVersion) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.buildLocalBodyHierarchy execution started.");
		Session session = null;
		List<LBTypeDetailsWithCategory> lbTypesDetails = new ArrayList<LBTypeDetailsWithCategory>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Dynamic_Local_body_Type_Details_with_Category");
			query.setParameter("setupCode", setupCode, Hibernate.INTEGER).setParameter("setupVersion", setupVersion, Hibernate.INTEGER);
			lbTypesDetails = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.buildLocalBodyHierarchy : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return lbTypesDetails;
	}

	@Override
	public Boolean validateDistrictContainSubdistrict(Integer districtCode) {
		Session session = null;
		boolean flag=true;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select case when count(1)>0 then true else false end from subdistrict  where dlc=:dlc and isactive");
			query.setParameter("dlc", districtCode, Hibernate.INTEGER);
			flag = (boolean)query.uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.buildLocalBodyHierarchy : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return flag;
	}
	
	/*
	  *  For Preview GIS Coverage#started @author Maneesh Kumar 30-12-2016
	 */
	@Override
	public String getMappedLBsForGISPreview(Integer localBodyCode,String panchayatName,String isShowOnlyBoundary,boolean updateApprovedGP,String deleteCode,String insertCode) throws IOException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getMappedLBsForGISPreview execution started.");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select * from get_lb_cov_with_covtype_fn(:localBodyCode)").setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			String mappedLocalBodies = (String) query.uniqueResult();

			/*query = session.createSQLQuery("select local_body_name_english from localbody  where    local_body_code  =:localBodyCode and isactive").setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			String panchayatName = (String) query.uniqueResult();*/
			List<String> villCode=new ArrayList<String>(); 
			if (mappedLocalBodies!=null && mappedLocalBodies.length()>0) {
				
				Scanner scanner = new Scanner(mappedLocalBodies);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					String codes=scanner.next();
					if(codes.indexOf("F")>-1){
						codes=codes.substring(0, (codes.indexOf("F")));
					}else{
						codes=codes.substring(0, (codes.indexOf("P")));
					}
					if(!(deleteCode.indexOf(codes)>-1)){
						villCode.add(codes+"E");
					}
					
				}
				scanner.close();
			}
			
			if (deleteCode!=null && deleteCode.length()>0) {
				
				Scanner scanner = new Scanner(deleteCode);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					villCode.add(scanner.next()+"D");
					
				}
				scanner.close();
			}
			
			if (insertCode!=null && insertCode.length()>0) {
				Scanner scanner = new Scanner(insertCode);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					villCode.add(scanner.next());
				}
				scanner.close();
			}
			
			if(! LocalBodyConstant.FAILED_MESSAGE.toString().equals(mappedLocalBodies) ){
				InputStream inputStreamPro = getClass().getClassLoader().getResourceAsStream("/upload_info.properties");
				String insertVillage="";
                if(villCode.size()>0){
                	insertVillage=villCode.toString();
                	insertVillage=insertVillage.substring(1, insertVillage.length()-1);
                }
                if(insertVillage.length()>0){
                    insertVillage=insertVillage.replaceAll("\\s+","");
                }
				Properties properties = new Properties();
				properties.load(inputStreamPro);
				String gisServerLoc=properties.getProperty("gisMap.server.location");
				
				
				String enlocalBodyCode=EncryptionUtil.encode(localBodyCode.toString());
				String eninsertVillage=EncryptionUtil.encode(insertVillage);
				String enpanchayatName=EncryptionUtil.encode(panchayatName);
				String enisShowOnlyBoundary=EncryptionUtil.encode(isShowOnlyBoundary);
				
				String enupdateApprovedGP=EncryptionUtil.encode("false");
				if(updateApprovedGP) {
					enupdateApprovedGP=EncryptionUtil.encode("true");
				}
				mappedLocalBodies = gisServerLoc+"?gpcode=" + enlocalBodyCode + "&vils=" +eninsertVillage+"&panchayatName="+enpanchayatName+"&isShowOnlyBoundary="+enisShowOnlyBoundary+"&updateApprovedGP="+enupdateApprovedGP;
			}
			return mappedLocalBodies;
		} catch (HibernateException e) {
			// TODO Auto-generated method stub
			log.error("LocalBodyDaoImpl.getMappedLBsForGIS : ", e);
			//throw e;
			return LocalBodyConstant.FAILED_MESSAGE.toString();
		} finally {
			closeConnection(session);
		}
	}
	/*
	  *  For Preview GIS Coverage#end
	 */
	
	public List<LBTypeDetails> buildLBTypeList(Integer stateCode) throws HibernateException {
	Session session = null;
	List<LBTypeDetails> lBTypeDetailsList=null;
	try{
	session = sessionFactory.openSession();
	Query query = session.getNamedQuery("Dynamic_Local_body_Hierarchy");
	query.setParameter("stateCode", stateCode).setParameter("localBodyType", LocalBodyConstant.LB_PANCHAYAT.toString(), Hibernate.STRING);
	List<LBTypeHierarchy> lbHierarchies = query.list();
	if(lbHierarchies!=null && !lbHierarchies.isEmpty()){
		LBTypeHierarchy lbTypeHierarchy=lbHierarchies.get(0);
		lBTypeDetailsList=this.buildLocalBodyHierarchy(lbTypeHierarchy.getLocalBodySetupCode(), lbTypeHierarchy.getLocalBodySetupVersion());
		
		
	}
	return lBTypeDetailsList;
	} catch (HibernateException e) {
		// TODO Auto-generated method stub
		log.error("LocalBodyDaoImpl.getMappedLBsForGIS : ", e);
		//throw e;
		return null;
	} finally {
		closeConnection(session);
	}
	}
	
	@Override
	public List<LocalBodyEntityDetails> getParentwiseLocalBodyRPT(Integer localBodyCode, Integer draftTempCode, Integer processId) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getParentwiseLocalBodyRPT execution started.");
		Session session = null;
		List<LocalBodyEntityDetails> parentwiseLocalBodyDetails = new ArrayList<LocalBodyEntityDetails>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Local_Body_List_By_Parent_RPT");
			query.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			query.setParameter("draftTempCode", draftTempCode, Hibernate.INTEGER);
			query.setParameter("processId", processId, Hibernate.INTEGER);
			parentwiseLocalBodyDetails = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getParentwiseLocalBody : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return parentwiseLocalBodyDetails;
	}
	
	@Override
	public List<LocalBodyEntityDetails> getDistrictPanchayatListRPT(Integer lbTypeCode, Integer stateCode, Integer draftTempCode, Integer processId) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getDistrictPanchayatList execution started.");
		System.out.println("23 Aug into District mehod");
		Session session = null;
		List<LocalBodyEntityDetails> localBodyDetails = new ArrayList<LocalBodyEntityDetails>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Local_Body_List_By_Type_State_RPT");
			query.setParameter("lbTypeCode", lbTypeCode, Hibernate.INTEGER);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("draftTempCode", draftTempCode, Hibernate.INTEGER);
			query.setParameter("processId", processId, Hibernate.INTEGER);
			localBodyDetails = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("Error in LocalBodyDaoImpl.getDistrictPanchayatList : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		System.out.println("23 Aug return value");
		return localBodyDetails;
	}

	@Override
	public String checkLocalbodyChangeParentInSameDistrict(Integer localBodyCode,Integer parentLblc)throws HibernateException{
		log.info("LocalBodyDaoImpl.checkLocalbodyChangeParentInSameDistrict execution started.");
		Session session = null;
		String retMessage = "";
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select * from check_local_body_change_parent_in_same_district(:localBodyCode, :parentLblc)");
			query.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			query.setParameter("parentLblc", parentLblc, Hibernate.INTEGER);
			retMessage = (String) query.uniqueResult();
		} catch (HibernateException e) {
			// TODO Auto-generated method stub
			log.error("LocalBodyDaoImpl.checkLocalbodyChangeParentInSameDistrict : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return retMessage;
	}
	
	@Override
	public List<UnmappedLandregions> fetchCoverageDetailsVillage(String unmappedCoverageLevel, String seltlcCode,List<Integer> existVillage, String localBodyType) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.fetchCoverageDetailsLandRegion execution started.");
		Session session = null;
		List<UnmappedLandregions> unmappedCovergaesLandRegion = new ArrayList<UnmappedLandregions>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Partially_Unmapped_List_At_TLC_Levels");
			query.setParameter("unmappedCoverageLevel", unmappedCoverageLevel, Hibernate.STRING);
			query.setParameter("seltlcCode", seltlcCode, Hibernate.STRING);
			query.setParameter("localBodyType", localBodyType, Hibernate.STRING);
			query.setParameterList("existVillage", existVillage);
			unmappedCovergaesLandRegion = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.fetchCoverageDetailsLandRegion : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return unmappedCovergaesLandRegion;
	}
	
	
	@Override
	public boolean lbPartCoverageExistState(Integer stateCode,Character landRegionType,Integer lrlc){
		Session session = null;
		boolean lbCovPartExist = false;
		try {
			session = sessionFactory.openSession();
			Query query =	session.createSQLQuery("select case when count(1)>1 then true else false end "
			+ " from localbody where isactive and slc=:stateCode and lb_covered_region_code "
			+ " in(select lb_covered_region_code from lb_covered_landregion  where  "
			+ " isactive  and land_region_type=:landRegionType and lrlc=:lrlc)");
			query.setParameter("stateCode",stateCode );
			query.setParameter("landRegionType",landRegionType );
			query.setParameter("lrlc", lrlc);
			lbCovPartExist = (boolean)query.uniqueResult();
		} 
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return lbCovPartExist;
	}
	
	@Override
	public List<CompletedCoverageDetails> fetchExistingCoverage(Integer localBodyCode) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.fetchCoverageDetailsLandRegion execution started.");
		Session session = null;
		List<CompletedCoverageDetails> completedCoverageDetailsList = new ArrayList<CompletedCoverageDetails>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Fetch_Completed_Coverage_Details");
			query.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			completedCoverageDetailsList = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.fetchCoverageDetailsLandRegion : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return completedCoverageDetailsList;
	}
	
	@Override
	public  List<LocalBodyDetailDto> getPreviousAttachedFilesbyLblc(Integer localBodyCode) throws HibernateException{
		 Session session = null;
/*		 List<LocalBodyForm> localBodyForm =null;
*/		 List<LocalBodyDetailDto> localBodyDetailDto =null;
              LocalBodyDetailDto localBodyDetailDto1 =null;
		 String sqlQuery=null;
		try {
			sqlQuery="select local_body_code ,file_title ,file_name ,file_size , file_content_type, file_location, file_timestamp  ,cast(go.order_no as character varying) , cast(go.order_date as date) ,cast(go.gaz_pub_date as date) from localbody l inner join government_order_entitywise  goe on goe.entity_code =l.local_body_code  inner join  government_order go  on go.order_code = goe.order_code left join   attachment a on a.announcement_id = go.order_code  where goe.entity_type= 'G' and entity_code =:localBodyCode and isactive ='true' and  local_body_version =entity_version";
			session = sessionFactory.openSession();
			Query query =	session.createSQLQuery(sqlQuery);
			query.setParameter("localBodyCode",localBodyCode );
			
			
			List<Object[]> localBodyFormObj =query.list();
			if(localBodyFormObj != null && localBodyFormObj.size() > 0)
			{
				localBodyDetailDto =new ArrayList<LocalBodyDetailDto>();
				for(Object[] obj:localBodyFormObj) {
					LocalBodyDetailDto beanOBJ=new LocalBodyDetailDto();
					if(obj[0] != null){
						beanOBJ.setLocalBodyCode((Integer)obj[0]);
					}
					if(obj[1] != null && !((String)obj[1]).toString().isEmpty()){
						beanOBJ.setFileTitle((String)obj[1]);
					}else{
						beanOBJ.setFileTitle("");
					}
					if(obj[2] != null && !((String)obj[2]).toString().isEmpty()){
						beanOBJ.setFileName((String)obj[2]);
					}else{
						beanOBJ.setFileName("");
					}
					if(obj[3] != null){
						beanOBJ.setFileSize(((BigInteger)obj[3]).intValue());
					}
					
					if(obj[4] != null && !((String)obj[4]).toString().isEmpty()){
						beanOBJ.setFileContentType((String)obj[4]);
					}else{
						beanOBJ.setFileContentType("");
					}
					if(obj[5] != null && !((String)obj[5]).toString().isEmpty()){
						beanOBJ.setFileLocation((String)obj[5]);
					}else{
						beanOBJ.setFileLocation("");
					}
					if(obj[6] != null && !((String)obj[6]).toString().isEmpty()){
						beanOBJ.setFileTimestamp((String)obj[6]);
					}else{
						beanOBJ.setFileTimestamp("");
					}
					if((String)obj[7] != null && !((String)obj[7]).toString().isEmpty()){
						beanOBJ.setOrderNo((String)obj[7]);
					}else{
						beanOBJ.setOrderNo("");
					}
					
					
					if(obj[8] != null && !((Date)obj[8]).toString().isEmpty()){
						beanOBJ.setOrderDate(((Date)obj[8]));
					}
					if(obj[9] != null && !((Date)obj[9]).toString().isEmpty()){
						beanOBJ.setGazPubDate(((Date)obj[9]));
					}
					localBodyDetailDto.add(beanOBJ);
				}
			
				
		}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.fetchCoverageDetailsLandRegion : ", e);
			throw e;
		}
		
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return localBodyDetailDto;
	}

	@Override
	public Boolean saveLocalBodyForm(LocalBodyForm localBodyForm) {
		Boolean status = Boolean.FALSE;
		Session session = null;
		try {
			
			String originalFilename=localBodyForm.getGazettePublicationUpload().get(0).getOriginalFilename();
			 String fileContentType =localBodyForm.getGazettePublicationUpload().get(0).getContentType();
			
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select * from correction_gov_order_of_localbody_fn(:lbcode, :orderno,:orderdate,:guzpubdate,:orderpath,"
					+ ":userid,:filename,:filesize,:filetype,:fileloc,:filetimestamp)");
			query.setParameter("lbcode", localBodyForm.getLocalBodyCode(), Hibernate.INTEGER);
			query.setParameter("orderno", localBodyForm.getOrderNo(), Hibernate.STRING);
			query.setParameter("orderdate", localBodyForm.getOrderDate(), Hibernate.DATE);
			query.setParameter("guzpubdate", localBodyForm.getGazPubDate(), Hibernate.DATE);
			query.setParameter("orderpath", localBodyForm.getOrderPath(), Hibernate.STRING);
			query.setParameter("userid", localBodyForm.getCreatedBy(), Hibernate.INTEGER);
			query.setParameter("filename", originalFilename, Hibernate.STRING);
			query.setParameter("filesize", localBodyForm.getOrderFileSize().intValue(), Hibernate.INTEGER);
			query.setParameter("filetype", fileContentType, Hibernate.STRING);
			query.setParameter("fileloc", localBodyForm.getFileLocation()+"\\"+localBodyForm.getNewFilename(), Hibernate.STRING);
			query.setParameter("filetimestamp", localBodyForm.getNewFilename(), Hibernate.STRING);
		
			 status = (Boolean) query.uniqueResult();
			
			
		
		} catch (Exception e) {
			throw e;
			
		} finally {
			closeConnection(session);
		}
		return status;
	}
	
	

	@Override
	public List<Object[]> getOrderCodeThroughLblc(Integer localBoadyCode) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getLBListOfSelectedLR execution started.");
		Session session = null;
		List<Object[]> governmentOrders=new ArrayList<Object[]>();
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select go.effective_date ,go.order_code ,a.attachment_id ,gowe.id from localbody l inner join government_order_entitywise gowe  on gowe.entity_code =l.local_body_code   inner join government_order go    on gowe.order_code = go.order_code left join attachment a on a.announcement_id = go.order_code  where  gowe.entity_code  =:localBoadyCode and gowe.entity_type= 'G' and  isactive ='true' and  local_body_version =entity_version");
			query.setParameter("localBoadyCode", localBoadyCode, Hibernate.INTEGER);
			governmentOrders = query.list();
		} catch (Exception e) {
			// TODO Auto-generated method stub
			log.error("Exception" , e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return governmentOrders;
	}
	
	@Override
	public List<Object[]> getLocalbodiesCoveredlrlc(Integer landRegionCode, String landRegionType) throws Exception {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getLBListOfSelectedLR execution started.");
		Session session = null;
		List<Object[]> localbodyList=new ArrayList<Object[]>();
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select local_body_code, local_body_name_english from localbody where lb_covered_region_code in(select lb_covered_region_code from lb_covered_landregion where lrlc=:landRegionCode and isactive and land_region_type=:landRegionType) and isactive ");
			query.setParameter("landRegionCode", landRegionCode, Hibernate.INTEGER);
			query.setParameter("landRegionType", landRegionType, Hibernate.STRING);			
			localbodyList = query.list();
		} catch (Exception e) {
			// TODO Auto-generated method stub
			log.error("Exception" , e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyList;
	}
	
	
	@Override
	public void saveLocalbodyCoverageType(Integer lbCode, String coverageList,Integer userId) throws Exception {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getLBListOfSelectedLR execution started.");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query =	session.createSQLQuery("select * from change_coverage_type_of_local_body_fn(:lbCode,:coverageList,:userId)");
			query.setParameter("lbCode",lbCode );
			query.setParameter("coverageList",coverageList );
			query.setParameter("userId",userId );
			query.uniqueResult();
			
		} catch (Exception e) {
			log.error("Localbody Coverage Change Exception" , e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
	}
	
	@Override
	public Response saveEffectiveDateEntityLB(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Integer userId) {
		Response response=new Response();
		Session session = null;
		String parameter1=null;
		Integer villageCode=null;
		try {
			session = sessionFactory.openSession();
			
			
			if(getEntityEffectiveDateList!=null && !getEntityEffectiveDateList.isEmpty()) {
				 
				villageCode=getEntityEffectiveDateList.get(0).getEntityCode();
				Character entityType=getEntityEffectiveDateList.get(0).getEntityType();
				Query query = session.getNamedQuery("GET_ENTITY_EFFECTIVE_DATE");
				query.setParameter("entityCode", villageCode);
				query.setParameter("entityType", "G");
				List<GetEntityEffectiveDate> effectiveDateListOld= query.list();
				
				if(villageService.validateNewEffectiveDate(villageCode, getEntityEffectiveDateList, effectiveDateListOld))	{
					 List<String> pArr=new ArrayList<String>();
						for(GetEntityEffectiveDate obj: getEntityEffectiveDateList) {
							if(obj.getEffectiveDate()!=obj.getNewEffectiveDate()) {
								pArr.add(obj.getEntityCode()+"#"+obj.getEntityVersion()+"#"+obj.getEntityMinorVersion()+"#"+obj.getNewEffectiveDate());
								
							}
						}
						parameter1=pArr.toString();
						if(parameter1.indexOf("[")>-1) {
							parameter1 = parameter1.replaceAll("[\\[\\](){}]","");
						}
						
						
						query = session.createSQLQuery("select * from change_localbody_effective_date_fn(:parameter1,:userId)");
						
						//query = session.createSQLQuery("select * from get_entity_effective_date_details(:parameter1,:userId)");
						query.setParameter("parameter1", parameter1.toString(), Hibernate.STRING);
						query.setParameter("userId", userId, Hibernate.INTEGER);
					    query.uniqueResult();
						session.flush();
						session.clear();
						response.setResponseCode(200);
				 }else {
					 response.setResponseCode(300);
					 response.setReponseObject("Effective Date not in Sequence");
				 }
			
			
		}else {
			response.setResponseCode(300);
		}
			
			
	
	}catch(Exception e){
		response.setResponseCode(400);
		response.setReponseObject(e);
	}finally {
		if(session!=null && session.isOpen()) {
			session.close();
		}
	}
		return response;
	}
	
	
	
	
}
