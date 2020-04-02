package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.AssemblyConstituency;
import in.nic.pes.lgd.bean.AssemblyConstituencyId;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.ParliamentConstituency;
import in.nic.pes.lgd.bean.ParliamentConstituencyId;
import in.nic.pes.lgd.bean.constituencyReplacedby;
import in.nic.pes.lgd.bean.constituencyReplaces;
import in.nic.pes.lgd.dao.AssemblyDAO;
import in.nic.pes.lgd.dao.DistrictDAO;
import in.nic.pes.lgd.dao.MapLandRegionDAO;
import in.nic.pes.lgd.dao.ParliamentDAO;
import in.nic.pes.lgd.forms.AssemblyForm;
import in.nic.pes.lgd.service.AssemblyService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.utils.DatePicker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pes.attachment.util.AttachedFilesForm;

@Service
public class AssemblyServiceImpl implements AssemblyService {
	private static final Logger log = Logger.getLogger(AssemblyServiceImpl.class);
	
	@Autowired
	AssemblyDAO AssemblyDAO;

	@Autowired
	ParliamentDAO parliamentDAO;

	@Autowired
	private MapLandRegionDAO mapLandRegionDAO;

	@Autowired
	DistrictDAO districtDAO;

	@Autowired
	GovernmentOrderService govtOrderService;

	@Autowired
	SessionFactory sessionFactory;


	Date timestamp = DatePicker.getDate("2011-09-26");
	int pcCode;
	int pcVersion;
	int mapCd;
	Session session = null;
	Transaction tx = null;
	int userID = 4000;

	@Override
	@Transactional
	public boolean addassembly(AssemblyForm acForm, int statecode,
			List<AttachedFilesForm> attachedList,int mapAttachmentid, Session session) throws Exception {

		String fullAssemblyConstList = null;
		int pcCode = 0;
		String coordinates = null;
		Integer ec_code = null;
		String partAssemblyConstList	=	null;
		try {
            if(acForm.getContributedParliament() != null && !acForm.getContributedParliament().isEmpty()){
            	pcCode = Integer.parseInt(acForm.getContributedParliament());
            }	
            
			fullAssemblyConstList = ParliamentServiceImpl
					.RemovePartandReplaceFull(acForm.getContributedAssembly());
			if(fullAssemblyConstList==null || acForm.getContributedAssembly().contains("PART"))
			{
				partAssemblyConstList	=	ParliamentServiceImpl.RemovePartandReplacePart(acForm.getContributedAssembly());
			}
			coordinates = ParliamentServiceImpl.getCoordinates(
					acForm.getLatitude(), acForm.getLongitude());
			if (acForm.getAssemblyECICODE() != null 
					&& !acForm.getAssemblyECICODE().isEmpty()){
				ec_code = Integer.parseInt(acForm.getAssemblyECICODE());
			}	
			@SuppressWarnings("unused")
			int resultCode = AssemblyDAO.saveAssemblyConstituency(pcCode,
					userID, acForm.getNewAssemblyNameEnglish(),
					fullAssemblyConstList, acForm.getNewAssemblyNameLocal(),
					ec_code, coordinates, session, acForm.getFileMapUpLoad(),partAssemblyConstList,mapAttachmentid);
			return true;

		} catch (Exception e) {
			tx.rollback();
			throw e;
		} 

	}

