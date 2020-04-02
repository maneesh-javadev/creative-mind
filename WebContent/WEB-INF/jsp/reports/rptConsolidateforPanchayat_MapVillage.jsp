<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<%-- <%
	java.text.DateFormat df = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");
%> --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<title>E-Panchayat</title>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" language="Javascript">

function goBack()
{
	//alert("inside the back method1111");
	document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "rptBacktoParentConsolidateReport.do?<csrf:token uri='rptBacktoParentConsolidateReport.do'/>";
	document.getElementById('form1').submit();
}

function PrintDiv() {

	
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("printable2");
	// document.getElementById('btn1').style.visibility = 'hidden';
	var Win4Print = window.open('', '', 'left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0');
	// alert("contect"+printContent.innerHTML);
	Win4Print.document.write(printContent.innerHTML);
	Win4Print.document.close();
	Win4Print.focus();
	Win4Print.print();
	Win4Print.close();
	document.getElementById('footer').style.display = 'none';
	document.getElementById('footer').style.visibility = 'hidden';
	
	return false;
}

$(document).ready(function(){
	 
	$("#tbl_with_brdr tr:even").css("background-color", "#dedede");
	 
	$("#tbl_with_brdr tr:odd").css("background-color", "#ffffff");
	 
	});
	
</script>
</head>
<body>
	<form:form commandName="consolidateReportLBRPT" id='form1'>
	<form:input path="stateId" type="hidden" name="stateId" id="stateId"  />
 								<form:input path="lbtype" type="hidden" name="lbtype" id="lbtype" /> 
 								<form:input path="statelevel" type="hidden" name="statelevel" id="statelevel" /> 
 								<form:input path="localbodycode" type="hidden" name="localbodycode" id="localbodycode" />
 								<form:input path="financialYear" type="hidden"  />
 								<form:input path="parentCode" type="hidden"  />
 								<form:input path="parentLevel" type="hidden"  />
 									
