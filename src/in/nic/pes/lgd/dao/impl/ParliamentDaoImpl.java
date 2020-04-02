package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.AssemblyConstituency;
import in.nic.pes.lgd.bean.AssemblyConstituencyId;
import in.nic.pes.lgd.bean.ConfigurationMapConstituency;
import in.nic.pes.lgd.bean.ConstituencyCoverage;
import in.nic.pes.lgd.bean.ConstituencyCoverageEntity;
import in.nic.pes.lgd.bean.ConstituencyCoverageEntitypartdetails;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.ParliamentConstituency;
import in.nic.pes.lgd.bean.ParliamentConstituencyId;
import in.nic.pes.lgd.bean.SaveParliamentConstituency;
import in.nic.pes.lgd.bean.SearchElectionConstituency;
import in.nic.pes.lgd.bean.constituencyReplacedby;
import in.nic.pes.lgd.bean.constituencyReplaces;
import in.nic.pes.lgd.dao.ParliamentDAO;
import in.nic.pes.lgd.forms.ParliamentForm;
import in.nic.pes.lgd.service.CommonService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pes.attachment.util.AttachedFilesForm;

@Repository
public class ParliamentDaoImpl implements ParliamentDAO {
	
	private static final Logger log = Logger.getLogger(ParliamentDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private CommonService commonService;

	public boolean addParliament(ParliamentConstituency parliamentconstituency)
			throws Exception {
		Session session = null;
		Transaction tx = null;
		//Object obj;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.save(parliamentconstituency);
			tx.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(tx != null){
				tx.rollback();
			}	
			return false;
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		return true;
	}

	public boolean addgovernment(GovernmentOrder governmentorder)
			throws Exception {
		Session session = null;
		Transaction tx = null;
		//Object obj;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
		 
				session.save(governmentorder);
				tx.commit();
			 

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(tx != null){
				tx.rollback();
			}	
			return false;
		} finally {
			if(session != null && session.isOpen())
			{	
				session.close();
			}	
		}
		return true;
	}

