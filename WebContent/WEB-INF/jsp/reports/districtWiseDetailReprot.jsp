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
<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrDistrictService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>

<script type="text/javascript" language="Javascript">
	dwr.engine.setActiveReverseAjax(true);
	
	/*Function called on the captcha getreport button*/
function getData() 
{
		var inSelectState	=	document.getElementById("stateList").value;
		var inSelectDist	=	document.getElementById("districtList").value;
		/* State not selected */
		if(inSelectState==null ||inSelectState==""){
			document.getElementById("errProSelect").innerHTML = "<spring:message code="Error.STATE"/>"; 
		    document.getElementById("errProSelect").focus();
		    return false;
			}else{
			document.getElementById("errProSelect").innerHTML= "";
		}
		if(inSelectDist==null ||inSelectDist==""||inSelectDist=="0"){
			document.getElementById("errProSelectDist").innerHTML = "<spring:message code="error.select.DISTRICTFRMLIST"/>"; 
		    document.getElementById("errProSelectDist").focus();
		    return false;
			}else{
			document.getElementById("errProSelectDist").innerHTML= "";
		}
		
		var errorCaptcha = document.getElementById('errorCaptcha');
		errorCaptcha.innerHTML = "";
		var sessionId = document.getElementById('sessionId').value;
		var capchaImgVal = document.getElementById('captchaAnswer').value;
		/* Empty Captcha Check */
		if(capchaImgVal==null || capchaImgVal==""){
				document.getElementById("errEmptyCaptcha").innerHTML = "<spring:message code="Error.EmptyCaptcha"/>"; 
			    document.getElementById("errEmptyCaptcha").focus();
			    return false;
			}else{
				document.getElementById("errEmptyCaptcha").innerHTML= "";
		}
						
		document.getElementById('form1').method = "post";
		document.getElementById('form1').action = "districtWiseDetailReport.do?<csrf:token uri='districtWiseDetailReport.do'/>";
		document.getElementById('form1').submit();
		return true;
}
/*DWR code for the retreival of the District List in the combo box on state selection*/
function getDistrictList(stateCode){   
	//alert("Inside getDistrict Method");
	lgdDwrDistrictService.getDistrictList(stateCode, {
	callback : handleDistrictSuccess,
	errorHandler : handleDistrictError
	});
}
//data contains the returned value
function handleDistrictSuccess(data) {
	// Assigns data to result id	
	var fieldId = 'districtList';
	var valueText = 'districtCode';
	var nameText = 'districtNameEnglish';
	dwr.util.removeAllOptions(fieldId);
	var st = document.getElementById(fieldId);
	st.add(new Option('Select District', '0'));
	dwr.util.addOptions(fieldId, data, valueText, nameText);
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

/*print the Report */
function CallPrint()
{ 
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("printable"); 
	//document.getElementById('btn1').style.visibility = 'hidden';
	var Win4Print = window.open('','','left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0'); 
	//alert("contect"+printContent.innerHTML);
	Win4Print.document.write(printContent.innerHTML); 
	Win4Print.document.close(); 
	Win4Print.focus(); 
	Win4Print.print(); 
	Win4Print.close(); 
	document.getElementById('footer').style.display = 'none';
	document.getElementById('footer').style.visibility = 'hidden';
}

/* Set Table color base on row is even or odd */
$(document).ready(function(){
	$("#detailReport tr:even").css("background-color", "#dedede");
	$("#detailReport tr:odd").css("background-color", "#ffffff");
});
 
$(document).ready(function() {
	document.getElementById("ddSourceState").selectedIndex = 0;
	
});	

</script>
</head>
<body>
<div id="frmcontent">
	<div class="frmhd">
		<h3 class="subtitle"><spring:message code="Label.distwisedetailreport" text="District wise Detail Report" htmlEscape="true"></spring:message></h3>
		<c:if test="${dataExist}">
			<td align="right">
				<a id="showprint" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint()" onmouseover="" alt="Print" /></a>
			</td>
		</c:if>
	</div>
		
	<div class="clear"></div>
	
	<div class="frmpnlbrdr">
		<c:if test="${empty resultList}">
		<form:form commandName="districtWiseDetail" id='form1' action="districtWiseDetailReport.do">
		<input type="hidden" name="<csrf:token-name/>"	value="<csrf:token-value uri="districtWiseDetailReport.do"/>" />
			<div class="box" id="box">
				<a class="boxclose" id="boxclose"></a>
			</div>

			
			<div id="cat">
				<div class="frmpnlbg">
					<div class="frmtxt">
						<div class="search_criteria">
							<div class="block">
								<ul class="blocklist">
									<li>
										<label for="stateList"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
											<form:select path="selectStateCode" class="combofield"
														 style="width: 175px" id="stateList"
														 onchange="getDistrictList(this.value);setStateName(this);"
											             onfocus="validateOnFocus('stateList');helpMessage(this,'ddSourceState_msg');"
														  onblur="vlidateOnblur('stateList','1','15','c');hideHelp();"
														 onkeyup="hideMessageOnKeyPress('stateList')">
														<form:option value="">
															<spring:message htmlEscape="true" code="Label.SEL"></spring:message>
														</form:option>
														<form:options items="${stateList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"/>
											</form:select>
											<div id="errProSelect" class="errormsg"></div>
									</li>
									<li>
										<label for="districtList"><spring:message htmlEscape="true" code="Label.SELECTDISTRICT"></spring:message></label>
										<form:select path="selectDistrictCode" 
													class="combofield"
													style="width: 175px" 
													id="districtList"
													onfocus="validateOnFocus('districtList');helpMessage(this,'ddSourceDistrict_msg');"
													onblur="vlidateOnblur('districtList','1','15','c');hideHelp();"
													onkeyup="hideMessageOnKeyPress('districtList')">
														
														<br />
										</form:select>
										<div id="errProSelectDist" class="errormsg"></div>
									</li>
								</ul>
							</div>
							<div class="block">
								<%@ include
											file="../common/captcha_integration.jspf"%>
								<div class="errormsg">
									<c:if test="${ captchaSuccess2 == false }">
									<spring:message code="captcha.errorMessage" htmlEscape="true" />
									</c:if>
								</div>
								<div class="buttons">
									<input 	type="button"
												value="<spring:message code="Label.GETREPORT" htmlEscape="true"></spring:message>"
												onclick="getData();" />
										<input 	type="button"
												name="Submit3" class="btn"
												value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>"
												onclick="javascript:location.href='welcome.do?<csrf:token uri='welcome.do'/>';" />		
								</div>
							</div>
							<div class="clear"></div>
						</div>
					
		 </div>
	  </div>
    </div>
   
	<input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
   </form:form>
   </c:if>
   
   <c:if test="${dataExist}">
	   <div id="printable">
	   	<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
	   		<c:out value="${message}" escapeXml="true"></c:out>
	   	</h6>
	   	
	   	<table width="100%" cellpadding="0" cellspacing="0" border="0" id="detailReport">
								<thead>
									<tr>
										<th width="4%"><spring:message code="Label.SNO"/></th>
										<th width="5%"><spring:message code="Label.DISTRICTCODE" /></th>
										<th width="11%"><spring:message code="Label.DISTRICTNAMEINENGLISH"/></th>
										<th width="5%"><spring:message code="Label.SUBDISTRICTCODE"/></th>
										<th width="11%"><spring:message code="Label.SUBDISTRICTNAMEENGLISH"/></th>
										<th width="5%"><spring:message code="Label.VILLAGECODE"/></th>
										<th width="11%"><spring:message code="Label.VILLAGENAMEINENGLISH"/></th>
										<th width="5%"><spring:message code="label.BLOCKPANCHAYATCODE" text="Block Panchayat Code"/></th>
										<th width="11%"><spring:message code="Label.BLOCKPANCHAYATNAME" text="Block Panchayat Name(In English)"/></th>
										<th width="5%"><spring:message code="label.GRAMPANCHAYATCODE" text="Gram Panchayat Code"/></th>
										<th width="11%"><spring:message code="Label.GRAMPANCHAYATNAME" text="Gram Panchayat Name(In English)"/></th>
										<th width="5%"><spring:message htmlEscape="true" code="Label.BLOCKCODE"/></th>
										<th width="11%"><spring:message htmlEscape="true" code="Label.BLOCKNAMEINENGLISH"/></th>
										
									</tr>
								</thead>
								<tbody>
									<c:forEach var="obj" items="${resultList}" varStatus="sno">
										<tr>
											<td width="4%" align="center"><esapi:encodeForHTMLAttribute>${sno.count}</esapi:encodeForHTMLAttribute></td>
											<td width="5%" align="center"><esapi:encodeForHTMLAttribute>${obj.districtCode}</esapi:encodeForHTMLAttribute></td>
											<td width="11%"><esapi:encodeForHTMLAttribute>${obj.districtNameEnglish}</esapi:encodeForHTMLAttribute></td>
											<td width="5%" align="center"><esapi:encodeForHTMLAttribute>${obj.subDistrictCode}</esapi:encodeForHTMLAttribute></td>
											<td width="11%"><esapi:encodeForHTMLAttribute>${obj.subDistrictNameEnglish}</esapi:encodeForHTMLAttribute></td>
											<td width="5%" align="center"><esapi:encodeForHTMLAttribute>${obj.villageCode}</esapi:encodeForHTMLAttribute></td>
											<td width="11%"><esapi:encodeForHTMLAttribute>${obj.villageNameEnglish}</esapi:encodeForHTMLAttribute></td>
											<td width="5%" align="center"><esapi:encodeForHTMLAttribute>${obj.bpCode}</esapi:encodeForHTMLAttribute></td>
											<td width="11%"><esapi:encodeForHTMLAttribute>${obj.bpNameEnglish}</esapi:encodeForHTMLAttribute></td>
											<td width="5%" align="center"><esapi:encodeForHTMLAttribute>${obj.gpCode}</esapi:encodeForHTMLAttribute></td>
											<td width="11%"><esapi:encodeForHTMLAttribute>${obj.gpNameEnglish}</esapi:encodeForHTMLAttribute></td>
											<td width="5%" align="center"><esapi:encodeForHTMLAttribute>${obj.blockCode}</esapi:encodeForHTMLAttribute></td>
											<td width="11%"><esapi:encodeForHTMLAttribute>${obj.blockNameEnglish}</esapi:encodeForHTMLAttribute></td>
										</tr>
									</c:forEach>
								</tbody>
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
		</div> <!-- Printable Div ends here -->
	
	
	<div class="buttons center">
		<input type="button" value="<spring:message htmlEscape="true"  code="Button.CLOSE"/>" onclick="javascript:go('welcome.do');"/>
		<input 	type="button" value="<spring:message htmlEscape="true"  code="Button.BACK"/>" onclick="javascript:location.href='districtWiseDetailReport.do?<csrf:token uri='districtWiseDetailReport.do'/>';" /> 
	</div>
	</c:if>
	<c:if test="${dataExist==false}">
		<div class="errormsg"><spring:message htmlEscape="true"  code="Label.NORECORDFOUND" text="No Record Found on your selection"/></div>
	</c:if>
   </div>
  </div>

</body>
</html>