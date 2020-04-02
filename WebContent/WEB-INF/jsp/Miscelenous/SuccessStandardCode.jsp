<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<head>
<title>Success Standard Code</title>
</head>
<body>
	<div class="frmhd">

		<h3 class="subtitle">
			<spring:message code="Label.UOTHERSTANDARDCODESCH" htmlEscape="true" text="Update Other Standard Codes/Local Names"></spring:message> 
		</h3>
		<!-- <ul class="listing">
			<li><a href="#" class="frmhelp">Help</a></li>
		</ul> -->
	</div>


			<br/><br/>
			<form:form action="" method="POST" commandName="standardCodeForm" id="form1">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="standardCodesPagination.htm"/>" />

				<c:if test="${!empty standardCodesDetailsupdate}">

					<table class="tbl_with_brdr" width="98%" align="center">
						<c:if test="${entityName eq 'state'}">
							<tr class="tblRowTitle tblclear">

								<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
								<td width="5%" rowspan="2"><spring:message code="Label.STATECODE" htmlEscape="true"></spring:message></td>
								<td width="16%" rowspan="2"><spring:message code="Label.STATENAMEINENGLISH" htmlEscape="true"></spring:message></td>
								<td width="16%" rowspan="2"><spring:message code="Label.STATENAMEINLOCAL" htmlEscape="true"></spring:message></td>
								<%-- <td width="16%" rowspan="2"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></td> --%>


							</tr>
						</c:if>
						<c:if test="${entityName eq'district' || entityName eq'districtDistUser'}">
							<tr class="tblRowTitle tblclear">
								<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
								<td width="5%" rowspan="2"><spring:message code="Label.DISTRICTCODE" htmlEscape="true"></spring:message></td>
								<td width="16%" rowspan="2"><spring:message code="Label.DISTRICTNAMEENG" htmlEscape="true"></spring:message></td>
								<td width="16%" rowspan="2"><spring:message code="Label.DISTRICTNAMELOCAL" htmlEscape="true"></spring:message></td>
								<%-- <td width="16%" rowspan="2"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></td> --%>
							</tr>
						</c:if>
						<c:if test="${entityName eq 'block' || entityName eq 'blockDistUser'}">
							<tr class="tblRowTitle tblclear">
								<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
								<td width="5%" rowspan="2"><spring:message code="Label.BLOCKCODE" htmlEscape="true"></spring:message></td>
								<td width="16%" rowspan="2"><spring:message code="Label.BLOCKNAMEINENGLISH" htmlEscape="true"></spring:message></td>
								<td width="16%" rowspan="2"><spring:message code="Label.BLOCKNAMEINLOCAL" htmlEscape="true"></spring:message></td>
							</tr>
						</c:if>
						<c:if test="${entityName eq 'subdistrict' || entityName eq 'subdistrictDistUser'}">
							<tr class="tblRowTitle tblclear">
								<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
								<td width="5%" rowspan="2"><spring:message code="Label.SUBDISTRICTCODE" htmlEscape="true"></spring:message></td>
								<td width="16%" rowspan="2"><spring:message code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"></spring:message></td>
								<td width="16%" rowspan="2"><spring:message code="Label.SUBDISTRICTNAMELOCAL" htmlEscape="true"></spring:message></td>
								<%-- <td width="16%" rowspan="2"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></td> --%>
							</tr>
						</c:if>
						<c:if test="${entityName eq 'village' || entityName eq 'villageDistUser'}">
							<tr class="tblRowTitle tblclear">
								<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
								<td width="5%" rowspan="2"><spring:message code="Label.VILLAGECODE" htmlEscape="true"></spring:message></td>
								<td width="16%" rowspan="2"><spring:message code="Label.VILLAGENAMEINENGLISH" htmlEscape="true"></spring:message></td>
								<td width="16%" rowspan="2"><spring:message code="Label.VILLAGENAMEINLOCAL" htmlEscape="true"></spring:message></td>
								<%-- <td width="16%" rowspan="2"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></td> --%>
							</tr>
						</c:if>


						<c:if test="${entityName eq 'locabody' }">
							<tr class="tblRowTitle tblclear">
								<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
								<td width="5%" rowspan="2"><spring:message code="Label.LOCALBODYCODE" htmlEscape="true"></spring:message></td>
								<td width="16%" rowspan="2"><spring:message code="Label.LOCALBODYNAMEINENG" htmlEscape="true"></spring:message></td>
								<td width="16%" rowspan="2"><spring:message code="Label.LOCALBODYNAMEINLOCAL" htmlEscape="true"></spring:message></td>
								<%-- <td width="16%" rowspan="2"><spring:message code="Label.CENSUSCODE2011" htmlEscape="true"></spring:message></td> --%>
							</tr>
						</c:if>
						<c:if test="${entityName eq 'adminLevel'}">
						<tr class="tblRowTitle tblclear">

							<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
							<td width="5%" rowspan="2"><spring:message code="Label.AdminUnitCode" htmlEscape="true"></spring:message></td>
							<td width="30%" rowspan="2"><spring:message code="Administrative Unit Level (In English)" htmlEscape="true"></spring:message></td>
							<td width="30%" rowspan="2"><spring:message code="Administrative Unit Level (In Local)" htmlEscape="true"></spring:message></td>


						</tr>
						<tr></tr>
						</c:if>
						<c:if test="${entityName eq 'adminEntity'}">
							<tr class="tblRowTitle tblclear">

								<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
								<td width="5%" rowspan="2"><spring:message code="Label.AdminEntityCode" htmlEscape="true"></spring:message></td>
								<td width="30%" rowspan="2"><spring:message code="Label.AdminUnitEntityEng" htmlEscape="true"></spring:message></td>
								<td width="30%" rowspan="2"><spring:message code="Label.AdminUnitEntityLoc" htmlEscape="true"></spring:message></td>


							</tr>
							<tr></tr>
						</c:if>
						<c:if test="${entityName eq 'dept'}">
						<tr class="tblRowTitle tblclear">

							<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
							<td width="5%" rowspan="2"><spring:message code="Label.DEPTCODE" htmlEscape="true"></spring:message></td>
							<td width="30%" rowspan="2"><spring:message code="Label.DEPTNAMEINENG" htmlEscape="true"></spring:message></td>
							<td width="30%" rowspan="2"><spring:message code="Label.DEPTNAMEINLOCAL" htmlEscape="true"></spring:message></td>


						</tr>
						<tr></tr>
					</c:if>
					<c:if test="${entityName eq 'org'}">
						<tr class="tblRowTitle tblclear">

							<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
							<td width="5%" rowspan="2"><spring:message code="Label.ORGCODE" htmlEscape="true"></spring:message></td>
							<td width="30%" rowspan="2"><spring:message code="Label.ORGNAMEINENG" htmlEscape="true"></spring:message></td>
							<td width="30%" rowspan="2"><spring:message code="Label.ORGNAMEINLOCAL" htmlEscape="true"></spring:message></td>


						</tr>
						<tr></tr>
					</c:if>
					<c:if test="${entityName eq 'orgunit'}">
						<tr class="tblRowTitle tblclear">

							<td width="5%" rowspan="2"><spring:message code="Label.SNO" htmlEscape="true"></spring:message></td>
							<td width="5%" rowspan="2"><spring:message code="Label.ORGUNITCODE" htmlEscape="true"></spring:message></td>
							<td width="30%" rowspan="2"><spring:message code="Label.ORGUNITNAMEINENG" htmlEscape="true"></spring:message></td>
							<td width="30%" rowspan="2"><spring:message code="Label.ORGUNITNAMEINLOCAL" htmlEscape="true"></spring:message></td>


						</tr>
						<tr></tr>
				  </c:if>
						<c:if test="${entityName ne 'adminLevel' and entityName ne 'adminEntity' and entityName ne 'org' and entityName ne 'dept' and entityName ne 'orgunit'}">
						<tr class="tblRowTitle tblclear">
							<c:if test="${entityName ne 'state'}">
								<td width="6%" align="center"><spring:message code="Label.STATESPECIFICCODES" htmlEscape="true"></spring:message></td>
							</c:if>
						</tr>
						</c:if>
						<c:forEach var="standardCodes" varStatus="listStandardRow" items="${standardCodesDetailsupdate}">
							<tr class="tblRowB">
								<td width="6%"><c:out value="${listStandardRow.index+1}" escapeXml="true"></c:out></td>
								<td align="left"><c:out value="${standardCodes.entityCode}" escapeXml="true"></c:out></td>
								<td align="left"><c:out value="${standardCodes.entityNameEnglish}" escapeXml="true"></c:out></td>
								<td align="left"><c:out value="${standardCodes.entityNameLocalch}" escapeXml="true"></c:out></td>
								<%-- <c:if test="${!(entityName eq 'block' or entityName eq blockDistUser or entityName eq 'adminLevel' or entityName eq 'adminEntity' or entityName eq 'org' or entityName eq 'dept'  or entityName eq 'orgunit')}">
									<td align="left"><c:out value="${standardCodes.census2011Codech}" escapeXml="true"></c:out></td>
								</c:if> --%>
								<c:if test="${entityName ne 'state' and entityName ne 'adminLevel' and entityName ne 'adminEntity' and entityName ne 'org' and entityName ne 'dept' and entityName ne 'orgunit'}">
									<td align="left"><c:out value="${standardCodes.ssCodech}" escapeXml="true"></c:out></td>
								</c:if>

							</tr>


						</c:forEach>


					</table>

				</c:if>

				<c:if test="${empty standardCodesDetailsupdate}">
					<table class="tbl_with_brdr" width="98%" align="center">
						<tr>
							<td align="center">No change Apply for Update Standard Code</td>
						</tr>
					</table>
				</c:if>


				<div class="btnpnl">
					<input type="button" name="Cancel" class="btn" value="<spring:message code="Button.CLOSE"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri="home.htm"/>'" />

				</div>


			</form:form>
		
</body>