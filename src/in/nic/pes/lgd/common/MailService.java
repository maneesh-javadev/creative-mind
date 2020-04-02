package in.nic.pes.lgd.common;
import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;

import in.nic.pes.lgd.bean.NotificationLog;
import in.nic.pes.lgd.dao.EmailDAO;

public class MailService
{

	//private MailSender mailSender;
	private static final String SMTP_HOST_NAME = "relay.nic.in";
	private static final int SMTP_HOST_PORT = 25;
	private static final String SMTP_AUTH_USER = "noreply@nic.in";
	private static final String SMTP_AUTH_PWD = "";
	/*private static final String SMTP_AUTH_USER = "nicmopr@nic.in";
	private static final String SMTP_AUTH_PWD = "Nic@#674";*/
	@Autowired
	EmailDAO emailDao;

	public void setMailSender()
	{
		//	this.mailSender = mailSender;
	}

	//public void sendMail(String toAddress, String messageSubject, String messageText) throws Exception {

	//		SimpleMailMessage message = new SimpleMailMessage();
	//		message.setTo(toAddress);
	//		message.setSubject(messageSubject);
	//		message.setText(messageText);
	//		System.out.println("Sending email ....");
	//		mailSender.send(message);	
	//		sendMail(toAddress,messageText);
	//}

	//public void sendMail(String fromAddress, String toAddress, String messageSubject, String messageText) throws Exception {

	//		SimpleMailMessage message = new SimpleMailMessage();
	//		message.setFrom(fromAddress);
	//		message.setTo(toAddress);
	//		message.setSubject(messageSubject);
	//		message.setText(messageText);
	//		System.out.println("Sending email ....");
	//		mailSender.send(message);	
	//sendMail(toAddress,messageText);
	//}

	public boolean sendMail(String mailTo, String subject, String mailMessage)
	{

		boolean isSent = false;		
		try
		{

			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.auth", "false");
			props.put("mail.smtp.quitwait", "false");
			props.put("mail.smtp.starttls.enable", "false");
			Session mailSession = Session.getDefaultInstance(props);

			mailSession.setDebug(true);

			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);

			//message.setFrom(new InternetAddress(mailTo));
			message.setFrom(new InternetAddress(SMTP_AUTH_USER));

			message.setSubject(subject);
			Multipart mp = new MimeMultipart();
			MimeBodyPart messageBodyParthtml = new MimeBodyPart();
			messageBodyParthtml.setContent(mailMessage, "text/html");

			mp.addBodyPart(messageBodyParthtml);

			message.setContent(mp);
			String[] mailList = mailTo.split(",");

			if (mailList.length > 0)
			{
				for (int lenOfMailList = 0; lenOfMailList < mailList.length; lenOfMailList++)
				{
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailList[lenOfMailList]));
				}

			}
			//  message.addRecipient(Message.RecipientType.TO, new InternetAddress("npsreejith@gmail.com"));
			/*    mailList=copyTo.split(";");
			    if(mailList.length>0){
			        for (int lenOfCcList=0;lenOfCcList<mailList.length;lenOfCcList++){
			            message.addRecipient(Message.RecipientType.CC, new InternetAddress(mailList[lenOfCcList]));
			        }
			      
			    }
			*/

			/*transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);*/
			transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

			transport.sendMessage(message, message.getAllRecipients());
			// transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
			System.out.println("Email send....@@");

			transport.close();
			isSent = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			e.getCause().getMessage();
			//System.out.println(" Email Error Occured"+e.toString());
			isSent = false;
		}
		 return isSent;

	}
      
	public boolean sendMailNotification(NotificationLog notificationlog)
	{

		boolean isSent = false;		
		try
		{

			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.auth", "false");
			props.put("mail.smtp.quitwait", "false");
			props.put("mail.smtp.starttls.enable", "false");
			Session mailSession = Session.getDefaultInstance(props);
			mailSession.setDebug(true);

			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);

			//message.setFrom(new InternetAddress(mailTo));
			message.setFrom(new InternetAddress(SMTP_AUTH_USER));

			message.setSubject(notificationlog.getSubject());
			Multipart mp = new MimeMultipart();
			MimeBodyPart messageBodyParthtml = new MimeBodyPart();
			messageBodyParthtml.setContent(notificationlog.getMsgDesc(), "text/html");

			mp.addBodyPart(messageBodyParthtml);
			
			if(notificationlog.getFile()!=null){
				MimeBodyPart mbpFile = new MimeBodyPart();
				DataSource source = new FileDataSource(notificationlog.getFile());
				mbpFile.setDataHandler(new DataHandler(source));
				mbpFile.setFileName(notificationlog.getFile().getName());
				mp.addBodyPart(mbpFile);
			}
			message.setContent(mp);
			String[] mailList = notificationlog.getEmailId().split(",");

			if (mailList.length > 0)
			{
				for (int lenOfMailList = 0; lenOfMailList < mailList.length; lenOfMailList++)
				{
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailList[lenOfMailList]));
				}

			}
			//  message.addRecipient(Message.RecipientType.TO, new InternetAddress("npsreejith@gmail.com"));
			/*    mailList=copyTo.split(";");
			    if(mailList.length>0){
			        for (int lenOfCcList=0;lenOfCcList<mailList.length;lenOfCcList++){
			            message.addRecipient(Message.RecipientType.CC, new InternetAddress(mailList[lenOfCcList]));
			        }
			      
			    }
			*/

			/*transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);*/
			transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

			transport.sendMessage(message, message.getAllRecipients());
			// transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
			System.out.println("Email send....@@");
			notificationlog.setMsgStatus("Success");
			transport.close();
			isSent = true;
		}
		catch (Exception e)
		{
			notificationlog.setMsgStatus("Failed  "+e.getMessage());
			e.printStackTrace();
			e.getCause().getMessage();
			//System.out.println(" Email Error Occured"+e.toString());
			isSent = false;
		}finally {
			_postSuccess(notificationlog);
	}
		 return isSent;

	}

	
	private void _postSuccess(NotificationLog notificationlog) {
		emailDao._postSuccess(notificationlog);
	}
	
	/*send mail with attachment*/
	
	public boolean sendMail(String mailTo, String subject, String mailMessage,File file)
	{
		boolean isSent = false;		
		try
		{
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.auth", "false");
			props.put("mail.smtp.quitwait", "false");
			props.put("mail.smtp.starttls.enable", "false");
			Session mailSession = Session.getDefaultInstance(props);
			mailSession.setDebug(true);
			Transport transport = mailSession.getTransport();
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(SMTP_AUTH_USER));

			message.setSubject(subject);
			Multipart mp = new MimeMultipart();
			MimeBodyPart messageBodyParthtml = new MimeBodyPart();
			messageBodyParthtml.setContent(mailMessage, "text/html");

			mp.addBodyPart(messageBodyParthtml);
			
			
			if(file!=null){
				MimeBodyPart mbpFile = new MimeBodyPart();
				DataSource source = new FileDataSource(file);
				mbpFile.setDataHandler(new DataHandler(source));
				mbpFile.setFileName(file.getName());
				mp.addBodyPart(mbpFile);
			}

			message.setContent(mp);
			String[] mailList = mailTo.split(",");

			if (mailList.length > 0)
			{
				for (int lenOfMailList = 0; lenOfMailList < mailList.length; lenOfMailList++)
				{
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailList[lenOfMailList]));
				}

			}
			transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
			transport.sendMessage(message, message.getAllRecipients());
			System.out.println("Email send....@@");
			transport.close();
			isSent = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			e.getCause().getMessage();
			isSent = false;
		}
		 return isSent;

	}

	
}
