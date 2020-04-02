<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<head>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" language="javascript">

	function submitForm() {
		document.getElementById('form3').action = "configureOrder.htm";
		document.getElementById('btnSave').disabled = true;
		document.forms["form3"].submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>
<body>
	<div class="clear"></div>
	<div class="frmhd">
		<table width="100%" class="tbl_no_brdr">
			<tr>
				<td><spring:message htmlEscape="true"  code="Label.CGD"></spring:message></td>
				<td>&nbsp;</td>
				<%-- //this link is not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message htmlEscape="true" 
							code="Label.HELP"></spring:message> --%>
				</a>
				</td>

			</tr>
		</table>
	</div>
	<div class="frmpnlbrdr">
		<form:form id="form3" name="formgovtorder" method="post"
			action="configureOrder.htm" commandName="configGovtOrderForm">
			<input type="hidden" name="<csrf:token-name/>"
				value="<csrf:token-value uri="configureOrder.htm"/>" />
			<div class="frmpnlbg">
				<div class="frmtxt" align="center">
					<div class="frmhdtitle">
						<spring:message htmlEscape="true"  code="Label.CGD"></spring:message>
					</div>
					<table width="70%" class="tbl_with_brdr">
						<thead>
							<tr class="tblRowTitleBold">
								<th width="30%"><spring:message htmlEscape="true"  code="Label.OPERATION"></spring:message>
								</th>
								<th width="2%"></th>
								<th width="30%" colspan="4"><spring:message htmlEscape="true" 
										code="Label.OPTIONS"></spring:message>
								</th>
							</tr>
						</thead>
						<c:forEach var="administratorUnit"
							varStatus="administratorUnitRow"
							items="${configGovtOrderForm.listAdminUnits}">
							<tr class="tblRowB" align="left">
								<spring:bind
									path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].operationBlockValue">
									<input type="hidden"
										name="<c:out value="${status.expression}"/>"
										value="<c:out value="${status.value}" escapeXml="true"></c:out>" />
									<td width="30%"><c:out
											value="${administratorUnit.operationBlockName}" escapeXml="true"></c:out>
									</td>
								</spring:bind>
								<spring:bind
									path="configGovtOrderForm.listAdminUnits[${administratorUnitRow.index}].isgovtorderuploadBlock">
									<td width="2%"><input type="radio"
										name="<c:out value="${status.expression}"/>" value="true"
										<c:if test="${status.value == true}">checked</c:if> /></td>
									<td width="30%"><spring:message htmlEscape="true"  code="Label.UGO"></spring:message>
									</td>
									<td width="2%"><input type="radio"
										name="<c:out value="${status.expression}"/>" value="false"
										<c:if test="${status.value == false}">checked</c:if> /></td>
									<td width="30%"><spring:message htmlEscape="true"  code="Label.GGO"></spring:message>
									</td>
								</spring:bind>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

			<div class="btnpnl">
				<label> <input type="button" id="btnSave" class="btn"
					onclick="submitForm()" name="Submit"
					value="<spring:message htmlEscape="true"  code="Button.SAVE"/>" /> </label> <label> <input
					type="button" class="btn" name="Submit6"
					value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
					onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
			</div>
		</form:form>
	</div>
</body>
</html>