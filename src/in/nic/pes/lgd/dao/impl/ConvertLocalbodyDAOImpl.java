package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.ConvertRLBtoTLB;
import in.nic.pes.lgd.bean.ConvertRLBtoULB;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.dao.ConvertLocalbodyDAO;
import in.nic.pes.lgd.forms.ConvertRLBtoTLBForm;
import in.nic.pes.lgd.forms.ConvertRLBtoULBForm;
import in.nic.pes.lgd.forms.ConvertTLBtoRLBForm;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.forms.GovernmentOrderForm;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pes.attachment.util.AttachedFilesForm;

@Transactional
public class ConvertLocalbodyDAOImpl implements ConvertLocalbodyDAO{
	private static final Logger log = Logger.getLogger(ConvertLocalbodyDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public Integer saveConversionRLBtoULBforMerge(String lbUrbanEnglish,
			int userId, int urbanbodyTypecode, Date effectiveDate,
			char type,int stateCode, String lbFULLlist1, String lbPARTlist1, String coveredArealist, String orderNo, Date orderDate, String orderPath, Date gazPubDate, Session session,int lbCode)throws Exception{
	              //lbCode
			Query query=null;
			List<ConvertRLBtoULB> comboList = new ArrayList<ConvertRLBtoULB>();
			Integer retValue=null;
			try {
				query = session.getNamedQuery("ConvertRLBtoULBforMerge")		
				.setParameter("dest_localbodyCode", lbUrbanEnglish,Hibernate.STRING)
				.setParameter("user_id", userId,Hibernate.INTEGER)
				.setParameter("localbodyTypeCode", urbanbodyTypecode,Hibernate.INTEGER)		
				.setParameter("orderEffectiveDate", effectiveDate,Hibernate.DATE)			
				.setParameter("type", type,Hibernate.CHARACTER)
				.setParameter("stateCode", stateCode,Hibernate.INTEGER)
				.setParameter("order_no", orderNo,Hibernate.STRING)
				.setParameter("order_date", orderDate,Hibernate.DATE)
				.setParameter("gaz_pub_date", gazPubDate,Hibernate.DATE)
				.setParameter("order_path", orderPath,Hibernate.STRING)
				.setParameter("source_full_localbodyCode", lbFULLlist1,Hibernate.STRING)
				.setParameter("source_part_localbodyCode", lbPARTlist1,Hibernate.STRING)
				.setParameter("source_part_coveredAreas", coveredArealist,Hibernate.STRING)
				.setParameter("localbodyEnglishName", null,Hibernate.STRING)
				.setParameter("localbodyEnglishName_local", null,Hibernate.STRING)
				.setParameter("alias_english", null,Hibernate.STRING)
				.setParameter("alias_local", null,Hibernate.STRING)
				.setParameter("lbCode", lbCode,Hibernate.INTEGER);
				
								
				
			
				
				comboList = query.list();
				retValue=comboList.get(0).getConvert_rlb_ulb_fn();
				 return retValue;
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
			return retValue;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer saveConversionRLBtoULBforNew(String lbUrbanEnglish, int userId,
			int urbanbodyTypecode, Date effectiveDate,
			char type,int stateCode, String lbFULLlist1, String lbPARTlist1,
			String coveredArealist, String localBodyNameInEn,
			String localBodyNameInLcl, String localBodyliasInEn,
			String localBodyliasInLcl, String orderNo, Date orderDate, String orderPath, Date gazPubDate, Session session,int lbCode)throws Exception{
		
		Query query=null;
		List<ConvertRLBtoULB> comboList = new ArrayList<ConvertRLBtoULB>();
		Integer retValue=null;
		
		try {
			query = session.getNamedQuery("ConvertRLBtoULBforNew")				
						.setParameter("dest_localbodyCode", lbUrbanEnglish,Hibernate.STRING)
						.setParameter("user_id", userId,Hibernate.INTEGER)
						.setParameter("localbodyTypeCode", urbanbodyTypecode,Hibernate.INTEGER)					
						.setParameter("orderEffectiveDate", effectiveDate,Hibernate.DATE)					
						.setParameter("type", type,Hibernate.CHARACTER)
						.setParameter("stateCode", stateCode,Hibernate.INTEGER)
						.setParameter("order_no", orderNo,Hibernate.STRING)
				        .setParameter("order_date", orderDate,Hibernate.DATE)
				        .setParameter("gaz_pub_date", gazPubDate,Hibernate.DATE)
				        .setParameter("order_path", orderPath,Hibernate.STRING)
						.setParameter("source_full_localbodyCode", lbFULLlist1,Hibernate.STRING)
						.setParameter("source_part_localbodyCode", lbPARTlist1,Hibernate.STRING)
						.setParameter("source_part_coveredAreas", coveredArealist,Hibernate.STRING)
						.setParameter("localbodyEnglishName", localBodyNameInEn,Hibernate.STRING)
						.setParameter("localbodyEnglishName_local", localBodyNameInLcl,Hibernate.STRING)
						.setParameter("alias_english", localBodyliasInEn,Hibernate.STRING)
						.setParameter("alias_local", localBodyliasInLcl,Hibernate.STRING)
			            .setParameter("lbCode", lbCode,Hibernate.INTEGER);
			
			
			comboList = query.list();
			
			retValue=comboList.get(0).getConvert_rlb_ulb_fn();
			 return retValue;
		} catch (Exception e) {
			log.debug("Exception" + e);
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	/**
	 * Method for upload document eg. government order
	 */
	@Override
	public boolean saveDataInAttach(ConvertRLBtoULBForm convertform, List<AttachedFilesForm> attachedList, Session session) {
		//Transaction tx1 = null;
		//Session sessionObj = sessionFactory.openSession();
		//tx1 = session.beginTransaction();
		Query query = null;
		Attachment attachment = null;
		GovernmentOrderEntityWise goe = null;
		boolean flag = false;
		try {

			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					Integer entityCode = Integer.parseInt(convertform.getUrbanlocalBodyNameEnglish());
					query = session.createQuery("from GovernmentOrderEntityWise where entityCode=:Code and entityVersion=(select MAX(a.entityVersion) from GovernmentOrderEntityWise a where a.entityCode=:entityCode and entityType=:entityType) and entityType=:type")
							.setParameter("Code", entityCode, Hibernate.INTEGER)
							.setParameter("entityCode", entityCode, Hibernate.INTEGER)
							.setParameter("entityType", 'G', Hibernate.CHARACTER)
							.setParameter("type", 'G', Hibernate.CHARACTER);
			
					@SuppressWarnings("rawtypes")
					List list = query.list();
					if (list != null && list.size() > 0) {
						goe = (GovernmentOrderEntityWise) query.list().get(0);
						if (goe != null) {
							GovernmentOrder govorder = new GovernmentOrder();
							govorder.setOrderCode(goe.getOrderCode());
							attachment.setGovernmentOrder(govorder);
						}
					}
					session.save(attachment);
					//tx1.commit();
					flag = true;
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			//tx1.rollback();
			flag = false;
		}
		return flag;
	}
	
	@Override
	public boolean saveDataInAttachRLBtoTLB(GovernmentOrderForm governmentOrderForm,ConvertRLBtoTLBForm convertform,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) 
	{
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		//Query query=null;
		Attachment attachment = null;
		//GovernmentOrderEntityWise goe=null;
	    boolean flag = true;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();				
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					
					GovernmentOrder govorder= new GovernmentOrder();
					if(getordercode!=0)
					{
						govorder.setOrderCode(getordercode);
						attachment.setGovernmentOrder(govorder);
					}
					sessionObj.save(attachment);					
					tx1.commit();
				}
			}
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
			tx1.rollback();
			flag =false;
		}
		finally {
			
			if (sessionObj != null && sessionObj.isOpen()){
				sessionObj.clear();
				sessionObj.close();
			}	
		}
		return flag;
	}
	
	@Override
	public boolean saveDataInAttachTLBtoRLB(GovernmentOrderForm governmentOrderForm,ConvertTLBtoRLBForm convertform,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) 
	{
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		//Query query=null;
		Attachment attachment = null;
		//GovernmentOrderEntityWise goe=null;
		//ShiftVillageSD orderno=null;
	    boolean flag = true;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();				
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					
					GovernmentOrder govorder= new GovernmentOrder();
					if(getordercode!=0)
					{
						govorder.setOrderCode(getordercode);
						attachment.setGovernmentOrder(govorder);
					}
					sessionObj.save(attachment);					
					tx1.commit();
				}
			}
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
			tx1.rollback();
			flag =false;
		}
		finally {
			
			if (sessionObj != null && sessionObj.isOpen()){
				sessionObj.clear();
				sessionObj.close();
			}	
		}
		return flag;
	}

	
	
	
	public boolean saveDataInAttachforNewUlb(ConvertRLBtoULBForm convertform,List<AttachedFilesForm> attachedList, Session session) {
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		@SuppressWarnings("unused")
		Query query=null;
		Attachment attachment = null;
		GovernmentOrderEntityWise goe=null;
	    boolean flag = true;
		try {
			
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();				
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
				Integer entityCode =Integer.parseInt(convertform.getUrbanLgTypeName());	
					query = sessionObj.createQuery(
							"from GovernmentOrderEntityWise where entityCode=:Code and entityVersion=:ver and entityType=:type")
							.setParameter("Code",entityCode, Hibernate.INTEGER)
							.setParameter("ver",2,Hibernate.INTEGER)
							.setParameter("type",'G',Hibernate.CHARACTER);					    
					//goe = (GovernmentOrderEntityWise) query.list().get(0);
					GovernmentOrder govorder= new GovernmentOrder();
					if(goe!=null)
					{
						govorder.setOrderCode(goe.getOrderCode());
						attachment.setGovernmentOrder(govorder);
					}
				
					sessionObj.save(attachment);					
					tx1.commit();
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			tx1.rollback();
			flag =false;
		}
		finally {
			
			if (sessionObj != null && sessionObj.isOpen()){
				sessionObj.clear();
				sessionObj.close();
			}	
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public synchronized List<ConvertRLBtoTLB> saveConversionRLBtoTLBforMerge(String sourcerlbType,String lbcodeformerger,
			int userId, int urbanbodyTypecode, Date effectiveDate,
			char type,int stateCode, String lbFULLlist1,String lbPARTlist1, String lbFULLlist2, String coveredArealist, String orderNo, Date orderDate, String orderPath, Date gazPubDate,String destLBTypeCode, Session session)throws Exception{
	
			Query query=null;
			List<ConvertRLBtoTLB> comboList = null;
			//List<ConvertRLBtoULB> comboList = new ArrayList<ConvertRLBtoULB>();
			try {
				query = session.getNamedQuery("ConvertRLBtoTLBforMerge")		
				.setParameter("source_rlb_type",sourcerlbType,Hibernate.CHARACTER)
				.setParameter("p_user_id",userId,Hibernate.INTEGER)
				.setParameter("p_slc",stateCode,Hibernate.INTEGER)
				.setParameter("p_order_no",orderNo,Hibernate.STRING)		
				.setParameter("p_order_date",orderDate,Hibernate.DATE)	
				.setParameter("p_effective_date",effectiveDate,Hibernate.DATE)	
				.setParameter("type_of_operation",type,Hibernate.CHARACTER)
				.setParameter("dest_local_body_type_code",Integer.parseInt(destLBTypeCode),Hibernate.INTEGER)
				.setParameter("p_operation_code",29,Hibernate.INTEGER)
				.setParameter("p_flag_code",26,Hibernate.INTEGER)
				.setParameter("source_full_fisrt_tier_code_list", lbFULLlist1,Hibernate.STRING)
				.setParameter("source_part_fisrt_tier_code_list", lbPARTlist1,Hibernate.STRING)
				.setParameter("source_full_second_tier_code_list", lbFULLlist2,Hibernate.STRING)
				.setParameter("p_local_body_name_english", null,Hibernate.STRING)
				.setParameter("p_local_body_name_local", null,Hibernate.STRING)
				.setParameter("p_alias_english", null,Hibernate.STRING)
				.setParameter("p_alias_local", null,Hibernate.STRING)
				.setParameter("local_body_code_for_merger",Integer.parseInt(lbcodeformerger),Hibernate.INTEGER)
				.setParameter("parent_lblc_code",null,Hibernate.INTEGER)
				.setParameter("child_localbody_type_code", null,Hibernate.INTEGER)
				.setParameter("p_gaz_pub_date", gazPubDate,Hibernate.DATE)	
				.setParameter("p_order_path", orderPath,Hibernate.STRING);
				
				
				
				comboList = query.list();
				 //return true;
			} 
			catch (Exception e) 
			{
				log.debug("Exception" + e);
			}
			return comboList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public synchronized List<ConvertRLBtoTLB> saveConversionRLBtoTLBforNew(String sourcerlbType,String lbcodeformerger, int userId,
			int urbanbodyTypecode, Date effectiveDate,
			char type,int stateCode, String lbFULLlist1,String lbPARTlist1, String lbFULLlist2,
			String coveredArealist, String localBodyNameInEn,
			String localBodyNameInLcl, String localBodyliasInEn,
			String localBodyliasInLcl, String orderNo, Date orderDate, String orderPath, Date gazPubDate, String destLBTypeCode,String parentLBCode,Session session)throws Exception
	{
		
		Query query=null;
		List<ConvertRLBtoTLB> comboList = null;
		
		try 
		{
				query = session.getNamedQuery("ConvertRLBtoTLBforNew")				
						.setParameter("source_rlb_type", sourcerlbType,Hibernate.CHARACTER)
						.setParameter("p_user_id", userId,Hibernate.INTEGER)
						.setParameter("p_slc", stateCode,Hibernate.INTEGER)
						.setParameter("p_order_no", orderNo,Hibernate.STRING)		
						.setParameter("p_order_date", orderDate,Hibernate.DATE)	
						.setParameter("p_effective_date", effectiveDate,Hibernate.DATE)	
						.setParameter("type_of_operation", type,Hibernate.CHARACTER)
						.setParameter("dest_local_body_type_code",Integer.parseInt(destLBTypeCode),Hibernate.INTEGER)
						.setParameter("p_operation_code",29,Hibernate.INTEGER)
						.setParameter("p_flag_code",45,Hibernate.INTEGER)
						.setParameter("source_full_fisrt_tier_code_list", lbFULLlist1,Hibernate.STRING)
						.setParameter("source_part_fisrt_tier_code_list", lbPARTlist1,Hibernate.STRING)
						.setParameter("source_full_second_tier_code_list", lbFULLlist2,Hibernate.STRING)
						.setParameter("p_local_body_name_english", localBodyNameInEn,Hibernate.STRING)
						.setParameter("p_local_body_name_local", localBodyNameInLcl,Hibernate.STRING)
						.setParameter("p_alias_english", localBodyliasInEn,Hibernate.STRING)
						.setParameter("p_alias_local", localBodyliasInLcl,Hibernate.STRING)
						.setParameter("local_body_code_for_merger",Integer.parseInt(lbcodeformerger),Hibernate.INTEGER)
						.setParameter("parent_lblc_code",Integer.parseInt(parentLBCode),Hibernate.INTEGER)
						.setParameter("child_localbody_type_code", null,Hibernate.INTEGER)
						.setParameter("p_gaz_pub_date", gazPubDate,Hibernate.DATE)	
						.setParameter("p_order_path", orderPath,Hibernate.STRING);
			
			
			comboList = query.list();
			// return true;
		} 
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		//return false;
		return comboList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public synchronized List<ConvertRLBtoTLB> saveConversionTLBtoRLBforMerge(String sourcerlbType,String lbcodeformerger,
			int userId, int urbanbodyTypecode, Date effectiveDate,
			char type,int stateCode, String lbFULLlist1,String lbPARTlist1, String lbFULLlist2, String coveredArealist, String orderNo, Date orderDate, String orderPath, Date gazPubDate,String destLBTypeCode, Session session)throws Exception{
	
			Query query=null;
			List<ConvertRLBtoTLB> comboList = null;
			//List<ConvertRLBtoULB> comboList = new ArrayList<ConvertRLBtoULB>();
			try {
				query = session.getNamedQuery("ConvertRLBtoTLBforMerge")		
				.setParameter("source_rlb_type",sourcerlbType,Hibernate.CHARACTER)
				.setParameter("p_user_id",userId,Hibernate.INTEGER)
				.setParameter("p_slc",stateCode,Hibernate.INTEGER)
				.setParameter("p_order_no",orderNo,Hibernate.STRING)		
				.setParameter("p_order_date",orderDate,Hibernate.DATE)	
				.setParameter("p_effective_date",effectiveDate,Hibernate.DATE)	
				.setParameter("type_of_operation",type,Hibernate.CHARACTER)
				.setParameter("dest_local_body_type_code",Integer.parseInt(destLBTypeCode),Hibernate.INTEGER)
				.setParameter("p_operation_code",64,Hibernate.INTEGER)
				.setParameter("p_flag_code",46,Hibernate.INTEGER)
				.setParameter("source_full_fisrt_tier_code_list", lbFULLlist1,Hibernate.STRING)
				.setParameter("source_part_fisrt_tier_code_list", lbPARTlist1,Hibernate.STRING)
				.setParameter("source_full_second_tier_code_list", lbFULLlist2,Hibernate.STRING)
				.setParameter("p_local_body_name_english", null,Hibernate.STRING)
				.setParameter("p_local_body_name_local", null,Hibernate.STRING)
				.setParameter("p_alias_english", null,Hibernate.STRING)
				.setParameter("p_alias_local", null,Hibernate.STRING)
				.setParameter("local_body_code_for_merger",Integer.parseInt(lbcodeformerger),Hibernate.INTEGER)
				.setParameter("parent_lblc_code",null,Hibernate.INTEGER)
				.setParameter("child_localbody_type_code", null,Hibernate.INTEGER)
				.setParameter("p_gaz_pub_date", gazPubDate,Hibernate.DATE)	
				.setParameter("p_order_path", orderPath,Hibernate.STRING);
				
				
				comboList = query.list();
				 //return true;
			} 
			catch (Exception e) 
			{
				log.debug("Exception" + e);
			}
			return comboList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public synchronized List<ConvertRLBtoTLB> saveConversionTLBtoRLBforNew(String sourcerlbType,String lbcodeformerger, int userId,
			int urbanbodyTypecode, Date effectiveDate,
			char type,int stateCode, String lbFULLlist1,String lbPARTlist1, String lbFULLlist2,
			String coveredArealist, String localBodyNameInEn,
			String localBodyNameInLcl, String localBodyliasInEn,
			String localBodyliasInLcl, String orderNo, Date orderDate, String orderPath, Date gazPubDate, String destLBTypeCode,String parentLBCode,Session session)throws Exception
	{
		
		Query query=null;
		List<ConvertRLBtoTLB> comboList = null;
		
		try 
		{
				query = session.getNamedQuery("ConvertRLBtoTLBforNew")				
						.setParameter("source_rlb_type", sourcerlbType,Hibernate.CHARACTER)
						.setParameter("p_user_id", userId,Hibernate.INTEGER)
						.setParameter("p_slc", stateCode,Hibernate.INTEGER)
						.setParameter("p_order_no", orderNo,Hibernate.STRING)		
						.setParameter("p_order_date", orderDate,Hibernate.DATE)	
						.setParameter("p_effective_date", effectiveDate,Hibernate.DATE)	
						.setParameter("type_of_operation", type,Hibernate.CHARACTER)
						.setParameter("dest_local_body_type_code",Integer.parseInt(destLBTypeCode),Hibernate.INTEGER)
						.setParameter("p_operation_code",64,Hibernate.INTEGER)
						.setParameter("p_flag_code",47,Hibernate.INTEGER)
						.setParameter("source_full_fisrt_tier_code_list", lbFULLlist1,Hibernate.STRING)
						.setParameter("source_part_fisrt_tier_code_list", lbPARTlist1,Hibernate.STRING)
						.setParameter("source_full_second_tier_code_list", lbFULLlist2,Hibernate.STRING)
						.setParameter("p_local_body_name_english", localBodyNameInEn,Hibernate.STRING)
						.setParameter("p_local_body_name_local", localBodyNameInLcl,Hibernate.STRING)
						.setParameter("p_alias_english", localBodyliasInEn,Hibernate.STRING)
						.setParameter("p_alias_local", localBodyliasInLcl,Hibernate.STRING)
						.setParameter("local_body_code_for_merger",Integer.parseInt(lbcodeformerger),Hibernate.INTEGER)
						.setParameter("parent_lblc_code",Integer.parseInt(parentLBCode),Hibernate.INTEGER)
						.setParameter("child_localbody_type_code", null,Hibernate.INTEGER)
						.setParameter("p_gaz_pub_date", gazPubDate,Hibernate.DATE)	
						.setParameter("p_order_path", orderPath,Hibernate.STRING);
			
			
			comboList = query.list();
			// return true;
		} 
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		//return false;
		return comboList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyforStateWise> getLocalBodyListStateWise(char localBodyType, int stateCode) throws Exception
	{
		Session session = null;
		List<LocalbodyforStateWise> localbodyList = null;
		Query query = null;
		try 
		{
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodylistStateWiseForTraditionalV")
					.setParameter("stateCode",stateCode, Hibernate.INTEGER)
					.setParameter("localBodyType", localBodyType,Hibernate.CHARACTER);
			localbodyList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {

			if (session != null && session.isOpen()){
				session.close();
			}	
		}

		return localbodyList;

	}
	
	@Override
	public boolean saveConversionRLBtoTLBforTemplate(GovernmentOrderForm governmentOrderForm,ConvertRLBtoTLBForm convertform, HttpSession session,int getordercode) 
	{
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		//Query query=null;
		boolean flag = true;
		Attachment attachment = null;
		//GovernmentOrderEntityWise goe=null;
		//ShiftVillageSD orderno=null;
		GenerateDetails genDetails=(GenerateDetails)session.getAttribute("validGovermentGenerateUpload");
		try
		{
			if(genDetails !=null)
			{
				attachment = new Attachment();	
				attachment.setFileName(genDetails.getFileName());
				attachment.setFileLocation(genDetails.getFileLocation());
				attachment.setFileTitle("");
				attachment.setFileTimestamp(genDetails.getFileTimestamp());
				attachment.setFileContentType(genDetails.getFileContentType());
				attachment.setFileSize(genDetails.getFileSize());
				//attachment.setFileSize();
				GovernmentOrder govorder= new GovernmentOrder();
				if(getordercode!=0)
				{
					govorder.setOrderCode(getordercode);
					attachment.setGovernmentOrder(govorder);
				}
				sessionObj.save(attachment);					
				tx1.commit();
			
			}
		}	
		
		catch (Exception e) 
		{
			log.debug("Exception" + e);
			tx1.rollback();
			flag =false;
		}
		finally 
		{
			if (sessionObj != null && sessionObj.isOpen()){
				sessionObj.clear();
				sessionObj.close();
			}	
		}
		return flag;
	}
	@Override
	public boolean saveConversionTLBtoRLBforTemplate(GovernmentOrderForm governmentOrderForm,ConvertTLBtoRLBForm convertform, HttpSession session,int getordercode) 
	{
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		//Query query=null;
		boolean flag = true;
		Attachment attachment = null;
		//GovernmentOrderEntityWise goe=null;
		//ShiftVillageSD orderno=null;
		GenerateDetails genDetails=(GenerateDetails)session.getAttribute("validGovermentGenerateUpload");
		try
		{
			if(genDetails !=null)
			{
				attachment = new Attachment();	
				attachment.setFileName(genDetails.getFileName());
				attachment.setFileLocation(genDetails.getFileLocation());
				attachment.setFileTitle("");
				attachment.setFileTimestamp(genDetails.getFileTimestamp());
				attachment.setFileContentType(genDetails.getFileContentType());
				attachment.setFileSize(genDetails.getFileSize());
				//attachment.setFileSize();
				GovernmentOrder govorder= new GovernmentOrder();
				if(getordercode!=0)
				{
					govorder.setOrderCode(getordercode);
					attachment.setGovernmentOrder(govorder);
				}
				sessionObj.save(attachment);					
				tx1.commit();
			
			}
		}	
		
		catch (Exception e) 
		{
			log.debug("Exception" + e);
			tx1.rollback();
			flag =false;
		}
		finally 
		{
			if (sessionObj != null && sessionObj.isOpen()){
				sessionObj.clear();
				sessionObj.close();
			}	
		}
		return flag;
	}
	
}
