package in.nic.pes.lgd.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmc.lgd.localbody.entities.LBTypeDetails;
import com.cmc.lgd.localbody.entities.LBTypeHierarchy;
import com.cmc.lgd.localbody.entities.LocalBodyTable;

import in.nic.pes.lgd.bean.DRAFTWARDCOVERAGE;
import in.nic.pes.lgd.bean.LbCoveredLandregion;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.LocalGovtBodyWard;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.bean.LocalbodyWard;
import in.nic.pes.lgd.bean.WardCoverageDetail;
import in.nic.pes.lgd.bean.localbodywardtemp;
import in.nic.pes.lgd.dao.WardDao;
import in.nic.pes.lgd.forms.WardForm;

@Transactional
@Repository
public class WardDAOImpl  implements WardDao{
	
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<LBTypeHierarchy> getLBHierarchyList(Integer stateCode, String PANCHAYAT_TYPE) throws Exception {
		Session session = null;
		List<LBTypeHierarchy> lbTypeHierarchyList = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Dynamic_Local_body_Hierarchy");
			query.setParameter("stateCode", stateCode).setParameter("localBodyType", PANCHAYAT_TYPE, Hibernate.STRING);
			lbTypeHierarchyList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

		return lbTypeHierarchyList;
	}

	public boolean saveTempWards(List<localbodywardtemp> insertListWard, List<localbodywardtemp> updateListWard, List<localbodywardtemp> deleteListWard) throws Exception {
		Session session = null;
		Transaction tx = null;
		boolean resultFlag = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			if (insertListWard != null && !insertListWard.isEmpty()) {
				for (localbodywardtemp localbodyWard : insertListWard) {
					localbodyWard.setWardCode(0);
					localbodyWard.setWardVersion(0);
					localbodyWard.setMapCode(0);
					localbodyWard.setIsactive(true);
					localbodyWard.setIsdelete(false);
					localbodyWard.setIsupdate(false);
					localbodyWard.setIsnew(true);
					session.save(localbodyWard);
					session.flush();
				}
			}
			if (updateListWard != null && !updateListWard.isEmpty()) {
				for (localbodywardtemp localbodyWard : updateListWard) {
					localbodyWard.setIsdelete(false);
					localbodyWard.setIsupdate(true);
					localbodyWard.setIsnew(false);
					session.save(localbodyWard);
					session.flush();
				}
			}
			if (deleteListWard != null && !deleteListWard.isEmpty()) {
				for (localbodywardtemp localbodyWard : deleteListWard) {
					localbodyWard.setIsdelete(true);
					localbodyWard.setIsupdate(false);
					localbodyWard.setIsnew(false);
					session.save(localbodyWard);
					session.flush();
				}
			}
			tx.commit();
			resultFlag = true;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return resultFlag;
	}

