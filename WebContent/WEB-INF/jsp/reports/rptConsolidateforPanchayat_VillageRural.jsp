<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>
<!-- Toooltip Code by Maneesh 13June2014 -->
<%@include file="consolidateReportTooltip.jsp"%>
<!-- Toooltip Code by Maneesh 13June2014 -->
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

function manageState1(url,stateid,lbtype,level,localbodycode)
{
	//alert("Inside the manageState1 function");
	dwr.util.setValue('stateId', stateid, {	escapeHtml : false	});
	dwr.util.setValue('lbtype', lbtype, { escapeHtml : false	});
	dwr.util.setValue('statelevel', level, { escapeHtml : false	});
	dwr.util.setValue('localbodycode', localbodycode, { escapeHtml : false	});
	
	//document.getElementById('form1').method = "GET";
	document.getElementById('form1').action = url+"?<csrf:token uri='"+url+"'/>";
	document.getElementById('form1').submit();
 }
 
function goBack()
{
	//alert("inside the back method");
	document.getElementById('form1').method = "post";
	document.getElementById('form1').action = "rptBacktoParentConsolidateReport.do?<csrf:token uri='rptBacktoParentConsolidateReport.do'/>";
	document.getElementById('form1').submit();
}

$(document).ready(function(){
	 
	$("#tbl_with_brdr tr:even").css("background-color", "#dedede");
	 
	$("#tbl_with_brdr tr:odd").css("background-color", "#ffffff");
	 
	});
	
	

         
