<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../common/taglib_includes.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<head>

<title>E-Panchayat</title>


<link href="css/errorLB.css" rel="stylesheet" type="text/css" />

<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>

<script src="js/trim-jquery.js"></script>
<link href="css/error.css" rel="stylesheet" type="text/css" />
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.9.1.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>

<link rel="stylesheet" href="datepicker/demos.css" />
<!-- Added by Sushil on 17-11-2014 -->
<script type="text/javascript" language="javascript">
	
	$(document).ready(function() {	
		if('${preVersionMaxEffDate}' != null && '${preVersionMaxEffDate}' != ""){
			$('#preVersionEffDate').val('${preVersionMaxEffDate}');
		}
	});
	
	$(function() {
		$( "#OrderDate" ).datepicker({dateFormat: 'dd-mm-yy',
			value: new Date(<%=currentYear%>, <%=currentMonth%>, <%=currentDate%>),
			changeMonth: true,
			changeYear: true
		});	
	});
	
	$(function() {
	$( "#EffectiveDate" ).datepicker({dateFormat: 'dd-mm-yy',
		value: new Date(<%=currentYear%>, <%=currentMonth%>, <%=currentDate%>),
		changeMonth: true,
		changeYear: true
	});
	});
	
	$(function() {
	$( "#GazPubDate" ).datepicker({dateFormat: 'dd-mm-yy',
		value: new Date(<%=currentYear%>, <%=currentMonth%>, <%=currentDate%>),
		changeMonth: true,
		changeYear: true
	});
	});
</script>
<script src="datepicker/calender.js"></script>
</head>

<body oncontextmenu="return false"
	onkeypress="disableCtrlKeyCombination(event);"
	onkeydown="disableCtrlKeyCombination(event);">

	<div class="overlay" id="overlay1" style="display: none;"></div>
	<div class="box" id="box1">
		<a class="boxclose" id="boxclose1"></a>
		<div>
			<c:if test="${!empty param.family_msg}">
				<table>
					<tr>
						<td rowspan="2"><center>
								<div class="success"></div>
							</center></td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div></td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}"></c:out></center>
							</div>
						</td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div></td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center></td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}"></c:out></div>
						</td>
					</tr>
				</table>

			</c:if>

		</div>
	</div>

	<div class="box" id="box">
		<a class="boxclose" id="boxclose"></a>
		<div id="validate_error">
			<table>
				<tr>
					<td rowspan="2"><div class="errorImg"></div></td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div></td>
				</tr>
				<tr>
					<td><div class="errorfont"><spring:message htmlEscape="true" code="Error.entermandatoryfield"></spring:message></div>
					</td>
				</tr>
			</table>

		</div>
	</div>

	<div id="helpDiv" class="helpMessage">
		<div class="helpheader">
			<h4>Help Message</h4>
		</div>
		<div id="helpMsgText" class="helptext"></div>
	</div>

	<div id="frmcontent">
		<div class="frmhd">
		
			<h3 class="subtitle"><spring:message code="Label.UGO" htmlEscape="true"></spring:message></h3>
										 <ul id="showhelp" class="listing">
					 				        <li>
					 				        <a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" 	data-closedimage="images/plus.jpg"><img src="images/plus.jpg"	border="0" /> </a>                     
					 				        </li>
					 				       <li>    
					 				       		<a href="#" class="frmhelp"><spring:message	code="Button.HELP" htmlEscape="true"></spring:message> </a>
					 				       </li>
					 					  </ul>
									
		
		
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">

			<form:form id="uploadGovtOrder" commandName="governmentOrder"
				onsubmit="cursorwait();" action="publishform.htm" method="POST"
				enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="publishform.htm"/>" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
							</div>
							
							<ul class="blocklist">
								<li>				<label for="OrderNo"><spring:message htmlEscape="true"
												code="Label.ORDERNO"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:input path="orderNo" htmlEscape="true"
											id="OrderNo" type="text" class="frmfield"
											style="width: 140px" maxLength="60"
											onfocus="helpMessage(this,'OrderNo_error');"
											onblur="vlidateOrderNo('OrderNo','1','60');hideHelp();"
											onkeypress="return validateaGovtOrderNO(event);"/>
											
											
											<div id="OrderNo_error" class="error"><spring:message code="error.required.ORDERNUM" htmlEscape="true"></spring:message></div>
											<div id="OrderNo_msg" class="error"><spring:message code="error.required.ORDERINPUTTYPE" text="Please Enter AlphaNumerics Space . / - ( ) Only" htmlEscape="true"/></div>
											<div class="errormsg" id="OrderNo_error1"><form:errors path="orderNo" htmlEscape="true"/></div>  
											<div class="errormsg" id="OrderNo_error2" style="display: none" ></div>
								
								</li>
								<li>
												<label for="OrderDate"><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
									</label><span class="errormsg">*</span><br />
									<form:input path="orderDate" readonly="true" id="OrderDate" type="text" class="frmfield" style="width: 140px" onchange="setEffectiveDate(this.value);" onkeypress="validateNumeric();"
											onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');" onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('OrderDate')" />
											<%-- <span id="OrderDate_msg"
										style="display: none">Please Enter Order Date Like
											12-04-2012</span><span class="errormsg" id="OrderDate_error"></span>
										<form:errors path="orderDate2" cssClass="errormsg" htmlEscape="true"></form:errors>
										<form:errors path="orderDate1" cssClass="errormsg" htmlEscape="true"></form:errors> --%>
											
											<div id="OrderDate_error" class="error"><spring:message code="error.required.ORDERDATE" htmlEscape="true"></spring:message></div>
											<div id="OrderDate_msg" style="display:none"><spring:message code="error.required.ORDERDATE" htmlEscape="true"/></div>
											<div class="errormsg" id="OrderDate_error1"><form:errors path="orderDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="OrderDate_error2" style="display: none" ></div>
										
								</li>
								<li>
										<label for="EffectiveDate"><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:input id="EffectiveDate" readonly="true" path="effectiveDate" type="text" class="frmfield" style="width: 140px" onkeypress="validateNumeric();"
											onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');" onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();" onkeyup="hideMessageOnKeyPress('EffectiveDate')" />
										
