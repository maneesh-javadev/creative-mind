<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />



<title>E-Panchayat</title>
<script src="js/shiftdistrict.js"></script>

<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />


<script src="js/common.js"></script>
<link href="css/errorLB.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>


<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>
</head>

<body onload="districtloadPage();" oncontextmenu="return false"
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">

	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center>
						</td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div>
						</td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div>
						</td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center>
						</td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
						</td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>

	<div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div>
					</td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div>
					</td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
					</td>
				</tr>
			</table>

		</div>
	</div>

	<div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div>

	<div id="frmcontent">

		<div class="frmhd">
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message code="Label.SHIFTDISTRICT" htmlEscape="true"></spring:message>
					</td>
					<%--//these links are not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message
								code="Button.HELP" htmlEscape="true"></spring:message> </a>
					</td> --%>
				</tr>
			</table>
		</div>

		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form id="shiftdistrict" commandName="shiftdistrict"
				action="draftShiftDistrict.htm" onsubmit="cursorwait();"
				method="POST" enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftShiftDistrict.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">

							<table width="100%" class="tbl_no_brdr">
								<tr>
									<input type="hidden" name="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>"/>
									<td width="14%" rowspan="8">
									<form:hidden htmlEscape="true"
											path="govtOrderConfig" value="${govtOrderConfig}"/> 
									<form:hidden
											path="operationCode" value="${shiftdistrict.operationCode}"></form:hidden>
									<form:hidden path="stateSName" htmlEscape="true"
											value="${shiftdistrict.stateSName}" id="hdnStateSName"></form:hidden>
									<form:hidden path="stateDName" htmlEscape="true"
											value="${shiftdistrict.stateDName}" id="hdnStateDName"></form:hidden>											
												
									</td>
									<%-- <c:if test="${isCenterState=='C'}"> --%>
									<td width="86%"><label><spring:message
												code="Label.SOURCESTATE" htmlEscape="true"></spring:message> </label> <span
										class="mndt">*</span> <br /> <form:select
											path="stateNameEnglish" class="combofield"
											style="width: 230px" id="ddSourceState"
											onchange="getList(this.value);"
											onfocus="validateOnFocus('ddSourceState');helpMessage(this,'ddSourceState_msg');"
											onblur="vlidateOnblur('ddSourceState','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceState')">
											<form:option value="0">
												<spring:message code="Label.SOURCESTATE" htmlEscape="true"></spring:message>
											</form:option>
											<%-- <form:options items="${stateSourceList}" itemLabel="stateNameEnglish" itemValue="stateCode"/> --%>
											<form:options items="${stateSourceList}"
											
												itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
										</form:select> 
										
										<%-- <span><form:errors path="stateNameEnglishSource"
												class="errormsg" htmlEscape="true"></form:errors> </span> <span
										id="ddSourceState_msg" style="display: none"><spring:message
												code="Msg.STATE" htmlEscape="true"></spring:message> </span> <span class="errormsg"
										id="ddSourceState_error"><spring:message
												code="Error.SOURCESTATE" htmlEscape="true"></spring:message> </span> --%>
												
										<div id="ddSourceState_error" class="error"><spring:message code="Error.SOURCESTATE" htmlEscape="true"></spring:message></div>
										<div id="ddSourceState_msg" style="display:none"><spring:message code="Error.SOURCESTATE" htmlEscape="true"/></div>
										<div class="errormsg" id="ddSourceState_error1"><form:errors path="stateNameEnglish" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddSourceState_error2" style="display: none" ></div>				
												
									</td>
									<%-- </c:if> --%>


								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<%-- <c:if test="${isCenterState=='C'}"> --%>
									<td><label><spring:message
												code="Label.TARGETSTATE" htmlEscape="true"></spring:message> </label><span
										class="mndt">*</span> <br /> <form:select
											path="stateNameEnglish" class="combofield"
											style="width: 230px" id="ddDestState"
											onfocus="validateOnFocus('ddDestState');helpMessage(this,'ddDestState_msg');"
											onblur="vlidateOnblur('ddDestState','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddDestState')">

										</form:select> 
										
										<%-- <span> <form:errors path="stateNameEnglishDest" htmlEscape="true"
												class="errormsg"></form:errors> </span><span id="ddDestState_msg"
										style="display: none"><spring:message code="Msg.STATE" htmlEscape="true"></spring:message>
										</span> <span class="errormsg" id="ddDestState_error"> <spring:message
												code="Error.TARGETSTATE" htmlEscape="true"></spring:message> </span> --%>
										<div id="ddDestState_error" class="error"><spring:message code="Error.TARGETSTATE" htmlEscape="true"></spring:message></div>
										<div id="ddDestState_msg" style="display:none"><spring:message code="Error.TARGETSTATE" htmlEscape="true"/></div>
										<div class="errormsg" id="ddDestState_error1"><form:errors path="stateNameEnglish" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddDestState_error2" style="display: none" ></div>	
												
									</td>
									<%-- </c:if> --%>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><table width="570" class="tbl_no_brdr">


											<tr>
												<td width="235" rowspan="3"><label><spring:message
															code="Label.AVAILABLEDISTRICT" htmlEscape="true"></spring:message> </label><br />
													<form:select id="ddSourceDistrict" path="districtNameEnglish"
														size="1" multiple="multiple" items="${districtSourceList}"
														class="frmtxtarea" style="height: 120px; width: 230px"
														itemLabel="districtNameEnglish" itemValue="districtCode">
													</form:select>
												
												<form:hidden path="districtName" id="hdnDistrictName" htmlEscape="true" />
												</td>
												<td width="100" align="center"><input type="button"
													class="btn" value=" &gt;&gt;" style="width: 40px"
													onclick="listbox_moveacross('ddSourceDistrict', 'ddDestDistrict','D')" />

												</td>
												<td width="235" rowspan="3"><label><spring:message
															code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message> </label><span
													class="errormsg">*</span> <br /> <form:select
														path="districtNameEnglish" id="ddDestDistrict" size="1"
														multiple="multiple" items="${districtDestList}"
														class="frmtxtarea" style="height:120px; width: 233px"
														onfocus="validateOnFocus('ddDestDistrict');helpMessage(this,'ddDestDistrict_msg');"
														onblur="vlidateOnblur('ddDestDistrict','1','15','p');hideHelp();"
														onkeyup="hideMessageOnKeyPress('ddDestDistrict')" >
													</form:select> <br /> 
													
													<%-- <span id="ddDestDistrict_msg"
													style="display: none;"> <spring:message
															code="Msg.DISTRICT" htmlEscape="true"></spring:message> </span> <span
													class="errormsg" id="ddDestDistrict_error"> <spring:message
															code="Error.DISTRICT" htmlEscape="true"></spring:message> </span><span><form:errors
															path="districtNameEnglish" class="errormsg"></form:errors>
													</span> --%>
													<div id="ddDestDistrict_error" class="error"><spring:message code="Error.DISTRICT" htmlEscape="true"></spring:message></div>
													<div id="ddDestState_msg" style="display:none"><spring:message code="Msg.DISTRICT" htmlEscape="true"/></div>
													<div class="errormsg" id="ddDestDistrict_error1"><form:errors path="districtNameEnglish" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddDestDistrict_error2" style="display: none" ></div>	
													
												</td>
											</tr>
											<tr>
												<td align="center"><input type="button" class="btn"
													value=" &lt;&lt;" style="width: 40px"
													onclick="listbox_moveacross('ddDestDistrict', 'ddSourceDistrict','D')" />
												</td>

											</tr>
										</table>
									</td>
								</tr>
							
							</table>
						</div>

					</div>
					<div class="btnpnl">
						<table width="100%" class="tbl_no_brdr">
							<tr>
								<td width="16%" rowspan="2">&nbsp;</td>
								<td width="84%" align="left"><label> <input
										type="submit" name="Save" id="btnSave" style="width: 100px"
										onclick="getDistrictNameEnglish();return validateAll();"
										value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
								</label> <label> <input type="button" class="btn" name="Submit6"
										value="<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>"
										 onclick="javascript:location.href='shiftdistrict.htm?<csrf:token uri='shiftdistrict.htm'/>';" /> </label> <label>
										<input type="button" class="btn" name="Cancel"
										value=<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>
										id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"  /> </label>
					</div>
					<br /> <br /> <br />
					</td>

					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					</table>
				</div>
		</div>
		</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
	</div>
	</div>

</body>
</html>