	public int getMaxacCode1() throws Exception {
		int MaxCode = 0;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery("select COALESCE(max(order_code),1) from government_order");
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (list.get(0) != null){
				MaxCode = Integer.parseInt(list.get(0).toString());
			}	
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session!=null && session.isOpen()){
				session.close();
			}	
		}
		return MaxCode;
	}

	public int getMaxacCode() throws Exception {
		int MaxCode = 0;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			MaxCode = Integer
					.parseInt(session
							.createSQLQuery(
									"select COALESCE(max(pc_code),1) from parliament_constituency")
							.list().get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session!=null && session.isOpen()){
				session.close();
			}	
		}
		return MaxCode;
	}

	@SuppressWarnings("unchecked")
	public List<ParliamentConstituency> viewparliamentDetails(String sql)
			throws Exception {
		List<ParliamentConstituency> lstSd = new ArrayList<ParliamentConstituency>();
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery(sql);
			lstSd = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session!=null && session.isOpen()){
				session.close();
			}	
		}
		return lstSd;

	}

	@SuppressWarnings("unchecked")
	public List<District> viewparliamentDetail(String sql) throws Exception {
		List<District> lstSd = new ArrayList<District>();
		Session session = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.createQuery(sql);
			lstSd = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session!=null && session.isOpen()){
				session.close();
			}	
		}
		return lstSd;

	}

	@SuppressWarnings("unchecked")
	public List<SearchElectionConstituency> getElectionConstituencySearchDetail(
			String entityName, String entityCode) throws Exception {

		Session session = null;
		Query query = null;
		List<SearchElectionConstituency> getElectionConstituencySearchDetail = null;

		try {
			session = sessionFactory.openSession();
			entityCode = entityCode == "PC" ? "P" : entityCode;
			query = session
					.getNamedQuery("getElectionConstituencySearchDetails")
					.setParameter("entityName", entityName)
					.setParameter("entityCode", entityCode);
			getElectionConstituencySearchDetail = query.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if(session!=null && session.isOpen()){
				session.close();
			}	
		}
		return getElectionConstituencySearchDetail;
	}
	
	@SuppressWarnings("unchecked")
	public List<SearchElectionConstituency> getElectionConstituencySearchDetailByCode(
			int enityCodeForSearch, String entityCode) throws Exception {

		Session session = null;
		Query query = null;
		List<SearchElectionConstituency> getElectionConstituencySearchDetail = null;

		try {
			session = sessionFactory.openSession();
			entityCode = entityCode == "PC" ? "P" : entityCode;
			query = session
					.getNamedQuery("getElectionConstituencySearchDetailsByCode")
					.setParameter("enityCodeForSearch", enityCodeForSearch)
					.setParameter("entityCode", entityCode);
			getElectionConstituencySearchDetail = query.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if(session!=null && session.isOpen()){
				session.close();
			}	
		}
		return getElectionConstituencySearchDetail;
	}

	@Override
	public boolean publishParliament(
			ParliamentConstituency parliamentConstituency, Session session)
			throws Exception {

		try {
			session.save(parliamentConstituency);
			session.flush();
			session.refresh(parliamentConstituency);

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	@Override
	public int getMaxRecords(String query) throws Exception {
		int MaxCode = 0;
		Session session = sessionFactory.openSession();
		try {
			MaxCode = Integer.parseInt(session.createSQLQuery(query).list()
					.get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxCode + 1;
	}

	@Override
	public int save(constituencyReplacedby constituencyReplacedby,
			Session session) throws Exception {
		int lrReplacedBy = 0;

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
			session.contains(constituencyReplaces);
			session.evict(constituencyReplaces);
			lrReplacedBy = Integer
					.parseInt(session
							.createSQLQuery(
									"select max(constituency_replaces) from constituency_replaces")
							.list().get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		session.saveOrUpdate(constituencyReplaces);
		session.flush();
		session.contains(constituencyReplaces);
		session.evict(constituencyReplaces);

		lrReplacedBy = Integer
				.parseInt(session
						.createSQLQuery(
								"select COALESCE(max(constituency_replaces),1) from constituency_replaces")
						.list().get(0).toString());

		return lrReplacedBy;
	}

	public boolean saveconstituency(ConstituencyCoverage constituencyCoverage,
			Session session) throws Exception {
		try {
			session.save(constituencyCoverage);
			session.flush();
			session.contains(constituencyCoverage);
			session.evict(constituencyCoverage);

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	public boolean saveconstituencypartdetail(
			ConstituencyCoverageEntitypartdetails constituencyCoveragepartdeatil,
			Session session) throws Exception {
		try {

			session.save(constituencyCoveragepartdeatil);
			session.flush();
			session.refresh(constituencyCoveragepartdeatil);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	@Override
	public boolean save(ConstituencyCoverageEntity ConstituencyCoverageEntity,
			Session session) throws Exception {
		try {

			session.save(ConstituencyCoverageEntity);
			session.flush();
			session.contains(ConstituencyCoverageEntity);
			session.evict(ConstituencyCoverageEntity);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	@Override
	public ParliamentConstituency getPcDetail(
			ParliamentConstituencyId ParliamentConstituencyPk, Session session)
			throws Exception {
		ParliamentConstituency parliamentConstituency = null;
		try {
			parliamentConstituency = (ParliamentConstituency) session.load(
					ParliamentConstituency.class, ParliamentConstituencyPk);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return parliamentConstituency;
	}

	@Override
	public boolean updateLrReplaces(int lrReplaces,
			ParliamentConstituencyId ParliamentConstituencyId, Session session)
			throws Exception {

		try {
			ParliamentConstituency ParliamentConstituency = (ParliamentConstituency) session
					.load(ParliamentConstituency.class,
							ParliamentConstituencyId);
			ParliamentConstituency.setConstituency_replaces(lrReplaces);

			session.update(ParliamentConstituency);
			return true;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return false;
		}
	}

	@Override
	public boolean updateAssembly(boolean isActive,
			AssemblyConstituencyId assemblyConstituencyId, Session session)
			throws Exception {
		AssemblyConstituency assemblyConstituency = (AssemblyConstituency) session
				.load(AssemblyConstituency.class, assemblyConstituencyId);
		assemblyConstituency.setIsactive(isActive);
		try {
			session.update(assemblyConstituency);
			return true;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return false;
		}
	}

	@Override
	public boolean updateIsActive(boolean isActive,
			ParliamentConstituencyId ParliamentConstituencyId, Session session)
			throws Exception {
		ParliamentConstituency ParliamentConstituency = (ParliamentConstituency) session
				.load(ParliamentConstituency.class, ParliamentConstituencyId);
		ParliamentConstituency.setIsactive(isActive);
		try {
			session.update(ParliamentConstituency);
			return true;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return false;
		}
	}

	public int saveParliamentConstituency(int statecode, int userId,
			String pcNameEnglish, String fullParliamentConstList, String partParliamentConstList,
			String fullAssemblyConstList, String pcNameLocal, Integer eciCode,
			String coordinates, List<CommonsMultipartFile> attachedFile,
			Session session, int mapAttachmentCode) throws Exception {
		SaveParliamentConstituency result = null;
		Query query = null;

		try {

			query = session
					.getNamedQuery("saveParliamentConstituency")
					.setParameter("stateCode", statecode, Hibernate.INTEGER)
					.setParameter("userId", userId, Hibernate.INTEGER)
					.setParameter("pcNameEnglish", pcNameEnglish,
							Hibernate.STRING)
					.setParameter("pcList", fullParliamentConstList,
							Hibernate.STRING)
							.setParameter("pcPartList", partParliamentConstList,
							Hibernate.STRING)
					.setParameter("acList", fullAssemblyConstList,
							Hibernate.STRING)
					.setParameter("pcNameLocal", pcNameLocal, Hibernate.STRING)
					.setParameter("eciCode", eciCode, Hibernate.INTEGER)
					.setParameter("coordinates", coordinates, Hibernate.STRING)
					//.setParameter("uploadMap", attachedFile, Hibernate.STRING)
					.setParameter("mapAttachmentCode", mapAttachmentCode, Hibernate.INTEGER);

			@SuppressWarnings("unchecked")
			List<SaveParliamentConstituency> list1 = query.list();

			if (!list1.isEmpty()) {
				result = (SaveParliamentConstituency) list1.get(0);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			throw e;
		}

		return result.getSet_parliament_constituency_fn();

	}

	@Override
	public int saveDataInMapAttach(ParliamentForm parliamentConstituency,
			List<AttachedFilesForm> attachedList, HttpSession httpsession,Session sessionObj) {
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
					mapAttachment.setMapEntityType('P');
					mapAttachment = (MapAttachment) sessionObj.merge(mapAttachment);
					Long attachmentId = mapAttachment.getAttachmentId();
					mapAttachmentid = attachmentId.intValue();
					
					mapAttachmentid = (int) mapAttachment.getAttachmentId();
					//pcCodeid = parliamentConstituency.getPcCode();
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return mapAttachmentid;
	}
	
	@SuppressWarnings("unchecked")
	public boolean existECICODEDAO(Integer EntityCode,Integer eciCode,char Type) throws Exception 
	{
		Session session=null;
		Query query=null;
		session = sessionFactory.openSession();
		Integer slc=null;
	    boolean flag=false;
		
		try
		{
			
			if(Type=='P')
			{
				List<ParliamentConstituency> parliamentConstituencyList=null;
				slc=commonService.getSlc(EntityCode);
				if(slc!=null)
				{
				query = session
						.createQuery("from ParliamentConstituency p where p.slc=:slc and p.isactive=true order by eciCode");
				query.setParameter("slc", slc, Hibernate.INTEGER);
				parliamentConstituencyList=query.list();
				
				
					for(ParliamentConstituency element : parliamentConstituencyList)
					{
						if(element.getEciCode()!=null)
						{
							if((element.getEciCode()).equals(eciCode))
							{
								flag=true;
								break;
							}
						}
					
					}
				}
				else
				{
					flag=true;
				}
			}
			else if(Type=='A')
			{
				List<AssemblyConstituency> assemblyConstituencyList=null;
				query = session
						.createQuery("from AssemblyConstituency a where a.plc=:plc and a.isactive=true order by eciCode");
				query.setParameter("plc", EntityCode, Hibernate.INTEGER);
				assemblyConstituencyList=query.list();
				for(AssemblyConstituency element : assemblyConstituencyList)
				{
					if(element.getEciCode()!=null)
					{
						if((element.getEciCode()).equals(eciCode))
						{
							flag=true;
							break;
						}
					}
					
				}
				
			}
			
			
		}catch(Exception e)
		{
			log.debug("Exception" + e);
			flag=true;
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return flag;
	}
	@SuppressWarnings("unchecked")
	@Override
	public int ParConstituencyCoverageExist(int coverageCode, char type,Session session2)
	{
		Query criteria = null;
		Session session = null;	
		List <ConstituencyCoverage> conCover = new ArrayList<ConstituencyCoverage>();
		int cccode=0;
						
			try {
				session = sessionFactory.openSession();
				criteria = session.createQuery("from ConstituencyCoverage ccv where ccv.constituencyLc=:coverageCode and ccv.constituencyType=:type and isactive=true ");
				criteria.setParameter("coverageCode", coverageCode, Hibernate.INTEGER);
				criteria.setParameter("type", type,
						Hibernate.CHARACTER);
				conCover= criteria.list();
				int size = conCover.size();
				if(size > 0){
						cccode = conCover.get(0).getCcCode();		
				}	
						
			} catch (HibernateException e) {
				log.debug("Exception" + e);
			} finally {
				if (session != null && session.isOpen()){
					session.close();
				}	
			}
			return cccode;
	}

	@Override
	public int mapPaliamentLBData(int coverageCode, String deletedObjectRecord, String objectRecord,String deletedobject, Session session) throws Exception {
		Query criteria = null;
		String[] coverage = null;
		String[] coverageElements = null;
		char entitytype;
		String lbcode=null;

		try {

			if (objectRecord != null && objectRecord.length() > 0) {
				coverage = objectRecord.split(";");
				int len = coverage.length;
				ConstituencyCoverageEntity[] coverageEnty = new ConstituencyCoverageEntity[len];
				int entityLc;
				char coveragetype;

				int i = 0;
				for (i = 0; i < len; i++) {
					if (coverage[i] != null) {
						coverageEnty[i] = new ConstituencyCoverageEntity();
						coverageEnty[i].setCccode(coverageCode);
						coverageElements = coverage[i].split(":");
						entityLc = Integer.parseInt(coverageElements[0]);
						coveragetype = coverageElements[2].charAt(0);
						entitytype = coverageElements[1].charAt(0);
						criteria = session.createQuery("from ConstituencyCoverageEntity b where b.entityLc=:elc and b.cccode=:cccode and b.entitytype=:etype  and isactive=true ");
						criteria.setParameter("elc", entityLc, Hibernate.INTEGER);
						criteria.setParameter("cccode", coverageCode, Hibernate.INTEGER);
						criteria.setParameter("etype", entitytype, Hibernate.CHARACTER);
						List<?> list = criteria.list();
						int size = list.size();
						if (size == 0) {
							coverageEnty[i].setEntityLc(entityLc);
							coverageEnty[i].setCoveragetype(coveragetype);
							coverageEnty[i].setIsactive(true);
							coverageEnty[i].setEntitytype(entitytype);
							session.save(coverageEnty[i]);
						}
					}

				}
			}

			
			if (!deletedObjectRecord.equals("1")) {
				lbcode = deletedObjectRecord;
				entitytype = lbcode.charAt(lbcode.length() - 1);
				lbcode = lbcode.substring(0, lbcode.length() - 2);
				Query query = session.createSQLQuery("delete from constituency_covered_entity b where b.entity_lc in(" + lbcode + ") and b.cc_code= " + coverageCode + " and b.entity_type='" + entitytype + "' and isactive=true ");
				query.executeUpdate();
			}
			if (!deletedobject.equals("1")) {
				lbcode = deletedobject;
				entitytype = lbcode.charAt(lbcode.length() - 1);
				lbcode = lbcode.substring(0, lbcode.length() - 2);
				Query query = session.createSQLQuery("delete from constituency_covered_entity b where b.entity_lc in(" + lbcode + ") and b.cc_code= " + coverageCode + " and b.entity_type='" + entitytype + "' and isactive=true ");
				query.executeUpdate();
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			return 0;
		} 
		return 1;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean existEntityName(Integer stateCode,String entityName,char EntityType) throws Exception
	{
		Session session=null;
		Query query=null;
		session = sessionFactory.openSession();
		Integer slc=null;
	    boolean flag=false;
		
		try
		{
			slc=commonService.getSlc(stateCode);
			if(slc!=null && slc!=0)
			{
				if(entityName.length()>0){
					entityName=entityName.trim();
				}	
				if(EntityType=='P')
				{
					List<ParliamentConstituency> parliamentConstituencyList=null;
					
					
					query = session
							.createSQLQuery("select * from parliament_constituency where slc="+slc+" and isactive=true and upper(pc_name_english) like upper('"+entityName+"')");
				
					parliamentConstituencyList=query.list();
					if(parliamentConstituencyList.size()>0)
						flag=true;
						else
						flag=false;
					
				}
				else if(EntityType=='A')
				{
					List<AssemblyConstituency> assemblyConstituencyList=null;
					query = session
							.createSQLQuery("select * from assembly_constituency where plc in(select pc_code from parliament_constituency where slc="+slc+" and isactive=true) and isactive=true and upper(ac_name_english) like upper('"+entityName+"')");
					assemblyConstituencyList=query.list();
					if(assemblyConstituencyList.size()>0)
					flag=true;
					else
					flag=false;
					
				}
			}
			
			
			
		}catch(Exception e)
		{
			log.debug("Exception" + e);
			flag=true;
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return flag;
	}
	
	 @SuppressWarnings("unchecked")
	public String getParliamentDetails(Integer pcCode)throws Exception
	 {
		 Session session=null;
			Query query=null;
			String dlc="";
			String queryString=null;
			List<Object[]>  temp=null;
		
			try {
				
				queryString="select parliament_constituency.pc_name_english,state.state_name_english from parliament_constituency LEFT JOIN  state  on parliament_constituency.slc=state.slc "
							+" where state.isactive=true and parliament_constituency.pc_code="+pcCode;

				session = sessionFactory.openSession();
				query = session
					.createSQLQuery(queryString);
			
			
			temp = query.list();
			
			
			if(temp.size()>0)
			{
				
				for (int k = 0; k < temp.size(); k++) {
					Object obj[] = temp.get(k);
					dlc+=obj[0].toString()+"~"+obj[1].toString();
				}
				
			/*	if(dlc.length()>0)
				dlc=dlc.substring(0,dlc.lastIndexOf("~"));*/
				
			}
				
			} catch (Exception e) {
				log.debug("Exception" + e);
			} finally {
				if (session != null && session.isOpen()){
					session.close();
				}	
			}
			
			return dlc;
		
		}
		
	 
	 @SuppressWarnings("unchecked")
	public List<ParliamentConstituency> getParliamentConstituencyDetail(Integer slc)
		
	 {
		 Query criteria = null;
			Session session = null;	
			List<ParliamentConstituency> parliamentConstituencyDetail=null;
			
							
				try {
					session = sessionFactory.openSession();
					criteria = session.createQuery("from ParliamentConstituency where slc=:slc and isactive=true order by pcNameEnglish ");
					criteria.setParameter("slc", slc, Hibernate.INTEGER);
					
					parliamentConstituencyDetail= criteria.list();
				
						
							
				} catch (HibernateException e) {
					log.debug("Exception" + e);
				} finally {
					if (session != null && session.isOpen()){
						session.close();
					}	
				}
				return parliamentConstituencyDetail;
	 }
	 
	 @SuppressWarnings("unchecked")
	public Boolean checkMapConfigurationforConstituenc(Integer stateCode,char constituencyType) throws Exception
	 {
		 	Session session=null;
			Query criteria=null;
			Boolean mapConfig=null;
			List<ConfigurationMapConstituency> configurationMapConstituencyList=null;
		    Integer slc;
			try {
				
													
							slc=commonService.getSlc(stateCode);
							session = sessionFactory.openSession();
							criteria = session.createQuery("from ConfigurationMapConstituency where slc=:slc and isactive=:isactive and constituencyType=:constituencyType")
										.setParameter("slc", slc, Hibernate.INTEGER)
										.setParameter("isactive", true,Hibernate.BOOLEAN)
										.setParameter("constituencyType", constituencyType,Hibernate.CHARACTER);
							     
							
							configurationMapConstituencyList= criteria.list();
			
			
			if(configurationMapConstituencyList.size()>0)
			{
				
				mapConfig=configurationMapConstituencyList.get(0).isIsmapupload();
				
				
			/*	if(dlc.length()>0)
				dlc=dlc.substring(0,dlc.lastIndexOf("~"));*/
				
			}
			
				
			} catch (Exception e) {
				log.debug("Exception" + e);
			} finally {
				if (session != null && session.isOpen()){
					session.close();
				}	
			}
			
			return mapConfig;
		
	 }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean parliamentExist(int slc, String constituencyName, char type) {
		Query criteria = null;
		Session session = null;
		constituencyName = constituencyName.trim().toUpperCase();
		String sql = null;
		boolean success = true;
		Query query = null;
		try {
			session = sessionFactory.openSession();

			if (type == 'P') {
				sql = "from ParliamentConstituency pc where pc.slc=:slc and isactive=true and  UPPER(TRIM(pc.pcNameEnglish)) =:constituencyName";
				criteria = session.createQuery(sql);
				criteria.setParameter("slc", slc, Hibernate.INTEGER);
				criteria.setParameter("constituencyName", constituencyName,
						Hibernate.STRING);
				List list = criteria.list();
				int size = list.size();
				if (size > 0){
					success = false;
				}
			} else if (type == 'A') {
				List<AssemblyConstituency> assemblyConstituencyList = null;
				query = session
						.createSQLQuery("select * from assembly_constituency where plc in(select pc_code from parliament_constituency where slc="
								+ slc
								+ " and isactive=true) and isactive=true and upper(ac_name_english) like upper('"
								+ constituencyName + "')");
				assemblyConstituencyList = query.list();
				if (assemblyConstituencyList.size() > 0){
					success = false;
				}	

			}

		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return success;
	}
	
	public Integer getEciCodeParliament(Integer entityCode,char type) throws Exception 
	{
		Query criteria = null;
		Session session = null;
		Integer eciCode=null;
		String sql = null;
		//boolean success = true;
		//Query query = null;
		try {
			session = sessionFactory.openSession();

			if (type == 'P') {
				sql = "from ParliamentConstituency  where pcCode=:pcCode and isactive=true";
				criteria = session.createQuery(sql);
				criteria.setParameter("pcCode", entityCode, Hibernate.INTEGER);
				
				@SuppressWarnings("unchecked")
				List<ParliamentConstituency> list = criteria.list();
				if(list.size()>0)
				{
					eciCode=list.get(0).getEciCode();
				}
			}
			
			if(type=='A')
			{
				sql = "from AssemblyConstituency  where acCode=:acCode and isactive=true";
				criteria = session.createQuery(sql);
				criteria.setParameter("acCode", entityCode, Hibernate.INTEGER);
				
				@SuppressWarnings("unchecked")
				List<AssemblyConstituency> list = criteria.list();
				if(list.size()>0)
				{
					eciCode=list.get(0).getEciCode();
				}
			}
			
			return eciCode;
			
		}catch(Exception e)
		{
			throw new Exception(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
	}
				
}
