<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<script type='text/javascript' src="<%=contextpthval%>/js/common.js"></script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=contextpthval%>/dwr/interface/lgdDwrCaptchaService.js'></script>
<script type="text/javascript" src="<%=contextpthval%>/external/jqueryCustom/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=contextpthval%>/external/jqueryCustom/js/TableTools.js" ></script>
<link href="<%=contextpthval%>/external/jqueryCustom/css/demo_table_jui.css" rel="stylesheet"  type="text/css" />
<script src="<%=contextpthval%>/JavaScriptServlet"></script>
<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		
		$('#tblViewLBTWards').dataTable({
			"bJQueryUI": true,
			"aoColumns":[{"bSortable": false}, null,{"bSortable": false},{"bSortable": false}, {"bSortable": false}],
			"sPaginationType": "full_numbers"
		});
	} );
	
	resetFrom = function(){
		displayLoadingImage();
		document.forms['lbTypeWiseWards'].method = "GET"; 
		document.forms['lbTypeWiseWards'].action = "lbWiseWardforCitizen.do?<csrf:token uri='lbWiseWardforCitizen.do'/>";
		document.forms['lbTypeWiseWards'].submit();
	};
	
	
	fetchLBTWards = function(){
 		var stateCode = $('#state').val();
 		if(stateCode == "-1"){
 			document.getElementById('errorstate').innerHTML="Please select a state.";
 			return false;
 		}
 		
 		var captchaAnswer = $('#captchaAnswer').val();
 		if(captchaAnswer == ""){
 			document.getElementById('errorCaptcha').innerHTML="Please enter captcha code shown above";
 			return false;
 		}
 		displayLoadingImage();
 		document.forms['lbTypeWiseWards'].method = "post";
		document.forms['lbTypeWiseWards'].action = "lbTypeWiseWardsData.do?<csrf:token uri='lbTypeWiseWardsData.do'/>";
		document.forms['lbTypeWiseWards'].submit();
		return true; 
 	 };
 	 
 	 
	showLBWards = function(localbodyTypeCode){
		var stateCode = $('#state').val();
 		if(stateCode == "-1"){
 			document.getElementById('errorstate').innerHTML="Please select a state.";
 			return false;
 		}
 		//displayLoadingImage();
 		document.forms['lbTypeWiseWards'].lbTypeCode.value = localbodyTypeCode;
		document.forms['lbTypeWiseWards'].method = "post";
		document.forms['lbTypeWiseWards'].action = "localBodyWiseWards.do?<csrf:token uri='localBodyWiseWards.do'/>";
		document.forms['lbTypeWiseWards'].submit();
		document.getElementById('backButton').show();
		return true;
 	 };
 	 
	clearMessage = function() {
		document.getElementById('errorstate').innerHTML="";
		document.getElementById('errorCaptcha').innerHTML="";
	};
 	 
