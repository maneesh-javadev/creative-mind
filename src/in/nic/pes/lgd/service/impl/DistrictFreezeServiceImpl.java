package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.FreezeDistrictBean;
import in.nic.pes.lgd.bean.LocalbodyPRIDistrictFreeze;
import in.nic.pes.lgd.bean.MappedLbWardDisttrictWise;
import in.nic.pes.lgd.bean.NodalOfficer;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.dao.DistrictFreezeDao;
import in.nic.pes.lgd.service.DistrictFreezeService;
import pes.attachment.util.AttachedFilesForm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @Author Vinay Yadav
 * @since 12 Feb 2015
 * 
 * 
 */
public class DistrictFreezeServiceImpl implements DistrictFreezeService {

	@Autowired
	private DistrictFreezeDao districtFreezeDAO;

	@Override
	public FreezeDistrictBean getDestrictDetails(Integer districtCode) throws Exception {
		
		return districtFreezeDAO.getDestrictDetails( districtCode );
	}
	
	@Override
	public Boolean freezeUnFreezeDistrictEntity(FreezeDistrictBean freezeDistrictBean) throws Exception {
	
		return districtFreezeDAO.freezeUnFreezeDistrictEntity(freezeDistrictBean);
	}
	
	@Override
	public List<Village> draftedVillagesFromDistrict(Integer districtCode) throws Exception {
	
		return districtFreezeDAO.draftedVillagesFromDistrict(districtCode);
	}

	@Override
	public List<LocalbodyPRIDistrictFreeze> freezeUnFreezeDistrictEntityCheckByDistrictId(Integer districtCode, String lB) throws Exception {
		
		return districtFreezeDAO.freezeUnFreezeDistrictEntityCheckByDistrictId(districtCode, lB);
	}

	@Override
	public List<LocalbodyPRIDistrictFreeze> draftedVillagesPRIFromDistrict(Integer districtCode, String lB) throws Exception {
		
		return districtFreezeDAO.draftedVillagesPRIFromDistrict(districtCode, lB);
	}
	
	public FreezeDistrictBean getDestrictDetailsForPRI(Integer districtCode) throws Exception {
		return districtFreezeDAO.getDestrictDetailsForPRI(districtCode);
	}

	@Override
	public Boolean freezeUnFreezeDistrictPRIEntity(FreezeDistrictBean freezeDistrictBean) throws Exception {
		
		return districtFreezeDAO.freezeUnFreezeDistrictPRIEntity(freezeDistrictBean);
	}

	@Override
	public FreezeDistrictBean getDestrictDetailsForTRD(Integer districtCode) throws Exception  {
		return districtFreezeDAO.getDestrictDetailsForTRD(districtCode);
	}

	@Override
	public FreezeDistrictBean getDestrictDetailsForURB(Integer districtCode) throws Exception {
		return districtFreezeDAO.getDestrictDetailsForURB(districtCode);
		
	}
	@Override
	 public NodalOfficer freezeUnFreezeDistrictNodaloffice(Integer districtCode) throws Exception {
		 return districtFreezeDAO.freezeUnFreezeDistrictNodaloffice(districtCode);
	 }
	@Override
	 public List<MappedLbWardDisttrictWise> getMappedLbWardDisttrictWise(Integer districtCode) throws Exception {
		 return districtFreezeDAO.getMappedLbWardDisttrictWise(districtCode);
	 }
	@Override
	public Boolean saveFreezePcAcMappingOfDistrict(NodalOfficer nodal,AttachedFilesForm attachedFilesForm){
		return districtFreezeDAO.saveFreezePcAcMappingOfDistrict(nodal,attachedFilesForm);
	}
	@Override
    public String isDistrictByNodalofficer(int distCode){
		return districtFreezeDAO.isDistrictByNodalofficer(distCode);
	}
	@Override
	public Boolean unfreezeDistrictPcAcMapping(NodalOfficer nodalOfficerbean){
		return districtFreezeDAO.unfreezeDistrictPcAcMapping(nodalOfficerbean);
	}
}
