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
						             <td><spring:message htmlEscape="true" code="Label.VIEWVILLAGE"></spring:message></td>
						             <td align="right"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a></td>
						             <%--//these links are not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></td> --%>
					     </tr>
				  </table>
			</div>
			   <div class="clear"></div>
			   <div class="frmpnlbrdr">
				   <form:form action="viewvillage.htm" id="form1" method="POST" commandName="villagebean">
				   <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewvillage.htm"/>"/>
					<div class="frmpnlbg" >
						<div class="frmtxt">
						<table class="tbl_with_brdr" width="98%" >
								<tr><td colspan="4" class="tblRowTitle tblclear"><label>New Village Created</label></td></tr>
								<tr class="tblRowTitle tblclear">
							    <td width="15%" ><spring:message htmlEscape="true"	code="Label.VILLAGECODE"></spring:message></td>
								<td width="15%" ><spring:message htmlEscape="true"	code="Label.VILLAGEVERSION"></spring:message></td>
								<td width="35%""><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
								<td width="35%" ><spring:message htmlEscape="true" code="Label.VILLAGENAMEINLOCAL"></spring:message></td>
							   </tr>
							    <tr class="tblRowB">
							    <td width="15%" ><c:out value="${newVillageBean.villagePK.villageCode}" escapeXml="true"></c:out></td>
								<td width="15%"><c:out value="${newVillageBean.villagePK.villageVersion}" escapeXml="true"></c:out></td>
								<td width="35%" ><c:out value="${newVillageBean.villageNameEnglish}" escapeXml="true"></c:out></td>
								<td width="35%" ><c:out value="${newVillageBean.villageNameLocal}" escapeXml="true"></c:out></td>
							  </tr>
						</table>
						</div>
						</div>
						<c:if   test="${fn:length(fullContributedList)> 0 || fn:length(partContributedList)> 0 }">
						<div class="frmpnlbg">
						<div class="frmtxt">
						
						<table class="tbl_with_brdr" width="98%">
						<tr><td colspan="4" class="tblRowTitle tblclear"><label><spring:message code="Label.CONTRIBUTINGVILLAGES" htmlEscape="true"></spring:message></label></td></tr>
						      <tr class="tblRowTitle tblclear">
							    <td width="15%" ><spring:message htmlEscape="true"	code="Label.VILLAGECODE"></spring:message></td>
								<td width="15%" ><spring:message htmlEscape="true"	code="Label.VILLAGEVERSION"></spring:message></td>
								<td width="35%" ><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
								<td width="35%" ><spring:message htmlEscape="true" code="Label.VILLAGENAMEINLOCAL"></spring:message></td>
							  </tr>
							  <c:forEach var="full" varStatus="fullRow" items="${fullContributedList}">
													<tr class="tblRowB">
														<td width="15%"><c:out value="${full.villageCode}" escapeXml="true"></c:out></td>
														<td width="15%"><c:out value="${full.villageVersion}" escapeXml="true"></c:out></td>
														<td width="35%"><c:out value="${full.villageNameEnglish}" escapeXml="true"></c:out>(FULLY CONTRIBUTED)</td>
														<td width="35%"><c:out value="${full.villageNameLocal}" escapeXml="true"></c:out></td>
													
													</tr>
								  </c:forEach>
							  <c:forEach var="part" varStatus="partRow" items="${partContributedList}">
													<tr class="tblRowB">
													<td width="15%"><c:out value="${part.villageCode}" escapeXml="true"></c:out></td>
														<td width="15%"><c:out value="${part.villageVersion}" escapeXml="true"></c:out></td>
														<td width="35%"><c:out value="${part.villageNameEnglish}" escapeXml="true"></c:out>(Partially CONTRIBUTED)</td>
														<td width="35%"><c:out value="${part.villageNameLocal}" escapeXml="true"></c:out></td>
														
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

