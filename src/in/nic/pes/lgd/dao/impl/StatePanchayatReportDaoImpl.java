package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.ReportStatePanchayatPojo;
import in.nic.pes.lgd.dao.StatePanchayatReportDao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class StatePanchayatReportDaoImpl implements StatePanchayatReportDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<ReportStatePanchayatPojo> getStatePanchayatReport(char entityType, Session session) throws Exception
			{
				 Query query = null;
				 List<ReportStatePanchayatPojo> reportList = new ArrayList<ReportStatePanchayatPojo>();
				 try {
					//System.out.println("Inside the DaoImpl class");
					 query = session.getNamedQuery("getLocalGovSetupRptFn")
							 .setParameter("entity_type",entityType,Hibernate.CHARACTER);
					 reportList = query.list();
				} catch (Exception e) {
					//e.printStackTrace();
					 throw e;
				}
				 return reportList;
			}
}
