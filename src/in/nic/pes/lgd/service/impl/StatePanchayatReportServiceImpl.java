package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.ReportStatePanchayatPojo;
import in.nic.pes.lgd.dao.StatePanchayatReportDao;
import in.nic.pes.lgd.service.StatePanchayatReportService;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatePanchayatReportServiceImpl implements StatePanchayatReportService{
private static final Logger log = Logger.getLogger(StatePanchayatReportServiceImpl.class);	

@Autowired
StatePanchayatReportDao statePanchayatReportDao;

@Autowired
SessionFactory sessionFactory;
	
@Override
public List<ReportStatePanchayatPojo> getStatePanchayatReport(char panchayat) throws Exception 
	{

		Session session = null;
		List<ReportStatePanchayatPojo> reportList = new ArrayList<ReportStatePanchayatPojo>();		
		try 
			{
				session = sessionFactory.openSession();
				reportList = statePanchayatReportDao.getStatePanchayatReport(panchayat, session);
			} 
		catch (Exception e) 
			{
				log.debug("Exception" + e);
				throw e;
			}
		finally
		{
			 if(session != null && session.isOpen()){
				 session.close();
			 }	 
		}
		return reportList;
	}

}



