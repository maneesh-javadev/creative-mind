package in.nic.pes.lgd.draft.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.draft.constant.DraftConstant;
import in.nic.pes.lgd.draft.dao.DraftDistrictDao;

@Repository
public class DraftDistrictDaoImpl implements DraftDistrictDao {

	@Autowired 
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<District> getDraftDistrictList(Integer entityCode,String entityType) throws Exception {
		List<District> draftDistrictList=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			StringBuilder sb=new StringBuilder("select d.district_code as districtCode, d.district_name_english as districtNameEnglish,");
			sb.append("case when d.district_code  in (select * from get_draft_used_lb_lr_temp(d.district_code,'D')) then  cast ('F' as character) ");
			sb.append(" else cast('A' as character) end as operation_state from district d  where d.isactive and ");
			if(DraftConstant.ENTITY_TYPE_STATE.toString().equals(entityType)){
				sb.append(" d.slc=:entityCode order by d.district_name_english");
			}else{
				sb.append(" d.dlc=:entityCode ");
			}
			SQLQuery query=session.createSQLQuery(sb.toString());
			query.setParameter("entityCode", entityCode);
			query.addScalar("districtCode").addScalar("districtNameEnglish").addScalar("operation_state");
			query.setResultTransformer(Transformers.aliasToBean(District.class));
			draftDistrictList = query.list();
		}catch(Exception e){
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return draftDistrictList;
	}

	
	public String getDistrictNameEng(Integer districtCode)throws Exception{
		Session session=null;
		String districtNameEng=null;
		try{
			session=sessionFactory.openSession();
			Query query=session.createSQLQuery("select district_name_english from district where district_code=:districtCode and isactive");
			query.setParameter("districtCode", districtCode);
			districtNameEng=(String)query.uniqueResult();
		
		}finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return districtNameEng;
	}
	
	
	

}
