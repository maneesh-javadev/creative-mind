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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>E-Panchayat</title>
<script type="text/javascript" src="js/common.js"></script>



<script type="text/javascript" language="Javascript">

function getData() 
{
		var errorCaptcha = document.getElementById('errorCaptcha');
		errorCaptcha.innerHTML = "";
		var sessionId = document.getElementById('sessionId').value;
		var capchaImgVal = document.getElementById('captchaAnswer').value;
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "rptStatePanchayats.do?<csrf:token uri='rptStatePanchayats.do'/>";
		document.getElementById('form1').submit();
		return true;
}

function PrintDiv() {


	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';	
	var printContent = document.getElementById("printble2");
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


$(document).ready(function() {

	$("#tbl_with_brdr tr:even").css("background-color", "#dedede");

	$("#tbl_with_brdr tr:odd").css("background-color", "#ffffff");

});
</script>
</head>
<body>
<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle">
				Report on State Panchayats
			</h3>
			<ul class="listing">
				<li>
					<c:if
						test="${not empty reportList}">						
						<a id="showprint" href="#"><img	src='<%=contextPath%>/images/printer_icon.png' border="0" alt="Print" onclick="PrintDiv();" /></a>				
					</c:if>
				</li>
			</ul>
		</div>
	<form:form commandName="reportStatePanchayat" id='form1' action="rptStatePanchayats.do">
		<input type="hidden" name="<csrf:token-name/>"
			value="<csrf:token-value uri="rptStatePanchayat.do"/>" />
		<div class="frmpnlbrdr">
			
			<div class="frmpnlbg">
				<c:if test="${empty reportList}">
					<ul class="captcha_fields viewstates">						
						<li><%@ include	file="../common/captcha_integration.jspf"%></li>
						<li>
							<input type="button" value="<spring:message code="Label.GETREPORT" htmlEscape="true"></spring:message>" onclick="getData();" />
							<input type="button" name="Submit3" class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
													onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" />
						</li>
					</ul>
				</c:if>
			</div>
		
			<div id="printble2">
			<!-- modified by pooja on 03-07-2015 to display heading msg only when list come -->
			<table class="tbl_no_brdr" width="100%">
			<input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
			<c:if test="${!empty reportList}">
			<tr><td align="center"><h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;"><spring:message
											htmlEscape="true" code="Label.StatePanchayatReportSetup">
										</spring:message></h6></td></tr>
				<tr>
					<td align="left" width="100%"><label>*N.A.- Not
							Applicable</label></td>
				</tr>
				<tr>
					<td align="center" width="100%">
						<table class="tbl_with_brdr" width="98%" align="center" id="tbl_with_brdr">



							<tr class="tblRowTitle tblclear">
								<td width="5%"><spring:message code="Label.SNO"></spring:message>
								</td>
								<td width="16%"><spring:message code="Label.STATENAME"></spring:message>
								</td>
								<td width="10%"><spring:message
										code="Label.DISTRICTPANCHAYATNAME"></spring:message>
								</td>
								<td width="21%"><spring:message
										code="Label.INTERMEDIATEPANCHAYATNAME"></spring:message>
								</td>
								<td width="21%"><spring:message
										code="Label.VILLAGEPANCHAYATNAME"></spring:message>
								</td>
							</tr>
							<!-- Getting Values in the form of the Table -->
							<c:forEach var="listStatePanchayatDetails"
								varStatus="listStatePanchayatRow" items="${reportList}">
								<tr class="tblRowB">
									<td><c:out value="${listStatePanchayatRow.index+1}" escapeXml="true"></c:out></td>
									<td><c:out value="${listStatePanchayatDetails.stateNameEnglish}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${listStatePanchayatDetails.dp}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${listStatePanchayatDetails.ip}" escapeXml="true"></c:out></td>
									<td align="left"><c:out value="${listStatePanchayatDetails.vp}" escapeXml="true"></c:out></td>

								</tr>
							</c:forEach>
						</table></td>
				</tr>
			</c:if>
		</table>
		
		<c:if test="${! empty reportList}">
		
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
	            <div id="displayBox" style="text-align: center;display:none;"><img  src="<%= request.getContextPath() %>/images/ajax-loader-2.gif" alt="Loading..."  /></div>
  
  			</div>
			</div> <!-- Printable Div ends here -->
			
			<c:if test="${! empty reportList}">
				<div class="buttons center">
					<input type="button" name="Submit3"	class="btn" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
					onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" />
				</div>
		   </c:if>
		   
		 
		<!-- Alert on getting empty village list -->
		<c:if test="${empty reportList}">
			<c:if test="${saveMsg != null}">
				<div class="center">
					<spring:message htmlEscape="true" code="error.NOVILLFOUND"></spring:message>
					<script>alert("<c:out value="${saveMsg}"/>");</script>
				</div>			
			</c:if>
		</c:if>
		</div>
	</form:form>		
	
</div>
</body>
</html>