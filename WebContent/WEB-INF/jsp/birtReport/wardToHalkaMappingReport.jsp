<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<%@include file="../common/taglib_includes.jsp"%>
<script type="text/javascript" src="js/common.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
<script type="text/javascript">
var id = "";
function getData() {
	id = document.getElementById('captchaAnswer').value;
	if (id == "") {
		alert("Please Enter Captcha Code");
		return false;
		}
	frmSubmit();
}
function frmSubmit(){
	document.getElementById("errormsg").innerHTML ="";
	document.forms['form1'].submit();
}
</script>
</head>
<body>
<div id="frmcontent">
<form:form action="wardToHalkaMappingReport.do" method="POST" commandName="downloadView" id="form1" >
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="wardToHalkaMappingReport.do"/>" />
		<div class="frmpnlbg">
			<div class="frmtxt">
				<div class="frmhdtitle">
					<spring:message code="Label.WardHalkaMappingReport" text="Ward to Halka Mapping Report (Jharkhand State)" htmlEscape="true"></spring:message>
				</div>
				 <table width="100%" class="tbl_no_brdr">
					<tr>
						<td align="center">
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td align="center">&nbsp;
									<input type="radio" id="chkPdf" name="downloadOption" value="pdf" checked="checked" />&nbsp; <spring:message code="Label.PDF" htmlEscape="true"></spring:message> &nbsp; &nbsp;
									<input type="radio" id="chkHtm" name="downloadOption" value="htm" />&nbsp; <spring:message code="Label.HTM" htmlEscape="true"></spring:message> &nbsp; &nbsp;
									<input type="radio" id="chkXls" name="downloadOption" value="xls" />&nbsp; <spring:message code="Label.XLS" htmlEscape="true"></spring:message> &nbsp; &nbsp;
									<input type="radio" id="chkCsv" name="downloadOption" value="csv" />&nbsp; <spring:message code="Label.CSV" htmlEscape="true"></spring:message> &nbsp; &nbsp;
									<%-- <input type="radio" id="chkXml" name="downloadOption" value="xml" />&nbsp; <spring:message code="Label.XML" htmlEscape="true"></spring:message>&nbsp; &nbsp; --%>
									<input type="radio" id="chkOdt" name="downloadOption" value="odt" />&nbsp; <spring:message code="Label.ODT" htmlEscape="true" text="ODT"></spring:message>&nbsp; &nbsp;
									</td>
								</tr>
								<tr>
									<td align="center"><%@ include file="../common/captcha_integration.jspf"%></td>
								</tr>
							</table> 
						</td>
					</tr>
					<tr>
						<td align="center">
							<div id="errormsg" style="color: red;"><b>${errormsg}</b></div>
						</td>
					</tr>
				</table>

				<div class="btnpnl">
					<table width="100%" class="tbl_no_brdr">
						<tr>
							<td>
								<label>
									<input type="button" value="<spring:message code="Label.GENERATEREPORT" htmlEscape="true"></spring:message>" onclick="getData();" />
								</label>
								<label>
									<input type="button" name="Submit2" class="btn" value="<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>" onclick="javascript:location.href='wardToHalkaMappingReport.do?<csrf:token uri='wardToHalkaMappingReport.do'/>';" />
								</label>
								<label>
									<input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:go('welcome.do');" />
								</label>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form:form>
</div>
</body>
</html>