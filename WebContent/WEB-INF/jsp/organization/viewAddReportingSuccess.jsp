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
						             <td><spring:message htmlEscape="true"  code="Label.VIEWREPORTING"></spring:message></td>
						             <td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></td>
						            <%-- //this link is not working  <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></td> --%>
					     </tr>
				  </table>
			</div>
			   <div class="clear"></div>
			   <div class="frmpnlbrdr">
				   <form:form action="viewSubdistrict.htm" method="POST" commandName="newsubdistrictform">
				   <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewSubdistrict.htm"/>"/>
					<div class="frmpnlbg" >
						<div class="frmtxt">
						<table class="tbl_with_brdr" width="98%">
						<tr align="center">
						<td><h2>New Reporting structure of Organization has been created </h2></td>
						</tr>
						
						</table>
						
						<table class="tbl_with_brdr" width="98%" >
								<tr><td colspan="4" class="tblRowTitle tblclear"><label>
								<spring:message htmlEscape="true"  code="Label.VIEWORG"></spring:message></label></td></tr>
								<tr class="tblRowTitle tblclear">
							    <td width="15%" ><spring:message htmlEscape="true"  code="Label.ORGTYPECODE"></spring:message></td>
								<td width="35%" ><spring:message htmlEscape="true"  code="Label.ORGTYPE"></spring:message></td>
								<td width="15%""><spring:message htmlEscape="true"  code="Label.ORGCODE"></spring:message></td>
								<td width="35%" ><spring:message htmlEscape="true"  code="Label.ORGNAMEINENG"></spring:message></td>
								<td width="15%" ><spring:message htmlEscape="true"  code="Label.ORGLEVEL"></spring:message></td>
							   </tr>
							    <tr class="tblRowB">
							    <td width="15%" ><c:out value="${orgTypeCode}" escapeXml="true"></c:out></td>
								<td width="35%"><c:out value="${orgType}" escapeXml="true"></c:out></td>
								<td width="15%" ><c:out value="${orgCode}" escapeXml="true"></c:out></td>
								<td width="35%" ><c:out value="${orgName}" escapeXml="true"></c:out></td>
								<td width="15%" ><c:out value="${orgLevel}" escapeXml="true"></c:out></td>
							  </tr>
						</table>
						</div>
						</div>
						<div class="frmpnlbg">
						<div class="frmtxt">
						
						<table class="tbl_with_brdr" width="98%">
						<%-- <tr><td colspan="4" class="tblRowTitle tblclear"><label>
						<spring:message htmlEscape="true"  code="Label.VIEWNEWDESIGNATION"></spring:message></label></td></tr> --%>
						      <tr class="tblRowTitle tblclear">
								<td width="40%" ><spring:message htmlEscape="true"  code="Label.D"></spring:message></td>
								<td width="40%""><spring:message htmlEscape="true"  code="Label.REPORTTO"></spring:message></td>
							  </tr>
							  <c:forEach var="report" varStatus="fullRow" items="${NewReportingStructure}">
													<tr class="tblRowB">
														<td width="40%"><c:out value="${report.orgName}" escapeXml="true"></c:out></td>
														<td width="40%"><c:out value="${report.orgNameLocal}" escapeXml="true"></c:out></td>
													</tr>
								  </c:forEach>
						</table>
						
						  </div>
					    </div>
				  </form:form>	
			  </div>
		</div>
</body>
</html>

