<%@include file="../common/taglib_includes.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="java.util.*,in.nic.pes.lgd.bean.State"%>
<%!String contextPath;%>
<% 
	contextPath = request.getContextPath(); 
%>

<html>
<head>
<script type="text/javascript" src="js/cancel.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<link href="HTML_panchayat - 2.2/css/panchayat_main.css"
	rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=contextPath%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=contextPath%>/dwr/util.js'></script>	
<script type='text/javascript' src='<%=contextPath%>/dwr/interface/lgdDWRDisturbedEntities.js'></script>

<script type="text/javascript" language="javascript">
var urlInvalidateLB="";
var urlChangeCovArea="";
var w ="";
var checkInval = new Boolean(false);
var checkChangeCov = new Boolean(false);


if (window.addEventListener) {
    // DOM2 standard
    window.addEventListener("load", onLoadSelect, false);
}
else if (window.attachEvent) {
    // Microsoft's precursor to it, IE8 and earlier
    window.attachEvent("onload", onLoadSelect);
}
else {
    // Some pre-1999 browser
    window.onload = onLoadSelect;
}

function Unset(id)
{
	var temp=id.split(",");
	document.getElementById("entityCode").value=temp[0];
	document.getElementById("entityVersion").value=temp[1];
	var confirmation=confirm("The flag will be unset, please ensure that the required changes are done.");
	if(confirmation==true){
		document.forms['GetEntitiesWithWanrning'].submit();
	}
}

function onLoadSelect()
{
	
	if('${warningEntiesFlag}' == 'W'){
		document.getElementById("wardisFlagW").checked = true;
		document.getElementById("warningId").style.display = "inline";
		document.getElementById("disturbId").style.display = "none";
		document.getElementById("headerId").innerHTML="<spring:message code="Label.RESOLVEWARNING"/>";
		
	}
	if('${warningEntiesFlag}' == "D"){
		document.getElementById("wardisFlagD").checked = true;
		document.getElementById("disturbId").style.display = "inline";
		document.getElementById("warningId").style.display = "none";
		document.getElementById("headerId").innerHTML="<spring:message code="Label.RESOLVEDISTURB"/>";
	
	}
	
	var mypath = window.location.href;
	document.getElementById(mypath.split("=")[1]).disabled = false;
	document.getElementById(mypath.split("=")[1]).style.backgroundColor="green";
	setTimeout("document.getElementById(mypath.split('=')[1]).style.backgroundColor=''", 500);
	setTimeout("onLoadSelect()",1500);
}

function WarDisFunction(val)
{
	if(val == "W"){		
		document.getElementById("warningId").style.display = "inline";
		document.getElementById("disturbId").style.display = "none";
		document.getElementById("headerId").innerHTML="<spring:message code="Label.RESOLVEWARNING"/>";
		document.getElementById("warningEntiesFlag").value = val;
	}
	if(val == "D"){
		document.getElementById("disturbId").style.display = "inline";
		document.getElementById("warningId").style.display = "none";
		document.getElementById("headerId").innerHTML="<spring:message code="Label.RESOLVEDISTURB"/>";
		document.getElementById("warningEntiesFlag").value = val;
	}
	
}

function getMessage(actionInvalidateLB,actionChangeCoveredArea,localbodycode)
{
	lgdDWRDisturbedEntities.getMessagebyLBCode(localbodycode, {
		callback : handledgetMessageSuccess,
		errorHandler : handledGetMessageError
	});
	
	urlInvalidateLB=actionInvalidateLB;
	urlChangeCovArea=actionChangeCoveredArea;
}

function handledgetMessageSuccess(data){
	
	var message="";
	for ( var i = 0; i < data.length; i++) {
		var labelDetails = data[i];
		message = labelDetails.getLocalbodyDisturbRegionfn;
	}
	callAction(message);
}


function handledGetMessageError() {
	alert("No data found!");
}

function callAction(message)
{	
	w = window.open('', '',
		'width=600,height=600,resizeable,position=center,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=yes',
		align = "center");
	
	var innerHTMLText = '';
    
	innerHTMLText += "<body><table width='500' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='300'><h2> Reason for Local Body to be in Disturbed State </h2></td></tr><tr class='tblRowTitle tblclear'><td width='300'>" + message + "</td></tr></table>";
	innerHTMLText += "<table width='500' class='tbl_with_brdr'><tr class='tblRowTitle tblclear'><td width='300'><h2>Action to be taken for Disturbed Flag "
		+ "</h2></td></tr>";
		
	innerHTMLText += "<tr class='tblRowB'><td><input type='radio' name='partR' id='partINV' onclick='window.opener.invalidR(partINV)' value=" + urlInvalidateLB
		 + "/>Invalidate Local Body" + "</td></tr><tr class='tblRowB'><td><input type='radio' name='partR' id='partCOV' onclick='window.opener.changeCov(partCOV)' value=" + urlChangeCovArea 
		 + "/>Modify Local Govt Body</td></tr></table>"
		 + "<tr><td><input type='button' name='Submit16' value='Get Value' onclick='window.opener.doSomeAction()' /></td></tr></body></html>";	
	
	w.document.write(innerHTMLText);
	w.document.close();	
}

