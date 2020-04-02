package in.nic.pes.lgd.draft.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.stereotype.Repository;

import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.draft.constant.DraftConstant;
import in.nic.pes.lgd.draft.constant.DraftUtils;
import in.nic.pes.lgd.draft.dao.DraftDistrictDao;
import in.nic.pes.lgd.draft.dao.DraftSubdistrictDao;
import in.nic.pes.lgd.draft.dao.DraftUtilDao;
import in.nic.pes.lgd.draft.dao.DraftVillageDao;
import in.nic.pes.lgd.draft.entities.ChangeSubdistrictFn;
import in.nic.pes.lgd.draft.entities.DraftMaster;
import in.nic.pes.lgd.draft.entities.DraftMasterPK;
import in.nic.pes.lgd.draft.entities.GovermentDetailDraft;
import in.nic.pes.lgd.draft.entities.InsertSubdistrictFn;
import in.nic.pes.lgd.draft.entities.SubdistrictDraft;
import in.nic.pes.lgd.draft.form.DraftGovermentOrderForm;
import in.nic.pes.lgd.draft.form.DraftManageSubdistrictForm;
import in.nic.pes.lgd.draft.form.DraftSubdistrictForm;

@Repository
public class DraftSubdistrictDaoImpl implements DraftSubdistrictDao {

	@Autowired 
	SessionFactory sessionFactory;
	
	@Autowired
	DraftUtils draftUtils;
	
	@Autowired
	DraftVillageDao draftVillageDao;
	
	@Autowired
	DraftUtilDao draftUtilDao;
	
	@Autowired
	DraftDistrictDao draftDistrictDao;
	

	
	
