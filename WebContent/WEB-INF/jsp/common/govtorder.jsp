<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<%@include file="../common/taglib_includes.jsp"%>
<%!String contextPath;%>
<title>E-Panchayat</title>
<link href="css/errorLB.css" rel="stylesheet" type="text/css" />

<script src="js/validation.js"></script>
<script src="js/successMessage.js"></script>
<script src="js/helpMessage.js"></script>
<script src="js/trim-jquery.js"></script>
<link href="css/successMessage.css" rel="stylesheet" type="text/css" />

<script src="js/govtorder.js"></script>
<script src="js/common.js"></script>
<%--<link href="datepicker/calender.css" rel="stylesheet" type="text/css" />
<script src="datepicker/jquery-1.6.2.js"></script>
<script src="datepicker/jquery.ui.core.js"></script>
<script src="datepicker/jquery.ui.widget.js"></script>
<script src="datepicker/jquery.ui.datepicker.js"></script>
<script src="datepicker/calender.js"></script>
<link rel="stylesheet" href="datepicker/demos.css" />--%>
<script type="text/javascript" language="javascript">
	
</script>

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
								<center><c:out value="${param.family_msg}" escapeXml="false"></c:out></center>
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
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="false"></c:out></div>
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
			<table width="100%" class="tbl_no_brdr">
				<tr>
					<td><spring:message code="Label.UGO" htmlEscape="true"></spring:message>
					</td>
					<td align="right"><a href="#" rel="toggle[cat]"
						data-openimage="images/minus.jpg"
						data-closedimage="images/plus.jpg"><img src="images/plus.jpg"
							border="0" /> </a>
					</td>
					<%--//these links are not working <td width="50" align="right"><a href="#" class="frmhelp"><spring:message
								code="Button.HELP" htmlEscape="true"></spring:message> </a></td> --%>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">

			<form:form id="uploadGovtOrder" commandName="governmentOrder"
				onsubmit="cursorwait();" action="saveGovtOrder.htm" method="POST"
				enctype="multipart/form-data">
				<input type="hidden" name="<csrf:token-name/>"
					value="<csrf:token-value uri="saveGovtOrder.htm"/>" />
				<div id="cat">
					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.GOVTORDERDETAILS" htmlEscape="true"></spring:message>
							</div>
							<table width="100%" class="tbl_no_brdr">
								<tr>
									<td colspan="2">
										<table width="100%" class="tbl_no_brdr">
											<tr>

											</tr>
										</table></td>
								</tr>

								<tr>
									<td width="14%" rowspan="7">&nbsp;</td>
									<td width="86%"><label><spring:message
												code="Label.ORDERNO" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:input path="orderNo"
											id="OrderNo" type="text" class="frmfield" htmlEscape="true"
											 style="width: 140px"
											onkeypress="validateNumericAlphaKeys();" onfocus="validateOnFocus('OrderNo');helpMessage(this,'OrderNo_msg');"
											onblur="vlidateOnblur('OrderNo','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('OrderNo')" /><span
										id="OrderNo_msg" style="display: none;">Please Enter
											Order No Like N893,AU435</span> <span class="errormsg"
										id="OrderNo_error"></span> <form:errors path="orderNo2"
											cssClass="errormsg" htmlEscape="true"></form:errors> <form:errors
											path="orderNo1" cssClass="errormsg" htmlEscape="true"></form:errors></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><label><spring:message code="Label.ORDERDATE" htmlEscape="true"></spring:message>
									</label><span class="errormsg">*</span><br /> <form:input
											path="orderDate" id="OrderDate" type="text" class="frmfield"
											style="width: 140px"  
											onchange="setEffectiveDate(this.value);"
											onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('OrderDate');helpMessage(this,'OrderDate_msg');"
											onblur="vlidateOnblur('OrderDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('OrderDate')" /> <span id="OrderDate_msg"
										style="display: none">Please Enter Order Date Like
											12-04-2012</span><span class="errormsg" id="OrderDate_error"></span>
										<form:errors path="orderDate2" cssClass="errormsg" htmlEscape="true"></form:errors>
										<form:errors path="orderDate1" cssClass="errormsg" htmlEscape="true"></form:errors>
									</td>

								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><label><spring:message
												code="Label.EFFECTIVEDATE" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:input 
											id="EffectiveDate" path="effectiveDate" type="text"
											class="frmfield" style="width: 140px"
											  onkeypress="validateNumeric();" 
											onfocus="validateOnFocus('EffectiveDate');helpMessage(this,'EffectiveDate_msg');"
											onblur="vlidateOnblur('EffectiveDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('EffectiveDate')" /><span
										class="errormsg" id="EffectiveDate_error"></span> <form:errors
											path="effectiveDate2" cssClass="errormsg" htmlEscape="true"></form:errors> <form:errors
											path="effectiveDate1" cssClass="errormsg" htmlEscape="true"></form:errors> <span
										id="EffectiveDate_msg" style="display: none;">Please
											Enter Effective Date Like 12-04-2012</span></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								 
									<tr>
										<td><label><spring:message
													code="Label.GAZPUBDATE" htmlEscape="true"></spring:message> </label><br /> <form:input
												id="GazPubDate" path="gazPubDate" type="text"
												class="frmfield" style="width: 140px"
												  onkeypress="validateNumeric();" 
												onfocus="validateOnFocus('GazPubDate');helpMessage(this,'GazPubDate_msg');"
											onblur="vlidateOnblur('GazPubDate','1','15','m');hideHelp();"
											onkeyup="hideMessageOnKeyPress('GazPubDate')"/>
											<span class="errormsg" id="GazPubDate_error"></span> <form:errors
												path="gazPubDate2" cssClass="errormsg" htmlEscape="true"></form:errors>
												 
												 <span
											id="GazPubDate_msg" style="display: none">Please Enter
												Gazette Publication Date Like 12-04-2012</span></td>
									</tr>
							 
								
							 
									<tr>
										<td width="100%" colspan="2">
											<table width="100%">
												<tr>
													<td width="12%">&nbsp;</td>
													<td width="88%"><%@ include
															file="../common/attachement.jspf"%></td>

												</tr>
											</table></td>
									</tr>

							 
								<tr>
									<td>&nbsp;</td>
								</tr>
								
								<tr>
									<td width="14%" rowspan="2">&nbsp;</td>
									<td><label><input type="submit" name="Submit2"
											class="btn" style="width: 80" onclick="return validateGovtOrderDetails();"
											value="<spring:message code="Button.SAVE" htmlEscape="true"></spring:message>"
											id="btnSave" /> </label> <label> <input type="button"
											name="Submit2" class="btn"
											value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>"
											id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" /> </label></td>
								</tr>
								<tr>
									<td>&nbsp;</td>

								</tr>
							</table>
						</div>

					</div>
				</div>
			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
		</div>
	</div>
</body>
</html>