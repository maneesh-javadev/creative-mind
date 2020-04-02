package in.nic.pes.lgd.dao.impl;



import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.nic.pes.lgd.bean.GETDashboardEntityCount;
import in.nic.pes.lgd.bean.GETDashboardLBDetails;
import in.nic.pes.lgd.bean.GETDashboardNodalOfficer;
import in.nic.pes.lgd.bean.GETDashboardVillageDetails;
import in.nic.pes.lgd.bean.GetDashboardChangeEntityCount;
import in.nic.pes.lgd.bean.GetDashboardChangeEntityDetail;
import in.nic.pes.lgd.dao.DashboardDao;
import in.nic.pes.lgd.forms.DashboardForm;
import ws.in.nic.pes.lgdws.entity.WSState;

@Repository
public class DashboardDaoImpl implements DashboardDao {
	
	private static final Logger log = Logger.getLogger(DashboardDaoImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public DashboardForm getDashboardDetails(Integer stateCode, Character userType) {
		DashboardForm dashboardForm=new DashboardForm();
		Session session=null;
		try{
			session = sessionFactory.openSession();
			Query query=null;
			
			if(stateCode>0) {
				query = session.getNamedQuery("GETDashboardEntityCountDTO"); 
				query.setParameter("stateCode", stateCode);
				dashboardForm.setGetDashboardRevenueDetails((GETDashboardEntityCount)query.uniqueResult());
				
				query = session.getNamedQuery("GETDashboardNodalOfficerDTO"); 
				query.setParameter("stateCode", stateCode);
				query.setParameter("userType", userType);
				dashboardForm.setGetDashboardNodalOfficer((GETDashboardNodalOfficer)query.uniqueResult());
			}else {
				session = sessionFactory.openSession();
				query = session.getNamedQuery("GETDashboardAllEntityCountDTO"); 
				dashboardForm.setGetDashboardRevenueDetails((GETDashboardEntityCount)query.uniqueResult());
			}
			
			
			query = session.getNamedQuery("DashboardChangeEntityCountWithoutFinYearDTO"); 
			query.setParameter("stateCode", stateCode);
			query.setParameter("userType", userType);
			dashboardForm.setGetDashboardEntityDetails((List<GetDashboardChangeEntityCount>)query.list());
		
		}catch(Exception e){
			log.error("Dashboard Exception(getDashboardDetails)-->" + e);
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return dashboardForm;
	}
	
	@Override
	public List<GetDashboardChangeEntityCount> getEntityDetailsList(Integer stateCode, Character userType,String finYear) {
		List<GetDashboardChangeEntityCount> getDashboardEntityDetailsList=null;
		Session session=null;
		try{
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("DashboardChangeEntityCountWithFinYearDTO"); 
			query.setParameter("stateCode", stateCode);
			query.setParameter("userType", userType);
			query.setParameter("finYear", finYear);
			getDashboardEntityDetailsList=query.list();
			
		}catch(Exception e){
			log.error("Dashboard Exception(getEntityDetailsList)-->" + e);
			e.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return getDashboardEntityDetailsList;
	}
	
	@Override
	public List<GETDashboardVillageDetails> getVillageDetails(Integer stateCode, String flag) {
		List<GETDashboardVillageDetails> getGETDashboardVillageDetailsList=null;
		Session session=null;
		try{
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("DashboardVillageDetails"); 
			query.setParameter("stateCode", stateCode);
			query.setParameter("flag", flag);
			System.out.print("select * from get_dashboard_invalidate_village_details("+stateCode+",'"+flag+"');");
			getGETDashboardVillageDetailsList=query.list();
			
		}catch(Exception e){
			log.error("Dashboard Exception(getVillageDetails)-->" + e);
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return getGETDashboardVillageDetailsList;
	}
	
	
	@Override
	public List<GETDashboardLBDetails> getLocalbodyDetails(Integer stateCode, String flag) {
		List<GETDashboardLBDetails> getDashboardLBDetailsList=null;
		Session session=null;
		try{
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("DashboardLBDetails"); 
			query.setParameter("stateCode", stateCode);
			query.setParameter("flag", flag);
			System.out.print("select * from get_dashboard_lb_details("+stateCode+",'"+flag+"');");
			getDashboardLBDetailsList=query.list();
			
		}catch(Exception e){
			log.error("Dashboard Exception(getLocalbodyDetails)-->" + e);
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return getDashboardLBDetailsList;
	}
	
	@Override
	public List<GETDashboardEntityCount> getAllEntityCountList() {
		List<GETDashboardEntityCount> getDashboardEntityCountList=null;
		Session session=null;
		try{
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("GETDashboardAllEntityCountDTO"); 
			getDashboardEntityCountList=query.list();
			
		}catch(Exception e){
			log.error("Dashboard Exception(getAllEntityCountList)-->" + e);
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return getDashboardEntityCountList;
	}
	
	@Override
	public List<WSState> getStateList() {
		List<WSState> getStateList=null;
		Session session=null;
		try{
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getStateList"); 
			getStateList=query.list();
			
		}catch(Exception e){
			log.error("Dashboard Exception(getStateList)-->" + e);
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return getStateList;
	}
	

	@Override
	public List<GetDashboardChangeEntityDetail> getEntityChangeDetail(Integer stateCode,String flag,Character userType,String finYear) {
		List<GetDashboardChangeEntityDetail> getDashboardChangeEntityDetailList=null;
		Session session=null;
		try{
			System.out.println("into getEntityChangeDetail method");
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("DashboardChangeEntityDetailWithFinYearDTO");
			query.setParameter("stateCode", stateCode);
			query.setParameter("userType", userType);
			query.setParameter("finYear", finYear);
			query.setParameter("flag", flag);
			System.out.println("select row_number() OVER () as id, * from get_dashboard_entity_change_deatil("+stateCode+",'" +userType+ "','" +flag +"','" +finYear+ "');");
			getDashboardChangeEntityDetailList=query.list();
			
		}catch(Exception e){
			log.error("Dashboard Exception(getEntityChangeDetail)-->" + e);
			e.printStackTrace(); 
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return getDashboardChangeEntityDetailList;
	}
	
	

}
