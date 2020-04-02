package in.nic.pes.lgd.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.nic.pes.common.menu.pojo.MenuProfile;
import in.nic.pes.lgd.bean.AllSearch;
import in.nic.pes.lgd.bean.AssemblyConstituency;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AuditTrailLGD;
import in.nic.pes.lgd.bean.ChangeState;
import in.nic.pes.lgd.bean.ConfigurationBlockVillageMapping;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictFreezeEntity;
import in.nic.pes.lgd.bean.DistrictPK;
import in.nic.pes.lgd.bean.EntityFreezeStatus;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.FreezeLocalBodyEntity;
import in.nic.pes.lgd.bean.FreezeUnfreezeStateConfigEntity;
import in.nic.pes.lgd.bean.GeneratedFileDetails;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetLBHierarchy;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.LgdDataConfirmation;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.NodalOfficerState;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.OrganizationByCentreLevel;
import in.nic.pes.lgd.bean.ParentwiseChildDetials;
import in.nic.pes.lgd.bean.ParliamentConstituency;
import in.nic.pes.lgd.bean.Search;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StateHistory;
import in.nic.pes.lgd.bean.StateNamed;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.SubdistrictPK;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillagePK;
import in.nic.pes.lgd.common.MailService;
import in.nic.pes.lgd.common.SmsManager;
import in.nic.pes.lgd.dao.DistrictDAO;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.draft.constant.DraftUtils;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.StateDataForm;
import in.nic.pes.lgd.forms.StateForm;
import in.nic.pes.lgd.menu.MenuDisableTable;
import in.nic.pes.lgd.resourcebundles.LGDResourceBundle;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.BASE64;
import pes.attachment.util.AttachedFilesForm;

