<%@include file="../common/taglib_includes.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>

<html>
<head>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/cancel.js"></script>
	<script type="text/javascript" src="js/common.js"></script>

</head>
<body>
	<form:form action="rename_ContriSD.htm" method="POST"
		commandName="newsubdistrictform">
		<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="rename_ContriSD.htm"/>"/>
		<div class="frmpnlbg"><strong><spring:message htmlEscape="true"  code="Label.VIEWASSIGNEDBLOCK"></spring:message></strong>
			<div class="frmtxt">
				<table width="70%" class="tbl_no_brdr" align="center">
					<tr>
						<td width="14%" align="center">
							<table class="tbl_with_brdr" width="100%" align="center">
								<tr class="tblRowTitle tblclear">
									<td align="left"><spring:message htmlEscape="true"  code="Label.SNO"></spring:message>
									</td>
									<td align="left" ><spring:message htmlEscape="true"  code="Label.BLOCKCODE"></spring:message>
									</td>
									<td  align="left" ><spring:message htmlEscape="true"  code="Label.BLOCKNAMEINENGLISH"></spring:message>
									</td>
									<td align="left" ><spring:message htmlEscape="true"  code="Label.VILLAGECODE"></spring:message>
									</td>
									<td align="left" ><spring:message htmlEscape="true"  code="Label.VILLAGENAMEINENGLISH"></spring:message>
									</td>
								</tr>

								<c:forEach var="villList"
									varStatus="villListRow" items="${villList}">
									<tr class="tblRowB">
										<td align="left"><c:out value="${villListRow.index+1}" escapeXml="true"></c:out></td>
										<td align="left"><c:out
												value="${villList.villageVersion}" escapeXml="true"></c:out>
										</td>
										<td align="left"><c:out
												value="${villList.aliasEnglish}" escapeXml="true"></c:out>
										</td>
										<td align="left"><c:out
												value="${villList.villageCode}" escapeXml="true"></c:out>
										</td>
										<td align="left"><c:out
												value="${villList.villageNameEnglish}" escapeXml="true"></c:out>
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
			<!-- <input type="button" class="btn" name="Submit3" value="Back" onclick="javascript:go('assignBlockVillageDisturbedState.htm');"/> --> 
				<input type="button" name="Submit6"
				value="Close" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/>
		</div>
		</div>
		</div>
	</form:form>

</body>
</html>