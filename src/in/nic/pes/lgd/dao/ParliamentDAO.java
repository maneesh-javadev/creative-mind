package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.AssemblyConstituencyId;
import in.nic.pes.lgd.bean.ConstituencyCoverage;
import in.nic.pes.lgd.bean.ConstituencyCoverageEntity;
import in.nic.pes.lgd.bean.ConstituencyCoverageEntitypartdetails;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.ParliamentConstituency;
import in.nic.pes.lgd.bean.ParliamentConstituencyId;
import in.nic.pes.lgd.bean.SearchElectionConstituency;
import in.nic.pes.lgd.bean.constituencyReplacedby;
import in.nic.pes.lgd.bean.constituencyReplaces;
import in.nic.pes.lgd.forms.ParliamentForm;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pes.attachment.util.AttachedFilesForm;

public interface ParliamentDAO {
	public boolean addParliament(ParliamentConstituency parliamentconstituency)throws Exception;
	public boolean addgovernment(GovernmentOrder governmentorder)throws Exception;
	public int getMaxacCode()throws Exception;
	public int getMaxacCode1()throws Exception;
	public List<ParliamentConstituency> viewparliamentDetails(String sql)throws Exception;

	public List<District> viewparliamentDetail(String sql)throws Exception;

	public List<SearchElectionConstituency> getElectionConstituencySearchDetail(String entityName,String entityCode)throws Exception;
	public boolean publishParliament(ParliamentConstituency parliamentConstituency, Session ses)throws Exception;
	public int getMaxRecords(String query)throws Exception;
	public int save(constituencyReplacedby constituencyReplacedby, Session session)throws Exception;
	public int save(constituencyReplaces constituencyReplacesobj, Session session)throws Exception;

	public boolean updateIsActive(boolean isActive, ParliamentConstituencyId subdistrictPK, Session session)throws Exception;
	
	
	public boolean updateLrReplaces(int lrReplaces, ParliamentConstituencyId ParliamentConstituencyId, Session session)throws Exception;
	
	public boolean saveconstituency(ConstituencyCoverage constituencyCoverageobject, Session session)throws Exception;                  
	public boolean save(ConstituencyCoverageEntity ConstituencyCoverageEntity, Session session)throws Exception;
	public ParliamentConstituency getPcDetail(ParliamentConstituencyId sdpk,
			Session session)throws Exception;
	
	public boolean saveconstituencypartdetail(
			ConstituencyCoverageEntitypartdetails constituencyCoveragepartobj,
			Session session)throws Exception;
	boolean updateAssembly(boolean isActive,
			AssemblyConstituencyId assemblyConstituencyId, Session session)throws Exception;
	
	public int saveParliamentConstituency(int statecode, int userId,
			String pcNameEnglish, String fullParliamentConstList, String partParliamentConstList,
			String fullAssemblyConstList, String pcNameLocal, Integer eciCode,
			String coordinates, List<CommonsMultipartFile> attachedFile, Session session, int mapAttachmentCode) throws Exception;
	public int saveDataInMapAttach(ParliamentForm parliamentConstituency,
			List<AttachedFilesForm> attachedList, HttpSession httpsession,Session session);
	public boolean existECICODEDAO(Integer EntityCode,Integer eciCode,char Type) throws Exception; 
	public int ParConstituencyCoverageExist(int coverageCode, char type,Session session)throws Exception; 
	public int mapPaliamentLBData(int coverageCode,String deletedObjectRecord,String deletedobject, String objectRecord,Session session)throws Exception;
	public boolean existEntityName(Integer stateCode,String entityName,char EntityType) throws Exception;
	 public String getParliamentDetails(Integer pcCode)throws Exception;
	 public List<ParliamentConstituency> getParliamentConstituencyDetail(Integer slc);
	 public Boolean checkMapConfigurationforConstituenc(Integer stateCode,char constituencyType) throws Exception;
	 public boolean parliamentExist(int slc, String parliamentName,char type);
	 public Integer getEciCodeParliament(Integer entityCode,char type) throws Exception; 
	 public List<SearchElectionConstituency> getElectionConstituencySearchDetailByCode(
				int enityCodeForSearch, String entityCode) throws Exception ;


}