	@SuppressWarnings("unchecked")
	public boolean publishWards(WardForm wardForm) throws Exception {
		Session session = null;
		boolean resultFlag = false;
		try {
			session = sessionFactory.openSession();
			String publisWards = wardForm.getPublishWardsList();
			if(publisWards!=null && publisWards.length()>0 && publisWards.contains(",")) {
				publisWards=publisWards.substring(0, publisWards.length()-1);
				Query query = session.createSQLQuery(" select * from create_or_update_localbody_ward_publish_fn(?)");
				query.setParameter(0, publisWards,Hibernate.STRING);
				resultFlag=(boolean)query.uniqueResult();
			}
			
			String deleteWards = wardForm.getDeleteWardsList();
			
			if (deleteWards.length() > 0) {
				List<Integer> deleteWardList = new ArrayList<Integer>();
				Scanner scanner = new Scanner(deleteWards);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					deleteWardList.add(Integer.parseInt(scanner.next()));
				}
				Query query = session.createQuery("delete from localbodywardtemp  where temp_ward_code in(:deleteWardList)");
				query.setParameterList("deleteWardList", deleteWardList);
				query.executeUpdate();
				session.flush();
				session.clear();
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return resultFlag;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<localbodywardtemp> getLocalbodyWardTempList(Integer lblc)throws Exception{
		Session session=null;
		List<localbodywardtemp> localbodywardtempList=null;
		try{
			session=sessionFactory.openSession();
			Query query=session.createQuery("from localbodywardtemp where isactive=true and lblc=:lblc");
			query.setParameter("lblc", lblc);
			localbodywardtempList=query.list();	
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return 	localbodywardtempList;
	}
	
	@SuppressWarnings("unchecked")
	public List<LocalbodyWard> getLocalbodyWardList(Integer lblc)throws Exception{
		Session session=null;
		List<LocalbodyWard> localbodyWardList=null;
		try{
			session=sessionFactory.openSession();
			Query query=session.createQuery("from LocalbodyWard where isactive=true and lblc=:lblc");
			query.setParameter("lblc", lblc);
			localbodyWardList=query.list();	
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return 	localbodyWardList;
	}
	
	@SuppressWarnings("unchecked")
	public List<LBTypeDetails> getLBTypeDetailsList(Integer stateCode,String PANCHAYAT_TYPE) throws Exception{
		Session session=null;
		List<LBTypeDetails> lbTypeDetailsList=null;
		try{
			session=sessionFactory.openSession();
			Query query = session.getNamedQuery("Urban_Local_body_Type_Details");
			query.setParameter("stateCode", stateCode).setParameter("panchayatType", PANCHAYAT_TYPE, Hibernate.STRING);
			lbTypeDetailsList = query.list();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbTypeDetailsList;
	
	}
	
	public Integer getlblcCodeofLocalbody(Integer localBodyCode) throws Exception{
	Integer lblc=null;
	Session session=null;
	try{
		session=sessionFactory.openSession();
		Query query = session.createSQLQuery("select lblc from localbody where local_body_code =:localBodyCode and isactive");
		query.setParameter("localBodyCode", localBodyCode);
		 lblc=(Integer) query.uniqueResult();
	}finally {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
	return lblc;
 }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyWard> getwardDetail(Integer wardCode) throws Exception {
		Session session = null;
		List<LocalbodyWard> localbodyWardList = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from LocalbodyWard where isactive=true and id.wardCode=:wardCode");
			query.setParameter("wardCode", wardCode);
			localbodyWardList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyWardList;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object[] getLocalbodyWardListbyLoclbodyCode(Integer localBodyCode) throws Exception {
		Session session = null;
		Object[] objectArray=null;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getLocalGovtBodyWardListView");
			query.setParameter("lblc", localBodyCode);
			objectArray=new Object[2];
			 List<LocalGovtBodyWard> localGovtBodyWardList = query.list();
			 objectArray[0]=localGovtBodyWardList;
			 objectArray[1]=localGovtBodyWardList.size();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return objectArray;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkLocalbodtSetupbyState(Integer stateCode,String PANCHAYAT_TYPE)throws Exception{
		Session session = null;
		Boolean isPanchayatType=false;
		try{
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select case when count(*)>0 then true else false end from local_body_setup  where isactive=true and slc =:stateCode and hierarchy_type in(:panchyatTypeList)");
					query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
					List<String> panchyatTypeList=new ArrayList<String>();
					panchyatTypeList.add(PANCHAYAT_TYPE);
					panchyatTypeList.add("M"); //M represent for mixed hierarchy type
					query.setParameterList("panchyatTypeList", panchyatTypeList);
					isPanchayatType = (Boolean) query.uniqueResult();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isPanchayatType;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyWard> getWardDetails(Integer lblc) throws Exception{
		List<LocalbodyWard> localbodyWardList = new ArrayList<LocalbodyWard>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from LocalbodyWard where lblc=:lblc and isactive=true order by wardNameEnglish ");
			query.setParameter("lblc", lblc);
			localbodyWardList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyWardList;

	}
	
	/**
	 * These Methods add for 'Add n Manage Ward Covergae '
	 * 
	 * @author Maneesh Kumar
	 * @since 01DEc2014
	 */
	public Object[] getWardCoverageDetail(Integer lblc, Integer wardCode, String selLevel,boolean isUrban) throws Exception {
		Object[] objectArray = null;
		Session session = null;
		Query query = null;
		boolean fullFlag = false;
		List<WardCoverageDetail> wardCoverageDetailList = null;
		try {
			if(isUrban){
				selLevel="D";
			}
			objectArray = new Object[7];
			session = sessionFactory.openSession();
			query = session.createQuery("from LocalbodyWard w where w.wardCode =:wardCode and w.isactive=true");
			query.setParameter("wardCode", wardCode);
			List<LocalbodyWard> localbodyWardList = query.list();
			if (localbodyWardList != null && !localbodyWardList.isEmpty()) {
				objectArray[0] = localbodyWardList.get(0);
			}
			
				query = session.getNamedQuery("localbodyCoverageDistrictList");
				query.setParameter("lblc", lblc);
				query.setParameter("wardCode", wardCode);
				query.setParameter("level", 'D');
				query.setParameter("listType", 'L');
				query.setParameter("selLevel", selLevel);

				wardCoverageDetailList = query.list();
				objectArray[1] = wardCoverageDetailList;

				query = session.getNamedQuery("wardCoverageDistrictList");
				query.setParameter("lblc", lblc);
				query.setParameter("wardCode", wardCode);
				query.setParameter("level", 'D');
				query.setParameter("listType", 'W');
				query.setParameter("selLevel", selLevel);
				wardCoverageDetailList = query.list();
				objectArray[2] = wardCoverageDetailList;
				if (wardCoverageDetailList != null && !wardCoverageDetailList.isEmpty()) {
					for (WardCoverageDetail wardCoverageDetail : wardCoverageDetailList) {
						if (wardCoverageDetail.getCoverageType()=='P') {
							fullFlag = true;
						}
					}
				}

				if ((selLevel != null && ("I").equals(selLevel.trim())) || (wardCoverageDetailList != null && !wardCoverageDetailList.isEmpty() && fullFlag)) {
					query = session.getNamedQuery("localbodyCoverageSubdistrictList");
					query.setParameter("lblc", lblc);
					query.setParameter("wardCode", wardCode);
					query.setParameter("level", 'T');
					query.setParameter("listType", 'L');
					query.setParameter("selLevel", selLevel);
				
					wardCoverageDetailList = query.list();
					objectArray[3] = wardCoverageDetailList;

					query = session.getNamedQuery("wardCoverageSubdistrictList");
					query.setParameter("lblc", lblc);
					query.setParameter("wardCode", wardCode);
					query.setParameter("level", 'T');
					query.setParameter("listType", 'W');
					query.setParameter("selLevel", selLevel);
					wardCoverageDetailList = query.list();
					objectArray[4] = wardCoverageDetailList;

				} else {
					objectArray[3] = null;
					objectArray[4] = null;
				}
				fullFlag = false;
				if (wardCoverageDetailList != null && !wardCoverageDetailList.isEmpty()) {
					for (WardCoverageDetail wardCoverageDetail : wardCoverageDetailList) {
						if (wardCoverageDetail.getCoverageType()=='P') {
							fullFlag = true;
						}
					}
				}

				if ((selLevel != null && ("V").equals(selLevel.trim())) || (wardCoverageDetailList != null && !wardCoverageDetailList.isEmpty() && fullFlag)) {
					query = session.getNamedQuery("localbodyCoverageVillageList");
					query.setParameter("lblc", lblc);
					query.setParameter("wardCode", wardCode);
					query.setParameter("level", 'V');
					query.setParameter("listType", 'L');
					query.setParameter("selLevel", selLevel);
					wardCoverageDetailList = query.list();
					objectArray[5] = wardCoverageDetailList;

					query = session.getNamedQuery("wardCoverageVillageList");
					query.setParameter("lblc", lblc);
					query.setParameter("wardCode", wardCode);
					query.setParameter("level", 'V');
					query.setParameter("listType", 'W');
					query.setParameter("selLevel", selLevel);
					wardCoverageDetailList = query.list();
					objectArray[6] = wardCoverageDetailList;
				} else {
					objectArray[5] = null;
					objectArray[6] = null;
				}

		
		  	
			
		
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return objectArray;
	}

	@Override
	public List<WardCoverageDetail> getWardCoverageDetailbyLevel(Integer lblc, Integer wardCode, Character level, String entityCodeList, boolean wardCovergaeFlag)throws Exception {
		Session session = null;
		Query query = null;
		List<WardCoverageDetail> wardCoverageDetailList = null;

		try {
			session = sessionFactory.openSession();
			switch (level) {
			case 'D':
				query = session.getNamedQuery("localbodyCoverageDistrictList");
				query.setParameter("lblc", lblc);
				query.setParameter("wardCode", wardCode);
				break;
			case 'T':
				if (wardCovergaeFlag) {
					query = session.getNamedQuery("wardCoverageSubdistrictListbyDistrict");
					query.setParameter("lblc", lblc);
					query.setParameter("wardCode", wardCode);
					query.setParameter("selLevel", 'T');
					query.setParameter("listType", 'W');
					if (entityCodeList != null) {
						StringBuilder entityList = new StringBuilder();
						Scanner scanner = new Scanner(entityCodeList);
						scanner.useDelimiter("@@");
						String entityCode;
						while (scanner.hasNext()) {
							entityCode = scanner.next();
							if (!(entityCode.indexOf("Full") > 0)) {
								entityList.append((Integer.parseInt(entityCode.substring(0, entityCode.indexOf("_")))) + ",");

							}
						}
						if (entityList != null && entityList.lastIndexOf(",") > 0) {
							query.setParameter("entityList", entityList.substring(0, entityList.lastIndexOf(",")));
						}
					}
					wardCoverageDetailList = query.list();
				} else {

					query = session.getNamedQuery("localbodyCoverageSubdistrictListbyDistrict");
					query.setParameter("lblc", lblc);
					query.setParameter("wardCode", wardCode);
					query.setParameter("selLevel", 'T');
					query.setParameter("listType", 'L');

					if (entityCodeList != null && !("").equals(entityCodeList)) {
						StringBuilder entityList = new StringBuilder();
						String entityCode;
						Scanner scanner = new Scanner(entityCodeList);
						scanner.useDelimiter("@@");
						while (scanner.hasNext()) {
							entityCode = scanner.next();
							if (!(entityCode.indexOf("Full") > 0)) {
								entityList.append((Integer.parseInt(entityCode.substring(0, entityCode.indexOf("_")))) + ",");

							}
						}
						if (entityList != null && entityList.lastIndexOf(",") > 0) {
							query.setParameter("entityList", entityList.substring(0, entityList.lastIndexOf(",")));
						}
					}
					wardCoverageDetailList = query.list();

				}
				break;
			case 'V':
				if (wardCovergaeFlag) {
					query = session.getNamedQuery("wardCoverageVillageListbySubdistrict");
					query.setParameter("lblc", lblc);
					query.setParameter("wardCode", wardCode);
					query.setParameter("selLevel", 'V');
					query.setParameter("listType", 'W');
					if (entityCodeList != null && !("").equals(entityCodeList)) {
						StringBuilder entityList = new StringBuilder();
						String entityCode;
						Scanner scanner = new Scanner(entityCodeList);
						scanner.useDelimiter("@@");
						while (scanner.hasNext()) {
							entityCode = scanner.next();
							if (!(entityCode.indexOf("Full") > 0)) {
								entityList.append((Integer.parseInt(entityCode.substring(0, entityCode.indexOf("_")))) + ",");

							}
						}
						if (entityList != null && entityList.lastIndexOf(",") > 0) {
							query.setParameter("entityList", entityList.substring(0, entityList.lastIndexOf(",")));
						}
					}
					wardCoverageDetailList = query.list();
				} else {

					query = session.getNamedQuery("localbodyCoverageVillageListbySubdistrict");
					query.setParameter("lblc", lblc);
					query.setParameter("wardCode", wardCode);
					query.setParameter("selLevel", 'V');
					query.setParameter("listType", 'L');

					if (entityCodeList != null && !("").equals(entityCodeList)) {
						StringBuilder entityList = new StringBuilder();
						String entityCode;
						Scanner scanner = new Scanner(entityCodeList);
						scanner.useDelimiter("@@");
						while (scanner.hasNext()) {
							entityCode = scanner.next();
							if (!(entityCode.indexOf("Full") > 0)) {
								entityList.append((Integer.parseInt(entityCode.substring(0, entityCode.indexOf("_")))) + ",");

							}
						}
						if (entityList != null && entityList.lastIndexOf(",") > 0) {
							query.setParameter("entityList", entityList.substring(0, entityList.lastIndexOf(",")));
						}
					}
					wardCoverageDetailList = query.list();

				}

			}

		}catch(Exception e) {
			e.printStackTrace();
		
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return wardCoverageDetailList;
	}
	
	
	

	@Override
	public boolean saveWardCoverageDetail(Integer wardCode, String selLevel, Object[] wardCoverageDistrictList,
			Object[] wardCoverageSubdistrictList, Object[] wardCoverageVillageList, Integer userId,String category) throws Exception{	
		Session session = null;
		Query query = null;
		Boolean flag=false;
		try {
			
			session = sessionFactory.openSession();
			query = session.createQuery("from LocalbodyWard w where w.wardCode =:wardCode and w.isactive=true");
			query.setParameter("wardCode", wardCode);
			List<LocalbodyWard> localbodyWardList = query.list();
			LocalbodyWard localbodyWard = localbodyWardList != null ? localbodyWardList.get(0) : null;

			List<String> draftList=new ArrayList<String>();
			
			
			if (localbodyWard != null) {
				
				if (wardCoverageDistrictList != null && selLevel != null && (("D").equals(selLevel) || ("U").equals(category) )) {
					for (int i = 0; i < wardCoverageDistrictList.length; i++) {
						if (wardCoverageDistrictList[i] != null) {
							String[] coverageColum = wardCoverageDistrictList[i].toString().split("_");
							draftList.add("D_"+coverageColum[0]+"_"+coverageColum[1].charAt(0));
							
						}
					}
				}

				if (wardCoverageSubdistrictList != null && selLevel != null && (("D").equals(selLevel) || ("I").equals(selLevel))) {
					for (int i = 0; i < wardCoverageSubdistrictList.length; i++) {
						if (wardCoverageSubdistrictList[i] != null) {
							if(wardCoverageSubdistrictList[i].toString().indexOf("_")>0) {
								String[] coverageColum = wardCoverageSubdistrictList[i].toString().split("_");
								draftList.add("T_"+coverageColum[0]+"_"+coverageColum[1].charAt(0));
							}
							else {
								draftList.add("T_"+wardCoverageSubdistrictList[i].toString()+"_F");
							}
							
						}
					}
				}

				if (wardCoverageVillageList != null && selLevel != null && (("D").equals(selLevel) || ("I").equals(selLevel) || ("V").equals(selLevel))) {
					for (int i = 0; i < wardCoverageVillageList.length; i++) {
						if (wardCoverageVillageList[i] != null) {
							if(wardCoverageVillageList[i].toString().indexOf("_")>0) {
								String[] coverageColum = wardCoverageVillageList[i].toString().split("_");
								draftList.add("V_"+coverageColum[0]+"_"+coverageColum[1].charAt(0));
							}
							else {
								draftList.add("V_"+wardCoverageVillageList[i].toString()+"_F");
							}
							
						}
					}
				}
				
				String draftString = draftList.toString().replaceAll("\\s+"," ");
				query = session.createSQLQuery("select * from create_or_update_ward_coverage_Draft(:wardCode, :wardVersion, :draftList ,:userId )");
				query.setParameter("wardCode", wardCode);
				query.setParameter("wardVersion", localbodyWard.getWardVersion());
				query.setParameter("draftList",draftString);
				query.setParameter("userId", userId);
				flag = (Boolean) query.uniqueResult();
				session.flush();
				session.clear();
				
				

			}
			
			return flag;
		
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
	}

	/**
	 * End of 'Add n Manage Ward Covergae ' methods.
	 */
	
	@Override
	public Object[] getBasicDetailofLocalbody(Integer localBodyCode,String PANCHAYAT_TYPE) throws Exception{
		Session session=null;
		Object[] objectArray=null;
		try{
			session=sessionFactory.openSession();
			LocalBodyTable lbEntiity = new LocalBodyTable();
			
			Criteria criteria = session.createCriteria(LocalBodyTable.class);
			criteria.add(Restrictions.eq("localBodyCode", localBodyCode));
			criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
			lbEntiity = (LocalBodyTable) criteria.uniqueResult();
			
			
			/*
			 * Query query = session.
			 * createQuery("from Localbody where isactive=true and localBodyCode=:localBodyCode"
			 * ); query.setParameter("localBodyCode", localBodyCode); List<Localbody>
			 * localbodyList= query.list();
			 */
			
			if(lbEntiity!=null ){
				objectArray=new Object[3];
				objectArray[0]=lbEntiity.getLblc();
				Query query = session.createQuery("from LocalBodyType where isactive=true and localBodyTypeCode=:localBodyTypeCode");
				query.setParameter("localBodyTypeCode", lbEntiity.getLocalBodyTypeCode());
				List<LocalBodyType> localBodyTypeList= query.list();
				if(localBodyTypeList!=null && !localBodyTypeList.isEmpty()){
					objectArray[1]=localBodyTypeList.get(0).getLevel();
					if(("U").equals(PANCHAYAT_TYPE)){
						query=session.createQuery(" from LbCoveredLandregion  where isactive=true and lbCoveredRegionCode=:lbCoveredRegionCode");
						query.setParameter("lbCoveredRegionCode", lbEntiity.getLbCoveredRegionCode());
						List<LbCoveredLandregion> lbCoveredLandregionList=query.list();
						StringBuffer URBAN_LEVEL=null;
						if(lbCoveredLandregionList!=null && !lbCoveredLandregionList.isEmpty()){
							URBAN_LEVEL=new StringBuffer();
							for(LbCoveredLandregion lbCoveredLandregion:lbCoveredLandregionList){
								URBAN_LEVEL.append(String.valueOf(lbCoveredLandregion.getLandRegionType())+"|");
							}
						}
						
						objectArray[2]=URBAN_LEVEL!=null?URBAN_LEVEL.toString():null;
					}
					
					
					
					
				}
				
				
			}
			
			
					
		
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return objectArray;
	 }

	
	
	
	@Override
	public List<LocalbodyWard> getDeleteWardList(Integer lblc) throws Exception {
		Session session=null;
		List<LocalbodyWard> localbodyWardList=null;
		try{
			session=sessionFactory.openSession();
			// delete ward flag code is added
			List<Integer> deleteFlagCode=new ArrayList<Integer>();
			List<Integer> activeWardCode=new ArrayList<Integer>();
			
			
			Query query=session.createSQLQuery("select f.flag_code from flags f where (f.flag_description ilike '%Delete Ward (PRI)%' or " + 
				"f.flag_description ilike '%Delete Ward (TRADITIONAL)%' or f.flag_description ilike '%Delete Ward (URBAN)%')");
			 deleteFlagCode=query.list();
			 session.flush();
			 session.clear();
			 activeWardCode.add(-1);
			 query=session.createSQLQuery("select ward_code from localbody_ward where lblc=:lblc and isactive");
			 query.setParameter("lblc", lblc);
			 activeWardCode=query.list();
			 activeWardCode.add(-1);
			 session.flush();
			 session.clear();
			 query=session.createQuery("from LocalbodyWard where isactive=false and lblc=:lblc and flagCode in(:deleteFlagCode)"
			 		+ "and wardCode not in(:activeWardCode) ");
			 query.setParameterList("deleteFlagCode", deleteFlagCode);
			 query.setParameterList("activeWardCode", activeWardCode);
			 query.setParameter("lblc", lblc);
			localbodyWardList=query.list();	
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return 	localbodyWardList;
	}

	@Override
	public String restoreDeleteWard(Integer wardCode,Integer wardVersion,Integer userId) throws Exception {
		Session session=null;
		String restoreFlag="E";
		Query query = null;
		try{
			session=sessionFactory.openSession();
			query = session.createSQLQuery("select * from reactive_ward_fn(:wardCode, :wardVersion,:userId)");
			query.setParameter("wardCode", wardCode);
			query.setParameter("wardVersion", wardVersion);
			query.setParameter("userId", userId);
			Character flag = (Character) query.uniqueResult();
			restoreFlag=flag.toString();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return 	restoreFlag;
	}

	@Override
	public boolean checkWardDuplicateInDraft(Integer lblc, String wardNameEnglish) throws Exception {
		Session session=null;
		Boolean duplicateFlag=false;
		try{
			session=sessionFactory.openSession();
			StringBuilder sb=new StringBuilder("select case when count(1)>0 then true else false end from localbody_ward_temp ");
			sb.append(" where lblc =:lblc and isactive  and ward_name_english ilike any(String_To_Array(:wardNameEnglish,'@'))");
			
	   	Query query=session.createSQLQuery(sb.toString());
	   	query.setParameter("lblc", lblc);
	   	query.setParameter("wardNameEnglish", wardNameEnglish);
	   	duplicateFlag=(Boolean) query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return duplicateFlag;
	}

	@Override
	public boolean checkWardCoverageExistinLocalbody(Integer lblc) throws Exception {
		Session session=null;
		Boolean duplicateFlag=true;
		try{
			session=sessionFactory.openSession();
			Query query=session.createSQLQuery("select case when count(1)>0 then true else false end  from  localbody l inner join  lb_covered_landregion lbc on l.lb_covered_region_code="
					+ "lbc.lb_covered_region_code where l.isactive and lbc.isactive and l.lblc=:lblc ");
			query.setParameter("lblc", lblc);
			duplicateFlag=(Boolean) query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return duplicateFlag;
	}
	
	@Override
	public List<LocalbodyWard> fetchDraftWardCoverage(Integer lblc) throws Exception{
		Session session = null;
		Query query = null;
		List<LocalbodyWard> coveredWardLandregionList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("fetchDraftWardCoverage");
			query.setParameter("lblc", lblc);
			coveredWardLandregionList = query.list();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return coveredWardLandregionList;
	
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public boolean publishWardCoverage(WardForm wardForm) throws Exception {
		Session session = null;
		boolean resultFlag = false;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			String publisWards = wardForm.getPublishWardsList();
			String deleteWards = wardForm.getDeleteWardsList();
			
			query = session.createSQLQuery("select * from create_or_update_ward_coverage_Publish(:pubWardCoverage) ;");
			query.setParameter("pubWardCoverage", publisWards.length()>0?publisWards:deleteWards);
			resultFlag = (Boolean) query.uniqueResult();
			session.flush();
			session.clear();
			
		
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return resultFlag;
	}

	@Override
	public boolean checkWardDuplicateInDeletedWard(Integer lblc, String wardNameEnglish) throws Exception {
		Session session=null;
		Boolean duplicateFlag=false;
		try{
			session=sessionFactory.openSession();
			StringBuilder sb=new StringBuilder("select case when count(1)>0 then true else false end  from localbody_ward lw inner join flags "
					+ " f on lw.flag_code=f.flag_code and (f.flag_description ilike '%Delete Ward (PRI)%' or f.flag_description "
					+ "ilike '%Delete Ward (TRADITIONAL)%' or f.flag_description ilike '%Delete Ward (URBAN)%') where lw.lblc=:lblc and  "
					+ "btrim(ward_name_english,' ') ilike btrim(:wardNameEnglish,' ')");
			
	   	Query query=session.createSQLQuery(sb.toString());
	   	query.setParameter("lblc", lblc);
	   	if(wardNameEnglish!=null && wardNameEnglish.indexOf("@")>0) {
	   		wardNameEnglish=wardNameEnglish.substring(0, wardNameEnglish.length()-1);
	   	}
	   	query.setParameter("wardNameEnglish", wardNameEnglish);
	   	duplicateFlag=(Boolean) query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return duplicateFlag;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public boolean saveLocalNameOfWard(String arrayupdate ,Integer userCode) throws Exception {
		Session session = null;
		boolean resultFlag = false;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			String arrayupdateList = arrayupdate;
			Integer userCodeConvert = userCode;
	     	query = session.createSQLQuery("select * from change_ward_name_local_fn(:arrayupdateList ,:userCodeConvert) ;");
			query.setParameter("arrayupdateList", arrayupdateList);
		    query.setParameter("userCodeConvert", userCodeConvert);
		
			resultFlag = (Boolean) query.uniqueResult();
			session.flush();
			session.clear();
			
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return resultFlag;
	}

	@Override
	public List<DRAFTWARDCOVERAGE> getDraftWardCoverageDetails(Integer wardCode, Integer wardVersion,boolean isDraft) {
		Session session = null;
		Query query = null;
		List<DRAFTWARDCOVERAGE> coveredWardLandregionList = null;
		 String queryName="GET_WARD_COVERAGE";
		if(isDraft) {
			queryName="GET_DRAFT_WARD_COVERAGE";
		}
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery(queryName);
			query.setParameter("wardCode", wardCode);
			query.setParameter("wardVersion", wardVersion);
			coveredWardLandregionList = query.list();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return coveredWardLandregionList;
	}
	
	
	
}



