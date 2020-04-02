package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.FreezeDistrictBean;
import in.nic.pes.lgd.bean.LocalbodyPRIDistrictFreeze;
import in.nic.pes.lgd.bean.MappedLbWardDisttrictWise;
import in.nic.pes.lgd.bean.NodalOfficer;
import in.nic.pes.lgd.bean.Village;
import pes.attachment.util.AttachedFilesForm;

import java.util.List;

/**
 * 
 * @Author Vinay Yadav
 * @since 12 Feb 2015
 * 
 * 
 */
public interface DistrictFreezeService {

	/**
	 * 
	 * @param districtCode
	 * @return
	 * @throws Exception
	 */
	public FreezeDistrictBean getDestrictDetails(Integer districtCode) throws Exception;
	
	/**
	 * 
	 * @param freezeDistrictBean
	 * @return
	 * @throws Exception
	 */
	public Boolean freezeUnFreezeDistrictEntity(FreezeDistrictBean freezeDistrictBean) throws Exception;
	
	/**
	 * 
	 * @param districtCode
	 * @return
	 * @throws Exception
	 */
	public List<Village> draftedVillagesFromDistrict(Integer districtCode) throws Exception;

	/**
	 * @author Anchal Todariya
	 * @category Local body District PRI, TRD, URB freeze unfreeze..
	 * @param freezeDistrictBean
	 * @return
	 * @throws Exception
	 */
	public List<LocalbodyPRIDistrictFreeze> freezeUnFreezeDistrictEntityCheckByDistrictId(Integer districtCode, String lB) throws Exception;
	
	/**
	 * 
	 * @param freezeDistrictBean
	 * @return
	 * @throws Exception
	 */
	public List<LocalbodyPRIDistrictFreeze> draftedVillagesPRIFromDistrict(Integer districtCode, String lB) throws Exception;
	
	public FreezeDistrictBean getDestrictDetailsForPRI(Integer districtCode) throws Exception;
	
	public Boolean freezeUnFreezeDistrictPRIEntity(FreezeDistrictBean freezeDistrictBean) throws Exception;

	public FreezeDistrictBean getDestrictDetailsForTRD(Integer districtCode) throws Exception;

	public FreezeDistrictBean getDestrictDetailsForURB(Integer districtCode) throws Exception;

	NodalOfficer freezeUnFreezeDistrictNodaloffice(Integer districtCode) throws Exception;

	List<MappedLbWardDisttrictWise> getMappedLbWardDisttrictWise(Integer districtCode) throws Exception;

	Boolean saveFreezePcAcMappingOfDistrict(NodalOfficer nodal, AttachedFilesForm attachedFilesForm);

	public String isDistrictByNodalofficer(int districtCode);

	Boolean unfreezeDistrictPcAcMapping(NodalOfficer nodalOfficerbean);
}
