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
						             <td><spring:message htmlEscape="true"  code="Label.VIEWDISTRICT"></spring:message></td>
						             <td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></td>
						            <%--//this link is not working  <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></td> --%>
					     </tr>
				  </table>
			</div>
			   <div class="clear"></div>
			   <div class="frmpnlbrdr">
				   <form:form action="viewSubdistrict.htm" method="POST" commandName="Preview">
					<div class="frmpnlbg" >
						<div class="frmtxt">
						<table class="tbl_with_brdr" width="98%" >
								<tr><td colspan="4" class="tblRowTitle tblclear"><label>New District Created</label></td></tr>
								<tr class="tblRowTitle tblclear">
							    <td width="15%" ><spring:message htmlEscape="true"  code="Label.DISTRICTCODE"></spring:message></td>
								<td width="15%" ><spring:message htmlEscape="true"  code="Label.DISTRICTVERSION"></spring:message></td>
								<td width="35%""><spring:message htmlEscape="true"  code="Label.DISTRICTNAMEENGLISH"></spring:message></td>
								<td width="35%" ><spring:message htmlEscape="true"  code="Label.DISTRICTNAMELOCAL"></spring:message></td>
							   </tr>
							   <c:forEach var="newSD" varStatus="fullRow" items="${newSD}">
							    <tr class="tblRowB">
							    <td width="15%" ><c:out value="${newSD.districtCode}" escapeXml="true"></c:out></td>
								<td width="15%"><c:out value="${newSD.districtVersion}" escapeXml="true"></c:out></td>
								<td width="35%" ><c:out value="${newSD.districtNameEnglish}" escapeXml="true"></c:out></td>
								<td width="35%" ><c:out value="${newSD.districtNameLocal}" escapeXml="true"></c:out></td>
							  </tr>
							  </c:forEach>
						</table>
						</div>
						</div>
						
						
						
						<div class="frmpnlbg">
						<div class="frmtxt">
						
						<table class="tbl_with_brdr" width="98%">
						<tr><td colspan="4" class="tblRowTitle tblclear"><label><spring:message htmlEscape="true" 	code="Label.CONTRIBUTINGDISTRICT"></spring:message></label></td></tr> 
						      <tr class="tblRowTitle tblclear">
							   <td width="15%" ><spring:message htmlEscape="true"  code="Label.DISTRICTCODE"></spring:message></td>
								<td width="15%" ><spring:message htmlEscape="true"  code="Label.DISTRICTVERSION"></spring:message></td>
								<td width="35%""><spring:message htmlEscape="true"  code="Label.DISTRICTNAMEENGLISH"></spring:message></td>
								<td width="35%" ><spring:message htmlEscape="true"  code="Label.DISTRICTNAMELOCAL"></spring:message></td>
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
						<tr><td colspan="4" class="tblRowTitle tblclear"><label><spring:message htmlEscape="true" 	code="Label.CONTRIBUTINGSUBDISTRICT"></spring:message></label></td></tr> 
						      <tr class="tblRowTitle tblclear">
							   <td width="15%" ><spring:message htmlEscape="true"  code="Label.SUBDISTRICTCODE"></spring:message></td>
								<td width="15%" ><spring:message htmlEscape="true"  code="Label.SUBDISTRICTVERSION"></spring:message></td>
								<td width="35%""><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></td>
								<td width="35%" ><spring:message htmlEscape="true"  code="Label.SUBDISTRICTNAMELOCAL"></spring:message></td>
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
						<tr><td colspan="4" class="tblRowTitle tblclear"><label><spring:message htmlEscape="true" 	code="Label.CONTRIBUTINGVILLAGES"></spring:message></label></td></tr> 
						      <tr class="tblRowTitle tblclear">
							    <td width="15%" ><spring:message htmlEscape="true" 	code="Label.VILLAGECODE"></spring:message></td>
								<td width="15%" ><spring:message htmlEscape="true" 	code="Label.VILLAGEVERSION"></spring:message></td>
								<td width="35%" ><spring:message htmlEscape="true"  code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
								<td width="35%" ><spring:message htmlEscape="true"  code="Label.VILLAGENAMEINLOCAL"></spring:message></td>
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