	@Override
	public List<Subdistrict> getDraftSubdistrictList(Integer districtCode,List<Integer> subdistrictListPart,List<Integer> subdistrictListFull) throws Exception {
		List<Subdistrict> draftSubdistrictList=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			StringBuilder sb=new StringBuilder("select t.subdistrict_code as subdistrictCode,t.subdistrict_name_english as subdistrictNameEnglish, ");
			sb.append(" case when t.subdistrict_code  in (select * from get_draft_used_lb_lr_temp(t.subdistrict_code,'T')) then ");
			sb.append(" cast ('F' as character) else cast('A' as character) end as operation_state,");
			sb.append(" case when t.subdistrict_code in(:subdistrictListPart) then cast ('T' as character) else ");
			sb.append(" cast('F' as character) end as isPart,t.subdistrict_name_local as subdistrictNameLocal  from subdistrict t ");
			sb.append(" where t.dlc=:districtCode and t.isactive and t.tlc not in(:subdistrictListFull) order by t.subdistrict_name_english ");
			
			
			SQLQuery query=session.createSQLQuery(sb.toString());
			query.setParameter("districtCode", districtCode);
			query.setParameterList("subdistrictListPart", subdistrictListPart);
			query.setParameterList("subdistrictListFull", subdistrictListFull);
			query.addScalar("subdistrictCode").addScalar("subdistrictNameEnglish").addScalar("subdistrictNameLocal");
			query.addScalar("operation_state").addScalar("isPart");
			query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class));
			
			draftSubdistrictList=query.list();
			
			
		}catch(Exception e){
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return draftSubdistrictList;
	}

	@Override
	public Character subdistrictNameIsUnique(String subdistrictNameEng,Integer districtCode) throws Exception {
		Character uniqueFlag='F';
		Session session=null;
		try{
			session=sessionFactory.openSession();
			StringBuilder sb=new StringBuilder("select case when count(1)>0 then  cast('D' as character) else (select case when count(1)>0 then cast('P' as character) else cast('F' as character) ");
						  sb.append("end from subdistrict  where dlc=:districtCode and isactive and trim(subdistrict_name_english) ilike trim(:subdistrictNameEng))  end as is_unique from  subdistrict_draft where district_code=:districtCode ");
					      sb.append("and trim(subdistrict_name_english) ilike trim(:subdistrictNameEng) ");
			
			Query query=session.createSQLQuery(sb.toString());
			query.setParameter("subdistrictNameEng", subdistrictNameEng);
			query.setParameter("districtCode", districtCode);
		uniqueFlag=(Character)query.uniqueResult();
		}catch(Exception e){
			throw e;
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return uniqueFlag;
	}

	@Override
	public Integer saveSubdistrictinDraft(LinkedList<DraftSubdistrictForm> storedSubdistrictForms,DraftGovermentOrderForm draftGovermentOrderForm,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Session session=null;
		Transaction transaction = null;
		Integer groupId=null;
		try{
			session=sessionFactory.openSession();
			
			transaction=session.beginTransaction();
			
			
			//draftGovermentOrderForm=draftUtils.uploadFileToServer(draftGovermentOrderForm,Long.parseLong( DraftConstant.ATTACHMENT_MASTER_GO.toString()),request,httpSession);
			
			GovermentDetailDraft govermentDetailDraft=new GovermentDetailDraft();
			draftUtils.copyBeanAttributes(govermentDetailDraft, draftGovermentOrderForm);
			session.save(govermentDetailDraft);
			session.flush();
			
			groupId=govermentDetailDraft.getGroupId();
			
			SubdistrictDraft subdistrictDraft=null;
			for(DraftSubdistrictForm draftSubdistrictForm:storedSubdistrictForms){
				subdistrictDraft=new SubdistrictDraft();
				draftUtils.copyBeanAttributes(subdistrictDraft, draftSubdistrictForm); 
				subdistrictDraft.setDraftCode(null);
				subdistrictDraft.setGroupId(govermentDetailDraft.getGroupId());
				session.save(subdistrictDraft);
				session.flush();
				saveCoverageSubdistricInDraftMaster(subdistrictDraft,session);
			}
			
			transaction.commit();
			
		}catch(Exception e){
			transaction.rollback();
			throw e;
			
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return groupId;
	}
	
   private void saveCoverageSubdistricInDraftMaster(SubdistrictDraft subdistrictDraft,Session session)throws Exception{
	   if(subdistrictDraft.getListofSubdistrictPart()!=null){
		   Scanner scanner = new Scanner(subdistrictDraft.getListofSubdistrictPart());
			scanner.useDelimiter("@");
			DraftMaster draftMaster=null;
			DraftMasterPK draftMasterPK=null;
			while (scanner.hasNext()) {
				draftMasterPK=new DraftMasterPK();
				draftMasterPK.setLbLRType(DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString());
				draftMasterPK.setLbLRCode(Integer.parseInt(scanner.next()));
				draftMasterPK.setDraftTempId(subdistrictDraft.getDraftCode());
				draftMaster=new DraftMaster();
				draftMaster.setProcessId(Integer.parseInt(DraftConstant.DRAFT_OPERATION_CREATE_SUBDISTRICT.toString()));
				draftMaster.setDraftMasterPK(draftMasterPK);
				session.save(draftMaster);
				session.flush();
				
			}
	   }
			
			if(subdistrictDraft.getListofSubdistrictFull()!=null){
					Scanner  scanner = new Scanner(subdistrictDraft.getListofSubdistrictFull());
					scanner.useDelimiter("@");
					DraftMaster draftMaster=null;
					DraftMasterPK draftMasterPK=null;
					while (scanner.hasNext()) {
						draftMasterPK=new DraftMasterPK();
						draftMasterPK.setLbLRType(DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString());
						draftMasterPK.setLbLRCode(Integer.parseInt(scanner.next()));
						draftMasterPK.setDraftTempId(subdistrictDraft.getDraftCode());
						draftMaster=new DraftMaster();
						draftMaster.setProcessId(Integer.parseInt(DraftConstant.DRAFT_OPERATION_CREATE_SUBDISTRICT.toString()));
						draftMaster.setDraftMasterPK(draftMasterPK);
						session.save(draftMaster);
						session.flush();
						
					}
			
			
	   }
			if(subdistrictDraft.getListofVillageFull()!=null){
				Scanner  scanner = new Scanner(subdistrictDraft.getListofVillageFull());
				scanner.useDelimiter(",");
				DraftMaster draftMaster=null;
				DraftMasterPK draftMasterPK=null;
				while (scanner.hasNext()) {
					draftMasterPK=new DraftMasterPK();
					draftMasterPK.setLbLRType(DraftConstant.ENTITY_TYPE_VILLAGE.toString());
					draftMasterPK.setLbLRCode(Integer.parseInt(scanner.next()));
					draftMasterPK.setDraftTempId(subdistrictDraft.getDraftCode());
					draftMaster=new DraftMaster();
					draftMaster.setProcessId(Integer.parseInt(DraftConstant.DRAFT_OPERATION_CREATE_SUBDISTRICT.toString()));
					draftMaster.setDraftMasterPK(draftMasterPK);
					session.save(draftMaster);
					session.flush();
					
				}
		
		
   }
   }

   @Override
	public List<SubdistrictDraft> getSubdistrictDraftList(String entityType, Integer entityCode) throws Exception {
	   List<SubdistrictDraft> subdistrictDraftList =null;
		Session session=null;
		try{
			
			session=sessionFactory.openSession();
			
			StringBuilder queryBuild = new StringBuilder(" select group_id as groupId,string_agg(subdistrict_name_english, ', ') AS subdistrictNameEnglish,string_agg(change_subdistrict_name_english, ', ') AS changeSubdistrictNameEnglish, ");
			queryBuild.append("opeartion_flag as opeartionFlag,case when count(1)>1 then true else false end as multipleFlag , string_agg(cast(draft_code as varchar), ', ') AS draftCodeText ");
			queryBuild.append(" from  subdistrict_draft where ");
			if(DraftConstant.ENTITY_TYPE_STATE.toString().equals(entityType)){
				queryBuild.append(" state_code=:entityCode ");  
			}else{
				queryBuild.append(" district_code=:entityCode ");  
			}
			queryBuild.append(" group by group_id,opeartion_flag "); 
			
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("entityCode", entityCode);
			query.addScalar("groupId");
			query.addScalar("subdistrictNameEnglish");
			query.addScalar("opeartionFlag");
			query.addScalar("multipleFlag");
			query.addScalar("draftCodeText");
			query.addScalar("changeSubdistrictNameEnglish");
			
			query.setResultTransformer(Transformers.aliasToBean(SubdistrictDraft.class));
			subdistrictDraftList=query.list();
			
			}catch(Exception e){
			throw e;
			}finally{
				if
				(session!=null && session.isOpen()){
					session.close();
				}
			}
		return subdistrictDraftList;
	}

	@Override
	public DraftSubdistrictForm loadSubdistrictDraftbyId(DraftSubdistrictForm draftSubdistrictForm) throws Exception {
		Session session=null;
		SubdistrictDraft SubdistrictDraft=null;
		try{
			session=sessionFactory.openSession();
			SubdistrictDraft=(SubdistrictDraft)session.get(SubdistrictDraft.class, draftSubdistrictForm.getParamCode());
			draftUtils.copyBeanAttributes(draftSubdistrictForm, SubdistrictDraft);
			
			StringBuilder sb=new StringBuilder("select t.subdistrict_code as subdistrictCode,t.subdistrict_name_english as subdistrictNameEnglish,");
			sb.append(" case when t.subdistrict_code  in(select lb_lr_code from draft_master where lb_lr_type='T') then cast ('F' as character) else ");
			sb.append(" cast('A' as character) end as operation_state,cast ('F' as character) as isPart from subdistrict t where t.dlc=(select district_code ");
			sb.append(" from subdistrict_draft where draft_code =:paramCode) and t.isactive and t.tlc not in (select lb_lr_code from draft_master ");
			sb.append(" where draft_temp_id =:paramCode and lb_lr_type='T') order by t.subdistrict_name_english");
			
			SQLQuery query=session.createSQLQuery(sb.toString());
			query.setParameter("paramCode", draftSubdistrictForm.getParamCode());
			query.addScalar("subdistrictCode").addScalar("subdistrictNameEnglish");
			query.addScalar("operation_state").addScalar("isPart");
			query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class)).list();
			draftSubdistrictForm.setDraftSubDistrictList(query.list());
			
			sb=new StringBuilder("(select t.subdistrict_code as subdistrictCode,t.subdistrict_name_english||'(PART)' as subdistrictNameEnglish,cast ('A' as character) as operation_state,");
			sb.append("case when t.subdistrict_code in(select (cast(regexp_split_to_table(list_of_subdistrict_part,'@')AS INT)) from subdistrict_draft  where draft_code!=:paramCode) then cast ('T' as character) else cast ('F' as character) ");  
			sb.append(" end as isPart from  subdistrict  t where isactive and tlc in(SELECT (cast(regexp_split_to_table(list_of_subdistrict_part ,'@')AS INT)) ");
			sb.append(" from subdistrict_draft where draft_code =:paramCode) ");
			sb.append(" union ");
			sb.append(" select t.subdistrict_code as subdistrictCode,t.subdistrict_name_english||'(FULL)' as subdistrictNameEnglish,cast ('A' as character) as operation_state,cast ('N' as character) as isPart");
			sb.append(" from subdistrict t where isactive and tlc in(SELECT (cast(regexp_split_to_table(list_of_subdistrict_full ,'@')AS INT)) from subdistrict_draft where draft_code =:paramCode)) ");
			sb.append(" order by subdistrictNameEnglish");
			query=session.createSQLQuery(sb.toString());
			query.setParameter("paramCode", draftSubdistrictForm.getParamCode());
			query.addScalar("subdistrictCode").addScalar("subdistrictNameEnglish");
			query.addScalar("operation_state").addScalar("isPart");
			query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class)).list();
			draftSubdistrictForm.setContDraftSubDistrictList(query.list());
			
			sb=new StringBuilder("select v.village_code as villageCode,v.village_name_english||'('||t.subdistrict_name_english||')' as villageNameEnglish, case when v.village_code  in");
			sb.append("(select lb_lr_code from draft_master where lb_lr_type='V') then cast ('F' as character) else cast('A' as character) end as operation_state from village v left join ");
			sb.append(" subdistrict t on v.tlc=t.tlc where t.isactive and v.tlc in(SELECT (cast(regexp_split_to_table(list_of_subdistrict_part ,'@')AS INT)) from subdistrict_draft where ");  
			sb.append(" draft_code =:paramCode) and v.isactive and v.village_code not in(select lb_lr_code from draft_master where draft_temp_id =:paramCode and lb_lr_type='V') "); 
			sb.append(" order by v.village_name_english");
			query=session.createSQLQuery(sb.toString());
			query.setParameter("paramCode", draftSubdistrictForm.getParamCode());
			query.addScalar("villageCode").addScalar("villageNameEnglish");
			query.addScalar("operation_state");
			query.setResultTransformer(Transformers.aliasToBean(Village.class)).list();
			draftSubdistrictForm.setDraftVillageList(query.list());
			
			sb=new StringBuilder("select v.village_code as villageCode,v.village_name_english||'('||t.subdistrict_name_english||')' as villageNameEnglish ,cast ('A' as character) as operation_state ");
			sb.append(" from  village  v  left join  subdistrict t on v.tlc=t.tlc where t.isactive and v.isactive and vlc in(SELECT (cast(regexp_split_to_table(list_of_village_full ,E'\\,')AS INT))");
			sb.append(" from subdistrict_draft where draft_code =:paramCode) ");
			query=session.createSQLQuery(sb.toString());
			query.setParameter("paramCode", draftSubdistrictForm.getParamCode());
			query.addScalar("villageCode").addScalar("villageNameEnglish");
			query.addScalar("operation_state");
			query.setResultTransformer(Transformers.aliasToBean(Village.class)).list();
			draftSubdistrictForm.setContdraftVillageList(query.list());
			
			}catch(Exception e){
			throw e;
			}finally{
				if
				(session!=null && session.isOpen()){
					session.close();
				}
			}
		return draftSubdistrictForm;
	}

	
	@Override
	public boolean updateSubdistrictinDraft(DraftSubdistrictForm draftSubdistrictForm) throws Exception {
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();
			
			transaction=session.beginTransaction();
			SubdistrictDraft subdistrictDraft=(SubdistrictDraft)session.get(SubdistrictDraft.class, draftSubdistrictForm.getParamCode());
			draftSubdistrictForm.setDistrictCode(subdistrictDraft.getDistrictCode());
			draftSubdistrictForm.setDraftCode(subdistrictDraft.getDraftCode());
			draftSubdistrictForm.setStateCode(subdistrictDraft.getStateCode());
			draftUtils.copyBeanAttributes(subdistrictDraft, draftSubdistrictForm); 
			subdistrictDraft.setOpeartionFlag(DraftConstant.OPERATION_TYPE_CREATE.toString());
			session.update(subdistrictDraft);
			session.flush();
			updateCoverageSubdistricInDraftMaster(subdistrictDraft,session);
			transaction.commit();
			
		}catch(Exception e){
			transaction.rollback();
			throw e;
			
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return true;
	}
	
	
	private void updateCoverageSubdistricInDraftMaster(SubdistrictDraft subdistrictDraft,Session session)throws Exception{
		  
		Criteria criteria = session.createCriteria(DraftMaster.class);
		criteria.add( Restrictions.eq("ProcessId", Integer.parseInt(DraftConstant.DRAFT_OPERATION_CREATE_SUBDISTRICT.toString())));
		criteria.add( Restrictions.eq("draftMasterPK.draftTempId", subdistrictDraft.getDraftCode()));
		
		List<DraftMaster> draftMasterList = criteria.list();
		for(DraftMaster draftMaster:draftMasterList){
			session.delete(draftMaster);
			session.flush();
		}
		
		
		if(subdistrictDraft.getListofSubdistrictPart()!=null){
			   Scanner scanner = new Scanner(subdistrictDraft.getListofSubdistrictPart());
				scanner.useDelimiter("@");
				DraftMaster draftMaster=null;
				DraftMasterPK draftMasterPK=null;
				while (scanner.hasNext()) {
					draftMasterPK=new DraftMasterPK();
					draftMasterPK.setLbLRType(DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString());
					draftMasterPK.setLbLRCode(Integer.parseInt(scanner.next()));
					draftMasterPK.setDraftTempId(subdistrictDraft.getDraftCode());
					draftMaster=new DraftMaster();
					draftMaster.setProcessId(Integer.parseInt(DraftConstant.DRAFT_OPERATION_CREATE_SUBDISTRICT.toString()));
					draftMaster.setDraftMasterPK(draftMasterPK);
					session.save(draftMaster);
					session.flush();
					
				}
		   }
				
				if(subdistrictDraft.getListofSubdistrictFull()!=null){
						Scanner  scanner = new Scanner(subdistrictDraft.getListofSubdistrictFull());
						scanner.useDelimiter("@");
						DraftMaster draftMaster=null;
						DraftMasterPK draftMasterPK=null;
						while (scanner.hasNext()) {
							draftMasterPK=new DraftMasterPK();
							draftMasterPK.setLbLRType(DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString());
							draftMasterPK.setLbLRCode(Integer.parseInt(scanner.next()));
							draftMasterPK.setDraftTempId(subdistrictDraft.getDraftCode());
							draftMaster=new DraftMaster();
							draftMaster.setProcessId(Integer.parseInt(DraftConstant.DRAFT_OPERATION_CREATE_SUBDISTRICT.toString()));
							draftMaster.setDraftMasterPK(draftMasterPK);
							session.save(draftMaster);
							session.flush();
							
						}
				
				
		   }
				if(subdistrictDraft.getListofVillageFull()!=null){
					Scanner  scanner = new Scanner(subdistrictDraft.getListofVillageFull());
					scanner.useDelimiter(",");
					DraftMaster draftMaster=null;
					DraftMasterPK draftMasterPK=null;
					while (scanner.hasNext()) {
						draftMasterPK=new DraftMasterPK();
						draftMasterPK.setLbLRType(DraftConstant.ENTITY_TYPE_VILLAGE.toString());
						draftMasterPK.setLbLRCode(Integer.parseInt(scanner.next()));
						draftMasterPK.setDraftTempId(subdistrictDraft.getDraftCode());
						draftMaster=new DraftMaster();
						draftMaster.setProcessId(Integer.parseInt(DraftConstant.DRAFT_OPERATION_CREATE_SUBDISTRICT.toString()));
						draftMaster.setDraftMasterPK(draftMasterPK);
						session.save(draftMaster);
						session.flush();
						
					}
			
			
	   }
	   }

	@Override
	public LinkedList<DraftSubdistrictForm> saveSubdistrict(LinkedList<DraftSubdistrictForm> storedSubdistrictForms,DraftGovermentOrderForm draftGovermentOrderForm) throws Exception {
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();
			
			transaction=session.beginTransaction();
			
			for(DraftSubdistrictForm draftSubdistrictForm:storedSubdistrictForms){
				if(draftSubdistrictForm.getCensus2011Code()==null || "".equals(draftSubdistrictForm.getCensus2011Code())){
					draftSubdistrictForm.setCensus2011Code("00000");
				}
				String listofSubdistrictFull=draftSubdistrictForm.getListofSubdistrictFull();
				if(listofSubdistrictFull!=null && !("").equals(listofSubdistrictFull) && listofSubdistrictFull.contains("@")){
					draftSubdistrictForm.setListofSubdistrictFull(listofSubdistrictFull.replace("@", ","));
				}
				
				String listofSubdistrictPart=draftSubdistrictForm.getListofSubdistrictPart();
				if(listofSubdistrictPart!=null && !("").equals(listofSubdistrictPart) && listofSubdistrictPart.contains("@") ){
					draftSubdistrictForm.setListofSubdistrictPart(listofSubdistrictPart.replace("@", ","));
				}
					
				InsertSubdistrictFn returnObj=(InsertSubdistrictFn)executeInsertSubdistrictFunction(session,draftSubdistrictForm,draftGovermentOrderForm);
				if(returnObj!=null){
				String returnObjArr[]=returnObj.getCreateSubdistrictFn().split(",");
				draftSubdistrictForm.setSubDistrictCode(Integer.parseInt(returnObjArr[0]));
				draftGovermentOrderForm.setOrderCode(Integer.parseInt(returnObjArr[1]));
				storedSubdistrictForms.set(storedSubdistrictForms.indexOf(draftSubdistrictForm), draftSubdistrictForm);
				draftUtilDao.saveGovermentOrderinAttachement(draftGovermentOrderForm, session);
				}
		}
		transaction.commit();
		
		}catch(Exception e){
			transaction.rollback();
			throw e;
			
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return storedSubdistrictForms;
	}
	
	/**
	 * 
	 * @param session
	 * @param localBodyForm
	 * @return
	 */
	private Object executeInsertSubdistrictFunction (Session session, DraftSubdistrictForm draftSubdistrictForm,DraftGovermentOrderForm draftGovermentOrderForm){
		Object returnedValue=null;
		try{
			Query query = session.getNamedQuery("Insert_Subdistrict_Details")
					.setParameter("districtCode"			,draftSubdistrictForm.getDistrictCode()				, Hibernate.INTEGER)
					.setParameter("userId"	    			,draftSubdistrictForm.getUserId()					, Hibernate.INTEGER)
					.setParameter("subdistrictNameEnglish"  ,draftSubdistrictForm.getSubdistrictNameEnglish()	, Hibernate.STRING)
					.setParameter("subdistrictNameLocal"    ,draftSubdistrictForm.getSubdistrictNameLocal()		, Hibernate.STRING)
					.setParameter("aliasEnglish"    		,draftSubdistrictForm.getAliasEnglish()				, Hibernate.STRING)
					.setParameter("aliasLocal"			    ,draftSubdistrictForm.getAliasLocal()				, Hibernate.STRING)
					.setParameter("census2011Code"			,draftSubdistrictForm.getCensus2011Code()			, Hibernate.STRING) 
					.setParameter("sscode"					,draftSubdistrictForm.getSscode()					, Hibernate.STRING)
					.setParameter("coordinates"				,draftSubdistrictForm.getCoordinates()				, Hibernate.STRING)
					.setParameter("headquarterNameEnglish"	,draftSubdistrictForm.getHeadquarterNameEnglish()	, Hibernate.STRING)
					.setParameter("headquarterNameLocal"	,draftSubdistrictForm.getHeadquarterNameLocal()		, Hibernate.STRING)
					.setParameter("orderNo"					,draftGovermentOrderForm.getOrderNo()				, Hibernate.STRING)
					.setParameter("orderDate"				,draftGovermentOrderForm.getOrderDate()				, Hibernate.TIMESTAMP)
					.setParameter("effectiveDate"			,draftGovermentOrderForm.getEffectiveDate()			, Hibernate.TIMESTAMP)
					.setParameter("gazPubDate"				,draftGovermentOrderForm.getGazPubDate()			, Hibernate.TIMESTAMP)
					.setParameter("listofSubdistrictFull"	,draftSubdistrictForm.getListofSubdistrictFull()	, Hibernate.STRING)
					.setParameter("listofSubdistrictPart"	,draftSubdistrictForm.getListofSubdistrictPart()	, Hibernate.STRING)
					.setParameter("listofVillageFull"		,draftSubdistrictForm.getListofVillageFull()		, Hibernate.STRING)
					.setParameter("mapLandregionCode"		,(draftSubdistrictForm.getMapLandregionCode()!=null?Integer.parseInt(draftSubdistrictForm.getMapLandregionCode()):0), Hibernate.INTEGER) // Map Attachment Code
					;
					 
		returnedValue =  query.uniqueResult();
		} catch(Exception e){
			throw new HibernateException(e);
		}
		return returnedValue;
	}

	@Override
	public Map<String, Object> viewSaveSubdistrictDetails(LinkedList<DraftSubdistrictForm> storedSubdistrictForms,DraftGovermentOrderForm draftGovermentOrderForm) throws Exception {
		Map<String,Object> StoreSubdistrictDetails=null;
		try{
			
			StoreSubdistrictDetails=new HashMap<String,Object>();
			Integer districtCode=null;
			for(DraftSubdistrictForm draftSubdistrictForm:storedSubdistrictForms){
				StoreSubdistrictDetails.put(draftSubdistrictForm.getSubdistrictNameEnglish()+DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString(), getCoverageSubdistrictDetails(draftSubdistrictForm));
				StoreSubdistrictDetails.put(draftSubdistrictForm.getSubdistrictNameEnglish()+DraftConstant.ENTITY_TYPE_VILLAGE.toString(), draftVillageDao.getCoverageVillageDetails(draftSubdistrictForm));
				if(districtCode==null){
					districtCode=draftSubdistrictForm.getDistrictCode();
				}
			}
			
			StoreSubdistrictDetails.put(DraftConstant.DISTRICT_NAME_ENGLISH.toString(), draftDistrictDao.getDistrictNameEng(districtCode));
		
		}catch(Exception e){
			
			throw e;
		}	
		return StoreSubdistrictDetails;
		
	}

	@Override
	public List<Subdistrict> getCoverageSubdistrictDetails(DraftSubdistrictForm draftSubdistrictForm) throws Exception {
		List<Subdistrict> coverageSubdistrictList=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			StringBuilder sb=new StringBuilder("(select subdistrict_code as subdistrictCode,subdistrict_name_english as subdistrictNameEnglish,cast('PART' as character varying)coverageType  from subdistrict  ");
						  sb.append(" where tlc in(:subdistrictListPart)  and isactive ");
						  sb.append(" union ");
						  sb.append(" select subdistrict_code as subdistrictCode,subdistrict_name_english as subdistrictNameEnglish,cast('FULL' as character varying)coverageType  from subdistrict  ");
						  sb.append(" where tlc in(:subdistrictListFull) and isactive )  order by coverageType");
			
			
			SQLQuery query=session.createSQLQuery(sb.toString());
			
			query.setParameterList("subdistrictListPart", draftUtils.createListFromString(draftSubdistrictForm.getListofSubdistrictPart()));
			query.setParameterList("subdistrictListFull", draftUtils.createListFromString(draftSubdistrictForm.getListofSubdistrictFull()));
			query.addScalar("subdistrictCode").addScalar("subdistrictNameEnglish");
			query.addScalar("coverageType");
			query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class));
			
			coverageSubdistrictList=query.list();
			
			
		}catch(Exception e){
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return coverageSubdistrictList;
	}
	
	
	@Override
	public Object[] getSubdistrictDetailsinDraft(DraftSubdistrictForm draftSubdistrictForm)throws Exception{
		Object[] objArray=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			objArray=new Object[2];
			
			Query query=session.createQuery("from SubdistrictDraft where groupId=:groupId");
			query.setParameter("groupId", draftSubdistrictForm.getParamCode());
			List<SubdistrictDraft> subdistrictDraftList=query.list();
			LinkedList<DraftSubdistrictForm> storedSubdistrictForms=new LinkedList<DraftSubdistrictForm>();
			DraftSubdistrictForm tempObject=null;
			for(SubdistrictDraft subdistrictDraft:subdistrictDraftList){
				tempObject=new DraftSubdistrictForm();
				draftUtils.copyBeanAttributes(tempObject, subdistrictDraft);
				tempObject.setUserId(draftSubdistrictForm.getUserId());
				storedSubdistrictForms.add(tempObject);
			}
			
			objArray[0]=storedSubdistrictForms;
			GovermentDetailDraft govermentDetailDraft=(GovermentDetailDraft)session.get(GovermentDetailDraft.class, draftSubdistrictForm.getParamCode());
			DraftGovermentOrderForm draftGovermentOrderForm=new DraftGovermentOrderForm();
			draftUtils.copyBeanAttributes(draftGovermentOrderForm, govermentDetailDraft);
			objArray[1]=draftGovermentOrderForm;
		}catch(Exception e){
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return objArray;
	}

	@Override
	public Object[] subdistrictDrafttoPublish(DraftSubdistrictForm draftSubdistrictForm)throws Exception {
		Session session=null;
		Transaction transaction = null;
		Object[] objArr=new Object[3];
		
		try{
			session=sessionFactory.openSession();
			
			transaction=session.beginTransaction();
			
			GovermentDetailDraft govermentDetailDraft=(GovermentDetailDraft)session.get(GovermentDetailDraft.class, draftSubdistrictForm.getParamCode());
			DraftGovermentOrderForm draftGovermentOrderForm=new DraftGovermentOrderForm();
			draftUtils.copyBeanAttributes(draftGovermentOrderForm, govermentDetailDraft);
			
			Query query=session.createQuery("from SubdistrictDraft where groupId=:groupId");
			query.setParameter("groupId", draftSubdistrictForm.getParamCode());
			List<SubdistrictDraft> subdistrictDraftList=query.list();
			
			Map<String,Object> StoreSubdistrictDetails=new HashMap<String,Object>();
			Integer districtCode=null;
			
			LinkedList<DraftSubdistrictForm> storedSubdistrictForms=new LinkedList<DraftSubdistrictForm>();
			DraftSubdistrictForm tempObject=null;
			for(SubdistrictDraft subdistrictDraft:subdistrictDraftList){
				tempObject=new DraftSubdistrictForm();
				draftUtils.copyBeanAttributes(tempObject, subdistrictDraft);
				tempObject.setUserId(draftSubdistrictForm.getUserId());
				
				
				StoreSubdistrictDetails.put(tempObject.getSubdistrictNameEnglish()+DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString(), getCoverageSubdistrictDetails(tempObject));
				StoreSubdistrictDetails.put(tempObject.getSubdistrictNameEnglish()+DraftConstant.ENTITY_TYPE_VILLAGE.toString(), draftVillageDao.getCoverageVillageDetails(tempObject));
				if(districtCode==null){
					districtCode=tempObject.getDistrictCode();
				}
				
				String listofSubdistrictFull=tempObject.getListofSubdistrictFull();
				if(listofSubdistrictFull!=null && !("").equals(listofSubdistrictFull) && listofSubdistrictFull.contains("@")){
					tempObject.setListofSubdistrictFull(listofSubdistrictFull.replace("@", ","));
				}
				
				String listofSubdistrictPart=tempObject.getListofSubdistrictPart();
				if(listofSubdistrictPart!=null && !("").equals(listofSubdistrictPart) && listofSubdistrictPart.contains("@") ){
					tempObject.setListofSubdistrictPart(listofSubdistrictPart.replace("@", ","));
				}
				
				/**
				 * Change Bean value according to create_subdistrict_fn function #started
				 */
				if(tempObject.getListofSubdistrictPart()==null){
					tempObject.setListofSubdistrictPart("");
				}
				
				if(tempObject.getListofVillageFull()==null){
					tempObject.setListofVillageFull("");
				}
				
				if(tempObject.getMapLandregionCode()==null){
					tempObject.setMapLandregionCode("0");
				}
				/**
				 * Change Bean value according to create_subdistrict_fn function #end
				 */
					
				InsertSubdistrictFn returnObj=(InsertSubdistrictFn)executeInsertSubdistrictFunction(session,tempObject,draftGovermentOrderForm);
				if(returnObj!=null){
					String returnObjArr[]=returnObj.getCreateSubdistrictFn().split(",");
					tempObject.setSubDistrictCode(Integer.parseInt(returnObjArr[0]));
					draftGovermentOrderForm.setOrderCode(Integer.parseInt(returnObjArr[1]));
					storedSubdistrictForms.add(tempObject);
					draftUtilDao.saveGovermentOrderinAttachement(draftGovermentOrderForm, session);
					
					Criteria criteria = session.createCriteria(DraftMaster.class);
					criteria.add( Restrictions.eq("ProcessId", Integer.parseInt(DraftConstant.DRAFT_OPERATION_CREATE_SUBDISTRICT.toString())));
					criteria.add( Restrictions.eq("draftMasterPK.draftTempId", subdistrictDraft.getDraftCode()));
					
					List<DraftMaster> draftMasterList = criteria.list();
					for(DraftMaster draftMaster:draftMasterList){
						session.delete(draftMaster);
						session.flush();
					}
					
					
					session.delete(subdistrictDraft);
					session.flush();
				
				}
				
			}
			
			StoreSubdistrictDetails.put(DraftConstant.DISTRICT_NAME_ENGLISH.toString(), draftDistrictDao.getDistrictNameEng(districtCode));
			
			session.delete(govermentDetailDraft);
			session.flush();
			
			draftGovermentOrderForm.setFormAction(DraftConstant.FORM_ACTION_PUBLISH.toString());
			
			transaction.commit();
		
			objArr[0]=storedSubdistrictForms;
			
			objArr[1]=draftGovermentOrderForm;
			
			objArr[2]=StoreSubdistrictDetails;
		
		}catch(Exception e){
			transaction.rollback();
			throw e;
			
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return objArr;
	}
	
	@Override
	public boolean deleteDraftSubdistrict(Integer paramCode,Integer processId)throws Exception{
	Session session=null;
	Transaction transaction = null;
	try{
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		GovermentDetailDraft govermentDetailDraft=(GovermentDetailDraft)session.get(GovermentDetailDraft.class,paramCode);
		
		Query query=session.createQuery("from SubdistrictDraft where groupId=:groupId");
		query.setParameter("groupId", paramCode);
		List<SubdistrictDraft> subdistrictDraftList=query.list();
		for(SubdistrictDraft subdistrictDraft:subdistrictDraftList){
				Criteria criteria = session.createCriteria(DraftMaster.class);
				criteria.add( Restrictions.eq("ProcessId",processId));
				criteria.add( Restrictions.eq("draftMasterPK.draftTempId", subdistrictDraft.getDraftCode()));
				
				List<DraftMaster> draftMasterList = criteria.list();
				for(DraftMaster draftMaster:draftMasterList){
					session.delete(draftMaster);
					session.flush();
				}
				session.delete(subdistrictDraft);
				session.flush();
		}
		session.delete(govermentDetailDraft);
		session.flush();
		transaction.commit();
	}catch(Exception e){
		transaction.rollback();
		throw e;
		
	}finally{
		if
		(session!=null && session.isOpen()){
			session.close();
		}
	}
	return true;
	}
	
	@Override
	public Object[] viewDraftSubdistricDetails(Integer groupId)throws Exception{
		Session session=null;
		Object[] objArr=new Object[3];
		
		try{
			session=sessionFactory.openSession();
			GovermentDetailDraft govermentDetailDraft=(GovermentDetailDraft)session.get(GovermentDetailDraft.class, groupId);
			DraftGovermentOrderForm draftGovermentOrderForm=new DraftGovermentOrderForm();
			draftUtils.copyBeanAttributes(draftGovermentOrderForm, govermentDetailDraft);
			
			Query query=session.createQuery("from SubdistrictDraft where groupId=:groupId");
			query.setParameter("groupId", groupId);
			List<SubdistrictDraft> subdistrictDraftList=query.list();
			
			Map<String,Object> StoreSubdistrictDetails=new HashMap<String,Object>();
			Integer districtCode=null;
			
			LinkedList<DraftSubdistrictForm> storedSubdistrictForms=new LinkedList<DraftSubdistrictForm>();
			DraftSubdistrictForm tempObject=null;
			for(SubdistrictDraft subdistrictDraft:subdistrictDraftList){
				tempObject=new DraftSubdistrictForm();
				draftUtils.copyBeanAttributes(tempObject, subdistrictDraft);
				
				
				
				StoreSubdistrictDetails.put(tempObject.getSubdistrictNameEnglish()+DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString(), getCoverageSubdistrictDetails(tempObject));
				StoreSubdistrictDetails.put(tempObject.getSubdistrictNameEnglish()+DraftConstant.ENTITY_TYPE_VILLAGE.toString(), draftVillageDao.getCoverageVillageDetails(tempObject));
				if(districtCode==null){
					districtCode=tempObject.getDistrictCode();
				}
				
				storedSubdistrictForms.add(tempObject);
				
				
			}
			
			StoreSubdistrictDetails.put(DraftConstant.DISTRICT_NAME_ENGLISH.toString(), draftDistrictDao.getDistrictNameEng(districtCode));
			draftGovermentOrderForm.setFormAction(DraftConstant.FORM_ACTION_DRAFT.toString());
			
			objArr[0]=storedSubdistrictForms;
			
			objArr[1]=draftGovermentOrderForm;
			
			objArr[2]=StoreSubdistrictDetails;
		
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return objArr;
	}
	
    @Override
	public String valdidateVillageCoverage(List<Integer> coverageSubdistList,List<Integer> coverageVillageList)throws Exception{
		
    	String leftSubdistrictCoverage=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			StringBuilder sb=new StringBuilder("select array_to_string(array(select t.subdistrict_name_english as ");
			sb.append(" subdistrictNameEnglish from subdistrict t  where isactive and tlc in(:coverageSubdistList) and tlc not in( select distinct tlc from village "); 
			sb.append(" where   isactive and vlc in(:coverageVillageList)) order by subdistrict_name_english ),',')");
			
			
			SQLQuery query=session.createSQLQuery(sb.toString());
			query.setParameterList("coverageSubdistList", coverageSubdistList);
			query.setParameterList("coverageVillageList", coverageVillageList);
			
			
			leftSubdistrictCoverage=(String)query.uniqueResult();
			
			
		}catch(Exception e){
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return leftSubdistrictCoverage;
		}

	@Override
	public DraftManageSubdistrictForm loadSubdistrictDetails(DraftManageSubdistrictForm draftManageSubdistrictForm) throws Exception {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Subdistrict.class);
			criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
			criteria.add( Restrictions.eq("subdistrictPK.subdistrictCode", draftManageSubdistrictForm.getParamCode()));
			
			@SuppressWarnings("unchecked")
			List<Subdistrict> SubdistrictList = criteria.list();
			if(SubdistrictList!=null && !SubdistrictList.isEmpty()){
				Subdistrict subdistrictObj=SubdistrictList.get(0);
				subdistrictObj.setEffectiveDate(null);
				draftUtils.copyBeanAttributes(draftManageSubdistrictForm, subdistrictObj);
			}
			
			
		}catch(Exception e){
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return draftManageSubdistrictForm;
	}
    
	@Override
	public Integer saveChangeSubdistrictinDraft(DraftManageSubdistrictForm draftManageSubdistrictForm,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Session session=null;
		Transaction transaction = null;
		Integer groupId=null;
		try{
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			
			DraftGovermentOrderForm draftGovermentOrderForm =new DraftGovermentOrderForm();
			draftUtils.copyBeanAttributes(draftGovermentOrderForm, draftManageSubdistrictForm);
			draftGovermentOrderForm.setEntityType(DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString().charAt(0));
			
			GovermentDetailDraft govermentDetailDraft =new GovermentDetailDraft();
			if (draftGovermentOrderForm.getGazettePublicationUpload() != null && !draftGovermentOrderForm.getGazettePublicationUpload().get(0).isEmpty()) {
				draftGovermentOrderForm=draftUtils.uploadFileToServer(draftGovermentOrderForm,Long.parseLong( DraftConstant.ATTACHMENT_MASTER_GO.toString()),request,httpSession);
				draftUtils.copyBeanAttributes(govermentDetailDraft, draftGovermentOrderForm);
				
			}else{
				if(draftManageSubdistrictForm.isEditMode()){
					 govermentDetailDraft=(GovermentDetailDraft)session.get(GovermentDetailDraft.class, draftManageSubdistrictForm.getGroupId());
				}
				
				if(draftManageSubdistrictForm.getTemplateId()!=null && draftManageSubdistrictForm.getTemplateId()>0){
					draftUtils.copyBeanAttributes(govermentDetailDraft, draftGovermentOrderForm);
				}
			}
			
			
			
			
			
			if(draftManageSubdistrictForm.isEditMode()){
				govermentDetailDraft.setOrderNo(draftManageSubdistrictForm.getOrderNo());
				govermentDetailDraft.setOrderDate(draftManageSubdistrictForm.getOrderDate());
				govermentDetailDraft.setEffectiveDate(draftManageSubdistrictForm.getEffectiveDate());
				govermentDetailDraft.setGazPubDate(draftManageSubdistrictForm.getGazPubDate());
				govermentDetailDraft.setGroupId(draftManageSubdistrictForm.getGroupId());
				
				session.update(govermentDetailDraft);
			}else{
				session.save(govermentDetailDraft);
			}
			
			session.flush();
			groupId=govermentDetailDraft.getGroupId();
			
			SubdistrictDraft subdistrictDraft=new SubdistrictDraft();
			draftUtils.copyBeanAttributes(subdistrictDraft, draftManageSubdistrictForm); 
			
			subdistrictDraft.setGroupId(govermentDetailDraft.getGroupId());
			if(draftManageSubdistrictForm.isEditMode()){
				
				session.update(subdistrictDraft);
			}else{
				subdistrictDraft.setDraftCode(null);
				session.save(subdistrictDraft);
			}
			
			session.flush();
			
			if(!draftManageSubdistrictForm.isEditMode()){
				DraftMasterPK draftMasterPK=new DraftMasterPK();
				draftMasterPK.setLbLRType(DraftConstant.ENTITY_TYPE_SUBDISTRICT.toString());
				draftMasterPK.setLbLRCode(draftManageSubdistrictForm.getSubdistrictCode());
				draftMasterPK.setDraftTempId(subdistrictDraft.getDraftCode());
				DraftMaster draftMaster=new DraftMaster();
				draftMaster.setProcessId(Integer.parseInt(DraftConstant.DRAFT_OPERATION_CHANGE_SUBDISTRICT.toString()));
				draftMaster.setDraftMasterPK(draftMasterPK);
				session.save(draftMaster);
				session.flush();
			}
			
					
			transaction.commit();
			
		}catch(Exception e){
			transaction.rollback();
			throw e;
			
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return groupId;
	}
    
	@Override
	public Object[] viewChangeDraftSubdistricDetails(DraftManageSubdistrictForm draftManageSubdistrictForm) throws Exception {
		Session session = null;
		Object[] objArr = new Object[3];
		List<Subdistrict> subdistrictList=null;
		Query query=null;
		Integer districtCode=null;
		Map<String, Object> StoreSubdistrictDetails = new HashMap<String, Object>();
		try {
			session = sessionFactory.openSession();
					if(!draftManageSubdistrictForm.isDirectMode()){
					GovermentDetailDraft govermentDetailDraft = (GovermentDetailDraft) session.get(GovermentDetailDraft.class, draftManageSubdistrictForm.getParamCode());
					draftUtils.copyBeanAttributes(draftManageSubdistrictForm, govermentDetailDraft);
		
					query = session.createQuery("from SubdistrictDraft where groupId=:groupId");
					query.setParameter("groupId", draftManageSubdistrictForm.getParamCode());
					List<SubdistrictDraft> subdistrictDraftList = query.list();
					
						if (subdistrictDraftList != null && !subdistrictDraftList.isEmpty()) {
							SubdistrictDraft SubdistrictDraft = subdistrictDraftList.get(0);
							draftUtils.copyBeanAttributes(draftManageSubdistrictForm, SubdistrictDraft);
							districtCode=SubdistrictDraft.getDistrictCode();
							draftManageSubdistrictForm.setSubdistrictCode(SubdistrictDraft.getSubdistrictCode());
						}	
					}else{
						districtCode=draftManageSubdistrictForm.getDistrictCode();
					}
					
					
					
					StoreSubdistrictDetails.put("subdistrictList", this.getSubDistrictDetails(draftManageSubdistrictForm.getSubdistrictCode()));
					
					
					StoreSubdistrictDetails.put(DraftConstant.DISTRICT_NAME_ENGLISH.toString(), draftDistrictDao.getDistrictNameEng(districtCode));
					objArr[0] = draftManageSubdistrictForm;
					
					objArr[1] = StoreSubdistrictDetails;
				
			
		
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return objArr;
	}
	
	
	@Override
	public DraftManageSubdistrictForm saveChangeSubdistrict(DraftManageSubdistrictForm draftManageSubdistrictForm,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();
			
			transaction=session.beginTransaction();
			
			
			if (draftManageSubdistrictForm.getGazettePublicationUpload() != null && !draftManageSubdistrictForm.getGazettePublicationUpload().get(0).isEmpty()) {
				DraftGovermentOrderForm draftGovermentOrderForm=new DraftGovermentOrderForm();
				draftUtils.copyBeanAttributes(draftGovermentOrderForm,draftManageSubdistrictForm); 
				draftGovermentOrderForm=draftUtils.uploadFileToServer(draftGovermentOrderForm,Long.parseLong( DraftConstant.ATTACHMENT_MASTER_GO.toString()),request,httpSession);
				draftUtils.copyBeanAttributes(draftManageSubdistrictForm, draftGovermentOrderForm);
			}else{
				if(draftManageSubdistrictForm.isEditMode()){
					GovermentDetailDraft govermentDetailDraft=(GovermentDetailDraft)session.get(GovermentDetailDraft.class, draftManageSubdistrictForm.getGroupId());
					draftUtils.copyBeanAttributes(draftManageSubdistrictForm, govermentDetailDraft);
				}
			}
					
			ChangeSubdistrictFn returnObj=(ChangeSubdistrictFn)executeChangeSubdistrictFunction(session,draftManageSubdistrictForm);
			if(returnObj!=null){
			String returnObjArr[]=returnObj.getChangeSubdistrictFn().split(",");
			DraftGovermentOrderForm draftGovermentOrderForm=new DraftGovermentOrderForm();
			draftUtils.copyBeanAttributes(draftGovermentOrderForm, draftManageSubdistrictForm);
			draftGovermentOrderForm.setOrderCode(Integer.parseInt(returnObjArr[1]));
			draftManageSubdistrictForm.setSubdistrictVersion(Integer.parseInt(returnObjArr[2]));
			draftUtilDao.saveGovermentOrderinAttachement(draftGovermentOrderForm, session);
				
		}
		transaction.commit();
		
		}catch(Exception e){
			transaction.rollback();
			throw e;
			
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return draftManageSubdistrictForm;
	}
	
	/**
	 * 
	 * @param session
	 * @param localBodyForm
	 * @return
	 */
	private Object executeChangeSubdistrictFunction (Session session, DraftManageSubdistrictForm draftManageSubdistrictForm){
		Query query = session.getNamedQuery("Change_Subdistrict_Details")
				
				
					.setParameter("subdistrictCode"					,draftManageSubdistrictForm.getSubdistrictCode()				, Hibernate.INTEGER)
					.setParameter("userId"	    					,draftManageSubdistrictForm.getUserId()							, Hibernate.INTEGER)
					.setParameter("changeSubdistrictNameEnglish"  	,draftManageSubdistrictForm.getChangeSubdistrictNameEnglish()	, Hibernate.STRING)
					.setParameter("orderNo"							,draftManageSubdistrictForm.getOrderNo()						, Hibernate.STRING)
					.setParameter("orderDate"						,draftManageSubdistrictForm.getOrderDate()						, Hibernate.TIMESTAMP)
					.setParameter("effectiveDate"					,draftManageSubdistrictForm.getEffectiveDate()					, Hibernate.TIMESTAMP)
					.setParameter("govOrder"						,"upload"														, Hibernate.STRING)
					.setParameter("subdistrictNameEnglish"			,draftManageSubdistrictForm.getSubdistrictNameEnglish()			, Hibernate.STRING)
					.setParameter("gazPubDate"						,draftManageSubdistrictForm.getGazPubDate()						, Hibernate.TIMESTAMP)
					.setParameter("subdistrictNameLocal"    		,draftManageSubdistrictForm.getSubdistrictNameLocal()			, Hibernate.STRING)
					.setParameter("aliasEnglish"    				,draftManageSubdistrictForm.getAliasEnglish()					, Hibernate.STRING)
					.setParameter("aliasLocal"			    		,draftManageSubdistrictForm.getAliasLocal()						, Hibernate.STRING)
					.setParameter("stateCode"						,draftManageSubdistrictForm.getStateCode()						, Hibernate.INTEGER)
					
					;
					 
		
		
		Object returnedValue =  query.uniqueResult();
		return returnedValue;
	}
	
	
	@Override
	public Object[] changeSubdistrictDrafttoPublish(DraftManageSubdistrictForm draftManageSubdistrictForm,HttpServletRequest request,HttpSession httpSession)throws Exception {
		Session session=null;
		Transaction transaction = null;
		Object[] objArr=new Object[3];
		
		try{
			session=sessionFactory.openSession();
			
			transaction=session.beginTransaction();
			
			GovermentDetailDraft govermentDetailDraft=(GovermentDetailDraft)session.get(GovermentDetailDraft.class, draftManageSubdistrictForm.getParamCode());
			DraftGovermentOrderForm draftGovermentOrderForm=new DraftGovermentOrderForm();
			draftUtils.copyBeanAttributes(draftManageSubdistrictForm, govermentDetailDraft);
			
			
			draftUtils.copyBeanAttributes(draftGovermentOrderForm,draftManageSubdistrictForm); 
			draftGovermentOrderForm=draftUtils.uploadFileToServer(draftGovermentOrderForm,Long.parseLong( DraftConstant.ATTACHMENT_MASTER_GO.toString()),request,httpSession);
			draftUtils.copyBeanAttributes(draftManageSubdistrictForm, draftGovermentOrderForm);
			
		
			
			Query query=session.createQuery("from 0 where groupId=:groupId");
			query.setParameter("groupId", draftManageSubdistrictForm.getParamCode());
			List<SubdistrictDraft> subdistrictDraftList=query.list();
			if(subdistrictDraftList!=null && !subdistrictDraftList.isEmpty()){
				SubdistrictDraft subdistrictDraft=subdistrictDraftList.get(0);
				draftUtils.copyBeanAttributes(draftManageSubdistrictForm, subdistrictDraft);
				
				/**
				 * Change Bean value according to create_subdistrict_fn function #end
				 */
					
				ChangeSubdistrictFn returnObj=(ChangeSubdistrictFn)executeChangeSubdistrictFunction(session,draftManageSubdistrictForm);
				if(returnObj!=null){
					String returnObjArr[]=returnObj.getChangeSubdistrictFn().split(",");
					draftGovermentOrderForm.setOrderCode(Integer.parseInt(returnObjArr[1]));
					draftManageSubdistrictForm.setSubdistrictVersion(Integer.parseInt(returnObjArr[2]));
					draftUtilDao.saveGovermentOrderinAttachement(draftGovermentOrderForm, session);
					
					Criteria criteria = session.createCriteria(DraftMaster.class);
					criteria.add( Restrictions.eq("ProcessId", Integer.parseInt(DraftConstant.DRAFT_OPERATION_CHANGE_SUBDISTRICT.toString())));
					criteria.add( Restrictions.eq("draftMasterPK.draftTempId", subdistrictDraft.getDraftCode()));
					
					List<DraftMaster> draftMasterList = criteria.list();
					for(DraftMaster draftMaster:draftMasterList){
						session.delete(draftMaster);
						session.flush();
					}
					
					
					Map<String, Object> StoreSubdistrictDetails = new HashMap<String, Object>();
					StoreSubdistrictDetails.put("subdistrictList", this.getSubDistrictDetails(draftManageSubdistrictForm.getSubdistrictCode()));
					
					StoreSubdistrictDetails.put(DraftConstant.DISTRICT_NAME_ENGLISH.toString(), draftDistrictDao.getDistrictNameEng(subdistrictDraft.getDistrictCode()));
					
					objArr[0] = draftManageSubdistrictForm;
					
					objArr[1] = StoreSubdistrictDetails;
					
					session.delete(subdistrictDraft);
					session.flush();
					
					session.delete(govermentDetailDraft);
					session.flush();
					
					transaction.commit();
				
				}
			}
			
				
			
		
		}catch(Exception e){
			transaction.rollback();
			throw e;
			
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return objArr;
	}

	@Override
	public List<SubdistrictDraft> getDraftSubdistrictListbyGroupId(Integer groupId) throws Exception {
		List<SubdistrictDraft> subdistrictDraftList=null;
		Session session=null;
		try{
			
			session=sessionFactory.openSession();
			
			Criteria criteria = session.createCriteria(SubdistrictDraft.class);
			criteria.add( Restrictions.eq("groupId", groupId));
			subdistrictDraftList = criteria.list();
			}catch(Exception e){
			throw e;
			}finally{
				if
				(session!=null && session.isOpen()){
					session.close();
				}
			}
		return subdistrictDraftList;
	}
	
	public GovermentDetailDraft getDraftGovermentDetailbyGroupId(Integer groupId) throws Exception {
		GovermentDetailDraft govermentDetailDraft=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			govermentDetailDraft=(GovermentDetailDraft)session.get(GovermentDetailDraft.class, groupId);
			}catch(Exception e){
			throw e;
			}finally{
				if
				(session!=null && session.isOpen()){
					session.close();
				}
			}
		return govermentDetailDraft;
	}
	

@Override	
public DraftManageSubdistrictForm getChangeDraftSubdistrict(Integer groupId)throws Exception{
		DraftManageSubdistrictForm draftManageSubdistrictForm=null;
		List<SubdistrictDraft> subdistrictDraftList =this.getDraftSubdistrictListbyGroupId(groupId);
		if(subdistrictDraftList!=null && !subdistrictDraftList.isEmpty()){
			SubdistrictDraft subdistrictDraft=subdistrictDraftList.get(0);
			draftManageSubdistrictForm=new DraftManageSubdistrictForm();
			draftUtils.copyBeanAttributes(draftManageSubdistrictForm, subdistrictDraft);
			
			draftUtils.copyBeanAttributes(draftManageSubdistrictForm, this.getDraftGovermentDetailbyGroupId(groupId));
		}
		return draftManageSubdistrictForm;
		
	}


	/* Author Pranav Tiwari
	 * Checking subDistrict map is finalisation 
	 * on 21-03-2017
	 * */
@Override
public boolean subDistrictMapFinalised(Integer subdistrictCode){
	Session session = null;
	boolean isMapfinalised = false;
	try {
		session = sessionFactory.openSession();
		Query query 	= 	session.createSQLQuery("select case when count(1)>0 then true else false end from village v, subdistrict t, village_gismap_status g "
							+ " where t.tlc=:subdistrictCode and t.isactive and t.tlc=v.tlc and v.isactive and v.vlc=g.village_code and g.ismapfinalized=false ");
		query.setParameter("subdistrictCode", subdistrictCode);
		isMapfinalised = (boolean)query.uniqueResult();
	} 
	finally{
		if(session != null && session.isOpen())
			session.close();
	}
	return isMapfinalised;
}
	

@Override
public  List<Subdistrict> getSubDistrictDetails(Integer subDistrictCode) throws Exception{
	Session session = null;
	List<Subdistrict> subdistrictList=null;
	try {
		session = sessionFactory.openSession();
		StringBuilder sb=new StringBuilder("select subdistrict_code as subdistrictCode ,subdistrict_version as subdistrictVersion,subdistrict_name_english as subdistrictNameEnglish, minor_version as minorVersion, ");
		sb.append("subdistrict_name_local as subdistrictNameLocal,alias_english as aliasEnglish,alias_local as aliasLocal from subdistrict  where tlc=:subDistrictCode order by subdistrict_version,minor_version");
		
		SQLQuery query = session.createSQLQuery(sb.toString());
		query.setParameter("subDistrictCode",subDistrictCode);
		query.addScalar("subdistrictCode");
		query.addScalar("subdistrictVersion");
		query.addScalar("subdistrictNameEnglish");
		query.addScalar("minorVersion");
		query.addScalar("subdistrictNameLocal");
		query.addScalar("aliasEnglish");
		query.addScalar("aliasLocal");
		
		query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class));
		subdistrictList = query.list();
	} 
	finally{
		if(session != null && session.isOpen())
			session.close();
	}
	return subdistrictList;
	
}

@Override
public List<DraftSubdistrictForm> checkSubdistrictIsEmpty(String partSubdistList,String villageList)throws Exception{
	List<DraftSubdistrictForm> invaliSubdistList=null;
	Session session = null;
	try {
		session = sessionFactory.openSession();
		Query query = session.createSQLQuery(" select * from check_empty_subdistrict(:partSubdistList,:villageList)");
		query.setParameter("partSubdistList", partSubdistList);
		query.setParameter("villageList", villageList);
		String retValue = (String) query.uniqueResult();
		
		Map<Integer, String> uniInvaliSubDist = new HashMap<Integer, String>();
		
		DraftSubdistrictForm tempSubdist=null;
		if (retValue!=null && retValue.contains(",")) {
			
			Scanner scanner = new Scanner(retValue);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				String distDetArr[]=scanner.next().split("@");
				uniInvaliSubDist.put(Integer.parseInt(distDetArr[0]), distDetArr[1]);
			
			}
		}else if (retValue!=null && retValue.length()>0) {
			String distDetArr[]=retValue.split("@");
			uniInvaliSubDist.put(Integer.parseInt(distDetArr[0]), distDetArr[1]);
		}
		
		invaliSubdistList=new ArrayList<DraftSubdistrictForm>();
		for (Map.Entry<Integer, String> obj : uniInvaliSubDist.entrySet()) {
			tempSubdist=new DraftSubdistrictForm();
			tempSubdist.setSubDistrictCode(obj.getKey());
			tempSubdist.setSubdistrictNameEnglish(obj.getValue());
			invaliSubdistList.add(tempSubdist);
	    }


	} finally {
		if(session!=null && session.isOpen())
			session.close();
	}
	return invaliSubdistList;
}


@Override
public boolean subdistInvalFnAfterCreateMulDist(Integer subdistrictCode,Integer userId,Date effectiveDate)throws Exception{
	boolean isInvalidate=false;
	Session session = null;
	try {
		session = sessionFactory.openSession();
		Query query = session.createSQLQuery(" select * from invalidate_subdistrict_without_village_fn(:subdistrictCode,:userId,:effectiveDate)");
		query.setParameter("subdistrictCode", subdistrictCode);
		query.setParameter("userId", userId);
		query.setParameter("effectiveDate", effectiveDate);
		String retValue = (String) query.uniqueResult();
		if(retValue!=null && ("SUCESS").equals(retValue)){
			isInvalidate=true;
		}

	} finally {
		if(session!=null && session.isOpen())
			session.close();
	}
	return isInvalidate;
}
	
}
