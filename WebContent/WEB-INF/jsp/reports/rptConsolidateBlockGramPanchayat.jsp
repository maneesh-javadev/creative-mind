<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%-- <% java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy"); %> --%>
<%!String contextPath;
	String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>E-Panchayat</title>
<script src="js/shiftsubdistrict.js"></script>
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript'
	src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>

<script type="text/javascript" language="Javascript">
	dwr.engine.setActiveReverseAjax(true);
	
/*DWR code for the retreival of the District List in the combo box on state selection*/
function getDistrict(stateCode)
{   
	lgdDwrDistrictService.getDistrictList(stateCode, {
	callback : handleDistrictSuccess,
	errorHandler : handleDistrictError
	});
}

//data contains the returned value
function handleDistrictSuccess(data) {
	var fieldId = 'ddSourceDistrict';
	dwr.util.removeAllOptions(fieldId);
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';
	var st = document.getElementById(fieldId);
	st.add(new Option("<spring:message code="Label.SEL"/>", ''));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
	
	if('${districtId}' != null)
		document.getElementById('ddSourceDistrict').value = '<c:out value="${districtId}" escapeXml="true"></c:out>';
}

function handleDistrictError() {
	// Show a popup message
	test="${fn:length(districtList)}";
	alert("No data found!");
}

/*DWR code ends*/
function noenter(e) {
    e = e || window.event;
    var key = e.keyCode || e.charCode;
    return key !== 13; 
}



function getData() {
	/* modified by pooja on 03-07-2015 to display error message in right place */
	document.getElementById("errSelectState").innerHTML = "";
	document.getElementById("errSelectDistrict").innerHTML = "";
	document.getElementById("errSelectCaptcha").innerHTML = "";
	var stateobj = document.getElementById("ddSourceState");
	var disttobj = document.getElementById("ddSourceDistrict");
	
	var inSelectState =	stateobj.value;
	if(inSelectState == null || inSelectState == ""){
		document.getElementById("errSelectState").innerHTML = "<font color='red'><br><b><spring:message code="Error.STATE"/></b></font>"; 
		return false;
	}
	
	var inSelectDist = disttobj.value;
	if(inSelectDist == null || inSelectDist == ""){
		document.getElementById("errSelectDistrict").innerHTML = "<font color='red'><br><b><spring:message code="error.select.DISTRICTFRMLIST"/></b></font>";  
		return false;
	}
	
	var capchaImgVal = document.getElementById('captchaAnswer').value;
	/* Empty Captcha Check */
	if(capchaImgVal == null || capchaImgVal == ""){
		document.getElementById("errSelectCaptcha").innerHTML = "<font color='red'><br><b><spring:message code="Error.EmptyCaptcha"/></b></font>"; 
	    return false;
	}
	
	document.getElementById('form1').statename.value = stateobj.options[stateobj.selectedIndex].text;
	document.getElementById('form1').disttname.value = disttobj.options[disttobj.selectedIndex].text;
	document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "rptConsolidateBlockGramPanchayat.do?<csrf:token uri='rptConsolidateBlockGramPanchayat.do'/>";
	document.getElementById('form1').submit();
	return true;
}

