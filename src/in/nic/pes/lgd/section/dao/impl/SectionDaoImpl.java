package in.nic.pes.lgd.section.dao.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.ast.tree.SelectExpression;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.section.dao.SectionDao;
import in.nic.pes.lgd.section.form.SectionForm;
import in.nic.pes.lgd.section.rule.InserSectionFn;
import in.nic.pes.lgd.section.rule.Section;
import in.nic.pes.lgd.section.rule.SectionConstant;
import in.nic.pes.lgd.section.rule.SectionPK;

/**
 * 
 * @author Maneesh Kumar 
 * @since 10Apr2016
 *
 */
@Repository
public class SectionDaoImpl implements SectionDao{

	private static final Logger log = Logger.getLogger(SectionDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public String saveSectionDetails(SectionForm sectionForm) throws Exception {
		Session session=null;
		String returnedValue=null;
		try{
			session=sessionFactory.openSession();
			if((SectionConstant.IS_CENTER.toString().equals(sectionForm.getIsCenterorstate()))){
				if( (SectionConstant.ENTITY_SPECIFIC.toString()).equals(sectionForm.getParentChildFlg())){
					sectionForm.setParentCode(sectionForm.getSelectedOrgList());
				}
				sectionForm.setParentType(SectionConstant.PARENT_TYPE_ORGANIZATION.toString());
			}else if((SectionConstant.PARENT_TYPE_LOCALBODY.toString()).equals(sectionForm.getParentType()) && (SectionConstant.ENTITY_SPECIFIC.toString()).equals(sectionForm.getParentChildFlg())){
				sectionForm.setParentCode(sectionForm.getSelectedLbList());
			}else if((SectionConstant.PARENT_TYPE_ORGANIZATION.toString()).equals(sectionForm.getParentType()) && (SectionConstant.ENTITY_SPECIFIC.toString()).equals(sectionForm.getParentChildFlg())){
				sectionForm.setParentCode(sectionForm.getSelectedOrgList());
			}
			
			InserSectionFn inserSectionFn= (InserSectionFn) executeInsertSectionFunction(session, sectionForm);
			returnedValue=inserSectionFn.getCreateSection();
		}catch(Exception e){
			returnedValue=null;
			StringBuilder sb=new StringBuilder("select * from create_section(");
			sb.append(sectionForm.getSectionNameEnglish()+","); 
			              sb.append(sectionForm.getParentCode()+","); 
			              sb.append(sectionForm.getParentType()+","); 
			              sb.append(sectionForm.getParentChildFlg()+","); 
			              sb.append(sectionForm.getEffectiveDate()+","); 
			              sb.append(sectionForm.getSlc()+","); 
			              sb.append(sectionForm.getUserId()+","); 
			              sb.append(sectionForm.getSectionNameLocal()+","); 
			              sb.append(sectionForm.getAddress1()+","); 
			              sb.append(sectionForm.getAddress2()+","); 
			              sb.append(sectionForm.getAddress3()+","); 
			              sb.append( sectionForm.getPinCode()+","); 
			              sb.append(sectionForm.getLocalAddress1()+","); 
			              sb.append(sectionForm.getLocalAddress2()+","); 
			              sb.append(sectionForm.getLocalAddress3()+",");
			              sb.append(sectionForm.getSectionShortName()+");"); 
			             
			              log.error("Exception Section Dao#saveSectionDetails-->" + e+"/n Insert Section query--> "+sb.toString());
			              throw e;
		}
		finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return returnedValue;
	}
	
	/**
	 * 
	 * @param session
	 * @param localBodyForm
	 * @return
	 */
	private Object executeInsertSectionFunction (Session session, SectionForm sectionForm){
		Query query = session.getNamedQuery("Insert_Section_Details")
					.setParameter("section_name_english", sectionForm.getSectionNameEnglish(), Hibernate.STRING)
					.setParameter("parent_codes"	    ,sectionForm.getParentCode(), Hibernate.STRING)
					.setParameter("parent_type"         ,sectionForm.getParentType(), Hibernate.STRING)
					.setParameter("parent_child_flg"    ,sectionForm.getParentChildFlg(), Hibernate.STRING)
					.setParameter("p_effective_date"    ,sectionForm.getEffectiveDate(), Hibernate.DATE)
					.setParameter("slc"			        ,sectionForm.getSlc(), Hibernate.INTEGER)
					.setParameter("userid"				, sectionForm.getUserId(), Hibernate.INTEGER) 
					.setParameter("section_name_local"	, sectionForm.getSectionNameLocal(), Hibernate.STRING)
					.setParameter("address1"			, sectionForm.getAddress1(), Hibernate.STRING)
					.setParameter("address2"			, sectionForm.getAddress2(), Hibernate.STRING)
					.setParameter("address3"			,sectionForm.getAddress3(), Hibernate.STRING)
					.setParameter("pin_code"			, sectionForm.getPinCode(), Hibernate.INTEGER)
					.setParameter("localaddress1"		, sectionForm.getLocalAddress1(), Hibernate.STRING)
					.setParameter("localaddress2"		, sectionForm.getLocalAddress2(), Hibernate.STRING)
					.setParameter("localaddress3"		,sectionForm.getLocalAddress3(), Hibernate.STRING)
					.setParameter("sectionShortName"	,sectionForm.getSectionShortName(), Hibernate.STRING);
					 
		Object returnedValue =  query.uniqueResult();
		return returnedValue;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Section> getSectionList(Integer parentCode,String parentType)throws Exception{
		List<Section> sectionList=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Query query=session.createQuery("from Section where parentType=:parentType and parentCode=:parentCode and isactive=true order by sectionNameEnglish");
			query.setParameter("parentType", parentType);
			query.setParameter("parentCode", parentCode);
			sectionList=query.list();
		}catch(Exception e){
			log.error("Exception Section Dao#getSectionList-->" + e);
			throw e;
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return sectionList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Section> getSectionListbyId(Integer sectionCode)throws Exception{
		List<Section> sectionList=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Query query=session.createQuery("from Section where sectionPK.sectionCode=:sectionCode and isactive=true order by sectionNameEnglish");
			query.setParameter("sectionCode", sectionCode);
			sectionList=query.list();
		}catch(Exception e){
			log.error("Exception Section Dao#getSectionListbyId-->" + e);
			throw e;
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return sectionList;
	}
	
	@Override 
	public boolean sectionNameUniquewithParent(String sectionNameEng,Integer parentCode,String parentType)throws Exception{
		boolean uniqueFlag=false;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Query query=session.createSQLQuery("select * from get_unique_section(:sectionNameEng,:parentCode,:parentType)");
			query.setParameter("sectionNameEng", sectionNameEng);
			query.setParameter("parentCode", parentCode);
			query.setParameter("parentType", parentType);
			uniqueFlag=(boolean)query.uniqueResult();
		}catch(Exception e){
			log.error("Exception Section Dao#sectionNameUniquewithParent-->" + e);
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
	public boolean updateSectionNameEnglish(SectionForm sectionForm)throws Exception{
		
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();
			
			transaction=session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Section.class);
			SectionPK sectionPK=new SectionPK();
			sectionPK.setSectionCode(sectionForm.getSectionCode());
			sectionPK.setSectionVersion(sectionForm.getSectionVersion());
			
			
			criteria.add(Restrictions.eq("sectionPK",sectionPK));
			Section prviousSectionObj = (Section)criteria.uniqueResult();
			
			Section newSectionObj=new Section();
			BeanUtils.copyProperties(newSectionObj, prviousSectionObj);
			newSectionObj.getSectionPK().setSectionCode(sectionForm.getSectionCode());
			newSectionObj.getSectionPK().setSectionVersion(sectionForm.getSectionVersion()+1);
			newSectionObj.setSectionNameEnglish(sectionForm.getSectionNameEnglishChange());
			newSectionObj.setLastupdatedby(sectionForm.getUserId().longValue());
			newSectionObj.setLastupdated(sectionForm.getEffectiveDate());
			newSectionObj.setFlagCode(Integer.parseInt(SectionConstant.SECTION_UPDATE_FLAG_CODE.toString()));
			session.saveOrUpdate(newSectionObj);
			session.flush();
			
			prviousSectionObj.setIsactive(Boolean.FALSE);
			prviousSectionObj.getSectionPK().setSectionVersion(sectionForm.getSectionVersion());
			session.saveOrUpdate(prviousSectionObj);
			session.flush();
			
			transaction.commit();
			
		}catch(Exception e){
			transaction.rollback();
			log.error("Exception Section Dao#updateSectionNameEnglish-->" + e);
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
	public boolean deleteSection(Integer sectionCode) throws Exception{
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			List<Section> sectionList=this.getSectionListbyId(sectionCode);
			if(sectionList!=null && !sectionList.isEmpty()){
				Section section=sectionList.get(0);
				section.setIsactive(Boolean.FALSE);
				session.update(section);
				session.flush();
				transaction.commit();
				return true;
			}
			
		}catch(Exception e){
			transaction.rollback();
			log.error("Exception Section Dao#deleteSection-->" + e);
			throw e;
			
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return false;
	}
	
	@Override
	public  Object[] getManageSectionObject(Integer parentCode,String parentType)throws Exception{
		Session session=null;
		Object[] manageSectionObject=null;
		try{
			manageSectionObject=new Object[2];
			session=sessionFactory.openSession();
			
			Query query=session.createQuery("from Section where parentType=:parentType and parentCode=:parentCode and isactive=true order by sectionNameEnglish");
			query.setParameter("parentType", parentType);
			query.setParameter("parentCode", parentCode);
			manageSectionObject[0]=query.list();
			
			StringBuilder sb=new StringBuilder("select a.section_code as sectionCode,a.section_version as sectionVersion,a.section_name_english as sectionNameEnglish,a.section_name_local as sectionNameLocal");
		    sb.append(" from section a, (select section_code,max(section_version) as maxversion from section where parent_code=:parentCode and parent_type=:parentType group by section_code) b ");
		    sb.append(" where a.section_code=b.section_code and a.section_version=b.maxversion and a.isactive=false");
			SQLQuery sqlQuery=session.createSQLQuery(sb.toString());
			sqlQuery.setParameter("parentCode", parentCode);
			sqlQuery.setParameter("parentType", parentType);
			sqlQuery.addScalar("sectionCode");
			sqlQuery.addScalar("sectionVersion");
			sqlQuery.addScalar("sectionNameEnglish");
			sqlQuery.addScalar("sectionNameLocal");
			sqlQuery.setResultTransformer(Transformers.aliasToBean(Section.class));
			manageSectionObject[1]=sqlQuery.list();
		}catch(Exception e){
			log.error("Exception Section Dao#getManageSectionObject-->" + e);
			throw e;
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return manageSectionObject;
	}
	
	@Override
	public boolean reActiveSection(SectionForm sectionForm)throws Exception{
		Session session=null;
		Transaction transaction = null;
		boolean isReactivated=false;
		try{
			session=sessionFactory.openSession();
			
			transaction=session.beginTransaction();
			SectionPK sectionPK=new SectionPK();
			sectionPK.setSectionCode(sectionForm.getSectionCode());
			sectionPK.setSectionVersion(sectionForm.getSectionVersion());
			Section  reActiveSection = (Section) session.load(Section.class,sectionPK);
			reActiveSection.setIsactive(Boolean.TRUE);
			session.update(reActiveSection);
			session.flush();
			transaction.commit();
			isReactivated= true;
			
		}catch(Exception e){
			log.error("Exception Section Dao#reActiveSection-->" + e);
			throw e;
			
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return isReactivated;
		
	}

}
