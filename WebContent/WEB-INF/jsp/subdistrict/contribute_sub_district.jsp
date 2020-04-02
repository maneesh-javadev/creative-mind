<%@include file="../common/taglib_includes.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<script type="text/javascript" src="js/cancel.js"></script>
<html>
<head>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />

<title>Contribute SubDistrict</title>
</head>
<body>
	<form:form action="rename_ContriSD.htm" method="POST"
		commandName="newsubdistrictform">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="rename_ContriSD.htm"/>"/>
		<div class="frmpnlbg"><strong><spring:message code="Label.CONTRIBUTINGSUBDISTRICT" htmlEscape="true"></spring:message></strong>
			<div class="frmtxt">
				<table width="70%" class="tbl_no_brdr" align="center">
					<tr>
						<td width="14%" align="center">
							<table class="tbl_with_brdr" width="100%" align="center">
								<tr class="tblRowTitle tblclear">
									<td rowspan="2"><spring:message htmlEscape="true"  code="App.SNO"></spring:message>
									</td>
									<td colspan="2" align="center" ><spring:message
											code="Label.SUBDISTRICTNAMEENGLISH" htmlEscape="true"></spring:message>
									</td>
									<td colspan="2" align="center" ><spring:message
											code="Label.SUBDISTRICTNAMELOCAL" htmlEscape="true"></spring:message>
									</td>
								</tr>
								<tr class="tblRowTitle tblclear">
									<td width="6%" align="center" style="color:gray;">Old</td>
									<td width="6%" align="center" style="color:gray;">New</td>
									<td width="6%" align="center" style="color:gray;">Old</td>
									<td width="6%" align="center" style="color:gray;">New</td>
								</tr>

								<c:forEach var="listSubDistrictDetail"
									varStatus="listSubDistirctRow" items="${listSD}">
									<tr class="tblRowB">
										<td width="2%"><c:out value="${listSubDistirctRow.index+1}" escapeXml="true"></c:out><input type="hidden" name="aliasEnglish"
												value="<c:out value='${listSubDistrictDetail.subdistrictCode}'/>"/></td>
										<td align="left"><c:out
												value="${listSubDistrictDetail.subdistrictNameEnglish}" escapeXml="true"></c:out>
										</td>
										<td><form:input path="subdistrictNameEnglish" htmlEscape="true"
												class="frmfield" value="" />
										</td>
										<td align="left"><c:out
												value="${listSubDistrictDetail.subdistrictNameLocal}" escapeXml="true"></c:out>
										</td>
										<td width="6%"><form:input path="subdistrictNameLocal" htmlEscape="true"
												class="frmfield" value=""/>
										</td>
									</tr>
								</c:forEach>

							</table></td>
					</tr>
					<tr>
						<td>&nbsp;</td>

					</tr>
				</table>
			</div>
			<input type="submit" class="btn" name="Submit3" value="Save" /> <span
				class="errormsg"> <input type="button" name="Submit6"
				value="Cancel" onclick="javascript:go('new_subdistrict.htm');"/> </span>
		</div>
		
		
	</form:form>

</body>
</html>