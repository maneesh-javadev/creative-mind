package in.nic.pes.lgd.restructure.reporting.dao.impl;

import in.nic.pes.lgd.bean.BlockWiseVillagesAndUlb;
import in.nic.pes.lgd.loggers.LGDLogger;
import in.nic.pes.lgd.restructure.reporting.dao.ViewEntityDetailsDao;
import in.nic.pes.lgd.restructure.reporting.entities.GenericReportingEntity;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ViewEntityDetailsDaoImpl implements ViewEntityDetailsDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<GenericReportingEntity> getViewStateDetails() throws HibernateException {
		Session session = null;
		List<GenericReportingEntity> districtlist = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("globalViewForStateReport");
			districtlist = query.list();
		} catch (HibernateException e) {
			LGDLogger.getLogger(ViewEntityDetailsDaoImpl.class).error(e.toString());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtlist;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GenericReportingEntity> getViewEntityWiseDetails(Integer stateCode, String ENTITY_TYPE, Integer paramEntityCode, String paramEntityName) throws HibernateException {
		Session session = null;
		List<GenericReportingEntity> districtlist = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("globalViewEntityGenericReports")
						   .setParameter("statecode", stateCode, Hibernate.INTEGER)
						   .setParameter("entitytype", ENTITY_TYPE, Hibernate.CHARACTER)
						   .setParameter("entityCode", paramEntityCode, Hibernate.INTEGER)
						   .setParameter("entityName", paramEntityName, Hibernate.STRING);
			districtlist = query.list();
		} catch (HibernateException e) {
			LGDLogger.getLogger(ViewEntityDetailsDaoImpl.class).error(e.toString());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GenericReportingEntity> getViewParentWiseDetails(String ENTITY_TYPE, Integer stateCode,Integer districtCode,Integer subDistrictCode) throws HibernateException {
		Session session = null;
		List<GenericReportingEntity> entityList = new LinkedList<GenericReportingEntity>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("globalViewForHierarchyGenericReports")
						   .setParameter("parentEntityType", ENTITY_TYPE, Hibernate.STRING)
						   .setParameter("parentStateCode", stateCode, Hibernate.INTEGER)
						   .setParameter("parentDistrictCode", districtCode, Hibernate.INTEGER)
						   .setParameter("parentSubDistrictCode", subDistrictCode, Hibernate.INTEGER);
			entityList = query.list();
		} catch (HibernateException e) {
			LGDLogger.getLogger(ViewEntityDetailsDaoImpl.class).error(e.toString());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return entityList;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BlockWiseVillagesAndUlb> getBlockwiseandULBDetails(Integer blockCode) throws HibernateException {
		Session session = null;
		List<BlockWiseVillagesAndUlb> blockWiseVillagesAndUlbList = new LinkedList<BlockWiseVillagesAndUlb>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getBlockWiseVillagesNUlb")
						   .setParameter("blc", blockCode, Hibernate.INTEGER);
			blockWiseVillagesAndUlbList = query.list();
		} catch (HibernateException e) {
			LGDLogger.getLogger(ViewEntityDetailsDaoImpl.class).error(e.toString());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return blockWiseVillagesAndUlbList;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	
	public List<Object[]> getVillageStatusWiseDetails(String ENTITY_TYPE, Integer stateCode,Integer districtCode,Integer subDistrictCode,Integer paramEntityCode,String paramEntityName,String searchCriteriaType) throws HibernateException{
		Session session = null;
		Query query1=null;
		
		String sqlQuery = null;
		List<Object[]> villagestatuslist = null;
		try {
			session = sessionFactory.openSession();
			if("P".equalsIgnoreCase(searchCriteriaType))
			{
				sqlQuery = "select w.entity_code,v.village_version,v.minor_version,w.entity_name_english,w.entity_name_local,w.entity_hierarchy,w.census_2001_code,w.census_2011_code," + 
						"(case v.village_status when 'I' then 'Inhabitant' when 'R' then 'Forest' when 'U' then 'Un-Inhabitant' end) as village_status ," + 
						"(case pl.pesa_type when 'P' then 'Partially Covered' when 'F' then 'Fully Covered' when 'N' then 'Not Covered' end) as pesa_status,w.file_timestamp from  "+ 
						" get_parent_land_region_wise_entity_details_fn2(:parentEntityType, :parentStateCode,:parentDistrictCode,:parentSubDistrictCode) w,village v,pesa_landregion pl "+ 
						" where w.entity_code=v.village_code and w.entity_version=v.village_version and v.isactive and v.vlc=pl.lrlc and   land_region_type ='V' ";
				
			
			query1 = session.createSQLQuery(sqlQuery)
					.setParameter("parentEntityType", ENTITY_TYPE, Hibernate.STRING)
					   .setParameter("parentStateCode", stateCode, Hibernate.INTEGER)
					   .setParameter("parentDistrictCode", districtCode, Hibernate.INTEGER)
					   .setParameter("parentSubDistrictCode", subDistrictCode, Hibernate.INTEGER);
			           
			
			          villagestatuslist = query1.list();
			}
			else if("N".equalsIgnoreCase(searchCriteriaType))
			{
				session = sessionFactory.openSession();
				
				sqlQuery ="select w.entity_code,v.village_version,v.minor_version,w.entity_name_english,w.entity_name_local,w.entity_hierarchy,w.census_2001_code,w.census_2011_code," + 
						"(case v.village_status when 'I' then 'Inhabitant' when 'R' then 'Forest' when 'U' then 'Un-Inhabitant' end) as village_status ," + 
						"(case w.is_pesa when 'P' then 'Partially Covered' when 'F' then 'Fully Covered' when 'N' then 'Not Covered' end) as pesa_status,w.file_timestamp from " + 
						"get_statewise_entity_details_for_globalview(:statecode, :entitytype, :entityCode, :entityName, null, null)w left join village v on w.entity_code=v.vlc and v.isactive";
				
				
				Query query = session.createSQLQuery(sqlQuery)
						   .setParameter("statecode", stateCode, Hibernate.INTEGER)
						   .setParameter("entitytype", ENTITY_TYPE, Hibernate.CHARACTER)
						   .setParameter("entityCode", paramEntityCode, Hibernate.INTEGER)
						   .setParameter("entityName", paramEntityName, Hibernate.STRING);
				villagestatuslist = query.list();
				
			}
		
		} catch (HibernateException e) {
			LGDLogger.getLogger(ViewEntityDetailsDaoImpl.class).error(e.toString());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villagestatuslist;
		
	}
	
	
	

	

}
