package in.nic.pes.lgd.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import in.nic.pes.common.beans.SubscribeApplication;
import in.nic.pes.lgd.bean.EmailNotification;
import in.nic.pes.lgd.forms.EmailNotiForm;

public interface EmailService {
	public boolean saveNotification(EmailNotiForm e);
	public boolean deleteNotification(Integer uid);
	public EmailNotification getEmailNotififcation(Integer uid);
	public int sendEmail(int code,char type);
	public List<SubscribeApplication> getSubscribingApplicationDetail(Long uid) throws Exception;

	public boolean updateSubscribeInformation(SubscribeApplication obj) throws Exception;
	public boolean insertSubscribeInformation(SubscribeApplication obj,HttpSession httpSession) throws Exception;
	public boolean deleteSubscribeInformation(SubscribeApplication obj) throws Exception;
	
}
