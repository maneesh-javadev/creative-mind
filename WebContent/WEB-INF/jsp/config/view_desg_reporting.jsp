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
		 	<h3 class="subtitle"><spring:message htmlEscape="true"  code="Label.VIEWREPORTING"></spring:message></h3>
		 	<ul class="listing">
		 		<li>
		 			<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a>
		 		</li>
		 		<%-- //these links are not working<li>
		 			<a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a>
		 		</li> --%>
		 	</ul>
		</div>
			   <div class="clear"></div>
			   <div class="frmpnlbrdr">
				   <form:form action="viewSubdistrict.htm" method="POST" commandName="newsubdistrictform">
						<div class="frmpnlbg">
							<div class="frmtxt">
								<table width="70%" >
									<tr>
										<th><font size="2">${successMsg}</font></th>
									</tr>
								</table>
								<table class="tbl_with_brdr" width="70%">
									<tr class="tblRowTitleBold">
										<td width="20%">Local Government Type</td>
										<td width="20%">Designation</td>
										<td width="20%">ReportTo</td>
									</tr>
								 <c:forEach var="NewDesig" varStatus="fullRow" items="${designationTier}">
									<tr class="tblRowB">
										<td width="20%"><c:out value="${NewDesig.nomenclatureEnglish}" escapeXml="true"></c:out></td>
										<td width="20%"><c:out value="${NewDesig.designation}" escapeXml="true"></c:out></td>
										<td width="20%"><c:out value="${NewDesig.reportTo}" escapeXml="true"></c:out></td>
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

