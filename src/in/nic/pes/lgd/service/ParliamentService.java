package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.ParliamentConstituency;
import in.nic.pes.lgd.bean.SearchElectionConstituency;
import in.nic.pes.lgd.forms.ParliamentForm;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import pes.attachment.util.AttachedFilesForm;

public interface ParliamentService {
	public void addgovernment(GovernmentOrder governmentorder)throws Exception;
	public List<SearchElectionConstituency> getElectionConstituencySearchDetail(String entityName,String entityCode)throws Exception;
	 
	boolean saveReplacedBy(int id, int lrReplacedBy, int sdCode,
			int sdVersionCode, Session session)throws Exception;
	
	boolean saveReplaces(int id, int lrReplaces, int sdCode, int pcversion,
			Session session)throws Exception;
	boolean saveAssemblyPartContri(int assCode, int newassCode,
			int newSDVersion, Session session)throws Exception;
	public boolean addParliament(ParliamentForm parliamentConstituency,
			int stateCode, List<AttachedFilesForm> metaInfoOfToBeAttachedMapList,int mapAttachmentid,Session session) throws Exception;
	boolean addParliament(ParliamentForm pcForm, int statecode)
			throws Exception;
	public int saveDataInMapAttach(ParliamentForm parliamentConstituency,List<AttachedFilesForm> attachedList, HttpSession httpSession,Session session) throws Exception; 
	public boolean existECICODE(Integer EntityCode,Integer eciCode,char Type) throws Exception;
	  public boolean existEntityName(Integer stateCode,String entityName,char EntityType) throws Exception;
	  public String getParliamentDetails(Integer pcCode)throws Exception;
	  public List<ParliamentConstituency> getParliamentConstituencyDetail(Integer slc);
	  public Boolean checkMapConfigurationforConstituenc(Integer stateCode,char constituencyType) throws Exception;
	  public boolean parliamentExist(int slc, String parliamentName,char type);
	  public Integer getEciCodeParliament(Integer pcCode,char type) throws Exception;
	  public List<SearchElectionConstituency> getElectionConstituencySearchDetailByCode(int entitycodeforsearch,String entityCode) throws Exception;
}
