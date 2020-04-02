package in.nic.pes.lgd.draft.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.draft.constant.DraftConstant;
import in.nic.pes.lgd.draft.constant.DraftUtils;
import in.nic.pes.lgd.draft.dao.DraftVillageDao;
import in.nic.pes.lgd.draft.form.DraftSubdistrictForm;

@Repository 
public class DraftVillageDaoImpl implements DraftVillageDao{
	
	@Autowired 
	SessionFactory sessionFactory;
	
	@Autowired
	DraftUtils draftUtils;

	@Override
	public List<Village> getDraftVillageList(List<Integer> subdistrictCodeList, List<Integer> villageListFull) throws Exception {
		List<Village> draftVillageList=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			StringBuilder sb=new StringBuilder("select v.village_code as villageCode,v.village_name_english||'-'||v.village_code||'('||t.subdistrict_name_english||')' as villageNameEnglish,");
			sb.append(" case when v.village_code  in (select * from get_draft_used_lb_lr_temp(v.village_code,'V')) then ");
			sb.append(" cast ('F' as character) else cast('A' as character) end as operation_state ");
			sb.append(" from village v left join subdistrict t on v.tlc=t.tlc where v.tlc in(:subdistrictCodeList) and v.isactive and t.isactive and v.vlc not in(:villageListFull) order by v.village_name_english");
			
			SQLQuery query=session.createSQLQuery(sb.toString());
			query.setParameterList("subdistrictCodeList", subdistrictCodeList);
			query.setParameterList("villageListFull", villageListFull);
			query.addScalar("villageCode").addScalar("villageNameEnglish");
			query.addScalar("operation_state");
			query.setResultTransformer(Transformers.aliasToBean(Village.class));
			
			draftVillageList=query.list();
			
			
		}catch(Exception e){
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return draftVillageList;
	}

	@Override
	public List<Village> getEditDraftVillageList(List<Integer> subdistrictCodeList, Integer paramCode,boolean isContribute)throws Exception {
		List<Village> draftVillageList=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			StringBuilder sb=new StringBuilder("select v.village_code as villageCode,v.village_name_english||'('||t.subdistrict_name_english||')' as villageNameEnglish");
			sb.append(",cast ('A' as character) as operation_state from  village  v  left join  subdistrict t on v.tlc=t.tlc where t.isactive and v.isactive  and ");
			sb.append(" t.tlc in(:subdistrictCodeList) and vlc ");
			if(!isContribute){
				sb.append(" not ");
			}
			sb.append(" in(select lb_lr_code from draft_master where lb_lr_type ='V' and draft_temp_id =:paramCode and process_id =:processId) order by v.village_name_english");
			
			
			SQLQuery query=session.createSQLQuery(sb.toString());
			query.setParameter("processId", Integer.parseInt(DraftConstant.DRAFT_OPERATION_CREATE_SUBDISTRICT.toString()));
			query.setParameter("paramCode", paramCode);
			query.setParameterList("subdistrictCodeList", subdistrictCodeList);
			query.addScalar("villageCode").addScalar("villageNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(Village.class));
			
			draftVillageList=query.list();
			
			
		}catch(Exception e){
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return draftVillageList;
	}

	@Override
	public List<Village> getCoverageVillageDetails(DraftSubdistrictForm draftSubdistrictForm) throws Exception {
		List<Village> coverageVillageList=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			StringBuilder sb=new StringBuilder(" select village_code as villageCode,village_name_english as villageNameEnglish,cast('Full' as character varying)coverageType,t.subdistrict_name_english ");
			sb.append(" as partSubdistrict  from village v left join subdistrict t on t.tlc=v.tlc  where v.vlc in(:villageListFull) and v.isactive and t.isactive");
			
			
			SQLQuery query=session.createSQLQuery(sb.toString());
			query.setParameterList("villageListFull", draftUtils.createListFromString(draftSubdistrictForm.getListofVillageFull()));
			query.addScalar("villageCode").addScalar("villageNameEnglish");
			query.addScalar("coverageType");
			query.addScalar("partSubdistrict");
			query.setResultTransformer(Transformers.aliasToBean(Village.class));
			
			coverageVillageList=query.list();
			
			
		}catch(Exception e){
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return coverageVillageList;
	}
	
	
	

}