@Repository
@Transactional
public class StateDAOImpl implements StateDAO {
	private static final Logger log = Logger.getLogger(StateDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CommonService commonService;

	@Autowired
	EmailService emailService;
	
	@Autowired
	DraftUtils draftUtils;
	
	@Autowired
	private DistrictDAO districtDAO;
	
	@Autowired
	  private VillageService villageService;


	@Override
	public String ChangeCrState(int stateCode, String stateNameEnglish, String stateNameEnglishch, int userId, String orderno, Date orderDate, Date efectivDate, Date gazpubDate, String orderPath, String stateOrUt, String stateNameLocal,
			String sortName, Session session, Integer slc) throws Exception {
		Query query = null;

		String data = null;

		try {
			query = session.getNamedQuery("StateChangeQuery").setParameter("stateCode", stateCode, Hibernate.INTEGER).setParameter("stateNameEnglish", stateNameEnglish, Hibernate.STRING).setParameter("userId", userId, Hibernate.INTEGER)
					.setParameter("orderNo", orderno, Hibernate.STRING).setParameter("Orderdate", orderDate, Hibernate.DATE).setParameter("effectiveDate", efectivDate, Hibernate.DATE).setParameter("orderpath", orderPath, Hibernate.STRING)
					.setParameter("stateNameEnglishch", stateNameEnglishch).setParameter("stateOrUt", stateOrUt, Hibernate.STRING).setParameter("slc", slc, Hibernate.INTEGER).setParameter("gazpubDate", gazpubDate, Hibernate.DATE)
					.setParameter("stateNameLocal", stateNameLocal, Hibernate.STRING).setParameter("sortName", sortName, Hibernate.STRING);
			@SuppressWarnings("unchecked")
			List<ChangeState> changestate = query.list();
			if (changestate.size() > 0) {
				data = changestate.get(0).getChange_state_fn().toString();
				/*
				 * String
				 * temp[]=changestate.get(0).getChange_state_fn().toString
				 * ().split(","); tid=Integer.parseInt(temp[0]);
				 * oderCode=Integer.parseInt(temp[1]); char t_type='S';
				 * EmailSmsThread est= new EmailSmsThread(tid,
				 * t_type,emailService); est.start();
				 */

			}
			// ChangeDistrict changedis=(ChangeDistrict) query.list().get(0);
			session.flush();
			if (session.contains(query)) {
				session.evict(query);
			}
			return data;

		} catch (Exception e) {
			log.debug("Exception" + e);
			throw new SQLException("calling function error"); // changed by
																// kirandeep and
																// Sushish
																// shakya
																// 15/10/2014
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StateNamed> createStateDAO(int userid, String state_name_english, String order_no, Date order_date, Date effective_date, char StateOrUt, String census_2011_code, Date gaz_pub_date, String list_of_state_full,
			String list_of_state_part, String list_of_district_full, String short_name, String headQuarterName, Session session) throws Exception {

		Query query = null;
		List<StateNamed> result = null;
		try {
			query = session.getNamedQuery("createState").setParameter("userid", userid, Hibernate.INTEGER).setParameter("state_name_english", state_name_english, Hibernate.STRING).setParameter("order_no", order_no, Hibernate.STRING)
					.setParameter("order_date", order_date, Hibernate.DATE).setParameter("effective_date", effective_date, Hibernate.DATE).setParameter("StateOrUt", StateOrUt, Hibernate.CHARACTER)
					.setParameter("census_2011_code", census_2011_code, Hibernate.STRING).setParameter("gaz_pub_date", gaz_pub_date, Hibernate.DATE).setParameter("list_of_state_full", list_of_state_full, Hibernate.STRING)
					.setParameter("list_of_state_part", list_of_state_part, Hibernate.STRING).setParameter("list_of_district_full", list_of_district_full, Hibernate.STRING)
					.setParameter("short_name", short_name, Hibernate.STRING).setParameter("headquarter_name", headQuarterName, Hibernate.STRING);
			result = query.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			return result;
		}
		return result;
	}

	/* added by kamlesh for finding duplicate state used in dwr on 18-march_13 */
	@Override
	public int findDuplicate(String stateName) {
		Query criteria = null;
		Session session = null;
		stateName = stateName.trim().toUpperCase();
		int recordCount = 0;
		try {
			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select count(*) from state where UPPER(TRIM(state_name_english)) LIKE :stateName");
			criteria.setParameter("stateName", stateName, Hibernate.STRING);
			@SuppressWarnings("rawtypes")
			List list = criteria.list();
			if (!list.isEmpty() && list.get(0) != null) {
				recordCount = Integer.parseInt(list.get(0).toString());
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return recordCount;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AssemblyConstituency> getAllassembly() throws Exception {
		List<AssemblyConstituency> assemplconst = null;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from AssemblyConstituency s where s.isactive =true and pc_code=20 order by  acNameEnglish");
			assemplconst = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return assemplconst;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<State> getAllNotInStates(String stateList) {
		Query sqlQuery = null;
		Session session = null;
		List<State> getAllNotInStates = null;

		try {
			session = sessionFactory.openSession();
			sqlQuery = session.createQuery("from State s where s.isactive =true and " + "stateCode not in (" + stateList + ")order by stateNameEnglish");
			getAllNotInStates = sqlQuery.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getAllNotInStates;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ParliamentConstituency> getAllparliament(int stateCode) throws Exception {
		List<ParliamentConstituency> parlconst = null;
		Session session = null;

		Query query = null;
		try {

			session = sessionFactory.openSession();

			query = session.createQuery("from ParliamentConstituency s where s.isactive =true and slc=" + stateCode + " order by  pcNameEnglish");
			parlconst = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return parlconst;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AssemblyConstituency> getAllparliamentassembly(int stateCode) throws Exception {
		List<AssemblyConstituency> assenblconst = null;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from AssemblyConstituency s where s.isactive =true and pc_code=" + stateCode + " order by  acNameEnglish");

			assenblconst = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return assenblconst;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<State> getAllStates() throws Exception {
		Session session = null;
		Query query = null;
		List<State> stateList = new ArrayList<State>();

		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from State s where s.isactive = true order by stateNameEnglish");
			stateList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return stateList;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<State> getAllStates(int stateCode) throws Exception {
		List<State> state = null;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from State s where s.isactive =true and state_code=" + stateCode + " order by stateNameEnglish");

			state = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return state;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> getAttacmentdetail(int orderCode) throws Exception {
		Session session = null;
		Query query = null;
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Attachment where announcement_id=:orderCode").setParameter("orderCode", orderCode, Hibernate.INTEGER);
			attachmentList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return attachmentList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GeneratedFileDetails> getCBTHtml(String fName, String documentType, int documentId) {

		List<GeneratedFileDetails> arrayList = new ArrayList<GeneratedFileDetails>(0);
		Session sessionObj = sessionFactory.openSession();
		try {
			// System.out.println("::"+fName+"::"+documentType+"::"+documentId);

			/*
			 * rs=statement.executeQuery(
			 * "select * from pescommon.get_all_languages()");
			 */
			Query query = sessionObj.getNamedQuery("getfiledetails").setParameter("documentId", documentId).setParameter("documentType", documentType).setParameter("fName", fName);
			arrayList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
			
		}
		return arrayList;
	}

	@Override
	public int getCurrentVersionbyStateCode(int stateCode) throws Exception {
		int CurrentVersionCode = 0;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select state_version from state where isactive=true and state_code=" + stateCode);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				CurrentVersionCode = (Integer) list.get(0);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return CurrentVersionCode;
	}

	/*
	 * @Override public void ChangeState(int stateCode, String stateNameEnglish,
	 * int userId,String Orderno,Date orderdate,String orderpath,Date
	 * gazPubDate,Date effectivedate, String stateNameLocal, String
	 * aliasEnglish, String aliasLocal, Session session) throws Exception { try
	 * { Query query = session .getNamedQuery("StateChangeQuery")
	 * .setParameter("stateCode", stateCode, Hibernate.INTEGER)
	 * .setParameter("stateNameEnglish", stateNameEnglish,Hibernate.STRING)
	 * .setParameter("userId", userId, Hibernate.INTEGER)
	 * .setParameter("orderNo", Orderno,Hibernate.STRING)
	 * .setParameter("oderDate", orderdate,Hibernate.DATE)
	 * .setParameter("orderpath", "", Hibernate.STRING)
	 * .setParameter("gazPubDate", gazPubDate,Hibernate.DATE)
	 * .setParameter("effectiveDate",effectivedate,Hibernate.DATE)
	 * .setParameter("stateNameLocal", stateNameLocal,Hibernate.STRING)
	 * .setParameter("aliasEnglish", aliasEnglish,Hibernate.STRING)
	 * .setParameter("aliasLocal", aliasLocal, Hibernate.STRING); query.list();
	 * } catch (Exception e) { log.debug("Exception" + e); } }
	 */

	@Override
	public int getCurrentVersionbyStateCode(int stateCode, Session session) throws Exception {
		int CurrentVersionCode = 0;

		Query sqlquery = null;
		try {
			sqlquery = session.createSQLQuery("" + "select state_version from state where isactive=true and state_code=" + stateCode);
			@SuppressWarnings("rawtypes")
			List list = sqlquery.list();
			CurrentVersionCode = Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		return CurrentVersionCode;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<District> getdistrictListbyDistrict(int stateCode) throws Exception {
		List<District> fullSubdistrictContributingList = null;
		Session session = null;
		Query criteria = null;
		try {
			int slc = commonService.getSlc(stateCode);
			session = sessionFactory.openSession();
			criteria = session.createQuery("from District sd where sd.isactive=true and slc=" + slc + " order by sd.districtNameEnglish");
			fullSubdistrictContributingList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return fullSubdistrictContributingList;

	}

	@Override
	public String getDistrictNameEnglishbyStateCode(int district_code) throws Exception {
		Query query = null;
		Session session = null;
		String stateName = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select district_name_english from district where district_code=:district_code and isactive=true").setParameter("district_code", district_code);
			
			@SuppressWarnings("rawtypes")
			List list = query.list();

			if (!list.isEmpty() && list.get(0) != null) {
				stateName = list.get(0).toString();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return stateName;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<State> getDistrictViewList(String query) throws Exception {
		List<State> state = null;
		Query sqlQuery = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			sqlQuery = session.createQuery(query);
			state = sqlQuery.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return state;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode, Session session) throws Exception {
		Query query = null;
		List<EntityWiseMapDetailsPojo> EntityWiseMapDetail = null;
		try {
			query = session.getNamedQuery("getEntityWiseMapDetailsFn").setParameter("entityType", entityType).setParameter("entityCode", entityCode);
			EntityWiseMapDetail = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return EntityWiseMapDetail;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GovernmentOrder> getGovermentOrderDetail(int orderCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<GovernmentOrder> getGovermentOrderDetail = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from GovernmentOrder where order_code=" + orderCode);
			getGovermentOrderDetail = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getGovermentOrderDetail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getGovtOrderByEntityCode(int entityCode, char type) {
		List<Object[]> state = null;
		Session session = null;
		SQLQuery query = null;
		String sql = null;
		try {
			session = sessionFactory.openSession();
			switch (type) {
			case 'S':
				sql = "select Distinct d.file_name, d.file_location from state a, government_order_entitywise b, government_order c, attachment d WHERE "
						+ "a.slc = b.entity_code and b.entity_type='S' and b.entity_version =(select max(entity_version) FROM government_order_entitywise where entity_code =:entityCode and entity_type='S') "
						+ "and b.order_code = c.order_code and c.order_code = d.announcement_id and a.slc =:entityCode ";
				break;

			case 'D':
				sql = "select Distinct a.file_name, a.file_location from district d inner join government_order_entitywise_new ge on d.district_code=ge.entity_code and d.district_version=ge.entity_version " + 
						" and d.minor_version=ge.minor_version inner join attachment a on ge.order_code=a.announcement_id " + 
						" where d.dlc = :entityCode and d.isactive";
				break;

			case 'T':
				sql = " select Distinct a.file_name, a.file_location from subdistrict t inner join government_order_entitywise_new ge on t.subdistrict_code=ge.entity_code and t.subdistrict_version=ge.entity_version " + 
						" and t.minor_version=ge.minor_version inner join attachment a on ge.order_code=a.announcement_id " + 
						" where t.tlc = :entityCode and t.isactive";
				break;

			case 'V':
				sql = "select Distinct a.file_name, a.file_location from village v inner join government_order_entitywise_new ge on v.village_code=ge.entity_code and v.village_version=ge.entity_version " + 
						" and v.minor_version=ge.minor_version inner join attachment a on ge.order_code=a.announcement_id " + 
						" where v.vlc = :entityCode and v.isactive";
				break;
			case 'G':
				sql = "select Distinct d.file_name, d.file_location from localbody a, government_order_entitywise_new b, government_order c, attachment d WHERE "
						+ "a.local_body_code = b.entity_code and b.entity_type='G' and b.entity_version =(select max(local_body_version) FROM localbody where local_body_code = :entityCode and entity_type='G') "
						+ "and b.order_code = c.order_code and c.order_code = d.announcement_id and a.local_body_code = :entityCode";
				break;
			}

			query = session.createSQLQuery(sql);
			query.setParameter("entityCode",entityCode);
			state = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return state;
	}

	@Override
	public String getLBHierarchy(int parentLBCode) throws Exception {
		Query query = null;
		Session session = null;
		String lbName = null;
		try {
			session = sessionFactory.openSession();

			// query =
			// session.createSQLQuery("SELECT get_lb_hierarchy_as_text_rpt FROM get_lb_hierarchy_as_text_rpt(552)");

			query = session.getNamedQuery("getLBHierarchy").setParameter("parentLBCode", parentLBCode);

			// query =
			// session.createSQLQuery("SELECT get_lb_hierarchy_as_text_rpt FROM get_lb_hierarchy_as_text_rpt(552)");

			@SuppressWarnings("unchecked")
			List<GetLBHierarchy> list = query.list();
			// System.out.println("THE PARENT CODE:::: " + query.list().size());

			if (!list.isEmpty() && list.get(0) != null) {
				lbName = list.get(0).getGet_lb_hierarchy_as_text_rpt();
			}
			// System.out.println("THE PARENT name:::: " +
			// lbName.toUpperCase());
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return lbName;
	}

	/* Retrieve files from the attachment table */

	@SuppressWarnings("unchecked")
	@Override
	public List<MapLandRegion> getMapDetailsModify(int mapLandregionCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<MapLandRegion> getMapDetailsModify = null;
		try {
			session = sessionFactory.openSession();
			/*
			 * criteria = session
			 * .createQuery("from MapLandRegion where map_landregion_code=" +
			 * mapLandregionCode);
			 */
			criteria = session.createQuery("from MapAttachment1 where map_attachment_code=" + mapLandregionCode);
			getMapDetailsModify = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getMapDetailsModify;
	}

	@Override
	public int getMaxStateCode(String query) throws Exception {
		int MaxCode = 0;
		Session session = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createSQLQuery(query);
			@SuppressWarnings("rawtypes")
			List list = criteria.list();
			MaxCode = Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return MaxCode;
	}

	@Override
	public int getMaxVersionbyStateCode(int stateCode) throws Exception {
		int MaxVersionCode = 0;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select max(state_version) from state where state_code=:stateCode");
			query.setParameter("stateCode", stateCode);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				MaxVersionCode = (Integer) list.get(0);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return MaxVersionCode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<State> getStatebyname(String name) throws Exception {
		Session session = null;
		Query query = null;
		List<State> stateList = new ArrayList<State>();
		try {
			session = sessionFactory.openSession();

			query = session.createQuery("from State k where isactive=TRUE and k.stateNameEnglish like :name");

			stateList = query.setParameter("name", name + "%").list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return stateList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<State> getStateDetailsModify(int stateCode) throws Exception {
		Session session = null;
		List<State> stateList = null;
		try {
			session = sessionFactory.openSession();
			Query criteria = session.createQuery("from State where isactive=true and stateCode=:stateCode order by stateNameEnglish");
			criteria.setParameter("stateCode", stateCode);
			stateList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return stateList;
	}

	// StateHistoryDetail
	@Override
	@SuppressWarnings("unchecked")
	public List<StateHistory> getStateHistoryDetail(char stateNameEnglish, int stateCode) throws Exception {
		Session session = null;
		List<StateHistory> stateHistoryDetail = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getStateHistoryDetails").setParameter("stateNameEnglish", stateNameEnglish).setParameter("stateCode", stateCode);
			stateHistoryDetail = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause().getMessage();
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return stateHistoryDetail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<State> getStateList(String query) throws Exception {
		List<State> lstSd = new ArrayList<State>();
		Session session = null;
		Query sqlQuery = null;
		try {
			session = sessionFactory.openSession();
			sqlQuery = session.createQuery(query);
			lstSd = sqlQuery.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lstSd;
	}

	@Override
	public String getStateNameEnglishbyStateCode(int stateCode) throws Exception {
		Query query = null;
		Session session = null;
		String stateName = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select state_name_english from state where state_code=:stateCode and isactive=true").setParameter("stateCode", stateCode);
			@SuppressWarnings("rawtypes")
			List list = query.list();

			if (!list.isEmpty() && list.get(0) != null) {
				stateName = list.get(0).toString();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return stateName;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Search> getStateSearchDetail(String entityName, String entityCode) throws Exception {
		Session session = null;
		Query query = null;
		List<Search> stateSearchDetail = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getStateSearchDetails").setParameter("entityName", entityName).setParameter("entityCode", entityCode);
			stateSearchDetail = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return stateSearchDetail;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Search> getStateSearchDetailByCode(int entityCodeForSearch, String entityCode) throws Exception {
		Session session = null;
		Query query = null;
		List<Search> stateSearchDetail = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getStateSearchDetailsByCode").setParameter("entityCodeForSearch", entityCodeForSearch).setParameter("entityCode", entityCode);
			stateSearchDetail = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return stateSearchDetail;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AllSearch> getAllSearchDetailByCode(String entityCodeForSearch, String entityCode,boolean isByCode) throws Exception {
		Session session = null;
		Query query = null;
		List<AllSearch> allSearchDetail = null;
		try {
			session = sessionFactory.openSession();
			if(isByCode){
			query = session.getNamedQuery("getAllSearchDetailsByCode");
			query.setParameter("entityCodeForSearch",Integer.parseInt(entityCodeForSearch),Hibernate.INTEGER);
			}
			
			else{
				query = session.getNamedQuery("getAllSearchDetails");
				query.setParameter("entityCodeForSearch", entityCodeForSearch,Hibernate.STRING);
			}
			
			query.setParameter("entityCode", entityCode,Hibernate.STRING);
			allSearchDetail = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return allSearchDetail;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<State> getStateTargetList(int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<State> stateList = new ArrayList<State>();

		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from State s where s.isactive =true and s.statePK.stateCode !=" + stateCode + " order by stateNameEnglish");
			stateList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return stateList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<State> getStateViewList(String value) throws Exception {
		List<State> state = null;
		Session session = null;
		Query query = null;

		try {

			session = sessionFactory.openSession();
			query = session.createQuery(value);
			state = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return state;
	}

	@Override
	public String getSubDistricNameEnglishbySubdistriccode(int subdistrict_code) throws Exception {
		Query query = null;
		Session session = null;
		String stateName = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select subdistrict_name_english from subdistrict where subdistrict_code=:subdistrict_code and isactive=true").setParameter("subdistrict_code", subdistrict_code);
			@SuppressWarnings("rawtypes")
			List list = query.list();

			if (!list.isEmpty() && list.get(0) != null) {
				stateName = list.get(0).toString();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return stateName;
	}

	@Override
	public State getSubDistrictDetail(StatePK statePK, Session session) throws Exception {
		State state = null;
		try {
			state = (State) session.load(State.class, statePK);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return state;
	}

	@Override
	public int getVersionByActiveState(int stateCode) throws Exception {
		Session session = null;
		int stateVersion = 0;

		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("Select state_version from state where isactive=true and state_code=" + stateCode);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				stateVersion = (Integer) list.get(0);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return stateVersion;
	}

	@Override
	public boolean modifyStateInfo(StateForm StateForm, StatePK statePk, int map_landRegionCode, Session session1) throws Exception {
		try {
			State state = new State();
			List<StateDataForm> listState = new ArrayList<StateDataForm>();
			listState = StateForm.getListStateDetails();
			Iterator<StateDataForm> itr = listState.iterator();
			while (itr.hasNext()) {
				StateDataForm element = itr.next();
				session1.flush();
				state = (State) session1.load(State.class, statePk);

				state.setStateNameEnglish(element.getStateNameEnglishch().trim());
				state.setStateNameLocal(element.getStateNameLocal().trim());
				state.setShortName(element.getShortName());
				/*
				 * state.setAliasEnglish(element.getAliasEnglish().trim());
				 * state.setAliasLocal(element.getAliasLocal().trim());
				 */
				state.setCensus2001Code("34");
				state.setCensus2011Code(element.getCensus2011Code());
				state.setStateCode(element.getStateCode());
				state.setIsactive(true);
				//state.setMapCode(map_landRegionCode);
				state.setLrReplaces(element.getLrReplaces());
				state.setLrReplacedby(element.getLrReplacedby());
				state.setFlagCode(element.getFlagCode());

				state.setStatePK(statePk);
				session1.update(state);
			}
		} catch (Exception e) {

			log.debug("Exception" + e);
			return false;
		}

		return true;
	}

	@Override
	public boolean modifyStateInfo(StateForm stateForm, String cord1, int stateCode, int map_landRegionCode, Session session) throws Exception {

		try {
			/*
			 * Criteria criteria=session.createCriteria(Subdistrict.class);
			 * criteria.add(Restrictions.eq("subdistrictCode",
			 * subdistrictCode)); criteria.add(Restrictions.eq("isactive",
			 * true));
			 */

			List<StateDataForm> listState = new ArrayList<StateDataForm>();
			listState = stateForm.getListStateDetails();

			Session session1 = null;
			session1 = sessionFactory.openSession();
			session1.createSQLQuery("update state set  alias_english='" + listState.get(0).getAliasEnglish() + "', alias_local='" + listState.get(0).getAliasLocal() + "', census_2011_code=" + listState.get(0).getCensus2011Code()

			+ " , coordinates='" + cord1 + "' where  state_code=" + stateCode + " and isactive=true").executeUpdate();

			/*
			 * Subdistrict subDist = (Subdistrict)session.get(Subdistrict.class,
			 * subdistrictPK); if(subDistrictForm.getSscode() != null &&
			 * !subDistrictForm.getSscode().isEmpty()) {
			 * subDist.setSscode(subDistrictForm.getSscode().trim()); }
			 * subDist.setIsactive(true);
			 * subDist.setMapLandregionCode(map_landRegionCode);
			 * subDist.setLrReplaces(1); subDist.setLrReplacedby(2);
			 * if(subDistrictForm.getCensus2011Code() != null &&
			 * !subDistrictForm.getCensus2011Code().isEmpty())
			 * subDist.setCensus2011Code
			 * (Integer.parseInt(subDistrictForm.getCensus2011Code()));
			 * session.update(subDist); session.flush();
			 * if(session.contains(subDist)) session.evict(subDist);
			 */

		} catch (Exception e) {

			log.debug("Exception" + e);

			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return true;
	}

	@Override
	public boolean save(MapLandRegion mapLandRegion, Session session) throws Exception {

		try {

			session.save(mapLandRegion);
		} catch (Exception e) {

			log.debug("Exception" + e);
			return false;
		}
		return true;
	}

	@Override
	public boolean saveDataInAttach(GovernmentOrderForm governmentOrderForm, List<AttachedFilesForm> attachedList, int vilcode, HttpSession session, Session ses) {

		/*
		 * Transaction tx1 = null; Session sessionObj =
		 * sessionFactory.openSession(); tx1 = sessionObj.beginTransaction();
		 */
		Attachment attachment = null;
		Query query = null;
		boolean flag = true;
		GovernmentOrderEntityWise goe = null;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = it.next();
					attachment = new Attachment();
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					query = ses.createQuery("from GovernmentOrderEntityWise where entityCode=:VilCode and entityVersion=:ver and entityType=:type").setParameter("VilCode", vilcode, Hibernate.INTEGER).setParameter("ver", 1, Hibernate.INTEGER)
							.setParameter("type", 'B', Hibernate.CHARACTER);
					goe = (GovernmentOrderEntityWise) query.list().get(0);
					if (goe != null) {
						GovernmentOrder govorder = new GovernmentOrder();
						govorder.setOrderCode(goe.getOrderCode());
						attachment.setGovernmentOrder(govorder);
					}
					ses.save(attachment);
					// tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			// tx1.rollback();
			flag = false;
		}
		/*
		 * finally {
		 * 
		 * if (sessionObj != null && sessionObj.isOpen()) sessionObj.clear();
		 * sessionObj.close(); }
		 */
		return flag;
	}

	@Override
	public boolean saveDataInAttach(StateForm stateForm, List<AttachedFilesForm> attachedList, HttpSession session) {
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query = null;
		Attachment attachment = null;
		GovernmentOrderEntityWise goe = null;
		boolean flag = true;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = it.next();
					attachment = new Attachment();
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());

					query = sessionObj.createQuery("from GovernmentOrderEntityWise where entityCode=:Code and entityVersion=:ver and entityType=:type").setParameter("Code", stateForm.getListStateDetails().get(0).getStateCode(), Hibernate.INTEGER)
							.setParameter("ver", stateForm.getListStateDetails().get(0).getStateVersion(), Hibernate.INTEGER).setParameter("type", 'S', Hibernate.CHARACTER);
					goe = (GovernmentOrderEntityWise) query.list().get(0);
					GovernmentOrder govorder = new GovernmentOrder();
					if (goe != null) {
						govorder.setOrderCode(goe.getOrderCode());
						attachment.setGovernmentOrder(govorder);
					}

					sessionObj.save(attachment);
					tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx1.rollback();
			flag = false;
		} finally {

			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
			
		}
		return flag;
	}

	/* Retrieving the Map details attachment from the database */

	@Override
	public boolean saveDataInAttachGenerateState(GovernmentOrderForm govtOrderForm, GenerateDetails genDetails, int orderCode, Session session) {

		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		boolean flag = true;
		Attachment attachment = null;

		try {
			if (genDetails != null) {
				attachment = new Attachment();
				attachment.setFileName(genDetails.getFileName());
				attachment.setFileLocation(genDetails.getFileLocation());
				attachment.setFileTitle("");
				attachment.setFileTimestamp(genDetails.getFileTimestamp());
				attachment.setFileContentType(genDetails.getFileContentType());
				attachment.setFileSize(genDetails.getFileSize());
				// attachment.setFileSize();

				GovernmentOrder govorder = new GovernmentOrder();
				govorder.setOrderCode(orderCode);
				attachment.setGovernmentOrder(govorder);
				// attachment.setAnnouncement_id(governmentOrderForm.getOrderCode().longValue());
				sessionObj.save(attachment);
				tx1.commit();
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			tx1.rollback();
			flag = false;
		} finally {
			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
			
		}

		return flag;
	}

	@Override
	public boolean saveDataInAttachMap(StateForm stateForm, List<AttachedFilesForm> attachedList, HttpSession session) {
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query = null;
		int stateid = stateForm.getListStateDetails().get(0).getStateCode();
		MapAttachment attachmentmap = null;
		//GovernmentOrderEntityWise goe = null;
		boolean flag = true;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = it.next();
					attachmentmap = new MapAttachment();
					attachmentmap.setFileName(filesForm.getFileName());
					attachmentmap.setFileSize(filesForm.getFileSize());
					attachmentmap.setFileContentType(filesForm.getFileType());
					attachmentmap.setFileLocation(filesForm.getFileLocation());
					attachmentmap.setFileTimestamp(filesForm.getFileTimestampName());
					attachmentmap.setMapEntityCode(stateid);
					attachmentmap.setMapEntityType('S');
					sessionObj.save(attachmentmap);

					int attachmentid = (int) attachmentmap.getAttachmentId();

					query = sessionObj.createSQLQuery("UPDATE state set map_attachment_code=" + attachmentid + "where state_code=" + stateid + " and isactive=true");
					query.executeUpdate();
					tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx1.rollback();
			flag = false;
		} finally {

			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
			
		}
		return flag;
	}

	@Override
	public boolean saveDataInAttachWithOrderCode(GovernmentOrderForm governmentOrderForm, List<AttachedFilesForm> attachedList, int orderCode, Session ses) {

		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Attachment attachment = null;
		//Query query = null;
		boolean flag = true;
		//GovernmentOrderEntityWise goe = null;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = it.next();
					attachment = new Attachment();
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					/*
					 * query = ses.createQuery(
					 * "from GovernmentOrderEntityWise where entityCode=:VilCode and entityVersion=:ver and entityType=:type"
					 * ) .setParameter("VilCode", vilcode, Hibernate.INTEGER)
					 * .setParameter("ver", 1,Hibernate.INTEGER)
					 * .setParameter("type",'B',Hibernate.CHARACTER); goe =
					 * (GovernmentOrderEntityWise) query.list().get(0);
					 * if(goe!=null) { GovernmentOrder govorder= new
					 * GovernmentOrder();
					 * govorder.setOrderCode(goe.getOrderCode());
					 * attachment.setGovernmentOrder(govorder); }
					 * ses.save(attachment);
					 */
					// tx1.commit();

					GovernmentOrder govorder = new GovernmentOrder();
					govorder.setOrderCode(governmentOrderForm.getOrderCode());
					attachment.setGovernmentOrder(govorder);
					// attachment.setAnnouncement_id(governmentOrderForm.getOrderCode().longValue());
					sessionObj.save(attachment);
					tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			// tx1.rollback();
			flag = false;
		}
		
		  finally {
		  
		  if (sessionObj != null && sessionObj.isOpen()) {
			  sessionObj.clear();
			  sessionObj.close();
		  }
		  }
		 
		return flag;
	}

	@Override
	public int saveDataInMap(StateForm stateForm, List<AttachedFilesForm> validFileMap, Session session) throws Exception {

		MapAttachment attachment = null;
		//Query query = null;
		//boolean flag = true;

		Integer mapAttachmentid = null;
		try {
			if (validFileMap != null) {
				Iterator<AttachedFilesForm> it = validFileMap.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = it.next();
					attachment = new MapAttachment();
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					// attachment.setMapEntityCode(blockForm.getBlockCode());
					attachment.setMapEntityType('S');
					MapAttachment mapAttachment = (MapAttachment) session.merge(attachment);

					Long attachmentId = mapAttachment.getAttachmentId();
					mapAttachmentid = attachmentId.intValue();

					mapAttachmentid = (int) mapAttachment.getAttachmentId();

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			//flag = false;
		}

		return mapAttachmentid;

	}

	@Override
	public boolean saveState(State state, Session session) throws Exception {

		try {

			session.save(state);
			session.flush();
			session.refresh(state);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	@Override
	public void SetGovermentOrderEntity(String entityCode, char entityType) throws Exception {

		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("GovOrderEntityWiseQuery").setParameter("entityCode", entityCode, Hibernate.STRING).setParameter("entityType", entityType, Hibernate.CHARACTER);
			query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public boolean updatedistrict(boolean isActive, DistrictPK districtPK, Session session) throws Exception {
		District district = (District) session.load(District.class, districtPK);
		district.setIsactive(isActive);
		try {
			session.update(district);
			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
	}

	@Override
	public boolean updateIsActive(boolean isActive, StatePK statePK, Session session) throws Exception {
		State state = (State) session.load(State.class, statePK);
		state.setIsactive(isActive);
		try {
			session.update(state);
			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
	}

	@Override
	public boolean updateIsActive(StatePK statePK, Session session) throws Exception {

		try {
			State state = (State) session.load(State.class, statePK);
			state.setIsactive(false);
			session.update(state);

		} catch (Exception e) {

			log.debug("Exception" + e);

			return false;
		}

		return true;
	}

	@Override
	public boolean updateLReplaces(int lrReplaces, StatePK statePK, Session session) throws Exception {

		try {
			State state = (State) session.load(State.class, statePK);
			state.setLrReplaces(lrReplaces);
			session.update(state);
		} catch (Exception e) {

			log.debug("Exception" + e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateLrReplaces(int lrReplaces, StatePK statePK, Session session) throws Exception {
		State state = (State) session.load(State.class, statePK);
		state.setLrReplaces(lrReplaces);
		try {
			session.update(state);
			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
	}

	@Override
	public boolean updateMapLanRegion(int mapCode, String coordinates, int stateCode, Session session1) throws Exception {
		try {
			MapLandRegion mapLandRegion = (MapLandRegion) session1.load(MapLandRegion.class, mapCode);
			mapLandRegion.setCoordinates(coordinates);
			mapLandRegion.setLandregionCode(stateCode);
			session1.update(mapLandRegion);

		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updatesubdistrict(boolean isActive, SubdistrictPK subdistrictPK, Session session) throws Exception {
		Subdistrict subdistrict = (Subdistrict) session.load(Subdistrict.class, subdistrictPK);
		subdistrict.setIsactive(isActive);
		try {
			session.update(subdistrict);
			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
	}

	@Override
	public boolean updatevillage(boolean isActive, VillagePK villagePK, Session session) throws Exception {
		Village village = (Village) session.load(Village.class, villagePK);
		village.setIsactive(isActive);
		try {
			session.update(village);
			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<State> getStateViewListSQL(String queryBuilder) throws Exception {
		// TODO Auto-generated method stub
		List<State> state = null;
		Session session = null;
		SQLQuery query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery(queryBuilder);
			query.addScalar("stateCode").addScalar("stateNameEnglish").addScalar("stateNameLocal").addScalar("mapupload").addScalar("census2001Code").addScalar("census2011Code").addScalar("fileLocation").addScalar("fileName");

			query.setResultTransformer(Transformers.aliasToBean(State.class));
			state = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return state;
	}

	@Override
	public String getStateNameEnglish(Integer stateCode) {
		Session session = null;
		String stateName = null;
		try {
			session = sessionFactory.openSession();
			// from State where stateCode=:stateCode and isactive=true
			Query query = session.createSQLQuery("select state_name_english from state where state_code = :stateCode and isactive");
			query.setParameter("stateCode", stateCode);
			stateName = (String) query.uniqueResult();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return stateName;
	}

	public boolean checkStateExist(Integer stateCode) {
		boolean isState = false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select count(*) from state where state_code = :stateCode and isactive");
			query.setParameter("stateCode", stateCode);
			BigInteger count = (BigInteger) query.uniqueResult();
			if (count.intValue() == 1) {
				isState = true;
			}
		} catch (Exception e) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isState;
	}

	/**
	 * Get All States For Extended Department Functionality.
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<State> getAllStatesExtended(Integer orgCode, Integer orgLocatedLevelCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<State> stateList = new ArrayList<State>();

		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select case when s.state_code =a.entity_code then cast('F' as character) else cast('A' as character) "
					+ " end as operation_extend_flag,s.state_code as stateCode,s.state_name_english as stateNameEnglish from state s left outer join " + " (select entity_code from org_coverage_detail where isactive and coverage_code in"
					+ " (select coverage_detail_code from org_coverage where isactive and org_coverage_entity_type=1 and org_located_level_code in "
					+ " (select org_located_level_code from org_located_at_levels  where isactive and olc=:orgCode and located_at_level=:locatedatlevelCode)))as a " + " ON s.state_code =a.entity_code "
					+ " where isactive  order by s.state_name_english");

			query.setParameter("locatedatlevelCode", orgLocatedLevelCode, Hibernate.INTEGER);
			query.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			query.addScalar("stateCode").addScalar("stateNameEnglish").addScalar("operation_extend_flag");
			query.setResultTransformer(Transformers.aliasToBean(State.class));
			stateList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return stateList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<State> getAllNotInStatesExtended(String stateList, Integer orgCode, Integer orgLocatedLevelCode) {
		//Query sqlQuery = null;
		Session session = null;
		SQLQuery query = null;
		List<State> getAllNotInStates = null;

		try {
			session = sessionFactory.openSession();

			query = session.createSQLQuery("select case when s.state_code =a.entity_code then cast('F' as character) else cast('A' as character) "
					+ " end as operation_extend_flag,s.state_code as stateCode,s.state_name_english as stateNameEnglish from state s left outer join " + " (select entity_code from org_coverage_detail where isactive and coverage_code in"
					+ " (select coverage_detail_code from org_coverage where isactive and org_coverage_entity_type=1 and org_located_level_code in "
					+ " (select org_located_level_code from org_located_at_levels  where isactive and olc=:orgCode and located_at_level=:locatedatlevelCode)))as a " + " ON s.state_code =a.entity_code " + " where isactive and s.state_code not in ("
					+ stateList + ") order by s.state_name_english");

			query.setParameter("locatedatlevelCode", orgLocatedLevelCode, Hibernate.INTEGER);
			query.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			query.addScalar("stateCode").addScalar("stateNameEnglish").addScalar("operation_extend_flag");
			query.setResultTransformer(Transformers.aliasToBean(State.class));
			getAllNotInStates = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getAllNotInStates;

	}

	/* added by ashish dhupia for menu disable functionality on 4/2/2015 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuProfile> getDisableMenueList(Integer slc, Integer districtCode) throws Exception {
		Session session = null;
		List<MenuProfile> menuList = new ArrayList<MenuProfile>();
		List<MenuDisableTable> statesMenuDisableData = null;
		String type = "S";
		SQLQuery qu = null;
		Integer entityCode = 0;
		if (slc != null && slc > 0) {
			entityCode = slc;
		}
		if (districtCode != null && districtCode > 0) {
			type = "D";
			entityCode = districtCode;
		}
		try {
			session = sessionFactory.openSession();
			if (type.equalsIgnoreCase("S")) {
				qu = session.createSQLQuery("select mt.menu_id as menuId from state_freeze st ,menu_disable_table mt where " + " st.status_by_state_user = 1 "
						+ " and mt.entity_type=:type and st.entity_code=:entityCode and mt.entity_type=st.entity_type ");
			} else {
				qu = session.createSQLQuery("select mt.menu_id as menuId from state_freeze st , menu_disable_table mt where mt.entity_type=:type and" + " entity_code=:entityCode and mt.entity_type=st.entity_type and status_by_state_user=1"
						+ " union " + " select mt.menu_id as menuId from district_freeze dt, menu_disable_table mt where " + " entity_code=:entityCode and mt.entity_type=:type and status = 1");
			}
			qu.setParameter("entityCode", entityCode);
			qu.setParameter("type", type);
			qu.addScalar("menuId");
			qu.setResultTransformer(Transformers.aliasToBean(MenuDisableTable.class));
			statesMenuDisableData = qu.list();
			if (!statesMenuDisableData.isEmpty()) {
				for (MenuDisableTable menuDisableTable : statesMenuDisableData) {
					MenuProfile menuProfile = new MenuProfile();
					if (menuDisableTable != null) {
						menuProfile.setMenuId(menuDisableTable.getMenuId());
						menuList.add(menuProfile);
					}
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return menuList;
	}

	/**
	 * Get all State List with operation state code used in coverage
	 * Administrative Entity at center level. 
	 * @author Pooja
	 * @since 21-10-2015
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<State> getStateListWithOperationState() throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<State> stateList = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select case when st.state_code  in (select local_body_code from get_draft_used_lb_lr_temp(st.state_code,'S')) then cast('F' as character) "
					+ "else cast('A' as character) end as operation_state ,state_code as stateCode,state_name_english as stateNameEnglish from state st where st.isactive order by stateNameEnglish");
			query.addScalar("operation_state");
			query.addScalar("stateCode");
			query.addScalar("stateNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(State.class));
			stateList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return stateList;
	}
	
	/**
	 * Code used to get Is Pesa Act implemented for the state or not.
	 * 
	 * @author Pooja
	 * @since 19-05-2016
	 * @param stateCode
	 * @return
	 * @throws Exception
	 */
	public Boolean isPesaState(Integer stateCode) throws Exception {
		Session session = null;
		Query query = null;
		Boolean isPesa = false;
		Character pesa = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("select isPesa from State where stateCode=:stateCode and isactive=true");
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			pesa = (Character) query.uniqueResult();
			if(pesa.equals('P')) {
				isPesa = true;
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isPesa;
	}

	@Override
	public Integer getStateCode(Integer districtCode) throws Exception {
		Session session = null;
	    Integer stateCode=null;
		try {
			session = sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select slc from district where dlc=:districtCode and isactive");
			query.setParameter("districtCode",districtCode);
			stateCode=(Integer)query.uniqueResult();
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return stateCode;
	}
	
	@Override
	public boolean saveNodalOfficerDetail(NodalOfficerState nodalOfficerState)throws Exception{
		Session session=null;
		try{
			session=sessionFactory.openSession();
			session.saveOrUpdate(nodalOfficerState);
			session.flush();
		}catch(Exception e){
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		
		}
		return true;
	}
	
	@Override
	public boolean updateNodalOfficerDetail(NodalOfficerState nodalOfficerState,NodalOfficerState existNodalOfficerState)throws Exception{
		Session session=null;
		Transaction tx=null;
		try{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			
			existNodalOfficerState.setIsactive(false);
			session.saveOrUpdate(existNodalOfficerState);
			session.flush();
			session.clear();
			
			session.saveOrUpdate(nodalOfficerState);
			session.flush();
			session.clear();
			tx.commit();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
			
		
		}
		return true;
	}
	
	
	@Override
	public Object[] getNodalOfficerDetails(Integer entityCode,Character entityType,Long userId,Boolean isNodalOfficeDet,Integer stateCode)throws Exception{
		Session session=null;
		Object[] objectArray = null;
		Character userType=null;
		LgdDataConfirmation lgdDataConfirmation=null;
		try{
			objectArray = new Object[7];
			objectArray[0]= false;
			session=sessionFactory.openSession();
			Criteria criteria = session.createCriteria(NodalOfficerState.class);
			criteria.add( Restrictions.eq("userId",userId));
			criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
			List<NodalOfficerState> nodalOfficerStateList = criteria.list();
			if(nodalOfficerStateList!=null && !nodalOfficerStateList.isEmpty()){
				objectArray[0]= true;
				userType=nodalOfficerStateList.get(0).getUserType();
				if(isNodalOfficeDet){
					objectArray[2]=nodalOfficerStateList.get(0);
				}else{
					objectArray[2]=null;
				}
				
			}
			
			if((boolean)objectArray[0]== true){
			Query query = session.getNamedQuery("getUserWiseEntityCount");
			query.setParameter("entityCode", entityCode,Hibernate.INTEGER);
			query.setParameter("entityType", entityType,Hibernate.CHARACTER);
			query.setParameter("userId", userId,Hibernate.LONG);
			query.setParameter("userType", userType,Hibernate.CHARACTER);
			List<LgdDataConfirmation> lgdDataConfirmationList =query.list();
			if(lgdDataConfirmationList!=null && !lgdDataConfirmationList.isEmpty()){
				lgdDataConfirmation=lgdDataConfirmationList.get(0);
				objectArray[1]=lgdDataConfirmation;
			}else{
				objectArray[1]=null;
			}
			
			 criteria = session.createCriteria(LgdDataConfirmation.class);
			criteria.add( Restrictions.eq("userId",userId));
			criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
			List<LgdDataConfirmation> existlgdDataConfirmationList = criteria.list();
			if(existlgdDataConfirmationList!=null && !existlgdDataConfirmationList.isEmpty() && objectArray[1]!=null){
				LgdDataConfirmation existlgdDataConfirmation=existlgdDataConfirmationList.get(0);
				int existTotal=totalCountEntities(existlgdDataConfirmation);
				int currentTotal=totalCountEntities(lgdDataConfirmation);
				objectArray[3]=(currentTotal==existTotal);
				objectArray[4]=existlgdDataConfirmation.getStatus();
			}else{
				objectArray[3]=false;
				objectArray[4]=null;
			}
			
			}else{
				objectArray[1]=null;
			}
			
			if(entityType=='D'){
				List<District> districtList=districtDAO.getDistrictListbyDistCode(entityCode);
				if(districtList!=null && !districtList.isEmpty()){
					objectArray[5]=districtList.get(0).getDistrictNameEnglish();
				}else{
					objectArray[5]=null;
				}	
				
				
				
			}else{
				objectArray[5]=null;
			}
			
			objectArray[6]='U';
			if(this.getFreezeStatusbyState(stateCode, userType, 'M')){
				objectArray[6]='F';
			}
			
			
		}catch(Exception e){
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		
		}
		return objectArray;
	}
	
	
	private Integer totalCountEntities(LgdDataConfirmation lgdDataConfirmation)throws Exception{
	
		Integer blocks=lgdDataConfirmation.getNoOfBlocks()!=null?lgdDataConfirmation.getNoOfBlocks():0;
		Integer districts=lgdDataConfirmation.getNoOfDistrict()!=null?lgdDataConfirmation.getNoOfDistrict():0;
		Integer subdistricts=lgdDataConfirmation.getNoOfSubdistricts()!=null?lgdDataConfirmation.getNoOfSubdistricts():0;
		Integer villages=lgdDataConfirmation.getNoOfVillages()!=null?lgdDataConfirmation.getNoOfVillages():0;
		Integer pris=lgdDataConfirmation.getNoOfPRI()!=null?lgdDataConfirmation.getNoOfPRI():0;
		Integer urbans=lgdDataConfirmation.getNoOfUrban()!=null?lgdDataConfirmation.getNoOfUrban():0;
		Integer trads=lgdDataConfirmation.getNoOfTraditional()!=null?lgdDataConfirmation.getNoOfTraditional():0;
		return (blocks+districts+subdistricts+villages+pris+urbans+trads);
	}
	
	@Override
	public boolean sendOTPForLGDDataConfirmation(Long userId){
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Criteria criteria = session.createCriteria(NodalOfficerState.class);
			criteria.add( Restrictions.eq("userId",userId));
			criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
			List<NodalOfficerState> nodalOfficerStateList = criteria.list();
			if(nodalOfficerStateList!=null && !nodalOfficerStateList.isEmpty()){
				NodalOfficerState nodalOfficerState=nodalOfficerStateList.get(0);
				if(nodalOfficerState.getToken()==null){
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					
					nodalOfficerState.setToken(BASE64.base64encode(this.OTP(6))); 
					nodalOfficerState.setExpireDuration(timestamp);
					session.saveOrUpdate(nodalOfficerState);
					session.flush();
					
				}
				
				if(nodalOfficerState.getToken()!=null && nodalOfficerState.getMobileNo()!=null){
					sendOTP(nodalOfficerState.getToken(),nodalOfficerState.getMobileNo());
					
				}
				if(nodalOfficerState.getToken()!=null && nodalOfficerState.getEmailId()!=null){
					sendMail(nodalOfficerState.getEmailId(),nodalOfficerState.getToken());
				}
				return true;
			}
			
		}catch(Exception e){
			log.error(e);
			return false;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		
		}
		return false;
	}
	
	
	
	private synchronized void sendOTP(String encodeotp,String pnos)throws Exception{
		String message = LGDResourceBundle.getBundle("lgd_mobile_message", Locale.ENGLISH).getObject("lgd.data.confirmation.message").toString();
		String otp =BASE64.base64decode(encodeotp);
		message = message.replace("<otpno>",otp);
		SmsManager sms = new SmsManager();
		try{
			System.out.println("opt pnos"+pnos+	" message "+message);
		sms.makeHTTPConnection(pnos, message);
		}catch(Exception e){
			log.error(e);
		}
	}
	
	private synchronized String OTP(int len)
	{
		String numbers = "0123456789";

		// Using random method
		Random rndm_method = new Random();

		char[] otp = new char[len];

		for (int i = 0; i < len; i++)
		{
			// Use of charAt() method : to get character value
			// Use of nextInt() as it is scanning the value as int
			otp[i] =
			numbers.charAt(rndm_method.nextInt(numbers.length()));
		}
		System.out.println("opt ------->>>>>"+String.valueOf(otp));
		return String.valueOf(otp);
	}
	
	
	
	@Override
	public Character validateOTP(String userOTP,Long userId)throws Exception{
		Session session=null;
		Character token_flag='W'; //W for wrong
		try{
		session=sessionFactory.openSession();
		Criteria criteria = session.createCriteria(NodalOfficerState.class);
		criteria.add( Restrictions.eq("userId",userId));
		criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
		List<NodalOfficerState> nodalOfficerStateList = criteria.list();
		if(nodalOfficerStateList!=null && !nodalOfficerStateList.isEmpty()){
			NodalOfficerState nodalOfficerState=nodalOfficerStateList.get(0);
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			 long diff = timestamp.getTime() - nodalOfficerState.getExpireDuration().getTime();
			 //long diffSeconds = diff / 1000 % 60;
		        long diffMinutes = diff / (60 * 1000) % 60;
		        long diffHours = diff / (60 * 60 * 1000);
		        int diffInDays = (int) ((timestamp.getTime() -nodalOfficerState.getExpireDuration().getTime()) / (1000 * 60 * 60 * 24));
			 if(diffInDays>1 ||diffHours>0 ||  diffMinutes>60){
				 nodalOfficerState.setToken(null); 
				 nodalOfficerState.setExpireDuration(null);
				 session.saveOrUpdate(nodalOfficerState);
				 session.flush();
				 token_flag='E'; // E for expire
				 
			 }
			 String dbuserOTP=BASE64.base64decode(nodalOfficerState.getToken());
			 System.out.println("dbuserOTP-->"+dbuserOTP);
			 System.out.println("userOTP-->"+userOTP);
			if(token_flag!='E' && nodalOfficerState.getToken()!=null && (dbuserOTP).equals(userOTP)){
				token_flag='C'; //C for correct
			}
			
		 }
		
		}catch(Exception e){
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		
		}
		return token_flag;
	}
	
	@Override
	public boolean saveLGDDataConfirmation(LgdDataConfirmation lgdDataConfirmation)throws Exception{
		Session session=null;
		boolean saveFlag=false;
		Transaction tx=null;
		try{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			Criteria criteria = session.createCriteria(LgdDataConfirmation.class);
			criteria.add( Restrictions.eq("userId",lgdDataConfirmation.getUserId()));
			criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
			List<LgdDataConfirmation> lgdDataConfirmationList = criteria.list();
			if(lgdDataConfirmationList!=null && !lgdDataConfirmationList.isEmpty()){
				LgdDataConfirmation existlgdDataConfirmation=lgdDataConfirmationList.get(0);
				existlgdDataConfirmation.setIsactive(Boolean.FALSE);
				session.saveOrUpdate(existlgdDataConfirmation);
				session.flush();
				session.clear();
				
				
				
			}
			
			session.saveOrUpdate(lgdDataConfirmation);
			session.flush();
			saveFlag=true;
			tx.commit();
			
		}catch(Exception e){
			tx.rollback();
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		
		}
		return saveFlag;
	}
	
	@Override
	public List<DistrictFreezeEntity> getDistrictwiseFreezeStatus(Integer stateCode,Character userType)throws Exception{
		Session session=null;
		List<DistrictFreezeEntity> districtFreezeEntityList=null;
		try{
			session=sessionFactory.openSession();
			Query query = session.getNamedQuery("getDistrictwiseFreezeStatus");
			query.setParameter("stateCode", stateCode,Hibernate.INTEGER);
			query.setParameter("userType", userType,Hibernate.CHARACTER);
			districtFreezeEntityList =query.list();
		}finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		
		}
		return districtFreezeEntityList;
	}
	
	@Override
	public boolean saveDistrictUnfreezebyState(LgdDataConfirmation lgdDataConfirmation)throws Exception{
		Session session=null;
		Transaction tx=null;
		boolean flag=false;
		try{
			session=sessionFactory.openSession();
			//Added  condition for PRI user By Sushma Singh 24-03-2020 for
			if(lgdDataConfirmation.getUserType()=='U'||lgdDataConfirmation.getUserType()=='P') {
				List<FreezeLocalBodyEntity> districtFreezeEntityList=lgdDataConfirmation.getDistrictFreezeEntityListULB();
				tx=session.beginTransaction();
				List<FreezeLocalBodyEntity> unfreezeDistrictList=getUnfreezeDistrictListULB(districtFreezeEntityList);
				for(FreezeLocalBodyEntity obj:unfreezeDistrictList ){
					Criteria criteria = session.createCriteria(NodalOfficerState.class);
					criteria.add( Restrictions.eq("userId", obj.getUserId().longValue()));
					criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
				
					
					List<NodalOfficerState> nodalOfficerStateList = criteria.list();
					if(nodalOfficerStateList!=null && !nodalOfficerStateList.isEmpty()){
						LgdDataConfirmation existlgdDataConfirmation=nodalOfficerStateList.get(0).getLgdDataConfirmation();
						if(lgdDataConfirmation!=null){
							LgdDataConfirmation newlgdDataConfirmation=new LgdDataConfirmation();
							draftUtils.copyBeanAttributes(newlgdDataConfirmation, existlgdDataConfirmation);;
							
							existlgdDataConfirmation.setIsactive(Boolean.FALSE);
							session.save(existlgdDataConfirmation);
							session.flush();
							session.clear();
							
							
							Timestamp timestamp = new Timestamp(System.currentTimeMillis());
							newlgdDataConfirmation.setUpdatedOn(timestamp);
							newlgdDataConfirmation.setUnfreezeReason(obj.getRemarks());
							newlgdDataConfirmation.setStatus('U');
							newlgdDataConfirmation.setUnfreeseUserId(lgdDataConfirmation.getUserId());
							session.save(newlgdDataConfirmation);
							session.flush();
							session.clear();
							
						}
					}
					
				}
			
				tx.commit();
				flag=true;
			}else {
				List<DistrictFreezeEntity> districtFreezeEntityList=lgdDataConfirmation.getDistrictFreezeEntityList();
				tx=session.beginTransaction();
				List<DistrictFreezeEntity> unfreezeDistrictList=getUnfreezeDistrictList(districtFreezeEntityList);
				for(DistrictFreezeEntity obj:unfreezeDistrictList ){
					Criteria criteria = session.createCriteria(NodalOfficerState.class);
					criteria.add( Restrictions.eq("userId", obj.getUserId().longValue()));
					criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
				
					
					List<NodalOfficerState> nodalOfficerStateList = criteria.list();
					if(nodalOfficerStateList!=null && !nodalOfficerStateList.isEmpty()){
						LgdDataConfirmation existlgdDataConfirmation=nodalOfficerStateList.get(0).getLgdDataConfirmation();
						if(lgdDataConfirmation!=null){
							LgdDataConfirmation newlgdDataConfirmation=new LgdDataConfirmation();
							draftUtils.copyBeanAttributes(newlgdDataConfirmation, existlgdDataConfirmation);;
							
							existlgdDataConfirmation.setIsactive(Boolean.FALSE);
							session.save(existlgdDataConfirmation);
							session.flush();
							session.clear();
							
							
							Timestamp timestamp = new Timestamp(System.currentTimeMillis());
							newlgdDataConfirmation.setUpdatedOn(timestamp);
							newlgdDataConfirmation.setUnfreezeReason(obj.getRemark());
							newlgdDataConfirmation.setStatus('U');
							newlgdDataConfirmation.setUnfreeseUserId(lgdDataConfirmation.getUserId());
							session.save(newlgdDataConfirmation);
							session.flush();
							session.clear();
							
						}
					}
					
				}
			
				tx.commit();
				flag=true;
			}
			
			
			
		}catch(Exception e){
		tx.rollback();
		log.error("Problem to unfeeze data-->",e);
	}finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		
		}
		return flag;
	}
	
	
	
	@Override
	public boolean saveStateFreezeUnfreezebyState(LgdDataConfirmation lgdDataConfirmation)throws Exception{
		Session session=null;
		boolean flag=false;
		Transaction tx=null;
		try{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			
			Criteria criteria = session.createCriteria(NodalOfficerState.class);
			criteria.add( Restrictions.eq("userId", lgdDataConfirmation.getUserId()));
			criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
		
			
			List<NodalOfficerState> nodalOfficerStateList = criteria.list();
			if(nodalOfficerStateList!=null && !nodalOfficerStateList.isEmpty()){
				LgdDataConfirmation existlgdDataConfirmation=nodalOfficerStateList.get(0).getLgdDataConfirmation();
			
				if(lgdDataConfirmation!=null){
					LgdDataConfirmation newlgdDataConfirmation=new LgdDataConfirmation();
					if(existlgdDataConfirmation!=null) {
						
						draftUtils.copyBeanAttributes(newlgdDataConfirmation, existlgdDataConfirmation);;
						
						existlgdDataConfirmation.setIsactive(Boolean.FALSE);
						session.save(existlgdDataConfirmation);
						session.flush();
						session.clear();
					}
				
					
					
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					newlgdDataConfirmation.setUpdatedOn(timestamp);
					newlgdDataConfirmation.setStatus('U');
					newlgdDataConfirmation.setUserId(lgdDataConfirmation.getUserId());
					newlgdDataConfirmation.setUserType(lgdDataConfirmation.getUserType());
					session.save(newlgdDataConfirmation);
					session.flush();
					session.clear();
					
				}
			
			}
				tx.commit();
				flag=true;
		}catch(Exception e){
		tx.rollback();
		log.error("Problem to unfeeze data-->",e);
	}finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		
		}
		return flag;
	}
	
	
	private List<DistrictFreezeEntity> getUnfreezeDistrictList(List<DistrictFreezeEntity> districtFreezeEntityList)throws Exception{
		List<DistrictFreezeEntity> unfreezeDistrictList=new ArrayList<DistrictFreezeEntity>();
		for(DistrictFreezeEntity obj:districtFreezeEntityList ){
			if(obj.getUnfreezeStatus()!=null && obj.getUnfreezeStatus()=='U'){
				unfreezeDistrictList.add(obj);
			}
		}
		return unfreezeDistrictList;
	}
	
	private List<FreezeLocalBodyEntity> getUnfreezeDistrictListULB(List<FreezeLocalBodyEntity> districtFreezeEntityList)throws Exception{
		List<FreezeLocalBodyEntity> unfreezeDistrictList=new ArrayList<FreezeLocalBodyEntity>();
		for(FreezeLocalBodyEntity obj:districtFreezeEntityList ){
			if(obj.getUnfreezeStatus()!=null && obj.getUnfreezeStatus()=='U'){
				unfreezeDistrictList.add(obj);
			}
		}
		return unfreezeDistrictList;
	}
	
	@Override
	public boolean getFreezeStatusbyState(Integer stateCode,Character userType,Character checkLevel)throws Exception{
		boolean isFreeze=false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(" select * from get_freeze_status_by_state(:stateCode,:userType,:checkLevel)");
			query.setParameter("stateCode", stateCode);
			query.setParameter("userType", userType);
			query.setParameter("checkLevel", checkLevel);
			isFreeze= (Boolean) query.uniqueResult();
		
		} finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return isFreeze;
	}
	
	@Override
	public Character getUserTypeofNodalOfficer(Long lUserId)throws Exception{
		Character userType=null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(" select distinct user_type from nodal_officer  where   user_id=:userId and isactive ");
			query.setParameter("userId", lUserId);
		
			userType= (Character) query.uniqueResult();
		
		}catch(Exception e){
				e.printStackTrace();
				log.error("Problem to unfeeze data-->",e);
			} finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return userType;
	}
	
	@Override
	public boolean getFreezeStatusbyUserId(Long userId,Character userType,Character level,Integer stateCode)throws Exception{
		boolean isFreeze=false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(" select * from get_freeze_status_by_user_id(:userId,:userType,:level,:stateCode)");
			query.setParameter("userId", userId);
			query.setParameter("userType", userType);
			query.setParameter("level", level);
			query.setParameter("stateCode", stateCode);
			isFreeze= (Boolean) query.uniqueResult();
		
		} finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return isFreeze;
	}
	
	
	@Override
	public List<EntityFreezeStatus> getEntityFreezeStatus(Integer stateCode)throws Exception{
		Session session=null;
		List<EntityFreezeStatus> entityFreezeStatusList=null;
		try{
			session=sessionFactory.openSession();
			Query query = session.getNamedQuery("statewiseFreezeStatus");
			if(stateCode!=null){
				query= session.getNamedQuery("districtwiseFreezeStatus");
				query.setParameter("stateCode", stateCode,Hibernate.INTEGER);
			}
			
		    entityFreezeStatusList =query.list();
		}catch(Exception e){
			e.printStackTrace();
		
		}finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		
		}
		return entityFreezeStatusList;
	}
	
	
	@Override
	public String getGisTokenValue()throws Exception{
		String token=null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(" select token from map_configuration where level =1");
			
			token= (String) query.uniqueResult();
		
		} finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return token;
	}
	
	@Override
	public List<AuditTrailLGD> getAuditTrailLGD()throws Exception{
		Session session=null;
		List<AuditTrailLGD> auditTrailLGDList=null;
		
		try{
			session=sessionFactory.openSession();
			Query query=session.createQuery("from AuditTrailLGD");
			auditTrailLGDList=query.list();
		}catch(Exception e){
			e.printStackTrace();
		
		}finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		
		}
		return auditTrailLGDList;
	}
	
	
	public void sendMail(String mailIds,String encodeotp) {
		try {
			
			String message = LGDResourceBundle.getBundle("lgd_mobile_message", Locale.ENGLISH).getObject("lgd.data.confirmation.message").toString();
			String otp = BASE64.base64decode(encodeotp);           
			message = message.replace("<otpno>",otp);
			
			String title = "Nodal Officer OTP from LGD";
			MailService mailService = new MailService();
			mailService.sendMail(mailIds, title, message);
		} catch (Exception e) {
			log.info("mail server not present in this server", e);
		}
	}
	
	
	
	@Override
	public String getStateSetupType(Integer stateCode)throws Exception{
		String  stateSetupType=null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(" select distinct * from (SELECT (case when (select count(*) from local_body_tiers_setup where local_body_setup_code=t.local_body_setup_code and local_body_setup_version=t.local_body_setup_version)=2 and t.local_body_type_code =2 then 'IV' when (select count(*) from local_body_tiers_setup where local_body_setup_code=t.local_body_setup_code and local_body_setup_version=t.local_body_setup_version)=2 and t.local_body_type_code =1  then 'DV' WHEN (select count(*) from local_body_tiers_setup where local_body_setup_code=t.local_body_setup_code and local_body_setup_version=t.local_body_setup_version)=3 then 'DIV' end) as type FROM local_body_setup s, local_body_tiers_setup t where s.local_body_setup_code =t.local_body_setup_code and  s.local_body_setup_version =t.local_body_setup_version and s.isactive and t.isactive and s.slc=:stateCode and s.hierarchy_type='P')x where type is not null");
			query.setParameter("stateCode", stateCode);
			
			stateSetupType=  (String)query.uniqueResult();
		
		} finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return stateSetupType;
	}
	
	
	@Override
	public List<ParentwiseChildDetials> getparentwisecountofBPandGP(Integer districtCode) throws Exception{
		Session session = null;
		Query query = null;
		List<ParentwiseChildDetials> parentwiseChildDetialsLsit = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("parentwisecountofBPandGP");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
		    parentwiseChildDetialsLsit = query.list();
		} catch (Exception e) {
			log.error("Eror in Consolidate Report of LGD-->",e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return parentwiseChildDetialsLsit;
	}
	
	@Override
	public List<DistrictFreezeEntity> getDistrictwiseFreezeStatusULB(Integer stateCode,Character userType)throws Exception{
		Session session=null;
		List<DistrictFreezeEntity> districtFreezeEntityList=null;
		try{
			session=sessionFactory.openSession();
			Query query = session.getNamedQuery("getDistrictwiseFreezeStatusULB");
			query.setParameter("stateCode", stateCode,Hibernate.INTEGER);
			query.setParameter("userType", userType,Hibernate.CHARACTER);
			districtFreezeEntityList =query.list();
		}
		
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
		
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		
		}
		return districtFreezeEntityList;
	}

	@Override
	public List<FreezeLocalBodyEntity> freezeUnfreezeLocalBodyEntity(Integer districtCode, Character parentType,
			Character userType, Long userId) throws Exception {
		Session session = null;
		Query query = null;
		List<FreezeLocalBodyEntity> freezeUnfreezeLocalBody = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("freezeunfreezeulbentity");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			query.setParameter("parentType", parentType,Hibernate.CHARACTER);
			query.setParameter("userType", userType,Hibernate.CHARACTER);
			query.setParameter("userId", userId!=null?userId.intValue():null, Hibernate.INTEGER);
			freezeUnfreezeLocalBody = query.list();
		} catch (Exception e) {
			log.error("Eror in Consolidate Report of LGD-->",e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return freezeUnfreezeLocalBody;
	}
	
	@Override
	public boolean saveConfigurationLGDUpdation(FreezeUnfreezeStateConfigEntity  freezeUnfreezeStateConfigEntity,LgdDataConfirmation lgdDataConfirmation)throws Exception {
		Session session = null;
		Transaction tx=null;
		try {
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			session.saveOrUpdate(freezeUnfreezeStateConfigEntity);
			
		
			if(lgdDataConfirmation!=null) {
				lgdDataConfirmation.setIsactive(Boolean.FALSE);
				session.update(lgdDataConfirmation);
				
			}
		
			
			tx.commit();
			return true;
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				
			}
		}
		
	}
	
	@Override
	public FreezeUnfreezeStateConfigEntity getConfigurationOfLGDDataConfirmation(Integer stateCode,Character userType) throws Exception
	{
		Session session = null;
		FreezeUnfreezeStateConfigEntity freezeUnfreezeStateConfigEntity=new FreezeUnfreezeStateConfigEntity();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(FreezeUnfreezeStateConfigEntity.class);
			criteria.add( Restrictions.eq("stateCode", stateCode));
			criteria.add( Restrictions.eq("userType", userType));
			List<FreezeUnfreezeStateConfigEntity> freezeUnfreezeStateConfigEntityList = criteria.list();
			if(freezeUnfreezeStateConfigEntityList!=null && !freezeUnfreezeStateConfigEntityList.isEmpty()){
				freezeUnfreezeStateConfigEntity=freezeUnfreezeStateConfigEntityList.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return freezeUnfreezeStateConfigEntity;
	}

	
	
	@Override
	public boolean saveStateFreezeUnfreezebyStateOnly(Integer stateCode,Character userType, Character status,Long userId,String fileName)throws Exception{
		boolean isStateFreeze=false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder sb=new StringBuilder(" select * from freez_data_by_state_only(:stateCode,:userType,:status,:userId");
			if(fileName !=null) {
				sb.append(" ,:fileName");
			}
			sb.append(")");
			Query query = session.createSQLQuery(sb.toString());
			query.setParameter("stateCode", stateCode);
			query.setParameter("userType", userType);
			query.setParameter("status", status);
			query.setParameter("userId", userId.intValue());
			if(fileName !=null) {
			query.setParameter("fileName",fileName);
			}
			isStateFreeze= (Boolean) query.uniqueResult();
		
		} finally {
			if(session!=null && session.isOpen()) 
				session.close();
		}
		return isStateFreeze;
	}
	
	@Override
	public List<OrganizationByCentreLevel> getOrganisationList(Integer stateCode) throws Exception{
		List<OrganizationByCentreLevel> fetchOrganisation =null;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getCentreStateOrganization").setParameter("stateCode", stateCode);
			fetchOrganisation= query.list();
		
		}catch (Exception e) {
			log.error("Exception-->" + e);
		}  
		finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return fetchOrganisation;
	}

	public  boolean saveConfigurationLGDUpdation(ConfigurationBlockVillageMapping saveconfigBlockVillageMapping) throws Exception{
		Session session = null;
		Transaction tx=null;
		try {
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			session.saveOrUpdate(saveconfigBlockVillageMapping); 
			
			tx.commit();
			return true;
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				
			}
		}
	}

	@Override
	public ConfigurationBlockVillageMapping getconfigureBlockVillage(Long userId, Integer stateCode) {
		Session session = null;
		Query query = null;
		ConfigurationBlockVillageMapping configurationBlockVillageMapping = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from ConfigurationBlockVillageMapping where stateCode=:stateCode");
			query.setParameter("stateCode", stateCode);
			List<ConfigurationBlockVillageMapping> configurationBlockVillageMappingList=query.list();
			if(configurationBlockVillageMappingList!=null && !configurationBlockVillageMappingList.isEmpty()) {
				configurationBlockVillageMapping = configurationBlockVillageMappingList.get(0);
			}
			
		} catch (Exception e) {
			log.error("Eror in Consolidate Report of LGD-->",e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return configurationBlockVillageMapping;
	}
	
	@Override
	public Response saveEffectiveDateEntityState(List<GetEntityEffectiveDate> getEntityEffectiveDateList,
		Long userId) throws Exception {

		Response response=new Response();
		Session session = null;
		String parameter1=null;
		Integer villageCode=null;

		try {
			session = sessionFactory.openSession();
			if(getEntityEffectiveDateList!=null && !getEntityEffectiveDateList.isEmpty()) {
				 villageCode=getEntityEffectiveDateList.get(0).getEntityCode();
				Character entityType=getEntityEffectiveDateList.get(0).getEntityType();
				Query query = session.getNamedQuery("GET_ENTITY_EFFECTIVE_DATE");
				query.setParameter("entityCode", villageCode);
				query.setParameter("entityType", "S");
				List<GetEntityEffectiveDate> effectiveDateListOld= query.list();
				
				if(villageService.validateNewEffectiveDate(villageCode, getEntityEffectiveDateList, effectiveDateListOld))	{
					 List<String> pArr=new ArrayList<String>();
						for(GetEntityEffectiveDate obj: getEntityEffectiveDateList) {
							if(obj.getEffectiveDate()!=obj.getNewEffectiveDate()) {
								pArr.add(obj.getEntityCode()+"#"+obj.getEntityVersion()+"#"+obj.getEntityMinorVersion()+"#"+obj.getNewEffectiveDate());
								
							}
						}
						parameter1=pArr.toString();
						if(parameter1.indexOf("[")>-1) {
							parameter1 = parameter1.replaceAll("[\\[\\](){}]","");
						}
						
						query = session.createSQLQuery("select * from change_state_effective_date_fn(:parameter1,:userId)");
						query.setParameter("parameter1", parameter1.toString(), Hibernate.STRING);
						query.setParameter("userId", userId, Hibernate.LONG);
					    query.uniqueResult();
						session.flush();
						session.clear();
						response.setResponseCode(200);
				 }else {
					 response.setResponseCode(300);
					 response.setReponseObject("Effective Date not in Sequence");
				 }
			
			
		}else {
			response.setResponseCode(300);
		}
			
			
	
	}catch(Exception e){
		response.setResponseCode(400);
		response.setReponseObject(e);
	}finally {
		if(session!=null && session.isOpen()) {
			session.close();
		}
	}
		return response;
		
		
	}
	
	
	
	
	
}


