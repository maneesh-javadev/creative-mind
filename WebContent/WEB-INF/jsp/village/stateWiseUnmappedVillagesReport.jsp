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
<script	type="text/javascript">
$(document).ready(function() {
	var list = ${SEARCH_UNMAPPED_VILLAGES_RESULTS_KEY};
	if(list == ''){
		alert("No Record Found");
	}
});
</script>
<title>

</title>

</head>

<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.StateWiseUnmappedVillages"></spring:message></h3>
			<c:if test="${empty SEARCH_UNMAPPED_VILLAGES_RESULTS_KEY}">
			<ul class="listing">
			<!-- 	<li>
					<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"> <img alt="Toggle" src="images/plus.jpg" border="0" /></a>
				</li> -->
				<%--//these links are not working <li>
					<a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a>
				</li>
				<li>
					<a href="#" class="frmhelp"><spring:message	htmlEscape="true" code="Label.HELP"></spring:message></a>
				</li> --%>
			</ul>
			</c:if>
			
			<c:if test="${!empty SEARCH_UNMAPPED_VILLAGES_RESULTS_KEY }">
				<a id="showprint" href="#"><img src='<%=contextPath%>/images/printer_icon.png' alt="Print" border="0" onclick="CallPrint();" /></a>				
			</c:if>
		</div>
			
		<div class="clear"></div>
		
		<div class="frmpnlbrdr">
			<form:form action="stateWiseUnmappedVillagesReport.do" id="form1" name="form1" method="POST"
				commandName="statewiseUnmappedVillagesForm">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='stateWiseUnmappedVillagesReport.do'/>" />
				<input type="hidden" name="direction" id="direction" value="0" />
				
			<div id="cat">
				   <c:if test="${empty SEARCH_UNMAPPED_VILLAGES_RESULTS_KEY}"> 
				<div class="frmpnlbg" id='viewentity'>
					<div class="frmtxt">
						<div class="frmhdtitle">
							<spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message>
						</div>
						
					<ul id='showbydropdown' class="listing">
						<li>
							<label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
									<form:select path="stateNameEnglish" class="combofield"
										style="width: 150px" id="ddSourceState" onchange="error_remove();">
										<form:option value="">
											<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
										</form:option>
										<form:options items="${stateSourceList}"
											itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
									</form:select>
									<div class="errormsg" id="errSelectStateName" style="visibility: hidden; display: none;"><spring:message code="error.select.SELECTSTATENAME"/></div>
						</li>
						<li>
							<label for="pageRows"><spring:message htmlEscape="true" code="Label.NOOFRECORDPERPAGE"></spring:message></label>
							<form:select htmlEscape="true" path="pageRows" class="combofield" style="width: 50px">
								<form:option value="50" label="50" />
								<form:option value="100" label="100" />
								<form:option value="250" label="250"/>
								<form:option value="500" label="500" />
								<form:option value="1000" label="1000" />
							</form:select>
						</li>
					</ul>
					
					<ul class="captcha_fields">
						<li><img src="captchaImage" alt="Captcha Image" /></li>
						<li>
							<label for="captchaAnswer"><spring:message code="captcha.message" htmlEscape="true" /></label></br>
							<form:input	path="captchaAnswers" name="captchaAnswesr" id="captchaAnswer"	class="frmfield" autocomplete="off" />
							<div class="errormsg">
											<c:if test="${ captchaSuccess2 == false }">
												<spring:message code="captcha.errorMessage" htmlEscape="true" />
											</c:if>
										</div>
							<div class="errormsg" id="errorCaptcha" style="visibility: hidden; display: none;"><spring:message code="captcha.errorMessage"/></div>
						</li>
						<li><c:if test = "${flag2 eq 1}"><span id="required" class="errormsg"><spring:message code="error.norecoredfound" htmlEscape="true"></spring:message></span></c:if></li>
						<li>
							<input type="submit" name="Submit" class="btn" onclick="return validate_report();" value=<spring:message htmlEscape="true"  code="Button.GETDATA"></spring:message> />
							<input type="button" name="Submit2" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>
										onclick="javascript:go('stateWiseUnmappedVillagesReport.do');" />
							<input type="button" name="Submit3" class="btn" value=<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>
										onclick="javascript:go('welcome.do');" /> 
						</li>
					</ul>	
					</div>
				</div>
				
				 </c:if> 
						
				  <c:if test="${! empty SEARCH_UNMAPPED_VILLAGES_RESULTS_KEY}">						
						<div id="printable">
							<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">${message}</h6>							
							<table class="tbl_with_brdr" width="100%" align="center" id="tbl_with_brdr">
											<tr class="tblRowTitle tblclear">
												<td width="8%" rowspan="2"><spring:message htmlEscape="true" code="Label.SNO"></spring:message></td>
											
												<td width="8%" rowspan="2"><spring:message htmlEscape="true"	code="Label.DISTRICTCODE"></spring:message></td>
												<td width="16%" rowspan="2"><spring:message htmlEscape="true" code="Label.DISTRICTNAMEINENGLISH"></spring:message></td>
												<td width="8%" rowspan="2"><spring:message htmlEscape="true"	code="Label.SUBDISTRICTCODE"></spring:message></td>
												<td width="16%" rowspan="2"><spring:message htmlEscape="true" code="Label.SUBDISTRICTNAMEENGLISH"></spring:message></td>
												<td width="8%" rowspan="2"><spring:message htmlEscape="true"	code="Label.VILLAGECODE"></spring:message></td>
												<td width="16%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
												<td width="20%" rowspan="2"><spring:message htmlEscape="true" code="Label.VILLAGESTATUS"></spring:message></td>
											
												
											</tr>
								
												<tr></tr>			
									<c:forEach var="listStatewiseUnmappedVillagesDetail" varStatus="listEntityRow" items="${SEARCH_UNMAPPED_VILLAGES_RESULTS_KEY}">
													<tr class="tblRowB">
														<td><c:out value="${(offsets*limits+listEntityRow.index+1)}" escapeXml="true"></c:out></td>
														<td><c:out value="${listStatewiseUnmappedVillagesDetail.districtCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listStatewiseUnmappedVillagesDetail.districtNameEnglish}" escapeXml="true"></c:out></td>
														<td><c:out value="${listStatewiseUnmappedVillagesDetail.subdistrictCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listStatewiseUnmappedVillagesDetail.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
														<td><c:out value="${listStatewiseUnmappedVillagesDetail.villageCode}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listStatewiseUnmappedVillagesDetail.villageNameEnglish}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listStatewiseUnmappedVillagesDetail.villageStatus}" escapeXml="true"></c:out></td>
														
													</tr>
								  </c:forEach>	
								 </table> 
								
								 <table class="tbl_with_brdr" width="100%" align="center">
								  	<tr>
											<td><table width="30%" class="tbl_no_brdr" align="right">
													<tr align="right">
														<td width="150" height="30" align="right" class="pageno"><c:out value="${villageCount}" escapeXml="true"></c:out></td>
														<td width="96" align="right" class="pre"><a href="#"
															onclick="setDirection(-1)"><spring:message
																	htmlEscape="true" code="Label.PREVIOUS"></spring:message>
														</a></td>
														<td width="24" align="center" class="pageno">|</td>
														<td width="51" align="right" class="nxt tblclear"><a
															href="#" onclick="setDirection(1)"><spring:message
																	htmlEscape="true" code="Label.NEXT"></spring:message> </a>
														</td>
														<td width="20" align="right" class="nxt tblclear">&nbsp;</td>
													</tr>
												</table>
											</td>
										</tr>
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
							<input type="button" name="Submit3" class="btn" value=<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>
									onclick="javascript:go('welcome.do');" />
						</div>
					   
					 </c:if>
						
				
				
				 
			</div>				
					
			</form:form>
		</div>
			
	</div>
		
		
			
</body>
</html>