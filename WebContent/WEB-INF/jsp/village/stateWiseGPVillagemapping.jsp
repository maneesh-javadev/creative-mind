<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />


<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'> </script>
<script type='text/javascript'  src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
<%-- <script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrBlockService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrStateService.js'></script> --%>

<script src="js/stateWiseUnmappedVillages.js"></script>
<script type="text/javascript" language="javascript">
function hideMessageOnKeyPressstate()
{	
	$("#OfficialAddress_msg").hide();	
	
}</script>
<style>
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
<title>

</title>

</head>

<body>
	<div id="frmcontent">
		<div class="frmhd">
		<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.stateWiseGPtoVillageMappingStatus"></spring:message></h3>
		<c:if test="${empty StatewiseGPtoVillageMappingList}"> 
			<ul class="listing">
				<!-- <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"> <img alt="Toggle" src="images/plus.jpg" border="0" /></a></li> -->
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
			</ul>
		</c:if>
		
		<c:if test="${!empty StatewiseGPtoVillageMappingList }">
			<a id="showprint" href="#"><img src='<%=contextPath%>/images/printer_icon.png' alt="Print" border="0" onclick="CallPrint();" /></a>			
		</c:if>
		</div>
			
		<div class="clear"></div>
		
		<div class="frmpnlbrdr">
			<form:form action="stateWiseMappedVillagesReport.do" id="form1" name="form1" method="POST"
				commandName="statewiseUnmappedVillagesForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='stateWiseMappedVillagesReport.do'/>" />
				<input type="hidden" name="direction" id="direction" value="0" />
			<div id="cat">
					
					
				   <c:if test="${empty StatewiseGPtoVillageMappingList}"> 
				<div class="frmpnlbg" id='viewentity'>
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message>
						</div>
						
				
				<div class="search_criteria" id='showbydropdown'>
					<div class="block">
						<ul class="blocklist">
							<li>
								<label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
								<form:select path="stateNameEnglish" class="combofield"
										style="width: 175px" id="ddSourceState" onchange="error_remove();hideMessageOnKeyPressstate()" >
										<form:option value="">
											<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
										</form:option>
										<form:options items="${stateSourceList}"
											itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
									</form:select>
									<div class="errormsg" id="errSelectStateName" style="visibility: hidden; display: none;"><spring:message code="error.select.SELECTSTATENAME"/></div>
									<div class="errormsg" id="errSelectStatedistrictName">
										<c:if test="${ districtpan == false }">
										<div id="OfficialAddress_msg" >Report can't be generate for this State <%-- <spring:message code="error.PSV" htmlEscape="true"/> --%></div>
																			
										</c:if>
							</li>
						</ul>
					</div>
					
					<div class="block">
						<ul class="captcha_fields">
							<li><img src="captchaImage" alt="Catcha Image" /></li>
							<li>
								<label for="captchaAnswer"><spring:message code="captcha.message" htmlEscape="true" /></label><br />
								<form:input path="captchaAnswers" name="captchaAnswesr" id="captchaAnswer" class="frmfield" autocomplete="off" />								
								<div class="errormsg">
									<c:if test="${ captchaSuccess2 == false }">
										<spring:message code="captcha.errorMessage" htmlEscape="true" />
									</c:if>
								</div>
								<div class="errormsg" id="errorCaptcha" style="visibility: hidden; display: none;"><spring:message code="captcha.errorMessage"/></div>
							</li>
							<li>
								<input type="submit" name="Submit" class="btn" onclick="return validate_report();" value="<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message>" />
								<input type="button" name="Submit2" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>" onclick="javascript:go('stateWiseGramPanchayats.do');" />
								<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:go('welcome.do');" />
							</li>
						</ul>
					</div>
					
					<div class="clear"></div>
				</div>
		
						
		
					</div>
				</div>
				 </c:if> 
						
				<c:if test="${! empty StatewiseGPtoVillageMappingList}">	
						<div id="printable">
							<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
								<spring:message htmlEscape="true" code="Label.DPGPVillMapping"></spring:message>
							</h6>							
						
							<table class="gridtable" width="100%" align="center" id="tbl_with_brdr" >
							
							<tr class="tblRowTitle tblclear" bgcolor="#e21d63">
										<td width="12%"><spring:message
												code="Label.SNO"></spring:message></td>
										<td width="20%"><spring:message
												code="Label.DISTRICTPANCHYATNME"></spring:message></td>
										<td width="17%"><spring:message
												code="Label.TotalGP"></spring:message></td>
										<td width="17%"><spring:message
												code="Label.TotalVLbyDP"></spring:message></td>
										
										<td width="17%" ><spring:message
												code="Label.TotalGPMappelVil"></spring:message>
										</td>
										<td width="17%"><spring:message
														htmlEscape="true" code="Label.VILLAGEMAPPEDPERCENTAGE"></spring:message>
										</td>
									</tr>
							
										
								
											<!-- 	<tr class="tblRowTitle tblclear"></tr>		 -->
									<c:forEach var="listStatewiseGPtoVillageMappingDetail" varStatus="listEntityRow" items="${StatewiseGPtoVillageMappingList}">
													<tr class="tblRowB">
														<td><c:out value="${(offsets*limits+listEntityRow.index+1)}" escapeXml="true"></c:out></td>
														<td><c:out value="${listStatewiseGPtoVillageMappingDetail.localbodyname}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listStatewiseGPtoVillageMappingDetail.totalgrampan}" escapeXml="true"></c:out></td>
														<td><c:out value="${listStatewiseGPtoVillageMappingDetail.toalVillage}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listStatewiseGPtoVillageMappingDetail.mappedVillage}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listStatewiseGPtoVillageMappingDetail.mappedPercent}" escapeXml="true"></c:out></td>
														
														
													</tr>
								  </c:forEach>
								
											
								  <tr class="tblRowTitle tblclear"> 
								  
								  <td><spring:message htmlEscape="true"	code="Label.TOTALS"></spring:message></td>
														<td></td>
														<td><c:out value="${totalGp}" escapeXml="true"></c:out></td>
														<td><c:out value="${totalVillage}" escapeXml="true"></c:out></td>
														<td><c:out value="${totalMappedVil}" escapeXml="true"></c:out></td>
														<td><c:out value="${mappedPercent}" escapeXml="true"></c:out></td>
												
														
														
													</tr>
								  <tr class="tblRowTitle tblclear"></tr>	
								 </table> 
								 
								<ul class="blocklist center" style="text-align: center; list-style: none;"> <!-- Inline style only for Print purpose -->
										<li><label><spring:message code="Label.URL"
													htmlEscape="true"></spring:message> <%=request.getScheme() + "://"
									+ request.getServerName()
									+ request.getContextPath()%>
										</label></li>
										<li>
											<%
												java.text.DateFormat df = new java.text.SimpleDateFormat(
																		"dd/MM/yyyy hh:mm:ss a");
											%>
											<label><spring:message code="Label.REPORTPRINTED"
													htmlEscape="true"></spring:message> <%=df.format(new java.util.Date())%>
										</label></li>
										<li><label><spring:message
													code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message>
										</label></li>
									</ul>
							
							 	 <div id="footer" style="visibility: hidden; display: none;">

									<p id="footertext" style="text-align: center">
													Site is designed, hosted and maintained by <a
														target="_blank" href="http://www.nic.in/">National
														Informatics Centre</a><br> Contents on this website is
														owned,updated and managed by the <a target="_blank"
														href="http://www.panchayat.gov.in/" target="_blank">Ministry of
															Panchayati Raj</a>, Government of India <br> Site best
															viewed on Internet Explorer (IE)-8 &amp; above, Mozilla
															Firefox-11 &amp; above 
									</p>
									<div id="displayBox" style="text-align: center; display: none;">
										<img
											src="<%=request.getContextPath()%>/images/ajax-loader-2.gif" alt="Loading..." />
									</div>

								</div>
							
						 </div>	 <!-- Printable Div ends here -->
							
						<div class="buttons center">
							<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:go('welcome.do');" />
						</div>
					  
					 </c:if> 
			</div>				
					
			</form:form>
		</div>
			
	</div>
		
		
			
</body>
</html>