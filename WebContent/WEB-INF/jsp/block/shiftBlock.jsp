<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>E-Panchayat</title> <script src="js/shiftblock.js"></script>

	<script src="js/validation.js"></script>
	<script src="js/successMessage.js"></script>
	<script src="js/helpMessage.js"></script>
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
		src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
	<script type="text/javascript" language="javascript">
		dwr.engine.setActiveReverseAjax(true);
		function open_win() {
			
			var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
			//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
		} 
		
		function validateNumeric() {
			var key = event.keyCode;

			if ((key >= 48) && (key <= 58) || (key == 45)) {

			} else {
				event.returnValue = false;
			}
		}
	</script>
</head>


<body onLoad="blockloadpageFinal();" oncontextmenu="return false"
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
		
			<h3 class="subtitle"><label><spring:message code="Label.SHIFTBLOCK" htmlEscape="true"></spring:message></label></h3>
										 <ul id="showhelp" class="listing">
					 				       <%--//these links are not working  <li>
					 				         <a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a>           
					 				        </li>
					 				        
					 				        <li>
					 				        	<a href="#" class="frmhelp"><spring:message
								code="Button.HELP" htmlEscape="true"></spring:message> </a>
					 				        </li> --%>
					 				     
					 			        </ul>
		
		</div>

		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form commandName="shiftblock" method="POST"
				onsubmit="cursorwait();" action="draftShiftBlock.htm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftShiftBlock.htm"/>"/>
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.SELSOURCE" htmlEscape="true"></spring:message>
							</div>
							<input type="hidden" id="ddSourceState" value="<c:out value='${stateCode}' escapeXml='true'></c:out>"/>
							<div>
							<ul class="blocklist">
							    <li>
							    <form:hidden path="govtOrderConfig" value="${govtOrderConfig}" htmlEscape="true"/> 
							    <form:hidden path="operationCode" value="${shiftblock.operationCode}" htmlEscape="true"/>
								<form:hidden path="districtSName" value="${shiftblock.districtSName}" id="hdnDistrictSName" htmlEscape="true"/>
								<form:hidden path="districtDName" value="${shiftblock.districtDName}" id="hdnDistrictDName" htmlEscape="true"/>
							    
							    </li>
							    <li>
							    				<label for="ddSourceDistrict"><spring:message
												code="Label.SOURCEDISTRICT" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select
											path="districtNameEnglish" cssClass="combofield"
											id="ddSourceDistrict"
											onchange="getTargetDictrictandBlockListFinal(this.value)"
											onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');"
											onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddSourceDistrict')" htmlEscape="true">
											<form:option value="" htmlEscape="true">
												<spring:message code="Label.SELECTDISTRICT"
													htmlEscape="true"></spring:message>
											</form:option>
											
											<c:forEach items="${districtList}" var="distListvar">
												<c:if test="${distListvar.operation_state == 'F'.charAt(0)}">
													<form:option value="${distListvar.districtCode}"
														disabled="true" htmlEscape="true"><c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												</c:if>
												<c:if test="${distListvar.operation_state == 'A'.charAt(0)}">
													<form:option value="${distListvar.districtCode}" htmlEscape="true">	<c:out value="${distListvar.districtNameEnglish}" escapeXml="true"></c:out></form:option>
												</c:if>
											</c:forEach>
											
											<%-- <form:options items="${districtList}"
												itemLabel="districtNameEnglish"
												itemValue="districtPK.districtCode" /> --%>
										</form:select>
										
										
										
										<div id="ddSourceDistrict_error" class="error"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"></spring:message></div>
										<div id="ddSourceDistrict_msg" style="display:none"><spring:message code="Error.SOURCEDISTRICT" htmlEscape="true"/></div>
										<div class="errormsg" id="ddSourceDistrict_error1"><form:errors path="districtNameEnglish" htmlEscape="true"/></div>  
										<div class="errormsg" id="ddSourceDistrict_error2" style="display: none" ></div>
							    
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
									<li>
											<label for="ddDestDistrict"><spring:message
												code="Label.TARGETDISTRICT" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select
											path="districtNameEnglish" 
											id="ddDestDistrict" cssClass="combofield"
											onfocus="validateOnFocus('ddDestDistrict');helpMessage(this,'ddDestDistrict_msg');"
											onblur="vlidateOnblur('ddDestDistrict','1','15','c');hideHelp();"
											onkeyup="hideMessageOnKeyPress('ddDestDistrict')" htmlEscape="true"></form:select>

										
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
								<spring:message code="Label.SHIFTBLOCK" htmlEscape="true"></spring:message>
							</div>
							
							<ul class="blocklist">
								<li>
									<div class="ms_container">
										<div class="ms_selectable">
											<label for="ddSourceBlock"><spring:message
													code="Label.AVAILBLOCKLIST" htmlEscape="true"></spring:message>
											</label> <br />
											<form:select id="ddSourceBlock" items="${blockSourceList}"
												path="blockNameEnglish" size="1" multiple="multiple"
												itemLabel="blockNameEnglish" itemValue="blockCode" class=""
												cssClass="frmtxtarea combofield" htmlEscape="true">

																</form:select> <form:hidden path="blockName" id="hdnBlockName" htmlEscape="true"/>
										
										</div>
										<div class="ms_buttons">
													<input type="button" class="bttn"
																value=" &gt;&gt;" 
																onclick="listbox_moveacross('ddSourceBlock', 'ddDestBlock','B')" />
										
													<input type="button"  class="bttn"
																value=" &lt;&lt;"
																onclick="listbox_moveacross('ddDestBlock', 'ddSourceBlock','B')" />
										</div>
										<div class="ms_selection">
											<label for="ddDestBlock"><spring:message
													code="Label.CONTRIBUTBLOCKLIST" htmlEscape="true"></spring:message>
											</label><span class="errormsg">*</span> <br />
											<form:select items="${blockDestList}" path="blockNameEnglish"
												id="ddDestBlock" size="1" multiple="multiple"
												class="frmtxtarea"
												onblur="vlidateOnblur('ddDestBlock','1','15','p');hideHelp();"
												onkeyup="hideMessageOnKeyPress('ddDestBlock')" htmlEscape="true">

																</form:select>
															
																<div id="ddDestBlock_error" class="error"><spring:message code="Error.BLOCK" htmlEscape="true"></spring:message></div>
																<div id="ddDestBlock_msg" style="display:none"><spring:message code="Error.BLOCK" htmlEscape="true"/></div>
																<div class="errormsg" id="ddDestBlock_error1"><form:errors path="blockNameEnglish" htmlEscape="true"/></div>  
																<div class="errormsg" id="ddDestBlock_error2" style="display: none" ></div>	
										</div>
									
									</div>
								</li>
							
							</ul>
							
							
							<div class="clear"></div>
							

						</div>
					</div>
					<div class="btnpnl">
						<label> <input
										type="submit" name="Submit" 
										onclick="getBlockName();return validateAll()" 
										value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" />
								</label> <label> <input type="button" class="btn" name="Submit6"
										value="<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>"
										onclick="javascript:location.href='shiftblock.htm?<csrf:token uri='shiftblock.htm'/>';"/> </label> <label>
										<input type="button" name="Cancel" class="btn"
										value=<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>
										id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label>
									</div> 
									
									<br /> <br /> <br />
					</div>
              
				
				
			</form:form>
			<div class="clear"></div>
			</div>
				<script src="/LGD/JavaScriptServlet"></script>
				<div class="clear"></div>
		</div>
		

	
</body>
</html>