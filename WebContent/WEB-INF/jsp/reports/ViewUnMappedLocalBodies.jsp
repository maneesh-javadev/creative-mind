<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<%@include file="../common/taglib_includes.jsp"%>

<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>


<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrlocalBodyService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/call/plaincall/lgdDwrlocalBodyService.getchildlocalbodiesByParentcode.dwr'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDesignationService.js'></script>
<%@include file="../common/dwr.jsp"%>
<script language="javascript">
function getLocalBodyTypeListByStateCode(stateCode)
{
	
	lgdDwrDesignationService.getLocalbodyDetail(stateCode, {
		callback : handleLocalbodySuccess,
		errorHandler : handleLocalbodyError
	});

}

function handleLocalbodySuccess(data) {
	// Assigns data to result id
	// alert("ti");
	// alert(data);
	var fieldId = 'lbTypeId';
	// alert(data);
	var valueText = 'nomenclatureEnglish';
	var nameText = 'localBodyTypeName';

	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById(fieldId);
	st.add(new Option('Select Local Body', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);

}

function handleLocalbodyError() {
	// Show a popup message
	alert("No data found in local body!");
}

function validatelbt() {
	var error = false;
	var inSelectState = document.getElementById("ddSourceState").value;
	var inSelectlbtype = document.getElementById("lbTypeId").value;

	if (inSelectState != 0 && inSelectState != "") {

		if (inSelectlbtype == 0 || inSelectlbtype == "") {
			error = true;
			$("#error_selectlbt").show();

		}
	} else {

		$("#error_selectstate").show();
		error = true;
	}

	if (error == true) {
		return false;
	} else {
		return true;
	}

}

function hideError() {
	$("#error_selectlbt").hide();
	$("#error_selectstate").hide();
	}
	
function loadMe() {
		hideError();		
	}
if ( window.addEventListener ) { 
    window.addEventListener( "load",loadMe, false );
 }
 else 
    if ( window.attachEvent ) { 
       window.attachEvent( "onload", loadMe );
 } else 
       if ( window.onLoad ) {
          window.onload = loadMe;
 }

</script>
</head>
<body>

	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle"><spring:message code="Label.viewUnmappedlocalbodies" htmlEscape="true"></spring:message></h3>
			<ul class="listing">
				<!-- <li id="showhelp"><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" alt="Toggle"	border="0" /></a></li> -->
				<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
				<li><a href="#" class="frmhelp"><spring:message	htmlEscape="true" code="Label.HELP"></spring:message> </a></li> --%>
			</ul>
			<a id="showprint" href="#" style="visibility: hidden; display: none;"><img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint()" onmouseover="" alt="Print" /></a>
			
		</div>
		
		<div class="clear"></div>
		
		<div class="frmpnlbrdr">
		
			<form:form action="rptViewUnmappedLocalBodies.do" method="POST" commandName="rptViewUnMappedLocalBodies" id="form1" name="form1">
		 <input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="createWardforPRI.htm"/>" /> 
  			

			<div id="cat">
			<c:if test="${empty viewUnMappedLBlist}">
				<div class="frmpnlbg" id="viewentity">
						<div class="frmtxt">
							<div class="frmhdtitle">
							<spring:message code="Label.SELECTCRITERIA" htmlEscape="true"></spring:message>
							</div>
							
							<div class="search_criteria">
								<div class="block">
									<ul class="blocklist">
										<li>
											<label for="ddSourceState"><spring:message code="Label.SELECTSTATE" htmlEscape="true"></spring:message></label>
											<form:select path="stateNameEnglish" class="combofield" style="width: 175px" id="ddSourceState" onchange="getLocalBodyTypeListByStateCode(this.value);hideError();">
											<form:option value=""> <spring:message code="Label.SELECT" htmlEscape="true"></spring:message> </form:option>
											<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
											</form:select>
											<div class="errormsg" id="error_selectstate">
											<spring:message htmlEscape="true" code="error.select.SELECTSTATENAME"></spring:message>
											</div>								
										</li>
										<li>
											<label for="lbTypeId"><spring:message code="Label.SELECTLOCALBODYTYPE" htmlEscape="true"></spring:message></label>												 											
												<form:select path="lgd_LBTypeName" id="lbTypeId" htmlEscape="true" class="combofield" style="width: 175px" onchange ="hideError();">
													<form:option selected="selected" value="" label="--select--" />
												</form:select>
												<div class="errormsg" id="error_selectlbt">
												<spring:message htmlEscape="true" code="error.select.LGOVTTYPE"></spring:message>
											</div>											
										</li>
										<li><input type="hidden" name="stateCode" value="<c:out value='${stateCode}' escapeXml='true'></c:out>" /><input type="hidden" value="<c:out value='${lbType}' escapeXml='true'></c:out>"/></li>
									</ul>
								</div>
								
								<div class="block">
									<ul class="captcha_fields">
										<li><img src="captchaImage" alt="Captcha Image" /></li>
										<li>
											<label><spring:message code="captcha.message" htmlEscape="true" /></label><br /> 
											<form:input path="captchaAnswer" name="captchaAnswer" id="captchaAnswer" />									
												<div class="errormsg">
													<c:if test="${ captchaSuccess1 == false }"><spring:message code="captcha.errorMessage" htmlEscape="true" /></c:if>
													<form:errors path="captchaAnswer" />
												</div>
											<div id="errorCaptcha" class="errormsg" style="visibility: hidden; display: none;"><spring:message code="captcha.errorMessage"/></div>
										</li>
										<li><c:if test = "${flag2 eq 1}"><span id="required" class="errormsg"><spring:message code="error.norecoredfound" htmlEscape="true"></spring:message></span></c:if></li>
										<li>
											<input type="submit" name="Submit" class="btn"  value=<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message> onclick="return validatelbt();"/>										
											<input type="button" name="Submit3" class="btn" value=<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>  onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" />
										</li>
									</ul>
								</div>
								
								<div class="clear"></div>	
									
							</div>
						</div>
					</div>				
			</c:if>
							
				
			<c:if test="${! empty viewUnMappedLBlist}">
					<div class="frmpnlbg" id="divData" >
						<div class="frmtxt">
							<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
								<spring:message htmlEscape="true" code="Label.viewUnmappedlocalbodies"> of <c:out value="${stateNameEng}" escapeXml="true"></c:out> </spring:message> 
							</h6>
							
							<table width="100%" class="tbl_no_brdr">

								<tr>
									<td width="14%" align="center">
										<table class="tbl_with_brdr" width="98%" align="center">
											<tr class="tblRowTitle tblclear">
												<td width="8%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.SNO"></spring:message>
												</td>
												<td width="18%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message>
												</td>
												<td width="23%" rowspan="2"><spring:message
														htmlEscape="true" code="Label.LOCALBODYNAMEINENG"></spring:message>
												</td>
												<td width="23%"  rowspan="2" align="center"><spring:message
														htmlEscape="true" code="Label.LOCALBODYTYPENAME"></spring:message>
												</td>
												<td width="23%"  rowspan="2" align="center"><spring:message
														htmlEscape="true" code="Label.PARENTLOCALBODYNAME"></spring:message>
												</td>
											</tr>
											<tr></tr>
											<c:forEach var="listLocalBodyDetails" varStatus="listLocalBodyRow" items="${viewUnMappedLBlist}">
												<tr class="tblRowB">
													<td><c:out value="${listLocalBodyRow.index+1}" escapeXml="true"></c:out></td>
													<td><c:out value="${listLocalBodyDetails.localBodyCode}" escapeXml="true"></c:out></td>
													<td><c:out value="${listLocalBodyDetails.localBodyNameEnglish}" escapeXml="true"></c:out></td>
													<td> <c:out value="${listLocalBodyDetails.localBodyTypeName}" escapeXml="true"></c:out></td>
													<td> <c:out value="${listLocalBodyDetails.parentname}" escapeXml="true"></c:out></td>
												</tr>
											</c:forEach>
											<tr><td align="right"></td></tr>
										</table>
								</td>
								</tr>	
					</table>
						</div>
						
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
							<div id="displayBox" style="text-align: center;display:none;"><img alt="Loading..."  src="<%= request.getContextPath() %>/images/ajax-loader-2.gif"  /></div>
					   </div> 
					   
						</div> 
						<!-- added by sunita on 07-7-2015  -->
						<div style="text-align: center">
							<input type="button" name="back" class="btn" value="<spring:message htmlEscape="true"  code="Button.BACK"></spring:message>" onclick="javascript:location.href='rptViewUnmappedLocalBodies.do?<csrf:token uri='rptViewUnmappedLocalBodies.do'/>';" />
							<input type="button" name="Submit3" class="btn" value=<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message> onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" />
						</div>
						
					</c:if>
			</form:form>
		</div>
	</div>


</body>
</html>