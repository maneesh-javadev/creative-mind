<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>

<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/shiftvillage.js"></script>
</head>
<body>
		<div id="frmcontent">
			 <div class="frmhd">
				    <table width="100%" class="tbl_no_brdr">
					      <tr>
						             <td><spring:message code="Label.VIEWSTATE" htmlEs;cape="true"></spring:message></td>
						             <td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></td>
						           <%--//these links are not working   <td width="50" align="right"><a href="#" class="frmhelp"><spring:message code="Label.HELP" htmlEscape="true"></spring:message></a></td> --%>
					     </tr>
				  </table>
			</div>
			   <div class="clear"></div>
			   <div class="frmpnlbrdr">
				   <form:form action="viewSubdistrict.htm" method="POST" commandName="Preview">
					<div class="frmpnlbg" >
						<div class="frmtxt">
						<table class="tbl_with_brdr" width="98%" >
								<tr><td colspan="4" class="tblRowTitle tblclear"><label>New State Created</label></td></tr>
								<tr class="tblRowTitle tblclear">
							    <td width="15%" ><spring:message code="Label.STATECODE" htmlEscape="true"></spring:message></td>
								<td width="15%" ><spring:message code="Label.STATEVERSION" htmlEscape="true"></spring:message></td>
								<td width="35%""><spring:message code="Label.STATENAMEENGLISH" htmlEscape="true"></spring:message></td>
								<td width="35%" ><spring:message code="Label.STATENAMELOCAL" htmlEscape="true"></spring:message></td>
							   </tr>
							   <c:forEach var="newSD" varStatus="fullRow" items="${newSD}">
							    <tr class="tblRowB">
							    <td width="15%" ><c:out value="${newSD.stateCode}" escapeXml="true"></c:out></td>
								<td width="15%"><c:out value="${newSD.stateVersion}" escapeXml="true"></c:out></td>
								<td width="35%" ><c:out value="${newSD.stateNameEnglish}" escapeXml="true"></c:out></td>
								<td width="35%" ><c:out value="${newSD.stateNameLocal}" escapeXml="true"></c:out></td>
							  </tr>
							  </c:forEach>
						</table>
						</div>
						</div>
						
						
						<div class="frmpnlbg">
						<div class="frmtxt">
						
						<table class="tbl_with_brdr" width="98%">
						<tr><td colspan="4" class="tblRowTitle tblclear"><label><spring:message code="Label.contributingstate" htmlEscape="true"></spring:message></label></td></tr>
						      <tr class="tblRowTitle tblclear">
							   <td width="15%" ><spring:message code="Label.STATECODE" htmlEscape="true"></spring:message></td>
								<td width="15%" ><spring:message code="Label.STATEVERSION" htmlEscape="true"></spring:message></td>
								<td width="35%""><spring:message code="Label.STATENAMEENGLISH" htmlEscape="true"></spring:message></td>
								<td width="35%" ><spring:message code="Label.STATENAMELOCAL" htmlEscape="true"></spring:message></td>
							  </tr>
							  <c:forEach var="listContriState" varStatus="fullRow" items="${contributeSts}">
													<tr class="tblRowB">
														<td width="15%"><c:out value="${listContriState.stateCode}" escapeXml="true"></c:out></td>
														<td width="15%"><c:out value="${listContriState.stateVersion}" escapeXml="true"></c:out></td>
														<td width="35%"><c:out value="${listContriState.stateNameEnglish}" escapeXml="true"></c:out></td>
														<td width="35%"><c:out value="${listContriState.stateNameLocal}" escapeXml="true"></c:out></td>
													
													</tr>
								  </c:forEach>
						</table>
						</div>
						  </div>
						
						<div class="frmpnlbg">
						<div class="frmtxt">
						
						<table class="tbl_with_brdr" width="98%">
						<tr><td colspan="4" class="tblRowTitle tblclear"><label><spring:message code="Label.CONTRIBUTINGDISTRICT" htmlEscape="true"></spring:message></label></td></tr>
						      <tr class="tblRowTitle tblclear">
							   <td width="15%" ><spring:message code="Label.DISTRICTCODE" htmlEscape="true"></spring:message></td>
								<td width="15%" ><spring:message code="Label.DISTRICTVERSION" htmlEscape="true"></spring:message></td>
								<td width="35%""><spring:message code="Label.DISTRICTNAMEENGLISH" htmlEscape="true"></spring:message></td>
								<td width="35%" ><spring:message code="Label.DISTRICTNAMELOCAL" htmlEscape="true"></spring:message></td>
							  </tr>
							  <c:forEach var="listContriDistrict" varStatus="fullRow" items="${contributedDs}">
													<tr class="tblRowB">
														<td width="15%"><c:out value="${listContriDistrict.districtCode}" escapeXml="true"></c:out></td>
														<td width="15%"><c:out value="${listContriDistrict.districtVersion}" escapeXml="true"></c:out></td>
														<td width="35%"><c:out value="${listContriDistrict.districtNameEnglish}" escapeXml="true"></c:out></td>
														<td width="35%"><c:out value="${listContriDistrict.districtNameLocal}" escapeXml="true"></c:out></td>
													
													</tr>
								  </c:forEach>
						</table>
						</div>
						  </div>
						
						<div class="frmpnlbg">
						<div class="frmtxt">
						
						<table class="tbl_with_brdr" width="98%">
						<tr><td colspan="4" class="tblRowTitle tblclear"><label><spring:message code="Label.CONTRIBUTINGSUBDISTRICT" htmlEscape="true"></spring:message>s</label></td></tr>
						      <tr class="tblRowTitle tblclear">
							   <td width="15%" ><spring:message code="Label.SUBDISTRICTCODE" htmlEscape="true"></spring:message></td>
								<td width="15%" ><spring:message code="Label.SUBDISTRICTVERSION" htmlEscape="true"></spring:message></td>
								<td width="35%""><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"></spring:message></td>
								<td width="35%" ><spring:message code="Label.SUBDISTRICTNAMELOCAL" htmlEscape="true"></spring:message></td>
							  </tr>
							  <c:forEach var="listContriSubDistrict" varStatus="fullRow" items="${contributedSDs}">
													<tr class="tblRowB">
														<td width="15%"><c:out value="${listContriSubDistrict.subdistrictCode}" escapeXml="true"></c:out></td>
														<td width="15%"><c:out value="${listContriSubDistrict.subdistrictVersion}" escapeXml="true"></c:out></td>
														<td width="35%"><c:out value="${listContriSubDistrict.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
														<td width="35%"><c:out value="${listContriSubDistrict.subdistrictNameLocal}" escapeXml="true"></c:out></td>
													
													</tr>
								  </c:forEach>
						</table>
						
						  </div>
					    </div>
					    <c:if   test="${fn:length(contributedVills)> 0}">
						<div class="frmpnlbg">
						<div class="frmtxt">
						
						<table class="tbl_with_brdr" width="98%">
						<tr><td colspan="4" class="tblRowTitle tblclear"><label><spring:message code="Label.CONTRIBUTINGVILLAGES" htmlEscape="true"></spring:message></label></td></tr>
						      <tr class="tblRowTitle tblclear">
							    <td width="15%" ><spring:message	code="Label.VILLAGECODE" htmlEscape="true"></spring:message></td>
								<td width="15%" ><spring:message	code="Label.VILLAGEVERSION" htmlEscape="true"></spring:message></td>
								<td width="35%" ><spring:message code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message></td>
								<td width="35%" ><spring:message code="Label.VILLAGENAMEINLOCAL" htmlEscape="true"></spring:message></td>
							  </tr>
							  <c:forEach var="vill" varStatus="fullRow" items="${contributedVills}">
													<tr class="tblRowB">
														<td width="15%"><c:out value="${vill.villageCode}" escapeXml="true"></c:out></td>
														<td width="15%"><c:out value="${vill.villageVersion}" escapeXml="true"></c:out></td>
														<td width="35%"><c:out value="${vill.villageNameEnglish}" escapeXml="true"></c:out></td>
														<td width="35%"><c:out value="${vill.villageNameLocal}" escapeXml="true"></c:out></td>
													</tr>
								  </c:forEach>
						</table>
						
						  </div>
					    </div>
					    </c:if>
				  </form:form>	
			  </div>
		</div>
</body>
</html>

