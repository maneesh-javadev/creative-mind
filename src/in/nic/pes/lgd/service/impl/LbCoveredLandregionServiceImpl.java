package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.LandRegion;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.dao.LbCoveredLandregionDAO;
import in.nic.pes.lgd.service.LbCoveredLandregionService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@Service
public class LbCoveredLandregionServiceImpl implements LbCoveredLandregionService{

	
	@Autowired
	LbCoveredLandregionDAO lbCoveredLandregionDAO;
	
	@Override
	public List<LandRegion> getCoveredLandRegionByULBList(int localBodyCode) throws Exception
	{
		//System.out.println("getCoveredLandRegionByULBList");
		List<LandRegion> LandRegionList=new ArrayList<LandRegion>();

		//Subdistrict subdistrictBean=null;
		
		
		LandRegionList=lbCoveredLandregionDAO.getcoveredLandregionByLocalbodyCode(localBodyCode);
		
		return LandRegionList;
/*		List<District> districtList =null;
		List<Subdistrict> subdistrictList=null;
		District districtBean=null;
		Subdistrict subdistrictBean=null;
		
		
		System.out.println("FDHFHSFDDSDSFSDFSDFD" + localBodyCode);
		List<LbCoveredLandregion> lbCoveredLandregionList=null;		
		lbCoveredLandregionList=new ArrayList<LbCoveredLandregion>();
		String sql="SELECT lv.land_region_code,lv.land_region_version,lv.land_region_type FROM localbody lb, lb_covered_landregion lv WHERE lb.lb_covered_region_code = lv.lb_covered_region_code AND lb.isactive=true and lb.local_body_code = '"+localBodyCode+"'";
		lbCoveredLandregionList=lbCoveredLandregionDAO.getcoveredLandregionByLocalbodyCode(sql);
		
		char landRegionType;
		int LRCode;
		String LRName;
		List<Object> list =new ArrayList<Object>();
		districtList =new ArrayList<District>();
		subdistrictList=new ArrayList<Subdistrict>();
		for (int i = 0; i < lbCoveredLandregionList.size(); i++) {
			landRegionType= lbCoveredLandregionList.get(i).getLandRegionType();
			LRCode= lbCoveredLandregionList.get(i).getLandRegionCode();
			if(landRegionType=='D' || landRegionType=='d')  //???
			{
				String qryLRname="from District where districtCode=:LRCode and isactive=true";
				LRName=lbCoveredLandregionDAO.getLandregionName(qryLRname);
				
				
				districtBean=new District();
				districtBean.setDistrictCode(LRCode);
				districtBean.setDistrictNameEnglish(LRName);
				districtList.add(districtBean);
				list.add(districtList);
			}
			if(landRegionType=='T' || landRegionType=='t')
			{
				String qryLRname="from Subdistrict where subdistrictCode=:LRCode and isactive=true";
				LRName=lbCoveredLandregionDAO.getLandregionName(qryLRname);
				
				subdistrictBean=new Subdistrict();
				subdistrictBean.setSubdistrictCode(LRCode);
				subdistrictBean.setSubdistrictNameEnglish(LRName);
				subdistrictList.add(subdistrictBean);
				//list.add(subdistrictList);
				
			}
		}
		
		//return lbCoveredLandregionList;
		return subdistrictList;*/
	}
	/**
	 * 
	 */
	@Override
	public List<Localbody> getULBList(int stateCode) throws Exception {
		// TODO Auto-generated method stub
		List<Localbody> uLBList=new ArrayList<Localbody>();
		uLBList=lbCoveredLandregionDAO.getULBList(stateCode);
		
		return uLBList;
	}
}
