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

<script src="js/stateWiseGPtoVillageMappingReport.js"></script>
<title>

</title>

</head>

<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.stateWiseGPtoVillageMappingStatus"></spring:message></h3>
			<c:if test="${empty StatewiseGPtoVillageMappingList}"> 
			<ul class="listing">
				<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"> <img src="images/plus.jpg" border="0" alt="Toggle" /></a></li>
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
			</ul>
			</c:if> 
			<c:if test="${!empty StatewiseGPtoVillageMappingList }">
				<a id="showprint" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="PrintDiv();" alt="Print" /></a>
			</c:if>	
		</div>
			
		<div class="clear"></div>
		
		<div class="frmpnlbrdr">
			<form:form action="stateWiseGPtoVillageMappingReport.do" id="form1" name="form1" method="POST"
				commandName="stateWiseGPtoVillageMappingForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='stateWiseGPtoVillageMappingReport.do'/>" />
				
			<div id="cat">
					
					
				   <c:if test="${empty StatewiseGPtoVillageMappingList}"> 
				<div class="frmpnlbg" id='viewentity'>					
				
				<ul class="captcha_fields viewstates" id='showbydropdown'>
					<li>
						<img src="captchaImage" alt="Captcha Image" />
					</li>
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
						<input type="submit" name="Submit" class="btn" value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> />
						<input type="button" name="Submit2" class="btn"	value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message> onclick="javascript:go('stateWiseGPtoVillageMappingReport.do');" />
						<input type="button" name="Submit3" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message> onclick="javascript:go('welcome.do');" />
					</li>					
				</ul>
				</div>
				 </c:if> 
				 
				
				<c:if test="${! empty StatewiseGPtoVillageMappingList}">
						<div id="printable" id="divData">
							<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
								<spring:message htmlEscape="true" code="Label.stateWiseGPtoVillageMappingStatus"></spring:message>
							</h6>
							
							<table class="tbl_with_brdr" width="100%" align="center" id="tbl_with_brdr">
											<tr class="tblRowTitle tblclear">
												<td width="10%" rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></td>
											
												<td width="30%" rowspan="2"><spring:message htmlEscape="true"	code="Label.STATE"></spring:message></td>
												<td width="20%" rowspan="2"><spring:message htmlEscape="true" code="Label.NOOFVILLAGES"></spring:message></td>
												<td width="20%" rowspan="2"><spring:message htmlEscape="true"	code="Label.VILLAGEMAPPED"></spring:message></td>
												<td width="20%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGEMAPPEDPERCENTAGE"></spring:message></td>
												
												
												
											</tr>
								
												<tr></tr>			
									<c:forEach var="listStatewiseGPtoVillageMappingDetail" varStatus="listEntityRow" items="${StatewiseGPtoVillageMappingList}">
													<tr class="tblRowB">
														<td><c:out value="${(offsets*limits+listEntityRow.index+1)}" escapeXml="true"></c:out></td>
														<td><c:out value="${listStatewiseGPtoVillageMappingDetail.stateName}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listStatewiseGPtoVillageMappingDetail.totalVillage}" escapeXml="true"></c:out></td>
														<td><c:out value="${listStatewiseGPtoVillageMappingDetail.mappedVillage}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listStatewiseGPtoVillageMappingDetail.mappedPercent}" escapeXml="true"></c:out></td>
														
														
													</tr>
								  </c:forEach>
								  
								 </table> 
								
								 <ul class="blocklist center"
										style="text-align: center; list-style: none;">
										<!-- Inline style only for Print purpose -->
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
							<input type="button" name="Submit3" class="btn" value=<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message> onclick="javascript:go('welcome.do');" />
						</div>
					   
					 </c:if> 
						
				
				
				 
			</div>				
					
			</form:form>
		</div>
			
	</div>
		
		
			
</body>
</html>