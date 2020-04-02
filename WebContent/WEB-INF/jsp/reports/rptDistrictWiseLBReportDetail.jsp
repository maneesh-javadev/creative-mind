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
			<h3 class="subtitle"><spring:message code="Label.distwiselbreport" htmlEscape="true"></spring:message></h3>
			<c:if test="${empty StatewiseGPtoVillageMappingList}"> 
				<ul class="listing">
					<!-- <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"> <img src="images/plus.jpg" border="0" /></a></li> -->
					<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
					<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
				</ul>	
			</c:if>	
			
			<c:if test="${!empty StatewiseGPtoVillageMappingList }">
				<a id="showprint" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint();" /></a>				
			</c:if>
		</div>
			
		<div class="clear"></div>
		
		<div class="frmpnlbrdr">
			<form:form action="stateWiseMappedVillagesReport.do" id="form1" name="form1" method="POST" 	commandName="consolidateReport">
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='stateWiseMappedVillagesReport.do'/>" />
				<input type="hidden" name="direction" id="direction" value="0" />
				
			<div id="cat">
				<c:if test="${! empty reportList}">						
						<div id="printable">
							<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
							District Wise Local Body Report
							</h6>
						
							<table class="gridtable" width="100%" align="center" id="tbl_with_brdr" >
							
							<tr class="tblRowTitle tblclear" bgcolor="#e21d63">
										<td width="12%"><spring:message
												code="Label.SNO"></spring:message></td>
												
												<td width="15%"><spring:message
												code="Label.LOCALBODYCODE"></spring:message></td>
												
										<td width="15%"><spring:message
												code="Label.LOCALGOVTBODYNAME"></spring:message></td>
											
										<td width="15%"><spring:message
												code="Label.LOCALBODYTYPENAME"></spring:message></td>
												
										<td width="15%">
										
										<spring:message
												code="Label.coveredvillage"></spring:message> 
												</td>
												
										<td width="15%" >
										
									
									<spring:message
												code="Label.parentpanchayat"></spring:message>
										</td>
									
									</tr>
							
										
								
											<!-- 	<tr class="tblRowTitle tblclear"></tr>		 -->
									<c:forEach var="listStatewiseGPtoVillageMappingDetail" varStatus="listEntityRow" items="${reportList}">
													<tr class="tblRowB">
														<td><c:out value="${(offsets*limits+listEntityRow.index+1)}" escapeXml="true"></c:out></td>
														<td><c:out value="${listStatewiseGPtoVillageMappingDetail.local_body_code}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listStatewiseGPtoVillageMappingDetail.local_body_name_english}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listStatewiseGPtoVillageMappingDetail.local_body_type_name}" escapeXml="true"></c:out></td>
														<td><c:out value="${listStatewiseGPtoVillageMappingDetail.village_name}" escapeXml="true"></c:out></td>
														<td align="left"><c:out value="${listStatewiseGPtoVillageMappingDetail.parent_name}" escapeXml="true"></c:out></td>
														
														
														
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
												src="<%=request.getContextPath()%>/images/ajax-loader-2.gif" />
										</div>

									</div>
							
						 </div>	
							
						<div class="buttons center">
							<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>" onclick="javascript:go('welcome.do');" />
						</div>	
					 </c:if> 
						
						
				
				
				 
			</div>				
					
			</form:form>
			
			 <c:if test="${recordlength ==0}">
						<div class="frmpnlbg" id="divData">
							<div class="frmtxt">
								<spring:message htmlEscape="true" code="error.norecordfound"></spring:message>
							</div>
						</div>
					</c:if>
			
		</div>
			
	</div>
		
		
			
</body>
</html>