<!-- 										<span class="errormsg" id="EffectiveDate_error"></span> -->
<%-- 										<form:errors path="effectiveDate2" cssClass="errormsg" htmlEscape="true"/> --%>
<%-- 										<form:errors path="effectiveDate1" cssClass="errormsg" htmlEscape="true"/> --%>
<!-- 										<span id="EffectiveDate_msg" style="display: none;">Please Enter Effective Date Like 12-04-2012</span> -->
										
										<div id="EffectiveDate_error" class="error"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"></spring:message></div>
											<div id="EffectiveDate_msg" style="display:none"><spring:message code="error.required.EFFECTIVEDATE" htmlEscape="true"/></div>
											<div class="errormsg" id="EffectiveDate_error1"><form:errors path="effectiveDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="EffectiveDate_error2" style="display: none" ></div>
											<input type="hidden" name="preVersionEffDate" id="preVersionEffDate" />
											
								</li>
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
								<li>
									<label for="GazPubDate"><spring:message
													code="Label.GAZPUBDATE" htmlEscape="true"></spring:message> </label><br /> <form:input
												id="GazPubDate" path="gazPubDate" type="text"  
												class="frmfield" style="width: 140px"
												onkeypress="validateNumeric();" 
												onfocus="validateOnFocus('GazPubDate');helpMessage(this,'GazPubDate_msg');"
											onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('GazPubDate')"/>
											<%-- <span class="errormsg" id="GazPubDate_error"></span> <form:errors
												path="gazPubDate2" cssClass="errormsg" htmlEscape="true"></form:errors> <span
											id="GazPubDate_msg" style="display: none">Please Enter
												Gazette Publication Date Like 12-04-2012</span> --%>
											<div id="GazPubDate_error" class="error"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"></spring:message></div>
											<div id="GazPubDate_msg" style="display:none"><spring:message code="GAZPUBDATE.REQUIRED" htmlEscape="true"/></div>
											<div class="errormsg" id="GazPubDate_error1"><form:errors path="gazPubDate" htmlEscape="true"/></div>  
											<div class="errormsg" id="GazPubDate_error2" style="display: none" ></div>	
								</li>
								</c:if>
								
								
								<li>
										<form:hidden path="govtOrderConfig"
											value="${govtOrderConfig}" id="hdngovtOrderConfig" />
								</li>
								
								<c:if test="${govtOrderConfig == 'govtOrderUpload'}">
									<li>
											<table width="100%">
												<tr>
<!-- 													<td width="12%">&nbsp;</td> -->
													<td width="88%"><%@ include
															file="../common/attachement.jspf"%></td>

												</tr>
											</table>
									</li>
								</c:if>
								
								<c:if test="${govtOrderConfig == 'govtOrderGenerate'}">
								<li><label><spring:message htmlEscape="true" code="Label.SELGOT"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:select
												path="templateList" id="templateList" style="width:280px"
												class="combofield" 
												onblur="vlidateOnblur('templateList','1','15','m');hideHelp();"
												onfocus="validateOnFocus('templateList');helpMessage(this,'templateList_msg');"
												onkeyup="hideMessageOnKeyPress('templateList')">
												<form:option value="0"><spring:message htmlEscape="true" code="Label.SELECT" ></spring:message></form:option>
												<form:options items="${templateList}"
													itemLabel="templateNameEnglish" itemValue="templateCode"></form:options>
											</form:select> <%-- <span class="errormsg" id="templateList_error"></span> <span><form:errors
													cssClass="errormsg" path="templateList" htmlEscape="true"></form:errors> </span> <span
											style="display: none;" id="templateList_msg">Please
												Select Government order Template</span> --%>
												<div id="templateList_error" class="error"><spring:message code="error.blank.template" htmlEscape="true"></spring:message></div>
												<div id="templateList_msg" style="display:none"><spring:message code="error.blank.template" htmlEscape="true"/></div>
												<div class="errormsg" id="templateList_error1"><form:errors path="templateList" htmlEscape="true"/></div>  
												<div class="errormsg" id="templateList_error2" style="display: none" ></div>
											</li>
								</c:if>
								
								<li>
											<label>
												<input type="submit" name="Submit2" class="btn" style="width: 80" onclick="return validateGovtOrder1();" value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>" id="btnSave" /> </label> <label>
												<input type="button" name="Submit2" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
											</label>
										
											
								</li>
							
							</ul>
							
							
						</div>

					</div>
				</div>
			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>