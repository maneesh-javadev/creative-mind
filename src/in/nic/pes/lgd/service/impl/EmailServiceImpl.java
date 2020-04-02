package in.nic.pes.lgd.service.impl;

import in.nic.pes.common.beans.SubscribeApplication;
import in.nic.pes.lgd.bean.EmailNotification;
import in.nic.pes.lgd.common.MailService;
import in.nic.pes.lgd.common.SmsManager;
import in.nic.pes.lgd.dao.EmailDAO;
import in.nic.pes.lgd.forms.EmailNotiForm;
import in.nic.pes.lgd.service.EmailService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailServiceImpl<E> implements EmailService {
	private static final Logger log = Logger.getLogger(EmailServiceImpl.class);
	@Autowired
	EmailDAO emailDao;
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean saveNotification(EmailNotiForm e) {
		EmailNotification eNoitification = new EmailNotification();
		eNoitification.setDistrict(e.getDistrict());
		eNoitification.setSubdistrict(e.getSubdistrict());
		eNoitification.setVillage(e.getVillage());
		eNoitification.setUrbanbody(e.getUrbanbody());
		eNoitification.setRuralbody(e.getRuralbody());
		eNoitification.setTradionbody(e.getTradionbody());
		eNoitification.setUserid(e.getUserid());
		eNoitification.setUser(e.getUser());
		eNoitification.setEmail(e.getEmail());
		eNoitification.setBlock(e.getBlock());
		try {
			emailDao.saveNotifacation(eNoitification);
		} catch (Exception e1) {
			log.debug("Exception" + e1);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteNotification(Integer uid) {
		try {
			emailDao.deleteNotifacation(uid);
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}

		return true;
	}

	@Override
	public EmailNotification getEmailNotififcation(Integer uid) {
		try {
			return emailDao.getEmailNotification(uid);
		} catch (Exception e) {
			log.debug("Exception" + e);
			return null;
		}

	}

	@Override
	public int sendEmail(int operationcode, char entitytype) {
		System.out.println("into sendEmail funcation....@@");
		int i = 0;
		int flag = 0;
		String mails_pnos = null;
		String subject = null;
		String message = null;
		boolean success = false;
		String emails = null;
		String pnos = null;
		String[] userdata = null;
		List<Object> maildescription = new ArrayList<Object>();
		MailService mailService = new MailService();
		Object[] obj = null;
		SmsManager sms = new SmsManager();
		// Get all Email id's Phone No in comma separated for - Subscribed Users
		mails_pnos = emailDao.getSubscribedUsers(entitytype);
		System.out.println("mails_pnos...@@"+mails_pnos);
		if (mails_pnos != null) {
			userdata = mails_pnos.split(";");
			if (userdata.length > 0) {
				emails = userdata[0].substring(0, userdata[0].length() - 1);
			}
			if (userdata.length > 1) {
				pnos = userdata[1].substring(1, userdata[1].length());
			}
			// Get Subject and message For Mail,SMS
			maildescription = (List<Object>) emailDao
					.maildetails(operationcode);
			if (maildescription.size() > 0) {
				obj = (Object[]) maildescription.get(i);
				if (obj != null) {
					if (obj[0] != null) {
						message = obj[0].toString();
					}
					if (obj[1] != null) {
						subject = obj[1].toString();
					}
				}
			}
			// Send Email's
			System.out.println("emails...@@"+emails);
			System.out.println("pnos...@@"+pnos);
			System.out.println("subject...@@"+subject);
			try {
				if (emails != null && !emails.isEmpty()) {
					if (subject != null && message != null) {
						String mailIds = emails;
						/**
						 * @author Maneesh Kumar 09May2016
						 * remove lgdirectory@googlegroups.com from mail service    
						 */
								//+ ",lgdirectory@googlegroups.com";
						success = mailService.sendMail(mailIds, subject,
								message);
					}
				} else {
					if (subject != null && message != null) {
						/*String mailIds = "lgdirectory@googlegroups.com";
						success = mailService.sendMail(mailIds, subject,
								message);*/
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				e.getCause().getMessage();
				log.debug("Exception" + e);
			}
			// Send SMS
			try {
				if (pnos != null && !pnos.isEmpty() && subject != null) {
					sms.makeHTTPConnection(pnos, message);

				}
			} catch (Exception e) {
				e.printStackTrace();
				e.getCause().getMessage();
				log.debug("Exception" + e);
			}
		}

		if (success) {
			flag = 1;
		}
		return flag;
	}

	@Override
	public List<SubscribeApplication> getSubscribingApplicationDetail(Long uid)
			throws Exception {

		List<SubscribeApplication> subscribeDetails = null;
		subscribeDetails = new ArrayList<SubscribeApplication>();

		Session hsession = null;
		hsession = sessionFactory.openSession();
		Transaction tx = hsession.beginTransaction();

		try {

			subscribeDetails = emailDao.getSubscribingApplicationDetailDAO(uid,
					hsession);

			if (subscribeDetails.size() > 0) {
				tx.commit();
				return subscribeDetails;
			} else
				tx.rollback();

		} catch (Exception e) {

			tx.rollback();
		} finally {
			if (hsession != null && hsession.isOpen()){
				hsession.close();
				sessionFactory.close();
			}	
			
		}
		return null;

	}

	@Override
	public boolean updateSubscribeInformation(SubscribeApplication obj)
			throws Exception {

		Session hsession = null;
		Transaction tx = null;
		boolean success = false;
		try {

			hsession = sessionFactory.openSession();
			tx = hsession.beginTransaction();
			success = emailDao.updateSubscribeInformationDAO(obj, hsession);
			tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null){
				tx.rollback();
			}	
			return false;
		}
		finally {

			if (hsession != null && hsession.isOpen()){
				hsession.close();	
			}	
			sessionFactory.close();
		}
		
		return success;
	}

	@Override
	public boolean insertSubscribeInformation(SubscribeApplication obj,
			HttpSession httpSession) throws Exception {

		Session hsession = null;
		Transaction tx = null;
		boolean success = false;
		try {

			hsession = sessionFactory.openSession();
			tx = hsession.beginTransaction();
			success = emailDao.insertSubscribeInformationDAO(obj, hsession,
					httpSession);
			tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null){
				tx.rollback();
			}	
			return false;
		}
		finally {

			if (hsession != null && hsession.isOpen()){
				hsession.close();	
			}	
			sessionFactory.close();
		}
		
		return success;
	}

	@Override
	public boolean deleteSubscribeInformation(SubscribeApplication obj)
			throws Exception {

		Session hsession = null;
		Transaction tx = null;
		boolean success = false;
		try {

			hsession = sessionFactory.openSession();
			tx = hsession.beginTransaction();
			success = emailDao.deleteSubscribeInformationDAO(obj, hsession);
			tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null){
				tx.rollback();
			}	
			return false;
		}
		finally {

			if (hsession != null && hsession.isOpen()){
				hsession.close();	
			}	
			sessionFactory.close();
		}
		
		return success;
	}

}