<div id="frmcontent">		
	<div class="frmhd">
			<h3 class="subtitle">&nbsp;</h3>
			<c:if test="${!empty consolidateLBList }">
					<a id="showprint" href="#"><img
						src='<%=contextPath%>/images/printer_icon.png' border="0"
						onclick="PrintDiv();" /></a>
		</c:if>
		</div>
			
		<div class="clear"></div>
							
	
	<div class="frmtxt" id="printable2">
		<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
				<%-- <spring:message code="Label.LISTOFVILLAGESMAPPED"
												htmlEscape="true"></spring:message> --%>
												<c:out value="${consolidateReportLBRPT.state_name_english}" escapeXml="true"></c:out> 
												<c:if test="${!empty consolidateReportLBRPT.financialYear and consolidateReportLBRPT.financialYear ne ''}">
													<c:out value="(${consolidateReportLBRPT.financialYear})" escapeXml="true"></c:out>
												</c:if>
							<!-- Temporarily Commented -->
							<%-- <c:if
									test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}">
									<label><spring:message code="Label.VILLAGEPANCHYATNME"
											htmlEscape="true"></spring:message> </label>&nbsp;<label><spring:message
											code="Label.OF" htmlEscape="true"></spring:message>&nbsp;${consolidateReportLBRPT.state_name_english}

									</label>
								</c:if> <c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}">
									<label><spring:message code="Label.VILLAGELVLTRADLB"
											htmlEscape="true"></spring:message> </label>&nbsp;<label><spring:message
											code="Label.OF" htmlEscape="true"></spring:message>&nbsp;${consolidateReportLBRPT.state_name_english}

									</label>
								</c:if> --%>
	   	</h6>
	   	
	   	<ul class="blocklist">
	   			
				<li>*N.A.- Not Applicable</li>
				<li>
					<c:if
						test="${!empty consolidateLBList}">
						** Due to periodic elections, data is dynamic in nature and keep on changing
					</c:if><input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
				</li>
				<li>
					<c:if
						test="${!empty consolidateLBList}">
						*** Local Government Directory is now mapped to Census 2011 village codes
					</c:if><input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
				</li>
		</ul>
		<br />
		
		<table class="tbl_with_brdr" width="100%" id="tbl_with_brdr">
						<tr class="tblRowTitle tblclear">
							<td width="4%"><label><spring:message
										code="Label.SRNO" htmlEscape="true"></spring:message> </label>
							</td>

							<td width="12%" align="center"><c:if
									test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}">
									<label> <spring:message code="Label.VILLAGECODE"
											htmlEscape="true"></spring:message> </label>
								</c:if> <c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}">
									<label> <spring:message code="Label.VILLAGECODE"
											htmlEscape="true"></spring:message> </label>
								</c:if></td>
							<td width="20%" align="center"><c:if
									test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}">
									<label> <spring:message code="Label.CENSUSFORVILLAGE"
											htmlEscape="true"></spring:message> </label>
								</c:if> <c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}">
									<label> <spring:message code="Label.CENSUSFORVILLAGE"
											htmlEscape="true"></spring:message> </label>
								</c:if></td>
							<td width="22%" align="left"><c:if
									test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}">
									<label> <spring:message code="Label.VILLAGENAME"
											htmlEscape="true"></spring:message> </label>
								</c:if> <c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}">
									<label> <spring:message code="Label.VILLAGENAME"
											htmlEscape="true"></spring:message> </label>
								</c:if></td>
								<td width="22%" align="left"><c:if
									test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}">
									<label> <spring:message code="Label.COVERAGEDTYPE"
											htmlEscape="true"></spring:message> </label>
								</c:if> <c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}">
									<label> <spring:message code="Label.COVERAGEDTYPE"
											htmlEscape="true"></spring:message> </label>
								</c:if></td>
							<td width="22%" align="center"><c:if
									test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}">
									<label> <spring:message code="Label.MAINVILLAGE"
											htmlEscape="true"></spring:message> </label>
								</c:if> <c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}">
									<label> <spring:message code="Label.MAINVILLAGE"
											htmlEscape="true"></spring:message> </label>
								</c:if></td>
							
						</tr>


						<c:if test="${! empty consolidateLBList}">
							<c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow"
								items="${consolidateLBList}">
								<tr class="tblRowB">
									<td width="4%"><c:out value="${panchaytSetUpRow.index +1}" escapeXml="true"></c:out></td>

									<td width="12%" align="center"><c:out
											value="${panchaytSetUp[0]}" escapeXml="true"></c:out></td>
									<td width="20%" align="center"><c:out
											value="${panchaytSetUp[1]}" escapeXml="true"></c:out></td>
									<td width="22%" align="left"><c:out
											value="${panchaytSetUp[2]}" escapeXml="true"></c:out></td>
											<td width="22%" align="left">
										
										
											 
											 <c:if test="${fn:containsIgnoreCase(panchaytSetUp[3],'F')}">
												<label> <spring:message code="Label.FULL" htmlEscape="true"></spring:message> </label>
											</c:if>
												 <c:if test="${fn:containsIgnoreCase(panchaytSetUp[3],'P')}">
												<label> <spring:message code="Label.PART" htmlEscape="true"></spring:message> </label>
											</c:if>
									
											
											</td>
									<td width="22%" align="center">
										<c:choose>
											<c:when test="${panchaytSetUp[4] == 'true'}">
												<img src='<%=contextPath%>/images/right-icon5.gif' border="0" />
											</c:when>
												<c:otherwise>
													<c:out value=""></c:out>
												</c:otherwise>
										</c:choose>
									</td>
									
									
									
								</tr>
							</c:forEach>
								<form:input path="stateId" type="hidden" name="stateId" id="stateId"  />
 								<form:input path="lbtype" type="hidden" name="lbtype" id="lbtype" /> 
 								<form:input path="statelevel" type="hidden" name="statelevel" id="statelevel" /> 
 								<form:input path="localbodycode" type="hidden" name="localbodycode" id="localbodycode" />
						</c:if>
						<tr class="tblRowTitle tblclear">

						</tr>
					</table>
					
					<ul class="blocklist center" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
						<li>
							<label><spring:message code="Label.URL" htmlEscape="true"></spring:message>
							<%=request.getScheme() + "://" + request.getServerName() + request.getContextPath()%>
							</label>
						</li>
						<li>
							<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
							<label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>
							<%=df.format(new java.util.Date())%> </label>
						</li>
						<li>
							<label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
						</li>
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
	   						 <div id="displayBox" style="text-align: center;display:none;"><img  src="<%= request.getContextPath() %>/images/ajax-loader-2.gif"  /></div>
	    
	    			</div> 
		
		</div>
		
		<div class="buttons center">
			<input type="button" name="Submit2" class="btn" value="<spring:message htmlEscape="true"  code="Button.BACK"></spring:message>"
										onclick="goBack();" />
			<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
											onclick="javascript:location.href='welcomeLocal.do?<csrf:token uri='welcomeLocal.do'/>';" />
		</div>
				
</div>
	</form:form>
	
	
	<c:if test="${consolidateReportLBRPT.otherInformationFlag eq true}">
		<%@include file="../reports/otherPesAppReport.jsp"%>
	</c:if>
</body>
</html>
