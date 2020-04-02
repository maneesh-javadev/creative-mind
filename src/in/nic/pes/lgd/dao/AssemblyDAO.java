package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.AssemblyConstituency;
import in.nic.pes.lgd.bean.ConstituencyCoverage;
import in.nic.pes.lgd.bean.constituencyReplacedby;
import in.nic.pes.lgd.bean.constituencyReplaces;
import in.nic.pes.lgd.forms.AssemblyForm;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import pes.attachment.util.AttachedFilesForm;

public interface AssemblyDAO {
// TODO Remove unused code found by UCDetector
// 	public boolean addParliament(ParliamentConstituency parliamentconstituency) throws Exception;
/*	public boolean addgovernment(GovernmentOrder governmentorder) throws Exception;*/
	public int getMaxacCode() throws Exception;
	public int getMaxacCode1() throws Exception;
// TODO Remove unused code found by UCDetector
// 	public List<AssemblyConstituency> viewparliamentDetails(String sql) throws Exception;

	
	/*public List<SearchElectionConstituency> getElectionConstituencySearchDetail(String entityName,String entityCode) throws Exception;*/
	public boolean publishAssembly(AssemblyConstituency parliamentConstituency, Session ses) throws Exception;
// TODO Remove unused code found by UCDetector
// 	public int getMaxRecords(String query) throws Exception;
	public int save(constituencyReplacedby constituencyReplacedby, Session session) throws Exception;
	public int save(constituencyReplaces constituencyReplaces, Session session) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public boolean updateIsActive(boolean isActive, AssemblyConstituencyId subdistrictPK, Session session) throws Exception;
	
	
	/*public boolean updateLrReplaces(int lrReplaces, AssemblyConstituencyId ParliamentConstituencyId, Session session) throws Exception;*/
	
	public boolean save(ConstituencyCoverage constituencyCoverage, Session session) throws Exception; // NO_UCD (unused code)
	public List<AssemblyConstituency> getAssembly(int districtCode) throws Exception;
	public int saveAssemblyConstituency(int statecode, int userID,
			String newAssemblyNameEnglish, String fullAssemblyConstList,
			String newAssemblyNameLocal, Integer ec_code, String coordinates,
			Session session,String attachFile,String partAssemblyConstList, int mapAttachmentid) throws Exception;
	
	public int saveDataInMapAttach(AssemblyForm assemblyConstituency,
			List<AttachedFilesForm> attachedList, HttpSession httpsession, Session session) throws Exception;
	/*public String getAssemblyDetails(Integer acCode)throws Exception;*/
	public List<AssemblyConstituency> getAssemblyConstituencyListbyParliamenCodet(Integer pcCode) throws Exception; //function add for download directory 1feb2016 by MK 
}
