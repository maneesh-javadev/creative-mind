<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@include file="../common/taglib_includes.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>E-Panchayat</title>
	<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<form:form commandName="statePanchayatSetup">
		<table class="tbl_no_brdr">
			<tr>
				<td>
					<table width="100%" class="tbl_no_brdr">
						<tr>
							<td>
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td><label><spring:message htmlEscape="true"  code="Label.LGD">
												</spring:message> </label></td>
									</tr>
									<tr>
										<td><label><spring:message htmlEscape="true" 
													code="Label.STATERLBSETUPRPT">
												</spring:message> </label></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td width="14%" align="center">
								<table class="tbl_with_brdr" width="98%" align="center">
									<tr class="tblRowTitle tblclear">
										<td width="5%" rowspan="2"><spring:message htmlEscape="true" 
												code="Label.STATENAME" htmlEscape="true"></spring:message></td>
										<td width="16%" rowspan="2"><spring:message htmlEscape="true" 
												code="Label.NUMOFTYPES" htmlEscape="true"></spring:message></td>
										<td width="21%" rowspan="2"><spring:message htmlEscape="true" 
												code="Label.ULBTYPES" htmlEscape="true"></spring:message></td>

									</tr>
									<c:if test="${! empty panchaytSetUpList}">
										<c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow"
											items="${panchaytSetUpList}">
											<tr class="tblRowB">
												<td width="6%"><c:out value="${panchaytSetUpRow.index+1}" escapeXml="true"></c:out></td>
												<td width="21%" align="left"><c:out
														value="${panchaytSetUpRow.stateName}" escapeXml="true"></c:out></td>
												<td colspan="6%" align="left"><c:out
														value="${panchaytSetUpRow.numberofTypes}" escapeXml="true"></c:out></td>
												<td colspan="21%" align="left"><c:out
														value="${panchaytSetUpRow.districtPanchayatName}" escapeXml="true"></c:out>
												</td>
											</tr>
										</c:forEach>
									</c:if>
								</table>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</form:form>
</body>
</html>