	public int publishMapLandRegion(AssemblyForm sdForm, int sdCode,
			int sdVersion, Session session) throws Exception {
		String cord;
		cord = sdForm.getLatitude() + ":" + sdForm.getLongitude();
		 
		MapLandRegion mapbean = new MapLandRegion();
		mapbean.setLandregionCode(sdCode);
		mapbean.setLandregionVersion(sdVersion);
		mapbean.setLandregionType('A');
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

	/*public int publishMapLandRegion(AssemblyForm sdForm, int sdCode,
			int sdVersion, Session session) {
		String cord=null;
		if(sdForm.getLati().split(",").length>1){
			String[] tempLati=sdForm.getLati().split(",");
			String[] tempLongi=sdForm.getLongi().split(",");
			cord=tempLati[0]+","+tempLongi[0]+":";
			if(tempLati.length>1){
				for(int i=1;i<tempLati.length;i++){
					cord+=tempLati[i]+","+tempLongi[i]+":";
				}
			}
			cord=cord.substring(0, cord.length()-1);
		}
		//cord = sdForm.getLati() + ":" + sdForm.getLongi();

		MapLandRegion mapbean = new MapLandRegion();
		mapbean.setLandregionCode(sdCode);
		mapbean.setLandregionVersion(sdVersion);
		mapbean.setLandregionType('T');
		mapbean.setCoordinates(cord);
		mapbean.setImagePath(sdForm.getMapUrl());
		mapbean.setLastupdated(timestamp);
		mapbean.setEffectiveDate(timestamp);
		mapbean.setLastupdatedby(1010101);
		mapbean.setCreatedby(1010101);
		mapbean.setCreatedon(timestamp);
		mapbean.setWarningflag(false);
		mapCd = mapLandRegionDAO.configMap(mapbean, session);
		return mapCd;
	}
*/
	

	/*private boolean saveconstituencycoverageentity(int id, int ccCode,int entityCode,int entity_Version,char entitytype,char coveragetype, Session session) throws Exception {
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

	/*private boolean saveconstituencycoverage(int constitutecoverage, int concoverage, 
			int pcVersion2, Session session) throws Exception {
		ConstituencyCoverage constituencyCoverageobj=null;
		constituencyCoverageobj=new ConstituencyCoverage();
		constituencyCoverageobj.setCcCode(constitutecoverage);
		//constituencyCoverageobj.setConstituencyCode(pcCode);
		constituencyCoverageobj.setConstituencyType('A');
		//constituencyCoverageobj.setConstituencyVersion(pcVersion2);
		constituencyCoverageobj.setLastupdated(timestamp);
		constituencyCoverageobj.setLastupdatedby(1010101);
		constituencyCoverageobj.setCreatedby(1010101);
		constituencyCoverageobj.setCreatedon(timestamp);
		constituencyCoverageobj.setIsactive(true);
		parliamentDAO.saveconstituency(constituencyCoverageobj, session);
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
		constituencyReplaces.setEntityType('A');
		AssemblyDAO.save(constituencyReplaces, session);
		session.flush();
		session.refresh(constituencyReplaces);
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
		constituencyReplacedby.setEntityType('A');
		AssemblyDAO.save(constituencyReplacedby, session);
		return true;
	}
	
	@SuppressWarnings("unchecked")
 
	public int getpcVersion(int pccode){
		Session session = null;
		Query query = null;
		int distVers=0;
		try {
			session = sessionFactory.openSession();
			query =session.createQuery("from ParliamentConstituency d where isactive=true and id.pcCode="+ pccode);
			List<ParliamentConstituency> dist = query.list();
			distVers=dist.get(0).getId().getPcVersion();
		} catch (Exception e) {
			//e.printStackTrace();
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return distVers;
	}

	
	public boolean saveAssembly(AssemblyForm sdForm, int pcCode, int pcVersion, Session session,int statecode,int mapCd,int pcCodenew) throws Exception 
	
	{
			Query tab=session.createQuery("from ParliamentConstituency where isactive=true And stateCode= "+(statecode));
			@SuppressWarnings("unchecked")
			List<ParliamentConstituency> list = tab.list();
			//ParliamentConstituency ParliamentConstituency = null;
			for(int i=0;i<list.size();i++){
			//ParliamentConstituency = (ParliamentConstituency) list.get(i);
			int pcVersionold=this.getpcVersion(pcCodenew);
					
		AssemblyConstituency assemblyConstituency = new AssemblyConstituency();
		ParliamentConstituency state = null;
		ParliamentConstituencyId statepk = null;
		state = new ParliamentConstituency();
			
		statepk=new ParliamentConstituencyId(pcCodenew,pcVersionold);
		state.setId(statepk);
		//Tanuj commented
		//assemblyConstituency.setParliamentConstituency(state);
		AssemblyConstituencyId sdpk = new AssemblyConstituencyId(pcCode, pcVersion);
		assemblyConstituency.setId(sdpk);
		assemblyConstituency.setAcNameEnglish(sdForm.getNewAssemblyNameEnglish());
		assemblyConstituency.setAcNameLocal(sdForm.getNewAssemblyNameLocal());
		assemblyConstituency.setCreatedby(1010101);
		//java.util.Date today = new java.util.Date();
		 assemblyConstituency.setCreatedon(timestamp);

		 assemblyConstituency.setEciCode(Integer.parseInt(sdForm.getAssemblyECICODE()));
		 /*assemblyConstituency.setEciCode(sdForm.getAssemblyECICODE());*/
		 assemblyConstituency.setEffectiveDate(timestamp);
		 assemblyConstituency.setIsactive(true);
		 assemblyConstituency.setIsdisturbed(true);
		 assemblyConstituency.setLastupdated(timestamp);
		 //assemblyConstituency.setLastupdatedby(1010101);
		 assemblyConstituency.setMapConstituencyCode(mapCd);
		 AssemblyDAO.publishAssembly(assemblyConstituency, session);
		
	}
		
			
	
