<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%-- <%
	java.text.DateFormat df = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");
%> --%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>E-Panchayat</title>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'>
	
</script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'>
	
</script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>

<script type="text/javascript" language="Javascript">
function open_win() {
	
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
	//var obj = showModalDialog("https://www.google.co.in", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: no; status:no");
} 
function manageState1(url,stateid,type,level)
{
 	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }

function manageState2(url,stateid,type,level)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState3(url,stateid,type,level)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState4(url,stateid,type,level)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState5(url,stateid,type,level)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState6(url,stateid,type,level)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState7(url,stateid,type,level)
{
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }

function getData() 
{
		var errorCaptcha = document.getElementById('errorCaptcha');
		errorCaptcha.innerHTML = "";
		var sessionId = document.getElementById('sessionId').value;
		/* New Captcha Code */
		var capchaImgVal = document.getElementById('captchaAnswer').value;
		displayLoadingImage();
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "rptConsolidateforPanchayat.do?<csrf:token uri='rptConsolidateforPanchayat.do'/>";
		document.getElementById('form1').submit();
		return true;
}

function loadBasic()
{
	 document.getElementById("captchaAnswer").focus();
	
	
	}
	
function PrintDiv() {

	
	
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	document.getElementById('footertext').style.fontSize = '13px';
	var printContent = document.getElementById("printable");
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
</script>
</head>
<body onload="loadBasic()">
<div id="frmcontent">
	<div class="frmhd">
		<h3 class="subtitle"><spring:message code="Label.CONSOLIDATEDRPTOFLB" htmlEscape="true"></spring:message></h3>
		<c:if test="${empty consolidateLBList}">
			<ul class="listing">
				<!-- <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" alt="Toggle"	border="0" /></a></li> -->
			<%--//these links are not working 	<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></li> --%>
			</ul>
		</c:if>
		<c:if test="${!empty consolidateLBList}">
			<a id="showprint" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" alt="Print" onclick="PrintDiv();" />
			</a>			
		</c:if>
	</div>
	
	<div class="frmpnlbrdr">
		<div id="cat">
		<div class="frmpnlbg">
			<form:form commandName="consolidateReportLBRPT" id='form1' action="rptConsolidateforPanchayat.do">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="rptConsolidateforPanchayat.do"/>" />
			
			<c:if test="${empty consolidateLBList}">
				<ul class="captcha_fields consolidate_report">
					<li><%@ include file="../common/captcha_integration.jspf"%></li>
					<li>
						<input type="button" value="<spring:message code="Label.GETREPORT" htmlEscape="true"></spring:message>" onclick="getData();" />
						<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" />
					</li>							
				</ul>
			</c:if>
			
		
			<div id="printable">
				<table class="tbl_no_brdr" width="100%">         	

			<tr>
				<td align="left" width="100%"><c:if
						test="${!empty consolidateLBList}">
						<label>*N.A.- Not Applicable</label>
					</c:if><input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
				</td>
			</tr>
			<tr>
				<td align="center" width="100%"><c:if
						test="${!empty consolidateLBList}">

						<br />
						<table class="tbl_with_brdr" width="100%" align="center">
							<tr class="tblRowTitle tblclear">
								<td width="6%" rowspan="2" style="font-size: 15px;"><label><spring:message
											code="Label.SRNO" htmlEscape="true"></spring:message> </label>
								</td>
								<td width="16%" align="center" rowspan="2"
									style="font-size: 15px;"><label><spring:message
											code="Label.STATENAME" htmlEscape="true"></spring:message> </label>
								</td>
								<td width="33%" align="center" colspan="3"
									style="font-size: 15px;"><label><spring:message
											code="Label.RURALLOCALBODY" htmlEscape="true"></spring:message>
								</label>
								</td>
								
								<td width="12%" align="center" style="font-size: 15px;"
									rowspan="2"></label> <spring:message code="Label.URBANLB"
										htmlEscape="true"></spring:message></label>
								</td>
							</tr>
							<tr class="tblRowTitle tblclear">
								<td width="11%" align="center"><spring:message
										code="Label.DISTRICTTRADNME" htmlEscape="true"></spring:message>
								</td>
								<td width="11%" align="center"><spring:message
										code="Label.INTERTRADNME" htmlEscape="true"></spring:message>
								</td>
								<td width="11%" align="center"><spring:message
										code="Label.VILLAGELVL" htmlEscape="true"></spring:message>
								</td>

							</tr>


							<c:if test="${not empty consolidateLBList}">
								<c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow"
									items="${consolidateLBList}">
									
										<!-- 	<td width="100%" colspan="9" >
										<table width="100%" class="tbl_no_brdr">
											<tr class="tblRowB"> -->
										<c:out value="${panchaytSetUpRow.index +1}" escapeXml="true"></c:out>
										<c:out value="${panchaytSetUp.state_name_english}" escapeXml="true"></c:out>

										<c:choose><c:when test="${panchaytSetUp.dp_count == 0}">
										<c:out value="N.A."></c:out>
												</c:when>
												<c:otherwise>
													<%-- <a
														href="rptConsolidateforPanbyLevel.do?selstate=${panchaytSetUp.state_code}&type=P&level=D&<csrf:token uri='rptConsolidateforPanbyLevel.do'/>">
														<c:out value="${panchaytSetUp.dp_count}"></c:out> </a> --%>
														
														<a href="#" onclick="javascript:manageState1('rptConsolidateforPanbyLevel.do',${panchaytSetUp.state_code},'R','D');"><c:out value="${panchaytSetUp.dp_count}" escapeXml="true"></c:out></a>
														
														
														
												</c:otherwise>
											</c:choose>
										</td>
										<td width="11%" align="right"><c:choose>
												
												<c:when test="${panchaytSetUp.ip_count == 0}">
													<c:out value="N.A."></c:out>
												</c:when>
												<c:otherwise>
												<c:if test="${panchaytSetUp.dp_count == 0}">
														<a href="#" onclick="javascript:manageState2('rptConsolidateforPanbyLevel.do',${panchaytSetUp.state_code},'R','I');"><c:out value="${panchaytSetUp.ip_count}" escapeXml="true"></c:out></a>
												</c:if>
												<c:if test="${panchaytSetUp.dp_count != 0}">
													<c:if test="${panchaytSetUp.state_code==35}">
														<a href="#" onclick="javascript:manageState2('rptConsolidateforPanbyLevel.do',${panchaytSetUp.state_code},'R','I');"><c:out value="${panchaytSetUp.ip_count}" escapeXml="true"></c:out></a>
													</c:if>
													<c:if test="${panchaytSetUp.state_code!=35}">
													<c:out value="${panchaytSetUp.ip_count}" escapeXml="true"></c:out>
													</c:if>
												
												</c:if>
												
												
													<%-- <a
														href="rptConsolidateforPanbyLevel.do?selstate=${panchaytSetUp.state_code}&type=P&level=I&<csrf:token uri='rptConsolidateforPanbyLevel.do'/>">
														<c:out value="${panchaytSetUp.ip_count}"></c:out> </a> --%>
													
													<%-- <a href="#" onclick="javascript:manageState2('rptConsolidateforPanbyLevel.do',${panchaytSetUp.state_code},'P','I');"> --%><!--  </a> -->	
														
												</c:otherwise>
											</c:choose>
										</td>
										<td width="11%" align="right"><c:choose>
												<c:when test="${panchaytSetUp.vp_count == 0}">
													<c:out value="N.A."></c:out>
												</c:when>
												<c:otherwise>
												<c:if test="${panchaytSetUp.dp_count == 0 and panchaytSetUp.ip_count == 0}">
													<a href="#" onclick="javascript:manageState3('rptConsolidateforPanbyLevel.do',${panchaytSetUp.state_code},'R','V');"><c:out value="${panchaytSetUp.vp_count}" escapeXml="true"></c:out></a>
												</c:if>
												<c:if test="${panchaytSetUp.dp_count != 0 or panchaytSetUp.ip_count != 0}">
													<c:out value="${panchaytSetUp.vp_count}" escapeXml="true"></c:out>
												</c:if>
													<%-- <a
														href="rptConsolidateforPanbyLevel.do?selstate=${panchaytSetUp.state_code}&type=P&level=V&<csrf:token uri='rptConsolidateforPanbyLevel.do'/>">
														<c:out value="${panchaytSetUp.vp_count}"></c:out> </a> --%>
													<%-- <a href="#" onclick="javascript:manageState3('rptConsolidateforPanbyLevel.do',${panchaytSetUp.state_code},'P','V');"> --%><%-- </a> --%>	
														
												</c:otherwise>
											</c:choose>
										</td>
										<td width="12%" align="right">
										<c:choose>
												<c:when test="${panchaytSetUp.urban_count == 0}">
													<c:out value="N.A."></c:out>
												</c:when>
												<c:otherwise>
													<%-- <a
														href="rptConsolidateforPanbyLevel.do?selstate=${panchaytSetUp.state_code}&type=U&level=I&<csrf:token uri='rptConsolidateforPanbyLevel.do'/>">
														<c:out value="${panchaytSetUp.urban_count}"></c:out> </a> --%>
													<a href="#" onclick="javascript:manageState7('rptConsolidateforPanbyLevel.do',${panchaytSetUp.state_code},'U','I');"><c:out value="${panchaytSetUp.urban_count}" escapeXml="true"></c:out></a>		

												</c:otherwise>
											</c:choose>
										</td>
									
								</c:forEach>
								
								<form:input path="stateId" type="hidden" name="stateId" id="stateId"  />
 								<form:input path="statetype" type="hidden" name="statetype" id="statetype" /> 
 								<form:input path="statelevel" type="hidden" name="statelevel" id="statelevel" /> 
								
							</c:if>

							<tr class="tblRowTitle tblclear">
								<td width="4%"></td>
								<td width="16%" align="left"><label><spring:message
											code="Label.TOTALS"></spring:message> </label>
								</td>

								<td width="11%" align="right"><c:out
										value="${consolidateReportLBRPT.totalDPCount}" escapeXml="true"></c:out>
								</td>
								<td width="11%" align="right"><c:out
										value="${consolidateReportLBRPT.totalIPCount}" escapeXml="true"></c:out>
								</td>
								<td width="11%" align="right"><c:out
										value="${consolidateReportLBRPT.totalVPCount}" escapeXml="true"></c:out>
								</td>
								<td width="14%" align="right"><c:out
										value="${consolidateReportLBRPT.totalUrbanCount}" escapeXml="true"></c:out>
								</td>
							</tr>
						</table>
					</c:if>
					
				</td>
			</tr>
			</table>
			
				<c:if test="${! empty consolidateLBList}">
					<ul class="blocklist center" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
						<li>
							<label><spring:message code="Label.URL" htmlEscape="true"></spring:message>
							<%=request.getScheme() + "://" + request.getServerName()  + request.getContextPath()%> </label>
						</li>
						<li>
							<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");%>
						    <label><spring:message code="Label.REPORTPRINTED" htmlEscape="true"></spring:message>								
				 			<%=df.format(new java.util.Date())%>  </label>
						</li>
						<li>
							<label><spring:message code="Label.LGDRPTFOOTERFORLBRPT" htmlEscape="true"></spring:message></label>
						</li>
					</ul>
				</c:if>
				
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
							<img src="<%=request.getContextPath()%>/images/ajax-loader-2.gif" alt="Loading..." />
						</div>
				</div>
						
			</div> <!-- Printable Div ends here -->
						
				<c:if test="${! empty consolidateLBList}">
					<div class="buttons center" id="showbutton">
						<input type="button" name="Submit3"	class="btn"	value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
								onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" />
					</div>
				</c:if>
		
	</form:form>
	</div>
	</div>
	</div>
	</div>
</body>
</html>
