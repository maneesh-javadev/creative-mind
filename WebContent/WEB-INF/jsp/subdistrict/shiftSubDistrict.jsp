<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<title>E-Panchayat</title>
<script src="js/shiftsubdistrict.js"></script>

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
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrSubDistrictService.js'></script>

<script type="text/javascript" language="javascript">
	dwr.engine.setActiveReverseAjax(true);
</script>
</head>


<body onload="subdistrictloadpageFinal();" oncontextmenu="return false"
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
		
			<h3 class="subtitle"><label><spring:message
								code="Label.SHIFTSUBDISTRICT" htmlEscape="true"></spring:message> </label></h3>
										 <ul id="showhelp" class="listing">
					 				       <!--//these links are not working   <li>
					 				         <a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a>           
					 				        </li> -->
					 				        <%--//these links are not working 
					 				        <li>
					 				        	<a href="#" class="frmhelp"><spring:message
								code="Button.HELP" htmlEscape="true"></spring:message> </a>
					 				        </li> --%>
					 				     
					 			        </ul>
		
			
		</div>

		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form commandName="shiftSubDistrict" method="POST"
				onsubmit="cursorwait();" action="draftShiftSubDistrict.htm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftShiftSubDistrict.htm"/>"/>
				<div id="cat">
					<form:hidden path="govtOrderConfig" value="${govtOrderConfig}" htmlEscape="true"/> <form:hidden
						path="operationCode"
						value="${shiftSubDistrict.operationCode}" htmlEscape="true"></form:hidden> <form:hidden
						path="stateSName" value="${shiftSubDistrict.stateSName}"
						id="hdnStateSName" htmlEscape="true"></form:hidden> <form:hidden
						path="stateDName" value="${shiftSubDistrict.stateDName}"
						id="hdnStateDName" htmlEscape="true"></form:hidden> <form:hidden
						path="districtSName"
						value="${shiftSubDistrict.districtSName}"
						id="hdnDistrictSName" htmlEscape="true"></form:hidden> <form:hidden
						path="districtDName"
						value="${shiftSubDistrict.districtDName}"
						id="hdnDistrictDName" htmlEscape="true"></form:hidden>
						<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>' />	
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.SELSOURCE" htmlEscape="true"></spring:message>
							</div>
						<div>
						
						<ul class="blocklist">
								<c:if test="${fn:containsIgnoreCase(isCenterState,'C')}">
								<li>
									<label for="ddSourceState"><spring:message
									code="Label.SOURCESTATE" htmlEscape="true"></spring:message> </label><span
									class="errormsg">*</span> <br /> <form:select
									path="stateNameEnglish" class="combofield"
									id="ddSourceState"
									onchange="getList(this.value);"
									onfocus="validateOnFocus('ddSourceState');helpMessage(this,'ddSourceState_msg');"
									onblur="vlidateOnblur('ddSourceState','1','15','c');hideHelp();"
									onkeyup="hideMessageOnKeyPress('ddSourceState')"  htmlEscape="true">

									<form:option value="0"  htmlEscape="true">
										<spring:message code="Label.SOURCESTATE" htmlEscape="true"></spring:message>
									</form:option>
									<form:options items="${stateSourceList}"
										itemValue="statePK.stateCode" itemLabel="stateNameEnglish"  htmlEscape="true"></form:options>
									</form:select> 
									<div id="ddSourceState_error" class="error"><spring:message code="Error.SOURCESTATE" htmlEscape="true"></spring:message></div>
									<div id="ddSourceState_msg" style="display:none"><spring:message code="Error.SOURCESTATE" htmlEscape="true"/></div>
									<div class="errormsg" id="ddSourceState_error1"><form:errors path="stateNameEnglish" htmlEscape="true"/></div>  
									<div class="errormsg" id="ddSourceState_error2" style="display: none" ></div>		
										
									</li>
								</c:if>
								
								<c:if test="${fn:containsIgnoreCase(isCenterState,'S')}">
								<li  style="display: none;" >
									<label for="ddSourceState"><spring:message code="Label.SOURCESTATE" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span> <br /> <form:select
										path="stateNameEnglish" class="combofield"
										id="ddSourceState"
										onchange="getList(this.value);"
										onfocus="validateOnFocus('ddSourceState');helpMessage(this,'ddSourceState_msg');"
										onblur="vlidateOnblur('ddSourceState','1','15','c');hideHelp();"
										onkeyup="hideMessageOnKeyPress('ddSourceState')"  htmlEscape="true">

										<form:option value="0"  htmlEscape="true">
											<spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message>
										</form:option>
										<form:options items="${stateSourceList}"
											itemValue="statePK.stateCode" itemLabel="stateNameEnglish"  htmlEscape="true"></form:options>
									</form:select> 
									
								
									<div id="ddSourceState_error" class="error"><spring:message code="Error.SOURCESTATE" htmlEscape="true"></spring:message></div>
									<div id="ddSourceState_msg" style="display:none"><spring:message code="Error.SOURCESTATE" htmlEscape="true"/></div>
									<div class="errormsg" id="ddSourceState_error1"><form:errors path="stateNameEnglish" htmlEscape="true"/></div>  
									<div class="errormsg" id="ddSourceState_error2" style="display: none" ></div>		
											
								</li>
								</c:if>
								
								<li>
										<label for="ddSourceDistrict"><spring:message htmlEscape="true"
												code="Label.SOURCEDISTRICT"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select
											path="districtNameEnglish" class="combofield"
											id="ddSourceDistrict"
											onchange="getSubDistrictList(this.value)"
											onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');"
											onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceDistrict')"  htmlEscape="true">
											<form:option value="0"  htmlEscape="true">
												<spring:message code="Label.SOURCEDISTRICT" htmlEscape="true"></spring:message>
											</form:option>
											
											<c:forEach items="${distrinctlist}" var="distListvar">
												  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
												    <form:option value="${distListvar.districtCode}" disabled="true" htmlEscape="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>  
												  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
												     <form:option value="${distListvar.districtCode}" htmlEscape="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>
											</c:forEach>
											
											<%-- <form:options items="${distrinctlist}"
												itemValue="districtPK.districtCode" itemLabel="districtNameEnglish"></form:options> --%>
											
										</form:select>
										
										
										
										<div id="ddSourceDistrict_error" class="error"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"></spring:message></div>
										<div id="ddSourceDistrict_msg" style="display:none"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/></div>
										<div class="errormsg" id="ddSourceDistrict_error1"><form:errors path="districtNameEnglish" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddSourceDistrict_error2" style="display: none" ></div>	
										
										
								</li>
								
							
						</ul>	
							
				</div>
						
						</div>

					</div>



					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.SELTARGET" htmlEscape="true"></spring:message>
							</div>
							
							<div>
								<ul class="blocklist">
										<li>
												<c:if test="${fn:containsIgnoreCase(isCenterState,'C')}"><li><label for="ddDestState"><spring:message htmlEscape="true"
												code="Label.TARGETSTATE"></spring:message> </label><span
										class="errormsg">*</span> <br /> <form:select
											path="stateNameEnglishTarget" class="combofield"
											id="ddDestState"
											onchange="getDestDistrictList(this.value)"
											onfocus="validateOnFocus('ddDestState');helpMessage(this,'ddDestState_msg');"
											onblur="vlidateOnblur('ddDestState','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddDestState')" htmlEscape="true">

										</form:select>
										
									
											
										<div id="ddDestState_error" class="error"><spring:message code="Error.TARGETSTATE" htmlEscape="true"></spring:message></div>
										<div id="ddDestState_msg" style="display:none"><spring:message code="Error.TARGETSTATE" htmlEscape="true"/></div>
										<div class="errormsg" id="ddDestState_error1"><form:errors path="stateNameEnglishTarget" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddDestState_error2" style="display: none" ></div>	
										
											
										</li></c:if>
										
										</li>
										<c:if test="${fn:containsIgnoreCase(isCenterState,'S')}">	<li style="display: none;"><label for="ddDestState"><spring:message htmlEscape="true"
												code="Label.TARGETSTATE"></spring:message> </label><span
										class="errormsg">*</span> <br /> 
										<form:select
											path="stateNameEnglishTarget" class="combofield"
											id="ddDestState"
											onchange="getDestDistrictList(this.value)"
											onfocus="validateOnFocus('ddDestState');helpMessage(this,'ddDestState_msg');"
											onblur="vlidateOnblur('ddDestState','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddDestState')" htmlEscape="true">

										</form:select> 
										
									
										
										<div id="ddDestState_error" class="error"><spring:message code="Error.TARGETSTATE" htmlEscape="true"></spring:message></div>
										<div id="ddDestState_msg" style="display:none"><spring:message code="Error.TARGETSTATE" htmlEscape="true"/></div>
										<div class="errormsg" id="ddDestState_error1"><form:errors path="stateNameEnglishTarget" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddDestState_error2" style="display: none" ></div>	
											
									</li></c:if>
									<li>
										<label for="ddDestDistrict"><spring:message code="Label.TARGETDISTRICT" htmlEscape="true"></spring:message>
									</label><span class="errormsg">*</span><br /> 
									 <form:select 
											path="districtNameEnglish" cssClass="combofield"
											id="ddDestDistrict" 
											onfocus="validateOnFocus('ddDestDistrict');helpMessage(this,'ddDestDistrict_msg');"
											onblur="vlidateOnblur('ddDestDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddDestDistrict')" htmlEscape="true">
									 </form:select>

										
										
										<div id="ddDestDistrict_error" class="error"><spring:message code="Error.TARGETDISTRICT" htmlEscape="true"></spring:message></div>
										<div id="ddDestDistrict_msg" style="display:none"><spring:message code="Error.TARGETDISTRICT" htmlEscape="true"/></div>
										<div class="errormsg" id="ddDestDistrict_error1"><form:errors path="districtNameEnglish" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddDestDistrict_error2" style="display: none" ></div>	
											
										
									</li>
												
													
								</ul>
							
							</div>
	
							
							<div class="clear"></div>
						</div>

					</div>

					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.SHIFTSUBDISTRICT" htmlEscape="true"></spring:message>

							</div>
							

							
							<ul class="blocklist">
								<li>
										<div class="ms_container">
											<div class="ms_selectable" >
														<label for="ddSourceSubDistrict"><spring:message
																		code="Label.AVAILSUBDISTRICTLIST" htmlEscape="true"></spring:message> </label> 
													<form:select id="ddSourceSubDistrict" 
																	items="${subdistrictSourceList}"
																	path="subdistrictNameEnglish" size="1"
																	multiple="multiple" class="frmtxtarea"
																	itemLabel="subdistrictNameEnglish"
																	itemValue="subdistrictCode" htmlEscape="true">

																</form:select> <form:hidden path="subDistrictName"
																	id="hdnSubdistrictName" htmlEscape="true"/>
											</div>
											<div class="ms_buttons">
													<input type="button"
																value=" &gt;&gt;" class="bttn" 
																onclick="listbox_moveacross('ddSourceSubDistrict', 'ddDestSubDistrict','T')" />
																
																
													<input type="button" class="bttn"
																value=" &lt;&lt;" 
																onclick="listbox_moveacross('ddDestSubDistrict', 'ddSourceSubDistrict','T')" />
											
											
											</div>
											<div class="ms_selection">
											
													<label for="ddDestSubDistrict"><spring:message
																		code="Label.CONTRIBUTSUBDISTRICTLIST" htmlEscape="true"></spring:message>
															</label><span class="errormsg">*</span> <br /> <form:select
																	path="subdistrictNameEnglish" id="ddDestSubDistrict"
																	items="${subdistrictDestList}" size="1"
																	multiple="multiple" class="frmtxtarea"
																	onfocus="validateOnFocus('ddDestSubDistrict');helpMessage(this,'ddDestSubDistrict_msg');"
																	onblur="vlidateOnblur('ddDestSubDistrict','1','15','p');hideHelp();"
																	onkeyup="hideMessageOnKeyPress('ddDestSubDistrict')" htmlEscape="true">

																</form:select>
																
																<div id="ddDestSubDistrict_error" class="error"><spring:message code="Error.SUBDISTRICT" htmlEscape="true"></spring:message></div>
																<div id="ddDestSubDistrict_msg" style="display:none"><spring:message code="Error.SUBDISTRICT" htmlEscape="true"/></div>
																<div class="errormsg" id="ddDestSubDistrict_error1"><form:errors path="subdistrictNameEnglish" htmlEscape="true"/></div>  
																<div class="errormsg" id="ddDestSubDistrict_error2" style="display: none" ></div>	
																
																	
											
												<div class="clear"></div>
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
										onclick="getSubdistrictNameEnglish();return validateAll();"
										value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
								</label> <label> <input type="button" class="btn" name="Submit6"
										value="<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>"
										 onclick="javascript:location.href='shiftSubDistrict.htm?<csrf:token uri='shiftSubDistrict.htm'/>';" /> </label> <label>
										<input type="button" name="Cancel" class="btn"
										value=<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>
										id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label> <br />
					
						
					</div>
					
					
				</div>
			</form:form>
				<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>

</body>
</html>