		return true;
		
	}
	
	/*public void addgovernment(GovernmentOrder governmentorder) throws Exception {
		//System.out.println("<-------add goverment order--------->");
		int orderCode=0;
		orderCode= AssemblyDAO.getMaxacCode1();
	 	if(orderCode==0){
	 		orderCode=1;
	 	}else{
	 		orderCode=orderCode+1;
	 	}
	 	governmentorder.setOrderCode(orderCode);
	 	AssemblyDAO.addgovernment(governmentorder);
		
	}*/
	
	
	/*public List<SearchElectionConstituency> getElectionConstituencySearchDetail(String entityName,String entityCode) throws Exception
	{
		List<SearchElectionConstituency> SearchEC= new ArrayList<SearchElectionConstituency>();
		SearchEC =AssemblyDAO.getElectionConstituencySearchDetail(entityName,entityCode);
		 return SearchEC;
		
		}*/
	
	public static String stringRemoveFull(String value) {
		String[] temp = value.split(",");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("PART")) {
				valueBuffer.append(temp[i].replace("PART", ""));
				if (i < temp.length){
					valueBuffer.append(",");
				}	
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();

	}
	
	public static String stringRemovePart(String value) {
		return value.replaceAll("PART", "");
	}

	
	public List<AssemblyConstituency> getAssembly(String assemblyCode) throws NumberFormatException, Exception
	{
		
		List<AssemblyConstituency> assemblyList=new ArrayList<AssemblyConstituency>();
		List<AssemblyConstituency> assemblyListwhole=new ArrayList<AssemblyConstituency>();
		String listOfIds = null;
		if (assemblyCode.contains(",")){
			listOfIds = stringRemoveFull(assemblyCode);
		}	
		else if (assemblyCode.contains("PART")){
			listOfIds = stringRemovePart(assemblyCode);
		}	
		String selectdDistPancId[] = listOfIds.split(",");

		for (int i = 0; i < selectdDistPancId.length; i++) {

			assemblyList = AssemblyDAO
					.getAssembly(Integer
							.parseInt(selectdDistPancId[i]));
			assemblyListwhole.addAll(assemblyList);
		}

		return assemblyListwhole;
		
		
	}

	
	public List<AssemblyConstituency> getAssemblyconstuincy(String districtCode) throws NumberFormatException, Exception
	{
		//return subdistrictDAO.getSubDistrictListbyDistrictCode(districtCode);
		return	AssemblyDAO.getAssembly(Integer.parseInt(districtCode));
	}
	

	public boolean addassembly(AssemblyForm pcForm, int stateCode)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/*public List<AssemblyConstituency> getAssemblyconstuincy(String districtCode)
	{
		System.out.println("dadadadd");
		System.out.println("district code"+districtCode);
		List<AssemblyConstituency> assemblyList=new ArrayList<AssemblyConstituency>();
		List<AssemblyConstituency> assemblyListwhole=new ArrayList<AssemblyConstituency>();
		String listOfIds = null;
		if (districtCode.contains(","))
			listOfIds = stringRemoveFull(districtCode);
		else if (districtCode.contains("PART"))
			listOfIds = stringRemovePart(districtCode);

		String selectdDistPancId[] = listOfIds.split(",");

		for (int i = 0; i < selectdDistPancId.length; i++) {

			assemblyList = AssemblyDAO
					.getAssembly(Integer
							.parseInt(selectdDistPancId[i]));
			assemblyListwhole.addAll(assemblyList);
		}

		return assemblyListwhole;
		
		
	}
*/
	@Override
	public int saveDataInMapAttach(AssemblyForm assemblyConstituency,List<AttachedFilesForm> attachedList, HttpSession httpsession, Session session) throws Exception 
	{	
		return AssemblyDAO.saveDataInMapAttach(assemblyConstituency,attachedList, httpsession,session);
	}

	@Override
	public List<AssemblyConstituency> getAssemblyConstituencyListbyParliamenCodet(Integer pcCode) throws Exception {
		return AssemblyDAO.getAssemblyConstituencyListbyParliamenCodet(pcCode);
	}
	
	/* public String getAssemblyDetails(Integer acCode)throws Exception
	  {
		  return (AssemblyDAO.getAssemblyDetails(acCode));
	  }*/
		
	
	
	
}
