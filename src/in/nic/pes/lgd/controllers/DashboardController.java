package in.nic.pes.lgd.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.GETDashboardEntityCount;
import in.nic.pes.lgd.bean.GETDashboardLBDetails;
import in.nic.pes.lgd.bean.GETDashboardVillageDetails;
import in.nic.pes.lgd.bean.GetDashboardChangeEntityCount;
import in.nic.pes.lgd.bean.GetDashboardChangeEntityDetail;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.forms.DashboardForm;
import in.nic.pes.lgd.service.DashboardService;
import ws.in.nic.pes.lgdws.entity.WSState;

@Controller
public class DashboardController {
	
	@Autowired
	DashboardService dashboardService;
	
	private Integer stateCode;
	
	private Integer userId;
	
	private Integer districtCode;
	
	private Character userType;
	
	
	/**
	 * 
	 * @param session
	 */
	private void setGlobalParams(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId().intValue();
		userType=session.getAttribute("isUserType")!=null?(Character)session.getAttribute("isUserType"):'X';
		
	}
	
	/**
	 * The {@code errorHandler} returns error path and saved required stack trace. 
	 * @param request
	 * @param e
	 * @return
	 */
	private String errorHandler(HttpServletRequest request, Exception e){
		IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		return expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
	}
	
	@RequestMapping(value = "/getDashboardDetials", method = RequestMethod.POST)
	public @ResponseBody DashboardForm getDashboardDetials(HttpSession session,@RequestBody Integer pStateCode) {
		setGlobalParams(session);
		pStateCode=pStateCode>0?pStateCode:stateCode;
		return dashboardService.getDashboardDetails(pStateCode, userType);
	}
	
	@RequestMapping(value = "/getEntityDetailsFinYearWise", method = RequestMethod.POST)
	public @ResponseBody List<GetDashboardChangeEntityCount> getEntityDetailsFinYearWise(HttpSession session,@RequestBody String finYear) {
		setGlobalParams(session);
		return dashboardService.getEntityDetailsList(stateCode, userType, finYear);
	}
	
	@RequestMapping(value = "/getVillageDetails", method = RequestMethod.POST)
	public @ResponseBody List<GETDashboardVillageDetails> getVillageDetails(HttpSession session,@RequestBody GETDashboardLBDetails getDashboardLBDetails) {
		setGlobalParams(session);
		Integer pStateCode=getDashboardLBDetails.getStateCode();
		pStateCode=pStateCode>0?pStateCode:stateCode;
		return dashboardService.getVillageDetails(pStateCode, getDashboardLBDetails.getFlag());
	}
	
	@RequestMapping(value = "/getLBDetails", method = RequestMethod.POST)
	public @ResponseBody List<GETDashboardLBDetails> getLBDetails(HttpSession session,@RequestBody GETDashboardLBDetails getDashboardLBDetails) {
		setGlobalParams(session);
		Integer pStateCode=getDashboardLBDetails.getStateCode();
		pStateCode=pStateCode>0?pStateCode:stateCode;
		return dashboardService.getLocalbodyDetails(pStateCode, getDashboardLBDetails.getFlag());
	}
	
	@RequestMapping(value = "/getAllEntityCountList", method = RequestMethod.POST)
	public @ResponseBody List<GETDashboardEntityCount> getAllEntityCountList() {
		return dashboardService.getAllEntityCountList();
	}
	
	@RequestMapping(value = "/getStateListDashboard", method = RequestMethod.POST)
	public @ResponseBody List<WSState> getStateListDashboard() {
		return dashboardService.getStateList();
	}
	
	@RequestMapping(value = "/getEntityChangeDetail", method = RequestMethod.POST)
	public @ResponseBody List<GetDashboardChangeEntityDetail> getEntityChangeDetail(HttpSession session,@RequestBody GetDashboardChangeEntityDetail getDashboardChangeEntityDetail) {
		
		setGlobalParams(session);
		Integer pStateCode=getDashboardChangeEntityDetail.getStateCode();
		pStateCode=pStateCode>0?pStateCode:stateCode;
		//flag=getDashboardChangeEntityDetail.getFlag()
		return dashboardService.getEntityChangeDetail(pStateCode, getDashboardChangeEntityDetail.getFlag(), userType, getDashboardChangeEntityDetail.getFinYear());
	}

}
