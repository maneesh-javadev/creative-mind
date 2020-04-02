package in.nic.pes.lgd.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import in.nic.pes.common.beans.SubscribeApplication;
import in.nic.pes.lgd.bean.EmailNotification;
import in.nic.pes.lgd.bean.NotificationLog;

public interface EmailDAO {
	public boolean saveNotifacation(EmailNotification en) throws Exception;
	public boolean deleteNotifacation(Integer uid) throws Exception;
    public EmailNotification getEmailNotification(Integer uid);
    public String getSubscribedUsers(char c) ;
    public  List<Object> maildetails(int tid);
    public List<SubscribeApplication> getSubscribingApplicationDetailDAO(Long uid,Session session);
    public boolean updateSubscribeInformationDAO(SubscribeApplication obj,Session session);
    public boolean insertSubscribeInformationDAO(SubscribeApplication obj,Session hsession,HttpSession httpSession);
	public boolean deleteSubscribeInformationDAO(SubscribeApplication obj,Session hsession);
	public void _postSuccess(NotificationLog notificationlog);
    
}
