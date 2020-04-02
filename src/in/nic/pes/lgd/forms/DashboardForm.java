package in.nic.pes.lgd.forms;

import java.util.List;

import in.nic.pes.lgd.bean.GETDashboardNodalOfficer;
import in.nic.pes.lgd.bean.GETDashboardEntityCount;
import in.nic.pes.lgd.bean.GetDashboardChangeEntityCount;

public class DashboardForm {
	
	
  private GETDashboardNodalOfficer getDashboardNodalOfficer;
  private GETDashboardEntityCount getDashboardRevenueDetails;
  private List<GetDashboardChangeEntityCount> getDashboardEntityDetails;
  
	public GETDashboardNodalOfficer getGetDashboardNodalOfficer() {
		return getDashboardNodalOfficer;
	}
	public void setGetDashboardNodalOfficer(GETDashboardNodalOfficer getDashboardNodalOfficer) {
		this.getDashboardNodalOfficer = getDashboardNodalOfficer;
	}
	public GETDashboardEntityCount getGetDashboardRevenueDetails() {
		return getDashboardRevenueDetails;
	}
	public void setGetDashboardRevenueDetails(GETDashboardEntityCount getDashboardRevenueDetails) {
		this.getDashboardRevenueDetails = getDashboardRevenueDetails;
	}
	public List<GetDashboardChangeEntityCount> getGetDashboardEntityDetails() {
		return getDashboardEntityDetails;
	}
	public void setGetDashboardEntityDetails(List<GetDashboardChangeEntityCount> getDashboardEntityDetails) {
		this.getDashboardEntityDetails = getDashboardEntityDetails;
	}
  
  
	
}
