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


<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>

<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
<script src="js/shiftvillage.js"></script>
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
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>

<script type="text/javascript" language="javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
	
	dwr.engine.setActiveReverseAjax(true);
</script>
</head>


<body oncontextmenu="return false"
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
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div>
						</td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div></td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center></td>
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
					<td rowspan="2"><div class="errorImg"></div></td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div></td>
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
					<h3 class="subtitle"><label><spring:message	code="Label.SHIFTVILLAGESUBDISTRICT" htmlEscape="true"></spring:message> </label></h3>
										 <ul id="showhelp" class="listing">
					 				        <li>
					 				         <a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a>           
					 				        </li>
					 				       <%--//these links are not working  <li>
					 				        	<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
					 				        </li>
					 				        
					 				        <li>
					 				        	<a href="#" class="frmhelp"><spring:message
								code="Button.HELP" htmlEscape="true"></spring:message> </a>
					 				        </li> --%>
					 				     
					 			        </ul>
		
		
		
	
		</div>

		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form commandName="shiftvillageSubDistrict"
				onsubmit="cursorwait();" action="draftShiftVillageSD.htm">
					<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftShiftVillageSD.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.SELSOURCE" htmlEscape="true"></spring:message>
							</div>
							
							<div>
							
									<ul class="blocklist">
											<li>
												<input type="hidden" name="districtCode" value="<c:out value='${districtCode}'/>"/>
												<input type="hidden" name="stateCode" value="<c:out value='${stateCode}'/>"/>
												<form:hidden htmlEscape="true"
												path="govtOrderConfig" value="${govtOrderConfig}"/> <form:hidden
												path="operationCode"
												value="${shiftvillageSubDistrict.operationCode}" htmlEscape="true"></form:hidden>
												<form:hidden path="operation" htmlEscape="true"
												value="${shiftvillageSubDistrict.operation}"></form:hidden> <form:hidden
												path="stateSName" htmlEscape="true"
												value="${shiftvillageSubDistrict.stateSName}"
												id="hdnStateSName"></form:hidden> <form:hidden
												path="districtDName" htmlEscape="true"
												value="${shiftvillageSubDistrict.districtDName}"
												id="hdnDistrictDName"></form:hidden> <form:hidden
												path="districtSName" htmlEscape="true"
												value="${shiftvillageSubDistrict.districtSName}"
												id="hdnDistrictSName"></form:hidden> <form:hidden
												path="subdistrictSName" htmlEscape="true"
												value="${shiftvillageSubDistrict.subdistrictSName}"
												id="hdnSubdistrictSName"></form:hidden> <form:hidden
												path="subdistrictDName" htmlEscape="true"
												value="${shiftvillageSubDistrict.subdistrictDName}"
												id="hdnSubdistrictDName"></form:hidden>
											</li>
											
											<c:if test="${fn:containsIgnoreCase(isCenterState,'C')}">
										<li ><label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message> </label><span
										class="errormsg">*</span><br />
									
									 <form:select
											path="stateNameEnglish" class="combofield"
											cssClass="combofield" id="ddSourceState"
											onchange="getDistrictList(this.value);"
											onfocus="validateOnFocus('ddSourceState');helpMessage(this,'ddSourceState_msg');"
											onblur="vlidateOnblur('ddSourceState','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceState')" htmlEscape="true">
											<form:option value="" htmlEscape="true">
												<spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message>
											</form:option>
											<form:options items="${stateSourceList}"
												itemValue="statePK.stateCode" itemLabel="stateNameEnglish" htmlEscape="true"></form:options>
										</form:select> 
										
										<div id="ddSourceState_error" class="error"><spring:message code="Error.STATE" htmlEscape="true"></spring:message></div>
										<div id="ddSourceState_msg" style="display:none"><spring:message code="Error.STATE" htmlEscape="true"/></div>
										<div class="errormsg" id="ddSourceState_error1"><form:errors path="stateNameEnglish" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddSourceState_error2" style="display: none" ></div>		
									</li> </c:if> 
											
											<c:if test="${districtCode == 0}">
									<li><label for="ddSourceDistrict"><spring:message htmlEscape="true"
												code="Label.SOURCEDISTRICT"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select
											path="districtNameEnglish" class="combofield"
											cssClass="combofield" id="ddSourceDistrict"
											onchange="getTargetDistrictandSubdistrictList(this.value);"
											onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');"
											onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceDistrict')" htmlEscape="true">
										<form:option value="" htmlEscape="true">
												<spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>
											</form:option>
											
												
											<c:forEach items="${districtSourceList}" var="distListvar">
												  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
												    <form:option value="${distListvar.districtCode}" disabled="true" htmlEscape="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>  
												  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
												     <form:option value="${distListvar.districtCode}" htmlEscape="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>
											</c:forEach>
											<%-- 
											<form:options items="${districtSourceList}"
												itemValue="districtPK.districtCode" itemLabel="districtNameEnglish"></form:options>	 --%>
										</form:select>
									
										<div id="ddSourceDistrict_error" class="error"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"></spring:message></div>
										<div id="ddSourceDistrict_msg" style="display:none"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/></div>
										<div class="errormsg" id="ddSourceDistrict_error1"><form:errors path="districtNameEnglish" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddSourceDistrict_error2" style="display: none" ></div>			
									</li></c:if>
									
									<li>
										<label for="ddSourceSubDistrict"><spring:message
												code="Label.SOURCESUBDISTRICT" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select
											path="subdistrictNameEnglish" class="combofield"
											cssClass="combofield" id="ddSourceSubDistrict"
											onchange="getVillageList(this.value)"
											onfocus="validateOnFocus('ddSourceSubDistrict');helpMessage(this,'ddSourceSubDistrict_msg');"
											onblur="vlidateOnblur('ddSourceSubDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceSubDistrict')" htmlEscape="true">
											<form:option value="" htmlEscape="true">
												<spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
											</form:option>
											
												<c:forEach items="${subdistrictSourceList}" var="SubdistListvar">
												  <c:if test="${SubdistListvar.operation_state == 'F'.charAt(0)}">
												    <form:option value="${SubdistListvar.subdistrictCode}" disabled="true"><c:out value="${SubdistListvar.subdistrictNameEnglish}" escapeXml="true"></c:out>'</form:option>
												  </c:if>  
												  <c:if test="${SubdistListvar.operation_state == 'A'.charAt(0)}">
												     <form:option value="${SubdistListvar.subdistrictCode}"><c:out value="${SubdistListvar.subdistrictNameEnglish}" escapeXml="true"></c:out>'</form:option>
												  </c:if>
											</c:forEach>
											
										</form:select>
								
										<div id="ddSourceSubDistrict_error" class="error"><spring:message code="Error.SOURCESUBDISTRICT" htmlEscape="true"></spring:message></div>
										<div id="ddSourceSubDistrict_msg" style="display:none"><spring:message code="Error.SOURCESUBDISTRICT" htmlEscape="true"/></div>
										<div class="errormsg" id="ddSourceSubDistrict_error1"><form:errors path="subdistrictNameEnglish" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddSourceSubDistrict_error2" style="display: none" ></div>					
												
									
									</li>
									</ul>
							
							</div>
							
							
							<div class="clear"></div>
						</div>
					</div>

					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.SELTARGET" htmlEscape="true"></spring:message>
							</div>
							
							
							<div>
									<ul class="blocklist">
									
											<c:if test="${fn:containsIgnoreCase(isCenterState,'C')}">
												<li><label for="ddTargetState"><spring:message htmlEscape="true"
													code="Label.SELECTSTATE"></spring:message> </label><span
												class="errormsg">*</span><br />
										
											    <form:select
												path="stateNameEnglish" class="combofield"
												cssClass="combofield" id="ddTargetState"
												onchange="getTargetDistrictList(this.value);"
												onfocus="validateOnFocus('ddSourceState');helpMessage(this,'ddTargetState_msg');"
												onblur="vlidateOnblur('ddSourceState','1','15','c');hideHelp();"
												onkeyup="hideMessageOnKeyPress('ddSourceState')" htmlEscape="true">
												
												</form:select> 
														
												<div id="ddTargetState_error" class="error"><spring:message code="Error.SOURCESTATE" htmlEscape="true"></spring:message></div>
												<div id="ddTargetState_msg" style="display:none"><spring:message code="Error.SOURCESTATE" htmlEscape="true"/></div>
												<div class="errormsg" id="ddTargetState_error1"><form:errors path="stateNameEnglish" htmlEscape="true"/></div>  
												<div class="errormsg" id="ddTargetState_error2" style="display: none" ></div>			
											
											</li>
									</c:if>
									
									<c:if test="${districtCode == 0}">
											<li><br /><label for="ddDestDistrict"><spring:message
													code="Label.TARGETDISTRICT" htmlEscape="true"></spring:message> </label><span
											class="errormsg">*</span><br /><form:select
												path="districtNameEnglish" class="combofield"
												cssClass="combofield" id="ddDestDistrict"
												onchange="getTargetSubDistrictList(this.value);"
												onfocus="validateOnFocus('ddDestDistrict');helpMessage(this,'ddDestDistrict_msg');"
												onblur="vlidateOnblur('ddDestDistrict','1','15','c');hideHelp();"
												onkeyup="hideMessageOnKeyPress('ddDestDistrict')" htmlEscape="true">
												<form:option value="" htmlEscape="true">
												<spring:message code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>
												</form:option>
												
													<c:forEach items="${districtTargetList}" var="distListvar">
												  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
												    <form:option value="${distListvar.districtCode}" disabled="true" htmlEscape="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>  
												  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
												     <form:option value="${distListvar.districtCode}" htmlEscape="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>
											</c:forEach>
												
												
											<%-- 	<form:options items="${districtTargetList}"
												itemValue="districtPK.districtCode" itemLabel="districtNameEnglish"></form:options> --%>
												</form:select>
											
											<div id="ddDestDistrict_error" class="error"><spring:message code="Error.TARGETDISTRICT" htmlEscape="true"></spring:message></div>
											<div id="ddDestDistrict_msg" style="display:none"><spring:message code="Error.TARGETDISTRICT" htmlEscape="true"/></div>
											<div class="errormsg" id="ddDestDistrict_error1"><form:errors path="districtNameEnglishDest" htmlEscape="true"/></div>  
											<div class="errormsg" id="ddDestDistrict_error2" style="display: none" ></div>		
											
													
											</li>
										</c:if>
									
										<li>
												<label for="ddTargetSubDistrict"><spring:message htmlEscape="true"
												code="Label.TARGETSUBDISTRICT"></spring:message> </label><span
											class="errormsg">*</span><br /> <form:select
											path="subdistrictNameEnglishTarget" class="combofield"
											cssClass="combofield" id="ddTargetSubDistrict"
											onfocus="validateOnFocus('ddTargetSubDistrict');helpMessage(this,'ddTargetSubDistrict_msg');"
											onblur="vlidateOnblur('ddTargetSubDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddTargetSubDistrict')" htmlEscape="true">
											<form:option value="" htmlEscape="true">
												<spring:message code="Label.SELECTSUBDISTRICT" htmlEscape="true"></spring:message>
											</form:option>
											<%-- <form:options items="${subdistrictTargetList}"
												itemValue="subdistrictPK.subdistrictCode" itemLabel="subdistrictNameEnglish"></form:options> --%>
											
										</form:select>
										
										<div id="ddTargetSubDistrict_error" class="error"><spring:message code="Error.TARGETSUBDISTRICT" htmlEscape="true"></spring:message></div>
										<div id="ddTargetSubDistrict_msg" style="display:none"><spring:message code="Error.TARGETSUBDISTRICT" htmlEscape="true"/></div>
										<div class="errormsg" id="ddTargetSubDistrict_error1"><form:errors path="subdistrictNameEnglishTarget" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddTargetSubDistrict_error2" style="display: none" ></div>		
											
										
										</li>
										
									
									
									</ul>
							
							</div>
							
							
							<div class="clear"></div>

						</div>
					</div>

					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.SHIFTVILLAGE" htmlEscape="true"></spring:message>
							</div>
							
							<ul class="blocklist">
								<li>
										<div class="ms_container">
											<div class="ms_selectable" >
													<label for="ddSourceVillage"><spring:message
															code="Label.AVAILVILLAGELIST" htmlEscape="true"></spring:message> </label><br /> <form:select
														id="ddSourceVillage" path="villageNameEnglish" size="1"
														multiple="multiple" class="frmtxtarea" htmlEscape="true">
													</form:select> <form:hidden path="villageName" id="hdnVillageName" htmlEscape="true"/>
											
											</div>
											<div class="ms_buttons">
											<input type="button"
													class="bttn" value=" &gt;&gt;" 
													onclick="listbox_moveacross('ddSourceVillage', 'ddTargetVillage','V')" />
											<input type="button"
													value=" &lt;&lt;" class="bttn"
													onclick="listbox_moveacross('ddTargetVillage', 'ddSourceVillage','V')" />
													
											</div>
											<div class="ms_selection">
											
											<label for="ddTargetVillage"><spring:message
															code="Label.CONTRIBUTVILLAGELIST" htmlEscape="true"></spring:message> </label><span
													class="errormsg">*</span><br /> <form:select
														path="villageNameEnglish" id="ddTargetVillage" size="1"
														multiple="multiple" class="frmtxtarea"
														onfocus="validateOnFocus('ddTargetVillage');helpMessage(this,'ddTargetVillage_msg');"
														onblur="vlidateOnblur('ddTargetVillage','1','15','p');hideHelp();"
														onkeyup="hideMessageOnKeyPress('ddTargetVillage')" htmlEscape="true">
													</form:select>
													
													<div id="ddTargetVillage_error" class="error"><spring:message code="Error.VILLAGE" htmlEscape="true"></spring:message></div>
													<div id="ddTargetSubDistrict_msg" style="display:none"><spring:message code="Error.VILLAGE" htmlEscape="true"/></div>
													<div class="errormsg" id="ddTargetVillage_error1"><form:errors path="villageNameEnglish" htmlEscape="true"/></div>  
													<div class="errormsg" id="ddTargetVillage_error2" style="display: none" ></div>	
											</div>
											
										</div>
										
								</li>
								</ul>
											
							
							
					<div class="clear"></div>
					
						</div>
					</div>
					<div class="btnpnl">
									<label> <input
										type="submit" name="Submit" id="btnSave" 
										onclick="return validateSDAll();getVillageName();"
										value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
										
								</label> <label> <input type="button" class="btn" name="Submit6"
										value="<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>"
										onclick="javascript:location.href='shiftvillageSubDistrict.htm?<csrf:token uri='shiftvillageSubDistrict.htm'/>';" /> </label> <label>
										<input type="button" name="Cancel" class="btn"
										value=<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>
										id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/> </label>
									</div> <br /> <br /> <br />
					</div>
				</div>

			</form:form>
				<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>