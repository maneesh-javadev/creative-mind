package in.nic.pes.lgd.restructure.reporting.service.impl;

import in.nic.pes.lgd.bean.BlockWiseVillagesAndUlb;
import in.nic.pes.lgd.restructure.reporting.dao.ViewEntityDetailsDao;
import in.nic.pes.lgd.restructure.reporting.entities.GenericReportingEntity;
import in.nic.pes.lgd.restructure.reporting.service.ViewEntityDetailsService;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@Service
public class ViewEntityDetailsServiceImpl implements ViewEntityDetailsService{

	@Autowired
	private ViewEntityDetailsDao viewEntityDetailsDao;
	
	@Override
	public List<GenericReportingEntity> getViewStateDetails() throws HibernateException {
		return viewEntityDetailsDao.getViewStateDetails();
	}
	
	@Override
	public List<GenericReportingEntity> getViewEntityWiseDetails(Integer stateCode, String ENTITY_TYPE, Integer paramEntityCode, String paramEntityName) throws HibernateException {
		return viewEntityDetailsDao.getViewEntityWiseDetails(stateCode, ENTITY_TYPE, paramEntityCode, paramEntityName);
	}

	@Override
	public List<GenericReportingEntity> getViewParentWiseDetails(String ENTITY_TYPE, Integer stateCode,Integer districtCode,Integer subDistrictCode) throws HibernateException {
		return viewEntityDetailsDao.getViewParentWiseDetails(ENTITY_TYPE, stateCode,districtCode,subDistrictCode);
	}

	
	
	
	

	@Override
	public List<Object[]> getVillageStatusWiseDetails(String ENTITY_TYPE, Integer stateCode, Integer districtCode,
			Integer subDistrictCode,Integer paramEntityCode,String paramEntityName,String searchCriteriaType) throws HibernateException {
		// TODO Auto-generated method stub
		return viewEntityDetailsDao.getVillageStatusWiseDetails(ENTITY_TYPE, stateCode,districtCode,subDistrictCode,paramEntityCode,paramEntityName,searchCriteriaType);
	}

	@Override
	public List<BlockWiseVillagesAndUlb> getBlockwiseandULBDetails(Integer blockCode) throws HibernateException {
	return viewEntityDetailsDao.getBlockwiseandULBDetails(blockCode);
	}

	

	

}
