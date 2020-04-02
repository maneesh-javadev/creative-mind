package in.nic.pes.lgd.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.ep.exception.BaseAppException;

import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.FreezeUnfreezeStatus;
import in.nic.pes.lgd.bean.NodalOfficerState;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.dao.CommonDAO;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.resourcebundles.LGDResourceBundle;

@Service
public class CommonDAOImpl implements CommonDAO {
	
	private static final Logger log = Logger.getLogger(CommonDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;
	
	/*@Resource(name="pesdataSource")
	private DataSource dataSource;
	
	Connection con=null;
	Statement statement=null,statement1=null;
	ResultSet rs=null,rs1=null;
	
	//private String url = LGDResourceBundle.getBundle("hibernate", Locale.ENGLISH).getObject("hibernate.connection.url").toString();
	//private String username = LGDResourceBundle.getBundle("hibernate", Locale.ENGLISH).getObject("hibernate.connection.username").toString();
	//private String password = LGDResourceBundle.getBundle("hibernate", Locale.ENGLISH).getObject("hibernate.connection.password").toString();
	public  Connection getConnection() throws ClassNotFoundException, SQLException
	{
	    Class.forName("org.postgresql.Driver");
		con=DriverManager.getConnection(url,username,password);
		con = dataSource.getConnection();
		return con;	
	}*/
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CheckAuthorization> checkAuthorization(char entityType,
			String parentEntityType, Integer parentEntityCode,
			Integer entityCode) throws BaseAppException {
		List<CheckAuthorization> checkAuthorization = null;
		Query query = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();
			query = session
					.getNamedQuery("getcheckAuthorization")
					.setParameter("entity_type", entityType,
							Hibernate.CHARACTER)
					.setParameter("entity_parent_type", parentEntityType,
							Hibernate.STRING)
					.setParameter("entity_parent_code", parentEntityCode,
							Hibernate.INTEGER)
					.setParameter("entity_code", entityCode, Hibernate.INTEGER);
			checkAuthorization = query.list();
			return  checkAuthorization;

		} catch (Exception e) {
			//throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return  checkAuthorization;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getSlc(Integer stateId) {
		Session session = null;
		Query query = null;
		Integer stateCode = null;
		List<State> statelist=null;
		State state=null;
		boolean isactive = true;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from State s where s.stateCode = :stateCode and s.isactive =:isactive");
			query.setParameter("stateCode", stateId);
			query.setParameter("isactive", isactive);
			statelist=query.list();
			if(statelist.size()>0)
			{
				state =(State)statelist.get(0);//         (State)query.list().get(0);
			stateCode = state.getSlc();
			}
			else
				stateCode=stateId;
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session!=null && session.isOpen()){
				session.close();
			}	
		}
		return stateCode;
	}
	
	@Override
	public Integer getdlc(Integer districtId) {
		Session session = null;
		Query query = null;
		Integer dlc = null;
		boolean isactive = true;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from District s where s.districtCode = :districtId and s.isactive =:isactive");
			query.setParameter("districtId", districtId);
			query.setParameter("isactive", isactive);
			District disrtict = (District)query.list().get(0);
			dlc = disrtict.getDistrictCode();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session!=null && session.isOpen()){
				session.close();
			}	
		}
		return dlc;
	}
	