</script>
</head>
<body>	
	<div class="frmhd">
		<h3 class="subtitle"><spring:message code="label.HeaderSummaryReportWard"></spring:message></h3>
		<ul class="listing">
			<!-- <li><a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"> <img alt="Toggle" src="images/plus.jpg" border="0" /></a></li> -->
			<%--//these links are not working <li><a href="#" class="cbthelp" onclick="open_win()"><spring:message htmlEscape="true"  code="Label.CBT"></spring:message></a></li>
			<li><a href="#" class="frmhelp"><spring:message htmlEscape="true" code="Label.HELP"></spring:message></a></li> --%>
		</ul>
		<c:if test="${!empty listLBTypeWiseWards}">
		<a id="showprint" href="#"><img src='<%=contextpthval%>/images/printer_icon.png' alt="Print" border="0" onclick="CallPrint();" /></a>	
		</c:if>
	</div>
	<div class="frmpnlbrdr">
		<form:form action="lbTypeWiseWardsData.do" id="lbTypeWiseWards" name="lbTypeWiseWards" commandName="lbTypeWiseWards">
		<input type="hidden" name="<csrf:token-name/>"    value="<csrf:token-value uri="lbTypeWiseWardsData.do"/>" />
			<input type="hidden" id="lbTypeCode" name="lbTypeCode"/>
			<div class="frmpnlbg" id='showbytext' <c:if test="${!enableSearch}">style="display: none;"</c:if>>
					<div class="frmtxt" id = "searchid">
						<div class="frmhdtitle" >
							<spring:message htmlEscape="true" code="Label.SEARCHCRITERIA"></spring:message>
						</div>
						
						<div class="search_criteria">
							<div class="block">
								<ul class="blocklist">
									<li>
										<label for="state"><spring:message htmlEscape="true" code="Label.SELECTSTATE"></spring:message><span class="mndt">*</span></label>
										<select id="state" class="combofield" name="state" style="width:175px" onchange="clearMessage();">
										<option value="-1"><spring:message htmlEscape="true" code="Label.SEL"/></option>
										<c:forEach var="states" items="${stateSourceList}">
											<option <c:if test="${states.statePK.stateCode == state}">selected="selected"</c:if> value="${states.statePK.stateCode}">
												<c:out value="${states.stateNameEnglish}" escapeXml="true"></c:out>
											</option>
										</c:forEach>
										</select>										
										<div id="errorstate" class="errormsg"><c:out value="${errorMsgState}" escapeXml="true"></c:out></div>
									</li>									
								</ul>
							</div>
							
							<div class="block">
								<ul class="captcha_fields">
									<li><img src="captchaImage" alt="Captcha Image" /></li>									
									<li>
										<label for="captchaAnswer"><spring:message code="captcha.message" htmlEscape="true"/><span class="mndt">*</span></label><br />
										<input type="text" id="captchaAnswer" name="captchaAnswer" onblur="clearMessage();"/>									
										<div id="errorCaptcha" class="errormsg"><c:if test="${invalidCaptcha}"><spring:message code="captcha.errorMessage" htmlEscape="true"/></c:if></div>
									</li>
									<li>
										<input type="submit" name="Submit" class="btn" onclick="javascript:fetchLBTWards();" value="<spring:message htmlEscape="true" code="Button.GETDATA"></spring:message>"/>
										<input type="button" name="Submit3" class="btn" onclick="javascript:location.href='lbWiseWardforCitizen.do?<csrf:token uri='lbWiseWardforCitizen.do'/>';" value="<spring:message htmlEscape="true"  code="Button.CLEAR"></spring:message>" />
									</li>
								</ul>
							</div>
							
							<div class="clear"></div>
						</div>						
						
				</div>
			</div>	
				
			<c:if test="${enableLBTypeWise}">
			<div class="frmpnlbg" id='showbytext' >
					<div class="frmtxt" align="center" id ="resultLBType">
						<div class="frmhdtitle" >
							<spring:message code="label.LocalBodyTypeWards" htmlEscape="true"/>
						</div>
						
						<table id="tblViewLBTWards" width="100%" >
							<thead>
								<tr >
									<th align="center" width="5%"><b><spring:message code="Label.SNO"/></b></th>
									<th align="center" width="30%"><b><spring:message code="Label.LGT"/></b></th>
									<th align="center" width="25%"><b><spring:message code="label.LocalbodyTypeNameEnglish"/></b></th>
									<th align="center" width="25%"><b><spring:message code="label.LocalbodyTypeNameLocal"/></b></th>
									<th align="center" width="15%"><b><spring:message code="label.TotalWards"/></b></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="data" items="${listLBTypeWiseWards}" varStatus="sn">
									<tr style="height: 30px;">		
										<td align="center"><c:out value="${sn.count}" escapeXml="true"></c:out></td>
										<c:choose>
											<c:when test="${data[2] gt 0}">
												<td align="left">
													<a href="#" onclick="return showLBWards('${data[0]}');"><c:out value="${data[1]}" escapeXml="true"></c:out></a>
												</td>
												<td>
													<a href="#" onclick="return showLBWards('${data[0]}');"><c:out value="${data[3]}" escapeXml="true"></c:out></a>
												</td>
												<td>
													<a href="#" onclick="return showLBWards('${data[0]}');"><c:out value="${data[4]}" escapeXml="true"></c:out></a>
												</td>	
												<td align="center">
													<a href="#" onclick="return showLBWards('${data[0]}');"><c:out value="${data[2]}" escapeXml="true"></c:out></a>
												</td>
											</c:when>
												<c:otherwise>
													<td align="left"><c:out value="${data[1]}" escapeXml="true"></c:out></td>
													<td><c:out value="${data[3]}" escapeXml="true"></c:out></td>
													<td><c:out value="${data[4]}" escapeXml="true"></c:out></td>	
													<td align="center"><c:out value="${data[2]}" escapeXml="true"></c:out></td>
												</c:otherwise> 
											</c:choose>
									</tr>
								</c:forEach>
							</tbody>
						</table> 
						
						<div class="buttons center">
							<input type="button" id="backButton" style="display: none;" name="close" class="btn" onclick="resetFrom();" value="<spring:message htmlEscape="true"  code="Button.BACK"></spring:message>" />
							<!-- added by sunita on 07-7-2015  -->
							<input type="button" name="close" class="btn" onclick="javascript:window.location.href='welcome.do'" value="<spring:message htmlEscape="true"  code="Button.CLOSE"></spring:message>" />
						</div>
						
				</div>
			</div>	
			</c:if>
		</form:form>
		<script src="/LGD/JavaScriptServlet"></script>
	</div>
</body>
</html>