function invalidR(partINV)
{
	checkInval=partINV.checked;
	urlInvalidateLB=partINV.value;
}

function changeCov(partCOV)
{
	checkChangeCov=partCOV.checked;
	urlChangeCovArea=partCOV.value;
}


function doSomeAction()
{
	if(checkInval==true)
 	{
 		document.forms["GetEntitiesWithWanrning"].action = urlInvalidateLB;
		document.forms["GetEntitiesWithWanrning"].submit();
		w.close();
 	}	
 	else if(checkChangeCov==true)
 	{
 		document.forms["GetEntitiesWithWanrning"].action = urlChangeCovArea;
		document.forms["GetEntitiesWithWanrning"].submit();
		w.close();
 	}	
}

dwr.engine.setActiveReverseAjax(true);
</script>
</head>
<body>
	<form:form  action="viewResolveEntitiesinDisturbedState.htm?resolved=${resolved}" method="POST" commandName="GetEntitiesWithWanrning">
	<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="viewResolveEntitiesinDisturbedState.htm"/>"  htmlEscape="true"/>

	
		<div class="frmpnlbg">
			<div class="frmtxt">
				<div class="frmhdtitle"><label id="headerId" ><spring:message code="Label.RESOLVEDISTURBSTATE"/></label></div>
				 
				
				
				<ul class="listing">
				 	<li>Warning State</li>
				 	<li><input type="radio" name="wardisFlag" id="wardisFlagW" value="W" onclick="WarDisFunction(this.value);" htmlEscape="true"/></li>
				 	
				 	<li>Disturb State</li>
				
				 <c:if test="${type == 'L'}">
				 
				 	<li><input type="radio" name="wardisFlag" id="wardisFlagD" value="D" disabled="disabled" onclick="WarDisFunction(this.value);" htmlEscape="true"/></li>
				 	
				 </c:if>
				  <c:if test="${type != 'L'}">
					 <li><input type="radio" name="wardisFlag" id="wardisFlagD" value="D" onclick="WarDisFunction(this.value);" htmlEscape="true"/></li>
				 </c:if>
				 				
			  </ul> 
				
				
				  <input type="hidden" name="warningEntiesFlag" id="warningEntiesFlag" value="<c:out value='${warningEntiesFlag}' escapeXml='true'></c:out>" htmlEscape="true"/>
				   <input type="hidden" name="type" id="type" value="<c:out value='${type}' escapeXml='true'></c:out>" htmlEscape="true"/>
				  <table id="warningId" width="100%" class="tbl_no_brdr" align="center" style="display:none;">
					<tr>
						<td width="14%" align="center">
							<table class="tbl_with_brdr" width="100%" align="center">
								<tr class="tblRowTitle tblclear">
									<td align="center"><spring:message htmlEscape="true"  code="Label.SNO"/></td>
									<td align="center" ><spring:message htmlEscape="true"  code="Label.FLAG"/></td>
									<td align="center" ><spring:message htmlEscape="true"  code="Label.ENTITYTYPE"/></td>
									<td align="center" ><spring:message htmlEscape="true"  code="Label.ENTITYNAME"/></td>
									<td align="center" ><spring:message htmlEscape="true"  code="Label.ACTION"/></td>
								</tr>
							    <c:choose>
	                               	<c:when test="${!empty warningEnties}">
	                               		<c:forEach var="disturbedEnties" varStatus="disturbedEntiesRow" items="${warningEnties}">
										<c:choose>
											<c:when test="${disturbedEnties.entityCode == id}">
												<tr class="tblRowB" style="background-color: #C6EFCE;">
													<td align="center" width="6%"><c:out value="${disturbedEntiesRow.count}" escapeXml="true"></c:out></td>
													<td align="center" width="6%"><img src="images/ylw_flg.png" width="13" height="9" /></td>
													<td align="center" width="10%"><c:out value="${disturbedEnties.entityType}" escapeXml="true"></c:out></td>
													<td width="29%"><c:out value="${disturbedEnties.entityNameEnglish}" escapeXml="true"></c:out></td>
													<td align="center" width="9%">
														<a href="<c:out value='${disturbedEnties.entityNameLocal}'></c:out>&<csrf:token uri="${disturbedEnties.entityNameLocal}"/>"><img src="images/edit.png"/></a>
													</td>
												</tr>
											</c:when>
											<c:otherwise>
												<tr class="tblRowB">
													<td align="center" width="6%"><c:out value="${disturbedEntiesRow.count}" escapeXml="true"></c:out></td>
													<td align="center" width="6%"><img src="images/ylw_flg.png" width="13" height="9" /></td>
													<td align="center" width="10%"><c:out value="${disturbedEnties.entityType}" escapeXml="true"></c:out></td>
													<td width="29%"><c:out value="${disturbedEnties.entityNameEnglish}" escapeXml="true"></c:out></td>
													<td align="center" width="9%">
														<a href="<c:out value='${disturbedEnties.entityNameLocal}'></c:out>&<csrf:token uri="${disturbedEnties.entityNameLocal}"/>"><img src="images/edit.png"/></a>
													</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</c:forEach>
	                               	</c:when>
	                               	<c:otherwise>
	                               		<tr class="tblRowB">
											<td colspan="7" align="center" height="30"><b><spring:message htmlEscape="true" code="Error.dialog-confirm-nodata"></spring:message></b></td>
										</tr>
	                               	</c:otherwise>
                               </c:choose>
                               <input type="hidden" name="entityCode" id="entityCode" htmlEscape="true" />
							   <input type="hidden" name="entityVersion" id="entityVersion" htmlEscape="true"/>
							</table>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>

					</tr>

 				</table>
 				
				<table id="disturbId" width="100%" class="tbl_no_brdr" align="center" style="display:none;">
					<tr>
						<td width="14%" >
							<table class="tbl_with_brdr" width="100%">
								<tr class="tblRowTitle tblclear">
									<td align="center"><spring:message htmlEscape="true" code="Label.SNO"/></td>
									<td align="center" ><spring:message htmlEscape="true" code="Label.FLAG"/></td>
									<td align="center" ><spring:message htmlEscape="true" code="Label.LOCALBODYCODE"/></td>
									<td align="center" ><spring:message htmlEscape="true" code="Label.NAMELOCALGOVTTYPE"/></td>
									<td align="center" ><spring:message htmlEscape="true" code="Label.LOCALGOVTBODYNAME"/></td>
									<td align="center" ><spring:message htmlEscape="true" code="Label.ACTION"/></td>
								</tr>
								<c:choose>
	                               	<c:when test="${!empty disturbedEnties}">
										<c:forEach var="disturbedEnties" varStatus="disturbedEntiesRow" items="${disturbedEnties}">
											<tr class="tblRowB">
												<td width="6%" align="center"><c:out value="${disturbedEntiesRow.count}" escapeXml="true"></c:out></td>
												<td align="center" width="6%"><img src="images/red_flg.png" width="13" height="9" /></td>
												<td align="center" width="15%"><c:out value="${disturbedEnties.localbodycode}" escapeXml="true"></c:out></td>
												<td align="center" width="15%"><c:out value="${disturbedEnties.localbodytypename}" escapeXml="true"></c:out></td>
												<td><c:out value="${disturbedEnties.localbodynameenglish}" escapeXml="true"></c:out></td>
												<td width="10%" align="center">
													<a href="#" style="text-decoration:none;" onclick="getMessage('${disturbedEnties.actionInvalidateLB}','${disturbedEnties.actionChangeCoveredArea}','${disturbedEnties.localbodycode}');">action</a>
												</td>
											</tr>
											 <c:set var="countb" value="${countb+1}"></c:set>
										</c:forEach>
									</c:when>
	                               	<c:otherwise>
										<tr class="tblRowB">
											<td colspan="5" align="center" height="30"><b><spring:message htmlEscape="true" code="Error.dialog-confirm-nodata"></spring:message></b></td>
										</tr>
									</c:otherwise>
								</c:choose>		
								<input type="hidden" name="entityCode" id="entityCode" htmlEscape="true"/>
								<input type="hidden" name="entityVersion" id="entityVersion" htmlEscape="true"/>
							</table>
						</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				
				</table>
				
				<div class="btnpnl">
					<label><input htmlEscape="true" type="button" name="Submit6" class="bttn" value="Cancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';"/></label>					
				</div>
			</div>
		</div>
	</form:form>
	<script src="${pageContext.request.contextPath}/JavaScriptServlet"></script>
	<div id="dialog-message" title="Select an Action" style="width: 1200px;"></div>
	 <script>
	 	/* callAction = function(urlInvalidateLB, urlChangeParent, urlChangeCoveredArea) {
			document.forms["GetEntitiesWithWanrning"].method = "post";
			$("#dialog:ui-dialog").dialog("destroy");
			$("#dialog-message" ).dialog({
				modal: true,
				//width: 550,
				height: 150,
				resizable: true,
                autoResize: true,
              	buttons: {
					"Invalidate Local Body": function() {
						document.forms["GetEntitiesWithWanrning"].action = urlInvalidateLB;
						document.forms["GetEntitiesWithWanrning"].submit();
						$( this).dialog("close");
					},
					"Change Parent": function() {
						document.forms["GetEntitiesWithWanrning"].action = urlChangeParent;
						document.forms["GetEntitiesWithWanrning"].submit();
						$( this).dialog("close");
					},
					"Change Covered Area": function() {
						document.forms["GetEntitiesWithWanrning"].action = urlChangeCoveredArea;
						document.forms["GetEntitiesWithWanrning"].submit();
						$( this).dialog("close");
					}
				} 
			});
			//$('#dialog-message').siblings('.ui-dialog-buttonpane').find('button').eq(1).hide();
			//$('#dialog-message').siblings('.ui-dialog-buttonpane').find('button').eq(2).hide();
		}; */
		
		
		
	 </script>

</body>
</html>