function PrintDiv() {

	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';	
	var printContent = document.getElementById("printble");
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

<body>
	<div id="frmcontent">
		<div class="frmhd">
			<h3 class="subtitle">
				<spring:message code="Label.ConsolidatedReportBlockWise"
					htmlEscape="true"></spring:message>
			</h3>
			<c:if test="${empty reportList}">
				<ul class="listing">
					<!-- <li><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" alt="Toggle" />
					</a>
					</li> -->
					<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message
								htmlEscape="true" code="Label.CBT"></spring:message>
					</a>
					</li>
					<li><a href="#" class="frmhelp"><spring:message
								htmlEscape="true" code="Label.HELP"></spring:message>
					</a>
					</li> --%>
				</ul>
			</c:if>
			<c:if test="${!empty reportList}">
				<a id="showprint" href="#"><img
					src='<%=contextPath%>/images/printer_icon.png' border="0"
					onclick="PrintDiv();" alt="Print" />
				</a>
			</c:if>
		</div>

		<div class="clear"></div>

		<div class="frmpnlbrdr">			
				<form:form commandName="consolidateReport" id='form1'
					action="rptConsolidateBlockGramPanchayat.do">
					<input type="hidden" id="statename" name="statename" />
					<input type="hidden" id="disttname" name="disttname" />

					<c:choose>
						<c:when test="${empty reportList}">
							<div id="cat">
								<div class="search_criteria">
									<div class="block">
										<ul class="blocklist">
											<li><label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message> </label>
												<div id="errSelectState" class="errormsg"><c:out value="${saveMsg}" escapeXml="true"></c:out></div>
												<form:select path="state_name_english" class="combofield" style="width: 175px" id="ddSourceState" onchange="getDistrict(this.value);" onfocus="validateOnFocus('ddSourceState');helpMessage(this,'ddSourceState_msg');"
												onblur="vlidateOnblur('ddSourceState','1','15','c');hideHelp();" onkeyup="hideMessageOnKeyPress('ddSourceState')">
												<form:option value="">
													<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
												</form:option>
												<form:options items="${statelist}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
											</form:select></li>
											<li><label for="ddSourceDistrict"><spring:message
														code="Label.SELECTDISTRICT" htmlEscape="true"></spring:message>
											</label>
											<div id="errSelectDistrict" class="errormsg"><c:out value="${saveMsg}" escapeXml="true"></c:out></div> 
											<form:select path="districtPName" class="combofield"
													style="width: 175px" id="ddSourceDistrict"
													onfocus="validateOnFocus('ddSourceDistrict');helpMessage(this,'ddSourceDistrict_msg');"
													onblur="vlidateOnblur('ddSourceDistrict','1','15','c');hideHelp();"
													onkeyup="hideMessageOnKeyPress('ddSourceDistrict')">
													<form:option value="">
														<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
													</form:option>
													<form:options items="${districtList}"
														itemValue="districtPK.districtCode"
														itemLabel="districtNameEnglish"></form:options>
												</form:select></li>
										</ul>
									</div>

									<div class="block">
										<ul class="captcha_fields">
											<li><img src="captchaImage" alt="Captcha Image" border="0" />
											</li>
											<li><form:input path="captchaAnswer"
													name="captchaAnswer" class="frmfield" autocomplete="off"
													onkeypress="return noenter(event)" />
												<div id="errSelectCaptcha" class="errormsg"><c:out value="${saveMsg}" escapeXml="true"></c:out></div>
												<form:errors path="captchaAnswer" cssClass="errormsg" /></li>
											<li><input type="button"
												value="<spring:message code="Label.GETREPORT" htmlEscape="true"></spring:message>"
												onclick="getData();" /> <input type="button" name="Submit3"
												class="btn"
												value="<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message>"
												onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" />
											</li>
										</ul>
									</div>

									<div class="clear"></div>
								</div>
							</div>
						</c:when>

						<c:otherwise>

							<div id="printble">
								<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;"><c:out value="${statusMessage}" escapeXml="true"></c:out></h6>
								<table class="tbl_with_brdr" width="100%" align="center">
									<tr class="tblRowTitle tblclear">
										<td width="5%"><spring:message code="Label.SNO" />
										</td>
										<td><spring:message code="Label.DISTRICTCODE"
												text="District Code" />
										</td>
										<td><spring:message code="Label.DISTRICTNAMEINENGLISH"
												text="District Name" />
										</td>
										<td><spring:message code="Label.BLOCKCODE"
												text="Block Code" />
										</td>
										<td><spring:message code="Label.BLOCKNAMEINENGLISH"
												text="Block Name" />
										</td>
										<td><c:out value="${villPanchyatHeading}" escapeXml="true"></c:out>&nbsp;<spring:message
												code="Label.CODE" text="Code" />
										</td>
										<td><c:out value="${villPanchyatHeading}" escapeXml="true"></c:out>&nbsp;<spring:message
												code="Label.NAME" text="Name" />
										</td>
										<td><spring:message code="Label.VILLAGECODE"
												text="Village code" />
										</td>
										<td><spring:message code="Label.VILLAGENAMEINENGLISH"
												text="Village Name" />
										</td>
									</tr>

									<c:forEach var="listBlockDetails" varStatus="blockRow"
										items="${reportList}">
										<tr class="tblRowB">
											<td align="center"><c:out value="${blockRow.count}" escapeXml="true"></c:out></td>
											<td><c:out value="${listBlockDetails[0]}" escapeXml="true"></c:out>
											</td>
											<td><c:out value="${listBlockDetails[1]}" escapeXml="true"></c:out>
											</td>
											<td><c:out value="${listBlockDetails[2]}" escapeXml="true"></c:out>
											</td>
											<td><c:out value="${listBlockDetails[3]}" escapeXml="true"></c:out>
											</td>
											<td><c:out value="${listBlockDetails[4]}" escapeXml="true"></c:out>
											</td>
											<td><c:out value="${listBlockDetails[5]}" escapeXml="true"></c:out>
											</td>
											<td><c:out value="${listBlockDetails[6]}" escapeXml="true"></c:out>
											</td>
											<td><c:out value="${listBlockDetails[7]}" escapeXml="true"></c:out>
											</td>
										</tr>
									</c:forEach>

								</table>

								<c:if test="${! empty reportList}">
									<ul class="blocklist center" style="text-align: center; list-style: none;">	<!-- Inline style only for Print purpose -->
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
									<div id="displayBox" style="text-align: center; display: none;">
										<img
											src="<%=request.getContextPath()%>/images/ajax-loader-2.gif" alt="Loading..." />
									</div>

								</div>
							</div> <!-- Printable Div ends here -->
							

							<div class="buttons center">
								<input type="button" name="back" class="btn"
									value="<spring:message htmlEscape="true"  code="Button.BACK"></spring:message>"
									onclick="javascript:location.href='rptConsolidateBlockGramPanchayat.do?<csrf:token uri='rptConsolidateBlockGramPanchayat.do'/>';" />
								<input type="button" name="Submit3" class="btn"
									value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
									onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" />
							</div>
						</c:otherwise>
					</c:choose>



				</form:form>

				<c:if test="${captchaError}">
					<script>
			getDistrict('${stateId}');
			
		</script>
				</c:if>
			
		</div>
	</div>
</body>
</html>