/*	@Override
	public List<FaqData> getFAQDAO(Integer appid) throws Exception 
	{
		//List<GetFaq> listFAQ = null;
		//listFAQ=new ArrayList<GetFaq>();
		//Query query = null;
		List<FaqData> list = new ArrayList<FaqData>();
		
		try 
		{
			
			con=getConnection();
			statement=con.createStatement();	
			rs=statement.executeQuery("select * from pescommon.get_faqList_by_appID(1)");
			while(rs.next())
			{
				FaqData faqdata=new FaqData();
				faqdata.setFaqQuestionText(rs.getString("faq_question_text"));
				faqdata.setFaqAnswerText(rs.getString("faq_answer_text"));
				list.add(faqdata);
			}
			session = sessionFactory.openSession();
			query = session
					.getNamedQuery("SelectFAQ")
					.setParameter("applicationId", appid,
							Hibernate.INTEGER);
			listFAQ = query.list();
			return  listFAQ;
			 
		} 
		catch (Exception e) 
		{
			throw e;
		}
		finally 
		{
			if (session != null && session.isOpen())
			{
				con.close();
				rs.close();
				
			}
		}
		return list;
	}
	
	@Override
	public List<ResponseData> getResponseDAO(Integer appid) throws Exception 
	{
		//List<ViewResponse> listResponse = null;
		//listResponse=new ArrayList<ViewResponse>();
		//Query query = null;
		List<ResponseData> list = new ArrayList<ResponseData>();
		//Session session = null;
		try 
		{
			
			con=getConnection();
			statement=con.createStatement();	
			rs=statement.executeQuery("select * from pescommon.get_viewResponseList_by_appID(1)");
			while(rs.next())
			{
				ResponseData responsedata=new ResponseData();
				responsedata.setQueryQuestionText(rs.getString("query_question_text"));
				responsedata.setQueryAnswerText(rs.getString("query_answer_text"));
				list.add(responsedata);
			}
			session = sessionFactory.openSession();
			query = session
					.getNamedQuery("SelectFAQ")
					.setParameter("applicationId", appid,
							Hibernate.INTEGER);
			listFAQ = query.list();
			return  listFAQ;
			 
		} 
		catch (Exception e) 
		{
			throw e;
		}
		finally 
		{
			if (session != null && session.isOpen())
			{
				con.close();
				rs.close();
				
			}
		}
		return list;
	}*/
	
	
	public boolean existEntityName(Integer entityParentCode,
									char entityParentType,
									String entityName) 
									throws Exception
	{
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			
			String queryString=null;
			switch(entityParentType)
			{
			case 'S':
				queryString="select count(*) from State where isactive=true and trim(UPPER(stateNameEnglish)) like trim(UPPER('"+entityName+"'))";
				break;
			case 'D':	
				queryString="select count(*) from District where isactive=true and trim(UPPER(districtNameEnglish)) like trim(UPPER('"+entityName+"')) and slc="+entityParentCode;
				break;
			case 'T':
				queryString="select count(*) from Subdistrict where isactive=true and trim(UPPER(subdistrictNameEnglish)) like trim(UPPER('"+entityName+"')) and dlc="+entityParentCode;
				break;
			case 'V':
				queryString="select count(*) from Village where isactive=true and trim(UPPER(villageNameEnglish)) like trim(UPPER('"+entityName+"')) and tlc="+entityParentCode;
				break;
			case 'B':
				queryString="select count(*) from Block where isactive=true and trim(UPPER(blockNameEnglish)) like trim(UPPER('"+entityName+"')) and dlc="+entityParentCode;
				break;
				
			}
			Query query = session.createQuery(queryString);
			Integer count=Integer.parseInt(query.uniqueResult().toString());
			if(count>0)
				return true;
			else
				return false;
			
		}catch(Exception e)
		{
			log.debug("Exception" + e);
			return false;
		}
		finally
		{
			if(session!=null && session.isOpen())
			{
				session.close();
			}
		}
	
	}
	
	/*@Override
	public java.util.Map<String, Object> getFAQandRespose(Integer appId) {
		Session session = null;
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		try {
			session = HibernateSessionEmail.email();
			SQLQuery query = session.createSQLQuery("select faq_question_text as faqQuestionText, faq_answer_text as faqAnswerText from pescommon.get_faqList_by_appID(:appid)");
			query.setParameter("appid", appId);
			query.addScalar("faqQuestionText").addScalar("faqAnswerText");
			query.setResultTransformer(Transformers.aliasToBean(FaqData.class));
			map.put("faqList", query.list()); 
			session.flush();
			
			query = session.createSQLQuery("select query_question_text as queryQuestionText, query_answer_text as queryAnswerText from pescommon.get_viewResponseList_by_appID(:appid)");
			query.setParameter("appid", appId);
			query.addScalar("queryQuestionText").addScalar("queryAnswerText");
			query.setResultTransformer(Transformers.aliasToBean(ResponseData.class));
			map.put("responseList", query.list());
			session.flush();
			
			Query criteria = session.getNamedQuery("GetAnnouncementList");
			criteria.setParameter("appid", appId);
			map.put("announcementList", criteria.list());
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		} 
		return map;
	}*/


	@Override
	public FreezeUnfreezeStatus getFreezeUnfreezeStatus(Integer entityCode, String entityType) {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.getFreezeUnfreezeStatus execution started.");
		Session session = null;
		FreezeUnfreezeStatus statusFreezeUnfreeze = new FreezeUnfreezeStatus();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Check_Freeze_Unfreeze_Status");
			query.setParameter("entityCode", entityCode, Hibernate.INTEGER);
			query.setParameter("entityType", entityType, Hibernate.STRING);
			statusFreezeUnfreeze = (FreezeUnfreezeStatus) query.uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getFreezeUnfreezeStatus : ", e);
			throw e;
		} finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
		return statusFreezeUnfreeze;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getDistrictDetailsByStateCode(Integer stateCode){
		log.info("getDistrictDetailsByStateCode execution started.");
		List<Object> list=new ArrayList<Object>();
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select d.district_code ,d.district_name_english,district_status,a.file_name,a.file_location from district d left outer join freeze_status_constituency f on d.dlc=f.district_code left outer join attachment a on f.document_id=a.attachment_id  where d.slc=:stateCode and d.isactive=true order by district_name_english");
			query.setParameter("stateCode", stateCode);
			list=query.list();
		} catch(Exception e){
			throw new HibernateException(e);
		} finally {
			if(session != null && session.isOpen()) {
				session.close();            
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkWsAllowedIpAddress(String ipaddress){
		log.info("checkWsAllowedIpAddress execution started.");
		List<Object> list=new ArrayList<Object>();
		boolean allowIp = false;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select * from ws_allowed_IPs where ip_address=:ipaddress");
			query.setParameter("ipaddress", ipaddress);
			
			if(query.list().size()>0)
				allowIp = true;
			else
				allowIp= false;
		} catch(Exception e){
			throw new HibernateException(e);
		} finally {
			if(session != null && session.isOpen()) {
				session.close();            
			}
		}
		return allowIp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean mobileTokenAuthentication(String token){
		log.info("mobileTokenAuthentication execution started.");
		boolean allowMobileToken = false;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select * from user_registration where registration_status = 'A' and mobile_web_token=:token");
			query.setParameter("token", token);
			
			if(query.list().size()>0)
				allowMobileToken = true;
			else
				allowMobileToken= false;
		} catch(Exception e){
			throw new HibernateException(e);
		} finally {
			if(session != null && session.isOpen()) {
				session.close();            
			}
		}
		return allowMobileToken;
	}
	
	
	@Override
	public boolean isAssignNodelOfficer(Long userId) throws Exception{
		boolean assignNodelOfficer = true;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select case when count(1)>0 then true else false end   from nodal_officer where user_id=:userId and isactive");
			query.setParameter("userId", userId);
			assignNodelOfficer=(boolean)query.uniqueResult();
		} catch(Exception e){
			throw new HibernateException(e);
		} finally {
			if(session != null && session.isOpen()) {
				session.close();            
			}
		}
		return assignNodelOfficer;
		
	}

	@Override
	public NodalOfficerState getNodalOfficerDetails(Long userId) {
		Session session = null;
		NodalOfficerState nodalOfficerState=null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("from NodalOfficerState where userId=:userId and isactive=true");
			query.setParameter("userId", userId);
			nodalOfficerState=(NodalOfficerState) query.list().get(0);
		} catch(Exception e){
			throw new HibernateException(e);
		} finally {
			if(session != null && session.isOpen()) {
				session.close();            
			}
		}
		return nodalOfficerState;
	}
	
	@Override
	public Response sendMailSMS(String mailIds,String pnos) {
		Response response=new Response();
		try {
			
			mailIds=checkEmail(mailIds)?mailIds:null;
			pnos=isValidMob(pnos)?pnos:null;
			if(!(mailIds==null && pnos==null)) {
				String message = LGDResourceBundle.getBundle("lgd_mobile_message", Locale.ENGLISH).getObject("lgd.test.message").toString();
				String tmessage=null;
				String subject =     LGDResourceBundle.getBundle("lgd_mobile_message", Locale.ENGLISH).getObject("lgd.test.subject").toString();
				SmsManager sms = new SmsManager();
				if(pnos!=null && !pnos.isEmpty() && pnos.length()>0) {
					tmessage="SMS "+message;
					sms.makeHTTPConnection(pnos, tmessage);
				}
				if(mailIds!=null && !mailIds.isEmpty() && mailIds.length()>0) {
					tmessage="Mail "+message;
					MailService mailService = new MailService();
					mailService.sendMail(mailIds, subject, tmessage);
				}
				response.setResponseCode(200);
				response.setResponseMessage("Your message sent successfully!");
			}else {
				response.setResponseCode(500);
				response.setResponseMessage("Please enter at least one valid option Email or Mobile No.");
			}
			
			
		} catch (Exception e) {
			log.error("testSMSandEmail", e);
			response.setResponseCode(500);
			response.setResponseMessage(e.getCause()!=null?e.getCause().getMessage():"Problem to send check log for detials");
		}
		return response;
	}
	
	public static boolean checkEmail(String mail)
	{
		String patteren="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if(!mail.matches(patteren)){
			return false;
		}else{
			return true;
		}
	}
	
	 public static boolean isValidMob(String s) 
	    { 
	        // The given argument to compile() method  
	        // is regular expression. With the help of  
	        // regular expression we can validate mobile 
	        // number.  
	        // 1) Begins with 0 or 91 
	        // 2) Then contains 7 or 8 or 9. 
	        // 3) Then contains 9 digits 
	        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 
	  
	        // Pattern class contains matcher() method 
	        // to find matching between given number  
	        // and regular expression 
	        Matcher m = p.matcher(s); 
	        return (m.find() && m.group().equals(s)); 
	    } 
}