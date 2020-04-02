package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.AssemblyConstituency;
import in.nic.pes.lgd.bean.AssemblyConstituencyId;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.ParliamentConstituency;
import in.nic.pes.lgd.bean.ParliamentConstituencyId;
import in.nic.pes.lgd.bean.SearchElectionConstituency;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.bean.constituencyReplacedby;
import in.nic.pes.lgd.bean.constituencyReplaces;
import in.nic.pes.lgd.dao.AssemblyDAO;
import in.nic.pes.lgd.dao.MapLandRegionDAO;
import in.nic.pes.lgd.dao.ParliamentDAO;
import in.nic.pes.lgd.dao.SubDistrictDAO;
import in.nic.pes.lgd.forms.ParliamentForm;
import in.nic.pes.lgd.service.ParliamentService;
import in.nic.pes.lgd.utils.DatePicker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pes.attachment.util.AttachedFilesForm;

@Service
public class ParliamentServiceImpl implements ParliamentService { // NO_UCD (use default)
	private static final Logger log = Logger.getLogger(ParliamentServiceImpl.class);
	@Autowired
	private ParliamentDAO parliamentDAO; 
	
	@Autowired
	private AssemblyDAO AssemblyDAO; 
	
	@Autowired
	private MapLandRegionDAO mapLandRegionDAO;
	
	@Autowired
	private SubDistrictDAO subdistrictDAO;

	@Autowired
	private SessionFactory sessionFactory;
	

	private int userId = 4000;
	private Date timestamp = DatePicker.getDate("2011-09-26");
	
	
	
	private Transaction tx = null;
	private int mapCd;
	
	
	@Override	
	public boolean addParliament(ParliamentForm pcForm,int statecode,List<AttachedFilesForm> attachedMapList, int mapAttachmentid, Session session) throws Exception {
 		String fullParliamentConstList = null;
 		String partParliamentConstList	=	null;
		String fullAssemblyConstList = null;
		 
		String coordinates = null;
		//int ec_code =0;
		Integer eciCode=null;
		int  mapAttachmentCode	=	mapAttachmentid;
		try {
			
			fullParliamentConstList = RemovePartandReplaceFull(pcForm.getContributedParliament());		
			if(fullParliamentConstList==null || pcForm.getContributedParliament().contains("PART"))
			{
				partParliamentConstList	=	RemovePartandReplacePart(pcForm.getContributedParliament());
			}
			fullAssemblyConstList = RemovePartandReplaceFull(pcForm.getContributedAssembly());
			coordinates = getCoordinates(pcForm.getLatitude(),pcForm.getLongitude());
			
			if(pcForm.getParliamentECICODE() != null && !pcForm.getParliamentECICODE().isEmpty()){
				eciCode=Integer.parseInt(pcForm.getParliamentECICODE());
			}	
			parliamentDAO.saveParliamentConstituency(statecode,userId,pcForm.getNewParliamentNameEnglish(),fullParliamentConstList,partParliamentConstList,fullAssemblyConstList,pcForm.getNewParliamentNameLocal(),eciCode,coordinates,pcForm.getAttachFile1(),session,mapAttachmentCode);
			return true;
			
			
		} catch (Exception e) {		
			tx.rollback();
			throw e;
		}	
	}
	 
		
 


	public static String getCoordinates(String latitude, String longitude) { // NO_UCD (use default)

		int i;
		String result = "";

		try {
			if (latitude != null && longitude != null && !latitude.isEmpty() && !longitude.isEmpty()) {
				String[] tempLati = latitude.split(",");
				String[] tempLongi = longitude.split(",");
				if (tempLati != null && tempLongi != null) {
					if (tempLati.length > 0
							&& tempLati.length == tempLongi.length){
						for (i = 0; i < tempLati.length; i++) {

							result += tempLati[i] + ":" + tempLongi[i] + ",";
						}
					}	
				}
				result = result.substring(0, result.length() - 1);
			}
			else
			{
				result = null;
			}
		} catch (Exception e) {
			return null;
		}

		return result;
	}
	
