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
			<h3 class="subtitle"><spring:message code="Label.rptdistrictWiseInvalidatedVillage" htmlEscape="true"></spring:message></h3>
			<c:if test="${empty districtInvalidate}">
			<ul class="listing">
					<li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"> <img src="images/plus.jpg" alt="Toggle" border="0" /></a></li>
					<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
					<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
			</ul>	
			</c:if>
			
			<c:if test="${!empty districtInvalidate }">
				<a id="showprint" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" alt="Print" onclick="CallPrint();" /></a>									
			</c:if>
		</div>
			
		<div class="clear"></div>
		
		<div class="frmpnlbrdr">
			<form:form action="rptDistrictWiseInvalidatedVillageDetail.do" id="form1" name="form1" method="POST" 	commandName="districtInvalidate">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='rptDistrictWiseInvalidatedVillageDetail.do'/>" />
				<input type="hidden" name="direction" id="direction" value="0" />
			<div id="cat">
			<c:if test="${! empty invalidatetList}">						
						
						<div id="printable">
							<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
							<spring:message htmlEscape="true" code="Label.rptdistrictWiseInvalidatedVillage" /><c:out value="${message}" escapeXml="true"></c:out>
							</h6>
							
						
							<table class="gridtable" width="100%" align="center" id="tbl_with_brdr" >
							
							<tr class="tblRowTitle tblclear" bgcolor="#e21d63">
										<td width="10%"><spring:message code="Label.SNO"></spring:message></td>
												
										<td width="15%"><spring:message code="Label.VILLAGENAMEINENGLISH"></spring:message></td>
												
										<td width="15%"><spring:message code="Label.VILLAGEVERSION"></spring:message></td>
											
										<td width="15%"><spring:message code="Label.SUBDISTRICTCODE"></spring:message></td>
										<td width="15%"><spring:message code="Label.SUBDISTRICTNAMEEN"></spring:message></td>
												
										<td width="15%"><spring:message code="Label.UlbCode"></spring:message></td>
												
										<td width="15%" ><spring:message code="Label.UlbName"></spring:message></td>
									
									</tr>
								
									<c:forEach var="getDistrictWiseInvalidatedVillage" varStatus="listEntityRow" items="${invalidatetList}">
													<tr class="tblRowB">
														<td><c:out value="${(offsets*limits+listEntityRow.index+1)}" escapeXml="true"></c:out></td>
														<td><c:out value="${getDistrictWiseInvalidatedVillage.village_name_english}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${getDistrictWiseInvalidatedVillage.village_version}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${getDistrictWiseInvalidatedVillage.subdistrict_code}" escapeXml="true"></c:out></td>
														<td><c:out value="${getDistrictWiseInvalidatedVillage.subdistrict_name_english}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${getDistrictWiseInvalidatedVillage.ulb_code}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${getDistrictWiseInvalidatedVillage.ulb_name}" escapeXml="true"></c:out></td>
														
														
													</tr>
								  </c:forEach>
							
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
															Panchayati Raj</a>, Government of India <br/> Site best
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
								<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>"
										onclick="javascript:go('welcome.do');" /> 
							</div>
					   
					 </c:if> 
						
			</div>				
					
			</form:form>
			</div>
			
			 <c:if test="${recordlength ==0}">
						<div class="errormsg" id="divData">
							<spring:message code="Error.noresult" htmlEscape="true"></spring:message>
						</div>
					</c:if>
			
	
			
	</div>
		
		
			
</body>
</html>