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
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type="text/javascript" src="js/addVillage.js" charset="utf-8"></script>
<script src="js/shiftvillage.js"></script>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3><spring:message code="Label.VIEWLOCALBODY" htmlEscape="true"></spring:message></h3>
			<ul class="listing">
				<li>
					<a href="#" rel="toggle[cat]"
					data-openimage="images/minus.jpg"
					data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
					border="0" /> </a>
				</li>
				<%--//these links are not working <li>
					<a href="#" class="frmhelp"><spring:message
					code="Label.HELP" htmlEscape="true"></spring:message> </a>	
				</li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form method="POST" commandName="Preview">
			 
				<div class="frmpnlbg">
					<div class="frmtxt">
						<table width="98%">
							<tr align="center">
								<td colspan="4"><label><h2><spring:message code="Label. NewLBCreated" htmlEscape="true"></spring:message></h2>
										</label></td>
							</tr>
						</table>
						
						<table class="tbl_with_brdr" width="98%">
							
							<!-- <tr>
								<td colspan="4" class="tblRowTitle tblclear"><label>The new local body is created successfully
										</label></td>
							</tr> -->
							<tr class="tblRowTitle tblclear">
								<td width="15%"><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"></spring:message>
								</td>
								<td width="15%"><spring:message
										code="Label.LOCALBODYVERSION" htmlEscape="true"></spring:message></td>
								<td width="35%""><spring:message
										code="Label.LOCALBODYNAMEENGLISH" htmlEscape="true"></spring:message></td>
								<td width="35%"><spring:message
										code="Label.LOCALBODYNAMELOCAL" htmlEscape="true"></spring:message></td>
							</tr>
							<c:forEach var="newSD" varStatus="fullRow"
								items="${localbodylist}">
								<tr class="tblRowB">
									<td width="15%"><c:out value="${newSD.localBodyCode}" escapeXml="true"></c:out></td>
									<td width="15%"><c:out value="${newSD.localBodyVersion}" escapeXml="true"></c:out></td>
									<td width="35%"><c:out value="${newSD.localBodyNameEnglish}" escapeXml="true"></c:out></td>
									<td width="35%"><c:out value="${newSD.localBodyNameLocal}" escapeXml="true"></c:out></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>


				<c:if test="${fn:length(contridistrictList)> 0}">
					<div class="frmpnlbg">
						<div class="frmtxt">

							<table class="tbl_with_brdr" width="98%">
								<tr>
									<td colspan="4" class="tblRowTitle tblclear"><label><spring:message code="Label.CONTRIBUTINGDISTRICT" htmlEscape="true"></spring:message></label></td>
								</tr>
								<tr class="tblRowTitle tblclear">
									<td width="15%"><spring:message code="Label.DISTRICTCODE" htmlEscape="true"></spring:message>
									</td>
									<td width="35%"><spring:message
											code="Label.DISTRICTVERSION" htmlEscape="true"></spring:message></td>
									<td width="35%""><spring:message
											code="Label.DISTRICTNAMEENGLISH" htmlEscape="true"></spring:message></td>

								</tr>
								<c:forEach var="listContriDistrict" varStatus="fullRow"
									items="${contridistrictList}">
									<tr class="tblRowB">
										<td width="15%"><c:out value="${listContriDistrict.landRegionCode}" escapeXml="true"></c:out></td>
										<td width="35%"><c:out value="${listContriDistrict.landRegionVersion}" escapeXml="true"></c:out></td>
										<td width="35%"><c:out value="${listContriDistrict.landRegionNameEnglish}" escapeXml="true"></c:out></td>


									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</c:if>
				<c:if test="${fn:length(contrisubdistrictList)> 0}">
					<div class="frmpnlbg">
						<div class="frmtxt">

							<table class="tbl_with_brdr" width="98%">
								<tr>
									<td colspan="4" class="tblRowTitle tblclear"><label><spring:message code="Label.CONTRIBUTINGSUBDISTRICT" htmlEscape="true"></spring:message></label></td>
								</tr>
								<tr class="tblRowTitle tblclear">
									<td width="15%"><spring:message
											code="Label.SUBDISTRICTCODE" htmlEscape="true"></spring:message></td>
									<td width="35%"><spring:message
											code="Label.SUBDISTRICTVERSION" htmlEscape="true"></spring:message></td>
									<td width="35%""><spring:message
											code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"></spring:message></td>

								</tr>
								<c:forEach var="listContriSubDistrict" varStatus="fullRow"
									items="${contrisubdistrictList}">
									<tr class="tblRowB">
										<td width="15%"><c:out value="${listContriSubDistrict.landRegionCode}" escapeXml="true"></c:out></td>
										<td width="35%"><c:out value="${listContriSubDistrict.landRegionVersion}" escapeXml="true"></c:out></td>
										<td width="35%"><c:out value="${listContriSubDistrict.landRegionNameEnglish}" escapeXml="true"></c:out></td>


									</tr>
								</c:forEach>
							</table>

						</div>
					</div>
				</c:if>
				<c:if test="${fn:length(contriVillageList)> 0}">
					<div class="frmpnlbg">
						<div class="frmtxt">

							<table class="tbl_with_brdr" width="98%">
								<tr>
									<td colspan="4" class="tblRowTitle tblclear"><label><spring:message code="Label.CONTRIBUTINGVILLAGES" htmlEscape="true"></spring:message></label></td>
								</tr>
								<tr class="tblRowTitle tblclear">
									<td width="15%"><spring:message code="Label.VILLAGECODE" htmlEscape="true"></spring:message>
									</td>
									<td width="35%"><spring:message
											code="Label.VILLAGEVERSION" htmlEscape="true"></spring:message></td>
									<td width="35%"><spring:message
											code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message></td>

								</tr>
								<c:forEach var="vill" varStatus="fullRow"
									items="${contriVillageList}">
									<tr class="tblRowB">
										<td width="15%"><c:out value="${vill.landRegionCode}" escapeXml="true"></c:out></td>
										<td width="35%"><c:out value="${vill.landRegionVersion}" escapeXml="true"></c:out></td>
										<td width="35%"><c:out value="${vill.landRegionNameEnglish}" escapeXml="true"></c:out></td>

									</tr>
								</c:forEach>
							</table>

						</div>
					</div>
				</c:if>
				<div class="btnpnl">
					
							<label><input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE"  ></spring:message>" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" id="btnClose" /> </label>
						
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>

