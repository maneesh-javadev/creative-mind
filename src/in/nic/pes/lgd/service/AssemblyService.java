package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.AssemblyConstituency;
import in.nic.pes.lgd.forms.AssemblyForm;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import pes.attachment.util.AttachedFilesForm;

public interface AssemblyService {
// TODO Remove unused code found by UCDetector
// 	public void addgovernment(GovernmentOrder governmentorder) throws Exception;
// TODO Remove unused code found by UCDetector
// 	public List<SearchElectionConstituency> getElectionConstituencySearchDetail(String entityName,String entityCode)throws Exception;
	boolean saveReplacedBy(int id, int lrReplacedBy, int sdCode, // NO_UCD (unused code)
			int sdVersionCode, Session session)throws Exception;
	
	boolean saveReplaces(int id, int lrReplaces, int sdCode, int pcversion, // NO_UCD (unused code)
			Session session)throws Exception;
	public boolean addassembly(AssemblyForm parliamentConstituency,int statecode, List<AttachedFilesForm> attachedList, int mapAttachmentid, Session session)throws Exception;
	public List<AssemblyConstituency> getAssembly(String assemblyCode)throws Exception; // NO_UCD (unused code)
	
	public List<AssemblyConstituency> getAssemblyconstuincy(String districtCode)throws Exception; // NO_UCD (unused code)
// TODO Remove unused code found by UCDetector
// 	boolean addassembly(AssemblyForm pcForm, int stateCode) throws Exception;

	public int saveDataInMapAttach(AssemblyForm assemblyConstituency,List<AttachedFilesForm> attachedList, HttpSession httpsession, Session session) throws Exception;
// TODO Remove unused code found by UCDetector
// 	 public String getAssemblyDetails(Integer acCode)throws Exception;
	public List<AssemblyConstituency> getAssemblyConstituencyListbyParliamenCodet(Integer pcCode) throws Exception; //function add for download directory 1feb2016 by MK 
}
