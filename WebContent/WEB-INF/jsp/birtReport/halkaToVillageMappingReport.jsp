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
function getData() {
	document.getElementById("errEmptyCaptcha").innerHTML = "";
	var stateobj = document.getElementById("ddSourceState");
	var disttobj = document.getElementById("ddSourceDistrict");
	var inSelectDist = disttobj.value;
	if(inSelectDist == null || inSelectDist == ""){
		document.getElementById("errSelectDistrict").innerHTML = "<font color='red'><br><b><spring:message code="error.select.DISTRICTFRMLIST"/></b></font>";  
		return false;
	}
	var capchaImgVal = document.getElementById('captchaAnswer').value;
	if(capchaImgVal == null || capchaImgVal == ""){
		document.getElementById("errEmptyCaptcha").style.display = 'block';
		document.getElementById("errEmptyCaptcha").innerHTML = "<font color='red'><br><b><spring:message code="Error.EmptyCaptcha"/></b></font>"; 
		document.getElementById('captchaAnswer').focus();
		return false;
	}
	
	document.getElementById('form1').statename.value = stateobj.options[stateobj.selectedIndex].text;
	document.getElementById('form1').disttname.value = disttobj.options[disttobj.selectedIndex].text;
	document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "halkaToVillageMappingReport.do?<csrf:token uri='halkaToVillageMappingReport.do'/>";
	document.getElementById('form1').submit();
	return true;
}
function noenter(e) {
    e = e || window.event;
    var key = e.keyCode || e.charCode;
    return key !== 13; 
}
function removeError(){
	document.getElementById("errSelectDistrict").innerHTML="";
	document.getElementById("errEmptyCaptcha").innerHTML="";
}
</script>
</head>
<body>
	<div id="frmcontent">
		<form:form action="halkaToVillageMappingReport.do" method="POST" commandName="downloadView" id="form1">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="halkaToVillageMappingReport.do"/>" />
			<input type="hidden" id="statename" name="statename" />
			<input type="hidden" id="disttname" name="disttname" />
			<div class="frmpnlbg">
				<div class="frmtxt">
					<div class="frmhdtitle">
						<spring:message code="Label.halkaToVillageMappingReport" text="Halka to Village Mapping Report (Jharkhand State)" htmlEscape="true"></spring:message>
					</div>
					<table width="100%" class="tbl_no_brdr">
						<tr>
							<td align="center">
								<table width="100%" class="tbl_no_brdr">
									 <tr>
										<td align="center"><label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message> </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<form:select path="stateNameEnglish" class="combofield" style="width: 175px" id="ddSourceState" disabled="true">
												<form:option value="">
													<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
												</form:option>
												<form:options items="${statelist}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
											</form:select><br/>
											<div id="errSelectState" class="errormsg"></div>
										</td>
									</tr>
									<tr>
										<td align="center"><label for="ddSourceDistrict"><spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>&nbsp;&nbsp;&nbsp;
											</label>
											<form:select path="districtNameEnglish" class="combofield" style="width: 175px" id="ddSourceDistrict" onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');"
												onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddSourceDistrict')" onchange="removeError();">
												<form:option value="">
													<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
												</form:option>
												<form:options items="${districtList}" itemValue="districtPK.districtCode" itemLabel="districtNameEnglish"></form:options>
											</form:select>
											<div id="errSelectDistrict" class="errormsg"></div>
										</td>					 
									</tr>
								</table>
								</td>
								</tr>
								</table>
							</div>         
			            </div>
			     <div class="frmpnlbg">
			    <div class="frmtxt">
			     <div class="frmhdtitle">
			     <spring:message code="Label.DDOPTION" htmlEscape="true"></spring:message>
				</div>
				<table width="100%" class="tbl_no_brdr">
						<tr>
							<td align="center">
								<table width="100%" class="tbl_no_brdr">
									<tr>
										<td align="center">&nbsp;
										<input type="radio" id="chkPdf" name="downloadOption" value="pdf" checked="checked" />&nbsp;<spring:message code="Label.PDF" htmlEscape="true"></spring:message> &nbsp; &nbsp;
										<input type="radio" id="chkXls" name="downloadOption" value="xls" />&nbsp; <spring:message code="Label.XLS" htmlEscape="true"></spring:message> &nbsp; &nbsp; 
										<input type="radio" id="chkHtm" name="downloadOption" value="htm" />&nbsp; <spring:message code="Label.HTM" htmlEscape="true"></spring:message> &nbsp; &nbsp;
										<input type="radio" id="chkOdt" name="downloadOption" value="odt" />&nbsp; <spring:message code="Label.ODT" htmlEscape="true" text="ODT"></spring:message>
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
								<div id="errormsg" style="color: red;">
									<b>${errormsg}</b>
								</div>
							</td>
						</tr>
					</table>

					<div class="btnpnl">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td>
								<label><input type="button" value="<spring:message code="Label.GENERATEREPORT" htmlEscape="true"></spring:message>" onclick="getData();" /></label>
								<label><input type="button" name="Submit2" class="btn" value='<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>' onclick="javascript:location.href='halkaToVillageMappingReport.do?<csrf:token uri='halkaToVillageMappingReport.do'/>';" /></label>
								<label><input type="button" name="Submit3" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" onclick="javascript:go('welcome.do');" /></label>
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