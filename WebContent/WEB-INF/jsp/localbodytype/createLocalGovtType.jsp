<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../common/taglib_includes.jsp"%>

<%!String contextPath;%>
<%
	contextPath = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>E-Panchayat</title> <script src="js/govtorder.js"></script>

	<script src="js/validation.js"></script>
	<script src="js/trim-jquery.js"></script>
	<script src="js/successMessage.js"></script>
	<script src="js/helpMessage.js"></script>
	<link href="css/successMessage.css" rel="stylesheet" type="text/css" />
	<script src="js/common.js"></script>
	<link href="css/error.css" rel="stylesheet" type="text/css" />

	<script src="js/createlocalgovttype.js" type="text/javascript"></script>


	<script type="text/javascript" language="Javascript">
	</script>
	<script language="javascript">
	if ( window.addEventListener ) { 
	    window.addEventListener( "load",hidediv, false );
	 }
	 else 
	    if ( window.attachEvent ) { 
	       window.attachEvent( "onload", hidediv );
	 } else 
	       if ( window.onLoad ) {
	          window.onload = hidediv;
	 }

	
	</script>
</head>
<body onload="hidediv();" oncontextmenu="return false"
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
							</center>
						</td>

						<td><div class="helpMsgHeader" style="width: 275px;">
								<h4>Success Message</h4>
							</div>
						</td>
					</tr>
					<tr>
						<td><div id="successMsg" class="successfont">
								<center><c:out value="${param.family_msg}" escapeXml="true"></c:out></center>
							</div></td>
					</tr>
				</table>

			</c:if>

			<c:if test="${!empty family_error}">

				<table>
					<tr>
						<td rowspan="2"><div class="failur"></div>
						</td>

						<td><center>
								<div class="helpMsgHeader" style="width: 275px;">
									<b>Failure Message</b>
								</div>
							</center>
						</td>
					</tr>
					<tr>
						<td><div id="failurMsg" class="errorfont"><c:out value="${family_error}" escapeXml="true"></c:out></div>
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
					<td rowspan="2"><div class="errorImg"></div>
					</td>
					<td><div class="helpMsgHeader" style="width: 275px;">
							<h4>Error Message</h4>
						</div>
					</td>
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
			<h3 class="subtitle">
				<spring:message code="Label.CREATELOCALGOVTTYPE" htmlEscape="true"></spring:message>
			</h3>
			<ul class="listing">
				<li>
					<a href="#" rel="toggle[cat]" data-openimage="images/minus.jpg" data-closedimage="images/plus.jpg"><img src="images/plus.jpg" border="0" /></a>
				</li>
				<%--//this link is not working <li> 
					<a href="#" class="frmhelp"><spring:message code="Button.HELP" htmlEscape="true"></spring:message> </a>
				</li> --%>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="frmpnlbrdr">
			<form:form method="POST" commandName="createlocalgovtType"
				action="draftLocalgovtType.htm">
				
				<input type="hidden" name="<csrf:token-name/>" value="<csrf:token-value uri="draftLocalgovtType.htm"/>"/>
				<div id="cat">

					<div class="frmpnlbg">
						<div class="frmtxt">
							<div class="frmhdtitle">
								<spring:message code="Label.CREATELOCALGOVTTYPE" htmlEscape="true"></spring:message>
							</div>
								<form:hidden path="govtOrderConfig" htmlEscape="true" value="${govtOrderConfig}"/>
								<ul class = "listing">
									<li>	 
									<label for = "txtlocalBodyTypeName"><spring:message
												code="Label.LOCALGOVTTYPENAME" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span> <br /> <form:input htmlEscape="true"
											path="localBodyTypeName" style="width: 250px"
											class="frmfield" onkeypress="validateAlphanumericKeys();"
											id="txtlocalBodyTypeName"
											onfocus="validateOnFocus('txtlocalBodyTypeName'); helpMessage(this,'txtlocalBodyTypeName_msg');"
											onblur="vlidateOnblur('txtlocalBodyTypeName','1','20','m'); hideHelp();" />
										<div><form:errors path="localBodyTypeName" htmlEscape="true"
												class="errormsg"></form:errors> </div> <div
										id="txtlocalBodyTypeName_msg" style="display: none"> <spring:message
												code="error.blank.localBodyTypeName" htmlEscape="true"></spring:message> </div> <div
										class="errormsg" id="txtlocalBodyTypeName_error"> <spring:message
												code="error.blank.localBodyTypeName" htmlEscape="true"></spring:message> </div>
										<div>
											<form:errors path="localBodyTypeName1" class="errormsg" htmlEscape="true"></form:errors>
										</div>
									<div class="errormsg"><c:out value="${msgid}" escapeXml="true"></c:out></div>
									</li>
									<li>
									<label for = "urbanId"><spring:message
												code="Label.CATLOCALGOVTTYPE" htmlEscape="true"></spring:message> </label><span
										class="errormsg">*</span><br /> <form:radiobutton
											path="categoryRadio" value="U" id="urbanId" htmlEscape="true"
											onclick="showdiv(this.value);"  /> <spring:message
											code="Label.ULG" htmlEscape="true"></spring:message> &nbsp; &nbsp; <form:radiobutton
											path="categoryRadio" value="R" id="ruralId"
											onclick="showdiv(this.value);" htmlEscape="true"/> <spring:message
											code="Label.RURALG" htmlEscape="true"></spring:message>
											&nbsp;&nbsp;&nbsp;&nbsp;
											 <div> <form:errors
												path="categoryRadio" class="errormsg" htmlEscape="true"></form:errors> </div> <div
										id="urbanId_msg" style="display: none"> <spring:message
												code="error.blank.category" htmlEscape="true"></spring:message> </div> <div
										class="errormsg" id="urbanId_error" > <spring:message
												code="error.blank.category" htmlEscape="true" ></spring:message> </div>
									</li>
								</ul>
								<div id="divRCategory" class="block">
											<ul class = "blocklist">
												<li>	
													<label for = "ddRuralCategory"><spring:message
																code="Label.RURALCATEGORY" htmlEscape="true"></spring:message>
													</label> <span class="errormsg">*</span><br /> <form:select
															path="ruralCategory" id="ddRuralCategory"  
															style="width: 200px" class="combofield"
															onfocus="validateOnFocus('ddRuralCategory');helpMessage(this,'ddRuralCategory_msg');"
															onblur="vlidateOnblur('ddRuralCategory','1','15','c');hideHelp();"
															>
															<form:option value="S" >
																<spring:message code="Label.SELECT"  htmlEscape="true"></spring:message>
															</form:option>
															<form:option value="P">
																<spring:message code="Label.PRI" htmlEscape="true"></spring:message>
															</form:option>
															<form:option value="T" >
																<spring:message code="Label.TRADITIONALBODY"  htmlEscape="true"></spring:message>
															</form:option>
														</form:select> <div><form:errors path="ruralCategory" htmlEscape="true"
																class="errormsg"></form:errors> </div> <div
														id="ddRuralCategory_msg" style="display: none"> <spring:message
																code="error.blank.Rcategory" htmlEscape="true"></spring:message> </div> <div
														class="errormsg" id="ddRuralCategory_error"> <spring:message
																code="error.blank.Rcategory" htmlEscape="true"></spring:message> </div>
												</li>
												<li>
												<label for = "ddlevel"><spring:message
																code="Label.LEVEL" htmlEscape="true"></spring:message> </label><span
														class="errormsg">*</span> <br /> <form:select
															path="level" id="ddlevel" style="width: 200px;"
															class="combofield"
															onfocus="validateOnFocus('ddlevel');helpMessage(this,'ddlevel_msg');"
															onblur="vlidateOnblur('ddlevel','1','15','c');hideHelp();"
															>
															<form:option value="S">
																<spring:message code="Label.SELECT" htmlEscape="true"></spring:message>
															</form:option>
															<form:option value="D">
																<spring:message code="Label.DISTRICT" htmlEscape="true"></spring:message>
															</form:option>
															<form:option value="I">
																<spring:message code="Label.INTERMEDIATE" htmlEscape="true"></spring:message>
															</form:option>
															<form:option value="V">
																<spring:message code="Label.VILLAGE" htmlEscape="true"></spring:message>
															</form:option>
														</form:select> <div><form:errors path="level" class="errormsg" htmlEscape="true"></form:errors>
													</div> <div id="ddlevel_msg" style="display: none"> <spring:message
																code="error.blank.level" htmlEscape="true"></spring:message> </div> <div
														class="errormsg" id="ddlevel_error"> <spring:message
																code="error.blank.level" htmlEscape="true"></spring:message> </div>
												</li>
											</ul>
										</div>
						</div>

					</div>

					<div class="btnpnl">
								<label>
									<input type="submit" class="btn" name="Submit" onclick="return validateAll();" style="width: 100px" value="<spring:message code="Button.SAVEPUB" htmlEscape="true"></spring:message>" />
								</label>
								 
								<label> 
									<input type="button" class="btn" name="Submit6" value="<spring:message code="Button.CLEAR" htmlEscape="true"></spring:message>" onclick="javascript:location.href='localgovtType.htm?<csrf:token uri='localgovtType.htm'/>';" /> 
								</label> 
								<label>
									<input type="button" name="Submit2" class="btn" value="<spring:message code="Button.CLOSE" htmlEscape="true"></spring:message>" id="btnCancel" onclick="javascript:location.href='home.htm?<csrf:token uri='home.htm'/>';" />
								 </label>
					</div>
				</div>

			</form:form>
			<script src="/LGD/JavaScriptServlet"></script>
			
		</div>
	</div>
</body>
</html>