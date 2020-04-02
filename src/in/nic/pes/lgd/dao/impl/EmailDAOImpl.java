package in.nic.pes.lgd.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

import in.nic.pes.common.beans.SubscribeApplication;
import in.nic.pes.lgd.bean.EmailId;
import in.nic.pes.lgd.bean.EmailNotification;
import in.nic.pes.lgd.bean.NotificationLog;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.common.persistencecontext.HibernateSessionEmail;
import in.nic.pes.lgd.dao.EmailDAO;

public class EmailDAOImpl implements EmailDAO {
	private static final Logger log = Logger.getLogger(EmailDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveNotifacation(EmailNotification eNoti) {
		Session session = null;
		Transaction tx = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			query = session
					.createQuery("from EmailNotification en where en.userid=:code ");
			query.setParameter("code", eNoti.getUserid(), Hibernate.INTEGER);
			@SuppressWarnings("unchecked")
			List<EmailNotification> list = query.list();
			if (list.size() > 0) {
				EmailNotification en = (EmailNotification) session.get(
						EmailNotification.class, list.get(0).getId());
				en.setDistrict(eNoti.getDistrict());
				en.setSubdistrict(eNoti.getSubdistrict());
				en.setVillage(eNoti.getVillage());
				en.setUrbanbody(eNoti.getUrbanbody());
				en.setRuralbody(eNoti.getRuralbody());
				en.setTradionbody(eNoti.getTradionbody());
				en.setBlock(eNoti.getBlock());
				session.save(en);
			} else
				session.save(eNoti);
			tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null){
				tx.rollback();
			}	
			return false;
		}

		finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return true;
	}

	@Override
	public boolean deleteNotifacation(Integer uid) throws Exception {
		Session session = null;
		Transaction tx = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			query = session
					.createQuery("from EmailNotification en where en.userid=:code ");
			query.setParameter("code", uid, Hibernate.INTEGER);
			@SuppressWarnings("unchecked")
			List<EmailNotification> list = query.list();

			EmailNotification en = (EmailNotification) session.get(
					EmailNotification.class, list.get(0).getId());
			en.setDistrict(false);
			en.setSubdistrict(false);
			en.setVillage(false);
			en.setUrbanbody(false);
			en.setRuralbody(false);
			en.setTradionbody(false);
			session.save(en);
			tx.commit();
			session.flush();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return false;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return true;
	}

	@Override
	public EmailNotification getEmailNotification(Integer uid) {
		Session session = null;
		//Transaction tx = null;
		//Integer id = null;
		Query query = null;
		EmailNotification en = null;
		try {
			session = sessionFactory.openSession();
			//tx = session.beginTransaction();
			query = session
					.createQuery("from EmailNotification en where en.userid=:code ");
			query.setParameter("code", uid, Hibernate.INTEGER);
			@SuppressWarnings("unchecked")
			List<EmailNotification> list = query.list();
			if (list.size() > 0)
				en = list.get(0);
			else
				return null;
			session.flush();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return en;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getSubscribedUsers(char c) {
		Session session = null;
		//Transaction tx = null;
		ArrayList<Integer> value = new ArrayList<Integer>();
		Query query = null;
		List<EmailNotification> list = null;
		EmailNotification emailNotification = null;
		String userid = new String();
		String emailids = null;
		try {
			session = sessionFactory.openSession();
			//tx = session.beginTransaction();
			if (c == 'V') {
				query = session
						.createQuery("from EmailNotification en where en.village=:code ");
				query.setParameter("code", true, Hibernate.BOOLEAN);

			} else if (c == 'D') {
				query = session
						.createQuery("from EmailNotification en where en.district=:code ");
				query.setParameter("code", true, Hibernate.BOOLEAN);

			} else if (c == 'S') {
				query = session
						.createQuery("from EmailNotification en where en.subdistrict=:code ");
				query.setParameter("code", true, Hibernate.BOOLEAN);

			} else if (c == 'U') {
				query = session
						.createQuery("from EmailNotification en where en.urbanbody=:code ");
				query.setParameter("code", true, Hibernate.BOOLEAN);

			} else if (c == 'R') {
				query = session
						.createQuery("from EmailNotification en where en.ruralbody=:code ");
				query.setParameter("code", true, Hibernate.BOOLEAN);

			} else if (c == 'T') {
				query = session
						.createQuery("from EmailNotification en where en.tradionbody=:code ");
				query.setParameter("code", true, Hibernate.BOOLEAN);

			} else if (c == 'B') {
				query = session
						.createQuery("from EmailNotification en where en.block=:code ");
				query.setParameter("code", true, Hibernate.BOOLEAN);
			}
			list = query.list();
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					emailNotification = list.get(i);
					value.add(emailNotification.getUserid());
					//System.out.println("THE VALUE RETURNED::: " + value.get(i));
				}

				for (Integer p : value) {

					userid = userid + new Integer(p).toString() + ",";
				}
				userid = userid.substring(0, userid.length() - 1);

			}
			if (userid != null && !userid.isEmpty() && !userid.trim().isEmpty()){
				emailids = Subsicribedemails(userid);
			}	
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;

		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return emailids;
	}

	@SuppressWarnings("unchecked")
	private String Subsicribedemails(String uid) {
		Query query = null;
		List<EmailId> userMailPhNO = null;
		EmailId eid = null;
		Session session2 = null;
		Transaction tx = null;
		String data = null;
		ArrayList<String> value = new ArrayList<String>();
		int i = 0;
		String mail_phonno = new String();
		try {
			session2 = HibernateSessionEmail.email();
			tx = session2.beginTransaction();
			query = session2.getNamedQuery("getEmails").setParameter("UserId",
					uid, Hibernate.STRING);
			userMailPhNO = query.list();
			if (userMailPhNO.size() > 0) {
				// Set all email id's in emails string in , seprated format
				for (i = 0; i < userMailPhNO.size(); i++) {
					eid = userMailPhNO.get(i);
					data = eid.getEmail();
					if (data != null){
						value.add(data);
					}	
				//	System.out.println("THE VALUE RETURNED::: " + value.get(i));

				}
				value.add(";");
				for (i = 0; i < userMailPhNO.size(); i++) {
					eid = userMailPhNO.get(i);
					data = eid.getPhoneno();
					if (data != null){
						value.add(data);
					}	
				//	System.out.println("THE VALUE RETURNED::: " + value.get(i));
				}
				for (String p : value) {
					if (!p.isEmpty()){
						mail_phonno += new String(p).toString() + ",";
					}	
				}
				mail_phonno = mail_phonno
						.substring(0, mail_phonno.length() - 1);
				// mail_phonno = mail_phonno.substring(4);
			} 
		} catch (Exception e) {
			tx.rollback();
			return null;
		} finally {
			HibernateSessionEmail.closeSession();
			if (session2 != null && session2.isOpen()){
				session2.close();
			}	
		}
		return mail_phonno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> maildetails(int tid) {
		Session session = null;
		//Transaction tx = null;
		Query query = null;
		List<Object> md = new ArrayList<Object>();

		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery("select entity_transactions.description,operations.operation_name from entity_transactions,operations where entity_transactions.tid="
							+ tid
							+ "and entity_transactions.operation_code=operations.operation_code");
			md = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return md;
	}

	@SuppressWarnings("unchecked")
	public List<SubscribeApplication> getSubscribingApplicationDetailDAO(
			Long uid, Session session) {

		Integer userid = uid.intValue();
		List<SubscribeApplication> subscribeDetails = null;
		subscribeDetails = new ArrayList<SubscribeApplication>();

		Query result = null;
		try {

			result = session
					.createQuery(
							"from SubscribeApplication where userId=:userId order by applicationId")
					.setParameter("userId", userid);

			subscribeDetails = result.list();
		} catch (Exception e) {
			log.debug("Exception" + e);

		}

		return subscribeDetails;

	}

	public boolean insertSubscribeInformationDAO(SubscribeApplication obj,
			Session hsession, HttpSession httpSession) {
		Query result = null;
	
		Integer applicationId = null;
		boolean success = false;
		try {
			SessionObject sessionObject = (SessionObject)httpSession.getAttribute("sessionObject");
			
				
			long useridl =sessionObject.getUserId().longValue();
			int userid=	(int)useridl;
				
			result = hsession
					.createSQLQuery(
							"select COALESCE(Max(application_id),1) from subscribing_application");
					
			applicationId = Integer.parseInt(result.list().get(0).toString());
			obj.setLastupdated(new Date());
			obj.setLastupdatedby(useridl);
			obj.setCreateBy(useridl);
			obj.setCreatedon(new Date());
			obj.setApplicationId(applicationId + 1);
			obj.setUserId(userid);
			obj.setFlag("Z");
			hsession.save(obj);

			success = true;
		} catch (Exception e) {
			log.debug("Exception" + e);

			success = false;
		}
		
		
		return success;
	}

	public boolean updateSubscribeInformationDAO(SubscribeApplication obj,
			Session hsession) {
		Query result = null;

		int rowupdate;
		boolean success = false;
		try {

			Long userid = Long.parseLong(obj.getUserId().toString());
			Long applicationid = Long.parseLong(obj.getApplicationId()
					.toString());

			result = hsession
					.createSQLQuery(
							"update public.subscribing_application set name_of_application=:nameofApplication,flag=:flag, url=:url,lastupdated=:lastupdated ,lastupdatedby=:lastupdatedby"
									+ " where userid=:userid and application_id=:applicationId")
					.setParameter("nameofApplication", obj.getApplicationName())
					.setParameter("url", obj.getUrl())
					.setParameter("lastupdated", new Date())
					.setParameter("lastupdatedby", userid)
					.setParameter("userid", userid)
					.setParameter("applicationId", applicationid)
					.setParameter("flag", "Z");

			rowupdate = result.executeUpdate();
			if (rowupdate > 0){
				success = true;
			}	
		} catch (Exception e) {
			log.debug("Exception" + e);

			success = false;
		}
		return success;
	}

	public boolean deleteSubscribeInformationDAO(SubscribeApplication obj,
			Session hsession) {

		Query result = null;
		int rowupdate;
		boolean success = false;
		try {
			Long userid = Long.parseLong(obj.getUserId().toString());
			Long applicationid = Long.parseLong(obj.getApplicationId()
					.toString());
			result = hsession
					.createSQLQuery(
							"delete from public.subscribing_application where userid=:userid and application_id=:applicationId")
					.setParameter("userid", userid)
					.setParameter("applicationId", applicationid);
			rowupdate = result.executeUpdate();
			if (rowupdate > 0){
				success = true;
			}	
		} catch (Exception e) {
			log.debug("Exception" + e);

			success = false;
		}
		return success;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void _postSuccess(NotificationLog notificationlog) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Timestamp timestamp= new Timestamp(System.currentTimeMillis());
			notificationlog.setCreatedOn(timestamp);
			session.save(notificationlog);
			session.flush();
		} catch(Exception exception){
			exception.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}
	
}