	public static String RemovePartandReplacePart(String value) { // NO_UCD (use default)
		if (value != null) {
			if (value.contains(",")) {
				String[] temp = value.split(",");
				StringBuffer valueBuffer = new StringBuffer();
				for (String val :temp) {
					if (val.contains("PART")) {
						valueBuffer.append(val.replace("PART", ""));						 
						valueBuffer.append(",");
					}					
				}	
				if(valueBuffer.length() >0){
					return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();
				}	
			}
			else
			{
				if(value.contains("PART"))
					{
					value =  value.replace("PART", "");
					return value;
					}
			}
		}
		 return null;
	}



	public static String RemovePartandReplaceFull(String value) { // NO_UCD (use default)
		if (value != null) {
			if (value.contains(",")) {
				String[] temp = value.split(",");
				StringBuffer valueBuffer = new StringBuffer();
				for (String val :temp) {
					if (val.contains("FULL")) {
						valueBuffer.append(val.replace("FULL", ""));						 
						valueBuffer.append(",");
					}					
				}	
				if(valueBuffer.length() >0){
					return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();
				}	
			}
			else
			{
				if(value.contains("FULL"))
					{
					value =  value.replace("FULL", "");
					return value;
					}
			}
		}
		 return null;
	}
	
	public boolean saveNewSubdistritfordist( int pcCodeNew,int acCode, int acVersion, Session session) throws Exception{
		//deactivating the old Subdistrit
	//new Version code
			 List<AssemblyConstituency> listAssemblyList=new ArrayList<AssemblyConstituency>();
			 String acInactivesql = "update assembly_constituency set isactive = '"+ false +
				"' where pc_code = "+acCode+" and isactive='true'";
		try {
			subdistrictDAO.updateAssembly(acInactivesql, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
			  listAssemblyList=subdistrictDAO.getAssemblyList(acCode);
    	for (int itrSD=0; itrSD<listAssemblyList.size(); itrSD++){
    		AssemblyConstituency assemblyConstituency = new AssemblyConstituency();
    		ParliamentConstituency state = null;
    		ParliamentConstituencyId statepk = null;
    		state = new ParliamentConstituency();
    			
    		statepk=new ParliamentConstituencyId(pcCodeNew,1);
    		state.setId(statepk);
    		//assemblyConstituency.setParliamentConstituency(state);
    	
    		
    		AssemblyConstituencyId asscId=null;
    		asscId=new AssemblyConstituencyId(listAssemblyList.get(itrSD).getAcCode(), listAssemblyList.get(itrSD).getAcVersion()+1);
    		assemblyConstituency.setId(asscId);
    		assemblyConstituency.setAcNameEnglish(listAssemblyList.get(itrSD).getAcNameEnglish());
    		assemblyConstituency.setAcNameLocal(listAssemblyList.get(itrSD).getAcNameLocal());
    		assemblyConstituency.setEciCode(listAssemblyList.get(itrSD).getEciCode());
    		assemblyConstituency.setCreatedby(listAssemblyList.get(itrSD).getCreatedby());
    		assemblyConstituency.setCreatedon(listAssemblyList.get(itrSD).getCreatedon());
    		assemblyConstituency.setEffectiveDate(listAssemblyList.get(itrSD).getEffectiveDate());
    		assemblyConstituency.setIsactive(true);
    		assemblyConstituency.setLastupdated(listAssemblyList.get(itrSD).getLastupdated());
    		//assemblyConstituency.setLastupdatedby(listAssemblyList.get(itrSD).getLastupdatedby());
    		//assemblyConstituency.setf(2);
	  try {
		  AssemblyDAO.publishAssembly(assemblyConstituency, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
				    	} 	
    	
	

	return true;
}




	
	
	public boolean saveNewparliament(int pcCode, int pcVersion, int lrReplacedBy, Session session) throws Exception{
		ParliamentConstituencyId sdpk =null;
		ParliamentConstituency sdbeanLocal = null;
		sdpk=new ParliamentConstituencyId(pcCode, pcVersion);
		sdbeanLocal=new ParliamentConstituency();
		sdbeanLocal=parliamentDAO.getPcDetail(sdpk, session);
		ParliamentConstituency sdbean=new ParliamentConstituency();
		sdpk =null;
		sdpk=new ParliamentConstituencyId(pcCode, pcVersion+1);
		//State dist=sdbeanLocal.getState();
		sdbean.setId(sdpk);
		sdbean.setPcNameEnglish(sdbeanLocal.getPcNameEnglish());
		sdbean.setPcNameLocal(sdbeanLocal.getPcNameLocal());
		sdbean.setCreatedby(sdbeanLocal.getCreatedby());
	    sdbean.setCreatedon(sdbeanLocal.getCreatedon());
		//sdbean.setState(dist);
		sdbean.setEciCode(sdbeanLocal.getEciCode());
		sdbean.setEffectiveDate(sdbeanLocal.getEffectiveDate());
		//sdbean.setFlagCode(2);
		sdbean.setIsactive(true);
		sdbean.setLastupdated(sdbeanLocal.getLastupdated());
		sdbean.setConstituency_replacedby(lrReplacedBy);
		sdbean.setConstituency_replaces(sdbeanLocal.getConstituency_replaces());
		sdbean.setIsdisturbed(true);
		try {
			 parliamentDAO.publishParliament(sdbean,session);
			 } catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}
	
/*	this.saveAssembly(pcCode, fullContributedAssemblyList.get(k).getId().getAcCode(), fullContributedAssemblyList.get(k).getId().getAcVersion(), session);
*/
	
	/*public boolean saveAssembly( int pccodeNew,int acCode, int acVersion, Session session){
		//deactivating the old Subdistrit
	//new Version code
			 List<Subdistrict> listSubdistrict=new ArrayList<Subdistrict>();
			 String villageInactivesql = "update subdistrict set isactive = '"+ false +
				"' where district_code = "+districtCode+" and isactive='true'";
		try {
			subdistrictDAO.update(villageInactivesql, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
			  listSubdistrict=subdistrictDAO.getSubDistrict(districtCode);
    	for (int itrSD=0; itrSD<listSubdistrict.size(); itrSD++){
    		District district=null;
    		
    		district=new District();
    		 DistrictPK districtPKObj=null;
		     districtPKObj=new DistrictPK(stateCodeNew,1);
		     district.setDistrictPK(districtPKObj);
		     SubdistrictPK sdpk=null;
	    DistrictPK distritpk=null;
	   	Subdistrict subDistrictbean=new Subdistrict();
	   	subDistrictbean.setDistrict(district);
	   	sdpk=new SubdistrictPK(listSubdistrict.get(itrSD).getSubdistrictCode(), listSubdistrict.get(itrSD).getSubdistrictVersion()+1);
			
		subDistrictbean.setSubdistrictPK(sdpk);
		subDistrictbean.setAliasEnglish(listSubdistrict.get(itrSD).getAliasEnglish());
		subDistrictbean.setAliasLocal(listSubdistrict.get(itrSD).getAliasLocal());
		subDistrictbean.setCensus2001Code(listSubdistrict.get(itrSD).getCensus2001Code());
		subDistrictbean.setCensus2011Code(listSubdistrict.get(itrSD).getCensus2011Code());
		subDistrictbean.setCreatedby(listSubdistrict.get(itrSD).getCreatedby());
		subDistrictbean.setCreatedon(listSubdistrict.get(itrSD).getCreatedon());
		
		subDistrictbean.setEffectiveDate(listSubdistrict.get(itrSD).getEffectiveDate());
		subDistrictbean.setFlagCode(2);
		subDistrictbean.setIsactive(true);
		subDistrictbean.setLastupdated(listSubdistrict.get(itrSD).getLastupdated());
		subDistrictbean.setLastupdatedby(listSubdistrict.get(itrSD).getLastupdatedby());
		subDistrictbean.setLrReplacedby(listSubdistrict.get(itrSD).getLrReplacedby());
		subDistrictbean.setLrReplaces(listSubdistrict.get(itrSD).getLrReplaces());
		subDistrictbean.setMapLandregionCode(listSubdistrict.get(itrSD).getMapLandregionCode());
		subDistrictbean.setSscode(listSubdistrict.get(itrSD).getSscode());
		subDistrictbean.setSubdistrictNameEnglish(listSubdistrict.get(itrSD).getSubdistrictNameEnglish());
		subDistrictbean.setSubdistrictNameLocal(listSubdistrict.get(itrSD).getSubdistrictNameLocal());
	  try {
		   subdistrictDAO.save(subDistrictbean,session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
				    	} 	
    	
	

	return true;
}

	*/
	/*@Override
	public boolean saveAssemblyPartContri(int assCode,
			int newassCode, int newSDVersion, Session session) {
		// new Version code
		List<Subdistrict> listVillage = null;
		listVillage = new ArrayList<Subdistrict>();
		listVillage = subdistrictDAO.getVillageDetailsModify(villCode);
		for (int villItr = 0; villItr < listVillage.size(); villItr++) {
			SubdistrictPK subdistrictPK = null;
			DistrictPK sdpk = null;
			District sdbean = null;
			sdpk = new DistrictPK(newSDCode, newSDVersion);
			sdbean = new District();
			subdistrictPK = new SubdistrictPK(listVillage.get(villItr)
					.getSubdistrictCode(), listVillage.get(villItr)
					.getSubdistrictVersion());
			sdbean.setDistrictPK(sdpk);
			try {
				Subdistrict subdistrict = (Subdistrict) session.load(
						Subdistrict.class, subdistrictPK);
				subdistrict.setDistrict(sdbean);
				subdistrict.setFlagCode(2);
				subdistrict.setIsactive(true);
				session.update(subdistrict);
				if(session.contains(subdistrict))
					session.evict(subdistrict);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
		}
		return true;
	}*/
	
	
	public int publishMapLandRegion(ParliamentForm sdForm, int sdCode,
			int sdVersion, Session session) throws Exception {
		String cord;
		cord = sdForm.getLatitude() + ":" + sdForm.getLongitude();
		// MultipartFile filename=null;
		// filename=sdForm.getFilePathMapUpLoad();
		// writeMap(filename,request);
		// String
		// filePath=request.getRealPath("/")+filename.getOriginalFilename() ;

		// System.out.println("Filepath will display here-MAPlandregion--"+filePath);

		MapLandRegion mapbean = new MapLandRegion();
		mapbean.setLandregionCode(sdCode);
		mapbean.setLandregionVersion(sdVersion);
		mapbean.setLandregionType('P');
		mapbean.setCoordinates(cord);
		// mapbean.setImagePath(sdForm.getMapUrl());
		mapbean.setLastupdated(timestamp);
		mapbean.setEffectiveDate(timestamp);
		mapbean.setLastupdatedby(1010101);
		mapbean.setCreatedby(1010101);
		mapbean.setCreatedon(timestamp);
		mapbean.setWarningflag(false);
		mapCd = mapLandRegionDAO.configMap(mapbean, session);
		return mapCd;
	}
	//this.saveconstituencycoverageentity(concoverageentity,concoverage, fullContributedList.get(k).getId().getPcCode(), fullContributedList.get(k).getId().getPcVersion(), session);


	/*private boolean saveconstituencycoverage(int constitutecoverage, int concoverage,int pcVersion2, Session session) throws Exception {
		ConstituencyCoverage constituencyCoverageobj=null;
		constituencyCoverageobj=new ConstituencyCoverage();
		constituencyCoverageobj.setCcCode(constitutecoverage);
		//constituencyCoverageobj.setConstituencyCode(concoverage);
		constituencyCoverageobj.setConstituencyType('P');
		//constituencyCoverageobj.setConstituencyVersion(pcVersion2);
		constituencyCoverageobj.setLastupdated(timestamp);
		constituencyCoverageobj.setLastupdatedby(1010101);
		constituencyCoverageobj.setCreatedby(1010101);
		constituencyCoverageobj.setCreatedon(timestamp);
		constituencyCoverageobj.setIsactive(true);
		parliamentDAO.saveconstituency(constituencyCoverageobj,session);
		session.flush();
		session.refresh(constituencyCoverageobj);
		// TODO Auto-generated method stub
		return true;
	}*/

	/*private boolean saveconstituencycoverageentity(int id, int ccCode,int entityCode,int entity_Version, char entitytype,char coveragetype, Session session) throws Exception {
		ConstituencyCoverageEntity constituencyCoverageobj=null;
		constituencyCoverageobj=new ConstituencyCoverageEntity();
		
		constituencyCoverageobj.setId(id);
		constituencyCoverageobj.setCccode(ccCode);
		//constituencyCoverageobj.setEntitycode(entityCode);
		//constituencyCoverageobj.setEntityversion(entity_Version);
		constituencyCoverageobj.setEntitytype(entitytype);
		constituencyCoverageobj.setCoveragetype(coveragetype);
		constituencyCoverageobj.setIsactive(true);
		
		parliamentDAO.save(constituencyCoverageobj, session);
		// TODO Auto-generated method stub
		return true;
	}*/

	@Override
	public boolean saveReplaces(int id, int lrReplaces, int sdCode,int pcversion,Session session) throws Exception{
		constituencyReplaces constituencyReplaces = null;
		constituencyReplaces = new constituencyReplaces();
		constituencyReplaces.setId(id);
		constituencyReplaces.setConstituencyReplaces(lrReplaces);
		constituencyReplaces.setEntityCode(sdCode);
		constituencyReplaces.setEntityVersion(pcversion);
		constituencyReplaces.setEntityType('P');
		parliamentDAO.save(constituencyReplaces, session);
	
	
		return true;
	}
	
	@Override
	public boolean saveReplacedBy(int id, int lrReplacedBy, int sdCode, int sdVersionCode, Session session) throws Exception{
		
		constituencyReplacedby constituencyReplacedby=null;
		/*LandregionReplacedby landregionReplacedbyBean=null;*/
		constituencyReplacedby =new constituencyReplacedby();
		constituencyReplacedby.setId(id);
		constituencyReplacedby.setConstituencyReplacedby(lrReplacedBy);
		constituencyReplacedby.setEntityCode(sdCode);
		constituencyReplacedby.setEntityVersion(sdVersionCode);
		constituencyReplacedby.setEntityType('P');
		parliamentDAO.save(constituencyReplacedby, session);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public int getpcVersion(int pccode){
		int distVers=0;
		Session session=null;
		session=sessionFactory.openSession();
		try {
			List<ParliamentConstituency> dist=session.createQuery("from ParliamentConstituency d where isactive=true and state_code="+ pccode).list();
			distVers=dist.get(0).getId().getPcVersion();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return distVers;
	}

	
	public boolean saveParliament(ParliamentForm sdForm, int pcCode, int pcVersion, Session session,int statecode,int mapCd) {
		try
		{
		//distVersion=this.getSubDistrictVersion(Integer.parseInt(sdForm.getDistrictCode()));
	//	DistrictPK dPK=new DistrictPK(Integer.parseInt(sdForm.getDistrictCode()),distVersion);
		//District dist=new District();
		//SubdistrictPK sdpk = new SubdistrictPK(sdCode, sdVersion);
		ParliamentConstituency parliamentconstituencyobj = new ParliamentConstituency();
		State state = null;
		StatePK statepk = null;
		state = new State();
		// stateVersion=this.getStateVersion(sdForm.getStateCode());
		/*int Statecode=1;*/
		int pcVersionold=this.getStateVersion(statecode);
		statepk=new StatePK(statecode,pcVersionold);
		state.setStatePK(statepk);
		//parliamentconstituencyobj.setState(state);
		ParliamentConstituencyId sdpk = new ParliamentConstituencyId(pcCode, pcVersion);
		parliamentconstituencyobj.setId(sdpk);
		 parliamentconstituencyobj.setPcNameEnglish(sdForm.getNewParliamentNameEnglish());                  
		 parliamentconstituencyobj.setPcNameLocal(sdForm.getNewParliamentNameLocal());
		 parliamentconstituencyobj.setCreatedby(1010101);
 
		 parliamentconstituencyobj.setCreatedon(timestamp);

		 parliamentconstituencyobj.setEciCode(Integer.parseInt(sdForm.getParliamentECICODE()));
		/* parliamentconstituencyobj.setEciCode(Integer.parseInt(sdForm.getParliamentECICODE()));*/
		 parliamentconstituencyobj.setEffectiveDate(timestamp);
		 parliamentconstituencyobj.setIsactive(true);
		 parliamentconstituencyobj.setIsdisturbed(true);
		// parliamentconstituencyobj.setStateCode(statecode);
		 parliamentconstituencyobj.setLastupdated(timestamp);
		 //parliamentconstituencyobj.setLastupdatedby(1010101);
		 
 
		 parliamentDAO.publishParliament(parliamentconstituencyobj, session);
		}
		catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		return true;
	}
	
	
	private int getStateVersion(int stateCode)throws Exception {
		int distVers = 0;
		try {
			distVers = Integer.parseInt(sessionFactory
					.openSession()
					.createQuery(
							"select stateVersion from State where isactive=true and stateCode="
									+ stateCode).list().get(0).toString());
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
			/*log.debug("Exception" + e);*/
		}
		return distVers;

	}
	
	
	public void addgovernment(GovernmentOrder governmentorder) throws Exception {
		int orderCode=0;
		orderCode= parliamentDAO.getMaxacCode1();
	 	if(orderCode==0){
	 		orderCode=1;
	 	}else{
	 		orderCode=orderCode+1;
	 	}
	 	governmentorder.setOrderCode(orderCode);
	 	parliamentDAO.addgovernment(governmentorder);
		
	}
	
	
	public List<SearchElectionConstituency> getElectionConstituencySearchDetail(String entityName,String entityCode) throws Exception
	{
		List<SearchElectionConstituency> SearchEC= new ArrayList<SearchElectionConstituency>();
		SearchEC =parliamentDAO.getElectionConstituencySearchDetail(entityName,entityCode);
		 return SearchEC;
		
		}
	
	public List<SearchElectionConstituency> getElectionConstituencySearchDetailByCode(int entitycodeforsearch,String entityCode) throws Exception
	{
		List<SearchElectionConstituency> SearchEC= new ArrayList<SearchElectionConstituency>();
		SearchEC =parliamentDAO.getElectionConstituencySearchDetailByCode(entitycodeforsearch,entityCode);
		 return SearchEC;
		
		}


	@Override
	public boolean saveAssemblyPartContri(int assCode, int newassCode,
			int newSDVersion, Session session) {
		// TODO Auto-generated method stub
		return false;
	}





	@Override
	public boolean addParliament(ParliamentForm pcForm, int statecode)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveDataInMapAttach(ParliamentForm parliamentConstituency,List<AttachedFilesForm> attachedList, HttpSession httpsession,Session session) throws Exception 
	{	
		return parliamentDAO.saveDataInMapAttach(parliamentConstituency,attachedList, httpsession,session);
	}
	
	
	public boolean existECICODE(Integer EntityCode,Integer eciCode,char Type) throws Exception 
	{
		return (parliamentDAO.existECICODEDAO(EntityCode,eciCode,Type));
	}

  public boolean existEntityName(Integer stateCode,String entityName,char EntityType) throws Exception
  {
		return (parliamentDAO.existEntityName(stateCode,entityName,EntityType));
  }
  
  public String getParliamentDetails(Integer pcCode)throws Exception
  {
	  return (parliamentDAO.getParliamentDetails(pcCode));
  }
  
  public List<ParliamentConstituency> getParliamentConstituencyDetail(Integer slc)
	{
	  return (parliamentDAO.getParliamentConstituencyDetail(slc));
	}
  
  public Boolean checkMapConfigurationforConstituenc(Integer stateCode,char constituencyType) throws Exception
  {
	  return (parliamentDAO.checkMapConfigurationforConstituenc(stateCode,constituencyType));
  }

public Integer getEciCodeParliament(Integer pcCode,char type) throws Exception

{
	 return (parliamentDAO.getEciCodeParliament(pcCode,type));
}



@Override
public boolean parliamentExist(int slc, String parliamentName,char type){
	// TODO Auto-generated method stub
	return parliamentDAO.parliamentExist(slc,parliamentName,type);
}
	
}
