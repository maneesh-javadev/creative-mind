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
						             <td><spring:message htmlEscape="true"  code="Label.VIEWDESIGNATION"></spring:message></td>
						             <td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></td>
<%-- 						     //this link is not working        <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></td>
 --%>					     </tr>
				  </table>
			</div>
			   <div class="clear"></div>
			   <div class="frmpnlbrdr">
				   <form:form action="viewSubdistrict.htm" method="POST" commandName="newsubdistrictform">
					
						<div class="frmpnlbg">
						<div class="frmtxt">
						<table >
						<tr>
						<th width="50%">&nbsp;</th>
						<th><font size="2"><c:out value="${successMsg}" escapeXml="true"></c:out></font></th>
						</tr>
						</table>
						<table class="tbl_with_brdr" width="80%">
						      <tr class="tblRowTitle tblclear">
<%-- 							   <td width="15%" ><spring:message htmlEscape="true"  code="Label.DESIGCODE"></spring:message></td> --%>
								<td width="10%" ><spring:message htmlEscape="true"  code="Label.DESIGNATIONNAMEENGLISH"></spring:message></td>
								<td width="10%" ><spring:message htmlEscape="true"  code="Label.DESIGNATIONNAMELOCAL"></spring:message></td>
<%-- 								<td width="15%""><spring:message htmlEscape="true"  code="Label.ISLB"></spring:message></td> --%>
								<td width="5%" ><spring:message htmlEscape="true"  code="Label.ISTOPDESIG"></spring:message></td>
								<td width="5%" ><spring:message htmlEscape="true"  code="Label.MULTIDESIG"></spring:message></td>
							  </tr>
							  <c:forEach var="NewDesig" varStatus="fullRow" items="${NewDesig}">
													<tr class="tblRowB">
<%-- 														<td width="15%">${NewDesig.designationCode}</td> --%>
														<td width="10%"><c:out value="${NewDesig.designationName}" escapeXml="true"></c:out></td>
														<td width="10%"><c:out value="${NewDesig.designationNameLocal}" escapeXml="true"></c:out></td>
<%-- 														<td width="15%">${NewDesig.islocalbody}</td> --%>
														
														<td width="5%">
															<c:if test="${NewDesig.istopdesignation=='true'}">Yes</c:if>
															<c:if test="${NewDesig.istopdesignation=='false'}">No</c:if>
														</td>
														<td width="5%">
															<c:if test="${NewDesig.ismultiple=='true'}">Yes</c:if>
															<c:if test="${NewDesig.ismultiple=='false'}">No</c:if>
														</td>
													</tr>
								  </c:forEach>
						</table>
						
						  </div>
					    </div>
				  </form:form>	
				   <script src="/LGD/JavaScriptServlet"></script>
			  </div>
		</div>
</body>
</html>

