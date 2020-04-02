package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.AssemblyConstituency;
import in.nic.pes.lgd.bean.ConstituencyCoverage;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.SaveAssemblyConstituency;
import in.nic.pes.lgd.bean.constituencyReplacedby;
import in.nic.pes.lgd.bean.constituencyReplaces;
import in.nic.pes.lgd.dao.AssemblyDAO;
import in.nic.pes.lgd.forms.AssemblyForm;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pes.attachment.util.AttachedFilesForm;

@Repository
public class AssemblyDaoImpl implements AssemblyDAO {
	private static final Logger log = Logger.getLogger(AssemblyDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	/*public boolean addParliament(ParliamentConstituency parliamentconstituency)
			throws Exception {
		Session session = null;
		Transaction tx = null;
		Object obj;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			try {
				session.save(parliamentconstituency);
				tx.commit();
			} catch (Exception e) {
				log.debug("Exception" + e);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(tx != null){
				tx.rollback();
			}	
			return false;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return true;
	}*/

	/*public boolean addgovernment(GovernmentOrder governmentorder)
			throws Exception {
		Session session = null;
		Object obj;
		try {
			session = sessionFactory.openSession();
			try {
				session.save(governmentorder);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return true;
	}*/

	public int getMaxacCode1() throws Exception {
		int maxCode = 0;
		Session session = null;
		//List list = null;
		Query query;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select COALESCE(max(order_code),1) from government_order");
			//list = query.list();
			maxCode = (Integer)query.uniqueResult(); //Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		}finally{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return maxCode;
	}

	public int getMaxacCode() throws Exception {
		int MaxCode = 0;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			MaxCode = Integer
					.parseInt(session
							.createSQLQuery(
									"select COALESCE(max(ac_code),1) from assembly_constituency")
							.list().get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return MaxCode;
	}

	/*@SuppressWarnings("unchecked")
	public List<AssemblyConstituency> viewparliamentDetails(String sql)
			throws Exception

	{
		List<AssemblyConstituency> lstSd = new ArrayList<AssemblyConstituency>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			lstSd = session.createQuery(sql).list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return lstSd;

	}*/

	/*public List<SearchElectionConstituency> getElectionConstituencySearchDetail(
			String entityName, String entityCode) throws Exception {
		 
		Session session = null;
		List list = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			entityCode = entityCode == "PC" ? "P" : entityCode;
			query = session
					.getNamedQuery("getElectionConstituencySearchDetails")
					.setParameter("entityName", entityName)
					.setParameter("entityCode", entityCode);
			list = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return list;
	}
*/
	@Override
	public boolean publishAssembly(AssemblyConstituency AssemblyConstituency,
			Session session) throws Exception {
		try {
			try {
				session.save(AssemblyConstituency);
				session.flush();
				session.refresh(AssemblyConstituency);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}  
		return true;
	}

	/*@Override
	public int getMaxRecords(String query) throws Exception {
		int MaxCode = 0;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			MaxCode = Integer.parseInt(session.createSQLQuery(query).list()
					.get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return MaxCode + 1;
	}
*/
	@Override
	public int save(constituencyReplacedby constituencyReplacedby,
			Session session) throws Exception {
		int lrReplacedBy = 0;
		try {
			session.saveOrUpdate(constituencyReplacedby);
			session.flush();
			session.refresh(constituencyReplacedby);
			lrReplacedBy = Integer
					.parseInt(session
							.createSQLQuery(
									"select COALESCE(max(constituency_replacedby),1) from constituency_replacedby")
							.list().get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		session.saveOrUpdate(constituencyReplacedby);
		session.flush();
		session.refresh(constituencyReplacedby);
		lrReplacedBy = Integer
				.parseInt(session
						.createSQLQuery(
								"select COALESCE(max(constituency_replacedby),1) from constituency_replacedby")
						.list().get(0).toString());

		return lrReplacedBy;
	}

	@Override
	public int save(constituencyReplaces constituencyReplaces, Session session)
			throws Exception {
		int lrReplacedBy = 0;

		try {
			session.saveOrUpdate(constituencyReplaces);
			session.flush();
			session.refresh(constituencyReplaces);
			lrReplacedBy = Integer
					.parseInt(session
							.createSQLQuery(
									"select COALESCE(max(constituency_replaces),1) from constituency_replaces")
							.list().get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		session.saveOrUpdate(constituencyReplaces);
		session.flush();
		session.refresh(constituencyReplaces);
		lrReplacedBy = Integer
				.parseInt(session
						.createSQLQuery(
								"select COALESCE(max(constituency_replaces),1) from constituency_replaces")
						.list().get(0).toString());

		return lrReplacedBy;
	}

	@Override
	public boolean save(ConstituencyCoverage constituencyCoverage,
			Session session) throws Exception {
		try {

			session.save(constituencyCoverage);
			session.flush();
			session.refresh(constituencyCoverage);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	/*@Override
	public boolean updateLrReplaces(int lrReplaces,
			AssemblyConstituencyId AssemblyConstituencyId, Session session)
			throws Exception {
		AssemblyConstituency AssemblyConstituency = null;
		try {
			AssemblyConstituency = (AssemblyConstituency) session.load(
					AssemblyConstituency.class, AssemblyConstituencyId);
			AssemblyConstituency.setConstituency_replaces(lrReplaces);
			session.update(AssemblyConstituency);
			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
	}*/

	@SuppressWarnings("unchecked")
	public List<AssemblyConstituency> getAssembly(int assemblyCode)
			throws Exception {

		Query criteria = null;
		Session session = null;
		List<AssemblyConstituency> assemblyConstituencyList = null;
		try {
			//Tanuj changed query
			session = sessionFactory.openSession();
			criteria = session
					.createQuery("from AssemblyConstituency sd where sd.isactive=true and sd.plc=:assemblyCode");
			criteria.setParameter("assemblyCode", assemblyCode,
					Hibernate.INTEGER);
			assemblyConstituencyList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}finally{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return assemblyConstituencyList;
		 

	}

/*	@Override
	public boolean updateIsActive(boolean isActive,
			AssemblyConstituencyId AssemblyConstituencyId, Session session)
			throws Exception {
		AssemblyConstituency assemblyConstituency = null;
		try {
			assemblyConstituency = (AssemblyConstituency) session.load(
					AssemblyConstituency.class, AssemblyConstituencyId);
			assemblyConstituency.setIsactive(isActive);
			session.update(assemblyConstituency);
			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
	}*/
	
	public int saveAssemblyConstituency(int pcCode, int userID,
			String newAssemblyNameEnglish, String fullAssemblyConstList,
			String newAssemblyNameLocal, Integer ec_code, String coordinates,
			Session session,String attachFile,String partAssemblyConstList,int mapAttachmentid) throws Exception 
			{
		  SaveAssemblyConstituency saveAssemblyConstituency = null ;
			Query query = null;
			 
			try {
			
				query = session
						.getNamedQuery("saveAssemblyConstituency")
						.setParameter("pcCode", pcCode,
								Hibernate.INTEGER)
						.setParameter("userId", userID,
								Hibernate.INTEGER)
						.setParameter("acNameEnglish", newAssemblyNameEnglish,
								Hibernate.STRING)	
						.setParameter("acList", fullAssemblyConstList,
								Hibernate.STRING)
						.setParameter("acNameLocal", newAssemblyNameLocal,
								Hibernate.STRING)
						.setParameter("eciCode", ec_code,
								Hibernate.INTEGER)
						.setParameter("coordinates", coordinates,
								Hibernate.STRING)
						.setParameter("uploadMap", null, Hibernate.STRING)
						.setParameter("acListPart", partAssemblyConstList,
								Hibernate.STRING)
						.setParameter("mapAttachmentid", mapAttachmentid,
								Hibernate.INTEGER);
								//.setParameter("uploadMap", attachFile, Hibernate.STRING)
				 
				@SuppressWarnings("unchecked")
				List<SaveAssemblyConstituency> list1=query.list();
				session.flush();
				if (!list1.isEmpty()) {
					saveAssemblyConstituency = (SaveAssemblyConstituency) list1.get(0);
					}				
				
				 
			} catch (Exception e) {
				log.debug("Exception" + e);
				 throw e;
			}
			
			return saveAssemblyConstituency.getSet_assembly_constituency_fn();
	
			}
	
	@Override
	public int saveDataInMapAttach(AssemblyForm assemblyConstituency,
			List<AttachedFilesForm> attachedList, HttpSession httpsession, Session session) {
		//Query query = null;
		MapAttachment mapAttachment = null;
		//int pcCodeid=0;
		int mapAttachmentid=0;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					mapAttachment = new MapAttachment();
					mapAttachment.setFileName(filesForm.getFileName());
					mapAttachment.setFileSize(filesForm.getFileSize());
					mapAttachment.setFileContentType(filesForm.getFileType());
					mapAttachment.setFileLocation(filesForm.getFileLocation());
					mapAttachment.setFileTimestamp(filesForm
							.getFileTimestampName());
					
					mapAttachment.setMapEntityType('A');
					mapAttachment = (MapAttachment) session.merge(mapAttachment);
					Long attachmentId = mapAttachment.getAttachmentId();
					mapAttachmentid = attachmentId.intValue();
					mapAttachmentid = (int) mapAttachment.getAttachmentId();
					
				}
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return mapAttachmentid;
	}
	
	/*public String getAssemblyDetails(Integer acCode)throws Exception
	{
		Session session = null;
		Query query = null;
		String dlc="";
		String queryString=null;
		List<Object[]>  temp=null;
	
		try {
			
			queryString="select assembly_constituency.ac_name_english,parliament_constituency.pc_name_english,state.state_name_english from assembly_constituency INNER JOIN  parliament_constituency"
						+" on  assembly_constituency.plc=parliament_constituency.pc_code LEFT JOIN  state  on parliament_constituency.slc=state.slc "
						+" where state.isactive=true and assembly_constituency.ac_code="+acCode;

			session = sessionFactory.openSession();
			query = session
				.createSQLQuery(queryString);
		
		
		temp = query.list();
		
		
		if(temp.size()>0)
		{
			
			for (int k = 0; k < temp.size(); k++) {
				Object obj[] = temp.get(k);
				dlc+=obj[0].toString()+"~"+obj[1].toString()+"~"+obj[2].toString();
			}
			
			if(dlc.length()>0)
			dlc=dlc.substring(0,dlc.lastIndexOf("~"));
			
		}
			
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		
		return dlc;
	
	}*/
	
	
	/**
	 * @author Maneesh kumar 
	 * @since 01Feb2016
	 * This method populate AssemblyConstituency List by Parliamen Code 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AssemblyConstituency> getAssemblyConstituencyListbyParliamenCodet(Integer pcCode) throws Exception {
		Session session=null;
		List<AssemblyConstituency> assemblyConstituencyList=null;
		try{
			session = sessionFactory.openSession();
			Query query=session.createQuery("from AssemblyConstituency where plc=:pcCode and isactive=true order by acNameEnglish");
			query.setParameter("pcCode", pcCode);
			assemblyConstituencyList=query.list();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return assemblyConstituencyList;
		
	}
	
	
}