function PrintDiv() {
	
	
	document.getElementById('footer').style.display = 'block';
	document.getElementById('footer').style.visibility = 'visible';
	var printContent = document.getElementById("printdoc");
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
	
function displayMap (villPanCode, stateCode) {
	openGISModal(villPanCode, null, "P", "VL", stateCode);
}
</script>
</head>
<body>
	<form:form commandName="consolidateReportLBRPT" id='form1'>
	<form:input path="stateId" type="hidden" name="stateId" id="stateId"  />
 								<form:input path="lbtype" type="hidden" name="lbtype" id="lbtype" /> 
 								<form:input path="statelevel" type="hidden" name="statelevel" id="statelevel" /> 
 								<form:input path="localbodycode" type="hidden" name="localbodycode" id="localbodycode" />
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
	
	<div class="frmtxt" id="printdoc">
		<h6 id="statusmessage" class="title" style="font-size:18px; font-weight:bold; text-align:center; margin:0;">
 				<c:if
					test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}">
					<c:out value="${consolidateReportLBRPT.state_name_english}" escapeXml="true"></c:out>
					<c:if test="${!empty consolidateReportLBRPT.financialYear and consolidateReportLBRPT.financialYear ne ''}">
						<c:out value="(${consolidateReportLBRPT.financialYear})" escapeXml="true"></c:out>
					</c:if>
				</c:if>
				<c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}">
					<spring:message code="Label.VILLAGELVLTRADLB"
							htmlEscape="true"></spring:message> &nbsp;<spring:message
							code="Label.OF" htmlEscape="true"></spring:message>&nbsp;
							<c:out value="${consolidateReportLBRPT.state_name_english}" escapeXml="true"></c:out>
							<c:if test="${!empty consolidateReportLBRPT.financialYear and consolidateReportLBRPT.financialYear ne ''}">
								<c:out value="(${consolidateReportLBRPT.financialYear})" escapeXml="true"></c:out>
							</c:if>
				</c:if>
		   	</h6>
		   	
		   	<ul class="blocklist">
		   		
					<li><c:if
						test="${!empty consolidateLBList}">
						* Due to periodic elections, data is dynamic in nature and keep on changing
						</c:if><input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
					</li>
					<li><c:if
						test="${!empty consolidateLBList}">
						** Local Government Directory is now mapped to Census 2011 village codes
						</c:if><input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
					</li>
					<li>
						<c:if
						test="${!empty consolidateLBList}">
						*** Click on the Localbody Name to view other related information
					</c:if><input type="hidden" id="sessionId" value='<%=sessionId%>'></input>
					</li>
			</ul>
			<br />
			
			<table class="tbl_with_brdr" width="100%" id="tbl_with_brdr">
						<tr class="tblRowTitle tblclear">
							<td width="4%"><label><spring:message
										code="Label.SRNO" htmlEscape="true"></spring:message> </label>
							</td>
					<td width="15%" align="center"><label><spring:message
										code="Label.LGDCODE" htmlEscape="true"></spring:message> </label></td>
							<td width="15%" align="center"><label><spring:message
										code="Label.STATESPECIFICCODE" htmlEscape="true"></spring:message> </label></td>
							<%-- 	<td width="20%" align="center"><label><spring:message
										code="Label.DISTRICTLBRPT" htmlEscape="true"></spring:message> </label>
							</td>
							<td width="20%" align="center"><label><spring:message
										code="Label.INTERMLBRPT" htmlEscape="true"></spring:message> </label></td> --%>
							<%-- <td width="12%" align="center"><c:if
									test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}">
									<label> <spring:message code="Label.VILLAGEPCODE"
											htmlEscape="true"></spring:message> </label>
								</c:if> <c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}">
									<label> <spring:message code="Label.VILLAGELVLLBCODE"
											htmlEscape="true"></spring:message> </label>
								</c:if></td> --%>
							<td width="20%" align="left"><c:if
									test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}">
									<label> <spring:message code="Label.VILLAGEPANCHAYATNAME"
											htmlEscape="true"></spring:message> </label>
								</c:if> <c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}">
									<label> <spring:message code="Label.VILLAGELVLTRADLB"
											htmlEscape="true"></spring:message> </label>
								</c:if></td>
							<td width="22%" align="left"><c:if
									test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}">
									<label> <spring:message code="Label.VILLAGEPNAMELOCAL"
											htmlEscape="true"></spring:message> </label>
								</c:if> <c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}">
									<label> <spring:message code="Label.VILLAGELVLLBLOCAL"
											htmlEscape="true"></spring:message> </label>
								</c:if></td>
							<td width="12%" align="center"><c:if
								test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'P')}">
								<label> <spring:message code="Label.NOOFCENSUSVILLAGESMAPPED"
										htmlEscape="true"></spring:message> </label>
							</c:if> <c:if test="${fn:containsIgnoreCase(consolidateReportLBRPT.lbtype,'T')}">
								<label> <spring:message code="Label.NOOFCENSUSVILLAGESMAPPED"
										htmlEscape="true"></spring:message> </label>
							</c:if></td>
							<td width="10%" align="center">Map</td>
						</tr>


						<c:if test="${! empty consolidateLBList}">
						<form:input path="financialYear" type="hidden"  />
							<c:forEach var="panchaytSetUp" varStatus="panchaytSetUpRow"	items="${consolidateLBList}">
							<c:set var="index" value="${panchaytSetUpRow.index +1}" />
								<c:set var="lbCode" value="${panchaytSetUp.localBodyCode}" />
								<c:set var="unitName" value="${panchaytSetUp.localbodyNameEnglish}" />
								<tr class="tblRowB">
									<td width="4%"><c:out value="${panchaytSetUpRow.index +1}" escapeXml="true"></c:out></td>
									<td width="15%" align="center"><c:out
											value="${panchaytSetUp.localBodyCode}" escapeXml="true"></c:out></td>
									<td width="15%" align="center"><c:out
											value="${panchaytSetUp.sscode}" escapeXml="true"></c:out></td>

									<%-- <td width="12%" align="center"><c:out
											value="${panchaytSetUp.localBodyCode}"></c:out></td> --%>
									<td width="20%" align="left">
									<!-- Toooltip Code by Maneesh 13June2014 -->
										<a class="popModal_ex${index}" onclick="setTooltip('${lbCode}','popModal_ex${index}','${lbCode}','${unitName}','${typeName}','${stateCode}','<%=languageCode%>' )">
											<b> <label title="Click the Localbody Name to view Other Information of  ${unitName} ${typeName}"><c:out value="${unitName}" escapeXml="true"></c:out></label></b>
										</a>
										
											<div style="display:none">
												<div id="${lbCode}" >
												</div>
										</div>
									<!-- Toooltip Code by Maneesh 13June2014 --></td>
									<td width="22%" align="left"><c:out
											value="${panchaytSetUp.localbodyNameLocal}" escapeXml="true"></c:out></td>
									<td width="23%" align="center"><c:choose>
											<c:when test="${panchaytSetUp.childCount == null}">
												<c:out value="0"></c:out>
											</c:when>
											<c:otherwise>
													<a href="#" onclick="javascript:manageState1('rptConsolidateforPanbyLevelRural.do',${consolidateReportLBRPT.state_code},'${consolidateReportLBRPT.lbtype}','M',${panchaytSetUp.localBodyCode});"><c:out value="${panchaytSetUp.childCount}" escapeXml="true"></c:out></a>	
											</c:otherwise>
										</c:choose></td>
									<td align="center" width="10%">
										<c:if test="${panchaytSetUp.childCount != null}">
											<img src="images/showMap.jpg" onclick="javascript:displayMap('${panchaytSetUp.localBodyCode}', '${consolidateReportLBRPT.state_code}');" width="18" height="19" border="0" />
										</c:if>
									</td>	
								</tr>

							</c:forEach>
								
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
