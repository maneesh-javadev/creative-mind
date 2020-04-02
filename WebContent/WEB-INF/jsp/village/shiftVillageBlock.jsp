<%@include file="../common/taglib_includes.jsp"%>

<html>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html" charset=ISO-8859-1"/>
<title>E-Panchayat</title>


<script src="js/shiftvillage.js"></script>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
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
	src='<%=contextPath%>/dwr/interface/lgdDwrVillageService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>


<script type="text/javascript">
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
						<td rowspan="2">
								<div class="success"></div>
							
						</td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div>
						</td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<c:out value="${param.family_msg}" escapeXml="true"></c:out>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div>
						</td>

						<td>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							
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
		
				<h3 class="subtitle"><label><spring:messages code="Label.SHIFTVILLAGEBLOCK" htmlEscape="true"></spring:message> </label></h3>
										 <ul id="showhelp" class="listing">
					 				        <li>
					 				         <a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a>           
					 				        </li>
					 				      <%--//these links are not working    <li>
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
			<form:form commandName="shiftvillageblock" onsubmit="cursorwait();"
				action="draftShiftVillageBlock.htm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftShiftVillageBlock.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.SELSOURCE" htmlEscape="true"></spring:message>
							</div>
							<div>
								<ul class="blocklist">
										<li>	<input type="hidden" id="districtCode" name="districtCode" value="<c:out value='${districtCode}'/>"/>
										<form:hidden htmlEscape="true" 
											path="govtOrderConfig" value="${govtOrderConfig}"/> <form:hidden
											path="operationCode"
											value="${shiftvillageblock.operationCode}"></form:hidden> 
											<form:hidden path="operation" htmlEscape="true" 
											value="${shiftvillageblock.operation}"></form:hidden> 
											<form:hidden
											path="districtSName" htmlEscape="true" 
											value="${shiftvillageblock.districtSName}" id="hdnDistrictSName"></form:hidden> <form:hidden
											path="blockSName" value="${shiftvillageblock.blockSName}" id="hdnBlockSName"></form:hidden>
										<form:hidden path="blockDName" id="hdnBlockDName" htmlEscape="true" 
											value="${shiftvillageblock.blockDName}"></form:hidden>
											<input type='hidden' id="hdnStateCode" value='<%=request.getAttribute("stateCode")%>' />
										</li>
										<c:if test="${districtCode == 0}">
									<li><label for="ddSourceDistrict"><spring:message  htmlEscape="true" 
												code="Label.SOURCEDISTRICT"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select
											path="districtNameEnglish" 
											id="ddSourceDistrict" cssClass="combofield"
											onchange="getSourceBlockListFinal(this.value);"
											onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');"
											onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceDistrict')">
											<form:option value="" htmlEscape="true">
												<esapi:encodeForHTMLAttribute><spring:message code="Label.SELECTDISTRICT" htmlEscape="true" ></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
											
											<c:forEach items="${distrinctlist}" var="distListvar">
												  <c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
												    <form:option htmlEscape="true" value="${distListvar.districtCode}" disabled="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>  
												  <c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
												     <form:option htmlEscape="true" value="${distListvar.districtCode}"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												  </c:if>
											</c:forEach>
											
											
											
											<%-- <form:options items="${distrinctlist}"
												itemLabel="districtNameEnglish"
												itemValue="districtPK.districtCode" /> --%>
										</form:select> 
									
										
										<div id="ddSourceDistrict_error" class="error"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"></spring:message></div>
										<div id="ddSourceDistrict_msg" style="display:none"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/></div>
										<div class="errormsg" id="ddSourceDistrict_error1"><form:errors path="districtNameEnglish" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddSourceDistrict_error2" style="display: none" ></div>				
												
									</li>
									</c:if>
								 <li>
										<label for="ddSourceBlock"><spring:message
												code="Label.SOURCEBLOCK" htmlEscape="true" ></spring:message> </label><span
										class="errormsg">*</span><br />
										
										  <form:select htmlEscape="true"
											path="blockNameEnglish" 
											cssClass="combofield" id="ddSourceBlock"
											onchange="getVillageBlockList(this.value);"
											onfocus="validateOnFocus('ddSourceBlock');helpMessage(this,'ddSourceBlock_msg');"
											onblur="vlidateOnblur('ddSourceBlock','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceBlock')">
											<form:option value="" htmlEscape="true">
												<esapi:encodeForHTMLAttribute><spring:message code="Label.SOURCEBLOCK" htmlEscape="true" ></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
											<form:options htmlEscape="true" items="${blockSourceList}"
												itemValue="blockCode" itemLabel="blockNameEnglish"></form:options>

										</form:select>
										
									
										
										<div id="ddSourceBlock_error" class="error"><spring:message code="Error.SOURCEBLOCK" htmlEscape="true"></spring:message></div>
										<div id="ddSourceBlock_msg" style="display:none"><spring:message code="Error.SOURCEBLOCK" htmlEscape="true"/></div>
										<div class="errormsg" id="ddSourceBlock_error1"><form:errors path="blockNameEnglish" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddSourceBlock_error2" style="display: none" ></div>			
												
								
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
										<c:if test="${districtCode == 0}">
								<li><label for="ddTargetDistrict"><spring:message  htmlEscape="true" 
												code="Label.TARGETDISTRICT"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select htmlEscape="true"
											path="districtNameEnglish" 
											id="ddTargetDistrict" cssClass="combofield"
											onchange="getTargetBlockList(this.value);"
											onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');"
											onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceDistrict')">
										
										</form:select> 
										
										<%-- <span id="ddSourceDistrict_msg" style="display: none;">Please Select District Like Ambala..
										</span> <span class="errormsg" id="ddSourceDistrict_error"><spring:message htmlEscape="true" 
												code="Error.SOURCEDISTRICT"></spring:message> </span> <span><form:errors
												path="districtNameEnglish" class="errormsg" htmlEscape="true"></form:errors> </span> --%>
										
										<div id="ddTargetDistrict_error" class="error"><spring:message code="Error.TARGETDISTRICT" htmlEscape="true"></spring:message></div>
										<div id="ddTargetDistrict_msg" style="display:none"><spring:message code="Error.TARGETDISTRICT" htmlEscape="true"/></div>
										<div class="errormsg" id="ddTargetDistrict_error1"><form:errors path="districtNameEnglish" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddTargetDistrict_error2" style="display: none" ></div>				
												
									</li>
									</c:if>
										<li><label for="ddTargetBlock"><spring:message
												code="Label.TARGETBLOCK" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select htmlEscape="true"
											path="blockNameEnglish" 
											cssClass="combofield" id="ddTargetBlock"
											onfocus="validateOnFocus('ddTargetBlock');helpMessage(this,'ddTargetBlock_msg');"
											onblur="vlidateOnblur('ddTargetBlock','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddTargetBlock')">
											<form:option value="" htmlEscape="true">
												<esapi:encodeForHTMLAttribute><spring:message code="Label.TARGETBLOCK" htmlEscape="true" ></spring:message></esapi:encodeForHTMLAttribute>
											</form:option>
											
										</form:select> 
										
									<%-- 	<span id="ddTargetBlock_msg" style="display: none;">Please Select Block Like Ambala-I.. </span>
										<span><form:errors path="blockNameEnglishDest" class="errormsg" htmlEscape="true"></form:errors>
										</span> <span class="errormsg" id="ddTargetBlock_error"><spring:message
												code="Error.TARGETBLOCK" htmlEscape="true"></spring:message> </span> --%>
										
										<div id="ddTargetBlock_error" class="error"><spring:message code="Error.TARGETBLOCK" htmlEscape="true"></spring:message></div>
										<div id="ddTargetBlock_msg" style="display:none"><spring:message code="Error.TARGETBLOCK" htmlEscape="true"/></div>
										<div class="errormsg" id="ddTargetBlock_error1"><form:errors path="blockNameEnglish" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddTargetBlock_error2" style="display: none" ></div>	</li>
										<li></li>
										<li></li>
										
									
									</ul>
							
							
							</div>
						
							
							<!-- <div class="clear"></div> -->

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
														multiple="multiple" class="frmtxtarea">
													</form:select> <form:hidden path="villageName" id="hdnVillageName" htmlEscape="true" />
											
											
											</div>
											<div class="ms_buttons">
														<input type="button"
													class="bttn" value=" &gt;&gt;" 
													onClick="listbox_moveacross('ddSourceVillage', 'ddTargetVillage','V')" />
													
													<input type="button"
													value=" &lt;&lt;" class="bttn" 
													onClick="listbox_moveacross('ddTargetVillage', 'ddSourceVillage','V')" />
											
											</div>
											<div class="ms_selection">
													<label for="ddTargetVillage"><spring:message
															code="Label.CONTRIBUTVILLAGELIST" htmlEscape="true"></spring:message> </label><span
													class="errormsg">*</span><br /> <form:select htmlEscape="true"
														path="villageNameEnglish" id="ddTargetVillage" size="1"
														multiple="multiple" class="frmtxtarea"
														onfocus="validateOnFocus('ddTargetVillage');helpMessage(this,'ddTargetVillage_msg');"
														onblur="vlidateOnblur('ddTargetVillage','1','15','p');hideHelp();"
														onkeyup="hideMessageOnKeyPress('ddTargetVillage')">
													</form:select> 
													
													
													<div id="ddTargetVillage_error" class="error"><spring:message code="Error.VILLAGE" htmlEscape="true"></spring:message></div>
													<div id="ddTargetVillage_msg" style="display:none"><spring:message code="Error.VILLAGE" htmlEscape="true"/></div>
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
										onclick="return validateBlockAll();getVillageName();"
										value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
										
								</label> <label> <input type="button" class="btn" name="Submit6"
										value="<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>"
									     onclick="javascript:location.href='shiftvillageblock.htm?<csrf:token uri='shiftvillageblock.htm'/>';"/> </label> <label>
										<input type="button" name="Cancel" class="btn"
										value=<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>
										id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
									</div> 
					</div>
				
	
			</form:form>
			<div class="clear"></div>
			</div>
			<script src="/LGD/JavaScriptServlet"></script>
			<div class="clear"></div>
			<%-- <div id="footer"><tiles:insertAttribute name="footer" ignore="true"  /></div> --%>
		</div>

</body>
</html>