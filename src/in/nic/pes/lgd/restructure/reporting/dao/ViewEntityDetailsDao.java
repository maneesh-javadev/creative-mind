package in.nic.pes.lgd.restructure.reporting.dao;

import in.nic.pes.lgd.bean.BlockWiseVillagesAndUlb;
import in.nic.pes.lgd.restructure.reporting.entities.GenericReportingEntity;

import java.util.List;

import org.hibernate.HibernateException;

public interface ViewEntityDetailsDao {
	
	public List<GenericReportingEntity> getViewStateDetails() throws HibernateException;
	
	public List<GenericReportingEntity> getViewEntityWiseDetails(Integer stateCode, String ENTITY_TYPE, Integer paramEntityCode, String paramEntityName) throws HibernateException;
	
	public List<GenericReportingEntity> getViewParentWiseDetails(String ENTITY_TYPE,Integer stateCode,Integer districtCode,Integer subDistrictCode) throws HibernateException;
	
	public List<Object[]> getVillageStatusWiseDetails(String ENTITY_TYPE,Integer stateCode,Integer districtCode,Integer subDistrictCode,Integer paramEntityCode,String paramEntityName,String searchCriteriaType) throws HibernateException; 
	
	public List<BlockWiseVillagesAndUlb> getBlockwiseandULBDetails(Integer blockCode) throws HibernateException; 

}
