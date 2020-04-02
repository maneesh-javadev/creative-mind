package in.nic.pes.lgd.common;

import java.io.IOException;
import java.net.URL;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.nic.pes.lgd.bean.SessionObject;

public class DWRRequestFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(DWRRequestFilter.class);
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpServletRequest.getSession();
		String userID = (String) httpSession.getAttribute("login_key_id");
		LOG.info("THE USER id " + userID);
		if (userID == null) {
			SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
			userID =sessionObject!=null && sessionObject.getUserId()!=null?sessionObject.getUserId().toString():null;
		}
		String requestURL = httpServletRequest.getRequestURL().toString();
		String contextPath = httpServletRequest.getContextPath();
		URL url = new URL(requestURL);
		String path = url.getPath();
		LOG.info("=============path=========:" + path);
		if (userID == null) 
		{
			if (path.equalsIgnoreCase(contextPath+"/dwr/util.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrCaptchaService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/engine.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/engine.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/__System.pageLoaded.dwr")
					|| path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrDistrictService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrVillageService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrSubDistrictService.js")
					// Tanuj
					|| path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrLGBTypeService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrLGBTypeService.getLBGTypeList.dwr")
					|| path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrlocalBodyService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode.dwr")
					|| path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrlocalBodyService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrlocalBodyService.getchildlocalbodiesByParentcode.dwr")
					// Tanuj End
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plainpoll/ReverseAjax.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/interface/consolidatedReport.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrCaptchaService.validateCaptchaCode.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrDistrictService.getDistrictListGlobal.dwr")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrSubDistrictService.getSubDistListbyDistCodeShift.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrDistrictService.getDistrictList.dwr")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrSubDistrictService.getSubDistrictList.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrParlimentService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrParlimentService.getParliamentConstituencyDetail.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrdownloadAssemblyService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrdownloadAssemblyService.getAssemblyConstituencyDetail.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrParlimentService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrParlimentService.getParliamentConstituencyDetail.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrdownloadAssemblyService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrdownloadAssemblyService.getAssemblyConstituencyDetail.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrDesignationService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrDesignationService.getLocalbodyDetail.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrlocalBodyService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrlocalBodyService.getPanchayatListbyStateandCategory.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrCoveredWardService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrCoveredWardService.getLGBforCoveredDistListExWard.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrlocalBodyServiceforULB.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrlocalBodyServiceforULB.getPanchayatListbyStateandlbTypeCode.dwr")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrlocalBodyService.getchildlocalbodiesWithoutCountByParentcode.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrStateService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrStateService.getGovtOrderByEntityCode.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrStateService.openFile.dwr")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrlocalBodyService.getPanchayatListbyStateandlbTypeCode.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/dwr/call/plaincall/lgdDwrDesignationService.getLocalbodyDetailbyCode.dwr")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrlocalBodyService.getPanchayatListbylblcCode.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrCoveredVillageService.js")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrCoveredVillageService.isVillageExist.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrDesignationService.getLocalbodyDetailbyCode.dwr")
					|| path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrBlockService.js") || path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrBlockService.getBlockList.dwr") || path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrBlockService.getBlockListbyDistrict.dwr")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrlocalBodyService.getPanchayatListbyStateandCategoryNewWardF.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrlocalBodyService.getStateTopHierarchy.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrlocalBodyService.getStateTopHierarchyforGta.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrStateService.getDetailsofDocument.dwr")
					/*added by Ashish Dhupia on 20/1/2015 for Habitation use case*/
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrVillageService.getVillageList.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrlocalBodyService.gethierarchyforGP.dwr")
					/*added by Ashish Dhupia on 10/02/2015 for Habitation Report use case*/
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrSubDistrictService.getSubDistrictListForLocalbody.dwr")
				
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrStateService.getAllStates.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrStateService.getStateSourceList.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrOrganizationService.js")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrOrganizationService.getOrganizationDetailbystateCode.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrOrganizationService.getOrganizationDetailbySlcCode.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrOrganizationService.getAdministrativeUnitLevelByOrgCode.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrOrganizationService.getDepartmentDetails.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrOrganizationService.getAdministrativeUnitLevelDeptByOrgCode.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrOrganizationService.getOrganizationDetails.dwr")

					||  path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrAssemblyService.js")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrAssemblyService.getAssemblyConstituencyListbyParliamenCodet.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrInitialService.js")
					||  path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrCommonService.js")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrInitialService.getAvailableFAQs.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/interface/dwrRestructuredLocalBodyService.js")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/dwrRestructuredLocalBodyService.getLBTypeHierarchyStateWiseDetials.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/dwrRestructuredLocalBodyService.buildLocalBodyHierarchy.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/dwrRestructuredLocalBodyService.getDistrictPanchayatList.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/dwrRestructuredLocalBodyService.getParentwiseLocalBody.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/dwrReportService.buildLocalBodyHierarchyWithCategorywithCantonmentRPT.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/dwrRestructuredLocalBodyService.getMappedLBsForGIS.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrCommonService.getDistrictDetailsByStateCode.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrCommonService.getUnMappedPolygonSubDistrictByDistrict.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/dwrRestructuredLocalBodyService.getParentwiseLocalBodyRPT.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/dwrRestructuredLocalBodyService.getDistrictPanchayatListRPT.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrInitialService.getSupportDownloadDoc.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/__System.pageLoaded.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/interface/lgdDwrWSService.js")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrWSService.getOrganizationListByLocation.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrWSService.captachAuthentication.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrInitialService.getDataFromJsonFile.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/interface/dwrReportService.js")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/dwrReportService.getParentwiseChildDetils.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrStateService.getEntityFreezeStatus.dwr")
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/dwrReportService.getEntitywiseParentDetails.dwr")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrDesignationService.getLocalbodyDetailCantonment.dwr")
					/**
					 * This Method is Use for External User 
					 * @param loginForm
					 * @author Maneesh Kumar
					 * @since 01-10-2019
					 * @return
					 */
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrInitialService.isUserLoginNameExist.dwr")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrInitialService.validateCaptchaAnswer.dwr")
					|| path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrInitialService.isPasswordValid.dwr")
					
					
			
					||  path.equalsIgnoreCase(contextPath+"/dwr/call/plaincall/lgdDwrStateService.getOrganisationList.dwr")
					
					 /**
					 * end External User
					 */
					 
			// Maneesh

			)
			{
				LOG.info("Healthy DWR Request...");
			} 
			else
			{
				LOG.info("Bad DWR Request...");
				httpServletResponse.sendError(226);
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}