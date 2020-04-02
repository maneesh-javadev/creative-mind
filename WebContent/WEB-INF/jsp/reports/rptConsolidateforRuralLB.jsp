<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<%!String sessionId;%>
<%
	contextPath = request.getContextPath();
	sessionId = request.getSession().getId();
%>
<head>
<title>E-Panchayat</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- <script type="text/javascript" src="js/common.js"></script> -->
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDwrCaptchaService.js'></script>

<script type="text/javascript" language="Javascript">
$(document).ready(function() {
	$("[id^=searchBy]").change(function() {
		$('#' + $(this).attr('paramShow')).show();
		$('#' + $(this).attr('paramHide')).hide();
		
	});
});

var parentLevel='S';
function open_win() {
	var obj = window.showModalDialog("help.do?<csrf:token uri='help.do'/>&Foldermapping=${Foldermapping}&Filename=${Filename}", '', "dialogWidth:400px; dialogHeight:400px; dialogLeft: 370; dialogTop: 300; center:yes; resizable: yes; status:no");
} 
function manageState1(url,stateid,type,level){	
 	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
 	dwr.util.setValue('parentCode', stateid, {	escapeHtml : false	});
 	dwr.util.setValue('parentLevel',parentLevel , {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }

function manageState2(url,stateid,type,level){
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
 	dwr.util.setValue('parentCode', stateid, {	escapeHtml : false	});
 	dwr.util.setValue('parentLevel',parentLevel , {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
			
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState3(url,stateid,type,level){
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
 	dwr.util.setValue('parentCode', stateid, {	escapeHtml : false	});
 	dwr.util.setValue('parentLevel',parentLevel , {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState4(url,stateid,type,level){
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState5(url,stateid,type,level){
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState6(url,stateid,type,level){
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }
function manageState7(url,stateid,type,level){
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('statetype', type, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	document.getElementById('form1').action = url;
	document.getElementById('form1').submit();
 }

$(document).ready(function(){
	$("#tbl_with_brdr tr:even").css("background-color", "#dedede");
	$("#tbl_with_brdr tr:odd").css("background-color", "#ffffff");
});
	

         
         
function PrintDiv() {
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("printable2");
	var Win4Print = window.open('', '', 'left=0,top=0,width=500,height=500,top=301,left=365,resizable=1,status=0,toolbar=0');
	Win4Print.document.write(printContent.innerHTML);
	Win4Print.document.close();
	Win4Print.focus();
	Win4Print.print();
	Win4Print.close();
	document.getElementById('footer').style.display = 'none';
	document.getElementById('footer').style.visibility = 'hidden';
	return false;
}

function displayMap (stateCode) {
	openGISModal(stateCode, 1, "P", null, null);
 }
 
 validateReport=function(){
	 var error=false;
	 if ($("#searchByPartiFinYear").is(':checked')){
			var financialYear=$("#financialYear").val();
			if($_checkEmptyObject(financialYear)){
				error=true; 
				 $( '#errrparentType').text("Select Financial Year");
			}
	 }
	 
	 var capchaAns=$("#captchaAnswer").val();
	 if(capchaAns==""){
		 $( '#errorcaptchaAnswers').text("<spring:message code='captcha.errorMessage' htmlEscape='true'/>");
		error=true;
	 }
	 
	 if(!error){
		 callActionUrl('rptConsolidateforRuralLB.do');
	 }
	 
	 
 };
 
 /* The {@code $_checkEmptyObject} used to check object / element  
  * is empty or undefined.
  */
  var $_checkEmptyObject = function(obj) {
  	if (jQuery.type(obj) === "undefined" || (obj == null || $.trim(obj).length == 0)) {
  		return true;
  	}
  	return false;
  };

 
callActionUrl=function(url){
 	/* document.forms['sectionForm'].action = "buildSelection.htm?<csrf:token uri='"buildSelection.htm'/>";
	document.forms['sectionForm'].method = "POST";
    document.forms['sectionForm'].submit(); */
   
    $( 'form[id=form1]' ).attr('action', url + '?<csrf:token uri="' + url + '"/>');
	$( 'form[id=form1]' ).attr('method','post');
	$( 'form[id=form1]' ).submit();
};
</script>
</head>
<body>
	<div id="frmcontent">
		<div class="frmhd">
			  
			<%-- <c:out value="(${consolidateReportForRuralLB.financialYear})" escapeXml="true"></c:out> --%>
			<c:choose>
				<c:when test="${empty consolidateLBList}">
				<h3 class="subtitle"><spring:message code="Label.ConsolidatedReportForRuralLB" htmlEscape="true"></spring:message></h3>
					<ul class="listing">
						<%-- <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" alt="Toggle"	border="0" /></a></li>
						<li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
						<li><a href="#" class="frmhelp"><spring:message htmlEscape="true"  code="Label.HELP"></spring:message></a></li> --%>
					</ul>					
                   </c:when>
                   <c:otherwise>
                   	<a id="showprint" href="#"><img src='<%=contextPath%>/images/printer_icon.png' border="0" alt="Print" onclick="PrintDiv();" /></a></td>
				</c:otherwise>
			</c:choose>
		</div>
	
		<div class="frmpnlbrdr">
			<div id="cat">
				<div class="frmpnlbg">
					<form:form commandName="consolidateReportForRuralLB" id="form1" name="form1" action="rptConsolidateforRuralLB.do">
						<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="rptConsolidateforRuralLB.do"/>" />
						
 								<form:input path="parentCode" type="hidden"  />
  								<form:input path="parentLevel" type="hidden"  />
 					<c:if test="${empty consolidateLBList}">		
 						<div id="divCenterAligned" class="form_stylings">
							<div class="form_block">
								<div class="col_1">
									<h4><spring:message htmlEscape="true" code="Label.FILTEROPTFORVIEWConsolidatedReportPanchayats" text="Filter Options For Consolidated Report of Panchayats"></spring:message></h4>
									<ul class="form_body" >
										<li>
											<form:radiobutton path="searchCriteriaType" id='searchByActiveFinYear' value="A" checked="checked" paramShow="displayNameCode" paramHide="displayHierarchy"/>
											List of localbodies Currently Active&nbsp;&nbsp;
									
											<form:radiobutton path="searchCriteriaType" id='searchByPartiFinYear' value="P" paramShow="displayHierarchy" paramHide="displayNameCode"/>
											List of localbodies for financial Year
										</li>
									</ul>
									<h4><!-- Used header for blank head, please dont remove.  --></h4>
									<ul class="form_body" >
										
										<div id="displayHierarchy" style="display: none;">
											<li>
										<label for=financialYear>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
										<spring:message htmlEscape="true" code="Select Financial Year"></spring:message></label>
										<form:select path="financialYear" class="combofield"
											 id="financialYear" onchange="error_remove();">
											
											<form:option value="2017-2018">
												<spring:message htmlEscape="true" code="2016-2017"></spring:message>
											 </form:option>		
											<form:option value="2016-2017">
												<spring:message htmlEscape="true" code="2016-2017"></spring:message>
											 </form:option>											
											<form:option value="2015-2016">
												<spring:message htmlEscape="true" code="2015-2016"></spring:message>
											 </form:option>
											<form:option value="2014-2015">
												<spring:message htmlEscape="true" code="2014-2015"></spring:message>
											</form:option>
											<form:option value="2013-2014">
												<spring:message htmlEscape="true" code="2013-2014"></spring:message>
											</form:option>
											<form:option value="2012-2013">
												<spring:message htmlEscape="true" code="2012-2013"></spring:message>
											</form:option>
											<form:option value="2011-2012">
												<spring:message htmlEscape="true" code="2011-2012"></spring:message>
											</form:option>
											<form:option value="2010-2011">
												<spring:message htmlEscape="true" code="2009-2010"></spring:message>
											</form:option>
										   <form:option value="2008-2009">
												<spring:message htmlEscape="true" code="2008-2009"></spring:message>
											</form:option>
											 <form:option value="2007-2008">
												<spring:message htmlEscape="true" code="2007-2008"></spring:message>
											</form:option>
											 <form:option value="2006-2007">
												<spring:message htmlEscape="true" code="2006-2007"></spring:message>
											</form:option>
											 <form:option value="2005-2006">
												<spring:message htmlEscape="true" code="2005-2006"></spring:message>
											</form:option>
											<%-- <form:options items="${stateSourceList}"
												itemValue="statePK.stateCode" itemLabel="stateNameEnglish">
											</form:options>
											 --%>
										</form:select>
										<span class="errormsg" id="errorfinancialYear"></span>
									</li>
										</div>
										<li>
											<label><!-- Used Label, please dont remove. --></label>
											<img src="captchaImage" alt="Captcha Image" />
										</li>
										<li>
											<label>
												<spring:message code="captcha.message"	htmlEscape="true" />
												<span class="mandate">*</span>
											</label>
											<form:input	path="captchaAnswers" id="captchaAnswer" autocomplete="off" />
											
												<br/><label><!-- Used Label, please dont remove. --></label>
												<span class="errormsg" id="errorcaptchaAnswers"></span>
												<c:if test="${not empty captchaSuccess1 and not captchaSuccess1}">
												<br/><label><!-- Used Label, please dont remove. --></label>
												<span class="errormsg"><strong><spring:message code="captcha.errorMessage"/></strong></span>
												</c:if>
										</li>
										<li>
											<label class="inline">&nbsp;</label>
											<input type="button" name="Submit"  value="<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message>"  onclick="validateReport();"/>
											<input type="button" value="<spring:message code="Button.CLOSE"/>" onclick="javascript:location.href='welcome.do'"/>
										</li>
									</ul>	
								</div>
							</div>
						</div>
						<div>
						
*Kindly select "Local bodies Currently Active" option for getting List of only those Local bodies which are currently active.<br/>
**List of local bodies in a particular financial year may also include list of local bodies which are currently Invalidated but exist in that financial year.For an Example a gram panchayat which is invalidated on 21/05/2016 will also come in the list of gram panchayat for the financial year 2016-2017.
						
						</div>
								
 						</c:if>		
						<div id="printable2">
					
							
							<c:if test="${!empty consolidateLBList}">
							<form:input path="financialYear" type="hidden"  />
							
								<ul class="blocklist">
								     
										<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
										<spring:message code="Label.ConsolidatedReportForRuralLB" htmlEscape="true"></spring:message>
										<c:if test="${consolidateReportLBRPT.searchCriteriaType ne 'A'  and consolidateReportLBRPT.financialYear ne ''}">
										 <c:out value="(${consolidateReportLBRPT.financialYear})" escapeXml="true"></c:out>
										</c:if>
										</h6>
									  
									<li>* N.A.- Not Applicable</li>
									<li>** Due to periodic elections, data is dynamic in
														nature and keep on changing</li>
									<li>*** Local Government Directory is now mapped to
														Census 2011 village codes</li>
								</ul>
									<table class="tbl_no_brdr" width="100%" id="tbl_no_brdr">										
										
										<tr>
											<td align="center" width="100%">
												<br />
												<table class="tbl_with_brdr" width="100%" align="center" id="tbl_with_brdr">
													<tr class="tblRowTitle tblclear" style="height: 50px;">
														<td width="6%"  style="font-size: 15px;">
															<spring:message code="Label.SRNO" htmlEscape="true"></spring:message>
														</td>
														<td width="16%" align="left"  style="font-size: 15px;">
															<spring:message code="Label.STATENAME" htmlEscape="true"></spring:message>
														</td>
														<td width="16%" align="center" style="font-size: 15px;">
															<spring:message code="Label.DISTRICTPANCHYATNME" htmlEscape="true"></spring:message>
														</td>
														<td width="16%" align="center" style="font-size: 15px;">
															<spring:message code="Label.INTERPANCHYATNME" htmlEscape="true"></spring:message>
														</td>
														<td width="16%" align="center" style="font-size: 15px;">
															<spring:message code="Label.VILLAGEPANCHYATNME" htmlEscape="true"></spring:message>
														</td>
														<td width="10%" align="center" style="font-size: 15px;">
															Map
														</td>
													</tr>
													<c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow" items="${consolidateLBList}">
														<c:set var="dpLevel" value="${panchaytSetUp.dp_count}"/>
														<c:set var="ipLevel" value="${panchaytSetUp.ip_count}"/>
														<c:set var="vpLevel" value="${panchaytSetUp.vp_count}"/>
														<c:set var="stateCode" value="${panchaytSetUp.state_code}"/>
														<c:set var="dpTotal" value="${dpTotal + dpLevel}"/>
														<c:set var="ipTotal" value="${ipTotal + ipLevel}"/>
														<c:set var="vpTotal" value="${vpTotal + vpLevel}"/>
														<tr>
															<td width="6%"><c:out value="${panchaytSetUpRow.count}" escapeXml="true"></c:out></td>
															<td width="16%" align="left"><c:out	value="${panchaytSetUp.state_name_english}" escapeXml="true"></c:out></td>
															<td width="11%" align="center">
																		<c:choose>
																				<c:when test="${dpLevel eq 0}">
																					<c:out value="N.A."/>
																				</c:when>
																				<c:otherwise>
																					<a 	href="#" onclick="javascript:manageState1('rptConsolidateforPanbyLevelRural.do',${stateCode},'P','D');">
																					<c:out value="${dpLevel}" escapeXml="true"></c:out></a>
																				</c:otherwise> 
																		</c:choose>
															</td>
															<td width="11%" align="center">
																		<c:choose>
																				<c:when test="${ipLevel eq 0}">
																					<c:out value="N.A."/>
																				</c:when>
																				<c:otherwise>
																					<c:choose>
																						<c:when test="${dpLevel eq 0}">
																								<a href="#" onclick="javascript:manageState2('rptConsolidateforPanbyLevelRural.do',${stateCode},'P','I');"><c:out
																								value="${ipLevel}" escapeXml="true"></c:out></a>
																						</c:when>
																						<c:otherwise>
																							<c:out value="${ipLevel}" escapeXml="true"></c:out>
																						</c:otherwise>
																					</c:choose>
																				</c:otherwise>
																			</c:choose>
															</td>
															<td width="11%" align="center">
																<c:choose>
																	<c:when test="${vpLevel eq 0}">
																		<c:out value="N.A."/>
																	</c:when>
																	<c:otherwise>
																		<c:choose>
																			<c:when test="${dpLevel eq 0 && ipLevel eq 0}">
																					<a href="#" onclick="javascript:manageState3('rptConsolidateforPanbyLevelRural.do',${stateCode},'P','V');">
																					<c:out value="${vpLevel}" escapeXml="true"></c:out></a>
																			</c:when>
																			<c:otherwise>
																					<c:out value="${vpLevel}" escapeXml="true"></c:out>
																			</c:otherwise>
																		</c:choose>	
																	</c:otherwise>
																</c:choose>
															</td>
															<td align="center" width="10%">
																<c:if test="${dpLevel gt 0}">
																	<img alt="Map" src="images/showMap.jpg" onclick="javascript:displayMap('${panchaytSetUp.state_code}');" width="18" height="19" border="0" />
																	<%-- <a style="text-decoration: none;" href="#" onclick="javascript:showMap('${panchaytSetUp.state_code}')">view</a> --%>
																</c:if>
															</td>
														</tr>
													</c:forEach>
							
													<form:input path="stateId" type="hidden" name="stateId" id="stateId" />
													<form:input path="statetype" type="hidden" name="statetype" id="statetype" />
													<form:input path="statelevel" type="hidden" name="statelevel" id="statelevel" />
															
													<tr class="tblRowTitle tblclear">
														<td width="4%"></td>
														<td width="16%" align="left"><label><spring:message code="Label.TOTALS"></spring:message> </label></td>
														<td width="11%" align="center"><c:out value="${dpTotal}" escapeXml="true"></c:out></td>
														<td width="11%" align="center"><c:out value="${ipTotal}" escapeXml="true"></c:out></td>
														<td width="11%" align="center"><c:out value="${vpTotal}" escapeXml="true"></c:out></td>
														<td width="11%" align="center">&nbsp;</td>
													</tr>
												</table>
											</td>
										</tr>
										
									</table>
							</c:if>		
						</div>
						
						
					 	<c:if test="${!empty consolidateLBList }">
					 		<ul class="blocklist center" style="text-align:center; list-style:none;"> <!-- Inline style only for Print purpose -->
								<li>
									<label><spring:message code="Label.URL" htmlEscape="true"/><%=request.getScheme() + "://" + request.getServerName() + request.getContextPath()%></label>
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
															Panchayati Raj</a>, Government of India <br/> Site best
															viewed on Internet Explorer (IE)-8 &amp; above, Mozilla
															Firefox-11 &amp; above 
								</p>
		     					<div id="displayBox" style="text-align: center;display:none;"><img  src="<%= request.getContextPath() %>/images/ajax-loader-2.gif" alt="Loading...'"  /></div>
				    	</div>
						
						<c:if test="${!empty consolidateLBList }">
						<div class="buttons center" id="showbutton">
							<input type="button" name="Submit3" class="btn" id="btn1" value="<spring:message htmlEscape="true"  code="Button.CLOSE"/>" onclick="javascript:location.href='welcomeLocal.do?<csrf:token uri='welcomeLocal.do'/>';" />
						</div>
						</c:if>
						
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
