<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<script type='text/javascript'  src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrParlimentService.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrAssemblyService.js'></script>
<script src="js/rptMappedGPNWardforPCAC.js"></script>
<script src="js/common.js"></script>
<style type="text/css">
table {
    width: 100%;
    display:block;
}
thead {
    display: inline-block;
    width: 100%;
    height: 20px;
}
tbody {
    height: 100%;
    display: inline-block;
    width: 100%;
    overflow: auto;
}
</style>
<script language="javascript">
function CallPrint() {
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	document.getElementById('footertext').style.fontSize = '13px';
	var printContent = document.getElementById("divData");
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
}

function hideError() {
	$("#error_selectac").hide();
	$("#error_selectpc").hide();
	$("#error_selectstate").hide();
	
	$("#error_selectpc1").hide();
	$("#error_selectstate1").hide();
	}
function loadMe() {
		var pcobj='<c:out value="${rptMappedGPNWardforPCACForm.pcCheck}" escapeXml="true"></c:out>';
		//alert(pcobj);
		hideError();
		if(pcobj=="true") {
			document.getElementById('showpc').style.visibility = 'visible';
			document.getElementById('showpc').style.display = 'block';
			document.getElementById('showac').style.visibility = 'hidden';
			document.getElementById('showac').style.display = 'none';
			document.getElementById('selectPC').checked=true;
			}
		else if(pcobj=="false") {
			document.getElementById('showac').style.visibility = 'visible';
			document.getElementById('showac').style.display = 'block';
			document.getElementById('showpc').style.visibility = 'hidden';
			document.getElementById('showpc').style.display = 'none';
			document.getElementById('selectAC').checked=true;
			}
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
			<h3 class="subtitle"><spring:message htmlEscape="true" code="Label.mappedLBWARDPCAC"></spring:message></h3>
			<c:if test="${empty pcACMappingforLBWardList}"> 
				<ul class="listing">
					<!-- <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"> <img src="images/plus.jpg" border="0" /></a></li> -->
					<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
					<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
				</ul>
			</c:if>
			
			<c:if test="${!empty pcACMappingforLBWardList}">			
				<a id="showprint" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" onclick="CallPrint();" /></a> </td>
			</c:if>
			
		</div>
			
		<div class="clear"></div>
		
		<div class="frmpnlbrdr">
		<form:form action="rptMappedGPNWardforPCAC.do" id="form1" name="form1" method="POST" commandName="rptMappedGPNWardforPCACForm">
			<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri='rptMappedGPNWardforPCAC.do'/>" />
			<form:input type="hidden" path="pcCheck" id="pcCheck" />
			<input type="hidden" name="flag1" id="flag1" value="<c:out value='${flag1}' escapeXml='true'></c:out>"/>
			<c:if test="${empty pcACMappingforLBWardList}">		
			<div class="frmpnlbg">
			
					        	
						<div class="frmtxt">
							<div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.mappedLBWARDPCAC"></spring:message></div>
							
							<ul class="listing">
								<li>
									<label for="selectPC">
										<input type="radio" name="correctionRadio" id='selectPC' onclick='toggledisplay("selectPC","selectAC");' />
										<spring:message htmlEscape="true" code="Label.SEARCHFORPARILAMENT" text="Search for Parliament"></spring:message>
									</label>
								</li>
								<li>
									<label for="selectAC">
										<input type="radio" name="correctionRadio" id='selectAC' onclick='toggledisplay("selectPC","selectAC");' />
										<spring:message htmlEscape="true" code="Label.SEARCHFORASSEMBLY" text="Search for Assembly"></spring:message>
									</label>
								</li>
								<li>
									<label>
										<input type="radio" style="display: none" name="correctionRadio" checked>
									</label>
								</li>
							</ul>							
						</div>
			
			</div>
				
			<div class="frmpnlbg" id='showpc' style="display: none">
			<div class="frmtxt">
				<div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message></div>
				<div class="search_criteria">
					<div class="block">
						<ul class="blocklist">
							<li>
								<label for="ddSourceState"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
								<form:select path="stateNameEnglish" class="combofield" style="width: 175px" id="ddSourceState" onchange="getListParliment(this.value);hideError();">
									<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
									<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
								</form:select>
								<div class="errormsg" id="error_selectstate">
								<spring:message htmlEscape="true" code="error.select.SELECTSTATENAME"></spring:message>
								</div>
								 <div id="errSelectStateName" class="errormsg"></div>
							</li>
							<li>
								<label for="parliment"><spring:message code="Label.SELECTPARLIAMENTCONSTITUENCY" htmlEscape="true"></spring:message></label>
									<form:select path="parlimentNameEnglish" class="combofield" style="width: 175px" id="parliment"	onchange ="hideError();">
									<form:option value=""><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></form:option>
								</form:select>
								<div class="errormsg" id="error_selectpc"><spring:message htmlEscape="true" code="Error.PARLIAMENTSELECT"></spring:message></div>
							</li>
						</ul>
					</div>
					<div class="block">
						<ul class="captcha_fields">
							<li>
								<img src="captchaImage" alt="Captcha Image" />
							</li>
							<li>
										<label for="captchaAnswer"><spring:message code="captcha.message" htmlEscape="true" /><br /></label>
										<form:input path="captchaAnswer" name="captchaAnswer" class="frmfield" autocomplete="off" />
										<div class="errormsg">
										<c:if test="${ captchaSuccess2 == false }">
										<spring:message code="captcha.errorMessage" htmlEscape="true" />
										</c:if>
										</div>
							</li>
							<li>
								<input type="submit" name="Submit" class="btn" onclick ="return validatepc();" value='<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message>'/>
								<input type="button" name="Submit3" class="btn" value=<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message> onclick="javascript:go('welcome.do');" /> 
							</li>
							<li>
								<c:if test = "${flag3 eq 1}"><span id="required" class="errormsg">No Record Exist For this Parliment</span></c:if>
							</li>
						</ul>
					</div>
					
					<div class="clear"></div>
				</div>
			</div>
		</div>
			
			<div class="frmpnlbg" id='showac' style="visibility: hidden; display: none">
					<div class="frmtxt">
						<div class="frmhdtitle"><spring:message htmlEscape="true" code="Label.SELECTCRITERIA"></spring:message></div>
						
						<div class="search_criteria">
							<div class="block">
								<ul class="blocklist">
									<li>
										<label for="ddSourceState1"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message></label>
												<form:select htmlEscape="true" path="stateNameEnglish" class="combofield" style="width: 175px" id="ddSourceState1" onchange="getListParlimentac(this.value);hideError();" >
												<form:option value=""><spring:message htmlEscape="true" code="Label.SEL"></spring:message></form:option>
												<form:options items="${stateSourceList}" itemValue="statePK.stateCode" itemLabel="stateNameEnglish"></form:options>
												</form:select> 
											<div class="errormsg" id="error_selectstate1"><spring:message htmlEscape="true" code="error.select.SELECTSTATENAME"></spring:message></div> 
									        <div id="errSelectStateName" class="errormsg"></div>
									</li>
									<li>
										<label for="parlimentac"><spring:message code="Label.SELECTPARLIAMENTCONSTITUENCY" htmlEscape="true"></spring:message></label>
										<form:select path="parlimentNameEnglish" class="combofield" style="width: 175px" id="parlimentac" onchange="getListAssembly(this.value);hideError();">
										<form:option value=""><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></form:option>
										</form:select>
										<div class="errormsg" id="error_selectpc1"><spring:message htmlEscape="true" code="Error.PARLIAMENTSELECT"></spring:message></div>
									</li>
									<li>
										<label for="assembly"><spring:message code="Label.SELECTASSEMBLYCONSTITUENCY" htmlEscape="true"></spring:message></label>
											<form:select path="acNameEnglish" class="combofield" style="width: 175px" id="assembly" onchange="hideError();">
											<form:option value=""><spring:message code="Label.SELECT" htmlEscape="true"></spring:message></form:option>
											</form:select>
											<div class="errormsg" id="error_selectac"><spring:message htmlEscape="true" code="Error.ASSEMBLYSELECT"></spring:message></div>
									</li>
									<li><c:if test = "${flag2 eq 1}"><span id="required" class="errormsg">No Record Exist For this Assembly</span></c:if></li>
								</ul>
							</div>
							<div class="block">
								<ul class="captcha_fields">
									<li><img src="captchaImage" /></li>
									<li>
										<label for="captchaAnswers"><spring:message code="captcha.message" htmlEscape="true" /></label><br /> 
										<form:input path="captchaAnswers" name="captchaAnswers" class="frmfield" autocomplete="off" />
											<div class="errormsg">
											<c:if test="${ captchaSuccess2 == false }"><spring:message code="captcha.errorMessage" htmlEscape="true" /></c:if>
												<div id="errorCaptchas" class="errormsg"></div>
											</div>
									</li>
									<li>
										<input type="submit" name="Submit" class="btn" onclick ="return validateac();" value='<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message>'/>
									    <input type="button" name="Submit3" class="btn" value=<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message> onclick="javascript:go('welcome.do');" />
									</li>
								</ul>
							</div>
							
							<div class="clear"></div>
						</div>
				</div>
			</div>
					
			</c:if>	
			
			<c:if test="${! empty pcACMappingforLBWardList}">
			<div id="divData">        	
						        	
					<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;"><spring:message htmlEscape="true" code="Label.mappedLBWARDPCAC"></spring:message></h6>
					       
								<table width="100%" class="tbl_no_brdr" align="center">
								<tr>
								<td>
								 <div >
									<table class="tbl_with_brdr" align="center" style="table-layout:fixed;" >
									<tbody style="overflow:auto; width: 2000px; height:400px;">
									
										<tr class="tblRowTitle tblclear">
											<td width="60px" ><spring:message htmlEscape="true" code="Label.SNO"></spring:message> </td>
											<td width="150px"><spring:message htmlEscape="true" code="Label.PARLIAMENTCONSTITUENCY"></spring:message> </td>
											<td width="150px" ><spring:message htmlEscape="true" code="Label.ASSEMBLYCONSTITUENCY"></spring:message> </td>
											<td width="120px" ><spring:message htmlEscape="true" code="Label.DISTRICTCODE"></spring:message> </td>
											<td width="150px" ><spring:message htmlEscape="true" code="Label.DISTRICTNAME"></spring:message> </td>
											<td width="120px" ><spring:message htmlEscape="true" code="Label.SUBDISTRICTCODE"></spring:message> </td>
											<td width="150px" ><spring:message htmlEscape="true" code="Label.SUBDISTRICTNAME"></spring:message> </td>
											<td width="120px" ><spring:message htmlEscape="true" code="Label.VILLAGECODE"></spring:message> </td>
											<td width="150px" ><spring:message htmlEscape="true" code="Label.VILLAGENAME"></spring:message> </td>
											<td width="120px" ><spring:message htmlEscape="true" code="Label.LOCALBODYCODE"></spring:message> </td>
											<td width="180px" ><spring:message htmlEscape="true" code="Label.LOCALGOVTBODYNAME"></spring:message> </td>
											<td width="180px" ><spring:message htmlEscape="true" code="Local Body Type"></spring:message> </td>
											<td width="120px" ><spring:message htmlEscape="true" code="Label.WARDNUMBER"></spring:message> </td>
											<td width="150px" ><spring:message htmlEscape="true" code="Label.WARDNAME"></spring:message> </td>
											<td width="120px" ><spring:message htmlEscape="true" code="Label.BLOCKCODE"></spring:message> </td>
											<td width="150px" ><spring:message htmlEscape="true" code="Label.BLOCKNAME"></spring:message> </td>
										<%-- 	<td width="8%" ><spring:message htmlEscape="true" code="Label.COVEREDAREA"></spring:message> </td> --%>
	
										<tr><td></td></tr>
										<c:forEach var="pcACMappingforLBWardDetails" varStatus="pcACMappingforLBWarRow" items="${pcACMappingforLBWardList}">
										<tr class="tblRowB"> 
											<td > <c:out value="${pcACMappingforLBWarRow.index+1}" escapeXml="true"></c:out></td> 
											<td ><c:out value="${pcACMappingforLBWardDetails.parliamentConstituency}" escapeXml="true"></c:out></td>
											<td><c:out value="${pcACMappingforLBWardDetails.assemblyConstituency}" escapeXml="true"></c:out></td>
											<td><c:out value="${pcACMappingforLBWardDetails.districtCode}" escapeXml="true"></c:out></td>
											<td><c:out value="${pcACMappingforLBWardDetails.districtNameEnglish}" escapeXml="true"></c:out></td>
											<td><c:out value="${pcACMappingforLBWardDetails.subdistrictCode}" escapeXml="true"></c:out></td>
											<td><c:out value="${pcACMappingforLBWardDetails.subdistrictNameEnglish}" escapeXml="true"></c:out></td>
											<td><c:out value="${pcACMappingforLBWardDetails.villageCode}" escapeXml="true"></c:out></td>
											<td><c:out value="${pcACMappingforLBWardDetails.villageNameEnglish}" escapeXml="true"></c:out></td>
											<td><c:out value="${pcACMappingforLBWardDetails.localBodyCode}" escapeXml="true"></c:out></td>
											<td><c:out value="${pcACMappingforLBWardDetails.localBodyNameEnglish}" escapeXml="true"></c:out></td>
											<td><c:out value="${pcACMappingforLBWardDetails.localBodyTypeName}" escapeXml="true"></c:out></td>
											<td><c:out value="${pcACMappingforLBWardDetails.wardCode}" escapeXml="true"></c:out></td>
											<td><c:out value="${pcACMappingforLBWardDetails.wardNameEnglish}" escapeXml="true"></c:out></td>
											<td><c:out value="${pcACMappingforLBWardDetails.blockCode}" escapeXml="true"></c:out></td>
											<td><c:out value="${pcACMappingforLBWardDetails.blockNameEnglish}" escapeXml="true"></c:out></td>
											<%-- <td><c:out value="${pcACMappingforLBWardDetails.coverageType}" escapeXml="true"></c:out></td> --%>
										</tr>
										</c:forEach>
										<tr><td align="right"></td></tr>
										</tbody>
									</table> 
									</div>
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
											src="<%=request.getContextPath()%>/images/ajax-loader-2.gif" />
									</div>

								</div>
								
						    </div> <!-- Printable Div ends here -->
						    
						    <div class="buttons center">
						    	<input type="button" name="Submit3" class="btn" value=<spring:message htmlEscape="true" code="Button.CLOSE"></spring:message> onclick="javascript:go('welcome.do');" />
						    </div>
						    
						</c:if>		        	
					
		</form:form>
	</div>
		</div>
	
</body>
</html>